package com.prineside.tdi2.buffs;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.buffs.processors.ArmorBuffProcessor;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.managers.AssetManager;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/ArmorBuff.class */
public final class ArmorBuff extends Buff {
    public ArmorBuff() {
        super(BuffType.ARMOR);
    }

    @Override // com.prineside.tdi2.Buff
    public final ArmorBuff cpy(float f) {
        ArmorBuff armorBuff = new ArmorBuff();
        float f2 = this.duration * f;
        float f3 = f2;
        if (f2 > this.maxDuration) {
            f3 = this.maxDuration;
        }
        armorBuff.setup(f3, this.maxDuration);
        return armorBuff;
    }

    @Override // com.prineside.tdi2.Buff
    public final TextureRegion getHealthBarIcon() {
        return AssetManager.TextureRegions.i().buffHealthBarIconArmor;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/ArmorBuff$ArmorBuffFactory.class */
    public static class ArmorBuffFactory extends Buff.Factory<ArmorBuff> {
        @Override // com.prineside.tdi2.Buff.Factory
        public BuffProcessor<ArmorBuff> createProcessor() {
            return new ArmorBuffProcessor();
        }

        @Override // com.prineside.tdi2.Buff.Factory
        public TextureRegion getHealthBarIcon() {
            return AssetManager.TextureRegions.i().buffHealthBarIconArmor;
        }
    }
}
