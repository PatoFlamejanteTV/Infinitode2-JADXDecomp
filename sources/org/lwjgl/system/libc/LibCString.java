package org.lwjgl.system.libc;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/libc/LibCString.class */
public class LibCString {
    public static native long nmemset(long j, int i, long j2);

    public static native long nmemcpy(long j, long j2, long j3);

    public static native long nmemmove(long j, long j2, long j3);

    public static native long nstrlen(long j);

    public static native long nstrerror(int i);

    public static native long nmemset(byte[] bArr, int i, long j);

    public static native long nmemset(short[] sArr, int i, long j);

    public static native long nmemset(int[] iArr, int i, long j);

    public static native long nmemset(long[] jArr, int i, long j);

    public static native long nmemset(float[] fArr, int i, long j);

    public static native long nmemset(double[] dArr, int i, long j);

    public static native long nmemcpy(byte[] bArr, byte[] bArr2, long j);

    public static native long nmemcpy(short[] sArr, short[] sArr2, long j);

    public static native long nmemcpy(int[] iArr, int[] iArr2, long j);

    public static native long nmemcpy(long[] jArr, long[] jArr2, long j);

    public static native long nmemcpy(float[] fArr, float[] fArr2, long j);

    public static native long nmemcpy(double[] dArr, double[] dArr2, long j);

    public static native long nmemmove(byte[] bArr, byte[] bArr2, long j);

    public static native long nmemmove(short[] sArr, short[] sArr2, long j);

    public static native long nmemmove(int[] iArr, int[] iArr2, long j);

    public static native long nmemmove(long[] jArr, long[] jArr2, long j);

    public static native long nmemmove(float[] fArr, float[] fArr2, long j);

    public static native long nmemmove(double[] dArr, double[] dArr2, long j);

    static {
        Library.initialize();
    }

    protected LibCString() {
        throw new UnsupportedOperationException();
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") ByteBuffer byteBuffer, int i) {
        return nmemset(MemoryUtil.memAddress(byteBuffer), i, byteBuffer.remaining());
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") ShortBuffer shortBuffer, int i) {
        return nmemset(MemoryUtil.memAddress(shortBuffer), i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") IntBuffer intBuffer, int i) {
        return nmemset(MemoryUtil.memAddress(intBuffer), i, Integer.toUnsignedLong(intBuffer.remaining()) << 2);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") LongBuffer longBuffer, int i) {
        return nmemset(MemoryUtil.memAddress(longBuffer), i, Integer.toUnsignedLong(longBuffer.remaining()) << 3);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") FloatBuffer floatBuffer, int i) {
        return nmemset(MemoryUtil.memAddress(floatBuffer), i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") DoubleBuffer doubleBuffer, int i) {
        return nmemset(MemoryUtil.memAddress(doubleBuffer), i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("void const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, byteBuffer2.remaining());
        }
        return nmemcpy(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), byteBuffer2.remaining());
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") ShortBuffer shortBuffer, @NativeType("void const *") ShortBuffer shortBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, shortBuffer2.remaining());
        }
        return nmemcpy(MemoryUtil.memAddress(shortBuffer), MemoryUtil.memAddress(shortBuffer2), Integer.toUnsignedLong(shortBuffer2.remaining()) << 1);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") IntBuffer intBuffer, @NativeType("void const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, intBuffer2.remaining());
        }
        return nmemcpy(MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), Integer.toUnsignedLong(intBuffer2.remaining()) << 2);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") LongBuffer longBuffer, @NativeType("void const *") LongBuffer longBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, longBuffer2.remaining());
        }
        return nmemcpy(MemoryUtil.memAddress(longBuffer), MemoryUtil.memAddress(longBuffer2), Integer.toUnsignedLong(longBuffer2.remaining()) << 3);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") FloatBuffer floatBuffer, @NativeType("void const *") FloatBuffer floatBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, floatBuffer2.remaining());
        }
        return nmemcpy(MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), Integer.toUnsignedLong(floatBuffer2.remaining()) << 2);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") DoubleBuffer doubleBuffer, @NativeType("void const *") DoubleBuffer doubleBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, doubleBuffer2.remaining());
        }
        return nmemcpy(MemoryUtil.memAddress(doubleBuffer), MemoryUtil.memAddress(doubleBuffer2), Integer.toUnsignedLong(doubleBuffer2.remaining()) << 3);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("void const *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, byteBuffer2.remaining());
        }
        return nmemmove(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), byteBuffer2.remaining());
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") ShortBuffer shortBuffer, @NativeType("void const *") ShortBuffer shortBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, shortBuffer2.remaining());
        }
        return nmemmove(MemoryUtil.memAddress(shortBuffer), MemoryUtil.memAddress(shortBuffer2), Integer.toUnsignedLong(shortBuffer2.remaining()) << 1);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") IntBuffer intBuffer, @NativeType("void const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, intBuffer2.remaining());
        }
        return nmemmove(MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), Integer.toUnsignedLong(intBuffer2.remaining()) << 2);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") LongBuffer longBuffer, @NativeType("void const *") LongBuffer longBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, longBuffer2.remaining());
        }
        return nmemmove(MemoryUtil.memAddress(longBuffer), MemoryUtil.memAddress(longBuffer2), Integer.toUnsignedLong(longBuffer2.remaining()) << 3);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") FloatBuffer floatBuffer, @NativeType("void const *") FloatBuffer floatBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, floatBuffer2.remaining());
        }
        return nmemmove(MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2), Integer.toUnsignedLong(floatBuffer2.remaining()) << 2);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") DoubleBuffer doubleBuffer, @NativeType("void const *") DoubleBuffer doubleBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, doubleBuffer2.remaining());
        }
        return nmemmove(MemoryUtil.memAddress(doubleBuffer), MemoryUtil.memAddress(doubleBuffer2), Integer.toUnsignedLong(doubleBuffer2.remaining()) << 3);
    }

    @NativeType("size_t")
    public static long strlen(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nstrlen(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("char *")
    public static String strerror(int i) {
        return MemoryUtil.memASCIISafe(nstrerror(i));
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") byte[] bArr, int i) {
        return nmemset(bArr, i, Integer.toUnsignedLong(bArr.length));
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") short[] sArr, int i) {
        return nmemset(sArr, i, Integer.toUnsignedLong(sArr.length) << 1);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") int[] iArr, int i) {
        return nmemset(iArr, i, Integer.toUnsignedLong(iArr.length) << 2);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") long[] jArr, int i) {
        return nmemset(jArr, i, Integer.toUnsignedLong(jArr.length) << 3);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") float[] fArr, int i) {
        return nmemset(fArr, i, Integer.toUnsignedLong(fArr.length) << 2);
    }

    @NativeType("void *")
    public static long memset(@NativeType("void *") double[] dArr, int i) {
        return nmemset(dArr, i, Integer.toUnsignedLong(dArr.length) << 3);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") byte[] bArr, @NativeType("void const *") byte[] bArr2) {
        if (Checks.CHECKS) {
            Checks.check(bArr, bArr2.length);
        }
        return nmemcpy(bArr, bArr2, Integer.toUnsignedLong(bArr2.length));
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") short[] sArr, @NativeType("void const *") short[] sArr2) {
        if (Checks.CHECKS) {
            Checks.check(sArr, sArr2.length);
        }
        return nmemcpy(sArr, sArr2, Integer.toUnsignedLong(sArr2.length) << 1);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") int[] iArr, @NativeType("void const *") int[] iArr2) {
        if (Checks.CHECKS) {
            Checks.check(iArr, iArr2.length);
        }
        return nmemcpy(iArr, iArr2, Integer.toUnsignedLong(iArr2.length) << 2);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") long[] jArr, @NativeType("void const *") long[] jArr2) {
        if (Checks.CHECKS) {
            Checks.check(jArr, jArr2.length);
        }
        return nmemcpy(jArr, jArr2, Integer.toUnsignedLong(jArr2.length) << 3);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") float[] fArr, @NativeType("void const *") float[] fArr2) {
        if (Checks.CHECKS) {
            Checks.check(fArr, fArr2.length);
        }
        return nmemcpy(fArr, fArr2, Integer.toUnsignedLong(fArr2.length) << 2);
    }

    @NativeType("void *")
    public static long memcpy(@NativeType("void *") double[] dArr, @NativeType("void const *") double[] dArr2) {
        if (Checks.CHECKS) {
            Checks.check(dArr, dArr2.length);
        }
        return nmemcpy(dArr, dArr2, Integer.toUnsignedLong(dArr2.length) << 3);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") byte[] bArr, @NativeType("void const *") byte[] bArr2) {
        if (Checks.CHECKS) {
            Checks.check(bArr, bArr2.length);
        }
        return nmemmove(bArr, bArr2, Integer.toUnsignedLong(bArr2.length));
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") short[] sArr, @NativeType("void const *") short[] sArr2) {
        if (Checks.CHECKS) {
            Checks.check(sArr, sArr2.length);
        }
        return nmemmove(sArr, sArr2, Integer.toUnsignedLong(sArr2.length) << 1);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") int[] iArr, @NativeType("void const *") int[] iArr2) {
        if (Checks.CHECKS) {
            Checks.check(iArr, iArr2.length);
        }
        return nmemmove(iArr, iArr2, Integer.toUnsignedLong(iArr2.length) << 2);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") long[] jArr, @NativeType("void const *") long[] jArr2) {
        if (Checks.CHECKS) {
            Checks.check(jArr, jArr2.length);
        }
        return nmemmove(jArr, jArr2, Integer.toUnsignedLong(jArr2.length) << 3);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") float[] fArr, @NativeType("void const *") float[] fArr2) {
        if (Checks.CHECKS) {
            Checks.check(fArr, fArr2.length);
        }
        return nmemmove(fArr, fArr2, Integer.toUnsignedLong(fArr2.length) << 2);
    }

    @NativeType("void *")
    public static long memmove(@NativeType("void *") double[] dArr, @NativeType("void const *") double[] dArr2) {
        if (Checks.CHECKS) {
            Checks.check(dArr, dArr2.length);
        }
        return nmemmove(dArr, dArr2, Integer.toUnsignedLong(dArr2.length) << 3);
    }

    @NativeType("void *")
    public static <T extends CustomBuffer<T>> long memset(@NativeType("void *") T t, @NativeType("int") int i) {
        return nmemset(MemoryUtil.memAddress((CustomBuffer<?>) t), i, Integer.toUnsignedLong(t.remaining()) * t.sizeof());
    }

    @NativeType("void *")
    public static <T extends CustomBuffer<T>> long memcpy(@NativeType("void *") T t, @NativeType("void const *") T t2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) t2, t.remaining());
        }
        return nmemcpy(MemoryUtil.memAddress((CustomBuffer<?>) t), MemoryUtil.memAddress((CustomBuffer<?>) t2), t2.remaining() * t2.sizeof());
    }

    @NativeType("void *")
    public static <T extends CustomBuffer<T>> long memmove(@NativeType("void *") T t, @NativeType("void const *") T t2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) t2, t.remaining());
        }
        return nmemmove(MemoryUtil.memAddress((CustomBuffer<?>) t), MemoryUtil.memAddress((CustomBuffer<?>) t2), t2.remaining() * t2.sizeof());
    }
}
