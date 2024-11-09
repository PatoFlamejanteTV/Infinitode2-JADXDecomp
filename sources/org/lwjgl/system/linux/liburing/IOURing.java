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

@NativeType("struct io_uring")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURing.class */
public class IOURing extends Struct<IOURing> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int SQ;
    public static final int CQ;
    public static final int FLAGS;
    public static final int RING_FD;
    public static final int FEATURES;
    public static final int ENTER_RING_FD;
    public static final int INT_FLAGS;
    public static final int PAD;
    public static final int PAD2;

    static {
        Struct.Layout __struct = __struct(__member(IOURingSQ.SIZEOF, IOURingSQ.ALIGNOF), __member(IOURingCQ.SIZEOF, IOURingCQ.ALIGNOF), __member(4), __member(4), __member(4), __member(4), __member(1), __array(1, 3), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        SQ = __struct.offsetof(0);
        CQ = __struct.offsetof(1);
        FLAGS = __struct.offsetof(2);
        RING_FD = __struct.offsetof(3);
        FEATURES = __struct.offsetof(4);
        ENTER_RING_FD = __struct.offsetof(5);
        INT_FLAGS = __struct.offsetof(6);
        PAD = __struct.offsetof(7);
        PAD2 = __struct.offsetof(8);
    }

    protected IOURing(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURing create(long j, ByteBuffer byteBuffer) {
        return new IOURing(j, byteBuffer);
    }

    public IOURing(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("struct io_uring_sq")
    public IOURingSQ sq() {
        return nsq(address());
    }

    @NativeType("struct io_uring_cq")
    public IOURingCQ cq() {
        return ncq(address());
    }

    @NativeType("unsigned int")
    public int flags() {
        return nflags(address());
    }

    public int ring_fd() {
        return nring_fd(address());
    }

    @NativeType("unsigned int")
    public int features() {
        return nfeatures(address());
    }

    public int enter_ring_fd() {
        return nenter_ring_fd(address());
    }

    @NativeType("__u8")
    public byte int_flags() {
        return nint_flags(address());
    }

    public IOURing sq(@NativeType("struct io_uring_sq") IOURingSQ iOURingSQ) {
        nsq(address(), iOURingSQ);
        return this;
    }

    public IOURing sq(Consumer<IOURingSQ> consumer) {
        consumer.accept(sq());
        return this;
    }

    public IOURing cq(@NativeType("struct io_uring_cq") IOURingCQ iOURingCQ) {
        ncq(address(), iOURingCQ);
        return this;
    }

    public IOURing cq(Consumer<IOURingCQ> consumer) {
        consumer.accept(cq());
        return this;
    }

    public IOURing flags(@NativeType("unsigned int") int i) {
        nflags(address(), i);
        return this;
    }

    public IOURing ring_fd(int i) {
        nring_fd(address(), i);
        return this;
    }

    public IOURing features(@NativeType("unsigned int") int i) {
        nfeatures(address(), i);
        return this;
    }

    public IOURing enter_ring_fd(int i) {
        nenter_ring_fd(address(), i);
        return this;
    }

    public IOURing int_flags(@NativeType("__u8") byte b2) {
        nint_flags(address(), b2);
        return this;
    }

    public IOURing set(IOURingSQ iOURingSQ, IOURingCQ iOURingCQ, int i, int i2, int i3, int i4, byte b2) {
        sq(iOURingSQ);
        cq(iOURingCQ);
        flags(i);
        ring_fd(i2);
        features(i3);
        enter_ring_fd(i4);
        int_flags(b2);
        return this;
    }

    public IOURing set(IOURing iOURing) {
        MemoryUtil.memCopy(iOURing.address(), address(), SIZEOF);
        return this;
    }

    public static IOURing malloc() {
        return new IOURing(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURing calloc() {
        return new IOURing(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURing create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURing(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURing create(long j) {
        return new IOURing(j, null);
    }

    public static IOURing createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURing(j, null);
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

    public static IOURing malloc(MemoryStack memoryStack) {
        return new IOURing(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURing calloc(MemoryStack memoryStack) {
        return new IOURing(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static IOURingSQ nsq(long j) {
        return IOURingSQ.create(j + SQ);
    }

    public static IOURingCQ ncq(long j) {
        return IOURingCQ.create(j + CQ);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static int nring_fd(long j) {
        return UNSAFE.getInt((Object) null, j + RING_FD);
    }

    public static int nfeatures(long j) {
        return UNSAFE.getInt((Object) null, j + FEATURES);
    }

    public static int nenter_ring_fd(long j) {
        return UNSAFE.getInt((Object) null, j + ENTER_RING_FD);
    }

    public static byte nint_flags(long j) {
        return UNSAFE.getByte((Object) null, j + INT_FLAGS);
    }

    public static ByteBuffer npad(long j) {
        return MemoryUtil.memByteBuffer(j + PAD, 3);
    }

    public static byte npad(long j, int i) {
        return UNSAFE.getByte((Object) null, j + PAD + Checks.check(i, 3));
    }

    public static int npad2(long j) {
        return UNSAFE.getInt((Object) null, j + PAD2);
    }

    public static void nsq(long j, IOURingSQ iOURingSQ) {
        MemoryUtil.memCopy(iOURingSQ.address(), j + SQ, IOURingSQ.SIZEOF);
    }

    public static void ncq(long j, IOURingCQ iOURingCQ) {
        MemoryUtil.memCopy(iOURingCQ.address(), j + CQ, IOURingCQ.SIZEOF);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void nring_fd(long j, int i) {
        UNSAFE.putInt((Object) null, j + RING_FD, i);
    }

    public static void nfeatures(long j, int i) {
        UNSAFE.putInt((Object) null, j + FEATURES, i);
    }

    public static void nenter_ring_fd(long j, int i) {
        UNSAFE.putInt((Object) null, j + ENTER_RING_FD, i);
    }

    public static void nint_flags(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + INT_FLAGS, b2);
    }

    public static void npad(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 3);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + PAD, byteBuffer.remaining());
    }

    public static void npad(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + PAD + Checks.check(i, 3), b2);
    }

    public static void npad2(long j, int i) {
        UNSAFE.putInt((Object) null, j + PAD2, i);
    }

    public static void validate(long j) {
        IOURingSQ.validate(j + SQ);
        IOURingCQ.validate(j + CQ);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURing$Buffer.class */
    public static class Buffer extends StructBuffer<IOURing, Buffer> implements NativeResource {
        private static final IOURing ELEMENT_FACTORY = IOURing.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURing.SIZEOF);
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
        public IOURing getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("struct io_uring_sq")
        public IOURingSQ sq() {
            return IOURing.nsq(address());
        }

        @NativeType("struct io_uring_cq")
        public IOURingCQ cq() {
            return IOURing.ncq(address());
        }

        @NativeType("unsigned int")
        public int flags() {
            return IOURing.nflags(address());
        }

        public int ring_fd() {
            return IOURing.nring_fd(address());
        }

        @NativeType("unsigned int")
        public int features() {
            return IOURing.nfeatures(address());
        }

        public int enter_ring_fd() {
            return IOURing.nenter_ring_fd(address());
        }

        @NativeType("__u8")
        public byte int_flags() {
            return IOURing.nint_flags(address());
        }

        public Buffer sq(@NativeType("struct io_uring_sq") IOURingSQ iOURingSQ) {
            IOURing.nsq(address(), iOURingSQ);
            return this;
        }

        public Buffer sq(Consumer<IOURingSQ> consumer) {
            consumer.accept(sq());
            return this;
        }

        public Buffer cq(@NativeType("struct io_uring_cq") IOURingCQ iOURingCQ) {
            IOURing.ncq(address(), iOURingCQ);
            return this;
        }

        public Buffer cq(Consumer<IOURingCQ> consumer) {
            consumer.accept(cq());
            return this;
        }

        public Buffer flags(@NativeType("unsigned int") int i) {
            IOURing.nflags(address(), i);
            return this;
        }

        public Buffer ring_fd(int i) {
            IOURing.nring_fd(address(), i);
            return this;
        }

        public Buffer features(@NativeType("unsigned int") int i) {
            IOURing.nfeatures(address(), i);
            return this;
        }

        public Buffer enter_ring_fd(int i) {
            IOURing.nenter_ring_fd(address(), i);
            return this;
        }

        public Buffer int_flags(@NativeType("__u8") byte b2) {
            IOURing.nint_flags(address(), b2);
            return this;
        }
    }
}
