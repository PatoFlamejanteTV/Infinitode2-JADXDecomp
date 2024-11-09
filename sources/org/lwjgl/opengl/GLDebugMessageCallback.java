package org.lwjgl.opengl;

import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLDebugMessageCallback.class */
public abstract class GLDebugMessageCallback extends Callback implements GLDebugMessageCallbackI {
    public static GLDebugMessageCallback create(long j) {
        GLDebugMessageCallbackI gLDebugMessageCallbackI = (GLDebugMessageCallbackI) Callback.get(j);
        return gLDebugMessageCallbackI instanceof GLDebugMessageCallback ? (GLDebugMessageCallback) gLDebugMessageCallbackI : new Container(j, gLDebugMessageCallbackI);
    }

    public static GLDebugMessageCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLDebugMessageCallback create(GLDebugMessageCallbackI gLDebugMessageCallbackI) {
        return gLDebugMessageCallbackI instanceof GLDebugMessageCallback ? (GLDebugMessageCallback) gLDebugMessageCallbackI : new Container(gLDebugMessageCallbackI.address(), gLDebugMessageCallbackI);
    }

    protected GLDebugMessageCallback() {
        super(CIF);
    }

    GLDebugMessageCallback(long j) {
        super(j);
    }

    public static String getMessage(int i, long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memByteBuffer(j, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLDebugMessageCallback$Container.class */
    public static final class Container extends GLDebugMessageCallback {
        private final GLDebugMessageCallbackI delegate;

        Container(long j, GLDebugMessageCallbackI gLDebugMessageCallbackI) {
            super(j);
            this.delegate = gLDebugMessageCallbackI;
        }

        @Override // org.lwjgl.opengl.GLDebugMessageCallbackI
        public final void invoke(int i, int i2, int i3, int i4, int i5, long j, long j2) {
            this.delegate.invoke(i, i2, i3, i4, i5, j, j2);
        }
    }
}
