package com.vladsch.flexmark.ext.jekyll.tag;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/JekyllTagVisitorExt.class */
public class JekyllTagVisitorExt {
    public static <V extends JekyllTagVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(JekyllTag.class, v::visit), new VisitHandler<>(JekyllTagBlock.class, v::visit)};
    }
}
