package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.function.Consumer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;
import org.lwjgl.system.linux.liburing.IOURingProbeOp;

@NativeType("struct io_uring_probe")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingProbe.class */
public class IOURingProbe extends Struct<IOURingProbe> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int LAST_OP;
    public static final int OPS_LEN;
    public static final int RESV;
    public static final int RESV2;
    public static final int OPS;

    static {
        Struct.Layout __struct = __struct(__member(1), __member(1), __member(2), __array(4, 3), __array(IOURingProbeOp.SIZEOF, IOURingProbeOp.ALIGNOF, 0));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        LAST_OP = __struct.offsetof(0);
        OPS_LEN = __struct.offsetof(1);
        RESV = __struct.offsetof(2);
        RESV2 = __struct.offsetof(3);
        OPS = __struct.offsetof(4);
    }

    protected IOURingProbe(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingProbe create(long j, ByteBuffer byteBuffer) {
        return new IOURingProbe(j, byteBuffer);
    }

    public IOURingProbe(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u8")
    public byte last_op() {
        return nlast_op(address());
    }

    @NativeType("__u8")
    public byte ops_len() {
        return nops_len(address());
    }

    @NativeType("struct io_uring_probe_op[0]")
    public IOURingProbeOp.Buffer ops() {
        return nops(address());
    }

    @NativeType("struct io_uring_probe_op")
    public IOURingProbeOp ops(int i) {
        return nops(address(), i);
    }

    public IOURingProbe last_op(@NativeType("__u8") byte b2) {
        nlast_op(address(), b2);
        return this;
    }

    public IOURingProbe ops_len(@NativeType("__u8") byte b2) {
        nops_len(address(), b2);
        return this;
    }

    public IOURingProbe ops(@NativeType("struct io_uring_probe_op[0]") IOURingProbeOp.Buffer buffer) {
        nops(address(), buffer);
        return this;
    }

    public IOURingProbe ops(int i, @NativeType("struct io_uring_probe_op") IOURingProbeOp iOURingProbeOp) {
        nops(address(), i, iOURingProbeOp);
        return this;
    }

    public IOURingProbe ops(Consumer<IOURingProbeOp.Buffer> consumer) {
        consumer.accept(ops());
        return this;
    }

    public IOURingProbe ops(int i, Consumer<IOURingProbeOp> consumer) {
        consumer.accept(ops(i));
        return this;
    }

    public IOURingProbe set(byte b2, byte b3, IOURingProbeOp.Buffer buffer) {
        last_op(b2);
        ops_len(b3);
        ops(buffer);
        return this;
    }

    public IOURingProbe set(IOURingProbe iOURingProbe) {
        MemoryUtil.memCopy(iOURingProbe.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingProbe malloc() {
        return new IOURingProbe(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingProbe calloc() {
        return new IOURingProbe(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingProbe create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingProbe(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingProbe create(long j) {
        return new IOURingProbe(j, null);
    }

    public static IOURingProbe createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingProbe(j, null);
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

    public static IOURingProbe malloc(MemoryStack memoryStack) {
        return new IOURingProbe(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingProbe calloc(MemoryStack memoryStack) {
        return new IOURingProbe(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static byte nlast_op(long j) {
        return UNSAFE.getByte((Object) null, j + LAST_OP);
    }

    public static byte nops_len(long j) {
        return UNSAFE.getByte((Object) null, j + OPS_LEN);
    }

    public static short nresv(long j) {
        return UNSAFE.getShort((Object) null, j + RESV);
    }

    public static IntBuffer nresv2(long j) {
        return MemoryUtil.memIntBuffer(j + RESV2, 3);
    }

    public static int nresv2(long j, int i) {
        return UNSAFE.getInt((Object) null, j + RESV2 + (Checks.check(i, 3) << 2));
    }

    public static IOURingProbeOp.Buffer nops(long j) {
        return IOURingProbeOp.create(j + OPS, 0);
    }

    public static IOURingProbeOp nops(long j, int i) {
        return IOURingProbeOp.create(j + OPS + (Checks.check(i, 0) * IOURingProbeOp.SIZEOF));
    }

    public static void nlast_op(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + LAST_OP, b2);
    }

    public static void nops_len(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + OPS_LEN, b2);
    }

    public static void nresv(long j, short s) {
        UNSAFE.putShort((Object) null, j + RESV, s);
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

    public static void nops(long j, IOURingProbeOp.Buffer buffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(buffer, 0);
        }
        MemoryUtil.memCopy(buffer.address(), j + OPS, buffer.remaining() * IOURingProbeOp.SIZEOF);
    }

    public static void nops(long j, int i, IOURingProbeOp iOURingProbeOp) {
        MemoryUtil.memCopy(iOURingProbeOp.address(), j + OPS + (Checks.check(i, 0) * IOURingProbeOp.SIZEOF), IOURingProbeOp.SIZEOF);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingProbe$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingProbe, Buffer> implements NativeResource {
        private static final IOURingProbe ELEMENT_FACTORY = IOURingProbe.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingProbe.SIZEOF);
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
        public IOURingProbe getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u8")
        public byte last_op() {
            return IOURingProbe.nlast_op(address());
        }

        @NativeType("__u8")
        public byte ops_len() {
            return IOURingProbe.nops_len(address());
        }

        @NativeType("struct io_uring_probe_op[0]")
        public IOURingProbeOp.Buffer ops() {
            return IOURingProbe.nops(address());
        }

        @NativeType("struct io_uring_probe_op")
        public IOURingProbeOp ops(int i) {
            return IOURingProbe.nops(address(), i);
        }

        public Buffer last_op(@NativeType("__u8") byte b2) {
            IOURingProbe.nlast_op(address(), b2);
            return this;
        }

        public Buffer ops_len(@NativeType("__u8") byte b2) {
            IOURingProbe.nops_len(address(), b2);
            return this;
        }

        public Buffer ops(@NativeType("struct io_uring_probe_op[0]") IOURingProbeOp.Buffer buffer) {
            IOURingProbe.nops(address(), buffer);
            return this;
        }

        public Buffer ops(int i, @NativeType("struct io_uring_probe_op") IOURingProbeOp iOURingProbeOp) {
            IOURingProbe.nops(address(), i, iOURingProbeOp);
            return this;
        }

        public Buffer ops(Consumer<IOURingProbeOp.Buffer> consumer) {
            consumer.accept(ops());
            return this;
        }

        public Buffer ops(int i, Consumer<IOURingProbeOp> consumer) {
            consumer.accept(ops(i));
            return this;
        }
    }
}
