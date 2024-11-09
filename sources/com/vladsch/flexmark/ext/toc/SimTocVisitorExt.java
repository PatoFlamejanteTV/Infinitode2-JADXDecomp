package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/SimTocVisitorExt.class */
public class SimTocVisitorExt {
    public static <V extends SimTocVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(SimTocBlock.class, v::visit), new VisitHandler<>(SimTocOptionList.class, v::visit), new VisitHandler<>(SimTocOption.class, v::visit), new VisitHandler<>(SimTocContent.class, v::visit)};
    }
}
