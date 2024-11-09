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

@NativeType("struct io_uring_cq")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingCQ.class */
public class IOURingCQ extends Struct<IOURingCQ> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int KHEAD;
    public static final int KTAIL;
    public static final int KRING_MASK;
    public static final int KRING_ENTRIES;
    public static final int KFLAGS;
    public static final int KOVERFLOW;
    public static final int CQES;
    public static final int RING_SZ;
    public static final int RING_PTR;
    public static final int RING_MASK;
    public static final int RING_ENTRIES;
    public static final int PAD;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(4), __member(4), __array(4, 2));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        KHEAD = __struct.offsetof(0);
        KTAIL = __struct.offsetof(1);
        KRING_MASK = __struct.offsetof(2);
        KRING_ENTRIES = __struct.offsetof(3);
        KFLAGS = __struct.offsetof(4);
        KOVERFLOW = __struct.offsetof(5);
        CQES = __struct.offsetof(6);
        RING_SZ = __struct.offsetof(7);
        RING_PTR = __struct.offsetof(8);
        RING_MASK = __struct.offsetof(9);
        RING_ENTRIES = __struct.offsetof(10);
        PAD = __struct.offsetof(11);
    }

    protected IOURingCQ(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingCQ create(long j, ByteBuffer byteBuffer) {
        return new IOURingCQ(j, byteBuffer);
    }

    public IOURingCQ(ByteBuffer byteBuffer) {
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
    public IntBuffer koverflow(int i) {
        return nkoverflow(address(), i);
    }

    @NativeType("struct io_uring_cqe *")
    public IOURingCQE cqes() {
        return ncqes(address());
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

    public IOURingCQ khead(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkhead(address(), intBuffer);
        return this;
    }

    public IOURingCQ ktail(@NativeType("unsigned *") IntBuffer intBuffer) {
        nktail(address(), intBuffer);
        return this;
    }

    public IOURingCQ kring_mask(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkring_mask(address(), intBuffer);
        return this;
    }

    public IOURingCQ kring_entries(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkring_entries(address(), intBuffer);
        return this;
    }

    public IOURingCQ kflags(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkflags(address(), intBuffer);
        return this;
    }

    public IOURingCQ koverflow(@NativeType("unsigned *") IntBuffer intBuffer) {
        nkoverflow(address(), intBuffer);
        return this;
    }

    public IOURingCQ cqes(@NativeType("struct io_uring_cqe *") IOURingCQE iOURingCQE) {
        ncqes(address(), iOURingCQE);
        return this;
    }

    public IOURingCQ ring_ptr(@NativeType("void *") ByteBuffer byteBuffer) {
        nring_ptr(address(), byteBuffer);
        return this;
    }

    public IOURingCQ ring_mask(@NativeType("unsigned") int i) {
        nring_mask(address(), i);
        return this;
    }

    public IOURingCQ ring_entries(@NativeType("unsigned") int i) {
        nring_entries(address(), i);
        return this;
    }

    public IOURingCQ set(IntBuffer intBuffer, IntBuffer intBuffer2, IntBuffer intBuffer3, IntBuffer intBuffer4, IntBuffer intBuffer5, IntBuffer intBuffer6, IOURingCQE iOURingCQE, ByteBuffer byteBuffer, int i, int i2) {
        khead(intBuffer);
        ktail(intBuffer2);
        kring_mask(intBuffer3);
        kring_entries(intBuffer4);
        kflags(intBuffer5);
        koverflow(intBuffer6);
        cqes(iOURingCQE);
        ring_ptr(byteBuffer);
        ring_mask(i);
        ring_entries(i2);
        return this;
    }

    public IOURingCQ set(IOURingCQ iOURingCQ) {
        MemoryUtil.memCopy(iOURingCQ.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingCQ malloc() {
        return new IOURingCQ(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingCQ calloc() {
        return new IOURingCQ(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingCQ create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingCQ(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingCQ create(long j) {
        return new IOURingCQ(j, null);
    }

    public static IOURingCQ createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingCQ(j, null);
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

    public static IOURingCQ malloc(MemoryStack memoryStack) {
        return new IOURingCQ(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingCQ calloc(MemoryStack memoryStack) {
        return new IOURingCQ(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static IntBuffer nkoverflow(long j, int i) {
        return MemoryUtil.memIntBuffer(MemoryUtil.memGetAddress(j + KOVERFLOW), i);
    }

    public static IOURingCQE ncqes(long j) {
        return IOURingCQE.create(MemoryUtil.memGetAddress(j + CQES));
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

    public static void nkoverflow(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + KOVERFLOW, MemoryUtil.memAddress(intBuffer));
    }

    public static void ncqes(long j, IOURingCQE iOURingCQE) {
        MemoryUtil.memPutAddress(j + CQES, iOURingCQE.address());
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
        Checks.check(MemoryUtil.memGetAddress(j + KOVERFLOW));
        Checks.check(MemoryUtil.memGetAddress(j + CQES));
        Checks.check(MemoryUtil.memGetAddress(j + RING_PTR));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingCQ$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingCQ, Buffer> implements NativeResource {
        private static final IOURingCQ ELEMENT_FACTORY = IOURingCQ.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingCQ.SIZEOF);
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
        public IOURingCQ getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("unsigned *")
        public IntBuffer khead(int i) {
            return IOURingCQ.nkhead(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer ktail(int i) {
            return IOURingCQ.nktail(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer kring_mask(int i) {
            return IOURingCQ.nkring_mask(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer kring_entries(int i) {
            return IOURingCQ.nkring_entries(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer kflags(int i) {
            return IOURingCQ.nkflags(address(), i);
        }

        @NativeType("unsigned *")
        public IntBuffer koverflow(int i) {
            return IOURingCQ.nkoverflow(address(), i);
        }

        @NativeType("struct io_uring_cqe *")
        public IOURingCQE cqes() {
            return IOURingCQ.ncqes(address());
        }

        @NativeType("size_t")
        public long ring_sz() {
            return IOURingCQ.nring_sz(address());
        }

        @NativeType("void *")
        public ByteBuffer ring_ptr() {
            return IOURingCQ.nring_ptr(address());
        }

        @NativeType("unsigned")
        public int ring_mask() {
            return IOURingCQ.nring_mask(address());
        }

        @NativeType("unsigned")
        public int ring_entries() {
            return IOURingCQ.nring_entries(address());
        }

        public Buffer khead(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingCQ.nkhead(address(), intBuffer);
            return this;
        }

        public Buffer ktail(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingCQ.nktail(address(), intBuffer);
            return this;
        }

        public Buffer kring_mask(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingCQ.nkring_mask(address(), intBuffer);
            return this;
        }

        public Buffer kring_entries(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingCQ.nkring_entries(address(), intBuffer);
            return this;
        }

        public Buffer kflags(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingCQ.nkflags(address(), intBuffer);
            return this;
        }

        public Buffer koverflow(@NativeType("unsigned *") IntBuffer intBuffer) {
            IOURingCQ.nkoverflow(address(), intBuffer);
            return this;
        }

        public Buffer cqes(@NativeType("struct io_uring_cqe *") IOURingCQE iOURingCQE) {
            IOURingCQ.ncqes(address(), iOURingCQE);
            return this;
        }

        public Buffer ring_ptr(@NativeType("void *") ByteBuffer byteBuffer) {
            IOURingCQ.nring_ptr(address(), byteBuffer);
            return this;
        }

        public Buffer ring_mask(@NativeType("unsigned") int i) {
            IOURingCQ.nring_mask(address(), i);
            return this;
        }

        public Buffer ring_entries(@NativeType("unsigned") int i) {
            IOURingCQ.nring_entries(address(), i);
            return this;
        }
    }
}
