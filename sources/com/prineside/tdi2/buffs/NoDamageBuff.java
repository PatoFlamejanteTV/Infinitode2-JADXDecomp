package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.NoDamageBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/NoDamageBuff.class */
public final class NoDamageBuff extends Buff {
    public NoDamageBuff() {
        super(BuffType.NO_DAMAGE);
    }

    @Override // com.prineside.tdi2.Buff
    public final SlippingBuff cpy(float f) {
        return null;
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconNoDamage;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/NoDamageBuff$NoDamageBuffFactory.class */
    public static final class NoDamageBuffFactory extends Buff.Factory<NoDamageBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public final BuffProcessor<NoDamageBuff> createProcessor() {
            return new NoDamageBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public final TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconNoDamage;
        }
    }
}
