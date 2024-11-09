package com.vladsch.flexmark.util.html.ui;

import java.awt.Font;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/FontStyler.class */
public class FontStyler extends HtmlStylerBase<Font> {
    @Override // com.vladsch.flexmark.util.html.ui.HtmlStylerBase, com.vladsch.flexmark.util.html.ui.HtmlStyler
    public String getStyle(Font font) {
        return font == null ? "" : String.format(Locale.US, "font-family:%s;font-size:%dpt;font-style:normal;font-weight:normal", font.getFamily(), Integer.valueOf(font.getSize()));
    }
}
