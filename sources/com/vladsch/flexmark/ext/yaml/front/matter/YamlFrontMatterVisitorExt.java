package com.vladsch.flexmark.ext.yaml.front.matter;

import com.vladsch.flexmark.util.ast.VisitHandler;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/yaml/front/matter/YamlFrontMatterVisitorExt.class */
public class YamlFrontMatterVisitorExt {
    public static <V extends YamlFrontMatterVisitor> VisitHandler<?>[] VISIT_HANDLERS(V v) {
        v.getClass();
        v.getClass();
        return new VisitHandler[]{new VisitHandler<>(YamlFrontMatterNode.class, v::visit), new VisitHandler<>(YamlFrontMatterBlock.class, v::visit)};
    }
}
