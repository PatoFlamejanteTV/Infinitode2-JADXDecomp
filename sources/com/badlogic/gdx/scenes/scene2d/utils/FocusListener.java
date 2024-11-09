package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/FocusListener.class */
public abstract class FocusListener implements EventListener {
    @Override // com.badlogic.gdx.scenes.scene2d.EventListener
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

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/FocusListener$FocusEvent.class */
    public static class FocusEvent extends Event {
        private boolean focused;
        private Type type;
        private Actor relatedActor;

        /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/FocusListener$FocusEvent$Type.class */
        public enum Type {
            keyboard,
            scroll
        }

        @Override // com.badlogic.gdx.scenes.scene2d.Event, com.badlogic.gdx.utils.Pool.Poolable
        public void reset() {
            super.reset();
            this.relatedActor = null;
        }

        public boolean isFocused() {
            return this.focused;
        }

        public void setFocused(boolean z) {
            this.focused = z;
        }

        public Type getType() {
            return this.type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        @Null
        public Actor getRelatedActor() {
            return this.relatedActor;
        }

        public void setRelatedActor(@Null Actor actor) {
            this.relatedActor = actor;
        }
    }
}
