package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWMonitorCallback.class */
public abstract class GLFWMonitorCallback extends Callback implements GLFWMonitorCallbackI {
    public static GLFWMonitorCallback create(long j) {
        GLFWMonitorCallbackI gLFWMonitorCallbackI = (GLFWMonitorCallbackI) Callback.get(j);
        return gLFWMonitorCallbackI instanceof GLFWMonitorCallback ? (GLFWMonitorCallback) gLFWMonitorCallbackI : new Container(j, gLFWMonitorCallbackI);
    }

    public static GLFWMonitorCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWMonitorCallback create(GLFWMonitorCallbackI gLFWMonitorCallbackI) {
        return gLFWMonitorCallbackI instanceof GLFWMonitorCallback ? (GLFWMonitorCallback) gLFWMonitorCallbackI : new Container(gLFWMonitorCallbackI.address(), gLFWMonitorCallbackI);
    }

    protected GLFWMonitorCallback() {
        super(CIF);
    }

    GLFWMonitorCallback(long j) {
        super(j);
    }

    public GLFWMonitorCallback set() {
        GLFW.glfwSetMonitorCallback(this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWMonitorCallback$Container.class */
    public static final class Container extends GLFWMonitorCallback {
        private final GLFWMonitorCallbackI delegate;

        Container(long j, GLFWMonitorCallbackI gLFWMonitorCallbackI) {
            super(j);
            this.delegate = gLFWMonitorCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWMonitorCallbackI
        public final void invoke(long j, int i) {
            this.delegate.invoke(j, i);
        }
    }
}
