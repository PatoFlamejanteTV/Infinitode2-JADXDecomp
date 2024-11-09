package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBCLEvent.class */
public class ARBCLEvent {
    public static final int GL_SYNC_CL_EVENT_ARB = 33344;
    public static final int GL_SYNC_CL_EVENT_COMPLETE_ARB = 33345;

    public static native long nglCreateSyncFromCLeventARB(long j, long j2, int i);

    static {
        GL.initialize();
    }

    protected ARBCLEvent() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLsync")
    public static long glCreateSyncFromCLeventARB(@NativeType("cl_context") long j, @NativeType("cl_event") long j2, @NativeType("GLbitfield") int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return nglCreateSyncFromCLeventARB(j, j2, i);
    }
}
