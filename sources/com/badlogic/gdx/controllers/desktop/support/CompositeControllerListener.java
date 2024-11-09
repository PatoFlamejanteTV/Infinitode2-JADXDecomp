package com.badlogic.gdx.controllers.desktop.support;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/support/CompositeControllerListener.class */
public class CompositeControllerListener implements ControllerListener {
    private final LinkedList<ControllerListener> listeners = new LinkedList<>();

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public void connected(Controller controller) {
        Iterator<ControllerListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().connected(controller);
        }
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public void disconnected(Controller controller) {
        Iterator<ControllerListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().disconnected(controller);
        }
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public boolean buttonDown(Controller controller, int i) {
        Iterator<ControllerListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            if (it.next().buttonDown(controller, i)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public boolean buttonUp(Controller controller, int i) {
        Iterator<ControllerListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            if (it.next().buttonUp(controller, i)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public boolean axisMoved(Controller controller, int i, float f) {
        Iterator<ControllerListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            if (it.next().axisMoved(controller, i, f)) {
                return true;
            }
        }
        return false;
    }

    public void addListener(ControllerListener controllerListener) {
        this.listeners.add(controllerListener);
    }

    public void removeListener(ControllerListener controllerListener) {
        this.listeners.remove(controllerListener);
    }

    public void clear() {
        this.listeners.clear();
    }
}
