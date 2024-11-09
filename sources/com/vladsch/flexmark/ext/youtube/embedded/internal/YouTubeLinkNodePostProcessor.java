package com.vladsch.flexmark.ext.youtube.embedded.internal;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ext.youtube.embedded.YouTubeLink;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/youtube/embedded/internal/YouTubeLinkNodePostProcessor.class */
public class YouTubeLinkNodePostProcessor extends NodePostProcessor {
    public YouTubeLinkNodePostProcessor(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        if (node instanceof Link) {
            Node previous = node.getPrevious();
            if (previous instanceof Text) {
                BasedSequence chars = previous.getChars();
                if (chars.endsWith("@") && chars.isContinuedBy(node.getChars()) && (chars.subSequence(0, chars.length() - 1).countTrailing(CharPredicate.BACKSLASH) & 1) == 0) {
                    previous.setChars(chars.subSequence(0, chars.length() - 1));
                    YouTubeLink youTubeLink = new YouTubeLink((Link) node);
                    youTubeLink.takeChildren(node);
                    node.unlink();
                    previous.insertAfter(youTubeLink);
                    nodeTracker.nodeRemoved(node);
                    nodeTracker.nodeAddedWithChildren(youTubeLink);
                }
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/youtube/embedded/internal/YouTubeLinkNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory(DataHolder dataHolder) {
            super(false);
            addNodes(Link.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new YouTubeLinkNodePostProcessor(document);
        }
    }
}
