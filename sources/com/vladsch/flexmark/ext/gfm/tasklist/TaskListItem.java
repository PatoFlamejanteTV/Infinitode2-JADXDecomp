package com.vladsch.flexmark.ext.gfm.tasklist;

import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.OrderedListItem;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.List;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/TaskListItem.class */
public class TaskListItem extends ListItem {
    protected boolean isOrderedItem;
    protected boolean canChangeMarker;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !TaskListItem.class.desiredAssertionStatus();
    }

    @Override // com.vladsch.flexmark.ast.ListItem, com.vladsch.flexmark.util.ast.Node
    public void getAstExtra(StringBuilder sb) {
        super.getAstExtra(sb);
        if (this.isOrderedItem) {
            sb.append(" isOrderedItem");
        }
        sb.append(isItemDoneMarker() ? " isDone" : " isNotDone");
    }

    @Override // com.vladsch.flexmark.ast.ListItem, com.vladsch.flexmark.ast.ParagraphItemContainer
    public boolean isParagraphWrappingDisabled(Paragraph paragraph, ListOptions listOptions, DataHolder dataHolder) {
        Node node;
        if (!$assertionsDisabled && paragraph.getParent() != this) {
            throw new AssertionError();
        }
        Node firstChild = getFirstChild();
        while (true) {
            node = firstChild;
            if (node == null || (node instanceof Paragraph)) {
                break;
            }
            firstChild = node.getNext();
        }
        return node == paragraph;
    }

    public TaskListItem() {
        this.isOrderedItem = false;
        this.canChangeMarker = true;
    }

    public TaskListItem(BasedSequence basedSequence) {
        super(basedSequence);
        this.isOrderedItem = false;
        this.canChangeMarker = true;
    }

    public TaskListItem(BasedSequence basedSequence, List<BasedSequence> list) {
        super(basedSequence, list);
        this.isOrderedItem = false;
        this.canChangeMarker = true;
    }

    public TaskListItem(BlockContent blockContent) {
        super(blockContent);
        this.isOrderedItem = false;
        this.canChangeMarker = true;
    }

    public TaskListItem(ListItem listItem) {
        super(listItem);
        this.isOrderedItem = false;
        this.canChangeMarker = true;
        this.isOrderedItem = listItem instanceof OrderedListItem;
    }

    @Override // com.vladsch.flexmark.ast.ListItem
    public void setOpeningMarker(BasedSequence basedSequence) {
        throw new IllegalStateException();
    }

    public boolean isItemDoneMarker() {
        return !this.markerSuffix.matches("[ ]");
    }

    @Override // com.vladsch.flexmark.ast.ListItem
    public boolean isOrderedItem() {
        return this.isOrderedItem;
    }

    public void setOrderedItem(boolean z) {
        this.isOrderedItem = z;
    }

    @Override // com.vladsch.flexmark.ast.ListItem
    public boolean canChangeMarker() {
        return this.canChangeMarker;
    }

    public void setCanChangeMarker(boolean z) {
        this.canChangeMarker = z;
    }
}
