package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.ObjectFloatMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/ObjectFloatMapSerializer.class */
public class ObjectFloatMapSerializer extends Serializer<ObjectFloatMap> {
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, ObjectFloatMap objectFloatMap) {
        output.writeVarInt(objectFloatMap.size, true);
        ObjectFloatMap.Keys keys = objectFloatMap.keys();
        while (keys.hasNext) {
            Object next = keys.next();
            kryo.writeClassAndObject(output, next);
            output.writeFloat(objectFloatMap.get(next, 0.0f));
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public ObjectFloatMap read2(Kryo kryo, Input input, Class<? extends ObjectFloatMap> cls) {
        int readVarInt = input.readVarInt(true);
        ObjectFloatMap objectFloatMap = new ObjectFloatMap(readVarInt);
        kryo.reference(objectFloatMap);
        for (int i = 0; i < readVarInt; i++) {
            objectFloatMap.put(kryo.readClassAndObject(input), input.readFloat());
        }
        return objectFloatMap;
    }
}
