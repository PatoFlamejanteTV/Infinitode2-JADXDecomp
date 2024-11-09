package com.badlogic.gdx.controllers;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/ControllerListener.class */
public interface ControllerListener {
    void connected(Controller controller);

    void disconnected(Controller controller);

    boolean buttonDown(Controller controller, int i);

    boolean buttonUp(Controller controller, int i);

    boolean axisMoved(Controller controller, int i, float f);
}
