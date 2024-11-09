package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_cqring_offsets")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOCQRingOffsets.class */
public class IOCQRingOffsets extends Struct<IOCQRingOffsets> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int HEAD;
    public static final int TAIL;
    public static final int RING_MASK;
    public static final int RING_ENTRIES;
    public static final int OVERFLOW;
    public static final int CQES;
    public static final int FLAGS;
    public static final int RESV1;
    public static final int USER_ADDR;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        HEAD = __struct.offsetof(0);
        TAIL = __struct.offsetof(1);
        RING_MASK = __struct.offsetof(2);
        RING_ENTRIES = __struct.offsetof(3);
        OVERFLOW = __struct.offsetof(4);
        CQES = __struct.offsetof(5);
        FLAGS = __struct.offsetof(6);
        RESV1 = __struct.offsetof(7);
        USER_ADDR = __struct.offsetof(8);
    }

    protected IOCQRingOffsets(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOCQRingOffsets create(long j, ByteBuffer byteBuffer) {
        return new IOCQRingOffsets(j, byteBuffer);
    }

    public IOCQRingOffsets(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int head() {
        return nhead(address());
    }

    @NativeType("__u32")
    public int tail() {
        return ntail(address());
    }

    @NativeType("__u32")
    public int ring_mask() {
        return nring_mask(address());
    }

    @NativeType("__u32")
    public int ring_entries() {
        return nring_entries(address());
    }

    @NativeType("__u32")
    public int overflow() {
        return noverflow(address());
    }

    @NativeType("__u32")
    public int cqes() {
        return ncqes(address());
    }

    @NativeType("__u32")
    public int flags() {
        return nflags(address());
    }

    @NativeType("__u64")
    public long user_addr() {
        return nuser_addr(address());
    }

    public IOCQRingOffsets head(@NativeType("__u32") int i) {
        nhead(address(), i);
        return this;
    }

    public IOCQRingOffsets tail(@NativeType("__u32") int i) {
        ntail(address(), i);
        return this;
    }

    public IOCQRingOffsets ring_mask(@NativeType("__u32") int i) {
        nring_mask(address(), i);
        return this;
    }

    public IOCQRingOffsets ring_entries(@NativeType("__u32") int i) {
        nring_entries(address(), i);
        return this;
    }

    public IOCQRingOffsets overflow(@NativeType("__u32") int i) {
        noverflow(address(), i);
        return this;
    }

    public IOCQRingOffsets cqes(@NativeType("__u32") int i) {
        ncqes(address(), i);
        return this;
    }

    public IOCQRingOffsets flags(@NativeType("__u32") int i) {
        nflags(address(), i);
        return this;
    }

    public IOCQRingOffsets user_addr(@NativeType("__u64") long j) {
        nuser_addr(address(), j);
        return this;
    }

    public IOCQRingOffsets set(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        head(i);
        tail(i2);
        ring_mask(i3);
        ring_entries(i4);
        overflow(i5);
        cqes(i6);
        flags(i7);
        user_addr(j);
        return this;
    }

    public IOCQRingOffsets set(IOCQRingOffsets iOCQRingOffsets) {
        MemoryUtil.memCopy(iOCQRingOffsets.address(), address(), SIZEOF);
        return this;
    }

    public static IOCQRingOffsets malloc() {
        return new IOCQRingOffsets(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOCQRingOffsets calloc() {
        return new IOCQRingOffsets(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOCQRingOffsets create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOCQRingOffsets(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOCQRingOffsets create(long j) {
        return new IOCQRingOffsets(j, null);
    }

    public static IOCQRingOffsets createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOCQRingOffsets(j, null);
    }

    public static Buffer malloc(int i) {
        return new Buffer(MemoryUtil.nmemAllocChecked(__checkMalloc(i, SIZEOF)), i);
    }

    public static Buffer calloc(int i) {
        return new Buffer(MemoryUtil.nmemCallocChecked(i, SIZEOF), i);
    }

    public static Buffer create(int i) {
        ByteBuffer __create = __create(i, SIZEOF);
        return new Buffer(MemoryUtil.memAddress(__create), __create, -1, 0, i, i);
    }

    public static Buffer create(long j, int i) {
        return new Buffer(j, i);
    }

    public static Buffer createSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return new Buffer(j, i);
    }

    public static IOCQRingOffsets malloc(MemoryStack memoryStack) {
        return new IOCQRingOffsets(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOCQRingOffsets calloc(MemoryStack memoryStack) {
        return new IOCQRingOffsets(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nhead(long j) {
        return UNSAFE.getInt((Object) null, j + HEAD);
    }

    public static int ntail(long j) {
        return UNSAFE.getInt((Object) null, j + TAIL);
    }

    public static int nring_mask(long j) {
        return UNSAFE.getInt((Object) null, j + RING_MASK);
    }

    public static int nring_entries(long j) {
        return UNSAFE.getInt((Object) null, j + RING_ENTRIES);
    }

    public static int noverflow(long j) {
        return UNSAFE.getInt((Object) null, j + OVERFLOW);
    }

    public static int ncqes(long j) {
        return UNSAFE.getInt((Object) null, j + CQES);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static int nresv1(long j) {
        return UNSAFE.getInt((Object) null, j + RESV1);
    }

    public static long nuser_addr(long j) {
        return UNSAFE.getLong((Object) null, j + USER_ADDR);
    }

    public static void nhead(long j, int i) {
        UNSAFE.putInt((Object) null, j + HEAD, i);
    }

    public static void ntail(long j, int i) {
        UNSAFE.putInt((Object) null, j + TAIL, i);
    }

    public static void nring_mask(long j, int i) {
        UNSAFE.putInt((Object) null, j + RING_MASK, i);
    }

    public static void nring_entries(long j, int i) {
        UNSAFE.putInt((Object) null, j + RING_ENTRIES, i);
    }

    public static void noverflow(long j, int i) {
        UNSAFE.putInt((Object) null, j + OVERFLOW, i);
    }

    public static void ncqes(long j, int i) {
        UNSAFE.putInt((Object) null, j + CQES, i);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void nresv1(long j, int i) {
        UNSAFE.putInt((Object) null, j + RESV1, i);
    }

    public static void nuser_addr(long j, long j2) {
        UNSAFE.putLong((Object) null, j + USER_ADDR, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOCQRingOffsets$Buffer.class */
    public static class Buffer extends StructBuffer<IOCQRingOffsets, Buffer> implements NativeResource {
        private static final IOCQRingOffsets ELEMENT_FACTORY = IOCQRingOffsets.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOCQRingOffsets.SIZEOF);
        }

        public Buffer(long j, int i) {
            super(j, null, -1, 0, i, i);
        }

        Buffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
            super(j, byteBuffer, i, i2, i3, i4);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.lwjgl.system.CustomBuffer
        public Buffer self() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.lwjgl.system.StructBuffer
        public IOCQRingOffsets getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int head() {
            return IOCQRingOffsets.nhead(address());
        }

        @NativeType("__u32")
        public int tail() {
            return IOCQRingOffsets.ntail(address());
        }

        @NativeType("__u32")
        public int ring_mask() {
            return IOCQRingOffsets.nring_mask(address());
        }

        @NativeType("__u32")
        public int ring_entries() {
            return IOCQRingOffsets.nring_entries(address());
        }

        @NativeType("__u32")
        public int overflow() {
            return IOCQRingOffsets.noverflow(address());
        }

        @NativeType("__u32")
        public int cqes() {
            return IOCQRingOffsets.ncqes(address());
        }

        @NativeType("__u32")
        public int flags() {
            return IOCQRingOffsets.nflags(address());
        }

        @NativeType("__u64")
        public long user_addr() {
            return IOCQRingOffsets.nuser_addr(address());
        }

        public Buffer head(@NativeType("__u32") int i) {
            IOCQRingOffsets.nhead(address(), i);
            return this;
        }

        public Buffer tail(@NativeType("__u32") int i) {
            IOCQRingOffsets.ntail(address(), i);
            return this;
        }

        public Buffer ring_mask(@NativeType("__u32") int i) {
            IOCQRingOffsets.nring_mask(address(), i);
            return this;
        }

        public Buffer ring_entries(@NativeType("__u32") int i) {
            IOCQRingOffsets.nring_entries(address(), i);
            return this;
        }

        public Buffer overflow(@NativeType("__u32") int i) {
            IOCQRingOffsets.noverflow(address(), i);
            return this;
        }

        public Buffer cqes(@NativeType("__u32") int i) {
            IOCQRingOffsets.ncqes(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("__u32") int i) {
            IOCQRingOffsets.nflags(address(), i);
            return this;
        }

        public Buffer user_addr(@NativeType("__u64") long j) {
            IOCQRingOffsets.nuser_addr(address(), j);
            return this;
        }
    }
}
