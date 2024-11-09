package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXAMDGPUAssociation.class */
public class GLXAMDGPUAssociation {
    public static final int GLX_GPU_VENDOR_AMD = 7936;
    public static final int GLX_GPU_RENDERER_STRING_AMD = 7937;
    public static final int GLX_GPU_OPENGL_VERSION_STRING_AMD = 7938;
    public static final int GLX_GPU_FASTEST_TARGET_GPUS_AMD = 8610;
    public static final int GLX_GPU_RAM_AMD = 8611;
    public static final int GLX_GPU_CLOCK_AMD = 8612;
    public static final int GLX_GPU_NUM_PIPES_AMD = 8613;
    public static final int GLX_GPU_NUM_SIMD_AMD = 8614;
    public static final int GLX_GPU_NUM_RB_AMD = 8615;
    public static final int GLX_GPU_NUM_SPI_AMD = 8616;

    protected GLXAMDGPUAssociation() {
        throw new UnsupportedOperationException();
    }

    public static void glXBlitContextFramebufferAMD(@NativeType("GLXContext") long j, @NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLbitfield") int i9, @NativeType("GLenum") int i10) {
        long j2 = GL.getCapabilitiesGLXClient().glXBlitContextFramebufferAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.callPV(j, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j2);
    }

    @NativeType("GLXContext")
    public static long glXCreateAssociatedContextAMD(@NativeType("unsigned int") int i, @NativeType("GLXContext") long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXCreateAssociatedContextAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPP(i, j, j2);
    }

    public static long nglXCreateAssociatedContextAttribsAMD(int i, long j, long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXCreateAssociatedContextAttribsAMD;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        return JNI.callPPP(i, j, j2, j3);
    }

    @NativeType("GLXContext")
    public static long glXCreateAssociatedContextAttribsAMD(@NativeType("unsigned int") int i, @NativeType("GLXContext") long j, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT(intBuffer);
        }
        return nglXCreateAssociatedContextAttribsAMD(i, j, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("Bool")
    public static boolean glXDeleteAssociatedContextAMD(@NativeType("GLXContext") long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXDeleteAssociatedContextAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }

    @NativeType("unsigned int")
    public static int glXGetContextGPUIDAMD(@NativeType("GLXContext") long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetContextGPUIDAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("GLXContext")
    public static long glXGetCurrentAssociatedContextAMD() {
        long j = GL.getCapabilitiesGLXClient().glXGetCurrentAssociatedContextAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }

    public static int nglXGetGPUIDsAMD(int i, long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetGPUIDsAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPI(i, j, j2);
    }

    @NativeType("unsigned int")
    public static int glXGetGPUIDsAMD(@NativeType("unsigned int *") IntBuffer intBuffer) {
        return nglXGetGPUIDsAMD(Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static int nglXGetGPUInfoAMD(int i, int i2, int i3, int i4, long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetGPUInfoAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPI(i, i2, i3, i4, j, j2);
    }

    public static int glXGetGPUInfoAMD(@NativeType("unsigned int") int i, int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        return nglXGetGPUInfoAMD(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("Bool")
    public static boolean glXMakeAssociatedContextCurrentAMD(@NativeType("GLXContext") long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXMakeAssociatedContextCurrentAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }

    @NativeType("GLXContext")
    public static long glXCreateAssociatedContextAttribsAMD(@NativeType("unsigned int") int i, @NativeType("GLXContext") long j, @NativeType("int const *") int[] iArr) {
        long j2 = GL.getCapabilitiesGLXClient().glXCreateAssociatedContextAttribsAMD;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.checkNT(iArr);
        }
        return JNI.callPPP(i, j, iArr, j2);
    }

    @NativeType("unsigned int")
    public static int glXGetGPUIDsAMD(@NativeType("unsigned int *") int[] iArr) {
        long j = GL.getCapabilitiesGLXClient().glXGetGPUIDsAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(Checks.lengthSafe(iArr), iArr, j);
    }
}
