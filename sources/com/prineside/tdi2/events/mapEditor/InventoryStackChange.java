package com.prineside.tdi2.events.mapEditor;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.events.StoppableEvent;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/mapEditor/InventoryStackChange.class */
public final class InventoryStackChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final ItemStack f2077a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2078b;

    public InventoryStackChange(ItemStack itemStack, int i) {
        Preconditions.checkNotNull(itemStack, "itemStack can not be null");
        Preconditions.checkArgument(i != 0, "delta can not be 0");
        this.f2077a = itemStack;
        this.f2078b = i;
    }

    public final ItemStack getItemStack() {
        return this.f2077a;
    }

    public final int getDelta() {
        return this.f2078b;
    }
}
