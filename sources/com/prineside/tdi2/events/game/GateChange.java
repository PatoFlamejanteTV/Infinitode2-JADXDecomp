package com.prineside.tdi2.events.game;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.Gate;
import com.prineside.tdi2.events.StoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/GateChange.class */
public final class GateChange extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final int f1995a;

    /* renamed from: b, reason: collision with root package name */
    private final int f1996b;
    private final boolean c;

    @Null
    private final Gate d;

    @Null
    private final Gate e;

    public GateChange(int i, int i2, boolean z, Gate gate, Gate gate2) {
        this.f1995a = i;
        this.f1996b = i2;
        this.c = z;
        this.d = gate;
        this.e = gate2;
    }

    public final int getX() {
        return this.f1995a;
    }

    public final int getY() {
        return this.f1996b;
    }

    public final boolean isLeftSide() {
        return this.c;
    }

    @Null
    public final Gate getOldGate() {
        return this.d;
    }

    @Null
    public final Gate getNewGate() {
        return this.e;
    }
}
