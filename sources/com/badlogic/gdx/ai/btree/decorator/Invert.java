package com.badlogic.gdx.ai.btree.decorator;

import com.badlogic.gdx.ai.btree.Decorator;
import com.badlogic.gdx.ai.btree.Task;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/btree/decorator/Invert.class */
public class Invert<E> extends Decorator<E> {
    public Invert() {
    }

    public Invert(Task<E> task) {
        super(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childSuccess(Task<E> task) {
        super.childFail(task);
    }

    @Override // com.badlogic.gdx.ai.btree.Decorator, com.badlogic.gdx.ai.btree.Task
    public void childFail(Task<E> task) {
        super.childSuccess(task);
    }
}
