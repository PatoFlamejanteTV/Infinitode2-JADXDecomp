package com.vladsch.flexmark.ext.gfm.tasklist.internal;

import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListItemCase;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListItemPlacement;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSetter;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/internal/TaskListFormatOptions.class */
public class TaskListFormatOptions implements MutableDataSetter {
    public final TaskListItemCase taskListItemCase;
    public final TaskListItemPlacement taskListItemPlacement;
    public final int formatOrderedTaskItemPriority;
    public final int formatDefaultTaskItemPriority;
    public final boolean formatPrioritizedTaskItems;
    public final Map<Character, Integer> formatTaskItemPriorities;

    public TaskListFormatOptions() {
        this(null);
    }

    public TaskListFormatOptions(DataHolder dataHolder) {
        this.taskListItemCase = TaskListExtension.FORMAT_LIST_ITEM_CASE.get(dataHolder);
        this.taskListItemPlacement = TaskListExtension.FORMAT_LIST_ITEM_PLACEMENT.get(dataHolder);
        this.formatOrderedTaskItemPriority = TaskListExtension.FORMAT_ORDERED_TASK_ITEM_PRIORITY.get(dataHolder).intValue();
        this.formatDefaultTaskItemPriority = TaskListExtension.FORMAT_DEFAULT_TASK_ITEM_PRIORITY.get(dataHolder).intValue();
        this.formatTaskItemPriorities = TaskListExtension.FORMAT_TASK_ITEM_PRIORITIES.get(dataHolder);
        this.formatPrioritizedTaskItems = TaskListExtension.FORMAT_PRIORITIZED_TASK_ITEMS.get(dataHolder).booleanValue();
    }

    @Override // com.vladsch.flexmark.util.data.MutableDataSetter
    public MutableDataHolder setIn(MutableDataHolder mutableDataHolder) {
        mutableDataHolder.set((DataKey<DataKey<TaskListItemCase>>) TaskListExtension.FORMAT_LIST_ITEM_CASE, (DataKey<TaskListItemCase>) this.taskListItemCase);
        mutableDataHolder.set((DataKey<DataKey<TaskListItemPlacement>>) TaskListExtension.FORMAT_LIST_ITEM_PLACEMENT, (DataKey<TaskListItemPlacement>) this.taskListItemPlacement);
        mutableDataHolder.set((DataKey<DataKey<Integer>>) TaskListExtension.FORMAT_ORDERED_TASK_ITEM_PRIORITY, (DataKey<Integer>) Integer.valueOf(this.formatOrderedTaskItemPriority));
        mutableDataHolder.set((DataKey<DataKey<Map<Character, Integer>>>) TaskListExtension.FORMAT_TASK_ITEM_PRIORITIES, (DataKey<Map<Character, Integer>>) this.formatTaskItemPriorities);
        mutableDataHolder.set((DataKey<DataKey<Boolean>>) TaskListExtension.FORMAT_PRIORITIZED_TASK_ITEMS, (DataKey<Boolean>) Boolean.valueOf(this.formatPrioritizedTaskItems));
        return mutableDataHolder;
    }
}
