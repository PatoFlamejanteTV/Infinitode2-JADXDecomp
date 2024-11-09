package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TowerSell.class */
public final class TowerSell extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Tower f2056a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2057b;

    public TowerSell(Tower tower, int i) {
        Preconditions.checkNotNull(tower);
        this.f2056a = tower;
        this.f2057b = i;
    }

    public final Tower getTower() {
        return this.f2056a;
    }

    public final int getReturnedCoins() {
        return this.f2057b;
    }
}
