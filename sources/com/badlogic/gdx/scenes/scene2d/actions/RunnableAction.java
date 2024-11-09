package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/RunnableAction.class */
public class RunnableAction extends Action {
    private Runnable runnable;
    private boolean ran;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        if (!this.ran) {
            this.ran = true;
            run();
            return true;
        }
        return true;
    }

    public void run() {
        Pool pool = getPool();
        setPool(null);
        try {
            this.runnable.run();
        } finally {
            setPool(pool);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.ran = false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.runnable = null;
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}
