package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/Action.class */
public abstract class Action implements Pool.Poolable {
    protected Actor actor;
    protected Actor target;

    @Null
    private Pool pool;

    public abstract boolean act(float f);

    public void restart() {
    }

    public void setActor(Actor actor) {
        this.actor = actor;
        if (this.target == null) {
            setTarget(actor);
        }
        if (actor == null && this.pool != null) {
            this.pool.free(this);
            this.pool = null;
        }
    }

    public Actor getActor() {
        return this.actor;
    }

    public void setTarget(Actor actor) {
        this.target = actor;
    }

    public Actor getTarget() {
        return this.target;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.actor = null;
        this.target = null;
        this.pool = null;
        restart();
    }

    @Null
    public Pool getPool() {
        return this.pool;
    }

    public void setPool(@Null Pool pool) {
        this.pool = pool;
    }

    public String toString() {
        String name = getClass().getName();
        String str = name;
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        if (str.endsWith("Action")) {
            str = str.substring(0, str.length() - 6);
        }
        return str;
    }
}
