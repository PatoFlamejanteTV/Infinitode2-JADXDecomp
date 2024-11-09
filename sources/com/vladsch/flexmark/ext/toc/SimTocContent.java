package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/toc/SimTocContent.class */
public class SimTocContent extends Block implements DoNotDecorate {
    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
    }

    public SimTocContent() {
    }

    public SimTocContent(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public SimTocContent(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public SimTocContent(List<BasedSequence> list) {
        super(list);
    }

    public SimTocContent(BlockContent blockContent) {
        super(blockContent);
    }
}
