package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_rsrc_register")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRSRCRegister.class */
public class IOURingRSRCRegister extends Struct<IOURingRSRCRegister> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NR;
    public static final int FLAGS;
    public static final int RESV2;
    public static final int DATA;
    public static final int TAGS;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(8), __member(8), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NR = __struct.offsetof(0);
        FLAGS = __struct.offsetof(1);
        RESV2 = __struct.offsetof(2);
        DATA = __struct.offsetof(3);
        TAGS = __struct.offsetof(4);
    }

    protected IOURingRSRCRegister(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingRSRCRegister create(long j, ByteBuffer byteBuffer) {
        return new IOURingRSRCRegister(j, byteBuffer);
    }

    public IOURingRSRCRegister(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int nr() {
        return nnr(address());
    }

    @NativeType("__u32")
    public int flags() {
        return nflags(address());
    }

    @NativeType("__u64")
    public long data() {
        return ndata(address());
    }

    @NativeType("__u64")
    public long tags() {
        return ntags(address());
    }

    public IOURingRSRCRegister nr(@NativeType("__u32") int i) {
        nnr(address(), i);
        return this;
    }

    public IOURingRSRCRegister flags(@NativeType("__u32") int i) {
        nflags(address(), i);
        return this;
    }

    public IOURingRSRCRegister data(@NativeType("__u64") long j) {
        ndata(address(), j);
        return this;
    }

    public IOURingRSRCRegister tags(@NativeType("__u64") long j) {
        ntags(address(), j);
        return this;
    }

    public IOURingRSRCRegister set(int i, int i2, long j, long j2) {
        nr(i);
        flags(i2);
        data(j);
        tags(j2);
        return this;
    }

    public IOURingRSRCRegister set(IOURingRSRCRegister iOURingRSRCRegister) {
        MemoryUtil.memCopy(iOURingRSRCRegister.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingRSRCRegister malloc() {
        return new IOURingRSRCRegister(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingRSRCRegister calloc() {
        return new IOURingRSRCRegister(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingRSRCRegister create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingRSRCRegister(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingRSRCRegister create(long j) {
        return new IOURingRSRCRegister(j, null);
    }

    public static IOURingRSRCRegister createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingRSRCRegister(j, null);
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

    public static IOURingRSRCRegister malloc(MemoryStack memoryStack) {
        return new IOURingRSRCRegister(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingRSRCRegister calloc(MemoryStack memoryStack) {
        return new IOURingRSRCRegister(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nnr(long j) {
        return UNSAFE.getInt((Object) null, j + NR);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static long nresv2(long j) {
        return UNSAFE.getLong((Object) null, j + RESV2);
    }

    public static long ndata(long j) {
        return UNSAFE.getLong((Object) null, j + DATA);
    }

    public static long ntags(long j) {
        return UNSAFE.getLong((Object) null, j + TAGS);
    }

    public static void nnr(long j, int i) {
        UNSAFE.putInt((Object) null, j + NR, i);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void nresv2(long j, long j2) {
        UNSAFE.putLong((Object) null, j + RESV2, j2);
    }

    public static void ndata(long j, long j2) {
        UNSAFE.putLong((Object) null, j + DATA, j2);
    }

    public static void ntags(long j, long j2) {
        UNSAFE.putLong((Object) null, j + TAGS, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRSRCRegister$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingRSRCRegister, Buffer> implements NativeResource {
        private static final IOURingRSRCRegister ELEMENT_FACTORY = IOURingRSRCRegister.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingRSRCRegister.SIZEOF);
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
        public IOURingRSRCRegister getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int nr() {
            return IOURingRSRCRegister.nnr(address());
        }

        @NativeType("__u32")
        public int flags() {
            return IOURingRSRCRegister.nflags(address());
        }

        @NativeType("__u64")
        public long data() {
            return IOURingRSRCRegister.ndata(address());
        }

        @NativeType("__u64")
        public long tags() {
            return IOURingRSRCRegister.ntags(address());
        }

        public Buffer nr(@NativeType("__u32") int i) {
            IOURingRSRCRegister.nnr(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("__u32") int i) {
            IOURingRSRCRegister.nflags(address(), i);
            return this;
        }

        public Buffer data(@NativeType("__u64") long j) {
            IOURingRSRCRegister.ndata(address(), j);
            return this;
        }

        public Buffer tags(@NativeType("__u64") long j) {
            IOURingRSRCRegister.ntags(address(), j);
            return this;
        }
    }
}
