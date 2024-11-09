package com.prineside.tdi2.events.mapEditor;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.events.StoppableEvent;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/mapEditor/InventoryItemAdd.class */
public final class InventoryItemAdd extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Item f2073a;

    /* renamed from: b, reason: collision with root package name */
    private int f2074b;
    private boolean c;

    public InventoryItemAdd(Item item, int i) {
        setItem(item);
        setCount(i);
    }

    public final Item getItem() {
        return this.f2073a;
    }

    public final void setItem(Item item) {
        Preconditions.checkNotNull(item, "Item can not be null");
        this.f2073a = item;
    }

    public final int getCount() {
        return this.f2074b;
    }

    public final void setCount(int i) {
        Preconditions.checkArgument(i > 0, "Count must be > 0, %s given", i);
        this.f2074b = i;
    }

    public final boolean isCancelled() {
        return this.c;
    }

    public final void setCancelled(boolean z) {
        this.c = z;
    }
}
