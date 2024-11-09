package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCharCallback.class */
public abstract class GLFWCharCallback extends Callback implements GLFWCharCallbackI {
    public static GLFWCharCallback create(long j) {
        GLFWCharCallbackI gLFWCharCallbackI = (GLFWCharCallbackI) Callback.get(j);
        return gLFWCharCallbackI instanceof GLFWCharCallback ? (GLFWCharCallback) gLFWCharCallbackI : new Container(j, gLFWCharCallbackI);
    }

    public static GLFWCharCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWCharCallback create(GLFWCharCallbackI gLFWCharCallbackI) {
        return gLFWCharCallbackI instanceof GLFWCharCallback ? (GLFWCharCallback) gLFWCharCallbackI : new Container(gLFWCharCallbackI.address(), gLFWCharCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWCharCallback() {
        super(CIF);
    }

    GLFWCharCallback(long j) {
        super(j);
    }

    public GLFWCharCallback set(long j) {
        GLFW.glfwSetCharCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCharCallback$Container.class */
    public static final class Container extends GLFWCharCallback {
        private final GLFWCharCallbackI delegate;

        Container(long j, GLFWCharCallbackI gLFWCharCallbackI) {
            super(j);
            this.delegate = gLFWCharCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWCharCallbackI
        public final void invoke(long j, int i) {
            this.delegate.invoke(j, i);
        }
    }
}
