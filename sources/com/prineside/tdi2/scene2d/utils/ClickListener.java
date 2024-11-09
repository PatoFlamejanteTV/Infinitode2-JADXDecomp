package com.prineside.tdi2.scene2d.utils;

import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.TimeUtils;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/utils/ClickListener.class */
public class ClickListener extends InputListener {
    public static float visualPressedDuration = 0.1f;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private long j;
    private int l;
    private long m;

    /* renamed from: a, reason: collision with root package name */
    private float f2708a = 14.0f;

    /* renamed from: b, reason: collision with root package name */
    private float f2709b = -1.0f;
    private float c = -1.0f;
    private int d = -1;
    private int e = -1;
    private long k = 400000000;

    public ClickListener() {
    }

    public ClickListener(int i) {
        this.f = i;
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (this.g) {
            return false;
        }
        if (i == 0 && this.f != -1 && i2 != this.f) {
            return false;
        }
        this.g = true;
        this.d = i;
        this.e = i2;
        this.f2709b = f;
        this.c = f2;
        setVisualPressed(true);
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
        if (i != this.d || this.i) {
            return;
        }
        this.g = isOver(inputEvent.getListenerActor(), f, f2);
        if (!this.g) {
            invalidateTapSquare();
        }
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
        if (i == this.d) {
            if (!this.i) {
                boolean isOver = isOver(inputEvent.getListenerActor(), f, f2);
                boolean z = isOver;
                if (isOver && i == 0 && this.f != -1 && i2 != this.f) {
                    z = false;
                }
                if (z) {
                    long nanoTime = TimeUtils.nanoTime();
                    if (nanoTime - this.m > this.k) {
                        this.l = 0;
                    }
                    this.l++;
                    this.m = nanoTime;
                    clicked(inputEvent, f, f2);
                }
            }
            this.g = false;
            this.d = -1;
            this.e = -1;
            this.i = false;
        }
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void enter(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (i != -1 || this.i) {
            return;
        }
        this.h = true;
    }

    @Override // com.prineside.tdi2.scene2d.InputListener
    public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
        if (i != -1 || this.i) {
            return;
        }
        this.h = false;
    }

    public void cancel() {
        if (this.d == -1) {
            return;
        }
        this.i = true;
        this.g = false;
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
        return !(this.f2709b == -1.0f && this.c == -1.0f) && Math.abs(f - this.f2709b) < this.f2708a && Math.abs(f2 - this.c) < this.f2708a;
    }

    public boolean inTapSquare() {
        return this.f2709b != -1.0f;
    }

    public void invalidateTapSquare() {
        this.f2709b = -1.0f;
        this.c = -1.0f;
    }

    public boolean isPressed() {
        return this.g;
    }

    public boolean isVisualPressed() {
        if (this.g) {
            return true;
        }
        if (this.j <= 0) {
            return false;
        }
        if (this.j > TimeUtils.millis()) {
            return true;
        }
        this.j = 0L;
        return false;
    }

    public void setVisualPressed(boolean z) {
        if (z) {
            this.j = TimeUtils.millis() + (visualPressedDuration * 1000.0f);
        } else {
            this.j = 0L;
        }
    }

    public boolean isOver() {
        return this.h || this.g;
    }

    public void setTapSquareSize(float f) {
        this.f2708a = f;
    }

    public float getTapSquareSize() {
        return this.f2708a;
    }

    public void setTapCountInterval(float f) {
        this.k = f * 1.0E9f;
    }

    public int getTapCount() {
        return this.l;
    }

    public void setTapCount(int i) {
        this.l = i;
    }

    public float getTouchDownX() {
        return this.f2709b;
    }

    public float getTouchDownY() {
        return this.c;
    }

    public int getPressedButton() {
        return this.e;
    }

    public int getPressedPointer() {
        return this.d;
    }

    public int getButton() {
        return this.f;
    }

    public void setButton(int i) {
        this.f = i;
    }
}
