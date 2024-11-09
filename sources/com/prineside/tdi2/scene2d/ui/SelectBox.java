package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.Stage;
import com.prineside.tdi2.scene2d.Touchable;
import com.prineside.tdi2.scene2d.actions.Actions;
import com.prineside.tdi2.scene2d.ui.List;
import com.prineside.tdi2.scene2d.ui.ScrollPane;
import com.prineside.tdi2.scene2d.utils.ArraySelection;
import com.prineside.tdi2.scene2d.utils.ClickListener;
import com.prineside.tdi2.scene2d.utils.Disableable;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/SelectBox.class */
public class SelectBox<T> extends Widget implements Disableable {
    static final Vector2 j = new Vector2();
    SelectBoxStyle k;
    SelectBoxScrollPane<T> m;
    private float p;
    private float q;
    private ClickListener r;
    private boolean s;
    boolean n;
    final Array<T> l = new Array<>();
    private int t = 8;
    final ArraySelection<T> o = new ArraySelection(this.l) { // from class: com.prineside.tdi2.scene2d.ui.SelectBox.1
        @Override // com.prineside.tdi2.scene2d.utils.Selection
        public boolean fireChangeEvent() {
            if (SelectBox.this.n) {
                SelectBox.this.invalidateHierarchy();
            }
            return super.fireChangeEvent();
        }
    };

    public SelectBox(SelectBoxStyle selectBoxStyle) {
        setStyle(selectBoxStyle);
        setSize(getPrefWidth(), getPrefHeight());
        this.o.setActor(this);
        this.o.setRequired(true);
        this.m = b();
        ClickListener clickListener = new ClickListener() { // from class: com.prineside.tdi2.scene2d.ui.SelectBox.2
            @Override // com.prineside.tdi2.scene2d.utils.ClickListener, com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if ((i == 0 && i2 != 0) || SelectBox.this.isDisabled()) {
                    return false;
                }
                if (SelectBox.this.m.hasParent()) {
                    SelectBox.this.hideScrollPane();
                    return true;
                }
                SelectBox.this.showScrollPane();
                return true;
            }
        };
        this.r = clickListener;
        addListener(clickListener);
    }

    private SelectBoxScrollPane<T> b() {
        return new SelectBoxScrollPane<>(this);
    }

    public void setMaxListCount(int i) {
        this.m.F = i;
    }

    public int getMaxListCount() {
        return this.m.F;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.Actor
    public final void a(Stage stage) {
        if (stage == null) {
            this.m.hide();
        }
        super.a(stage);
    }

    public void setStyle(SelectBoxStyle selectBoxStyle) {
        if (selectBoxStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.k = selectBoxStyle;
        if (this.m != null) {
            this.m.setStyle(selectBoxStyle.scrollStyle);
            this.m.G.setStyle(selectBoxStyle.listStyle);
        }
        invalidateHierarchy();
    }

    public SelectBoxStyle getStyle() {
        return this.k;
    }

    public void setItems(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float prefWidth = getPrefWidth();
        this.l.clear();
        this.l.addAll(tArr);
        this.o.validate();
        this.m.G.setItems(this.l);
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
        if (array != this.l) {
            this.l.clear();
            this.l.addAll(array);
        }
        this.o.validate();
        this.m.G.setItems(this.l);
        invalidate();
        if (prefWidth != getPrefWidth()) {
            invalidateHierarchy();
        }
    }

    public void clearItems() {
        if (this.l.size == 0) {
            return;
        }
        this.l.clear();
        this.o.clear();
        this.m.G.clearItems();
        invalidateHierarchy();
    }

    public Array<T> getItems() {
        return this.l;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        Drawable drawable = this.k.background;
        BitmapFont bitmapFont = this.k.font;
        if (drawable != null) {
            this.q = Math.max(((drawable.getTopHeight() + drawable.getBottomHeight()) + bitmapFont.getCapHeight()) - (bitmapFont.getDescent() * 2.0f), drawable.getMinHeight());
        } else {
            this.q = bitmapFont.getCapHeight() - (bitmapFont.getDescent() * 2.0f);
        }
        Pool pool = Pools.get(GlyphLayout.class);
        GlyphLayout glyphLayout = (GlyphLayout) pool.obtain();
        if (this.n) {
            this.p = 0.0f;
            if (drawable != null) {
                this.p = drawable.getLeftWidth() + drawable.getRightWidth();
            }
            T selected = getSelected();
            if (selected != null) {
                glyphLayout.setText(bitmapFont, a(selected));
                this.p += glyphLayout.width;
            }
        } else {
            float f = 0.0f;
            for (int i = 0; i < this.l.size; i++) {
                glyphLayout.setText(bitmapFont, a(this.l.get(i)));
                f = Math.max(glyphLayout.width, f);
            }
            this.p = f;
            if (drawable != null) {
                this.p = Math.max(this.p + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
            }
            List.ListStyle listStyle = this.k.listStyle;
            ScrollPane.ScrollPaneStyle scrollPaneStyle = this.k.scrollStyle;
            float leftWidth = f + listStyle.selection.getLeftWidth() + listStyle.selection.getRightWidth();
            Drawable drawable2 = scrollPaneStyle.background;
            if (drawable2 != null) {
                leftWidth = Math.max(leftWidth + drawable2.getLeftWidth() + drawable2.getRightWidth(), drawable2.getMinWidth());
            }
            if (this.m == null || !this.m.C) {
                leftWidth += Math.max(this.k.scrollStyle.vScroll != null ? this.k.scrollStyle.vScroll.getMinWidth() : 0.0f, this.k.scrollStyle.vScrollKnob != null ? this.k.scrollStyle.vScrollKnob.getMinWidth() : 0.0f);
            }
            this.p = Math.max(this.p, leftWidth);
        }
        pool.free(glyphLayout);
    }

    @Null
    private Drawable c() {
        return (!isDisabled() || this.k.backgroundDisabled == null) ? (!this.m.hasParent() || this.k.backgroundOpen == null) ? (!isOver() || this.k.backgroundOver == null) ? this.k.background : this.k.backgroundOver : this.k.backgroundOpen : this.k.backgroundDisabled;
    }

    private Color d() {
        return (!isDisabled() || this.k.disabledFontColor == null) ? (this.k.overFontColor == null || !(isOver() || this.m.hasParent())) ? this.k.fontColor : this.k.overFontColor : this.k.disabledFontColor;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        float f2;
        validate();
        Drawable c = c();
        Color d = d();
        BitmapFont bitmapFont = this.k.font;
        Color color = getColor();
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
        if (c != null) {
            c.draw(batch, x, y, width, height);
        }
        T first = this.o.first();
        if (first != null) {
            if (c != null) {
                width -= c.getLeftWidth() + c.getRightWidth();
                float bottomHeight = height - (c.getBottomHeight() + c.getTopHeight());
                x += c.getLeftWidth();
                f2 = y + ((int) ((bottomHeight / 2.0f) + c.getBottomHeight() + (bitmapFont.getData().capHeight / 2.0f)));
            } else {
                f2 = y + ((int) ((height / 2.0f) + (bitmapFont.getData().capHeight / 2.0f)));
            }
            bitmapFont.setColor(d.r, d.g, d.f888b, d.f889a * f);
            a(batch, bitmapFont, first, x, f2, width);
        }
    }

    private GlyphLayout a(Batch batch, BitmapFont bitmapFont, T t, float f, float f2, float f3) {
        String a2 = a(t);
        return bitmapFont.draw(batch, a2, f, f2, 0, a2.length(), f3, this.t, false, "...");
    }

    public void setAlignment(int i) {
        this.t = i;
    }

    public ArraySelection<T> getSelection() {
        return this.o;
    }

    @Null
    public T getSelected() {
        return this.o.first();
    }

    public void setSelected(@Null T t) {
        if (this.l.contains(t, false)) {
            this.o.set(t);
        } else if (this.l.size > 0) {
            this.o.set(this.l.first());
        } else {
            this.o.clear();
        }
    }

    public int getSelectedIndex() {
        OrderedSet<T> items = this.o.items();
        if (items.size == 0) {
            return -1;
        }
        return this.l.indexOf(items.first(), false);
    }

    public void setSelectedIndex(int i) {
        this.o.set(this.l.get(i));
    }

    public void setSelectedPrefWidth(boolean z) {
        this.n = z;
    }

    public boolean getSelectedPrefWidth() {
        return this.n;
    }

    public float getMaxSelectedPrefWidth() {
        GlyphLayout glyphLayout = (GlyphLayout) Pools.get(GlyphLayout.class).obtain();
        float f = 0.0f;
        for (int i = 0; i < this.l.size; i++) {
            glyphLayout.setText(this.k.font, a(this.l.get(i)));
            f = Math.max(glyphLayout.width, f);
        }
        Drawable drawable = this.k.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
        }
        return f;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        if (z && !this.s) {
            hideScrollPane();
        }
        this.s = z;
    }

    @Override // com.prineside.tdi2.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.s;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        validate();
        return this.p;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        validate();
        return this.q;
    }

    protected static String a(T t) {
        return t.toString();
    }

    @Deprecated
    public void showList() {
        showScrollPane();
    }

    public void showScrollPane() {
        if (this.l.size != 0 && getStage() != null) {
            this.m.show(getStage());
        }
    }

    @Deprecated
    public void hideList() {
        hideScrollPane();
    }

    public void hideScrollPane() {
        this.m.hide();
    }

    public List<T> getList() {
        return this.m.G;
    }

    public void setScrollingDisabled(boolean z) {
        this.m.setScrollingDisabled(true, z);
        invalidateHierarchy();
    }

    public SelectBoxScrollPane getScrollPane() {
        return this.m;
    }

    public boolean isOver() {
        return this.r.isOver();
    }

    public ClickListener getClickListener() {
        return this.r;
    }

    protected static void a(Actor actor) {
        actor.getColor().f889a = 0.0f;
        actor.addAction(Actions.fadeIn(0.3f, Interpolation.fade));
    }

    protected static void b(Actor actor) {
        actor.getColor().f889a = 1.0f;
        actor.addAction(Actions.sequence(Actions.fadeOut(0.15f, Interpolation.fade), Actions.removeActor()));
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/SelectBox$SelectBoxScrollPane.class */
    public static class SelectBoxScrollPane<T> extends ScrollPane {
        final SelectBox<T> E;
        int F;
        private final Vector2 H;
        final List<T> G;
        private InputListener I;
        private Actor J;

        public SelectBoxScrollPane(final SelectBox<T> selectBox) {
            super(null, selectBox.k.scrollStyle);
            this.H = new Vector2();
            this.E = selectBox;
            setOverscroll(false, false);
            setFadeScrollBars(false);
            setScrollingDisabled(true, false);
            this.G = g();
            this.G.setTouchable(Touchable.disabled);
            this.G.setTypeToSelect(true);
            setActor(this.G);
            this.G.addListener(new ClickListener() { // from class: com.prineside.tdi2.scene2d.ui.SelectBox.SelectBoxScrollPane.1
                @Override // com.prineside.tdi2.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent, float f, float f2) {
                    T selected = SelectBoxScrollPane.this.G.getSelected();
                    if (selected != null) {
                        selectBox.o.items().clear(51);
                    }
                    selectBox.o.choose(selected);
                    SelectBoxScrollPane.this.hide();
                }

                @Override // com.prineside.tdi2.scene2d.InputListener
                public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                    int itemIndexAt = SelectBoxScrollPane.this.G.getItemIndexAt(f2);
                    if (itemIndexAt != -1) {
                        SelectBoxScrollPane.this.G.setSelectedIndex(itemIndexAt);
                        return true;
                    }
                    return true;
                }
            });
            addListener(new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.SelectBox.SelectBoxScrollPane.2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.prineside.tdi2.scene2d.InputListener
                public void exit(InputEvent inputEvent, float f, float f2, int i, @Null Actor actor) {
                    Object selected;
                    if ((actor == null || !SelectBoxScrollPane.this.isAscendantOf(actor)) && (selected = selectBox.getSelected()) != null) {
                        SelectBoxScrollPane.this.G.k.set(selected);
                    }
                }
            });
            this.I = new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.SelectBox.SelectBoxScrollPane.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.prineside.tdi2.scene2d.InputListener
                public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                    if (SelectBoxScrollPane.this.isAscendantOf(inputEvent.getTarget())) {
                        return false;
                    }
                    SelectBoxScrollPane.this.G.k.set(selectBox.getSelected());
                    SelectBoxScrollPane.this.hide();
                    return false;
                }

                /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0001. Please report as an issue. */
                @Override // com.prineside.tdi2.scene2d.InputListener
                public boolean keyDown(InputEvent inputEvent, int i) {
                    switch (i) {
                        case 66:
                        case 160:
                            selectBox.o.choose(SelectBoxScrollPane.this.G.getSelected());
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

        private List<T> g() {
            return new List<T>(this.E.k.listStyle) { // from class: com.prineside.tdi2.scene2d.ui.SelectBox.SelectBoxScrollPane.4
                @Override // com.prineside.tdi2.scene2d.ui.List
                public String toString(T t) {
                    return SelectBox.a(t);
                }
            };
        }

        public void show(Stage stage) {
            if (this.G.isTouchable()) {
                return;
            }
            stage.addActor(this);
            stage.addCaptureListener(this.I);
            stage.addListener(this.G.getKeyListener());
            this.E.localToStageCoordinates(this.H.set(0.0f, 0.0f));
            float itemHeight = this.G.getItemHeight();
            float min = itemHeight * (this.F <= 0 ? this.E.l.size : Math.min(this.F, this.E.l.size));
            Drawable drawable = getStyle().background;
            if (drawable != null) {
                min += drawable.getTopHeight() + drawable.getBottomHeight();
            }
            Drawable drawable2 = this.G.getStyle().background;
            if (drawable2 != null) {
                min += drawable2.getTopHeight() + drawable2.getBottomHeight();
            }
            float f = this.H.y;
            float height = (stage.getHeight() - f) - this.E.getHeight();
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
                setY(this.H.y - min);
            } else {
                setY(this.H.y + this.E.getHeight());
            }
            setHeight(min);
            validate();
            float max = Math.max(getPrefWidth(), this.E.getWidth());
            setWidth(max);
            float f2 = this.H.x;
            float f3 = f2;
            if (f2 + max > stage.getWidth()) {
                float width = f3 - ((getWidth() - this.E.getWidth()) - 1.0f);
                f3 = width;
                if (width < 0.0f) {
                    f3 = 0.0f;
                }
            }
            setX(f3);
            validate();
            scrollTo(0.0f, (this.G.getHeight() - (this.E.getSelectedIndex() * itemHeight)) - (itemHeight / 2.0f), 0.0f, 0.0f, true, true);
            updateVisualScroll();
            this.J = null;
            Actor scrollFocus = stage.getScrollFocus();
            if (scrollFocus != null && !scrollFocus.isDescendantOf(this)) {
                this.J = scrollFocus;
            }
            stage.setScrollFocus(this);
            this.G.k.set(this.E.getSelected());
            this.G.setTouchable(Touchable.enabled);
            clearActions();
            SelectBox.a((Actor) this);
        }

        public void hide() {
            if (this.G.isTouchable() && hasParent()) {
                this.G.setTouchable(Touchable.disabled);
                Stage stage = getStage();
                if (stage != null) {
                    stage.removeCaptureListener(this.I);
                    stage.removeListener(this.G.getKeyListener());
                    if (this.J != null && this.J.getStage() == null) {
                        this.J = null;
                    }
                    Actor scrollFocus = stage.getScrollFocus();
                    if (scrollFocus == null || isAscendantOf(scrollFocus)) {
                        stage.setScrollFocus(this.J);
                    }
                }
                clearActions();
                SelectBox.b(this);
            }
        }

        @Override // com.prineside.tdi2.scene2d.ui.ScrollPane, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
        public void draw(Batch batch, float f) {
            this.E.localToStageCoordinates(SelectBox.j.set(0.0f, 0.0f));
            if (!SelectBox.j.equals(this.H)) {
                hide();
            }
            super.draw(batch, f);
        }

        @Override // com.prineside.tdi2.scene2d.ui.ScrollPane, com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
        public void act(float f) {
            super.act(f);
            toFront();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.prineside.tdi2.scene2d.Group, com.prineside.tdi2.scene2d.Actor
        public final void a(Stage stage) {
            Stage stage2 = getStage();
            if (stage2 != null) {
                stage2.removeCaptureListener(this.I);
                stage2.removeListener(this.G.getKeyListener());
            }
            super.a(stage);
        }

        public List<T> getList() {
            return this.G;
        }

        public SelectBox<T> getSelectBox() {
            return this.E;
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/SelectBox$SelectBoxStyle.class */
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
