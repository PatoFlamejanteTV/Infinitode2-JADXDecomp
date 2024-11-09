package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/AudioLinkVisitorExt.class */
public class AudioLinkVisitorExt {
    public static <V extends AudioLinkVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(AudioLink.class, v::visit)};
    }
}
