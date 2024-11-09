package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct __kernel_timespec")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/KernelTimespec.class */
public class KernelTimespec extends Struct<KernelTimespec> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TV_SEC;
    public static final int TV_NSEC;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TV_SEC = __struct.offsetof(0);
        TV_NSEC = __struct.offsetof(1);
    }

    protected KernelTimespec(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public KernelTimespec create(long j, ByteBuffer byteBuffer) {
        return new KernelTimespec(j, byteBuffer);
    }

    public KernelTimespec(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("int64_t")
    public long tv_sec() {
        return ntv_sec(address());
    }

    @NativeType("long long")
    public long tv_nsec() {
        return ntv_nsec(address());
    }

    public KernelTimespec tv_sec(@NativeType("int64_t") long j) {
        ntv_sec(address(), j);
        return this;
    }

    public KernelTimespec tv_nsec(@NativeType("long long") long j) {
        ntv_nsec(address(), j);
        return this;
    }

    public KernelTimespec set(long j, long j2) {
        tv_sec(j);
        tv_nsec(j2);
        return this;
    }

    public KernelTimespec set(KernelTimespec kernelTimespec) {
        MemoryUtil.memCopy(kernelTimespec.address(), address(), SIZEOF);
        return this;
    }

    public static KernelTimespec malloc() {
        return new KernelTimespec(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static KernelTimespec calloc() {
        return new KernelTimespec(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static KernelTimespec create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new KernelTimespec(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static KernelTimespec create(long j) {
        return new KernelTimespec(j, null);
    }

    public static KernelTimespec createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new KernelTimespec(j, null);
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

    public static KernelTimespec malloc(MemoryStack memoryStack) {
        return new KernelTimespec(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static KernelTimespec calloc(MemoryStack memoryStack) {
        return new KernelTimespec(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long ntv_sec(long j) {
        return UNSAFE.getLong((Object) null, j + TV_SEC);
    }

    public static long ntv_nsec(long j) {
        return UNSAFE.getLong((Object) null, j + TV_NSEC);
    }

    public static void ntv_sec(long j, long j2) {
        UNSAFE.putLong((Object) null, j + TV_SEC, j2);
    }

    public static void ntv_nsec(long j, long j2) {
        UNSAFE.putLong((Object) null, j + TV_NSEC, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/KernelTimespec$Buffer.class */
    public static class Buffer extends StructBuffer<KernelTimespec, Buffer> implements NativeResource {
        private static final KernelTimespec ELEMENT_FACTORY = KernelTimespec.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / KernelTimespec.SIZEOF);
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
        public KernelTimespec getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("int64_t")
        public long tv_sec() {
            return KernelTimespec.ntv_sec(address());
        }

        @NativeType("long long")
        public long tv_nsec() {
            return KernelTimespec.ntv_nsec(address());
        }

        public Buffer tv_sec(@NativeType("int64_t") long j) {
            KernelTimespec.ntv_sec(address(), j);
            return this;
        }

        public Buffer tv_nsec(@NativeType("long long") long j) {
            KernelTimespec.ntv_nsec(address(), j);
            return this;
        }
    }
}
