package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/VerticalGroup.class */
public class VerticalGroup extends WidgetGroup {
    private float k;
    private float l;
    private float m;
    private FloatArray o;
    private int q;
    private boolean r;
    private boolean t;
    private boolean u;
    private float v;
    private float w;
    private float x;
    private float y;
    private float z;
    private float A;
    private float B;
    private boolean n = true;
    private int p = 2;
    private boolean s = true;

    public VerticalGroup() {
        setTouchable(Touchable.childrenOnly);
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.n = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void d() {
        float width;
        float height;
        this.n = false;
        SnapshotArray<Actor> children = getChildren();
        int i = children.size;
        this.k = 0.0f;
        if (this.t) {
            this.l = 0.0f;
            if (this.o == null) {
                this.o = new FloatArray();
            } else {
                this.o.clear();
            }
            FloatArray floatArray = this.o;
            float f = this.v;
            float f2 = this.w;
            float f3 = this.y + this.A;
            float height2 = getHeight() - f3;
            float f4 = 0.0f;
            float f5 = 0.0f;
            float f6 = 0.0f;
            int i2 = 0;
            int i3 = 1;
            if (this.r) {
                i2 = i - 1;
                i = -1;
                i3 = -1;
            }
            while (i2 != i) {
                Actor actor = children.get(i2);
                if (actor instanceof Layout) {
                    Layout layout = (Layout) actor;
                    width = layout.getPrefWidth();
                    float prefHeight = layout.getPrefHeight();
                    height = prefHeight;
                    if (prefHeight > height2) {
                        height = Math.max(height2, layout.getMinHeight());
                    }
                } else {
                    width = actor.getWidth();
                    height = actor.getHeight();
                }
                float f7 = height + (f5 > 0.0f ? f : 0.0f);
                if (f5 + f7 > height2 && f5 > 0.0f) {
                    floatArray.add(f5);
                    floatArray.add(f6);
                    this.l = Math.max(this.l, f5 + f3);
                    if (f4 > 0.0f) {
                        f4 += f2;
                    }
                    f4 += f6;
                    f6 = 0.0f;
                    f5 = 0.0f;
                    f7 = height;
                }
                f5 += f7;
                f6 = Math.max(f6, width);
                i2 += i3;
            }
            floatArray.add(f5);
            floatArray.add(f6);
            this.l = Math.max(this.l, f5 + f3);
            if (f4 > 0.0f) {
                f4 += f2;
            }
            this.k = Math.max(this.k, f4 + f6);
        } else {
            this.l = this.y + this.A + (this.v * (i - 1));
            for (int i4 = 0; i4 < i; i4++) {
                Actor actor2 = children.get(i4);
                if (actor2 instanceof Layout) {
                    Layout layout2 = (Layout) actor2;
                    this.k = Math.max(this.k, layout2.getPrefWidth());
                    this.l += layout2.getPrefHeight();
                } else {
                    this.k = Math.max(this.k, actor2.getWidth());
                    this.l += actor2.getHeight();
                }
            }
        }
        this.k += this.z + this.B;
        if (this.s) {
            this.k = (float) Math.ceil(this.k);
            this.l = (float) Math.ceil(this.l);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        float width;
        float width2;
        float height;
        if (this.n) {
            d();
        }
        if (this.t) {
            e();
            return;
        }
        boolean z = this.s;
        int i = this.p;
        float f = this.v;
        float f2 = this.z;
        float f3 = this.x;
        float width3 = ((this.u ? getWidth() : this.k) - f2) - this.B;
        float f4 = (this.l - this.y) + f;
        if ((i & 2) != 0) {
            f4 += getHeight() - this.l;
        } else if ((i & 4) == 0) {
            f4 += (getHeight() - this.l) / 2.0f;
        }
        if ((i & 8) != 0) {
            width = f2;
        } else if ((i & 16) != 0) {
            width = (getWidth() - this.B) - width3;
        } else {
            width = f2 + ((((getWidth() - f2) - this.B) - width3) / 2.0f);
        }
        int i2 = this.q;
        SnapshotArray<Actor> children = getChildren();
        int i3 = 0;
        int i4 = children.size;
        int i5 = 1;
        if (this.r) {
            i3 = i4 - 1;
            i4 = -1;
            i5 = -1;
        }
        while (i3 != i4) {
            Actor actor = children.get(i3);
            Layout layout = null;
            if (actor instanceof Layout) {
                Layout layout2 = (Layout) actor;
                layout = layout2;
                width2 = layout2.getPrefWidth();
                height = layout.getPrefHeight();
            } else {
                width2 = actor.getWidth();
                height = actor.getHeight();
            }
            if (f3 > 0.0f) {
                width2 = width3 * f3;
            }
            if (layout != null) {
                width2 = Math.max(width2, layout.getMinWidth());
                float maxWidth = layout.getMaxWidth();
                if (maxWidth > 0.0f && width2 > maxWidth) {
                    width2 = maxWidth;
                }
            }
            float f5 = width;
            if ((i2 & 16) != 0) {
                f5 += width3 - width2;
            } else if ((i2 & 8) == 0) {
                f5 += (width3 - width2) / 2.0f;
            }
            f4 -= height + f;
            if (z) {
                actor.setBounds((float) Math.floor(f5), (float) Math.floor(f4), (float) Math.ceil(width2), (float) Math.ceil(height));
            } else {
                actor.setBounds(f5, f4, width2, height);
            }
            if (layout != null) {
                layout.validate();
            }
            i3 += i5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void e() {
        float width;
        float height;
        float prefWidth = getPrefWidth();
        if (prefWidth != this.m) {
            this.m = prefWidth;
            invalidateHierarchy();
        }
        int i = this.p;
        boolean z = this.s;
        float f = this.v;
        float f2 = this.z;
        float f3 = this.x;
        float f4 = this.w;
        float f5 = (this.l - this.y) - this.A;
        float f6 = f2;
        float height2 = getHeight();
        float f7 = (this.l - this.y) + f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        if ((i & 16) != 0) {
            f6 += getWidth() - prefWidth;
        } else if ((i & 8) == 0) {
            f6 += (getWidth() - prefWidth) / 2.0f;
        }
        if ((i & 2) != 0) {
            f7 += height2 - this.l;
        } else if ((i & 4) == 0) {
            f7 += (height2 - this.l) / 2.0f;
        }
        float f10 = height2 - this.y;
        int i2 = this.q;
        FloatArray floatArray = this.o;
        SnapshotArray<Actor> children = getChildren();
        int i3 = 0;
        int i4 = children.size;
        int i5 = 1;
        if (this.r) {
            i3 = i4 - 1;
            i4 = -1;
            i5 = -1;
        }
        int i6 = 0;
        while (i3 != i4) {
            Actor actor = children.get(i3);
            Layout layout = null;
            if (actor instanceof Layout) {
                Layout layout2 = (Layout) actor;
                layout = layout2;
                width = layout2.getPrefWidth();
                float prefHeight = layout.getPrefHeight();
                height = prefHeight;
                if (prefHeight > f10) {
                    height = Math.max(f10, layout.getMinHeight());
                }
            } else {
                width = actor.getWidth();
                height = actor.getHeight();
            }
            if ((f8 - height) - f < this.A || i6 == 0) {
                int min = Math.min(i6, floatArray.size - 2);
                f8 = f7;
                if ((i2 & 4) != 0) {
                    f8 -= f5 - floatArray.get(min);
                } else if ((i2 & 2) == 0) {
                    f8 -= (f5 - floatArray.get(min)) / 2.0f;
                }
                if (min > 0) {
                    f6 = f6 + f4 + f9;
                }
                f9 = floatArray.get(min + 1);
                i6 = min + 2;
            }
            if (f3 > 0.0f) {
                width = f9 * f3;
            }
            if (layout != null) {
                width = Math.max(width, layout.getMinWidth());
                float maxWidth = layout.getMaxWidth();
                if (maxWidth > 0.0f && width > maxWidth) {
                    width = maxWidth;
                }
            }
            float f11 = f6;
            if ((i2 & 16) != 0) {
                f11 += f9 - width;
            } else if ((i2 & 8) == 0) {
                f11 += (f9 - width) / 2.0f;
            }
            f8 -= height + f;
            if (z) {
                actor.setBounds((float) Math.floor(f11), (float) Math.floor(f8), (float) Math.ceil(width), (float) Math.ceil(height));
            } else {
                actor.setBounds(f11, f8, width, height);
            }
            if (layout != null) {
                layout.validate();
            }
            i3 += i5;
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.n) {
            d();
        }
        return this.k;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.t) {
            return 0.0f;
        }
        if (this.n) {
            d();
        }
        return this.l;
    }

    public int getColumns() {
        if (this.t) {
            return this.o.size >> 1;
        }
        return 1;
    }

    public void setRound(boolean z) {
        this.s = z;
    }

    public VerticalGroup reverse() {
        this.r = true;
        return this;
    }

    public VerticalGroup reverse(boolean z) {
        this.r = z;
        return this;
    }

    public boolean getReverse() {
        return this.r;
    }

    public VerticalGroup space(float f) {
        this.v = f;
        return this;
    }

    public float getSpace() {
        return this.v;
    }

    public VerticalGroup wrapSpace(float f) {
        this.w = f;
        return this;
    }

    public float getWrapSpace() {
        return this.w;
    }

    public VerticalGroup pad(float f) {
        this.y = f;
        this.z = f;
        this.A = f;
        this.B = f;
        return this;
    }

    public VerticalGroup pad(float f, float f2, float f3, float f4) {
        this.y = f;
        this.z = f2;
        this.A = f3;
        this.B = f4;
        return this;
    }

    public VerticalGroup padTop(float f) {
        this.y = f;
        return this;
    }

    public VerticalGroup padLeft(float f) {
        this.z = f;
        return this;
    }

    public VerticalGroup padBottom(float f) {
        this.A = f;
        return this;
    }

    public VerticalGroup padRight(float f) {
        this.B = f;
        return this;
    }

    public float getPadTop() {
        return this.y;
    }

    public float getPadLeft() {
        return this.z;
    }

    public float getPadBottom() {
        return this.A;
    }

    public float getPadRight() {
        return this.B;
    }

    public VerticalGroup align(int i) {
        this.p = i;
        return this;
    }

    public VerticalGroup center() {
        this.p = 1;
        return this;
    }

    public VerticalGroup top() {
        this.p |= 2;
        this.p &= -5;
        return this;
    }

    public VerticalGroup left() {
        this.p |= 8;
        this.p &= -17;
        return this;
    }

    public VerticalGroup bottom() {
        this.p |= 4;
        this.p &= -3;
        return this;
    }

    public VerticalGroup right() {
        this.p |= 16;
        this.p &= -9;
        return this;
    }

    public int getAlign() {
        return this.p;
    }

    public VerticalGroup fill() {
        this.x = 1.0f;
        return this;
    }

    public VerticalGroup fill(float f) {
        this.x = f;
        return this;
    }

    public float getFill() {
        return this.x;
    }

    public VerticalGroup expand() {
        this.u = true;
        return this;
    }

    public VerticalGroup expand(boolean z) {
        this.u = z;
        return this;
    }

    public boolean getExpand() {
        return this.u;
    }

    public VerticalGroup grow() {
        this.u = true;
        this.x = 1.0f;
        return this;
    }

    public VerticalGroup wrap() {
        this.t = true;
        return this;
    }

    public VerticalGroup wrap(boolean z) {
        this.t = z;
        return this;
    }

    public boolean getWrap() {
        return this.t;
    }

    public VerticalGroup columnAlign(int i) {
        this.q = i;
        return this;
    }

    public VerticalGroup columnCenter() {
        this.q = 1;
        return this;
    }

    public VerticalGroup columnTop() {
        this.q |= 2;
        this.q &= -5;
        return this;
    }

    public VerticalGroup columnLeft() {
        this.q |= 8;
        this.q &= -17;
        return this;
    }

    public VerticalGroup columnBottom() {
        this.q |= 4;
        this.q &= -3;
        return this;
    }

    public VerticalGroup columnRight() {
        this.q |= 16;
        this.q &= -9;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public final void a(ShapeRenderer shapeRenderer) {
        super.a(shapeRenderer);
        if (getDebug()) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            if (getStage() != null) {
                shapeRenderer.setColor(getStage().getDebugColor());
            }
            shapeRenderer.rect(getX() + this.z, getY() + this.A, getOriginX(), getOriginY(), (getWidth() - this.z) - this.B, (getHeight() - this.A) - this.y, getScaleX(), getScaleY(), getRotation());
        }
    }
}
