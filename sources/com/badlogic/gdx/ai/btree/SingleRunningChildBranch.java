package com.badlogic.gdx.ai.btree;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/SingleRunningChildBranch.class */
public abstract class SingleRunningChildBranch<E> extends BranchTask<E> {
    protected Task<E> runningChild;
    protected int currentChildIndex;
    protected Task<E>[] randomChildren;

    public SingleRunningChildBranch() {
    }

    public SingleRunningChildBranch(Array<Task<E>> array) {
        super(array);
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childRunning(Task<E> task, Task<E> task2) {
        this.runningChild = task;
        running();
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        this.runningChild = null;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        this.runningChild = null;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void run() {
        if (this.runningChild != null) {
            this.runningChild.run();
            return;
        }
        if (this.currentChildIndex < this.children.size) {
            if (this.randomChildren != null) {
                int i = this.children.size - 1;
                if (this.currentChildIndex < i) {
                    int random = MathUtils.random(this.currentChildIndex, i);
                    Task<E> task = this.randomChildren[this.currentChildIndex];
                    this.randomChildren[this.currentChildIndex] = this.randomChildren[random];
                    this.randomChildren[random] = task;
                }
                this.runningChild = this.randomChildren[this.currentChildIndex];
            } else {
                this.runningChild = this.children.get(this.currentChildIndex);
            }
            this.runningChild.setControl(this);
            this.runningChild.start();
            if (!this.runningChild.checkGuard(this)) {
                this.runningChild.fail();
            } else {
                run();
            }
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void start() {
        this.currentChildIndex = 0;
        this.runningChild = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.Task
    public void cancelRunningChildren(int i) {
        super.cancelRunningChildren(i);
        this.runningChild = null;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void resetTask() {
        super.resetTask();
        this.currentChildIndex = 0;
        this.runningChild = null;
        this.randomChildren = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.BranchTask, com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        ((SingleRunningChildBranch) task).randomChildren = null;
        return super.copyTo(task);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Task<E>[] createRandomChildren() {
        Task<E>[] taskArr = new Task[this.children.size];
        System.arraycopy(this.children.items, 0, taskArr, 0, this.children.size);
        return taskArr;
    }

    @Override // com.badlogic.gdx.ai.btree.BranchTask, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.currentChildIndex = 0;
        this.runningChild = null;
        this.randomChildren = null;
        super.reset();
    }
}
