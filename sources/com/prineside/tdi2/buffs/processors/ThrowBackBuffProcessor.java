package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.utils.IntSet;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.ThrowBackBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/ThrowBackBuffProcessor.class */
public final class ThrowBackBuffProcessor extends BuffProcessor<ThrowBackBuff> {
    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_TB;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, ThrowBackBuff throwBackBuff) {
        if (enemy.hasBuffsByType(BuffType.THROW_BACK)) {
            return false;
        }
        if ((enemy.thrownBackBy == null || (!enemy.thrownBackBy.contains(throwBackBuff.ownerId) && enemy.thrownBackBy.size < 3)) && super.addBuff(enemy, (Enemy) throwBackBuff)) {
            if (enemy.thrownBackBy == null) {
                enemy.thrownBackBy = new IntSet();
            }
            enemy.thrownBackBy.add(throwBackBuff.ownerId);
            return true;
        }
        return false;
    }
}
