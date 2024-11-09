package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.processors.PoisonBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/PoisonBuff.class */
public final class PoisonBuff extends Buff {
    public Tower tower;
    public float hitDamage;
    public float poisonDamage;
    public int fastShellsStackCount;
    public Ability fromAbility;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.tower);
        output.writeFloat(this.hitDamage);
        output.writeFloat(this.poisonDamage);
        output.writeVarInt(this.fastShellsStackCount, true);
        kryo.writeClassAndObject(output, this.fromAbility);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.tower = (Tower) kryo.readClassAndObject(input);
        this.hitDamage = input.readFloat();
        this.poisonDamage = input.readFloat();
        this.fastShellsStackCount = input.readVarInt(true);
        this.fromAbility = (Ability) kryo.readClassAndObject(input);
    }

    public PoisonBuff() {
        super(BuffType.POISON);
        this.fastShellsStackCount = 1;
    }

    @Override // com.prineside.tdi2.Buff
    public final PoisonBuff cpy(float f) {
        PoisonBuff poisonBuff = new PoisonBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        poisonBuff.setup(this.tower, f3, this.maxDuration, this.hitDamage, this.poisonDamage, this.fromAbility);
        poisonBuff.fastShellsStackCount = this.fastShellsStackCount;
        return poisonBuff;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        switch (this.fastShellsStackCount) {
            case 0:
            case 1:
                return AssetManager.TextureRegions.i().buffHealthBarIconPoison;
            case 2:
                return AssetManager.TextureRegions.i().buffHealthBarIconPoisonTwo;
            default:
                return AssetManager.TextureRegions.i().buffHealthBarIconPoisonThree;
        }
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4, Ability ability) {
        super.setup(f, f2);
        this.tower = tower;
        this.hitDamage = f3;
        this.poisonDamage = f4;
        this.fromAbility = ability;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/PoisonBuff$PoisonBuffFactory.class */
    public static final class PoisonBuffFactory extends Buff.Factory<PoisonBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public final BuffProcessor<PoisonBuff> createProcessor() {
            return new PoisonBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public final TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconPoison;
        }
    }
}
