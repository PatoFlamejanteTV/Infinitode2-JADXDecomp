package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.FloatArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/FloatArraySerializer.class */
public final class FloatArraySerializer extends Serializer<FloatArray> {
    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, FloatArray floatArray) {
        int i = floatArray.size;
        output.writeBoolean(floatArray.ordered);
        output.writeInt(i, true);
        if (i == 0) {
            return;
        }
        for (int i2 = 0; i2 < floatArray.size; i2++) {
            output.writeFloat(floatArray.items[i2]);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final FloatArray read2(Kryo kryo, Input input, Class<? extends FloatArray> cls) {
        try {
            boolean readBoolean = input.readBoolean();
            int readInt = input.readInt(true);
            FloatArray floatArray = new FloatArray(readBoolean, readInt);
            kryo.reference(floatArray);
            floatArray.ensureCapacity(readInt);
            for (int i = 0; i < readInt; i++) {
                floatArray.add(input.readFloat());
            }
            return floatArray;
        } catch (Exception e) {
            throw new IllegalStateException("Can't create object instance", e);
        }
    }
}
