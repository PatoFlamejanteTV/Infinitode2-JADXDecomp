package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.buffs.BurnBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.DamageType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/BurnBuffProcessor.class */
public final class BurnBuffProcessor extends BuffProcessor<BurnBuff> {

    /* renamed from: a, reason: collision with root package name */
    @FrameAccumulatorForPerformance
    private byte f1817a;

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.f1817a);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1817a = input.readByte();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_I;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, BurnBuff burnBuff) {
        DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.BURN);
        if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
            BurnBuff burnBuff2 = ((BurnBuff[]) buffsByTypeOrNull.items)[0];
            if (burnBuff2.fireDamage * burnBuff2.duration < burnBuff.duration * burnBuff.fireDamage) {
                removeBuffAtIndex(enemy, BuffType.BURN, 0);
                return super.addBuff(enemy, (Enemy) burnBuff);
            }
            return false;
        }
        return super.addBuff(enemy, (Enemy) burnBuff);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final void update(float f) {
        DelayedRemovalArray buffsByTypeOrNull;
        this.f1817a = (byte) (this.f1817a + 1);
        if (this.f1817a == 8) {
            float f2 = f * 8.0f;
            this.f1817a = (byte) 0;
            this.S.map.spawnedEnemies.begin();
            int i = this.S.map.spawnedEnemies.size;
            for (int i2 = 0; i2 < i; i2++) {
                Enemy enemy = this.S.map.spawnedEnemies.items[i2].enemy;
                if (enemy != null && (buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.BURN)) != null && buffsByTypeOrNull.size != 0) {
                    buffsByTypeOrNull.begin();
                    int i3 = buffsByTypeOrNull.size;
                    for (int i4 = 0; i4 < i3; i4++) {
                        BurnBuff burnBuff = ((BurnBuff[]) buffsByTypeOrNull.items)[i4];
                        float buffVulnerability = burnBuff.fireDamage * f2 * enemy.getBuffVulnerability(BuffType.BURN) * (1.0f + (burnBuff.bonusDamagePerEnemyNearby * enemy.otherEnemiesNearby));
                        if (burnBuff.tower != null && !burnBuff.tower.isRegistered()) {
                            burnBuff.tower = null;
                        }
                        if (buffVulnerability > 0.0f) {
                            this.S.damage.queueDamage(this.S.damage.obtainRecord().setup(enemy, buffVulnerability, DamageType.FIRE).setTower(burnBuff.tower).setAbility(burnBuff.fromAbility).setEfficiency(4));
                        }
                    }
                    buffsByTypeOrNull.end();
                }
            }
            this.S.map.spawnedEnemies.end();
        }
    }
}
