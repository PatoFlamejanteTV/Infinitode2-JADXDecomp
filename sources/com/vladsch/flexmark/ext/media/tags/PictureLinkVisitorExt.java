package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/PictureLinkVisitorExt.class */
public class PictureLinkVisitorExt {
    public static <V extends PictureLinkVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(PictureLink.class, v::visit)};
    }
}
