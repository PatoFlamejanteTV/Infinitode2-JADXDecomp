package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Explosion;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.buffs.DeathExplosionBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/DeathExplosionBuffProcessor.class */
public final class DeathExplosionBuffProcessor extends BuffProcessor<DeathExplosionBuff> {

    /* renamed from: a, reason: collision with root package name */
    private OnEnemyDie f1819a = new OnEnemyDie(this);

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f1819a);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1819a = (OnEnemyDie) kryo.readObject(input, OnEnemyDie.class);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(this.f1819a);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        this.S.events.getListeners(EnemyDie.class).remove(this.f1819a);
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, DeathExplosionBuff deathExplosionBuff) {
        return a(enemy, deathExplosionBuff);
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_BC;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/DeathExplosionBuffProcessor$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<DeathExplosionBuffProcessor> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(DeathExplosionBuffProcessor deathExplosionBuffProcessor) {
            this.f1759a = deathExplosionBuffProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Enemy enemy = enemyDie.getLastDamage().getEnemy();
            DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.DEATH_EXPLOSION);
            if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                for (int i = 0; i < buffsByTypeOrNull.size; i++) {
                    DeathExplosionBuff deathExplosionBuff = (DeathExplosionBuff) buffsByTypeOrNull.items[i];
                    Explosion explosion = deathExplosionBuff.explosion;
                    deathExplosionBuff.explosion = null;
                    if (explosion != null) {
                        explosion.position.set(enemy.getPosition());
                        ((DeathExplosionBuffProcessor) this.f1759a).S.explosion.register(explosion);
                        explosion.explode();
                    }
                }
            }
        }
    }
}
