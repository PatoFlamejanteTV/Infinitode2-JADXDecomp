package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowContentScaleCallback.class */
public abstract class GLFWWindowContentScaleCallback extends Callback implements GLFWWindowContentScaleCallbackI {
    public static GLFWWindowContentScaleCallback create(long j) {
        GLFWWindowContentScaleCallbackI gLFWWindowContentScaleCallbackI = (GLFWWindowContentScaleCallbackI) Callback.get(j);
        return gLFWWindowContentScaleCallbackI instanceof GLFWWindowContentScaleCallback ? (GLFWWindowContentScaleCallback) gLFWWindowContentScaleCallbackI : new Container(j, gLFWWindowContentScaleCallbackI);
    }

    public static GLFWWindowContentScaleCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowContentScaleCallback create(GLFWWindowContentScaleCallbackI gLFWWindowContentScaleCallbackI) {
        return gLFWWindowContentScaleCallbackI instanceof GLFWWindowContentScaleCallback ? (GLFWWindowContentScaleCallback) gLFWWindowContentScaleCallbackI : new Container(gLFWWindowContentScaleCallbackI.address(), gLFWWindowContentScaleCallbackI);
    }

    protected GLFWWindowContentScaleCallback() {
        super(CIF);
    }

    GLFWWindowContentScaleCallback(long j) {
        super(j);
    }

    public GLFWWindowContentScaleCallback set(long j) {
        GLFW.glfwSetWindowContentScaleCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowContentScaleCallback$Container.class */
    public static final class Container extends GLFWWindowContentScaleCallback {
        private final GLFWWindowContentScaleCallbackI delegate;

        Container(long j, GLFWWindowContentScaleCallbackI gLFWWindowContentScaleCallbackI) {
            super(j);
            this.delegate = gLFWWindowContentScaleCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowContentScaleCallbackI
        public final void invoke(long j, float f, float f2) {
            this.delegate.invoke(j, f, f2);
        }
    }
}
