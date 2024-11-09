package com.badlogic.gdx.ai.fsm;

import com.badlogic.gdx.ai.msg.Telegram;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fsm/State.class */
public interface State<E> {
    void enter(E e);

    void update(E e);

    void exit(E e);

    boolean onMessage(E e, Telegram telegram);
}
