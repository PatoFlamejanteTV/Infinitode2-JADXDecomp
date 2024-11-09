package com.vladsch.flexmark.ext.abbreviation.internal;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/internal/AbbreviationFormatOptions.class */
public class AbbreviationFormatOptions {
    public final ElementPlacement abbreviationsPlacement;
    public final ElementPlacementSort abbreviationsSort;

    public AbbreviationFormatOptions(DataHolder dataHolder) {
        this.abbreviationsPlacement = AbbreviationExtension.ABBREVIATIONS_PLACEMENT.get(dataHolder);
        this.abbreviationsSort = AbbreviationExtension.ABBREVIATIONS_SORT.get(dataHolder);
    }
}
