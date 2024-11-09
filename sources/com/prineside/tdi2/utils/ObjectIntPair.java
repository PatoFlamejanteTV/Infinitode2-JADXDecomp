package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ObjectIntPair.class */
public final class ObjectIntPair<T> implements KryoSerializable {
    public T object;
    public int intValue;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.object);
        output.writeInt(this.intValue);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.object = (T) kryo.readClassAndObject(input);
        this.intValue = input.readInt();
    }

    public ObjectIntPair() {
    }

    public ObjectIntPair(T t, int i) {
        this.object = t;
        this.intValue = i;
    }
}
