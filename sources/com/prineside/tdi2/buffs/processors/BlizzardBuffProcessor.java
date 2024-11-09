package com.prineside.tdi2.buffs.processors;

import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.BlizzardBuff;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/BlizzardBuffProcessor.class */
public final class BlizzardBuffProcessor extends BuffProcessor<BlizzardBuff> {
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, BlizzardBuff blizzardBuff) {
        return a(enemy, blizzardBuff);
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_F;
    }
}
