package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Buff;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/AddBuffToEnemy.class */
public final class AddBuffToEnemy extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Enemy f1972a;

    /* renamed from: b, reason: collision with root package name */
    private Buff f1973b;
    private boolean c;

    public final AddBuffToEnemy setup(Enemy enemy, Buff buff) {
        setEnemy(enemy);
        setBuff(buff);
        this.c = false;
        return this;
    }

    public final AddBuffToEnemy reset() {
        this.f1972a = null;
        this.f1973b = null;
        this.c = false;
        return this;
    }

    public final boolean isCancelled() {
        return this.c;
    }

    public final void setCancelled(boolean z) {
        this.c = z;
    }

    public final Enemy getEnemy() {
        return this.f1972a;
    }

    public final AddBuffToEnemy setEnemy(Enemy enemy) {
        Preconditions.checkNotNull(enemy, "Enemy can not be null");
        this.f1972a = enemy;
        return this;
    }

    public final Buff getBuff() {
        return this.f1973b;
    }

    public final AddBuffToEnemy setBuff(Buff buff) {
        Preconditions.checkNotNull(buff, "buff can not be null");
        this.f1973b = buff;
        return this;
    }
}
