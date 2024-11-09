package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLAMDGPUAssociation.class */
public class WGLAMDGPUAssociation {
    public static final int WGL_GPU_VENDOR_AMD = 7936;
    public static final int WGL_GPU_RENDERER_STRING_AMD = 7937;
    public static final int WGL_GPU_OPENGL_VERSION_STRING_AMD = 7938;
    public static final int WGL_GPU_FASTEST_TARGET_GPUS_AMD = 8610;
    public static final int WGL_GPU_RAM_AMD = 8611;
    public static final int WGL_GPU_CLOCK_AMD = 8612;
    public static final int WGL_GPU_NUM_PIPES_AMD = 8613;
    public static final int WGL_GPU_NUM_SIMD_AMD = 8614;
    public static final int WGL_GPU_NUM_RB_AMD = 8615;
    public static final int WGL_GPU_NUM_SPI_AMD = 8616;

    protected WGLAMDGPUAssociation() {
        throw new UnsupportedOperationException();
    }

    public static int nwglGetGPUIDsAMD(int i, long j) {
        long j2 = GL.getCapabilitiesWGL().wglGetGPUIDsAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPI(i, j, j2);
    }

    @NativeType("UINT")
    public static int wglGetGPUIDsAMD(@NativeType("UINT *") IntBuffer intBuffer) {
        return nwglGetGPUIDsAMD(Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static int nwglGetGPUInfoAMD(int i, int i2, int i3, int i4, long j) {
        long j2 = GL.getCapabilitiesWGL().wglGetGPUInfoAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPI(i, i2, i3, i4, j, j2);
    }

    public static int wglGetGPUInfoAMD(@NativeType("UINT") int i, int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        return nwglGetGPUInfoAMD(i, i2, i3, byteBuffer.remaining() >> GLChecks.typeToByteShift(i3), MemoryUtil.memAddress(byteBuffer));
    }

    public static int wglGetGPUInfoAMD(@NativeType("UINT") int i, int i2, @NativeType("GLenum") int i3, @NativeType("void *") IntBuffer intBuffer) {
        return nwglGetGPUInfoAMD(i, i2, i3, (int) ((intBuffer.remaining() << 2) >> GLChecks.typeToByteShift(i3)), MemoryUtil.memAddress(intBuffer));
    }

    public static int wglGetGPUInfoAMD(@NativeType("UINT") int i, int i2, @NativeType("GLenum") int i3, @NativeType("void *") FloatBuffer floatBuffer) {
        return nwglGetGPUInfoAMD(i, i2, i3, (int) ((floatBuffer.remaining() << 2) >> GLChecks.typeToByteShift(i3)), MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("UINT")
    public static int wglGetContextGPUIDAMD(@NativeType("HGLRC") long j) {
        long j2 = GL.getCapabilitiesWGL().wglGetContextGPUIDAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("HGLRC")
    public static long wglCreateAssociatedContextAMD(@NativeType("UINT") int i) {
        long j = GL.getCapabilitiesWGL().wglCreateAssociatedContextAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(i, j);
    }

    public static long nwglCreateAssociatedContextAttribsAMD(int i, long j, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglCreateAssociatedContextAttribsAMD;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        return JNI.callPPP(i, j, j2, j3);
    }

    @NativeType("HGLRC")
    public static long wglCreateAssociatedContextAttribsAMD(@NativeType("UINT") int i, @NativeType("HGLRC") long j, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nwglCreateAssociatedContextAttribsAMD(i, j, MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("BOOL")
    public static boolean wglDeleteAssociatedContextAMD(@NativeType("HGLRC") long j) {
        long j2 = GL.getCapabilitiesWGL().wglDeleteAssociatedContextAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglMakeAssociatedContextCurrentAMD(@NativeType("HGLRC") long j) {
        long j2 = GL.getCapabilitiesWGL().wglMakeAssociatedContextCurrentAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }

    @NativeType("HGLRC")
    public static long wglGetCurrentAssociatedContextAMD() {
        long j = GL.getCapabilitiesWGL().wglGetCurrentAssociatedContextAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }

    @NativeType("VOID")
    public static void wglBlitContextFramebufferAMD(@NativeType("HGLRC") long j, @NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLbitfield") int i9, @NativeType("GLenum") int i10) {
        long j2 = GL.getCapabilitiesWGL().wglBlitContextFramebufferAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.callPV(j, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j2);
    }

    @NativeType("UINT")
    public static int wglGetGPUIDsAMD(@NativeType("UINT *") int[] iArr) {
        long j = GL.getCapabilitiesWGL().wglGetGPUIDsAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(Checks.lengthSafe(iArr), iArr, j);
    }

    public static int wglGetGPUInfoAMD(@NativeType("UINT") int i, int i2, @NativeType("GLenum") int i3, @NativeType("void *") int[] iArr) {
        long j = GL.getCapabilitiesWGL().wglGetGPUInfoAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(i, i2, i3, iArr.length, iArr, j);
    }

    public static int wglGetGPUInfoAMD(@NativeType("UINT") int i, int i2, @NativeType("GLenum") int i3, @NativeType("void *") float[] fArr) {
        long j = GL.getCapabilitiesWGL().wglGetGPUInfoAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(i, i2, i3, fArr.length, fArr, j);
    }

    @NativeType("HGLRC")
    public static long wglCreateAssociatedContextAttribsAMD(@NativeType("UINT") int i, @NativeType("HGLRC") long j, @NativeType("int const *") int[] iArr) {
        long j2 = GL.getCapabilitiesWGL().wglCreateAssociatedContextAttribsAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPP(i, j, iArr, j2);
    }
}
