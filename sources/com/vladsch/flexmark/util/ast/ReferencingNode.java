package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.ast.NodeRepository;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/ReferencingNode.class */
public interface ReferencingNode<R extends NodeRepository<B>, B extends ReferenceNode> {
    boolean isDefined();

    BasedSequence getReference();

    B getReferenceNode(Document document);

    B getReferenceNode(R r);
}
