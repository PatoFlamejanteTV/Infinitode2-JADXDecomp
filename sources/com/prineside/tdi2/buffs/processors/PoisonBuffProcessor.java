package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.PoisonBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.GameValueType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.towers.VenomTower;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.REGS;
import java.util.Comparator;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/PoisonBuffProcessor.class */
public final class PoisonBuffProcessor extends BuffProcessor<PoisonBuff> {

    /* renamed from: a, reason: collision with root package name */
    private static final Comparator<PoisonBuff> f1822a = (poisonBuff, poisonBuff2) -> {
        if (poisonBuff.poisonDamage == poisonBuff2.poisonDamage) {
            return 0;
        }
        return poisonBuff.poisonDamage > poisonBuff2.poisonDamage ? -1 : 1;
    };

    /* renamed from: b, reason: collision with root package name */
    @FrameAccumulatorForPerformance
    private byte f1823b;

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.f1823b);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1823b = input.readByte();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_P;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, PoisonBuff poisonBuff) {
        DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.POISON);
        if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
            for (int i = 0; i < buffsByTypeOrNull.size; i++) {
                PoisonBuff poisonBuff2 = ((PoisonBuff[]) buffsByTypeOrNull.items)[i];
                if (poisonBuff2.tower == poisonBuff.tower) {
                    if ((poisonBuff.tower instanceof VenomTower) && poisonBuff.tower.isAbilityInstalled(2)) {
                        if (poisonBuff2.fastShellsStackCount < this.S.gameValue.getIntValue(GameValueType.TOWER_VENOM_A_FAST_MAX_DEBUFFS)) {
                            poisonBuff2.fastShellsStackCount++;
                            poisonBuff2.poisonDamage = (float) (poisonBuff2.poisonDamage + (poisonBuff.poisonDamage * this.S.gameValue.getPercentValueAsMultiplier(GameValueType.TOWER_VENOM_A_FAST_DAMAGE_PER_STACK)));
                        }
                    } else {
                        poisonBuff2.poisonDamage = poisonBuff.poisonDamage;
                    }
                    poisonBuff2.duration = poisonBuff.duration;
                    return true;
                }
            }
        }
        return super.addBuff(enemy, (Enemy) poisonBuff);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final void update(float f) {
        DelayedRemovalArray buffsByTypeOrNull;
        this.f1823b = (byte) (this.f1823b + 1);
        if (this.f1823b == 9) {
            float f2 = f * 9.0f;
            this.f1823b = (byte) 0;
            this.S.map.spawnedEnemies.begin();
            int i = this.S.map.spawnedEnemies.size;
            for (int i2 = 0; i2 < i; i2++) {
                Enemy.EnemyReference enemyReference = this.S.map.spawnedEnemies.items[i2];
                if (enemyReference.enemy != null && (buffsByTypeOrNull = enemyReference.enemy.getBuffsByTypeOrNull(BuffType.POISON)) != null && buffsByTypeOrNull.size != 0) {
                    this.S.TSH.sort.sort(buffsByTypeOrNull, f1822a);
                    float f3 = 1.0f;
                    buffsByTypeOrNull.begin();
                    int i3 = buffsByTypeOrNull.size;
                    for (int i4 = 0; i4 < i3; i4++) {
                        PoisonBuff poisonBuff = ((PoisonBuff[]) buffsByTypeOrNull.items)[i4];
                        float f4 = poisonBuff.poisonDamage * f2 * f3;
                        poisonBuff.duration += enemyReference.enemy.buffFreezingPoisonDurationBonus * 0.01f * f2;
                        if (f4 > 0.0f) {
                            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemyReference.enemy, f4, DamageType.POISON).setTower(poisonBuff.tower).setAbility(poisonBuff.fromAbility).setEfficiency(4));
                        }
                        float f5 = f3 * 0.75f;
                        f3 = f5;
                        if (f5 < 0.15f) {
                            f3 = 0.15f;
                        }
                    }
                    buffsByTypeOrNull.end();
                }
            }
            this.S.map.spawnedEnemies.end();
        }
    }
}
