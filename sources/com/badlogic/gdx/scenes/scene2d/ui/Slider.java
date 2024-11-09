package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Slider.class */
public class Slider extends ProgressBar {
    int button;
    int draggingPointer;
    boolean mouseOver;
    private Interpolation visualInterpolationInverse;
    private float[] snapValues;
    private float threshold;

    public Slider(float f, float f2, float f3, boolean z, Skin skin) {
        this(f, f2, f3, z, (SliderStyle) skin.get("default-" + (z ? "vertical" : "horizontal"), SliderStyle.class));
    }

    public Slider(float f, float f2, float f3, boolean z, Skin skin, String str) {
        this(f, f2, f3, z, (SliderStyle) skin.get(str, SliderStyle.class));
    }

    public Slider(float f, float f2, float f3, boolean z, SliderStyle sliderStyle) {
        super(f, f2, f3, z, sliderStyle);
        this.button = -1;
        this.draggingPointer = -1;
        this.visualInterpolationInverse = Interpolation.linear;
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Slider.1
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f4, float f5, int i, int i2) {
                if (Slider.this.disabled) {
                    return false;
                }
                if ((Slider.this.button != -1 && Slider.this.button != i2) || Slider.this.draggingPointer != -1) {
                    return false;
                }
                Slider.this.draggingPointer = i;
                Slider.this.calculatePositionAndValue(f4, f5);
                return true;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f4, float f5, int i, int i2) {
                if (i != Slider.this.draggingPointer) {
                    return;
                }
                Slider.this.draggingPointer = -1;
                if (inputEvent.isTouchFocusCancel() || !Slider.this.calculatePositionAndValue(f4, f5)) {
                    ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
                    Slider.this.fire(changeEvent);
                    Pools.free(changeEvent);
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f4, float f5, int i) {
                Slider.this.calculatePositionAndValue(f4, f5);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void enter(InputEvent inputEvent, float f4, float f5, int i, @Null Actor actor) {
                if (i == -1) {
                    Slider.this.mouseOver = true;
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f4, float f5, int i, @Null Actor actor) {
                if (i == -1) {
                    Slider.this.mouseOver = false;
                }
            }
        });
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    public SliderStyle getStyle() {
        return (SliderStyle) super.getStyle();
    }

    public boolean isOver() {
        return this.mouseOver;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    @Null
    protected Drawable getBackgroundDrawable() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || sliderStyle.disabledBackground == null) ? (!isDragging() || sliderStyle.backgroundDown == null) ? (!this.mouseOver || sliderStyle.backgroundOver == null) ? sliderStyle.background : sliderStyle.backgroundOver : sliderStyle.backgroundDown : sliderStyle.disabledBackground;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    @Null
    protected Drawable getKnobDrawable() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || sliderStyle.disabledKnob == null) ? (!isDragging() || sliderStyle.knobDown == null) ? (!this.mouseOver || sliderStyle.knobOver == null) ? sliderStyle.knob : sliderStyle.knobOver : sliderStyle.knobDown : sliderStyle.disabledKnob;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getKnobBeforeDrawable() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || sliderStyle.disabledKnobBefore == null) ? (!isDragging() || sliderStyle.knobBeforeDown == null) ? (!this.mouseOver || sliderStyle.knobBeforeOver == null) ? sliderStyle.knobBefore : sliderStyle.knobBeforeOver : sliderStyle.knobBeforeDown : sliderStyle.disabledKnobBefore;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
    protected Drawable getKnobAfterDrawable() {
        SliderStyle sliderStyle = (SliderStyle) super.getStyle();
        return (!this.disabled || sliderStyle.disabledKnobAfter == null) ? (!isDragging() || sliderStyle.knobAfterDown == null) ? (!this.mouseOver || sliderStyle.knobAfterOver == null) ? sliderStyle.knobAfter : sliderStyle.knobAfterOver : sliderStyle.knobAfterDown : sliderStyle.disabledKnobAfter;
    }

    boolean calculatePositionAndValue(float f, float f2) {
        float apply;
        Drawable drawable = getStyle().knob;
        Drawable backgroundDrawable = getBackgroundDrawable();
        float f3 = this.position;
        float minValue = getMinValue();
        float maxValue = getMaxValue();
        if (this.vertical) {
            float height = (getHeight() - backgroundDrawable.getTopHeight()) - backgroundDrawable.getBottomHeight();
            float minHeight = drawable == null ? 0.0f : drawable.getMinHeight();
            this.position = (f2 - backgroundDrawable.getBottomHeight()) - (minHeight * 0.5f);
            apply = minValue + ((maxValue - minValue) * this.visualInterpolationInverse.apply(this.position / (height - minHeight)));
            this.position = Math.max(Math.min(0.0f, backgroundDrawable.getBottomHeight()), this.position);
            this.position = Math.min(height - minHeight, this.position);
        } else {
            float width = (getWidth() - backgroundDrawable.getLeftWidth()) - backgroundDrawable.getRightWidth();
            float minWidth = drawable == null ? 0.0f : drawable.getMinWidth();
            this.position = (f - backgroundDrawable.getLeftWidth()) - (minWidth * 0.5f);
            apply = minValue + ((maxValue - minValue) * this.visualInterpolationInverse.apply(this.position / (width - minWidth)));
            this.position = Math.max(Math.min(0.0f, backgroundDrawable.getLeftWidth()), this.position);
            this.position = Math.min(width - minWidth, this.position);
        }
        float f4 = apply;
        if (!Gdx.input.isKeyPressed(59) && !Gdx.input.isKeyPressed(60)) {
            apply = snap(apply);
        }
        boolean value = setValue(apply);
        if (apply == f4) {
            this.position = f3;
        }
        return value;
    }

    protected float snap(float f) {
        if (this.snapValues == null || this.snapValues.length == 0) {
            return f;
        }
        float f2 = -1.0f;
        float f3 = 0.0f;
        for (int i = 0; i < this.snapValues.length; i++) {
            float f4 = this.snapValues[i];
            float abs = Math.abs(f - f4);
            if (abs <= this.threshold && (f2 == -1.0f || abs < f2)) {
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
        this.snapValues = fArr;
        this.threshold = f;
    }

    @Deprecated
    public void setSnapToValues(@Null float[] fArr, float f) {
        setSnapToValues(f, fArr);
    }

    @Null
    public float[] getSnapToValues() {
        return this.snapValues;
    }

    public float getSnapToValuesThreshold() {
        return this.threshold;
    }

    public boolean isDragging() {
        return this.draggingPointer != -1;
    }

    public void setButton(int i) {
        this.button = i;
    }

    public void setVisualInterpolationInverse(Interpolation interpolation) {
        this.visualInterpolationInverse = interpolation;
    }

    public void setVisualPercent(float f) {
        setValue(this.min + ((this.max - this.min) * this.visualInterpolationInverse.apply(f)));
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Slider$SliderStyle.class */
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
