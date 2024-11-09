package com.badlogic.gdx.ai.btree.branch;

import com.badlogic.gdx.ai.btree.BranchTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/branch/DynamicGuardSelector.class */
public class DynamicGuardSelector<E> extends BranchTask<E> {
    protected Task<E> runningChild;

    public DynamicGuardSelector() {
    }

    public DynamicGuardSelector(Task<E>... taskArr) {
        super(new Array(taskArr));
    }

    public DynamicGuardSelector(Array<Task<E>> array) {
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
        success();
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        this.runningChild = null;
        fail();
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void run() {
        Task<E> task = null;
        int i = 0;
        int i2 = this.children.size;
        while (true) {
            if (i >= i2) {
                break;
            }
            Task<E> task2 = this.children.get(i);
            if (!task2.checkGuard(this)) {
                i++;
            } else {
                task = task2;
                break;
            }
        }
        if (this.runningChild != null && this.runningChild != task) {
            this.runningChild.cancel();
            this.runningChild = null;
        }
        if (task == null) {
            fail();
            return;
        }
        if (this.runningChild == null) {
            this.runningChild = task;
            this.runningChild.setControl(this);
            this.runningChild.start();
        }
        this.runningChild.run();
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void resetTask() {
        super.resetTask();
        this.runningChild = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.BranchTask, com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        ((DynamicGuardSelector) task).runningChild = null;
        return super.copyTo(task);
    }

    @Override // com.badlogic.gdx.ai.btree.BranchTask, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.runningChild = null;
        super.reset();
    }
}
