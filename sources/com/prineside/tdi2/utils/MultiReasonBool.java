package com.prineside.tdi2.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.IntArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import java.util.Arrays;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MultiReasonBool.class */
public final class MultiReasonBool implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private Entry[] f3870a = new Entry[0];

    /* renamed from: b, reason: collision with root package name */
    private byte f3871b;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f3870a);
        output.writeByte(this.f3871b);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f3870a = (Entry[]) kryo.readObject(input, Entry[].class);
        this.f3871b = input.readByte();
    }

    public final boolean addReason(String str) {
        return addReasonForDuration(str, 2.1474836E9f);
    }

    public final boolean addReasonForDuration(String str, float f) {
        Preconditions.checkNotNull(str, "reason can not be null");
        for (int i = 0; i < this.f3871b; i++) {
            if (this.f3870a[i].f3872a.equals(str)) {
                if (this.f3870a[i].f3873b >= f) {
                    return false;
                }
                this.f3870a[i].f3873b = f;
                return true;
            }
        }
        if (this.f3871b >= this.f3870a.length) {
            int ceil = MathUtils.ceil(this.f3871b * 0.2f);
            int i2 = ceil;
            if (ceil == 0) {
                i2 = 1;
            }
            Entry[] entryArr = this.f3870a;
            this.f3870a = new Entry[this.f3870a.length + i2];
            System.arraycopy(entryArr, 0, this.f3870a, 0, this.f3871b);
        }
        Entry[] entryArr2 = this.f3870a;
        byte b2 = this.f3871b;
        this.f3871b = (byte) (b2 + 1);
        entryArr2[b2] = new Entry(str, f);
        return true;
    }

    public final boolean hasReason(String str) {
        if (this.f3871b == 0) {
            return false;
        }
        Preconditions.checkNotNull(str);
        for (int i = 0; i < this.f3871b; i++) {
            if (this.f3870a[i].f3872a.equals(str)) {
                return this.f3870a[i].f3873b > 0.0f;
            }
        }
        return false;
    }

    public final boolean removeReason(String str) {
        if (this.f3871b == 0) {
            return false;
        }
        Preconditions.checkNotNull(str);
        for (int i = 0; i < this.f3871b; i++) {
            if (this.f3870a[i].f3872a.equals(str)) {
                this.f3871b = (byte) (this.f3871b - 1);
                Entry[] entryArr = this.f3870a;
                entryArr[i] = entryArr[this.f3871b];
                this.f3870a[this.f3871b] = null;
                return true;
            }
        }
        return false;
    }

    public final int getReasonCount() {
        return this.f3871b;
    }

    public final boolean isTrue() {
        return this.f3871b != 0;
    }

    public final void update(float f) {
        IntArray intArray = null;
        for (int i = 0; i < this.f3871b; i++) {
            Entry entry = this.f3870a[i];
            Entry.b(entry, f);
            if (entry.f3873b <= 0.0f) {
                if (intArray == null) {
                    intArray = new IntArray();
                }
                intArray.add(i);
            }
        }
        if (intArray != null) {
            intArray.reverse();
            for (int i2 = 0; i2 < intArray.size; i2++) {
                int i3 = intArray.items[i2];
                this.f3871b = (byte) (this.f3871b - 1);
                Entry[] entryArr = this.f3870a;
                entryArr[i3] = entryArr[this.f3871b];
                this.f3870a[this.f3871b] = null;
            }
        }
    }

    public final void clear() {
        Arrays.fill(this.f3870a, (Object) null);
        this.f3871b = (byte) 0;
    }

    public final Entry[] getReasonsBuffer() {
        return this.f3870a;
    }

    public final String toString() {
        return isTrue() + " (" + ((int) this.f3871b) + " reasons: " + Arrays.toString(this.f3870a) + ")";
    }

    @REGS(arrayLevels = 1)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MultiReasonBool$Entry.class */
    public static final class Entry implements KryoSerializable {

        /* renamed from: a, reason: collision with root package name */
        private String f3872a;

        /* renamed from: b, reason: collision with root package name */
        private float f3873b;

        static /* synthetic */ float b(Entry entry, float f) {
            float f2 = entry.f3873b - f;
            entry.f3873b = f2;
            return f2;
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void write(Kryo kryo, Output output) {
            output.writeString(this.f3872a);
            output.writeFloat(this.f3873b);
        }

        @Override // com.esotericsoftware.kryo.KryoSerializable
        public final void read(Kryo kryo, Input input) {
            this.f3872a = input.readString();
            this.f3873b = input.readFloat();
        }

        public Entry() {
        }

        public Entry(String str, float f) {
            this.f3872a = str;
            this.f3873b = f;
        }

        public final String toString() {
            return this.f3872a;
        }
    }
}
