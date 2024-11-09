package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCursorPosCallback.class */
public abstract class GLFWCursorPosCallback extends Callback implements GLFWCursorPosCallbackI {
    public static GLFWCursorPosCallback create(long j) {
        GLFWCursorPosCallbackI gLFWCursorPosCallbackI = (GLFWCursorPosCallbackI) Callback.get(j);
        return gLFWCursorPosCallbackI instanceof GLFWCursorPosCallback ? (GLFWCursorPosCallback) gLFWCursorPosCallbackI : new Container(j, gLFWCursorPosCallbackI);
    }

    public static GLFWCursorPosCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWCursorPosCallback create(GLFWCursorPosCallbackI gLFWCursorPosCallbackI) {
        return gLFWCursorPosCallbackI instanceof GLFWCursorPosCallback ? (GLFWCursorPosCallback) gLFWCursorPosCallbackI : new Container(gLFWCursorPosCallbackI.address(), gLFWCursorPosCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWCursorPosCallback() {
        super(CIF);
    }

    GLFWCursorPosCallback(long j) {
        super(j);
    }

    public GLFWCursorPosCallback set(long j) {
        GLFW.glfwSetCursorPosCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCursorPosCallback$Container.class */
    public static final class Container extends GLFWCursorPosCallback {
        private final GLFWCursorPosCallbackI delegate;

        Container(long j, GLFWCursorPosCallbackI gLFWCursorPosCallbackI) {
            super(j);
            this.delegate = gLFWCursorPosCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWCursorPosCallbackI
        public final void invoke(long j, double d, double d2) {
            this.delegate.invoke(j, d, d2);
        }
    }
}
