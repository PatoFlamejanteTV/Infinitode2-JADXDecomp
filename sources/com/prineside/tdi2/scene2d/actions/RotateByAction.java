package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/RotateByAction.class */
public class RotateByAction extends RelativeTemporalAction {
    private float c;

    @Override // com.prineside.tdi2.scene2d.actions.RelativeTemporalAction
    protected final void b(float f) {
        this.f2637b.rotateBy(this.c * f);
    }

    public float getAmount() {
        return this.c;
    }

    public void setAmount(float f) {
        this.c = f;
    }
}
