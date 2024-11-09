package com.vladsch.flexmark.ext.admonition.internal;

import com.vladsch.flexmark.ext.admonition.AdmonitionBlock;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/admonition/internal/AdmonitionCollectingVisitor.class */
public class AdmonitionCollectingVisitor {
    private LinkedHashSet<String> qualifiers;
    private final NodeVisitor myVisitor = new NodeVisitor(new VisitHandler(AdmonitionBlock.class, this::visit));

    public LinkedHashSet<String> getQualifiers() {
        return this.qualifiers;
    }

    public void collect(Node node) {
        this.qualifiers = new LinkedHashSet<>();
        this.myVisitor.visit(node);
    }

    public Set<String> collectAndGetQualifiers(Node node) {
        collect(node);
        return this.qualifiers;
    }

    void visit(AdmonitionBlock admonitionBlock) {
        this.qualifiers.add(admonitionBlock.getInfo().toString());
    }
}
