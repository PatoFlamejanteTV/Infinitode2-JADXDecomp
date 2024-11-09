package com.vladsch.flexmark.ext.aside;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/aside/AsideVisitorExt.class */
public class AsideVisitorExt {
    public static <V extends AsideVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(AsideBlock.class, v::visit)};
    }
}
