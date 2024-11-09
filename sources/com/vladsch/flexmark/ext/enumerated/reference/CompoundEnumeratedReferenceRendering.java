package com.vladsch.flexmark.ext.enumerated.reference;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/CompoundEnumeratedReferenceRendering.class */
public class CompoundEnumeratedReferenceRendering {
    public final int ordinal;
    public final EnumeratedReferenceBlock referenceFormat;
    public final String defaultText;
    public final boolean needSeparator;

    public CompoundEnumeratedReferenceRendering(int i, EnumeratedReferenceBlock enumeratedReferenceBlock, String str, boolean z) {
        this.ordinal = i;
        this.referenceFormat = enumeratedReferenceBlock;
        this.defaultText = str;
        this.needSeparator = z;
    }
}
