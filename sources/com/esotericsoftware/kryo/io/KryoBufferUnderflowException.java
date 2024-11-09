package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/KryoBufferUnderflowException.class */
public class KryoBufferUnderflowException extends KryoException {
    public KryoBufferUnderflowException() {
    }

    public KryoBufferUnderflowException(String str, Throwable th) {
        super(str, th);
    }

    public KryoBufferUnderflowException(String str) {
        super(str);
    }

    public KryoBufferUnderflowException(Throwable th) {
        super(th);
    }
}
