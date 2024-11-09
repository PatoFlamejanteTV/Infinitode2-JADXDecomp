package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/FloatAction.class */
public class FloatAction extends TemporalAction {
    private float c;
    private float d;
    private float e;

    public FloatAction() {
        this.c = 0.0f;
        this.d = 1.0f;
    }

    public FloatAction(float f, float f2) {
        this.c = f;
        this.d = f2;
    }

    public FloatAction(float f, float f2, float f3) {
        super(f3);
        this.c = f;
        this.d = f2;
    }

    public FloatAction(float f, float f2, float f3, @Null Interpolation interpolation) {
        super(f3, interpolation);
        this.c = f;
        this.d = f2;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        this.e = this.c;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a(float f) {
        if (f == 0.0f) {
            this.e = this.c;
        } else if (f == 1.0f) {
            this.e = this.d;
        } else {
            this.e = this.c + ((this.d - this.c) * f);
        }
    }

    public float getValue() {
        return this.e;
    }

    public void setValue(float f) {
        this.e = f;
    }

    public float getStart() {
        return this.c;
    }

    public void setStart(float f) {
        this.c = f;
    }

    public float getEnd() {
        return this.d;
    }

    public void setEnd(float f) {
        this.d = f;
    }
}
