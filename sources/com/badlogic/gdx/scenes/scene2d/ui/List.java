package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/List.class */
public class List<T> extends Widget implements Cullable {
    ListStyle style;
    final Array<T> items;
    ArraySelection<T> selection;
    private Rectangle cullingArea;
    private float prefWidth;
    private float prefHeight;
    float itemHeight;
    private int alignment;
    int pressedIndex;
    int overIndex;
    private InputListener keyListener;
    boolean typeToSelect;

    public List(Skin skin) {
        this((ListStyle) skin.get(ListStyle.class));
    }

    public List(Skin skin, String str) {
        this((ListStyle) skin.get(str, ListStyle.class));
    }

    public List(ListStyle listStyle) {
        this.items = new Array<>();
        this.selection = new ArraySelection<>(this.items);
        this.alignment = 8;
        this.pressedIndex = -1;
        this.overIndex = -1;
        this.selection.setActor(this);
        this.selection.setRequired(true);
        setStyle(listStyle);
        setSize(getPrefWidth(), getPrefHeight());
        InputListener inputListener = new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.List.1
            long typeTimeout;
            String prefix;

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent, int i) {
                if (List.this.items.isEmpty()) {
                    return false;
                }
                switch (i) {
                    case 3:
                        List.this.setSelectedIndex(0);
                        return true;
                    case 19:
                        int indexOf = List.this.items.indexOf(List.this.getSelected(), false) - 1;
                        int i2 = indexOf;
                        if (indexOf < 0) {
                            i2 = List.this.items.size - 1;
                        }
                        List.this.setSelectedIndex(i2);
                        return true;
                    case 20:
                        int indexOf2 = List.this.items.indexOf(List.this.getSelected(), false) + 1;
                        int i3 = indexOf2;
                        if (indexOf2 >= List.this.items.size) {
                            i3 = 0;
                        }
                        List.this.setSelectedIndex(i3);
                        return true;
                    case 29:
                        if (UIUtils.ctrl() && List.this.selection.getMultiple()) {
                            List.this.selection.clear();
                            List.this.selection.addAll(List.this.items);
                            return true;
                        }
                        return false;
                    case 111:
                        if (List.this.getStage() != null) {
                            List.this.getStage().setKeyboardFocus(null);
                            return true;
                        }
                        return true;
                    case 123:
                        List.this.setSelectedIndex(List.this.items.size - 1);
                        return true;
                    default:
                        return false;
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean keyTyped(InputEvent inputEvent, char c) {
                if (!List.this.typeToSelect) {
                    return false;
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis > this.typeTimeout) {
                    this.prefix = "";
                }
                this.typeTimeout = currentTimeMillis + 300;
                this.prefix += Character.toLowerCase(c);
                int i = List.this.items.size;
                for (int i2 = 0; i2 < i; i2++) {
                    if (List.this.toString(List.this.items.get(i2)).toLowerCase().startsWith(this.prefix)) {
                        List.this.setSelectedIndex(i2);
                        return false;
                    }
                }
                return false;
            }
        };
        this.keyListener = inputListener;
        addListener(inputListener);
        addListener(new InputListener() { // from class: com.badlogic.gdx.scenes.scene2d.ui.List.2
            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                int itemIndexAt;
                if (i != 0 || i2 != 0 || List.this.selection.isDisabled()) {
                    return true;
                }
                if (List.this.getStage() != null) {
                    List.this.getStage().setKeyboardFocus(List.this);
                }
                if (List.this.items.size == 0 || (itemIndexAt = List.this.getItemIndexAt(f2)) == -1) {
                    return true;
                }
                List.this.selection.choose(List.this.items.get(itemIndexAt));
                List.this.pressedIndex = itemIndexAt;
                return true;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i == 0 && i2 == 0) {
                    List.this.pressedIndex = -1;
                }
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                List.this.overIndex = List.this.getItemIndexAt(f2);
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                List.this.overIndex = List.this.getItemIndexAt(f2);
                return false;
            }

            @Override // com.badlogic.gdx.scenes.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == 0) {
                    List.this.pressedIndex = -1;
                }
                if (i == -1) {
                    List.this.overIndex = -1;
                }
            }
        });
    }

    public void setStyle(ListStyle listStyle) {
        if (listStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = listStyle;
        invalidateHierarchy();
    }

    public ListStyle getStyle() {
        return this.style;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        BitmapFont bitmapFont = this.style.font;
        Drawable drawable = this.style.selection;
        this.itemHeight = bitmapFont.getCapHeight() - (bitmapFont.getDescent() * 2.0f);
        this.itemHeight += drawable.getTopHeight() + drawable.getBottomHeight();
        this.prefWidth = 0.0f;
        Pool pool = Pools.get(GlyphLayout.class);
        GlyphLayout glyphLayout = (GlyphLayout) pool.obtain();
        for (int i = 0; i < this.items.size; i++) {
            glyphLayout.setText(bitmapFont, toString(this.items.get(i)));
            this.prefWidth = Math.max(glyphLayout.width, this.prefWidth);
        }
        pool.free(glyphLayout);
        this.prefWidth += drawable.getLeftWidth() + drawable.getRightWidth();
        this.prefHeight = this.items.size * this.itemHeight;
        Drawable drawable2 = this.style.background;
        if (drawable2 != null) {
            this.prefWidth = Math.max(this.prefWidth + drawable2.getLeftWidth() + drawable2.getRightWidth(), drawable2.getMinWidth());
            this.prefHeight = Math.max(this.prefHeight + drawable2.getTopHeight() + drawable2.getBottomHeight(), drawable2.getMinHeight());
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        drawBackground(batch, f);
        BitmapFont bitmapFont = this.style.font;
        Drawable drawable = this.style.selection;
        Color color = this.style.fontColorSelected;
        Color color2 = this.style.fontColorUnselected;
        Color color3 = getColor();
        batch.setColor(color3.r, color3.g, color3.f888b, color3.f889a * f);
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        float f2 = height;
        Drawable drawable2 = this.style.background;
        if (drawable2 != null) {
            float leftWidth = drawable2.getLeftWidth();
            x += leftWidth;
            f2 = height - drawable2.getTopHeight();
            width -= leftWidth + drawable2.getRightWidth();
        }
        float leftWidth2 = drawable.getLeftWidth();
        float rightWidth = (width - leftWidth2) - drawable.getRightWidth();
        float topHeight = drawable.getTopHeight() - bitmapFont.getDescent();
        bitmapFont.setColor(color2.r, color2.g, color2.f888b, color2.f889a * f);
        for (int i = 0; i < this.items.size; i++) {
            if (this.cullingArea == null || (f2 - this.itemHeight <= this.cullingArea.y + this.cullingArea.height && f2 >= this.cullingArea.y)) {
                T t = this.items.get(i);
                boolean contains = this.selection.contains(t);
                Drawable drawable3 = null;
                if (this.pressedIndex == i && this.style.down != null) {
                    drawable3 = this.style.down;
                } else if (contains) {
                    drawable3 = drawable;
                    bitmapFont.setColor(color.r, color.g, color.f888b, color.f889a * f);
                } else if (this.overIndex == i && this.style.over != null) {
                    drawable3 = this.style.over;
                }
                drawSelection(batch, drawable3, x, (y + f2) - this.itemHeight, width, this.itemHeight);
                drawItem(batch, bitmapFont, i, t, x + leftWidth2, (y + f2) - topHeight, rightWidth);
                if (contains) {
                    bitmapFont.setColor(color2.r, color2.g, color2.f888b, color2.f889a * f);
                }
            } else if (f2 < this.cullingArea.y) {
                return;
            }
            f2 -= this.itemHeight;
        }
    }

    protected void drawSelection(Batch batch, @Null Drawable drawable, float f, float f2, float f3, float f4) {
        if (drawable != null) {
            drawable.draw(batch, f, f2, f3, f4);
        }
    }

    protected void drawBackground(Batch batch, float f) {
        if (this.style.background != null) {
            Color color = getColor();
            batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
            this.style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    protected GlyphLayout drawItem(Batch batch, BitmapFont bitmapFont, int i, T t, float f, float f2, float f3) {
        String list = toString(t);
        return bitmapFont.draw(batch, list, f, f2, 0, list.length(), f3, this.alignment, false, "...");
    }

    public ArraySelection<T> getSelection() {
        return this.selection;
    }

    public void setSelection(ArraySelection<T> arraySelection) {
        this.selection = arraySelection;
    }

    @Null
    public T getSelected() {
        return this.selection.first();
    }

    public void setSelected(@Null T t) {
        if (this.items.contains(t, false)) {
            this.selection.set(t);
        } else if (this.selection.getRequired() && this.items.size > 0) {
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
        if (i < -1 || i >= this.items.size) {
            throw new IllegalArgumentException("index must be >= -1 and < " + this.items.size + ": " + i);
        }
        if (i == -1) {
            this.selection.clear();
        } else {
            this.selection.set(this.items.get(i));
        }
    }

    public T getOverItem() {
        if (this.overIndex == -1) {
            return null;
        }
        return this.items.get(this.overIndex);
    }

    public T getPressedItem() {
        if (this.pressedIndex == -1) {
            return null;
        }
        return this.items.get(this.pressedIndex);
    }

    @Null
    public T getItemAt(float f) {
        int itemIndexAt = getItemIndexAt(f);
        if (itemIndexAt == -1) {
            return null;
        }
        return this.items.get(itemIndexAt);
    }

    public int getItemIndexAt(float f) {
        float height = getHeight();
        Drawable drawable = this.style.background;
        if (drawable != null) {
            height -= drawable.getTopHeight() + drawable.getBottomHeight();
            f -= drawable.getBottomHeight();
        }
        int i = (int) ((height - f) / this.itemHeight);
        if (i < 0 || i >= this.items.size) {
            return -1;
        }
        return i;
    }

    public void setItems(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float prefWidth = getPrefWidth();
        float prefHeight = getPrefHeight();
        this.items.clear();
        this.items.addAll(tArr);
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.validate();
        invalidate();
        if (prefWidth != getPrefWidth() || prefHeight != getPrefHeight()) {
            invalidateHierarchy();
        }
    }

    public void setItems(Array array) {
        if (array == null) {
            throw new IllegalArgumentException("newItems cannot be null.");
        }
        float prefWidth = getPrefWidth();
        float prefHeight = getPrefHeight();
        if (array != this.items) {
            this.items.clear();
            this.items.addAll(array);
        }
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.validate();
        invalidate();
        if (prefWidth != getPrefWidth() || prefHeight != getPrefHeight()) {
            invalidateHierarchy();
        }
    }

    public void clearItems() {
        if (this.items.size == 0) {
            return;
        }
        this.items.clear();
        this.overIndex = -1;
        this.pressedIndex = -1;
        this.selection.clear();
        invalidateHierarchy();
    }

    public Array<T> getItems() {
        return this.items;
    }

    public float getItemHeight() {
        return this.itemHeight;
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

    public String toString(T t) {
        return t.toString();
    }

    @Override // com.badlogic.gdx.scenes.scene2d.utils.Cullable
    public void setCullingArea(@Null Rectangle rectangle) {
        this.cullingArea = rectangle;
    }

    public Rectangle getCullingArea() {
        return this.cullingArea;
    }

    public void setAlignment(int i) {
        this.alignment = i;
    }

    public int getAlignment() {
        return this.alignment;
    }

    public void setTypeToSelect(boolean z) {
        this.typeToSelect = z;
    }

    public InputListener getKeyListener() {
        return this.keyListener;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/List$ListStyle.class */
    public static class ListStyle {
        public BitmapFont font;
        public Color fontColorSelected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        public Color fontColorUnselected = new Color(1.0f, 1.0f, 1.0f, 1.0f);
        public Drawable selection;

        @Null
        public Drawable down;

        @Null
        public Drawable over;

        @Null
        public Drawable background;

        public ListStyle() {
        }

        public ListStyle(BitmapFont bitmapFont, Color color, Color color2, Drawable drawable) {
            this.font = bitmapFont;
            this.fontColorSelected.set(color);
            this.fontColorUnselected.set(color2);
            this.selection = drawable;
        }

        public ListStyle(ListStyle listStyle) {
            this.font = listStyle.font;
            this.fontColorSelected.set(listStyle.fontColorSelected);
            this.fontColorUnselected.set(listStyle.fontColorUnselected);
            this.selection = listStyle.selection;
            this.down = listStyle.down;
            this.over = listStyle.over;
            this.background = listStyle.background;
        }
    }
}
