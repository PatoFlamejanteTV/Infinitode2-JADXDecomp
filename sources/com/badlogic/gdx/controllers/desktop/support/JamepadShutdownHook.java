package com.badlogic.gdx.controllers.desktop.support;

import com.badlogic.gdx.LifecycleListener;
import com.studiohartman.jamepad.ControllerManager;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/support/JamepadShutdownHook.class */
public class JamepadShutdownHook implements LifecycleListener {
    private final ControllerManager controllerManager;

    public JamepadShutdownHook(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    @Override // com.badlogic.gdx.LifecycleListener
    public void pause() {
    }

    @Override // com.badlogic.gdx.LifecycleListener
    public void resume() {
    }

    @Override // com.badlogic.gdx.LifecycleListener
    public void dispose() {
        this.controllerManager.b();
    }
}
