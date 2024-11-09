package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/GameValuesRecalculate.class */
public final class GameValuesRecalculate extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final double[] f1994a;

    public GameValuesRecalculate(double[] dArr) {
        Preconditions.checkNotNull(dArr);
        this.f1994a = dArr;
    }

    public final double[] getOldValues() {
        return this.f1994a;
    }
}
