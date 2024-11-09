package com.prineside.tdi2.scene2d.actions;

import com.prineside.tdi2.scene2d.Action;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/RemoveActorAction.class */
public class RemoveActorAction extends Action {
    private boolean c;

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        if (!this.c) {
            this.c = true;
            this.f2637b.remove();
            return true;
        }
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void restart() {
        this.c = false;
    }
}
