package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TowerPreSell.class */
public final class TowerPreSell extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Tower f2054a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2055b;

    public TowerPreSell(Tower tower, int i) {
        Preconditions.checkNotNull(tower);
        this.f2054a = tower;
        this.f2055b = i;
    }

    public final Tower getTower() {
        return this.f2054a;
    }

    public final int getReturnedCoins() {
        return this.f2055b;
    }
}
