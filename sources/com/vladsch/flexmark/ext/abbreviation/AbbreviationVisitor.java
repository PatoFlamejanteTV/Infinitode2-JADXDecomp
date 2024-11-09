package com.vladsch.flexmark.ext.abbreviation;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/AbbreviationVisitor.class */
public interface AbbreviationVisitor {
    void visit(AbbreviationBlock abbreviationBlock);

    void visit(Abbreviation abbreviation);
}
