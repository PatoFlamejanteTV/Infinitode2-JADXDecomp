package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_sq")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingSQ.class */
public class IOURingSQ extends Struct<IOURingSQ> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int KHEAD;
    public static final int KTAIL;
    public static final int KRING_MASK;
    public static final int KRING_ENTRIES;
    public static final int KFLAGS;
    public static final int KDROPPED;
    public static final int ARRAY;
    public static final int SQES;
    public static final int SQE_HEAD;
    public static final int SQE_TAIL;
    public static final int RING_SZ;
    public static final int RING_PTR;
    public static final int RING_MASK;
    public static final int RING_ENTRIES;
    public static final int PAD;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(4), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(4), __member(4), __array(4, 2));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        KHEAD = __struct.offsetof(0);
        KTAIL = __struct.offsetof(1);
        KRING_MASK = __struct.offsetof(2);
        KRING_ENTRIES = __struct.offsetof(3);
        KFLAGS = __struct.offsetof(4);
        KDROPPED = __struct.offsetof(5);
        ARRAY = __struct.offsetof(6);
        SQES = __struct.offsetof(7);
        SQE_HEAD = __struct.offsetof(8);
        SQE_TAIL = __struct.offsetof(9);
        RING_SZ = __struct.offsetof(10);
        RING_PTR = __struct.offsetof(11);
        RING_MASK = __struct.offsetof(12);
        RING_ENTRIES = __struct.offsetof(13);
        PAD = __struct.offsetof(14);
    }

    protected IOURingSQ(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingSQ create(long j, ByteBuffer byteBuffer) {
        return new IOURingSQ(j, byteBuffer);
    }

    public IOURingSQ(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("unsigned *")
    public IntBuffer khead(int i) {
        return nkhead(address(), i);
    }

    @NativeType("unsigned *")
    public IntBuffer ktail(int i) {
        return nktail(address(), i);
    }

    @NativeType("unsigned *")
    public IntBuffer kring_mask(int i) {
        return nkring_mask(address(), i);
    }

    @NativeType("unsigned *")
    public IntBuffer kring_entries(int i) {
        return nkring_entries(address(), i);
    }

    @NativeType("unsigned *")
    public IntBuffer kflags(int i) {
        return nkflags(address(), i);
    }

    @NativeType("unsigned *")
    public IntBuffer kdropped(int i) {
        return nkdropped(address(), i);
    }

    @NativeType("unsigned *")
    public IntBuffer array(int i) {
        return narray(address(), i);
    }

    @NativeType("struct io_uring_sqe *")
    public IOURingSQE sqes() {
        return nsqes(address());
    }

    @NativeType("unsigned")
    public int sqe_head() {
        return nsqe_head(address());
    }

    @NativeType("unsigned")
    public int sqe_tail() {
        return nsqe_tail(address());
    }

    @NativeType("size_t")
    public long ring_sz() {
        return nring_sz(address());
    }

    @NativeType("void *")
    public ByteBuffer ring_ptr() {
        return nring_ptr(address());
    }

    @NativeType("unsigned")
    public int ring_mask() {
        return nring_mask(address());
    }

    @NativeType("unsigned")
    public int ring_entries() {
        return nring_entries(address());
    }

    public IOURingSQ khead(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkhead(address(), intBuffer);
        return this;
    }

    public IOURingSQ ktail(@NativeType("unsigned *") IntBuffer intBuffer) {
        nktail(address(), intBuffer);
        return this;
    }

    public IOURingSQ kring_mask(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkring_mask(address(), intBuffer);
        return this;
    }

    public IOURingSQ kring_entries(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkring_entries(address(), intBuffer);
        return this;
    }

    public IOURingSQ kflags(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkflags(address(), intBuffer);
        return this;
    }

    public IOURingSQ kdropped(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkdropped(address(), intBuffer);
        return this;
    }

    public IOURingSQ array(@NativeType("unsigned *") IntBuffer intBuffer) {
        narray(address(), intBuffer);
        return this;
    }

    public IOURingSQ sqes(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE) {
        nsqes(address(), iOURingSQE);
        return this;
    }

    public IOURingSQ sqe_head(@NativeType("unsigned") int i) {
        nsqe_head(address(), i);
        return this;
    }

    public IOURingSQ sqe_tail(@NativeType("unsigned") int i) {
        nsqe_tail(address(), i);
        return this;
    }

    public IOURingSQ ring_ptr(@NativeType("void *") ByteBuffer byteBuffer) {
        nring_ptr(address(), byteBuffer);
        return this;
    }

    public IOURingSQ ring_mask(@NativeType("unsigned") int i) {
        nring_mask(address(), i);
        return this;
    }

    public IOURingSQ ring_entries(@NativeType("unsigned") int i) {
        nring_entries(address(), i);
        return this;
    }

    public IOURingSQ set(IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, IntBuffer intBuffer4, IntBuffer intBuffer5, IntBuffer intBuffer6, IntBuffer intBuffer7, IOURingSQE iOURingSQE, int i, int i2, ByteBuffer byteBuffer, int i3, int i4) {
        khead(intBuffer);
        ktail(intBuffer2);
        kring_mask(intBuffer3);
        kring_entries(intBuffer4);
        kflags(intBuffer5);
        kdropped(intBuffer6);
        array(intBuffer7);
        sqes(iOURingSQE);
        sqe_head(i);
        sqe_tail(i2);
        ring_ptr(byteBuffer);
        ring_mask(i3);
        ring_entries(i4);
        return this;
    }

    public IOURingSQ set(IOURingSQ iOURingSQ) {
        MemoryUtil.memCopy(iOURingSQ.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingSQ malloc() {
        return new IOURingSQ(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingSQ calloc() {
        return new IOURingSQ(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingSQ create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingSQ(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingSQ create(long j) {
        return new IOURingSQ(j, null);
    }

    public static IOURingSQ createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingSQ(j, null);
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

    public static IOURingSQ malloc(MemoryStack memoryStack) {
        return new IOURingSQ(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingSQ calloc(MemoryStack memoryStack) {
        return new IOURingSQ(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static IntBuffer nkhead(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + KHEAD), i);
    }

    public static IntBuffer nktail(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + KTAIL), i);
    }

    public static IntBuffer nkring_mask(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + KRING_MASK), i);
    }

    public static IntBuffer nkring_entries(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + KRING_ENTRIES), i);
    }

    public static IntBuffer nkflags(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + KFLAGS), i);
    }

    public static IntBuffer nkdropped(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + KDROPPED), i);
    }

    public static IntBuffer narray(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + ARRAY), i);
    }

    public static IOURingSQE nsqes(long j) {
        return IOURingSQE.create(MemoryUtil.memGetAddress(j + SQES));
    }

    public static int nsqe_head(long j) {
        return UNSAFE.getInt((Object) null, j + SQE_HEAD);
    }

    public static int nsqe_tail(long j) {
        return UNSAFE.getInt((Object) null, j + SQE_TAIL);
    }

    public static long nring_sz(long j) {
        return MemoryUtil.memGetAddress(j + RING_SZ);
    }

    public static ByteBuffer nring_ptr(long j) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + RING_PTR), (int) nring_sz(j));
    }

    public static int nring_mask(long j) {
        return UNSAFE.getInt((Object) null, j + RING_MASK);
    }

    public static int nring_entries(long j) {
        return UNSAFE.getInt((Object) null, j + RING_ENTRIES);
    }

    public static IntBuffer npad(long j) {
        return MemoryUtil.memIntBuffer(j + PAD, 2);
    }

    public static int npad(long j, int i) {
        return UNSAFE.getInt((Object) null, j + PAD + (Checks.check(i, 2) << 2));
    }

    public static void nkhead(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + KHEAD, MemoryUtil.memAddress(intBuffer));
    }

    public static void nktail(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + KTAIL, MemoryUtil.memAddress(intBuffer));
    }

    public static void nkring_mask(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + KRING_MASK, MemoryUtil.memAddress(intBuffer));
    }

    public static void nkring_entries(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + KRING_ENTRIES, MemoryUtil.memAddress(intBuffer));
    }

    public static void nkflags(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + KFLAGS, MemoryUtil.memAddress(intBuffer));
    }

    public static void nkdropped(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + KDROPPED, MemoryUtil.memAddress(intBuffer));
    }

    public static void narray(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + ARRAY, MemoryUtil.memAddress(intBuffer));
    }

    public static void nsqes(long j, IOURingSQE iOURingSQE) {
        MemoryUtil.memPutAddress(j + SQES, iOURingSQE.address());
    }

    public static void nsqe_head(long j, int i) {
        UNSAFE.putInt((Object) null, j + SQE_HEAD, i);
    }

    public static void nsqe_tail(long j, int i) {
        UNSAFE.putInt((Object) null, j + SQE_TAIL, i);
    }

    public static void nring_sz(long j, long j2) {
        MemoryUtil.memPutAddress(j + RING_SZ, j2);
    }

    public static void nring_ptr(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + RING_PTR, MemoryUtil.memAddress(byteBuffer));
        nring_sz(j, byteBuffer.remaining());
    }

    public static void nring_mask(long j, int i) {
        UNSAFE.putInt((Object) null, j + RING_MASK, i);
    }

    public static void nring_entries(long j, int i) {
        UNSAFE.putInt((Object) null, j + RING_ENTRIES, i);
    }

    public static void npad(long j, IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(intBuffer, 2);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(intBuffer), j + PAD, intBuffer.remaining() << 2);
    }

    public static void npad(long j, int i, int i2) {
        UNSAFE.putInt((Object) null, j + PAD + (Checks.check(i, 2) << 2), i2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + KHEAD));
        Checks.check(MemoryUtil.memGetAddress(j + KTAIL));
        Checks.check(MemoryUtil.memGetAddress(j + KRING_MASK));
        Checks.check(MemoryUtil.memGetAddress(j + KRING_ENTRIES));
        Checks.check(MemoryUtil.memGetAddress(j + KFLAGS));
        Checks.check(MemoryUtil.memGetAddress(j + KDROPPED));
        Checks.check(MemoryUtil.memGetAddress(j + ARRAY));
        Checks.check(MemoryUtil.memGetAddress(j + SQES));
        Checks.check(MemoryUtil.memGetAddress(j + RING_PTR));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingSQ$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingSQ, Buffer> implements NativeResource {
        private static final IOURingSQ ELEMENT_FACTORY = IOURingSQ.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingSQ.SIZEOF);
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
        public IOURingSQ getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("unsigned *")
        public IntBuffer khead(int i) {
            return IOURingSQ.nkhead(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer ktail(int i) {
            return IOURingSQ.nktail(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer kring_mask(int i) {
            return IOURingSQ.nkring_mask(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer kring_entries(int i) {
            return IOURingSQ.nkring_entries(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer kflags(int i) {
            return IOURingSQ.nkflags(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer kdropped(int i) {
            return IOURingSQ.nkdropped(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer array(int i) {
            return IOURingSQ.narray(address(), i);
        }

        @NativeType("struct io_uring_sqe *")
        public IOURingSQE sqes() {
            return IOURingSQ.nsqes(address());
        }

        @NativeType("unsigned")
        public int sqe_head() {
            return IOURingSQ.nsqe_head(address());
        }

        @NativeType("unsigned")
        public int sqe_tail() {
            return IOURingSQ.nsqe_tail(address());
        }

        @NativeType("size_t")
        public long ring_sz() {
            return IOURingSQ.nring_sz(address());
        }

        @NativeType("void *")
        public ByteBuffer ring_ptr() {
            return IOURingSQ.nring_ptr(address());
        }

        @NativeType("unsigned")
        public int ring_mask() {
            return IOURingSQ.nring_mask(address());
        }

        @NativeType("unsigned")
        public int ring_entries() {
            return IOURingSQ.nring_entries(address());
        }

        public Buffer khead(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingSQ.nkhead(address(), intBuffer);
            return this;
        }

        public Buffer ktail(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingSQ.nktail(address(), intBuffer);
            return this;
        }

        public Buffer kring_mask(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingSQ.nkring_mask(address(), intBuffer);
            return this;
        }

        public Buffer kring_entries(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingSQ.nkring_entries(address(), intBuffer);
            return this;
        }

        public Buffer kflags(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingSQ.nkflags(address(), intBuffer);
            return this;
        }

        public Buffer kdropped(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingSQ.nkdropped(address(), intBuffer);
            return this;
        }

        public Buffer array(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingSQ.narray(address(), intBuffer);
            return this;
        }

        public Buffer sqes(@NativeType("struct io_uring_sqe *") IOURingSQE iOURingSQE) {
            IOURingSQ.nsqes(address(), iOURingSQE);
            return this;
        }

        public Buffer sqe_head(@NativeType("unsigned") int i) {
            IOURingSQ.nsqe_head(address(), i);
            return this;
        }

        public Buffer sqe_tail(@NativeType("unsigned") int i) {
            IOURingSQ.nsqe_tail(address(), i);
            return this;
        }

        public Buffer ring_ptr(@NativeType("void *") ByteBuffer byteBuffer) {
            IOURingSQ.nring_ptr(address(), byteBuffer);
            return this;
        }

        public Buffer ring_mask(@NativeType("unsigned") int i) {
            IOURingSQ.nring_mask(address(), i);
            return this;
        }

        public Buffer ring_entries(@NativeType("unsigned") int i) {
            IOURingSQ.nring_entries(address(), i);
            return this;
        }
    }
}
