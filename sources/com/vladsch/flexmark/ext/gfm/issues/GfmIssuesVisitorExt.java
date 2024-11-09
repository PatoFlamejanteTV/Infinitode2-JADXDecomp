package com.vladsch.flexmark.ext.gfm.issues;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/issues/GfmIssuesVisitorExt.class */
public class GfmIssuesVisitorExt {
    public static <V extends GfmIssuesVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(GfmIssue.class, v::visit)};
    }
}
