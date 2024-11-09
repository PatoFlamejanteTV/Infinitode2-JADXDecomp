package com.vladsch.flexmark.ext.footnotes;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/FootnoteVisitor.class */
public interface FootnoteVisitor {
    void visit(FootnoteBlock footnoteBlock);

    void visit(Footnote footnote);
}
