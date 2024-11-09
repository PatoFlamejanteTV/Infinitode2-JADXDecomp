package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWIMEStatusCallback.class */
public abstract class GLFWIMEStatusCallback extends Callback implements GLFWIMEStatusCallbackI {
    public static GLFWIMEStatusCallback create(long j) {
        GLFWIMEStatusCallbackI gLFWIMEStatusCallbackI = (GLFWIMEStatusCallbackI) Callback.get(j);
        return gLFWIMEStatusCallbackI instanceof GLFWIMEStatusCallback ? (GLFWIMEStatusCallback) gLFWIMEStatusCallbackI : new Container(j, gLFWIMEStatusCallbackI);
    }

    public static GLFWIMEStatusCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWIMEStatusCallback create(GLFWIMEStatusCallbackI gLFWIMEStatusCallbackI) {
        return gLFWIMEStatusCallbackI instanceof GLFWIMEStatusCallback ? (GLFWIMEStatusCallback) gLFWIMEStatusCallbackI : new Container(gLFWIMEStatusCallbackI.address(), gLFWIMEStatusCallbackI);
    }

    protected GLFWIMEStatusCallback() {
        super(CIF);
    }

    GLFWIMEStatusCallback(long j) {
        super(j);
    }

    public GLFWIMEStatusCallback set(long j) {
        GLFW.glfwSetIMEStatusCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWIMEStatusCallback$Container.class */
    public static final class Container extends GLFWIMEStatusCallback {
        private final GLFWIMEStatusCallbackI delegate;

        Container(long j, GLFWIMEStatusCallbackI gLFWIMEStatusCallbackI) {
            super(j);
            this.delegate = gLFWIMEStatusCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWIMEStatusCallbackI
        public final void invoke(long j) {
            this.delegate.invoke(j);
        }
    }
}
