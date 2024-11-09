package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.KryoException;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/KryoBufferOverflowException.class */
public class KryoBufferOverflowException extends KryoException {
    public KryoBufferOverflowException() {
    }

    public KryoBufferOverflowException(String str, Throwable th) {
        super(str, th);
    }

    public KryoBufferOverflowException(String str) {
        super(str);
    }

    public KryoBufferOverflowException(Throwable th) {
        super(th);
    }
}
