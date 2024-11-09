package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWFramebufferSizeCallback.class */
public abstract class GLFWFramebufferSizeCallback extends Callback implements GLFWFramebufferSizeCallbackI {
    public static GLFWFramebufferSizeCallback create(long j) {
        GLFWFramebufferSizeCallbackI gLFWFramebufferSizeCallbackI = (GLFWFramebufferSizeCallbackI) Callback.get(j);
        return gLFWFramebufferSizeCallbackI instanceof GLFWFramebufferSizeCallback ? (GLFWFramebufferSizeCallback) gLFWFramebufferSizeCallbackI : new Container(j, gLFWFramebufferSizeCallbackI);
    }

    public static GLFWFramebufferSizeCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWFramebufferSizeCallback create(GLFWFramebufferSizeCallbackI gLFWFramebufferSizeCallbackI) {
        return gLFWFramebufferSizeCallbackI instanceof GLFWFramebufferSizeCallback ? (GLFWFramebufferSizeCallback) gLFWFramebufferSizeCallbackI : new Container(gLFWFramebufferSizeCallbackI.address(), gLFWFramebufferSizeCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWFramebufferSizeCallback() {
        super(CIF);
    }

    GLFWFramebufferSizeCallback(long j) {
        super(j);
    }

    public GLFWFramebufferSizeCallback set(long j) {
        GLFW.glfwSetFramebufferSizeCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWFramebufferSizeCallback$Container.class */
    public static final class Container extends GLFWFramebufferSizeCallback {
        private final GLFWFramebufferSizeCallbackI delegate;

        Container(long j, GLFWFramebufferSizeCallbackI gLFWFramebufferSizeCallbackI) {
            super(j);
            this.delegate = gLFWFramebufferSizeCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWFramebufferSizeCallbackI
        public final void invoke(long j, int i, int i2) {
            this.delegate.invoke(j, i, i2);
        }
    }
}
