package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/GameStateTick.class */
public final class GameStateTick extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private float f1993a;

    public GameStateTick(float f) {
        setDeltaTime(f);
    }

    public final float getDeltaTime() {
        return this.f1993a;
    }

    public final GameStateTick setDeltaTime(float f) {
        Preconditions.checkArgument(PMath.isFinite(f) && f >= 0.0f, "Invalid delta time: %s", Float.valueOf(f));
        this.f1993a = f;
        return this;
    }
}
