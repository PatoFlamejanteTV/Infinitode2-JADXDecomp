package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/VideoLinkVisitorExt.class */
public class VideoLinkVisitorExt {
    public static <V extends VideoLinkVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(VideoLink.class, v::visit)};
    }
}
