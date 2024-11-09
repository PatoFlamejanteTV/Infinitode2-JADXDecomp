package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowMaximizeCallback.class */
public abstract class GLFWWindowMaximizeCallback extends Callback implements GLFWWindowMaximizeCallbackI {
    public static GLFWWindowMaximizeCallback create(long j) {
        GLFWWindowMaximizeCallbackI gLFWWindowMaximizeCallbackI = (GLFWWindowMaximizeCallbackI) Callback.get(j);
        return gLFWWindowMaximizeCallbackI instanceof GLFWWindowMaximizeCallback ? (GLFWWindowMaximizeCallback) gLFWWindowMaximizeCallbackI : new Container(j, gLFWWindowMaximizeCallbackI);
    }

    public static GLFWWindowMaximizeCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowMaximizeCallback create(GLFWWindowMaximizeCallbackI gLFWWindowMaximizeCallbackI) {
        return gLFWWindowMaximizeCallbackI instanceof GLFWWindowMaximizeCallback ? (GLFWWindowMaximizeCallback) gLFWWindowMaximizeCallbackI : new Container(gLFWWindowMaximizeCallbackI.address(), gLFWWindowMaximizeCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWWindowMaximizeCallback() {
        super(CIF);
    }

    GLFWWindowMaximizeCallback(long j) {
        super(j);
    }

    public GLFWWindowMaximizeCallback set(long j) {
        GLFW.glfwSetWindowMaximizeCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowMaximizeCallback$Container.class */
    public static final class Container extends GLFWWindowMaximizeCallback {
        private final GLFWWindowMaximizeCallbackI delegate;

        Container(long j, GLFWWindowMaximizeCallbackI gLFWWindowMaximizeCallbackI) {
            super(j);
            this.delegate = gLFWWindowMaximizeCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowMaximizeCallbackI
        public final void invoke(long j, boolean z) {
            this.delegate.invoke(j, z);
        }
    }
}
