package com.esotericsoftware.kryo;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/ReferenceResolver.class */
public interface ReferenceResolver {
    void setKryo(Kryo kryo);

    int getWrittenId(Object obj);

    int addWrittenObject(Object obj);

    int nextReadId(Class cls);

    void setReadObject(int i, Object obj);

    Object getReadObject(Class cls, int i);

    void reset();

    boolean useReferences(Class cls);
}
