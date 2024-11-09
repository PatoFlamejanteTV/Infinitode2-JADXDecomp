package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.IntArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/IntArraySerializer.class */
public final class IntArraySerializer extends Serializer<IntArray> {
    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, IntArray intArray) {
        int i = intArray.size;
        output.writeInt(i, true);
        if (i == 0) {
            return;
        }
        for (int i2 = 0; i2 < intArray.size; i2++) {
            output.writeVarInt(intArray.items[i2], false);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final IntArray read2(Kryo kryo, Input input, Class<? extends IntArray> cls) {
        try {
            IntArray intArray = new IntArray();
            kryo.reference(intArray);
            int readInt = input.readInt(true);
            intArray.ensureCapacity(readInt);
            for (int i = 0; i < readInt; i++) {
                intArray.add(input.readVarInt(false));
            }
            return intArray;
        } catch (Exception e) {
            throw new IllegalStateException("Can't create object instance", e);
        }
    }
}
