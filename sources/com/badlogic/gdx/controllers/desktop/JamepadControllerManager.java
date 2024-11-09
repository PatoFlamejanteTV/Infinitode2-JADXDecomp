package com.badlogic.gdx.controllers.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.AbstractControllerManager;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.desktop.support.CompositeControllerListener;
import com.badlogic.gdx.controllers.desktop.support.JamepadControllerMonitor;
import com.badlogic.gdx.controllers.desktop.support.JamepadShutdownHook;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.a;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/JamepadControllerManager.class */
public class JamepadControllerManager extends AbstractControllerManager implements Disposable {
    public static a jamepadConfiguration;
    private static boolean nativeLibInitialized = false;
    private static ControllerManager controllerManager;
    private final CompositeControllerListener compositeListener = new CompositeControllerListener();

    public JamepadControllerManager() {
        this.compositeListener.addListener(new ManageControllers());
        if (!nativeLibInitialized) {
            if (jamepadConfiguration == null) {
                jamepadConfiguration = new a();
            }
            ControllerManager controllerManager2 = new ControllerManager(jamepadConfiguration);
            controllerManager = controllerManager2;
            controllerManager2.a();
            JamepadControllerMonitor jamepadControllerMonitor = new JamepadControllerMonitor(controllerManager, this.compositeListener);
            jamepadControllerMonitor.run();
            Gdx.app.addLifecycleListener(new JamepadShutdownHook(controllerManager));
            Gdx.app.postRunnable(jamepadControllerMonitor);
            nativeLibInitialized = true;
        }
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public void addListener(ControllerListener controllerListener) {
        this.compositeListener.addListener(controllerListener);
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public void removeListener(ControllerListener controllerListener) {
        this.compositeListener.removeListener(controllerListener);
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public Array<ControllerListener> getListeners() {
        Array<ControllerListener> array = new Array<>();
        array.add(this.compositeListener);
        return array;
    }

    @Override // com.badlogic.gdx.controllers.ControllerManager
    public void clearListeners() {
        this.compositeListener.clear();
        this.compositeListener.addListener(new ManageControllers());
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        controllerManager.b();
    }

    public static void addMappingsFromFile(String str) {
        controllerManager.a(str);
    }

    public static void logLastNativeGamepadError() {
        Gdx.app.error("Jamepad", controllerManager.getLastNativeError());
    }

    /* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/JamepadControllerManager$ManageControllers.class */
    private class ManageControllers extends AbstractControllerManager.ManageCurrentControllerListener {
        private ManageControllers() {
            super();
        }

        @Override // com.badlogic.gdx.controllers.AbstractControllerManager.ManageCurrentControllerListener, com.badlogic.gdx.controllers.ControllerAdapter, com.badlogic.gdx.controllers.ControllerListener
        public void connected(Controller controller) {
            synchronized (JamepadControllerManager.this.controllers) {
                JamepadControllerManager.this.controllers.add(controller);
            }
            super.connected(controller);
        }

        @Override // com.badlogic.gdx.controllers.AbstractControllerManager.ManageCurrentControllerListener, com.badlogic.gdx.controllers.ControllerAdapter, com.badlogic.gdx.controllers.ControllerListener
        public void disconnected(Controller controller) {
            synchronized (JamepadControllerManager.this.controllers) {
                JamepadControllerManager.this.controllers.removeValue(controller, true);
            }
            super.disconnected(controller);
        }
    }
}
