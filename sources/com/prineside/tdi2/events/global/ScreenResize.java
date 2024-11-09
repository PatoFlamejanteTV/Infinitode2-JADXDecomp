package com.prineside.tdi2.events.global;

import com.prineside.tdi2.events.StoppableEvent;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/global/ScreenResize.class */
public final class ScreenResize extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final int f2070a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2071b;

    public ScreenResize(int i, int i2) {
        i = i <= 0 ? 1 : i;
        i2 = i2 <= 0 ? 1 : i2;
        this.f2070a = i;
        this.f2071b = i2;
    }

    public final int getWidth() {
        return this.f2070a;
    }

    public final int getHeight() {
        return this.f2071b;
    }
}
