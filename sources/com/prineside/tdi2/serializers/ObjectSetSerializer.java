package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.ObjectSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/ObjectSetSerializer.class */
public final class ObjectSetSerializer extends Serializer<ObjectSet> {
    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, ObjectSet objectSet) {
        output.writeVarInt(objectSet.size, true);
        ObjectSet.ObjectSetIterator it = objectSet.iterator();
        while (it.hasNext) {
            kryo.writeClassAndObject(output, it.next());
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final ObjectSet read2(Kryo kryo, Input input, Class<? extends ObjectSet> cls) {
        int readVarInt = input.readVarInt(true);
        ObjectSet objectSet = new ObjectSet(readVarInt);
        kryo.reference(objectSet);
        for (int i = 0; i < readVarInt; i++) {
            objectSet.add(kryo.readClassAndObject(input));
        }
        return objectSet;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final ObjectSet copy(Kryo kryo, ObjectSet objectSet) {
        ObjectSet objectSet2 = new ObjectSet(objectSet.size);
        objectSet2.addAll(objectSet);
        return objectSet2;
    }
}
