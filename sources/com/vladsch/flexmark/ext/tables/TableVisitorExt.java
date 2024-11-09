package com.vladsch.flexmark.ext.tables;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/tables/TableVisitorExt.class */
public class TableVisitorExt {
    public static <V extends TableVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(TableBlock.class, v::visit), new VisitHandler<>(TableHead.class, v::visit), new VisitHandler<>(TableSeparator.class, v::visit), new VisitHandler<>(TableBody.class, v::visit), new VisitHandler<>(TableRow.class, v::visit), new VisitHandler<>(TableCell.class, v::visit), new VisitHandler<>(TableCaption.class, v::visit)};
    }
}
