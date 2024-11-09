package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/InputEvent.class */
public class InputEvent extends Event {
    private Type type;
    private float stageX;
    private float stageY;
    private float scrollAmountX;
    private float scrollAmountY;
    private int pointer;
    private int button;
    private int keyCode;
    private char character;

    @Null
    private Actor relatedActor;
    private boolean touchFocus = true;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/InputEvent$Type.class */
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

    @Override // com.badlogic.gdx.scenes.scene2d.Event, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.relatedActor = null;
        this.button = -1;
    }

    public float getStageX() {
        return this.stageX;
    }

    public void setStageX(float f) {
        this.stageX = f;
    }

    public float getStageY() {
        return this.stageY;
    }

    public void setStageY(float f) {
        this.stageY = f;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPointer() {
        return this.pointer;
    }

    public void setPointer(int i) {
        this.pointer = i;
    }

    public int getButton() {
        return this.button;
    }

    public void setButton(int i) {
        this.button = i;
    }

    public int getKeyCode() {
        return this.keyCode;
    }

    public void setKeyCode(int i) {
        this.keyCode = i;
    }

    public char getCharacter() {
        return this.character;
    }

    public void setCharacter(char c) {
        this.character = c;
    }

    public float getScrollAmountX() {
        return this.scrollAmountX;
    }

    public float getScrollAmountY() {
        return this.scrollAmountY;
    }

    public void setScrollAmountX(float f) {
        this.scrollAmountX = f;
    }

    public void setScrollAmountY(float f) {
        this.scrollAmountY = f;
    }

    @Null
    public Actor getRelatedActor() {
        return this.relatedActor;
    }

    public void setRelatedActor(@Null Actor actor) {
        this.relatedActor = actor;
    }

    public Vector2 toCoordinates(Actor actor, Vector2 vector2) {
        vector2.set(this.stageX, this.stageY);
        actor.stageToLocalCoordinates(vector2);
        return vector2;
    }

    public boolean isTouchFocusCancel() {
        return this.stageX == -2.1474836E9f || this.stageY == -2.1474836E9f;
    }

    public boolean getTouchFocus() {
        return this.touchFocus;
    }

    public void setTouchFocus(boolean z) {
        this.touchFocus = z;
    }

    public String toString() {
        return this.type.toString();
    }
}
