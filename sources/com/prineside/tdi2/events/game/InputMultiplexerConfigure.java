package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.InputMultiplexerExtended;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/InputMultiplexerConfigure.class */
public class InputMultiplexerConfigure extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final InputMultiplexerExtended f1998a;

    public InputMultiplexerConfigure(InputMultiplexerExtended inputMultiplexerExtended) {
        this.f1998a = inputMultiplexerExtended;
    }

    public InputMultiplexerExtended getMultiplexer() {
        return this.f1998a;
    }
}
