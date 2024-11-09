package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.processors.FreezingBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/FreezingBuff.class */
public final class FreezingBuff extends Buff {
    public float speed;
    public float maxPercent;
    public float poisonDurationBonus;
    public float lightningLengthBonus;
    public Tower tower;
    public boolean copyDisabled;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.speed);
        output.writeFloat(this.maxPercent);
        output.writeFloat(this.poisonDurationBonus);
        output.writeFloat(this.lightningLengthBonus);
        kryo.writeClassAndObject(output, this.tower);
        output.writeBoolean(this.copyDisabled);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.speed = input.readFloat();
        this.maxPercent = input.readFloat();
        this.poisonDurationBonus = input.readFloat();
        this.lightningLengthBonus = input.readFloat();
        this.tower = (Tower) kryo.readClassAndObject(input);
        this.copyDisabled = input.readBoolean();
    }

    public FreezingBuff() {
        super(BuffType.FREEZING);
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconFreezing;
    }

    public final void setup(Tower tower, float f, float f2, float f3, float f4, float f5, float f6) {
        super.setup(f3, f4);
        this.tower = tower;
        this.speed = f;
        this.maxPercent = f2;
        this.poisonDurationBonus = f5;
        this.lightningLengthBonus = f6;
        this.copyDisabled = false;
    }

    @Override // com.prineside.tdi2.Buff
    public final FreezingBuff cpy(float f) {
        if (this.copyDisabled) {
            return null;
        }
        FreezingBuff freezingBuff = new FreezingBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        freezingBuff.setup(this.tower, this.speed, this.maxPercent, f3, this.maxDuration, this.poisonDurationBonus, this.lightningLengthBonus);
        return freezingBuff;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/FreezingBuff$FreezingBuffFactory.class */
    public static class FreezingBuffFactory extends Buff.Factory<FreezingBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<FreezingBuff> createProcessor() {
            return new FreezingBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconFreezing;
        }
    }
}
