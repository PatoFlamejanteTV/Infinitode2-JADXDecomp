package com.badlogic.gdx.controllers;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/ControllerAdapter.class */
public class ControllerAdapter implements ControllerListener {
    @Override // com.badlogic.gdx.controllers.ControllerListener
    public boolean buttonDown(Controller controller, int i) {
        return false;
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public boolean buttonUp(Controller controller, int i) {
        return false;
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public boolean axisMoved(Controller controller, int i, float f) {
        return false;
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public void connected(Controller controller) {
    }

    @Override // com.badlogic.gdx.controllers.ControllerListener
    public void disconnected(Controller controller) {
    }
}
