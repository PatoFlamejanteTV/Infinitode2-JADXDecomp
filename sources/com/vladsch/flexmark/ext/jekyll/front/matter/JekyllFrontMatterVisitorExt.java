package com.vladsch.flexmark.ext.jekyll.front.matter;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/front/matter/JekyllFrontMatterVisitorExt.class */
public class JekyllFrontMatterVisitorExt {
    public static <V extends JekyllFrontMatterVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(JekyllFrontMatterBlock.class, v::visit)};
    }
}
