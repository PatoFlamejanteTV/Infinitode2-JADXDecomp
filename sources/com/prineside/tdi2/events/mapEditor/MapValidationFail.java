package com.prineside.tdi2.events.mapEditor;

import com.prineside.tdi2.Map;
import com.prineside.tdi2.events.StoppableEvent;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/events/mapEditor/MapValidationFail.class */
public final class MapValidationFail extends StoppableEvent {

    /* renamed from: a, reason: collision with root package name */
    private final Map.InvalidMapException f2079a;

    public MapValidationFail(Map.InvalidMapException invalidMapException) {
        this.f2079a = invalidMapException;
    }

    public final Map.InvalidMapException getException() {
        return this.f2079a;
    }
}
