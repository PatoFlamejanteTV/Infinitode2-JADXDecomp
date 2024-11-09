package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MovingAverageFloat.class */
public final class MovingAverageFloat implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private float[] f3862a;

    /* renamed from: b, reason: collision with root package name */
    private double f3863b;
    private int c;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f3862a);
        output.writeDouble(this.f3863b);
        output.writeVarInt(this.c, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f3862a = (float[]) kryo.readObject(input, float[].class);
        this.f3863b = input.readDouble();
        this.c = input.readVarInt(true);
    }

    private MovingAverageFloat() {
    }

    public MovingAverageFloat(int i) {
        this.f3862a = new float[i];
    }

    public final void push(float f) {
        if (this.c == this.f3862a.length) {
            this.f3863b -= this.f3862a[0];
            System.arraycopy(this.f3862a, 1, this.f3862a, 0, this.f3862a.length - 1);
            this.f3862a[this.f3862a.length - 1] = f;
        } else {
            float[] fArr = this.f3862a;
            int i = this.c;
            this.c = i + 1;
            fArr[i] = f;
        }
        this.f3863b += f;
    }

    public final float getAverage() {
        if (this.c == 0) {
            return 0.0f;
        }
        return (float) (this.f3863b / this.c);
    }

    public final int getCount() {
        return this.c;
    }

    public final void reset() {
        this.f3863b = 0.0d;
        this.c = 0;
    }
}
