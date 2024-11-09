package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinerUpgrade.class */
public final class MinerUpgrade extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Miner f2019a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2020b;

    public MinerUpgrade(Miner miner, int i) {
        Preconditions.checkNotNull(miner, "miner can not be null");
        Preconditions.checkArgument(i >= 0, "invalid price: %s", i);
        this.f2019a = miner;
        this.f2020b = i;
    }

    public final Miner getMiner() {
        return this.f2019a;
    }

    public final int getUpgradePrice() {
        return this.f2020b;
    }
}
