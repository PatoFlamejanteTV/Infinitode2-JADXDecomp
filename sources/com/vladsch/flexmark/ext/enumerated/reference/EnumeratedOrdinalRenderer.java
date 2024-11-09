package com.vladsch.flexmark.ext.enumerated.reference;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedOrdinalRenderer.class */
public interface EnumeratedOrdinalRenderer {
    void startRendering(EnumeratedReferenceRendering[] enumeratedReferenceRenderingArr);

    void setEnumOrdinalRunnable(Runnable runnable);

    Runnable getEnumOrdinalRunnable();

    void render(int i, EnumeratedReferenceBlock enumeratedReferenceBlock, String str, boolean z);

    void endRendering();
}
