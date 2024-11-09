package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.SnapshotArray;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/HorizontalGroup.class */
public class HorizontalGroup extends WidgetGroup {
    private float prefWidth;
    private float prefHeight;
    private float lastPrefHeight;
    private FloatArray rowSizes;
    private int rowAlign;
    private boolean reverse;
    private boolean wrap;
    private boolean wrapReverse;
    private boolean expand;
    private float space;
    private float wrapSpace;
    private float fill;
    private float padTop;
    private float padLeft;
    private float padBottom;
    private float padRight;
    private boolean sizeInvalid = true;
    private int align = 8;
    private boolean round = true;

    public HorizontalGroup() {
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
        this.prefHeight = 0.0f;
        if (this.wrap) {
            this.prefWidth = 0.0f;
            if (this.rowSizes == null) {
                this.rowSizes = new FloatArray();
            } else {
                this.rowSizes.clear();
            }
            FloatArray floatArray = this.rowSizes;
            float f = this.space;
            float f2 = this.wrapSpace;
            float f3 = this.padLeft + this.padRight;
            float width2 = getWidth() - f3;
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
                    this.prefWidth = Math.max(this.prefWidth, f4 + f3);
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
            this.prefWidth = Math.max(this.prefWidth, f4 + f3);
            if (f5 > 0.0f) {
                f5 += f2;
            }
            this.prefHeight = Math.max(this.prefHeight, f5 + f6);
        } else {
            this.prefWidth = this.padLeft + this.padRight + (this.space * (i - 1));
            for (int i4 = 0; i4 < i; i4++) {
                Actor actor2 = children.get(i4);
                if (actor2 instanceof Layout) {
                    Layout layout2 = (Layout) actor2;
                    this.prefWidth += layout2.getPrefWidth();
                    this.prefHeight = Math.max(this.prefHeight, layout2.getPrefHeight());
                } else {
                    this.prefWidth += actor2.getWidth();
                    this.prefHeight = Math.max(this.prefHeight, actor2.getHeight());
                }
            }
        }
        this.prefHeight += this.padTop + this.padBottom;
        if (this.round) {
            this.prefWidth = (float) Math.ceil(this.prefWidth);
            this.prefHeight = (float) Math.ceil(this.prefHeight);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        float height;
        float width;
        float height2;
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
        float f2 = this.padBottom;
        float f3 = this.fill;
        float height3 = ((this.expand ? getHeight() : this.prefHeight) - this.padTop) - f2;
        float f4 = this.padLeft;
        if ((i & 16) != 0) {
            f4 += getWidth() - this.prefWidth;
        } else if ((i & 8) == 0) {
            f4 += (getWidth() - this.prefWidth) / 2.0f;
        }
        if ((i & 4) != 0) {
            height = f2;
        } else if ((i & 2) != 0) {
            height = (getHeight() - this.padTop) - height3;
        } else {
            height = f2 + ((((getHeight() - f2) - this.padTop) - height3) / 2.0f);
        }
        int i2 = this.rowAlign;
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
    private void layoutWrapped() {
        float width;
        float height;
        float prefHeight = getPrefHeight();
        if (prefHeight != this.lastPrefHeight) {
            this.lastPrefHeight = prefHeight;
            invalidateHierarchy();
        }
        int i = this.align;
        boolean z = this.round;
        float f = this.space;
        float f2 = this.fill;
        float f3 = this.wrapSpace;
        float f4 = (this.prefWidth - this.padLeft) - this.padRight;
        float f5 = prefHeight - this.padTop;
        float width2 = getWidth();
        float f6 = this.padLeft;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = -1.0f;
        if ((i & 2) != 0) {
            f5 += getHeight() - prefHeight;
        } else if ((i & 4) == 0) {
            f5 += (getHeight() - prefHeight) / 2.0f;
        }
        if (this.wrapReverse) {
            f5 -= prefHeight + this.rowSizes.get(1);
            f9 = 1.0f;
        }
        if ((i & 16) != 0) {
            f6 += width2 - this.prefWidth;
        } else if ((i & 8) == 0) {
            f6 += (width2 - this.prefWidth) / 2.0f;
        }
        float f10 = width2 - this.padRight;
        int i2 = this.rowAlign;
        FloatArray floatArray = this.rowSizes;
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

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.wrap) {
            return 0.0f;
        }
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.sizeInvalid) {
            computeSize();
        }
        return this.prefHeight;
    }

    public int getRows() {
        if (this.wrap) {
            return this.rowSizes.size >> 1;
        }
        return 1;
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public HorizontalGroup reverse() {
        this.reverse = true;
        return this;
    }

    public HorizontalGroup reverse(boolean z) {
        this.reverse = z;
        return this;
    }

    public boolean getReverse() {
        return this.reverse;
    }

    public HorizontalGroup wrapReverse() {
        this.wrapReverse = true;
        return this;
    }

    public HorizontalGroup wrapReverse(boolean z) {
        this.wrapReverse = z;
        return this;
    }

    public boolean getWrapReverse() {
        return this.wrapReverse;
    }

    public HorizontalGroup space(float f) {
        this.space = f;
        return this;
    }

    public float getSpace() {
        return this.space;
    }

    public HorizontalGroup wrapSpace(float f) {
        this.wrapSpace = f;
        return this;
    }

    public float getWrapSpace() {
        return this.wrapSpace;
    }

    public HorizontalGroup pad(float f) {
        this.padTop = f;
        this.padLeft = f;
        this.padBottom = f;
        this.padRight = f;
        return this;
    }

    public HorizontalGroup pad(float f, float f2, float f3, float f4) {
        this.padTop = f;
        this.padLeft = f2;
        this.padBottom = f3;
        this.padRight = f4;
        return this;
    }

    public HorizontalGroup padTop(float f) {
        this.padTop = f;
        return this;
    }

    public HorizontalGroup padLeft(float f) {
        this.padLeft = f;
        return this;
    }

    public HorizontalGroup padBottom(float f) {
        this.padBottom = f;
        return this;
    }

    public HorizontalGroup padRight(float f) {
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

    public HorizontalGroup align(int i) {
        this.align = i;
        return this;
    }

    public HorizontalGroup center() {
        this.align = 1;
        return this;
    }

    public HorizontalGroup top() {
        this.align |= 2;
        this.align &= -5;
        return this;
    }

    public HorizontalGroup left() {
        this.align |= 8;
        this.align &= -17;
        return this;
    }

    public HorizontalGroup bottom() {
        this.align |= 4;
        this.align &= -3;
        return this;
    }

    public HorizontalGroup right() {
        this.align |= 16;
        this.align &= -9;
        return this;
    }

    public int getAlign() {
        return this.align;
    }

    public HorizontalGroup fill() {
        this.fill = 1.0f;
        return this;
    }

    public HorizontalGroup fill(float f) {
        this.fill = f;
        return this;
    }

    public float getFill() {
        return this.fill;
    }

    public HorizontalGroup expand() {
        this.expand = true;
        return this;
    }

    public HorizontalGroup expand(boolean z) {
        this.expand = z;
        return this;
    }

    public boolean getExpand() {
        return this.expand;
    }

    public HorizontalGroup grow() {
        this.expand = true;
        this.fill = 1.0f;
        return this;
    }

    public HorizontalGroup wrap() {
        this.wrap = true;
        return this;
    }

    public HorizontalGroup wrap(boolean z) {
        this.wrap = z;
        return this;
    }

    public boolean getWrap() {
        return this.wrap;
    }

    public HorizontalGroup rowAlign(int i) {
        this.rowAlign = i;
        return this;
    }

    public HorizontalGroup rowCenter() {
        this.rowAlign = 1;
        return this;
    }

    public HorizontalGroup rowTop() {
        this.rowAlign |= 2;
        this.rowAlign &= -5;
        return this;
    }

    public HorizontalGroup rowLeft() {
        this.rowAlign |= 8;
        this.rowAlign &= -17;
        return this;
    }

    public HorizontalGroup rowBottom() {
        this.rowAlign |= 4;
        this.rowAlign &= -3;
        return this;
    }

    public HorizontalGroup rowRight() {
        this.rowAlign |= 16;
        this.rowAlign &= -9;
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
