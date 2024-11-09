package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Window.class */
public class Window extends Table {
    private static final Vector2 tmpPosition = new Vector2();
    private static final Vector2 tmpSize = new Vector2();
    private static final int MOVE = 32;
    private WindowStyle style;
    boolean isMovable;
    boolean isModal;
    boolean isResizable;
    int resizeBorder;
    boolean keepWithinStage;
    Label titleLabel;
    Table titleTable;
    boolean drawTitleTable;
    protected int edge;
    protected boolean dragging;

    public Window(String str, Skin skin) {
        this(str, (WindowStyle) skin.get(WindowStyle.class));
        setSkin(skin);
    }

    public Window(String str, Skin skin, String str2) {
        this(str, (WindowStyle) skin.get(str2, WindowStyle.class));
        setSkin(skin);
    }

    public Window(String str, WindowStyle windowStyle) {
        this.isMovable = true;
        this.resizeBorder = 8;
        this.keepWithinStage = true;
        if (str == null) {
            throw new IllegalArgumentException("title cannot be null.");
        }
        setTouchable(Touchable.enabled);
        setClip(true);
        this.titleLabel = newLabel(str, new Label.LabelStyle(windowStyle.titleFont, windowStyle.titleFontColor));
        this.titleLabel.setEllipsis(true);
        this.titleTable = new Table() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Window.1
            @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
            public void draw(Batch batch, float f) {
                if (Window.this.drawTitleTable) {
                    super.draw(batch, f);
                }
            }
        };
        this.titleTable.add((Table) this.titleLabel).expandX().fillX().minWidth(0.0f);
        addActor(this.titleTable);
        setStyle(windowStyle);
        setWidth(150.0f);
        setHeight(150.0f);
        addCaptureListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Window.2
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                Window.this.toFront();
                return false;
            }
        });
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.Window.3
            float startX;
            float startY;
            float lastX;
            float lastY;

            private void updateEdge(float f, float f2) {
                float f3 = Window.this.resizeBorder / 2.0f;
                float width = Window.this.getWidth();
                float height = Window.this.getHeight();
                float padTop = Window.this.getPadTop();
                float padLeft = Window.this.getPadLeft();
                float padBottom = Window.this.getPadBottom();
                float padRight = width - Window.this.getPadRight();
                Window.this.edge = 0;
                if (Window.this.isResizable && f >= padLeft - f3 && f <= padRight + f3 && f2 >= padBottom - f3) {
                    if (f < padLeft + f3) {
                        Window.this.edge |= 8;
                    }
                    if (f > padRight - f3) {
                        Window.this.edge |= 16;
                    }
                    if (f2 < padBottom + f3) {
                        Window.this.edge |= 4;
                    }
                    if (Window.this.edge != 0) {
                        f3 += 25.0f;
                    }
                    if (f < padLeft + f3) {
                        Window.this.edge |= 8;
                    }
                    if (f > padRight - f3) {
                        Window.this.edge |= 16;
                    }
                    if (f2 < padBottom + f3) {
                        Window.this.edge |= 4;
                    }
                }
                if (!Window.this.isMovable || Window.this.edge != 0 || f2 > height || f2 < height - padTop || f < padLeft || f > padRight) {
                    return;
                }
                Window.this.edge = 32;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i2 == 0) {
                    updateEdge(f, f2);
                    Window.this.dragging = Window.this.edge != 0;
                    this.startX = f;
                    this.startY = f2;
                    this.lastX = f - Window.this.getWidth();
                    this.lastY = f2 - Window.this.getHeight();
                }
                return Window.this.edge != 0 || Window.this.isModal;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                Window.this.dragging = false;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                if (Window.this.dragging) {
                    float width = Window.this.getWidth();
                    float height = Window.this.getHeight();
                    float x = Window.this.getX();
                    float y = Window.this.getY();
                    float minWidth = Window.this.getMinWidth();
                    Window.this.getMaxWidth();
                    float minHeight = Window.this.getMinHeight();
                    Window.this.getMaxHeight();
                    Stage stage = Window.this.getStage();
                    boolean z = Window.this.keepWithinStage && stage != null && Window.this.getParent() == stage.getRoot();
                    if ((Window.this.edge & 32) != 0) {
                        x += f - this.startX;
                        y += f2 - this.startY;
                    }
                    if ((Window.this.edge & 8) != 0) {
                        float f3 = f - this.startX;
                        if (width - f3 < minWidth) {
                            f3 = -(minWidth - width);
                        }
                        if (z && x + f3 < 0.0f) {
                            f3 = -x;
                        }
                        width -= f3;
                        x += f3;
                    }
                    if ((Window.this.edge & 4) != 0) {
                        float f4 = f2 - this.startY;
                        if (height - f4 < minHeight) {
                            f4 = -(minHeight - height);
                        }
                        if (z && y + f4 < 0.0f) {
                            f4 = -y;
                        }
                        height -= f4;
                        y += f4;
                    }
                    if ((Window.this.edge & 16) != 0) {
                        float f5 = (f - this.lastX) - width;
                        if (width + f5 < minWidth) {
                            f5 = minWidth - width;
                        }
                        if (z && x + width + f5 > stage.getWidth()) {
                            f5 = (stage.getWidth() - x) - width;
                        }
                        width += f5;
                    }
                    if ((Window.this.edge & 2) != 0) {
                        float f6 = (f2 - this.lastY) - height;
                        if (height + f6 < minHeight) {
                            f6 = minHeight - height;
                        }
                        if (z && y + height + f6 > stage.getHeight()) {
                            f6 = (stage.getHeight() - y) - height;
                        }
                        height += f6;
                    }
                    Window.this.setBounds(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                updateEdge(f, f2);
                return Window.this.isModal;
            }

            public boolean scrolled(InputEvent inputEvent, float f, float f2, int i) {
                return Window.this.isModal;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent, int i) {
                return Window.this.isModal;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyUp(InputEvent inputEvent, int i) {
                return Window.this.isModal;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyTyped(InputEvent inputEvent, char c) {
                return Window.this.isModal;
            }
        });
    }

    protected Label newLabel(String str, Label.LabelStyle labelStyle) {
        return new Label(str, labelStyle);
    }

    public void setStyle(WindowStyle windowStyle) {
        if (windowStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = windowStyle;
        setBackground(windowStyle.background);
        this.titleLabel.setStyle(new Label.LabelStyle(windowStyle.titleFont, windowStyle.titleFontColor));
        invalidateHierarchy();
    }

    public WindowStyle getStyle() {
        return this.style;
    }

    public void keepWithinStage() {
        Stage stage;
        if (this.keepWithinStage && (stage = getStage()) != null) {
            Camera camera = stage.getCamera();
            if (!(camera instanceof OrthographicCamera)) {
                if (getParent() == stage.getRoot()) {
                    float width = stage.getWidth();
                    float height = stage.getHeight();
                    if (getX() < 0.0f) {
                        setX(0.0f);
                    }
                    if (getRight() > width) {
                        setX(width - getWidth());
                    }
                    if (getY() < 0.0f) {
                        setY(0.0f);
                    }
                    if (getTop() > height) {
                        setY(height - getHeight());
                        return;
                    }
                    return;
                }
                return;
            }
            OrthographicCamera orthographicCamera = (OrthographicCamera) camera;
            float width2 = stage.getWidth();
            float height2 = stage.getHeight();
            if (getX(16) - camera.position.x > (width2 / 2.0f) / orthographicCamera.zoom) {
                setPosition(camera.position.x + ((width2 / 2.0f) / orthographicCamera.zoom), getY(16), 16);
            }
            if (getX(8) - camera.position.x < ((-width2) / 2.0f) / orthographicCamera.zoom) {
                setPosition(camera.position.x - ((width2 / 2.0f) / orthographicCamera.zoom), getY(8), 8);
            }
            if (getY(2) - camera.position.y > (height2 / 2.0f) / orthographicCamera.zoom) {
                setPosition(getX(2), camera.position.y + ((height2 / 2.0f) / orthographicCamera.zoom), 2);
            }
            if (getY(4) - camera.position.y < ((-height2) / 2.0f) / orthographicCamera.zoom) {
                setPosition(getX(4), camera.position.y - ((height2 / 2.0f) / orthographicCamera.zoom), 4);
            }
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        Stage stage = getStage();
        if (stage != null) {
            if (stage.getKeyboardFocus() == null) {
                stage.setKeyboardFocus(this);
            }
            keepWithinStage();
            if (this.style.stageBackground != null) {
                stageToLocalCoordinates(tmpPosition.set(0.0f, 0.0f));
                stageToLocalCoordinates(tmpSize.set(stage.getWidth(), stage.getHeight()));
                drawStageBackground(batch, f, getX() + tmpPosition.x, getY() + tmpPosition.y, getX() + tmpSize.x, getY() + tmpSize.y);
            }
        }
        super.draw(batch, f);
    }

    protected void drawStageBackground(Batch batch, float f, float f2, float f3, float f4, float f5) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        this.style.stageBackground.draw(batch, f2, f3, f4, f5);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table
    public void drawBackground(Batch batch, float f, float f2, float f3) {
        super.drawBackground(batch, f, f2, f3);
        this.titleTable.getColor().f889a = getColor().f889a;
        float padTop = getPadTop();
        float padLeft = getPadLeft();
        this.titleTable.setSize((getWidth() - padLeft) - getPadRight(), padTop);
        this.titleTable.setPosition(padLeft, getHeight() - padTop);
        this.drawTitleTable = true;
        this.titleTable.draw(batch, f);
        this.drawTitleTable = false;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
    @Null
    public Actor hit(float f, float f2, boolean z) {
        Actor actor;
        if (!isVisible()) {
            return null;
        }
        Actor hit = super.hit(f, f2, z);
        if (hit == null && this.isModal && (!z || getTouchable() == Touchable.enabled)) {
            return this;
        }
        float height = getHeight();
        if (hit == null || hit == this) {
            return hit;
        }
        if (f2 <= height && f2 >= height - getPadTop() && f >= 0.0f && f <= getWidth()) {
            Actor actor2 = hit;
            while (true) {
                actor = actor2;
                if (actor.getParent() == this) {
                    break;
                }
                actor2 = actor.getParent();
            }
            if (getCell(actor) != null) {
                return this;
            }
        }
        return hit;
    }

    public boolean isMovable() {
        return this.isMovable;
    }

    public void setMovable(boolean z) {
        this.isMovable = z;
    }

    public boolean isModal() {
        return this.isModal;
    }

    public void setModal(boolean z) {
        this.isModal = z;
    }

    public void setKeepWithinStage(boolean z) {
        this.keepWithinStage = z;
    }

    public boolean isResizable() {
        return this.isResizable;
    }

    public void setResizable(boolean z) {
        this.isResizable = z;
    }

    public void setResizeBorder(int i) {
        this.resizeBorder = i;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Table, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        return Math.max(super.getPrefWidth(), this.titleTable.getPrefWidth() + getPadLeft() + getPadRight());
    }

    public Table getTitleTable() {
        return this.titleTable;
    }

    public Label getTitleLabel() {
        return this.titleLabel;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Window$WindowStyle.class */
    public static class WindowStyle {

        @Null
        public Drawable background;
        public BitmapFont titleFont;

        @Null
        public Color titleFontColor;

        @Null
        public Drawable stageBackground;

        public WindowStyle() {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public WindowStyle(BitmapFont bitmapFont, Color color, @Null Drawable drawable) {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.titleFont = bitmapFont;
            this.titleFontColor.set(color);
            this.background = drawable;
        }

        public WindowStyle(WindowStyle windowStyle) {
            this.titleFontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);
            this.titleFont = windowStyle.titleFont;
            if (windowStyle.titleFontColor != null) {
                this.titleFontColor = new Color(windowStyle.titleFontColor);
            }
            this.background = windowStyle.background;
            this.stageBackground = windowStyle.stageBackground;
        }
    }
}
