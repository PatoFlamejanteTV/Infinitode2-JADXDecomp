package com.prineside.tdi2.ui.events;

import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Event;
import com.prineside.tdi2.scene2d.EventListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/events/MoveToFrontListener.class */
public abstract class MoveToFrontListener implements EventListener {

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/events/MoveToFrontListener$MoveToFrontEvent.class */
    public static class MoveToFrontEvent extends Event {
    }

    public abstract void actorMovedToFront(MoveToFrontEvent moveToFrontEvent, Actor actor, boolean z);

    @Override // com.prineside.tdi2.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof MoveToFrontEvent)) {
            return false;
        }
        actorMovedToFront((MoveToFrontEvent) event, event.getTarget(), event.getTarget() == event.getListenerActor());
        return false;
    }
}
