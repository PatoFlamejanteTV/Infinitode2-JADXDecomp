package com.badlogic.gdx.ai.btree.utils;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/utils/BehaviorTreeLibraryManager.class */
public final class BehaviorTreeLibraryManager {
    private static BehaviorTreeLibraryManager instance = new BehaviorTreeLibraryManager();
    protected BehaviorTreeLibrary library;

    private BehaviorTreeLibraryManager() {
        setLibrary(new BehaviorTreeLibrary());
    }

    public static BehaviorTreeLibraryManager getInstance() {
        return instance;
    }

    public final BehaviorTreeLibrary getLibrary() {
        return this.library;
    }

    public final void setLibrary(BehaviorTreeLibrary behaviorTreeLibrary) {
        this.library = behaviorTreeLibrary;
    }

    public final <T> Task<T> createRootTask(String str) {
        return this.library.createRootTask(str);
    }

    public final <T> BehaviorTree<T> createBehaviorTree(String str) {
        return this.library.createBehaviorTree(str);
    }

    public final <T> BehaviorTree<T> createBehaviorTree(String str, T t) {
        return this.library.createBehaviorTree(str, t);
    }

    public final void disposeBehaviorTree(String str, BehaviorTree<?> behaviorTree) {
        this.library.disposeBehaviorTree(str, behaviorTree);
    }
}
