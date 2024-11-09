package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/SplitPane.class */
public class SplitPane extends WidgetGroup {
    SplitPaneStyle style;

    @Null
    private Actor firstWidget;

    @Null
    private Actor secondWidget;
    boolean vertical;
    float splitAmount;
    float minAmount;
    float maxAmount;
    private final Rectangle firstWidgetBounds;
    private final Rectangle secondWidgetBounds;
    final Rectangle handleBounds;
    boolean cursorOverHandle;
    private final Rectangle tempScissors;
    Vector2 lastPoint;
    Vector2 handlePosition;

    public SplitPane(@Null Actor actor, @Null Actor actor2, boolean z, Skin skin) {
        this(actor, actor2, z, skin, "default-" + (z ? "vertical" : "horizontal"));
    }

    public SplitPane(@Null Actor actor, @Null Actor actor2, boolean z, Skin skin, String str) {
        this(actor, actor2, z, (SplitPaneStyle) skin.get(str, SplitPaneStyle.class));
    }

    public SplitPane(@Null Actor actor, @Null Actor actor2, boolean z, SplitPaneStyle splitPaneStyle) {
        this.splitAmount = 0.5f;
        this.maxAmount = 1.0f;
        this.firstWidgetBounds = new Rectangle();
        this.secondWidgetBounds = new Rectangle();
        this.handleBounds = new Rectangle();
        this.tempScissors = new Rectangle();
        this.lastPoint = new Vector2();
        this.handlePosition = new Vector2();
        this.vertical = z;
        setStyle(splitPaneStyle);
        setFirstWidget(actor);
        setSecondWidget(actor2);
        setSize(getPrefWidth(), getPrefHeight());
        initialize();
    }

    private void initialize() {
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SplitPane.1
            int draggingPointer = -1;

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (this.draggingPointer != -1) {
                    return false;
                }
                if ((i != 0 || i2 == 0) && SplitPane.this.handleBounds.contains(f, f2)) {
                    this.draggingPointer = i;
                    SplitPane.this.lastPoint.set(f, f2);
                    SplitPane.this.handlePosition.set(SplitPane.this.handleBounds.x, SplitPane.this.handleBounds.y);
                    return true;
                }
                return false;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i == this.draggingPointer) {
                    this.draggingPointer = -1;
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                if (i != this.draggingPointer) {
                    return;
                }
                Drawable drawable = SplitPane.this.style.handle;
                if (!SplitPane.this.vertical) {
                    float f3 = f - SplitPane.this.lastPoint.x;
                    float width = SplitPane.this.getWidth() - drawable.getMinWidth();
                    float f4 = SplitPane.this.handlePosition.x + f3;
                    SplitPane.this.handlePosition.x = f4;
                    SplitPane.this.splitAmount = Math.min(width, Math.max(0.0f, f4)) / width;
                    SplitPane.this.lastPoint.set(f, f2);
                } else {
                    float f5 = f2 - SplitPane.this.lastPoint.y;
                    float height = SplitPane.this.getHeight() - drawable.getMinHeight();
                    float f6 = SplitPane.this.handlePosition.y + f5;
                    SplitPane.this.handlePosition.y = f6;
                    SplitPane.this.splitAmount = 1.0f - (Math.min(height, Math.max(0.0f, f6)) / height);
                    SplitPane.this.lastPoint.set(f, f2);
                }
                SplitPane.this.invalidate();
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                SplitPane.this.cursorOverHandle = SplitPane.this.handleBounds.contains(f, f2);
                return false;
            }
        });
    }

    public void setStyle(SplitPaneStyle splitPaneStyle) {
        this.style = splitPaneStyle;
        invalidateHierarchy();
    }

    public SplitPaneStyle getStyle() {
        return this.style;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        clampSplitAmount();
        if (!this.vertical) {
            calculateHorizBoundsAndPositions();
        } else {
            calculateVertBoundsAndPositions();
        }
        Actor actor = this.firstWidget;
        if (actor != 0) {
            Rectangle rectangle = this.firstWidgetBounds;
            actor.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (actor instanceof Layout) {
                ((Layout) actor).validate();
            }
        }
        Actor actor2 = this.secondWidget;
        if (actor2 != 0) {
            Rectangle rectangle2 = this.secondWidgetBounds;
            actor2.setBounds(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            if (actor2 instanceof Layout) {
                ((Layout) actor2).validate();
            }
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        float prefWidth;
        float prefWidth2;
        if (this.firstWidget == null) {
            prefWidth = 0.0f;
        } else {
            prefWidth = this.firstWidget instanceof Layout ? ((Layout) this.firstWidget).getPrefWidth() : this.firstWidget.getWidth();
        }
        float f = prefWidth;
        if (this.secondWidget == null) {
            prefWidth2 = 0.0f;
        } else {
            prefWidth2 = this.secondWidget instanceof Layout ? ((Layout) this.secondWidget).getPrefWidth() : this.secondWidget.getWidth();
        }
        float f2 = prefWidth2;
        return this.vertical ? Math.max(f, f2) : f + this.style.handle.getMinWidth() + f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        float prefHeight;
        float prefHeight2;
        if (this.firstWidget == null) {
            prefHeight = 0.0f;
        } else {
            prefHeight = this.firstWidget instanceof Layout ? ((Layout) this.firstWidget).getPrefHeight() : this.firstWidget.getHeight();
        }
        float f = prefHeight;
        if (this.secondWidget == null) {
            prefHeight2 = 0.0f;
        } else {
            prefHeight2 = this.secondWidget instanceof Layout ? ((Layout) this.secondWidget).getPrefHeight() : this.secondWidget.getHeight();
        }
        float f2 = prefHeight2;
        return !this.vertical ? Math.max(f, f2) : f + this.style.handle.getMinHeight() + f2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        float minWidth = this.firstWidget instanceof Layout ? ((Layout) this.firstWidget).getMinWidth() : 0.0f;
        float minWidth2 = this.secondWidget instanceof Layout ? ((Layout) this.secondWidget).getMinWidth() : 0.0f;
        return this.vertical ? Math.max(minWidth, minWidth2) : minWidth + this.style.handle.getMinWidth() + minWidth2;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        float minHeight = this.firstWidget instanceof Layout ? ((Layout) this.firstWidget).getMinHeight() : 0.0f;
        float minHeight2 = this.secondWidget instanceof Layout ? ((Layout) this.secondWidget).getMinHeight() : 0.0f;
        return !this.vertical ? Math.max(minHeight, minHeight2) : minHeight + this.style.handle.getMinHeight() + minHeight2;
    }

    public void setVertical(boolean z) {
        if (this.vertical == z) {
            return;
        }
        this.vertical = z;
        invalidateHierarchy();
    }

    public boolean isVertical() {
        return this.vertical;
    }

    private void calculateHorizBoundsAndPositions() {
        Drawable drawable = this.style.handle;
        float height = getHeight();
        float width = getWidth() - drawable.getMinWidth();
        float f = (int) (width * this.splitAmount);
        float f2 = width - f;
        float minWidth = drawable.getMinWidth();
        this.firstWidgetBounds.set(0.0f, 0.0f, f, height);
        this.secondWidgetBounds.set(f + minWidth, 0.0f, f2, height);
        this.handleBounds.set(f, 0.0f, minWidth, height);
    }

    private void calculateVertBoundsAndPositions() {
        Drawable drawable = this.style.handle;
        float width = getWidth();
        float height = getHeight();
        float minHeight = height - drawable.getMinHeight();
        float f = (int) (minHeight * this.splitAmount);
        float f2 = minHeight - f;
        float minHeight2 = drawable.getMinHeight();
        this.firstWidgetBounds.set(0.0f, height - f, width, f);
        this.secondWidgetBounds.set(0.0f, 0.0f, width, f2);
        this.handleBounds.set(0.0f, f2, width, minHeight2);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        Stage stage = getStage();
        if (stage == null) {
            return;
        }
        validate();
        Color color = getColor();
        float f2 = color.f889a * f;
        applyTransform(batch, computeTransform());
        if (this.firstWidget != null && this.firstWidget.isVisible()) {
            batch.flush();
            stage.calculateScissors(this.firstWidgetBounds, this.tempScissors);
            if (ScissorStack.pushScissors(this.tempScissors)) {
                this.firstWidget.draw(batch, f2);
                batch.flush();
                ScissorStack.popScissors();
            }
        }
        if (this.secondWidget != null && this.secondWidget.isVisible()) {
            batch.flush();
            stage.calculateScissors(this.secondWidgetBounds, this.tempScissors);
            if (ScissorStack.pushScissors(this.tempScissors)) {
                this.secondWidget.draw(batch, f2);
                batch.flush();
                ScissorStack.popScissors();
            }
        }
        batch.setColor(color.r, color.g, color.f888b, f2);
        this.style.handle.draw(batch, this.handleBounds.x, this.handleBounds.y, this.handleBounds.width, this.handleBounds.height);
        resetTransform(batch);
    }

    public void setSplitAmount(float f) {
        this.splitAmount = f;
        invalidate();
    }

    public float getSplitAmount() {
        return this.splitAmount;
    }

    protected void clampSplitAmount() {
        float f = this.minAmount;
        float f2 = this.maxAmount;
        if (this.vertical) {
            float height = getHeight() - this.style.handle.getMinHeight();
            if (this.firstWidget instanceof Layout) {
                f = Math.max(f, Math.min(((Layout) this.firstWidget).getMinHeight() / height, 1.0f));
            }
            if (this.secondWidget instanceof Layout) {
                f2 = Math.min(f2, 1.0f - Math.min(((Layout) this.secondWidget).getMinHeight() / height, 1.0f));
            }
        } else {
            float width = getWidth() - this.style.handle.getMinWidth();
            if (this.firstWidget instanceof Layout) {
                f = Math.max(f, Math.min(((Layout) this.firstWidget).getMinWidth() / width, 1.0f));
            }
            if (this.secondWidget instanceof Layout) {
                f2 = Math.min(f2, 1.0f - Math.min(((Layout) this.secondWidget).getMinWidth() / width, 1.0f));
            }
        }
        if (f > f2) {
            this.splitAmount = 0.5f * (f + f2);
        } else {
            this.splitAmount = Math.max(Math.min(this.splitAmount, f2), f);
        }
    }

    public float getMinSplitAmount() {
        return this.minAmount;
    }

    public void setMinSplitAmount(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new GdxRuntimeException("minAmount has to be >= 0 and <= 1");
        }
        this.minAmount = f;
    }

    public float getMaxSplitAmount() {
        return this.maxAmount;
    }

    public void setMaxSplitAmount(float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new GdxRuntimeException("maxAmount has to be >= 0 and <= 1");
        }
        this.maxAmount = f;
    }

    public void setFirstWidget(@Null Actor actor) {
        if (this.firstWidget != null) {
            super.removeActor(this.firstWidget);
        }
        this.firstWidget = actor;
        if (actor != null) {
            super.addActor(actor);
        }
        invalidate();
    }

    public void setSecondWidget(@Null Actor actor) {
        if (this.secondWidget != null) {
            super.removeActor(this.secondWidget);
        }
        this.secondWidget = actor;
        if (actor != null) {
            super.addActor(actor);
        }
        invalidate();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void addActorAt(int i, Actor actor) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use SplitPane#setWidget.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor == this.firstWidget) {
            setFirstWidget(null);
            return true;
        }
        if (actor == this.secondWidget) {
            setSecondWidget(null);
            return true;
        }
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor, boolean z) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor == this.firstWidget) {
            super.removeActor(actor, z);
            this.firstWidget = null;
            invalidate();
            return true;
        }
        if (actor == this.secondWidget) {
            super.removeActor(actor, z);
            this.secondWidget = null;
            invalidate();
            return true;
        }
        return false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int i, boolean z) {
        Actor removeActorAt = super.removeActorAt(i, z);
        if (removeActorAt == this.firstWidget) {
            super.removeActor(removeActorAt, z);
            this.firstWidget = null;
            invalidate();
        } else if (removeActorAt == this.secondWidget) {
            super.removeActor(removeActorAt, z);
            this.secondWidget = null;
            invalidate();
        }
        return removeActorAt;
    }

    public boolean isCursorOverHandle() {
        return this.cursorOverHandle;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/SplitPane$SplitPaneStyle.class */
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
