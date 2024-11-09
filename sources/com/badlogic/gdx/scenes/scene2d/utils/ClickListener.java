package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.TimeUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/ClickListener.class */
public class ClickListener extends InputListener {
    public static float visualPressedDuration = 0.1f;
    private int button;
    private boolean pressed;
    private boolean over;
    private boolean cancelled;
    private long visualPressedTime;
    private int tapCount;
    private long lastTapTime;
    private float tapSquareSize = 14.0f;
    private float touchDownX = -1.0f;
    private float touchDownY = -1.0f;
    private int pressedPointer = -1;
    private int pressedButton = -1;
    private long tapCountInterval = 400000000;

    public ClickListener() {
    }

    public ClickListener(int i) {
        this.button = i;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (this.pressed) {
            return false;
        }
        if (i == 0 && this.button != -1 && i2 != this.button) {
            return false;
        }
        this.pressed = true;
        this.pressedPointer = i;
        this.pressedButton = i2;
        this.touchDownX = f;
        this.touchDownY = f2;
        setVisualPressed(true);
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
        if (i != this.pressedPointer || this.cancelled) {
            return;
        }
        this.pressed = isOver(inputEvent.getListenerActor(), f, f2);
        if (!this.pressed) {
            invalidateTapSquare();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (i == this.pressedPointer) {
            if (!this.cancelled) {
                boolean isOver = isOver(inputEvent.getListenerActor(), f, f2);
                boolean z = isOver;
                if (isOver && i == 0 && this.button != -1 && i2 != this.button) {
                    z = false;
                }
                if (z) {
                    long nanoTime = TimeUtils.nanoTime();
                    if (nanoTime - this.lastTapTime > this.tapCountInterval) {
                        this.tapCount = 0;
                    }
                    this.tapCount++;
                    this.lastTapTime = nanoTime;
                    clicked(inputEvent, f, f2);
                }
            }
            this.pressed = false;
            this.pressedPointer = -1;
            this.pressedButton = -1;
            this.cancelled = false;
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (i != -1 || this.cancelled) {
            return;
        }
        this.over = true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.InputListener
    public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (i != -1 || this.cancelled) {
            return;
        }
        this.over = false;
    }

    public void cancel() {
        if (this.pressedPointer == -1) {
            return;
        }
        this.cancelled = true;
        this.pressed = false;
    }

    public void clicked(InputEvent inputEvent, float f, float f2) {
    }

    public boolean isOver(Actor actor, float f, float f2) {
        Actor hit = actor.hit(f, f2, true);
        if (hit == null || !hit.isDescendantOf(actor)) {
            return inTapSquare(f, f2);
        }
        return true;
    }

    public boolean inTapSquare(float f, float f2) {
        return !(this.touchDownX == -1.0f && this.touchDownY == -1.0f) && Math.abs(f - this.touchDownX) < this.tapSquareSize && Math.abs(f2 - this.touchDownY) < this.tapSquareSize;
    }

    public boolean inTapSquare() {
        return this.touchDownX != -1.0f;
    }

    public void invalidateTapSquare() {
        this.touchDownX = -1.0f;
        this.touchDownY = -1.0f;
    }

    public boolean isPressed() {
        return this.pressed;
    }

    public boolean isVisualPressed() {
        if (this.pressed) {
            return true;
        }
        if (this.visualPressedTime <= 0) {
            return false;
        }
        if (this.visualPressedTime > TimeUtils.millis()) {
            return true;
        }
        this.visualPressedTime = 0L;
        return false;
    }

    public void setVisualPressed(boolean z) {
        if (z) {
            this.visualPressedTime = TimeUtils.millis() + (visualPressedDuration * 1000.0f);
        } else {
            this.visualPressedTime = 0L;
        }
    }

    public boolean isOver() {
        return this.over || this.pressed;
    }

    public void setTapSquareSize(float f) {
        this.tapSquareSize = f;
    }

    public float getTapSquareSize() {
        return this.tapSquareSize;
    }

    public void setTapCountInterval(float f) {
        this.tapCountInterval = f * 1.0E9f;
    }

    public int getTapCount() {
        return this.tapCount;
    }

    public void setTapCount(int i) {
        this.tapCount = i;
    }

    public float getTouchDownX() {
        return this.touchDownX;
    }

    public float getTouchDownY() {
        return this.touchDownY;
    }

    public int getPressedButton() {
        return this.pressedButton;
    }

    public int getPressedPointer() {
        return this.pressedPointer;
    }

    public int getButton() {
        return this.button;
    }

    public void setButton(int i) {
        this.button = i;
    }
}
