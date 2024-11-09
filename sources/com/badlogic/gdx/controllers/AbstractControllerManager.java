package com.badlogic.gdx.controllers;

import com.badlogic.gdx.utils.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/AbstractControllerManager.class */
public abstract class AbstractControllerManager implements ControllerManager {
    protected final Array<Controller> controllers = new Array<>();
    private Controller currentController;

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public Array<Controller> getControllers() {
        return this.controllers;
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public Controller getCurrentController() {
        return this.currentController;
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/AbstractControllerManager$ManageCurrentControllerListener.class */
    public class ManageCurrentControllerListener extends ControllerAdapter {
        public ManageCurrentControllerListener() {
        }

        @Override // com.badlogic.gdx.controllers.ControllerAdapter, com.badlogic.gdx.controllers.ControllerListener
        public void connected(Controller controller) {
            if (AbstractControllerManager.this.currentController == null) {
                AbstractControllerManager.this.currentController = controller;
            }
        }

        @Override // com.badlogic.gdx.controllers.ControllerAdapter, com.badlogic.gdx.controllers.ControllerListener
        public void disconnected(Controller controller) {
            if (AbstractControllerManager.this.currentController == controller) {
                AbstractControllerManager.this.currentController = null;
            }
        }

        @Override // com.badlogic.gdx.controllers.ControllerAdapter, com.badlogic.gdx.controllers.ControllerListener
        public boolean buttonDown(Controller controller, int i) {
            AbstractControllerManager.this.currentController = controller;
            return false;
        }

        @Override // com.badlogic.gdx.controllers.ControllerAdapter, com.badlogic.gdx.controllers.ControllerListener
        public boolean buttonUp(Controller controller, int i) {
            AbstractControllerManager.this.currentController = controller;
            return false;
        }

        @Override // com.badlogic.gdx.controllers.ControllerAdapter, com.badlogic.gdx.controllers.ControllerListener
        public boolean axisMoved(Controller controller, int i, float f) {
            AbstractControllerManager.this.currentController = controller;
            return false;
        }
    }
}
