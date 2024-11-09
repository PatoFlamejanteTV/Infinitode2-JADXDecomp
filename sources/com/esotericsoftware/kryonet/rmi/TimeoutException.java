package com.esotericsoftware.kryonet.rmi;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/TimeoutException.class */
public class TimeoutException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public TimeoutException() {
    }

    public TimeoutException(String str, Throwable th) {
        super(str, th);
    }

    public TimeoutException(String str) {
        super(str);
    }

    public TimeoutException(Throwable th) {
        super(th);
    }
}
