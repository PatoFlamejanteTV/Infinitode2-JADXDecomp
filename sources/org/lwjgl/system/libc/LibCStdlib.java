package org.lwjgl.system.libc;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/libc/LibCStdlib.class */
public class LibCStdlib {
    public static native long nmalloc(long j);

    public static native long ncalloc(long j, long j2);

    public static native long nrealloc(long j, long j2);

    public static native void nfree(long j);

    public static native long naligned_alloc(long j, long j2);

    public static native void naligned_free(long j);

    static {
        Library.initialize();
    }

    protected LibCStdlib() {
        throw new UnsupportedOperationException();
    }

    @NativeType("void *")
    public static ByteBuffer malloc(@NativeType("size_t") long j) {
        return MemoryUtil.memByteBufferSafe(nmalloc(j), (int) j);
    }

    @NativeType("void *")
    public static ByteBuffer calloc(@NativeType("size_t") long j, @NativeType("size_t") long j2) {
        return MemoryUtil.memByteBufferSafe(ncalloc(j, j2), ((int) j) * ((int) j2));
    }

    @NativeType("void *")
    public static ByteBuffer realloc(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("size_t") long j) {
        return MemoryUtil.memByteBufferSafe(nrealloc(MemoryUtil.memAddressSafe(byteBuffer), j), (int) j);
    }

    public static void free(@NativeType("void *") ByteBuffer byteBuffer) {
        nfree(MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void free(@NativeType("void *") ShortBuffer shortBuffer) {
        nfree(MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void free(@NativeType("void *") IntBuffer intBuffer) {
        nfree(MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void free(@NativeType("void *") LongBuffer longBuffer) {
        nfree(MemoryUtil.memAddressSafe(longBuffer));
    }

    public static void free(@NativeType("void *") FloatBuffer floatBuffer) {
        nfree(MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void free(@NativeType("void *") DoubleBuffer doubleBuffer) {
        nfree(MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void free(@NativeType("void *") PointerBuffer pointerBuffer) {
        nfree(MemoryUtil.memAddressSafe(pointerBuffer));
    }

    @NativeType("void *")
    public static ByteBuffer aligned_alloc(@NativeType("size_t") long j, @NativeType("size_t") long j2) {
        return MemoryUtil.memByteBufferSafe(naligned_alloc(j, j2), (int) j2);
    }

    public static void aligned_free(@NativeType("void *") ByteBuffer byteBuffer) {
        naligned_free(MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void aligned_free(@NativeType("void *") ShortBuffer shortBuffer) {
        naligned_free(MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void aligned_free(@NativeType("void *") IntBuffer intBuffer) {
        naligned_free(MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void aligned_free(@NativeType("void *") LongBuffer longBuffer) {
        naligned_free(MemoryUtil.memAddressSafe(longBuffer));
    }

    public static void aligned_free(@NativeType("void *") FloatBuffer floatBuffer) {
        naligned_free(MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void aligned_free(@NativeType("void *") DoubleBuffer doubleBuffer) {
        naligned_free(MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void aligned_free(@NativeType("void *") PointerBuffer pointerBuffer) {
        naligned_free(MemoryUtil.memAddressSafe(pointerBuffer));
    }
}
