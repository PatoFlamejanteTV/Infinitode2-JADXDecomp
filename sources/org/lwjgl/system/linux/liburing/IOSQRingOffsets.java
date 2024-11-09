package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_sqring_offsets")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOSQRingOffsets.class */
public class IOSQRingOffsets extends Struct<IOSQRingOffsets> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int HEAD;
    public static final int TAIL;
    public static final int RING_MASK;
    public static final int RING_ENTRIES;
    public static final int FLAGS;
    public static final int DROPPED;
    public static final int ARRAY;
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
        FLAGS = __struct.offsetof(4);
        DROPPED = __struct.offsetof(5);
        ARRAY = __struct.offsetof(6);
        RESV1 = __struct.offsetof(7);
        USER_ADDR = __struct.offsetof(8);
    }

    protected IOSQRingOffsets(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOSQRingOffsets create(long j, ByteBuffer byteBuffer) {
        return new IOSQRingOffsets(j, byteBuffer);
    }

    public IOSQRingOffsets(ByteBuffer byteBuffer) {
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
    public int flags() {
        return nflags(address());
    }

    @NativeType("__u32")
    public int dropped() {
        return ndropped(address());
    }

    @NativeType("__u32")
    public int array() {
        return narray(address());
    }

    @NativeType("__u64")
    public long user_addr() {
        return nuser_addr(address());
    }

    public IOSQRingOffsets head(@NativeType("__u32") int i) {
        nhead(address(), i);
        return this;
    }

    public IOSQRingOffsets tail(@NativeType("__u32") int i) {
        ntail(address(), i);
        return this;
    }

    public IOSQRingOffsets ring_mask(@NativeType("__u32") int i) {
        nring_mask(address(), i);
        return this;
    }

    public IOSQRingOffsets ring_entries(@NativeType("__u32") int i) {
        nring_entries(address(), i);
        return this;
    }

    public IOSQRingOffsets flags(@NativeType("__u32") int i) {
        nflags(address(), i);
        return this;
    }

    public IOSQRingOffsets dropped(@NativeType("__u32") int i) {
        ndropped(address(), i);
        return this;
    }

    public IOSQRingOffsets array(@NativeType("__u32") int i) {
        narray(address(), i);
        return this;
    }

    public IOSQRingOffsets user_addr(@NativeType("__u64") long j) {
        nuser_addr(address(), j);
        return this;
    }

    public IOSQRingOffsets set(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        head(i);
        tail(i2);
        ring_mask(i3);
        ring_entries(i4);
        flags(i5);
        dropped(i6);
        array(i7);
        user_addr(j);
        return this;
    }

    public IOSQRingOffsets set(IOSQRingOffsets iOSQRingOffsets) {
        MemoryUtil.memCopy(iOSQRingOffsets.address(), address(), SIZEOF);
        return this;
    }

    public static IOSQRingOffsets malloc() {
        return new IOSQRingOffsets(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOSQRingOffsets calloc() {
        return new IOSQRingOffsets(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOSQRingOffsets create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOSQRingOffsets(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOSQRingOffsets create(long j) {
        return new IOSQRingOffsets(j, null);
    }

    public static IOSQRingOffsets createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOSQRingOffsets(j, null);
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

    public static IOSQRingOffsets malloc(MemoryStack memoryStack) {
        return new IOSQRingOffsets(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOSQRingOffsets calloc(MemoryStack memoryStack) {
        return new IOSQRingOffsets(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static int ndropped(long j) {
        return UNSAFE.getInt((Object) null, j + DROPPED);
    }

    public static int narray(long j) {
        return UNSAFE.getInt((Object) null, j + ARRAY);
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

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void ndropped(long j, int i) {
        UNSAFE.putInt((Object) null, j + DROPPED, i);
    }

    public static void narray(long j, int i) {
        UNSAFE.putInt((Object) null, j + ARRAY, i);
    }

    public static void nresv1(long j, int i) {
        UNSAFE.putInt((Object) null, j + RESV1, i);
    }

    public static void nuser_addr(long j, long j2) {
        UNSAFE.putLong((Object) null, j + USER_ADDR, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOSQRingOffsets$Buffer.class */
    public static class Buffer extends StructBuffer<IOSQRingOffsets, Buffer> implements NativeResource {
        private static final IOSQRingOffsets ELEMENT_FACTORY = IOSQRingOffsets.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOSQRingOffsets.SIZEOF);
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
        public IOSQRingOffsets getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int head() {
            return IOSQRingOffsets.nhead(address());
        }

        @NativeType("__u32")
        public int tail() {
            return IOSQRingOffsets.ntail(address());
        }

        @NativeType("__u32")
        public int ring_mask() {
            return IOSQRingOffsets.nring_mask(address());
        }

        @NativeType("__u32")
        public int ring_entries() {
            return IOSQRingOffsets.nring_entries(address());
        }

        @NativeType("__u32")
        public int flags() {
            return IOSQRingOffsets.nflags(address());
        }

        @NativeType("__u32")
        public int dropped() {
            return IOSQRingOffsets.ndropped(address());
        }

        @NativeType("__u32")
        public int array() {
            return IOSQRingOffsets.narray(address());
        }

        @NativeType("__u64")
        public long user_addr() {
            return IOSQRingOffsets.nuser_addr(address());
        }

        public Buffer head(@NativeType("__u32") int i) {
            IOSQRingOffsets.nhead(address(), i);
            return this;
        }

        public Buffer tail(@NativeType("__u32") int i) {
            IOSQRingOffsets.ntail(address(), i);
            return this;
        }

        public Buffer ring_mask(@NativeType("__u32") int i) {
            IOSQRingOffsets.nring_mask(address(), i);
            return this;
        }

        public Buffer ring_entries(@NativeType("__u32") int i) {
            IOSQRingOffsets.nring_entries(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("__u32") int i) {
            IOSQRingOffsets.nflags(address(), i);
            return this;
        }

        public Buffer dropped(@NativeType("__u32") int i) {
            IOSQRingOffsets.ndropped(address(), i);
            return this;
        }

        public Buffer array(@NativeType("__u32") int i) {
            IOSQRingOffsets.narray(address(), i);
            return this;
        }

        public Buffer user_addr(@NativeType("__u64") long j) {
            IOSQRingOffsets.nuser_addr(address(), j);
            return this;
        }
    }
}
