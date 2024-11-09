package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVShadingRateImage.class */
public class NVShadingRateImage {
    public static final int GL_SHADING_RATE_IMAGE_NV = 38243;
    public static final int GL_SHADING_RATE_NO_INVOCATIONS_NV = 38244;
    public static final int GL_SHADING_RATE_1_INVOCATION_PER_PIXEL_NV = 38245;
    public static final int GL_SHADING_RATE_1_INVOCATION_PER_1X2_PIXELS_NV = 38246;
    public static final int GL_SHADING_RATE_1_INVOCATION_PER_2X1_PIXELS_NV = 38247;
    public static final int GL_SHADING_RATE_1_INVOCATION_PER_2X2_PIXELS_NV = 38248;
    public static final int GL_SHADING_RATE_1_INVOCATION_PER_2X4_PIXELS_NV = 38249;
    public static final int GL_SHADING_RATE_1_INVOCATION_PER_4X2_PIXELS_NV = 38250;
    public static final int GL_SHADING_RATE_1_INVOCATION_PER_4X4_PIXELS_NV = 38251;
    public static final int GL_SHADING_RATE_2_INVOCATIONS_PER_PIXEL_NV = 38252;
    public static final int GL_SHADING_RATE_4_INVOCATIONS_PER_PIXEL_NV = 38253;
    public static final int GL_SHADING_RATE_8_INVOCATIONS_PER_PIXEL_NV = 38254;
    public static final int GL_SHADING_RATE_16_INVOCATIONS_PER_PIXEL_NV = 38255;
    public static final int GL_SHADING_RATE_IMAGE_BINDING_NV = 38235;
    public static final int GL_SHADING_RATE_IMAGE_TEXEL_WIDTH_NV = 38236;
    public static final int GL_SHADING_RATE_IMAGE_TEXEL_HEIGHT_NV = 38237;
    public static final int GL_SHADING_RATE_IMAGE_PALETTE_SIZE_NV = 38238;
    public static final int GL_MAX_COARSE_FRAGMENT_SAMPLES_NV = 38239;
    public static final int GL_SHADING_RATE_SAMPLE_ORDER_DEFAULT_NV = 38318;
    public static final int GL_SHADING_RATE_SAMPLE_ORDER_PIXEL_MAJOR_NV = 38319;
    public static final int GL_SHADING_RATE_SAMPLE_ORDER_SAMPLE_MAJOR_NV = 38320;

    public static native void glBindShadingRateImageNV(@NativeType("GLuint") int i);

    public static native void nglShadingRateImagePaletteNV(int i, int i2, int i3, long j);

    public static native void nglGetShadingRateImagePaletteNV(int i, int i2, long j);

    public static native void glShadingRateImageBarrierNV(@NativeType("GLboolean") boolean z);

    public static native void glShadingRateSampleOrderNV(@NativeType("GLenum") int i);

    public static native void nglShadingRateSampleOrderCustomNV(int i, int i2, long j);

    public static native void nglGetShadingRateSampleLocationivNV(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected NVShadingRateImage() {
        throw new UnsupportedOperationException();
    }

    public static void glShadingRateImagePaletteNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum const *") IntBuffer intBuffer) {
        nglShadingRateImagePaletteNV(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetShadingRateImagePaletteNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetShadingRateImagePaletteNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glShadingRateSampleOrderCustomNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglShadingRateSampleOrderCustomNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetShadingRateSampleLocationivNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglGetShadingRateSampleLocationivNV(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glShadingRateImagePaletteNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum const *") int[] iArr) {
        long j = GL.getICD().glShadingRateImagePaletteNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glGetShadingRateImagePaletteNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum *") int[] iArr) {
        long j = GL.getICD().glGetShadingRateImagePaletteNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glShadingRateSampleOrderCustomNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glShadingRateSampleOrderCustomNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetShadingRateSampleLocationivNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetShadingRateSampleLocationivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }
}
