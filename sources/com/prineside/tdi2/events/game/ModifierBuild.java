package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/ModifierBuild.class */
public final class ModifierBuild extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Modifier f2021a;

    /* renamed from: b, reason: collision with root package name */
    private final int f2022b;

    public ModifierBuild(Modifier modifier, int i) {
        Preconditions.checkNotNull(modifier, "modifier can not be null");
        Preconditions.checkArgument(i >= 0, "invalid price: %s", i);
        this.f2021a = modifier;
        this.f2022b = i;
    }

    public final Modifier getModifier() {
        return this.f2021a;
    }

    public final int getPrice() {
        return this.f2022b;
    }
}
