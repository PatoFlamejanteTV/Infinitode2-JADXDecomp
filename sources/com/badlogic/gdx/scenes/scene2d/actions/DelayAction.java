package com.badlogic.gdx.scenes.scene2d.actions;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/DelayAction.class */
public class DelayAction extends DelegateAction {
    private float duration;
    private float time;

    public DelayAction() {
    }

    public DelayAction(float f) {
        this.duration = f;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction
    protected boolean delegate(float f) {
        if (this.time < this.duration) {
            this.time += f;
            if (this.time < this.duration) {
                return false;
            }
            f = this.time - this.duration;
        }
        if (this.action == null) {
            return true;
        }
        return this.action.act(f);
    }

    public void finish() {
        this.time = this.duration;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.actions.DelegateAction, com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        super.restart();
        this.time = 0.0f;
    }

    public float getTime() {
        return this.time;
    }

    public void setTime(float f) {
        this.time = f;
    }

    public float getDuration() {
        return this.duration;
    }

    public void setDuration(float f) {
        this.duration = f;
    }
}
