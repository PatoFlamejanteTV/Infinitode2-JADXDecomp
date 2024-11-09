package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.FreezingBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/FreezingBuffProcessor.class */
public final class FreezingBuffProcessor extends BuffProcessor<FreezingBuff> {

    /* renamed from: a, reason: collision with root package name */
    @FrameAccumulatorForPerformance
    private byte f1820a;

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.f1820a);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1820a = input.readByte();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_F;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, FreezingBuff freezingBuff) {
        DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.FREEZING);
        if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
            buffsByTypeOrNull.begin();
            int i = 0;
            while (true) {
                if (i >= buffsByTypeOrNull.size) {
                    break;
                }
                if (((FreezingBuff[]) buffsByTypeOrNull.items)[i].tower != freezingBuff.tower) {
                    i++;
                } else {
                    removeBuffAtIndex(enemy, BuffType.FREEZING, i);
                    break;
                }
            }
            buffsByTypeOrNull.end();
        }
        return super.addBuff(enemy, (Enemy) freezingBuff);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final void update(float f) {
        this.f1820a = (byte) (this.f1820a + 1);
        if (this.f1820a == 3) {
            float f2 = f * 3.0f;
            this.f1820a = (byte) 0;
            this.S.map.spawnedEnemies.begin();
            int i = this.S.map.spawnedEnemies.size;
            for (int i2 = 0; i2 < i; i2++) {
                Enemy enemy = this.S.map.spawnedEnemies.items[i2].enemy;
                if (enemy != null) {
                    DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.FREEZING);
                    if (enemy.buffFreezingPercent != 0.0f || (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0)) {
                        boolean hasBuffsByType = enemy.hasBuffsByType(BuffType.BURN);
                        if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                            enemy.buffFreezingLightningLengthBonus = 0.0f;
                            enemy.buffFreezingPoisonDurationBonus = 0.0f;
                            float f3 = 0.0f;
                            buffsByTypeOrNull.begin();
                            int i3 = buffsByTypeOrNull.size;
                            for (int i4 = 0; i4 < i3; i4++) {
                                FreezingBuff freezingBuff = ((FreezingBuff[]) buffsByTypeOrNull.items)[i4];
                                if (enemy.buffFreezingPercent < freezingBuff.maxPercent) {
                                    float f4 = freezingBuff.maxPercent - enemy.buffFreezingPercent;
                                    float f5 = f2 * freezingBuff.speed;
                                    if (hasBuffsByType) {
                                        f5 *= 0.333f;
                                    }
                                    if (f5 > f4) {
                                        f5 = f4;
                                    }
                                    enemy.buffFreezingPercent += f5;
                                    if (freezingBuff.tower != null) {
                                        this.S.experience.addExperienceBuffed(freezingBuff.tower, f5 * 0.02f);
                                    }
                                }
                                if (freezingBuff.maxPercent > f3) {
                                    f3 = freezingBuff.maxPercent;
                                }
                                if (enemy.buffFreezingPoisonDurationBonus < freezingBuff.poisonDurationBonus) {
                                    enemy.buffFreezingPoisonDurationBonus = freezingBuff.poisonDurationBonus;
                                }
                                if (enemy.buffFreezingLightningLengthBonus < freezingBuff.lightningLengthBonus) {
                                    enemy.buffFreezingLightningLengthBonus = freezingBuff.lightningLengthBonus;
                                }
                            }
                            buffsByTypeOrNull.end();
                            if (hasBuffsByType) {
                                f3 *= 0.67f;
                            }
                            if (enemy.buffFreezingPercent > f3) {
                                enemy.buffFreezingPercent -= StrictMath.min(100.0f * f2, enemy.buffFreezingPercent - f3);
                                if (enemy.buffFreezingPercent < 0.0f) {
                                    enemy.buffFreezingPercent = 0.0f;
                                }
                            }
                        } else {
                            float f6 = 100.0f * f2;
                            if (hasBuffsByType) {
                                f6 *= 3.0f;
                            }
                            enemy.buffFreezingPercent -= f6;
                            if (enemy.buffFreezingPercent < 0.0f) {
                                enemy.buffFreezingPercent = 0.0f;
                            }
                            enemy.buffFreezingLightningLengthBonus = 0.0f;
                            enemy.buffFreezingPoisonDurationBonus = 0.0f;
                        }
                        if (enemy.buffFreezingPercent > 100.0f) {
                            enemy.buffFreezingPercent = 100.0f;
                        }
                    }
                }
            }
            this.S.map.spawnedEnemies.end();
        }
    }
}
