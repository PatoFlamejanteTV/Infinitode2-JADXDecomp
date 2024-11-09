package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_rsrc_update")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRSRCUpdate.class */
public class IOURingRSRCUpdate extends Struct<IOURingRSRCUpdate> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int OFFSET;
    public static final int RESV;
    public static final int DATA;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        OFFSET = __struct.offsetof(0);
        RESV = __struct.offsetof(1);
        DATA = __struct.offsetof(2);
    }

    protected IOURingRSRCUpdate(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingRSRCUpdate create(long j, ByteBuffer byteBuffer) {
        return new IOURingRSRCUpdate(j, byteBuffer);
    }

    public IOURingRSRCUpdate(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int offset() {
        return noffset(address());
    }

    @NativeType("__u64")
    public long data() {
        return ndata(address());
    }

    public IOURingRSRCUpdate offset(@NativeType("__u32") int i) {
        noffset(address(), i);
        return this;
    }

    public IOURingRSRCUpdate data(@NativeType("__u64") long j) {
        ndata(address(), j);
        return this;
    }

    public IOURingRSRCUpdate set(int i, long j) {
        offset(i);
        data(j);
        return this;
    }

    public IOURingRSRCUpdate set(IOURingRSRCUpdate iOURingRSRCUpdate) {
        MemoryUtil.memCopy(iOURingRSRCUpdate.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingRSRCUpdate malloc() {
        return new IOURingRSRCUpdate(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingRSRCUpdate calloc() {
        return new IOURingRSRCUpdate(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingRSRCUpdate create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingRSRCUpdate(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingRSRCUpdate create(long j) {
        return new IOURingRSRCUpdate(j, null);
    }

    public static IOURingRSRCUpdate createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingRSRCUpdate(j, null);
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

    public static IOURingRSRCUpdate malloc(MemoryStack memoryStack) {
        return new IOURingRSRCUpdate(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingRSRCUpdate calloc(MemoryStack memoryStack) {
        return new IOURingRSRCUpdate(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int noffset(long j) {
        return UNSAFE.getInt((Object) null, j + OFFSET);
    }

    public static int nresv(long j) {
        return UNSAFE.getInt((Object) null, j + RESV);
    }

    public static long ndata(long j) {
        return UNSAFE.getLong((Object) null, j + DATA);
    }

    public static void noffset(long j, int i) {
        UNSAFE.putInt((Object) null, j + OFFSET, i);
    }

    public static void nresv(long j, int i) {
        UNSAFE.putInt((Object) null, j + RESV, i);
    }

    public static void ndata(long j, long j2) {
        UNSAFE.putLong((Object) null, j + DATA, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRSRCUpdate$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingRSRCUpdate, Buffer> implements NativeResource {
        private static final IOURingRSRCUpdate ELEMENT_FACTORY = IOURingRSRCUpdate.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingRSRCUpdate.SIZEOF);
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
        public IOURingRSRCUpdate getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int offset() {
            return IOURingRSRCUpdate.noffset(address());
        }

        @NativeType("__u64")
        public long data() {
            return IOURingRSRCUpdate.ndata(address());
        }

        public Buffer offset(@NativeType("__u32") int i) {
            IOURingRSRCUpdate.noffset(address(), i);
            return this;
        }

        public Buffer data(@NativeType("__u64") long j) {
            IOURingRSRCUpdate.ndata(address(), j);
            return this;
        }
    }
}
