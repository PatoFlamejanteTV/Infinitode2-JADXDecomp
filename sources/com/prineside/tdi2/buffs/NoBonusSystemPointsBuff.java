package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.NoBonusSystemPointsBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/NoBonusSystemPointsBuff.class */
public final class NoBonusSystemPointsBuff extends Buff {
    public NoBonusSystemPointsBuff() {
        super(BuffType.NO_BONUS_SYSTEM_POINTS);
    }

    @Override // com.prineside.tdi2.Buff
    public final SlippingBuff cpy(float f) {
        return null;
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconNoBonusSystemPoints;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/NoBonusSystemPointsBuff$NoBonusSystemPointsBuffFactory.class */
    public static final class NoBonusSystemPointsBuffFactory extends Buff.Factory<NoBonusSystemPointsBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public final BuffProcessor<NoBonusSystemPointsBuff> createProcessor() {
            return new NoBonusSystemPointsBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public final TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconNoBonusSystemPoints;
        }
    }
}
