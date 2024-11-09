package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWReallocateCallback.class */
public abstract class GLFWReallocateCallback extends Callback implements GLFWReallocateCallbackI {
    public static GLFWReallocateCallback create(long j) {
        GLFWReallocateCallbackI gLFWReallocateCallbackI = (GLFWReallocateCallbackI) Callback.get(j);
        return gLFWReallocateCallbackI instanceof GLFWReallocateCallback ? (GLFWReallocateCallback) gLFWReallocateCallbackI : new Container(j, gLFWReallocateCallbackI);
    }

    public static GLFWReallocateCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWReallocateCallback create(GLFWReallocateCallbackI gLFWReallocateCallbackI) {
        return gLFWReallocateCallbackI instanceof GLFWReallocateCallback ? (GLFWReallocateCallback) gLFWReallocateCallbackI : new Container(gLFWReallocateCallbackI.address(), gLFWReallocateCallbackI);
    }

    protected GLFWReallocateCallback() {
        super(CIF);
    }

    GLFWReallocateCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWReallocateCallback$Container.class */
    public static final class Container extends GLFWReallocateCallback {
        private final GLFWReallocateCallbackI delegate;

        Container(long j, GLFWReallocateCallbackI gLFWReallocateCallbackI) {
            super(j);
            this.delegate = gLFWReallocateCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWReallocateCallbackI
        public final long invoke(long j, long j2, long j3) {
            return this.delegate.invoke(j, j2, j3);
        }
    }
}
