package com.prineside.tdi2.scene2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/InputEvent.class */
public class InputEvent extends Event {

    /* renamed from: a, reason: collision with root package name */
    private Type f2642a;

    /* renamed from: b, reason: collision with root package name */
    private float f2643b;
    private float c;
    private float d;
    private float e;
    private int f;
    private int g;
    private int h;
    private char i;

    @Null
    private Actor j;
    private boolean k = true;

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/InputEvent$Type.class */
    public enum Type {
        touchDown,
        touchUp,
        touchDragged,
        mouseMoved,
        enter,
        exit,
        scrolled,
        keyDown,
        keyUp,
        keyTyped
    }

    @Override // com.prineside.tdi2.scene2d.Event, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.j = null;
        this.g = -1;
    }

    public float getStageX() {
        return this.f2643b;
    }

    public void setStageX(float f) {
        this.f2643b = f;
    }

    public float getStageY() {
        return this.c;
    }

    public void setStageY(float f) {
        this.c = f;
    }

    public Type getType() {
        return this.f2642a;
    }

    public void setType(Type type) {
        this.f2642a = type;
    }

    public int getPointer() {
        return this.f;
    }

    public void setPointer(int i) {
        this.f = i;
    }

    public int getButton() {
        return this.g;
    }

    public void setButton(int i) {
        this.g = i;
    }

    public int getKeyCode() {
        return this.h;
    }

    public void setKeyCode(int i) {
        this.h = i;
    }

    public char getCharacter() {
        return this.i;
    }

    public void setCharacter(char c) {
        this.i = c;
    }

    public float getScrollAmountX() {
        return this.d;
    }

    public float getScrollAmountY() {
        return this.e;
    }

    public void setScrollAmountX(float f) {
        this.d = f;
    }

    public void setScrollAmountY(float f) {
        this.e = f;
    }

    @Null
    public Actor getRelatedActor() {
        return this.j;
    }

    public void setRelatedActor(@Null Actor actor) {
        this.j = actor;
    }

    public Vector2 toCoordinates(Actor actor, Vector2 vector2) {
        vector2.set(this.f2643b, this.c);
        actor.stageToLocalCoordinates(vector2);
        return vector2;
    }

    public boolean isTouchFocusCancel() {
        return this.f2643b == -2.1474836E9f || this.c == -2.1474836E9f;
    }

    public boolean getTouchFocus() {
        return this.k;
    }

    public void setTouchFocus(boolean z) {
        this.k = z;
    }

    public String toString() {
        return this.f2642a.toString();
    }
}
