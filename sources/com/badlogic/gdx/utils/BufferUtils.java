package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/BufferUtils.class */
public final class BufferUtils {
    static Array<ByteBuffer> unsafeBuffers = new Array<>();
    static int allocatedUnsafe = 0;

    private static native void freeMemory(ByteBuffer byteBuffer);

    private static native ByteBuffer newDisposableByteBuffer(int i);

    private static native long getBufferAddress(Buffer buffer);

    public static native void clear(ByteBuffer byteBuffer, int i);

    private static native void copyJni(float[] fArr, Buffer buffer, int i, int i2);

    private static native void copyJni(byte[] bArr, int i, Buffer buffer, int i2, int i3);

    private static native void copyJni(char[] cArr, int i, Buffer buffer, int i2, int i3);

    private static native void copyJni(short[] sArr, int i, Buffer buffer, int i2, int i3);

    private static native void copyJni(int[] iArr, int i, Buffer buffer, int i2, int i3);

    private static native void copyJni(long[] jArr, int i, Buffer buffer, int i2, int i3);

    private static native void copyJni(float[] fArr, int i, Buffer buffer, int i2, int i3);

    private static native void copyJni(double[] dArr, int i, Buffer buffer, int i2, int i3);

    private static native void copyJni(Buffer buffer, int i, Buffer buffer2, int i2, int i3);

    private static native void transformV4M4Jni(Buffer buffer, int i, int i2, float[] fArr, int i3);

    private static native void transformV4M4Jni(float[] fArr, int i, int i2, float[] fArr2, int i3);

    private static native void transformV3M4Jni(Buffer buffer, int i, int i2, float[] fArr, int i3);

    private static native void transformV3M4Jni(float[] fArr, int i, int i2, float[] fArr2, int i3);

    private static native void transformV2M4Jni(Buffer buffer, int i, int i2, float[] fArr, int i3);

    private static native void transformV2M4Jni(float[] fArr, int i, int i2, float[] fArr2, int i3);

    private static native void transformV3M3Jni(Buffer buffer, int i, int i2, float[] fArr, int i3);

    private static native void transformV3M3Jni(float[] fArr, int i, int i2, float[] fArr2, int i3);

    private static native void transformV2M3Jni(Buffer buffer, int i, int i2, float[] fArr, int i3);

    private static native void transformV2M3Jni(float[] fArr, int i, int i2, float[] fArr2, int i3);

    private static native long find(Buffer buffer, int i, int i2, Buffer buffer2, int i3, int i4);

    private static native long find(float[] fArr, int i, int i2, Buffer buffer, int i3, int i4);

    private static native long find(Buffer buffer, int i, int i2, float[] fArr, int i3, int i4);

    private static native long find(float[] fArr, int i, int i2, float[] fArr2, int i3, int i4);

    private static native long find(Buffer buffer, int i, int i2, Buffer buffer2, int i3, int i4, float f);

    private static native long find(float[] fArr, int i, int i2, Buffer buffer, int i3, int i4, float f);

    private static native long find(Buffer buffer, int i, int i2, float[] fArr, int i3, int i4, float f);

    private static native long find(float[] fArr, int i, int i2, float[] fArr2, int i3, int i4, float f);

    private BufferUtils() {
    }

    public static void copy(float[] fArr, Buffer buffer, int i, int i2) {
        if (buffer instanceof ByteBuffer) {
            buffer.limit(i << 2);
        } else if (buffer instanceof FloatBuffer) {
            buffer.limit(i);
        }
        copyJni(fArr, buffer, i, i2);
        buffer.position(0);
    }

    public static void copy(byte[] bArr, int i, Buffer buffer, int i2) {
        buffer.limit(buffer.position() + bytesToElements(buffer, i2));
        copyJni(bArr, i, buffer, positionInBytes(buffer), i2);
    }

    public static void copy(short[] sArr, int i, Buffer buffer, int i2) {
        buffer.limit(buffer.position() + bytesToElements(buffer, i2 << 1));
        copyJni(sArr, i, buffer, positionInBytes(buffer), i2 << 1);
    }

    public static void copy(char[] cArr, int i, int i2, Buffer buffer) {
        copyJni(cArr, i, buffer, positionInBytes(buffer), i2 << 1);
    }

    public static void copy(int[] iArr, int i, int i2, Buffer buffer) {
        copyJni(iArr, i, buffer, positionInBytes(buffer), i2 << 2);
    }

    public static void copy(long[] jArr, int i, int i2, Buffer buffer) {
        copyJni(jArr, i, buffer, positionInBytes(buffer), i2 << 3);
    }

    public static void copy(float[] fArr, int i, int i2, Buffer buffer) {
        copyJni(fArr, i, buffer, positionInBytes(buffer), i2 << 2);
    }

    public static void copy(double[] dArr, int i, int i2, Buffer buffer) {
        copyJni(dArr, i, buffer, positionInBytes(buffer), i2 << 3);
    }

    public static void copy(char[] cArr, int i, Buffer buffer, int i2) {
        buffer.limit(buffer.position() + bytesToElements(buffer, i2 << 1));
        copyJni(cArr, i, buffer, positionInBytes(buffer), i2 << 1);
    }

    public static void copy(int[] iArr, int i, Buffer buffer, int i2) {
        buffer.limit(buffer.position() + bytesToElements(buffer, i2 << 2));
        copyJni(iArr, i, buffer, positionInBytes(buffer), i2 << 2);
    }

    public static void copy(long[] jArr, int i, Buffer buffer, int i2) {
        buffer.limit(buffer.position() + bytesToElements(buffer, i2 << 3));
        copyJni(jArr, i, buffer, positionInBytes(buffer), i2 << 3);
    }

    public static void copy(float[] fArr, int i, Buffer buffer, int i2) {
        buffer.limit(buffer.position() + bytesToElements(buffer, i2 << 2));
        copyJni(fArr, i, buffer, positionInBytes(buffer), i2 << 2);
    }

    public static void copy(double[] dArr, int i, Buffer buffer, int i2) {
        buffer.limit(buffer.position() + bytesToElements(buffer, i2 << 3));
        copyJni(dArr, i, buffer, positionInBytes(buffer), i2 << 3);
    }

    public static void copy(Buffer buffer, Buffer buffer2, int i) {
        int elementsToBytes = elementsToBytes(buffer, i);
        buffer2.limit(buffer2.position() + bytesToElements(buffer2, elementsToBytes));
        copyJni(buffer, positionInBytes(buffer), buffer2, positionInBytes(buffer2), elementsToBytes);
    }

    public static void transform(Buffer buffer, int i, int i2, int i3, Matrix4 matrix4) {
        transform(buffer, i, i2, i3, matrix4, 0);
    }

    public static void transform(float[] fArr, int i, int i2, int i3, Matrix4 matrix4) {
        transform(fArr, i, i2, i3, matrix4, 0);
    }

    public static void transform(Buffer buffer, int i, int i2, int i3, Matrix4 matrix4, int i4) {
        switch (i) {
            case 2:
                transformV2M4Jni(buffer, i2, i3, matrix4.val, positionInBytes(buffer) + i4);
                return;
            case 3:
                transformV3M4Jni(buffer, i2, i3, matrix4.val, positionInBytes(buffer) + i4);
                return;
            case 4:
                transformV4M4Jni(buffer, i2, i3, matrix4.val, positionInBytes(buffer) + i4);
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void transform(float[] fArr, int i, int i2, int i3, Matrix4 matrix4, int i4) {
        switch (i) {
            case 2:
                transformV2M4Jni(fArr, i2, i3, matrix4.val, i4);
                return;
            case 3:
                transformV3M4Jni(fArr, i2, i3, matrix4.val, i4);
                return;
            case 4:
                transformV4M4Jni(fArr, i2, i3, matrix4.val, i4);
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void transform(Buffer buffer, int i, int i2, int i3, Matrix3 matrix3) {
        transform(buffer, i, i2, i3, matrix3, 0);
    }

    public static void transform(float[] fArr, int i, int i2, int i3, Matrix3 matrix3) {
        transform(fArr, i, i2, i3, matrix3, 0);
    }

    public static void transform(Buffer buffer, int i, int i2, int i3, Matrix3 matrix3, int i4) {
        switch (i) {
            case 2:
                transformV2M3Jni(buffer, i2, i3, matrix3.val, positionInBytes(buffer) + i4);
                return;
            case 3:
                transformV3M3Jni(buffer, i2, i3, matrix3.val, positionInBytes(buffer) + i4);
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void transform(float[] fArr, int i, int i2, int i3, Matrix3 matrix3, int i4) {
        switch (i) {
            case 2:
                transformV2M3Jni(fArr, i2, i3, matrix3.val, i4);
                return;
            case 3:
                transformV3M3Jni(fArr, i2, i3, matrix3.val, i4);
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static long findFloats(Buffer buffer, int i, Buffer buffer2, int i2) {
        return find(buffer, positionInBytes(buffer), i, buffer2, positionInBytes(buffer2), i2);
    }

    public static long findFloats(float[] fArr, int i, Buffer buffer, int i2) {
        return find(fArr, 0, i, buffer, positionInBytes(buffer), i2);
    }

    public static long findFloats(Buffer buffer, int i, float[] fArr, int i2) {
        return find(buffer, positionInBytes(buffer), i, fArr, 0, i2);
    }

    public static long findFloats(float[] fArr, int i, float[] fArr2, int i2) {
        return find(fArr, 0, i, fArr2, 0, i2);
    }

    public static long findFloats(Buffer buffer, int i, Buffer buffer2, int i2, float f) {
        return find(buffer, positionInBytes(buffer), i, buffer2, positionInBytes(buffer2), i2, f);
    }

    public static long findFloats(float[] fArr, int i, Buffer buffer, int i2, float f) {
        return find(fArr, 0, i, buffer, positionInBytes(buffer), i2, f);
    }

    public static long findFloats(Buffer buffer, int i, float[] fArr, int i2, float f) {
        return find(buffer, positionInBytes(buffer), i, fArr, 0, i2, f);
    }

    public static long findFloats(float[] fArr, int i, float[] fArr2, int i2, float f) {
        return find(fArr, 0, i, fArr2, 0, i2, f);
    }

    private static int positionInBytes(Buffer buffer) {
        if (buffer instanceof ByteBuffer) {
            return buffer.position();
        }
        if (buffer instanceof ShortBuffer) {
            return buffer.position() << 1;
        }
        if (buffer instanceof CharBuffer) {
            return buffer.position() << 1;
        }
        if (buffer instanceof IntBuffer) {
            return buffer.position() << 2;
        }
        if (buffer instanceof LongBuffer) {
            return buffer.position() << 3;
        }
        if (buffer instanceof FloatBuffer) {
            return buffer.position() << 2;
        }
        if (buffer instanceof DoubleBuffer) {
            return buffer.position() << 3;
        }
        throw new GdxRuntimeException("Can't copy to a " + buffer.getClass().getName() + " instance");
    }

    private static int bytesToElements(Buffer buffer, int i) {
        if (buffer instanceof ByteBuffer) {
            return i;
        }
        if (buffer instanceof ShortBuffer) {
            return i >>> 1;
        }
        if (buffer instanceof CharBuffer) {
            return i >>> 1;
        }
        if (buffer instanceof IntBuffer) {
            return i >>> 2;
        }
        if (buffer instanceof LongBuffer) {
            return i >>> 3;
        }
        if (buffer instanceof FloatBuffer) {
            return i >>> 2;
        }
        if (buffer instanceof DoubleBuffer) {
            return i >>> 3;
        }
        throw new GdxRuntimeException("Can't copy to a " + buffer.getClass().getName() + " instance");
    }

    private static int elementsToBytes(Buffer buffer, int i) {
        if (buffer instanceof ByteBuffer) {
            return i;
        }
        if (buffer instanceof ShortBuffer) {
            return i << 1;
        }
        if (buffer instanceof CharBuffer) {
            return i << 1;
        }
        if (buffer instanceof IntBuffer) {
            return i << 2;
        }
        if (buffer instanceof LongBuffer) {
            return i << 3;
        }
        if (buffer instanceof FloatBuffer) {
            return i << 2;
        }
        if (buffer instanceof DoubleBuffer) {
            return i << 3;
        }
        throw new GdxRuntimeException("Can't copy to a " + buffer.getClass().getName() + " instance");
    }

    public static FloatBuffer newFloatBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i << 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect.asFloatBuffer();
    }

    public static DoubleBuffer newDoubleBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i << 3);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect.asDoubleBuffer();
    }

    public static ByteBuffer newByteBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect;
    }

    public static ShortBuffer newShortBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i << 1);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect.asShortBuffer();
    }

    public static CharBuffer newCharBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i << 1);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect.asCharBuffer();
    }

    public static IntBuffer newIntBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i << 2);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect.asIntBuffer();
    }

    public static LongBuffer newLongBuffer(int i) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(i << 3);
        allocateDirect.order(ByteOrder.nativeOrder());
        return allocateDirect.asLongBuffer();
    }

    public static void disposeUnsafeByteBuffer(ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity();
        synchronized (unsafeBuffers) {
            if (!unsafeBuffers.removeValue(byteBuffer, true)) {
                throw new IllegalArgumentException("buffer not allocated with newUnsafeByteBuffer or already disposed");
            }
        }
        allocatedUnsafe -= capacity;
        freeMemory(byteBuffer);
    }

    public static boolean isUnsafeByteBuffer(ByteBuffer byteBuffer) {
        boolean contains;
        synchronized (unsafeBuffers) {
            contains = unsafeBuffers.contains(byteBuffer, true);
        }
        return contains;
    }

    public static ByteBuffer newUnsafeByteBuffer(int i) {
        ByteBuffer newDisposableByteBuffer = newDisposableByteBuffer(i);
        newDisposableByteBuffer.order(ByteOrder.nativeOrder());
        allocatedUnsafe += i;
        synchronized (unsafeBuffers) {
            unsafeBuffers.add(newDisposableByteBuffer);
        }
        return newDisposableByteBuffer;
    }

    public static long getUnsafeBufferAddress(Buffer buffer) {
        return getBufferAddress(buffer) + buffer.position();
    }

    public static ByteBuffer newUnsafeByteBuffer(ByteBuffer byteBuffer) {
        allocatedUnsafe += byteBuffer.capacity();
        synchronized (unsafeBuffers) {
            unsafeBuffers.add(byteBuffer);
        }
        return byteBuffer;
    }

    public static int getAllocatedBytesUnsafe() {
        return allocatedUnsafe;
    }
}
