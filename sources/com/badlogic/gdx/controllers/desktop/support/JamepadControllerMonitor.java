package com.badlogic.gdx.controllers.desktop.support;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.desktop.JamepadControllerManager;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.IntMap;
import com.studiohartman.jamepad.ControllerIndex;
import com.studiohartman.jamepad.ControllerManager;
import java.util.Iterator;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/support/JamepadControllerMonitor.class */
public class JamepadControllerMonitor implements Runnable {
    private final ControllerManager controllerManager;
    private final ControllerListener listener;
    private final IntMap<Tuple> indexToController = new IntMap<>();
    private final IntArray disconnectedControllers = new IntArray();

    public JamepadControllerMonitor(ControllerManager controllerManager, ControllerListener controllerListener) {
        this.controllerManager = controllerManager;
        this.listener = controllerListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.controllerManager.c();
        checkForNewControllers();
        update();
        Gdx.app.postRunnable(this);
    }

    private void checkForNewControllers() {
        int i = JamepadControllerManager.jamepadConfiguration.f4023a;
        for (int i2 = 0; i2 < i; i2++) {
            try {
                ControllerIndex a2 = this.controllerManager.a(i2);
                if (!this.indexToController.containsKey(a2.d()) && a2.c()) {
                    Tuple tuple = new Tuple(a2);
                    tuple.controller.addListener(this.listener);
                    this.indexToController.put(a2.d(), tuple);
                    this.listener.connected(tuple.controller);
                }
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
        }
    }

    private void update() {
        this.disconnectedControllers.clear();
        Iterator<Tuple> it = this.indexToController.values().iterator();
        while (it.hasNext()) {
            Tuple next = it.next();
            if (!next.controller.update()) {
                this.disconnectedControllers.add(next.index.d());
            }
        }
        for (int i = 0; i < this.disconnectedControllers.size; i++) {
            this.indexToController.remove(this.disconnectedControllers.get(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/desktop/support/JamepadControllerMonitor$Tuple.class */
    public class Tuple {
        public final ControllerIndex index;
        public final JamepadController controller;

        public Tuple(ControllerIndex controllerIndex) {
            this.index = controllerIndex;
            this.controller = new JamepadController(controllerIndex);
        }
    }
}
