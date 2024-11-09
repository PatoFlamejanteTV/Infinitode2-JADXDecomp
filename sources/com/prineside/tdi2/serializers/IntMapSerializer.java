package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.IntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/IntMapSerializer.class */
public final class IntMapSerializer extends Serializer<IntMap> {
    static {
        (entry, entry2) -> {
            return Integer.compare(entry.key, entry2.key);
        };
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, IntMap intMap) {
        output.writeVarInt(intMap.size, true);
        IntMap.Keys keys = intMap.keys();
        while (keys.hasNext) {
            int next = keys.next();
            output.writeInt(next);
            kryo.writeClassAndObject(output, intMap.get(next));
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final IntMap read2(Kryo kryo, Input input, Class<? extends IntMap> cls) {
        int readVarInt = input.readVarInt(true);
        IntMap intMap = new IntMap(readVarInt);
        kryo.reference(intMap);
        for (int i = 0; i < readVarInt; i++) {
            intMap.put(input.readInt(), kryo.readClassAndObject(input));
        }
        return intMap;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final IntMap copy(Kryo kryo, IntMap intMap) {
        IntMap intMap2 = new IntMap(intMap.size);
        kryo.reference(intMap2);
        intMap2.putAll(intMap);
        return intMap2;
    }
}
