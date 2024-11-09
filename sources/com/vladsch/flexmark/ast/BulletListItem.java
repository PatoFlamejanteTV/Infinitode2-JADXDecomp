package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/BulletListItem.class */
public class BulletListItem extends ListItem {
    public BulletListItem() {
    }

    public BulletListItem(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public BulletListItem(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public BulletListItem(BlockContent blockContent) {
        super(blockContent);
    }
}
