package com.vladsch.flexmark.html2md.converter;

import java.util.Locale;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/html2md/converter/ListState.class */
public class ListState {
    public final boolean isNumbered;
    public int itemCount = 0;

    public ListState(boolean z) {
        this.isNumbered = z;
    }

    public String getItemPrefix(HtmlConverterOptions htmlConverterOptions) {
        return this.isNumbered ? String.format(Locale.US, "%d%c ", Integer.valueOf(this.itemCount), Character.valueOf(htmlConverterOptions.orderedListDelimiter)) : String.format("%c ", Character.valueOf(htmlConverterOptions.unorderedListDelimiter));
    }
}
