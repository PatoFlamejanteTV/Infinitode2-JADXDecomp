package com.badlogic.gdx.ai.btree.branch;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/branch/RandomSequence.class */
public class RandomSequence<E> extends Sequence<E> {
    public RandomSequence() {
    }

    public RandomSequence(Array<Task<E>> array) {
        super(array);
    }

    public RandomSequence(Task<E>... taskArr) {
        super(new Array(taskArr));
    }

    @Override // com.badlogic.gdx.ai.btree.SingleRunningChildBranch, com.badlogic.gdx.ai.btree.Task
    public void start() {
        super.start();
        if (this.randomChildren == null) {
            this.randomChildren = createRandomChildren();
        }
    }
}
