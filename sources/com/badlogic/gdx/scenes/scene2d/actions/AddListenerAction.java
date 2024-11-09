package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/AddListenerAction.class */
public class AddListenerAction extends Action {
    private EventListener listener;
    private boolean capture;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        if (this.capture) {
            this.target.addCaptureListener(this.listener);
            return true;
        }
        this.target.addListener(this.listener);
        return true;
    }

    public EventListener getListener() {
        return this.listener;
    }

    public void setListener(EventListener eventListener) {
        this.listener = eventListener;
    }

    public boolean getCapture() {
        return this.capture;
    }

    public void setCapture(boolean z) {
        this.capture = z;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.listener = null;
    }
}
