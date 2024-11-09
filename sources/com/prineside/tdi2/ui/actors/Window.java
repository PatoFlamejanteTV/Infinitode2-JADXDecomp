package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.Game;
import com.prineside.tdi2.events.global.StartRender;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.Cell;
import com.prineside.tdi2.scene2d.ui.Image;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.ui.Table;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.ui.actors.Label;
import com.prineside.tdi2.ui.events.MoveToFrontListener;
import com.prineside.tdi2.utils.IgnoreMethodOverloadLuaDefWarning;
import com.prineside.tdi2.utils.InputVoid;
import com.prineside.tdi2.utils.MaterialColor;
import com.prineside.tdi2.utils.ObjectConsumer;
import com.prineside.tdi2.utils.PackColor;
import com.prineside.tdi2.utils.UiUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Window.class */
public class Window extends Table {
    private WindowStyle n;
    public Table headerLayout;
    public Label title;
    private ScrollPane o;
    public Table main;
    public float minWidth;
    public float minHeight;
    public float maxWidth;
    public float maxHeight;
    private Image[] p;
    protected boolean k;
    private boolean q;
    private boolean r;
    private boolean s;
    private int t;
    private DelayedRemovalArray<WindowListener> u;
    private ObjectConsumer<ObjectConsumer<Boolean>> v;
    protected static final Rectangle l = new Rectangle();
    private static Vector2 w = new Vector2();
    private static final Rectangle x = new Rectangle();

    public Window() {
        this(Game.i.assetManager.createDefaultWindowStyle());
    }

    public Window(WindowStyle windowStyle) {
        this.maxWidth = 4096.0f;
        this.maxHeight = 4096.0f;
        this.q = true;
        this.r = false;
        this.s = false;
        this.t = 1;
        this.n = windowStyle;
        if (windowStyle.catchAllTouches) {
            addListener(new InputVoid());
        }
        Image image = new Image(windowStyle.background);
        image.setFillParent(true);
        addActor(image);
        if (windowStyle.headerBackground != null) {
            float headerHeight = getHeaderHeight();
            this.headerLayout = new Table();
            this.headerLayout.setTouchable(Touchable.enabled);
            add((Window) this.headerLayout).fillX().expandX().fillY().row();
            this.headerLayout.background(windowStyle.headerBackground);
            this.title = new Label("", windowStyle.titleLabelStyle);
            this.headerLayout.add((Table) this.title).expandX().fillX().height(headerHeight).padLeft(windowStyle.defaultPadding);
            if (windowStyle.closeButton == null) {
                PaddedImageButton paddedImageButton = new PaddedImageButton(Game.i.assetManager.getDrawable("icon-times"), this::close, Color.WHITE, MaterialColor.RED.P500, MaterialColor.RED.P300);
                paddedImageButton.setIconSize(0.625f * headerHeight, 0.625f * headerHeight);
                paddedImageButton.setIconPosition(0.1875f * headerHeight, 0.1875f * headerHeight);
                this.headerLayout.add((Table) paddedImageButton).size(headerHeight).padLeft(windowStyle.defaultPadding).padRight(windowStyle.defaultPadding * 0.5f);
            } else {
                PaddedImageButton paddedImageButton2 = new PaddedImageButton(windowStyle.closeButton, this::close, windowStyle.closeButtonColor.getColorAtIndex(0), windowStyle.closeButtonColor.getColorAtIndex(1), windowStyle.closeButtonColor.getColorAtIndex(2));
                paddedImageButton2.setIconSize(headerHeight, headerHeight);
                this.headerLayout.add((Table) paddedImageButton2).size(headerHeight).padLeft(windowStyle.defaultPadding);
            }
        }
        this.main = new Table();
        if (windowStyle.scrollPaneStyle != null) {
            this.o = new ScrollPane(this.main, windowStyle.scrollPaneStyle);
            this.o.setOverscroll(false, false);
            this.o.setFadeScrollBars(false);
            this.o.setName("Window content scroll");
            UiUtils.enableMouseMoveScrollFocus(this.o);
            add((Window) this.o).grow().row();
        } else {
            add((Window) this.main).grow().row();
        }
        if (windowStyle.resizeable) {
            setResizeable(true);
        }
        if (windowStyle.draggable) {
            setDraggable(true);
        }
        this.k = windowStyle.resizeHasMinSize;
        addListener(new InputListener() { // from class: com.prineside.tdi2.ui.actors.Window.1
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                Window.this.bringToFront();
                return false;
            }
        });
        if (windowStyle.alwaysOnTop) {
            addListener(new MoveToFrontListener() { // from class: com.prineside.tdi2.ui.actors.Window.2

                /* renamed from: a, reason: collision with root package name */
                private int f3249a;

                @Override // com.prineside.tdi2.ui.events.MoveToFrontListener
                public void actorMovedToFront(MoveToFrontListener.MoveToFrontEvent moveToFrontEvent, Actor actor, boolean z) {
                    int eventsTriggered;
                    if (!z && this.f3249a != (eventsTriggered = Game.EVENTS.getListeners(StartRender.class).getEventsTriggered())) {
                        this.f3249a = eventsTriggered;
                        moveToFrontEvent.cancel();
                        Window.this.bringToFront();
                    }
                }
            });
        }
        setVisible(false);
        setTitle("Window-" + Integer.toHexString(hashCode()));
    }

    @Null
    public ScrollPane getScrollPane() {
        return this.o;
    }

    public void setCloseHandler(ObjectConsumer<ObjectConsumer<Boolean>> objectConsumer) {
        this.v = objectConsumer;
    }

    public WindowStyle getStyle() {
        return this.n;
    }

    public int getHeaderHeight() {
        if (this.n.headerBackground == null) {
            return 0;
        }
        return MathUtils.round(this.n.headerBackground.getMinHeight());
    }

    public int getContentHeight() {
        return MathUtils.round(getHeight()) - getHeaderHeight();
    }

    public Cell<Actor> setContents(Actor actor) {
        this.main.clear();
        return this.main.add((Table) actor).grow();
    }

    public Actor getContents() {
        return this.main.getChild(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Actor
    public void sizeChanged() {
        super.sizeChanged();
        d();
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void positionChanged() {
        super.positionChanged();
        a((v0) -> {
            v0.moved();
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public final void a() {
        super.a();
        d();
    }

    private void d() {
        e();
    }

    public void setResizeable(boolean z) {
        if (z) {
            if (this.p == null) {
                this.p = new Image[8];
                for (int i = 0; i < this.p.length; i++) {
                    this.p[i] = new Image();
                    addActor(this.p[i]);
                    this.p[i].setTouchable(Touchable.enabled);
                    this.p[i].addListener(new ResizeHandleListener(i));
                }
                if (Game.i.cursorGraphics != null) {
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[1], Cursor.SystemCursor.VerticalResize);
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[6], Cursor.SystemCursor.VerticalResize);
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[3], Cursor.SystemCursor.HorizontalResize);
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[4], Cursor.SystemCursor.HorizontalResize);
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[0], Cursor.SystemCursor.NWSEResize);
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[7], Cursor.SystemCursor.NWSEResize);
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[2], Cursor.SystemCursor.NESWResize);
                    Game.i.cursorGraphics.setActorCustomMouseCursor(this.p[5], Cursor.SystemCursor.NESWResize);
                }
            }
            e();
            return;
        }
        if (this.p != null) {
            for (Image image : this.p) {
                image.remove();
            }
            this.p = null;
        }
    }

    public void setDraggable(boolean z) {
        if (this.s != z) {
            this.s = z;
            if (z) {
                if (this.headerLayout != null) {
                    this.headerLayout.addListener(new DragHandleListener());
                    return;
                } else {
                    addListener(new DragHandleListener());
                    return;
                }
            }
            if (this.headerLayout != null) {
                for (int i = 0; i < this.headerLayout.getListeners().size; i++) {
                    if (this.headerLayout.getListeners().get(i) instanceof DragHandleListener) {
                        this.headerLayout.removeListener(this.headerLayout.getListeners().get(i));
                        return;
                    }
                }
                return;
            }
            for (int i2 = 0; i2 < getListeners().size; i2++) {
                if (getListeners().get(i2) instanceof DragHandleListener) {
                    removeListener(getListeners().get(i2));
                    return;
                }
            }
        }
    }

    private void e() {
        if (this.p != null) {
            float f = this.n.resizeHandleSize;
            float f2 = this.n.resizeHandleOverlap;
            float f3 = this.n.resizeHandleSizeHeader;
            float f4 = this.n.resizeHandleOverlapHeader;
            this.p[0].setSize(f3, f3);
            this.p[0].setPosition((-f3) + f4, getHeight() - f4);
            this.p[1].setSize(getWidth() - (f4 * 2.0f), f3);
            this.p[1].setPosition(f4, getHeight() - f4);
            this.p[2].setSize(f3, f3);
            this.p[2].setPosition(getWidth() - f4, getHeight() - f4);
            this.p[3].setSize(f, (getHeight() - f4) - f2);
            this.p[3].setPosition((-f) + f2, f2);
            this.p[4].setSize(f, (getHeight() - f4) - f2);
            this.p[4].setPosition(getWidth() - f2, f2);
            this.p[5].setSize(f, f);
            this.p[5].setPosition((-f) + f2, (-f) + f2);
            this.p[6].setSize(getWidth() - (f2 * 2.0f), f);
            this.p[6].setPosition(f2, (-f) + f2);
            this.p[7].setSize(f, f);
            this.p[7].setPosition(getWidth() - f2, (-f) + f2);
        }
    }

    public void flash() {
        setTransform(true);
        setOrigin(1);
        addAction(Actions.sequence(Actions.scaleTo(1.1f, 0.9f, 0.1f, Interpolation.pow2Out), Actions.scaleTo(0.9f, 1.1f, 0.1f, Interpolation.pow2), Actions.scaleTo(1.0f, 1.0f, 0.07f, Interpolation.pow2), Actions.run(() -> {
            setTransform(false);
        })));
    }

    public void bringToFront() {
        UiUtils.bringToFront(this);
    }

    public static Rectangle getBox(Actor actor, Rectangle rectangle) {
        return rectangle.set(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
    }

    public Rectangle getBox(Rectangle rectangle) {
        return getBox(this, rectangle);
    }

    public Rectangle getContentBox(Rectangle rectangle) {
        return getBox(this.main, rectangle);
    }

    public void addListener(WindowListener windowListener) {
        if (this.u == null) {
            this.u = new DelayedRemovalArray<>(true, 1, WindowListener.class);
        }
        this.u.add(windowListener);
    }

    public void removeListener(WindowListener windowListener) {
        if (this.u != null) {
            this.u.removeValue(windowListener, true);
        }
    }

    public Array<WindowListener> getWindowListeners() {
        return this.u;
    }

    public void setTitle(CharSequence charSequence) {
        if (this.title != null) {
            this.title.setText(charSequence);
            this.title.setName("window-title-" + ((Object) charSequence));
        }
        if (this.o != null) {
            this.o.setName("window-scroll-" + ((Object) charSequence));
        }
        this.main.setName("window-main-" + ((Object) charSequence));
    }

    public StringBuilder getTitle() {
        if (this.title == null) {
            return null;
        }
        return this.title.getText();
    }

    public void setAnimated(boolean z) {
        this.q = z;
    }

    public boolean isAnimated() {
        return this.q;
    }

    public boolean isResizeable() {
        return this.p != null;
    }

    public void setAppearAlign(int i) {
        this.t = i;
    }

    public void alignToPoint(float f, float f2, int i) {
        float width = getWidth();
        float height = getHeight();
        switch (i) {
            case 1:
                setPosition(f - (width * 0.5f), f2 - (height * 0.5f));
                return;
            case 2:
                setPosition(f - (width * 0.5f), f2 - height);
                return;
            case 3:
            case 5:
            case 6:
            case 7:
            case 9:
            case 11:
            case 13:
            case 14:
            case 15:
            case 17:
            case 19:
            default:
                return;
            case 4:
                setPosition(f - (width * 0.5f), f2);
                return;
            case 8:
                setPosition(f, f2 - (height * 0.5f));
                return;
            case 10:
                setPosition(f, f2 - height);
                return;
            case 12:
                setPosition(f, f2);
                return;
            case 16:
                setPosition(f - width, f2 - (height * 0.5f));
                return;
            case 18:
                setPosition(f - width, f2 - height);
                return;
            case 20:
                setPosition(f - width, f2);
                return;
        }
    }

    protected final void a(ObjectConsumer<WindowListener> objectConsumer) {
        if (this.u != null) {
            this.u.begin();
            for (int i = 0; i < this.u.size; i++) {
                objectConsumer.accept(this.u.get(i));
            }
            this.u.end();
        }
    }

    public void showAtCursor() {
        Vector2 vector2 = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        Game.i.uiManager.stage.screenToStageCoordinates(vector2);
        showAtPointAligned(vector2.x, vector2.y, 1);
    }

    public void show() {
        clearActions();
        if (this.q) {
            setOrigin(this.t);
            setTransform(true);
            addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f), Actions.scaleTo(1.0f, 1.0f, 0.08f), Actions.run(() -> {
                setTransform(false);
            })));
        } else {
            setScale(1.0f);
            setTransform(false);
        }
        clampWindowPosition();
        setVisible(true);
        this.r = true;
        bringToFront();
        a((v0) -> {
            v0.shown();
        });
    }

    public void showAtPoint(float f, float f2) {
        alignToPoint(f, f2, this.t);
        show();
    }

    public void showAtPointAligned(float f, float f2, int i) {
        setAppearAlign(i);
        alignToPoint(f, f2, i);
        show();
    }

    public int getWindowAlign() {
        return this.t;
    }

    private void f() {
        this.r = false;
        clearActions();
        if (this.q) {
            setOrigin(this.t);
            setTransform(true);
            addAction(Actions.sequence(Actions.scaleTo(0.0f, 0.0f, 0.08f), Actions.run(() -> {
                setTransform(false);
                setVisible(false);
                a((v0) -> {
                    v0.closed();
                });
            })));
        } else {
            setTransform(false);
            setVisible(false);
            a((v0) -> {
                v0.closed();
            });
        }
    }

    public void close() {
        if (this.r) {
            if (this.v == null) {
                f();
            } else {
                this.v.accept(bool -> {
                    if (bool.booleanValue()) {
                        f();
                    }
                });
            }
        }
    }

    public void fitToContentSimple() {
        fitToContent(this.t, true, true, false);
    }

    public void fitToContent(int i, boolean z, boolean z2, boolean z3) {
        float x2;
        float y;
        if (getStage() == null) {
            throw new IllegalStateException("Actor has no stage");
        }
        if (Align.isLeft(i)) {
            x2 = getX();
        } else {
            x2 = Align.isRight(i) ? getX() + getWidth() : getX() + (getWidth() * 0.5f);
        }
        float f = x2;
        if (Align.isBottom(i)) {
            y = getY();
        } else {
            y = Align.isTop(i) ? getY() + getHeight() : getY() + (getHeight() * 0.5f);
        }
        float f2 = y;
        float width = getWidth();
        float height = getHeight();
        layout();
        pack();
        g();
        if (!z2) {
            setHeight(height);
        }
        if (!z) {
            setWidth(width);
        }
        if (z3 && getWidth() < width) {
            setWidth(width);
        }
        if (z3 && getHeight() < height) {
            setHeight(height);
        }
        setPosition(f, f2, i);
        clampWindowPosition();
    }

    public void setPositionByCorner(int i, float f, float f2) {
        float width;
        float height;
        if (Align.isLeft(i)) {
            width = f;
        } else {
            width = Align.isRight(i) ? f - getWidth() : f - (getWidth() * 0.5f);
        }
        float f3 = width;
        if (Align.isBottom(i)) {
            height = f2;
        } else {
            height = Align.isTop(i) ? f2 - getHeight() : f2 - (getHeight() * 0.5f);
        }
        setPosition(f3, height);
    }

    private void g() {
        if (getWidth() > getMaxWidth()) {
            setWidth(getMaxWidth());
        }
        if (getHeight() > getMaxHeight()) {
            setHeight(getMaxHeight());
        }
        if (getWidth() < getMinWidth()) {
            setWidth(getMinWidth());
        }
        if (getHeight() < getMinHeight()) {
            setHeight(getMinHeight());
        }
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    @IgnoreMethodOverloadLuaDefWarning
    public void setPosition(float f, float f2, int i) {
        float height;
        float width;
        if (Align.isBottom(i)) {
            height = f2;
        } else if (Align.isTop(i)) {
            height = f2 - getHeight();
        } else {
            height = f2 - (getHeight() * 0.5f);
        }
        if (Align.isLeft(i)) {
            width = f;
        } else if (Align.isRight(i)) {
            width = f - getWidth();
        } else {
            width = f - (getWidth() * 0.5f);
        }
        setPosition(width, height);
    }

    public boolean clampWindowPosition() {
        if (getParent() == null) {
            return false;
        }
        w.set(getParent().getWidth(), getParent().getHeight());
        Rectangle moveIntoViewport = moveIntoViewport(getBox(l), w);
        if (getX() != moveIntoViewport.x || getY() != moveIntoViewport.y) {
            setPosition(moveIntoViewport.x, moveIntoViewport.y);
            return true;
        }
        return false;
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public void act(float f) {
        clampWindowPosition();
        super.act(f);
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    @IgnoreMethodOverloadLuaDefWarning
    public void setPosition(float f, float f2) {
        super.setPosition(MathUtils.round(f), MathUtils.round(f2));
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public void setSize(float f, float f2) {
        if (f > getMaxWidth()) {
            f = getMaxWidth();
        }
        if (f2 > getMaxHeight()) {
            f2 = getMaxHeight();
        }
        if (f < getMinWidth()) {
            f = getMinWidth();
        }
        if (f2 < getMinHeight()) {
            f2 = getMinHeight();
        }
        super.setSize(MathUtils.round(f), MathUtils.round(f2));
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinWidth() {
        return Math.max(this.minWidth, this.n.inheritWidgetMinSize ? super.getMinWidth() : 0.0f);
    }

    @Override // com.prineside.tdi2.scene2d.ui.Table, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMinHeight() {
        return Math.max(this.minHeight, this.n.inheritWidgetMinSize ? super.getMinHeight() : 0.0f);
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxWidth() {
        return Math.min(this.maxWidth, getStage().getWidth());
    }

    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getMaxHeight() {
        return Math.min(this.maxHeight, getStage().getHeight());
    }

    @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    public static Rectangle moveIntoViewport(Rectangle rectangle, Vector2 vector2) {
        if (rectangle.x < 0.0f) {
            rectangle.x = 0.0f;
        }
        if (rectangle.y < 0.0f) {
            rectangle.y = 0.0f;
        }
        x.set(rectangle);
        if (x.x < 0.0f) {
            x.x = 0.0f;
        } else if (x.x + x.width > vector2.x) {
            x.x = vector2.x - x.width;
        }
        if (x.y < 0.0f) {
            x.y = 0.0f;
        } else if (x.y + x.height > vector2.y) {
            x.y = vector2.y - x.height;
        }
        return x;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Window$WindowListener.class */
    public interface WindowListener {
        void dragged();

        void shown();

        void closed();

        void resized();

        void moved();

        /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Window$WindowListener$Adapter.class */
        public static class Adapter implements WindowListener {
            @Override // com.prineside.tdi2.ui.actors.Window.WindowListener
            public void dragged() {
            }

            @Override // com.prineside.tdi2.ui.actors.Window.WindowListener
            public void shown() {
            }

            @Override // com.prineside.tdi2.ui.actors.Window.WindowListener
            public void closed() {
            }

            @Override // com.prineside.tdi2.ui.actors.Window.WindowListener
            public void resized() {
            }

            @Override // com.prineside.tdi2.ui.actors.Window.WindowListener
            public void moved() {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Window$DragHandleListener.class */
    public class DragHandleListener extends InputListener {

        /* renamed from: a, reason: collision with root package name */
        private float f3251a;

        /* renamed from: b, reason: collision with root package name */
        private float f3252b;
        private float c;
        private float d;

        protected DragHandleListener() {
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
            if (i2 == 0) {
                this.c = inputEvent.getStageX();
                this.d = inputEvent.getStageY();
                this.f3251a = Window.this.getX();
                this.f3252b = Window.this.getY();
                return true;
            }
            return false;
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
            Window.this.setPosition((int) (this.f3251a + (inputEvent.getStageX() - this.c)), (int) (this.f3252b + (inputEvent.getStageY() - this.d)));
            Window.this.clampWindowPosition();
            Window.this.a((v0) -> {
                v0.dragged();
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Window$ResizeHandleListener.class */
    public class ResizeHandleListener extends InputListener {

        /* renamed from: a, reason: collision with root package name */
        private int f3253a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f3254b;
        private Rectangle c = new Rectangle();
        private Rectangle d = new Rectangle();
        private float e;
        private float f;

        public ResizeHandleListener(int i) {
            this.f3253a = i;
        }

        private void a() {
            if (Window.this.getParent() == null) {
                return;
            }
            if (this.d.x < 0.0f) {
                this.d.width += this.d.x;
                this.d.x = 0.0f;
            }
            if (this.d.y < 0.0f) {
                this.d.height += this.d.y;
                this.d.y = 0.0f;
            }
            float width = Window.this.getParent().getWidth();
            float height = Window.this.getParent().getHeight();
            if (this.d.x + this.d.width > width) {
                this.d.width -= (this.d.x + this.d.width) - width;
            }
            if (this.d.y + this.d.height > height) {
                this.d.height -= (this.d.y + this.d.height) - height;
            }
            if (Window.this.k) {
                if (this.d.width < Window.this.getMinWidth()) {
                    if (this.f3253a == 0 || this.f3253a == 3 || this.f3253a == 5) {
                        this.d.x -= Window.this.getMinWidth() - this.d.width;
                    }
                    this.d.width = Window.this.getMinWidth();
                }
                if (this.d.height < Window.this.getMinHeight()) {
                    if (this.f3253a == 5 || this.f3253a == 6 || this.f3253a == 7) {
                        this.d.y -= Window.this.getMinHeight() - this.d.height;
                    }
                    this.d.height = Window.this.getMinHeight();
                }
            }
            if (this.d.width > Window.this.getMaxWidth()) {
                if (this.f3253a == 0 || this.f3253a == 3 || this.f3253a == 5) {
                    this.d.x -= Window.this.getMaxWidth() - this.d.width;
                }
                this.d.width = Window.this.getMaxWidth();
            }
            if (this.d.height > Window.this.getMaxHeight()) {
                if (this.f3253a == 5 || this.f3253a == 6 || this.f3253a == 7) {
                    this.d.y -= Window.this.getMaxHeight() - this.d.height;
                }
                this.d.height = Window.this.getMaxHeight();
            }
            Window.this.setPosition(this.d.x, this.d.y);
            Window.this.setSize(this.d.width, this.d.height);
            Window.this.a((v0) -> {
                v0.resized();
            });
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
            if (i2 == 0) {
                this.e = inputEvent.getStageX();
                this.f = inputEvent.getStageY();
                this.c.set(Window.this.getBox(Window.l));
                this.d.set(this.c);
                this.f3254b = true;
                return true;
            }
            return false;
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
            if (this.f3254b) {
                float stageX = inputEvent.getStageX() - this.e;
                float stageY = inputEvent.getStageY() - this.f;
                switch (this.f3253a) {
                    case 0:
                        this.d.set(this.c);
                        this.d.x += stageX;
                        this.d.width -= stageX;
                        this.d.height += stageY;
                        break;
                    case 1:
                        this.d.set(this.c);
                        this.d.height += stageY;
                        break;
                    case 2:
                        this.d.set(this.c);
                        this.d.width += stageX;
                        this.d.height += stageY;
                        break;
                    case 3:
                        this.d.set(this.c);
                        this.d.x += stageX;
                        this.d.width -= stageX;
                        break;
                    case 4:
                        this.d.set(this.c);
                        this.d.width += stageX;
                        break;
                    case 5:
                        this.d.set(this.c);
                        this.d.x += stageX;
                        this.d.y += stageY;
                        this.d.width -= stageX;
                        this.d.height -= stageY;
                        break;
                    case 6:
                        this.d.set(this.c);
                        this.d.y += stageY;
                        this.d.height -= stageY;
                        break;
                    case 7:
                        this.d.set(this.c);
                        this.d.width += stageX;
                        this.d.height -= stageY;
                        this.d.y += stageY;
                        break;
                }
                a();
            }
        }

        @Override // com.prineside.tdi2.scene2d.InputListener
        public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
            if (this.f3254b) {
                this.f3254b = false;
                Window.this.clampWindowPosition();
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Window$WindowStyle.class */
    public static class WindowStyle {
        public boolean catchAllTouches;
        public Drawable background;
        public Drawable headerBackground;

        @Null
        public ScrollPane.ScrollPaneStyle scrollPaneStyle;
        public Label.LabelStyle titleLabelStyle;
        public float defaultPadding;
        public float resizeHandleSize;
        public float resizeHandleOverlap;
        public float resizeHandleSizeHeader;
        public float resizeHandleOverlapHeader;
        public boolean resizeable;
        public boolean draggable;
        public boolean resizeHasMinSize;
        public boolean alwaysOnTop;
        public boolean inheritWidgetMinSize;
        public Drawable closeButton;
        public PackColor closeButtonColor;

        public WindowStyle() {
            this.catchAllTouches = true;
            this.defaultPadding = 8.0f;
            this.resizeHandleSize = 8.0f;
            this.resizeHandleOverlap = 1.0f;
            this.resizeHandleSizeHeader = 13.0f;
            this.resizeHandleOverlapHeader = 3.0f;
            this.resizeable = false;
            this.draggable = true;
            this.resizeHasMinSize = true;
            this.alwaysOnTop = false;
            this.inheritWidgetMinSize = true;
            this.closeButtonColor = new PackColor(Color.WHITE);
        }

        public WindowStyle(WindowStyle windowStyle) {
            this.catchAllTouches = true;
            this.defaultPadding = 8.0f;
            this.resizeHandleSize = 8.0f;
            this.resizeHandleOverlap = 1.0f;
            this.resizeHandleSizeHeader = 13.0f;
            this.resizeHandleOverlapHeader = 3.0f;
            this.resizeable = false;
            this.draggable = true;
            this.resizeHasMinSize = true;
            this.alwaysOnTop = false;
            this.inheritWidgetMinSize = true;
            this.closeButtonColor = new PackColor(Color.WHITE);
            this.catchAllTouches = windowStyle.catchAllTouches;
            this.background = windowStyle.background;
            this.headerBackground = windowStyle.headerBackground;
            this.titleLabelStyle = windowStyle.titleLabelStyle;
            this.defaultPadding = windowStyle.defaultPadding;
            this.resizeHandleSize = windowStyle.resizeHandleSize;
            this.resizeHandleOverlap = windowStyle.resizeHandleOverlap;
            this.resizeHandleSizeHeader = windowStyle.resizeHandleSizeHeader;
            this.resizeHandleOverlapHeader = windowStyle.resizeHandleOverlapHeader;
            this.alwaysOnTop = windowStyle.alwaysOnTop;
            this.scrollPaneStyle = windowStyle.scrollPaneStyle;
            this.resizeable = windowStyle.resizeable;
            this.draggable = windowStyle.draggable;
            this.inheritWidgetMinSize = windowStyle.inheritWidgetMinSize;
            this.resizeHasMinSize = windowStyle.resizeHasMinSize;
            this.closeButton = windowStyle.closeButton;
            this.closeButtonColor = windowStyle.closeButtonColor;
        }

        public WindowStyle setCatchAllTouches(boolean z) {
            this.catchAllTouches = z;
            return this;
        }

        public WindowStyle setDraggable(boolean z) {
            this.draggable = z;
            return this;
        }

        public WindowStyle setAlwaysOnTop(boolean z) {
            this.alwaysOnTop = z;
            return this;
        }

        public WindowStyle setBackground(Drawable drawable) {
            this.background = drawable;
            return this;
        }

        public WindowStyle setHeaderBackground(Drawable drawable) {
            this.headerBackground = drawable;
            return this;
        }

        public WindowStyle setInheritWidgetMinSize(boolean z) {
            this.inheritWidgetMinSize = z;
            return this;
        }

        public WindowStyle setTitleLabelStyle(Label.LabelStyle labelStyle) {
            this.titleLabelStyle = labelStyle;
            return this;
        }

        public WindowStyle setDefaultPadding(float f) {
            this.defaultPadding = f;
            return this;
        }

        public WindowStyle setScrollPaneStyle(ScrollPane.ScrollPaneStyle scrollPaneStyle) {
            this.scrollPaneStyle = scrollPaneStyle;
            return this;
        }

        public WindowStyle setResizeable(boolean z) {
            this.resizeable = z;
            return this;
        }

        public WindowStyle setResizeHasMinSize(boolean z) {
            this.resizeHasMinSize = z;
            return this;
        }
    }
}
