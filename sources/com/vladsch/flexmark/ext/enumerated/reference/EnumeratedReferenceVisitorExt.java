package com.vladsch.flexmark.ext.enumerated.reference;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/enumerated/reference/EnumeratedReferenceVisitorExt.class */
public class EnumeratedReferenceVisitorExt {
    public static <V extends EnumeratedReferenceVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(EnumeratedReferenceText.class, v::visit), new VisitHandler<>(EnumeratedReferenceLink.class, v::visit), new VisitHandler<>(EnumeratedReferenceBlock.class, v::visit)};
    }
}
