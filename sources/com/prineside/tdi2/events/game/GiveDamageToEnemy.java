package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.events.CancellableStoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/GiveDamageToEnemy.class */
public final class GiveDamageToEnemy extends CancellableStoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final DamageRecord f1997a;

    public GiveDamageToEnemy(DamageRecord damageRecord) {
        Preconditions.checkNotNull(damageRecord);
        this.f1997a = damageRecord;
    }

    public final DamageRecord getRecord() {
        return this.f1997a;
    }
}
