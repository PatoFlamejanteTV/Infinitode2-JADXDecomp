package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/HtmlBlockBase.class */
public abstract class HtmlBlockBase extends Block {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public HtmlBlockBase() {
    }

    public HtmlBlockBase(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public HtmlBlockBase(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public HtmlBlockBase(BlockContent blockContent) {
        super(blockContent);
    }
}
