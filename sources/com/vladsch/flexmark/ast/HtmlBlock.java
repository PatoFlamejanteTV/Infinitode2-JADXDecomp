package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/HtmlBlock.class */
public class HtmlBlock extends HtmlBlockBase {
    @Override // com.vladsch.flexmark.ast.HtmlBlockBase, com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public HtmlBlock() {
    }

    public HtmlBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public HtmlBlock(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public HtmlBlock(BlockContent blockContent) {
        super(blockContent);
    }
}
