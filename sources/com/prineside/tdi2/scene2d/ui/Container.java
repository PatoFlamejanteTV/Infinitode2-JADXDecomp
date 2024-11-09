package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Null;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.ui.Value;
import com.prineside.tdi2.scene2d.utils.Cullable;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/Container.class */
public class Container<T extends Actor> extends WidgetGroup {

    @Null
    private T k;
    private Value l;
    private Value m;
    private Value n;
    private Value o;
    private Value p;
    private Value q;
    private Value r;
    private Value s;
    private Value t;
    private Value u;
    private float v;
    private float w;
    private int x;

    @Null
    private Drawable y;
    private boolean z;
    private boolean A;

    public Container() {
        this.l = Value.minWidth;
        this.m = Value.minHeight;
        this.n = Value.prefWidth;
        this.o = Value.prefHeight;
        this.p = Value.zero;
        this.q = Value.zero;
        this.r = Value.zero;
        this.s = Value.zero;
        this.t = Value.zero;
        this.u = Value.zero;
        this.A = true;
        setTouchable(Touchable.childrenOnly);
        setTransform(false);
    }

    public Container(@Null T t) {
        this();
        setActor(t);
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        if (isTransform()) {
            a(batch, b());
            a(batch, f, 0.0f, 0.0f);
            if (this.z) {
                batch.flush();
                float f2 = this.s.get(this);
                float f3 = this.t.get(this);
                if (clipBegin(f2, f3, (getWidth() - f2) - this.u.get(this), (getHeight() - f3) - this.r.get(this))) {
                    a(batch, f);
                    batch.flush();
                    clipEnd();
                }
            } else {
                a(batch, f);
            }
            a(batch);
            return;
        }
        a(batch, f, getX(), getY());
        super.draw(batch, f);
    }

    private void a(Batch batch, float f, float f2, float f3) {
        if (this.y == null) {
            return;
        }
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        this.y.draw(batch, f2, f3, getWidth(), getHeight());
    }

    public void setBackground(@Null Drawable drawable) {
        setBackground(drawable, true);
    }

    public void setBackground(@Null Drawable drawable, boolean z) {
        if (this.y == drawable) {
            return;
        }
        this.y = drawable;
        if (z) {
            if (drawable == null) {
                pad(Value.zero);
            } else {
                pad(drawable.getTopHeight(), drawable.getLeftWidth(), drawable.getBottomHeight(), drawable.getRightWidth());
            }
            invalidate();
        }
    }

    public Container<T> background(@Null Drawable drawable) {
        setBackground(drawable);
        return this;
    }

    @Null
    public Drawable getBackground() {
        return this.y;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        float min;
        float min2;
        if (this.k == null) {
            return;
        }
        float f = this.s.get(this);
        float f2 = this.t.get(this);
        float width = (getWidth() - f) - this.u.get(this);
        float height = (getHeight() - f2) - this.r.get(this);
        float f3 = this.l.get(this.k);
        float f4 = this.m.get(this.k);
        float f5 = this.n.get(this.k);
        float f6 = this.o.get(this.k);
        float f7 = this.p.get(this.k);
        float f8 = this.q.get(this.k);
        if (this.v > 0.0f) {
            min = width * this.v;
        } else {
            min = Math.min(f5, width);
        }
        if (min < f3) {
            min = f3;
        }
        if (f7 > 0.0f && min > f7) {
            min = f7;
        }
        if (this.w > 0.0f) {
            min2 = height * this.w;
        } else {
            min2 = Math.min(f6, height);
        }
        if (min2 < f4) {
            min2 = f4;
        }
        if (f8 > 0.0f && min2 > f8) {
            min2 = f8;
        }
        float f9 = f;
        if ((this.x & 16) != 0) {
            f9 = f + (width - min);
        } else if ((this.x & 8) == 0) {
            f9 = f + ((width - min) / 2.0f);
        }
        float f10 = f2;
        if ((this.x & 2) != 0) {
            f10 = f2 + (height - min2);
        } else if ((this.x & 4) == 0) {
            f10 = f2 + ((height - min2) / 2.0f);
        }
        if (this.A) {
            f9 = (float) Math.floor(f9);
            f10 = (float) Math.floor(f10);
            min = (float) Math.ceil(min);
            min2 = (float) Math.ceil(min2);
        }
        this.k.setBounds(f9, f10, min, min2);
        if (this.k instanceof Layout) {
            ((Layout) this.k).validate();
        }
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.utils.Cullable
    public void setCullingArea(Rectangle rectangle) {
        super.setCullingArea(rectangle);
        if (this.v != 1.0f || this.w != 1.0f || !(this.k instanceof Cullable)) {
            return;
        }
        ((Cullable) this.k).setCullingArea(rectangle);
    }

    public void setActor(@Null T t) {
        if (t == this) {
            throw new IllegalArgumentException("actor cannot be the Container.");
        }
        if (t == this.k) {
            return;
        }
        if (this.k != null) {
            super.removeActor(this.k);
        }
        this.k = t;
        if (t != null) {
            super.addActor(t);
        }
    }

    @Null
    public T getActor() {
        return this.k;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActorAt(int i, Actor actor) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    @Deprecated
    public void addActorAfter(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use Container#setActor.");
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.k) {
            return false;
        }
        setActor(null);
        return true;
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public boolean removeActor(Actor actor, boolean z) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.k) {
            return false;
        }
        this.k = null;
        return super.removeActor(actor, z);
    }

    @Override // com.prineside.tdi2.scene2d.Group
    public Actor removeActorAt(int i, boolean z) {
        Actor removeActorAt = super.removeActorAt(i, z);
        if (removeActorAt == this.k) {
            this.k = null;
        }
        return removeActorAt;
    }

    public Container<T> size(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.l = value;
        this.m = value;
        this.n = value;
        this.o = value;
        this.p = value;
        this.q = value;
        return this;
    }

    public Container<T> size(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.l = value;
        this.m = value2;
        this.n = value;
        this.o = value2;
        this.p = value;
        this.q = value2;
        return this;
    }

    public Container<T> size(float f) {
        size(Value.Fixed.valueOf(f));
        return this;
    }

    public Container<T> size(float f, float f2) {
        size(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> width(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        this.l = value;
        this.n = value;
        this.p = value;
        return this;
    }

    public Container<T> width(float f) {
        width(Value.Fixed.valueOf(f));
        return this;
    }

    public Container<T> height(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.m = value;
        this.o = value;
        this.q = value;
        return this;
    }

    public Container<T> height(float f) {
        height(Value.Fixed.valueOf(f));
        return this;
    }

    public Container<T> minSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.l = value;
        this.m = value;
        return this;
    }

    public Container<T> minSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.l = value;
        this.m = value2;
        return this;
    }

    public Container<T> minWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("minWidth cannot be null.");
        }
        this.l = value;
        return this;
    }

    public Container<T> minHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("minHeight cannot be null.");
        }
        this.m = value;
        return this;
    }

    public Container<T> minSize(float f) {
        minSize(Value.Fixed.valueOf(f));
        return this;
    }

    public Container<T> minSize(float f, float f2) {
        minSize(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> minWidth(float f) {
        this.l = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> minHeight(float f) {
        this.m = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> prefSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.n = value;
        this.o = value;
        return this;
    }

    public Container<T> prefSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.n = value;
        this.o = value2;
        return this;
    }

    public Container<T> prefWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("prefWidth cannot be null.");
        }
        this.n = value;
        return this;
    }

    public Container<T> prefHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("prefHeight cannot be null.");
        }
        this.o = value;
        return this;
    }

    public Container<T> prefSize(float f, float f2) {
        prefSize(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> prefSize(float f) {
        prefSize(Value.Fixed.valueOf(f));
        return this;
    }

    public Container<T> prefWidth(float f) {
        this.n = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> prefHeight(float f) {
        this.o = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> maxSize(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.p = value;
        this.q = value;
        return this;
    }

    public Container<T> maxSize(Value value, Value value2) {
        if (value == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.p = value;
        this.q = value2;
        return this;
    }

    public Container<T> maxWidth(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("maxWidth cannot be null.");
        }
        this.p = value;
        return this;
    }

    public Container<T> maxHeight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("maxHeight cannot be null.");
        }
        this.q = value;
        return this;
    }

    public Container<T> maxSize(float f) {
        maxSize(Value.Fixed.valueOf(f));
        return this;
    }

    public Container<T> maxSize(float f, float f2) {
        maxSize(Value.Fixed.valueOf(f), Value.Fixed.valueOf(f2));
        return this;
    }

    public Container<T> maxWidth(float f) {
        this.p = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> maxHeight(float f) {
        this.q = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> pad(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.r = value;
        this.s = value;
        this.t = value;
        this.u = value;
        return this;
    }

    public Container<T> pad(Value value, Value value2, Value value3, Value value4) {
        if (value == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if (value2 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if (value3 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if (value4 == null) {
            throw new IllegalArgumentException("right cannot be null.");
        }
        this.r = value;
        this.s = value2;
        this.t = value3;
        this.u = value4;
        return this;
    }

    public Container<T> padTop(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.r = value;
        return this;
    }

    public Container<T> padLeft(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.s = value;
        return this;
    }

    public Container<T> padBottom(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.t = value;
        return this;
    }

    public Container<T> padRight(Value value) {
        if (value == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.u = value;
        return this;
    }

    public Container<T> pad(float f) {
        Value.Fixed valueOf = Value.Fixed.valueOf(f);
        this.r = valueOf;
        this.s = valueOf;
        this.t = valueOf;
        this.u = valueOf;
        return this;
    }

    public Container<T> pad(float f, float f2, float f3, float f4) {
        this.r = Value.Fixed.valueOf(f);
        this.s = Value.Fixed.valueOf(f2);
        this.t = Value.Fixed.valueOf(f3);
        this.u = Value.Fixed.valueOf(f4);
        return this;
    }

    public Container<T> padTop(float f) {
        this.r = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> padLeft(float f) {
        this.s = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> padBottom(float f) {
        this.t = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> padRight(float f) {
        this.u = Value.Fixed.valueOf(f);
        return this;
    }

    public Container<T> fill() {
        this.v = 1.0f;
        this.w = 1.0f;
        return this;
    }

    public Container<T> fillX() {
        this.v = 1.0f;
        return this;
    }

    public Container<T> fillY() {
        this.w = 1.0f;
        return this;
    }

    public Container<T> fill(float f, float f2) {
        this.v = f;
        this.w = f2;
        return this;
    }

    public Container<T> fill(boolean z, boolean z2) {
        this.v = z ? 1.0f : 0.0f;
        this.w = z2 ? 1.0f : 0.0f;
        return this;
    }

    public Container<T> fill(boolean z) {
        this.v = z ? 1.0f : 0.0f;
        this.w = z ? 1.0f : 0.0f;
        return this;
    }

    public Container<T> align(int i) {
        this.x = i;
        return this;
    }

    public Container<T> center() {
        this.x = 1;
        return this;
    }

    public Container<T> top() {
        this.x |= 2;
        this.x &= -5;
        return this;
    }

    public Container<T> left() {
        this.x |= 8;
        this.x &= -17;
        return this;
    }

    public Container<T> bottom() {
        this.x |= 4;
        this.x &= -3;
        return this;
    }

    public Container<T> right() {
        this.x |= 16;
        this.x &= -9;
        return this;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        return this.l.get(this.k) + this.s.get(this) + this.u.get(this);
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup
    public Value getMinHeightValue() {
        return this.m;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        return this.m.get(this.k) + this.r.get(this) + this.t.get(this);
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup
    public Value getPrefWidthValue() {
        return this.n;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        float f = this.n.get(this.k);
        if (this.y != null) {
            f = Math.max(f, this.y.getMinWidth());
        }
        return Math.max(getMinWidth(), f + this.s.get(this) + this.u.get(this));
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup
    public Value getPrefHeightValue() {
        return this.o;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        float f = this.o.get(this.k);
        if (this.y != null) {
            f = Math.max(f, this.y.getMinHeight());
        }
        return Math.max(getMinHeight(), f + this.r.get(this) + this.t.get(this));
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup
    public Value getMaxWidthValue() {
        return this.p;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxWidth() {
        float f = this.p.get(this.k);
        float f2 = f;
        if (f > 0.0f) {
            f2 += this.s.get(this) + this.u.get(this);
        }
        return f2;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup
    public Value getMaxHeightValue() {
        return this.q;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxHeight() {
        float f = this.q.get(this.k);
        float f2 = f;
        if (f > 0.0f) {
            f2 += this.r.get(this) + this.t.get(this);
        }
        return f2;
    }

    public Value getPadTopValue() {
        return this.r;
    }

    public float getPadTop() {
        return this.r.get(this);
    }

    public Value getPadLeftValue() {
        return this.s;
    }

    public float getPadLeft() {
        return this.s.get(this);
    }

    public Value getPadBottomValue() {
        return this.t;
    }

    public float getPadBottom() {
        return this.t.get(this);
    }

    public Value getPadRightValue() {
        return this.u;
    }

    public float getPadRight() {
        return this.u.get(this);
    }

    public float getPadX() {
        return this.s.get(this) + this.u.get(this);
    }

    public float getPadY() {
        return this.r.get(this) + this.t.get(this);
    }

    public float getFillX() {
        return this.v;
    }

    public float getFillY() {
        return this.w;
    }

    public int getAlign() {
        return this.x;
    }

    public void setRound(boolean z) {
        this.A = z;
    }

    public Container<T> clip() {
        setClip(true);
        return this;
    }

    public Container<T> clip(boolean z) {
        setClip(z);
        return this;
    }

    public void setClip(boolean z) {
        this.z = z;
        setTransform(z);
        invalidate();
    }

    public boolean getClip() {
        return this.z;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        if (!this.z || (!(z && getTouchable() == Touchable.disabled) && f >= 0.0f && f < getWidth() && f2 >= 0.0f && f2 < getHeight())) {
            return super.hit(f, f2, z);
        }
        return null;
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        validate();
        if (isTransform()) {
            a(shapeRenderer, b());
            if (this.z) {
                shapeRenderer.flush();
                float f = this.s.get(this);
                float f2 = this.t.get(this);
                if (this.y == null ? clipBegin(0.0f, 0.0f, getWidth(), getHeight()) : clipBegin(f, f2, (getWidth() - f) - this.u.get(this), (getHeight() - f2) - this.r.get(this))) {
                    b(shapeRenderer);
                    clipEnd();
                }
            } else {
                b(shapeRenderer);
            }
            c(shapeRenderer);
            return;
        }
        super.drawDebug(shapeRenderer);
    }
}
