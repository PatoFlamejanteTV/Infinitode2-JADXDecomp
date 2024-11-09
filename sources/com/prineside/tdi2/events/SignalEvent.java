package com.prineside.tdi2.events;

import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/SignalEvent.class */
public abstract class SignalEvent implements Event {
    @Override // com.prineside.tdi2.events.Event
    public final boolean isStopped() {
        return false;
    }

    @Override // com.prineside.tdi2.events.Event
    public final void stop() {
        throw new IllegalStateException("This event can not be stopped");
    }
}
