package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct iovec")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/IOVec.class */
public class IOVec extends Struct<IOVec> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int IOV_BASE;
    public static final int IOV_LEN;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        IOV_BASE = __struct.offsetof(0);
        IOV_LEN = __struct.offsetof(1);
    }

    protected IOVec(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOVec create(long j, ByteBuffer byteBuffer) {
        return new IOVec(j, byteBuffer);
    }

    public IOVec(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("void *")
    public ByteBuffer iov_base() {
        return niov_base(address());
    }

    @NativeType("size_t")
    public long iov_len() {
        return niov_len(address());
    }

    public IOVec iov_base(@NativeType("void *") ByteBuffer byteBuffer) {
        niov_base(address(), byteBuffer);
        return this;
    }

    public IOVec iov_len(@NativeType("size_t") long j) {
        niov_len(address(), j);
        return this;
    }

    public IOVec set(ByteBuffer byteBuffer, long j) {
        iov_base(byteBuffer);
        iov_len(j);
        return this;
    }

    public IOVec set(IOVec iOVec) {
        MemoryUtil.memCopy(iOVec.address(), address(), SIZEOF);
        return this;
    }

    public static IOVec malloc() {
        return new IOVec(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOVec calloc() {
        return new IOVec(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOVec create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOVec(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOVec create(long j) {
        return new IOVec(j, null);
    }

    public static IOVec createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOVec(j, null);
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

    public static IOVec malloc(MemoryStack memoryStack) {
        return new IOVec(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOVec calloc(MemoryStack memoryStack) {
        return new IOVec(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ByteBuffer niov_base(long j) {
        return MemoryUtil.memByteBufferSafe(MemoryUtil.memGetAddress(j + IOV_BASE), (int) niov_len(j));
    }

    public static long niov_len(long j) {
        return MemoryUtil.memGetAddress(j + IOV_LEN);
    }

    public static void niov_base(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + IOV_BASE, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void niov_len(long j, long j2) {
        MemoryUtil.memPutAddress(j + IOV_LEN, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/IOVec$Buffer.class */
    public static class Buffer extends StructBuffer<IOVec, Buffer> implements NativeResource {
        private static final IOVec ELEMENT_FACTORY = IOVec.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOVec.SIZEOF);
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
        public IOVec getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("void *")
        public ByteBuffer iov_base() {
            return IOVec.niov_base(address());
        }

        @NativeType("size_t")
        public long iov_len() {
            return IOVec.niov_len(address());
        }

        public Buffer iov_base(@NativeType("void *") ByteBuffer byteBuffer) {
            IOVec.niov_base(address(), byteBuffer);
            return this;
        }

        public Buffer iov_len(@NativeType("size_t") long j) {
            IOVec.niov_len(address(), j);
            return this;
        }
    }
}
