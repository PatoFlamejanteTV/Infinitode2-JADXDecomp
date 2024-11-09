package com.vladsch.flexmark.ext.gfm.strikethrough;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/SubscriptVisitorExt.class */
public class SubscriptVisitorExt {
    public static <V extends SubscriptVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(Subscript.class, v::visit)};
    }
}
