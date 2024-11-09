package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/Event.class */
public class Event implements Pool.Poolable {
    private Stage stage;
    private Actor targetActor;
    private Actor listenerActor;
    private boolean capture;
    private boolean bubbles = true;
    private boolean handled;
    private boolean stopped;
    private boolean cancelled;

    public void handle() {
        this.handled = true;
    }

    public void cancel() {
        this.cancelled = true;
        this.stopped = true;
        this.handled = true;
    }

    public void stop() {
        this.stopped = true;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.stage = null;
        this.targetActor = null;
        this.listenerActor = null;
        this.capture = false;
        this.bubbles = true;
        this.handled = false;
        this.stopped = false;
        this.cancelled = false;
    }

    public Actor getTarget() {
        return this.targetActor;
    }

    public void setTarget(Actor actor) {
        this.targetActor = actor;
    }

    public Actor getListenerActor() {
        return this.listenerActor;
    }

    public void setListenerActor(Actor actor) {
        this.listenerActor = actor;
    }

    public boolean getBubbles() {
        return this.bubbles;
    }

    public void setBubbles(boolean z) {
        this.bubbles = z;
    }

    public boolean isHandled() {
        return this.handled;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCapture(boolean z) {
        this.capture = z;
    }

    public boolean isCapture() {
        return this.capture;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }
}
