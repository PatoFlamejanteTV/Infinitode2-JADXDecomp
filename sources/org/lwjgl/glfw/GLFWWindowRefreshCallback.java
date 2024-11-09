package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowRefreshCallback.class */
public abstract class GLFWWindowRefreshCallback extends Callback implements GLFWWindowRefreshCallbackI {
    public static GLFWWindowRefreshCallback create(long j) {
        GLFWWindowRefreshCallbackI gLFWWindowRefreshCallbackI = (GLFWWindowRefreshCallbackI) Callback.get(j);
        return gLFWWindowRefreshCallbackI instanceof GLFWWindowRefreshCallback ? (GLFWWindowRefreshCallback) gLFWWindowRefreshCallbackI : new Container(j, gLFWWindowRefreshCallbackI);
    }

    public static GLFWWindowRefreshCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWWindowRefreshCallback create(GLFWWindowRefreshCallbackI gLFWWindowRefreshCallbackI) {
        return gLFWWindowRefreshCallbackI instanceof GLFWWindowRefreshCallback ? (GLFWWindowRefreshCallback) gLFWWindowRefreshCallbackI : new Container(gLFWWindowRefreshCallbackI.address(), gLFWWindowRefreshCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWWindowRefreshCallback() {
        super(CIF);
    }

    GLFWWindowRefreshCallback(long j) {
        super(j);
    }

    public GLFWWindowRefreshCallback set(long j) {
        GLFW.glfwSetWindowRefreshCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWWindowRefreshCallback$Container.class */
    public static final class Container extends GLFWWindowRefreshCallback {
        private final GLFWWindowRefreshCallbackI delegate;

        Container(long j, GLFWWindowRefreshCallbackI gLFWWindowRefreshCallbackI) {
            super(j);
            this.delegate = gLFWWindowRefreshCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWWindowRefreshCallbackI
        public final void invoke(long j) {
            this.delegate.invoke(j);
        }
    }
}
