package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.IssuedItems;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/IssuedItemsAdd.class */
public final class IssuedItemsAdd extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private IssuedItems f1999a;

    /* renamed from: b, reason: collision with root package name */
    private ItemStack f2000b;
    private float c;
    private float d;
    private int e;

    public IssuedItemsAdd(IssuedItems issuedItems, ItemStack itemStack, float f, float f2, int i) {
        setIssuedItems(issuedItems);
        setItemStack(itemStack);
        setStageX(f);
        setStageY(f2);
        setFlyAlign(i);
    }

    public final IssuedItems getIssuedItems() {
        return this.f1999a;
    }

    public final IssuedItemsAdd setIssuedItems(IssuedItems issuedItems) {
        Preconditions.checkNotNull(issuedItems);
        this.f1999a = issuedItems;
        return this;
    }

    public final ItemStack getItemStack() {
        return this.f2000b;
    }

    public final IssuedItemsAdd setItemStack(ItemStack itemStack) {
        Preconditions.checkNotNull(itemStack);
        this.f2000b = itemStack;
        return this;
    }

    public final float getStageX() {
        return this.c;
    }

    public final IssuedItemsAdd setStageX(float f) {
        this.c = f;
        return this;
    }

    public final float getStageY() {
        return this.d;
    }

    public final IssuedItemsAdd setStageY(float f) {
        this.d = f;
        return this;
    }

    public final int getFlyAlign() {
        return this.e;
    }

    public final IssuedItemsAdd setFlyAlign(int i) {
        this.e = i;
        return this;
    }
}
