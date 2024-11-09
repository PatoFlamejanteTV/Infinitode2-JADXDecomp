package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Item;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/EnemyLootAdd.class */
public final class EnemyLootAdd extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private int f1987a;

    /* renamed from: b, reason: collision with root package name */
    private Item f1988b;

    public EnemyLootAdd(Item item, int i) {
        setItem(item);
        setCount(i);
    }

    public final int getCount() {
        return this.f1987a;
    }

    public final EnemyLootAdd setCount(int i) {
        Preconditions.checkArgument(i >= 0, "count can not be %s", i);
        this.f1987a = i;
        return this;
    }

    public final Item getItem() {
        return this.f1988b;
    }

    public final EnemyLootAdd setItem(Item item) {
        Preconditions.checkNotNull(item);
        this.f1988b = item;
        return this;
    }
}
