package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Enemy;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/EnemyReachTarget.class */
public final class EnemyReachTarget extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Enemy f1989a;

    /* renamed from: b, reason: collision with root package name */
    private float f1990b;
    private int c;
    private boolean d;

    public EnemyReachTarget(Enemy enemy, float f, int i) {
        setEnemy(enemy);
        setBaseDamage(f);
        setFactDamage(i);
    }

    public final Enemy getEnemy() {
        return this.f1989a;
    }

    public final void setEnemy(Enemy enemy) {
        Preconditions.checkNotNull(enemy, "Enemy can not be null");
        this.f1989a = enemy;
    }

    public final float getBaseDamage() {
        return this.f1990b;
    }

    public final EnemyReachTarget setBaseDamage(float f) {
        Preconditions.checkArgument(f >= 0.0f && PMath.isFinite(f), "Base damage must be >= 0, %s given", Float.valueOf(f));
        this.f1990b = f;
        return this;
    }

    public final int getFactDamage() {
        return this.c;
    }

    public final EnemyReachTarget setFactDamage(int i) {
        Preconditions.checkArgument(i >= 0, "Fact damage must be >= 0, %s given", i);
        this.c = i;
        return this;
    }

    public final boolean isCancelled() {
        return this.d;
    }

    public final void setCancelled(boolean z) {
        this.d = z;
    }
}
