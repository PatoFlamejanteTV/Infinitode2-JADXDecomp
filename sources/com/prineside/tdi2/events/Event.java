package com.prineside.tdi2.events;

import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/Event.class */
public interface Event {
    boolean isStopped();

    void stop();
}
