package com.prineside.tdi2;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.enums.AchievementType;
import com.prineside.tdi2.enums.BuffType;
import com.prineside.tdi2.enums.StatisticsType;
import com.prineside.tdi2.events.EventListeners;
import com.prineside.tdi2.events.game.AddBuffToEnemy;
import com.prineside.tdi2.events.game.RemoveBuffFromEnemy;
import com.prineside.tdi2.utils.NAGS;
import com.prineside.tdi2.utils.REGS;

@REGS(arrayLevels = 1)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/BuffProcessor.class */
public abstract class BuffProcessor<T extends Buff> extends Registrable {
    public ListenerGroup<BuffProcessorListener> listeners = new ListenerGroup<>(BuffProcessorListener.class);

    /* renamed from: a, reason: collision with root package name */
    @NAGS
    private AddBuffToEnemy f1669a = new AddBuffToEnemy();

    @REGS(classOnly = true)
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/BuffProcessor$BuffProcessorListener.class */
    public interface BuffProcessorListener extends GameListener {
        void buffAdded(Enemy enemy, Buff buff);

        void buffRemoved(Enemy enemy, Buff buff);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void write(Kryo kryo, Output output) {
        super.write(kryo, output);
        kryo.writeObject(output, this.listeners);
    }

    @Override // com.prineside.tdi2.Registrable, com.esotericsoftware.kryo.KryoSerializable
    public void read(Kryo kryo, Input input) {
        super.read(kryo, input);
        this.listeners = (ListenerGroup) kryo.readObject(input, ListenerGroup.class);
    }

    public StatisticsType getBuffCountStatistic() {
        return null;
    }

    public boolean isDebuff() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean a(Enemy enemy, T t) {
        if (enemy.hasBuffsByType(t.getType())) {
            removeBuffAtIndex(enemy, t.getType(), 0);
        }
        return b(enemy, t);
    }

    private boolean b(Enemy enemy, T t) {
        StatisticsType buffCountStatistic;
        AddBuffToEnemy addBuffToEnemy;
        boolean z;
        if (!enemy.canBeBuffed(t.getType())) {
            return false;
        }
        EventListeners listeners = this.S.events.getListeners(AddBuffToEnemy.class);
        if (listeners.size() != 0) {
            if (this.f1669a == null) {
                addBuffToEnemy = new AddBuffToEnemy();
                z = false;
            } else {
                addBuffToEnemy = this.f1669a;
                this.f1669a = null;
                z = true;
            }
            listeners.trigger(addBuffToEnemy.setup(enemy, t));
            boolean isCancelled = addBuffToEnemy.isCancelled();
            if (z) {
                this.f1669a = addBuffToEnemy.reset();
            }
            if (isCancelled) {
                return false;
            }
        }
        enemy.initBuffsByTypeArray();
        if (this.S.syncChecking) {
            this.S.map.spawnedEnemies.begin();
            for (int i = 0; i < this.S.map.spawnedEnemies.size; i++) {
                Enemy enemy2 = this.S.map.spawnedEnemies.items[i].enemy;
                if (enemy2 != null && enemy2.buffsByType != null) {
                    DelayedRemovalArray delayedRemovalArray = enemy2.buffsByType[t.getType().ordinal()];
                    for (int i2 = 0; i2 < delayedRemovalArray.size; i2++) {
                        if (delayedRemovalArray.items[i2] == t) {
                            throw new IllegalStateException("Buff " + t + " is already applied to " + enemy2 + ", can't apply to " + enemy);
                        }
                    }
                }
            }
            this.S.map.spawnedEnemies.end();
        }
        enemy.buffsByType[t.getType().ordinal()].add(t);
        this.listeners.begin();
        for (int i3 = 0; i3 < this.listeners.size(); i3++) {
            this.listeners.get(i3).buffAdded(enemy, t);
        }
        this.listeners.end();
        this.S.statistics.addStatistic(StatisticsType.EB, 1.0d);
        if (enemy.buffsAppliedByType == null) {
            enemy.buffsAppliedByType = new boolean[BuffType.values.length];
        }
        if (!enemy.buffsAppliedByType[t.getType().ordinal()] && (buffCountStatistic = getBuffCountStatistic()) != null) {
            this.S.statistics.addStatistic(buffCountStatistic, 1.0d);
            enemy.buffsAppliedByType[t.getType().ordinal()] = true;
        }
        if (this.S.achievement.isActive()) {
            int i4 = 0;
            for (DelayedRemovalArray delayedRemovalArray2 : enemy.buffsByType) {
                if (delayedRemovalArray2.size != 0) {
                    i4++;
                }
            }
            this.S.achievement.setProgress(AchievementType.MASS_BUFF_ENEMY, i4);
            return true;
        }
        return true;
    }

    public boolean addBuff(Enemy enemy, T t) {
        return b(enemy, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeBuffAtIndex(Enemy enemy, BuffType buffType, int i) {
        Buff buff = ((Buff[]) enemy.buffsByType[buffType.ordinal()].items)[i];
        this.listeners.begin();
        for (int i2 = 0; i2 < this.listeners.size(); i2++) {
            this.listeners.get(i2).buffRemoved(enemy, buff);
        }
        this.listeners.end();
        enemy.buffsByType[buffType.ordinal()].removeIndex(i);
        this.S.events.trigger(new RemoveBuffFromEnemy(enemy, buff));
    }

    public void removeBuff(Enemy enemy, T t) {
        int indexOf;
        if (enemy.buffsByType != null && (indexOf = enemy.buffsByType[t.getType().ordinal()].indexOf(t, true)) != -1) {
            removeBuffAtIndex(enemy, t.getType(), indexOf);
        }
    }

    public void removeAllBuffs(Enemy enemy, BuffType buffType) {
        if (enemy.buffsByType == null) {
            return;
        }
        DelayedRemovalArray delayedRemovalArray = enemy.buffsByType[buffType.ordinal()];
        delayedRemovalArray.begin();
        for (int i = 0; i < delayedRemovalArray.size; i++) {
            removeBuffAtIndex(enemy, buffType, i);
        }
        delayedRemovalArray.end();
    }

    public void update(float f) {
    }
}
