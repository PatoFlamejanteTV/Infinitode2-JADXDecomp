package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCharModsCallback.class */
public abstract class GLFWCharModsCallback extends Callback implements GLFWCharModsCallbackI {
    public static GLFWCharModsCallback create(long j) {
        GLFWCharModsCallbackI gLFWCharModsCallbackI = (GLFWCharModsCallbackI) Callback.get(j);
        return gLFWCharModsCallbackI instanceof GLFWCharModsCallback ? (GLFWCharModsCallback) gLFWCharModsCallbackI : new Container(j, gLFWCharModsCallbackI);
    }

    public static GLFWCharModsCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWCharModsCallback create(GLFWCharModsCallbackI gLFWCharModsCallbackI) {
        return gLFWCharModsCallbackI instanceof GLFWCharModsCallback ? (GLFWCharModsCallback) gLFWCharModsCallbackI : new Container(gLFWCharModsCallbackI.address(), gLFWCharModsCallbackI);
    }

    protected GLFWCharModsCallback() {
        super(CIF);
    }

    GLFWCharModsCallback(long j) {
        super(j);
    }

    public GLFWCharModsCallback set(long j) {
        GLFW.glfwSetCharModsCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWCharModsCallback$Container.class */
    public static final class Container extends GLFWCharModsCallback {
        private final GLFWCharModsCallbackI delegate;

        Container(long j, GLFWCharModsCallbackI gLFWCharModsCallbackI) {
            super(j);
            this.delegate = gLFWCharModsCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWCharModsCallbackI
        public final void invoke(long j, int i, int i2) {
            this.delegate.invoke(j, i, i2);
        }
    }
}
