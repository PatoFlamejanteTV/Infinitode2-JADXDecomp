package com.esotericsoftware.kryo.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.ObjectMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/JavaSerializer.class */
public class JavaSerializer extends Serializer {
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, Object obj) {
        try {
            ObjectMap graphContext = kryo.getGraphContext();
            ObjectOutputStream objectOutputStream = (ObjectOutputStream) graphContext.get(this);
            ObjectOutputStream objectOutputStream2 = objectOutputStream;
            if (objectOutputStream == null) {
                objectOutputStream2 = new ObjectOutputStream(output);
                graphContext.put(this, objectOutputStream2);
            }
            objectOutputStream2.writeObject(obj);
            objectOutputStream2.flush();
        } catch (Exception e) {
            throw new KryoException("Error during Java serialization.", e);
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public Object read2(Kryo kryo, Input input, Class cls) {
        try {
            ObjectMap graphContext = kryo.getGraphContext();
            ObjectInputStream objectInputStream = (ObjectInputStream) graphContext.get(this);
            ObjectInputStream objectInputStream2 = objectInputStream;
            if (objectInputStream == null) {
                objectInputStream2 = new ObjectInputStreamWithKryoClassLoader(input, kryo);
                graphContext.put(this, objectInputStream2);
            }
            return objectInputStream2.readObject();
        } catch (Exception e) {
            throw new KryoException("Error during Java deserialization.", e);
        }
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/serializers/JavaSerializer$ObjectInputStreamWithKryoClassLoader.class */
    private static class ObjectInputStreamWithKryoClassLoader extends ObjectInputStream {
        private final Kryo kryo;

        ObjectInputStreamWithKryoClassLoader(InputStream inputStream, Kryo kryo) {
            super(inputStream);
            this.kryo = kryo;
        }

        @Override // java.io.ObjectInputStream
        protected Class resolveClass(ObjectStreamClass objectStreamClass) {
            try {
                return Class.forName(objectStreamClass.getName(), false, this.kryo.getClassLoader());
            } catch (ClassNotFoundException unused) {
                try {
                    return super.resolveClass(objectStreamClass);
                } catch (IOException e) {
                    throw new KryoException("Could not load class: " + objectStreamClass.getName(), e);
                } catch (ClassNotFoundException e2) {
                    throw new KryoException("Class not found: " + objectStreamClass.getName(), e2);
                }
            }
        }
    }
}
