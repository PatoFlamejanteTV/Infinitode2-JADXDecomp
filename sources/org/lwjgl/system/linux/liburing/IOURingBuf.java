package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_buf")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBuf.class */
public class IOURingBuf extends Struct<IOURingBuf> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int ADDR;
    public static final int LEN;
    public static final int BID;
    public static final int RESV;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(4), __member(2), __member(2));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        ADDR = __struct.offsetof(0);
        LEN = __struct.offsetof(1);
        BID = __struct.offsetof(2);
        RESV = __struct.offsetof(3);
    }

    protected IOURingBuf(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingBuf create(long j, ByteBuffer byteBuffer) {
        return new IOURingBuf(j, byteBuffer);
    }

    public IOURingBuf(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u64")
    public long addr() {
        return naddr(address());
    }

    @NativeType("__u32")
    public int len() {
        return nlen(address());
    }

    @NativeType("__u16")
    public short bid() {
        return nbid(address());
    }

    public IOURingBuf addr(@NativeType("__u64") long j) {
        naddr(address(), j);
        return this;
    }

    public IOURingBuf len(@NativeType("__u32") int i) {
        nlen(address(), i);
        return this;
    }

    public IOURingBuf bid(@NativeType("__u16") short s) {
        nbid(address(), s);
        return this;
    }

    public IOURingBuf set(long j, int i, short s) {
        addr(j);
        len(i);
        bid(s);
        return this;
    }

    public IOURingBuf set(IOURingBuf iOURingBuf) {
        MemoryUtil.memCopy(iOURingBuf.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingBuf malloc() {
        return new IOURingBuf(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingBuf calloc() {
        return new IOURingBuf(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingBuf create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingBuf(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingBuf create(long j) {
        return new IOURingBuf(j, null);
    }

    public static IOURingBuf createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingBuf(j, null);
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

    public static IOURingBuf malloc(MemoryStack memoryStack) {
        return new IOURingBuf(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingBuf calloc(MemoryStack memoryStack) {
        return new IOURingBuf(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long naddr(long j) {
        return UNSAFE.getLong((Object) null, j + ADDR);
    }

    public static int nlen(long j) {
        return UNSAFE.getInt((Object) null, j + LEN);
    }

    public static short nbid(long j) {
        return UNSAFE.getShort((Object) null, j + BID);
    }

    public static short nresv(long j) {
        return UNSAFE.getShort((Object) null, j + RESV);
    }

    public static void naddr(long j, long j2) {
        UNSAFE.putLong((Object) null, j + ADDR, j2);
    }

    public static void nlen(long j, int i) {
        UNSAFE.putInt((Object) null, j + LEN, i);
    }

    public static void nbid(long j, short s) {
        UNSAFE.putShort((Object) null, j + BID, s);
    }

    public static void nresv(long j, short s) {
        UNSAFE.putShort((Object) null, j + RESV, s);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBuf$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingBuf, Buffer> implements NativeResource {
        private static final IOURingBuf ELEMENT_FACTORY = IOURingBuf.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingBuf.SIZEOF);
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
        public IOURingBuf getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u64")
        public long addr() {
            return IOURingBuf.naddr(address());
        }

        @NativeType("__u32")
        public int len() {
            return IOURingBuf.nlen(address());
        }

        @NativeType("__u16")
        public short bid() {
            return IOURingBuf.nbid(address());
        }

        public Buffer addr(@NativeType("__u64") long j) {
            IOURingBuf.naddr(address(), j);
            return this;
        }

        public Buffer len(@NativeType("__u32") int i) {
            IOURingBuf.nlen(address(), i);
            return this;
        }

        public Buffer bid(@NativeType("__u16") short s) {
            IOURingBuf.nbid(address(), s);
            return this;
        }
    }
}
