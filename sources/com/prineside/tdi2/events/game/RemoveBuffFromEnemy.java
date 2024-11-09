package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/RemoveBuffFromEnemy.class */
public final class RemoveBuffFromEnemy extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Enemy f2036a;

    /* renamed from: b, reason: collision with root package name */
    private Buff f2037b;

    public RemoveBuffFromEnemy(Enemy enemy, Buff buff) {
        Preconditions.checkNotNull(enemy);
        Preconditions.checkNotNull(buff);
        this.f2036a = enemy;
        this.f2037b = buff;
    }

    public final Enemy getEnemy() {
        return this.f2036a;
    }

    public final Buff getBuff() {
        return this.f2037b;
    }
}
