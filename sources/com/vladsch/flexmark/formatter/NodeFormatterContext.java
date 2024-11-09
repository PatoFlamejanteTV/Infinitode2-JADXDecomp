package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.format.NodeContext;
import com.vladsch.flexmark.util.format.TrackedOffsetList;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/formatter/NodeFormatterContext.class */
public interface NodeFormatterContext extends ExplicitAttributeIdProvider, TranslationContext, LinkResolverContext, NodeContext<Node, NodeFormatterContext> {
    MarkdownWriter getMarkdown();

    void render(Node node);

    void renderChildren(Node node);

    FormattingPhase getFormattingPhase();

    void delegateRender();

    DataHolder getOptions();

    FormatterOptions getFormatterOptions();

    Document getDocument();

    CharPredicate getBlockQuoteLikePrefixPredicate();

    BasedSequence getBlockQuoteLikePrefixChars();

    TrackedOffsetList getTrackedOffsets();

    boolean isRestoreTrackedSpaces();

    BasedSequence getTrackedSequence();

    Iterable<? extends Node> nodesOfType(Class<?>[] clsArr);

    Iterable<? extends Node> nodesOfType(Collection<Class<?>> collection);

    Iterable<? extends Node> reversedNodesOfType(Class<?>[] clsArr);

    Iterable<? extends Node> reversedNodesOfType(Collection<Class<?>> collection);
}
