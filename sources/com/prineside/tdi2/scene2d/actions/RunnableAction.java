package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.utils.Pool;
import com.prineside.tdi2.scene2d.Action;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/RunnableAction.class */
public class RunnableAction extends Action {
    private Runnable c;
    private boolean d;

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        if (!this.d) {
            this.d = true;
            run();
            return true;
        }
        return true;
    }

    public void run() {
        Pool pool = getPool();
        setPool(null);
        try {
            this.c.run();
        } finally {
            setPool(pool);
        }
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public void restart() {
        this.d = false;
    }

    @Override // com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.c = null;
    }

    public Runnable getRunnable() {
        return this.c;
    }

    public void setRunnable(Runnable runnable) {
        this.c = runnable;
    }
}
