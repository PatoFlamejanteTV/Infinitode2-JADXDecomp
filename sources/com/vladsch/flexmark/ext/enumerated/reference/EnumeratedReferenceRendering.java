package com.vladsch.flexmark.ext.enumerated.reference;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferenceRendering.class */
public class EnumeratedReferenceRendering {
    public final EnumeratedReferenceBlock referenceFormat;
    public final String referenceType;
    public final int referenceOrdinal;

    public EnumeratedReferenceRendering(EnumeratedReferenceBlock enumeratedReferenceBlock, String str, int i) {
        this.referenceFormat = enumeratedReferenceBlock;
        this.referenceType = str;
        this.referenceOrdinal = i;
    }
}
