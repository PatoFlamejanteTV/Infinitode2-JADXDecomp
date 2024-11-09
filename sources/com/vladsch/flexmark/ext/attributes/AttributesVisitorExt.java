package com.vladsch.flexmark.ext.attributes;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/attributes/AttributesVisitorExt.class */
public class AttributesVisitorExt {
    public static <V extends AttributesVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(AttributesNode.class, v::visit), new VisitHandler<>(AttributeNode.class, v::visit)};
    }
}
