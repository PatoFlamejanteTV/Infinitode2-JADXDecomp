package com.vladsch.flexmark.ext.abbreviation;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/abbreviation/AbbreviationVisitorExt.class */
public class AbbreviationVisitorExt {
    public static <V extends AbbreviationVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(AbbreviationBlock.class, v::visit), new VisitHandler<>(Abbreviation.class, v::visit)};
    }
}
