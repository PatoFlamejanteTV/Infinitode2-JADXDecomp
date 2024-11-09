package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/ThematicBreak.class */
public class ThematicBreak extends Block {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public ThematicBreak() {
    }

    public ThematicBreak(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public ThematicBreak(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public ThematicBreak(BlockContent blockContent) {
        super(blockContent);
    }
}
