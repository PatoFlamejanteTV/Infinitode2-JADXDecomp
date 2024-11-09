package org.lwjgl;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import org.lwjgl.system.CheckIntrinsics;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/CLongBuffer.class */
public class CLongBuffer extends CustomBuffer<CLongBuffer> implements Comparable<CLongBuffer> {
    protected CLongBuffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
        super(j, byteBuffer, i, i2, i3, i4);
    }

    public static CLongBuffer allocateDirect(int i) {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(BufferUtils.getAllocationSize(i, CLONG_SHIFT));
        return new CLongBuffer(MemoryUtil.memAddress(createByteBuffer), createByteBuffer, -1, 0, i, i);
    }

    public static CLongBuffer create(long j, int i) {
        return new CLongBuffer(j, null, -1, 0, i, i);
    }

    public static CLongBuffer create(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining() >> CLONG_SHIFT;
        return new CLongBuffer(MemoryUtil.memAddress(byteBuffer), byteBuffer, -1, 0, remaining, remaining);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.CustomBuffer
    public CLongBuffer self() {
        return this;
    }

    @Override // org.lwjgl.system.CustomBuffer
    public int sizeof() {
        return CLONG_SIZE;
    }

    public long get() {
        return MemoryUtil.memGetCLong(this.address + (Integer.toUnsignedLong(nextGetIndex()) * CLONG_SIZE));
    }

    public static long get(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < CLONG_SIZE) {
            throw new BufferUnderflowException();
        }
        try {
            return MemoryUtil.memGetCLong(MemoryUtil.memAddress(byteBuffer));
        } finally {
            byteBuffer.position(byteBuffer.position() + CLONG_SIZE);
        }
    }

    public CLongBuffer put(long j) {
        MemoryUtil.memPutCLong(this.address + (Integer.toUnsignedLong(nextPutIndex()) * CLONG_SIZE), j);
        return this;
    }

    public static void put(ByteBuffer byteBuffer, long j) {
        if (byteBuffer.remaining() < CLONG_SIZE) {
            throw new BufferOverflowException();
        }
        try {
            MemoryUtil.memPutCLong(MemoryUtil.memAddress(byteBuffer), j);
        } finally {
            byteBuffer.position(byteBuffer.position() + CLONG_SIZE);
        }
    }

    public long get(int i) {
        return MemoryUtil.memGetCLong(this.address + (Checks.check(i, this.limit) * CLONG_SIZE));
    }

    public static long get(ByteBuffer byteBuffer, int i) {
        CheckIntrinsics.checkFromIndexSize(i, CLONG_SIZE, byteBuffer.limit());
        return MemoryUtil.memGetCLong(MemoryUtil.memAddress0(byteBuffer) + i);
    }

    public CLongBuffer put(int i, long j) {
        MemoryUtil.memPutCLong(this.address + (Checks.check(i, this.limit) * CLONG_SIZE), j);
        return this;
    }

    public static void put(ByteBuffer byteBuffer, int i, long j) {
        CheckIntrinsics.checkFromIndexSize(i, CLONG_SIZE, byteBuffer.limit());
        MemoryUtil.memPutCLong(MemoryUtil.memAddress0(byteBuffer) + i, j);
    }

    public CLongBuffer get(long[] jArr) {
        return get(jArr, 0, jArr.length);
    }

    public CLongBuffer get(long[] jArr, int i, int i2) {
        if (CLONG_SIZE == 8) {
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

    public CLongBuffer put(long[] jArr) {
        return put(jArr, 0, jArr.length);
    }

    public CLongBuffer put(long[] jArr, int i, int i2) {
        if (CLONG_SIZE == 8) {
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
        if (!(obj instanceof CLongBuffer)) {
            return false;
        }
        CLongBuffer cLongBuffer = (CLongBuffer) obj;
        if (remaining() != cLongBuffer.remaining()) {
            return false;
        }
        int position = position();
        int limit = limit() - 1;
        int limit2 = cLongBuffer.limit() - 1;
        while (limit >= position) {
            if (get(limit) == cLongBuffer.get(limit2)) {
                limit--;
                limit2--;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override // java.lang.Comparable
    public int compareTo(CLongBuffer cLongBuffer) {
        int position = position() + Math.min(remaining(), cLongBuffer.remaining());
        int position2 = position();
        int position3 = cLongBuffer.position();
        while (position2 < position) {
            long j = get(position2);
            long j2 = cLongBuffer.get(position3);
            if (j != j2) {
                if (j < j2) {
                    return -1;
                }
                return 1;
            }
            position2++;
            position3++;
        }
        return remaining() - cLongBuffer.remaining();
    }
}
