package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct statx_timestamp")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/StatxTimestamp.class */
public class StatxTimestamp extends Struct<StatxTimestamp> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TV_SEC;
    public static final int TV_NSEC;
    public static final int __RESERVED;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TV_SEC = __struct.offsetof(0);
        TV_NSEC = __struct.offsetof(1);
        __RESERVED = __struct.offsetof(2);
    }

    protected StatxTimestamp(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public StatxTimestamp create(long j, ByteBuffer byteBuffer) {
        return new StatxTimestamp(j, byteBuffer);
    }

    public StatxTimestamp(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__s64")
    public long tv_sec() {
        return ntv_sec(address());
    }

    @NativeType("__u32")
    public int tv_nsec() {
        return ntv_nsec(address());
    }

    public StatxTimestamp tv_sec(@NativeType("__s64") long j) {
        ntv_sec(address(), j);
        return this;
    }

    public StatxTimestamp tv_nsec(@NativeType("__u32") int i) {
        ntv_nsec(address(), i);
        return this;
    }

    public StatxTimestamp set(long j, int i) {
        tv_sec(j);
        tv_nsec(i);
        return this;
    }

    public StatxTimestamp set(StatxTimestamp statxTimestamp) {
        MemoryUtil.memCopy(statxTimestamp.address(), address(), SIZEOF);
        return this;
    }

    public static StatxTimestamp malloc() {
        return new StatxTimestamp(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static StatxTimestamp calloc() {
        return new StatxTimestamp(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static StatxTimestamp create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new StatxTimestamp(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static StatxTimestamp create(long j) {
        return new StatxTimestamp(j, null);
    }

    public static StatxTimestamp createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new StatxTimestamp(j, null);
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

    public static StatxTimestamp malloc(MemoryStack memoryStack) {
        return new StatxTimestamp(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static StatxTimestamp calloc(MemoryStack memoryStack) {
        return new StatxTimestamp(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int ntv_nsec(long j) {
        return UNSAFE.getInt((Object) null, j + TV_NSEC);
    }

    public static int n__reserved(long j) {
        return UNSAFE.getInt((Object) null, j + __RESERVED);
    }

    public static void ntv_sec(long j, long j2) {
        UNSAFE.putLong((Object) null, j + TV_SEC, j2);
    }

    public static void ntv_nsec(long j, int i) {
        UNSAFE.putInt((Object) null, j + TV_NSEC, i);
    }

    public static void n__reserved(long j, int i) {
        UNSAFE.putInt((Object) null, j + __RESERVED, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/StatxTimestamp$Buffer.class */
    public static class Buffer extends StructBuffer<StatxTimestamp, Buffer> implements NativeResource {
        private static final StatxTimestamp ELEMENT_FACTORY = StatxTimestamp.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / StatxTimestamp.SIZEOF);
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
        public StatxTimestamp getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__s64")
        public long tv_sec() {
            return StatxTimestamp.ntv_sec(address());
        }

        @NativeType("__u32")
        public int tv_nsec() {
            return StatxTimestamp.ntv_nsec(address());
        }

        public Buffer tv_sec(@NativeType("__s64") long j) {
            StatxTimestamp.ntv_sec(address(), j);
            return this;
        }

        public Buffer tv_nsec(@NativeType("__u32") int i) {
            StatxTimestamp.ntv_nsec(address(), i);
            return this;
        }
    }
}
