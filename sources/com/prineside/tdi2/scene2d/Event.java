package com.prineside.tdi2.scene2d;

import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/Event.class */
public class Event implements Pool.Poolable {

    /* renamed from: a, reason: collision with root package name */
    private Stage f2640a;

    /* renamed from: b, reason: collision with root package name */
    private Actor f2641b;
    private Actor c;
    private boolean d;
    private boolean e = true;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;

    public void handle() {
        this.f = true;
    }

    public void cancel() {
        this.i = true;
        this.g = true;
        this.f = true;
    }

    public void stop() {
        this.g = true;
    }

    public void halt() {
        this.h = true;
        this.g = true;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.f2640a = null;
        this.f2641b = null;
        this.c = null;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
    }

    public Actor getTarget() {
        return this.f2641b;
    }

    public void setTarget(Actor actor) {
        this.f2641b = actor;
    }

    public Actor getListenerActor() {
        return this.c;
    }

    public void setListenerActor(Actor actor) {
        this.c = actor;
    }

    public boolean getBubbles() {
        return this.e;
    }

    public void setBubbles(boolean z) {
        this.e = z;
    }

    public boolean isHandled() {
        return this.f;
    }

    public boolean isStopped() {
        return this.g;
    }

    public boolean isHalted() {
        return this.h;
    }

    public boolean isCancelled() {
        return this.i;
    }

    public void setCapture(boolean z) {
        this.d = z;
    }

    public boolean isCapture() {
        return this.d;
    }

    public void setStage(Stage stage) {
        this.f2640a = stage;
    }

    public Stage getStage() {
        return this.f2640a;
    }
}
