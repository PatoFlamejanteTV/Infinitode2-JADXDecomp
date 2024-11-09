package com.badlogic.gdx.controllers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/controllers/Controllers.class */
public class Controllers {
    private static final String TAG = "Controllers";
    static final ObjectMap<Application, ControllerManager> managers = new ObjectMap<>();
    public static String preferredManager = null;

    public static Array<Controller> getControllers() {
        initialize();
        return getManager().getControllers();
    }

    public static Controller getCurrent() {
        initialize();
        return getManager().getCurrentController();
    }

    public static void addListener(ControllerListener controllerListener) {
        initialize();
        getManager().addListener(controllerListener);
    }

    public static void removeListener(ControllerListener controllerListener) {
        initialize();
        getManager().removeListener(controllerListener);
    }

    public static void clearListeners() {
        initialize();
        getManager().clearListeners();
    }

    public static Array<ControllerListener> getListeners() {
        initialize();
        return getManager().getListeners();
    }

    private static ControllerManager getManager() {
        return managers.get(Gdx.app);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v22, types: [com.badlogic.gdx.controllers.ControllerManager] */
    private static void initialize() {
        if (managers.containsKey(Gdx.app)) {
            return;
        }
        String str = null;
        Application.ApplicationType type = Gdx.app.getType();
        ControllerManagerStub controllerManagerStub = null;
        if (preferredManager != null) {
            str = preferredManager;
        } else if (type == Application.ApplicationType.Android) {
            str = "com.badlogic.gdx.controllers.android.AndroidControllers";
        } else if (type == Application.ApplicationType.Desktop) {
            str = "com.badlogic.gdx.controllers.desktop.JamepadControllerManager";
        } else if (type == Application.ApplicationType.WebGL) {
            str = "com.badlogic.gdx.controllers.gwt.GwtControllers";
        } else if (type == Application.ApplicationType.iOS) {
            str = "com.badlogic.gdx.controllers.IosControllerManager";
        } else {
            Gdx.app.log(TAG, "No controller manager is available for: " + Gdx.app.getType());
            controllerManagerStub = new ControllerManagerStub();
        }
        if (controllerManagerStub == null) {
            try {
                controllerManagerStub = (ControllerManager) ClassReflection.newInstance(ClassReflection.forName(str));
            } catch (Throwable th) {
                throw new GdxRuntimeException("Error creating controller manager: " + str, th);
            }
        }
        managers.put(Gdx.app, controllerManagerStub);
        final Application application = Gdx.app;
        Gdx.app.addLifecycleListener(new LifecycleListener() { // from class: com.badlogic.gdx.controllers.Controllers.1
            @Override // com.badlogic.gdx.LifecycleListener
            public final void resume() {
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public final void pause() {
            }

            @Override // com.badlogic.gdx.LifecycleListener
            public final void dispose() {
                Controllers.managers.remove(Application.this);
                Gdx.app.log(Controllers.TAG, "removed manager for application, " + Controllers.managers.size + " managers active");
            }
        });
        Gdx.app.log(TAG, "added manager for application, " + managers.size + " managers active");
    }
}
