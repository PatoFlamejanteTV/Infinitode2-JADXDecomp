package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_file_index_range")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingFileIndexRange.class */
public class IOURingFileIndexRange extends Struct<IOURingFileIndexRange> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int OFF;
    public static final int LEN;
    public static final int RESV;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        OFF = __struct.offsetof(0);
        LEN = __struct.offsetof(1);
        RESV = __struct.offsetof(2);
    }

    protected IOURingFileIndexRange(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingFileIndexRange create(long j, ByteBuffer byteBuffer) {
        return new IOURingFileIndexRange(j, byteBuffer);
    }

    public IOURingFileIndexRange(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int off() {
        return noff(address());
    }

    @NativeType("__u32")
    public int len() {
        return nlen(address());
    }

    public IOURingFileIndexRange off(@NativeType("__u32") int i) {
        noff(address(), i);
        return this;
    }

    public IOURingFileIndexRange len(@NativeType("__u32") int i) {
        nlen(address(), i);
        return this;
    }

    public IOURingFileIndexRange set(int i, int i2) {
        off(i);
        len(i2);
        return this;
    }

    public IOURingFileIndexRange set(IOURingFileIndexRange iOURingFileIndexRange) {
        MemoryUtil.memCopy(iOURingFileIndexRange.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingFileIndexRange malloc() {
        return new IOURingFileIndexRange(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingFileIndexRange calloc() {
        return new IOURingFileIndexRange(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingFileIndexRange create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingFileIndexRange(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingFileIndexRange create(long j) {
        return new IOURingFileIndexRange(j, null);
    }

    public static IOURingFileIndexRange createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingFileIndexRange(j, null);
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

    public static IOURingFileIndexRange malloc(MemoryStack memoryStack) {
        return new IOURingFileIndexRange(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingFileIndexRange calloc(MemoryStack memoryStack) {
        return new IOURingFileIndexRange(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int noff(long j) {
        return UNSAFE.getInt((Object) null, j + OFF);
    }

    public static int nlen(long j) {
        return UNSAFE.getInt((Object) null, j + LEN);
    }

    public static long nresv(long j) {
        return UNSAFE.getLong((Object) null, j + RESV);
    }

    public static void noff(long j, int i) {
        UNSAFE.putInt((Object) null, j + OFF, i);
    }

    public static void nlen(long j, int i) {
        UNSAFE.putInt((Object) null, j + LEN, i);
    }

    public static void nresv(long j, long j2) {
        UNSAFE.putLong((Object) null, j + RESV, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingFileIndexRange$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingFileIndexRange, Buffer> implements NativeResource {
        private static final IOURingFileIndexRange ELEMENT_FACTORY = IOURingFileIndexRange.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingFileIndexRange.SIZEOF);
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
        public IOURingFileIndexRange getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int off() {
            return IOURingFileIndexRange.noff(address());
        }

        @NativeType("__u32")
        public int len() {
            return IOURingFileIndexRange.nlen(address());
        }

        public Buffer off(@NativeType("__u32") int i) {
            IOURingFileIndexRange.noff(address(), i);
            return this;
        }

        public Buffer len(@NativeType("__u32") int i) {
            IOURingFileIndexRange.nlen(address(), i);
            return this;
        }
    }
}