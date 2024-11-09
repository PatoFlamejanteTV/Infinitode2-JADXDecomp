package com.vladsch.flexmark.ext.gfm.strikethrough;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/strikethrough/StrikethroughVisitorExt.class */
public class StrikethroughVisitorExt {
    public static <V extends StrikethroughVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(Strikethrough.class, v::visit)};
    }
}
