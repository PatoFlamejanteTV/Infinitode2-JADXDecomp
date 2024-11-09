package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.utils.SnapshotArray;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Stack.class */
public class Stack extends WidgetGroup {
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private boolean q;

    public Stack() {
        this.q = true;
        setTransform(false);
        setWidth(150.0f);
        setHeight(150.0f);
        setTouchable(Touchable.childrenOnly);
    }

    public Stack(Actor... actorArr) {
        this();
        for (Actor actor : actorArr) {
            addActor(actor);
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.q = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d() {
        float f;
        float f2;
        this.q = false;
        this.k = 0.0f;
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.o = 0.0f;
        this.p = 0.0f;
        SnapshotArray<Actor> children = getChildren();
        int i = children.size;
        for (int i2 = 0; i2 < i; i2++) {
            Actor actor = children.get(i2);
            if (actor instanceof Layout) {
                Layout layout = (Layout) actor;
                this.k = Math.max(this.k, layout.getPrefWidth());
                this.l = Math.max(this.l, layout.getPrefHeight());
                this.m = Math.max(this.m, layout.getMinWidth());
                this.n = Math.max(this.n, layout.getMinHeight());
                f = layout.getMaxWidth();
                f2 = layout.getMaxHeight();
            } else {
                this.k = Math.max(this.k, actor.getWidth());
                this.l = Math.max(this.l, actor.getHeight());
                this.m = Math.max(this.m, actor.getWidth());
                this.n = Math.max(this.n, actor.getHeight());
                f = 0.0f;
                f2 = 0.0f;
            }
            if (f > 0.0f) {
                this.o = this.o == 0.0f ? f : Math.min(this.o, f);
            }
            if (f2 > 0.0f) {
                this.p = this.p == 0.0f ? f2 : Math.min(this.p, f2);
            }
        }
    }

    public void add(Actor actor) {
        addActor(actor);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        if (this.q) {
            d();
        }
        float width = getWidth();
        float height = getHeight();
        SnapshotArray<Actor> children = getChildren();
        int i = children.size;
        for (int i2 = 0; i2 < i; i2++) {
            Actor actor = children.get(i2);
            actor.setBounds(0.0f, 0.0f, width, height);
            if (actor instanceof Layout) {
                ((Layout) actor).validate();
            }
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.q) {
            d();
        }
        return this.k;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.q) {
            d();
        }
        return this.l;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        if (this.q) {
            d();
        }
        return this.m;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        if (this.q) {
            d();
        }
        return this.n;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxWidth() {
        if (this.q) {
            d();
        }
        return this.o;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxHeight() {
        if (this.q) {
            d();
        }
        return this.p;
    }
}
