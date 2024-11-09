package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/GdxRuntimeException.class */
public class GdxRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 6735854402467673117L;

    public GdxRuntimeException(String str) {
        super(str);
    }

    public GdxRuntimeException(Throwable th) {
        super(th);
    }

    public GdxRuntimeException(String str, Throwable th) {
        super(str, th);
    }
}
