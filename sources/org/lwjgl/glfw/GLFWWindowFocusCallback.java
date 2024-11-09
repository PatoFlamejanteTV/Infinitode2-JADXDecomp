package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowFocusCallback.class */
public abstract class GLFWWindowFocusCallback extends Callback implements GLFWWindowFocusCallbackI {
    public static GLFWWindowFocusCallback create(long j) {
        GLFWWindowFocusCallbackI gLFWWindowFocusCallbackI = (GLFWWindowFocusCallbackI) Callback.get(j);
        return gLFWWindowFocusCallbackI instanceof GLFWWindowFocusCallback ? (GLFWWindowFocusCallback) gLFWWindowFocusCallbackI : new Container(j, gLFWWindowFocusCallbackI);
    }

    public static GLFWWindowFocusCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowFocusCallback create(GLFWWindowFocusCallbackI gLFWWindowFocusCallbackI) {
        return gLFWWindowFocusCallbackI instanceof GLFWWindowFocusCallback ? (GLFWWindowFocusCallback) gLFWWindowFocusCallbackI : new Container(gLFWWindowFocusCallbackI.address(), gLFWWindowFocusCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWWindowFocusCallback() {
        super(CIF);
    }

    GLFWWindowFocusCallback(long j) {
        super(j);
    }

    public GLFWWindowFocusCallback set(long j) {
        GLFW.glfwSetWindowFocusCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowFocusCallback$Container.class */
    public static final class Container extends GLFWWindowFocusCallback {
        private final GLFWWindowFocusCallbackI delegate;

        Container(long j, GLFWWindowFocusCallbackI gLFWWindowFocusCallbackI) {
            super(j);
            this.delegate = gLFWWindowFocusCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowFocusCallbackI
        public final void invoke(long j, boolean z) {
            this.delegate.invoke(j, z);
        }
    }
}
