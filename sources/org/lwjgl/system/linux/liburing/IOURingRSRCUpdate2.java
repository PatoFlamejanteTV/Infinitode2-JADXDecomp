package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_rsrc_update2")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRSRCUpdate2.class */
public class IOURingRSRCUpdate2 extends Struct<IOURingRSRCUpdate2> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int OFFSET;
    public static final int RESV;
    public static final int DATA;
    public static final int TAGS;
    public static final int NR;
    public static final int RESV2;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(8), __member(8), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        OFFSET = __struct.offsetof(0);
        RESV = __struct.offsetof(1);
        DATA = __struct.offsetof(2);
        TAGS = __struct.offsetof(3);
        NR = __struct.offsetof(4);
        RESV2 = __struct.offsetof(5);
    }

    protected IOURingRSRCUpdate2(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingRSRCUpdate2 create(long j, ByteBuffer byteBuffer) {
        return new IOURingRSRCUpdate2(j, byteBuffer);
    }

    public IOURingRSRCUpdate2(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int offset() {
        return noffset(address());
    }

    @NativeType("__u64")
    public long data() {
        return ndata(address());
    }

    @NativeType("__u64")
    public long tags() {
        return ntags(address());
    }

    @NativeType("__u32")
    public int nr() {
        return nnr(address());
    }

    public IOURingRSRCUpdate2 offset(@NativeType("__u32") int i) {
        noffset(address(), i);
        return this;
    }

    public IOURingRSRCUpdate2 data(@NativeType("__u64") long j) {
        ndata(address(), j);
        return this;
    }

    public IOURingRSRCUpdate2 tags(@NativeType("__u64") long j) {
        ntags(address(), j);
        return this;
    }

    public IOURingRSRCUpdate2 nr(@NativeType("__u32") int i) {
        nnr(address(), i);
        return this;
    }

    public IOURingRSRCUpdate2 set(int i, long j, long j2, int i2) {
        offset(i);
        data(j);
        tags(j2);
        nr(i2);
        return this;
    }

    public IOURingRSRCUpdate2 set(IOURingRSRCUpdate2 iOURingRSRCUpdate2) {
        MemoryUtil.memCopy(iOURingRSRCUpdate2.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingRSRCUpdate2 malloc() {
        return new IOURingRSRCUpdate2(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingRSRCUpdate2 calloc() {
        return new IOURingRSRCUpdate2(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingRSRCUpdate2 create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingRSRCUpdate2(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingRSRCUpdate2 create(long j) {
        return new IOURingRSRCUpdate2(j, null);
    }

    public static IOURingRSRCUpdate2 createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingRSRCUpdate2(j, null);
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

    public static IOURingRSRCUpdate2 malloc(MemoryStack memoryStack) {
        return new IOURingRSRCUpdate2(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingRSRCUpdate2 calloc(MemoryStack memoryStack) {
        return new IOURingRSRCUpdate2(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int noffset(long j) {
        return UNSAFE.getInt((Object) null, j + OFFSET);
    }

    public static int nresv(long j) {
        return UNSAFE.getInt((Object) null, j + RESV);
    }

    public static long ndata(long j) {
        return UNSAFE.getLong((Object) null, j + DATA);
    }

    public static long ntags(long j) {
        return UNSAFE.getLong((Object) null, j + TAGS);
    }

    public static int nnr(long j) {
        return UNSAFE.getInt((Object) null, j + NR);
    }

    public static int nresv2(long j) {
        return UNSAFE.getInt((Object) null, j + RESV2);
    }

    public static void noffset(long j, int i) {
        UNSAFE.putInt((Object) null, j + OFFSET, i);
    }

    public static void nresv(long j, int i) {
        UNSAFE.putInt((Object) null, j + RESV, i);
    }

    public static void ndata(long j, long j2) {
        UNSAFE.putLong((Object) null, j + DATA, j2);
    }

    public static void ntags(long j, long j2) {
        UNSAFE.putLong((Object) null, j + TAGS, j2);
    }

    public static void nnr(long j, int i) {
        UNSAFE.putInt((Object) null, j + NR, i);
    }

    public static void nresv2(long j, int i) {
        UNSAFE.putInt((Object) null, j + RESV2, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRSRCUpdate2$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingRSRCUpdate2, Buffer> implements NativeResource {
        private static final IOURingRSRCUpdate2 ELEMENT_FACTORY = IOURingRSRCUpdate2.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingRSRCUpdate2.SIZEOF);
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
        public IOURingRSRCUpdate2 getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int offset() {
            return IOURingRSRCUpdate2.noffset(address());
        }

        @NativeType("__u64")
        public long data() {
            return IOURingRSRCUpdate2.ndata(address());
        }

        @NativeType("__u64")
        public long tags() {
            return IOURingRSRCUpdate2.ntags(address());
        }

        @NativeType("__u32")
        public int nr() {
            return IOURingRSRCUpdate2.nnr(address());
        }

        public Buffer offset(@NativeType("__u32") int i) {
            IOURingRSRCUpdate2.noffset(address(), i);
            return this;
        }

        public Buffer data(@NativeType("__u64") long j) {
            IOURingRSRCUpdate2.ndata(address(), j);
            return this;
        }

        public Buffer tags(@NativeType("__u64") long j) {
            IOURingRSRCUpdate2.ntags(address(), j);
            return this;
        }

        public Buffer nr(@NativeType("__u32") int i) {
            IOURingRSRCUpdate2.nnr(address(), i);
            return this;
        }
    }
}
