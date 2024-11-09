package com.vladsch.flexmark.util.html.ui;

import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/Color.class */
public class Color extends java.awt.Color {
    public static final Color NULL = new Color(new java.awt.Color(0, true));
    public static final Color WHITE = new Color(java.awt.Color.WHITE);
    public static final Color LIGHT_GRAY = new Color(java.awt.Color.LIGHT_GRAY);
    public static final Color GRAY = new Color(java.awt.Color.GRAY);
    public static final Color DARK_GRAY = new Color(java.awt.Color.DARK_GRAY);
    public static final Color BLACK = new Color(java.awt.Color.BLACK);
    public static final Color RED = new Color(java.awt.Color.RED);
    public static final Color PINK = new Color(java.awt.Color.PINK);
    public static final Color ORANGE = new Color(java.awt.Color.ORANGE);
    public static final Color YELLOW = new Color(java.awt.Color.YELLOW);
    public static final Color GREEN = new Color(java.awt.Color.GREEN);
    public static final Color MAGENTA = new Color(java.awt.Color.MAGENTA);
    public static final Color CYAN = new Color(java.awt.Color.CYAN);
    public static final Color BLUE = new Color(java.awt.Color.BLUE);

    protected Color(java.awt.Color color) {
        super(color.getRGB());
    }

    protected Color(int i) {
        super(i);
    }

    public static Color of(java.awt.Color color) {
        return new Color(color);
    }

    public static Color of(int i) {
        return new Color(i);
    }

    public static Color of(String str) {
        java.awt.Color namedColor = ColorStyler.getNamedColor(str);
        return namedColor == null ? NULL : new Color(namedColor);
    }

    public String toString() {
        return "Color { " + HtmlHelpers.toHtmlString(this) + SequenceUtils.SPACE + HtmlHelpers.toRgbString(this) + "}";
    }
}
