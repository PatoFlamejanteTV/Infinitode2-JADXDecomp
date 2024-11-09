package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/TocVisitorExt.class */
public class TocVisitorExt {
    public static <V extends TocVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(TocBlock.class, v::visit)};
    }
}
