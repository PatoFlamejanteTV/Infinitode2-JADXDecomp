package org.jsoup.helper;

import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import org.jsoup.helper.RequestAuthenticator;

/* loaded from: infinitode-2.jar:org/jsoup/helper/AuthenticationHandler.class */
class AuthenticationHandler extends Authenticator {
    static final int MaxAttempts = 5;
    static AuthShim handler;
    RequestAuthenticator auth;
    int attemptCount = 0;

    /* loaded from: infinitode-2.jar:org/jsoup/helper/AuthenticationHandler$AuthShim.class */
    interface AuthShim {
        void enable(RequestAuthenticator requestAuthenticator, HttpURLConnection httpURLConnection);

        void remove();

        AuthenticationHandler get(AuthenticationHandler authenticationHandler);
    }

    static {
        try {
            handler = (AuthShim) Class.forName("org.jsoup.helper.RequestAuthHandler").getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            handler = new GlobalHandler();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    AuthenticationHandler() {
    }

    AuthenticationHandler(RequestAuthenticator requestAuthenticator) {
        this.auth = requestAuthenticator;
    }

    @Override // java.net.Authenticator
    public final PasswordAuthentication getPasswordAuthentication() {
        AuthenticationHandler authenticationHandler = handler.get(this);
        if (authenticationHandler == null) {
            return null;
        }
        authenticationHandler.attemptCount++;
        if (authenticationHandler.attemptCount > 5 || authenticationHandler.auth == null) {
            return null;
        }
        return authenticationHandler.auth.authenticate(new RequestAuthenticator.Context(getRequestingURL(), getRequestorType(), getRequestingPrompt()));
    }

    /* loaded from: infinitode-2.jar:org/jsoup/helper/AuthenticationHandler$GlobalHandler.class */
    static class GlobalHandler implements AuthShim {
        static ThreadLocal<AuthenticationHandler> authenticators = new ThreadLocal<>();

        GlobalHandler() {
        }

        static {
            Authenticator.setDefault(new AuthenticationHandler());
        }

        @Override // org.jsoup.helper.AuthenticationHandler.AuthShim
        public void enable(RequestAuthenticator requestAuthenticator, HttpURLConnection httpURLConnection) {
            authenticators.set(new AuthenticationHandler(requestAuthenticator));
        }

        @Override // org.jsoup.helper.AuthenticationHandler.AuthShim
        public void remove() {
            authenticators.remove();
        }

        @Override // org.jsoup.helper.AuthenticationHandler.AuthShim
        public AuthenticationHandler get(AuthenticationHandler authenticationHandler) {
            return authenticators.get();
        }
    }
}
