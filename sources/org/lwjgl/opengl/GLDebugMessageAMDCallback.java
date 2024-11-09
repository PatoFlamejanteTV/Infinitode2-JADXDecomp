package org.lwjgl.opengl;

import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLDebugMessageAMDCallback.class */
public abstract class GLDebugMessageAMDCallback extends Callback implements GLDebugMessageAMDCallbackI {
    public static GLDebugMessageAMDCallback create(long j) {
        GLDebugMessageAMDCallbackI gLDebugMessageAMDCallbackI = (GLDebugMessageAMDCallbackI) Callback.get(j);
        return gLDebugMessageAMDCallbackI instanceof GLDebugMessageAMDCallback ? (GLDebugMessageAMDCallback) gLDebugMessageAMDCallbackI : new Container(j, gLDebugMessageAMDCallbackI);
    }

    public static GLDebugMessageAMDCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLDebugMessageAMDCallback create(GLDebugMessageAMDCallbackI gLDebugMessageAMDCallbackI) {
        return gLDebugMessageAMDCallbackI instanceof GLDebugMessageAMDCallback ? (GLDebugMessageAMDCallback) gLDebugMessageAMDCallbackI : new Container(gLDebugMessageAMDCallbackI.address(), gLDebugMessageAMDCallbackI);
    }

    protected GLDebugMessageAMDCallback() {
        super(CIF);
    }

    GLDebugMessageAMDCallback(long j) {
        super(j);
    }

    public static String getMessage(int i, long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memByteBuffer(j, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLDebugMessageAMDCallback$Container.class */
    public static final class Container extends GLDebugMessageAMDCallback {
        private final GLDebugMessageAMDCallbackI delegate;

        Container(long j, GLDebugMessageAMDCallbackI gLDebugMessageAMDCallbackI) {
            super(j);
            this.delegate = gLDebugMessageAMDCallbackI;
        }

        @Override // org.lwjgl.opengl.GLDebugMessageAMDCallbackI
        public final void invoke(int i, int i2, int i3, int i4, long j, long j2) {
            this.delegate.invoke(i, i2, i3, i4, j, j2);
        }
    }
}
