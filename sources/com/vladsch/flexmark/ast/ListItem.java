package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.BlankLineContainer;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/ListItem.class */
public abstract class ListItem extends Block implements ParagraphContainer, ParagraphItemContainer, BlankLineContainer {
    protected BasedSequence openingMarker;
    protected BasedSequence markerSuffix;
    private boolean tight;
    private boolean hadBlankAfterItemParagraph;
    private boolean containsBlankLine;
    private int priority;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ListItem.class.desiredAssertionStatus();
    }

    public ListItem() {
        this.openingMarker = BasedSequence.NULL;
        this.markerSuffix = BasedSequence.NULL;
        this.tight = true;
        this.hadBlankAfterItemParagraph = false;
        this.containsBlankLine = false;
        this.priority = Integer.MIN_VALUE;
    }

    public ListItem(ListItem listItem) {
        this.openingMarker = BasedSequence.NULL;
        this.markerSuffix = BasedSequence.NULL;
        this.tight = true;
        this.hadBlankAfterItemParagraph = false;
        this.containsBlankLine = false;
        this.priority = Integer.MIN_VALUE;
        this.openingMarker = listItem.openingMarker;
        this.markerSuffix = listItem.markerSuffix;
        this.tight = listItem.tight;
        this.hadBlankAfterItemParagraph = listItem.hadBlankAfterItemParagraph;
        this.containsBlankLine = listItem.containsBlankLine;
        this.priority = listItem.priority;
        takeChildren(listItem);
        setCharsFromContent();
    }

    public ListItem(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.markerSuffix = BasedSequence.NULL;
        this.tight = true;
        this.hadBlankAfterItemParagraph = false;
        this.containsBlankLine = false;
        this.priority = Integer.MIN_VALUE;
    }

    public ListItem(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
        this.openingMarker = BasedSequence.NULL;
        this.markerSuffix = BasedSequence.NULL;
        this.tight = true;
        this.hadBlankAfterItemParagraph = false;
        this.containsBlankLine = false;
        this.priority = Integer.MIN_VALUE;
    }

    public ListItem(BlockContent blockContent) {
        super(blockContent);
        this.openingMarker = BasedSequence.NULL;
        this.markerSuffix = BasedSequence.NULL;
        this.tight = true;
        this.hadBlankAfterItemParagraph = false;
        this.containsBlankLine = false;
        this.priority = Integer.MIN_VALUE;
    }

    public boolean isOrderedItem() {
        return false;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.markerSuffix, "openSuffix");
        if (isTight()) {
            sb.append(" isTight");
        } else {
            sb.append(" isLoose");
        }
        if (isHadBlankAfterItemParagraph()) {
            sb.append(" hadBlankLineAfter");
        } else if (isContainsBlankLine()) {
            sb.append(" hadBlankLine");
        }
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.markerSuffix};
    }

    public boolean canChangeMarker() {
        return true;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int i) {
        this.priority = i;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getMarkerSuffix() {
        return this.markerSuffix;
    }

    public void setMarkerSuffix(BasedSequence basedSequence) {
        if (!$assertionsDisabled && !basedSequence.isNull() && this.openingMarker.getBase() != basedSequence.getBase()) {
            throw new AssertionError();
        }
        this.markerSuffix = basedSequence;
    }

    public void setTight(boolean z) {
        this.tight = z;
    }

    public void setLoose(boolean z) {
        this.tight = !z;
    }

    public boolean isTight() {
        return this.tight && isInTightList();
    }

    public boolean isOwnTight() {
        return this.tight;
    }

    public boolean isLoose() {
        return !isTight();
    }

    @Override // com.vladsch.flexmark.ast.ParagraphContainer
    public boolean isParagraphEndWrappingDisabled(Paragraph paragraph) {
        if (getFirstChild() != paragraph && getLastChild() == paragraph) {
            return true;
        }
        if (getFirstChild() == paragraph) {
            return getParent() == null || getParent().getLastChildAny(ListItem.class) == this;
        }
        return false;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphContainer
    public boolean isParagraphStartWrappingDisabled(Paragraph paragraph) {
        return isItemParagraph(paragraph);
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphInTightListItem(Paragraph paragraph) {
        if (isTight()) {
            return isItemParagraph(paragraph);
        }
        return false;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isItemParagraph(Paragraph paragraph) {
        Node node;
        Node firstChild = getFirstChild();
        while (true) {
            node = firstChild;
            if (node == null || (node instanceof Paragraph)) {
                break;
            }
            firstChild = node.getNext();
        }
        return node == paragraph;
    }

    @Override // com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphWrappingDisabled(Paragraph paragraph, ListOptions listOptions, DataHolder dataHolder) {
        if ($assertionsDisabled || paragraph.getParent() == this) {
            return listOptions.isInTightListItem(paragraph);
        }
        throw new AssertionError();
    }

    public boolean isInTightList() {
        return !(getParent() instanceof ListBlock) || ((ListBlock) getParent()).isTight();
    }

    public boolean isHadBlankAfterItemParagraph() {
        return this.hadBlankAfterItemParagraph;
    }

    public boolean isContainsBlankLine() {
        return this.containsBlankLine;
    }

    public void setContainsBlankLine(boolean z) {
        this.containsBlankLine = z;
    }

    public void setHadBlankAfterItemParagraph(boolean z) {
        this.hadBlankAfterItemParagraph = z;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public Node getLastBlankLineChild() {
        return getLastChild();
    }
}
