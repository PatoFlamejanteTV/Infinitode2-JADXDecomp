package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/TimeScaleAction.class */
public class TimeScaleAction extends DelegateAction {
    private float d;

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction
    protected final boolean a(float f) {
        if (this.c == null) {
            return true;
        }
        return this.c.act(f * this.d);
    }

    public float getScale() {
        return this.d;
    }

    public void setScale(float f) {
        this.d = f;
    }
}
