package com.vladsch.flexmark.ast.util;

import com.vladsch.flexmark.ast.HtmlInnerBlock;
import com.vladsch.flexmark.ast.HtmlInnerBlockComment;
import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/util/HtmlInnerVisitorExt.class */
public class HtmlInnerVisitorExt {
    public static <V extends HtmlInnerVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(HtmlInnerBlock.class, v::visit), new VisitHandler<>(HtmlInnerBlockComment.class, v::visit)};
    }
}
