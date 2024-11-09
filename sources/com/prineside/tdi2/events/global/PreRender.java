package com.prineside.tdi2.events.global;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/global/PreRender.class */
public final class PreRender extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final float f2068a;

    public PreRender(float f) {
        this.f2068a = (!PMath.isFinite(f) || f < 0.0f) ? 0.0f : f;
    }

    public final float getDeltaTime() {
        return this.f2068a;
    }
}
