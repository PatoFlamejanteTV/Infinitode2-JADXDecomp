package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/NoFieldKryoSerializable.class */
public interface NoFieldKryoSerializable extends KryoSerializable {
    @Override // com.esotericsoftware.kryo.KryoSerializable
    default void write(Kryo kryo, Output output) {
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    default void read(Kryo kryo, Input input) {
    }
}
