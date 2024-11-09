package org.lwjgl.stb;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBImage.class */
public class STBImage {
    public static final int STBI_default = 0;
    public static final int STBI_grey = 1;
    public static final int STBI_grey_alpha = 2;
    public static final int STBI_rgb = 3;
    public static final int STBI_rgb_alpha = 4;

    public static native long nstbi_load(long j, long j2, long j3, long j4, int i);

    public static native long nstbi_load_from_memory(long j, int i, long j2, long j3, long j4, int i2);

    public static native long nstbi_load_from_callbacks(long j, long j2, long j3, long j4, long j5, int i);

    public static native long nstbi_load_gif_from_memory(long j, int i, long j2, long j3, long j4, long j5, long j6, int i2);

    public static native long nstbi_load_16(long j, long j2, long j3, long j4, int i);

    public static native long nstbi_load_16_from_memory(long j, int i, long j2, long j3, long j4, int i2);

    public static native long nstbi_load_16_from_callbacks(long j, long j2, long j3, long j4, long j5, int i);

    public static native long nstbi_loadf(long j, long j2, long j3, long j4, int i);

    public static native long nstbi_loadf_from_memory(long j, int i, long j2, long j3, long j4, int i2);

    public static native long nstbi_loadf_from_callbacks(long j, long j2, long j3, long j4, long j5, int i);

    public static native void stbi_hdr_to_ldr_gamma(float f);

    public static native void stbi_hdr_to_ldr_scale(float f);

    public static native void stbi_ldr_to_hdr_gamma(float f);

    public static native void stbi_ldr_to_hdr_scale(float f);

    public static native int nstbi_is_hdr(long j);

    public static native int nstbi_is_hdr_from_memory(long j, int i);

    public static native int nstbi_is_hdr_from_callbacks(long j, long j2);

    public static native long nstbi_failure_reason();

    public static native void nstbi_image_free(long j);

    public static native int nstbi_info(long j, long j2, long j3, long j4);

    public static native int nstbi_info_from_memory(long j, int i, long j2, long j3, long j4);

    public static native int nstbi_info_from_callbacks(long j, long j2, long j3, long j4, long j5);

    public static native int nstbi_is_16_bit(long j);

    public static native int nstbi_is_16_bit_from_memory(long j, int i);

    public static native int nstbi_is_16_bit_from_callbacks(long j, long j2);

    public static native void nstbi_set_unpremultiply_on_load(int i);

    public static native void nstbi_convert_iphone_png_to_rgb(int i);

    public static native void nstbi_set_flip_vertically_on_load(int i);

    public static native void nstbi_set_unpremultiply_on_load_thread(int i);

    public static native void nstbi_convert_iphone_png_to_rgb_thread(int i);

    public static native void stbi_set_flip_vertically_on_load_thread(int i);

    public static native long nstbi_zlib_decode_malloc_guesssize(long j, int i, int i2, long j2);

    public static native long nstbi_zlib_decode_malloc_guesssize_headerflag(long j, int i, int i2, long j2, int i3);

    public static native long nstbi_zlib_decode_malloc(long j, int i, long j2);

    public static native int nstbi_zlib_decode_buffer(long j, int i, long j2, int i2);

    public static native long nstbi_zlib_decode_noheader_malloc(long j, int i, long j2);

    public static native int nstbi_zlib_decode_noheader_buffer(long j, int i, long j2, int i2);

    public static native long nstbi_load(long j, int[] iArr, int[] iArr2, int[] iArr3, int i);

    public static native long nstbi_load_from_memory(long j, int i, int[] iArr, int[] iArr2, int[] iArr3, int i2);

    public static native long nstbi_load_from_callbacks(long j, long j2, int[] iArr, int[] iArr2, int[] iArr3, int i);

    public static native long nstbi_load_gif_from_memory(long j, int i, long j2, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int i2);

    public static native long nstbi_load_16(long j, int[] iArr, int[] iArr2, int[] iArr3, int i);

    public static native long nstbi_load_16_from_memory(long j, int i, int[] iArr, int[] iArr2, int[] iArr3, int i2);

    public static native long nstbi_load_16_from_callbacks(long j, long j2, int[] iArr, int[] iArr2, int[] iArr3, int i);

    public static native long nstbi_loadf(long j, int[] iArr, int[] iArr2, int[] iArr3, int i);

    public static native long nstbi_loadf_from_memory(long j, int i, int[] iArr, int[] iArr2, int[] iArr3, int i2);

    public static native long nstbi_loadf_from_callbacks(long j, long j2, int[] iArr, int[] iArr2, int[] iArr3, int i);

    public static native int nstbi_info(long j, int[] iArr, int[] iArr2, int[] iArr3);

    public static native int nstbi_info_from_memory(long j, int i, int[] iArr, int[] iArr2, int[] iArr3);

    public static native int nstbi_info_from_callbacks(long j, long j2, int[] iArr, int[] iArr2, int[] iArr3);

    static {
        LibSTB.initialize();
    }

    protected STBImage() {
        throw new UnsupportedOperationException();
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return MemoryUtil.memByteBufferSafe(nstbi_load(stackGet.getPointerAddress(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load_from_callbacks(sTBIIOCallbacks.address(), j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load_gif_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int **") PointerBuffer pointerBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4, int i) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            Checks.check((Buffer) intBuffer4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load_gif_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(intBuffer4), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * intBuffer3.get(intBuffer3.position()) * (i != 0 ? i : intBuffer4.get(intBuffer4.position())));
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return MemoryUtil.memShortBufferSafe(nstbi_load_16(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return MemoryUtil.memShortBufferSafe(nstbi_load_16(stackGet.getPointerAddress(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return MemoryUtil.memShortBufferSafe(nstbi_load_16_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return MemoryUtil.memShortBufferSafe(nstbi_load_16_from_callbacks(sTBIIOCallbacks.address(), j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return MemoryUtil.memFloatBufferSafe(nstbi_loadf(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return MemoryUtil.memFloatBufferSafe(nstbi_loadf(stackGet.getPointerAddress(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return MemoryUtil.memFloatBufferSafe(nstbi_loadf_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, int i) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return MemoryUtil.memFloatBufferSafe(nstbi_loadf_from_callbacks(sTBIIOCallbacks.address(), j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), i), intBuffer.get(intBuffer.position()) * intBuffer2.get(intBuffer2.position()) * (i != 0 ? i : intBuffer3.get(intBuffer3.position())));
    }

    @NativeType("int")
    public static boolean stbi_is_hdr(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nstbi_is_hdr(MemoryUtil.memAddress(byteBuffer)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_is_hdr(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_is_hdr(stackGet.getPointerAddress()) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_is_hdr_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer) {
        return nstbi_is_hdr_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining()) != 0;
    }

    @NativeType("int")
    public static boolean stbi_is_hdr_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j) {
        if (Checks.CHECKS) {
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return nstbi_is_hdr_from_callbacks(sTBIIOCallbacks.address(), j) != 0;
    }

    @NativeType("char const *")
    public static String stbi_failure_reason() {
        return MemoryUtil.memASCIISafe(nstbi_failure_reason());
    }

    public static void stbi_image_free(@NativeType("void *") ByteBuffer byteBuffer) {
        nstbi_image_free(MemoryUtil.memAddress(byteBuffer));
    }

    public static void stbi_image_free(@NativeType("void *") ShortBuffer shortBuffer) {
        nstbi_image_free(MemoryUtil.memAddress(shortBuffer));
    }

    public static void stbi_image_free(@NativeType("void *") FloatBuffer floatBuffer) {
        nstbi_image_free(MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("int")
    public static boolean stbi_info(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return nstbi_info(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_info(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_info(stackGet.getPointerAddress(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3)) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_info_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        return nstbi_info_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_info_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return nstbi_info_from_callbacks(sTBIIOCallbacks.address(), j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_is_16_bit(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nstbi_is_16_bit(MemoryUtil.memAddress(byteBuffer)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_is_16_bit(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_is_16_bit(stackGet.getPointerAddress()) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_is_16_bit_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer) {
        return nstbi_is_16_bit_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining()) != 0;
    }

    @NativeType("int")
    public static boolean stbi_is_16_bit_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j) {
        if (Checks.CHECKS) {
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return nstbi_is_16_bit_from_callbacks(sTBIIOCallbacks.address(), j) != 0;
    }

    public static void stbi_set_unpremultiply_on_load(@NativeType("int") boolean z) {
        nstbi_set_unpremultiply_on_load(z ? 1 : 0);
    }

    public static void stbi_convert_iphone_png_to_rgb(@NativeType("int") boolean z) {
        nstbi_convert_iphone_png_to_rgb(z ? 1 : 0);
    }

    public static void stbi_set_flip_vertically_on_load(@NativeType("int") boolean z) {
        nstbi_set_flip_vertically_on_load(z ? 1 : 0);
    }

    public static void stbi_set_unpremultiply_on_load_thread(@NativeType("int") boolean z) {
        nstbi_set_unpremultiply_on_load_thread(z ? 1 : 0);
    }

    public static void stbi_convert_iphone_png_to_rgb_thread(@NativeType("int") boolean z) {
        nstbi_convert_iphone_png_to_rgb_thread(z ? 1 : 0);
    }

    @NativeType("char *")
    public static ByteBuffer stbi_zlib_decode_malloc_guesssize(@NativeType("char const *") ByteBuffer byteBuffer, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            return MemoryUtil.memByteBufferSafe(nstbi_zlib_decode_malloc_guesssize(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("char *")
    public static ByteBuffer stbi_zlib_decode_malloc_guesssize_headerflag(@NativeType("char const *") ByteBuffer byteBuffer, int i, @NativeType("int") boolean z) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            return MemoryUtil.memByteBufferSafe(nstbi_zlib_decode_malloc_guesssize_headerflag(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i, MemoryUtil.memAddress(callocInt), z ? 1 : 0), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("char *")
    public static ByteBuffer stbi_zlib_decode_malloc(@NativeType("char const *") ByteBuffer byteBuffer) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            return MemoryUtil.memByteBufferSafe(nstbi_zlib_decode_malloc(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stbi_zlib_decode_buffer(@NativeType("char *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2) {
        return nstbi_zlib_decode_buffer(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer2), byteBuffer2.remaining());
    }

    @NativeType("char *")
    public static ByteBuffer stbi_zlib_decode_noheader_malloc(@NativeType("char const *") ByteBuffer byteBuffer) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            return MemoryUtil.memByteBufferSafe(nstbi_zlib_decode_noheader_malloc(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int stbi_zlib_decode_noheader_buffer(@NativeType("char *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2) {
        return nstbi_zlib_decode_noheader_buffer(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer2), byteBuffer2.remaining());
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load(MemoryUtil.memAddress(byteBuffer), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return MemoryUtil.memByteBufferSafe(nstbi_load(stackGet.getPointerAddress(), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load_from_callbacks(sTBIIOCallbacks.address(), j, iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("stbi_uc *")
    public static ByteBuffer stbi_load_gif_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int **") PointerBuffer pointerBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4, int i) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            Checks.check(iArr4, 1);
        }
        return MemoryUtil.memByteBufferSafe(nstbi_load_gif_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), iArr, iArr2, iArr3, iArr4, i), iArr[0] * iArr2[0] * iArr3[0] * (i != 0 ? i : iArr4[0]));
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return MemoryUtil.memShortBufferSafe(nstbi_load_16(MemoryUtil.memAddress(byteBuffer), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return MemoryUtil.memShortBufferSafe(nstbi_load_16(stackGet.getPointerAddress(), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return MemoryUtil.memShortBufferSafe(nstbi_load_16_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("stbi_us *")
    public static ShortBuffer stbi_load_16_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return MemoryUtil.memShortBufferSafe(nstbi_load_16_from_callbacks(sTBIIOCallbacks.address(), j, iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return MemoryUtil.memFloatBufferSafe(nstbi_loadf(MemoryUtil.memAddress(byteBuffer), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return MemoryUtil.memFloatBufferSafe(nstbi_loadf(stackGet.getPointerAddress(), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return MemoryUtil.memFloatBufferSafe(nstbi_loadf_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("float *")
    public static FloatBuffer stbi_loadf_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, int i) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return MemoryUtil.memFloatBufferSafe(nstbi_loadf_from_callbacks(sTBIIOCallbacks.address(), j, iArr, iArr2, iArr3, i), iArr[0] * iArr2[0] * (i != 0 ? i : iArr3[0]));
    }

    @NativeType("int")
    public static boolean stbi_info(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return nstbi_info(MemoryUtil.memAddress(byteBuffer), iArr, iArr2, iArr3) != 0;
    }

    @NativeType("int")
    public static boolean stbi_info(@NativeType("char const *") CharSequence charSequence, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_info(stackGet.getPointerAddress(), iArr, iArr2, iArr3) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_info_from_memory(@NativeType("stbi_uc const *") ByteBuffer byteBuffer, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        return nstbi_info_from_memory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), iArr, iArr2, iArr3) != 0;
    }

    @NativeType("int")
    public static boolean stbi_info_from_callbacks(@NativeType("stbi_io_callbacks const *") STBIIOCallbacks sTBIIOCallbacks, @NativeType("void *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3) {
        if (Checks.CHECKS) {
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
            STBIIOCallbacks.validate(sTBIIOCallbacks.address());
        }
        return nstbi_info_from_callbacks(sTBIIOCallbacks.address(), j, iArr, iArr2, iArr3) != 0;
    }
}
