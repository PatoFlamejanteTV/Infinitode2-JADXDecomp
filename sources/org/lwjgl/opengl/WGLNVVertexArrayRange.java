package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLNVVertexArrayRange.class */
public class WGLNVVertexArrayRange {
    protected WGLNVVertexArrayRange() {
        throw new UnsupportedOperationException();
    }

    public static long nwglAllocateMemoryNV(int i, float f, float f2, float f3) {
        long j = GL.getCapabilitiesWGL().wglAllocateMemoryNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(i, f, f2, f3, j);
    }

    @NativeType("void *")
    public static ByteBuffer wglAllocateMemoryNV(@NativeType("GLsizei") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3) {
        return MemoryUtil.memByteBufferSafe(nwglAllocateMemoryNV(i, f, f2, f3), i);
    }

    public static void nwglFreeMemoryNV(long j) {
        long j2 = GL.getCapabilitiesWGL().wglFreeMemoryNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPV(j, j2);
    }

    public static void wglFreeMemoryNV(@NativeType("void *") ByteBuffer byteBuffer) {
        nwglFreeMemoryNV(MemoryUtil.memAddress(byteBuffer));
    }
}
