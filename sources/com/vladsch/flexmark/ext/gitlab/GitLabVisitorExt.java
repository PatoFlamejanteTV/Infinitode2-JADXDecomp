package com.vladsch.flexmark.ext.gitlab;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gitlab/GitLabVisitorExt.class */
public class GitLabVisitorExt {
    public static <V extends GitLabVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(GitLabIns.class, v::visit), new VisitHandler<>(GitLabDel.class, v::visit), new VisitHandler<>(GitLabInlineMath.class, v::visit), new VisitHandler<>(GitLabBlockQuote.class, v::visit)};
    }
}
