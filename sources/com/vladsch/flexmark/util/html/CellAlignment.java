package com.vladsch.flexmark.util.html;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/CellAlignment.class */
public enum CellAlignment {
    NONE,
    LEFT,
    CENTER,
    RIGHT;

    public static CellAlignment getAlignment(String str) {
        for (CellAlignment cellAlignment : values()) {
            if (cellAlignment.name().equalsIgnoreCase(str)) {
                return cellAlignment;
            }
        }
        return NONE;
    }
}
