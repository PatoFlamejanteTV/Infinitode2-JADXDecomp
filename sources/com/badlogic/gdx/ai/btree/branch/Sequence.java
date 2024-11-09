package com.badlogic.gdx.ai.btree.branch;

import com.badlogic.gdx.ai.btree.SingleRunningChildBranch;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/branch/Sequence.class */
public class Sequence<E> extends SingleRunningChildBranch<E> {
    public Sequence() {
    }

    public Sequence(Array<Task<E>> array) {
        super(array);
    }

    public Sequence(Task<E>... taskArr) {
        super(new Array(taskArr));
    }

    @Override // com.badlogic.gdx.ai.btree.SingleRunningChildBranch, com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        super.childSuccess(task);
        int i = this.currentChildIndex + 1;
        this.currentChildIndex = i;
        if (i < this.children.size) {
            run();
        } else {
            success();
        }
    }

    @Override // com.badlogic.gdx.ai.btree.SingleRunningChildBranch, com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        super.childFail(task);
        fail();
    }
}
