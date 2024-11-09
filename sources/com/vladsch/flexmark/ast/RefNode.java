package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.DoNotLinkDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.ReferencingNode;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/RefNode.class */
public abstract class RefNode extends Node implements LinkRefDerived, DoNotLinkDecorate, ReferencingNode<ReferenceRepository, Reference>, TextContainer {
    protected BasedSequence textOpeningMarker;
    protected BasedSequence text;
    protected BasedSequence textClosingMarker;
    protected BasedSequence referenceOpeningMarker;
    protected BasedSequence reference;
    protected BasedSequence referenceClosingMarker;
    protected boolean isDefined;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        if (isReferenceTextCombined()) {
            return new BasedSequence[]{this.referenceOpeningMarker, this.reference, this.referenceClosingMarker, this.textOpeningMarker, this.text, this.textClosingMarker};
        }
        return new BasedSequence[]{this.textOpeningMarker, this.text, this.textClosingMarker, this.referenceOpeningMarker, this.reference, this.referenceClosingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        if (isReferenceTextCombined()) {
            delimitedSegmentSpanChars(sb, this.referenceOpeningMarker, this.reference, this.referenceClosingMarker, "reference");
            delimitedSegmentSpanChars(sb, this.textOpeningMarker, this.text, this.textClosingMarker, "text");
        } else {
            delimitedSegmentSpanChars(sb, this.textOpeningMarker, this.text, this.textClosingMarker, "text");
            delimitedSegmentSpanChars(sb, this.referenceOpeningMarker, this.reference, this.referenceClosingMarker, "reference");
        }
    }

    public RefNode() {
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.referenceOpeningMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.referenceClosingMarker = BasedSequence.NULL;
        this.isDefined = false;
    }

    public RefNode(BasedSequence basedSequence) {
        super(basedSequence);
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.referenceOpeningMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.referenceClosingMarker = BasedSequence.NULL;
        this.isDefined = false;
    }

    public RefNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence6.getEndOffset()));
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.referenceOpeningMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.referenceClosingMarker = BasedSequence.NULL;
        this.isDefined = false;
        this.textOpeningMarker = basedSequence;
        this.text = basedSequence2;
        this.textClosingMarker = basedSequence3;
        this.referenceOpeningMarker = basedSequence4;
        this.reference = basedSequence5;
        this.referenceClosingMarker = basedSequence6;
    }

    public RefNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5, BasedSequence basedSequence6, BasedSequence basedSequence7) {
        super(basedSequence);
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.referenceOpeningMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.referenceClosingMarker = BasedSequence.NULL;
        this.isDefined = false;
        this.textOpeningMarker = basedSequence2;
        this.text = basedSequence3;
        this.textClosingMarker = basedSequence4;
        this.referenceOpeningMarker = basedSequence5;
        this.reference = basedSequence6;
        this.referenceClosingMarker = basedSequence7;
    }

    public RefNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence3.getEndOffset()));
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.referenceOpeningMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.referenceClosingMarker = BasedSequence.NULL;
        this.isDefined = false;
        this.textOpeningMarker = basedSequence;
        this.text = basedSequence2;
        this.textClosingMarker = basedSequence3;
    }

    public RefNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4) {
        super(basedSequence);
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.referenceOpeningMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.referenceClosingMarker = BasedSequence.NULL;
        this.isDefined = false;
        this.textOpeningMarker = basedSequence2;
        this.text = basedSequence3;
        this.textClosingMarker = basedSequence4;
    }

    public RefNode(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3, BasedSequence basedSequence4, BasedSequence basedSequence5) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence5.getEndOffset()));
        this.textOpeningMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.textClosingMarker = BasedSequence.NULL;
        this.referenceOpeningMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.referenceClosingMarker = BasedSequence.NULL;
        this.isDefined = false;
        this.textOpeningMarker = basedSequence;
        this.text = basedSequence2;
        this.textClosingMarker = basedSequence3;
        this.referenceOpeningMarker = basedSequence4;
        this.referenceClosingMarker = basedSequence5;
    }

    public void setReferenceChars(BasedSequence basedSequence) {
        int length = basedSequence.length();
        int i = basedSequence.charAt(0) == '!' ? 2 : 1;
        this.referenceOpeningMarker = basedSequence.subSequence(0, i);
        this.reference = basedSequence.subSequence(i, length - 1).trim();
        this.referenceClosingMarker = basedSequence.subSequence(length - 1, length);
    }

    public void setTextChars(BasedSequence basedSequence) {
        int length = basedSequence.length();
        this.textOpeningMarker = basedSequence.subSequence(0, 1);
        this.text = basedSequence.subSequence(1, length - 1).trim();
        this.textClosingMarker = basedSequence.subSequence(length - 1, length);
    }

    public boolean isReferenceTextCombined() {
        return this.text == BasedSequence.NULL;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public boolean isDefined() {
        return this.isDefined;
    }

    public void setDefined(boolean z) {
        this.isDefined = z;
    }

    @Override // com.vladsch.flexmark.ast.LinkRefDerived
    public boolean isTentative() {
        return !this.isDefined;
    }

    public boolean isDummyReference() {
        return (this.textOpeningMarker == BasedSequence.NULL || this.text != BasedSequence.NULL || this.textClosingMarker == BasedSequence.NULL) ? false : true;
    }

    public BasedSequence getText() {
        return this.text;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public BasedSequence getReference() {
        return this.reference;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public Reference getReferenceNode(Document document) {
        return getReferenceNode(Parser.REFERENCES.get(document));
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public Reference getReferenceNode(ReferenceRepository referenceRepository) {
        if (referenceRepository == null) {
            return null;
        }
        return referenceRepository.get(referenceRepository.normalizeKey(this.reference));
    }

    public BasedSequence getTextOpeningMarker() {
        return this.textOpeningMarker;
    }

    public void setTextOpeningMarker(BasedSequence basedSequence) {
        this.textOpeningMarker = basedSequence;
    }

    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence;
    }

    public BasedSequence getTextClosingMarker() {
        return this.textClosingMarker;
    }

    public void setTextClosingMarker(BasedSequence basedSequence) {
        this.textClosingMarker = basedSequence;
    }

    public BasedSequence getReferenceOpeningMarker() {
        return this.referenceOpeningMarker;
    }

    public void setReferenceOpeningMarker(BasedSequence basedSequence) {
        this.referenceOpeningMarker = basedSequence;
    }

    public void setReference(BasedSequence basedSequence) {
        this.reference = basedSequence;
    }

    public BasedSequence getDummyReference() {
        if (isDummyReference()) {
            return getChars().baseSubSequence(this.textOpeningMarker.getStartOffset(), this.textClosingMarker.getEndOffset());
        }
        return BasedSequence.NULL;
    }

    public BasedSequence getReferenceClosingMarker() {
        return this.referenceClosingMarker;
    }

    public void setReferenceClosingMarker(BasedSequence basedSequence) {
        this.referenceClosingMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.TextContainer
    public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> iSequenceBuilder, int i, NodeVisitor nodeVisitor) {
        BasedSequence url;
        int i2 = i & F_LINK_TEXT_TYPE;
        if (i2 == 0) {
            if (BitFieldSet.any(i, F_FOR_HEADING_ID) && (this instanceof ImageRef)) {
                return false;
            }
            if (BitFieldSet.any(i, F_FOR_HEADING_ID) && BitFieldSet.any(i, F_NO_TRIM_REF_TEXT_START | F_NO_TRIM_REF_TEXT_END)) {
                BasedSequence[] segments = getSegments();
                if (BitFieldSet.any(i, F_NO_TRIM_REF_TEXT_START)) {
                    iSequenceBuilder.append((CharSequence) getChars().baseSubSequence(segments[0].getEndOffset(), segments[1].getStartOffset()));
                }
                nodeVisitor.visitChildren(this);
                if (BitFieldSet.any(i, F_NO_TRIM_REF_TEXT_END)) {
                    iSequenceBuilder.append((CharSequence) getChars().baseSubSequence(segments[1].getEndOffset(), segments[2].getStartOffset()));
                    return false;
                }
                return false;
            }
            return true;
        }
        Reference referenceNode = getReferenceNode(getDocument());
        if (i2 == 4) {
            iSequenceBuilder.append((CharSequence) getChars());
            return false;
        }
        if (referenceNode == null) {
            return true;
        }
        switch (i2) {
            case 1:
                url = referenceNode.getPageRef();
                break;
            case 2:
                url = referenceNode.getAnchorRef();
                break;
            case 3:
                url = referenceNode.getUrl();
                break;
            default:
                return true;
        }
        ReplacedTextMapper replacedTextMapper = new ReplacedTextMapper(url);
        iSequenceBuilder.append((CharSequence) Escaping.percentDecodeUrl(Escaping.unescape(url, replacedTextMapper), replacedTextMapper));
        return false;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    protected String toStringAttributes() {
        return "text=" + ((Object) this.text) + ", reference=" + ((Object) this.reference);
    }
}
