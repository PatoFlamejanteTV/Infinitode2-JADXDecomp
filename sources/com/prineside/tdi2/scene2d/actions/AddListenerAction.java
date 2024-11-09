package com.prineside.tdi2.scene2d.actions;

import com.prineside.tdi2.scene2d.Action;
import com.prineside.tdi2.scene2d.EventListener;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/scene2d/actions/AddListenerAction.class */
public class AddListenerAction extends Action {
    private EventListener c;
    private boolean d;

    @Override // com.prineside.tdi2.scene2d.Action
    public boolean act(float f) {
        if (this.d) {
            this.f2637b.addCaptureListener(this.c);
            return true;
        }
        this.f2637b.addListener(this.c);
        return true;
    }

    public EventListener getListener() {
        return this.c;
    }

    public void setListener(EventListener eventListener) {
        this.c = eventListener;
    }

    public boolean getCapture() {
        return this.d;
    }

    public void setCapture(boolean z) {
        this.d = z;
    }

    @Override // com.prineside.tdi2.scene2d.Action, com.badlogic.gdx.utils.Pool.Poolable
    public void reset() {
        super.reset();
        this.c = null;
    }
}
