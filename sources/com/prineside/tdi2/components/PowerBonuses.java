package com.prineside.tdi2.components;

import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Null;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.REGS;
import java.util.Iterator;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/components/PowerBonuses.class */
public final class PowerBonuses implements KryoSerializable {
    public static final int SOURCE_BASIC_SPECIAL_ABILITY = 0;

    /* renamed from: a, reason: collision with root package name */
    private IntMap<Entry[]> f1827a = new IntMap<>();

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f1827a);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f1827a = (IntMap) kryo.readObject(input, IntMap.class);
    }

    @Null
    public final Entry[] getEffects(int i) {
        return this.f1827a.get(i);
    }

    @Null
    public final Entry[] getBonuses(int i) {
        return this.f1827a.get(i);
    }

    public final boolean hasBonuses(int i) {
        return this.f1827a.containsKey(i);
    }

    public final boolean hasBonusesFromSource(int i, int i2) {
        Entry[] entryArr = this.f1827a.get(i);
        if (entryArr != null) {
            for (Entry entry : entryArr) {
                if (entry.sourceId == i2) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final boolean removeBonus(int i) {
        return this.f1827a.remove(i) != null;
    }

    public final boolean removeBonusFromSource(int i, int i2) {
        Entry[] entryArr = this.f1827a.get(i);
        if (entryArr != null) {
            for (Entry entry : entryArr) {
                if (entry.sourceId == i2) {
                    if (entryArr.length == 1) {
                        removeBonus(i);
                        return true;
                    }
                    Entry[] entryArr2 = new Entry[entryArr.length - 1];
                    int i3 = 0;
                    for (Entry entry2 : entryArr) {
                        if (entry2 != entry) {
                            int i4 = i3;
                            i3++;
                            entryArr2[i4] = entry2;
                        }
                    }
                    this.f1827a.put(i, entryArr2);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final boolean addOrReplaceBonus(int i, int i2, float f) {
        Entry[] entryArr;
        Entry[] entryArr2 = this.f1827a.get(i);
        if (entryArr2 != null) {
            for (Entry entry : entryArr2) {
                if (entry.sourceId == i2) {
                    if (entry.delta != f) {
                        entry.delta = f;
                        return true;
                    }
                    return false;
                }
            }
        }
        Entry entry2 = new Entry();
        entry2.sourceId = i2;
        entry2.delta = f;
        if (entryArr2 == null) {
            entryArr = r0;
            Entry[] entryArr3 = {entry2};
        } else {
            Entry[] entryArr4 = new Entry[entryArr2.length + 1];
            System.arraycopy(entryArr2, 0, entryArr4, 0, entryArr2.length);
            entryArr = entryArr4;
        }
        Entry[] entryArr5 = entryArr;
        entryArr5[entryArr5.length - 1] = entry2;
        this.f1827a.put(i, entryArr);
        return true;
    }

    public final float getBonusesSum() {
        float f = 0.0f;
        Iterator<IntMap.Entry<Entry[]>> it = this.f1827a.iterator();
        while (it.hasNext()) {
            for (Entry entry : it.next().value) {
                f += entry.delta;
            }
        }
        return f;
    }

    @REGS(arrayLevels = 1)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/components/PowerBonuses$Entry.class */
    public static final class Entry implements KryoSerializable {
        public int sourceId;
        public float delta;

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeVarInt(this.sourceId, true);
            output.writeFloat(this.delta);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.sourceId = input.readVarInt(true);
            this.delta = input.readFloat();
        }
    }
}
