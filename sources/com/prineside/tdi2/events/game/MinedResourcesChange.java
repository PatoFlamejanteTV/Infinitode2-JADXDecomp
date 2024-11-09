package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinedResourcesChange.class */
public final class MinedResourcesChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private ResourceType f2006a;

    /* renamed from: b, reason: collision with root package name */
    private int f2007b;
    private boolean c;

    public MinedResourcesChange(ResourceType resourceType, int i, boolean z) {
        setType(resourceType);
        setOldValue(i);
        setGained(z);
    }

    public final ResourceType getType() {
        return this.f2006a;
    }

    public final MinedResourcesChange setType(ResourceType resourceType) {
        Preconditions.checkNotNull(resourceType);
        this.f2006a = resourceType;
        return this;
    }

    public final int getOldValue() {
        return this.f2007b;
    }

    public final MinedResourcesChange setOldValue(int i) {
        this.f2007b = i;
        return this;
    }

    public final boolean isGained() {
        return this.c;
    }

    public final MinedResourcesChange setGained(boolean z) {
        this.c = z;
        return this;
    }
}
