package com.vladsch.flexmark.ext.macros;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/MacrosVisitorExt.class */
public class MacrosVisitorExt {
    public static <V extends MacrosVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(MacroReference.class, v::visit), new VisitHandler<>(MacroDefinitionBlock.class, v::visit)};
    }
}
