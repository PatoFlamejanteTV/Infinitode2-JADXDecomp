package org.lwjgl.opengl;

import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLDebugMessageARBCallback.class */
public abstract class GLDebugMessageARBCallback extends Callback implements GLDebugMessageARBCallbackI {
    public static GLDebugMessageARBCallback create(long j) {
        GLDebugMessageARBCallbackI gLDebugMessageARBCallbackI = (GLDebugMessageARBCallbackI) Callback.get(j);
        return gLDebugMessageARBCallbackI instanceof GLDebugMessageARBCallback ? (GLDebugMessageARBCallback) gLDebugMessageARBCallbackI : new Container(j, gLDebugMessageARBCallbackI);
    }

    public static GLDebugMessageARBCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLDebugMessageARBCallback create(GLDebugMessageARBCallbackI gLDebugMessageARBCallbackI) {
        return gLDebugMessageARBCallbackI instanceof GLDebugMessageARBCallback ? (GLDebugMessageARBCallback) gLDebugMessageARBCallbackI : new Container(gLDebugMessageARBCallbackI.address(), gLDebugMessageARBCallbackI);
    }

    protected GLDebugMessageARBCallback() {
        super(CIF);
    }

    GLDebugMessageARBCallback(long j) {
        super(j);
    }

    public static String getMessage(int i, long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memByteBuffer(j, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLDebugMessageARBCallback$Container.class */
    public static final class Container extends GLDebugMessageARBCallback {
        private final GLDebugMessageARBCallbackI delegate;

        Container(long j, GLDebugMessageARBCallbackI gLDebugMessageARBCallbackI) {
            super(j);
            this.delegate = gLDebugMessageARBCallbackI;
        }

        @Override // org.lwjgl.opengl.GLDebugMessageARBCallbackI
        public final void invoke(int i, int i2, int i3, int i4, int i5, long j, long j2) {
            this.delegate.invoke(i, i2, i3, i4, i5, j, j2);
        }
    }
}
