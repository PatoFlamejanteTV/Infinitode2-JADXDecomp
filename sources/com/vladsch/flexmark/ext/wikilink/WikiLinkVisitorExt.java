package com.vladsch.flexmark.ext.wikilink;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/WikiLinkVisitorExt.class */
public class WikiLinkVisitorExt {
    public static <V extends WikiLinkVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(WikiLink.class, v::visit)};
    }
}
