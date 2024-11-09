package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.buffs.RegenerationBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.utils.FrameAccumulatorForPerformance;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/RegenerationBuffProcessor.class */
public final class RegenerationBuffProcessor extends BuffProcessor<RegenerationBuff> {

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private ParticleEffectPool f1824a;

    /* renamed from: b, reason: collision with root package name */
    @FrameAccumulatorForPerformance
    private byte f1825b;

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        output.writeByte(this.f1825b);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1825b = input.readByte();
    }

    public RegenerationBuffProcessor() {
        if (Game.i.assetManager != null) {
            this.f1824a = Game.i.assetManager.getParticleEffectPool("regeneration.prt");
        }
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean isDebuff() {
        return false;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return null;
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, RegenerationBuff regenerationBuff) {
        return addBuffStackSameSourceRemoveOthers(enemy, regenerationBuff, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean addBuffStackSameSourceRemoveOthers(Enemy enemy, RegenerationBuff regenerationBuff, boolean z) {
        boolean z2 = true;
        DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.REGENERATION);
        if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
            RegenerationBuff regenerationBuff2 = (RegenerationBuff) buffsByTypeOrNull.first();
            if (z) {
                if (regenerationBuff2.issuer == regenerationBuff.issuer) {
                    float f = (regenerationBuff2.duration * regenerationBuff2.hpPerSecond) + (regenerationBuff.duration * regenerationBuff.hpPerSecond);
                    regenerationBuff2.duration = Math.max(regenerationBuff2.duration, regenerationBuff.duration);
                    regenerationBuff2.hpPerSecond = f / regenerationBuff2.duration;
                    z2 = false;
                } else {
                    removeBuffAtIndex(enemy, BuffType.REGENERATION, 0);
                }
            } else {
                if (regenerationBuff2.hpPerSecond * regenerationBuff2.duration < regenerationBuff.duration * regenerationBuff.hpPerSecond) {
                    removeBuffAtIndex(enemy, BuffType.REGENERATION, 0);
                }
                z2 = false;
            }
        }
        if (z2) {
            return super.addBuff(enemy, (Enemy) regenerationBuff);
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final void update(float f) {
        this.f1825b = (byte) (this.f1825b + 1);
        if (this.f1825b == 2) {
            float f2 = f * 2.0f;
            this.f1825b = (byte) 0;
            this.S.map.spawnedEnemies.begin();
            int i = this.S.map.spawnedEnemies.size;
            for (int i2 = 0; i2 < i; i2++) {
                Enemy enemy = this.S.map.spawnedEnemies.items[i2].enemy;
                if (enemy != null) {
                    boolean z = false;
                    DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.REGENERATION);
                    if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                        buffsByTypeOrNull.begin();
                        int i3 = buffsByTypeOrNull.size;
                        for (int i4 = 0; i4 < i3; i4++) {
                            if (enemy.getHealth() < enemy.maxHealth) {
                                float health = enemy.getHealth() + (((RegenerationBuff) buffsByTypeOrNull.get(i4)).hpPerSecond * f2);
                                float f3 = health;
                                if (health > enemy.maxHealth) {
                                    f3 = enemy.maxHealth;
                                }
                                enemy.setHealth(f3);
                                z = true;
                            }
                        }
                        buffsByTypeOrNull.end();
                    }
                    if (z) {
                        if (enemy.getAttachedParticle(Enemy.ATTACHED_PARTICLE_REGENERATION_BUFF) == null && this.S._particle != null && Game.i.settingsManager.isParticlesDrawing() && !this.S._particle.willParticleBeSkipped() && enemy.otherEnemiesNearby < 8) {
                            ParticleEffectPool.PooledEffect obtain = this.f1824a.obtain();
                            enemy.attachParticle(Enemy.ATTACHED_PARTICLE_REGENERATION_BUFF, obtain);
                            this.S._particle.addParticle(obtain, false);
                        }
                    } else {
                        ParticleEffectPool.PooledEffect detachParticle = enemy.detachParticle(Enemy.ATTACHED_PARTICLE_REGENERATION_BUFF);
                        if (detachParticle != null) {
                            detachParticle.allowCompletion();
                        }
                    }
                }
            }
            this.S.map.spawnedEnemies.end();
        }
    }
}
