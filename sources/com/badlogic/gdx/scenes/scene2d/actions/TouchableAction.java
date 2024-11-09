package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/scenes/scene2d/actions/TouchableAction.class */
public class TouchableAction extends Action {
    private Touchable touchable;

    @Override // com.badlogic.gdx.scenes.scene2d.Action
    public boolean act(float f) {
        this.target.setTouchable(this.touchable);
        return true;
    }

    public Touchable getTouchable() {
        return this.touchable;
    }

    public void setTouchable(Touchable touchable) {
        this.touchable = touchable;
    }
}
