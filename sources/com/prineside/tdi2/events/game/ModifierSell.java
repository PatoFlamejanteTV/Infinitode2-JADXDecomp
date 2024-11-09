package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/ModifierSell.class */
public final class ModifierSell extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Modifier f2025a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2026b;

    public ModifierSell(Modifier modifier, int i) {
        Preconditions.checkNotNull(modifier, "modifier can not be null");
        Preconditions.checkArgument(i >= 0, "invalid returnedCoins: %s", i);
        this.f2025a = modifier;
        this.f2026b = i;
    }

    public final Modifier getModifier() {
        return this.f2025a;
    }

    public final int getReturnedCoins() {
        return this.f2026b;
    }
}
