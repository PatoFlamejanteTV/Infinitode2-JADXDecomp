package org.lwjgl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/BufferUtils.class */
public final class BufferUtils {
    private BufferUtils() {
    }

    public static ByteBuffer createByteBuffer(int i) {
        return ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getAllocationSize(int i, int i2) {
        APIUtil.apiCheckAllocation(i, APIUtil.apiGetBytes(i, i2), 2147483647L);
        return i << i2;
    }

    public static ShortBuffer createShortBuffer(int i) {
        return createByteBuffer(getAllocationSize(i, 1)).asShortBuffer();
    }

    public static CharBuffer createCharBuffer(int i) {
        return createByteBuffer(getAllocationSize(i, 1)).asCharBuffer();
    }

    public static IntBuffer createIntBuffer(int i) {
        return createByteBuffer(getAllocationSize(i, 2)).asIntBuffer();
    }

    public static LongBuffer createLongBuffer(int i) {
        return createByteBuffer(getAllocationSize(i, 3)).asLongBuffer();
    }

    public static CLongBuffer createCLongBuffer(int i) {
        return CLongBuffer.allocateDirect(i);
    }

    public static FloatBuffer createFloatBuffer(int i) {
        return createByteBuffer(getAllocationSize(i, 2)).asFloatBuffer();
    }

    public static DoubleBuffer createDoubleBuffer(int i) {
        return createByteBuffer(getAllocationSize(i, 3)).asDoubleBuffer();
    }

    public static PointerBuffer createPointerBuffer(int i) {
        return PointerBuffer.allocateDirect(i);
    }

    public static void zeroBuffer(ByteBuffer byteBuffer) {
        MemoryUtil.memSet(byteBuffer, 0);
    }

    public static void zeroBuffer(ShortBuffer shortBuffer) {
        MemoryUtil.memSet(shortBuffer, 0);
    }

    public static void zeroBuffer(CharBuffer charBuffer) {
        MemoryUtil.memSet(charBuffer, 0);
    }

    public static void zeroBuffer(IntBuffer intBuffer) {
        MemoryUtil.memSet(intBuffer, 0);
    }

    public static void zeroBuffer(FloatBuffer floatBuffer) {
        MemoryUtil.memSet(floatBuffer, 0);
    }

    public static void zeroBuffer(LongBuffer longBuffer) {
        MemoryUtil.memSet(longBuffer, 0);
    }

    public static void zeroBuffer(DoubleBuffer doubleBuffer) {
        MemoryUtil.memSet(doubleBuffer, 0);
    }

    public static <T extends CustomBuffer<T>> void zeroBuffer(T t) {
        MemoryUtil.memSet(t, 0);
    }
}
