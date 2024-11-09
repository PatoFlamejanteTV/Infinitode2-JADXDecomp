package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/Block.class */
public abstract class Block extends ContentNode {
    public Block() {
    }

    public Block(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public Block(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public Block(List<BasedSequence> list) {
        super(list);
    }

    public Block(BlockContent blockContent) {
        super(blockContent);
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public Block getParent() {
        return (Block) super.getParent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vladsch.flexmark.util.ast.Node
    public void setParent(Node node) {
        if (node != null && !(node instanceof Block)) {
            throw new IllegalArgumentException("Parent of block must also be block (can not be inline)");
        }
        super.setParent(node);
    }
}
