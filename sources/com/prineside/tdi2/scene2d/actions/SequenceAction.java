package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.scene2d.Action;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/SequenceAction.class */
public class SequenceAction extends ParallelAction {
    private int d;

    public SequenceAction() {
    }

    public SequenceAction(Action action) {
        addAction(action);
    }

    public SequenceAction(Action action, Action action2) {
        addAction(action);
        addAction(action2);
    }

    public SequenceAction(Action action, Action action2, Action action3) {
        addAction(action);
        addAction(action2);
        addAction(action3);
    }

    public SequenceAction(Action action, Action action2, Action action3, Action action4) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
    }

    public SequenceAction(Action action, Action action2, Action action3, Action action4, Action action5) {
        addAction(action);
        addAction(action2);
        addAction(action3);
        addAction(action4);
        addAction(action5);
    }

    @Override // com.prineside.tdi2.scene2d.actions.ParallelAction, com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        if (this.d >= this.c.size) {
            return true;
        }
        Pool pool = getPool();
        setPool(null);
        try {
            if (this.c.get(this.d).act(f)) {
                if (this.f2636a == null) {
                    setPool(pool);
                    return true;
                }
                this.d++;
                if (this.d >= this.c.size) {
                    setPool(pool);
                    return true;
                }
            }
            setPool(pool);
            return false;
        } catch (Throwable th) {
            setPool(pool);
            throw th;
        }
    }

    @Override // com.prineside.tdi2.scene2d.actions.ParallelAction, com.prineside.tdi2.scene2d.Action
    public void restart() {
        super.restart();
        this.d = 0;
    }
}
