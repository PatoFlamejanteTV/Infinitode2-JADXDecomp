package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Event;
import com.prineside.tdi2.scene2d.EventListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/EventAction.class */
public abstract class EventAction<T extends Event> extends Action {
    final Class<? extends T> c;
    boolean d;
    boolean e;
    private final EventListener f = new EventListener() { // from class: com.prineside.tdi2.scene2d.actions.EventAction.1
        @Override // com.prineside.tdi2.scene2d.EventListener
        public boolean handle(Event event) {
            if (!EventAction.this.e || !ClassReflection.isInstance(EventAction.this.c, event)) {
                return false;
            }
            EventAction.this.d = EventAction.this.handle(event);
            return EventAction.this.d;
        }
    };

    public abstract boolean handle(T t);

    public EventAction(Class<? extends T> cls) {
        this.c = cls;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void restart() {
        this.d = false;
        this.e = false;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void setTarget(Actor actor) {
        if (this.f2637b != null) {
            this.f2637b.removeListener(this.f);
        }
        super.setTarget(actor);
        if (actor != null) {
            actor.addListener(this.f);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        this.e = true;
        return this.d;
    }

    public boolean isActive() {
        return this.e;
    }

    public void setActive(boolean z) {
        this.e = z;
    }
}
