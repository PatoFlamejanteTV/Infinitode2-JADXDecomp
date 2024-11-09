package com.vladsch.flexmark.ext.jekyll.tag;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/jekyll/tag/JekyllTagBlock.class */
public class JekyllTagBlock extends Block {
    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return Node.EMPTY_SEGMENTS;
    }

    public JekyllTagBlock() {
    }

    public JekyllTagBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public JekyllTagBlock(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public JekyllTagBlock(List<BasedSequence> list) {
        super(list);
    }

    public JekyllTagBlock(BlockContent blockContent) {
        super(blockContent);
    }

    public JekyllTagBlock(Node node) {
        appendChild(node);
        setCharsFromContent();
    }
}
