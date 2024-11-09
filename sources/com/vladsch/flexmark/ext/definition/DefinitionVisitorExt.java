package com.vladsch.flexmark.ext.definition;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/DefinitionVisitorExt.class */
public class DefinitionVisitorExt {
    public static <V extends DefinitionVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(DefinitionItem.class, v::visit), new VisitHandler<>(DefinitionList.class, v::visit), new VisitHandler<>(DefinitionTerm.class, v::visit), new VisitHandler<>(DefinitionItem.class, v::visit)};
    }
}
