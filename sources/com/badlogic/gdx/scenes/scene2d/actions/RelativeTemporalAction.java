package com.badlogic.gdx.scenes.scene2d.actions;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/RelativeTemporalAction.class */
public abstract class RelativeTemporalAction extends TemporalAction {
    private float lastPercent;

    protected abstract void updateRelative(float f);

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.lastPercent = 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        updateRelative(f - this.lastPercent);
        this.lastPercent = f;
    }
}
