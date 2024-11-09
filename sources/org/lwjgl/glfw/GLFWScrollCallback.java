package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWScrollCallback.class */
public abstract class GLFWScrollCallback extends Callback implements GLFWScrollCallbackI {
    public static GLFWScrollCallback create(long j) {
        GLFWScrollCallbackI gLFWScrollCallbackI = (GLFWScrollCallbackI) Callback.get(j);
        return gLFWScrollCallbackI instanceof GLFWScrollCallback ? (GLFWScrollCallback) gLFWScrollCallbackI : new Container(j, gLFWScrollCallbackI);
    }

    public static GLFWScrollCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWScrollCallback create(GLFWScrollCallbackI gLFWScrollCallbackI) {
        return gLFWScrollCallbackI instanceof GLFWScrollCallback ? (GLFWScrollCallback) gLFWScrollCallbackI : new Container(gLFWScrollCallbackI.address(), gLFWScrollCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWScrollCallback() {
        super(CIF);
    }

    GLFWScrollCallback(long j) {
        super(j);
    }

    public GLFWScrollCallback set(long j) {
        GLFW.glfwSetScrollCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWScrollCallback$Container.class */
    public static final class Container extends GLFWScrollCallback {
        private final GLFWScrollCallbackI delegate;

        Container(long j, GLFWScrollCallbackI gLFWScrollCallbackI) {
            super(j);
            this.delegate = gLFWScrollCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWScrollCallbackI
        public final void invoke(long j, double d, double d2) {
            this.delegate.invoke(j, d, d2);
        }
    }
}
