package com.prineside.tdi2.buffs.processors;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.BuffProcessor;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.GameSystemProvider;
import com.prineside.tdi2.SerializableListener;
import com.prineside.tdi2.buffs.ChainReactionBuff;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.Listener;
import com.prineside.tdi2.events.game.EnemyDie;
import com.prineside.tdi2.projectiles.BuffProjectile;
import com.prineside.tdi2.utils.REGS;

@REGS
/* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/ChainReactionBuffProcessor.class */
public final class ChainReactionBuffProcessor extends BuffProcessor<ChainReactionBuff> {
    public static final int MAX_BUFFS_PER_ENEMY = 2;

    /* renamed from: a, reason: collision with root package name */
    private OnEnemyDie f1818a = new OnEnemyDie(this);

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.f1818a);
    }

    @Override // com.prineside.tdi2.BuffProcessor, com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public final void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.f1818a = (OnEnemyDie) kryo.readObject(input, OnEnemyDie.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.BuffProcessor
    public final boolean addBuff(Enemy enemy, ChainReactionBuff chainReactionBuff) {
        DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.CHAIN_REACTION);
        if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
            if (((ChainReactionBuff[]) buffsByTypeOrNull.items)[0].chance > chainReactionBuff.chance) {
                return false;
            }
            removeBuffAtIndex(enemy, BuffType.CHAIN_REACTION, 0);
        }
        return super.addBuff(enemy, (Enemy) chainReactionBuff);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static void b(GameSystemProvider gameSystemProvider, Enemy enemy, Enemy enemy2, float f) {
        Buff cpy;
        if (enemy2.wasAimedAtWithChainReactionBuff) {
            return;
        }
        enemy2.wasAimedAtWithChainReactionBuff = true;
        BuffProjectile obtain = gameSystemProvider.projectile.F.BUFF.obtain();
        for (BuffType buffType : BuffType.values) {
            DelayedRemovalArray delayedRemovalArray = enemy.buffsByType[buffType.ordinal()];
            for (int i = 0; i < delayedRemovalArray.size; i++) {
                Buff buff = ((Buff[]) delayedRemovalArray.items)[i];
                if (gameSystemProvider.buff.getProcessor(buff.getType()).isDebuff() && (cpy = buff.cpy(f)) != null) {
                    obtain.buffs.add(cpy);
                }
            }
        }
        gameSystemProvider.projectile.register(obtain);
        obtain.setup(enemy2, obtain.buffs, enemy.getPosition(), 1.75f);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setRegistered(GameSystemProvider gameSystemProvider) {
        super.setRegistered(gameSystemProvider);
        this.S.events.getListeners(EnemyDie.class).addStateAffecting(this.f1818a);
    }

    @Override // com.prineside.tdi2.Registrable
    public final void setUnregistered() {
        this.S.events.getListeners(EnemyDie.class).remove(this.f1818a);
        super.setUnregistered();
    }

    @Override // com.prineside.tdi2.BuffProcessor
    public final StatisticsType getBuffCountStatistic() {
        return StatisticsType.EB_CR;
    }

    @REGS
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/buffs/processors/ChainReactionBuffProcessor$OnEnemyDie.class */
    public static final class OnEnemyDie extends SerializableListener<ChainReactionBuffProcessor> implements Listener<EnemyDie> {
        private OnEnemyDie() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public OnEnemyDie(ChainReactionBuffProcessor chainReactionBuffProcessor) {
            this.f1759a = chainReactionBuffProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.prineside.tdi2.events.Listener
        public final void handleEvent(EnemyDie enemyDie) {
            Enemy enemy = enemyDie.getLastDamage().getEnemy();
            DelayedRemovalArray buffsByTypeOrNull = enemy.getBuffsByTypeOrNull(BuffType.CHAIN_REACTION);
            if (buffsByTypeOrNull != null && buffsByTypeOrNull.size != 0) {
                ChainReactionBuff chainReactionBuff = (ChainReactionBuff) buffsByTypeOrNull.first();
                float f = chainReactionBuff.rangeInTiles * 64.0f;
                float f2 = chainReactionBuff.chance;
                ((ChainReactionBuffProcessor) this.f1759a).S.map.getEnemiesInRect(enemy.getPosition().x - f, enemy.getPosition().y - f, enemy.getPosition().x + f, enemy.getPosition().y + f, (enemyReference, f3, f4, f5) -> {
                    Enemy enemy2 = enemyReference.enemy;
                    if (enemy2 != null && enemy2 != enemy && ((ChainReactionBuffProcessor) this.f1759a).S.gameState.randomFloat() <= f2) {
                        ChainReactionBuffProcessor.b(((ChainReactionBuffProcessor) this.f1759a).S, enemy, enemy2, chainReactionBuff.durationMultiplier);
                        return true;
                    }
                    return true;
                });
            }
        }
    }
}
