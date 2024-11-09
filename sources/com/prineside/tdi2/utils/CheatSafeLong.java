package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/CheatSafeLong.class */
public final class CheatSafeLong implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3819a = TLog.forClass(CheatSafeLong.class);

    /* renamed from: b, reason: collision with root package name */
    private long f3820b;
    private long c;
    private long d;
    private long e;
    private long f;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        output.writeVarLong(this.e, false);
        output.writeVarLong(this.f, false);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.e = input.readVarLong(false);
        set(input.readVarLong(false));
    }

    private CheatSafeLong() {
    }

    public CheatSafeLong(long j, long j2) {
        this.e = -j2;
        set(j);
    }

    public final long getSetOnCheat() {
        return -this.e;
    }

    private boolean a() {
        return ((((this.f * 31) + this.f3820b) * 31) + this.c) + ((long) hashCode()) != this.d;
    }

    public final long get() {
        if (a()) {
            long j = -this.e;
            set(j);
            return j;
        }
        return this.f;
    }

    public final void set(long j) {
        this.f = j;
        this.f3820b = FastRandom.getInt(8192);
        this.c = FastRandom.getInt(8192);
        this.d = (((this.f * 31) + this.f3820b) * 31) + this.c + hashCode();
        if (a()) {
            f3819a.e("invalid on " + j, new Object[0]);
        }
    }

    public final void add(long j) {
        if (get() >= 0 && j >= 0 && get() + j < 0) {
            j = Long.MAX_VALUE - get();
        }
        set(get() + j);
    }

    public final void sub(long j) {
        set(get() - j);
    }

    public final String toString() {
        return Long.toString(get());
    }
}
