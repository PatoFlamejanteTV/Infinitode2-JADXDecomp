package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/CodeBlock.class */
public class CodeBlock extends Block {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public CodeBlock() {
    }

    public CodeBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public CodeBlock(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public CodeBlock(BlockContent blockContent) {
        super(blockContent);
    }
}
