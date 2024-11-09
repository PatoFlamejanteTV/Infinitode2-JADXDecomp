package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Actor;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/ParallelAction.class */
public class ParallelAction extends Action {
    Array<Action> c = new Array<>(4);
    private boolean d;

    public ParallelAction() {
    }

    public ParallelAction(Action action) {
        addAction(action);
    }

    public ParallelAction(Action action, Action action2) {
        addAction(action);
        addAction(action2);
    }

    public ParallelAction(Action action, Action action2, Action action3) {
        addAction(action);
        addAction(action2);
        addAction(action3);
    }

    public ParallelAction(Action action, Action action2, Action action3, Action action4) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
    }

    public ParallelAction(Action action, Action action2, Action action3, Action action4, Action action5) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
        addAction(action5);
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        if (this.d) {
            return true;
        }
        this.d = true;
        Pool pool = getPool();
        setPool(null);
        try {
            Array<Action> array = this.c;
            int i = array.size;
            for (int i2 = 0; i2 < i && this.f2636a != null; i2++) {
                Action action = array.get(i2);
                if (action.getActor() != null && !action.act(f)) {
                    this.d = false;
                }
                if (this.f2636a == null) {
                    setPool(pool);
                    return true;
                }
            }
            return this.d;
        } finally {
            setPool(pool);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void restart() {
        this.d = false;
        Array<Action> array = this.c;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            array.get(i2).restart();
        }
    }

    @Override // com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.c.clear();
    }

    public void addAction(Action action) {
        this.c.add(action);
        if (this.f2636a != null) {
            action.setActor(this.f2636a);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void setActor(Actor actor) {
        Array<Action> array = this.c;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            array.get(i2).setActor(actor);
        }
        super.setActor(actor);
    }

    public Array<Action> getActions() {
        return this.c;
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(super.toString());
        sb.append('(');
        Array<Action> array = this.c;
        int i = array.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(array.get(i2));
        }
        sb.append(')');
        return sb.toString();
    }
}
