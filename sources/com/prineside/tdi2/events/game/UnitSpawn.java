package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Unit;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/UnitSpawn.class */
public final class UnitSpawn extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Unit f2063a;

    public UnitSpawn(Unit unit) {
        Preconditions.checkNotNull(unit);
        this.f2063a = unit;
    }

    public final Unit getUnit() {
        return this.f2063a;
    }
}
