package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/EmbedLinkVisitorExt.class */
public class EmbedLinkVisitorExt {
    public static <V extends EmbedLinkVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(EmbedLink.class, v::visit)};
    }
}
