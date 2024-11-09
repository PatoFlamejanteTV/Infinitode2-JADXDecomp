package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TowerAbilityChange.class */
public final class TowerAbilityChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Tower f2044a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2045b;
    private final boolean c;

    public TowerAbilityChange(Tower tower, int i, boolean z) {
        Preconditions.checkNotNull(tower);
        this.f2044a = tower;
        this.f2045b = i;
        this.c = z;
    }

    public final Tower getTower() {
        return this.f2044a;
    }

    public final int getAbilityIndex() {
        return this.f2045b;
    }

    public final boolean isInstalled() {
        return this.c;
    }
}
