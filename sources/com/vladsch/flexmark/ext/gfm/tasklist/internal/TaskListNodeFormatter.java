package com.vladsch.flexmark.ext.gfm.tasklist.internal;

import com.vladsch.flexmark.ast.BulletList;
import com.vladsch.flexmark.ast.ListBlock;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.OrderedList;
import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListItem;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListItemPlacement;
import com.vladsch.flexmark.formatter.FormatterUtils;
import com.vladsch.flexmark.formatter.MarkdownWriter;
import com.vladsch.flexmark.formatter.NodeFormatter;
import com.vladsch.flexmark.formatter.NodeFormatterContext;
import com.vladsch.flexmark.formatter.NodeFormattingHandler;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.BlankLine;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/internal/TaskListNodeFormatter.class */
public class TaskListNodeFormatter implements NodeFormatter {
    private final TaskListFormatOptions taskListFormatOptions;
    private final ListOptions listOptions;

    public TaskListNodeFormatter(DataHolder dataHolder) {
        this.taskListFormatOptions = new TaskListFormatOptions(dataHolder);
        this.listOptions = ListOptions.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<NodeFormattingHandler<?>> getNodeFormattingHandlers() {
        return new HashSet(Arrays.asList(new NodeFormattingHandler(TaskListItem.class, this::render), new NodeFormattingHandler(BulletList.class, this::render), new NodeFormattingHandler(OrderedList.class, this::render)));
    }

    @Override // com.vladsch.flexmark.formatter.NodeFormatter
    public Set<Class<?>> getNodeClasses() {
        return null;
    }

    private void render(TaskListItem taskListItem, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        BasedSequence sequence;
        if (nodeFormatterContext.isTransformingText()) {
            FormatterUtils.renderListItem(taskListItem, nodeFormatterContext, markdownWriter, this.listOptions, taskListItem.getMarkerSuffix(), false);
            return;
        }
        BasedSequence markerSuffix = taskListItem.getMarkerSuffix();
        switch (this.taskListFormatOptions.taskListItemCase) {
            case AS_IS:
                break;
            case LOWERCASE:
                markerSuffix = markerSuffix.toLowerCase();
                break;
            case UPPERCASE:
                markerSuffix = markerSuffix.toUpperCase();
                break;
            default:
                throw new IllegalStateException("Missing case for TaskListItemCase " + this.taskListFormatOptions.taskListItemCase.name());
        }
        if (taskListItem.isItemDoneMarker()) {
            switch (this.taskListFormatOptions.taskListItemPlacement) {
                case AS_IS:
                case INCOMPLETE_FIRST:
                case INCOMPLETE_NESTED_FIRST:
                    break;
                case COMPLETE_TO_NON_TASK:
                case COMPLETE_NESTED_TO_NON_TASK:
                    markerSuffix = markerSuffix.getEmptySuffix();
                    break;
                default:
                    throw new IllegalStateException("Missing case for ListItemPlacement " + this.taskListFormatOptions.taskListItemPlacement.name());
            }
        }
        if (markerSuffix.isNotEmpty() && this.taskListFormatOptions.formatPrioritizedTaskItems) {
            taskListItem.setCanChangeMarker(false);
        }
        boolean z = taskListItem.isLoose() && taskListItem.hasChildren() && taskListItem.getFirstChildAnyNot(BlankLine.class) != null;
        ListOptions listOptions = this.listOptions;
        if (markerSuffix.isEmpty()) {
            sequence = markerSuffix;
        } else {
            BasedSequence basedSequence = markerSuffix;
            sequence = markerSuffix.getBuilder().append((CharSequence) markerSuffix).append((CharSequence) SequenceUtils.SPACE).append((CharSequence) basedSequence.baseSubSequence(basedSequence.getEndOffset() + 1, markerSuffix.getEndOffset() + 1)).toSequence();
        }
        FormatterUtils.renderListItem(taskListItem, nodeFormatterContext, markdownWriter, listOptions, sequence, z);
    }

    private void render(BulletList bulletList, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderList(bulletList, nodeFormatterContext, markdownWriter);
    }

    private void render(OrderedList orderedList, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        renderList(orderedList, nodeFormatterContext, markdownWriter);
    }

    public static boolean hasIncompleteDescendants(Node node) {
        Node firstChild = node.getFirstChild();
        while (true) {
            Node node2 = firstChild;
            if (node2 != null) {
                if ((node2 instanceof TaskListItem) && !((TaskListItem) node2).isItemDoneMarker()) {
                    return true;
                }
                if ((node2 instanceof Block) && !(node2 instanceof Paragraph) && hasIncompleteDescendants(node2)) {
                    return true;
                }
                firstChild = node2.getNext();
            } else {
                return false;
            }
        }
    }

    public int taskItemPriority(Node node) {
        if (node instanceof TaskListItem) {
            if (((TaskListItem) node).isOrderedItem()) {
                return this.taskListFormatOptions.formatOrderedTaskItemPriority;
            }
            BasedSequence openingMarker = ((ListItem) node).getOpeningMarker();
            if (openingMarker.length() > 0) {
                Integer num = this.taskListFormatOptions.formatTaskItemPriorities.get(Character.valueOf(openingMarker.charAt(0)));
                if (num != null) {
                    return num.intValue();
                }
                return this.taskListFormatOptions.formatDefaultTaskItemPriority;
            }
            return Integer.MIN_VALUE;
        }
        return Integer.MIN_VALUE;
    }

    public int itemPriority(Node node) {
        int i = Integer.MIN_VALUE;
        if ((node instanceof TaskListItem) && !((TaskListItem) node).isItemDoneMarker()) {
            i = Math.max(Integer.MIN_VALUE, taskItemPriority(node));
        }
        for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
            if ((firstChild instanceof TaskListItem) && !((TaskListItem) firstChild).isItemDoneMarker()) {
                i = Math.max(i, taskItemPriority(firstChild));
            }
            if ((firstChild instanceof Block) && !(firstChild instanceof Paragraph)) {
                i = Math.max(i, itemPriority(firstChild));
            }
        }
        return i;
    }

    public void renderList(ListBlock listBlock, NodeFormatterContext nodeFormatterContext, MarkdownWriter markdownWriter) {
        if (nodeFormatterContext.isTransformingText()) {
            nodeFormatterContext.renderChildren(listBlock);
            return;
        }
        ArrayList arrayList = new ArrayList();
        TaskListItemPlacement taskListItemPlacement = this.taskListFormatOptions.taskListItemPlacement;
        if (taskListItemPlacement != TaskListItemPlacement.AS_IS) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            boolean z = taskListItemPlacement == TaskListItemPlacement.INCOMPLETE_NESTED_FIRST || taskListItemPlacement == TaskListItemPlacement.COMPLETE_NESTED_TO_NON_TASK;
            Node firstChild = listBlock.getFirstChild();
            while (true) {
                Node node = firstChild;
                if (node == null) {
                    break;
                }
                if (node instanceof TaskListItem) {
                    if (!((TaskListItem) node).isItemDoneMarker() || (z && hasIncompleteDescendants(node))) {
                        arrayList2.add((ListItem) node);
                    } else {
                        arrayList3.add((ListItem) node);
                    }
                } else if (node instanceof ListItem) {
                    if (z && hasIncompleteDescendants(node)) {
                        arrayList2.add((ListItem) node);
                    } else {
                        arrayList3.add((ListItem) node);
                    }
                }
                firstChild = node.getNext();
            }
            if (this.taskListFormatOptions.formatPrioritizedTaskItems) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ListItem listItem = (ListItem) it.next();
                    listItem.setPriority(itemPriority(listItem));
                }
                arrayList2.sort((listItem2, listItem3) -> {
                    return Integer.compare(listItem3.getPriority(), listItem2.getPriority());
                });
                arrayList.addAll(arrayList2);
            } else {
                arrayList.addAll(arrayList2);
            }
            arrayList.addAll(arrayList3);
        } else {
            Node firstChild2 = listBlock.getFirstChild();
            while (true) {
                Node node2 = firstChild2;
                if (node2 == null) {
                    break;
                }
                arrayList.add(node2);
                firstChild2 = node2.getNext();
            }
        }
        FormatterUtils.renderList(listBlock, nodeFormatterContext, markdownWriter, arrayList);
    }
}
