package com.badlogic.gdx;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/InputProcessor.class */
public interface InputProcessor {
    boolean keyDown(int i);

    boolean keyUp(int i);

    boolean keyTyped(char c);

    boolean touchDown(int i, int i2, int i3, int i4);

    boolean touchUp(int i, int i2, int i3, int i4);

    boolean touchCancelled(int i, int i2, int i3, int i4);

    boolean touchDragged(int i, int i2, int i3);

    boolean mouseMoved(int i, int i2);

    boolean scrolled(float f, float f2);
}
