package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TowerUpgrade.class */
public final class TowerUpgrade extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Tower f2058a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2059b;

    public TowerUpgrade(Tower tower, int i) {
        Preconditions.checkNotNull(tower);
        this.f2058a = tower;
        this.f2059b = i;
    }

    public final Tower getTower() {
        return this.f2058a;
    }

    public final int getPrice() {
        return this.f2059b;
    }
}
