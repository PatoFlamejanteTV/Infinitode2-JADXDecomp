package org.lwjgl.glfw;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWPreeditCandidateCallback.class */
public abstract class GLFWPreeditCandidateCallback extends Callback implements GLFWPreeditCandidateCallbackI {
    public static GLFWPreeditCandidateCallback create(long j) {
        GLFWPreeditCandidateCallbackI gLFWPreeditCandidateCallbackI = (GLFWPreeditCandidateCallbackI) Callback.get(j);
        return gLFWPreeditCandidateCallbackI instanceof GLFWPreeditCandidateCallback ? (GLFWPreeditCandidateCallback) gLFWPreeditCandidateCallbackI : new Container(j, gLFWPreeditCandidateCallbackI);
    }

    public static GLFWPreeditCandidateCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWPreeditCandidateCallback create(GLFWPreeditCandidateCallbackI gLFWPreeditCandidateCallbackI) {
        return gLFWPreeditCandidateCallbackI instanceof GLFWPreeditCandidateCallback ? (GLFWPreeditCandidateCallback) gLFWPreeditCandidateCallbackI : new Container(gLFWPreeditCandidateCallbackI.address(), gLFWPreeditCandidateCallbackI);
    }

    protected GLFWPreeditCandidateCallback() {
        super(CIF);
    }

    GLFWPreeditCandidateCallback(long j) {
        super(j);
    }

    public GLFWPreeditCandidateCallback set(long j) {
        GLFW.glfwSetPreeditCandidateCallback(j, this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWPreeditCandidateCallback$Container.class */
    public static final class Container extends GLFWPreeditCandidateCallback {
        private final GLFWPreeditCandidateCallbackI delegate;

        Container(long j, GLFWPreeditCandidateCallbackI gLFWPreeditCandidateCallbackI) {
            super(j);
            this.delegate = gLFWPreeditCandidateCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWPreeditCandidateCallbackI
        public final void invoke(long j, int i, int i2, int i3, int i4) {
            this.delegate.invoke(j, i, i2, i3, i4);
        }
    }
}
