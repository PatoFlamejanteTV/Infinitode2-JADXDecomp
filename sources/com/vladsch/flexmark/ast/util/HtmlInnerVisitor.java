package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.HtmlInnerBlock;
import com.vladsch.flexmark.ast.HtmlInnerBlockComment;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/HtmlInnerVisitor.class */
public interface HtmlInnerVisitor {
    void visit(HtmlInnerBlock htmlInnerBlock);

    void visit(HtmlInnerBlockComment htmlInnerBlockComment);
}
