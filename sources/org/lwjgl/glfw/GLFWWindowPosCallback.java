package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowPosCallback.class */
public abstract class GLFWWindowPosCallback extends Callback implements GLFWWindowPosCallbackI {
    public static GLFWWindowPosCallback create(long j) {
        GLFWWindowPosCallbackI gLFWWindowPosCallbackI = (GLFWWindowPosCallbackI) Callback.get(j);
        return gLFWWindowPosCallbackI instanceof GLFWWindowPosCallback ? (GLFWWindowPosCallback) gLFWWindowPosCallbackI : new Container(j, gLFWWindowPosCallbackI);
    }

    public static GLFWWindowPosCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowPosCallback create(GLFWWindowPosCallbackI gLFWWindowPosCallbackI) {
        return gLFWWindowPosCallbackI instanceof GLFWWindowPosCallback ? (GLFWWindowPosCallback) gLFWWindowPosCallbackI : new Container(gLFWWindowPosCallbackI.address(), gLFWWindowPosCallbackI);
    }

    protected GLFWWindowPosCallback() {
        super(CIF);
    }

    GLFWWindowPosCallback(long j) {
        super(j);
    }

    public GLFWWindowPosCallback set(long j) {
        GLFW.glfwSetWindowPosCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowPosCallback$Container.class */
    public static final class Container extends GLFWWindowPosCallback {
        private final GLFWWindowPosCallbackI delegate;

        Container(long j, GLFWWindowPosCallbackI gLFWWindowPosCallbackI) {
            super(j);
            this.delegate = gLFWWindowPosCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowPosCallbackI
        public final void invoke(long j, int i, int i2) {
            this.delegate.invoke(j, i, i2);
        }
    }
}
