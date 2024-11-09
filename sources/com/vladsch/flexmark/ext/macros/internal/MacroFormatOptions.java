package com.vladsch.flexmark.ext.macros.internal;

import com.vladsch.flexmark.ext.macros.MacrosExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/internal/MacroFormatOptions.class */
public class MacroFormatOptions {
    public final ElementPlacement macrosPlacement;
    public final ElementPlacementSort macrosSort;

    public MacroFormatOptions(DataHolder dataHolder) {
        this.macrosPlacement = MacrosExtension.MACRO_DEFINITIONS_PLACEMENT.get(dataHolder);
        this.macrosSort = MacrosExtension.MACRO_DEFINITIONS_SORT.get(dataHolder);
    }
}
