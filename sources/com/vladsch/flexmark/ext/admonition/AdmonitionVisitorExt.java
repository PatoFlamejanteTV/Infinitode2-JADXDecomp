package com.vladsch.flexmark.ext.admonition;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/AdmonitionVisitorExt.class */
public class AdmonitionVisitorExt {
    public static <V extends AdmonitionVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(AdmonitionBlock.class, v::visit)};
    }
}
