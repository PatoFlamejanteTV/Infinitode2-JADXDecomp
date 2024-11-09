package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWMouseButtonCallback.class */
public abstract class GLFWMouseButtonCallback extends Callback implements GLFWMouseButtonCallbackI {
    public static GLFWMouseButtonCallback create(long j) {
        GLFWMouseButtonCallbackI gLFWMouseButtonCallbackI = (GLFWMouseButtonCallbackI) Callback.get(j);
        return gLFWMouseButtonCallbackI instanceof GLFWMouseButtonCallback ? (GLFWMouseButtonCallback) gLFWMouseButtonCallbackI : new Container(j, gLFWMouseButtonCallbackI);
    }

    public static GLFWMouseButtonCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWMouseButtonCallback create(GLFWMouseButtonCallbackI gLFWMouseButtonCallbackI) {
        return gLFWMouseButtonCallbackI instanceof GLFWMouseButtonCallback ? (GLFWMouseButtonCallback) gLFWMouseButtonCallbackI : new Container(gLFWMouseButtonCallbackI.address(), gLFWMouseButtonCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWMouseButtonCallback() {
        super(CIF);
    }

    GLFWMouseButtonCallback(long j) {
        super(j);
    }

    public GLFWMouseButtonCallback set(long j) {
        GLFW.glfwSetMouseButtonCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWMouseButtonCallback$Container.class */
    public static final class Container extends GLFWMouseButtonCallback {
        private final GLFWMouseButtonCallbackI delegate;

        Container(long j, GLFWMouseButtonCallbackI gLFWMouseButtonCallbackI) {
            super(j);
            this.delegate = gLFWMouseButtonCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWMouseButtonCallbackI
        public final void invoke(long j, int i, int i2, int i3) {
            this.delegate.invoke(j, i, i2, i3);
        }
    }
}
