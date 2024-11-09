package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.ai.btree.annotation.TaskConstraint;
import com.badlogic.gdx.ai.utils.random.ConstantFloatDistribution;
import com.badlogic.gdx.ai.utils.random.FloatDistribution;
import com.badlogic.gdx.math.MathUtils;

@TaskConstraint(minChildren = 0, maxChildren = 1)
/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/Random.class */
public class Random<E> extends Decorator<E> {

    @TaskAttribute
    public FloatDistribution success;
    private float p;

    public Random() {
        this(ConstantFloatDistribution.ZERO_POINT_FIVE);
    }

    public Random(Task<E> task) {
        this(ConstantFloatDistribution.ZERO_POINT_FIVE, task);
    }

    public Random(FloatDistribution floatDistribution) {
        this.success = floatDistribution;
    }

    public Random(FloatDistribution floatDistribution, Task<E> task) {
        super(task);
        this.success = floatDistribution;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void start() {
        this.p = this.success.nextFloat();
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void run() {
        if (this.child != null) {
            super.run();
        } else {
            decide();
        }
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        decide();
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        decide();
    }

    private void decide() {
        if (MathUtils.random() <= this.p) {
            success();
        } else {
            fail();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public Task<E> copyTo(Task<E> task) {
        ((Random) task).success = this.success;
        return super.copyTo(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.p = 0.0f;
        this.success = ConstantFloatDistribution.ZERO_POINT_FIVE;
        super.reset();
    }
}
