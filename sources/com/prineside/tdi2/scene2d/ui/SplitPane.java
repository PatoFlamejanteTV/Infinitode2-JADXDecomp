package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.Layout;
import com.prineside.tdi2.scene2d.utils.ScissorStack;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/SplitPane.class */
public class SplitPane extends WidgetGroup {
    SplitPaneStyle k;

    @Null
    private Actor r;

    @Null
    private Actor s;
    boolean l;
    private float t;
    boolean o;
    float m = 0.5f;
    private float u = 1.0f;
    private final Rectangle v = new Rectangle();
    private final Rectangle w = new Rectangle();
    final Rectangle n = new Rectangle();
    private final Rectangle x = new Rectangle();
    Vector2 p = new Vector2();
    Vector2 q = new Vector2();

    public SplitPane(@Null Actor actor, @Null Actor actor2, boolean z, SplitPaneStyle splitPaneStyle) {
        this.l = z;
        setStyle(splitPaneStyle);
        setFirstWidget(actor);
        setSecondWidget(actor2);
        setSize(getPrefWidth(), getPrefHeight());
        d();
    }

    private void d() {
        addListener(new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.SplitPane.1

            /* renamed from: a, reason: collision with root package name */
            private int f2672a = -1;

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (this.f2672a != -1) {
                    return false;
                }
                if ((i != 0 || i2 == 0) && SplitPane.this.n.contains(f, f2)) {
                    this.f2672a = i;
                    SplitPane.this.p.set(f, f2);
                    SplitPane.this.q.set(SplitPane.this.n.x, SplitPane.this.n.y);
                    return true;
                }
                return false;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i == this.f2672a) {
                    this.f2672a = -1;
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                if (i != this.f2672a) {
                    return;
                }
                Drawable drawable = SplitPane.this.k.handle;
                if (!SplitPane.this.l) {
                    float f3 = f - SplitPane.this.p.x;
                    float width = SplitPane.this.getWidth() - drawable.getMinWidth();
                    float f4 = SplitPane.this.q.x + f3;
                    SplitPane.this.q.x = f4;
                    SplitPane.this.m = Math.min(width, Math.max(0.0f, f4)) / width;
                    SplitPane.this.p.set(f, f2);
                } else {
                    float f5 = f2 - SplitPane.this.p.y;
                    float height = SplitPane.this.getHeight() - drawable.getMinHeight();
                    float f6 = SplitPane.this.q.y + f5;
                    SplitPane.this.q.y = f6;
                    SplitPane.this.m = 1.0f - (Math.min(height, Math.max(0.0f, f6)) / height);
                    SplitPane.this.p.set(f, f2);
                }
                SplitPane.this.invalidate();
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                SplitPane.this.o = SplitPane.this.n.contains(f, f2);
                return false;
            }
        });
    }

    public void setStyle(SplitPaneStyle splitPaneStyle) {
        this.k = splitPaneStyle;
        invalidateHierarchy();
    }

    public SplitPaneStyle getStyle() {
        return this.k;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        g();
        if (!this.l) {
            e();
        } else {
            f();
        }
        Actor actor = this.r;
        if (actor != 0) {
            Rectangle rectangle = this.v;
            actor.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (actor instanceof Layout) {
                ((Layout) actor).validate();
            }
        }
        Actor actor2 = this.s;
        if (actor2 != 0) {
            Rectangle rectangle2 = this.w;
            actor2.setBounds(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            if (actor2 instanceof Layout) {
                ((Layout) actor2).validate();
            }
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        float prefWidth;
        float prefWidth2;
        if (this.r == null) {
            prefWidth = 0.0f;
        } else {
            prefWidth = this.r instanceof Layout ? ((Layout) this.r).getPrefWidth() : this.r.getWidth();
        }
        float f = prefWidth;
        if (this.s == null) {
            prefWidth2 = 0.0f;
        } else {
            prefWidth2 = this.s instanceof Layout ? ((Layout) this.s).getPrefWidth() : this.s.getWidth();
        }
        float f2 = prefWidth2;
        return this.l ? Math.max(f, f2) : f + this.k.handle.getMinWidth() + f2;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        float prefHeight;
        float prefHeight2;
        if (this.r == null) {
            prefHeight = 0.0f;
        } else {
            prefHeight = this.r instanceof Layout ? ((Layout) this.r).getPrefHeight() : this.r.getHeight();
        }
        float f = prefHeight;
        if (this.s == null) {
            prefHeight2 = 0.0f;
        } else {
            prefHeight2 = this.s instanceof Layout ? ((Layout) this.s).getPrefHeight() : this.s.getHeight();
        }
        float f2 = prefHeight2;
        return !this.l ? Math.max(f, f2) : f + this.k.handle.getMinHeight() + f2;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        float minWidth = this.r instanceof Layout ? ((Layout) this.r).getMinWidth() : 0.0f;
        float minWidth2 = this.s instanceof Layout ? ((Layout) this.s).getMinWidth() : 0.0f;
        return this.l ? Math.max(minWidth, minWidth2) : minWidth + this.k.handle.getMinWidth() + minWidth2;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        float minHeight = this.r instanceof Layout ? ((Layout) this.r).getMinHeight() : 0.0f;
        float minHeight2 = this.s instanceof Layout ? ((Layout) this.s).getMinHeight() : 0.0f;
        return !this.l ? Math.max(minHeight, minHeight2) : minHeight + this.k.handle.getMinHeight() + minHeight2;
    }

    public void setVertical(boolean z) {
        if (this.l == z) {
            return;
        }
        this.l = z;
        invalidateHierarchy();
    }

    public boolean isVertical() {
        return this.l;
    }

    private void e() {
        Drawable drawable = this.k.handle;
        float height = getHeight();
        float width = getWidth() - drawable.getMinWidth();
        float f = (int) (width * this.m);
        float f2 = width - f;
        float minWidth = drawable.getMinWidth();
        this.v.set(0.0f, 0.0f, f, height);
        this.w.set(f + minWidth, 0.0f, f2, height);
        this.n.set(f, 0.0f, minWidth, height);
    }

    private void f() {
        Drawable drawable = this.k.handle;
        float width = getWidth();
        float height = getHeight();
        float minHeight = height - drawable.getMinHeight();
        float f = (int) (minHeight * this.m);
        float f2 = minHeight - f;
        float minHeight2 = drawable.getMinHeight();
        this.v.set(0.0f, height - f, width, f);
        this.w.set(0.0f, 0.0f, width, f2);
        this.n.set(0.0f, f2, width, minHeight2);
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        Stage stage = getStage();
        if (stage == null) {
            return;
        }
        validate();
        Color color = getColor();
        float f2 = color.f889a * f;
        a(batch, b());
        if (this.r != null && this.r.isVisible()) {
            batch.flush();
            stage.calculateScissors(this.v, this.x);
            if (ScissorStack.pushScissors(this.x)) {
                this.r.draw(batch, f2);
                batch.flush();
                ScissorStack.popScissors();
            }
        }
        if (this.s != null && this.s.isVisible()) {
            batch.flush();
            stage.calculateScissors(this.w, this.x);
            if (ScissorStack.pushScissors(this.x)) {
                this.s.draw(batch, f2);
                batch.flush();
                ScissorStack.popScissors();
            }
        }
        batch.setColor(color.r, color.g, color.f888b, f2);
        this.k.handle.draw(batch, this.n.x, this.n.y, this.n.width, this.n.height);
        a(batch);
    }

    public void setSplitAmount(float f) {
        this.m = f;
        invalidate();
    }

    public float getSplitAmount() {
        return this.m;
    }

    private void g() {
        float f = this.t;
        float f2 = this.u;
        if (this.l) {
            float height = getHeight() - this.k.handle.getMinHeight();
            if (this.r instanceof Layout) {
                f = Math.max(f, Math.min(((Layout) this.r).getMinHeight() / height, 1.0f));
            }
            if (this.s instanceof Layout) {
                f2 = Math.min(f2, 1.0f - Math.min(((Layout) this.s).getMinHeight() / height, 1.0f));
            }
        } else {
            float width = getWidth() - this.k.handle.getMinWidth();
            if (this.r instanceof Layout) {
                f = Math.max(f, Math.min(((Layout) this.r).getMinWidth() / width, 1.0f));
            }
            if (this.s instanceof Layout) {
                f2 = Math.min(f2, 1.0f - Math.min(((Layout) this.s).getMinWidth() / width, 1.0f));
            }
        }
        if (f > f2) {
            this.m = 0.5f * (f + f2);
        } else {
            this.m = Math.max(Math.min(this.m, f2), f);
        }
    }

    public float getMinSplitAmount() {
        return this.t;
    }

    public void setMinSplitAmount(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new GdxRuntimeException("minAmount has to be >= 0 and <= 1");
        }
        this.t = f;
    }

    public float getMaxSplitAmount() {
        return this.u;
    }

    public void setMaxSplitAmount(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new GdxRuntimeException("maxAmount has to be >= 0 and <= 1");
        }
        this.u = f;
    }

    public void setFirstWidget(@Null Actor actor) {
        if (this.r != null) {
            super.removeActor(this.r);
        }
        this.r = actor;
        if (actor != null) {
            super.addActor(actor);
        }
        invalidate();
    }

    public void setSecondWidget(@Null Actor actor) {
        if (this.s != null) {
            super.removeActor(this.s);
        }
        this.s = actor;
        if (actor != null) {
            super.addActor(actor);
        }
        invalidate();
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public void addActorAt(int i, Actor actor) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor == this.r) {
            setFirstWidget(null);
            return true;
        }
        if (actor == this.s) {
            setSecondWidget(null);
            return true;
        }
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor, boolean z) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor == this.r) {
            super.removeActor(actor, z);
            this.r = null;
            invalidate();
            return true;
        }
        if (actor == this.s) {
            super.removeActor(actor, z);
            this.s = null;
            invalidate();
            return true;
        }
        return false;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public Actor removeActorAt(int i, boolean z) {
        Actor removeActorAt = super.removeActorAt(i, z);
        if (removeActorAt == this.r) {
            super.removeActor(removeActorAt, z);
            this.r = null;
            invalidate();
        } else if (removeActorAt == this.s) {
            super.removeActor(removeActorAt, z);
            this.s = null;
            invalidate();
        }
        return removeActorAt;
    }

    public boolean isCursorOverHandle() {
        return this.o;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/SplitPane$SplitPaneStyle.class */
    public static class SplitPaneStyle {
        public Drawable handle;

        public SplitPaneStyle() {
        }

        public SplitPaneStyle(Drawable drawable) {
            this.handle = drawable;
        }

        public SplitPaneStyle(SplitPaneStyle splitPaneStyle) {
            this.handle = splitPaneStyle.handle;
        }
    }
}
