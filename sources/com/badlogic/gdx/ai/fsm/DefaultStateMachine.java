package com.badlogic.gdx.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fsm/DefaultStateMachine.class */
public class DefaultStateMachine<E, S extends State<E>> implements StateMachine<E, S> {
    protected E owner;
    protected S currentState;
    protected S previousState;
    protected S globalState;

    public DefaultStateMachine() {
        this(null, null, null);
    }

    public DefaultStateMachine(E e) {
        this(e, null, null);
    }

    public DefaultStateMachine(E e, S s) {
        this(e, s, null);
    }

    public DefaultStateMachine(E e, S s, S s2) {
        this.owner = e;
        setInitialState(s);
        setGlobalState(s2);
    }

    public E getOwner() {
        return this.owner;
    }

    public void setOwner(E e) {
        this.owner = e;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public void setInitialState(S s) {
        this.previousState = null;
        this.currentState = s;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public void setGlobalState(S s) {
        this.globalState = s;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public S getCurrentState() {
        return this.currentState;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public S getGlobalState() {
        return this.globalState;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public S getPreviousState() {
        return this.previousState;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public void update() {
        if (this.globalState != null) {
            this.globalState.update(this.owner);
        }
        if (this.currentState != null) {
            this.currentState.update(this.owner);
        }
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public void changeState(S s) {
        this.previousState = this.currentState;
        if (this.currentState != null) {
            this.currentState.exit(this.owner);
        }
        this.currentState = s;
        if (this.currentState != null) {
            this.currentState.enter(this.owner);
        }
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public boolean revertToPreviousState() {
        if (this.previousState == null) {
            return false;
        }
        changeState(this.previousState);
        return true;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine
    public boolean isInState(S s) {
        return this.currentState == s;
    }

    @Override // com.badlogic.gdx.ai.fsm.StateMachine, com.badlogic.gdx.ai.msg.Telegraph
    public boolean handleMessage(Telegram telegram) {
        if (this.currentState != null && this.currentState.onMessage(this.owner, telegram)) {
            return true;
        }
        if (this.globalState != null && this.globalState.onMessage(this.owner, telegram)) {
            return true;
        }
        return false;
    }
}
