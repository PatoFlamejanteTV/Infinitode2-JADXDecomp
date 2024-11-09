package com.vladsch.flexmark.ext.footnotes;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/footnotes/FootnoteVisitorExt.class */
public class FootnoteVisitorExt {
    public static <V extends FootnoteVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(FootnoteBlock.class, v::visit), new VisitHandler<>(Footnote.class, v::visit)};
    }
}
