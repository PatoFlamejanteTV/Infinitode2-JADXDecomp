package com.prineside.tdi2.events.global;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/global/StartRender.class */
public final class StartRender extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private float f2072a;

    public final StartRender setDeltaTime(float f) {
        if (!PMath.isFinite(f) || f < 0.0f) {
            f = 0.0f;
        }
        this.f2072a = f;
        return this;
    }

    public final float getDeltaTime() {
        return this.f2072a;
    }
}
