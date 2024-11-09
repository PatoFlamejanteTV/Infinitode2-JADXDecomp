package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.LoopDecorator;
import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/UntilSuccess.class */
public class UntilSuccess<E> extends LoopDecorator<E> {
    public UntilSuccess() {
    }

    public UntilSuccess(Task<E> task) {
        super(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        success();
        this.loop = false;
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        this.loop = true;
    }
}
