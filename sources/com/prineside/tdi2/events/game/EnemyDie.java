package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/EnemyDie.class */
public final class EnemyDie extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final DamageRecord f1985a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f1986b;

    public EnemyDie(DamageRecord damageRecord) {
        Preconditions.checkNotNull(damageRecord);
        this.f1985a = damageRecord;
    }

    public final DamageRecord getLastDamage() {
        return this.f1985a;
    }

    public final boolean isCancelled() {
        return this.f1986b;
    }

    public final void setCancelled(boolean z) {
        this.f1986b = z;
    }
}
