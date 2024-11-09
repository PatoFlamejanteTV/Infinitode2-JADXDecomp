package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/ActorGestureListener.class */
public class ActorGestureListener implements EventListener {
    static final Vector2 tmpCoords = new Vector2();
    static final Vector2 tmpCoords2 = new Vector2();
    private final GestureDetector detector;
    InputEvent event;
    Actor actor;
    Actor touchDownTarget;

    public ActorGestureListener() {
        this(20.0f, 0.4f, 1.1f, 2.1474836E9f);
    }

    public ActorGestureListener(float f, float f2, float f3, float f4) {
        this.detector = new GestureDetector(f, f2, f3, f4, new GestureDetector.GestureAdapter() { // from class: com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener.1
            private final Vector2 initialPointer1 = new Vector2();
            private final Vector2 initialPointer2 = new Vector2();
            private final Vector2 pointer1 = new Vector2();
            private final Vector2 pointer2 = new Vector2();

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean tap(float f5, float f6, int i, int i2) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f5, f6));
                ActorGestureListener.this.tap(ActorGestureListener.this.event, ActorGestureListener.tmpCoords.x, ActorGestureListener.tmpCoords.y, i, i2);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean longPress(float f5, float f6) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f5, f6));
                return ActorGestureListener.this.longPress(ActorGestureListener.this.actor, ActorGestureListener.tmpCoords.x, ActorGestureListener.tmpCoords.y);
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean fling(float f5, float f6, int i) {
                stageToLocalAmount(ActorGestureListener.tmpCoords.set(f5, f6));
                ActorGestureListener.this.fling(ActorGestureListener.this.event, ActorGestureListener.tmpCoords.x, ActorGestureListener.tmpCoords.y, i);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean pan(float f5, float f6, float f7, float f8) {
                stageToLocalAmount(ActorGestureListener.tmpCoords.set(f7, f8));
                float f9 = ActorGestureListener.tmpCoords.x;
                float f10 = ActorGestureListener.tmpCoords.y;
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f5, f6));
                ActorGestureListener.this.pan(ActorGestureListener.this.event, ActorGestureListener.tmpCoords.x, ActorGestureListener.tmpCoords.y, f9, f10);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean panStop(float f5, float f6, int i, int i2) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords.set(f5, f6));
                ActorGestureListener.this.panStop(ActorGestureListener.this.event, ActorGestureListener.tmpCoords.x, ActorGestureListener.tmpCoords.y, i, i2);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean zoom(float f5, float f6) {
                ActorGestureListener.this.zoom(ActorGestureListener.this.event, f5, f6);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.initialPointer1.set(vector2));
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.initialPointer2.set(vector22));
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.pointer1.set(vector23));
                ActorGestureListener.this.actor.stageToLocalCoordinates(this.pointer2.set(vector24));
                ActorGestureListener.this.pinch(ActorGestureListener.this.event, this.initialPointer1, this.initialPointer2, this.pointer1, this.pointer2);
                return true;
            }

            private void stageToLocalAmount(Vector2 vector2) {
                ActorGestureListener.this.actor.stageToLocalCoordinates(vector2);
                vector2.sub(ActorGestureListener.this.actor.stageToLocalCoordinates(ActorGestureListener.tmpCoords2.set(0.0f, 0.0f)));
            }
        });
    }

    @Override // com.badlogic.gdx.scenes.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent = (InputEvent) event;
        switch (inputEvent.getType()) {
            case touchDown:
                this.actor = inputEvent.getListenerActor();
                this.touchDownTarget = inputEvent.getTarget();
                this.detector.touchDown(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer(), inputEvent.getButton());
                this.actor.stageToLocalCoordinates(tmpCoords.set(inputEvent.getStageX(), inputEvent.getStageY()));
                touchDown(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getPointer(), inputEvent.getButton());
                if (inputEvent.getTouchFocus()) {
                    inputEvent.getStage().addTouchFocus(this, inputEvent.getListenerActor(), inputEvent.getTarget(), inputEvent.getPointer(), inputEvent.getButton());
                    return true;
                }
                return true;
            case touchUp:
                boolean isTouchFocusCancel = inputEvent.isTouchFocusCancel();
                if (isTouchFocusCancel) {
                    this.detector.reset();
                } else {
                    this.event = inputEvent;
                    this.actor = inputEvent.getListenerActor();
                    this.detector.touchUp(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer(), inputEvent.getButton());
                    this.actor.stageToLocalCoordinates(tmpCoords.set(inputEvent.getStageX(), inputEvent.getStageY()));
                    touchUp(inputEvent, tmpCoords.x, tmpCoords.y, inputEvent.getPointer(), inputEvent.getButton());
                }
                this.event = null;
                this.actor = null;
                this.touchDownTarget = null;
                return !isTouchFocusCancel;
            case touchDragged:
                this.event = inputEvent;
                this.actor = inputEvent.getListenerActor();
                this.detector.touchDragged(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer());
                return true;
            default:
                return false;
        }
    }

    public void touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
    }

    public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
    }

    public void tap(InputEvent inputEvent, float f, float f2, int i, int i2) {
    }

    public boolean longPress(Actor actor, float f, float f2) {
        return false;
    }

    public void fling(InputEvent inputEvent, float f, float f2, int i) {
    }

    public void pan(InputEvent inputEvent, float f, float f2, float f3, float f4) {
    }

    public void panStop(InputEvent inputEvent, float f, float f2, int i, int i2) {
    }

    public void zoom(InputEvent inputEvent, float f, float f2) {
    }

    public void pinch(InputEvent inputEvent, Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
    }

    public GestureDetector getGestureDetector() {
        return this.detector;
    }

    @Null
    public Actor getTouchDownTarget() {
        return this.touchDownTarget;
    }
}
