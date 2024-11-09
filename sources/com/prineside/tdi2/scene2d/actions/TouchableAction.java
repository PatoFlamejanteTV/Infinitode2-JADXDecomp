package com.prineside.tdi2.scene2d.actions;

import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Touchable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/TouchableAction.class */
public class TouchableAction extends Action {
    private Touchable c;

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        this.f2637b.setTouchable(this.c);
        return true;
    }

    public Touchable getTouchable() {
        return this.c;
    }

    public void setTouchable(Touchable touchable) {
        this.c = touchable;
    }
}
