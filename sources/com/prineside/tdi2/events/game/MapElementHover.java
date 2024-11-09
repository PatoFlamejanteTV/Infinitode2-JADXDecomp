package com.prineside.tdi2.events.game;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.MapElementPos;
import com.prineside.tdi2.events.CancellableStoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MapElementHover.class */
public final class MapElementHover extends CancellableStoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    @Null
    private final MapElementPos f2001a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private final MapElementPos f2002b;

    public MapElementHover(@Null MapElementPos mapElementPos, @Null MapElementPos mapElementPos2) {
        this.f2001a = mapElementPos;
        this.f2002b = mapElementPos2;
    }

    @Null
    public final MapElementPos getPrevious() {
        return this.f2001a;
    }

    @Null
    public final MapElementPos getCurrent() {
        return this.f2002b;
    }
}
