package com.badlogic.gdx.scenes.scene2d.actions;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/SizeToAction.class */
public class SizeToAction extends TemporalAction {
    private float startWidth;
    private float startHeight;
    private float endWidth;
    private float endHeight;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void begin() {
        this.startWidth = this.target.getWidth();
        this.startHeight = this.target.getHeight();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.TemporalAction
    protected void update(float f) {
        float f2;
        float f3;
        if (f == 0.0f) {
            f2 = this.startWidth;
            f3 = this.startHeight;
        } else if (f == 1.0f) {
            f2 = this.endWidth;
            f3 = this.endHeight;
        } else {
            f2 = this.startWidth + ((this.endWidth - this.startWidth) * f);
            f3 = this.startHeight + ((this.endHeight - this.startHeight) * f);
        }
        this.target.setSize(f2, f3);
    }

    public void setSize(float f, float f2) {
        this.endWidth = f;
        this.endHeight = f2;
    }

    public float getWidth() {
        return this.endWidth;
    }

    public void setWidth(float f) {
        this.endWidth = f;
    }

    public float getHeight() {
        return this.endHeight;
    }

    public void setHeight(float f) {
        this.endHeight = f;
    }
}
