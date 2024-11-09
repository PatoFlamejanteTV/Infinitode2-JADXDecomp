package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TowerCustomButtonPress.class */
public final class TowerCustomButtonPress extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Tower f2049a;

    public TowerCustomButtonPress(Tower tower) {
        Preconditions.checkNotNull(tower);
        this.f2049a = tower;
    }

    public final Tower getTower() {
        return this.f2049a;
    }
}
