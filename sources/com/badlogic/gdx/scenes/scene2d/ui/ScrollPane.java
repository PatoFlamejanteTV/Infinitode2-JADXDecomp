package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/ScrollPane.class */
public class ScrollPane extends WidgetGroup {
    private ScrollPaneStyle style;
    private Actor actor;
    final Rectangle actorArea;
    final Rectangle hScrollBounds;
    final Rectangle hKnobBounds;
    final Rectangle vScrollBounds;
    final Rectangle vKnobBounds;
    private final Rectangle actorCullingArea;
    private ActorGestureListener flickScrollListener;
    boolean scrollX;
    boolean scrollY;
    boolean vScrollOnRight;
    boolean hScrollOnBottom;
    float amountX;
    float amountY;
    float visualAmountX;
    float visualAmountY;
    float maxX;
    float maxY;
    boolean touchScrollH;
    boolean touchScrollV;
    final Vector2 lastPoint;
    boolean fadeScrollBars;
    boolean smoothScrolling;
    boolean scrollBarTouch;
    float fadeAlpha;
    float fadeAlphaSeconds;
    float fadeDelay;
    float fadeDelaySeconds;
    boolean cancelTouchFocus;
    boolean flickScroll;
    float flingTime;
    float flingTimer;
    float velocityX;
    float velocityY;
    private boolean overscrollX;
    private boolean overscrollY;
    private float overscrollDistance;
    private float overscrollSpeedMin;
    private float overscrollSpeedMax;
    private boolean forceScrollX;
    private boolean forceScrollY;
    boolean disableX;
    boolean disableY;
    private boolean clamp;
    private boolean scrollbarsOnTop;
    private boolean variableSizeKnobs;
    int draggingPointer;

    public ScrollPane(@Null Actor actor) {
        this(actor, new ScrollPaneStyle());
    }

    public ScrollPane(@Null Actor actor, Skin skin) {
        this(actor, (ScrollPaneStyle) skin.get(ScrollPaneStyle.class));
    }

    public ScrollPane(@Null Actor actor, Skin skin, String str) {
        this(actor, (ScrollPaneStyle) skin.get(str, ScrollPaneStyle.class));
    }

    public ScrollPane(@Null Actor actor, ScrollPaneStyle scrollPaneStyle) {
        this.actorArea = new Rectangle();
        this.hScrollBounds = new Rectangle();
        this.hKnobBounds = new Rectangle();
        this.vScrollBounds = new Rectangle();
        this.vKnobBounds = new Rectangle();
        this.actorCullingArea = new Rectangle();
        this.vScrollOnRight = true;
        this.hScrollOnBottom = true;
        this.lastPoint = new Vector2();
        this.fadeScrollBars = true;
        this.smoothScrolling = true;
        this.scrollBarTouch = true;
        this.fadeAlphaSeconds = 1.0f;
        this.fadeDelaySeconds = 1.0f;
        this.cancelTouchFocus = true;
        this.flickScroll = true;
        this.flingTime = 1.0f;
        this.overscrollX = true;
        this.overscrollY = true;
        this.overscrollDistance = 50.0f;
        this.overscrollSpeedMin = 30.0f;
        this.overscrollSpeedMax = 200.0f;
        this.clamp = true;
        this.variableSizeKnobs = true;
        this.draggingPointer = -1;
        if (scrollPaneStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = scrollPaneStyle;
        setActor(actor);
        setSize(150.0f, 150.0f);
        addCaptureListener();
        this.flickScrollListener = getFlickScrollListener();
        addListener(this.flickScrollListener);
        addScrollListener();
    }

    protected void addCaptureListener() {
        addCaptureListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.1
            private float handlePosition;

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (ScrollPane.this.draggingPointer != -1) {
                    return false;
                }
                if (i == 0 && i2 != 0) {
                    return false;
                }
                if (ScrollPane.this.getStage() != null) {
                    ScrollPane.this.getStage().setScrollFocus(ScrollPane.this);
                }
                if (!ScrollPane.this.flickScroll) {
                    ScrollPane.this.setScrollbarsVisible(true);
                }
                if (ScrollPane.this.fadeAlpha == 0.0f) {
                    return false;
                }
                if (ScrollPane.this.scrollBarTouch && ScrollPane.this.scrollX && ScrollPane.this.hScrollBounds.contains(f, f2)) {
                    inputEvent.stop();
                    ScrollPane.this.setScrollbarsVisible(true);
                    if (ScrollPane.this.hKnobBounds.contains(f, f2)) {
                        ScrollPane.this.lastPoint.set(f, f2);
                        this.handlePosition = ScrollPane.this.hKnobBounds.x;
                        ScrollPane.this.touchScrollH = true;
                        ScrollPane.this.draggingPointer = i;
                        return true;
                    }
                    ScrollPane.this.setScrollX(ScrollPane.this.amountX + (ScrollPane.this.actorArea.width * (f < ScrollPane.this.hKnobBounds.x ? -1 : 1)));
                    return true;
                }
                if (ScrollPane.this.scrollBarTouch && ScrollPane.this.scrollY && ScrollPane.this.vScrollBounds.contains(f, f2)) {
                    inputEvent.stop();
                    ScrollPane.this.setScrollbarsVisible(true);
                    if (ScrollPane.this.vKnobBounds.contains(f, f2)) {
                        ScrollPane.this.lastPoint.set(f, f2);
                        this.handlePosition = ScrollPane.this.vKnobBounds.y;
                        ScrollPane.this.touchScrollV = true;
                        ScrollPane.this.draggingPointer = i;
                        return true;
                    }
                    ScrollPane.this.setScrollY(ScrollPane.this.amountY + (ScrollPane.this.actorArea.height * (f2 < ScrollPane.this.vKnobBounds.y ? 1 : -1)));
                    return true;
                }
                return false;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i != ScrollPane.this.draggingPointer) {
                    return;
                }
                ScrollPane.this.cancel();
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                if (i != ScrollPane.this.draggingPointer) {
                    return;
                }
                if (ScrollPane.this.touchScrollH) {
                    float f3 = this.handlePosition + (f - ScrollPane.this.lastPoint.x);
                    this.handlePosition = f3;
                    float min = Math.min((ScrollPane.this.hScrollBounds.x + ScrollPane.this.hScrollBounds.width) - ScrollPane.this.hKnobBounds.width, Math.max(ScrollPane.this.hScrollBounds.x, f3));
                    float f4 = ScrollPane.this.hScrollBounds.width - ScrollPane.this.hKnobBounds.width;
                    if (f4 != 0.0f) {
                        ScrollPane.this.setScrollPercentX((min - ScrollPane.this.hScrollBounds.x) / f4);
                    }
                    ScrollPane.this.lastPoint.set(f, f2);
                    return;
                }
                if (ScrollPane.this.touchScrollV) {
                    float f5 = this.handlePosition + (f2 - ScrollPane.this.lastPoint.y);
                    this.handlePosition = f5;
                    float min2 = Math.min((ScrollPane.this.vScrollBounds.y + ScrollPane.this.vScrollBounds.height) - ScrollPane.this.vKnobBounds.height, Math.max(ScrollPane.this.vScrollBounds.y, f5));
                    float f6 = ScrollPane.this.vScrollBounds.height - ScrollPane.this.vKnobBounds.height;
                    if (f6 != 0.0f) {
                        ScrollPane.this.setScrollPercentY(1.0f - ((min2 - ScrollPane.this.vScrollBounds.y) / f6));
                    }
                    ScrollPane.this.lastPoint.set(f, f2);
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                if (!ScrollPane.this.flickScroll) {
                    ScrollPane.this.setScrollbarsVisible(true);
                    return false;
                }
                return false;
            }
        });
    }

    protected ActorGestureListener getFlickScrollListener() {
        return new ActorGestureListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.2
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
            public void pan(InputEvent inputEvent, float f, float f2, float f3, float f4) {
                ScrollPane.this.setScrollbarsVisible(true);
                if (!ScrollPane.this.scrollX) {
                    f3 = 0.0f;
                }
                if (!ScrollPane.this.scrollY) {
                    f4 = 0.0f;
                }
                ScrollPane.this.amountX -= f3;
                ScrollPane.this.amountY += f4;
                ScrollPane.this.clamp();
                if (ScrollPane.this.cancelTouchFocus) {
                    if (f3 != 0.0f || f4 != 0.0f) {
                        ScrollPane.this.cancelTouchFocus();
                    }
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
            public void fling(InputEvent inputEvent, float f, float f2, int i) {
                float f3 = (Math.abs(f) <= 150.0f || !ScrollPane.this.scrollX) ? 0.0f : f;
                float f4 = (Math.abs(f2) <= 150.0f || !ScrollPane.this.scrollY) ? 0.0f : -f2;
                if (f3 != 0.0f || f4 != 0.0f) {
                    if (ScrollPane.this.cancelTouchFocus) {
                        ScrollPane.this.cancelTouchFocus();
                    }
                    ScrollPane.this.fling(ScrollPane.this.flingTime, f3, f4);
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener, com.badlogic.gdx.scenes.scene2d.EventListener
            public boolean handle(Event event) {
                if (super.handle(event)) {
                    if (((InputEvent) event).getType() == InputEvent.Type.touchDown) {
                        ScrollPane.this.flingTimer = 0.0f;
                        return true;
                    }
                    return true;
                }
                if ((event instanceof InputEvent) && ((InputEvent) event).isTouchFocusCancel()) {
                    ScrollPane.this.cancel();
                    return false;
                }
                return false;
            }
        };
    }

    protected void addScrollListener() {
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.3
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean scrolled(InputEvent inputEvent, float f, float f2, float f3, float f4) {
                inputEvent.cancel();
                ScrollPane.this.setScrollbarsVisible(true);
                if (ScrollPane.this.scrollY || ScrollPane.this.scrollX) {
                    if (ScrollPane.this.scrollY) {
                        if (!ScrollPane.this.scrollX && f4 == 0.0f) {
                            f4 = f3;
                        }
                    } else if (ScrollPane.this.scrollX && f3 == 0.0f) {
                        f3 = f4;
                    }
                    ScrollPane.this.setScrollY(ScrollPane.this.amountY + (ScrollPane.this.getMouseWheelY() * f4));
                    ScrollPane.this.setScrollX(ScrollPane.this.amountX + (ScrollPane.this.getMouseWheelX() * f3));
                    return true;
                }
                return false;
            }
        });
    }

    public void setScrollbarsVisible(boolean z) {
        if (z) {
            this.fadeAlpha = this.fadeAlphaSeconds;
            this.fadeDelay = this.fadeDelaySeconds;
        } else {
            this.fadeAlpha = 0.0f;
            this.fadeDelay = 0.0f;
        }
    }

    public void cancelTouchFocus() {
        Stage stage = getStage();
        if (stage != null) {
            stage.cancelTouchFocusExcept(this.flickScrollListener, this);
        }
    }

    public void cancel() {
        this.draggingPointer = -1;
        this.touchScrollH = false;
        this.touchScrollV = false;
        this.flickScrollListener.getGestureDetector().cancel();
    }

    void clamp() {
        if (this.clamp) {
            scrollX(this.overscrollX ? MathUtils.clamp(this.amountX, -this.overscrollDistance, this.maxX + this.overscrollDistance) : MathUtils.clamp(this.amountX, 0.0f, this.maxX));
            scrollY(this.overscrollY ? MathUtils.clamp(this.amountY, -this.overscrollDistance, this.maxY + this.overscrollDistance) : MathUtils.clamp(this.amountY, 0.0f, this.maxY));
        }
    }

    public void setStyle(ScrollPaneStyle scrollPaneStyle) {
        if (scrollPaneStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = scrollPaneStyle;
        invalidateHierarchy();
    }

    public ScrollPaneStyle getStyle() {
        return this.style;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        Stage stage;
        super.act(f);
        boolean isPanning = this.flickScrollListener.getGestureDetector().isPanning();
        boolean z = false;
        if (this.fadeAlpha > 0.0f && this.fadeScrollBars && !isPanning && !this.touchScrollH && !this.touchScrollV) {
            this.fadeDelay -= f;
            if (this.fadeDelay <= 0.0f) {
                this.fadeAlpha = Math.max(0.0f, this.fadeAlpha - f);
            }
            z = true;
        }
        if (this.flingTimer > 0.0f) {
            setScrollbarsVisible(true);
            float f2 = this.flingTimer / this.flingTime;
            this.amountX -= (this.velocityX * f2) * f;
            this.amountY -= (this.velocityY * f2) * f;
            clamp();
            if (this.amountX == (-this.overscrollDistance)) {
                this.velocityX = 0.0f;
            }
            if (this.amountX >= this.maxX + this.overscrollDistance) {
                this.velocityX = 0.0f;
            }
            if (this.amountY == (-this.overscrollDistance)) {
                this.velocityY = 0.0f;
            }
            if (this.amountY >= this.maxY + this.overscrollDistance) {
                this.velocityY = 0.0f;
            }
            this.flingTimer -= f;
            if (this.flingTimer <= 0.0f) {
                this.velocityX = 0.0f;
                this.velocityY = 0.0f;
            }
            z = true;
        }
        if (!this.smoothScrolling || this.flingTimer > 0.0f || isPanning || ((this.touchScrollH && (!this.scrollX || this.maxX / (this.hScrollBounds.width - this.hKnobBounds.width) <= this.actorArea.width * 0.1f)) || (this.touchScrollV && (!this.scrollY || this.maxY / (this.vScrollBounds.height - this.vKnobBounds.height) <= this.actorArea.height * 0.1f)))) {
            if (this.visualAmountX != this.amountX) {
                visualScrollX(this.amountX);
            }
            if (this.visualAmountY != this.amountY) {
                visualScrollY(this.amountY);
            }
        } else {
            if (this.visualAmountX != this.amountX) {
                if (this.visualAmountX < this.amountX) {
                    visualScrollX(Math.min(this.amountX, this.visualAmountX + Math.max(200.0f * f, (this.amountX - this.visualAmountX) * 7.0f * f)));
                } else {
                    visualScrollX(Math.max(this.amountX, this.visualAmountX - Math.max(200.0f * f, ((this.visualAmountX - this.amountX) * 7.0f) * f)));
                }
                z = true;
            }
            if (this.visualAmountY != this.amountY) {
                if (this.visualAmountY < this.amountY) {
                    visualScrollY(Math.min(this.amountY, this.visualAmountY + Math.max(200.0f * f, (this.amountY - this.visualAmountY) * 7.0f * f)));
                } else {
                    visualScrollY(Math.max(this.amountY, this.visualAmountY - Math.max(200.0f * f, ((this.visualAmountY - this.amountY) * 7.0f) * f)));
                }
                z = true;
            }
        }
        if (!isPanning) {
            if (this.overscrollX && this.scrollX) {
                if (this.amountX < 0.0f) {
                    setScrollbarsVisible(true);
                    this.amountX += (this.overscrollSpeedMin + (((this.overscrollSpeedMax - this.overscrollSpeedMin) * (-this.amountX)) / this.overscrollDistance)) * f;
                    if (this.amountX > 0.0f) {
                        scrollX(0.0f);
                    }
                    z = true;
                } else if (this.amountX > this.maxX) {
                    setScrollbarsVisible(true);
                    this.amountX -= (this.overscrollSpeedMin + (((this.overscrollSpeedMax - this.overscrollSpeedMin) * (-(this.maxX - this.amountX))) / this.overscrollDistance)) * f;
                    if (this.amountX < this.maxX) {
                        scrollX(this.maxX);
                    }
                    z = true;
                }
            }
            if (this.overscrollY && this.scrollY) {
                if (this.amountY < 0.0f) {
                    setScrollbarsVisible(true);
                    this.amountY += (this.overscrollSpeedMin + (((this.overscrollSpeedMax - this.overscrollSpeedMin) * (-this.amountY)) / this.overscrollDistance)) * f;
                    if (this.amountY > 0.0f) {
                        scrollY(0.0f);
                    }
                    z = true;
                } else if (this.amountY > this.maxY) {
                    setScrollbarsVisible(true);
                    this.amountY -= (this.overscrollSpeedMin + (((this.overscrollSpeedMax - this.overscrollSpeedMin) * (-(this.maxY - this.amountY))) / this.overscrollDistance)) * f;
                    if (this.amountY < this.maxY) {
                        scrollY(this.maxY);
                    }
                    z = true;
                }
            }
        }
        if (!z || (stage = getStage()) == null || !stage.getActionsRequestRendering()) {
            return;
        }
        Gdx.graphics.requestRendering();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        float width;
        float height;
        Drawable drawable = this.style.background;
        Drawable drawable2 = this.style.hScrollKnob;
        Drawable drawable3 = this.style.vScrollKnob;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        if (drawable != null) {
            f = drawable.getLeftWidth();
            f2 = drawable.getRightWidth();
            f3 = drawable.getTopHeight();
            f4 = drawable.getBottomHeight();
        }
        float width2 = getWidth();
        float height2 = getHeight();
        this.actorArea.set(f, f4, (width2 - f) - f2, (height2 - f3) - f4);
        if (this.actor == null) {
            return;
        }
        float f5 = 0.0f;
        float f6 = 0.0f;
        if (drawable2 != null) {
            f5 = drawable2.getMinHeight();
        }
        if (this.style.hScroll != null) {
            f5 = Math.max(f5, this.style.hScroll.getMinHeight());
        }
        if (drawable3 != null) {
            f6 = drawable3.getMinWidth();
        }
        if (this.style.vScroll != null) {
            f6 = Math.max(f6, this.style.vScroll.getMinWidth());
        }
        if (this.actor instanceof Layout) {
            Layout layout = (Layout) this.actor;
            width = layout.getPrefWidth();
            height = layout.getPrefHeight();
        } else {
            width = this.actor.getWidth();
            height = this.actor.getHeight();
        }
        this.scrollX = this.forceScrollX || (width > this.actorArea.width && !this.disableX);
        this.scrollY = this.forceScrollY || (height > this.actorArea.height && !this.disableY);
        if (!this.scrollbarsOnTop) {
            if (this.scrollY) {
                this.actorArea.width -= f6;
                if (!this.vScrollOnRight) {
                    this.actorArea.x += f6;
                }
                if (!this.scrollX && width > this.actorArea.width && !this.disableX) {
                    this.scrollX = true;
                }
            }
            if (this.scrollX) {
                this.actorArea.height -= f5;
                if (this.hScrollOnBottom) {
                    this.actorArea.y += f5;
                }
                if (!this.scrollY && height > this.actorArea.height && !this.disableY) {
                    this.scrollY = true;
                    this.actorArea.width -= f6;
                    if (!this.vScrollOnRight) {
                        this.actorArea.x += f6;
                    }
                }
            }
        }
        float max = this.disableX ? this.actorArea.width : Math.max(this.actorArea.width, width);
        float max2 = this.disableY ? this.actorArea.height : Math.max(this.actorArea.height, height);
        this.maxX = max - this.actorArea.width;
        this.maxY = max2 - this.actorArea.height;
        scrollX(MathUtils.clamp(this.amountX, 0.0f, this.maxX));
        scrollY(MathUtils.clamp(this.amountY, 0.0f, this.maxY));
        if (this.scrollX) {
            if (drawable2 != null) {
                this.hScrollBounds.set(this.scrollbarsOnTop ? f : this.actorArea.x, this.hScrollOnBottom ? f4 : (height2 - f3) - f5, this.actorArea.width, f5);
                if (this.scrollY && this.scrollbarsOnTop) {
                    this.hScrollBounds.width -= f6;
                    if (!this.vScrollOnRight) {
                        this.hScrollBounds.x += f6;
                    }
                }
                if (this.variableSizeKnobs) {
                    this.hKnobBounds.width = Math.max(drawable2.getMinWidth(), (int) ((this.hScrollBounds.width * this.actorArea.width) / max));
                } else {
                    this.hKnobBounds.width = drawable2.getMinWidth();
                }
                if (this.hKnobBounds.width > max) {
                    this.hKnobBounds.width = 0.0f;
                }
                this.hKnobBounds.height = drawable2.getMinHeight();
                this.hKnobBounds.x = this.hScrollBounds.x + ((int) ((this.hScrollBounds.width - this.hKnobBounds.width) * getScrollPercentX()));
                this.hKnobBounds.y = this.hScrollBounds.y;
            } else {
                this.hScrollBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.hKnobBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        if (this.scrollY) {
            if (drawable3 != null) {
                this.vScrollBounds.set(this.vScrollOnRight ? (width2 - f2) - f6 : f, this.scrollbarsOnTop ? f4 : this.actorArea.y, f6, this.actorArea.height);
                if (this.scrollX && this.scrollbarsOnTop) {
                    this.vScrollBounds.height -= f5;
                    if (this.hScrollOnBottom) {
                        this.vScrollBounds.y += f5;
                    }
                }
                this.vKnobBounds.width = drawable3.getMinWidth();
                if (this.variableSizeKnobs) {
                    this.vKnobBounds.height = Math.max(drawable3.getMinHeight(), (int) ((this.vScrollBounds.height * this.actorArea.height) / max2));
                } else {
                    this.vKnobBounds.height = drawable3.getMinHeight();
                }
                if (this.vKnobBounds.height > max2) {
                    this.vKnobBounds.height = 0.0f;
                }
                this.vKnobBounds.x = this.vScrollOnRight ? (width2 - f2) - drawable3.getMinWidth() : f;
                this.vKnobBounds.y = this.vScrollBounds.y + ((int) ((this.vScrollBounds.height - this.vKnobBounds.height) * (1.0f - getScrollPercentY())));
            } else {
                this.vScrollBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
                this.vKnobBounds.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
        updateActorPosition();
        if (this.actor instanceof Layout) {
            this.actor.setSize(max, max2);
            ((Layout) this.actor).validate();
        }
    }

    private void updateActorPosition() {
        float f = this.actorArea.x - (this.scrollX ? (int) this.visualAmountX : 0);
        float f2 = this.actorArea.y - ((int) (this.scrollY ? this.maxY - this.visualAmountY : this.maxY));
        this.actor.setPosition(f, f2);
        if (this.actor instanceof Cullable) {
            this.actorCullingArea.x = this.actorArea.x - f;
            this.actorCullingArea.y = this.actorArea.y - f2;
            this.actorCullingArea.width = this.actorArea.width;
            this.actorCullingArea.height = this.actorArea.height;
            ((Cullable) this.actor).setCullingArea(this.actorCullingArea);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        if (this.actor == null) {
            return;
        }
        validate();
        applyTransform(batch, computeTransform());
        if (this.scrollX) {
            this.hKnobBounds.x = this.hScrollBounds.x + ((int) ((this.hScrollBounds.width - this.hKnobBounds.width) * getVisualScrollPercentX()));
        }
        if (this.scrollY) {
            this.vKnobBounds.y = this.vScrollBounds.y + ((int) ((this.vScrollBounds.height - this.vKnobBounds.height) * (1.0f - getVisualScrollPercentY())));
        }
        updateActorPosition();
        Color color = getColor();
        float f2 = color.f889a * f;
        if (this.style.background != null) {
            batch.setColor(color.r, color.g, color.f888b, f2);
            this.style.background.draw(batch, 0.0f, 0.0f, getWidth(), getHeight());
        }
        batch.flush();
        if (clipBegin(this.actorArea.x, this.actorArea.y, this.actorArea.width, this.actorArea.height)) {
            drawChildren(batch, f);
            batch.flush();
            clipEnd();
        }
        batch.setColor(color.r, color.g, color.f888b, f2);
        if (this.fadeScrollBars) {
            f2 *= Interpolation.fade.apply(this.fadeAlpha / this.fadeAlphaSeconds);
        }
        drawScrollBars(batch, color.r, color.g, color.f888b, f2);
        resetTransform(batch);
    }

    protected void drawScrollBars(Batch batch, float f, float f2, float f3, float f4) {
        if (f4 <= 0.0f) {
            return;
        }
        batch.setColor(f, f2, f3, f4);
        boolean z = this.scrollX && this.hKnobBounds.width > 0.0f;
        boolean z2 = this.scrollY && this.vKnobBounds.height > 0.0f;
        if (z) {
            if (z2 && this.style.corner != null) {
                this.style.corner.draw(batch, this.hScrollBounds.x + this.hScrollBounds.width, this.hScrollBounds.y, this.vScrollBounds.width, this.vScrollBounds.y);
            }
            if (this.style.hScroll != null) {
                this.style.hScroll.draw(batch, this.hScrollBounds.x, this.hScrollBounds.y, this.hScrollBounds.width, this.hScrollBounds.height);
            }
            if (this.style.hScrollKnob != null) {
                this.style.hScrollKnob.draw(batch, this.hKnobBounds.x, this.hKnobBounds.y, this.hKnobBounds.width, this.hKnobBounds.height);
            }
        }
        if (z2) {
            if (this.style.vScroll != null) {
                this.style.vScroll.draw(batch, this.vScrollBounds.x, this.vScrollBounds.y, this.vScrollBounds.width, this.vScrollBounds.height);
            }
            if (this.style.vScrollKnob != null) {
                this.style.vScrollKnob.draw(batch, this.vKnobBounds.x, this.vKnobBounds.y, this.vKnobBounds.width, this.vKnobBounds.height);
            }
        }
    }

    public void fling(float f, float f2, float f3) {
        this.flingTimer = f;
        this.velocityX = f2;
        this.velocityY = f3;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        float f = 0.0f;
        if (this.actor instanceof Layout) {
            f = ((Layout) this.actor).getPrefWidth();
        } else if (this.actor != null) {
            f = this.actor.getWidth();
        }
        Drawable drawable = this.style.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
        }
        if (this.scrollY) {
            float f2 = 0.0f;
            if (this.style.vScrollKnob != null) {
                f2 = this.style.vScrollKnob.getMinWidth();
            }
            if (this.style.vScroll != null) {
                f2 = Math.max(f2, this.style.vScroll.getMinWidth());
            }
            f += f2;
        }
        return f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        float f = 0.0f;
        if (this.actor instanceof Layout) {
            f = ((Layout) this.actor).getPrefHeight();
        } else if (this.actor != null) {
            f = this.actor.getHeight();
        }
        Drawable drawable = this.style.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getTopHeight() + drawable.getBottomHeight(), drawable.getMinHeight());
        }
        if (this.scrollX) {
            float f2 = 0.0f;
            if (this.style.hScrollKnob != null) {
                f2 = this.style.hScrollKnob.getMinHeight();
            }
            if (this.style.hScroll != null) {
                f2 = Math.max(f2, this.style.hScroll.getMinHeight());
            }
            f += f2;
        }
        return f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinWidth() {
        return 0.0f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getMinHeight() {
        return 0.0f;
    }

    public void setActor(@Null Actor actor) {
        if (this.actor == this) {
            throw new IllegalArgumentException("actor cannot be the ScrollPane.");
        }
        if (this.actor != null) {
            super.removeActor(this.actor);
        }
        this.actor = actor;
        if (actor != null) {
            super.addActor(actor);
        }
    }

    @Null
    public Actor getActor() {
        return this.actor;
    }

    @Deprecated
    public void setWidget(@Null Actor actor) {
        setActor(actor);
    }

    @Deprecated
    @Null
    public Actor getWidget() {
        return this.actor;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActor(Actor actor) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAt(int i, Actor actor) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorBefore(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    @Deprecated
    public void addActorAfter(Actor actor, Actor actor2) {
        throw new UnsupportedOperationException("Use ScrollPane#setActor.");
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.actor) {
            return false;
        }
        setActor(null);
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor, boolean z) {
        if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        if (actor != this.actor) {
            return false;
        }
        this.actor = null;
        return super.removeActor(actor, z);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int i, boolean z) {
        Actor removeActorAt = super.removeActorAt(i, z);
        if (removeActorAt == this.actor) {
            this.actor = null;
        }
        return removeActorAt;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        if (f < 0.0f || f >= getWidth() || f2 < 0.0f || f2 >= getHeight()) {
            return null;
        }
        if (z && getTouchable() == Touchable.enabled && isVisible()) {
            if (this.scrollX && this.touchScrollH && this.hScrollBounds.contains(f, f2)) {
                return this;
            }
            if (this.scrollY && this.touchScrollV && this.vScrollBounds.contains(f, f2)) {
                return this;
            }
        }
        return super.hit(f, f2, z);
    }

    protected void scrollX(float f) {
        this.amountX = f;
    }

    protected void scrollY(float f) {
        this.amountY = f;
    }

    protected void visualScrollX(float f) {
        this.visualAmountX = f;
    }

    protected void visualScrollY(float f) {
        this.visualAmountY = f;
    }

    protected float getMouseWheelX() {
        return Math.min(this.actorArea.width, Math.max(this.actorArea.width * 0.9f, this.maxX * 0.1f) / 4.0f);
    }

    protected float getMouseWheelY() {
        return Math.min(this.actorArea.height, Math.max(this.actorArea.height * 0.9f, this.maxY * 0.1f) / 4.0f);
    }

    public void setScrollX(float f) {
        scrollX(MathUtils.clamp(f, 0.0f, this.maxX));
    }

    public float getScrollX() {
        return this.amountX;
    }

    public void setScrollY(float f) {
        scrollY(MathUtils.clamp(f, 0.0f, this.maxY));
    }

    public float getScrollY() {
        return this.amountY;
    }

    public void updateVisualScroll() {
        this.visualAmountX = this.amountX;
        this.visualAmountY = this.amountY;
    }

    public float getVisualScrollX() {
        if (this.scrollX) {
            return this.visualAmountX;
        }
        return 0.0f;
    }

    public float getVisualScrollY() {
        if (this.scrollY) {
            return this.visualAmountY;
        }
        return 0.0f;
    }

    public float getVisualScrollPercentX() {
        if (this.maxX == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.visualAmountX / this.maxX, 0.0f, 1.0f);
    }

    public float getVisualScrollPercentY() {
        if (this.maxY == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.visualAmountY / this.maxY, 0.0f, 1.0f);
    }

    public float getScrollPercentX() {
        if (this.maxX == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.amountX / this.maxX, 0.0f, 1.0f);
    }

    public void setScrollPercentX(float f) {
        scrollX(this.maxX * MathUtils.clamp(f, 0.0f, 1.0f));
    }

    public float getScrollPercentY() {
        if (this.maxY == 0.0f) {
            return 0.0f;
        }
        return MathUtils.clamp(this.amountY / this.maxY, 0.0f, 1.0f);
    }

    public void setScrollPercentY(float f) {
        scrollY(this.maxY * MathUtils.clamp(f, 0.0f, 1.0f));
    }

    public void setFlickScroll(boolean z) {
        if (this.flickScroll == z) {
            return;
        }
        this.flickScroll = z;
        if (z) {
            addListener(this.flickScrollListener);
        } else {
            removeListener(this.flickScrollListener);
        }
        invalidate();
    }

    public void setFlickScrollTapSquareSize(float f) {
        this.flickScrollListener.getGestureDetector().setTapSquareSize(f);
    }

    public void scrollTo(float f, float f2, float f3, float f4) {
        scrollTo(f, f2, f3, f4, false, false);
    }

    public void scrollTo(float f, float f2, float f3, float f4, boolean z, boolean z2) {
        float clamp;
        float clamp2;
        validate();
        float f5 = this.amountX;
        if (z) {
            clamp = f + ((f3 - this.actorArea.width) / 2.0f);
        } else {
            clamp = MathUtils.clamp(f5, f, (f + f3) - this.actorArea.width);
        }
        scrollX(MathUtils.clamp(clamp, 0.0f, this.maxX));
        float f6 = this.amountY;
        float f7 = this.maxY - f2;
        if (z2) {
            clamp2 = f7 + ((this.actorArea.height + f4) / 2.0f);
        } else {
            clamp2 = MathUtils.clamp(f6, f7 + f4, f7 + this.actorArea.height);
        }
        scrollY(MathUtils.clamp(clamp2, 0.0f, this.maxY));
    }

    public float getMaxX() {
        return this.maxX;
    }

    public float getMaxY() {
        return this.maxY;
    }

    public float getScrollBarHeight() {
        if (!this.scrollX) {
            return 0.0f;
        }
        float f = 0.0f;
        if (this.style.hScrollKnob != null) {
            f = this.style.hScrollKnob.getMinHeight();
        }
        if (this.style.hScroll != null) {
            f = Math.max(f, this.style.hScroll.getMinHeight());
        }
        return f;
    }

    public float getScrollBarWidth() {
        if (!this.scrollY) {
            return 0.0f;
        }
        float f = 0.0f;
        if (this.style.vScrollKnob != null) {
            f = this.style.vScrollKnob.getMinWidth();
        }
        if (this.style.vScroll != null) {
            f = Math.max(f, this.style.vScroll.getMinWidth());
        }
        return f;
    }

    public float getScrollWidth() {
        return this.actorArea.width;
    }

    public float getScrollHeight() {
        return this.actorArea.height;
    }

    public boolean isScrollX() {
        return this.scrollX;
    }

    public boolean isScrollY() {
        return this.scrollY;
    }

    public void setScrollingDisabled(boolean z, boolean z2) {
        if (z == this.disableX && z2 == this.disableY) {
            return;
        }
        this.disableX = z;
        this.disableY = z2;
        invalidate();
    }

    public boolean isScrollingDisabledX() {
        return this.disableX;
    }

    public boolean isScrollingDisabledY() {
        return this.disableY;
    }

    public boolean isLeftEdge() {
        return !this.scrollX || this.amountX <= 0.0f;
    }

    public boolean isRightEdge() {
        return !this.scrollX || this.amountX >= this.maxX;
    }

    public boolean isTopEdge() {
        return !this.scrollY || this.amountY <= 0.0f;
    }

    public boolean isBottomEdge() {
        return !this.scrollY || this.amountY >= this.maxY;
    }

    public boolean isDragging() {
        return this.draggingPointer != -1;
    }

    public boolean isPanning() {
        return this.flickScrollListener.getGestureDetector().isPanning();
    }

    public boolean isFlinging() {
        return this.flingTimer > 0.0f;
    }

    public void setVelocityX(float f) {
        this.velocityX = f;
    }

    public float getVelocityX() {
        return this.velocityX;
    }

    public void setVelocityY(float f) {
        this.velocityY = f;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public void setOverscroll(boolean z, boolean z2) {
        this.overscrollX = z;
        this.overscrollY = z2;
    }

    public void setupOverscroll(float f, float f2, float f3) {
        this.overscrollDistance = f;
        this.overscrollSpeedMin = f2;
        this.overscrollSpeedMax = f3;
    }

    public float getOverscrollDistance() {
        return this.overscrollDistance;
    }

    public void setForceScroll(boolean z, boolean z2) {
        this.forceScrollX = z;
        this.forceScrollY = z2;
    }

    public boolean isForceScrollX() {
        return this.forceScrollX;
    }

    public boolean isForceScrollY() {
        return this.forceScrollY;
    }

    public void setFlingTime(float f) {
        this.flingTime = f;
    }

    public void setClamp(boolean z) {
        this.clamp = z;
    }

    public void setScrollBarPositions(boolean z, boolean z2) {
        this.hScrollOnBottom = z;
        this.vScrollOnRight = z2;
    }

    public void setFadeScrollBars(boolean z) {
        if (this.fadeScrollBars == z) {
            return;
        }
        this.fadeScrollBars = z;
        if (!z) {
            this.fadeAlpha = this.fadeAlphaSeconds;
        }
        invalidate();
    }

    public void setupFadeScrollBars(float f, float f2) {
        this.fadeAlphaSeconds = f;
        this.fadeDelaySeconds = f2;
    }

    public boolean getFadeScrollBars() {
        return this.fadeScrollBars;
    }

    public void setScrollBarTouch(boolean z) {
        this.scrollBarTouch = z;
    }

    public void setSmoothScrolling(boolean z) {
        this.smoothScrolling = z;
    }

    public void setScrollbarsOnTop(boolean z) {
        this.scrollbarsOnTop = z;
        invalidate();
    }

    public boolean getVariableSizeKnobs() {
        return this.variableSizeKnobs;
    }

    public void setVariableSizeKnobs(boolean z) {
        this.variableSizeKnobs = z;
    }

    public void setCancelTouchFocus(boolean z) {
        this.cancelTouchFocus = z;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer) {
        drawDebugBounds(shapeRenderer);
        applyTransform(shapeRenderer, computeTransform());
        if (clipBegin(this.actorArea.x, this.actorArea.y, this.actorArea.width, this.actorArea.height)) {
            drawDebugChildren(shapeRenderer);
            shapeRenderer.flush();
            clipEnd();
        }
        resetTransform(shapeRenderer);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/ScrollPane$ScrollPaneStyle.class */
    public static class ScrollPaneStyle {

        @Null
        public Drawable background;

        @Null
        public Drawable corner;

        @Null
        public Drawable hScroll;

        @Null
        public Drawable hScrollKnob;

        @Null
        public Drawable vScroll;

        @Null
        public Drawable vScrollKnob;

        public ScrollPaneStyle() {
        }

        public ScrollPaneStyle(@Null Drawable drawable, @Null Drawable drawable2, @Null Drawable drawable3, @Null Drawable drawable4, @Null Drawable drawable5) {
            this.background = drawable;
            this.hScroll = drawable2;
            this.hScrollKnob = drawable3;
            this.vScroll = drawable4;
            this.vScrollKnob = drawable5;
        }

        public ScrollPaneStyle(ScrollPaneStyle scrollPaneStyle) {
            this.background = scrollPaneStyle.background;
            this.corner = scrollPaneStyle.corner;
            this.hScroll = scrollPaneStyle.hScroll;
            this.hScrollKnob = scrollPaneStyle.hScrollKnob;
            this.vScroll = scrollPaneStyle.vScroll;
            this.vScrollKnob = scrollPaneStyle.vScrollKnob;
        }
    }
}
