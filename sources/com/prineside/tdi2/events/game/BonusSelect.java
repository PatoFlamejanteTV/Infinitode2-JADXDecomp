package com.prineside.tdi2.events.game;

import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/BonusSelect.class */
public final class BonusSelect extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final int f1975a;

    public BonusSelect(int i) {
        this.f1975a = i;
    }

    public final int getStageNumber() {
        return this.f1975a;
    }
}
