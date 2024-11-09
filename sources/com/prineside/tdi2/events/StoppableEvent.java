package com.prineside.tdi2.events;

import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/StoppableEvent.class */
public abstract class StoppableEvent implements Event {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1968a;

    @Override // com.prineside.tdi2.events.Event
    public final boolean isStopped() {
        return this.f1968a;
    }

    @Override // com.prineside.tdi2.events.Event
    public final void stop() {
        this.f1968a = true;
    }
}
