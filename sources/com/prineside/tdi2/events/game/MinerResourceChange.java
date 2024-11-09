package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinerResourceChange.class */
public final class MinerResourceChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Miner f2015a;

    /* renamed from: b, reason: collision with root package name */
    private ResourceType f2016b;
    private int c;
    private boolean d;
    private boolean e;

    public MinerResourceChange(Miner miner, ResourceType resourceType, int i, boolean z) {
        setMiner(miner);
        setResourceType(resourceType);
        setDelta(i);
        this.d = z;
        this.e = false;
    }

    public final boolean isCancelled() {
        return this.e;
    }

    public final void setCancelled(boolean z) {
        this.e = z;
    }

    public final Miner getMiner() {
        return this.f2015a;
    }

    public final MinerResourceChange setMiner(Miner miner) {
        Preconditions.checkNotNull(miner, "miner can not be null");
        this.f2015a = miner;
        return this;
    }

    public final ResourceType getResourceType() {
        return this.f2016b;
    }

    public final MinerResourceChange setResourceType(ResourceType resourceType) {
        Preconditions.checkNotNull(resourceType, "resourceType can not be null");
        this.f2016b = resourceType;
        return this;
    }

    public final int getDelta() {
        return this.c;
    }

    public final MinerResourceChange setDelta(int i) {
        Preconditions.checkArgument(i != 0, "delta can not be 0");
        this.c = i;
        return this;
    }

    public final boolean isMined() {
        return this.d;
    }

    public final MinerResourceChange setMined(boolean z) {
        this.d = z;
        return this;
    }
}
