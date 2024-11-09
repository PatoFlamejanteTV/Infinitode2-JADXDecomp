package com.prineside.tdi2.events.mapEditor;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.events.StoppableEvent;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/mapEditor/InventoryItemRemove.class */
public final class InventoryItemRemove extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Item f2075a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2076b;
    private int c;

    public InventoryItemRemove(Item item, int i, int i2) {
        Preconditions.checkNotNull(item, "Item can not be null");
        Preconditions.checkArgument(i2 > 0, "Available count must be > 0, %s given", i2);
        this.f2075a = item;
        this.f2076b = i2;
        setCount(i);
    }

    public final Item getItem() {
        return this.f2075a;
    }

    public final int getCount() {
        return this.c;
    }

    public final void setCount(int i) {
        Preconditions.checkArgument(i > 0 && i <= this.f2076b, "Count must be between 1 and %s, %s given", this.f2076b, i);
        this.c = i;
    }

    public final int getAvailableCount() {
        return this.f2076b;
    }
}
