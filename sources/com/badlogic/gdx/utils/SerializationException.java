package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/SerializationException.class */
public class SerializationException extends RuntimeException {
    private StringBuilder trace;

    public SerializationException() {
    }

    public SerializationException(String str, Throwable th) {
        super(str, th);
    }

    public SerializationException(String str) {
        super(str);
    }

    public SerializationException(Throwable th) {
        super("", th);
    }

    public boolean causedBy(Class cls) {
        return causedBy(this, cls);
    }

    private boolean causedBy(Throwable th, Class cls) {
        Throwable cause = th.getCause();
        if (cause == null || cause == th) {
            return false;
        }
        if (cls.isAssignableFrom(cause.getClass())) {
            return true;
        }
        return causedBy(cause, cls);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (this.trace == null) {
            return super.getMessage();
        }
        StringBuilder stringBuilder = new StringBuilder(512);
        stringBuilder.append(super.getMessage());
        if (stringBuilder.length() > 0) {
            stringBuilder.append('\n');
        }
        stringBuilder.append("Serialization trace:");
        stringBuilder.append(this.trace);
        return stringBuilder.toString();
    }

    public void addTrace(String str) {
        if (str == null) {
            throw new IllegalArgumentException("info cannot be null.");
        }
        if (this.trace == null) {
            this.trace = new StringBuilder(512);
        }
        this.trace.append('\n');
        this.trace.append(str);
    }
}
