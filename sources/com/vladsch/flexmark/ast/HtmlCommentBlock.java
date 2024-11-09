package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/HtmlCommentBlock.class */
public class HtmlCommentBlock extends HtmlBlockBase {
    public HtmlCommentBlock() {
    }

    public HtmlCommentBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public HtmlCommentBlock(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public HtmlCommentBlock(BlockContent blockContent) {
        super(blockContent);
    }
}
