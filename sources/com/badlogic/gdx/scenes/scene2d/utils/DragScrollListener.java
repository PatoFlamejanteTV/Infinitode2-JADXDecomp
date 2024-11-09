package com.badlogic.gdx.scenes.scene2d.utils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.utils.Timer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/utils/DragScrollListener.class */
public class DragScrollListener extends DragListener {
    static final Vector2 tmpCoords = new Vector2();
    private ScrollPane scroll;
    private Timer.Task scrollUp;
    private Timer.Task scrollDown;
    long startTime;
    float padTop;
    float padBottom;
    Interpolation interpolation = Interpolation.exp5In;
    float minSpeed = 15.0f;
    float maxSpeed = 75.0f;
    float tickSecs = 0.05f;
    long rampTime = 1750;

    public DragScrollListener(final ScrollPane scrollPane) {
        this.scroll = scrollPane;
        this.scrollUp = new Timer.Task() { // from class: com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener.1
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                DragScrollListener.this.scroll(scrollPane.getScrollY() - DragScrollListener.this.getScrollPixels());
            }
        };
        this.scrollDown = new Timer.Task() { // from class: com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener.2
            @Override // com.badlogic.gdx.utils.Timer.Task, java.lang.Runnable
            public void run() {
                DragScrollListener.this.scroll(scrollPane.getScrollY() + DragScrollListener.this.getScrollPixels());
            }
        };
    }

    public void setup(float f, float f2, float f3, float f4) {
        this.minSpeed = f;
        this.maxSpeed = f2;
        this.tickSecs = f3;
        this.rampTime = f4 * 1000.0f;
    }

    float getScrollPixels() {
        return this.interpolation.apply(this.minSpeed, this.maxSpeed, Math.min(1.0f, ((float) (System.currentTimeMillis() - this.startTime)) / ((float) this.rampTime)));
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void drag(InputEvent inputEvent, float f, float f2, int i) {
        inputEvent.getListenerActor().localToActorCoordinates(this.scroll, tmpCoords.set(f, f2));
        if (isAbove(tmpCoords.y)) {
            this.scrollDown.cancel();
            if (!this.scrollUp.isScheduled()) {
                this.startTime = System.currentTimeMillis();
                Timer.schedule(this.scrollUp, this.tickSecs, this.tickSecs);
                return;
            }
            return;
        }
        if (isBelow(tmpCoords.y)) {
            this.scrollUp.cancel();
            if (!this.scrollDown.isScheduled()) {
                this.startTime = System.currentTimeMillis();
                Timer.schedule(this.scrollDown, this.tickSecs, this.tickSecs);
                return;
            }
            return;
        }
        this.scrollUp.cancel();
        this.scrollDown.cancel();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.DragListener
    public void dragStop(InputEvent inputEvent, float f, float f2, int i) {
        this.scrollUp.cancel();
        this.scrollDown.cancel();
    }

    protected boolean isAbove(float f) {
        return f >= this.scroll.getHeight() - this.padTop;
    }

    protected boolean isBelow(float f) {
        return f < this.padBottom;
    }

    protected void scroll(float f) {
        this.scroll.setScrollY(f);
    }

    public void setPadding(float f, float f2) {
        this.padTop = f;
        this.padBottom = f2;
    }
}
