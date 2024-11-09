package com.prineside.tdi2.scene2d.actions;

import com.prineside.tdi2.scene2d.Action;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/VisibleAction.class */
public class VisibleAction extends Action {
    private boolean c;

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        this.f2637b.setVisible(this.c);
        return true;
    }

    public boolean isVisible() {
        return this.c;
    }

    public void setVisible(boolean z) {
        this.c = z;
    }
}
