package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXSGIVideoSync.class */
public class GLXSGIVideoSync {
    protected GLXSGIVideoSync() {
        throw new UnsupportedOperationException();
    }

    public static int nglXGetVideoSyncSGI(long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetVideoSyncSGI;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("GLint")
    public static int glXGetVideoSyncSGI(@NativeType("unsigned int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXGetVideoSyncSGI(MemoryUtil.memAddress(intBuffer));
    }

    public static int nglXWaitVideoSyncSGI(int i, int i2, long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXWaitVideoSyncSGI;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPI(i, i2, j, j2);
    }

    @NativeType("GLint")
    public static int glXWaitVideoSyncSGI(int i, int i2, @NativeType("unsigned int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXWaitVideoSyncSGI(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("GLint")
    public static int glXGetVideoSyncSGI(@NativeType("unsigned int *") int[] iArr) {
        long j = GL.getCapabilitiesGLXClient().glXGetVideoSyncSGI;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPI(iArr, j);
    }

    @NativeType("GLint")
    public static int glXWaitVideoSyncSGI(int i, int i2, @NativeType("unsigned int *") int[] iArr) {
        long j = GL.getCapabilitiesGLXClient().glXWaitVideoSyncSGI;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        return JNI.callPI(i, i2, iArr, j);
    }
}
