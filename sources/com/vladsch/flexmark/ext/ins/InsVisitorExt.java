package com.vladsch.flexmark.ext.ins;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/ins/InsVisitorExt.class */
public class InsVisitorExt {
    public static <V extends InsVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(Ins.class, v::visit)};
    }
}
