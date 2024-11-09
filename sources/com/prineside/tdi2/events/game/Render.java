package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/Render.class */
public final class Render extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private float f2038a;

    /* renamed from: b, reason: collision with root package name */
    private float f2039b;
    private float c;

    public Render(float f, float f2) {
        setRealDeltaTime(f);
        setInGameDeltaTime(f2);
    }

    public final float getRealDeltaTime() {
        return this.f2038a;
    }

    public final void setRealDeltaTime(float f) {
        this.f2038a = f;
    }

    public final float getInGameDeltaTime() {
        return this.f2039b;
    }

    public final void setInGameDeltaTime(float f) {
        this.f2039b = f;
    }

    public final float getInterpolatedTime() {
        return this.c;
    }

    public final void setInterpolatedTime(float f) {
        this.c = f;
    }
}
