package com.vladsch.flexmark.ext.enumerated.reference.internal;

import com.vladsch.flexmark.ext.enumerated.reference.EnumeratedReferenceExtension;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.options.ElementPlacement;
import com.vladsch.flexmark.util.format.options.ElementPlacementSort;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/internal/EnumeratedReferenceFormatOptions.class */
public class EnumeratedReferenceFormatOptions {
    public final ElementPlacement enumeratedReferencePlacement;
    public final ElementPlacementSort enumeratedReferenceSort;

    public EnumeratedReferenceFormatOptions(DataHolder dataHolder) {
        this.enumeratedReferencePlacement = EnumeratedReferenceExtension.ENUMERATED_REFERENCE_PLACEMENT.get(dataHolder);
        this.enumeratedReferenceSort = EnumeratedReferenceExtension.ENUMERATED_REFERENCE_SORT.get(dataHolder);
    }
}
