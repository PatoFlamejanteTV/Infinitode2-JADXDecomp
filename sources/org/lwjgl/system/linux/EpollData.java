package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("union epoll_data_t")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/EpollData.class */
public class EpollData extends Struct<EpollData> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int PTR;
    public static final int FD;
    public static final int U32;
    public static final int U64;

    static {
        Struct.Layout __union = __union(__member(POINTER_SIZE), __member(4), __member(4), __member(8));
        SIZEOF = __union.getSize();
        ALIGNOF = __union.getAlignment();
        PTR = __union.offsetof(0);
        FD = __union.offsetof(1);
        U32 = __union.offsetof(2);
        U64 = __union.offsetof(3);
    }

    protected EpollData(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public EpollData create(long j, ByteBuffer byteBuffer) {
        return new EpollData(j, byteBuffer);
    }

    public EpollData(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("void *")
    public long ptr() {
        return nptr(address());
    }

    public int fd() {
        return nfd(address());
    }

    @NativeType("uint32_t")
    public int u32() {
        return nu32(address());
    }

    @NativeType("uint64_t")
    public long u64() {
        return nu64(address());
    }

    public EpollData ptr(@NativeType("void *") long j) {
        nptr(address(), j);
        return this;
    }

    public EpollData fd(int i) {
        nfd(address(), i);
        return this;
    }

    public EpollData u32(@NativeType("uint32_t") int i) {
        nu32(address(), i);
        return this;
    }

    public EpollData u64(@NativeType("uint64_t") long j) {
        nu64(address(), j);
        return this;
    }

    public EpollData set(EpollData epollData) {
        MemoryUtil.memCopy(epollData.address(), address(), SIZEOF);
        return this;
    }

    public static EpollData malloc() {
        return new EpollData(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static EpollData calloc() {
        return new EpollData(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static EpollData create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new EpollData(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static EpollData create(long j) {
        return new EpollData(j, null);
    }

    public static EpollData createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new EpollData(j, null);
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

    public static EpollData malloc(MemoryStack memoryStack) {
        return new EpollData(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static EpollData calloc(MemoryStack memoryStack) {
        return new EpollData(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nptr(long j) {
        return MemoryUtil.memGetAddress(j + PTR);
    }

    public static int nfd(long j) {
        return UNSAFE.getInt((Object) null, j + FD);
    }

    public static int nu32(long j) {
        return UNSAFE.getInt((Object) null, j + U32);
    }

    public static long nu64(long j) {
        return UNSAFE.getLong((Object) null, j + U64);
    }

    public static void nptr(long j, long j2) {
        MemoryUtil.memPutAddress(j + PTR, Checks.check(j2));
    }

    public static void nfd(long j, int i) {
        UNSAFE.putInt((Object) null, j + FD, i);
    }

    public static void nu32(long j, int i) {
        UNSAFE.putInt((Object) null, j + U32, i);
    }

    public static void nu64(long j, long j2) {
        UNSAFE.putLong((Object) null, j + U64, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/EpollData$Buffer.class */
    public static class Buffer extends StructBuffer<EpollData, Buffer> implements NativeResource {
        private static final EpollData ELEMENT_FACTORY = EpollData.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / EpollData.SIZEOF);
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
        public EpollData getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("void *")
        public long ptr() {
            return EpollData.nptr(address());
        }

        public int fd() {
            return EpollData.nfd(address());
        }

        @NativeType("uint32_t")
        public int u32() {
            return EpollData.nu32(address());
        }

        @NativeType("uint64_t")
        public long u64() {
            return EpollData.nu64(address());
        }

        public Buffer ptr(@NativeType("void *") long j) {
            EpollData.nptr(address(), j);
            return this;
        }

        public Buffer fd(int i) {
            EpollData.nfd(address(), i);
            return this;
        }

        public Buffer u32(@NativeType("uint32_t") int i) {
            EpollData.nu32(address(), i);
            return this;
        }

        public Buffer u64(@NativeType("uint64_t") long j) {
            EpollData.nu64(address(), j);
            return this;
        }
    }
}
