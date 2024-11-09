package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.tiles.SourceTile;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinerRemove.class */
public final class MinerRemove extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Miner f2013a;

    /* renamed from: b, reason: collision with root package name */
    private final SourceTile f2014b;

    public MinerRemove(Miner miner, SourceTile sourceTile) {
        Preconditions.checkNotNull(miner, "miner can not be null");
        Preconditions.checkNotNull(sourceTile, "oldTile can not be null");
        this.f2013a = miner;
        this.f2014b = sourceTile;
    }

    public final Miner getMiner() {
        return this.f2013a;
    }

    public final SourceTile getOldTile() {
        return this.f2014b;
    }
}
