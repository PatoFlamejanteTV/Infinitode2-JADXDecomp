package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Building;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.tiles.PlatformTile;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/BuildingRemove.class */
public final class BuildingRemove extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Building f1977a;

    /* renamed from: b, reason: collision with root package name */
    private final PlatformTile f1978b;

    public BuildingRemove(Building building, PlatformTile platformTile) {
        Preconditions.checkNotNull(building, "building can not be null");
        Preconditions.checkNotNull(platformTile, "oldTile can not be null");
        this.f1977a = building;
        this.f1978b = platformTile;
    }

    public final Building getBuilding() {
        return this.f1977a;
    }

    public final PlatformTile getOldTile() {
        return this.f1978b;
    }
}
