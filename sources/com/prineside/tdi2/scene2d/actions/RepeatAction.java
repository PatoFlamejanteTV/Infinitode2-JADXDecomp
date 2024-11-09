package com.prineside.tdi2.scene2d.actions;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/RepeatAction.class */
public class RepeatAction extends DelegateAction {
    public static final int FOREVER = -1;
    private int d;
    private int e;
    private boolean f;

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction
    protected final boolean a(float f) {
        if (this.e == this.d) {
            return true;
        }
        if (this.c.act(f)) {
            if (this.f) {
                return true;
            }
            if (this.d > 0) {
                this.e++;
            }
            if (this.e == this.d) {
                return true;
            }
            if (this.c != null) {
                this.c.restart();
                return false;
            }
            return false;
        }
        return false;
    }

    public void finish() {
        this.f = true;
    }

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction, com.prineside.tdi2.scene2d.Action
    public void restart() {
        super.restart();
        this.e = 0;
        this.f = false;
    }

    public void setCount(int i) {
        this.d = i;
    }

    public int getCount() {
        return this.d;
    }
}
