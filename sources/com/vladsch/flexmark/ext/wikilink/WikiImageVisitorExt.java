package com.vladsch.flexmark.ext.wikilink;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/WikiImageVisitorExt.class */
public class WikiImageVisitorExt {
    public static <V extends WikiImageVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(WikiImage.class, v::visit)};
    }
}
