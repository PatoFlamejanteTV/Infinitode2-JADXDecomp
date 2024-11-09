package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Tower;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.PMath;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/TowerExperienceChange.class */
public final class TowerExperienceChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private Tower f2050a;

    /* renamed from: b, reason: collision with root package name */
    private final float f2051b;

    public TowerExperienceChange(Tower tower, float f) {
        Preconditions.checkNotNull(tower, "tower can not be null");
        Preconditions.checkArgument(PMath.isFinite(f), "invalid delta %s", Float.valueOf(f));
        this.f2050a = tower;
        this.f2051b = f;
    }

    public final Tower getTower() {
        return this.f2050a;
    }

    public final float getDelta() {
        return this.f2051b;
    }
}
