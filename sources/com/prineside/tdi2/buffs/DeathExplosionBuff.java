package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.buffs.processors.DeathExplosionBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/DeathExplosionBuff.class */
public final class DeathExplosionBuff extends Buff {
    public Explosion explosion;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.explosion);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.explosion = (Explosion) kryo.readClassAndObject(input);
    }

    @Override // com.prineside.tdi2.Buff
    public final DeathExplosionBuff cpy(float f) {
        Explosion cpy;
        if (this.explosion == null || (cpy = this.explosion.cpy()) == null) {
            return null;
        }
        DeathExplosionBuff deathExplosionBuff = new DeathExplosionBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        deathExplosionBuff.setup(f3, this.maxDuration, cpy);
        return deathExplosionBuff;
    }

    public DeathExplosionBuff() {
        super(BuffType.DEATH_EXPLOSION);
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconDeathExplosion;
    }

    public final DeathExplosionBuff setup(float f, float f2, Explosion explosion) {
        super.setup(f, f2);
        this.explosion = explosion;
        return this;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/DeathExplosionBuff$ExplosionChargeBuffFactory.class */
    public static class ExplosionChargeBuffFactory extends Buff.Factory<DeathExplosionBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<DeathExplosionBuff> createProcessor() {
            return new DeathExplosionBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconDeathExplosion;
        }
    }
}
