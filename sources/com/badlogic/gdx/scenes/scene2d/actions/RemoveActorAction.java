package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/RemoveActorAction.class */
public class RemoveActorAction extends Action {
    private boolean removed;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        if (!this.removed) {
            this.removed = true;
            this.target.remove();
            return true;
        }
        return true;
    }

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public void restart() {
        this.removed = false;
    }
}
