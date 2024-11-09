package com.esotericsoftware.kryo;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/ClassResolver.class */
public interface ClassResolver {
    void setKryo(Kryo kryo);

    Registration register(Registration registration);

    Registration unregister(int i);

    Registration registerImplicit(Class cls);

    Registration getRegistration(Class cls);

    Registration getRegistration(int i);

    Registration writeClass(Output output, Class cls);

    Registration readClass(Input input);

    void reset();
}
