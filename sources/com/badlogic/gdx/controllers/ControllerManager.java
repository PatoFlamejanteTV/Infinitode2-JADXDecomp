package com.badlogic.gdx.controllers;

import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/ControllerManager.class */
public interface ControllerManager {
    Array<Controller> getControllers();

    Controller getCurrentController();

    void addListener(ControllerListener controllerListener);

    void removeListener(ControllerListener controllerListener);

    Array<ControllerListener> getListeners();

    void clearListeners();
}
