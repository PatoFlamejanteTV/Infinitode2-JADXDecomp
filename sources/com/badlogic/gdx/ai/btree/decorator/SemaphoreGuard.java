package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.ai.utils.NonBlockingSemaphore;
import com.badlogic.gdx.ai.utils.NonBlockingSemaphoreRepository;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/SemaphoreGuard.class */
public class SemaphoreGuard<E> extends Decorator<E> {

    @TaskAttribute(required = true)
    public String name;
    private transient NonBlockingSemaphore semaphore;
    private boolean semaphoreAcquired;

    public SemaphoreGuard() {
    }

    public SemaphoreGuard(Task<E> task) {
        super(task);
    }

    public SemaphoreGuard(String str) {
        this.name = str;
    }

    public SemaphoreGuard(String str, Task<E> task) {
        super(task);
        this.name = str;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void start() {
        if (this.semaphore == null) {
            this.semaphore = NonBlockingSemaphoreRepository.getSemaphore(this.name);
        }
        this.semaphoreAcquired = this.semaphore.acquire();
        super.start();
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void run() {
        if (this.semaphoreAcquired) {
            super.run();
        } else {
            fail();
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void end() {
        if (this.semaphoreAcquired) {
            if (this.semaphore == null) {
                this.semaphore = NonBlockingSemaphoreRepository.getSemaphore(this.name);
            }
            this.semaphore.release();
            this.semaphoreAcquired = false;
        }
        super.end();
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void resetTask() {
        super.resetTask();
        this.semaphore = null;
        this.semaphoreAcquired = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        SemaphoreGuard semaphoreGuard = (SemaphoreGuard) task;
        semaphoreGuard.name = this.name;
        semaphoreGuard.semaphore = null;
        semaphoreGuard.semaphoreAcquired = false;
        return super.copyTo(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.name = null;
        this.semaphore = null;
        this.semaphoreAcquired = false;
        super.reset();
    }
}
