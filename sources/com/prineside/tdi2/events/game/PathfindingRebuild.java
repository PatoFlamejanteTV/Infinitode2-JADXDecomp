package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/PathfindingRebuild.class */
public final class PathfindingRebuild extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f2033a;

    public PathfindingRebuild(boolean z) {
        this.f2033a = z;
    }

    public final boolean isDefaultPathsAffected() {
        return this.f2033a;
    }
}
