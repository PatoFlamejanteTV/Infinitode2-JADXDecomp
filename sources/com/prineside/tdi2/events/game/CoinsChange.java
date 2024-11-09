package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/CoinsChange.class */
public final class CoinsChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private int f1979a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1980b;

    public CoinsChange(int i, boolean z) {
        setOldValue(i);
        setGained(z);
    }

    public final int getOldValue() {
        return this.f1979a;
    }

    public final CoinsChange setOldValue(int i) {
        this.f1979a = i;
        return this;
    }

    public final boolean isGained() {
        return this.f1980b;
    }

    public final CoinsChange setGained(boolean z) {
        this.f1980b = z;
        return this;
    }
}
