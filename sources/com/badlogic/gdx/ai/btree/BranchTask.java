package com.badlogic.gdx.ai.btree;

import com.badlogic.gdx.ai.btree.annotation.TaskConstraint;
import com.badlogic.gdx.utils.Array;

@TaskConstraint(minChildren = 1)
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/BranchTask.class */
public abstract class BranchTask<E> extends Task<E> {
    protected Array<Task<E>> children;

    public BranchTask() {
        this(new Array());
    }

    public BranchTask(Array<Task<E>> array) {
        this.children = array;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    protected int addChildToTask(Task<E> task) {
        this.children.add(task);
        return this.children.size - 1;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public int getChildCount() {
        return this.children.size;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public Task<E> getChild(int i) {
        return this.children.get(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        BranchTask branchTask = (BranchTask) task;
        if (this.children != null) {
            for (int i = 0; i < this.children.size; i++) {
                branchTask.children.add(this.children.get(i).cloneTask());
            }
        }
        return task;
    }

    @Override // com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.children.clear();
        super.reset();
    }
}
