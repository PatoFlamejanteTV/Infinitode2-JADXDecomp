package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.Node;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/ExplicitAttributeIdProvider.class */
public interface ExplicitAttributeIdProvider {
    void addExplicitId(Node node, String str, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter);
}
