package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/ScaleByAction.class */
public class ScaleByAction extends RelativeTemporalAction {
    private float c;
    private float d;

    @Override // com.prineside.tdi2.scene2d.actions.RelativeTemporalAction
    protected final void b(float f) {
        this.f2637b.scaleBy(this.c * f, this.d * f);
    }

    public void setAmount(float f, float f2) {
        this.c = f;
        this.d = f2;
    }

    public void setAmount(float f) {
        this.c = f;
        this.d = f;
    }

    public float getAmountX() {
        return this.c;
    }

    public void setAmountX(float f) {
        this.c = f;
    }

    public float getAmountY() {
        return this.d;
    }

    public void setAmountY(float f) {
        this.d = f;
    }
}
