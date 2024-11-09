package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Pool;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ObjectPair.class */
public final class ObjectPair<F, S> implements Pool.Poolable, KryoSerializable {
    public F first;
    public S second;

    public ObjectPair() {
    }

    public ObjectPair(F f, S s) {
        this.first = f;
        this.second = s;
    }

    public final ObjectPair<F, S> set(F f, S s) {
        this.first = f;
        this.second = s;
        return this;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public final void reset() {
        this.first = null;
        this.second = null;
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.first);
        kryo.writeClassAndObject(output, this.second);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.first = (F) kryo.readClassAndObject(input);
        this.second = (S) kryo.readClassAndObject(input);
    }

    public final String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " {" + this.first + ", " + this.second + "}";
    }
}
