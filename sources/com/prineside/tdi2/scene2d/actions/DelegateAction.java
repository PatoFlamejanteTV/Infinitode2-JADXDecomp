package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Actor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/DelegateAction.class */
public abstract class DelegateAction extends Action {
    protected Action c;

    protected abstract boolean a(float f);

    public void setAction(Action action) {
        this.c = action;
    }

    public Action getAction() {
        return this.c;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public final boolean act(float f) {
        Pool pool = getPool();
        setPool(null);
        try {
            return a(f);
        } finally {
            setPool(pool);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void restart() {
        if (this.c != null) {
            this.c.restart();
        }
    }

    @Override // com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.c = null;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void setActor(Actor actor) {
        if (this.c != null) {
            this.c.setActor(actor);
        }
        super.setActor(actor);
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void setTarget(Actor actor) {
        if (this.c != null) {
            this.c.setTarget(actor);
        }
        super.setTarget(actor);
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public String toString() {
        return super.toString() + (this.c == null ? "" : "(" + this.c + ")");
    }
}
