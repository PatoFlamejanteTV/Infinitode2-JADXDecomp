package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TowerBuild.class */
public final class TowerBuild extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Tower f2047a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2048b;

    public TowerBuild(Tower tower, int i) {
        Preconditions.checkNotNull(tower);
        this.f2047a = tower;
        this.f2048b = i;
    }

    public final Tower getTower() {
        return this.f2047a;
    }

    public final int getPrice() {
        return this.f2048b;
    }
}
