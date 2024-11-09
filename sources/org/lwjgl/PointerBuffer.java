package org.lwjgl;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.CheckIntrinsics;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Pointer;

/* loaded from: infinitode-2.jar:org/lwjgl/PointerBuffer.class */
public class PointerBuffer extends CustomBuffer<PointerBuffer> implements Comparable<PointerBuffer> {
    protected PointerBuffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        super(j, byteBuffer, i, i2, i3, i4);
    }

    public static PointerBuffer allocateDirect(int i) {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(BufferUtils.getAllocationSize(i, POINTER_SHIFT));
        return new PointerBuffer(MemoryUtil.memAddress(createByteBuffer), createByteBuffer, -1, 0, i, i);
    }

    public static PointerBuffer create(long j, int i) {
        return new PointerBuffer(j, null, -1, 0, i, i);
    }

    public static PointerBuffer create(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining() >> POINTER_SHIFT;
        return new PointerBuffer(MemoryUtil.memAddress(byteBuffer), byteBuffer, -1, 0, remaining, remaining);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.CustomBuffer
    public PointerBuffer self() {
        return this;
    }

    @Override // org.lwjgl.system.CustomBuffer
    public int sizeof() {
        return POINTER_SIZE;
    }

    public long get() {
        return MemoryUtil.memGetAddress(this.address + (Integer.toUnsignedLong(nextGetIndex()) * POINTER_SIZE));
    }

    public static long get(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < POINTER_SIZE) {
            throw new BufferUnderflowException();
        }
        try {
            return MemoryUtil.memGetAddress(MemoryUtil.memAddress(byteBuffer));
        } finally {
            byteBuffer.position(byteBuffer.position() + POINTER_SIZE);
        }
    }

    public PointerBuffer put(long j) {
        MemoryUtil.memPutAddress(this.address + (Integer.toUnsignedLong(nextPutIndex()) * POINTER_SIZE), j);
        return this;
    }

    public static void put(ByteBuffer byteBuffer, long j) {
        if (byteBuffer.remaining() < POINTER_SIZE) {
            throw new BufferOverflowException();
        }
        try {
            MemoryUtil.memPutAddress(MemoryUtil.memAddress(byteBuffer), j);
        } finally {
            byteBuffer.position(byteBuffer.position() + POINTER_SIZE);
        }
    }

    public long get(int i) {
        return MemoryUtil.memGetAddress(this.address + (Checks.check(i, this.limit) * POINTER_SIZE));
    }

    public static long get(ByteBuffer byteBuffer, int i) {
        CheckIntrinsics.checkFromIndexSize(i, POINTER_SIZE, byteBuffer.limit());
        return MemoryUtil.memGetAddress(MemoryUtil.memAddress0(byteBuffer) + i);
    }

    public PointerBuffer put(int i, long j) {
        MemoryUtil.memPutAddress(this.address + (Checks.check(i, this.limit) * POINTER_SIZE), j);
        return this;
    }

    public static void put(ByteBuffer byteBuffer, int i, long j) {
        CheckIntrinsics.checkFromIndexSize(i, POINTER_SIZE, byteBuffer.limit());
        MemoryUtil.memPutAddress(MemoryUtil.memAddress0(byteBuffer) + i, j);
    }

    public PointerBuffer put(Pointer pointer) {
        put(pointer.address());
        return this;
    }

    public PointerBuffer put(int i, Pointer pointer) {
        put(i, pointer.address());
        return this;
    }

    public PointerBuffer put(ByteBuffer byteBuffer) {
        put(MemoryUtil.memAddress(byteBuffer));
        return this;
    }

    public PointerBuffer put(ShortBuffer shortBuffer) {
        put(MemoryUtil.memAddress(shortBuffer));
        return this;
    }

    public PointerBuffer put(IntBuffer intBuffer) {
        put(MemoryUtil.memAddress(intBuffer));
        return this;
    }

    public PointerBuffer put(LongBuffer longBuffer) {
        put(MemoryUtil.memAddress(longBuffer));
        return this;
    }

    public PointerBuffer put(FloatBuffer floatBuffer) {
        put(MemoryUtil.memAddress(floatBuffer));
        return this;
    }

    public PointerBuffer put(DoubleBuffer doubleBuffer) {
        put(MemoryUtil.memAddress(doubleBuffer));
        return this;
    }

    public PointerBuffer putAddressOf(CustomBuffer<?> customBuffer) {
        put(MemoryUtil.memAddress(customBuffer));
        return this;
    }

    public PointerBuffer put(int i, ByteBuffer byteBuffer) {
        put(i, MemoryUtil.memAddress(byteBuffer));
        return this;
    }

    public PointerBuffer put(int i, ShortBuffer shortBuffer) {
        put(i, MemoryUtil.memAddress(shortBuffer));
        return this;
    }

    public PointerBuffer put(int i, IntBuffer intBuffer) {
        put(i, MemoryUtil.memAddress(intBuffer));
        return this;
    }

    public PointerBuffer put(int i, LongBuffer longBuffer) {
        put(i, MemoryUtil.memAddress(longBuffer));
        return this;
    }

    public PointerBuffer put(int i, FloatBuffer floatBuffer) {
        put(i, MemoryUtil.memAddress(floatBuffer));
        return this;
    }

    public PointerBuffer put(int i, DoubleBuffer doubleBuffer) {
        put(i, MemoryUtil.memAddress(doubleBuffer));
        return this;
    }

    public PointerBuffer putAddressOf(int i, CustomBuffer<?> customBuffer) {
        put(i, MemoryUtil.memAddress(customBuffer));
        return this;
    }

    public ByteBuffer getByteBuffer(int i) {
        return MemoryUtil.memByteBuffer(get(), i);
    }

    public ShortBuffer getShortBuffer(int i) {
        return MemoryUtil.memShortBuffer(get(), i);
    }

    public IntBuffer getIntBuffer(int i) {
        return MemoryUtil.memIntBuffer(get(), i);
    }

    public LongBuffer getLongBuffer(int i) {
        return MemoryUtil.memLongBuffer(get(), i);
    }

    public FloatBuffer getFloatBuffer(int i) {
        return MemoryUtil.memFloatBuffer(get(), i);
    }

    public DoubleBuffer getDoubleBuffer(int i) {
        return MemoryUtil.memDoubleBuffer(get(), i);
    }

    public PointerBuffer getPointerBuffer(int i) {
        return MemoryUtil.memPointerBuffer(get(), i);
    }

    public String getStringASCII() {
        return MemoryUtil.memASCII(get());
    }

    public String getStringUTF8() {
        return MemoryUtil.memUTF8(get());
    }

    public String getStringUTF16() {
        return MemoryUtil.memUTF16(get());
    }

    public ByteBuffer getByteBuffer(int i, int i2) {
        return MemoryUtil.memByteBuffer(get(i), i2);
    }

    public ShortBuffer getShortBuffer(int i, int i2) {
        return MemoryUtil.memShortBuffer(get(i), i2);
    }

    public IntBuffer getIntBuffer(int i, int i2) {
        return MemoryUtil.memIntBuffer(get(i), i2);
    }

    public LongBuffer getLongBuffer(int i, int i2) {
        return MemoryUtil.memLongBuffer(get(i), i2);
    }

    public FloatBuffer getFloatBuffer(int i, int i2) {
        return MemoryUtil.memFloatBuffer(get(i), i2);
    }

    public DoubleBuffer getDoubleBuffer(int i, int i2) {
        return MemoryUtil.memDoubleBuffer(get(i), i2);
    }

    public PointerBuffer getPointerBuffer(int i, int i2) {
        return MemoryUtil.memPointerBuffer(get(i), i2);
    }

    public String getStringASCII(int i) {
        return MemoryUtil.memASCII(get(i));
    }

    public String getStringUTF8(int i) {
        return MemoryUtil.memUTF8(get(i));
    }

    public String getStringUTF16(int i) {
        return MemoryUtil.memUTF16(get(i));
    }

    public PointerBuffer get(long[] jArr) {
        return get(jArr, 0, jArr.length);
    }

    public PointerBuffer get(long[] jArr, int i, int i2) {
        if (BITS64) {
            MemoryUtil.memLongBuffer(address(), remaining()).get(jArr, i, i2);
            position(position() + i2);
        } else {
            get32(jArr, i, i2);
        }
        return this;
    }

    private void get32(long[] jArr, int i, int i2) {
        CheckIntrinsics.checkFromIndexSize(i, i2, jArr.length);
        if (remaining() < i2) {
            throw new BufferUnderflowException();
        }
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            jArr[i4] = get();
        }
    }

    public PointerBuffer put(long[] jArr) {
        return put(jArr, 0, jArr.length);
    }

    public PointerBuffer put(long[] jArr, int i, int i2) {
        if (BITS64) {
            MemoryUtil.memLongBuffer(address(), remaining()).put(jArr, i, i2);
            position(position() + i2);
        } else {
            put32(jArr, i, i2);
        }
        return this;
    }

    private void put32(long[] jArr, int i, int i2) {
        CheckIntrinsics.checkFromIndexSize(i, i2, jArr.length);
        if (remaining() < i2) {
            throw new BufferOverflowException();
        }
        int i3 = i + i2;
        for (int i4 = i; i4 < i3; i4++) {
            put(jArr[i4]);
        }
    }

    @Override // org.lwjgl.system.Pointer.Default
    public int hashCode() {
        int i = 1;
        int position = position();
        for (int limit = limit() - 1; limit >= position; limit--) {
            i = (i * 31) + ((int) get(limit));
        }
        return i;
    }

    @Override // org.lwjgl.system.Pointer.Default
    public boolean equals(Object obj) {
        if (!(obj instanceof PointerBuffer)) {
            return false;
        }
        PointerBuffer pointerBuffer = (PointerBuffer) obj;
        if (remaining() != pointerBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = pointerBuffer.limit() - 1;
        while (limit >= position) {
            if (get(limit) == pointerBuffer.get(limit2)) {
                limit--;
                limit2--;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Comparable
    public int compareTo(PointerBuffer pointerBuffer) {
        int position = position() + Math.min(remaining(), pointerBuffer.remaining());
        int position2 = position();
        int position3 = pointerBuffer.position();
        while (position2 < position) {
            long j = get(position2);
            long j2 = pointerBuffer.get(position3);
            if (j != j2) {
                if (j < j2) {
                    return -1;
                }
                return 1;
            }
            position2++;
            position3++;
        }
        return remaining() - pointerBuffer.remaining();
    }
}
