package com.vladsch.flexmark.util.html.ui;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/html/ui/FontStyleStyler.class */
public class FontStyleStyler extends HtmlStylerBase<FontStyle> {
    @Override // com.vladsch.flexmark.util.html.ui.HtmlStylerBase, com.vladsch.flexmark.util.html.ui.HtmlStyler
    public String getStyle(FontStyle fontStyle) {
        if (fontStyle == null) {
            return "";
        }
        return (fontStyle.isItalic() ? "font-style:italic;" : "font-style:normal;") + (fontStyle.isBold() ? "font-weight:bold" : "font-weight:normal");
    }
}
