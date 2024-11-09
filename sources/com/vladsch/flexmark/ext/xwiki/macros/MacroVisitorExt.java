package com.vladsch.flexmark.ext.xwiki.macros;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/xwiki/macros/MacroVisitorExt.class */
public class MacroVisitorExt {
    public static <V extends MacroVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(Macro.class, v::visit), new VisitHandler<>(MacroClose.class, v::visit), new VisitHandler<>(MacroBlock.class, v::visit)};
    }
}
