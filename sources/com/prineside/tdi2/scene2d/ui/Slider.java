package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.ui.ProgressBar;
import com.prineside.tdi2.scene2d.utils.ChangeListener;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Slider.class */
public class Slider extends ProgressBar {
    int o;
    int p;
    boolean q;
    private Interpolation r;
    private float[] s;
    private float t;

    public Slider(float f, float f2, float f3, boolean z, SliderStyle sliderStyle) {
        super(f, f2, f3, z, sliderStyle);
        this.o = -1;
        this.p = -1;
        this.r = Interpolation.linear;
        addListener(new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.Slider.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f4, float f5, int i, int i2) {
                if (Slider.this.n) {
                    return false;
                }
                if ((Slider.this.o != -1 && Slider.this.o != i2) || Slider.this.p != -1) {
                    return false;
                }
                Slider.this.p = i;
                Slider.this.a(f4, f5);
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f4, float f5, int i, int i2) {
                if (i != Slider.this.p) {
                    return;
                }
                Slider.this.p = -1;
                if (inputEvent.isTouchFocusCancel() || !Slider.this.a(f4, f5)) {
                    ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
                    Slider.this.fire(changeEvent);
                    Pools.free(changeEvent);
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f4, float f5, int i) {
                Slider.this.a(f4, f5);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f4, float f5, int i, @Null Actor actor) {
                if (i == -1) {
                    Slider.this.q = true;
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f4, float f5, int i, @Null Actor actor) {
                if (i == -1) {
                    Slider.this.q = false;
                }
            }
        });
    }

    @Override // com.prineside.tdi2.scene2d.ui.ProgressBar
    public SliderStyle getStyle() {
        return (SliderStyle) super.getStyle();
    }

    public boolean isOver() {
        return this.q;
    }

    @Override // com.prineside.tdi2.scene2d.ui.ProgressBar
    @Null
    protected final Drawable b() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.n || sliderStyle.disabledBackground == null) ? (!isDragging() || sliderStyle.backgroundDown == null) ? (!this.q || sliderStyle.backgroundOver == null) ? sliderStyle.background : sliderStyle.backgroundOver : sliderStyle.backgroundDown : sliderStyle.disabledBackground;
    }

    @Override // com.prineside.tdi2.scene2d.ui.ProgressBar
    @Null
    protected final Drawable c() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.n || sliderStyle.disabledKnob == null) ? (!isDragging() || sliderStyle.knobDown == null) ? (!this.q || sliderStyle.knobOver == null) ? sliderStyle.knob : sliderStyle.knobOver : sliderStyle.knobDown : sliderStyle.disabledKnob;
    }

    @Override // com.prineside.tdi2.scene2d.ui.ProgressBar
    protected final Drawable d() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.n || sliderStyle.disabledKnobBefore == null) ? (!isDragging() || sliderStyle.knobBeforeDown == null) ? (!this.q || sliderStyle.knobBeforeOver == null) ? sliderStyle.knobBefore : sliderStyle.knobBeforeOver : sliderStyle.knobBeforeDown : sliderStyle.disabledKnobBefore;
    }

    @Override // com.prineside.tdi2.scene2d.ui.ProgressBar
    protected final Drawable e() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.n || sliderStyle.disabledKnobAfter == null) ? (!isDragging() || sliderStyle.knobAfterDown == null) ? (!this.q || sliderStyle.knobAfterOver == null) ? sliderStyle.knobAfter : sliderStyle.knobAfterOver : sliderStyle.knobAfterDown : sliderStyle.disabledKnobAfter;
    }

    final boolean a(float f, float f2) {
        float apply;
        Drawable drawable = getStyle().knob;
        Drawable b2 = b();
        float f3 = this.l;
        float minValue = getMinValue();
        float maxValue = getMaxValue();
        if (this.m) {
            float height = (getHeight() - b2.getTopHeight()) - b2.getBottomHeight();
            float minHeight = drawable == null ? 0.0f : drawable.getMinHeight();
            this.l = (f2 - b2.getBottomHeight()) - (minHeight * 0.5f);
            apply = minValue + ((maxValue - minValue) * this.r.apply(this.l / (height - minHeight)));
            this.l = Math.max(Math.min(0.0f, b2.getBottomHeight()), this.l);
            this.l = Math.min(height - minHeight, this.l);
        } else {
            float width = (getWidth() - b2.getLeftWidth()) - b2.getRightWidth();
            float minWidth = drawable == null ? 0.0f : drawable.getMinWidth();
            this.l = (f - b2.getLeftWidth()) - (minWidth * 0.5f);
            apply = minValue + ((maxValue - minValue) * this.r.apply(this.l / (width - minWidth)));
            this.l = Math.max(Math.min(0.0f, b2.getLeftWidth()), this.l);
            this.l = Math.min(width - minWidth, this.l);
        }
        float f4 = apply;
        if (!Gdx.input.isKeyPressed(59) && !Gdx.input.isKeyPressed(60)) {
            apply = a(apply);
        }
        boolean value = setValue(apply);
        if (apply == f4) {
            this.l = f3;
        }
        return value;
    }

    private float a(float f) {
        if (this.s == null || this.s.length == 0) {
            return f;
        }
        float f2 = -1.0f;
        float f3 = 0.0f;
        for (int i = 0; i < this.s.length; i++) {
            float f4 = this.s[i];
            float abs = Math.abs(f - f4);
            if (abs <= this.t && (f2 == -1.0f || abs < f2)) {
                f2 = abs;
                f3 = f4;
            }
        }
        return f2 == -1.0f ? f : f3;
    }

    public void setSnapToValues(float f, @Null float... fArr) {
        if (fArr != null && fArr.length == 0) {
            throw new IllegalArgumentException("values cannot be empty.");
        }
        this.s = fArr;
        this.t = f;
    }

    @Deprecated
    public void setSnapToValues(@Null float[] fArr, float f) {
        setSnapToValues(f, fArr);
    }

    @Null
    public float[] getSnapToValues() {
        return this.s;
    }

    public float getSnapToValuesThreshold() {
        return this.t;
    }

    public boolean isDragging() {
        return this.p != -1;
    }

    public void setButton(int i) {
        this.o = i;
    }

    public void setVisualInterpolationInverse(Interpolation interpolation) {
        this.r = interpolation;
    }

    public void setVisualPercent(float f) {
        setValue(this.j + ((this.k - this.j) * this.r.apply(f)));
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Slider$SliderStyle.class */
    public static class SliderStyle extends ProgressBar.ProgressBarStyle {

        @Null
        public Drawable backgroundOver;

        @Null
        public Drawable backgroundDown;

        @Null
        public Drawable knobOver;

        @Null
        public Drawable knobDown;

        @Null
        public Drawable knobBeforeOver;

        @Null
        public Drawable knobBeforeDown;

        @Null
        public Drawable knobAfterOver;

        @Null
        public Drawable knobAfterDown;

        public SliderStyle() {
        }

        public SliderStyle(@Null Drawable drawable, @Null Drawable drawable2) {
            super(drawable, drawable2);
        }

        public SliderStyle(SliderStyle sliderStyle) {
            super(sliderStyle);
            this.backgroundOver = sliderStyle.backgroundOver;
            this.backgroundDown = sliderStyle.backgroundDown;
            this.knobOver = sliderStyle.knobOver;
            this.knobDown = sliderStyle.knobDown;
            this.knobBeforeOver = sliderStyle.knobBeforeOver;
            this.knobBeforeDown = sliderStyle.knobBeforeDown;
            this.knobAfterOver = sliderStyle.knobAfterOver;
            this.knobAfterDown = sliderStyle.knobAfterDown;
        }
    }
}
