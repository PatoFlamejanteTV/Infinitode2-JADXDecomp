package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/CoreTileLevelUp.class */
public final class CoreTileLevelUp extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final CoreTile f1981a;

    public CoreTileLevelUp(CoreTile coreTile) {
        Preconditions.checkNotNull(coreTile);
        this.f1981a = coreTile;
    }

    public final CoreTile getCoreTile() {
        return this.f1981a;
    }
}
