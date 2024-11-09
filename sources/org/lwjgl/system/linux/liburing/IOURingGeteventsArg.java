package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_getevents_arg")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingGeteventsArg.class */
public class IOURingGeteventsArg extends Struct<IOURingGeteventsArg> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int SIGMASK;
    public static final int SIGMASK_SZ;
    public static final int PAD;
    public static final int TS;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(4), __member(4), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        SIGMASK = __struct.offsetof(0);
        SIGMASK_SZ = __struct.offsetof(1);
        PAD = __struct.offsetof(2);
        TS = __struct.offsetof(3);
    }

    protected IOURingGeteventsArg(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingGeteventsArg create(long j, ByteBuffer byteBuffer) {
        return new IOURingGeteventsArg(j, byteBuffer);
    }

    public IOURingGeteventsArg(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u64")
    public long sigmask() {
        return nsigmask(address());
    }

    @NativeType("__u32")
    public int sigmask_sz() {
        return nsigmask_sz(address());
    }

    @NativeType("__u32")
    public int pad() {
        return npad(address());
    }

    @NativeType("__u64")
    public long ts() {
        return nts(address());
    }

    public IOURingGeteventsArg sigmask(@NativeType("__u64") long j) {
        nsigmask(address(), j);
        return this;
    }

    public IOURingGeteventsArg sigmask_sz(@NativeType("__u32") int i) {
        nsigmask_sz(address(), i);
        return this;
    }

    public IOURingGeteventsArg pad(@NativeType("__u32") int i) {
        npad(address(), i);
        return this;
    }

    public IOURingGeteventsArg ts(@NativeType("__u64") long j) {
        nts(address(), j);
        return this;
    }

    public IOURingGeteventsArg set(long j, int i, int i2, long j2) {
        sigmask(j);
        sigmask_sz(i);
        pad(i2);
        ts(j2);
        return this;
    }

    public IOURingGeteventsArg set(IOURingGeteventsArg iOURingGeteventsArg) {
        MemoryUtil.memCopy(iOURingGeteventsArg.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingGeteventsArg malloc() {
        return new IOURingGeteventsArg(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingGeteventsArg calloc() {
        return new IOURingGeteventsArg(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingGeteventsArg create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingGeteventsArg(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingGeteventsArg create(long j) {
        return new IOURingGeteventsArg(j, null);
    }

    public static IOURingGeteventsArg createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingGeteventsArg(j, null);
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

    public static IOURingGeteventsArg malloc(MemoryStack memoryStack) {
        return new IOURingGeteventsArg(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingGeteventsArg calloc(MemoryStack memoryStack) {
        return new IOURingGeteventsArg(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nsigmask(long j) {
        return UNSAFE.getLong((Object) null, j + SIGMASK);
    }

    public static int nsigmask_sz(long j) {
        return UNSAFE.getInt((Object) null, j + SIGMASK_SZ);
    }

    public static int npad(long j) {
        return UNSAFE.getInt((Object) null, j + PAD);
    }

    public static long nts(long j) {
        return UNSAFE.getLong((Object) null, j + TS);
    }

    public static void nsigmask(long j, long j2) {
        UNSAFE.putLong((Object) null, j + SIGMASK, j2);
    }

    public static void nsigmask_sz(long j, int i) {
        UNSAFE.putInt((Object) null, j + SIGMASK_SZ, i);
    }

    public static void npad(long j, int i) {
        UNSAFE.putInt((Object) null, j + PAD, i);
    }

    public static void nts(long j, long j2) {
        UNSAFE.putLong((Object) null, j + TS, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingGeteventsArg$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingGeteventsArg, Buffer> implements NativeResource {
        private static final IOURingGeteventsArg ELEMENT_FACTORY = IOURingGeteventsArg.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingGeteventsArg.SIZEOF);
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
        public IOURingGeteventsArg getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u64")
        public long sigmask() {
            return IOURingGeteventsArg.nsigmask(address());
        }

        @NativeType("__u32")
        public int sigmask_sz() {
            return IOURingGeteventsArg.nsigmask_sz(address());
        }

        @NativeType("__u32")
        public int pad() {
            return IOURingGeteventsArg.npad(address());
        }

        @NativeType("__u64")
        public long ts() {
            return IOURingGeteventsArg.nts(address());
        }

        public Buffer sigmask(@NativeType("__u64") long j) {
            IOURingGeteventsArg.nsigmask(address(), j);
            return this;
        }

        public Buffer sigmask_sz(@NativeType("__u32") int i) {
            IOURingGeteventsArg.nsigmask_sz(address(), i);
            return this;
        }

        public Buffer pad(@NativeType("__u32") int i) {
            IOURingGeteventsArg.npad(address(), i);
            return this;
        }

        public Buffer ts(@NativeType("__u64") long j) {
            IOURingGeteventsArg.nts(address(), j);
            return this;
        }
    }
}
