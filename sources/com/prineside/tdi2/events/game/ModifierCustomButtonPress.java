package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Modifier;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/ModifierCustomButtonPress.class */
public final class ModifierCustomButtonPress extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Modifier f2023a;

    public ModifierCustomButtonPress(Modifier modifier) {
        Preconditions.checkNotNull(modifier);
        this.f2023a = modifier;
    }

    public final Modifier getModifier() {
        return this.f2023a;
    }
}
