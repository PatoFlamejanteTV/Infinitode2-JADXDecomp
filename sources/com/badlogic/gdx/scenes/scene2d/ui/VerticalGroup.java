package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/VerticalGroup.class */
public class VerticalGroup extends WidgetGroup {
    private float prefWidth;
    private float prefHeight;
    private float lastPrefWidth;
    private FloatArray columnSizes;
    private int columnAlign;
    private boolean reverse;
    private boolean wrap;
    private boolean expand;
    private float space;
    private float wrapSpace;
    private float fill;
    private float padTop;
    private float padLeft;
    private float padBottom;
    private float padRight;
    private boolean sizeInvalid = true;
    private int align = 2;
    private boolean round = true;

    public VerticalGroup() {
        setTouchable(Touchable.childrenOnly);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void computeSize() {
        float width;
        float height;
        this.sizeInvalid = false;
        SnapshotArray<Actor> children = getChildren();
        int i = children.size;
        this.prefWidth = 0.0f;
        if (this.wrap) {
            this.prefHeight = 0.0f;
            if (this.columnSizes == null) {
                this.columnSizes = new FloatArray();
            } else {
                this.columnSizes.clear();
            }
            FloatArray floatArray = this.columnSizes;
            float f = this.space;
            float f2 = this.wrapSpace;
            float f3 = this.padTop + this.padBottom;
            float height2 = getHeight() - f3;
            float f4 = 0.0f;
            float f5 = 0.0f;
            float f6 = 0.0f;
            int i2 = 0;
            int i3 = 1;
            if (this.reverse) {
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
                    this.prefHeight = Math.max(this.prefHeight, f5 + f3);
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
            this.prefHeight = Math.max(this.prefHeight, f5 + f3);
            if (f4 > 0.0f) {
                f4 += f2;
            }
            this.prefWidth = Math.max(this.prefWidth, f4 + f6);
        } else {
            this.prefHeight = this.padTop + this.padBottom + (this.space * (i - 1));
            for (int i4 = 0; i4 < i; i4++) {
                Actor actor2 = children.get(i4);
                if (actor2 instanceof Layout) {
                    Layout layout2 = (Layout) actor2;
                    this.prefWidth = Math.max(this.prefWidth, layout2.getPrefWidth());
                    this.prefHeight += layout2.getPrefHeight();
                } else {
                    this.prefWidth = Math.max(this.prefWidth, actor2.getWidth());
                    this.prefHeight += actor2.getHeight();
                }
            }
        }
        this.prefWidth += this.padLeft + this.padRight;
        if (this.round) {
            this.prefWidth = (float) Math.ceil(this.prefWidth);
            this.prefHeight = (float) Math.ceil(this.prefHeight);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        float width;
        float width2;
        float height;
        if (this.sizeInvalid) {
            computeSize();
        }
        if (this.wrap) {
            layoutWrapped();
            return;
        }
        boolean z = this.round;
        int i = this.align;
        float f = this.space;
        float f2 = this.padLeft;
        float f3 = this.fill;
        float width3 = ((this.expand ? getWidth() : this.prefWidth) - f2) - this.padRight;
        float f4 = (this.prefHeight - this.padTop) + f;
        if ((i & 2) != 0) {
            f4 += getHeight() - this.prefHeight;
        } else if ((i & 4) == 0) {
            f4 += (getHeight() - this.prefHeight) / 2.0f;
        }
        if ((i & 8) != 0) {
            width = f2;
        } else if ((i & 16) != 0) {
            width = (getWidth() - this.padRight) - width3;
        } else {
            width = f2 + ((((getWidth() - f2) - this.padRight) - width3) / 2.0f);
        }
        int i2 = this.columnAlign;
        SnapshotArray<Actor> children = getChildren();
        int i3 = 0;
        int i4 = children.size;
        int i5 = 1;
        if (this.reverse) {
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
    private void layoutWrapped() {
        float width;
        float height;
        float prefWidth = getPrefWidth();
        if (prefWidth != this.lastPrefWidth) {
            this.lastPrefWidth = prefWidth;
            invalidateHierarchy();
        }
        int i = this.align;
        boolean z = this.round;
        float f = this.space;
        float f2 = this.padLeft;
        float f3 = this.fill;
        float f4 = this.wrapSpace;
        float f5 = (this.prefHeight - this.padTop) - this.padBottom;
        float f6 = f2;
        float height2 = getHeight();
        float f7 = (this.prefHeight - this.padTop) + f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        if ((i & 16) != 0) {
            f6 = f2 + (getWidth() - prefWidth);
        } else if ((i & 8) == 0) {
            f6 = f2 + ((getWidth() - prefWidth) / 2.0f);
        }
        if ((i & 2) != 0) {
            f7 += height2 - this.prefHeight;
        } else if ((i & 4) == 0) {
            f7 += (height2 - this.prefHeight) / 2.0f;
        }
        float f10 = height2 - this.padTop;
        int i2 = this.columnAlign;
        FloatArray floatArray = this.columnSizes;
        SnapshotArray<Actor> children = getChildren();
        int i3 = 0;
        int i4 = children.size;
        int i5 = 1;
        if (this.reverse) {
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
            if ((f8 - height) - f < this.padBottom || i6 == 0) {
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

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.wrap) {
            return 0.0f;
        }
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefHeight;
    }

    public int getColumns() {
        if (this.wrap) {
            return this.columnSizes.size >> 1;
        }
        return 1;
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public VerticalGroup reverse() {
        this.reverse = true;
        return this;
    }

    public VerticalGroup reverse(boolean z) {
        this.reverse = z;
        return this;
    }

    public boolean getReverse() {
        return this.reverse;
    }

    public VerticalGroup space(float f) {
        this.space = f;
        return this;
    }

    public float getSpace() {
        return this.space;
    }

    public VerticalGroup wrapSpace(float f) {
        this.wrapSpace = f;
        return this;
    }

    public float getWrapSpace() {
        return this.wrapSpace;
    }

    public VerticalGroup pad(float f) {
        this.padTop = f;
        this.padLeft = f;
        this.padBottom = f;
        this.padRight = f;
        return this;
    }

    public VerticalGroup pad(float f, float f2, float f3, float f4) {
        this.padTop = f;
        this.padLeft = f2;
        this.padBottom = f3;
        this.padRight = f4;
        return this;
    }

    public VerticalGroup padTop(float f) {
        this.padTop = f;
        return this;
    }

    public VerticalGroup padLeft(float f) {
        this.padLeft = f;
        return this;
    }

    public VerticalGroup padBottom(float f) {
        this.padBottom = f;
        return this;
    }

    public VerticalGroup padRight(float f) {
        this.padRight = f;
        return this;
    }

    public float getPadTop() {
        return this.padTop;
    }

    public float getPadLeft() {
        return this.padLeft;
    }

    public float getPadBottom() {
        return this.padBottom;
    }

    public float getPadRight() {
        return this.padRight;
    }

    public VerticalGroup align(int i) {
        this.align = i;
        return this;
    }

    public VerticalGroup center() {
        this.align = 1;
        return this;
    }

    public VerticalGroup top() {
        this.align |= 2;
        this.align &= -5;
        return this;
    }

    public VerticalGroup left() {
        this.align |= 8;
        this.align &= -17;
        return this;
    }

    public VerticalGroup bottom() {
        this.align |= 4;
        this.align &= -3;
        return this;
    }

    public VerticalGroup right() {
        this.align |= 16;
        this.align &= -9;
        return this;
    }

    public int getAlign() {
        return this.align;
    }

    public VerticalGroup fill() {
        this.fill = 1.0f;
        return this;
    }

    public VerticalGroup fill(float f) {
        this.fill = f;
        return this;
    }

    public float getFill() {
        return this.fill;
    }

    public VerticalGroup expand() {
        this.expand = true;
        return this;
    }

    public VerticalGroup expand(boolean z) {
        this.expand = z;
        return this;
    }

    public boolean getExpand() {
        return this.expand;
    }

    public VerticalGroup grow() {
        this.expand = true;
        this.fill = 1.0f;
        return this;
    }

    public VerticalGroup wrap() {
        this.wrap = true;
        return this;
    }

    public VerticalGroup wrap(boolean z) {
        this.wrap = z;
        return this;
    }

    public boolean getWrap() {
        return this.wrap;
    }

    public VerticalGroup columnAlign(int i) {
        this.columnAlign = i;
        return this;
    }

    public VerticalGroup columnCenter() {
        this.columnAlign = 1;
        return this;
    }

    public VerticalGroup columnTop() {
        this.columnAlign |= 2;
        this.columnAlign &= -5;
        return this;
    }

    public VerticalGroup columnLeft() {
        this.columnAlign |= 8;
        this.columnAlign &= -17;
        return this;
    }

    public VerticalGroup columnBottom() {
        this.columnAlign |= 4;
        this.columnAlign &= -3;
        return this;
    }

    public VerticalGroup columnRight() {
        this.columnAlign |= 16;
        this.columnAlign &= -9;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebugBounds(ShapeRenderer shapeRenderer) {
        super.drawDebugBounds(shapeRenderer);
        if (getDebug()) {
            shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            if (getStage() != null) {
                shapeRenderer.setColor(getStage().getDebugColor());
            }
            shapeRenderer.rect(getX() + this.padLeft, getY() + this.padBottom, getOriginX(), getOriginY(), (getWidth() - this.padLeft) - this.padRight, (getHeight() - this.padBottom) - this.padTop, getScaleX(), getScaleY(), getRotation());
        }
    }
}
