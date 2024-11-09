package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/VerifyException.class */
public class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(String str) {
        super(str);
    }

    public VerifyException(Throwable th) {
        super(th);
    }

    public VerifyException(String str, Throwable th) {
        super(str, th);
    }
}
