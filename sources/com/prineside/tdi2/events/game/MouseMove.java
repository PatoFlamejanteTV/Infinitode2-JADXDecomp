package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MouseMove.class */
public final class MouseMove extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final float f2029a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2030b;
    private final int c;

    public MouseMove(float f, float f2, int i) {
        this.f2029a = f;
        this.f2030b = f2;
        this.c = i;
    }

    public final float getMapX() {
        return this.f2029a;
    }

    public final float getMapY() {
        return this.f2030b;
    }

    public final int getPointer() {
        return this.c;
    }
}
