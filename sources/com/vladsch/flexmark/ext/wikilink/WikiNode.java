package com.vladsch.flexmark.ext.wikilink;

import com.vladsch.flexmark.ast.LinkRefDerived;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/wikilink/WikiNode.class */
public class WikiNode extends Node implements LinkRefDerived, DoNotDecorate, TextContainer {
    public static final char SEPARATOR_CHAR = '|';
    protected BasedSequence openingMarker;
    protected BasedSequence link;
    protected BasedSequence pageRef;
    protected BasedSequence anchorMarker;
    protected BasedSequence anchorRef;
    protected BasedSequence textSeparatorMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected final boolean linkIsFirst;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        if (this.linkIsFirst) {
            return new BasedSequence[]{this.openingMarker, this.link, this.pageRef, this.anchorMarker, this.anchorRef, this.textSeparatorMarker, this.text, this.closingMarker};
        }
        return new BasedSequence[]{this.openingMarker, this.text, this.textSeparatorMarker, this.link, this.pageRef, this.anchorMarker, this.anchorRef, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        if (this.linkIsFirst) {
            segmentSpanChars(sb, this.openingMarker, "linkOpen");
            segmentSpanChars(sb, this.text, "text");
            segmentSpanChars(sb, this.textSeparatorMarker, "textSep");
            segmentSpanChars(sb, this.link, "link");
            if (this.pageRef.isNotNull()) {
                segmentSpanChars(sb, this.pageRef, "pageRef");
            }
            if (this.anchorMarker.isNotNull()) {
                segmentSpanChars(sb, this.anchorMarker, "anchorMarker");
            }
            if (this.anchorRef.isNotNull()) {
                segmentSpanChars(sb, this.anchorRef, "anchorRef");
            }
            segmentSpanChars(sb, this.closingMarker, "linkClose");
            return;
        }
        segmentSpanChars(sb, this.openingMarker, "linkOpen");
        segmentSpanChars(sb, this.link, "link");
        if (this.pageRef.isNotNull()) {
            segmentSpanChars(sb, this.pageRef, "pageRef");
        }
        if (this.anchorMarker.isNotNull()) {
            segmentSpanChars(sb, this.anchorMarker, "anchorMarker");
        }
        if (this.anchorRef.isNotNull()) {
            segmentSpanChars(sb, this.anchorRef, "anchorRef");
        }
        segmentSpanChars(sb, this.textSeparatorMarker, "textSep");
        segmentSpanChars(sb, this.text, "text");
        segmentSpanChars(sb, this.closingMarker, "linkClose");
    }

    public boolean isLinkIsFirst() {
        return this.linkIsFirst;
    }

    public WikiNode(boolean z) {
        this.openingMarker = BasedSequence.NULL;
        this.link = BasedSequence.NULL;
        this.pageRef = BasedSequence.NULL;
        this.anchorMarker = BasedSequence.NULL;
        this.anchorRef = BasedSequence.NULL;
        this.textSeparatorMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.linkIsFirst = z;
    }

    @Override // com.vladsch.flexmark.ast.LinkRefDerived
    public boolean isTentative() {
        return false;
    }

    public WikiNode(BasedSequence basedSequence, boolean z, boolean z2, boolean z3, boolean z4) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.link = BasedSequence.NULL;
        this.pageRef = BasedSequence.NULL;
        this.anchorMarker = BasedSequence.NULL;
        this.anchorRef = BasedSequence.NULL;
        this.textSeparatorMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.linkIsFirst = z;
        setLinkChars(basedSequence, z2, z3, z4);
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getPageRef() {
        return this.pageRef;
    }

    public void setPageRef(BasedSequence basedSequence) {
        this.pageRef = basedSequence;
    }

    public BasedSequence getTextSeparatorMarker() {
        return this.textSeparatorMarker;
    }

    public void setTextSeparatorMarker(BasedSequence basedSequence) {
        this.textSeparatorMarker = basedSequence;
    }

    public BasedSequence getText() {
        return this.text;
    }

    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public BasedSequence getAnchorMarker() {
        return this.anchorMarker;
    }

    public void setAnchorMarker(BasedSequence basedSequence) {
        this.anchorMarker = basedSequence;
    }

    public BasedSequence getAnchorRef() {
        return this.anchorRef;
    }

    public void setAnchorRef(BasedSequence basedSequence) {
        this.anchorRef = basedSequence;
    }

    public BasedSequence getLink() {
        return this.link;
    }

    public void setLink(BasedSequence basedSequence, boolean z, boolean z2) {
        this.link = basedSequence;
        if (!z) {
            this.pageRef = basedSequence;
            return;
        }
        int i = -1;
        do {
            int indexOf = basedSequence.indexOf('#', i + 1);
            i = indexOf;
            if (indexOf == -1 || !z2 || i <= 0 || basedSequence.charAt(i - 1) != '\\') {
                break;
            }
        } while ((basedSequence.subSequence(0, i).countTrailing(CharPredicate.BACKSLASH) & 1) == 1);
        if (i < 0) {
            this.pageRef = basedSequence;
            return;
        }
        this.pageRef = basedSequence.subSequence(0, i);
        this.anchorMarker = basedSequence.subSequence(i, i + 1);
        this.anchorRef = basedSequence.subSequence(i + 1);
    }

    public void setLinkChars(BasedSequence basedSequence, boolean z, boolean z2, boolean z3) {
        int i;
        BasedSequence subSequence;
        int length = basedSequence.length();
        int i2 = this instanceof WikiImage ? 3 : 2;
        this.openingMarker = basedSequence.subSequence(0, i2);
        this.closingMarker = basedSequence.subSequence(length - 2, length);
        if (this.linkIsFirst) {
            i = basedSequence.length() - 2;
            do {
                int lastIndexOf = basedSequence.lastIndexOf('|', i - 1);
                i = lastIndexOf;
                if (lastIndexOf == -1 || !z2 || i <= 0 || basedSequence.charAt(i - 1) != '\\') {
                    break;
                }
            } while ((basedSequence.subSequence(0, i).countTrailing(CharPredicate.BACKSLASH) & 1) == 1);
        } else {
            i = -1;
            do {
                int indexOf = basedSequence.indexOf('|', i + 1);
                i = indexOf;
                if (indexOf == -1 || !z2 || i <= 0 || basedSequence.charAt(i - 1) != '\\') {
                    break;
                }
            } while ((basedSequence.subSequence(0, i).countTrailing(CharPredicate.BACKSLASH) & 1) == 1);
        }
        if (i < 0) {
            subSequence = basedSequence.subSequence(i2, length - 2);
        } else {
            int i3 = i;
            this.textSeparatorMarker = basedSequence.subSequence(i3, i3 + 1);
            if (this.linkIsFirst) {
                subSequence = basedSequence.subSequence(i2, i);
                this.text = basedSequence.subSequence(i + 1, length - 2);
            } else {
                this.text = basedSequence.subSequence(i2, i);
                subSequence = basedSequence.subSequence(i + 1, length - 2);
            }
        }
        setLink(subSequence, z, z3);
        if (this.text.isNull() && z && !this.anchorMarker.isNull()) {
            this.text = this.pageRef;
        }
    }

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        BasedSequence basedSequence;
        int i2 = i & F_LINK_TEXT_TYPE;
        switch (i2) {
            case 1:
                basedSequence = getPageRef();
                break;
            case 2:
                basedSequence = getAnchorRef();
                break;
            case 3:
                basedSequence = getLink();
                break;
            case 4:
                basedSequence = BasedSequence.NULL;
                break;
            default:
                return true;
        }
        if (i2 == 4) {
            iSequenceBuilder.append((CharSequence) getChars());
            return false;
        }
        iSequenceBuilder.append((CharSequence) Escaping.unescape(basedSequence, new ReplacedTextMapper(basedSequence)));
        return false;
    }
}
