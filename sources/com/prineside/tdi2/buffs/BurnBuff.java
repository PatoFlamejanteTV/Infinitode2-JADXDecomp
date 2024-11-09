package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.processors.BurnBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BurnBuff.class */
public final class BurnBuff extends Buff {
    public Tower tower;
    public float fireDamage;
    public float bonusDamagePerEnemyNearby;
    public Ability fromAbility;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeClassAndObject(output, this.tower);
        output.writeFloat(this.fireDamage);
        output.writeFloat(this.bonusDamagePerEnemyNearby);
        kryo.writeClassAndObject(output, this.fromAbility);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.tower = (Tower) kryo.readClassAndObject(input);
        this.fireDamage = input.readFloat();
        this.bonusDamagePerEnemyNearby = input.readFloat();
        this.fromAbility = (Ability) kryo.readClassAndObject(input);
    }

    public BurnBuff() {
        super(BuffType.BURN);
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconBurn;
    }

    public final void setup(Tower tower, float f, float f2, float f3, Ability ability) {
        super.setup(f, f2);
        this.tower = tower;
        this.fireDamage = f3;
        this.fromAbility = ability;
    }

    @Override // com.prineside.tdi2.Buff
    public final BurnBuff cpy(float f) {
        BurnBuff burnBuff = new BurnBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        burnBuff.setup(this.tower, f3, this.maxDuration, this.fireDamage, this.fromAbility);
        burnBuff.bonusDamagePerEnemyNearby = this.bonusDamagePerEnemyNearby;
        return burnBuff;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BurnBuff$BurnBuffFactory.class */
    public static class BurnBuffFactory extends Buff.Factory<BurnBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<BurnBuff> createProcessor() {
            return new BurnBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconBurn;
        }
    }
}
