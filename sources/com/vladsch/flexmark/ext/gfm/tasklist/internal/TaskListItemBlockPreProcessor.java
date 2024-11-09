package com.vladsch.flexmark.ext.gfm.tasklist.internal;

import com.vladsch.flexmark.ast.BulletListItem;
import com.vladsch.flexmark.ast.ListItem;
import com.vladsch.flexmark.ast.OrderedListItem;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListItem;
import com.vladsch.flexmark.parser.block.BlockPreProcessor;
import com.vladsch.flexmark.parser.block.BlockPreProcessorFactory;
import com.vladsch.flexmark.parser.block.ParserState;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.util.HashSet;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/internal/TaskListItemBlockPreProcessor.class */
public class TaskListItemBlockPreProcessor implements BlockPreProcessor {
    public TaskListItemBlockPreProcessor(DataHolder dataHolder) {
    }

    @Override // com.vladsch.flexmark.parser.block.BlockPreProcessor
    public void preProcess(ParserState parserState, Block block) {
        if ((block instanceof BulletListItem) || (block instanceof OrderedListItem)) {
            ListItem listItem = (ListItem) block;
            BasedSequence markerSuffix = listItem.getMarkerSuffix();
            if (markerSuffix.matches("[ ]") || markerSuffix.matches("[x]") || markerSuffix.matches("[X]")) {
                TaskListItem taskListItem = new TaskListItem(listItem);
                taskListItem.setTight(listItem.isOwnTight());
                listItem.insertBefore(taskListItem);
                listItem.unlink();
                parserState.blockAdded(taskListItem);
                parserState.blockRemoved(listItem);
            }
        }
    }

    /* loaded from: infinitode-2.jar:com/vladsch/flexmark/ext/gfm/tasklist/internal/TaskListItemBlockPreProcessor$Factory.class */
    public static class Factory implements BlockPreProcessorFactory {
        @Override // com.vladsch.flexmark.parser.block.BlockPreProcessorFactory
        public Set<Class<? extends Block>> getBlockTypes() {
            HashSet hashSet = new HashSet();
            hashSet.add(BulletListItem.class);
            hashSet.add(OrderedListItem.class);
            return hashSet;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getAfterDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public Set<Class<?>> getBeforeDependents() {
            return null;
        }

        @Override // com.vladsch.flexmark.util.dependency.Dependent
        public boolean affectsGlobalScope() {
            return true;
        }

        @Override // com.vladsch.flexmark.parser.block.BlockPreProcessorFactory, java.util.function.Function
        public BlockPreProcessor apply(ParserState parserState) {
            return new TaskListItemBlockPreProcessor(parserState.getProperties());
        }
    }
}
