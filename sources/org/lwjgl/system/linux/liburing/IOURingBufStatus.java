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

@NativeType("struct io_uring_buf_status")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBufStatus.class */
public class IOURingBufStatus extends Struct<IOURingBufStatus> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int BUF_GROUP;
    public static final int HEAD;
    public static final int RESV;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __array(4, 8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        BUF_GROUP = __struct.offsetof(0);
        HEAD = __struct.offsetof(1);
        RESV = __struct.offsetof(2);
    }

    protected IOURingBufStatus(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingBufStatus create(long j, ByteBuffer byteBuffer) {
        return new IOURingBufStatus(j, byteBuffer);
    }

    public IOURingBufStatus(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int buf_group() {
        return nbuf_group(address());
    }

    @NativeType("__u32")
    public int head() {
        return nhead(address());
    }

    public IOURingBufStatus buf_group(@NativeType("__u32") int i) {
        nbuf_group(address(), i);
        return this;
    }

    public IOURingBufStatus head(@NativeType("__u32") int i) {
        nhead(address(), i);
        return this;
    }

    public IOURingBufStatus set(int i, int i2) {
        buf_group(i);
        head(i2);
        return this;
    }

    public IOURingBufStatus set(IOURingBufStatus iOURingBufStatus) {
        MemoryUtil.memCopy(iOURingBufStatus.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingBufStatus malloc() {
        return new IOURingBufStatus(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingBufStatus calloc() {
        return new IOURingBufStatus(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingBufStatus create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingBufStatus(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingBufStatus create(long j) {
        return new IOURingBufStatus(j, null);
    }

    public static IOURingBufStatus createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingBufStatus(j, null);
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

    public static IOURingBufStatus malloc(MemoryStack memoryStack) {
        return new IOURingBufStatus(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingBufStatus calloc(MemoryStack memoryStack) {
        return new IOURingBufStatus(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nbuf_group(long j) {
        return UNSAFE.getInt((Object) null, j + BUF_GROUP);
    }

    public static int nhead(long j) {
        return UNSAFE.getInt((Object) null, j + HEAD);
    }

    public static IntBuffer nresv(long j) {
        return MemoryUtil.memIntBuffer(j + RESV, 8);
    }

    public static int nresv(long j, int i) {
        return UNSAFE.getInt((Object) null, j + RESV + (Checks.check(i, 8) << 2));
    }

    public static void nbuf_group(long j, int i) {
        UNSAFE.putInt((Object) null, j + BUF_GROUP, i);
    }

    public static void nhead(long j, int i) {
        UNSAFE.putInt((Object) null, j + HEAD, i);
    }

    public static void nresv(long j, IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(intBuffer, 8);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(intBuffer), j + RESV, intBuffer.remaining() << 2);
    }

    public static void nresv(long j, int i, int i2) {
        UNSAFE.putInt((Object) null, j + RESV + (Checks.check(i, 8) << 2), i2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingBufStatus$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingBufStatus, Buffer> implements NativeResource {
        private static final IOURingBufStatus ELEMENT_FACTORY = IOURingBufStatus.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingBufStatus.SIZEOF);
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
        public IOURingBufStatus getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int buf_group() {
            return IOURingBufStatus.nbuf_group(address());
        }

        @NativeType("__u32")
        public int head() {
            return IOURingBufStatus.nhead(address());
        }

        public Buffer buf_group(@NativeType("__u32") int i) {
            IOURingBufStatus.nbuf_group(address(), i);
            return this;
        }

        public Buffer head(@NativeType("__u32") int i) {
            IOURingBufStatus.nhead(address(), i);
            return this;
        }
    }
}
