package com.prineside.tdi2.ui.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.prineside.tdi2.scene2d.ui.Widget;
import com.prineside.tdi2.scene2d.utils.Drawable;
import com.prineside.tdi2.utils.UiUtils;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Label.class */
public class Label extends Widget {
    private static final Color j = new Color();
    private static final GlyphLayout k = new GlyphLayout();
    private LabelStyle l;
    private float n;
    private float o;
    private BitmapFontCache r;
    private boolean u;
    private float v;

    @Null
    private String A;
    private final GlyphLayout m = new GlyphLayout();
    private final StringBuilder p = new StringBuilder();
    private int q = Integer.MIN_VALUE;
    private int s = 8;
    private int t = 8;
    private boolean w = true;
    private float x = 1.0f;
    private float y = 1.0f;
    private boolean z = false;

    public Label(@Null CharSequence charSequence, LabelStyle labelStyle) {
        if (charSequence != null) {
            try {
                this.p.append(charSequence);
            } catch (Exception e) {
                throw new IllegalStateException("Label creation failed, actor path:\n" + ((Object) UiUtils.getFullPathToStage(this)), e);
            }
        }
        setStyle(labelStyle);
        if (charSequence == null || charSequence.length() <= 0) {
            return;
        }
        setSize(getPrefWidth(), getPrefHeight());
    }

    public void setStyle(LabelStyle labelStyle) {
        if (labelStyle == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        if (labelStyle.font == null) {
            throw new IllegalArgumentException("Missing LabelStyle font.");
        }
        this.l = labelStyle;
        this.r = labelStyle.font.newFontCache();
        invalidateHierarchy();
    }

    public LabelStyle getStyle() {
        return this.l;
    }

    public boolean setTextFromInt(int i) {
        if (this.q == i) {
            return false;
        }
        this.p.clear();
        this.p.append(i);
        this.q = i;
        invalidateHierarchy();
        return true;
    }

    public void setText(@Null CharSequence charSequence) {
        if (charSequence == null) {
            if (this.p.length == 0) {
                return;
            } else {
                this.p.clear();
            }
        } else if (charSequence instanceof StringBuilder) {
            if (this.p.equals(charSequence)) {
                return;
            }
            this.p.clear();
            this.p.append((StringBuilder) charSequence);
        } else {
            if (textEquals(charSequence)) {
                return;
            }
            this.p.clear();
            this.p.append(charSequence);
        }
        this.q = Integer.MIN_VALUE;
        invalidateHierarchy();
    }

    public boolean textEquals(CharSequence charSequence) {
        int i = this.p.length;
        char[] cArr = this.p.chars;
        if (i != charSequence.length()) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (cArr[i2] != charSequence.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    public StringBuilder getText() {
        return this.p;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.w = true;
    }

    private void c() {
        BitmapFont font = this.r.getFont();
        float scaleX = font.getScaleX();
        float scaleY = font.getScaleY();
        if (this.z) {
            font.getData().setScale(this.x, this.y);
        }
        a(k);
        if (this.z) {
            font.getData().setScale(scaleX, scaleY);
        }
    }

    private void a(GlyphLayout glyphLayout) {
        this.w = false;
        if (this.u && this.A == null) {
            float width = getWidth();
            if (this.l.background != null) {
                width = (Math.max(width, this.l.background.getMinWidth()) - this.l.background.getLeftWidth()) - this.l.background.getRightWidth();
            }
            glyphLayout.setText(this.r.getFont(), this.p, Color.WHITE, width, 8, true);
        } else {
            glyphLayout.setText(this.r.getFont(), this.p);
        }
        this.n = glyphLayout.width;
        this.o = glyphLayout.height;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public void layout() {
        float f;
        float f2;
        float f3;
        try {
            BitmapFont font = this.r.getFont();
            float scaleX = font.getScaleX();
            float scaleY = font.getScaleY();
            if (this.z) {
                font.getData().setScale(this.x, this.y);
            }
            boolean z = this.u && this.A == null;
            boolean z2 = z;
            if (z) {
                float prefHeight = getPrefHeight();
                if (prefHeight != this.v) {
                    this.v = prefHeight;
                    invalidateHierarchy();
                }
            }
            float width = getWidth();
            float height = getHeight();
            Drawable drawable = this.l.background;
            float f4 = 0.0f;
            float f5 = 0.0f;
            if (drawable != null) {
                f4 = drawable.getLeftWidth();
                f5 = drawable.getBottomHeight();
                width -= drawable.getLeftWidth() + drawable.getRightWidth();
                height -= drawable.getBottomHeight() + drawable.getTopHeight();
            }
            GlyphLayout glyphLayout = this.m;
            if (z2 || this.p.indexOf(SequenceUtils.EOL) != -1) {
                glyphLayout.setText(font, this.p, 0, this.p.length, Color.WHITE, width, this.t, z2, this.A);
                f = glyphLayout.width;
                f2 = glyphLayout.height;
                if ((this.s & 8) == 0) {
                    if ((this.s & 16) != 0) {
                        f4 += width - f;
                    } else {
                        f4 += (width - f) / 2.0f;
                    }
                }
            } else {
                f = width;
                f2 = font.getData().capHeight;
            }
            if ((this.s & 2) != 0) {
                f3 = f5 + (this.r.getFont().isFlipped() ? 0.0f : height - f2) + this.l.font.getDescent();
            } else if ((this.s & 4) != 0) {
                f3 = (f5 + (this.r.getFont().isFlipped() ? height - f2 : 0.0f)) - this.l.font.getDescent();
            } else {
                f3 = f5 + ((height - f2) / 2.0f);
            }
            if (!this.r.getFont().isFlipped()) {
                f3 += f2;
            }
            glyphLayout.setText(font, this.p, 0, this.p.length, Color.WHITE, f, this.t, z2, this.A);
            this.r.setText(glyphLayout, f4, f3);
            if (this.z) {
                font.getData().setScale(scaleX, scaleY);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Label.layout failed, actor path:\n" + ((Object) UiUtils.getFullPathToStage(this)), e);
        }
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        Color color = j.set(getColor());
        color.f889a *= f;
        if (this.l.background != null) {
            batch.setColor(color.r, color.g, color.f888b, color.f889a);
            this.l.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (this.l.fontColor != null) {
            color.mul(this.l.fontColor);
        }
        this.r.tint(color);
        this.r.setPosition(getX(), getY());
        this.r.draw(batch);
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.u) {
            return 0.0f;
        }
        if (this.w) {
            c();
        }
        float f = this.n;
        Drawable drawable = this.l.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
        }
        return f;
    }

    @Override // com.prineside.tdi2.scene2d.ui.Widget, com.prineside.tdi2.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.w) {
            c();
        }
        float f = 1.0f;
        if (this.z) {
            f = this.y / this.l.font.getScaleY();
        }
        float descent = this.o - ((this.l.font.getDescent() * f) * 2.0f);
        Drawable drawable = this.l.background;
        if (drawable != null) {
            descent = Math.max(descent + drawable.getTopHeight() + drawable.getBottomHeight(), drawable.getMinHeight());
        }
        return descent;
    }

    public GlyphLayout getGlyphLayout() {
        return this.m;
    }

    public void setWrap(boolean z) {
        this.u = z;
        invalidateHierarchy();
    }

    public boolean getWrap() {
        return this.u;
    }

    public int getLabelAlign() {
        return this.s;
    }

    public int getLineAlign() {
        return this.t;
    }

    public void setAlignment(int i) {
        setAlignmentSeparate(i, i);
    }

    public void setAlignmentSeparate(int i, int i2) {
        this.s = i;
        if ((i2 & 8) != 0) {
            this.t = 8;
        } else if ((i2 & 16) != 0) {
            this.t = 16;
        } else {
            this.t = 1;
        }
        invalidate();
    }

    public void setFontScale(float f) {
        setFontScaleSeparate(f, f);
    }

    public void setFontScaleSeparate(float f, float f2) {
        this.z = true;
        this.x = f;
        this.y = f2;
        invalidateHierarchy();
    }

    public float getFontScaleX() {
        return this.x;
    }

    public void setFontScaleX(float f) {
        setFontScaleSeparate(f, this.y);
    }

    public float getFontScaleY() {
        return this.y;
    }

    public void setFontScaleY(float f) {
        setFontScaleSeparate(this.x, f);
    }

    public void setEllipsisString(@Null String str) {
        this.A = str;
    }

    public void setEllipsis(boolean z) {
        if (z) {
            this.A = "...";
        } else {
            this.A = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final BitmapFontCache b() {
        return this.r;
    }

    @Override // com.prineside.tdi2.scene2d.Actor
    public String toString() {
        String name = getName();
        if (name != null) {
            return name;
        }
        String name2 = getClass().getName();
        String str = name2;
        int lastIndexOf = name2.lastIndexOf(46);
        if (lastIndexOf != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        return (str.indexOf(36) != -1 ? "Label " : "") + str + ": " + ((Object) this.p);
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/ui/actors/Label$LabelStyle.class */
    public static class LabelStyle {
        public BitmapFont font;

        @Null
        public Color fontColor;

        @Null
        public Drawable background;

        public LabelStyle() {
        }

        public LabelStyle(BitmapFont bitmapFont, @Null Color color) {
            this.font = bitmapFont;
            this.fontColor = color;
        }

        public LabelStyle(LabelStyle labelStyle) {
            this.font = labelStyle.font;
            if (labelStyle.fontColor != null) {
                this.fontColor = new Color(labelStyle.fontColor);
            }
            this.background = labelStyle.background;
        }
    }
}
