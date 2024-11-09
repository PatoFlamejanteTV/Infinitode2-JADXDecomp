package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/SizeToAction.class */
public class SizeToAction extends TemporalAction {
    private float c;
    private float d;
    private float e;
    private float f;

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        this.c = this.f2637b.getWidth();
        this.d = this.f2637b.getHeight();
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
        this.f2637b.setSize(f2, f3);
    }

    public void setSize(float f, float f2) {
        this.e = f;
        this.f = f2;
    }

    public float getWidth() {
        return this.e;
    }

    public void setWidth(float f) {
        this.e = f;
    }

    public float getHeight() {
        return this.f;
    }

    public void setHeight(float f) {
        this.f = f;
    }
}
