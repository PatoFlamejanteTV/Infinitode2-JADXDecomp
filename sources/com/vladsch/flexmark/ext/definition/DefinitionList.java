package com.vladsch.flexmark.ext.definition;

import com.vladsch.flexmark.ast.ListBlock;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/definition/DefinitionList.class */
public class DefinitionList extends ListBlock {
    public DefinitionList() {
    }

    public DefinitionList(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public DefinitionList(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public DefinitionList(BlockContent blockContent) {
        super(blockContent);
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }
}
