package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.function.Consumer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;
import org.lwjgl.system.linux.KernelTimespec;

@NativeType("struct io_uring_sync_cancel_reg")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingSyncCancelReg.class */
public class IOURingSyncCancelReg extends Struct<IOURingSyncCancelReg> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int ADDR;
    public static final int FD;
    public static final int FLAGS;
    public static final int TIMEOUT;
    public static final int PAD;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(4), __member(4), __member(KernelTimespec.SIZEOF, KernelTimespec.ALIGNOF), __array(8, 4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        ADDR = __struct.offsetof(0);
        FD = __struct.offsetof(1);
        FLAGS = __struct.offsetof(2);
        TIMEOUT = __struct.offsetof(3);
        PAD = __struct.offsetof(4);
    }

    protected IOURingSyncCancelReg(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingSyncCancelReg create(long j, ByteBuffer byteBuffer) {
        return new IOURingSyncCancelReg(j, byteBuffer);
    }

    public IOURingSyncCancelReg(ByteBuffer byteBuffer) {
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

    @NativeType("__s32")
    public int fd() {
        return nfd(address());
    }

    @NativeType("__u32")
    public int flags() {
        return nflags(address());
    }

    @NativeType("struct __kernel_timespec")
    public KernelTimespec timeout() {
        return ntimeout(address());
    }

    public IOURingSyncCancelReg addr(@NativeType("__u64") long j) {
        naddr(address(), j);
        return this;
    }

    public IOURingSyncCancelReg fd(@NativeType("__s32") int i) {
        nfd(address(), i);
        return this;
    }

    public IOURingSyncCancelReg flags(@NativeType("__u32") int i) {
        nflags(address(), i);
        return this;
    }

    public IOURingSyncCancelReg timeout(@NativeType("struct __kernel_timespec") KernelTimespec kernelTimespec) {
        ntimeout(address(), kernelTimespec);
        return this;
    }

    public IOURingSyncCancelReg timeout(Consumer<KernelTimespec> consumer) {
        consumer.accept(timeout());
        return this;
    }

    public IOURingSyncCancelReg set(long j, int i, int i2, KernelTimespec kernelTimespec) {
        addr(j);
        fd(i);
        flags(i2);
        timeout(kernelTimespec);
        return this;
    }

    public IOURingSyncCancelReg set(IOURingSyncCancelReg iOURingSyncCancelReg) {
        MemoryUtil.memCopy(iOURingSyncCancelReg.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingSyncCancelReg malloc() {
        return new IOURingSyncCancelReg(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingSyncCancelReg calloc() {
        return new IOURingSyncCancelReg(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingSyncCancelReg create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingSyncCancelReg(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingSyncCancelReg create(long j) {
        return new IOURingSyncCancelReg(j, null);
    }

    public static IOURingSyncCancelReg createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingSyncCancelReg(j, null);
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

    public static IOURingSyncCancelReg malloc(MemoryStack memoryStack) {
        return new IOURingSyncCancelReg(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingSyncCancelReg calloc(MemoryStack memoryStack) {
        return new IOURingSyncCancelReg(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nfd(long j) {
        return UNSAFE.getInt((Object) null, j + FD);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static KernelTimespec ntimeout(long j) {
        return KernelTimespec.create(j + TIMEOUT);
    }

    public static LongBuffer npad(long j) {
        return MemoryUtil.memLongBuffer(j + PAD, 4);
    }

    public static long npad(long j, int i) {
        return UNSAFE.getLong((Object) null, j + PAD + (Checks.check(i, 4) << 3));
    }

    public static void naddr(long j, long j2) {
        UNSAFE.putLong((Object) null, j + ADDR, j2);
    }

    public static void nfd(long j, int i) {
        UNSAFE.putInt((Object) null, j + FD, i);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void ntimeout(long j, KernelTimespec kernelTimespec) {
        MemoryUtil.memCopy(kernelTimespec.address(), j + TIMEOUT, KernelTimespec.SIZEOF);
    }

    public static void npad(long j, LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(longBuffer, 4);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(longBuffer), j + PAD, longBuffer.remaining() << 3);
    }

    public static void npad(long j, int i, long j2) {
        UNSAFE.putLong((Object) null, j + PAD + (Checks.check(i, 4) << 3), j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingSyncCancelReg$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingSyncCancelReg, Buffer> implements NativeResource {
        private static final IOURingSyncCancelReg ELEMENT_FACTORY = IOURingSyncCancelReg.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingSyncCancelReg.SIZEOF);
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
        public IOURingSyncCancelReg getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u64")
        public long addr() {
            return IOURingSyncCancelReg.naddr(address());
        }

        @NativeType("__s32")
        public int fd() {
            return IOURingSyncCancelReg.nfd(address());
        }

        @NativeType("__u32")
        public int flags() {
            return IOURingSyncCancelReg.nflags(address());
        }

        @NativeType("struct __kernel_timespec")
        public KernelTimespec timeout() {
            return IOURingSyncCancelReg.ntimeout(address());
        }

        public Buffer addr(@NativeType("__u64") long j) {
            IOURingSyncCancelReg.naddr(address(), j);
            return this;
        }

        public Buffer fd(@NativeType("__s32") int i) {
            IOURingSyncCancelReg.nfd(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("__u32") int i) {
            IOURingSyncCancelReg.nflags(address(), i);
            return this;
        }

        public Buffer timeout(@NativeType("struct __kernel_timespec") KernelTimespec kernelTimespec) {
            IOURingSyncCancelReg.ntimeout(address(), kernelTimespec);
            return this;
        }

        public Buffer timeout(Consumer<KernelTimespec> consumer) {
            consumer.accept(timeout());
            return this;
        }
    }
}
