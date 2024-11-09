package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.LoopDecorator;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.ai.utils.random.ConstantIntegerDistribution;
import com.badlogic.gdx.ai.utils.random.IntegerDistribution;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/Repeat.class */
public class Repeat<E> extends LoopDecorator<E> {

    @TaskAttribute
    public IntegerDistribution times;
    private int count;

    public Repeat() {
        this(null);
    }

    public Repeat(Task<E> task) {
        this(ConstantIntegerDistribution.NEGATIVE_ONE, task);
    }

    public Repeat(IntegerDistribution integerDistribution, Task<E> task) {
        super(task);
        this.times = integerDistribution;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void start() {
        this.count = this.times.nextInt();
    }

    @Override // com.badlogic.gdx.ai.btree.LoopDecorator
    public boolean condition() {
        return this.loop && this.count != 0;
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        if (this.count > 0) {
            this.count--;
        }
        if (this.count == 0) {
            super.childSuccess(task);
            this.loop = false;
        } else {
            this.loop = true;
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        childSuccess(task);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        ((Repeat) task).times = this.times;
        return super.copyTo(task);
    }

    @Override // com.badlogic.gdx.ai.btree.LoopDecorator, com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.count = 0;
        this.times = null;
        super.reset();
    }
}
