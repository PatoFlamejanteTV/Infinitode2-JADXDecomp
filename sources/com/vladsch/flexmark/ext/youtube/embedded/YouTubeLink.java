package com.vladsch.flexmark.ext.youtube.embedded;

import com.vladsch.flexmark.ast.InlineLinkNode;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/youtube/embedded/YouTubeLink.class */
public class YouTubeLink extends InlineLinkNode {
    public YouTubeLink() {
    }

    public YouTubeLink(Link link) {
        super(link.baseSubSequence(link.getStartOffset() - 1, link.getEndOffset()), link.baseSubSequence(link.getStartOffset() - 1, link.getTextOpeningMarker().getEndOffset()), link.getText(), link.getTextClosingMarker(), link.getLinkOpeningMarker(), link.getUrl(), link.getTitleOpeningMarker(), link.getTitle(), link.getTitleClosingMarker(), link.getLinkClosingMarker());
    }

    @Override // com.vladsch.flexmark.ast.InlineLinkNode
    public void setTextChars(BasedSequence basedSequence) {
        int length = basedSequence.length();
        this.textOpeningMarker = basedSequence.subSequence(0, 1);
        this.text = basedSequence.subSequence(1, length - 1).trim();
        this.textClosingMarker = basedSequence.subSequence(length - 1, length);
    }
}
