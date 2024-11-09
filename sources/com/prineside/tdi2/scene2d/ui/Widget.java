package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Group;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.ui.Value;
import com.prineside.tdi2.scene2d.utils.Cullable;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Widget.class */
public class Widget extends Actor implements Layout {
    private boolean k;

    @Null
    private Value m;

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
    private boolean j = true;
    private boolean l = true;

    public float getMinWidth() {
        if (this.m == null) {
            return 0.0f;
        }
        return this.m.get(this);
    }

    public void setMinWidth(float f) {
        setMinWidth(new Value.Fixed(f));
    }

    public void setMinWidth(@Null Value value) {
        this.m = value;
        invalidateHierarchy();
    }

    @Null
    public Value getMinWidthValue() {
        return this.m;
    }

    public float getPrefWidth() {
        if (this.o == null) {
            return 0.0f;
        }
        return this.o.get(this);
    }

    public void setPrefWidth(@Null Value value) {
        this.o = value;
        invalidateHierarchy();
    }

    public void setPrefWidth(float f) {
        setPrefWidth(new Value.Fixed(f));
    }

    @Null
    public Value getPrefWidthValue() {
        return this.o;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxWidth() {
        if (this.q == null) {
            return Float.MAX_VALUE;
        }
        return this.q.get(this);
    }

    public void setMaxWidth(@Null Value value) {
        this.q = value;
        invalidateHierarchy();
    }

    public void setMaxWidth(float f) {
        setMaxWidth(new Value.Fixed(f));
    }

    @Null
    public Value getMaxWidthValue() {
        return this.q;
    }

    public float getMinHeight() {
        if (this.n == null) {
            return 0.0f;
        }
        return this.n.get(this);
    }

    public void setMinHeight(@Null Value value) {
        this.n = value;
        invalidateHierarchy();
    }

    public void setMinHeight(float f) {
        setMinHeight(new Value.Fixed(f));
    }

    @Null
    public Value getMinHeightValue() {
        return this.n;
    }

    public float getPrefHeight() {
        if (this.p == null) {
            return 0.0f;
        }
        return this.p.get(this);
    }

    public void setPrefHeight(@Null Value value) {
        this.p = value;
        invalidateHierarchy();
    }

    public void setPrefHeight(float f) {
        setPrefHeight(new Value.Fixed(f));
    }

    @Null
    public Value getPrefHeightValue() {
        return this.p;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxHeight() {
        if (this.r == null) {
            return Float.MAX_VALUE;
        }
        return this.r.get(this);
    }

    public void setMaxHeight(@Null Value value) {
        this.r = value;
        invalidateHierarchy();
    }

    public void setMaxHeight(float f) {
        setMaxHeight(new Value.Fixed(f));
    }

    @Null
    public Value getMaxHeightValue() {
        return this.r;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void setLayoutEnabled(boolean z) {
        this.l = z;
        if (z) {
            invalidateHierarchy();
        }
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void validate() {
        float width;
        float height;
        if (this.l) {
            Group parent = getParent();
            if (this.k && parent != null) {
                Stage stage = getStage();
                if (stage != null && parent == stage.getRoot()) {
                    width = stage.getWidth();
                    height = stage.getHeight();
                } else {
                    width = parent.getWidth();
                    height = parent.getHeight();
                }
                setSize(width, height);
            }
            if (this.j) {
                this.j = false;
                layout();
            }
        }
    }

    public boolean needsLayout() {
        return this.j;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void invalidate() {
        this.j = true;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void invalidateHierarchy() {
        if (this.l) {
            invalidate();
            Cullable parent = getParent();
            if (parent instanceof Layout) {
                ((Layout) parent).invalidateHierarchy();
            }
        }
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
    }

    @Override // com.prineside.tdi2.scene2d.utils.Layout
    public void setFillParent(boolean z) {
        this.k = z;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
    }

    public void layout() {
    }
}
