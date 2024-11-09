package com.vladsch.flexmark.ext.wikilink.internal;

import com.vladsch.flexmark.ext.wikilink.WikiImage;
import com.vladsch.flexmark.ext.wikilink.WikiLink;
import com.vladsch.flexmark.ext.wikilink.WikiLinkExtension;
import com.vladsch.flexmark.ext.wikilink.WikiNode;
import com.vladsch.flexmark.parser.LinkRefProcessor;
import com.vladsch.flexmark.parser.LinkRefProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/internal/WikiLinkLinkRefProcessor.class */
public class WikiLinkLinkRefProcessor implements LinkRefProcessor {
    static final int BRACKET_NESTING_LEVEL = 1;
    private final WikiLinkOptions options;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !WikiLinkLinkRefProcessor.class.desiredAssertionStatus();
    }

    public WikiLinkLinkRefProcessor(Document document) {
        this.options = new WikiLinkOptions(document);
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public boolean getWantExclamationPrefix() {
        return this.options.imageLinks;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public int getBracketNestingLevel() {
        return 1;
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public boolean isMatch(BasedSequence basedSequence) {
        int length = basedSequence.length();
        return this.options.imageLinks ? (length < 5 || basedSequence.charAt(0) != '!') ? length >= 4 && basedSequence.charAt(0) == '[' && basedSequence.charAt(1) == '[' && basedSequence.endCharAt(1) == ']' && basedSequence.endCharAt(2) == ']' : basedSequence.charAt(1) == '[' && basedSequence.charAt(2) == '[' && basedSequence.endCharAt(1) == ']' && basedSequence.endCharAt(2) == ']' : length >= 4 && basedSequence.charAt(0) == '[' && basedSequence.charAt(1) == '[' && basedSequence.endCharAt(1) == ']' && basedSequence.endCharAt(2) == ']';
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public BasedSequence adjustInlineText(Document document, Node node) {
        if (!$assertionsDisabled && !(node instanceof WikiNode)) {
            throw new AssertionError();
        }
        WikiNode wikiNode = (WikiNode) node;
        return wikiNode.getText().ifNull(wikiNode.getLink());
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public boolean allowDelimiters(BasedSequence basedSequence, Document document, Node node) {
        if (!$assertionsDisabled && !(node instanceof WikiNode)) {
            throw new AssertionError();
        }
        WikiNode wikiNode = (WikiNode) node;
        return (node instanceof WikiLink) && WikiLinkExtension.ALLOW_INLINES.get(document).booleanValue() && wikiNode.getText().ifNull(wikiNode.getLink()).containsAllOf(basedSequence);
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public void updateNodeElements(Document document, Node node) {
        if (!$assertionsDisabled && !(node instanceof WikiNode)) {
            throw new AssertionError();
        }
        WikiNode wikiNode = (WikiNode) node;
        if ((node instanceof WikiLink) && WikiLinkExtension.ALLOW_INLINES.get(document).booleanValue() && wikiNode.getText().isNull()) {
            wikiNode.setLink(new TextCollectingVisitor().collectAndGetSequence(node, TextContainer.F_NODE_TEXT), WikiLinkExtension.ALLOW_ANCHORS.get(document).booleanValue(), WikiLinkExtension.ALLOW_ANCHOR_ESCAPE.get(document).booleanValue());
        }
    }

    @Override // com.vladsch.flexmark.parser.LinkRefProcessor
    public Node createNode(BasedSequence basedSequence) {
        return basedSequence.firstChar() == '!' ? new WikiImage(basedSequence, this.options.linkFirstSyntax, this.options.allowPipeEscape) : new WikiLink(basedSequence, this.options.linkFirstSyntax, this.options.allowAnchors, this.options.allowPipeEscape, this.options.allowAnchorEscape);
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/internal/WikiLinkLinkRefProcessor$Factory.class */
    public static class Factory implements LinkRefProcessorFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.parser.LinkRefProcessorFactory, java.util.function.Function
        public LinkRefProcessor apply(Document document) {
            return new WikiLinkLinkRefProcessor(document);
        }

        @Override // com.vladsch.flexmark.parser.LinkRefProcessorFactory
        public boolean getWantExclamationPrefix(DataHolder dataHolder) {
            return WikiLinkExtension.IMAGE_LINKS.get(dataHolder).booleanValue();
        }

        @Override // com.vladsch.flexmark.parser.LinkRefProcessorFactory
        public int getBracketNestingLevel(DataHolder dataHolder) {
            return 1;
        }
    }
}
