package com.esotericsoftware.kryo;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/KryoCopyable.class */
public interface KryoCopyable<T> {
    T copy(Kryo kryo);
}
