package com.badlogic.gdx.scenes.scene2d.actions;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/MoveToAction.class */
public class MoveToAction extends TemporalAction {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int alignment = 12;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startX = this.target.getX(this.alignment);
        this.startY = this.target.getY(this.alignment);
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
        this.target.setPosition(f2, f3, this.alignment);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction, com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.alignment = 12;
    }

    public void setStartPosition(float f, float f2) {
        this.startX = f;
        this.startY = f2;
    }

    public void setPosition(float f, float f2) {
        this.endX = f;
        this.endY = f2;
    }

    public void setPosition(float f, float f2, int i) {
        this.endX = f;
        this.endY = f2;
        this.alignment = i;
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

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public int getAlignment() {
        return this.alignment;
    }

    public void setAlignment(int i) {
        this.alignment = i;
    }
}
