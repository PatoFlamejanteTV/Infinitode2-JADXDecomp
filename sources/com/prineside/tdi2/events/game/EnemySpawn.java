package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/EnemySpawn.class */
public final class EnemySpawn extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Enemy f1991a;

    public EnemySpawn(Enemy enemy) {
        setEnemy(enemy);
    }

    public final Enemy getEnemy() {
        return this.f1991a;
    }

    public final EnemySpawn setEnemy(Enemy enemy) {
        Preconditions.checkNotNull(enemy);
        this.f1991a = enemy;
        return this;
    }
}
