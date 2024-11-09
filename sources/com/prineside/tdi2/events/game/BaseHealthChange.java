package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/BaseHealthChange.class */
public final class BaseHealthChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private int f1974a;

    public BaseHealthChange(int i) {
        this.f1974a = i;
    }

    public final int getOldValue() {
        return this.f1974a;
    }

    public final BaseHealthChange setOldValue(int i) {
        this.f1974a = i;
        return this;
    }
}
