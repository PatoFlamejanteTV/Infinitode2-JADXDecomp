package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/ProgressBar.class */
public class ProgressBar extends Widget implements Disableable {
    private ProgressBarStyle style;
    float min;
    float max;
    float stepSize;
    private float value;
    private float animateFromValue;
    float position;
    final boolean vertical;
    private float animateDuration;
    private float animateTime;
    private Interpolation animateInterpolation;
    private Interpolation visualInterpolation;
    boolean disabled;
    private boolean round;
    private boolean programmaticChangeEvents;

    public ProgressBar(float f, float f2, float f3, boolean z, Skin skin) {
        this(f, f2, f3, z, (ProgressBarStyle) skin.get("default-" + (z ? "vertical" : "horizontal"), ProgressBarStyle.class));
    }

    public ProgressBar(float f, float f2, float f3, boolean z, Skin skin, String str) {
        this(f, f2, f3, z, (ProgressBarStyle) skin.get(str, ProgressBarStyle.class));
    }

    public ProgressBar(float f, float f2, float f3, boolean z, ProgressBarStyle progressBarStyle) {
        this.animateInterpolation = Interpolation.linear;
        this.visualInterpolation = Interpolation.linear;
        this.round = true;
        this.programmaticChangeEvents = true;
        if (f > f2) {
            throw new IllegalArgumentException("max must be > min. min,max: " + f + ", " + f2);
        }
        if (f3 <= 0.0f) {
            throw new IllegalArgumentException("stepSize must be > 0: " + f3);
        }
        setStyle(progressBarStyle);
        this.min = f;
        this.max = f2;
        this.stepSize = f3;
        this.vertical = z;
        this.value = f;
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle(ProgressBarStyle progressBarStyle) {
        if (progressBarStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = progressBarStyle;
        invalidateHierarchy();
    }

    public ProgressBarStyle getStyle() {
        return this.style;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        super.act(f);
        if (this.animateTime > 0.0f) {
            this.animateTime -= f;
            Stage stage = getStage();
            if (stage == null || !stage.getActionsRequestRendering()) {
                return;
            }
            Gdx.graphics.requestRendering();
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        Drawable drawable = this.style.knob;
        Drawable knobDrawable = getKnobDrawable();
        Drawable backgroundDrawable = getBackgroundDrawable();
        Drawable knobBeforeDrawable = getKnobBeforeDrawable();
        Drawable knobAfterDrawable = getKnobAfterDrawable();
        Color color = getColor();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        float minHeight = drawable == null ? 0.0f : drawable.getMinHeight();
        float minWidth = drawable == null ? 0.0f : drawable.getMinWidth();
        float visualPercent = getVisualPercent();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        if (this.vertical) {
            float f2 = 0.0f;
            if (backgroundDrawable != null) {
                drawRound(batch, backgroundDrawable, x + ((width - backgroundDrawable.getMinWidth()) * 0.5f), y, backgroundDrawable.getMinWidth(), height);
                float topHeight = backgroundDrawable.getTopHeight();
                f2 = backgroundDrawable.getBottomHeight();
                height -= topHeight + f2;
            }
            float f3 = height - minHeight;
            float clamp = MathUtils.clamp(f3 * visualPercent, 0.0f, f3);
            this.position = f2 + clamp;
            float f4 = minHeight * 0.5f;
            if (knobBeforeDrawable != null) {
                drawRound(batch, knobBeforeDrawable, x + ((width - knobBeforeDrawable.getMinWidth()) * 0.5f), y + f2, knobBeforeDrawable.getMinWidth(), clamp + f4);
            }
            if (knobAfterDrawable != null) {
                drawRound(batch, knobAfterDrawable, x + ((width - knobAfterDrawable.getMinWidth()) * 0.5f), y + this.position + f4, knobAfterDrawable.getMinWidth(), f3 - (this.round ? (float) Math.ceil(clamp - f4) : clamp - f4));
            }
            if (knobDrawable != null) {
                float minWidth2 = knobDrawable.getMinWidth();
                float minHeight2 = knobDrawable.getMinHeight();
                drawRound(batch, knobDrawable, x + ((width - minWidth2) * 0.5f), y + this.position + ((minHeight - minHeight2) * 0.5f), minWidth2, minHeight2);
                return;
            }
            return;
        }
        float f5 = 0.0f;
        if (backgroundDrawable != null) {
            drawRound(batch, backgroundDrawable, x, Math.round(y + ((height - backgroundDrawable.getMinHeight()) * 0.5f)), width, Math.round(backgroundDrawable.getMinHeight()));
            f5 = backgroundDrawable.getLeftWidth();
            width -= f5 + backgroundDrawable.getRightWidth();
        }
        float f6 = width - minWidth;
        float clamp2 = MathUtils.clamp(f6 * visualPercent, 0.0f, f6);
        this.position = f5 + clamp2;
        float f7 = minWidth * 0.5f;
        if (knobBeforeDrawable != null) {
            drawRound(batch, knobBeforeDrawable, x + f5, y + ((height - knobBeforeDrawable.getMinHeight()) * 0.5f), clamp2 + f7, knobBeforeDrawable.getMinHeight());
        }
        if (knobAfterDrawable != null) {
            drawRound(batch, knobAfterDrawable, x + this.position + f7, y + ((height - knobAfterDrawable.getMinHeight()) * 0.5f), f6 - (this.round ? (float) Math.ceil(clamp2 - f7) : clamp2 - f7), knobAfterDrawable.getMinHeight());
        }
        if (knobDrawable != null) {
            float minWidth3 = knobDrawable.getMinWidth();
            float minHeight3 = knobDrawable.getMinHeight();
            drawRound(batch, knobDrawable, x + this.position + ((minWidth - minWidth3) * 0.5f), y + ((height - minHeight3) * 0.5f), minWidth3, minHeight3);
        }
    }

    private void drawRound(Batch batch, Drawable drawable, float f, float f2, float f3, float f4) {
        if (this.round) {
            f = (float) Math.floor(f);
            f2 = (float) Math.floor(f2);
            f3 = (float) Math.ceil(f3);
            f4 = (float) Math.ceil(f4);
        }
        drawable.draw(batch, f, f2, f3, f4);
    }

    public float getValue() {
        return this.value;
    }

    public float getVisualValue() {
        return this.animateTime > 0.0f ? this.animateInterpolation.apply(this.animateFromValue, this.value, 1.0f - (this.animateTime / this.animateDuration)) : this.value;
    }

    public void updateVisualValue() {
        this.animateTime = 0.0f;
    }

    public float getPercent() {
        if (this.min == this.max) {
            return 0.0f;
        }
        return (this.value - this.min) / (this.max - this.min);
    }

    public float getVisualPercent() {
        if (this.min == this.max) {
            return 0.0f;
        }
        return this.visualInterpolation.apply((getVisualValue() - this.min) / (this.max - this.min));
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        return (!this.disabled || this.style.disabledBackground == null) ? this.style.background : this.style.disabledBackground;
    }

    @Null
    protected Drawable getKnobDrawable() {
        return (!this.disabled || this.style.disabledKnob == null) ? this.style.knob : this.style.disabledKnob;
    }

    protected Drawable getKnobBeforeDrawable() {
        return (!this.disabled || this.style.disabledKnobBefore == null) ? this.style.knobBefore : this.style.disabledKnobBefore;
    }

    protected Drawable getKnobAfterDrawable() {
        return (!this.disabled || this.style.disabledKnobAfter == null) ? this.style.knobAfter : this.style.disabledKnobAfter;
    }

    protected float getKnobPosition() {
        return this.position;
    }

    public boolean setValue(float f) {
        float clamp = clamp(round(f));
        float f2 = this.value;
        if (clamp == f2) {
            return false;
        }
        float visualValue = getVisualValue();
        this.value = clamp;
        if (this.programmaticChangeEvents) {
            ChangeListener.ChangeEvent changeEvent = (ChangeListener.ChangeEvent) Pools.obtain(ChangeListener.ChangeEvent.class);
            boolean fire = fire(changeEvent);
            Pools.free(changeEvent);
            if (fire) {
                this.value = f2;
                return false;
            }
        }
        if (this.animateDuration > 0.0f) {
            this.animateFromValue = visualValue;
            this.animateTime = this.animateDuration;
            return true;
        }
        return true;
    }

    protected float round(float f) {
        return Math.round(f / this.stepSize) * this.stepSize;
    }

    protected float clamp(float f) {
        return MathUtils.clamp(f, this.min, this.max);
    }

    public void setRange(float f, float f2) {
        if (f > f2) {
            throw new IllegalArgumentException("min must be <= max: " + f + " <= " + f2);
        }
        this.min = f;
        this.max = f2;
        if (this.value < f) {
            setValue(f);
        } else if (this.value > f2) {
            setValue(f2);
        }
    }

    public void setStepSize(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("steps must be > 0: " + f);
        }
        this.stepSize = f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.vertical) {
            Drawable drawable = this.style.knob;
            Drawable backgroundDrawable = getBackgroundDrawable();
            return Math.max(drawable == null ? 0.0f : drawable.getMinWidth(), backgroundDrawable == null ? 0.0f : backgroundDrawable.getMinWidth());
        }
        return 140.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.vertical) {
            return 140.0f;
        }
        Drawable drawable = this.style.knob;
        Drawable backgroundDrawable = getBackgroundDrawable();
        return Math.max(drawable == null ? 0.0f : drawable.getMinHeight(), backgroundDrawable == null ? 0.0f : backgroundDrawable.getMinHeight());
    }

    public float getMinValue() {
        return this.min;
    }

    public float getMaxValue() {
        return this.max;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public void setAnimateDuration(float f) {
        this.animateDuration = f;
    }

    public void setAnimateInterpolation(Interpolation interpolation) {
        if (interpolation == null) {
            throw new IllegalArgumentException("animateInterpolation cannot be null.");
        }
        this.animateInterpolation = interpolation;
    }

    public void setVisualInterpolation(Interpolation interpolation) {
        this.visualInterpolation = interpolation;
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.disabled = z;
    }

    public boolean isAnimating() {
        return this.animateTime > 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    public boolean isVertical() {
        return this.vertical;
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/ProgressBar$ProgressBarStyle.class */
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
