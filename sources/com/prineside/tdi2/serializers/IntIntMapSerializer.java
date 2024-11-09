package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntIntMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/IntIntMapSerializer.class */
public final class IntIntMapSerializer extends Serializer<IntIntMap> {

    /* renamed from: a, reason: collision with root package name */
    private static final Array<IntIntMap.Entry> f2898a = new Array<>(IntIntMap.Entry.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Comparator<IntIntMap.Entry> f2899b = (entry, entry2) -> {
        return Integer.compare(entry.key, entry2.key);
    };

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, IntIntMap intIntMap) {
        output.writeVarInt(intIntMap.size, true);
        f2898a.clear();
        Iterator<IntIntMap.Entry> it = intIntMap.iterator();
        while (it.hasNext()) {
            IntIntMap.Entry next = it.next();
            IntIntMap.Entry entry = new IntIntMap.Entry();
            entry.key = next.key;
            entry.value = next.value;
            f2898a.add(entry);
        }
        f2898a.sort(f2899b);
        for (int i = 0; i < f2898a.size; i++) {
            IntIntMap.Entry entry2 = f2898a.items[i];
            output.writeInt(entry2.key);
            output.writeInt(entry2.value);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final IntIntMap read2(Kryo kryo, Input input, Class<? extends IntIntMap> cls) {
        int readVarInt = input.readVarInt(true);
        IntIntMap intIntMap = new IntIntMap(readVarInt);
        kryo.reference(intIntMap);
        for (int i = 0; i < readVarInt; i++) {
            intIntMap.put(input.readInt(), input.readInt());
        }
        return intIntMap;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final IntIntMap copy(Kryo kryo, IntIntMap intIntMap) {
        IntIntMap intIntMap2 = new IntIntMap(intIntMap.size);
        kryo.reference(intIntMap2);
        intIntMap2.putAll(intIntMap);
        return intIntMap2;
    }
}
