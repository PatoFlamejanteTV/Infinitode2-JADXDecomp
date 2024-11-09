package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowCloseCallback.class */
public abstract class GLFWWindowCloseCallback extends Callback implements GLFWWindowCloseCallbackI {
    public static GLFWWindowCloseCallback create(long j) {
        GLFWWindowCloseCallbackI gLFWWindowCloseCallbackI = (GLFWWindowCloseCallbackI) Callback.get(j);
        return gLFWWindowCloseCallbackI instanceof GLFWWindowCloseCallback ? (GLFWWindowCloseCallback) gLFWWindowCloseCallbackI : new Container(j, gLFWWindowCloseCallbackI);
    }

    public static GLFWWindowCloseCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowCloseCallback create(GLFWWindowCloseCallbackI gLFWWindowCloseCallbackI) {
        return gLFWWindowCloseCallbackI instanceof GLFWWindowCloseCallback ? (GLFWWindowCloseCallback) gLFWWindowCloseCallbackI : new Container(gLFWWindowCloseCallbackI.address(), gLFWWindowCloseCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWWindowCloseCallback() {
        super(CIF);
    }

    GLFWWindowCloseCallback(long j) {
        super(j);
    }

    public GLFWWindowCloseCallback set(long j) {
        GLFW.glfwSetWindowCloseCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowCloseCallback$Container.class */
    public static final class Container extends GLFWWindowCloseCallback {
        private final GLFWWindowCloseCallbackI delegate;

        Container(long j, GLFWWindowCloseCallbackI gLFWWindowCloseCallbackI) {
            super(j);
            this.delegate = gLFWWindowCloseCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowCloseCallbackI
        public final void invoke(long j) {
            this.delegate.invoke(j);
        }
    }
}
