package com.prineside.tdi2.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/ProxySerializer.class */
public final class ProxySerializer extends Serializer<Object> {
    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, Object obj) {
        kryo.writeClassAndObject(output, Proxy.getInvocationHandler(obj));
        kryo.writeObject(output, obj.getClass().getInterfaces());
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final Object read2(Kryo kryo, Input input, Class<? extends Object> cls) {
        InvocationHandler invocationHandler = (InvocationHandler) kryo.readClassAndObject(input);
        return Proxy.newProxyInstance(kryo.getClassLoader(), (Class[]) kryo.readObject(input, Class[].class), invocationHandler);
    }
}
