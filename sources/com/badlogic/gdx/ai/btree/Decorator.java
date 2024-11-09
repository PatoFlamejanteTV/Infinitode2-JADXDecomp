package com.badlogic.gdx.ai.btree;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskConstraint;

@TaskConstraint(minChildren = 1, maxChildren = 1)
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/Decorator.class */
public abstract class Decorator<E> extends Task<E> {
    protected Task<E> child;

    public Decorator() {
    }

    public Decorator(Task<E> task) {
        this.child = task;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    protected int addChildToTask(Task<E> task) {
        if (this.child != null) {
            throw new IllegalStateException("A decorator task cannot have more than one child");
        }
        this.child = task;
        return 0;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public int getChildCount() {
        return this.child == null ? 0 : 1;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public Task<E> getChild(int i) {
        if (i != 0 || this.child == null) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + getChildCount());
        }
        return this.child;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void run() {
        if (this.child.status == Task.Status.RUNNING) {
            this.child.run();
            return;
        }
        this.child.setControl(this);
        this.child.start();
        if (this.child.checkGuard(this)) {
            this.child.run();
        } else {
            this.child.fail();
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childRunning(Task<E> task, Task<E> task2) {
        running();
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        fail();
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        success();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        if (this.child != null) {
            ((Decorator) task).child = this.child.cloneTask();
        }
        return task;
    }

    @Override // com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.child = null;
        super.reset();
    }
}
