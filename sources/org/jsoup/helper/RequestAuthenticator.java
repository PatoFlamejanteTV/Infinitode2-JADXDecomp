package org.jsoup.helper;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;

@FunctionalInterface
/* loaded from: infinitode-2.jar:org/jsoup/helper/RequestAuthenticator.class */
public interface RequestAuthenticator {
    PasswordAuthentication authenticate(Context context);

    /* loaded from: infinitode-2.jar:org/jsoup/helper/RequestAuthenticator$Context.class */
    public static class Context {
        private final URL url;
        private final Authenticator.RequestorType type;
        private final String realm;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Context(URL url, Authenticator.RequestorType requestorType, String str) {
            this.url = url;
            this.type = requestorType;
            this.realm = str;
        }

        public URL url() {
            return this.url;
        }

        public Authenticator.RequestorType type() {
            return this.type;
        }

        public String realm() {
            return this.realm;
        }

        public boolean isProxy() {
            return this.type == Authenticator.RequestorType.PROXY;
        }

        public boolean isServer() {
            return this.type == Authenticator.RequestorType.SERVER;
        }

        public PasswordAuthentication credentials(String str, String str2) {
            return new PasswordAuthentication(str, str2.toCharArray());
        }
    }
}
