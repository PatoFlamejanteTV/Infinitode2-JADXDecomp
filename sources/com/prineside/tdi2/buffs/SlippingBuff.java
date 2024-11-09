package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.SlippingBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/SlippingBuff.class */
public final class SlippingBuff extends Buff {
    public static final float SPEED_MULTIPLIER = 0.65f;
    public static final float THROW_BACK_DISTANCE = 1.2f;
    public float speedMultiplier;
    public float throwBackDistance;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.speedMultiplier);
        output.writeFloat(this.throwBackDistance);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.speedMultiplier = input.readFloat();
        this.throwBackDistance = input.readFloat();
    }

    public SlippingBuff() {
        super(BuffType.SLIPPING);
        this.speedMultiplier = 0.65f;
        this.throwBackDistance = 1.2f;
    }

    @Override // com.prineside.tdi2.Buff
    public final SlippingBuff cpy(float f) {
        SlippingBuff slippingBuff = new SlippingBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        slippingBuff.setup(f3, this.maxDuration);
        slippingBuff.speedMultiplier = this.speedMultiplier;
        slippingBuff.throwBackDistance = this.throwBackDistance;
        return slippingBuff;
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconSlipping;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/SlippingBuff$SlippingBuffFactory.class */
    public static final class SlippingBuffFactory extends Buff.Factory<SlippingBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public final BuffProcessor<SlippingBuff> createProcessor() {
            return new SlippingBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public final TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconSlipping;
        }
    }
}
