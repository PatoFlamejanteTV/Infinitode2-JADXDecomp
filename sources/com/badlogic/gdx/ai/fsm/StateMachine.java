package com.badlogic.gdx.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fsm/StateMachine.class */
public interface StateMachine<E, S extends State<E>> extends Telegraph {
    void update();

    void changeState(S s);

    boolean revertToPreviousState();

    void setInitialState(S s);

    void setGlobalState(S s);

    S getCurrentState();

    S getGlobalState();

    S getPreviousState();

    boolean isInState(S s);

    @Override // com.badlogic.gdx.ai.msg.Telegraph
    boolean handleMessage(Telegram telegram);
}
