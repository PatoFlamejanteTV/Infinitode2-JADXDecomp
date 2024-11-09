package com.vladsch.flexmark.ext.anchorlink;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/anchorlink/AnchorLinkVisitorExt.class */
public class AnchorLinkVisitorExt {
    public static <V extends AnchorLinkVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(AnchorLink.class, v::visit)};
    }
}
