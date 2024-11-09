package com.badlogic.gdx.scenes.scene2d.actions;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/ScaleToAction.class */
public class ScaleToAction extends TemporalAction {
    private float startX;
    private float startY;
    private float endX;
    private float endY;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startX = this.target.getScaleX();
        this.startY = this.target.getScaleY();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        float f2;
        float f3;
        if (f == 0.0f) {
            f2 = this.startX;
            f3 = this.startY;
        } else if (f == 1.0f) {
            f2 = this.endX;
            f3 = this.endY;
        } else {
            f2 = this.startX + ((this.endX - this.startX) * f);
            f3 = this.startY + ((this.endY - this.startY) * f);
        }
        this.target.setScale(f2, f3);
    }

    public void setScale(float f, float f2) {
        this.endX = f;
        this.endY = f2;
    }

    public void setScale(float f) {
        this.endX = f;
        this.endY = f;
    }

    public float getX() {
        return this.endX;
    }

    public void setX(float f) {
        this.endX = f;
    }

    public float getY() {
        return this.endY;
    }

    public void setY(float f) {
        this.endY = f;
    }
}
