package com.vladsch.flexmark.ext.anchorlink.internal;

import com.vladsch.flexmark.ast.BlockQuote;
import com.vladsch.flexmark.ast.Heading;
import com.vladsch.flexmark.ext.anchorlink.AnchorLink;
import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.data.DataHolder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/anchorlink/internal/AnchorLinkNodePostProcessor.class */
public class AnchorLinkNodePostProcessor extends NodePostProcessor {
    private final AnchorLinkOptions options;

    public AnchorLinkNodePostProcessor(DataHolder dataHolder) {
        this.options = new AnchorLinkOptions(dataHolder);
    }

    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        if (node instanceof Heading) {
            Heading heading = (Heading) node;
            if (heading.getText().isNotNull()) {
                Node anchorLink = new AnchorLink();
                if (!this.options.wrapText) {
                    if (heading.getFirstChild() == null) {
                        anchorLink.setChars(heading.getText().subSequence(0, 0));
                        heading.appendChild(anchorLink);
                    } else {
                        anchorLink.setChars(heading.getFirstChild().getChars().subSequence(0, 0));
                        heading.getFirstChild().insertBefore(anchorLink);
                    }
                } else {
                    anchorLink.takeChildren(heading);
                    heading.appendChild(anchorLink);
                }
                anchorLink.setCharsFromContent();
                nodeTracker.nodeAdded(anchorLink);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/anchorlink/internal/AnchorLinkNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory(DataHolder dataHolder) {
            super(false);
            if (AnchorLinkExtension.ANCHORLINKS_NO_BLOCK_QUOTE.get(dataHolder).booleanValue()) {
                addNodeWithExclusions(Heading.class, BlockQuote.class);
            } else {
                addNodes(Heading.class);
            }
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new AnchorLinkNodePostProcessor(document);
        }
    }
}
