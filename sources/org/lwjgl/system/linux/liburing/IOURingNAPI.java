package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_napi")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingNAPI.class */
public class IOURingNAPI extends Struct<IOURingNAPI> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int BUSY_POLL_TO;
    public static final int PREFER_BUSY_POLL;
    public static final int PAD;
    public static final int RESV;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(1), __array(1, 3), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        BUSY_POLL_TO = __struct.offsetof(0);
        PREFER_BUSY_POLL = __struct.offsetof(1);
        PAD = __struct.offsetof(2);
        RESV = __struct.offsetof(3);
    }

    protected IOURingNAPI(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingNAPI create(long j, ByteBuffer byteBuffer) {
        return new IOURingNAPI(j, byteBuffer);
    }

    public IOURingNAPI(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int busy_poll_to() {
        return nbusy_poll_to(address());
    }

    @NativeType("__u8")
    public byte prefer_busy_poll() {
        return nprefer_busy_poll(address());
    }

    public IOURingNAPI busy_poll_to(@NativeType("__u32") int i) {
        nbusy_poll_to(address(), i);
        return this;
    }

    public IOURingNAPI prefer_busy_poll(@NativeType("__u8") byte b2) {
        nprefer_busy_poll(address(), b2);
        return this;
    }

    public IOURingNAPI set(int i, byte b2) {
        busy_poll_to(i);
        prefer_busy_poll(b2);
        return this;
    }

    public IOURingNAPI set(IOURingNAPI iOURingNAPI) {
        MemoryUtil.memCopy(iOURingNAPI.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingNAPI malloc() {
        return new IOURingNAPI(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingNAPI calloc() {
        return new IOURingNAPI(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingNAPI create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingNAPI(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingNAPI create(long j) {
        return new IOURingNAPI(j, null);
    }

    public static IOURingNAPI createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingNAPI(j, null);
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

    public static IOURingNAPI malloc(MemoryStack memoryStack) {
        return new IOURingNAPI(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingNAPI calloc(MemoryStack memoryStack) {
        return new IOURingNAPI(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nbusy_poll_to(long j) {
        return UNSAFE.getInt((Object) null, j + BUSY_POLL_TO);
    }

    public static byte nprefer_busy_poll(long j) {
        return UNSAFE.getByte((Object) null, j + PREFER_BUSY_POLL);
    }

    public static ByteBuffer npad(long j) {
        return MemoryUtil.memByteBuffer(j + PAD, 3);
    }

    public static byte npad(long j, int i) {
        return UNSAFE.getByte((Object) null, j + PAD + Checks.check(i, 3));
    }

    public static long nresv(long j) {
        return UNSAFE.getLong((Object) null, j + RESV);
    }

    public static void nbusy_poll_to(long j, int i) {
        UNSAFE.putInt((Object) null, j + BUSY_POLL_TO, i);
    }

    public static void nprefer_busy_poll(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + PREFER_BUSY_POLL, b2);
    }

    public static void npad(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 3);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + PAD, byteBuffer.remaining());
    }

    public static void npad(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + PAD + Checks.check(i, 3), b2);
    }

    public static void nresv(long j, long j2) {
        UNSAFE.putLong((Object) null, j + RESV, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingNAPI$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingNAPI, Buffer> implements NativeResource {
        private static final IOURingNAPI ELEMENT_FACTORY = IOURingNAPI.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingNAPI.SIZEOF);
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
        public IOURingNAPI getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int busy_poll_to() {
            return IOURingNAPI.nbusy_poll_to(address());
        }

        @NativeType("__u8")
        public byte prefer_busy_poll() {
            return IOURingNAPI.nprefer_busy_poll(address());
        }

        public Buffer busy_poll_to(@NativeType("__u32") int i) {
            IOURingNAPI.nbusy_poll_to(address(), i);
            return this;
        }

        public Buffer prefer_busy_poll(@NativeType("__u8") byte b2) {
            IOURingNAPI.nprefer_busy_poll(address(), b2);
            return this;
        }
    }
}
