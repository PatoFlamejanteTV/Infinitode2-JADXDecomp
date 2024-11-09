package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCursorEnterCallback.class */
public abstract class GLFWCursorEnterCallback extends Callback implements GLFWCursorEnterCallbackI {
    public static GLFWCursorEnterCallback create(long j) {
        GLFWCursorEnterCallbackI gLFWCursorEnterCallbackI = (GLFWCursorEnterCallbackI) Callback.get(j);
        return gLFWCursorEnterCallbackI instanceof GLFWCursorEnterCallback ? (GLFWCursorEnterCallback) gLFWCursorEnterCallbackI : new Container(j, gLFWCursorEnterCallbackI);
    }

    public static GLFWCursorEnterCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWCursorEnterCallback create(GLFWCursorEnterCallbackI gLFWCursorEnterCallbackI) {
        return gLFWCursorEnterCallbackI instanceof GLFWCursorEnterCallback ? (GLFWCursorEnterCallback) gLFWCursorEnterCallbackI : new Container(gLFWCursorEnterCallbackI.address(), gLFWCursorEnterCallbackI);
    }

    protected GLFWCursorEnterCallback() {
        super(CIF);
    }

    GLFWCursorEnterCallback(long j) {
        super(j);
    }

    public GLFWCursorEnterCallback set(long j) {
        GLFW.glfwSetCursorEnterCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCursorEnterCallback$Container.class */
    public static final class Container extends GLFWCursorEnterCallback {
        private final GLFWCursorEnterCallbackI delegate;

        Container(long j, GLFWCursorEnterCallbackI gLFWCursorEnterCallbackI) {
            super(j);
            this.delegate = gLFWCursorEnterCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWCursorEnterCallbackI
        public final void invoke(long j, boolean z) {
            this.delegate.invoke(j, z);
        }
    }
}
