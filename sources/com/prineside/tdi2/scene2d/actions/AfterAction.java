package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.utils.Array;
import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Actor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/AfterAction.class */
public class AfterAction extends DelegateAction {
    private Array<Action> d = new Array<>(false, 4);

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction, com.prineside.tdi2.scene2d.Action
    public void setTarget(Actor actor) {
        if (actor != null) {
            this.d.addAll(actor.getActions());
        }
        super.setTarget(actor);
    }

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction, com.prineside.tdi2.scene2d.Action
    public void restart() {
        super.restart();
        this.d.clear();
    }

    @Override // com.prineside.tdi2.scene2d.actions.DelegateAction
    protected final boolean a(float f) {
        Array<Action> actions = this.f2637b.getActions();
        if (actions.size == 1) {
            this.d.clear();
        }
        for (int i = this.d.size - 1; i >= 0; i--) {
            if (actions.indexOf(this.d.get(i), true) == -1) {
                this.d.removeIndex(i);
            }
        }
        if (this.d.size > 0) {
            return false;
        }
        return this.c.act(f);
    }
}
