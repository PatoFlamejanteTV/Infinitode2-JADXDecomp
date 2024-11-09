package com.prineside.tdi2.scene2d.actions;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.Actor;
import com.prineside.tdi2.scene2d.utils.Layout;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/LayoutAction.class */
public class LayoutAction extends Action {
    private boolean c;

    @Override // com.prineside.tdi2.scene2d.Action
    public void setTarget(Actor actor) {
        if (actor != null && !(actor instanceof Layout)) {
            throw new GdxRuntimeException("Actor must implement layout: " + actor);
        }
        super.setTarget(actor);
    }

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        ((Layout) this.f2637b).setLayoutEnabled(this.c);
        return true;
    }

    public boolean isEnabled() {
        return this.c;
    }

    public void setLayoutEnabled(boolean z) {
        this.c = z;
    }
}
