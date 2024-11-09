package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.BlizzardBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BlizzardBuff.class */
public final class BlizzardBuff extends Buff {
    public float timePassed;
    public float damageMultiplier;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.timePassed);
        output.writeFloat(this.damageMultiplier);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.timePassed = input.readFloat();
        this.damageMultiplier = input.readFloat();
    }

    public BlizzardBuff() {
        super(BuffType.BLIZZARD);
    }

    @Override // com.prineside.tdi2.Buff
    public final BlizzardBuff cpy(float f) {
        BlizzardBuff blizzardBuff = new BlizzardBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        blizzardBuff.setup(f3, this.maxDuration, this.damageMultiplier);
        return blizzardBuff;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconBlizzard;
    }

    public final void setup(float f, float f2, float f3) {
        super.setup(f, f2);
        this.damageMultiplier = f3;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BlizzardBuff$BlizzardBuffFactory.class */
    public static class BlizzardBuffFactory extends Buff.Factory<BlizzardBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<BlizzardBuff> createProcessor() {
            return new BlizzardBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconBlizzard;
        }
    }
}
