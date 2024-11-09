package com.prineside.tdi2.serializers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntFloatMap;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.util.Comparator;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/serializers/IntFloatMapSerializer.class */
public final class IntFloatMapSerializer extends Serializer<IntFloatMap> {

    /* renamed from: a, reason: collision with root package name */
    private static final Array<IntFloatMap.Entry> f2896a = new Array<>(IntFloatMap.Entry.class);

    /* renamed from: b, reason: collision with root package name */
    private static final Comparator<IntFloatMap.Entry> f2897b = (entry, entry2) -> {
        return Float.compare(entry.key, entry2.key);
    };

    @Override // com.esotericsoftware.kryo.Serializer
    public final void write(Kryo kryo, Output output, IntFloatMap intFloatMap) {
        output.writeVarInt(intFloatMap.size, true);
        f2896a.clear();
        Iterator<IntFloatMap.Entry> it = intFloatMap.iterator();
        while (it.hasNext()) {
            IntFloatMap.Entry next = it.next();
            IntFloatMap.Entry entry = new IntFloatMap.Entry();
            entry.key = next.key;
            entry.value = next.value;
            f2896a.add(entry);
        }
        f2896a.sort(f2897b);
        for (int i = 0; i < f2896a.size; i++) {
            IntFloatMap.Entry entry2 = f2896a.items[i];
            output.writeInt(entry2.key);
            output.writeFloat(entry2.value);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.esotericsoftware.kryo.Serializer
    /* renamed from: read */
    public final IntFloatMap read2(Kryo kryo, Input input, Class<? extends IntFloatMap> cls) {
        int readVarInt = input.readVarInt(true);
        IntFloatMap intFloatMap = new IntFloatMap(readVarInt);
        kryo.reference(intFloatMap);
        for (int i = 0; i < readVarInt; i++) {
            intFloatMap.put(input.readInt(), input.readFloat());
        }
        return intFloatMap;
    }

    @Override // com.esotericsoftware.kryo.Serializer
    public final IntFloatMap copy(Kryo kryo, IntFloatMap intFloatMap) {
        IntFloatMap intFloatMap2 = new IntFloatMap(intFloatMap.size);
        kryo.reference(intFloatMap2);
        intFloatMap2.putAll(intFloatMap);
        return intFloatMap2;
    }
}
