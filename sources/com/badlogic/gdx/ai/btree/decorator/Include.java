package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.TaskCloneException;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.ai.btree.annotation.TaskConstraint;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeLibraryManager;

@TaskConstraint(minChildren = 0, maxChildren = 0)
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/Include.class */
public class Include<E> extends Decorator<E> {

    @TaskAttribute(required = true)
    public String subtree;

    @TaskAttribute
    public boolean lazy;

    public Include() {
    }

    public Include(String str) {
        this.subtree = str;
    }

    public Include(String str, boolean z) {
        this.subtree = str;
        this.lazy = z;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void start() {
        if (!this.lazy) {
            throw new UnsupportedOperationException("A non-lazy " + Include.class.getSimpleName() + " isn't meant to be run!");
        }
        if (this.child == null) {
            addChild(createSubtreeRootTask());
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public Task<E> cloneTask() {
        return this.lazy ? super.cloneTask() : createSubtreeRootTask();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        if (!this.lazy) {
            throw new TaskCloneException("A non-lazy " + getClass().getSimpleName() + " should never be copied.");
        }
        Include include = (Include) task;
        include.subtree = this.subtree;
        include.lazy = this.lazy;
        return task;
    }

    private Task<E> createSubtreeRootTask() {
        return BehaviorTreeLibraryManager.getInstance().createRootTask(this.subtree);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.lazy = false;
        this.subtree = null;
        super.reset();
    }
}
