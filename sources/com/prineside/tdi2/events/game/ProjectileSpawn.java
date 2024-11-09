package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/ProjectileSpawn.class */
public final class ProjectileSpawn extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Projectile f2035a;

    public ProjectileSpawn(Projectile projectile) {
        setProjectile(projectile);
    }

    public final Projectile getProjectile() {
        return this.f2035a;
    }

    public final ProjectileSpawn setProjectile(Projectile projectile) {
        Preconditions.checkNotNull(projectile);
        this.f2035a = projectile;
        return this;
    }
}
