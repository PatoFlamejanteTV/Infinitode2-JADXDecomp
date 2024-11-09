package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWDeallocateCallback.class */
public abstract class GLFWDeallocateCallback extends Callback implements GLFWDeallocateCallbackI {
    public static GLFWDeallocateCallback create(long j) {
        GLFWDeallocateCallbackI gLFWDeallocateCallbackI = (GLFWDeallocateCallbackI) Callback.get(j);
        return gLFWDeallocateCallbackI instanceof GLFWDeallocateCallback ? (GLFWDeallocateCallback) gLFWDeallocateCallbackI : new Container(j, gLFWDeallocateCallbackI);
    }

    public static GLFWDeallocateCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWDeallocateCallback create(GLFWDeallocateCallbackI gLFWDeallocateCallbackI) {
        return gLFWDeallocateCallbackI instanceof GLFWDeallocateCallback ? (GLFWDeallocateCallback) gLFWDeallocateCallbackI : new Container(gLFWDeallocateCallbackI.address(), gLFWDeallocateCallbackI);
    }

    protected GLFWDeallocateCallback() {
        super(CIF);
    }

    GLFWDeallocateCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWDeallocateCallback$Container.class */
    public static final class Container extends GLFWDeallocateCallback {
        private final GLFWDeallocateCallbackI delegate;

        Container(long j, GLFWDeallocateCallbackI gLFWDeallocateCallbackI) {
            super(j);
            this.delegate = gLFWDeallocateCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWDeallocateCallbackI
        public final void invoke(long j, long j2) {
            this.delegate.invoke(j, j2);
        }
    }
}
