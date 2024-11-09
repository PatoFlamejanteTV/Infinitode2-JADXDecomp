package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.Disableable;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ProgressBar.class */
public class ProgressBar extends Widget implements Disableable {
    private ProgressBarStyle o;
    float j;
    float k;
    private float p;
    private float q;
    private float r;
    float l;
    final boolean m;
    private float s;
    private float t;
    boolean n;
    private Interpolation u = Interpolation.linear;
    private Interpolation v = Interpolation.linear;
    private boolean w = true;
    private boolean x = true;

    public ProgressBar(float f, float f2, float f3, boolean z, ProgressBarStyle progressBarStyle) {
        if (f > f2) {
            throw new IllegalArgumentException("max must be > min. min,max: " + f + ", " + f2);
        }
        if (f3 <= 0.0f) {
            throw new IllegalArgumentException("stepSize must be > 0: " + f3);
        }
        setStyle(progressBarStyle);
        this.j = f;
        this.k = f2;
        this.p = f3;
        this.m = z;
        this.q = f;
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle(ProgressBarStyle progressBarStyle) {
        if (progressBarStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.o = progressBarStyle;
        invalidateHierarchy();
    }

    public ProgressBarStyle getStyle() {
        return this.o;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        super.act(f);
        if (this.t > 0.0f) {
            this.t -= f;
            Stage stage = getStage();
            if (stage == null || !stage.getActionsRequestRendering()) {
                return;
            }
            Gdx.graphics.requestRendering();
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        Drawable drawable = this.o.knob;
        Drawable c = c();
        Drawable b2 = b();
        Drawable d = d();
        Drawable e = e();
        Color color = getColor();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        float minHeight = drawable == null ? 0.0f : drawable.getMinHeight();
        float minWidth = drawable == null ? 0.0f : drawable.getMinWidth();
        float visualPercent = getVisualPercent();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        if (this.m) {
            float f2 = 0.0f;
            if (b2 != null) {
                a(batch, b2, x + ((width - b2.getMinWidth()) * 0.5f), y, b2.getMinWidth(), height);
                float topHeight = b2.getTopHeight();
                f2 = b2.getBottomHeight();
                height -= topHeight + f2;
            }
            float f3 = height - minHeight;
            float clamp = MathUtils.clamp(f3 * visualPercent, 0.0f, f3);
            this.l = f2 + clamp;
            float f4 = minHeight * 0.5f;
            if (d != null) {
                a(batch, d, x + ((width - d.getMinWidth()) * 0.5f), y + f2, d.getMinWidth(), clamp + f4);
            }
            if (e != null) {
                a(batch, e, x + ((width - e.getMinWidth()) * 0.5f), y + this.l + f4, e.getMinWidth(), f3 - (this.w ? (float) Math.ceil(clamp - f4) : clamp - f4));
            }
            if (c != null) {
                float minWidth2 = c.getMinWidth();
                float minHeight2 = c.getMinHeight();
                a(batch, c, x + ((width - minWidth2) * 0.5f), y + this.l + ((minHeight - minHeight2) * 0.5f), minWidth2, minHeight2);
                return;
            }
            return;
        }
        float f5 = 0.0f;
        if (b2 != null) {
            a(batch, b2, x, Math.round(y + ((height - b2.getMinHeight()) * 0.5f)), width, Math.round(b2.getMinHeight()));
            f5 = b2.getLeftWidth();
            width -= f5 + b2.getRightWidth();
        }
        float f6 = width - minWidth;
        float clamp2 = MathUtils.clamp(f6 * visualPercent, 0.0f, f6);
        this.l = f5 + clamp2;
        float f7 = minWidth * 0.5f;
        if (d != null) {
            a(batch, d, x + f5, y + ((height - d.getMinHeight()) * 0.5f), clamp2 + f7, d.getMinHeight());
        }
        if (e != null) {
            a(batch, e, x + this.l + f7, y + ((height - e.getMinHeight()) * 0.5f), f6 - (this.w ? (float) Math.ceil(clamp2 - f7) : clamp2 - f7), e.getMinHeight());
        }
        if (c != null) {
            float minWidth3 = c.getMinWidth();
            float minHeight3 = c.getMinHeight();
            a(batch, c, x + this.l + ((minWidth - minWidth3) * 0.5f), y + ((height - minHeight3) * 0.5f), minWidth3, minHeight3);
        }
    }

    private void a(Batch batch, Drawable drawable, float f, float f2, float f3, float f4) {
        if (this.w) {
            f = (float) Math.floor(f);
            f2 = (float) Math.floor(f2);
            f3 = (float) Math.ceil(f3);
            f4 = (float) Math.ceil(f4);
        }
        drawable.draw(batch, f, f2, f3, f4);
    }

    public float getValue() {
        return this.q;
    }

    public float getVisualValue() {
        return this.t > 0.0f ? this.u.apply(this.r, this.q, 1.0f - (this.t / this.s)) : this.q;
    }

    public void updateVisualValue() {
        this.t = 0.0f;
    }

    public float getPercent() {
        if (this.j == this.k) {
            return 0.0f;
        }
        return (this.q - this.j) / (this.k - this.j);
    }

    public float getVisualPercent() {
        if (this.j == this.k) {
            return 0.0f;
        }
        return this.v.apply((getVisualValue() - this.j) / (this.k - this.j));
    }

    @Null
    protected Drawable b() {
        return (!this.n || this.o.disabledBackground == null) ? this.o.background : this.o.disabledBackground;
    }

    @Null
    protected Drawable c() {
        return (!this.n || this.o.disabledKnob == null) ? this.o.knob : this.o.disabledKnob;
    }

    protected Drawable d() {
        return (!this.n || this.o.disabledKnobBefore == null) ? this.o.knobBefore : this.o.disabledKnobBefore;
    }

    protected Drawable e() {
        return (!this.n || this.o.disabledKnobAfter == null) ? this.o.knobAfter : this.o.disabledKnobAfter;
    }

    public boolean setValue(float f) {
        float b2 = b(a(f));
        float f2 = this.q;
        if (b2 == f2) {
            return false;
        }
        float visualValue = getVisualValue();
        this.q = b2;
        if (this.x) {
            ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
            boolean fire = fire(changeEvent);
            Pools.free(changeEvent);
            if (fire) {
                this.q = f2;
                return false;
            }
        }
        if (this.s > 0.0f) {
            this.r = visualValue;
            this.t = this.s;
            return true;
        }
        return true;
    }

    private float a(float f) {
        return Math.round(f / this.p) * this.p;
    }

    private float b(float f) {
        return MathUtils.clamp(f, this.j, this.k);
    }

    public void setRange(float f, float f2) {
        if (f > f2) {
            throw new IllegalArgumentException("min must be <= max: " + f + " <= " + f2);
        }
        this.j = f;
        this.k = f2;
        if (this.q < f) {
            setValue(f);
        } else if (this.q > f2) {
            setValue(f2);
        }
    }

    public void setStepSize(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("steps must be > 0: " + f);
        }
        this.p = f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.m) {
            Drawable drawable = this.o.knob;
            Drawable b2 = b();
            return Math.max(drawable == null ? 0.0f : drawable.getMinWidth(), b2 == null ? 0.0f : b2.getMinWidth());
        }
        return 140.0f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.m) {
            return 140.0f;
        }
        Drawable drawable = this.o.knob;
        Drawable b2 = b();
        return Math.max(drawable == null ? 0.0f : drawable.getMinHeight(), b2 == null ? 0.0f : b2.getMinHeight());
    }

    public float getMinValue() {
        return this.j;
    }

    public float getMaxValue() {
        return this.k;
    }

    public float getStepSize() {
        return this.p;
    }

    public void setAnimateDuration(float f) {
        this.s = f;
    }

    public void setAnimateInterpolation(Interpolation interpolation) {
        if (interpolation == null) {
            throw new IllegalArgumentException("animateInterpolation cannot be null.");
        }
        this.u = interpolation;
    }

    public void setVisualInterpolation(Interpolation interpolation) {
        this.v = interpolation;
    }

    public void setRound(boolean z) {
        this.w = z;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.n = z;
    }

    public boolean isAnimating() {
        return this.t > 0.0f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.n;
    }

    public boolean isVertical() {
        return this.m;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.x = z;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/ProgressBar$ProgressBarStyle.class */
    public static class ProgressBarStyle {

        @Null
        public Drawable background;

        @Null
        public Drawable disabledBackground;

        @Null
        public Drawable knob;

        @Null
        public Drawable disabledKnob;

        @Null
        public Drawable knobBefore;

        @Null
        public Drawable disabledKnobBefore;

        @Null
        public Drawable knobAfter;

        @Null
        public Drawable disabledKnobAfter;

        public ProgressBarStyle() {
        }

        public ProgressBarStyle(@Null Drawable drawable, @Null Drawable drawable2) {
            this.background = drawable;
            this.knob = drawable2;
        }

        public ProgressBarStyle(ProgressBarStyle progressBarStyle) {
            this.background = progressBarStyle.background;
            this.disabledBackground = progressBarStyle.disabledBackground;
            this.knob = progressBarStyle.knob;
            this.disabledKnob = progressBarStyle.disabledKnob;
            this.knobBefore = progressBarStyle.knobBefore;
            this.disabledKnobBefore = progressBarStyle.disabledKnobBefore;
            this.knobAfter = progressBarStyle.knobAfter;
            this.disabledKnobAfter = progressBarStyle.disabledKnobAfter;
        }
    }
}
