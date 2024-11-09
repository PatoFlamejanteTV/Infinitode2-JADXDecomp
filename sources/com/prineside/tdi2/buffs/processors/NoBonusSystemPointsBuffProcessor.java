package com.prineside.tdi2.buffs.processors;

import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.NoBonusSystemPointsBuff;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/NoBonusSystemPointsBuffProcessor.class */
public final class NoBonusSystemPointsBuffProcessor extends BuffProcessor<NoBonusSystemPointsBuff> {
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean isDebuff() {
        return true;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, NoBonusSystemPointsBuff noBonusSystemPointsBuff) {
        return a(enemy, noBonusSystemPointsBuff);
    }
}
