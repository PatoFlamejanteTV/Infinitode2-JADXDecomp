package org.a.c.h.c;

import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;

/* loaded from: infinitode-2.jar:org/a/c/h/c/f.class */
public final class f extends a {

    /* renamed from: a, reason: collision with root package name */
    private String f4499a;

    /* renamed from: b, reason: collision with root package name */
    private KeyStore f4500b;
    private String c;

    public f(KeyStore keyStore, String str, String str2) {
        this.f4499a = null;
        this.f4500b = null;
        this.c = null;
        this.f4500b = keyStore;
        this.c = str;
        this.f4499a = str2;
    }

    public final X509Certificate a() {
        if (this.f4500b.size() == 1) {
            return (X509Certificate) this.f4500b.getCertificate(this.f4500b.aliases().nextElement());
        }
        if (this.f4500b.containsAlias(this.c)) {
            return (X509Certificate) this.f4500b.getCertificate(this.c);
        }
        throw new KeyStoreException("the keystore does not contain the given alias");
    }

    public final Key b() {
        try {
            if (this.f4500b.size() == 1) {
                return this.f4500b.getKey(this.f4500b.aliases().nextElement(), this.f4499a.toCharArray());
            }
            if (this.f4500b.containsAlias(this.c)) {
                return this.f4500b.getKey(this.c, this.f4499a.toCharArray());
            }
            throw new KeyStoreException("the keystore does not contain the given alias");
        } catch (NoSuchAlgorithmException e) {
            throw new KeyStoreException("the algorithm necessary to recover the key is not available", e);
        } catch (UnrecoverableKeyException e2) {
            throw new KeyStoreException("the private key is not recoverable", e2);
        }
    }
}
