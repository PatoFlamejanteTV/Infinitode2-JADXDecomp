package com.badlogic.gdx.ai.btree.leaf;

import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.ai.btree.annotation.TaskAttribute;
import com.badlogic.gdx.ai.utils.random.ConstantFloatDistribution;
import com.badlogic.gdx.ai.utils.random.FloatDistribution;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/leaf/Wait.class */
public class Wait<E> extends LeafTask<E> {

    @TaskAttribute(required = true)
    public FloatDistribution seconds;
    private float startTime;
    private float timeout;

    public Wait() {
        this(ConstantFloatDistribution.ZERO);
    }

    public Wait(float f) {
        this(new ConstantFloatDistribution(f));
    }

    public Wait(FloatDistribution floatDistribution) {
        this.seconds = floatDistribution;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    public void start() {
        this.timeout = this.seconds.nextFloat();
        this.startTime = GdxAI.getTimepiece().getTime();
    }

    @Override // com.badlogic.gdx.ai.btree.LeafTask
    public Task.Status execute() {
        return GdxAI.getTimepiece().getTime() - this.startTime < this.timeout ? Task.Status.RUNNING : Task.Status.SUCCEEDED;
    }

    @Override // com.badlogic.gdx.ai.btree.Task
    protected Task<E> copyTo(Task<E> task) {
        ((Wait) task).seconds = this.seconds;
        return task;
    }

    @Override // com.badlogic.gdx.ai.btree.Task, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        this.seconds = ConstantFloatDistribution.ZERO;
        this.startTime = 0.0f;
        this.timeout = 0.0f;
        super.reset();
    }
}
