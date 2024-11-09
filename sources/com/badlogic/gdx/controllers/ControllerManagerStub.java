package com.badlogic.gdx.controllers;

import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/ControllerManagerStub.class */
public class ControllerManagerStub extends AbstractControllerManager {
    @Override // com.badlogic.gdx.controllers.ControllerManager
    public void addListener(ControllerListener controllerListener) {
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public void removeListener(ControllerListener controllerListener) {
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public void clearListeners() {
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public Array<ControllerListener> getListeners() {
        return new Array<>();
    }
}
