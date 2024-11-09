package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MdpsUpdate.class */
public final class MdpsUpdate extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final double f2005a;

    public MdpsUpdate(double d) {
        this.f2005a = d;
    }

    public final double getOldValue() {
        return this.f2005a;
    }
}
