package com.vladsch.flexmark.ext.youtube.embedded;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/youtube/embedded/YouTubeLinkVisitorExt.class */
public class YouTubeLinkVisitorExt {
    public static <V extends YouTubeLinkVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(YouTubeLink.class, v::visit)};
    }
}
