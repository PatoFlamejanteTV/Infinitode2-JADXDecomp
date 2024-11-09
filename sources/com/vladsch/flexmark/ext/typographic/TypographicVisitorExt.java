package com.vladsch.flexmark.ext.typographic;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/typographic/TypographicVisitorExt.class */
public class TypographicVisitorExt {
    public static <V extends TypographicVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(TypographicSmarts.class, v::visit), new VisitHandler<>(TypographicQuotes.class, v::visit)};
    }
}
