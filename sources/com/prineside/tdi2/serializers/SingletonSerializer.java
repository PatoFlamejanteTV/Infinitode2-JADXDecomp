package com.prineside.tdi2.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/SingletonSerializer.class */
public abstract class SingletonSerializer<T> extends Serializer<T> {
    public abstract T read();

    public SingletonSerializer() {
        setImmutable(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, T t) {
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        return read();
    }
}
