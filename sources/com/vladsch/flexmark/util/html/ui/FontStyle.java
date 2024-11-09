package com.vladsch.flexmark.util.html.ui;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/FontStyle.class */
public class FontStyle {
    public static final FontStyle PLAIN = new FontStyle(0);
    public static final FontStyle BOLD = new FontStyle(1);
    public static final FontStyle ITALIC = new FontStyle(2);
    public static final FontStyle BOLD_ITALIC = new FontStyle(3);
    public final int fontStyle;

    private FontStyle(int i) {
        this.fontStyle = i;
    }

    public boolean isItalic() {
        return (this.fontStyle & 2) != 0;
    }

    public boolean isBold() {
        return (this.fontStyle & 1) != 0;
    }

    public static FontStyle of(int i) {
        return (i & 3) == 3 ? BOLD_ITALIC : (i & 1) == 1 ? BOLD : (i & 2) == 2 ? ITALIC : PLAIN;
    }
}
