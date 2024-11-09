package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWJoystickCallback.class */
public abstract class GLFWJoystickCallback extends Callback implements GLFWJoystickCallbackI {
    public static GLFWJoystickCallback create(long j) {
        GLFWJoystickCallbackI gLFWJoystickCallbackI = (GLFWJoystickCallbackI) Callback.get(j);
        return gLFWJoystickCallbackI instanceof GLFWJoystickCallback ? (GLFWJoystickCallback) gLFWJoystickCallbackI : new Container(j, gLFWJoystickCallbackI);
    }

    public static GLFWJoystickCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWJoystickCallback create(GLFWJoystickCallbackI gLFWJoystickCallbackI) {
        return gLFWJoystickCallbackI instanceof GLFWJoystickCallback ? (GLFWJoystickCallback) gLFWJoystickCallbackI : new Container(gLFWJoystickCallbackI.address(), gLFWJoystickCallbackI);
    }

    protected GLFWJoystickCallback() {
        super(CIF);
    }

    GLFWJoystickCallback(long j) {
        super(j);
    }

    public GLFWJoystickCallback set() {
        GLFW.glfwSetJoystickCallback(this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWJoystickCallback$Container.class */
    public static final class Container extends GLFWJoystickCallback {
        private final GLFWJoystickCallbackI delegate;

        Container(long j, GLFWJoystickCallbackI gLFWJoystickCallbackI) {
            super(j);
            this.delegate = gLFWJoystickCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWJoystickCallbackI
        public final void invoke(int i, int i2) {
            this.delegate.invoke(i, i2);
        }
    }
}
