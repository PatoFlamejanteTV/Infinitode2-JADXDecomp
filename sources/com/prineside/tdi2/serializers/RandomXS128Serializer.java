package com.prineside.tdi2.serializers;

import com.badlogic.gdx.math.RandomXS128;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/RandomXS128Serializer.class */
public final class RandomXS128Serializer extends Serializer<RandomXS128> {
    public RandomXS128Serializer() {
        setAcceptsNull(true);
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, RandomXS128 randomXS128) {
        output.writeLong(randomXS128.getState(0));
        output.writeLong(randomXS128.getState(1));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final RandomXS128 read2(Kryo kryo, Input input, Class<? extends RandomXS128> cls) {
        RandomXS128 randomXS128 = new RandomXS128(input.readLong(), input.readLong());
        kryo.reference(randomXS128);
        return randomXS128;
    }
}
