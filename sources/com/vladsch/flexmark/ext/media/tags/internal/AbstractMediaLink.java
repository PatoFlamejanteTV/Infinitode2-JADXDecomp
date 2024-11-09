package com.vladsch.flexmark.ext.media.tags.internal;

import com.vladsch.flexmark.ast.InlineLinkNode;
import com.vladsch.flexmark.ast.Link;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/media/tags/internal/AbstractMediaLink.class */
public abstract class AbstractMediaLink extends InlineLinkNode {
    private static final String INVALID_SEQUENCE = "%s Link's CharSequence MUST start with an '%s'!";
    private final String PREFIX;
    private final String TYPE;

    public AbstractMediaLink(String str, String str2) {
        this.PREFIX = str;
        this.TYPE = str2;
    }

    public AbstractMediaLink(String str, String str2, Link link) {
        super(link.baseSubSequence(link.getStartOffset() - str.length(), link.getEndOffset()), link.baseSubSequence(link.getStartOffset() - str.length(), link.getTextOpeningMarker().getEndOffset()), link.getText(), link.getTextClosingMarker(), link.getLinkOpeningMarker(), link.getUrl(), link.getTitleOpeningMarker(), link.getTitle(), link.getTitleClosingMarker(), link.getLinkClosingMarker());
        this.PREFIX = str;
        this.TYPE = str2;
        verifyBasedSequence(link.getChars(), link.getStartOffset() - str.length());
    }

    public final String getPrefix() {
        return this.PREFIX;
    }

    @Override // com.vladsch.flexmark.ast.InlineLinkNode
    public void setTextChars(BasedSequence basedSequence) {
        verifyBasedSequence(basedSequence, 0);
        int length = basedSequence.length();
        this.textOpeningMarker = basedSequence.subSequence(0, this.PREFIX.length() + 1);
        this.text = basedSequence.subSequence(this.PREFIX.length() + 2, length - 1).trim();
        this.textClosingMarker = basedSequence.subSequence(length - 1, length);
    }

    protected final void verifyBasedSequence(BasedSequence basedSequence, int i) {
        if (!basedSequence.baseSubSequence(i, i + this.PREFIX.length()).matches(this.PREFIX)) {
            throw new IllegalArgumentException(String.format(INVALID_SEQUENCE, this.TYPE, this.PREFIX));
        }
    }
}
