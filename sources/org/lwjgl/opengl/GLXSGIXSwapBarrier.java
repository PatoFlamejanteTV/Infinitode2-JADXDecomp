package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXSGIXSwapBarrier.class */
public class GLXSGIXSwapBarrier {
    protected GLXSGIXSwapBarrier() {
        throw new UnsupportedOperationException();
    }

    public static void glXBindSwapBarrierSGIX(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, int i) {
        long j3 = GL.getCapabilitiesGLXClient().glXBindSwapBarrierSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, i, j3);
    }

    public static int nglXQueryMaxSwapBarriersSGIX(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXQueryMaxSwapBarriersSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("Bool")
    public static boolean glXQueryMaxSwapBarriersSGIX(@NativeType("Display *") long j, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXQueryMaxSwapBarriersSGIX(j, i, MemoryUtil.memAddress(intBuffer)) != 0;
    }

    @NativeType("Bool")
    public static boolean glXQueryMaxSwapBarriersSGIX(@NativeType("Display *") long j, int i, @NativeType("int *") int[] iArr) {
        long j2 = GL.getCapabilitiesGLXClient().glXQueryMaxSwapBarriersSGIX;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPPI(j, i, iArr, j2) != 0;
    }
}
