package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.OrderedSet;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.utils.ArraySelection;
import com.prineside.tdi2.scene2d.utils.Cullable;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.scene2d.utils.UIUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/List.class */
public class List<T> extends Widget implements Cullable {
    private ListStyle o;
    private Rectangle p;
    private float q;
    private float r;
    private float s;
    private InputListener u;
    boolean n;
    final Array<T> j = new Array<>();
    ArraySelection<T> k = new ArraySelection<>(this.j);
    private int t = 8;
    int l = -1;
    int m = -1;

    public List(ListStyle listStyle) {
        this.k.setActor(this);
        this.k.setRequired(true);
        setStyle(listStyle);
        setSize(getPrefWidth(), getPrefHeight());
        InputListener inputListener = new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.List.1

            /* renamed from: a, reason: collision with root package name */
            private long f2658a;

            /* renamed from: b, reason: collision with root package name */
            private String f2659b;

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean keyDown(InputEvent inputEvent, int i) {
                if (List.this.j.isEmpty()) {
                    return false;
                }
                switch (i) {
                    case 3:
                        List.this.setSelectedIndex(0);
                        return true;
                    case 19:
                        int indexOf = List.this.j.indexOf(List.this.getSelected(), false) - 1;
                        int i2 = indexOf;
                        if (indexOf < 0) {
                            i2 = List.this.j.size - 1;
                        }
                        List.this.setSelectedIndex(i2);
                        return true;
                    case 20:
                        int indexOf2 = List.this.j.indexOf(List.this.getSelected(), false) + 1;
                        int i3 = indexOf2;
                        if (indexOf2 >= List.this.j.size) {
                            i3 = 0;
                        }
                        List.this.setSelectedIndex(i3);
                        return true;
                    case 29:
                        if (UIUtils.ctrl() && List.this.k.getMultiple()) {
                            List.this.k.clear();
                            List.this.k.addAll(List.this.j);
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
                        List.this.setSelectedIndex(List.this.j.size - 1);
                        return true;
                    default:
                        return false;
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean keyTyped(InputEvent inputEvent, char c) {
                if (!List.this.n) {
                    return false;
                }
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis > this.f2658a) {
                    this.f2659b = "";
                }
                this.f2658a = currentTimeMillis + 300;
                this.f2659b += Character.toLowerCase(c);
                int i = List.this.j.size;
                for (int i2 = 0; i2 < i; i2++) {
                    if (List.this.toString(List.this.j.get(i2)).toLowerCase().startsWith(this.f2659b)) {
                        List.this.setSelectedIndex(i2);
                        return false;
                    }
                }
                return false;
            }
        };
        this.u = inputListener;
        addListener(inputListener);
        addListener(new InputListener() { // from class: com.prineside.tdi2.scene2d.ui.List.2
            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent, float f, float f2, int i, int i2) {
                int itemIndexAt;
                if (i != 0 || i2 != 0 || List.this.k.isDisabled()) {
                    return true;
                }
                if (List.this.getStage() != null) {
                    List.this.getStage().setKeyboardFocus(List.this);
                }
                if (List.this.j.size == 0 || (itemIndexAt = List.this.getItemIndexAt(f2)) == -1) {
                    return true;
                }
                List.this.k.choose(List.this.j.get(itemIndexAt));
                List.this.l = itemIndexAt;
                return true;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchUp(InputEvent inputEvent, float f, float f2, int i, int i2) {
                if (i == 0 && i2 == 0) {
                    List.this.l = -1;
                }
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void touchDragged(InputEvent inputEvent, float f, float f2, int i) {
                List.this.m = List.this.getItemIndexAt(f2);
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent, float f, float f2) {
                List.this.m = List.this.getItemIndexAt(f2);
                return false;
            }

            @Override // com.prineside.tdi2.scene2d.InputListener
            public void exit(InputEvent inputEvent, float f, float f2, int i, Actor actor) {
                if (i == 0) {
                    List.this.l = -1;
                }
                if (i == -1) {
                    List.this.m = -1;
                }
            }
        });
    }

    public void setStyle(ListStyle listStyle) {
        if (listStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.o = listStyle;
        invalidateHierarchy();
    }

    public ListStyle getStyle() {
        return this.o;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        BitmapFont bitmapFont = this.o.font;
        Drawable drawable = this.o.selection;
        this.s = bitmapFont.getCapHeight() - (bitmapFont.getDescent() * 2.0f);
        this.s += drawable.getTopHeight() + drawable.getBottomHeight();
        this.q = 0.0f;
        Pool pool = Pools.get(GlyphLayout.class);
        GlyphLayout glyphLayout = (GlyphLayout) pool.obtain();
        for (int i = 0; i < this.j.size; i++) {
            glyphLayout.setText(bitmapFont, toString(this.j.get(i)));
            this.q = Math.max(glyphLayout.width, this.q);
        }
        pool.free(glyphLayout);
        this.q += drawable.getLeftWidth() + drawable.getRightWidth();
        this.r = this.j.size * this.s;
        Drawable drawable2 = this.o.background;
        if (drawable2 != null) {
            this.q = Math.max(this.q + drawable2.getLeftWidth() + drawable2.getRightWidth(), drawable2.getMinWidth());
            this.r = Math.max(this.r + drawable2.getTopHeight() + drawable2.getBottomHeight(), drawable2.getMinHeight());
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        a(batch, f);
        BitmapFont bitmapFont = this.o.font;
        Drawable drawable = this.o.selection;
        Color color = this.o.fontColorSelected;
        Color color2 = this.o.fontColorUnselected;
        Color color3 = getColor();
        batch.setColor(color3.r, color3.g, color3.f888b, color3.f889a * f);
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        float f2 = height;
        Drawable drawable2 = this.o.background;
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
        for (int i = 0; i < this.j.size; i++) {
            if (this.p == null || (f2 - this.s <= this.p.y + this.p.height && f2 >= this.p.y)) {
                T t = this.j.get(i);
                boolean contains = this.k.contains(t);
                Drawable drawable3 = null;
                if (this.l == i && this.o.down != null) {
                    drawable3 = this.o.down;
                } else if (contains) {
                    drawable3 = drawable;
                    bitmapFont.setColor(color.r, color.g, color.f888b, color.f889a * f);
                } else if (this.m == i && this.o.over != null) {
                    drawable3 = this.o.over;
                }
                a(batch, drawable3, x, (y + f2) - this.s, width, this.s);
                a(batch, bitmapFont, (BitmapFont) t, x + leftWidth2, (y + f2) - topHeight, rightWidth);
                if (contains) {
                    bitmapFont.setColor(color2.r, color2.g, color2.f888b, color2.f889a * f);
                }
            } else if (f2 < this.p.y) {
                return;
            }
            f2 -= this.s;
        }
    }

    private static void a(Batch batch, @Null Drawable drawable, float f, float f2, float f3, float f4) {
        if (drawable != null) {
            drawable.draw(batch, f, f2, f3, f4);
        }
    }

    private void a(Batch batch, float f) {
        if (this.o.background != null) {
            Color color = getColor();
            batch.setColor(color.r, color.g, color.f888b, color.f889a * f);
            this.o.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    private GlyphLayout a(Batch batch, BitmapFont bitmapFont, T t, float f, float f2, float f3) {
        String list = toString(t);
        return bitmapFont.draw(batch, list, f, f2, 0, list.length(), f3, this.t, false, "...");
    }

    public ArraySelection<T> getSelection() {
        return this.k;
    }

    public void setSelection(ArraySelection<T> arraySelection) {
        this.k = arraySelection;
    }

    @Null
    public T getSelected() {
        return this.k.first();
    }

    public void setSelected(@Null T t) {
        if (this.j.contains(t, false)) {
            this.k.set(t);
        } else if (this.k.getRequired() && this.j.size > 0) {
            this.k.set(this.j.first());
        } else {
            this.k.clear();
        }
    }

    public int getSelectedIndex() {
        OrderedSet<T> items = this.k.items();
        if (items.size == 0) {
            return -1;
        }
        return this.j.indexOf(items.first(), false);
    }

    public void setSelectedIndex(int i) {
        if (i < -1 || i >= this.j.size) {
            throw new IllegalArgumentException("index must be >= -1 and < " + this.j.size + ": " + i);
        }
        if (i == -1) {
            this.k.clear();
        } else {
            this.k.set(this.j.get(i));
        }
    }

    public T getOverItem() {
        if (this.m == -1) {
            return null;
        }
        return this.j.get(this.m);
    }

    public T getPressedItem() {
        if (this.l == -1) {
            return null;
        }
        return this.j.get(this.l);
    }

    @Null
    public T getItemAt(float f) {
        int itemIndexAt = getItemIndexAt(f);
        if (itemIndexAt == -1) {
            return null;
        }
        return this.j.get(itemIndexAt);
    }

    public int getItemIndexAt(float f) {
        float height = getHeight();
        Drawable drawable = this.o.background;
        if (drawable != null) {
            height -= drawable.getTopHeight() + drawable.getBottomHeight();
            f -= drawable.getBottomHeight();
        }
        int i = (int) ((height - f) / this.s);
        if (i < 0 || i >= this.j.size) {
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
        this.j.clear();
        this.j.addAll(tArr);
        this.m = -1;
        this.l = -1;
        this.k.validate();
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
        if (array != this.j) {
            this.j.clear();
            this.j.addAll(array);
        }
        this.m = -1;
        this.l = -1;
        this.k.validate();
        invalidate();
        if (prefWidth != getPrefWidth() || prefHeight != getPrefHeight()) {
            invalidateHierarchy();
        }
    }

    public void clearItems() {
        if (this.j.size == 0) {
            return;
        }
        this.j.clear();
        this.m = -1;
        this.l = -1;
        this.k.clear();
        invalidateHierarchy();
    }

    public Array<T> getItems() {
        return this.j;
    }

    public float getItemHeight() {
        return this.s;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        validate();
        return this.q;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        validate();
        return this.r;
    }

    public String toString(T t) {
        return t.toString();
    }

    @Override // com.prineside.tdi2.scene2d.utils.Cullable
    public void setCullingArea(@Null Rectangle rectangle) {
        this.p = rectangle;
    }

    public Rectangle getCullingArea() {
        return this.p;
    }

    public void setAlignment(int i) {
        this.t = i;
    }

    public int getAlignment() {
        return this.t;
    }

    public void setTypeToSelect(boolean z) {
        this.n = z;
    }

    public InputListener getKeyListener() {
        return this.u;
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/List$ListStyle.class */
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
