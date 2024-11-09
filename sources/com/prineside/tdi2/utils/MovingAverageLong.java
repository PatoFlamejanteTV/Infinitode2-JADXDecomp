package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/MovingAverageLong.class */
public final class MovingAverageLong implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private long[] f3866a;

    /* renamed from: b, reason: collision with root package name */
    private long f3867b;
    private int c;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeObject(output, this.f3866a);
        output.writeVarLong(this.f3867b, true);
        output.writeVarInt(this.c, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f3866a = (long[]) kryo.readObject(input, long[].class);
        this.f3867b = input.readVarLong(true);
        this.c = input.readVarInt(true);
    }

    private MovingAverageLong() {
    }

    public MovingAverageLong(int i) {
        this.f3866a = new long[i];
    }

    public final void push(long j) {
        if (this.c == this.f3866a.length) {
            this.f3867b -= this.f3866a[0];
            System.arraycopy(this.f3866a, 1, this.f3866a, 0, this.f3866a.length - 1);
            this.f3866a[this.f3866a.length - 1] = j;
        } else {
            long[] jArr = this.f3866a;
            int i = this.c;
            this.c = i + 1;
            jArr[i] = j;
        }
        this.f3867b += j;
    }

    public final long getAverage() {
        if (this.c == 0) {
            return 0L;
        }
        return (int) (this.f3867b / this.c);
    }
}
