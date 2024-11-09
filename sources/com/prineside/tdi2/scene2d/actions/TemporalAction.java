package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.scene2d.Action;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/TemporalAction.class */
public abstract class TemporalAction extends Action {
    private float c;
    private float d;

    @Null
    private Interpolation e;
    private boolean f;
    private boolean g;
    private boolean h;

    protected abstract void a(float f);

    public TemporalAction() {
    }

    public TemporalAction(float f) {
        this.c = f;
    }

    public TemporalAction(float f, @Null Interpolation interpolation) {
        this.c = f;
        this.e = interpolation;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        if (this.h) {
            return true;
        }
        Pool pool = getPool();
        setPool(null);
        try {
            if (!this.g) {
                a();
                this.g = true;
            }
            this.d += f;
            this.h = this.d >= this.c;
            float f2 = this.h ? 1.0f : this.d / this.c;
            if (this.e != null) {
                f2 = this.e.apply(f2);
            }
            a(this.f ? 1.0f - f2 : f2);
            return this.h;
        } finally {
            setPool(pool);
        }
    }

    protected void a() {
    }

    public void finish() {
        this.d = this.c;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void restart() {
        this.d = 0.0f;
        this.g = false;
        this.h = false;
    }

    @Override // com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.f = false;
        this.e = null;
    }

    public float getTime() {
        return this.d;
    }

    public void setTime(float f) {
        this.d = f;
    }

    public float getDuration() {
        return this.c;
    }

    public void setDuration(float f) {
        this.c = f;
    }

    @Null
    public Interpolation getInterpolation() {
        return this.e;
    }

    public void setInterpolation(@Null Interpolation interpolation) {
        this.e = interpolation;
    }

    public boolean isReverse() {
        return this.f;
    }

    public void setReverse(boolean z) {
        this.f = z;
    }

    public boolean isComplete() {
        return this.h;
    }
}
