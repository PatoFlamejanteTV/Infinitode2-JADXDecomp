package com.vladsch.flexmark.util.ast;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/ast/BlockTracker.class */
public interface BlockTracker {
    void blockAdded(Block block);

    void blockAddedWithChildren(Block block);

    void blockAddedWithDescendants(Block block);

    void blockRemoved(Block block);

    void blockRemovedWithChildren(Block block);

    void blockRemovedWithDescendants(Block block);
}
