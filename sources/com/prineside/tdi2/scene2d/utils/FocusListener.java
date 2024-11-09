package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Event;
import com.prineside.tdi2.scene2d.EventListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/FocusListener.class */
public abstract class FocusListener implements EventListener {
    @Override // com.prineside.tdi2.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof FocusEvent)) {
            return false;
        }
        FocusEvent focusEvent = (FocusEvent) event;
        switch (focusEvent.getType()) {
            case keyboard:
                keyboardFocusChanged(focusEvent, event.getTarget(), focusEvent.isFocused());
                return false;
            case scroll:
                scrollFocusChanged(focusEvent, event.getTarget(), focusEvent.isFocused());
                return false;
            default:
                return false;
        }
    }

    public void keyboardFocusChanged(FocusEvent focusEvent, Actor actor, boolean z) {
    }

    public void scrollFocusChanged(FocusEvent focusEvent, Actor actor, boolean z) {
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/FocusListener$FocusEvent.class */
    public static class FocusEvent extends Event {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2727a;

        /* renamed from: b, reason: collision with root package name */
        private Type f2728b;
        private Actor c;

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/FocusListener$FocusEvent$Type.class */
        public enum Type {
            keyboard,
            scroll
        }

        @Override // com.prineside.tdi2.scene2d.Event, com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            super.reset();
            this.c = null;
        }

        public boolean isFocused() {
            return this.f2727a;
        }

        public void setFocused(boolean z) {
            this.f2727a = z;
        }

        public Type getType() {
            return this.f2728b;
        }

        public void setType(Type type) {
            this.f2728b = type;
        }

        @Null
        public Actor getRelatedActor() {
            return this.c;
        }

        public void setRelatedActor(@Null Actor actor) {
            this.c = actor;
        }
    }
}
