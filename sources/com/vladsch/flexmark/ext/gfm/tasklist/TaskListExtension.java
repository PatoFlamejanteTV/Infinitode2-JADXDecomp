package com.vladsch.flexmark.ext.gfm.tasklist;

import com.vladsch.flexmark.ext.gfm.tasklist.internal.TaskListItemBlockPreProcessor;
import com.vladsch.flexmark.ext.gfm.tasklist.internal.TaskListNodeFormatter;
import com.vladsch.flexmark.ext.gfm.tasklist.internal.TaskListNodeRenderer;
import com.vladsch.flexmark.formatter.Formatter;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/TaskListExtension.class */
public class TaskListExtension implements Formatter.FormatterExtension, HtmlRenderer.HtmlRendererExtension, Parser.ParserExtension {
    public static final Map<Character, Integer> DEFAULT_PRIORITIES;
    public static final DataKey<String> ITEM_DONE_MARKER;
    public static final DataKey<String> ITEM_NOT_DONE_MARKER;
    public static final DataKey<String> TIGHT_ITEM_CLASS;
    public static final DataKey<String> LOOSE_ITEM_CLASS;
    public static final DataKey<String> PARAGRAPH_CLASS;
    public static final DataKey<String> ITEM_DONE_CLASS;
    public static final DataKey<String> ITEM_NOT_DONE_CLASS;
    public static final DataKey<TaskListItemCase> FORMAT_LIST_ITEM_CASE;
    public static final DataKey<TaskListItemPlacement> FORMAT_LIST_ITEM_PLACEMENT;
    public static final DataKey<Integer> FORMAT_ORDERED_TASK_ITEM_PRIORITY;
    public static final DataKey<Integer> FORMAT_DEFAULT_TASK_ITEM_PRIORITY;
    public static final DataKey<Boolean> FORMAT_PRIORITIZED_TASK_ITEMS;
    public static final DataKey<Map<Character, Integer>> FORMAT_TASK_ITEM_PRIORITIES;

    static {
        HashMap hashMap = new HashMap();
        DEFAULT_PRIORITIES = hashMap;
        hashMap.put('+', 1);
        DEFAULT_PRIORITIES.put('*', 0);
        DEFAULT_PRIORITIES.put('-', -1);
        ITEM_DONE_MARKER = new DataKey<>("ITEM_DONE_MARKER", "<input type=\"checkbox\" class=\"task-list-item-checkbox\" checked=\"checked\" disabled=\"disabled\" readonly=\"readonly\" />&nbsp;");
        ITEM_NOT_DONE_MARKER = new DataKey<>("ITEM_NOT_DONE_MARKER", "<input type=\"checkbox\" class=\"task-list-item-checkbox\" disabled=\"disabled\" readonly=\"readonly\" />&nbsp;");
        TIGHT_ITEM_CLASS = new DataKey<>("TIGHT_ITEM_CLASS", "task-list-item");
        LOOSE_ITEM_CLASS = new DataKey<>("LOOSE_ITEM_CLASS", (DataKey) TIGHT_ITEM_CLASS);
        PARAGRAPH_CLASS = new DataKey<>("PARAGRAPH_CLASS", "");
        ITEM_DONE_CLASS = new DataKey<>("ITEM_DONE_CLASS", "");
        ITEM_NOT_DONE_CLASS = new DataKey<>("ITEM_NOT_DONE_CLASS", "");
        FORMAT_LIST_ITEM_CASE = new DataKey<>("FORMAT_LIST_ITEM_CASE", TaskListItemCase.AS_IS);
        FORMAT_LIST_ITEM_PLACEMENT = new DataKey<>("FORMAT_LIST_ITEM_PLACEMENT", TaskListItemPlacement.AS_IS);
        FORMAT_ORDERED_TASK_ITEM_PRIORITY = new DataKey<>("FORMAT_ORDERED_TASK_ITEM_PRIORITY", 0);
        FORMAT_DEFAULT_TASK_ITEM_PRIORITY = new DataKey<>("FORMAT_DEFAULT_TASK_ITEM_PRIORITY", 0);
        FORMAT_PRIORITIZED_TASK_ITEMS = new DataKey<>("FORMAT_PRIORITIZED_TASK_ITEMS", Boolean.FALSE);
        FORMAT_TASK_ITEM_PRIORITIES = new DataKey<>("FORMAT_TASK_ITEM_PRIORITIES", DEFAULT_PRIORITIES);
    }

    private TaskListExtension() {
    }

    public static TaskListExtension create() {
        return new TaskListExtension();
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension
    public void extend(Formatter.Builder builder) {
        builder.nodeFormatterFactory(TaskListNodeFormatter::new);
    }

    @Override // com.vladsch.flexmark.formatter.Formatter.FormatterExtension, com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void rendererOptions(MutableDataHolder mutableDataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void parserOptions(MutableDataHolder mutableDataHolder) {
        ListOptions.addItemMarkerSuffixes(mutableDataHolder, "[ ]", "[x]", "[X]");
    }

    @Override // com.vladsch.flexmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder builder) {
        builder.blockPreProcessorFactory(new TaskListItemBlockPreProcessor.Factory());
    }

    @Override // com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension
    public void extend(HtmlRenderer.Builder builder, String str) {
        if (builder.isRendererType("HTML")) {
            builder.nodeRendererFactory(new TaskListNodeRenderer.Factory());
        } else {
            builder.isRendererType("JIRA");
        }
    }
}
