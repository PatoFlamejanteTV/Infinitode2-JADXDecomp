package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.tiles.CoreTile;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/CoreTileUpgradeInstall.class */
public final class CoreTileUpgradeInstall extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final CoreTile f1982a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1983b;
    private final int c;

    public CoreTileUpgradeInstall(CoreTile coreTile, int i, int i2) {
        Preconditions.checkNotNull(coreTile, "coreTile can not be null");
        this.c = i;
        this.f1983b = i2;
        this.f1982a = coreTile;
    }

    public final CoreTile getCoreTile() {
        return this.f1982a;
    }

    public final int getRow() {
        return this.f1983b;
    }

    public final int getCol() {
        return this.c;
    }
}
