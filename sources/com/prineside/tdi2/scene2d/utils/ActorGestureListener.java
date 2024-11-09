package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Event;
import com.prineside.tdi2.scene2d.EventListener;
import com.prineside.tdi2.scene2d.InputEvent;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/ActorGestureListener.class */
public class ActorGestureListener implements EventListener {

    /* renamed from: a, reason: collision with root package name */
    static final Vector2 f2701a = new Vector2();

    /* renamed from: b, reason: collision with root package name */
    static final Vector2 f2702b = new Vector2();
    private final GestureDetector e;
    InputEvent c;
    Actor d;
    private Actor f;

    public ActorGestureListener() {
        this(20.0f, 0.4f, 1.1f, 2.1474836E9f);
    }

    public ActorGestureListener(float f, float f2, float f3, float f4) {
        this.e = new GestureDetector(f, f2, f3, f4, new GestureDetector.GestureAdapter() { // from class: com.prineside.tdi2.scene2d.utils.ActorGestureListener.1

            /* renamed from: a, reason: collision with root package name */
            private final Vector2 f2703a = new Vector2();

            /* renamed from: b, reason: collision with root package name */
            private final Vector2 f2704b = new Vector2();
            private final Vector2 c = new Vector2();
            private final Vector2 d = new Vector2();

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean tap(float f5, float f6, int i, int i2) {
                ActorGestureListener.this.d.stageToLocalCoordinates(ActorGestureListener.f2701a.set(f5, f6));
                ActorGestureListener.this.tap(ActorGestureListener.this.c, ActorGestureListener.f2701a.x, ActorGestureListener.f2701a.y, i, i2);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean longPress(float f5, float f6) {
                ActorGestureListener.this.d.stageToLocalCoordinates(ActorGestureListener.f2701a.set(f5, f6));
                return ActorGestureListener.this.longPress(ActorGestureListener.this.d, ActorGestureListener.f2701a.x, ActorGestureListener.f2701a.y);
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean fling(float f5, float f6, int i) {
                a(ActorGestureListener.f2701a.set(f5, f6));
                ActorGestureListener.this.fling(ActorGestureListener.this.c, ActorGestureListener.f2701a.x, ActorGestureListener.f2701a.y, i);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean pan(float f5, float f6, float f7, float f8) {
                a(ActorGestureListener.f2701a.set(f7, f8));
                float f9 = ActorGestureListener.f2701a.x;
                float f10 = ActorGestureListener.f2701a.y;
                ActorGestureListener.this.d.stageToLocalCoordinates(ActorGestureListener.f2701a.set(f5, f6));
                ActorGestureListener.this.pan(ActorGestureListener.this.c, ActorGestureListener.f2701a.x, ActorGestureListener.f2701a.y, f9, f10);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean panStop(float f5, float f6, int i, int i2) {
                ActorGestureListener.this.d.stageToLocalCoordinates(ActorGestureListener.f2701a.set(f5, f6));
                ActorGestureListener.this.panStop(ActorGestureListener.this.c, ActorGestureListener.f2701a.x, ActorGestureListener.f2701a.y, i, i2);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean zoom(float f5, float f6) {
                ActorGestureListener.this.zoom(ActorGestureListener.this.c, f5, f6);
                return true;
            }

            @Override // com.badlogic.gdx.input.GestureDetector.GestureAdapter, com.badlogic.gdx.input.GestureDetector.GestureListener
            public boolean pinch(Vector2 vector2, Vector2 vector22, Vector2 vector23, Vector2 vector24) {
                ActorGestureListener.this.d.stageToLocalCoordinates(this.f2703a.set(vector2));
                ActorGestureListener.this.d.stageToLocalCoordinates(this.f2704b.set(vector22));
                ActorGestureListener.this.d.stageToLocalCoordinates(this.c.set(vector23));
                ActorGestureListener.this.d.stageToLocalCoordinates(this.d.set(vector24));
                ActorGestureListener.this.pinch(ActorGestureListener.this.c, this.f2703a, this.f2704b, this.c, this.d);
                return true;
            }

            private void a(Vector2 vector2) {
                ActorGestureListener.this.d.stageToLocalCoordinates(vector2);
                vector2.sub(ActorGestureListener.this.d.stageToLocalCoordinates(ActorGestureListener.f2702b.set(0.0f, 0.0f)));
            }
        });
    }

    @Override // com.prineside.tdi2.scene2d.EventListener
    public boolean handle(Event event) {
        if (!(event instanceof InputEvent)) {
            return false;
        }
        InputEvent inputEvent = (InputEvent) event;
        switch (inputEvent.getType()) {
            case touchDown:
                this.d = inputEvent.getListenerActor();
                this.f = inputEvent.getTarget();
                this.e.touchDown(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer(), inputEvent.getButton());
                this.d.stageToLocalCoordinates(f2701a.set(inputEvent.getStageX(), inputEvent.getStageY()));
                touchDown(inputEvent, f2701a.x, f2701a.y, inputEvent.getPointer(), inputEvent.getButton());
                if (inputEvent.getTouchFocus()) {
                    inputEvent.getStage().addTouchFocus(this, inputEvent.getListenerActor(), inputEvent.getTarget(), inputEvent.getPointer(), inputEvent.getButton());
                    return true;
                }
                return true;
            case touchUp:
                boolean isTouchFocusCancel = inputEvent.isTouchFocusCancel();
                if (isTouchFocusCancel) {
                    this.e.reset();
                } else {
                    this.c = inputEvent;
                    this.d = inputEvent.getListenerActor();
                    this.e.touchUp(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer(), inputEvent.getButton());
                    this.d.stageToLocalCoordinates(f2701a.set(inputEvent.getStageX(), inputEvent.getStageY()));
                    touchUp(inputEvent, f2701a.x, f2701a.y, inputEvent.getPointer(), inputEvent.getButton());
                }
                this.c = null;
                this.d = null;
                this.f = null;
                return !isTouchFocusCancel;
            case touchDragged:
                this.c = inputEvent;
                this.d = inputEvent.getListenerActor();
                this.e.touchDragged(inputEvent.getStageX(), inputEvent.getStageY(), inputEvent.getPointer());
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
        return this.e;
    }

    @Null
    public Actor getTouchDownTarget() {
        return this.f;
    }
}
