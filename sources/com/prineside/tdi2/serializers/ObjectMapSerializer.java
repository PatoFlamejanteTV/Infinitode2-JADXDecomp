package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/ObjectMapSerializer.class */
public final class ObjectMapSerializer extends Serializer<ObjectMap> {
    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, ObjectMap objectMap) {
        output.writeVarInt(objectMap.size, true);
        ObjectMap.Keys keys = objectMap.keys();
        while (keys.hasNext) {
            Object next = keys.next();
            kryo.writeClassAndObject(output, next);
            kryo.writeClassAndObject(output, objectMap.get(next));
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final ObjectMap read2(Kryo kryo, Input input, Class<? extends ObjectMap> cls) {
        int readVarInt = input.readVarInt(true);
        ObjectMap objectMap = new ObjectMap(readVarInt);
        kryo.reference(objectMap);
        for (int i = 0; i < readVarInt; i++) {
            objectMap.put(kryo.readClassAndObject(input), kryo.readClassAndObject(input));
        }
        return objectMap;
    }
}
