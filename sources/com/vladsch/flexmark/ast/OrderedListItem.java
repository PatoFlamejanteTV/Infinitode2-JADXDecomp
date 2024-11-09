package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ast/OrderedListItem.class */
public class OrderedListItem extends ListItem {
    public OrderedListItem() {
    }

    public OrderedListItem(BasedSequence basedSequence) {
        super(basedSequence);
    }

    public OrderedListItem(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
    }

    public OrderedListItem(BlockContent blockContent) {
        super(blockContent);
    }

    @Override // com.vladsch.flexmark.ast.ListItem
    public boolean isOrderedItem() {
        return true;
    }
}
