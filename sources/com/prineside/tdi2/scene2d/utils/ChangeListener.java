package com.prineside.tdi2.scene2d.utils;

import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Event;
import com.prineside.tdi2.scene2d.EventListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/ChangeListener.class */
public abstract class ChangeListener implements EventListener {

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/ChangeListener$ChangeEvent.class */
    public static class ChangeEvent extends Event {
    }

    public abstract void changed(ChangeEvent changeEvent, Actor actor);

    @Override // com.prineside.tdi2.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof ChangeEvent)) {
            return false;
        }
        changed((ChangeEvent) event, event.getTarget());
        return false;
    }
}
