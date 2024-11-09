package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/TemporalAction.class */
public abstract class TemporalAction extends Action {
    private float duration;
    private float time;

    @Null
    private Interpolation interpolation;
    private boolean reverse;
    private boolean began;
    private boolean complete;

    protected abstract void update(float f);

    public TemporalAction() {
    }

    public TemporalAction(float f) {
        this.duration = f;
    }

    public TemporalAction(float f, @Null Interpolation interpolation) {
        this.duration = f;
        this.interpolation = interpolation;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        if (this.complete) {
            return true;
        }
        Pool pool = getPool();
        setPool(null);
        try {
            if (!this.began) {
                begin();
                this.began = true;
            }
            this.time += f;
            this.complete = this.time >= this.duration;
            float f2 = this.complete ? 1.0f : this.time / this.duration;
            if (this.interpolation != null) {
                f2 = this.interpolation.apply(f2);
            }
            update(this.reverse ? 1.0f - f2 : f2);
            if (this.complete) {
                end();
            }
            return this.complete;
        } finally {
            setPool(pool);
        }
    }

    protected void begin() {
    }

    protected void end() {
    }

    public void finish() {
        this.time = this.duration;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.time = 0.0f;
        this.began = false;
        this.complete = false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.reverse = false;
        this.interpolation = null;
    }

    public float getTime() {
        return this.time;
    }

    public void setTime(float f) {
        this.time = f;
    }

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float f) {
        this.duration = f;
    }

    @Null
    public Interpolation getInterpolation() {
        return this.interpolation;
    }

    public void setInterpolation(@Null Interpolation interpolation) {
        this.interpolation = interpolation;
    }

    public boolean isReverse() {
        return this.reverse;
    }

    public void setReverse(boolean z) {
        this.reverse = z;
    }

    public boolean isComplete() {
        return this.complete;
    }
}
