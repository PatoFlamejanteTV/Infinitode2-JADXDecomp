package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/ScaleToAction.class */
public class ScaleToAction extends TemporalAction {
    private float c;
    private float d;
    private float e;
    private float f;

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        this.c = this.f2637b.getScaleX();
        this.d = this.f2637b.getScaleY();
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a(float f) {
        float f2;
        float f3;
        if (f == 0.0f) {
            f2 = this.c;
            f3 = this.d;
        } else if (f == 1.0f) {
            f2 = this.e;
            f3 = this.f;
        } else {
            f2 = this.c + ((this.e - this.c) * f);
            f3 = this.d + ((this.f - this.d) * f);
        }
        this.f2637b.setScale(f2, f3);
    }

    public void setScale(float f, float f2) {
        this.e = f;
        this.f = f2;
    }

    public void setScale(float f) {
        this.e = f;
        this.f = f;
    }

    public float getX() {
        return this.e;
    }

    public void setX(float f) {
        this.e = f;
    }

    public float getY() {
        return this.f;
    }

    public void setY(float f) {
        this.f = f;
    }
}
