package com.prineside.tdi2.events;

import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/CancellableStoppableEvent.class */
public abstract class CancellableStoppableEvent extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private boolean f1959a;

    public final boolean isCancelled() {
        return this.f1959a;
    }

    public final void cancel() {
        this.f1959a = true;
    }
}
