package com.vladsch.flexmark.ext.macros;

import com.vladsch.flexmark.ext.macros.internal.MacroDefinitionRepository;
import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferencingNode;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/macros/MacroReference.class */
public class MacroReference extends Node implements DelimitedNode, DoNotDecorate, ReferencingNode<MacroDefinitionRepository, MacroDefinitionBlock> {
    protected BasedSequence openingMarker;
    protected BasedSequence text;
    protected BasedSequence closingMarker;
    protected MacroDefinitionBlock myMacroDefinitionBlock;

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public boolean isDefined() {
        return this.myMacroDefinitionBlock != null;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public BasedSequence getReference() {
        return this.text;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public MacroDefinitionBlock getReferenceNode(Document document) {
        if (this.myMacroDefinitionBlock != null || this.text.isEmpty()) {
            return this.myMacroDefinitionBlock;
        }
        this.myMacroDefinitionBlock = getMacroDefinitionBlock(MacrosExtension.MACRO_DEFINITIONS.get(document));
        return this.myMacroDefinitionBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.ReferencingNode
    public MacroDefinitionBlock getReferenceNode(MacroDefinitionRepository macroDefinitionRepository) {
        if (this.myMacroDefinitionBlock != null || this.text.isEmpty()) {
            return this.myMacroDefinitionBlock;
        }
        this.myMacroDefinitionBlock = getMacroDefinitionBlock(macroDefinitionRepository);
        return this.myMacroDefinitionBlock;
    }

    public MacroDefinitionBlock getMacroDefinitionBlock(MacroDefinitionRepository macroDefinitionRepository) {
        if (this.text.isEmpty()) {
            return null;
        }
        return macroDefinitionRepository.get(this.text.toString());
    }

    public MacroDefinitionBlock getMacroDefinitionBlock() {
        return this.myMacroDefinitionBlock;
    }

    public void setMacroDefinitionBlock(MacroDefinitionBlock macroDefinitionBlock) {
        this.myMacroDefinitionBlock = macroDefinitionBlock;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return new BasedSequence[]{this.openingMarker, this.text, this.closingMarker};
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        delimitedSegmentSpanChars(sb, this.openingMarker, this.text, this.closingMarker, "text");
    }

    public MacroReference() {
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public MacroReference(BasedSequence basedSequence) {
        super(basedSequence);
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
    }

    public MacroReference(BasedSequence basedSequence, BasedSequence basedSequence2, BasedSequence basedSequence3) {
        super(basedSequence.baseSubSequence(basedSequence.getStartOffset(), basedSequence3.getEndOffset()));
        this.openingMarker = BasedSequence.NULL;
        this.text = BasedSequence.NULL;
        this.closingMarker = BasedSequence.NULL;
        this.openingMarker = basedSequence;
        this.text = basedSequence2;
        this.closingMarker = basedSequence3;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getOpeningMarker() {
        return this.openingMarker;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setOpeningMarker(BasedSequence basedSequence) {
        this.openingMarker = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getText() {
        return this.text;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setText(BasedSequence basedSequence) {
        this.text = basedSequence;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public BasedSequence getClosingMarker() {
        return this.closingMarker;
    }

    @Override // com.vladsch.flexmark.util.ast.DelimitedNode
    public void setClosingMarker(BasedSequence basedSequence) {
        this.closingMarker = basedSequence;
    }
}
