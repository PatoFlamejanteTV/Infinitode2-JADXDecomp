package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/SelectBox.class */
public class SelectBox<T> extends Widget implements Disableable {
    static final Vector2 temp = new Vector2();
    SelectBoxStyle style;
    final Array<T> items;
    SelectBoxScrollPane<T> scrollPane;
    private float prefWidth;
    private float prefHeight;
    private ClickListener clickListener;
    boolean disabled;
    private int alignment;
    boolean selectedPrefWidth;
    final ArraySelection<T> selection;

    public SelectBox(Skin skin) {
        this((SelectBoxStyle) skin.get(SelectBoxStyle.class));
    }

    public SelectBox(Skin skin, String str) {
        this((SelectBoxStyle) skin.get(str, SelectBoxStyle.class));
    }

    public SelectBox(SelectBoxStyle selectBoxStyle) {
        this.items = new Array<>();
        this.alignment = 8;
        this.selection = new ArraySelection(this.items) { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.1
            @Override // com.badlogic.gdx.scenes.scene2d.utils.Selection
            public boolean fireChangeEvent() {
                if (SelectBox.this.selectedPrefWidth) {
                    SelectBox.this.invalidateHierarchy();
                }
                return super.fireChangeEvent();
            }
        };
        setStyle(selectBoxStyle);
        setSize(getPrefWidth(), getPrefHeight());
        this.selection.setActor(this);
        this.selection.setRequired(true);
        this.scrollPane = newScrollPane();
        ClickListener clickListener = new ClickListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.2
            @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener, com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if ((i == 0 && i2 != 0) || SelectBox.this.isDisabled()) {
                    return false;
                }
                if (SelectBox.this.scrollPane.hasParent()) {
                    SelectBox.this.hideScrollPane();
                    return true;
                }
                SelectBox.this.showScrollPane();
                return true;
            }
        };
        this.clickListener = clickListener;
        addListener(clickListener);
    }

    protected SelectBoxScrollPane<T> newScrollPane() {
        return new SelectBoxScrollPane<>(this);
    }

    public void setMaxListCount(int i) {
        this.scrollPane.maxListCount = i;
    }

    public int getMaxListCount() {
        return this.scrollPane.maxListCount;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.scenes.scene2d.Actor
    public void setStage(Stage stage) {
        if (stage == null) {
            this.scrollPane.hide();
        }
        super.setStage(stage);
    }

    public void setStyle(SelectBoxStyle selectBoxStyle) {
        if (selectBoxStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = selectBoxStyle;
        if (this.scrollPane != null) {
            this.scrollPane.setStyle(selectBoxStyle.scrollStyle);
            this.scrollPane.list.setStyle(selectBoxStyle.listStyle);
        }
        invalidateHierarchy();
    }

    public SelectBoxStyle getStyle() {
        return this.style;
    }

    public void setItems(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float prefWidth = getPrefWidth();
        this.items.clear();
        this.items.addAll(tArr);
        this.selection.validate();
        this.scrollPane.list.setItems(this.items);
        invalidate();
        if (prefWidth != getPrefWidth()) {
            invalidateHierarchy();
        }
    }

    public void setItems(Array<T> array) {
        if (array == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float prefWidth = getPrefWidth();
        if (array != this.items) {
            this.items.clear();
            this.items.addAll(array);
        }
        this.selection.validate();
        this.scrollPane.list.setItems(this.items);
        invalidate();
        if (prefWidth != getPrefWidth()) {
            invalidateHierarchy();
        }
    }

    public void clearItems() {
        if (this.items.size == 0) {
            return;
        }
        this.items.clear();
        this.selection.clear();
        this.scrollPane.list.clearItems();
        invalidateHierarchy();
    }

    public Array<T> getItems() {
        return this.items;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        Drawable drawable = this.style.background;
        BitmapFont bitmapFont = this.style.font;
        if (drawable != null) {
            this.prefHeight = Math.max(((drawable.getTopHeight() + drawable.getBottomHeight()) + bitmapFont.getCapHeight()) - (bitmapFont.getDescent() * 2.0f), drawable.getMinHeight());
        } else {
            this.prefHeight = bitmapFont.getCapHeight() - (bitmapFont.getDescent() * 2.0f);
        }
        Pool pool = Pools.get(GlyphLayout.class);
        GlyphLayout glyphLayout = (GlyphLayout) pool.obtain();
        if (this.selectedPrefWidth) {
            this.prefWidth = 0.0f;
            if (drawable != null) {
                this.prefWidth = drawable.getLeftWidth() + drawable.getRightWidth();
            }
            T selected = getSelected();
            if (selected != null) {
                glyphLayout.setText(bitmapFont, toString(selected));
                this.prefWidth += glyphLayout.width;
            }
        } else {
            float f = 0.0f;
            for (int i = 0; i < this.items.size; i++) {
                glyphLayout.setText(bitmapFont, toString(this.items.get(i)));
                f = Math.max(glyphLayout.width, f);
            }
            this.prefWidth = f;
            if (drawable != null) {
                this.prefWidth = Math.max(this.prefWidth + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
            }
            List.ListStyle listStyle = this.style.listStyle;
            ScrollPane.ScrollPaneStyle scrollPaneStyle = this.style.scrollStyle;
            float leftWidth = f + listStyle.selection.getLeftWidth() + listStyle.selection.getRightWidth();
            Drawable drawable2 = scrollPaneStyle.background;
            if (drawable2 != null) {
                leftWidth = Math.max(leftWidth + drawable2.getLeftWidth() + drawable2.getRightWidth(), drawable2.getMinWidth());
            }
            if (this.scrollPane == null || !this.scrollPane.disableY) {
                leftWidth += Math.max(this.style.scrollStyle.vScroll != null ? this.style.scrollStyle.vScroll.getMinWidth() : 0.0f, this.style.scrollStyle.vScrollKnob != null ? this.style.scrollStyle.vScrollKnob.getMinWidth() : 0.0f);
            }
            this.prefWidth = Math.max(this.prefWidth, leftWidth);
        }
        pool.free(glyphLayout);
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        return (!isDisabled() || this.style.backgroundDisabled == null) ? (!this.scrollPane.hasParent() || this.style.backgroundOpen == null) ? (!isOver() || this.style.backgroundOver == null) ? this.style.background : this.style.backgroundOver : this.style.backgroundOpen : this.style.backgroundDisabled;
    }

    protected Color getFontColor() {
        return (!isDisabled() || this.style.disabledFontColor == null) ? (this.style.overFontColor == null || !(isOver() || this.scrollPane.hasParent())) ? this.style.fontColor : this.style.overFontColor : this.style.disabledFontColor;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        float f2;
        validate();
        Drawable backgroundDrawable = getBackgroundDrawable();
        Color fontColor = getFontColor();
        BitmapFont bitmapFont = this.style.font;
        Color color = getColor();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        if (backgroundDrawable != null) {
            backgroundDrawable.draw(batch, x, y, width, height);
        }
        T first = this.selection.first();
        if (first != null) {
            if (backgroundDrawable != null) {
                width -= backgroundDrawable.getLeftWidth() + backgroundDrawable.getRightWidth();
                float bottomHeight = height - (backgroundDrawable.getBottomHeight() + backgroundDrawable.getTopHeight());
                x += backgroundDrawable.getLeftWidth();
                f2 = y + ((int) ((bottomHeight / 2.0f) + backgroundDrawable.getBottomHeight() + (bitmapFont.getData().capHeight / 2.0f)));
            } else {
                f2 = y + ((int) ((height / 2.0f) + (bitmapFont.getData().capHeight / 2.0f)));
            }
            bitmapFont.setColor(fontColor.r, fontColor.g, fontColor.f888b, fontColor.f889a * f);
            drawItem(batch, bitmapFont, first, x, f2, width);
        }
    }

    protected GlyphLayout drawItem(Batch batch, BitmapFont bitmapFont, T t, float f, float f2, float f3) {
        String selectBox = toString(t);
        return bitmapFont.draw(batch, selectBox, f, f2, 0, selectBox.length(), f3, this.alignment, false, "...");
    }

    public void setAlignment(int i) {
        this.alignment = i;
    }

    public ArraySelection<T> getSelection() {
        return this.selection;
    }

    @Null
    public T getSelected() {
        return this.selection.first();
    }

    public void setSelected(@Null T t) {
        if (this.items.contains(t, false)) {
            this.selection.set(t);
        } else if (this.items.size > 0) {
            this.selection.set(this.items.first());
        } else {
            this.selection.clear();
        }
    }

    public int getSelectedIndex() {
        OrderedSet<T> items = this.selection.items();
        if (items.size == 0) {
            return -1;
        }
        return this.items.indexOf(items.first(), false);
    }

    public void setSelectedIndex(int i) {
        this.selection.set(this.items.get(i));
    }

    public void setSelectedPrefWidth(boolean z) {
        this.selectedPrefWidth = z;
    }

    public boolean getSelectedPrefWidth() {
        return this.selectedPrefWidth;
    }

    public float getMaxSelectedPrefWidth() {
        GlyphLayout glyphLayout = (GlyphLayout) Pools.get(GlyphLayout.class).obtain();
        float f = 0.0f;
        for (int i = 0; i < this.items.size; i++) {
            glyphLayout.setText(this.style.font, toString(this.items.get(i)));
            f = Math.max(glyphLayout.width, f);
        }
        Drawable drawable = this.style.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
        }
        return f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        if (z && !this.disabled) {
            hideScrollPane();
        }
        this.disabled = z;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        validate();
        return this.prefWidth;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        validate();
        return this.prefHeight;
    }

    protected String toString(T t) {
        return t.toString();
    }

    @Deprecated
    public void showList() {
        showScrollPane();
    }

    public void showScrollPane() {
        if (this.items.size != 0 && getStage() != null) {
            this.scrollPane.show(getStage());
        }
    }

    @Deprecated
    public void hideList() {
        hideScrollPane();
    }

    public void hideScrollPane() {
        this.scrollPane.hide();
    }

    public List<T> getList() {
        return this.scrollPane.list;
    }

    public void setScrollingDisabled(boolean z) {
        this.scrollPane.setScrollingDisabled(true, z);
        invalidateHierarchy();
    }

    public SelectBoxScrollPane getScrollPane() {
        return this.scrollPane;
    }

    public boolean isOver() {
        return this.clickListener.isOver();
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    protected void onShow(Actor actor, boolean z) {
        actor.getColor().f889a = 0.0f;
        actor.addAction(Actions.fadeIn(0.3f, Interpolation.fade));
    }

    protected void onHide(Actor actor) {
        actor.getColor().f889a = 1.0f;
        actor.addAction(Actions.sequence(Actions.fadeOut(0.15f, Interpolation.fade), Actions.removeActor()));
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/SelectBox$SelectBoxScrollPane.class */
    public static class SelectBoxScrollPane<T> extends ScrollPane {
        final SelectBox<T> selectBox;
        int maxListCount;
        private final Vector2 stagePosition;
        final List<T> list;
        private InputListener hideListener;
        private Actor previousScrollFocus;

        public SelectBoxScrollPane(final SelectBox<T> selectBox) {
            super((Actor) null, selectBox.style.scrollStyle);
            this.stagePosition = new Vector2();
            this.selectBox = selectBox;
            setOverscroll(false, false);
            setFadeScrollBars(false);
            setScrollingDisabled(true, false);
            this.list = newList();
            this.list.setTouchable(Touchable.disabled);
            this.list.setTypeToSelect(true);
            setActor(this.list);
            this.list.addListener(new ClickListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxScrollPane.1
                @Override // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    T selected = SelectBoxScrollPane.this.list.getSelected();
                    if (selected != null) {
                        selectBox.selection.items().clear(51);
                    }
                    selectBox.selection.choose(selected);
                    SelectBoxScrollPane.this.hide();
                }

                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                    int itemIndexAt = SelectBoxScrollPane.this.list.getItemIndexAt(f2);
                    if (itemIndexAt != -1) {
                        SelectBoxScrollPane.this.list.setSelectedIndex(itemIndexAt);
                        return true;
                    }
                    return true;
                }
            });
            addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxScrollPane.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                    Object selected;
                    if ((actor == null || !SelectBoxScrollPane.this.isAscendantOf(actor)) && (selected = selectBox.getSelected()) != null) {
                        SelectBoxScrollPane.this.list.selection.set(selected);
                    }
                }
            });
            this.hideListener = new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxScrollPane.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                    if (SelectBoxScrollPane.this.isAscendantOf(inputEvent.getTarget())) {
                        return false;
                    }
                    SelectBoxScrollPane.this.list.selection.set(selectBox.getSelected());
                    SelectBoxScrollPane.this.hide();
                    return false;
                }

                /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0001. Please report as an issue. */
                @Override // com.badlogic.gdx.scenes.scene2d.InputListener
                public boolean keyDown(InputEvent inputEvent, int i) {
                    switch (i) {
                        case 66:
                        case 160:
                            selectBox.selection.choose(SelectBoxScrollPane.this.list.getSelected());
                        case 111:
                            SelectBoxScrollPane.this.hide();
                            inputEvent.stop();
                            return true;
                        default:
                            return false;
                    }
                }
            };
        }

        protected List<T> newList() {
            return new List<T>(this.selectBox.style.listStyle) { // from class: com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxScrollPane.4
                @Override // com.badlogic.gdx.scenes.scene2d.ui.List
                public String toString(T t) {
                    return SelectBoxScrollPane.this.selectBox.toString(t);
                }
            };
        }

        public void show(Stage stage) {
            if (this.list.isTouchable()) {
                return;
            }
            stage.addActor(this);
            stage.addCaptureListener(this.hideListener);
            stage.addListener(this.list.getKeyListener());
            this.selectBox.localToStageCoordinates(this.stagePosition.set(0.0f, 0.0f));
            float itemHeight = this.list.getItemHeight();
            float min = itemHeight * (this.maxListCount <= 0 ? this.selectBox.items.size : Math.min(this.maxListCount, this.selectBox.items.size));
            Drawable drawable = getStyle().background;
            if (drawable != null) {
                min += drawable.getTopHeight() + drawable.getBottomHeight();
            }
            Drawable drawable2 = this.list.getStyle().background;
            if (drawable2 != null) {
                min += drawable2.getTopHeight() + drawable2.getBottomHeight();
            }
            float f = this.stagePosition.y;
            float height = (stage.getHeight() - f) - this.selectBox.getHeight();
            boolean z = true;
            if (min > f) {
                if (height > f) {
                    z = false;
                    min = Math.min(min, height);
                } else {
                    min = f;
                }
            }
            if (z) {
                setY(this.stagePosition.y - min);
            } else {
                setY(this.stagePosition.y + this.selectBox.getHeight());
            }
            setHeight(min);
            validate();
            float max = Math.max(getPrefWidth(), this.selectBox.getWidth());
            setWidth(max);
            float f2 = this.stagePosition.x;
            float f3 = f2;
            if (f2 + max > stage.getWidth()) {
                float width = f3 - ((getWidth() - this.selectBox.getWidth()) - 1.0f);
                f3 = width;
                if (width < 0.0f) {
                    f3 = 0.0f;
                }
            }
            setX(f3);
            validate();
            scrollTo(0.0f, (this.list.getHeight() - (this.selectBox.getSelectedIndex() * itemHeight)) - (itemHeight / 2.0f), 0.0f, 0.0f, true, true);
            updateVisualScroll();
            this.previousScrollFocus = null;
            Actor scrollFocus = stage.getScrollFocus();
            if (scrollFocus != null && !scrollFocus.isDescendantOf(this)) {
                this.previousScrollFocus = scrollFocus;
            }
            stage.setScrollFocus(this);
            this.list.selection.set(this.selectBox.getSelected());
            this.list.setTouchable(Touchable.enabled);
            clearActions();
            this.selectBox.onShow(this, z);
        }

        public void hide() {
            if (this.list.isTouchable() && hasParent()) {
                this.list.setTouchable(Touchable.disabled);
                Stage stage = getStage();
                if (stage != null) {
                    stage.removeCaptureListener(this.hideListener);
                    stage.removeListener(this.list.getKeyListener());
                    if (this.previousScrollFocus != null && this.previousScrollFocus.getStage() == null) {
                        this.previousScrollFocus = null;
                    }
                    Actor scrollFocus = stage.getScrollFocus();
                    if (scrollFocus == null || isAscendantOf(scrollFocus)) {
                        stage.setScrollFocus(this.previousScrollFocus);
                    }
                }
                clearActions();
                this.selectBox.onHide(this);
            }
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane, com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
        public void draw(Batch batch, float f) {
            this.selectBox.localToStageCoordinates(SelectBox.temp.set(0.0f, 0.0f));
            if (!SelectBox.temp.equals(this.stagePosition)) {
                hide();
            }
            super.draw(batch, f);
        }

        @Override // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane, com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
        public void act(float f) {
            super.act(f);
            toFront();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.badlogic.gdx.scenes.scene2d.Group, com.badlogic.gdx.scenes.scene2d.Actor
        public void setStage(Stage stage) {
            Stage stage2 = getStage();
            if (stage2 != null) {
                stage2.removeCaptureListener(this.hideListener);
                stage2.removeListener(this.list.getKeyListener());
            }
            super.setStage(stage);
        }

        public List<T> getList() {
            return this.list;
        }

        public SelectBox<T> getSelectBox() {
            return this.selectBox;
        }
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/SelectBox$SelectBoxStyle.class */
    public static class SelectBoxStyle {
        public BitmapFont font;
        public Color fontColor = new Color(1.0f, 1.0f, 1.0f, 1.0f);

        @Null
        public Color overFontColor;

        @Null
        public Color disabledFontColor;

        @Null
        public Drawable background;
        public ScrollPane.ScrollPaneStyle scrollStyle;
        public List.ListStyle listStyle;

        @Null
        public Drawable backgroundOver;

        @Null
        public Drawable backgroundOpen;

        @Null
        public Drawable backgroundDisabled;

        public SelectBoxStyle() {
        }

        public SelectBoxStyle(BitmapFont bitmapFont, Color color, @Null Drawable drawable, ScrollPane.ScrollPaneStyle scrollPaneStyle, List.ListStyle listStyle) {
            this.font = bitmapFont;
            this.fontColor.set(color);
            this.background = drawable;
            this.scrollStyle = scrollPaneStyle;
            this.listStyle = listStyle;
        }

        public SelectBoxStyle(SelectBoxStyle selectBoxStyle) {
            this.font = selectBoxStyle.font;
            this.fontColor.set(selectBoxStyle.fontColor);
            if (selectBoxStyle.overFontColor != null) {
                this.overFontColor = new Color(selectBoxStyle.overFontColor);
            }
            if (selectBoxStyle.disabledFontColor != null) {
                this.disabledFontColor = new Color(selectBoxStyle.disabledFontColor);
            }
            this.background = selectBoxStyle.background;
            this.scrollStyle = new ScrollPane.ScrollPaneStyle(selectBoxStyle.scrollStyle);
            this.listStyle = new List.ListStyle(selectBoxStyle.listStyle);
            this.backgroundOver = selectBoxStyle.backgroundOver;
            this.backgroundOpen = selectBoxStyle.backgroundOpen;
            this.backgroundDisabled = selectBoxStyle.backgroundDisabled;
        }
    }
}
