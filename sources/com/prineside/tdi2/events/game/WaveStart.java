package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/WaveStart.class */
public final class WaveStart extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Wave f2065a;

    public WaveStart(Wave wave) {
        Preconditions.checkNotNull(wave, "wave can not be null");
        this.f2065a = wave;
    }

    public final Wave getWave() {
        return this.f2065a;
    }
}
