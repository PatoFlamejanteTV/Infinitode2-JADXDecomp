package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWKeyCallback.class */
public abstract class GLFWKeyCallback extends Callback implements GLFWKeyCallbackI {
    public static GLFWKeyCallback create(long j) {
        GLFWKeyCallbackI gLFWKeyCallbackI = (GLFWKeyCallbackI) Callback.get(j);
        return gLFWKeyCallbackI instanceof GLFWKeyCallback ? (GLFWKeyCallback) gLFWKeyCallbackI : new Container(j, gLFWKeyCallbackI);
    }

    public static GLFWKeyCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWKeyCallback create(GLFWKeyCallbackI gLFWKeyCallbackI) {
        return gLFWKeyCallbackI instanceof GLFWKeyCallback ? (GLFWKeyCallback) gLFWKeyCallbackI : new Container(gLFWKeyCallbackI.address(), gLFWKeyCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWKeyCallback() {
        super(CIF);
    }

    GLFWKeyCallback(long j) {
        super(j);
    }

    public GLFWKeyCallback set(long j) {
        GLFW.glfwSetKeyCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWKeyCallback$Container.class */
    public static final class Container extends GLFWKeyCallback {
        private final GLFWKeyCallbackI delegate;

        Container(long j, GLFWKeyCallbackI gLFWKeyCallbackI) {
            super(j);
            this.delegate = gLFWKeyCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWKeyCallbackI
        public final void invoke(long j, int i, int i2, int i3, int i4) {
            this.delegate.invoke(j, i, i2, i3, i4);
        }
    }
}
