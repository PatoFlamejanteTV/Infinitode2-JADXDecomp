package com.prineside.tdi2.events.game;

import com.google.common.base.Preconditions;
import com.prineside.tdi2.Wave;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/WaveComplete.class */
public final class WaveComplete extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Wave f2064a;

    public WaveComplete(Wave wave) {
        Preconditions.checkNotNull(wave);
        this.f2064a = wave;
    }

    public final Wave getWave() {
        return this.f2064a;
    }
}
