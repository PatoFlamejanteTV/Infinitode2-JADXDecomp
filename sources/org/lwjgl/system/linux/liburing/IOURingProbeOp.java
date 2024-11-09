package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_probe_op")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingProbeOp.class */
public class IOURingProbeOp extends Struct<IOURingProbeOp> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int OP;
    public static final int RESV;
    public static final int FLAGS;
    public static final int RESV2;

    static {
        Struct.Layout __struct = __struct(__member(1), __member(1), __member(2), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        OP = __struct.offsetof(0);
        RESV = __struct.offsetof(1);
        FLAGS = __struct.offsetof(2);
        RESV2 = __struct.offsetof(3);
    }

    protected IOURingProbeOp(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingProbeOp create(long j, ByteBuffer byteBuffer) {
        return new IOURingProbeOp(j, byteBuffer);
    }

    public IOURingProbeOp(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u8")
    public byte op() {
        return nop(address());
    }

    @NativeType("__u16")
    public short flags() {
        return nflags(address());
    }

    public IOURingProbeOp op(@NativeType("__u8") byte b2) {
        nop(address(), b2);
        return this;
    }

    public IOURingProbeOp flags(@NativeType("__u16") short s) {
        nflags(address(), s);
        return this;
    }

    public IOURingProbeOp set(byte b2, short s) {
        op(b2);
        flags(s);
        return this;
    }

    public IOURingProbeOp set(IOURingProbeOp iOURingProbeOp) {
        MemoryUtil.memCopy(iOURingProbeOp.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingProbeOp malloc() {
        return new IOURingProbeOp(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingProbeOp calloc() {
        return new IOURingProbeOp(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingProbeOp create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingProbeOp(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingProbeOp create(long j) {
        return new IOURingProbeOp(j, null);
    }

    public static IOURingProbeOp createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingProbeOp(j, null);
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

    public static IOURingProbeOp malloc(MemoryStack memoryStack) {
        return new IOURingProbeOp(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingProbeOp calloc(MemoryStack memoryStack) {
        return new IOURingProbeOp(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static byte nop(long j) {
        return UNSAFE.getByte((Object) null, j + OP);
    }

    public static byte nresv(long j) {
        return UNSAFE.getByte((Object) null, j + RESV);
    }

    public static short nflags(long j) {
        return UNSAFE.getShort((Object) null, j + FLAGS);
    }

    public static int nresv2(long j) {
        return UNSAFE.getInt((Object) null, j + RESV2);
    }

    public static void nop(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + OP, b2);
    }

    public static void nresv(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + RESV, b2);
    }

    public static void nflags(long j, short s) {
        UNSAFE.putShort((Object) null, j + FLAGS, s);
    }

    public static void nresv2(long j, int i) {
        UNSAFE.putInt((Object) null, j + RESV2, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingProbeOp$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingProbeOp, Buffer> implements NativeResource {
        private static final IOURingProbeOp ELEMENT_FACTORY = IOURingProbeOp.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingProbeOp.SIZEOF);
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
        public IOURingProbeOp getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u8")
        public byte op() {
            return IOURingProbeOp.nop(address());
        }

        @NativeType("__u16")
        public short flags() {
            return IOURingProbeOp.nflags(address());
        }

        public Buffer op(@NativeType("__u8") byte b2) {
            IOURingProbeOp.nop(address(), b2);
            return this;
        }

        public Buffer flags(@NativeType("__u16") short s) {
            IOURingProbeOp.nflags(address(), s);
            return this;
        }
    }
}
