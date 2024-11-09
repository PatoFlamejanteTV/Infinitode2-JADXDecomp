package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinerSell.class */
public final class MinerSell extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Miner f2017a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2018b;

    public MinerSell(Miner miner, int i) {
        Preconditions.checkNotNull(miner, "miner can not be null");
        Preconditions.checkArgument(i >= 0, "invalid returned coins: %s", i);
        this.f2017a = miner;
        this.f2018b = i;
    }

    public final Miner getMiner() {
        return this.f2017a;
    }

    public final int getReturnedCoins() {
        return this.f2018b;
    }
}
