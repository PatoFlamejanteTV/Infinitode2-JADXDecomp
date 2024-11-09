package com.badlogic.gdx.ai.btree;

import com.badlogic.gdx.ai.btree.annotation.TaskConstraint;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;

@TaskConstraint
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/Task.class */
public abstract class Task<E> implements Pool.Poolable {
    public static TaskCloner TASK_CLONER = null;
    protected Status status = Status.FRESH;
    protected Task<E> control;
    protected BehaviorTree<E> tree;
    protected Task<E> guard;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/Task$Status.class */
    public enum Status {
        FRESH,
        RUNNING,
        FAILED,
        SUCCEEDED,
        CANCELLED
    }

    protected abstract int addChildToTask(Task<E> task);

    public abstract int getChildCount();

    public abstract Task<E> getChild(int i);

    public abstract void run();

    public abstract void childSuccess(Task<E> task);

    public abstract void childFail(Task<E> task);

    public abstract void childRunning(Task<E> task, Task<E> task2);

    protected abstract Task<E> copyTo(Task<E> task);

    public final int addChild(Task<E> task) {
        int addChildToTask = addChildToTask(task);
        if (this.tree != null && this.tree.listeners != null) {
            this.tree.notifyChildAdded(this, addChildToTask);
        }
        return addChildToTask;
    }

    public E getObject() {
        if (this.tree == null) {
            throw new IllegalStateException("This task has never run");
        }
        return this.tree.getObject();
    }

    public Task<E> getGuard() {
        return this.guard;
    }

    public void setGuard(Task<E> task) {
        this.guard = task;
    }

    public final Status getStatus() {
        return this.status;
    }

    public final void setControl(Task<E> task) {
        this.control = task;
        this.tree = task.tree;
    }

    public boolean checkGuard(Task<E> task) {
        if (this.guard == null) {
            return true;
        }
        if (!this.guard.checkGuard(task)) {
            return false;
        }
        this.guard.setControl(task.tree.guardEvaluator);
        this.guard.start();
        this.guard.run();
        switch (this.guard.getStatus()) {
            case SUCCEEDED:
                return true;
            case FAILED:
                return false;
            default:
                throw new IllegalStateException("Illegal guard status '" + this.guard.getStatus() + "'. Guards must either succeed or fail in one step.");
        }
    }

    public void start() {
    }

    public void end() {
    }

    public final void running() {
        Status status = this.status;
        this.status = Status.RUNNING;
        if (this.tree.listeners != null && this.tree.listeners.size > 0) {
            this.tree.notifyStatusUpdated(this, status);
        }
        if (this.control != null) {
            this.control.childRunning(this, this);
        }
    }

    public final void success() {
        Status status = this.status;
        this.status = Status.SUCCEEDED;
        if (this.tree.listeners != null && this.tree.listeners.size > 0) {
            this.tree.notifyStatusUpdated(this, status);
        }
        end();
        if (this.control != null) {
            this.control.childSuccess(this);
        }
    }

    public final void fail() {
        Status status = this.status;
        this.status = Status.FAILED;
        if (this.tree.listeners != null && this.tree.listeners.size > 0) {
            this.tree.notifyStatusUpdated(this, status);
        }
        end();
        if (this.control != null) {
            this.control.childFail(this);
        }
    }

    public final void cancel() {
        cancelRunningChildren(0);
        Status status = this.status;
        this.status = Status.CANCELLED;
        if (this.tree.listeners != null && this.tree.listeners.size > 0) {
            this.tree.notifyStatusUpdated(this, status);
        }
        end();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cancelRunningChildren(int i) {
        int childCount = getChildCount();
        for (int i2 = i; i2 < childCount; i2++) {
            Task<E> child = getChild(i2);
            if (child.status == Status.RUNNING) {
                child.cancel();
            }
        }
    }

    public void resetTask() {
        if (this.status == Status.RUNNING) {
            cancel();
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChild(i).resetTask();
        }
        this.status = Status.FRESH;
        this.tree = null;
        this.control = null;
    }

    public Task<E> cloneTask() {
        if (TASK_CLONER != null) {
            try {
                return TASK_CLONER.cloneTask(this);
            } finally {
                TaskCloneException taskCloneException = new TaskCloneException(e);
            }
        }
        try {
            Task<E> copyTo = copyTo((Task) ClassReflection.newInstance(getClass()));
            copyTo.guard = this.guard == null ? null : this.guard.cloneTask();
            return copyTo;
        } catch (ReflectionException e) {
            throw new TaskCloneException(e);
        }
    }

    @Override // com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.control = null;
        this.guard = null;
        this.status = Status.FRESH;
        this.tree = null;
    }
}
