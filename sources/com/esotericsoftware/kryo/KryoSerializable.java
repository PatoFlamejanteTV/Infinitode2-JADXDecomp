package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/KryoSerializable.class */
public interface KryoSerializable {
    void write(Kryo kryo, Output output);

    void read(Kryo kryo, Input input);
}
