package com.vladsch.flexmark.ext.jekyll.tag;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/JekyllTagVisitor.class */
public interface JekyllTagVisitor {
    void visit(JekyllTag jekyllTag);

    void visit(JekyllTagBlock jekyllTagBlock);
}
