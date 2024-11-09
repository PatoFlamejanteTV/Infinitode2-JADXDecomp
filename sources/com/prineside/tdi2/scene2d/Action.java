package com.prineside.tdi2.scene2d;

import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/Action.class */
public abstract class Action implements Pool.Poolable {

    /* renamed from: a, reason: collision with root package name */
    protected Actor f2636a;

    /* renamed from: b, reason: collision with root package name */
    protected Actor f2637b;

    @Null
    private Pool c;

    public abstract boolean act(float f);

    public void restart() {
    }

    public void setActor(Actor actor) {
        this.f2636a = actor;
        if (this.f2637b == null) {
            setTarget(actor);
        }
        if (actor == null && this.c != null) {
            this.c.free(this);
            this.c = null;
        }
    }

    public Actor getActor() {
        return this.f2636a;
    }

    public void setTarget(Actor actor) {
        this.f2637b = actor;
    }

    public Actor getTarget() {
        return this.f2637b;
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.f2636a = null;
        this.f2637b = null;
        this.c = null;
        restart();
    }

    @Null
    public Pool getPool() {
        return this.c;
    }

    public void setPool(@Null Pool pool) {
        this.c = pool;
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
