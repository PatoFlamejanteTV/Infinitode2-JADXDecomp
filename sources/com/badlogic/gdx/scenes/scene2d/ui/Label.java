package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.StringBuilder;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Label.class */
public class Label extends Widget {
    private static final Color tempColor = new Color();
    private static final GlyphLayout prefSizeLayout = new GlyphLayout();
    private LabelStyle style;
    private final GlyphLayout layout;
    private float prefWidth;
    private float prefHeight;
    private final StringBuilder text;
    private int intValue;
    private BitmapFontCache cache;
    private int labelAlign;
    private int lineAlign;
    private boolean wrap;
    private float lastPrefHeight;
    private boolean prefSizeInvalid;
    private float fontScaleX;
    private float fontScaleY;
    private boolean fontScaleChanged;

    @Null
    private String ellipsis;

    public Label(@Null CharSequence charSequence, Skin skin) {
        this(charSequence, (LabelStyle) skin.get(LabelStyle.class));
    }

    public Label(@Null CharSequence charSequence, Skin skin, String str) {
        this(charSequence, (LabelStyle) skin.get(str, LabelStyle.class));
    }

    public Label(@Null CharSequence charSequence, Skin skin, String str, Color color) {
        this(charSequence, new LabelStyle(skin.getFont(str), color));
    }

    public Label(@Null CharSequence charSequence, Skin skin, String str, String str2) {
        this(charSequence, new LabelStyle(skin.getFont(str), skin.getColor(str2)));
    }

    public Label(@Null CharSequence charSequence, LabelStyle labelStyle) {
        this.layout = new GlyphLayout();
        this.text = new StringBuilder();
        this.intValue = Integer.MIN_VALUE;
        this.labelAlign = 8;
        this.lineAlign = 8;
        this.prefSizeInvalid = true;
        this.fontScaleX = 1.0f;
        this.fontScaleY = 1.0f;
        this.fontScaleChanged = false;
        if (charSequence != null) {
            this.text.append(charSequence);
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
        this.style = labelStyle;
        this.cache = labelStyle.font.newFontCache();
        invalidateHierarchy();
    }

    public LabelStyle getStyle() {
        return this.style;
    }

    public boolean setText(int i) {
        if (this.intValue == i) {
            return false;
        }
        this.text.clear();
        this.text.append(i);
        this.intValue = i;
        invalidateHierarchy();
        return true;
    }

    public void setText(@Null CharSequence charSequence) {
        if (charSequence == null) {
            if (this.text.length == 0) {
                return;
            } else {
                this.text.clear();
            }
        } else if (charSequence instanceof StringBuilder) {
            if (this.text.equals(charSequence)) {
                return;
            }
            this.text.clear();
            this.text.append((StringBuilder) charSequence);
        } else {
            if (textEquals(charSequence)) {
                return;
            }
            this.text.clear();
            this.text.append(charSequence);
        }
        this.intValue = Integer.MIN_VALUE;
        invalidateHierarchy();
    }

    public boolean textEquals(CharSequence charSequence) {
        int i = this.text.length;
        char[] cArr = this.text.chars;
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
        return this.text;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void invalidate() {
        super.invalidate();
        this.prefSizeInvalid = true;
    }

    private void scaleAndComputePrefSize() {
        BitmapFont font = this.cache.getFont();
        float scaleX = font.getScaleX();
        float scaleY = font.getScaleY();
        if (this.fontScaleChanged) {
            font.getData().setScale(this.fontScaleX, this.fontScaleY);
        }
        computePrefSize(prefSizeLayout);
        if (this.fontScaleChanged) {
            font.getData().setScale(scaleX, scaleY);
        }
    }

    protected void computePrefSize(GlyphLayout glyphLayout) {
        this.prefSizeInvalid = false;
        if (this.wrap && this.ellipsis == null) {
            float width = getWidth();
            if (this.style.background != null) {
                width = (Math.max(width, this.style.background.getMinWidth()) - this.style.background.getLeftWidth()) - this.style.background.getRightWidth();
            }
            glyphLayout.setText(this.cache.getFont(), this.text, Color.WHITE, width, 8, true);
        } else {
            glyphLayout.setText(this.cache.getFont(), this.text);
        }
        this.prefWidth = glyphLayout.width;
        this.prefHeight = glyphLayout.height;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public void layout() {
        float f;
        float f2;
        float f3;
        BitmapFont font = this.cache.getFont();
        float scaleX = font.getScaleX();
        float scaleY = font.getScaleY();
        if (this.fontScaleChanged) {
            font.getData().setScale(this.fontScaleX, this.fontScaleY);
        }
        boolean z = this.wrap && this.ellipsis == null;
        boolean z2 = z;
        if (z) {
            float prefHeight = getPrefHeight();
            if (prefHeight != this.lastPrefHeight) {
                this.lastPrefHeight = prefHeight;
                invalidateHierarchy();
            }
        }
        float width = getWidth();
        float height = getHeight();
        Drawable drawable = this.style.background;
        float f4 = 0.0f;
        float f5 = 0.0f;
        if (drawable != null) {
            f4 = drawable.getLeftWidth();
            f5 = drawable.getBottomHeight();
            width -= drawable.getLeftWidth() + drawable.getRightWidth();
            height -= drawable.getBottomHeight() + drawable.getTopHeight();
        }
        GlyphLayout glyphLayout = this.layout;
        if (z2 || this.text.indexOf(SequenceUtils.EOL) != -1) {
            glyphLayout.setText(font, this.text, 0, this.text.length, Color.WHITE, width, this.lineAlign, z2, this.ellipsis);
            f = glyphLayout.width;
            f2 = glyphLayout.height;
            if ((this.labelAlign & 8) == 0) {
                if ((this.labelAlign & 16) != 0) {
                    f4 += width - f;
                } else {
                    f4 += (width - f) / 2.0f;
                }
            }
        } else {
            f = width;
            f2 = font.getData().capHeight;
        }
        if ((this.labelAlign & 2) != 0) {
            f3 = f5 + (this.cache.getFont().isFlipped() ? 0.0f : height - f2) + this.style.font.getDescent();
        } else if ((this.labelAlign & 4) != 0) {
            f3 = (f5 + (this.cache.getFont().isFlipped() ? height - f2 : 0.0f)) - this.style.font.getDescent();
        } else {
            f3 = f5 + ((height - f2) / 2.0f);
        }
        if (!this.cache.getFont().isFlipped()) {
            f3 += f2;
        }
        glyphLayout.setText(font, this.text, 0, this.text.length, Color.WHITE, f, this.lineAlign, z2, this.ellipsis);
        this.cache.setText(glyphLayout, f4, f3);
        if (this.fontScaleChanged) {
            font.getData().setScale(scaleX, scaleY);
        }
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch, float f) {
        validate();
        Color color = tempColor.set(getColor());
        color.f889a *= f;
        if (this.style.background != null) {
            batch.setColor(color.r, color.g, color.f888b, color.f889a);
            this.style.background.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
        if (this.style.fontColor != null) {
            color.mul(this.style.fontColor);
        }
        this.cache.tint(color);
        this.cache.setPosition(getX(), getY());
        this.cache.draw(batch);
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefWidth() {
        if (this.wrap) {
            return 0.0f;
        }
        if (this.prefSizeInvalid) {
            scaleAndComputePrefSize();
        }
        float f = this.prefWidth;
        Drawable drawable = this.style.background;
        if (drawable != null) {
            f = Math.max(f + drawable.getLeftWidth() + drawable.getRightWidth(), drawable.getMinWidth());
        }
        return f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.ui.Widget, com.badlogic.gdx.scenes.scene2d.utils.Layout
    public float getPrefHeight() {
        if (this.prefSizeInvalid) {
            scaleAndComputePrefSize();
        }
        float f = 1.0f;
        if (this.fontScaleChanged) {
            f = this.fontScaleY / this.style.font.getScaleY();
        }
        float descent = this.prefHeight - ((this.style.font.getDescent() * f) * 2.0f);
        Drawable drawable = this.style.background;
        if (drawable != null) {
            descent = Math.max(descent + drawable.getTopHeight() + drawable.getBottomHeight(), drawable.getMinHeight());
        }
        return descent;
    }

    public GlyphLayout getGlyphLayout() {
        return this.layout;
    }

    public void setWrap(boolean z) {
        this.wrap = z;
        invalidateHierarchy();
    }

    public boolean getWrap() {
        return this.wrap;
    }

    public int getLabelAlign() {
        return this.labelAlign;
    }

    public int getLineAlign() {
        return this.lineAlign;
    }

    public void setAlignment(int i) {
        setAlignment(i, i);
    }

    public void setAlignment(int i, int i2) {
        this.labelAlign = i;
        if ((i2 & 8) != 0) {
            this.lineAlign = 8;
        } else if ((i2 & 16) != 0) {
            this.lineAlign = 16;
        } else {
            this.lineAlign = 1;
        }
        invalidate();
    }

    public void setFontScale(float f) {
        setFontScale(f, f);
    }

    public void setFontScale(float f, float f2) {
        this.fontScaleChanged = true;
        this.fontScaleX = f;
        this.fontScaleY = f2;
        invalidateHierarchy();
    }

    public float getFontScaleX() {
        return this.fontScaleX;
    }

    public void setFontScaleX(float f) {
        setFontScale(f, this.fontScaleY);
    }

    public float getFontScaleY() {
        return this.fontScaleY;
    }

    public void setFontScaleY(float f) {
        setFontScale(this.fontScaleX, f);
    }

    public void setEllipsis(@Null String str) {
        this.ellipsis = str;
    }

    public void setEllipsis(boolean z) {
        if (z) {
            this.ellipsis = "...";
        } else {
            this.ellipsis = null;
        }
    }

    protected BitmapFontCache getBitmapFontCache() {
        return this.cache;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Actor
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
        return (str.indexOf(36) != -1 ? "Label " : "") + str + ": " + ((Object) this.text);
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/ui/Label$LabelStyle.class */
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
