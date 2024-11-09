package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MovingAverageInt.class */
public final class MovingAverageInt implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private int[] f3864a;

    /* renamed from: b, reason: collision with root package name */
    private long f3865b;
    private int c;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f3864a);
        output.writeVarLong(this.f3865b, true);
        output.writeVarInt(this.c, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f3864a = (int[]) kryo.readObject(input, int[].class);
        this.f3865b = input.readVarLong(true);
        this.c = input.readVarInt(true);
    }

    private MovingAverageInt() {
    }

    public MovingAverageInt(int i) {
        this.f3864a = new int[i];
    }

    public final void push(int i) {
        if (this.c == this.f3864a.length) {
            this.f3865b -= this.f3864a[0];
            System.arraycopy(this.f3864a, 1, this.f3864a, 0, this.f3864a.length - 1);
            this.f3864a[this.f3864a.length - 1] = i;
        } else {
            int[] iArr = this.f3864a;
            int i2 = this.c;
            this.c = i2 + 1;
            iArr[i2] = i;
        }
        this.f3865b += i;
    }

    public final int getAverage() {
        if (this.c == 0) {
            return 0;
        }
        return (int) (this.f3865b / this.c);
    }

    public final float getAverageFloat() {
        if (this.c == 0) {
            return 0.0f;
        }
        return ((float) this.f3865b) / this.c;
    }
}
