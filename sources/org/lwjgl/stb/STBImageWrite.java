package org.lwjgl.stb;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBImageWrite.class */
public class STBImageWrite {
    public static final IntBuffer stbi_write_png_compression_level;
    public static final IntBuffer stbi_write_force_png_filter;
    public static final PointerBuffer stbi_zlib_compress;
    public static final IntBuffer stbi_write_tga_with_rle;

    public static native int nstbi_write_png(long j, int i, int i2, int i3, long j2, int i4);

    private static native long nstbi_write_png_compression_level();

    private static native long nstbi_write_force_png_filter();

    private static native long nstbi_zlib_compress();

    public static native int nstbi_write_bmp(long j, int i, int i2, int i3, long j2);

    public static native int nstbi_write_tga(long j, int i, int i2, int i3, long j2);

    private static native long nstbi_write_tga_with_rle();

    public static native int nstbi_write_hdr(long j, int i, int i2, int i3, long j2);

    public static native int nstbi_write_jpg(long j, int i, int i2, int i3, long j2, int i4);

    public static native int nstbi_write_png_to_func(long j, long j2, int i, int i2, int i3, long j3, int i4);

    public static native int nstbi_write_bmp_to_func(long j, long j2, int i, int i2, int i3, long j3);

    public static native int nstbi_write_tga_to_func(long j, long j2, int i, int i2, int i3, long j3);

    public static native int nstbi_write_hdr_to_func(long j, long j2, int i, int i2, int i3, long j3);

    public static native int nstbi_write_jpg_to_func(long j, long j2, int i, int i2, int i3, long j3, int i4);

    public static native void nstbi_flip_vertically_on_write(int i);

    public static native int nstbi_write_hdr(long j, int i, int i2, int i3, float[] fArr);

    public static native int nstbi_write_hdr_to_func(long j, long j2, int i, int i2, int i3, float[] fArr);

    static {
        LibSTB.initialize();
        stbi_write_png_compression_level = stbi_write_png_compression_level();
        stbi_write_force_png_filter = stbi_write_force_png_filter();
        stbi_zlib_compress = stbi_zlib_compress();
        stbi_write_tga_with_rle = stbi_write_tga_with_rle();
    }

    protected STBImageWrite() {
        throw new UnsupportedOperationException();
    }

    @NativeType("int")
    public static boolean stbi_write_png(@NativeType("char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer2, int i4) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) byteBuffer2, (i4 != 0 ? i4 : i * i3) * i2);
        }
        return nstbi_write_png(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddress(byteBuffer2), i4) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_png(@NativeType("char const *") CharSequence charSequence, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i4 != 0 ? i4 : i * i3) * i2);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_write_png(stackGet.getPointerAddress(), i, i2, i3, MemoryUtil.memAddress(byteBuffer), i4) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int *")
    private static IntBuffer stbi_write_png_compression_level() {
        return MemoryUtil.memIntBuffer(nstbi_write_png_compression_level(), 1);
    }

    @NativeType("int *")
    private static IntBuffer stbi_write_force_png_filter() {
        return MemoryUtil.memIntBuffer(nstbi_write_force_png_filter(), 1);
    }

    @NativeType("unsigned char * (*) (unsigned char *, int, int *, int) *")
    private static PointerBuffer stbi_zlib_compress() {
        return MemoryUtil.memPointerBuffer(nstbi_zlib_compress(), 1);
    }

    @NativeType("int")
    public static boolean stbi_write_bmp(@NativeType("char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) byteBuffer2, i * i2 * i3);
        }
        return nstbi_write_bmp(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddress(byteBuffer2)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_bmp(@NativeType("char const *") CharSequence charSequence, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i * i2 * i3);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_write_bmp(stackGet.getPointerAddress(), i, i2, i3, MemoryUtil.memAddress(byteBuffer)) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_write_tga(@NativeType("char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) byteBuffer2, i * i2 * i3);
        }
        return nstbi_write_tga(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddress(byteBuffer2)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_tga(@NativeType("char const *") CharSequence charSequence, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i * i2 * i3);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_write_tga(stackGet.getPointerAddress(), i, i2, i3, MemoryUtil.memAddress(byteBuffer)) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int *")
    private static IntBuffer stbi_write_tga_with_rle() {
        return MemoryUtil.memIntBuffer(nstbi_write_tga_with_rle(), 1);
    }

    @NativeType("int")
    public static boolean stbi_write_hdr(@NativeType("char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("float const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) floatBuffer, i * i2 * i3);
        }
        return nstbi_write_hdr(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddress(floatBuffer)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_hdr(@NativeType("char const *") CharSequence charSequence, int i, int i2, int i3, @NativeType("float const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, i * i2 * i3);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_write_hdr(stackGet.getPointerAddress(), i, i2, i3, MemoryUtil.memAddress(floatBuffer)) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_write_jpg(@NativeType("char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer2, int i4) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) byteBuffer2, i * i2 * i3);
        }
        return nstbi_write_jpg(MemoryUtil.memAddress(byteBuffer), i, i2, i3, MemoryUtil.memAddress(byteBuffer2), i4) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_jpg(@NativeType("char const *") CharSequence charSequence, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i * i2 * i3);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_write_jpg(stackGet.getPointerAddress(), i, i2, i3, MemoryUtil.memAddress(byteBuffer), i4) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_write_png_to_func(@NativeType("stbi_write_func *") STBIWriteCallbackI sTBIWriteCallbackI, @NativeType("void *") long j, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, (i4 != 0 ? i4 : i * i3) * i2);
        }
        return nstbi_write_png_to_func(sTBIWriteCallbackI.address(), j, i, i2, i3, MemoryUtil.memAddress(byteBuffer), i4) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_bmp_to_func(@NativeType("stbi_write_func *") STBIWriteCallbackI sTBIWriteCallbackI, @NativeType("void *") long j, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i * i2 * i3);
        }
        return nstbi_write_bmp_to_func(sTBIWriteCallbackI.address(), j, i, i2, i3, MemoryUtil.memAddress(byteBuffer)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_tga_to_func(@NativeType("stbi_write_func *") STBIWriteCallbackI sTBIWriteCallbackI, @NativeType("void *") long j, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i * i2 * i3);
        }
        return nstbi_write_tga_to_func(sTBIWriteCallbackI.address(), j, i, i2, i3, MemoryUtil.memAddress(byteBuffer)) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_hdr_to_func(@NativeType("stbi_write_func *") STBIWriteCallbackI sTBIWriteCallbackI, @NativeType("void *") long j, int i, int i2, int i3, @NativeType("float const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, i * i2 * i3);
        }
        return nstbi_write_hdr_to_func(sTBIWriteCallbackI.address(), j, i, i2, i3, MemoryUtil.memAddress(floatBuffer)) != 0;
    }

    public static int stbi_write_jpg_to_func(@NativeType("stbi_write_func *") STBIWriteCallbackI sTBIWriteCallbackI, @NativeType("void *") long j, int i, int i2, int i3, @NativeType("void const *") ByteBuffer byteBuffer, int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i * i2 * i3);
        }
        return nstbi_write_jpg_to_func(sTBIWriteCallbackI.address(), j, i, i2, i3, MemoryUtil.memAddress(byteBuffer), i4);
    }

    public static void stbi_flip_vertically_on_write(@NativeType("int") boolean z) {
        nstbi_flip_vertically_on_write(z ? 1 : 0);
    }

    @NativeType("int")
    public static boolean stbi_write_hdr(@NativeType("char const *") ByteBuffer byteBuffer, int i, int i2, int i3, @NativeType("float const *") float[] fArr) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(fArr, i * i2 * i3);
        }
        return nstbi_write_hdr(MemoryUtil.memAddress(byteBuffer), i, i2, i3, fArr) != 0;
    }

    @NativeType("int")
    public static boolean stbi_write_hdr(@NativeType("char const *") CharSequence charSequence, int i, int i2, int i3, @NativeType("float const *") float[] fArr) {
        if (Checks.CHECKS) {
            Checks.check(fArr, i * i2 * i3);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstbi_write_hdr(stackGet.getPointerAddress(), i, i2, i3, fArr) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean stbi_write_hdr_to_func(@NativeType("stbi_write_func *") STBIWriteCallbackI sTBIWriteCallbackI, @NativeType("void *") long j, int i, int i2, int i3, @NativeType("float const *") float[] fArr) {
        if (Checks.CHECKS) {
            Checks.check(fArr, i * i2 * i3);
        }
        return nstbi_write_hdr_to_func(sTBIWriteCallbackI.address(), j, i, i2, i3, fArr) != 0;
    }
}
