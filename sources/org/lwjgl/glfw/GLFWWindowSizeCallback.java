package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowSizeCallback.class */
public abstract class GLFWWindowSizeCallback extends Callback implements GLFWWindowSizeCallbackI {
    public static GLFWWindowSizeCallback create(long j) {
        GLFWWindowSizeCallbackI gLFWWindowSizeCallbackI = (GLFWWindowSizeCallbackI) Callback.get(j);
        return gLFWWindowSizeCallbackI instanceof GLFWWindowSizeCallback ? (GLFWWindowSizeCallback) gLFWWindowSizeCallbackI : new Container(j, gLFWWindowSizeCallbackI);
    }

    public static GLFWWindowSizeCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowSizeCallback create(GLFWWindowSizeCallbackI gLFWWindowSizeCallbackI) {
        return gLFWWindowSizeCallbackI instanceof GLFWWindowSizeCallback ? (GLFWWindowSizeCallback) gLFWWindowSizeCallbackI : new Container(gLFWWindowSizeCallbackI.address(), gLFWWindowSizeCallbackI);
    }

    protected GLFWWindowSizeCallback() {
        super(CIF);
    }

    GLFWWindowSizeCallback(long j) {
        super(j);
    }

    public GLFWWindowSizeCallback set(long j) {
        GLFW.glfwSetWindowSizeCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowSizeCallback$Container.class */
    public static final class Container extends GLFWWindowSizeCallback {
        private final GLFWWindowSizeCallbackI delegate;

        Container(long j, GLFWWindowSizeCallbackI gLFWWindowSizeCallbackI) {
            super(j);
            this.delegate = gLFWWindowSizeCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowSizeCallbackI
        public final void invoke(long j, int i, int i2) {
            this.delegate.invoke(j, i, i2);
        }
    }
}
