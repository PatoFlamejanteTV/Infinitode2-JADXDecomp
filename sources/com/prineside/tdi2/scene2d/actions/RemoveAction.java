package com.prineside.tdi2.scene2d.actions;

import com.prineside.tdi2.scene2d.Action;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/RemoveAction.class */
public class RemoveAction extends Action {
    private Action c;

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        this.f2637b.removeAction(this.c);
        return true;
    }

    public Action getAction() {
        return this.c;
    }

    public void setAction(Action action) {
        this.c = action;
    }

    @Override // com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.c = null;
    }
}
