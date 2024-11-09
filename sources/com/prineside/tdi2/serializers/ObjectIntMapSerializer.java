package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.ObjectIntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/ObjectIntMapSerializer.class */
public class ObjectIntMapSerializer extends Serializer<ObjectIntMap> {
    @Override // com.esotericsoftware.kryo.Serializer
    public void write(Kryo kryo, Output output, ObjectIntMap objectIntMap) {
        output.writeVarInt(objectIntMap.size, true);
        ObjectIntMap.Keys keys = objectIntMap.keys();
        while (keys.hasNext) {
            Object next = keys.next();
            kryo.writeClassAndObject(output, next);
            output.writeVarInt(objectIntMap.get(next, 0), false);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public ObjectIntMap read2(Kryo kryo, Input input, Class<? extends ObjectIntMap> cls) {
        int readVarInt = input.readVarInt(true);
        ObjectIntMap objectIntMap = new ObjectIntMap(readVarInt);
        kryo.reference(objectIntMap);
        for (int i = 0; i < readVarInt; i++) {
            objectIntMap.put(kryo.readClassAndObject(input), input.readVarInt(false));
        }
        return objectIntMap;
    }
}
