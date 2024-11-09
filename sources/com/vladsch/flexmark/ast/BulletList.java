package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/BulletList.class */
public class BulletList extends ListBlock {
    private char openingMarker;

    @Override // com.vladsch.flexmark.util.ast.Node
    public BasedSequence[] getSegments() {
        return EMPTY_SEGMENTS;
    }

    public BulletList() {
    }

    public BulletList(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public BulletList(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public BulletList(BlockContent blockContent) {
        super(blockContent);
    }

    public char getOpeningMarker() {
        return this.openingMarker;
    }

    public void setOpeningMarker(char c) {
        this.openingMarker = c;
    }
}
