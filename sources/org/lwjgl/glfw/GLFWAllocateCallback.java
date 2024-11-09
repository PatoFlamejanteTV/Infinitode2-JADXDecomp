package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWAllocateCallback.class */
public abstract class GLFWAllocateCallback extends Callback implements GLFWAllocateCallbackI {
    public static GLFWAllocateCallback create(long j) {
        GLFWAllocateCallbackI gLFWAllocateCallbackI = (GLFWAllocateCallbackI) Callback.get(j);
        return gLFWAllocateCallbackI instanceof GLFWAllocateCallback ? (GLFWAllocateCallback) gLFWAllocateCallbackI : new Container(j, gLFWAllocateCallbackI);
    }

    public static GLFWAllocateCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWAllocateCallback create(GLFWAllocateCallbackI gLFWAllocateCallbackI) {
        return gLFWAllocateCallbackI instanceof GLFWAllocateCallback ? (GLFWAllocateCallback) gLFWAllocateCallbackI : new Container(gLFWAllocateCallbackI.address(), gLFWAllocateCallbackI);
    }

    protected GLFWAllocateCallback() {
        super(CIF);
    }

    GLFWAllocateCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWAllocateCallback$Container.class */
    public static final class Container extends GLFWAllocateCallback {
        private final GLFWAllocateCallbackI delegate;

        Container(long j, GLFWAllocateCallbackI gLFWAllocateCallbackI) {
            super(j);
            this.delegate = gLFWAllocateCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWAllocateCallbackI
        public final long invoke(long j, long j2) {
            return this.delegate.invoke(j, j2);
        }
    }
}
