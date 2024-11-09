package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.processors.BonusXpBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BonusXpBuff.class */
public final class BonusXpBuff extends Buff {
    public float bonusXpMultiplier;
    public Tower issuer;

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeFloat(this.bonusXpMultiplier);
        kryo.writeClassAndObject(output, this.issuer);
    }

    @Override // com.prineside.tdi2.Buff, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.bonusXpMultiplier = input.readFloat();
        this.issuer = (Tower) kryo.readClassAndObject(input);
    }

    public BonusXpBuff() {
        super(BuffType.BONUS_XP);
    }

    @Override // com.prineside.tdi2.Buff
    public final BonusXpBuff cpy(float f) {
        BonusXpBuff bonusXpBuff = new BonusXpBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        bonusXpBuff.setup(f3, this.maxDuration, this.bonusXpMultiplier, this.issuer);
        return bonusXpBuff;
    }

    @Override // com.prineside.tdi2.Buff
    @Deprecated
    public final void setup(float f, float f2) {
        throw new IllegalStateException("Use other constructor");
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconBonusXp;
    }

    public final void setup(float f, float f2, float f3, Tower tower) {
        super.setup(f, f2);
        this.bonusXpMultiplier = f3;
        this.issuer = tower;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/BonusXpBuff$BonusXpBuffFactory.class */
    public static class BonusXpBuffFactory extends Buff.Factory<BonusXpBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<BonusXpBuff> createProcessor() {
            return new BonusXpBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconBonusXp;
        }
    }
}
