package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Touchpad.class */
public class Touchpad extends Widget {
    private TouchpadStyle l;
    boolean j;
    private float m;
    boolean k = true;
    private final Circle n = new Circle(0.0f, 0.0f, 0.0f);
    private final Circle o = new Circle(0.0f, 0.0f, 0.0f);
    private final Circle p = new Circle(0.0f, 0.0f, 0.0f);
    private final Vector2 q = new Vector2();
    private final Vector2 r = new Vector2();

    public Touchpad(float f, TouchpadStyle touchpadStyle) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("deadzoneRadius must be > 0");
        }
        this.m = f;
        this.q.set(getWidth() / 2.0f, getHeight() / 2.0f);
        setStyle(touchpadStyle);
        setSize(getPrefWidth(), getPrefHeight());
        addListener(new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.Touchpad.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                if (Touchpad.this.j) {
                    return false;
                }
                Touchpad.this.j = true;
                Touchpad.this.a(f2, f3, false);
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f2, float f3, int i) {
                Touchpad.this.a(f2, f3, false);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f2, float f3, int i, int i2) {
                Touchpad.this.j = false;
                Touchpad.this.a(f2, f3, Touchpad.this.k);
            }
        });
    }

    final void a(float f, float f2, boolean z) {
        float f3 = this.q.x;
        float f4 = this.q.y;
        float f5 = this.r.x;
        float f6 = this.r.y;
        float f7 = this.n.x;
        float f8 = this.n.y;
        this.q.set(f7, f8);
        this.r.set(0.0f, 0.0f);
        if (!z && !this.p.contains(f, f2)) {
            this.r.set((f - f7) / this.n.radius, (f2 - f8) / this.n.radius);
            float len = this.r.len();
            if (len > 1.0f) {
                this.r.scl(1.0f / len);
            }
            if (this.n.contains(f, f2)) {
                this.q.set(f, f2);
            } else {
                this.q.set(this.r).nor().scl(this.n.radius).add(this.n.x, this.n.y);
            }
        }
        if (f5 != this.r.x || f6 != this.r.y) {
            ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
            if (fire(changeEvent)) {
                this.r.set(f5, f6);
                this.q.set(f3, f4);
            }
            Pools.free(changeEvent);
        }
    }

    public void setStyle(TouchpadStyle touchpadStyle) {
        if (touchpadStyle == null) {
            throw new IllegalArgumentException("style cannot be null");
        }
        this.l = touchpadStyle;
        invalidateHierarchy();
    }

    public TouchpadStyle getStyle() {
        return this.l;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public Actor hit(float f, float f2, boolean z) {
        if ((!z || getTouchable() == Touchable.enabled) && isVisible() && this.o.contains(f, f2)) {
            return this;
        }
        return null;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        float width = getWidth() / 2.0f;
        float height = getHeight() / 2.0f;
        float min = Math.min(width, height);
        this.o.set(width, height, min);
        if (this.l.knob != null) {
            min -= Math.max(this.l.knob.getMinWidth(), this.l.knob.getMinHeight()) / 2.0f;
        }
        this.n.set(width, height, min);
        this.p.set(width, height, this.m);
        this.q.set(width, height);
        this.r.set(0.0f, 0.0f);
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        Drawable drawable = this.l.background;
        if (drawable != null) {
            drawable.draw(batch, x, y, width, height);
        }
        Drawable drawable2 = this.l.knob;
        if (drawable2 != null) {
            drawable2.draw(batch, x + (this.q.x - (drawable2.getMinWidth() / 2.0f)), y + (this.q.y - (drawable2.getMinHeight() / 2.0f)), drawable2.getMinWidth(), drawable2.getMinHeight());
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.l.background != null) {
            return this.l.background.getMinWidth();
        }
        return 0.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.l.background != null) {
            return this.l.background.getMinHeight();
        }
        return 0.0f;
    }

    public boolean isTouched() {
        return this.j;
    }

    public boolean getResetOnTouchUp() {
        return this.k;
    }

    public void setResetOnTouchUp(boolean z) {
        this.k = z;
    }

    public void setDeadzone(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("deadzoneRadius must be > 0");
        }
        this.m = f;
        invalidate();
    }

    public float getKnobX() {
        return this.q.x;
    }

    public float getKnobY() {
        return this.q.y;
    }

    public float getKnobPercentX() {
        return this.r.x;
    }

    public float getKnobPercentY() {
        return this.r.y;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Touchpad$TouchpadStyle.class */
    public static class TouchpadStyle {

        @Null
        public Drawable background;

        @Null
        public Drawable knob;

        public TouchpadStyle() {
        }

        public TouchpadStyle(@Null Drawable drawable, @Null Drawable drawable2) {
            this.background = drawable;
            this.knob = drawable2;
        }

        public TouchpadStyle(TouchpadStyle touchpadStyle) {
            this.background = touchpadStyle.background;
            this.knob = touchpadStyle.knob;
        }
    }
}
