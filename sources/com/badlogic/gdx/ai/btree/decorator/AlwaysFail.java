package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/AlwaysFail.class */
public class AlwaysFail<E> extends Decorator<E> {
    public AlwaysFail() {
    }

    public AlwaysFail(Task<E> task) {
        super(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        childFail(task);
    }
}
