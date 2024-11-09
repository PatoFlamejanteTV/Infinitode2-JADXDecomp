package com.vladsch.flexmark.ext.footnotes.internal;

import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/internal/FootnoteFormatOptions.class */
public class FootnoteFormatOptions {
    public final ElementPlacement footnotePlacement;
    public final ElementPlacementSort footnoteSort;

    public FootnoteFormatOptions(DataHolder dataHolder) {
        this.footnotePlacement = FootnoteExtension.FOOTNOTE_PLACEMENT.get(dataHolder);
        this.footnoteSort = FootnoteExtension.FOOTNOTE_SORT.get(dataHolder);
    }
}
