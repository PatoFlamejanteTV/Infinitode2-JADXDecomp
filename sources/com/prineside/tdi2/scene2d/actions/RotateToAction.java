package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/RotateToAction.class */
public class RotateToAction extends TemporalAction {
    private float c;
    private float d;
    private boolean e;

    public RotateToAction() {
        this.e = false;
    }

    public RotateToAction(boolean z) {
        this.e = false;
        this.e = z;
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        this.c = this.f2637b.getRotation();
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a(float f) {
        float f2;
        if (f == 0.0f) {
            f2 = this.c;
        } else if (f == 1.0f) {
            f2 = this.d;
        } else if (this.e) {
            f2 = MathUtils.lerpAngleDeg(this.c, this.d, f);
        } else {
            f2 = this.c + ((this.d - this.c) * f);
        }
        this.f2637b.setRotation(f2);
    }

    public float getRotation() {
        return this.d;
    }

    public void setRotation(float f) {
        this.d = f;
    }

    public boolean isUseShortestDirection() {
        return this.e;
    }

    public void setUseShortestDirection(boolean z) {
        this.e = z;
    }
}
