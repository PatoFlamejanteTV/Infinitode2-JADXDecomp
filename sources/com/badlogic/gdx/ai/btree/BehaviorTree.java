package com.badlogic.gdx.ai.btree;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.utils.Array;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/BehaviorTree.class */
public class BehaviorTree<E> extends Task<E> {
    private Task<E> rootTask;
    private E object;
    GuardEvaluator<E> guardEvaluator;
    public Array<Listener<E>> listeners;

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/BehaviorTree$Listener.class */
    public interface Listener<E> {
        void statusUpdated(Task<E> task, Task.Status status);

        void childAdded(Task<E> task, int i);
    }

    public BehaviorTree() {
        this(null, null);
    }

    public BehaviorTree(Task<E> task) {
        this(task, null);
    }

    public BehaviorTree(Task<E> task, E e) {
        this.rootTask = task;
        this.object = e;
        this.tree = this;
        this.guardEvaluator = new GuardEvaluator<>(this);
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public E getObject() {
        return this.object;
    }

    public void setObject(E e) {
        this.object = e;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    protected int addChildToTask(Task<E> task) {
        if (this.rootTask != null) {
            throw new IllegalStateException("A behavior tree cannot have more than one root task");
        }
        this.rootTask = task;
        return 0;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public int getChildCount() {
        return this.rootTask == null ? 0 : 1;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public Task<E> getChild(int i) {
        if (i != 0 || this.rootTask == null) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + getChildCount());
        }
        return this.rootTask;
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

    public void step() {
        if (this.rootTask.status == Task.Status.RUNNING) {
            this.rootTask.run();
            return;
        }
        this.rootTask.setControl(this);
        this.rootTask.start();
        if (this.rootTask.checkGuard(this)) {
            this.rootTask.run();
        } else {
            this.rootTask.fail();
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void run() {
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void resetTask() {
        super.resetTask();
        this.tree = this;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    protected Task<E> copyTo(Task<E> task) {
        ((BehaviorTree) task).rootTask = this.rootTask.cloneTask();
        return task;
    }

    public void addListener(Listener<E> listener) {
        if (this.listeners == null) {
            this.listeners = new Array<>();
        }
        this.listeners.add(listener);
    }

    public void removeListener(Listener<E> listener) {
        if (this.listeners != null) {
            this.listeners.removeValue(listener, true);
        }
    }

    public void removeListeners() {
        if (this.listeners != null) {
            this.listeners.clear();
        }
    }

    public void notifyStatusUpdated(Task<E> task, Task.Status status) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).statusUpdated(task, status);
        }
    }

    public void notifyChildAdded(Task<E> task, int i) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).childAdded(task, i);
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        removeListeners();
        this.rootTask = null;
        this.object = null;
        this.listeners = null;
        super.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/BehaviorTree$GuardEvaluator.class */
    public static final class GuardEvaluator<E> extends Task<E> {
        public GuardEvaluator() {
        }

        public GuardEvaluator(BehaviorTree<E> behaviorTree) {
            this.tree = behaviorTree;
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        protected final int addChildToTask(Task<E> task) {
            return 0;
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        public final int getChildCount() {
            return 0;
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        public final Task<E> getChild(int i) {
            return null;
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        public final void run() {
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        public final void childSuccess(Task<E> task) {
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        public final void childFail(Task<E> task) {
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        public final void childRunning(Task<E> task, Task<E> task2) {
        }

        @Override // com.badlogic.gdx.ai.btree.Task
        protected final Task<E> copyTo(Task<E> task) {
            return null;
        }
    }
}
