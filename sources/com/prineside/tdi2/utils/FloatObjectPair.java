package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/FloatObjectPair.class */
public final class FloatObjectPair<T> implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    public float f3829a;
    public T t;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        output.writeFloat(this.f3829a);
        kryo.writeClassAndObject(output, this.t);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f3829a = input.readFloat();
        this.t = (T) kryo.readClassAndObject(input);
    }

    public FloatObjectPair() {
    }

    public FloatObjectPair(float f, T t) {
        this.f3829a = f;
        this.t = t;
    }

    public final String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " {" + this.f3829a + ", " + this.t + "}";
    }
}
