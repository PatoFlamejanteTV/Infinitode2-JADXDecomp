package com.vladsch.flexmark.ext.gfm.tasklist.internal;

import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListItem;
import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.html.renderer.CoreNodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.html.Attribute;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/internal/TaskListNodeRenderer.class */
public class TaskListNodeRenderer implements NodeRenderer {
    public static final AttributablePart TASK_ITEM_PARAGRAPH = new AttributablePart("TASK_ITEM_PARAGRAPH");
    final String doneMarker;
    final String notDoneMarker;
    private final String tightItemClass;
    private final String looseItemClass;
    private final String itemDoneClass;
    private final String itemNotDoneClass;
    final String paragraphClass;
    private final ListOptions listOptions;

    public TaskListNodeRenderer(DataHolder dataHolder) {
        this.doneMarker = TaskListExtension.ITEM_DONE_MARKER.get(dataHolder);
        this.notDoneMarker = TaskListExtension.ITEM_NOT_DONE_MARKER.get(dataHolder);
        this.tightItemClass = TaskListExtension.TIGHT_ITEM_CLASS.get(dataHolder);
        this.looseItemClass = TaskListExtension.LOOSE_ITEM_CLASS.get(dataHolder);
        this.itemDoneClass = TaskListExtension.ITEM_DONE_CLASS.get(dataHolder);
        this.itemNotDoneClass = TaskListExtension.ITEM_NOT_DONE_CLASS.get(dataHolder);
        this.paragraphClass = TaskListExtension.PARAGRAPH_CLASS.get(dataHolder);
        this.listOptions = ListOptions.get(dataHolder);
    }

    @Override // com.vladsch.flexmark.html.renderer.NodeRenderer
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NodeRenderingHandler(TaskListItem.class, this::render));
        return hashSet;
    }

    void render(TaskListItem taskListItem, NodeRendererContext nodeRendererContext, HtmlWriter htmlWriter) {
        BasedSequence chars = (nodeRendererContext.getHtmlOptions().sourcePositionParagraphLines || taskListItem.getFirstChild() == null) ? taskListItem.getChars() : taskListItem.getFirstChild().getChars();
        String str = taskListItem.isItemDoneMarker() ? this.itemDoneClass : this.itemNotDoneClass;
        if (this.listOptions.isTightListItem(taskListItem)) {
            if (!this.tightItemClass.isEmpty()) {
                htmlWriter.attr(Attribute.CLASS_ATTR, this.tightItemClass);
            }
            if (!str.isEmpty() && !str.equals(this.tightItemClass)) {
                htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) str);
            }
            htmlWriter.srcPos(chars.getStartOffset(), chars.getEndOffset()).withAttr(CoreNodeRenderer.TIGHT_LIST_ITEM).withCondIndent().tagLine((CharSequence) FlexmarkHtmlConverter.LI_NODE, () -> {
                htmlWriter.raw((CharSequence) (taskListItem.isItemDoneMarker() ? this.doneMarker : this.notDoneMarker));
                nodeRendererContext.renderChildren(taskListItem);
            });
            return;
        }
        if (!this.looseItemClass.isEmpty()) {
            htmlWriter.attr(Attribute.CLASS_ATTR, this.looseItemClass);
        }
        if (!str.isEmpty() && !str.equals(this.looseItemClass)) {
            htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) str);
        }
        htmlWriter.withAttr(CoreNodeRenderer.LOOSE_LIST_ITEM).tagIndent(FlexmarkHtmlConverter.LI_NODE, () -> {
            if (!this.paragraphClass.isEmpty()) {
                htmlWriter.attr(Attribute.CLASS_ATTR, (CharSequence) this.paragraphClass);
            }
            htmlWriter.srcPos(chars.getStartOffset(), chars.getEndOffset()).withAttr(TASK_ITEM_PARAGRAPH).tagLine(FlexmarkHtmlConverter.P_NODE, () -> {
                htmlWriter.raw((CharSequence) (taskListItem.isItemDoneMarker() ? this.doneMarker : this.notDoneMarker));
                nodeRendererContext.renderChildren(taskListItem);
            });
        });
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/internal/TaskListNodeRenderer$Factory.class */
    public static class Factory implements NodeRendererFactory {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.vladsch.flexmark.html.renderer.NodeRendererFactory, java.util.function.Function
        public NodeRenderer apply(DataHolder dataHolder) {
            return new TaskListNodeRenderer(dataHolder);
        }
    }
}
