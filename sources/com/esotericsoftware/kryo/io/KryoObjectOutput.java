package com.esotericsoftware.kryo.io;

import com.esotericsoftware.kryo.Kryo;
import java.io.ObjectOutput;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/io/KryoObjectOutput.class */
public class KryoObjectOutput extends KryoDataOutput implements ObjectOutput {
    private final Kryo kryo;

    public KryoObjectOutput(Kryo kryo, Output output) {
        super(output);
        this.kryo = kryo;
    }

    @Override // java.io.ObjectOutput
    public void writeObject(Object obj) {
        this.kryo.writeClassAndObject(this.output, obj);
    }

    @Override // java.io.ObjectOutput
    public void flush() {
        this.output.flush();
    }

    @Override // com.esotericsoftware.kryo.io.KryoDataOutput, java.lang.AutoCloseable
    public void close() {
        this.output.close();
    }
}
