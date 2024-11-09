package com.prineside.tdi2.events.game;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.systems.WaveSystem;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/WaveStatusChange.class */
public final class WaveStatusChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    @Null
    private final WaveSystem.Status f2066a;

    public WaveStatusChange(@Null WaveSystem.Status status) {
        this.f2066a = status;
    }

    @Null
    public final WaveSystem.Status getOldStatus() {
        return this.f2066a;
    }
}
