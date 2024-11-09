package com.badlogic.gdx.controllers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/AbstractController.class */
abstract class AbstractController implements Controller, Disposable {
    private final Array<ControllerListener> listeners = new Array<>();
    private boolean connected = true;

    AbstractController() {
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        synchronized (this.listeners) {
            this.listeners.clear();
        }
        this.connected = false;
    }

    protected void notifyListenersButtonUp(int i) {
        Array<ControllerListener> listeners = Controllers.getListeners();
        synchronized (listeners) {
            Array.ArrayIterator<ControllerListener> it = listeners.iterator();
            while (it.hasNext() && !it.next().buttonUp(this, i)) {
            }
        }
        synchronized (this.listeners) {
            Array.ArrayIterator<ControllerListener> it2 = this.listeners.iterator();
            while (it2.hasNext() && !it2.next().buttonUp(this, i)) {
            }
        }
    }

    protected void notifyListenersButtonDown(int i) {
        Array<ControllerListener> listeners = Controllers.getListeners();
        synchronized (listeners) {
            Array.ArrayIterator<ControllerListener> it = listeners.iterator();
            while (it.hasNext() && !it.next().buttonDown(this, i)) {
            }
        }
        synchronized (this.listeners) {
            Array.ArrayIterator<ControllerListener> it2 = this.listeners.iterator();
            while (it2.hasNext() && !it2.next().buttonDown(this, i)) {
            }
        }
    }

    protected void notifyListenersAxisMoved(int i, float f) {
        Array<ControllerListener> listeners = Controllers.getListeners();
        synchronized (listeners) {
            Array.ArrayIterator<ControllerListener> it = listeners.iterator();
            while (it.hasNext() && !it.next().axisMoved(this, i, f)) {
            }
        }
        synchronized (this.listeners) {
            Array.ArrayIterator<ControllerListener> it2 = this.listeners.iterator();
            while (it2.hasNext() && !it2.next().axisMoved(this, i, f)) {
            }
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void addListener(ControllerListener controllerListener) {
        synchronized (this.listeners) {
            if (!this.listeners.contains(controllerListener, true)) {
                this.listeners.add(controllerListener);
            }
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void removeListener(ControllerListener controllerListener) {
        synchronized (this.listeners) {
            this.listeners.removeValue(controllerListener, true);
        }
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean canVibrate() {
        return false;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean isVibrating() {
        return false;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void startVibration(int i, float f) {
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void cancelVibration() {
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean supportsPlayerIndex() {
        return false;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public boolean isConnected() {
        return this.connected;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public int getPlayerIndex() {
        return -1;
    }

    @Override // com.badlogic.gdx.controllers.Controller
    public void setPlayerIndex(int i) {
    }
}
