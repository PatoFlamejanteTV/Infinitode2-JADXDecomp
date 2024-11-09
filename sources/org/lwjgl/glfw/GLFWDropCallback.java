package org.lwjgl.glfw;

import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Pointer;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWDropCallback.class */
public abstract class GLFWDropCallback extends Callback implements GLFWDropCallbackI {
    public static GLFWDropCallback create(long j) {
        GLFWDropCallbackI gLFWDropCallbackI = (GLFWDropCallbackI) Callback.get(j);
        return gLFWDropCallbackI instanceof GLFWDropCallback ? (GLFWDropCallback) gLFWDropCallbackI : new Container(j, gLFWDropCallbackI);
    }

    public static GLFWDropCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWDropCallback create(GLFWDropCallbackI gLFWDropCallbackI) {
        return gLFWDropCallbackI instanceof GLFWDropCallback ? (GLFWDropCallback) gLFWDropCallbackI : new Container(gLFWDropCallbackI.address(), gLFWDropCallbackI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLFWDropCallback() {
        super(CIF);
    }

    GLFWDropCallback(long j) {
        super(j);
    }

    public static String getName(long j, int i) {
        return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(j + (Pointer.POINTER_SIZE * i)));
    }

    public GLFWDropCallback set(long j) {
        GLFW.glfwSetDropCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWDropCallback$Container.class */
    public static final class Container extends GLFWDropCallback {
        private final GLFWDropCallbackI delegate;

        Container(long j, GLFWDropCallbackI gLFWDropCallbackI) {
            super(j);
            this.delegate = gLFWDropCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWDropCallbackI
        public final void invoke(long j, int i, long j2) {
            this.delegate.invoke(j, i, j2);
        }
    }
}
