package org.lwjgl.stb;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBImageResize.class */
public class STBImageResize {
    public static final int STBIR_1CHANNEL = 1;
    public static final int STBIR_2CHANNEL = 2;
    public static final int STBIR_RGB = 3;
    public static final int STBIR_BGR = 0;
    public static final int STBIR_4CHANNEL = 5;
    public static final int STBIR_RGBA = 4;
    public static final int STBIR_BGRA = 6;
    public static final int STBIR_ARGB = 7;
    public static final int STBIR_ABGR = 8;
    public static final int STBIR_RA = 9;
    public static final int STBIR_AR = 10;
    public static final int STBIR_RGBA_PM = 11;
    public static final int STBIR_BGRA_PM = 12;
    public static final int STBIR_ARGB_PM = 13;
    public static final int STBIR_ABGR_PM = 14;
    public static final int STBIR_RA_PM = 15;
    public static final int STBIR_AR_PM = 16;
    public static final int STBIR_RGBA_NO_AW = 11;
    public static final int STBIR_BGRA_NO_AW = 12;
    public static final int STBIR_ARGB_NO_AW = 13;
    public static final int STBIR_ABGR_NO_AW = 14;
    public static final int STBIR_RA_NO_AW = 15;
    public static final int STBIR_AR_NO_AW = 16;
    public static final int STBIR_EDGE_CLAMP = 0;
    public static final int STBIR_EDGE_REFLECT = 1;
    public static final int STBIR_EDGE_WRAP = 2;
    public static final int STBIR_EDGE_ZERO = 3;
    public static final int STBIR_FILTER_DEFAULT = 0;
    public static final int STBIR_FILTER_BOX = 1;
    public static final int STBIR_FILTER_TRIANGLE = 2;
    public static final int STBIR_FILTER_CUBICBSPLINE = 3;
    public static final int STBIR_FILTER_CATMULLROM = 4;
    public static final int STBIR_FILTER_MITCHELL = 5;
    public static final int STBIR_FILTER_POINT_SAMPLE = 6;
    public static final int STBIR_FILTER_OTHER = 7;
    public static final int STBIR_TYPE_UINT8 = 0;
    public static final int STBIR_TYPE_UINT8_SRGB = 1;
    public static final int STBIR_TYPE_UINT8_SRGB_ALPHA = 2;
    public static final int STBIR_TYPE_UINT16 = 3;
    public static final int STBIR_TYPE_FLOAT = 4;
    public static final int STBIR_TYPE_HALF_FLOAT = 5;
    private static final int[] stbir_pixel_layout_channels;
    private static final int[] stbir_type_size;

    public static native long nstbir_resize_uint8_srgb(long j, int i, int i2, int i3, long j2, int i4, int i5, int i6, int i7);

    public static native long nstbir_resize_uint8_linear(long j, int i, int i2, int i3, long j2, int i4, int i5, int i6, int i7);

    public static native long nstbir_resize_float_linear(long j, int i, int i2, int i3, long j2, int i4, int i5, int i6, int i7);

    public static native long nstbir_resize(long j, int i, int i2, int i3, long j2, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public static native void nstbir_resize_init(long j, long j2, int i, int i2, int i3, long j3, int i4, int i5, int i6, int i7, int i8);

    public static native void nstbir_set_datatypes(long j, int i, int i2);

    public static native void nstbir_set_pixel_callbacks(long j, long j2, long j3);

    public static native void nstbir_set_user_data(long j, long j2);

    public static native void nstbir_set_buffer_ptrs(long j, long j2, int i, long j3, int i2);

    public static native int nstbir_set_pixel_layouts(long j, int i, int i2);

    public static native int nstbir_set_edgemodes(long j, int i, int i2);

    public static native int nstbir_set_filters(long j, int i, int i2);

    public static native int nstbir_set_filter_callbacks(long j, long j2, long j3, long j4, long j5);

    public static native int nstbir_set_pixel_subrect(long j, int i, int i2, int i3, int i4);

    public static native int nstbir_set_input_subrect(long j, double d, double d2, double d3, double d4);

    public static native int nstbir_set_output_pixel_subrect(long j, int i, int i2, int i3, int i4);

    public static native int nstbir_set_non_pm_alpha_speed_over_quality(long j, int i);

    public static native int nstbir_build_samplers(long j);

    public static native void nstbir_free_samplers(long j);

    public static native int nstbir_resize_extended(long j);

    public static native int nstbir_build_samplers_with_splits(long j, int i);

    public static native int nstbir_resize_extended_split(long j, int i, int i2);

    static {
        LibSTB.initialize();
        stbir_pixel_layout_channels = new int[]{3, 1, 2, 3, 4, 4, 4, 4, 4, 2, 2, 4, 4, 4, 4, 2, 2};
        stbir_type_size = new int[]{1, 1, 1, 2, 4, 2};
    }

    protected STBImageResize() {
        throw new UnsupportedOperationException();
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbir_resize_uint8_srgb(@NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("unsigned char *") ByteBuffer byteBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7) {
        int calculateBufferSize = calculateBufferSize(i4, i5, i6, i7, 1);
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer2, calculateBufferSize);
        }
        return MemoryUtil.memByteBufferSafe(nstbir_resize_uint8_srgb(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddressSafe(byteBuffer2), i4, i5, i6, i7), calculateBufferSize);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbir_resize_uint8_srgb(@NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("unsigned char *") ByteBuffer byteBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7, long j) {
        if (Checks.CHECKS) {
            Checks.checkSafe(byteBuffer2, j);
        }
        return MemoryUtil.memByteBufferSafe(nstbir_resize_uint8_srgb(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddressSafe(byteBuffer2), i4, i5, i6, i7), (int) j);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbir_resize_uint8_linear(@NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("unsigned char *") ByteBuffer byteBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7) {
        int calculateBufferSize = calculateBufferSize(i4, i5, i6, i7, 1);
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer2, calculateBufferSize);
        }
        return MemoryUtil.memByteBufferSafe(nstbir_resize_uint8_linear(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddressSafe(byteBuffer2), i4, i5, i6, i7), calculateBufferSize);
    }

    @NativeType("unsigned char *")
    public static ByteBuffer stbir_resize_uint8_linear(@NativeType("unsigned char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("unsigned char *") ByteBuffer byteBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7, long j) {
        if (Checks.CHECKS) {
            Checks.checkSafe(byteBuffer2, j);
        }
        return MemoryUtil.memByteBufferSafe(nstbir_resize_uint8_linear(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddressSafe(byteBuffer2), i4, i5, i6, i7), (int) j);
    }

    @NativeType("float *")
    public static FloatBuffer stbir_resize_float_linear(@NativeType("float const *") FloatBuffer floatBuffer, int i, int i2, int i3, @NativeType("float *") FloatBuffer floatBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7) {
        int calculateBufferSize = calculateBufferSize(i4, i5, i6, i7, 4);
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) floatBuffer2, calculateBufferSize);
        }
        return MemoryUtil.memFloatBufferSafe(nstbir_resize_float_linear(MemoryUtil.memAddress(floatBuffer), i, i2, i3, MemoryUtil.memAddressSafe(floatBuffer2), i4, i5, i6, i7), calculateBufferSize);
    }

    @NativeType("float *")
    public static FloatBuffer stbir_resize_float_linear(@NativeType("float const *") FloatBuffer floatBuffer, int i, int i2, int i3, @NativeType("float *") FloatBuffer floatBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7, long j) {
        if (Checks.CHECKS) {
            Checks.checkSafe(floatBuffer2, j);
        }
        return MemoryUtil.memFloatBufferSafe(nstbir_resize_float_linear(MemoryUtil.memAddress(floatBuffer), i, i2, i3, MemoryUtil.memAddressSafe(floatBuffer2), i4, i5, i6, i7), (int) j);
    }

    @NativeType("void *")
    public static ByteBuffer stbir_resize(@NativeType("void const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("void *") ByteBuffer byteBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7, @NativeType("stbir_datatype") int i8, @NativeType("stbir_edge") int i9, @NativeType("stbir_filter") int i10) {
        int calculateBufferSize = calculateBufferSize(i4, i5, i6, i7, stbir_type_size[i8]);
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer2, calculateBufferSize);
        }
        return MemoryUtil.memByteBufferSafe(nstbir_resize(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddressSafe(byteBuffer2), i4, i5, i6, i7, i8, i9, i10), calculateBufferSize);
    }

    @NativeType("void *")
    public static ByteBuffer stbir_resize(@NativeType("void const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("void *") ByteBuffer byteBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7, @NativeType("stbir_datatype") int i8, @NativeType("stbir_edge") int i9, @NativeType("stbir_filter") int i10, long j) {
        if (Checks.CHECKS) {
            Checks.checkSafe(byteBuffer2, j);
        }
        return MemoryUtil.memByteBufferSafe(nstbir_resize(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddressSafe(byteBuffer2), i4, i5, i6, i7, i8, i9, i10), (int) j);
    }

    public static void stbir_resize_init(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("void const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("void *") ByteBuffer byteBuffer2, int i4, int i5, int i6, @NativeType("stbir_pixel_layout") int i7, @NativeType("stbir_datatype") int i8) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer2, calculateBufferSize(i4, i5, i6, i7, stbir_type_size[i8]));
        }
        nstbir_resize_init(stbir_resize.address(), MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddressSafe(byteBuffer2), i4, i5, i6, i7, i8);
    }

    public static void stbir_set_datatypes(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("stbir_datatype") int i, @NativeType("stbir_datatype") int i2) {
        nstbir_set_datatypes(stbir_resize.address(), i, i2);
    }

    public static void stbir_set_pixel_callbacks(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("stbir_input_callback *") STBIRInputCallbackI sTBIRInputCallbackI, @NativeType("stbir_output_callback *") STBIROutputCallbackI sTBIROutputCallbackI) {
        nstbir_set_pixel_callbacks(stbir_resize.address(), MemoryUtil.memAddressSafe(sTBIRInputCallbackI), MemoryUtil.memAddressSafe(sTBIROutputCallbackI));
    }

    public static void stbir_set_user_data(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("void *") long j) {
        nstbir_set_user_data(stbir_resize.address(), j);
    }

    public static void stbir_set_buffer_ptrs(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("void const *") ByteBuffer byteBuffer, int i, @NativeType("void *") ByteBuffer byteBuffer2, int i2) {
        nstbir_set_buffer_ptrs(stbir_resize.address(), MemoryUtil.memAddress(byteBuffer), i, MemoryUtil.memAddressSafe(byteBuffer2), i2);
    }

    public static int stbir_set_pixel_layouts(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("stbir_pixel_layout") int i, @NativeType("stbir_pixel_layout") int i2) {
        return nstbir_set_pixel_layouts(stbir_resize.address(), i, i2);
    }

    public static int stbir_set_edgemodes(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("stbir_edge") int i, @NativeType("stbir_edge") int i2) {
        return nstbir_set_edgemodes(stbir_resize.address(), i, i2);
    }

    public static int stbir_set_filters(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("stbir_filter") int i, @NativeType("stbir_filter") int i2) {
        return nstbir_set_filters(stbir_resize.address(), i, i2);
    }

    public static int stbir_set_filter_callbacks(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("stbir__kernel_callback *") STBIRKernelCallbackI sTBIRKernelCallbackI, @NativeType("stbir__support_callback *") STBIRSupportCallbackI sTBIRSupportCallbackI, @NativeType("stbir__kernel_callback *") STBIRKernelCallbackI sTBIRKernelCallbackI2, @NativeType("stbir__support_callback *") STBIRSupportCallbackI sTBIRSupportCallbackI2) {
        return nstbir_set_filter_callbacks(stbir_resize.address(), MemoryUtil.memAddressSafe(sTBIRKernelCallbackI), MemoryUtil.memAddressSafe(sTBIRSupportCallbackI), MemoryUtil.memAddressSafe(sTBIRKernelCallbackI2), MemoryUtil.memAddressSafe(sTBIRSupportCallbackI2));
    }

    public static int stbir_set_pixel_subrect(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, int i, int i2, int i3, int i4) {
        return nstbir_set_pixel_subrect(stbir_resize.address(), i, i2, i3, i4);
    }

    public static int stbir_set_input_subrect(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, double d, double d2, double d3, double d4) {
        return nstbir_set_input_subrect(stbir_resize.address(), d, d2, d3, d4);
    }

    public static int stbir_set_output_pixel_subrect(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, int i, int i2, int i3, int i4) {
        return nstbir_set_output_pixel_subrect(stbir_resize.address(), i, i2, i3, i4);
    }

    public static int stbir_set_non_pm_alpha_speed_over_quality(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, @NativeType("int") boolean z) {
        return nstbir_set_non_pm_alpha_speed_over_quality(stbir_resize.address(), z ? 1 : 0);
    }

    public static int stbir_build_samplers(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize) {
        return nstbir_build_samplers(stbir_resize.address());
    }

    public static void stbir_free_samplers(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize) {
        nstbir_free_samplers(stbir_resize.address());
    }

    public static int stbir_resize_extended(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize) {
        return nstbir_resize_extended(stbir_resize.address());
    }

    public static int stbir_build_samplers_with_splits(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, int i) {
        return nstbir_build_samplers_with_splits(stbir_resize.address(), i);
    }

    public static int stbir_resize_extended_split(@NativeType("STBIR_RESIZE *") STBIR_RESIZE stbir_resize, int i, int i2) {
        return nstbir_resize_extended_split(stbir_resize.address(), i, i2);
    }

    private static int calculateBufferSize(int i, int i2, int i3, int i4, int i5) {
        return i2 * (i3 == 0 ? i * stbir_pixel_layout_channels[i4] * i5 : i3);
    }
}
