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

@NativeType("struct io_uring_cqe")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingCQE.class */
public class IOURingCQE extends Struct<IOURingCQE> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int USER_DATA;
    public static final int RES;
    public static final int FLAGS;
    public static final int BIG_CQE;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(4), __member(4), __array(8, 0));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        USER_DATA = __struct.offsetof(0);
        RES = __struct.offsetof(1);
        FLAGS = __struct.offsetof(2);
        BIG_CQE = __struct.offsetof(3);
    }

    protected IOURingCQE(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingCQE create(long j, ByteBuffer byteBuffer) {
        return new IOURingCQE(j, byteBuffer);
    }

    public IOURingCQE(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u64")
    public long user_data() {
        return nuser_data(address());
    }

    @NativeType("__s32")
    public int res() {
        return nres(address());
    }

    @NativeType("__u32")
    public int flags() {
        return nflags(address());
    }

    @NativeType("__u64[0]")
    public LongBuffer big_cqe() {
        return nbig_cqe(address());
    }

    @NativeType("__u64")
    public long big_cqe(int i) {
        return nbig_cqe(address(), i);
    }

    public IOURingCQE user_data(@NativeType("__u64") long j) {
        nuser_data(address(), j);
        return this;
    }

    public IOURingCQE res(@NativeType("__s32") int i) {
        nres(address(), i);
        return this;
    }

    public IOURingCQE flags(@NativeType("__u32") int i) {
        nflags(address(), i);
        return this;
    }

    public IOURingCQE big_cqe(@NativeType("__u64[0]") LongBuffer longBuffer) {
        nbig_cqe(address(), longBuffer);
        return this;
    }

    public IOURingCQE big_cqe(int i, @NativeType("__u64") long j) {
        nbig_cqe(address(), i, j);
        return this;
    }

    public IOURingCQE set(long j, int i, int i2, LongBuffer longBuffer) {
        user_data(j);
        res(i);
        flags(i2);
        big_cqe(longBuffer);
        return this;
    }

    public IOURingCQE set(IOURingCQE iOURingCQE) {
        MemoryUtil.memCopy(iOURingCQE.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingCQE malloc() {
        return new IOURingCQE(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingCQE calloc() {
        return new IOURingCQE(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingCQE create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingCQE(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingCQE create(long j) {
        return new IOURingCQE(j, null);
    }

    public static IOURingCQE createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingCQE(j, null);
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

    public static IOURingCQE malloc(MemoryStack memoryStack) {
        return new IOURingCQE(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingCQE calloc(MemoryStack memoryStack) {
        return new IOURingCQE(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nuser_data(long j) {
        return UNSAFE.getLong((Object) null, j + USER_DATA);
    }

    public static int nres(long j) {
        return UNSAFE.getInt((Object) null, j + RES);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static LongBuffer nbig_cqe(long j) {
        return MemoryUtil.memLongBuffer(j + BIG_CQE, 0);
    }

    public static long nbig_cqe(long j, int i) {
        return UNSAFE.getLong((Object) null, j + BIG_CQE + (Checks.check(i, 0) << 3));
    }

    public static void nuser_data(long j, long j2) {
        UNSAFE.putLong((Object) null, j + USER_DATA, j2);
    }

    public static void nres(long j, int i) {
        UNSAFE.putInt((Object) null, j + RES, i);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void nbig_cqe(long j, LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(longBuffer, 0);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(longBuffer), j + BIG_CQE, longBuffer.remaining() << 3);
    }

    public static void nbig_cqe(long j, int i, long j2) {
        UNSAFE.putLong((Object) null, j + BIG_CQE + (Checks.check(i, 0) << 3), j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingCQE$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingCQE, Buffer> implements NativeResource {
        private static final IOURingCQE ELEMENT_FACTORY = IOURingCQE.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingCQE.SIZEOF);
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
        public IOURingCQE getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u64")
        public long user_data() {
            return IOURingCQE.nuser_data(address());
        }

        @NativeType("__s32")
        public int res() {
            return IOURingCQE.nres(address());
        }

        @NativeType("__u32")
        public int flags() {
            return IOURingCQE.nflags(address());
        }

        @NativeType("__u64[0]")
        public LongBuffer big_cqe() {
            return IOURingCQE.nbig_cqe(address());
        }

        @NativeType("__u64")
        public long big_cqe(int i) {
            return IOURingCQE.nbig_cqe(address(), i);
        }

        public Buffer user_data(@NativeType("__u64") long j) {
            IOURingCQE.nuser_data(address(), j);
            return this;
        }

        public Buffer res(@NativeType("__s32") int i) {
            IOURingCQE.nres(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("__u32") int i) {
            IOURingCQE.nflags(address(), i);
            return this;
        }

        public Buffer big_cqe(@NativeType("__u64[0]") LongBuffer longBuffer) {
            IOURingCQE.nbig_cqe(address(), longBuffer);
            return this;
        }

        public Buffer big_cqe(int i, @NativeType("__u64") long j) {
            IOURingCQE.nbig_cqe(address(), i, j);
            return this;
        }
    }
}
