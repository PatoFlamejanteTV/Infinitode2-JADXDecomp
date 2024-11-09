package com.badlogic.gdx.scenes.scene2d.actions;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/RepeatAction.class */
public class RepeatAction extends DelegateAction {
    public static final int FOREVER = -1;
    private int repeatCount;
    private int executedCount;
    private boolean finished;

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f) {
        if (this.executedCount == this.repeatCount) {
            return true;
        }
        if (this.action.act(f)) {
            if (this.finished) {
                return true;
            }
            if (this.repeatCount > 0) {
                this.executedCount++;
            }
            if (this.executedCount == this.repeatCount) {
                return true;
            }
            if (this.action != null) {
                this.action.restart();
                return false;
            }
            return false;
        }
        return false;
    }

    public void finish() {
        this.finished = true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction, com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        super.restart();
        this.executedCount = 0;
        this.finished = false;
    }

    public void setCount(int i) {
        this.repeatCount = i;
    }

    public int getCount() {
        return this.repeatCount;
    }
}
