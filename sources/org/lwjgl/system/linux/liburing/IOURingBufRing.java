package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;
import org.lwjgl.system.linux.liburing.IOURingBuf;

@NativeType("struct io_uring_buf_ring")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBufRing.class */
public class IOURingBufRing extends Struct<IOURingBufRing> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int RESV1;
    public static final int RESV2;
    public static final int RESV3;
    public static final int TAIL;
    public static final int BUFS;

    static {
        Struct.Layout __struct = __struct(__union(__struct(__member(8), __member(4), __member(2), __member(2)), __array(IOURingBuf.SIZEOF, IOURingBuf.ALIGNOF, 0)));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        RESV1 = __struct.offsetof(2);
        RESV2 = __struct.offsetof(3);
        RESV3 = __struct.offsetof(4);
        TAIL = __struct.offsetof(5);
        BUFS = __struct.offsetof(6);
    }

    protected IOURingBufRing(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingBufRing create(long j, ByteBuffer byteBuffer) {
        return new IOURingBufRing(j, byteBuffer);
    }

    public IOURingBufRing(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u64")
    public long resv1() {
        return nresv1(address());
    }

    @NativeType("__u32")
    public int resv2() {
        return nresv2(address());
    }

    @NativeType("__u16")
    public short resv3() {
        return nresv3(address());
    }

    @NativeType("__u16")
    public short tail() {
        return ntail(address());
    }

    @NativeType("struct io_uring_buf[0]")
    public IOURingBuf.Buffer bufs() {
        return nbufs(address());
    }

    @NativeType("struct io_uring_buf")
    public IOURingBuf bufs(int i) {
        return nbufs(address(), i);
    }

    public IOURingBufRing resv1(@NativeType("__u64") long j) {
        nresv1(address(), j);
        return this;
    }

    public IOURingBufRing resv2(@NativeType("__u32") int i) {
        nresv2(address(), i);
        return this;
    }

    public IOURingBufRing resv3(@NativeType("__u16") short s) {
        nresv3(address(), s);
        return this;
    }

    public IOURingBufRing tail(@NativeType("__u16") short s) {
        ntail(address(), s);
        return this;
    }

    public IOURingBufRing bufs(@NativeType("struct io_uring_buf[0]") IOURingBuf.Buffer buffer) {
        nbufs(address(), buffer);
        return this;
    }

    public IOURingBufRing bufs(int i, @NativeType("struct io_uring_buf") IOURingBuf iOURingBuf) {
        nbufs(address(), i, iOURingBuf);
        return this;
    }

    public IOURingBufRing bufs(Consumer<IOURingBuf.Buffer> consumer) {
        consumer.accept(bufs());
        return this;
    }

    public IOURingBufRing bufs(int i, Consumer<IOURingBuf> consumer) {
        consumer.accept(bufs(i));
        return this;
    }

    public IOURingBufRing set(IOURingBufRing iOURingBufRing) {
        MemoryUtil.memCopy(iOURingBufRing.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingBufRing malloc() {
        return new IOURingBufRing(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingBufRing calloc() {
        return new IOURingBufRing(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingBufRing create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingBufRing(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingBufRing create(long j) {
        return new IOURingBufRing(j, null);
    }

    public static IOURingBufRing createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingBufRing(j, null);
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

    public static IOURingBufRing malloc(MemoryStack memoryStack) {
        return new IOURingBufRing(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingBufRing calloc(MemoryStack memoryStack) {
        return new IOURingBufRing(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nresv1(long j) {
        return UNSAFE.getLong((Object) null, j + RESV1);
    }

    public static int nresv2(long j) {
        return UNSAFE.getInt((Object) null, j + RESV2);
    }

    public static short nresv3(long j) {
        return UNSAFE.getShort((Object) null, j + RESV3);
    }

    public static short ntail(long j) {
        return UNSAFE.getShort((Object) null, j + TAIL);
    }

    public static IOURingBuf.Buffer nbufs(long j) {
        return IOURingBuf.create(j + BUFS, 0);
    }

    public static IOURingBuf nbufs(long j, int i) {
        return IOURingBuf.create(j + BUFS + (Checks.check(i, 0) * IOURingBuf.SIZEOF));
    }

    public static void nresv1(long j, long j2) {
        UNSAFE.putLong((Object) null, j + RESV1, j2);
    }

    public static void nresv2(long j, int i) {
        UNSAFE.putInt((Object) null, j + RESV2, i);
    }

    public static void nresv3(long j, short s) {
        UNSAFE.putShort((Object) null, j + RESV3, s);
    }

    public static void ntail(long j, short s) {
        UNSAFE.putShort((Object) null, j + TAIL, s);
    }

    public static void nbufs(long j, IOURingBuf.Buffer buffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(buffer, 0);
        }
        MemoryUtil.memCopy(buffer.address(), j + BUFS, buffer.remaining() * IOURingBuf.SIZEOF);
    }

    public static void nbufs(long j, int i, IOURingBuf iOURingBuf) {
        MemoryUtil.memCopy(iOURingBuf.address(), j + BUFS + (Checks.check(i, 0) * IOURingBuf.SIZEOF), IOURingBuf.SIZEOF);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBufRing$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingBufRing, Buffer> implements NativeResource {
        private static final IOURingBufRing ELEMENT_FACTORY = IOURingBufRing.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingBufRing.SIZEOF);
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
        public IOURingBufRing getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u64")
        public long resv1() {
            return IOURingBufRing.nresv1(address());
        }

        @NativeType("__u32")
        public int resv2() {
            return IOURingBufRing.nresv2(address());
        }

        @NativeType("__u16")
        public short resv3() {
            return IOURingBufRing.nresv3(address());
        }

        @NativeType("__u16")
        public short tail() {
            return IOURingBufRing.ntail(address());
        }

        @NativeType("struct io_uring_buf[0]")
        public IOURingBuf.Buffer bufs() {
            return IOURingBufRing.nbufs(address());
        }

        @NativeType("struct io_uring_buf")
        public IOURingBuf bufs(int i) {
            return IOURingBufRing.nbufs(address(), i);
        }

        public Buffer resv1(@NativeType("__u64") long j) {
            IOURingBufRing.nresv1(address(), j);
            return this;
        }

        public Buffer resv2(@NativeType("__u32") int i) {
            IOURingBufRing.nresv2(address(), i);
            return this;
        }

        public Buffer resv3(@NativeType("__u16") short s) {
            IOURingBufRing.nresv3(address(), s);
            return this;
        }

        public Buffer tail(@NativeType("__u16") short s) {
            IOURingBufRing.ntail(address(), s);
            return this;
        }

        public Buffer bufs(@NativeType("struct io_uring_buf[0]") IOURingBuf.Buffer buffer) {
            IOURingBufRing.nbufs(address(), buffer);
            return this;
        }

        public Buffer bufs(int i, @NativeType("struct io_uring_buf") IOURingBuf iOURingBuf) {
            IOURingBufRing.nbufs(address(), i, iOURingBuf);
            return this;
        }

        public Buffer bufs(Consumer<IOURingBuf.Buffer> consumer) {
            consumer.accept(bufs());
            return this;
        }

        public Buffer bufs(int i, Consumer<IOURingBuf> consumer) {
            consumer.accept(bufs(i));
            return this;
        }
    }
}
