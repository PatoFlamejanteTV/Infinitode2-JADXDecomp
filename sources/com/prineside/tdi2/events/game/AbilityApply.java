package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Ability;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/AbilityApply.class */
public final class AbilityApply extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Ability f1969a;

    /* renamed from: b, reason: collision with root package name */
    private int f1970b;
    private int c;

    public AbilityApply(Ability ability, int i, int i2) {
        Preconditions.checkNotNull(ability);
        this.f1969a = ability;
        setX(i);
        setY(i2);
    }

    public final Ability getAbility() {
        return this.f1969a;
    }

    public final int getX() {
        return this.f1970b;
    }

    public final void setX(int i) {
        this.f1970b = i;
    }

    public final int getY() {
        return this.c;
    }

    public final void setY(int i) {
        this.c = i;
    }
}
