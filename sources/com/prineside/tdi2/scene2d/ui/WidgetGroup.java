package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SnapshotArray;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.ui.Value;
import com.prineside.tdi2.scene2d.utils.Cullable;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/WidgetGroup.class */
public class WidgetGroup extends Group implements Layout {
    private boolean l;

    @Null
    private Value n;

    @Null
    private Value o;

    @Null
    private Value p;

    @Null
    private Value q;

    @Null
    private Value r;

    @Null
    private Value s;
    private boolean k = true;
    private boolean m = true;

    public WidgetGroup() {
    }

    public WidgetGroup(Actor... actorArr) {
        for (Actor actor : actorArr) {
            addActor(actor);
        }
    }

    public float getMinWidth() {
        if (this.n == null) {
            return 0.0f;
        }
        return this.n.get(this);
    }

    public void setMinWidth(float f) {
        setMinWidth(new Value.Fixed(f));
    }

    public void setMinWidth(@Null Value value) {
        this.n = value;
        invalidateHierarchy();
    }

    @Null
    public Value getMinWidthValue() {
        return this.n;
    }

    public float getMinHeight() {
        if (this.o == null) {
            return 0.0f;
        }
        return this.o.get(this);
    }

    public void setMinHeight(@Null Value value) {
        this.o = value;
        invalidateHierarchy();
    }

    public void setMinHeight(float f) {
        setMinHeight(new Value.Fixed(f));
    }

    @Null
    public Value getMinHeightValue() {
        return this.o;
    }

    public float getPrefWidth() {
        if (this.p == null) {
            return 0.0f;
        }
        return this.p.get(this);
    }

    public void setPrefWidth(@Null Value value) {
        this.p = value;
        invalidateHierarchy();
    }

    public void setPrefWidth(float f) {
        setPrefWidth(new Value.Fixed(f));
    }

    @Null
    public Value getPrefWidthValue() {
        return this.p;
    }

    public float getPrefHeight() {
        if (this.q == null) {
            return 0.0f;
        }
        return this.q.get(this);
    }

    public void setPrefHeight(@Null Value value) {
        this.q = value;
        invalidateHierarchy();
    }

    public void setPrefHeight(float f) {
        setPrefHeight(new Value.Fixed(f));
    }

    @Null
    public Value getPrefHeightValue() {
        return this.q;
    }

    public float getMaxWidth() {
        if (this.r == null) {
            return Float.MAX_VALUE;
        }
        return this.r.get(this);
    }

    public void setMaxWidth(@Null Value value) {
        this.r = value;
        invalidateHierarchy();
    }

    public void setMaxWidth(float f) {
        setMaxWidth(new Value.Fixed(f));
    }

    @Null
    public Value getMaxWidthValue() {
        return this.r;
    }

    public float getMaxHeight() {
        if (this.s == null) {
            return Float.MAX_VALUE;
        }
        return this.s.get(this);
    }

    public void setMaxHeight(@Null Value value) {
        this.s = value;
        invalidateHierarchy();
    }

    public void setMaxHeight(float f) {
        setMaxHeight(new Value.Fixed(f));
    }

    @Null
    public Value getMaxHeightValue() {
        return this.s;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void setLayoutEnabled(boolean z) {
        this.m = z;
        a(this, z);
    }

    private void a(Group group, boolean z) {
        SnapshotArray<Actor> children = group.getChildren();
        int i = children.size;
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = (Actor) children.get(i2);
            if (obj instanceof Layout) {
                ((Layout) obj).setLayoutEnabled(z);
            } else if (obj instanceof Group) {
                a((Group) obj, z);
            }
        }
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void validate() {
        if (this.m) {
            Group parent = getParent();
            if (this.l && parent != null) {
                Stage stage = getStage();
                if (stage != null && parent == stage.getRoot()) {
                    setSize(stage.getWidth(), stage.getHeight());
                } else {
                    setSize(parent.getWidth(), parent.getHeight());
                }
            }
            if (this.k) {
                this.k = false;
                layout();
                if (!this.k || (parent instanceof WidgetGroup)) {
                    return;
                }
                for (int i = 0; i < 5; i++) {
                    this.k = false;
                    layout();
                    if (!this.k) {
                        return;
                    }
                }
            }
        }
    }

    public boolean needsLayout() {
        return this.k;
    }

    public void invalidate() {
        this.k = true;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void invalidateHierarchy() {
        invalidate();
        Cullable parent = getParent();
        if (parent instanceof Layout) {
            ((Layout) parent).invalidateHierarchy();
        }
    }

    @Override // com.prineside.tdi2.scene2d.Group
    protected final void c() {
        invalidateHierarchy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public void sizeChanged() {
        invalidate();
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void pack() {
        setSize(getPrefWidth(), getPrefHeight());
        validate();
        setSize(getPrefWidth(), getPrefHeight());
        validate();
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void setWidth(float f) {
        super.setWidth(f);
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void setFillParent(boolean z) {
        this.l = z;
    }

    public void layout() {
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public Actor hit(float f, float f2, boolean z) {
        validate();
        return super.hit(f, f2, z);
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        super.draw(batch, f);
    }
}
