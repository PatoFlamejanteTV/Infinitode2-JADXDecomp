package com.esotericsoftware.kryonet;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/KryoNetException.class */
public class KryoNetException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public KryoNetException() {
    }

    public KryoNetException(String str, Throwable th) {
        super(str, th);
    }

    public KryoNetException(String str) {
        super(str);
    }

    public KryoNetException(Throwable th) {
        super(th);
    }
}
