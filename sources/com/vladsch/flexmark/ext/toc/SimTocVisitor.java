package com.vladsch.flexmark.ext.toc;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/SimTocVisitor.class */
public interface SimTocVisitor {
    void visit(SimTocBlock simTocBlock);

    void visit(SimTocOptionList simTocOptionList);

    void visit(SimTocOption simTocOption);

    void visit(SimTocContent simTocContent);
}
