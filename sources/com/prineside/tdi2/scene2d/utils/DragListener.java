package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.math.Vector2;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/DragListener.class */
public class DragListener extends InputListener {
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private int m;
    private boolean n;

    /* renamed from: a, reason: collision with root package name */
    private float f2718a = 14.0f;

    /* renamed from: b, reason: collision with root package name */
    private float f2719b = -1.0f;
    private float c = -1.0f;
    private float d = -1.0f;
    private float e = -1.0f;
    private int l = -1;

    @Override // com.prineside.tdi2.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (this.l != -1) {
            return false;
        }
        if (i == 0 && this.m != -1 && i2 != this.m) {
            return false;
        }
        this.l = i;
        this.f2719b = f;
        this.c = f2;
        this.d = inputEvent.getStageX();
        this.e = inputEvent.getStageY();
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
        if (i != this.l) {
            return;
        }
        if (!this.n && (Math.abs(this.f2719b - f) > this.f2718a || Math.abs(this.c - f2) > this.f2718a)) {
            this.n = true;
            this.f = f;
            this.g = f2;
            dragStart(inputEvent, f, f2, i);
            this.j = f;
            this.k = f2;
        }
        if (this.n) {
            this.h = this.j;
            this.i = this.k;
            this.j = f;
            this.k = f2;
            drag(inputEvent, f, f2, i);
        }
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (i == this.l) {
            if (this.m == -1 || i2 == this.m) {
                if (this.n) {
                    dragStop(inputEvent, f, f2, i);
                }
                cancel();
            }
        }
    }

    public void dragStart(InputEvent inputEvent, float f, float f2, int i) {
    }

    public void drag(InputEvent inputEvent, float f, float f2, int i) {
    }

    public void dragStop(InputEvent inputEvent, float f, float f2, int i) {
    }

    public void cancel() {
        this.n = false;
        this.l = -1;
    }

    public boolean isDragging() {
        return this.n;
    }

    public void setTapSquareSize(float f) {
        this.f2718a = f;
    }

    public float getTapSquareSize() {
        return this.f2718a;
    }

    public float getTouchDownX() {
        return this.f2719b;
    }

    public float getTouchDownY() {
        return this.c;
    }

    public float getStageTouchDownX() {
        return this.d;
    }

    public float getStageTouchDownY() {
        return this.e;
    }

    public float getDragStartX() {
        return this.f;
    }

    public void setDragStartX(float f) {
        this.f = f;
    }

    public float getDragStartY() {
        return this.g;
    }

    public void setDragStartY(float f) {
        this.g = f;
    }

    public float getDragX() {
        return this.j;
    }

    public float getDragY() {
        return this.k;
    }

    public float getDragDistance() {
        return Vector2.len(this.j - this.f, this.k - this.g);
    }

    public float getDeltaX() {
        return this.j - this.h;
    }

    public float getDeltaY() {
        return this.k - this.i;
    }

    public int getButton() {
        return this.m;
    }

    public void setButton(int i) {
        this.m = i;
    }
}
