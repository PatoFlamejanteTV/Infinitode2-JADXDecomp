package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLARBPixelFormat.class */
public class WGLARBPixelFormat {
    public static final int WGL_NUMBER_PIXEL_FORMATS_ARB = 8192;
    public static final int WGL_DRAW_TO_WINDOW_ARB = 8193;
    public static final int WGL_DRAW_TO_BITMAP_ARB = 8194;
    public static final int WGL_ACCELERATION_ARB = 8195;
    public static final int WGL_NEED_PALETTE_ARB = 8196;
    public static final int WGL_NEED_SYSTEM_PALETTE_ARB = 8197;
    public static final int WGL_SWAP_LAYER_BUFFERS_ARB = 8198;
    public static final int WGL_SWAP_METHOD_ARB = 8199;
    public static final int WGL_NUMBER_OVERLAYS_ARB = 8200;
    public static final int WGL_NUMBER_UNDERLAYS_ARB = 8201;
    public static final int WGL_TRANSPARENT_ARB = 8202;
    public static final int WGL_TRANSPARENT_RED_VALUE_ARB = 8247;
    public static final int WGL_TRANSPARENT_GREEN_VALUE_ARB = 8248;
    public static final int WGL_TRANSPARENT_BLUE_VALUE_ARB = 8249;
    public static final int WGL_TRANSPARENT_ALPHA_VALUE_ARB = 8250;
    public static final int WGL_TRANSPARENT_INDEX_VALUE_ARB = 8251;
    public static final int WGL_SHARE_DEPTH_ARB = 8204;
    public static final int WGL_SHARE_STENCIL_ARB = 8205;
    public static final int WGL_SHARE_ACCUM_ARB = 8206;
    public static final int WGL_SUPPORT_GDI_ARB = 8207;
    public static final int WGL_SUPPORT_OPENGL_ARB = 8208;
    public static final int WGL_DOUBLE_BUFFER_ARB = 8209;
    public static final int WGL_STEREO_ARB = 8210;
    public static final int WGL_PIXEL_TYPE_ARB = 8211;
    public static final int WGL_COLOR_BITS_ARB = 8212;
    public static final int WGL_RED_BITS_ARB = 8213;
    public static final int WGL_RED_SHIFT_ARB = 8214;
    public static final int WGL_GREEN_BITS_ARB = 8215;
    public static final int WGL_GREEN_SHIFT_ARB = 8216;
    public static final int WGL_BLUE_BITS_ARB = 8217;
    public static final int WGL_BLUE_SHIFT_ARB = 8218;
    public static final int WGL_ALPHA_BITS_ARB = 8219;
    public static final int WGL_ALPHA_SHIFT_ARB = 8220;
    public static final int WGL_ACCUM_BITS_ARB = 8221;
    public static final int WGL_ACCUM_RED_BITS_ARB = 8222;
    public static final int WGL_ACCUM_GREEN_BITS_ARB = 8223;
    public static final int WGL_ACCUM_BLUE_BITS_ARB = 8224;
    public static final int WGL_ACCUM_ALPHA_BITS_ARB = 8225;
    public static final int WGL_DEPTH_BITS_ARB = 8226;
    public static final int WGL_STENCIL_BITS_ARB = 8227;
    public static final int WGL_AUX_BUFFERS_ARB = 8228;
    public static final int WGL_NO_ACCELERATION_ARB = 8229;
    public static final int WGL_GENERIC_ACCELERATION_ARB = 8230;
    public static final int WGL_FULL_ACCELERATION_ARB = 8231;
    public static final int WGL_SWAP_EXCHANGE_ARB = 8232;
    public static final int WGL_SWAP_COPY_ARB = 8233;
    public static final int WGL_SWAP_UNDEFINED_ARB = 8234;
    public static final int WGL_TYPE_RGBA_ARB = 8235;
    public static final int WGL_TYPE_COLORINDEX_ARB = 8236;

    protected WGLARBPixelFormat() {
        throw new UnsupportedOperationException();
    }

    public static int nwglGetPixelFormatAttribivARB(long j, int i, int i2, int i3, long j2, long j3) {
        long j4 = GL.getCapabilitiesWGL().wglGetPixelFormatAttribivARB;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPI(j, i, i2, i3, j2, j3, j4);
    }

    @NativeType("BOOL")
    public static boolean wglGetPixelFormatAttribivARB(@NativeType("HDC") long j, int i, int i2, @NativeType("int const *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer2, intBuffer.remaining());
        }
        return nwglGetPixelFormatAttribivARB(j, i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglGetPixelFormatAttribiARB(@NativeType("HDC") long j, int i, int i2, @NativeType("int const *") int i3, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            return nwglGetPixelFormatAttribivARB(j, i, i2, 1, MemoryUtil.memAddress(stackGet.ints(i3)), MemoryUtil.memAddress(intBuffer)) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nwglGetPixelFormatAttribfvARB(long j, int i, int i2, int i3, long j2, long j3) {
        long j4 = GL.getCapabilitiesWGL().wglGetPixelFormatAttribfvARB;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPI(j, i, i2, i3, j2, j3, j4);
    }

    @NativeType("BOOL")
    public static boolean wglGetPixelFormatAttribfvARB(@NativeType("HDC") long j, int i, int i2, @NativeType("int const *") IntBuffer intBuffer, @NativeType("FLOAT *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, intBuffer.remaining());
        }
        return nwglGetPixelFormatAttribfvARB(j, i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(floatBuffer)) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglGetPixelFormatAttribfARB(@NativeType("HDC") long j, int i, int i2, @NativeType("int const *") int i3, @NativeType("FLOAT *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            return nwglGetPixelFormatAttribfvARB(j, i, i2, 1, MemoryUtil.memAddress(stackGet.ints(i3)), MemoryUtil.memAddress(floatBuffer)) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nwglChoosePixelFormatARB(long j, long j2, long j3, int i, long j4, long j5) {
        long j6 = GL.getCapabilitiesWGL().wglChoosePixelFormatARB;
        if (Checks.CHECKS) {
            Checks.check(j6);
            Checks.check(j);
        }
        return JNI.callPPPPPI(j, j2, j3, i, j4, j5, j6);
    }

    @NativeType("BOOL")
    public static boolean wglChoosePixelFormatARB(@NativeType("HDC") long j, @NativeType("int const *") IntBuffer intBuffer, @NativeType("FLOAT const *") FloatBuffer floatBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("UINT *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
            Checks.checkNTSafe(floatBuffer);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return nwglChoosePixelFormatARB(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(floatBuffer), intBuffer2.remaining(), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3)) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglGetPixelFormatAttribivARB(@NativeType("HDC") long j, int i, int i2, @NativeType("int const *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = GL.getCapabilitiesWGL().wglGetPixelFormatAttribivARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(iArr2, iArr.length);
        }
        return JNI.callPPPI(j, i, i2, iArr.length, iArr, iArr2, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglGetPixelFormatAttribfvARB(@NativeType("HDC") long j, int i, int i2, @NativeType("int const *") int[] iArr, @NativeType("FLOAT *") float[] fArr) {
        long j2 = GL.getCapabilitiesWGL().wglGetPixelFormatAttribfvARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.check(fArr, iArr.length);
        }
        return JNI.callPPPI(j, i, i2, iArr.length, iArr, fArr, j2) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglChoosePixelFormatARB(@NativeType("HDC") long j, @NativeType("int const *") int[] iArr, @NativeType("FLOAT const *") float[] fArr, @NativeType("int *") int[] iArr2, @NativeType("UINT *") int[] iArr3) {
        long j2 = GL.getCapabilitiesWGL().wglChoosePixelFormatARB;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
            Checks.checkNTSafe(fArr);
            Checks.check(iArr3, 1);
        }
        return JNI.callPPPPPI(j, iArr, fArr, iArr2.length, iArr2, iArr3, j2) != 0;
    }
}
