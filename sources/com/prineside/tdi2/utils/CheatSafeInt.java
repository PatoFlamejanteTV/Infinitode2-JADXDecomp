package com.prineside.tdi2.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.utils.logging.TLog;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/CheatSafeInt.class */
public final class CheatSafeInt implements KryoSerializable {

    /* renamed from: a, reason: collision with root package name */
    private static final TLog f3817a = TLog.forClass(CheatSafeInt.class);

    /* renamed from: b, reason: collision with root package name */
    private int f3818b;
    private int c;
    private int d;
    private int e;
    private int f;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        output.writeVarInt(this.e, false);
        output.writeVarInt(this.f, false);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.e = input.readVarInt(false);
        set(input.readVarInt(false));
    }

    private CheatSafeInt() {
    }

    public CheatSafeInt(int i, int i2) {
        this.e = -i2;
        set(i);
    }

    public final int getSetOnCheat() {
        return -this.e;
    }

    private boolean a() {
        return ((((this.f * 31) + this.f3818b) * 31) + this.c) + hashCode() != this.d;
    }

    public final int get() {
        if (a()) {
            int i = -this.e;
            set(i);
            return i;
        }
        return this.f;
    }

    public final void set(int i) {
        this.f = i;
        this.f3818b = FastRandom.getInt(8192);
        this.c = FastRandom.getInt(8192);
        this.d = (((this.f * 31) + this.f3818b) * 31) + this.c + hashCode();
        if (a()) {
            f3817a.e("invalid on " + i, new Object[0]);
        }
    }

    public final void add(int i) {
        if (get() >= 0 && i >= 0 && get() + i < 0) {
            i = Integer.MAX_VALUE - get();
        }
        set(get() + i);
    }

    public final void sub(int i) {
        set(get() - i);
    }

    public final String toString() {
        return PMath.toString(get());
    }
}
