package com.vladsch.flexmark.ext.gfm.tasklist;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/TaskListItemVisitorExt.class */
public class TaskListItemVisitorExt {
    public static <V extends TaskListItemVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(TaskListItem.class, v::visit)};
    }
}
