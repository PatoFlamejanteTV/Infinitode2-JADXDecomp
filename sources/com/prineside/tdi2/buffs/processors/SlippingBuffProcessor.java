package com.prineside.tdi2.buffs.processors;

import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.SlippingBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/SlippingBuffProcessor.class */
public final class SlippingBuffProcessor extends BuffProcessor<SlippingBuff> {
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, SlippingBuff slippingBuff) {
        if (enemy.hasBuffsByType(BuffType.SLIPPING)) {
            return false;
        }
        return super.addBuff(enemy, (Enemy) slippingBuff);
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_SL;
    }
}
