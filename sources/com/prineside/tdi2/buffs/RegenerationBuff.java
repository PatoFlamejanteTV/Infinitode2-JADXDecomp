package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.processors.RegenerationBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/RegenerationBuff.class */
public final class RegenerationBuff extends Buff {
    public Enemy.EnemyReference issuer;
    public float hpPerSecond;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.issuer);
        output.writeFloat(this.hpPerSecond);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.issuer = (Enemy.EnemyReference) kryo.readObject(input, Enemy.EnemyReference.class);
        this.hpPerSecond = input.readFloat();
    }

    public RegenerationBuff() {
        super(BuffType.REGENERATION);
        this.issuer = Enemy.EnemyReference.NULL;
    }

    @Override // com.prineside.tdi2.Buff
    public final RegenerationBuff cpy(float f) {
        RegenerationBuff regenerationBuff = new RegenerationBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        regenerationBuff.setup(f3, this.maxDuration, this.hpPerSecond, this.issuer);
        return regenerationBuff;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconRegeneration;
    }

    public final void setup(float f, float f2, float f3, Enemy.EnemyReference enemyReference) {
        super.setup(f, f2);
        this.hpPerSecond = f3;
        this.issuer = enemyReference;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/RegenerationBuff$RegenerationBuffFactory.class */
    public static class RegenerationBuffFactory extends Buff.Factory<RegenerationBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<RegenerationBuff> createProcessor() {
            return new RegenerationBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconRegeneration;
        }
    }
}
