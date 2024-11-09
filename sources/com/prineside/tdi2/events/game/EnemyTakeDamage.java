package com.prineside.tdi2.events.game;

import com.prineside.tdi2.DamageRecord;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/EnemyTakeDamage.class */
public final class EnemyTakeDamage extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private DamageRecord f1992a;

    public final EnemyTakeDamage setup(DamageRecord damageRecord) {
        this.f1992a = damageRecord;
        return this;
    }

    public final DamageRecord getRecord() {
        return this.f1992a;
    }

    public final EnemyTakeDamage reset() {
        this.f1992a = null;
        return this;
    }
}
