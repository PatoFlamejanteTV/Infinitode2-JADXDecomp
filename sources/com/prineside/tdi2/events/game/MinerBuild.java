package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinerBuild.class */
public final class MinerBuild extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Miner f2008a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2009b;

    public MinerBuild(Miner miner, int i) {
        Preconditions.checkNotNull(miner, "miner can not be null");
        Preconditions.checkArgument(i >= 0, "invalid price: %s", i);
        this.f2008a = miner;
        this.f2009b = i;
    }

    public final Miner getMiner() {
        return this.f2008a;
    }

    public final int getBuildPrice() {
        return this.f2009b;
    }
}
