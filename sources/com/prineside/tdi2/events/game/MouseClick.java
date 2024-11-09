package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MouseClick.class */
public final class MouseClick extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final float f2027a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2028b;
    private final int c;
    private final int d;

    public MouseClick(float f, float f2, int i, int i2) {
        this.f2027a = f;
        this.f2028b = f2;
        this.c = i;
        this.d = i2;
    }

    public final float getMapX() {
        return this.f2027a;
    }

    public final float getMapY() {
        return this.f2028b;
    }

    public final int getPointer() {
        return this.c;
    }

    public final int getButton() {
        return this.d;
    }
}
