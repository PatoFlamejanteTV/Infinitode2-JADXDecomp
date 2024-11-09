package com.vladsch.flexmark.ext.gfm.users;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/users/GfmUsersVisitorExt.class */
public class GfmUsersVisitorExt {
    public static <V extends GfmUsersVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(GfmUser.class, v::visit)};
    }
}
