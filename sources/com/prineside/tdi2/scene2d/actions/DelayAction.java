package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/DelayAction.class */
public class DelayAction extends DelegateAction {
    private float d;
    private float e;

    public DelayAction() {
    }

    public DelayAction(float f) {
        this.d = f;
    }

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction
    protected final boolean a(float f) {
        if (this.e < this.d) {
            this.e += f;
            if (this.e < this.d) {
                return false;
            }
            f = this.e - this.d;
        }
        if (this.c == null) {
            return true;
        }
        return this.c.act(f);
    }

    public void finish() {
        this.e = this.d;
    }

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction, com.prineside.tdi2.scene2d.Action
    public void restart() {
        super.restart();
        this.e = 0.0f;
    }

    public float getTime() {
        return this.e;
    }

    public void setTime(float f) {
        this.e = f;
    }

    public float getDuration() {
        return this.d;
    }

    public void setDuration(float f) {
        this.d = f;
    }
}
