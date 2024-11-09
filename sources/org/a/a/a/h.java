package org.a.a.a;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.PrivilegedAction;
import java.util.Properties;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/a/a/h.class */
public class h implements PrivilegedAction {

    /* renamed from: a, reason: collision with root package name */
    private final URL f4190a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(URL url) {
        this.f4190a = url;
    }

    @Override // java.security.PrivilegedAction
    public final Object run() {
        InputStream inputStream = null;
        try {
            try {
                URLConnection openConnection = this.f4190a.openConnection();
                openConnection.setUseCaches(false);
                InputStream inputStream2 = openConnection.getInputStream();
                if (inputStream2 == null) {
                    if (inputStream2 == null) {
                        return null;
                    }
                    try {
                        inputStream2.close();
                        return null;
                    } catch (IOException unused) {
                        if (!c.c()) {
                            return null;
                        }
                        c.a(new StringBuffer("Unable to close stream for URL ").append(this.f4190a).toString());
                        return null;
                    }
                }
                Properties properties = new Properties();
                properties.load(inputStream2);
                inputStream2.close();
                inputStream = null;
                if (0 != 0) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                        if (c.c()) {
                            c.a(new StringBuffer("Unable to close stream for URL ").append(this.f4190a).toString());
                        }
                    }
                }
                return properties;
            } catch (IOException unused3) {
                if (c.c()) {
                    c.a(new StringBuffer("Unable to read URL ").append(this.f4190a).toString());
                }
                if (inputStream == null) {
                    return null;
                }
                try {
                    inputStream.close();
                    return null;
                } catch (IOException unused4) {
                    if (!c.c()) {
                        return null;
                    }
                    c.a(new StringBuffer("Unable to close stream for URL ").append(this.f4190a).toString());
                    return null;
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused5) {
                    if (c.c()) {
                        c.a(new StringBuffer("Unable to close stream for URL ").append(this.f4190a).toString());
                    }
                }
            }
            throw th;
        }
    }
}
