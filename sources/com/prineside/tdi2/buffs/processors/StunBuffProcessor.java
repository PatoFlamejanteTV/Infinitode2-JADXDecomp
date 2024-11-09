package com.prineside.tdi2.buffs.processors;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.StunBuff;
import com.prineside.tdi2.components.StunDebuffStats;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/StunBuffProcessor.class */
public final class StunBuffProcessor extends BuffProcessor<StunBuff> {

    /* renamed from: a, reason: collision with root package name */
    @FrameAccumulatorForPerformance
    private byte f1826a;

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.f1826a);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1826a = input.readByte();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_S;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, StunBuff stunBuff) {
        if (enemy.stunDebuffStats == null) {
            enemy.stunDebuffStats = new StunDebuffStats();
        }
        if (enemy.stunDebuffStats.totalCount >= 6 || enemy.hasBuffsByType(BuffType.STUN) || this.S.gameState.randomFloat() < enemy.stunDebuffStats.immunity) {
            return false;
        }
        enemy.stunDebuffStats.addStunnedBy(stunBuff.issuerId);
        StunDebuffStats stunDebuffStats = enemy.stunDebuffStats;
        stunDebuffStats.totalCount = (byte) (stunDebuffStats.totalCount + 1);
        enemy.stunDebuffStats.immunity = 1.0f;
        enemy.stunDebuffStats.passedTilesOnLastStun = enemy.sumPassedTiles;
        return super.addBuff(enemy, (Enemy) stunBuff);
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final void update(float f) {
        StunDebuffStats stunDebuffStats;
        this.f1826a = (byte) (this.f1826a + 1);
        if (this.f1826a == 7) {
            this.f1826a = (byte) 0;
            int i = this.S.map.spawnedEnemies.size;
            for (int i2 = 0; i2 < i; i2++) {
                Enemy enemy = this.S.map.spawnedEnemies.items[i2].enemy;
                if (enemy != null && (stunDebuffStats = enemy.stunDebuffStats) != null) {
                    float f2 = enemy.sumPassedTiles - stunDebuffStats.passedTilesOnLastStun;
                    if (f2 < 3.0f) {
                        stunDebuffStats.immunity = 1.0f;
                    } else {
                        stunDebuffStats.immunity = 1.0f - (f2 * 0.2f);
                    }
                    if (stunDebuffStats.immunity < 0.0f) {
                        stunDebuffStats.immunity = 0.0f;
                    }
                }
            }
        }
    }
}
