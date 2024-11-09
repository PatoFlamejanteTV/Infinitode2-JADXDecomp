package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWPreeditCallback.class */
public abstract class GLFWPreeditCallback extends Callback implements GLFWPreeditCallbackI {
    public static GLFWPreeditCallback create(long j) {
        GLFWPreeditCallbackI gLFWPreeditCallbackI = (GLFWPreeditCallbackI) Callback.get(j);
        return gLFWPreeditCallbackI instanceof GLFWPreeditCallback ? (GLFWPreeditCallback) gLFWPreeditCallbackI : new Container(j, gLFWPreeditCallbackI);
    }

    public static GLFWPreeditCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWPreeditCallback create(GLFWPreeditCallbackI gLFWPreeditCallbackI) {
        return gLFWPreeditCallbackI instanceof GLFWPreeditCallback ? (GLFWPreeditCallback) gLFWPreeditCallbackI : new Container(gLFWPreeditCallbackI.address(), gLFWPreeditCallbackI);
    }

    protected GLFWPreeditCallback() {
        super(CIF);
    }

    GLFWPreeditCallback(long j) {
        super(j);
    }

    public GLFWPreeditCallback set(long j) {
        GLFW.glfwSetPreeditCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWPreeditCallback$Container.class */
    public static final class Container extends GLFWPreeditCallback {
        private final GLFWPreeditCallbackI delegate;

        Container(long j, GLFWPreeditCallbackI gLFWPreeditCallbackI) {
            super(j);
            this.delegate = gLFWPreeditCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWPreeditCallbackI
        public final void invoke(long j, int i, long j2, int i2, long j3, int i3, int i4) {
            this.delegate.invoke(j, i, j2, i2, j3, i3, i4);
        }
    }
}
