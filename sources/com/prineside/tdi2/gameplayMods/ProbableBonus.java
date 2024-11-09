package com.prineside.tdi2.gameplayMods;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/gameplayMods/ProbableBonus.class */
public final class ProbableBonus implements KryoSerializable {
    public static final int DEFAULT = 100000;

    /* renamed from: a, reason: collision with root package name */
    private GameplayMod f2083a;

    /* renamed from: b, reason: collision with root package name */
    private int f2084b;

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        kryo.writeClassAndObject(output, this.f2083a);
        output.writeVarInt(this.f2084b, true);
    }

    @Override // com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        this.f2083a = (GameplayMod) kryo.readClassAndObject(input);
        this.f2084b = input.readVarInt(true);
    }

    private ProbableBonus() {
    }

    public ProbableBonus(GameplayMod gameplayMod, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("probability can not be <= 0");
        }
        Preconditions.checkNotNull(gameplayMod, "bonus can not be null");
        this.f2083a = gameplayMod;
        this.f2084b = i;
    }

    public final GameplayMod getBonus() {
        return this.f2083a;
    }

    public final int getProbability() {
        return this.f2084b;
    }

    public final void setProbability(int i) {
        this.f2084b = i;
    }
}
