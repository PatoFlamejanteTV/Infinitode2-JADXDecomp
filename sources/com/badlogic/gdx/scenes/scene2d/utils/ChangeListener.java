package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/ChangeListener.class */
public abstract class ChangeListener implements EventListener {

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/ChangeListener$ChangeEvent.class */
    public static class ChangeEvent extends Event {
    }

    public abstract void changed(ChangeEvent changeEvent, Actor actor);

    @Override // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof ChangeEvent)) {
            return false;
        }
        changed((ChangeEvent) event, event.getTarget());
        return false;
    }
}
