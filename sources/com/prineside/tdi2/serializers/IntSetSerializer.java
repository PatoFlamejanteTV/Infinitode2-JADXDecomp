package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntSet;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/IntSetSerializer.class */
public final class IntSetSerializer extends Serializer<IntSet> {

    /* renamed from: a, reason: collision with root package name */
    private static final IntArray f2900a = new IntArray();

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, IntSet intSet) {
        output.writeVarInt(intSet.size, true);
        IntSet.IntSetIterator it = intSet.iterator();
        f2900a.clear();
        while (it.hasNext) {
            f2900a.add(it.next());
        }
        f2900a.sort();
        for (int i = 0; i < f2900a.size; i++) {
            output.writeInt(f2900a.items[i]);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final IntSet read2(Kryo kryo, Input input, Class<? extends IntSet> cls) {
        int readVarInt = input.readVarInt(true);
        IntSet intSet = new IntSet(readVarInt);
        kryo.reference(intSet);
        for (int i = 0; i < readVarInt; i++) {
            intSet.add(input.readInt());
        }
        return intSet;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final IntSet copy(Kryo kryo, IntSet intSet) {
        IntSet intSet2 = new IntSet(intSet.size);
        intSet2.addAll(intSet);
        return intSet2;
    }
}
