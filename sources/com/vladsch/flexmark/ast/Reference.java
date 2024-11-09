package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.PrefixedSubSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/Reference.class */
public class Reference extends LinkNodeBase implements ReferenceNode<ReferenceRepository, Reference, RefNode> {
    protected BasedSequence openingMarker;
    protected BasedSequence reference;
    protected BasedSequence closingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.reference, this.closingMarker, this.urlOpeningMarker, this.url, this.pageRef, this.anchorMarker, this.anchorRef, this.urlClosingMarker, this.titleOpeningMarker, this.title, this.titleClosingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegmentsForChars() {
        return new BasedSequence[]{this.openingMarker, this.reference, this.closingMarker, PrefixedSubSequence.prefixOf(SequenceUtils.SPACE, this.closingMarker.getEmptySuffix()), this.urlOpeningMarker, this.pageRef, this.anchorMarker, this.anchorRef, this.urlClosingMarker, this.titleOpeningMarker, this.title, this.titleClosingMarker};
    }

    @Override // java.lang.Comparable
    public int compareTo(Reference reference) {
        return SequenceUtils.compare(getReference(), reference.getReference(), true);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferenceNode
    public RefNode getReferencingNode(Node node) {
        if (node instanceof RefNode) {
            return (RefNode) node;
        }
        return null;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.reference, this.closingMarker, "ref");
        delimitedSegmentSpanChars(sb, this.urlOpeningMarker, this.url, this.urlClosingMarker, "url");
        delimitedSegmentSpanChars(sb, this.titleOpeningMarker, this.title, this.titleClosingMarker, Attribute.TITLE_ATTR);
    }

    public Reference(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(BasedSequence.NULL);
        this.openingMarker = BasedSequence.NULL;
        this.reference = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence.subSequence(0, 1);
        this.reference = basedSequence.subSequence(1, basedSequence.length() - 2).trim();
        this.closingMarker = basedSequence.subSequence(basedSequence.length() - 2, basedSequence.length());
        setUrlChars(basedSequence2);
        if (basedSequence3 != null) {
            this.titleOpeningMarker = basedSequence3.subSequence(0, 1);
            this.title = basedSequence3.subSequence(1, basedSequence3.length() - 1);
            this.titleClosingMarker = basedSequence3.subSequence(basedSequence3.length() - 1, basedSequence3.length());
        }
        setCharsFromContent();
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getUrlOpeningMarker() {
        return this.urlOpeningMarker;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setUrlOpeningMarker(BasedSequence basedSequence) {
        this.urlOpeningMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getUrlClosingMarker() {
        return this.urlClosingMarker;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setUrlClosingMarker(BasedSequence basedSequence) {
        this.urlClosingMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getTitleOpeningMarker() {
        return this.titleOpeningMarker;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setTitleOpeningMarker(BasedSequence basedSequence) {
        this.titleOpeningMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getTitleClosingMarker() {
        return this.titleClosingMarker;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setTitleClosingMarker(BasedSequence basedSequence) {
        this.titleClosingMarker = basedSequence;
    }

    public BasedSequence getReference() {
        return this.reference;
    }

    public void setReference(BasedSequence basedSequence) {
        this.reference = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getUrl() {
        return this.url;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setUrl(BasedSequence basedSequence) {
        this.url = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getPageRef() {
        return this.pageRef;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setPageRef(BasedSequence basedSequence) {
        this.pageRef = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getAnchorMarker() {
        return this.anchorMarker;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setAnchorMarker(BasedSequence basedSequence) {
        this.anchorMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getAnchorRef() {
        return this.anchorRef;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setAnchorRef(BasedSequence basedSequence) {
        this.anchorRef = basedSequence;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public BasedSequence getTitle() {
        return this.title;
    }

    @Override // com.vladsch.flexmark.ast.LinkNodeBase
    public void setTitle(BasedSequence basedSequence) {
        this.title = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    protected String toStringAttributes() {
        return "reference=" + ((Object) this.reference) + ", url=" + ((Object) this.url);
    }
}
