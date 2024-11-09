package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/IntObjectPair.class */
public final class IntObjectPair<T> implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    public int f3849a;
    public T t;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        output.writeInt(this.f3849a);
        kryo.writeClassAndObject(output, this.t);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f3849a = input.readInt();
        this.t = (T) kryo.readClassAndObject(input);
    }

    public IntObjectPair() {
    }

    public IntObjectPair(int i, T t) {
        this.f3849a = i;
        this.t = t;
    }

    public final String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " {" + this.f3849a + ", " + this.t + "}";
    }
}
