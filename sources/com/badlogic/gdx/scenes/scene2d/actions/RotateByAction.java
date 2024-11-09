package com.badlogic.gdx.scenes.scene2d.actions;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/RotateByAction.class */
public class RotateByAction extends RelativeTemporalAction {
    private float amount;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.RelativeTemporalAction
    protected void updateRelative(float f) {
        this.target.rotateBy(this.amount * f);
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }
}
