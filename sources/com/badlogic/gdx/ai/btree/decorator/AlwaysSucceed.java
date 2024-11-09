package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/AlwaysSucceed.class */
public class AlwaysSucceed<E> extends Decorator<E> {
    public AlwaysSucceed() {
    }

    public AlwaysSucceed(Task<E> task) {
        super(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        childSuccess(task);
    }
}
