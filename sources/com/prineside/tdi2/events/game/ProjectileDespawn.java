package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Projectile;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/ProjectileDespawn.class */
public final class ProjectileDespawn extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Projectile f2034a;

    public ProjectileDespawn(Projectile projectile) {
        Preconditions.checkNotNull(projectile);
        this.f2034a = projectile;
    }

    public final Projectile getProjectile() {
        return this.f2034a;
    }
}
