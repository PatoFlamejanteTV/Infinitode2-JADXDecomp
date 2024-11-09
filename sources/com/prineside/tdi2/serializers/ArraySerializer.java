package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/ArraySerializer.class */
public final class ArraySerializer<T extends Array> extends Serializer<T> {
    public ArraySerializer() {
        setAcceptsNull(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, T t) {
        output.writeInt(t.size, true);
        output.writeBoolean(t.ordered);
        kryo.writeClass(output, t.items.getClass().getComponentType());
        for (int i = 0; i < t.size; i++) {
            kryo.writeClassAndObject(output, t.items[i]);
        }
    }

    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final T read2(Kryo kryo, Input input, Class<? extends T> cls) {
        try {
            int readInt = input.readInt(true);
            T newInstance = cls.getConstructor(Boolean.TYPE, Integer.TYPE, Class.class).newInstance(Boolean.valueOf(input.readBoolean()), Integer.valueOf(readInt), kryo.readClass(input).getType());
            kryo.reference(newInstance);
            newInstance.ensureCapacity(readInt);
            for (int i = 0; i < readInt; i++) {
                newInstance.add(kryo.readClassAndObject(input));
            }
            return newInstance;
        } catch (Exception e) {
            throw new IllegalStateException("Can't create object instance", e);
        }
    }
}
