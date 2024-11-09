package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.SnowballBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/SnowballBuff.class */
public final class SnowballBuff extends Buff {
    public SnowballBuff() {
        super(BuffType.SNOWBALL);
    }

    @Override // com.prineside.tdi2.Buff
    public final SnowballBuff cpy(float f) {
        SnowballBuff snowballBuff = new SnowballBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        snowballBuff.setup(f3, this.maxDuration);
        return snowballBuff;
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconSnowball;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/SnowballBuff$SnowballBuffFactory.class */
    public static final class SnowballBuffFactory extends Buff.Factory<SnowballBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public final BuffProcessor<SnowballBuff> createProcessor() {
            return new SnowballBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public final TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconSnowball;
        }
    }
}
