package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Serializer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/ImmutableSerializer.class */
public abstract class ImmutableSerializer<T> extends Serializer<T> {
    public ImmutableSerializer() {
        setImmutable(true);
    }
}
