package com.badlogic.gdx.ai.btree.branch;

import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/branch/RandomSelector.class */
public class RandomSelector<E> extends Selector<E> {
    public RandomSelector() {
    }

    public RandomSelector(Task<E>... taskArr) {
        super(new Array(taskArr));
    }

    public RandomSelector(Array<Task<E>> array) {
        super(array);
    }

    @Override // com.badlogic.gdx.ai.btree.SingleRunningChildBranch, com.badlogic.gdx.ai.btree.Task
    public void start() {
        super.start();
        if (this.randomChildren == null) {
            this.randomChildren = createRandomChildren();
        }
    }
}
