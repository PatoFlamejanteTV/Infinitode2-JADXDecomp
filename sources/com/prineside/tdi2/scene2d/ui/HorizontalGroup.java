package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/HorizontalGroup.class */
public class HorizontalGroup extends WidgetGroup {
    private float k;
    private float l;
    private float m;
    private FloatArray o;
    private int q;
    private boolean r;
    private boolean t;
    private boolean u;
    private boolean v;
    private float w;
    private float x;
    private float y;
    private float z;
    private float A;
    private float B;
    private float C;
    private boolean n = true;
    private int p = 8;
    private boolean s = true;

    public HorizontalGroup() {
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
        this.l = 0.0f;
        if (this.t) {
            this.k = 0.0f;
            if (this.o == null) {
                this.o = new FloatArray();
            } else {
                this.o.clear();
            }
            FloatArray floatArray = this.o;
            float f = this.w;
            float f2 = this.x;
            float f3 = this.A + this.C;
            float width2 = getWidth() - f3;
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
                    float prefWidth = layout.getPrefWidth();
                    width = prefWidth;
                    if (prefWidth > width2) {
                        width = Math.max(width2, layout.getMinWidth());
                    }
                    height = layout.getPrefHeight();
                } else {
                    width = actor.getWidth();
                    height = actor.getHeight();
                }
                float f7 = width + (f4 > 0.0f ? f : 0.0f);
                if (f4 + f7 > width2 && f4 > 0.0f) {
                    floatArray.add(f4);
                    floatArray.add(f6);
                    this.k = Math.max(this.k, f4 + f3);
                    if (f5 > 0.0f) {
                        f5 += f2;
                    }
                    f5 += f6;
                    f6 = 0.0f;
                    f4 = 0.0f;
                    f7 = width;
                }
                f4 += f7;
                f6 = Math.max(f6, height);
                i2 += i3;
            }
            floatArray.add(f4);
            floatArray.add(f6);
            this.k = Math.max(this.k, f4 + f3);
            if (f5 > 0.0f) {
                f5 += f2;
            }
            this.l = Math.max(this.l, f5 + f6);
        } else {
            this.k = this.A + this.C + (this.w * (i - 1));
            for (int i4 = 0; i4 < i; i4++) {
                Actor actor2 = children.get(i4);
                if (actor2 instanceof Layout) {
                    Layout layout2 = (Layout) actor2;
                    this.k += layout2.getPrefWidth();
                    this.l = Math.max(this.l, layout2.getPrefHeight());
                } else {
                    this.k += actor2.getWidth();
                    this.l = Math.max(this.l, actor2.getHeight());
                }
            }
        }
        this.l += this.z + this.B;
        if (this.s) {
            this.k = (float) Math.ceil(this.k);
            this.l = (float) Math.ceil(this.l);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        float height;
        float width;
        float height2;
        if (this.n) {
            d();
        }
        if (this.t) {
            e();
            return;
        }
        boolean z = this.s;
        int i = this.p;
        float f = this.w;
        float f2 = this.B;
        float f3 = this.y;
        float height3 = ((this.v ? getHeight() : this.l) - this.z) - f2;
        float f4 = this.A;
        if ((i & 16) != 0) {
            f4 += getWidth() - this.k;
        } else if ((i & 8) == 0) {
            f4 += (getWidth() - this.k) / 2.0f;
        }
        if ((i & 4) != 0) {
            height = f2;
        } else if ((i & 2) != 0) {
            height = (getHeight() - this.z) - height3;
        } else {
            height = f2 + ((((getHeight() - f2) - this.z) - height3) / 2.0f);
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
                width = layout2.getPrefWidth();
                height2 = layout.getPrefHeight();
            } else {
                width = actor.getWidth();
                height2 = actor.getHeight();
            }
            if (f3 > 0.0f) {
                height2 = height3 * f3;
            }
            if (layout != null) {
                height2 = Math.max(height2, layout.getMinHeight());
                float maxHeight = layout.getMaxHeight();
                if (maxHeight > 0.0f && height2 > maxHeight) {
                    height2 = maxHeight;
                }
            }
            float f5 = height;
            if ((i2 & 2) != 0) {
                f5 += height3 - height2;
            } else if ((i2 & 4) == 0) {
                f5 += (height3 - height2) / 2.0f;
            }
            if (z) {
                actor.setBounds((float) Math.floor(f4), (float) Math.floor(f5), (float) Math.ceil(width), (float) Math.ceil(height2));
            } else {
                actor.setBounds(f4, f5, width, height2);
            }
            f4 += width + f;
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
        float prefHeight = getPrefHeight();
        if (prefHeight != this.m) {
            this.m = prefHeight;
            invalidateHierarchy();
        }
        int i = this.p;
        boolean z = this.s;
        float f = this.w;
        float f2 = this.y;
        float f3 = this.x;
        float f4 = (this.k - this.A) - this.C;
        float f5 = prefHeight - this.z;
        float width2 = getWidth();
        float f6 = this.A;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = -1.0f;
        if ((i & 2) != 0) {
            f5 += getHeight() - prefHeight;
        } else if ((i & 4) == 0) {
            f5 += (getHeight() - prefHeight) / 2.0f;
        }
        if (this.u) {
            f5 -= prefHeight + this.o.get(1);
            f9 = 1.0f;
        }
        if ((i & 16) != 0) {
            f6 += width2 - this.k;
        } else if ((i & 8) == 0) {
            f6 += (width2 - this.k) / 2.0f;
        }
        float f10 = width2 - this.C;
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
                float prefWidth = layout2.getPrefWidth();
                width = prefWidth;
                if (prefWidth > f10) {
                    width = Math.max(f10, layout.getMinWidth());
                }
                height = layout.getPrefHeight();
            } else {
                width = actor.getWidth();
                height = actor.getHeight();
            }
            if (f7 + width > f10 || i6 == 0) {
                int min = Math.min(i6, floatArray.size - 2);
                f7 = f6;
                if ((i2 & 16) != 0) {
                    f7 += f4 - floatArray.get(min);
                } else if ((i2 & 8) == 0) {
                    f7 += (f4 - floatArray.get(min)) / 2.0f;
                }
                f8 = floatArray.get(min + 1);
                if (min > 0) {
                    f5 += f3 * f9;
                }
                f5 += f8 * f9;
                i6 = min + 2;
            }
            if (f2 > 0.0f) {
                height = f8 * f2;
            }
            if (layout != null) {
                height = Math.max(height, layout.getMinHeight());
                float maxHeight = layout.getMaxHeight();
                if (maxHeight > 0.0f && height > maxHeight) {
                    height = maxHeight;
                }
            }
            float f11 = f5;
            if ((i2 & 2) != 0) {
                f11 += f8 - height;
            } else if ((i2 & 4) == 0) {
                f11 += (f8 - height) / 2.0f;
            }
            if (z) {
                actor.setBounds((float) Math.floor(f7), (float) Math.floor(f11), (float) Math.ceil(width), (float) Math.ceil(height));
            } else {
                actor.setBounds(f7, f11, width, height);
            }
            f7 += width + f;
            if (layout != null) {
                layout.validate();
            }
            i3 += i5;
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.t) {
            return 0.0f;
        }
        if (this.n) {
            d();
        }
        return this.k;
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.n) {
            d();
        }
        return this.l;
    }

    public int getRows() {
        if (this.t) {
            return this.o.size >> 1;
        }
        return 1;
    }

    public void setRound(boolean z) {
        this.s = z;
    }

    public HorizontalGroup reverse() {
        this.r = true;
        return this;
    }

    public HorizontalGroup reverse(boolean z) {
        this.r = z;
        return this;
    }

    public boolean getReverse() {
        return this.r;
    }

    public HorizontalGroup wrapReverse() {
        this.u = true;
        return this;
    }

    public HorizontalGroup wrapReverse(boolean z) {
        this.u = z;
        return this;
    }

    public boolean getWrapReverse() {
        return this.u;
    }

    public HorizontalGroup space(float f) {
        this.w = f;
        return this;
    }

    public float getSpace() {
        return this.w;
    }

    public HorizontalGroup wrapSpace(float f) {
        this.x = f;
        return this;
    }

    public float getWrapSpace() {
        return this.x;
    }

    public HorizontalGroup pad(float f) {
        this.z = f;
        this.A = f;
        this.B = f;
        this.C = f;
        return this;
    }

    public HorizontalGroup pad(float f, float f2, float f3, float f4) {
        this.z = f;
        this.A = f2;
        this.B = f3;
        this.C = f4;
        return this;
    }

    public HorizontalGroup padTop(float f) {
        this.z = f;
        return this;
    }

    public HorizontalGroup padLeft(float f) {
        this.A = f;
        return this;
    }

    public HorizontalGroup padBottom(float f) {
        this.B = f;
        return this;
    }

    public HorizontalGroup padRight(float f) {
        this.C = f;
        return this;
    }

    public float getPadTop() {
        return this.z;
    }

    public float getPadLeft() {
        return this.A;
    }

    public float getPadBottom() {
        return this.B;
    }

    public float getPadRight() {
        return this.C;
    }

    public HorizontalGroup align(int i) {
        this.p = i;
        return this;
    }

    public HorizontalGroup center() {
        this.p = 1;
        return this;
    }

    public HorizontalGroup top() {
        this.p |= 2;
        this.p &= -5;
        return this;
    }

    public HorizontalGroup left() {
        this.p |= 8;
        this.p &= -17;
        return this;
    }

    public HorizontalGroup bottom() {
        this.p |= 4;
        this.p &= -3;
        return this;
    }

    public HorizontalGroup right() {
        this.p |= 16;
        this.p &= -9;
        return this;
    }

    public int getAlign() {
        return this.p;
    }

    public HorizontalGroup fill() {
        this.y = 1.0f;
        return this;
    }

    public HorizontalGroup fill(float f) {
        this.y = f;
        return this;
    }

    public float getFill() {
        return this.y;
    }

    public HorizontalGroup expand() {
        this.v = true;
        return this;
    }

    public HorizontalGroup expand(boolean z) {
        this.v = z;
        return this;
    }

    public boolean getExpand() {
        return this.v;
    }

    public HorizontalGroup grow() {
        this.v = true;
        this.y = 1.0f;
        return this;
    }

    public HorizontalGroup wrap() {
        this.t = true;
        return this;
    }

    public HorizontalGroup wrap(boolean z) {
        this.t = z;
        return this;
    }

    public boolean getWrap() {
        return this.t;
    }

    public HorizontalGroup rowAlign(int i) {
        this.q = i;
        return this;
    }

    public HorizontalGroup rowCenter() {
        this.q = 1;
        return this;
    }

    public HorizontalGroup rowTop() {
        this.q |= 2;
        this.q &= -5;
        return this;
    }

    public HorizontalGroup rowLeft() {
        this.q |= 8;
        this.q &= -17;
        return this;
    }

    public HorizontalGroup rowBottom() {
        this.q |= 4;
        this.q &= -3;
        return this;
    }

    public HorizontalGroup rowRight() {
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
            shapeRenderer.rect(getX() + this.A, getY() + this.B, getOriginX(), getOriginY(), (getWidth() - this.A) - this.C, (getHeight() - this.B) - this.z, getScaleX(), getScaleY(), getRotation());
        }
    }
}
