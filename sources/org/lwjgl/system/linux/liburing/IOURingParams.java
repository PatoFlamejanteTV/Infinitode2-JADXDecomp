package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.function.Consumer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_params")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingParams.class */
public class IOURingParams extends Struct<IOURingParams> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int SQ_ENTRIES;
    public static final int CQ_ENTRIES;
    public static final int FLAGS;
    public static final int SQ_THREAD_CPU;
    public static final int SQ_THREAD_IDLE;
    public static final int FEATURES;
    public static final int WQ_FD;
    public static final int RESV;
    public static final int SQ_OFF;
    public static final int CQ_OFF;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __array(4, 3), __member(IOSQRingOffsets.SIZEOF, IOSQRingOffsets.ALIGNOF), __member(IOCQRingOffsets.SIZEOF, IOCQRingOffsets.ALIGNOF));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        SQ_ENTRIES = __struct.offsetof(0);
        CQ_ENTRIES = __struct.offsetof(1);
        FLAGS = __struct.offsetof(2);
        SQ_THREAD_CPU = __struct.offsetof(3);
        SQ_THREAD_IDLE = __struct.offsetof(4);
        FEATURES = __struct.offsetof(5);
        WQ_FD = __struct.offsetof(6);
        RESV = __struct.offsetof(7);
        SQ_OFF = __struct.offsetof(8);
        CQ_OFF = __struct.offsetof(9);
    }

    protected IOURingParams(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingParams create(long j, ByteBuffer byteBuffer) {
        return new IOURingParams(j, byteBuffer);
    }

    public IOURingParams(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int sq_entries() {
        return nsq_entries(address());
    }

    @NativeType("__u32")
    public int cq_entries() {
        return ncq_entries(address());
    }

    @NativeType("__u32")
    public int flags() {
        return nflags(address());
    }

    @NativeType("__u32")
    public int sq_thread_cpu() {
        return nsq_thread_cpu(address());
    }

    @NativeType("__u32")
    public int sq_thread_idle() {
        return nsq_thread_idle(address());
    }

    @NativeType("__u32")
    public int features() {
        return nfeatures(address());
    }

    @NativeType("__u32")
    public int wq_fd() {
        return nwq_fd(address());
    }

    @NativeType("struct io_sqring_offsets")
    public IOSQRingOffsets sq_off() {
        return nsq_off(address());
    }

    @NativeType("struct io_cqring_offsets")
    public IOCQRingOffsets cq_off() {
        return ncq_off(address());
    }

    public IOURingParams sq_entries(@NativeType("__u32") int i) {
        nsq_entries(address(), i);
        return this;
    }

    public IOURingParams cq_entries(@NativeType("__u32") int i) {
        ncq_entries(address(), i);
        return this;
    }

    public IOURingParams flags(@NativeType("__u32") int i) {
        nflags(address(), i);
        return this;
    }

    public IOURingParams sq_thread_cpu(@NativeType("__u32") int i) {
        nsq_thread_cpu(address(), i);
        return this;
    }

    public IOURingParams sq_thread_idle(@NativeType("__u32") int i) {
        nsq_thread_idle(address(), i);
        return this;
    }

    public IOURingParams features(@NativeType("__u32") int i) {
        nfeatures(address(), i);
        return this;
    }

    public IOURingParams wq_fd(@NativeType("__u32") int i) {
        nwq_fd(address(), i);
        return this;
    }

    public IOURingParams sq_off(@NativeType("struct io_sqring_offsets") IOSQRingOffsets iOSQRingOffsets) {
        nsq_off(address(), iOSQRingOffsets);
        return this;
    }

    public IOURingParams sq_off(Consumer<IOSQRingOffsets> consumer) {
        consumer.accept(sq_off());
        return this;
    }

    public IOURingParams cq_off(@NativeType("struct io_cqring_offsets") IOCQRingOffsets iOCQRingOffsets) {
        ncq_off(address(), iOCQRingOffsets);
        return this;
    }

    public IOURingParams cq_off(Consumer<IOCQRingOffsets> consumer) {
        consumer.accept(cq_off());
        return this;
    }

    public IOURingParams set(int i, int i2, int i3, int i4, int i5, int i6, int i7, IOSQRingOffsets iOSQRingOffsets, IOCQRingOffsets iOCQRingOffsets) {
        sq_entries(i);
        cq_entries(i2);
        flags(i3);
        sq_thread_cpu(i4);
        sq_thread_idle(i5);
        features(i6);
        wq_fd(i7);
        sq_off(iOSQRingOffsets);
        cq_off(iOCQRingOffsets);
        return this;
    }

    public IOURingParams set(IOURingParams iOURingParams) {
        MemoryUtil.memCopy(iOURingParams.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingParams malloc() {
        return new IOURingParams(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingParams calloc() {
        return new IOURingParams(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingParams create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingParams(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingParams create(long j) {
        return new IOURingParams(j, null);
    }

    public static IOURingParams createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingParams(j, null);
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

    public static IOURingParams malloc(MemoryStack memoryStack) {
        return new IOURingParams(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingParams calloc(MemoryStack memoryStack) {
        return new IOURingParams(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nsq_entries(long j) {
        return UNSAFE.getInt((Object) null, j + SQ_ENTRIES);
    }

    public static int ncq_entries(long j) {
        return UNSAFE.getInt((Object) null, j + CQ_ENTRIES);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static int nsq_thread_cpu(long j) {
        return UNSAFE.getInt((Object) null, j + SQ_THREAD_CPU);
    }

    public static int nsq_thread_idle(long j) {
        return UNSAFE.getInt((Object) null, j + SQ_THREAD_IDLE);
    }

    public static int nfeatures(long j) {
        return UNSAFE.getInt((Object) null, j + FEATURES);
    }

    public static int nwq_fd(long j) {
        return UNSAFE.getInt((Object) null, j + WQ_FD);
    }

    public static IntBuffer nresv(long j) {
        return MemoryUtil.memIntBuffer(j + RESV, 3);
    }

    public static int nresv(long j, int i) {
        return UNSAFE.getInt((Object) null, j + RESV + (Checks.check(i, 3) << 2));
    }

    public static IOSQRingOffsets nsq_off(long j) {
        return IOSQRingOffsets.create(j + SQ_OFF);
    }

    public static IOCQRingOffsets ncq_off(long j) {
        return IOCQRingOffsets.create(j + CQ_OFF);
    }

    public static void nsq_entries(long j, int i) {
        UNSAFE.putInt((Object) null, j + SQ_ENTRIES, i);
    }

    public static void ncq_entries(long j, int i) {
        UNSAFE.putInt((Object) null, j + CQ_ENTRIES, i);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void nsq_thread_cpu(long j, int i) {
        UNSAFE.putInt((Object) null, j + SQ_THREAD_CPU, i);
    }

    public static void nsq_thread_idle(long j, int i) {
        UNSAFE.putInt((Object) null, j + SQ_THREAD_IDLE, i);
    }

    public static void nfeatures(long j, int i) {
        UNSAFE.putInt((Object) null, j + FEATURES, i);
    }

    public static void nwq_fd(long j, int i) {
        UNSAFE.putInt((Object) null, j + WQ_FD, i);
    }

    public static void nresv(long j, IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(intBuffer, 3);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(intBuffer), j + RESV, intBuffer.remaining() << 2);
    }

    public static void nresv(long j, int i, int i2) {
        UNSAFE.putInt((Object) null, j + RESV + (Checks.check(i, 3) << 2), i2);
    }

    public static void nsq_off(long j, IOSQRingOffsets iOSQRingOffsets) {
        MemoryUtil.memCopy(iOSQRingOffsets.address(), j + SQ_OFF, IOSQRingOffsets.SIZEOF);
    }

    public static void ncq_off(long j, IOCQRingOffsets iOCQRingOffsets) {
        MemoryUtil.memCopy(iOCQRingOffsets.address(), j + CQ_OFF, IOCQRingOffsets.SIZEOF);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingParams$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingParams, Buffer> implements NativeResource {
        private static final IOURingParams ELEMENT_FACTORY = IOURingParams.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingParams.SIZEOF);
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
        public IOURingParams getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int sq_entries() {
            return IOURingParams.nsq_entries(address());
        }

        @NativeType("__u32")
        public int cq_entries() {
            return IOURingParams.ncq_entries(address());
        }

        @NativeType("__u32")
        public int flags() {
            return IOURingParams.nflags(address());
        }

        @NativeType("__u32")
        public int sq_thread_cpu() {
            return IOURingParams.nsq_thread_cpu(address());
        }

        @NativeType("__u32")
        public int sq_thread_idle() {
            return IOURingParams.nsq_thread_idle(address());
        }

        @NativeType("__u32")
        public int features() {
            return IOURingParams.nfeatures(address());
        }

        @NativeType("__u32")
        public int wq_fd() {
            return IOURingParams.nwq_fd(address());
        }

        @NativeType("struct io_sqring_offsets")
        public IOSQRingOffsets sq_off() {
            return IOURingParams.nsq_off(address());
        }

        @NativeType("struct io_cqring_offsets")
        public IOCQRingOffsets cq_off() {
            return IOURingParams.ncq_off(address());
        }

        public Buffer sq_entries(@NativeType("__u32") int i) {
            IOURingParams.nsq_entries(address(), i);
            return this;
        }

        public Buffer cq_entries(@NativeType("__u32") int i) {
            IOURingParams.ncq_entries(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("__u32") int i) {
            IOURingParams.nflags(address(), i);
            return this;
        }

        public Buffer sq_thread_cpu(@NativeType("__u32") int i) {
            IOURingParams.nsq_thread_cpu(address(), i);
            return this;
        }

        public Buffer sq_thread_idle(@NativeType("__u32") int i) {
            IOURingParams.nsq_thread_idle(address(), i);
            return this;
        }

        public Buffer features(@NativeType("__u32") int i) {
            IOURingParams.nfeatures(address(), i);
            return this;
        }

        public Buffer wq_fd(@NativeType("__u32") int i) {
            IOURingParams.nwq_fd(address(), i);
            return this;
        }

        public Buffer sq_off(@NativeType("struct io_sqring_offsets") IOSQRingOffsets iOSQRingOffsets) {
            IOURingParams.nsq_off(address(), iOSQRingOffsets);
            return this;
        }

        public Buffer sq_off(Consumer<IOSQRingOffsets> consumer) {
            consumer.accept(sq_off());
            return this;
        }

        public Buffer cq_off(@NativeType("struct io_cqring_offsets") IOCQRingOffsets iOCQRingOffsets) {
            IOURingParams.ncq_off(address(), iOCQRingOffsets);
            return this;
        }

        public Buffer cq_off(Consumer<IOCQRingOffsets> consumer) {
            consumer.accept(cq_off());
            return this;
        }
    }
}
