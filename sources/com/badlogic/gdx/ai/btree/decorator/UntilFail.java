package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.LoopDecorator;
import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/UntilFail.class */
public class UntilFail<E> extends LoopDecorator<E> {
    public UntilFail() {
    }

    public UntilFail(Task<E> task) {
        super(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        this.loop = true;
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        success();
        this.loop = false;
    }
}
