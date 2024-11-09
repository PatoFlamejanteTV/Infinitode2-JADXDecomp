package com.vladsch.flexmark.util.html.ui;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/BackgroundColor.class */
public class BackgroundColor extends java.awt.Color {
    public static final BackgroundColor NULL = new BackgroundColor(new java.awt.Color(0, true));
    public static final BackgroundColor WHITE = new BackgroundColor(java.awt.Color.WHITE);
    public static final BackgroundColor LIGHT_GRAY = new BackgroundColor(java.awt.Color.LIGHT_GRAY);
    public static final BackgroundColor GRAY = new BackgroundColor(java.awt.Color.GRAY);
    public static final BackgroundColor DARK_GRAY = new BackgroundColor(java.awt.Color.DARK_GRAY);
    public static final BackgroundColor BLACK = new BackgroundColor(java.awt.Color.BLACK);
    public static final BackgroundColor RED = new BackgroundColor(java.awt.Color.RED);
    public static final BackgroundColor PINK = new BackgroundColor(java.awt.Color.PINK);
    public static final BackgroundColor ORANGE = new BackgroundColor(java.awt.Color.ORANGE);
    public static final BackgroundColor YELLOW = new BackgroundColor(java.awt.Color.YELLOW);
    public static final BackgroundColor GREEN = new BackgroundColor(java.awt.Color.GREEN);
    public static final BackgroundColor MAGENTA = new BackgroundColor(java.awt.Color.MAGENTA);
    public static final BackgroundColor CYAN = new BackgroundColor(java.awt.Color.CYAN);
    public static final BackgroundColor BLUE = new BackgroundColor(java.awt.Color.BLUE);

    protected BackgroundColor(java.awt.Color color) {
        super(color.getRGB());
    }

    protected BackgroundColor(int i) {
        super(i);
    }

    public static BackgroundColor of(java.awt.Color color) {
        return new BackgroundColor(color);
    }

    public static BackgroundColor of(int i) {
        return new BackgroundColor(i);
    }

    public static BackgroundColor of(String str) {
        java.awt.Color namedColor = ColorStyler.getNamedColor(str);
        return namedColor == null ? NULL : new BackgroundColor(namedColor);
    }
}
