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

@NativeType("struct io_uring_restriction")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRestriction.class */
public class IOURingRestriction extends Struct<IOURingRestriction> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int OPCODE;
    public static final int REGISTER_OP;
    public static final int SQE_OP;
    public static final int SQE_FLAGS;
    public static final int RESV;
    public static final int RESV2;

    static {
        Struct.Layout __struct = __struct(__member(2), __union(__member(1), __member(1), __member(1)), __member(1), __array(4, 3));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        OPCODE = __struct.offsetof(0);
        REGISTER_OP = __struct.offsetof(2);
        SQE_OP = __struct.offsetof(3);
        SQE_FLAGS = __struct.offsetof(4);
        RESV = __struct.offsetof(5);
        RESV2 = __struct.offsetof(6);
    }

    protected IOURingRestriction(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingRestriction create(long j, ByteBuffer byteBuffer) {
        return new IOURingRestriction(j, byteBuffer);
    }

    public IOURingRestriction(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u16")
    public short opcode() {
        return nopcode(address());
    }

    @NativeType("__u8")
    public byte register_op() {
        return nregister_op(address());
    }

    @NativeType("__u8")
    public byte sqe_op() {
        return nsqe_op(address());
    }

    @NativeType("__u8")
    public byte sqe_flags() {
        return nsqe_flags(address());
    }

    public IOURingRestriction opcode(@NativeType("__u16") short s) {
        nopcode(address(), s);
        return this;
    }

    public IOURingRestriction register_op(@NativeType("__u8") byte b2) {
        nregister_op(address(), b2);
        return this;
    }

    public IOURingRestriction sqe_op(@NativeType("__u8") byte b2) {
        nsqe_op(address(), b2);
        return this;
    }

    public IOURingRestriction sqe_flags(@NativeType("__u8") byte b2) {
        nsqe_flags(address(), b2);
        return this;
    }

    public IOURingRestriction set(IOURingRestriction iOURingRestriction) {
        MemoryUtil.memCopy(iOURingRestriction.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingRestriction malloc() {
        return new IOURingRestriction(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingRestriction calloc() {
        return new IOURingRestriction(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingRestriction create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingRestriction(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingRestriction create(long j) {
        return new IOURingRestriction(j, null);
    }

    public static IOURingRestriction createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingRestriction(j, null);
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

    public static IOURingRestriction malloc(MemoryStack memoryStack) {
        return new IOURingRestriction(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingRestriction calloc(MemoryStack memoryStack) {
        return new IOURingRestriction(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static short nopcode(long j) {
        return UNSAFE.getShort((Object) null, j + OPCODE);
    }

    public static byte nregister_op(long j) {
        return UNSAFE.getByte((Object) null, j + REGISTER_OP);
    }

    public static byte nsqe_op(long j) {
        return UNSAFE.getByte((Object) null, j + SQE_OP);
    }

    public static byte nsqe_flags(long j) {
        return UNSAFE.getByte((Object) null, j + SQE_FLAGS);
    }

    public static byte nresv(long j) {
        return UNSAFE.getByte((Object) null, j + RESV);
    }

    public static IntBuffer nresv2(long j) {
        return MemoryUtil.memIntBuffer(j + RESV2, 3);
    }

    public static int nresv2(long j, int i) {
        return UNSAFE.getInt((Object) null, j + RESV2 + (Checks.check(i, 3) << 2));
    }

    public static void nopcode(long j, short s) {
        UNSAFE.putShort((Object) null, j + OPCODE, s);
    }

    public static void nregister_op(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + REGISTER_OP, b2);
    }

    public static void nsqe_op(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + SQE_OP, b2);
    }

    public static void nsqe_flags(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + SQE_FLAGS, b2);
    }

    public static void nresv(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + RESV, b2);
    }

    public static void nresv2(long j, IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(intBuffer, 3);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(intBuffer), j + RESV2, intBuffer.remaining() << 2);
    }

    public static void nresv2(long j, int i, int i2) {
        UNSAFE.putInt((Object) null, j + RESV2 + (Checks.check(i, 3) << 2), i2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRestriction$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingRestriction, Buffer> implements NativeResource {
        private static final IOURingRestriction ELEMENT_FACTORY = IOURingRestriction.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingRestriction.SIZEOF);
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
        public IOURingRestriction getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u16")
        public short opcode() {
            return IOURingRestriction.nopcode(address());
        }

        @NativeType("__u8")
        public byte register_op() {
            return IOURingRestriction.nregister_op(address());
        }

        @NativeType("__u8")
        public byte sqe_op() {
            return IOURingRestriction.nsqe_op(address());
        }

        @NativeType("__u8")
        public byte sqe_flags() {
            return IOURingRestriction.nsqe_flags(address());
        }

        public Buffer opcode(@NativeType("__u16") short s) {
            IOURingRestriction.nopcode(address(), s);
            return this;
        }

        public Buffer register_op(@NativeType("__u8") byte b2) {
            IOURingRestriction.nregister_op(address(), b2);
            return this;
        }

        public Buffer sqe_op(@NativeType("__u8") byte b2) {
            IOURingRestriction.nsqe_op(address(), b2);
            return this;
        }

        public Buffer sqe_flags(@NativeType("__u8") byte b2) {
            IOURingRestriction.nsqe_flags(address(), b2);
            return this;
        }
    }
}
