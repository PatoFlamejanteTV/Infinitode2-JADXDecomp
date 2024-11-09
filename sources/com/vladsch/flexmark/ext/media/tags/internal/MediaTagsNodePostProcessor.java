package com.vladsch.flexmark.ext.media.tags.internal;

import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.ast.Text;
import com.vladsch.flexmark.ext.media.tags.AudioLink;
import com.vladsch.flexmark.ext.media.tags.EmbedLink;
import com.vladsch.flexmark.ext.media.tags.PictureLink;
import com.vladsch.flexmark.ext.media.tags.VideoLink;
import com.vladsch.flexmark.parser.block.NodePostProcessor;
import com.vladsch.flexmark.parser.block.NodePostProcessorFactory;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeTracker;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/internal/MediaTagsNodePostProcessor.class */
public class MediaTagsNodePostProcessor extends NodePostProcessor {
    public MediaTagsNodePostProcessor(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.PostProcessor
    public void process(NodeTracker nodeTracker, Node node) {
        AbstractMediaLink videoLink;
        if (node instanceof Link) {
            Node previous = node.getPrevious();
            if (previous instanceof Text) {
                BasedSequence chars = previous.getChars();
                if (chars.isContinuedBy(node.getChars())) {
                    if (chars.endsWith(AudioLink.PREFIX) && !isEscaped(chars, AudioLink.PREFIX)) {
                        videoLink = new AudioLink((Link) node);
                    } else if (chars.endsWith(EmbedLink.PREFIX) && !isEscaped(chars, EmbedLink.PREFIX)) {
                        videoLink = new EmbedLink((Link) node);
                    } else if (chars.endsWith(PictureLink.PREFIX) && !isEscaped(chars, PictureLink.PREFIX)) {
                        videoLink = new PictureLink((Link) node);
                    } else if (chars.endsWith(VideoLink.PREFIX) && !isEscaped(chars, VideoLink.PREFIX)) {
                        videoLink = new VideoLink((Link) node);
                    } else {
                        return;
                    }
                    videoLink.takeChildren(node);
                    node.unlink();
                    nodeTracker.nodeRemoved(node);
                    previous.insertAfter(videoLink);
                    nodeTracker.nodeAddedWithChildren(videoLink);
                    previous.setChars(chars.subSequence(0, chars.length() - videoLink.getPrefix().length()));
                    if (previous.getChars().length() == 0) {
                        previous.unlink();
                        nodeTracker.nodeRemoved(previous);
                    }
                }
            }
        }
    }

    private boolean isEscaped(BasedSequence basedSequence, String str) {
        return (basedSequence.subSequence(0, basedSequence.length() - str.length()).countTrailing(CharPredicate.BACKSLASH) & 1) != 0;
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/internal/MediaTagsNodePostProcessor$Factory.class */
    public static class Factory extends NodePostProcessorFactory {
        public Factory(DataHolder dataHolder) {
            super(false);
            addNodes(Link.class);
        }

        @Override // com.vladsch.flexmark.parser.block.NodePostProcessorFactory, com.vladsch.flexmark.parser.PostProcessorFactory, java.util.function.Function
        public NodePostProcessor apply(Document document) {
            return new MediaTagsNodePostProcessor(document);
        }
    }
}
