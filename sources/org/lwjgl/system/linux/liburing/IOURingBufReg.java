package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_buf_reg")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBufReg.class */
public class IOURingBufReg extends Struct<IOURingBufReg> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int RING_ADDR;
    public static final int RING_ENTRIES;
    public static final int BGID;
    public static final int FLAGS;
    public static final int RESV;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(4), __member(2), __member(2), __array(8, 3));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        RING_ADDR = __struct.offsetof(0);
        RING_ENTRIES = __struct.offsetof(1);
        BGID = __struct.offsetof(2);
        FLAGS = __struct.offsetof(3);
        RESV = __struct.offsetof(4);
    }

    protected IOURingBufReg(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingBufReg create(long j, ByteBuffer byteBuffer) {
        return new IOURingBufReg(j, byteBuffer);
    }

    public IOURingBufReg(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u64")
    public long ring_addr() {
        return nring_addr(address());
    }

    @NativeType("__u32")
    public int ring_entries() {
        return nring_entries(address());
    }

    @NativeType("__u16")
    public short bgid() {
        return nbgid(address());
    }

    @NativeType("__u16")
    public short flags() {
        return nflags(address());
    }

    public IOURingBufReg ring_addr(@NativeType("__u64") long j) {
        nring_addr(address(), j);
        return this;
    }

    public IOURingBufReg ring_entries(@NativeType("__u32") int i) {
        nring_entries(address(), i);
        return this;
    }

    public IOURingBufReg bgid(@NativeType("__u16") short s) {
        nbgid(address(), s);
        return this;
    }

    public IOURingBufReg flags(@NativeType("__u16") short s) {
        nflags(address(), s);
        return this;
    }

    public IOURingBufReg set(long j, int i, short s, short s2) {
        ring_addr(j);
        ring_entries(i);
        bgid(s);
        flags(s2);
        return this;
    }

    public IOURingBufReg set(IOURingBufReg iOURingBufReg) {
        MemoryUtil.memCopy(iOURingBufReg.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingBufReg malloc() {
        return new IOURingBufReg(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingBufReg calloc() {
        return new IOURingBufReg(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingBufReg create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingBufReg(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingBufReg create(long j) {
        return new IOURingBufReg(j, null);
    }

    public static IOURingBufReg createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingBufReg(j, null);
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

    public static IOURingBufReg malloc(MemoryStack memoryStack) {
        return new IOURingBufReg(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingBufReg calloc(MemoryStack memoryStack) {
        return new IOURingBufReg(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nring_addr(long j) {
        return UNSAFE.getLong((Object) null, j + RING_ADDR);
    }

    public static int nring_entries(long j) {
        return UNSAFE.getInt((Object) null, j + RING_ENTRIES);
    }

    public static short nbgid(long j) {
        return UNSAFE.getShort((Object) null, j + BGID);
    }

    public static short nflags(long j) {
        return UNSAFE.getShort((Object) null, j + FLAGS);
    }

    public static LongBuffer nresv(long j) {
        return MemoryUtil.memLongBuffer(j + RESV, 3);
    }

    public static long nresv(long j, int i) {
        return UNSAFE.getLong((Object) null, j + RESV + (Checks.check(i, 3) << 3));
    }

    public static void nring_addr(long j, long j2) {
        UNSAFE.putLong((Object) null, j + RING_ADDR, j2);
    }

    public static void nring_entries(long j, int i) {
        UNSAFE.putInt((Object) null, j + RING_ENTRIES, i);
    }

    public static void nbgid(long j, short s) {
        UNSAFE.putShort((Object) null, j + BGID, s);
    }

    public static void nflags(long j, short s) {
        UNSAFE.putShort((Object) null, j + FLAGS, s);
    }

    public static void nresv(long j, LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(longBuffer, 3);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(longBuffer), j + RESV, longBuffer.remaining() << 3);
    }

    public static void nresv(long j, int i, long j2) {
        UNSAFE.putLong((Object) null, j + RESV + (Checks.check(i, 3) << 3), j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBufReg$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingBufReg, Buffer> implements NativeResource {
        private static final IOURingBufReg ELEMENT_FACTORY = IOURingBufReg.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingBufReg.SIZEOF);
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
        public IOURingBufReg getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u64")
        public long ring_addr() {
            return IOURingBufReg.nring_addr(address());
        }

        @NativeType("__u32")
        public int ring_entries() {
            return IOURingBufReg.nring_entries(address());
        }

        @NativeType("__u16")
        public short bgid() {
            return IOURingBufReg.nbgid(address());
        }

        @NativeType("__u16")
        public short flags() {
            return IOURingBufReg.nflags(address());
        }

        public Buffer ring_addr(@NativeType("__u64") long j) {
            IOURingBufReg.nring_addr(address(), j);
            return this;
        }

        public Buffer ring_entries(@NativeType("__u32") int i) {
            IOURingBufReg.nring_entries(address(), i);
            return this;
        }

        public Buffer bgid(@NativeType("__u16") short s) {
            IOURingBufReg.nbgid(address(), s);
            return this;
        }

        public Buffer flags(@NativeType("__u16") short s) {
            IOURingBufReg.nflags(address(), s);
            return this;
        }
    }
}
