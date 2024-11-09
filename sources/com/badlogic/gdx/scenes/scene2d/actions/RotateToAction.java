package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.math.MathUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/RotateToAction.class */
public class RotateToAction extends TemporalAction {
    private float start;
    private float end;
    private boolean useShortestDirection;

    public RotateToAction() {
        this.useShortestDirection = false;
    }

    public RotateToAction(boolean z) {
        this.useShortestDirection = false;
        this.useShortestDirection = z;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.start = this.target.getRotation();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        float f2;
        if (f == 0.0f) {
            f2 = this.start;
        } else if (f == 1.0f) {
            f2 = this.end;
        } else if (this.useShortestDirection) {
            f2 = MathUtils.lerpAngleDeg(this.start, this.end, f);
        } else {
            f2 = this.start + ((this.end - this.start) * f);
        }
        this.target.setRotation(f2);
    }

    public float getRotation() {
        return this.end;
    }

    public void setRotation(float f) {
        this.end = f;
    }

    public boolean isUseShortestDirection() {
        return this.useShortestDirection;
    }

    public void setUseShortestDirection(boolean z) {
        this.useShortestDirection = z;
    }
}
