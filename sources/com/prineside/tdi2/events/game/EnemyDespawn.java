package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/EnemyDespawn.class */
public final class EnemyDespawn extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Enemy f1984a;

    public EnemyDespawn(Enemy enemy) {
        setEnemy(enemy);
    }

    public final Enemy getEnemy() {
        return this.f1984a;
    }

    public final EnemyDespawn setEnemy(Enemy enemy) {
        Preconditions.checkNotNull(enemy);
        this.f1984a = enemy;
        return this;
    }
}
