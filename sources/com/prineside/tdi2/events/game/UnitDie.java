package com.prineside.tdi2.events.game;

import com.badlogic.gdx.utils.Null;
import com.google.common.base.Preconditions;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/UnitDie.class */
public final class UnitDie extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Unit f2061a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private final Enemy f2062b;

    public UnitDie(Unit unit, @Null Enemy enemy) {
        Preconditions.checkNotNull(unit);
        this.f2061a = unit;
        this.f2062b = enemy;
    }

    public final Unit getUnit() {
        return this.f2061a;
    }

    public final Enemy getKiller() {
        return this.f2062b;
    }
}
