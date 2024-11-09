package com.prineside.tdi2.events.game;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.MapElementPos;
import com.prineside.tdi2.events.CancellableStoppableEvent;
import com.prineside.tdi2.utils.REGS;

@REGS(classOnly = true)
/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/game/MapElementSelect.class */
public final class MapElementSelect extends CancellableStoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    @Null
    private final MapElementPos f2003a;

    /* renamed from: b, reason: collision with root package name */
    @Null
    private final MapElementPos f2004b;

    public MapElementSelect(@Null MapElementPos mapElementPos, @Null MapElementPos mapElementPos2) {
        this.f2003a = mapElementPos;
        this.f2004b = mapElementPos2;
    }

    @Null
    public final MapElementPos getPrevious() {
        return this.f2003a;
    }

    @Null
    public final MapElementPos getCurrent() {
        return this.f2004b;
    }
}
