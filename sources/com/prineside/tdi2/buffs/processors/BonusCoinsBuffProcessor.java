package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.buffs.BonusCoinsBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/BonusCoinsBuffProcessor.class */
public final class BonusCoinsBuffProcessor extends BuffProcessor<BonusCoinsBuff> {

    /* renamed from: a, reason: collision with root package name */
    private OnEnemyDie f1813a = new OnEnemyDie(this);

    /* renamed from: b, reason: collision with root package name */
    private GameSystemProvider f1814b;

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f1813a);
        kryo.writeObjectOrNull(output, this.f1814b, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1813a = (OnEnemyDie) kryo.readObject(input, OnEnemyDie.class);
        this.f1814b = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        this.f1814b = gameSystemProvider;
        this.f1814b.events.getListeners(EnemyDie.class).addStateAffectingWithPriority(this.f1813a, 1000);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        this.f1814b.events.getListeners(EnemyDie.class).remove(this.f1813a);
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, BonusCoinsBuff bonusCoinsBuff) {
        return a(enemy, bonusCoinsBuff);
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_BC;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/BonusCoinsBuffProcessor$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<BonusCoinsBuffProcessor> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(BonusCoinsBuffProcessor bonusCoinsBuffProcessor) {
            this.f1759a = bonusCoinsBuffProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            DamageRecord lastDamage = enemyDie.getLastDamage();
            Enemy enemy = lastDamage.getEnemy();
            Tower tower = lastDamage.getTower();
            DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.BONUS_COINS);
            if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                BonusCoinsBuff bonusCoinsBuff = (BonusCoinsBuff) buffsByTypeOrNull.first();
                float f = bonusCoinsBuff.bonusCoinsMultiplier * enemy.bounty;
                if (tower != null && bonusCoinsBuff.issuer == tower) {
                    f *= 0.5f;
                }
                if (f > 0.0f) {
                    enemy.bounty += f;
                    if (bonusCoinsBuff.issuer != null) {
                        bonusCoinsBuff.issuer.bonusCoinsBrought += f;
                    } else if (tower != null) {
                        tower.bonusCoinsBrought += f;
                    }
                }
            }
        }
    }
}
