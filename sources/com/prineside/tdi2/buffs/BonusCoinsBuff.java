package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.processors.BonusCoinsBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BonusCoinsBuff.class */
public final class BonusCoinsBuff extends Buff {
    public float bonusCoinsMultiplier;
    public Tower issuer;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.bonusCoinsMultiplier);
        kryo.writeClassAndObject(output, this.issuer);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.bonusCoinsMultiplier = input.readFloat();
        this.issuer = (Tower) kryo.readClassAndObject(input);
    }

    public BonusCoinsBuff() {
        super(BuffType.BONUS_COINS);
    }

    @Override // com.prineside.tdi2.Buff
    public final BonusCoinsBuff cpy(float f) {
        BonusCoinsBuff bonusCoinsBuff = new BonusCoinsBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        bonusCoinsBuff.setup(f3, this.maxDuration, this.bonusCoinsMultiplier, this.issuer);
        return bonusCoinsBuff;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconBonusCoins;
    }

    public final void setup(float f, float f2, float f3, Tower tower) {
        super.setup(f, f2);
        this.bonusCoinsMultiplier = f3;
        this.issuer = tower;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BonusCoinsBuff$BonusCoinsBuffFactory.class */
    public static class BonusCoinsBuffFactory extends Buff.Factory<BonusCoinsBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<BonusCoinsBuff> createProcessor() {
            return new BonusCoinsBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconBonusCoins;
        }
    }
}
