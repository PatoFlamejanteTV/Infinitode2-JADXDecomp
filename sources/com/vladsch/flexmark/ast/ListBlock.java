package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlankLineContainer;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/ListBlock.class */
public abstract class ListBlock extends Block implements BlankLineContainer {
    private boolean tight;

    public ListBlock() {
    }

    public ListBlock(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public ListBlock(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public ListBlock(BlockContent blockContent) {
        super(blockContent);
    }

    public boolean isTight() {
        return this.tight;
    }

    public boolean isLoose() {
        return !this.tight;
    }

    public void setTight(boolean z) {
        this.tight = z;
    }

    public void setLoose(boolean z) {
        this.tight = !z;
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public Node getLastBlankLineChild() {
        return getLastChild();
    }

    @Override // com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        super.getAstExtra(sb);
        if (isTight()) {
            sb.append(" isTight");
        } else {
            sb.append(" isLoose");
        }
    }
}
