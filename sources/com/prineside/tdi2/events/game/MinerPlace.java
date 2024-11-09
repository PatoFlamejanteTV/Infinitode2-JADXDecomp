package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinerPlace.class */
public final class MinerPlace extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Miner f2012a;

    public MinerPlace(Miner miner) {
        Preconditions.checkNotNull(miner);
        this.f2012a = miner;
    }

    public final Miner getMiner() {
        return this.f2012a;
    }
}
