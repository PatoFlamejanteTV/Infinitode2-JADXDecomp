package com.vladsch.flexmark.ext.definition;

import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/DefinitionTerm.class */
public class DefinitionTerm extends ListItem {
    @Override // com.vladsch.flexmark.ast.ListItem, com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
    }

    @Override // com.vladsch.flexmark.ast.ListItem, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return Node.EMPTY_SEGMENTS;
    }

    public DefinitionTerm() {
    }

    public DefinitionTerm(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public DefinitionTerm(Node node) {
        appendChild(node);
        setCharsFromContent();
    }

    @Override // com.vladsch.flexmark.ast.ListItem, com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphWrappingDisabled(Paragraph paragraph, ListOptions listOptions, DataHolder dataHolder) {
        return true;
    }
}
