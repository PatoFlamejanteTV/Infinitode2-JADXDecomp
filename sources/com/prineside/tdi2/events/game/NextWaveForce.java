package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/NextWaveForce.class */
public final class NextWaveForce extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final int f2031a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2032b;
    private final float c;

    public NextWaveForce(int i, int i2, float f) {
        this.f2031a = i;
        this.f2032b = i2;
        this.c = f;
    }

    public final int getBonusMoney() {
        return this.f2031a;
    }

    public final int getBonusScore() {
        return this.f2032b;
    }

    public final float getTime() {
        return this.c;
    }
}
