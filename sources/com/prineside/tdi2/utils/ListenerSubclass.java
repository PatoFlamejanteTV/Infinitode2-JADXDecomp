package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/ListenerSubclass.class */
public abstract class ListenerSubclass<T> implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private T f3859a;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.f3859a);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.f3859a = (T) kryo.readClassAndObject(input);
    }

    protected ListenerSubclass() {
    }

    public ListenerSubclass(T t) {
        Preconditions.checkNotNull(t, "Parent object can not be null");
        this.f3859a = t;
    }
}
