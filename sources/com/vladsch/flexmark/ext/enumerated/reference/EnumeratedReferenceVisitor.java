package com.vladsch.flexmark.ext.enumerated.reference;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferenceVisitor.class */
public interface EnumeratedReferenceVisitor {
    void visit(EnumeratedReferenceText enumeratedReferenceText);

    void visit(EnumeratedReferenceLink enumeratedReferenceLink);

    void visit(EnumeratedReferenceBlock enumeratedReferenceBlock);
}
