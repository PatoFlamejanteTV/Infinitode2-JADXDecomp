package com.badlogic.gdx.ai.btree.leaf;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/leaf/Failure.class */
public class Failure<E> extends LeafTask<E> {
    @Override // com.badlogic.gdx.ai.btree.LeafTask
    public Task.Status execute() {
        return Task.Status.FAILED;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    protected Task<E> copyTo(Task<E> task) {
        return task;
    }
}
