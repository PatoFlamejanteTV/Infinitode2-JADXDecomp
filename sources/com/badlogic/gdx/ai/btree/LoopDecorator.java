package com.badlogic.gdx.ai.btree;

import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/LoopDecorator.class */
public abstract class LoopDecorator<E> extends Decorator<E> {
    protected boolean loop;

    public LoopDecorator() {
    }

    public LoopDecorator(Task<E> task) {
        super(task);
    }

    public boolean condition() {
        return this.loop;
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void run() {
        this.loop = true;
        while (condition()) {
            if (this.child.status == Task.Status.RUNNING) {
                this.child.run();
            } else {
                this.child.setControl(this);
                this.child.start();
                if (this.child.checkGuard(this)) {
                    this.child.run();
                } else {
                    this.child.fail();
                }
            }
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childRunning(Task<E> task, Task<E> task2) {
        super.childRunning(task, task2);
        this.loop = false;
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.loop = false;
        super.reset();
    }
}
