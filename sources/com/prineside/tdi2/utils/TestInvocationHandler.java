package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/TestInvocationHandler.class */
public class TestInvocationHandler implements KryoSerializable, Serializable, InvocationHandler {
    public Object[] arr = new Object[1];

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.arr);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        this.arr = (Object[]) kryo.readObject(input, Object[].class);
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        if ("toString".equals(method.getName())) {
            return "TestInvocationHandler";
        }
        return null;
    }
}
