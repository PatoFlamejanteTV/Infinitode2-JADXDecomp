package com.vladsch.flexmark.ext.macros;

import com.vladsch.flexmark.ext.macros.internal.MacroDefinitionRepository;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/MacroDefinitionBlock.class */
public class MacroDefinitionBlock extends Block implements ReferenceNode<MacroDefinitionRepository, MacroDefinitionBlock, MacroReference> {
    private BasedSequence openingMarker;
    private BasedSequence name;
    private BasedSequence openingTrailing;
    private BasedSequence closingMarker;
    private BasedSequence closingTrailing;
    private int ordinal;
    private int firstReferenceOffset;
    private int footnoteReferences;
    private boolean inExpansion;

    public int getFootnoteReferences() {
        return this.footnoteReferences;
    }

    public void setFootnoteReferences(int i) {
        this.footnoteReferences = i;
    }

    public int getFirstReferenceOffset() {
        return this.firstReferenceOffset;
    }

    public void setFirstReferenceOffset(int i) {
        this.firstReferenceOffset = i;
    }

    public void addFirstReferenceOffset(int i) {
        if (this.firstReferenceOffset < i) {
            this.firstReferenceOffset = i;
        }
    }

    public boolean isReferenced() {
        return this.firstReferenceOffset < Integer.MAX_VALUE;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(int i) {
        this.ordinal = i;
    }

    public boolean isInExpansion() {
        return this.inExpansion;
    }

    public void setInExpansion(boolean z) {
        this.inExpansion = z;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        segmentSpanChars(sb, this.openingMarker, "open");
        segmentSpanChars(sb, this.name, Attribute.NAME_ATTR);
        segmentSpanChars(sb, this.openingTrailing, "openTrail");
        segmentSpanChars(sb, this.closingMarker, "close");
        segmentSpanChars(sb, this.closingTrailing, "closeTrail");
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.name, this.openingTrailing, this.closingMarker, this.closingTrailing};
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferenceNode
    public MacroReference getReferencingNode(Node node) {
        if (node instanceof MacroReference) {
            return (MacroReference) node;
        }
        return null;
    }

    @Override // java.lang.Comparable
    public int compareTo(MacroDefinitionBlock macroDefinitionBlock) {
        return SequenceUtils.compare(this.name, macroDefinitionBlock.name, true);
    }

    public MacroDefinitionBlock() {
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
        this.ordinal = 0;
        this.firstReferenceOffset = Integer.MAX_VALUE;
        this.footnoteReferences = 0;
        this.inExpansion = false;
    }

    public MacroDefinitionBlock(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
        this.ordinal = 0;
        this.firstReferenceOffset = Integer.MAX_VALUE;
        this.footnoteReferences = 0;
        this.inExpansion = false;
    }

    public MacroDefinitionBlock(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
        this.ordinal = 0;
        this.firstReferenceOffset = Integer.MAX_VALUE;
        this.footnoteReferences = 0;
        this.inExpansion = false;
    }

    public MacroDefinitionBlock(BlockContent blockContent) {
        super(blockContent);
        this.openingMarker = BasedSequence.NULL;
        this.name = BasedSequence.NULL;
        this.openingTrailing = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.closingTrailing = BasedSequence.NULL;
        this.ordinal = 0;
        this.firstReferenceOffset = Integer.MAX_VALUE;
        this.footnoteReferences = 0;
        this.inExpansion = false;
    }

    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    public BasedSequence getName() {
        return this.name;
    }

    public void setName(BasedSequence basedSequence) {
        this.name = basedSequence;
    }

    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }

    public BasedSequence getOpeningTrailing() {
        return this.openingTrailing;
    }

    public void setOpeningTrailing(BasedSequence basedSequence) {
        this.openingTrailing = basedSequence;
    }

    public BasedSequence getClosingTrailing() {
        return this.closingTrailing;
    }

    public void setClosingTrailing(BasedSequence basedSequence) {
        this.closingTrailing = basedSequence;
    }
}
