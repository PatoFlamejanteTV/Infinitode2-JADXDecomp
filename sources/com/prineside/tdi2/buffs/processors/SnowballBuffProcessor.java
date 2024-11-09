package com.prineside.tdi2.buffs.processors;

import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.SnowballBuff;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/SnowballBuffProcessor.class */
public final class SnowballBuffProcessor extends BuffProcessor<SnowballBuff> {
    public static final float[] STUN_DURATION_BY_STUN_COUNT;
    public static final int MAX_HITS_ONE_ENEMY;

    static {
        float[] fArr = {1.0f, 0.8f, 0.65f, 0.5f, 0.35f, 0.2f};
        STUN_DURATION_BY_STUN_COUNT = fArr;
        MAX_HITS_ONE_ENEMY = fArr.length;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, SnowballBuff snowballBuff) {
        if (enemy.buffSnowballHits >= MAX_HITS_ONE_ENEMY || enemy.hasBuffsByType(BuffType.SNOWBALL)) {
            return false;
        }
        boolean addBuff = super.addBuff(enemy, (Enemy) snowballBuff);
        if (addBuff) {
            enemy.buffSnowballHits++;
            this.S.achievement.setProgress(AchievementType.HIT_ENEMY_WITH_SNOWBALLS, enemy.buffSnowballHits);
        }
        return addBuff;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_F;
    }
}
