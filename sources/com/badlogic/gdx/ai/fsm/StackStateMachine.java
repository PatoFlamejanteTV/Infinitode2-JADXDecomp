package com.badlogic.gdx.ai.fsm;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/ai/fsm/StackStateMachine.class */
public class StackStateMachine<E, S extends State<E>> extends DefaultStateMachine<E, S> {
    private Array<S> stateStack;

    public StackStateMachine() {
        this(null, null, null);
    }

    public StackStateMachine(E e) {
        this(e, null, null);
    }

    public StackStateMachine(E e, S s) {
        this(e, s, null);
    }

    public StackStateMachine(E e, S s, S s2) {
        super(e, s, s2);
    }

    @Override // com.badlogic.gdx.ai.fsm.DefaultStateMachine, com.badlogic.gdx.ai.fsm.StateMachine
    public void setInitialState(S s) {
        if (this.stateStack == null) {
            this.stateStack = new Array<>();
        }
        this.stateStack.clear();
        this.currentState = s;
    }

    @Override // com.badlogic.gdx.ai.fsm.DefaultStateMachine, com.badlogic.gdx.ai.fsm.StateMachine
    public S getCurrentState() {
        return this.currentState;
    }

    @Override // com.badlogic.gdx.ai.fsm.DefaultStateMachine, com.badlogic.gdx.ai.fsm.StateMachine
    public S getPreviousState() {
        if (this.stateStack.size == 0) {
            return null;
        }
        return this.stateStack.peek();
    }

    @Override // com.badlogic.gdx.ai.fsm.DefaultStateMachine, com.badlogic.gdx.ai.fsm.StateMachine
    public void changeState(S s) {
        changeState(s, true);
    }

    @Override // com.badlogic.gdx.ai.fsm.DefaultStateMachine, com.badlogic.gdx.ai.fsm.StateMachine
    public boolean revertToPreviousState() {
        if (this.stateStack.size == 0) {
            return false;
        }
        changeState(this.stateStack.pop(), false);
        return true;
    }

    private void changeState(S s, boolean z) {
        if (z && this.currentState != null) {
            this.stateStack.add(this.currentState);
        }
        if (this.currentState != null) {
            this.currentState.exit(this.owner);
        }
        this.currentState = s;
        this.currentState.enter(this.owner);
    }
}
