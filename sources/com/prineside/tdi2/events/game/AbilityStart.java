package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/AbilityStart.class */
public final class AbilityStart extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Ability f1971a;

    public AbilityStart(Ability ability) {
        Preconditions.checkNotNull(ability);
        this.f1971a = ability;
    }

    public final Ability getAbility() {
        return this.f1971a;
    }
}
