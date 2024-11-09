package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.ItemStack;
import com.prineside.tdi2.Miner;
import com.prineside.tdi2.enums.RarityType;
import com.prineside.tdi2.enums.ResourceType;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MinerMineItem.class */
public final class MinerMineItem extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Miner f2010a;

    /* renamed from: b, reason: collision with root package name */
    private ResourceType f2011b;
    private ItemStack c;
    private float d;
    private RarityType e;
    private float f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;

    public MinerMineItem(Miner miner, ResourceType resourceType, ItemStack itemStack, float f, RarityType rarityType, float f2) {
        this.g = true;
        this.h = true;
        this.i = true;
        setMiner(miner);
        setResourceType(resourceType);
        setItemStack(itemStack);
        setQuality(f);
        setRarity(rarityType);
        setRarityQuality(f2);
        setCountTowardsInventoryStatistics(this.g);
        setAddAndShowActualLoot(this.h);
        setAddToEmptyItemSlot(this.i);
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = false;
    }

    public final Miner getMiner() {
        return this.f2010a;
    }

    public final MinerMineItem setMiner(Miner miner) {
        Preconditions.checkNotNull(miner);
        this.f2010a = miner;
        return this;
    }

    public final ResourceType getResourceType() {
        return this.f2011b;
    }

    public final MinerMineItem setResourceType(ResourceType resourceType) {
        Preconditions.checkNotNull(resourceType);
        this.f2011b = resourceType;
        return this;
    }

    public final ItemStack getItemStack() {
        return this.c;
    }

    public final MinerMineItem setItemStack(ItemStack itemStack) {
        Preconditions.checkNotNull(itemStack);
        this.c = itemStack;
        return this;
    }

    public final float getQuality() {
        return this.d;
    }

    public final MinerMineItem setQuality(float f) {
        Preconditions.checkArgument(f >= 0.0f && f <= 10.0f && PMath.isFinite(f));
        this.d = f;
        return this;
    }

    public final RarityType getRarity() {
        return this.e;
    }

    public final MinerMineItem setRarity(RarityType rarityType) {
        Preconditions.checkNotNull(rarityType);
        this.e = rarityType;
        return this;
    }

    public final float getRarityQuality() {
        return this.f;
    }

    public final MinerMineItem setRarityQuality(float f) {
        Preconditions.checkArgument(f >= 0.0f && f <= 10.0f && PMath.isFinite(f));
        this.f = f;
        return this;
    }

    public final boolean isCountTowardsInventoryStatistics() {
        return this.g;
    }

    public final MinerMineItem setCountTowardsInventoryStatistics(boolean z) {
        this.g = z;
        return this;
    }

    public final boolean isAddAndShowActualLoot() {
        return this.h;
    }

    public final MinerMineItem setAddAndShowActualLoot(boolean z) {
        this.h = z;
        return this;
    }

    public final boolean isAddToEmptyItemSlot() {
        return this.i;
    }

    public final MinerMineItem setAddToEmptyItemSlot(boolean z) {
        this.i = z;
        return this;
    }

    public final boolean isCancelled() {
        return this.j;
    }

    public final MinerMineItem setCancelled(boolean z) {
        this.j = z;
        return this;
    }
}
