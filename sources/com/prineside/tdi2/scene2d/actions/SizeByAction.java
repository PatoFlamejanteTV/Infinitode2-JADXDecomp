package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/SizeByAction.class */
public class SizeByAction extends RelativeTemporalAction {
    private float c;
    private float d;

    @Override // com.prineside.tdi2.scene2d.actions.RelativeTemporalAction
    protected final void b(float f) {
        this.f2637b.sizeBy(this.c * f, this.d * f);
    }

    public void setAmount(float f, float f2) {
        this.c = f;
        this.d = f2;
    }

    public float getAmountWidth() {
        return this.c;
    }

    public void setAmountWidth(float f) {
        this.c = f;
    }

    public float getAmountHeight() {
        return this.d;
    }

    public void setAmountHeight(float f) {
        this.d = f;
    }
}
