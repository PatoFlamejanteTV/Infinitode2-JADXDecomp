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
import com.prineside.tdi2.buffs.BonusXpBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.events.game.EnemyTakeDamage;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/BonusXpBuffProcessor.class */
public final class BonusXpBuffProcessor extends BuffProcessor<BonusXpBuff> {

    /* renamed from: a, reason: collision with root package name */
    private OnEnemyDie f1815a = new OnEnemyDie(this);

    /* renamed from: b, reason: collision with root package name */
    private OnEnemyTakeDamage f1816b = new OnEnemyTakeDamage(this);
    private GameSystemProvider c;

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f1815a);
        kryo.writeObject(output, this.f1816b);
        kryo.writeObjectOrNull(output, this.c, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1815a = (OnEnemyDie) kryo.readObject(input, OnEnemyDie.class);
        this.f1816b = (OnEnemyTakeDamage) kryo.readObject(input, OnEnemyTakeDamage.class);
        this.c = (GameSystemProvider) kryo.readObjectOrNull(input, GameSystemProvider.class);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        this.c = gameSystemProvider;
        this.c.events.getListeners(EnemyDie.class).addStateAffecting(this.f1815a);
        this.c.events.getListeners(EnemyTakeDamage.class).addStateAffecting(this.f1816b);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        this.c.events.getListeners(EnemyDie.class).remove(this.f1815a);
        this.c.events.getListeners(EnemyTakeDamage.class).remove(this.f1816b);
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, BonusXpBuff bonusXpBuff) {
        return a(enemy, bonusXpBuff);
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_BXP;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/BonusXpBuffProcessor$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<BonusXpBuffProcessor> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(BonusXpBuffProcessor bonusXpBuffProcessor) {
            this.f1759a = bonusXpBuffProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Enemy enemy;
            DelayedRemovalArray buffsByTypeOrNull;
            DamageRecord lastDamage = enemyDie.getLastDamage();
            if (lastDamage.getTower() != null && (buffsByTypeOrNull = (enemy = lastDamage.getEnemy()).getBuffsByTypeOrNull(BuffType.BONUS_XP)) != null && buffsByTypeOrNull.size != 0) {
                ((BonusXpBuffProcessor) this.f1759a).c.statistics.addStatistic(StatisticsType.XPG_BB, ((BonusXpBuffProcessor) this.f1759a).c.experience.addExperienceBuffed(r0, enemy.getKillExp() * ((BonusXpBuff) buffsByTypeOrNull.first()).bonusXpMultiplier));
            }
        }
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/BonusXpBuffProcessor$OnEnemyTakeDamage.class */
    public static final class OnEnemyTakeDamage extends SerializableListener<BonusXpBuffProcessor> implements Listener<EnemyTakeDamage> {
        private OnEnemyTakeDamage() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyTakeDamage(BonusXpBuffProcessor bonusXpBuffProcessor) {
            this.f1759a = bonusXpBuffProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyTakeDamage enemyTakeDamage) {
            DamageRecord record = enemyTakeDamage.getRecord();
            if (record.getTower() == null) {
                return;
            }
            Enemy enemy = record.getEnemy();
            float factDamage = record.getFactDamage();
            DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.BONUS_XP);
            if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0 && factDamage > 0.0f) {
                ((BonusXpBuffProcessor) this.f1759a).c.statistics.addStatistic(StatisticsType.XPG_BB, ((BonusXpBuffProcessor) this.f1759a).c.experience.addExperienceBuffed(r0, (factDamage / enemy.maxHealth) * enemy.getKillExp() * 2.0f * ((BonusXpBuff) buffsByTypeOrNull.first()).bonusXpMultiplier));
            }
        }
    }
}
