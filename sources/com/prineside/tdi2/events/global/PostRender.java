package com.prineside.tdi2.events.global;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.PMath;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/global/PostRender.class */
public final class PostRender extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final float f2067a;

    public PostRender(float f) {
        this.f2067a = (!PMath.isFinite(f) || f < 0.0f) ? 0.0f : f;
    }

    public final float getDeltaTime() {
        return this.f2067a;
    }
}
