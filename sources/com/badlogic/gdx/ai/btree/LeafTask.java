package com.badlogic.gdx.ai.btree;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskConstraint;

@TaskConstraint(minChildren = 0, maxChildren = 0)
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/LeafTask.class */
public abstract class LeafTask<E> extends Task<E> {
    public abstract Task.Status execute();

    @Override // com.badlogic.gdx.ai.btree.Task
    public final void run() {
        Task.Status execute = execute();
        if (execute == null) {
            throw new IllegalStateException("Invalid status 'null' returned by the execute method");
        }
        switch (execute) {
            case SUCCEEDED:
                success();
                return;
            case FAILED:
                fail();
                return;
            case RUNNING:
                running();
                return;
            default:
                throw new IllegalStateException("Invalid status '" + execute.name() + "' returned by the execute method");
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    protected int addChildToTask(Task<E> task) {
        throw new IllegalStateException("A leaf task cannot have any children");
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public int getChildCount() {
        return 0;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public Task<E> getChild(int i) {
        throw new IndexOutOfBoundsException("A leaf task can not have any child");
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public final void childRunning(Task<E> task, Task<E> task2) {
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public final void childFail(Task<E> task) {
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public final void childSuccess(Task<E> task) {
    }
}
