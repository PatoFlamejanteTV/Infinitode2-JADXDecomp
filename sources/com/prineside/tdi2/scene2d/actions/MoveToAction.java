package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/MoveToAction.class */
public class MoveToAction extends TemporalAction {
    private float c;
    private float d;
    private float e;
    private float f;
    private int g = 12;

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction
    protected final void a() {
        this.c = this.f2637b.getX(this.g);
        this.d = this.f2637b.getY(this.g);
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
        this.f2637b.setPosition(f2, f3, this.g);
    }

    @Override // com.prineside.tdi2.scene2d.actions.TemporalAction, com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.g = 12;
    }

    public void setStartPosition(float f, float f2) {
        this.c = f;
        this.d = f2;
    }

    public void setPosition(float f, float f2) {
        this.e = f;
        this.f = f2;
    }

    public void setPosition(float f, float f2, int i) {
        this.e = f;
        this.f = f2;
        this.g = i;
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

    public float getStartX() {
        return this.c;
    }

    public float getStartY() {
        return this.d;
    }

    public int getAlignment() {
        return this.g;
    }

    public void setAlignment(int i) {
        this.g = i;
    }
}
