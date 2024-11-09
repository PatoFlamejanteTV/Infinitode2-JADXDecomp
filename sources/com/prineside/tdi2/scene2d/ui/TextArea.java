package com.prineside.tdi2.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.prineside.tdi2.scene2d.InputEvent;
import com.prineside.tdi2.scene2d.InputListener;
import com.prineside.tdi2.scene2d.ui.TextField;
import com.prineside.tdi2.scene2d.utils.Drawable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextArea.class */
public class TextArea extends TextField {
    IntArray k;
    private String O;
    int l;
    int m;
    private int P;
    float n;
    public boolean prefSizeDependsOnContents;
    private float Q;

    public TextArea(String str, TextField.TextFieldStyle textFieldStyle) {
        super(str, textFieldStyle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public final void d() {
        super.d();
        this.writeEnters = true;
        this.k = new IntArray();
        this.l = 0;
        this.m = 0;
        this.n = -1.0f;
        this.P = 0;
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public int letterUnderCursor(float f) {
        if (this.k.size > 0) {
            if ((this.l << 1) >= this.k.size) {
                return this.o.length();
            }
            float[] fArr = this.s.items;
            int i = this.k.items[this.l << 1];
            float f2 = f + fArr[i];
            int i2 = this.k.items[(this.l << 1) + 1];
            int i3 = i;
            while (i3 < i2 && fArr[i3] <= f2) {
                i3++;
            }
            return (i3 <= 0 || fArr[i3] - f2 > f2 - fArr[i3 - 1]) ? Math.max(0, i3 - 1) : i3;
        }
        return 0;
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public void setStyle(TextField.TextFieldStyle textFieldStyle) {
        if (textFieldStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.t = textFieldStyle;
        this.H = textFieldStyle.font.getCapHeight() - textFieldStyle.font.getDescent();
        if (this.o != null) {
            j();
        }
        invalidateHierarchy();
    }

    public void setPrefRows(float f) {
        this.Q = f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField, com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.prefSizeDependsOnContents) {
            float ceil = (float) Math.ceil(this.t.font.getLineHeight() * getLines());
            if (this.t.background != null) {
                ceil = Math.max(ceil + this.t.background.getBottomHeight() + this.t.background.getTopHeight(), this.t.background.getMinHeight());
            }
            return ceil;
        }
        if (this.Q <= 0.0f) {
            return super.getPrefHeight();
        }
        float ceil2 = (float) Math.ceil(this.t.font.getLineHeight() * this.Q);
        if (this.t.background != null) {
            ceil2 = Math.max(ceil2 + this.t.background.getBottomHeight() + this.t.background.getTopHeight(), this.t.background.getMinHeight());
        }
        return ceil2;
    }

    public int getLines() {
        return (this.k.size / 2) + (newLineAtEnd() ? 1 : 0);
    }

    public boolean newLineAtEnd() {
        if (this.o.length() != 0) {
            return this.o.charAt(this.o.length() - 1) == '\n' || this.o.charAt(this.o.length() - 1) == '\r';
        }
        return false;
    }

    public void moveCursorLine(int i) {
        if (i < 0) {
            this.l = 0;
            this.p = 0;
            this.n = -1.0f;
        } else if (i >= getLines()) {
            int lines = getLines() - 1;
            this.p = this.o.length();
            if (i > getLines() || lines == this.l) {
                this.n = -1.0f;
            }
            this.l = lines;
        } else if (i != this.l) {
            if (this.n < 0.0f) {
                this.n = this.k.size <= (this.l << 1) ? 0.0f : this.s.get(this.p) - this.s.get(this.k.get(this.l << 1));
            }
            this.l = i;
            TextArea textArea = this;
            int length = (textArea.l << 1) >= this.k.size ? this.o.length() : this.k.get(this.l << 1);
            while (true) {
                textArea.p = length;
                if (this.p >= this.o.length() || this.p > this.k.get((this.l << 1) + 1) - 1 || this.s.get(this.p) - this.s.get(this.k.get(this.l << 1)) >= this.n) {
                    break;
                }
                textArea = this;
                length = textArea.p + 1;
            }
            f();
        }
        updateContextMenu();
    }

    final void e() {
        int b2 = b(this.p);
        int i = b2 / 2;
        if ((b2 % 2 == 0 || b2 + 1 >= this.k.size || this.p != this.k.items[b2] || this.k.items[b2 + 1] != this.k.items[b2]) && (i < this.k.size / 2 || this.o.length() == 0 || this.o.charAt(this.o.length() - 1) == '\n' || this.o.charAt(this.o.length() - 1) == '\r')) {
            this.l = i;
        }
        l();
    }

    final void f() {
        e();
        l();
    }

    private void l() {
        if (this.l != this.m) {
            int i = this.l >= this.m ? 1 : -1;
            while (true) {
                if (this.m > this.l || (this.m + this.P) - 1 < this.l) {
                    this.m += i;
                } else {
                    return;
                }
            }
        }
    }

    private int b(int i) {
        int i2 = 0;
        while (i2 < this.k.size && i > this.k.items[i2]) {
            i2++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.ui.WidgetGroup, com.prineside.tdi2.scene2d.Actor
    public void sizeChanged() {
        this.O = null;
        BitmapFont bitmapFont = this.t.font;
        this.P = (int) Math.floor((getHeight() - (this.t.background == null ? 0.0f : r0.getBottomHeight() + r0.getTopHeight())) / bitmapFont.getLineHeight());
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    protected final float a(BitmapFont bitmapFont, @Null Drawable drawable) {
        float height = getHeight();
        if (drawable != null) {
            height -= drawable.getTopHeight();
        }
        if (bitmapFont.usesIntegerPositions()) {
            height = (int) height;
        }
        return height;
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    protected final void a(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        float f3 = 0.0f;
        int min = Math.min(this.p, this.q);
        int max = Math.max(this.p, this.q);
        BitmapFont.BitmapFontData data = bitmapFont.getData();
        float lineHeight = this.t.font.getLineHeight();
        for (int i = this.m << 1; i + 1 < this.k.size && i < ((this.m + this.P) << 1); i += 2) {
            int i2 = this.k.get(i);
            int i3 = this.k.get(i + 1);
            if ((min >= i2 || min >= i3 || max >= i2 || max >= i3) && (min <= i2 || min <= i3 || max <= i2 || max <= i3)) {
                int max2 = Math.max(i2, min);
                int min2 = Math.min(i3, max);
                float f4 = 0.0f;
                float f5 = 0.0f;
                BitmapFont.Glyph glyph = data.getGlyph(this.u.charAt(i2));
                if (glyph != null) {
                    if (max2 == i2) {
                        f5 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                    } else {
                        f4 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                    }
                }
                float f6 = this.s.get(max2) - this.s.get(i2);
                float f7 = this.s.get(min2) - this.s.get(max2);
                if (min2 == i3 && min2 < max) {
                    f7 = 4096.0f;
                }
                drawable.draw(batch, f + f6 + f4, (f2 - lineHeight) - f3, f7 + f5, bitmapFont.getLineHeight());
            }
            f3 += bitmapFont.getLineHeight();
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    protected final void a(Batch batch, BitmapFont bitmapFont, float f, float f2) {
        float f3 = (-(this.t.font.getLineHeight() - this.H)) / 2.0f;
        for (int i = this.m << 1; i < ((this.m + this.P) << 1) && i < this.k.size; i += 2) {
            bitmapFont.draw(batch, this.u, f, f2 + f3, this.k.items[i], this.k.items[i + 1], 0.0f, 8, false);
            f3 -= bitmapFont.getLineHeight();
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    protected final void b(Drawable drawable, Batch batch, BitmapFont bitmapFont, float f, float f2) {
        drawable.draw(batch, f + getCursorX(), f2 + getCursorY(), drawable.getMinWidth(), bitmapFont.getLineHeight());
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public void getSelectionBoundingBox(Rectangle rectangle) {
        g();
        float f = 0.0f;
        Drawable i = i();
        if (i != null) {
            f = i.getLeftWidth();
        }
        float a2 = a(this.t.font, i);
        if (this.J && this.r && this.p != this.q) {
            float f2 = 100000.0f;
            float f3 = 100000.0f;
            float f4 = -100000.0f;
            float f5 = -100000.0f;
            float f6 = 0.0f;
            int min = Math.min(this.p, this.q);
            int max = Math.max(this.p, this.q);
            BitmapFont.BitmapFontData data = this.t.font.getData();
            float lineHeight = this.t.font.getLineHeight();
            for (int i2 = this.m << 1; i2 + 1 < this.k.size && i2 < ((this.m + this.P) << 1); i2 += 2) {
                int i3 = this.k.get(i2);
                int i4 = this.k.get(i2 + 1);
                if ((min >= i3 || min >= i4 || max >= i3 || max >= i4) && (min <= i3 || min <= i4 || max <= i3 || max <= i4)) {
                    int max2 = Math.max(i3, min);
                    int min2 = Math.min(i4, max);
                    float f7 = 0.0f;
                    float f8 = 0.0f;
                    BitmapFont.Glyph glyph = data.getGlyph(this.u.charAt(i3));
                    if (glyph != null) {
                        if (max2 == i3) {
                            f8 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                        } else {
                            f7 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
                        }
                    }
                    float f9 = this.s.get(max2) - this.s.get(i3);
                    float f10 = this.s.get(min2) - this.s.get(max2);
                    float f11 = f + f9 + f7;
                    float f12 = (a2 - lineHeight) - f6;
                    f2 = Math.min(f2, f11);
                    f3 = Math.min(f3, f12);
                    f4 = Math.max(f4, f11 + f10 + f8);
                    f5 = Math.max(f5, f12 + this.t.font.getLineHeight());
                }
                f6 += this.t.font.getLineHeight();
            }
            rectangle.x = f2;
            rectangle.y = f3;
            rectangle.width = f4 - f2;
            rectangle.height = f5 - f3;
            return;
        }
        rectangle.x = f + getCursorX();
        rectangle.y = a2 + getCursorY();
        rectangle.width = 0.0f;
        rectangle.height = this.t.font.getLineHeight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public final void g() {
        super.g();
        if (!this.o.equals(this.O)) {
            this.O = this.o;
            BitmapFont bitmapFont = this.t.font;
            float width = getWidth() - (this.t.background != null ? this.t.background.getLeftWidth() + this.t.background.getRightWidth() : 0.0f);
            this.k.clear();
            int i = 0;
            int i2 = 0;
            Pool pool = Pools.get(GlyphLayout.class);
            GlyphLayout glyphLayout = (GlyphLayout) pool.obtain();
            for (int i3 = 0; i3 < this.o.length(); i3++) {
                char charAt = this.o.charAt(i3);
                if (charAt == '\r' || charAt == '\n') {
                    this.k.add(i);
                    this.k.add(i3);
                    i = i3 + 1;
                } else {
                    i2 = a(i3, 0) ? i2 : i3;
                    glyphLayout.setText(bitmapFont, this.o.subSequence(i, i3 + 1));
                    if (glyphLayout.width > width) {
                        if (i >= i2) {
                            i2 = i3 - 1;
                        }
                        this.k.add(i);
                        this.k.add(i2 + 1);
                        int i4 = i2 + 1;
                        i = i4;
                        i2 = i4;
                    }
                }
            }
            pool.free(glyphLayout);
            if (i < this.o.length()) {
                this.k.add(i);
                this.k.add(this.o.length());
            }
            f();
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    protected final InputListener h() {
        return new TextAreaListener();
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public void setSelection(int i, int i2) {
        super.setSelection(i, i2);
        e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public final void a(boolean z, boolean z2) {
        int i = z ? 1 : -1;
        int i2 = (this.l << 1) + i;
        if (i2 >= 0 && i2 + 1 < this.k.size && this.k.items[i2] == this.p && this.k.items[i2 + 1] == this.p) {
            this.l += i;
            if (z2) {
                super.a(z, z2);
            }
            f();
        } else {
            super.a(z, z2);
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public final boolean a(int i, int i2) {
        int b2 = b(i + i2);
        if (super.a(i, i2)) {
            return b2 < 0 || b2 >= this.k.size - 2 || this.k.items[b2 + 1] != i || this.k.items[b2 + 1] == this.k.items[b2 + 2];
        }
        return false;
    }

    public int getCursorLine() {
        return this.l;
    }

    public int getFirstLineShowing() {
        return this.m;
    }

    public int getLinesShowing() {
        return this.P;
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public float getCursorX() {
        float f = 0.0f;
        BitmapFont.BitmapFontData data = this.t.font.getData();
        if (this.p < this.s.size && (this.l << 1) < this.k.size) {
            int i = this.k.items[this.l << 1];
            float f2 = 0.0f;
            BitmapFont.Glyph glyph = data.getGlyph(this.u.charAt(i));
            if (glyph != null) {
                f2 = glyph.fixedWidth ? 0.0f : ((-glyph.xoffset) * data.scaleX) - data.padLeft;
            }
            f = (this.s.get(this.p) - this.s.get(i)) + f2;
        }
        return f + data.cursorX;
    }

    @Override // com.prineside.tdi2.scene2d.ui.TextField
    public float getCursorY() {
        return (-((this.l - this.m) + 1)) * this.t.font.getLineHeight();
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/ui/TextArea$TextAreaListener.class */
    public class TextAreaListener extends TextField.TextFieldClickListener {
        public TextAreaListener() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.prineside.tdi2.scene2d.ui.TextField.TextFieldClickListener
        public final void a(float f, float f2) {
            TextArea.this.n = -1.0f;
            Drawable drawable = TextArea.this.t.background;
            BitmapFont bitmapFont = TextArea.this.t.font;
            float height = TextArea.this.getHeight();
            if (drawable != null) {
                height -= drawable.getTopHeight();
                f -= drawable.getLeftWidth();
            }
            float max = Math.max(0.0f, f);
            TextArea.this.l = ((int) Math.floor((height - f2) / bitmapFont.getLineHeight())) + TextArea.this.m;
            TextArea.this.l = Math.max(0, Math.min(TextArea.this.l, TextArea.this.getLines() - 1));
            super.a(max, f2);
            TextArea.this.e();
        }

        @Override // com.prineside.tdi2.scene2d.ui.TextField.TextFieldClickListener, com.prineside.tdi2.scene2d.InputListener
        public boolean keyDown(InputEvent inputEvent, int i) {
            boolean keyDown = super.keyDown(inputEvent, i);
            if (TextArea.this.hasKeyboardFocus()) {
                boolean z = false;
                boolean z2 = Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60);
                if (i == 20) {
                    if (z2) {
                        if (!TextArea.this.r) {
                            TextArea.this.q = TextArea.this.p;
                            TextArea.this.r = true;
                        }
                    } else {
                        TextArea.this.clearSelection();
                    }
                    TextArea.this.moveCursorLine(TextArea.this.l + 1);
                    z = true;
                    TextArea.this.updateContextMenu();
                } else if (i == 19) {
                    if (z2) {
                        if (!TextArea.this.r) {
                            TextArea.this.q = TextArea.this.p;
                            TextArea.this.r = true;
                        }
                    } else {
                        TextArea.this.clearSelection();
                    }
                    TextArea.this.moveCursorLine(TextArea.this.l - 1);
                    z = true;
                    TextArea.this.updateContextMenu();
                } else {
                    TextArea.this.n = -1.0f;
                }
                if (z) {
                    a(i);
                }
                TextArea.this.updateContextMenu();
                TextArea.this.f();
                return true;
            }
            return keyDown;
        }

        @Override // com.prineside.tdi2.scene2d.ui.TextField.TextFieldClickListener
        protected final boolean a(char c) {
            return TextArea.this.A && c == '\t';
        }

        @Override // com.prineside.tdi2.scene2d.ui.TextField.TextFieldClickListener, com.prineside.tdi2.scene2d.InputListener
        public boolean keyTyped(InputEvent inputEvent, char c) {
            boolean keyTyped = super.keyTyped(inputEvent, c);
            TextArea.this.f();
            return keyTyped;
        }

        @Override // com.prineside.tdi2.scene2d.ui.TextField.TextFieldClickListener
        protected final void a(boolean z) {
            if (z) {
                TextArea.this.p = 0;
            } else if ((TextArea.this.l << 1) < TextArea.this.k.size) {
                TextArea.this.p = TextArea.this.k.get(TextArea.this.l << 1);
            }
            TextArea.this.updateContextMenu();
        }

        @Override // com.prineside.tdi2.scene2d.ui.TextField.TextFieldClickListener
        protected final void b(boolean z) {
            if (z || TextArea.this.l >= TextArea.this.getLines()) {
                TextArea.this.p = TextArea.this.o.length();
            } else if ((TextArea.this.l << 1) + 1 < TextArea.this.k.size) {
                TextArea.this.p = TextArea.this.k.get((TextArea.this.l << 1) + 1);
            }
            TextArea.this.updateContextMenu();
        }
    }
}
