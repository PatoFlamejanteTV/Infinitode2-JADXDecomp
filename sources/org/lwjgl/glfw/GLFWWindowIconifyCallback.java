package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowIconifyCallback.class */
public abstract class GLFWWindowIconifyCallback extends Callback implements GLFWWindowIconifyCallbackI {
    public static GLFWWindowIconifyCallback create(long j) {
        GLFWWindowIconifyCallbackI gLFWWindowIconifyCallbackI = (GLFWWindowIconifyCallbackI) Callback.get(j);
        return gLFWWindowIconifyCallbackI instanceof GLFWWindowIconifyCallback ? (GLFWWindowIconifyCallback) gLFWWindowIconifyCallbackI : new Container(j, gLFWWindowIconifyCallbackI);
    }

    public static GLFWWindowIconifyCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowIconifyCallback create(GLFWWindowIconifyCallbackI gLFWWindowIconifyCallbackI) {
        return gLFWWindowIconifyCallbackI instanceof GLFWWindowIconifyCallback ? (GLFWWindowIconifyCallback) gLFWWindowIconifyCallbackI : new Container(gLFWWindowIconifyCallbackI.address(), gLFWWindowIconifyCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWWindowIconifyCallback() {
        super(CIF);
    }

    GLFWWindowIconifyCallback(long j) {
        super(j);
    }

    public GLFWWindowIconifyCallback set(long j) {
        GLFW.glfwSetWindowIconifyCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowIconifyCallback$Container.class */
    public static final class Container extends GLFWWindowIconifyCallback {
        private final GLFWWindowIconifyCallbackI delegate;

        Container(long j, GLFWWindowIconifyCallbackI gLFWWindowIconifyCallbackI) {
            super(j);
            this.delegate = gLFWWindowIconifyCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowIconifyCallbackI
        public final void invoke(long j, boolean z) {
            this.delegate.invoke(j, z);
        }
    }
}
