package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/IndentedCodeBlock.class */
public class IndentedCodeBlock extends Block {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public IndentedCodeBlock() {
    }

    public IndentedCodeBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public IndentedCodeBlock(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public IndentedCodeBlock(BlockContent blockContent) {
        super(blockContent);
    }
}
