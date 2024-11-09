package org.lwjgl.system.linux.liburing;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct io_uring_recvmsg_out")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRecvmsgOut.class */
public class IOURingRecvmsgOut extends Struct<IOURingRecvmsgOut> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NAMELEN;
    public static final int CONTROLLEN;
    public static final int PAYLOADLEN;
    public static final int FLAGS;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NAMELEN = __struct.offsetof(0);
        CONTROLLEN = __struct.offsetof(1);
        PAYLOADLEN = __struct.offsetof(2);
        FLAGS = __struct.offsetof(3);
    }

    protected IOURingRecvmsgOut(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public IOURingRecvmsgOut create(long j, ByteBuffer byteBuffer) {
        return new IOURingRecvmsgOut(j, byteBuffer);
    }

    public IOURingRecvmsgOut(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u32")
    public int namelen() {
        return nnamelen(address());
    }

    @NativeType("__u32")
    public int controllen() {
        return ncontrollen(address());
    }

    @NativeType("__u32")
    public int payloadlen() {
        return npayloadlen(address());
    }

    @NativeType("__u32")
    public int flags() {
        return nflags(address());
    }

    public IOURingRecvmsgOut namelen(@NativeType("__u32") int i) {
        nnamelen(address(), i);
        return this;
    }

    public IOURingRecvmsgOut controllen(@NativeType("__u32") int i) {
        ncontrollen(address(), i);
        return this;
    }

    public IOURingRecvmsgOut payloadlen(@NativeType("__u32") int i) {
        npayloadlen(address(), i);
        return this;
    }

    public IOURingRecvmsgOut flags(@NativeType("__u32") int i) {
        nflags(address(), i);
        return this;
    }

    public IOURingRecvmsgOut set(int i, int i2, int i3, int i4) {
        namelen(i);
        controllen(i2);
        payloadlen(i3);
        flags(i4);
        return this;
    }

    public IOURingRecvmsgOut set(IOURingRecvmsgOut iOURingRecvmsgOut) {
        MemoryUtil.memCopy(iOURingRecvmsgOut.address(), address(), SIZEOF);
        return this;
    }

    public static IOURingRecvmsgOut malloc() {
        return new IOURingRecvmsgOut(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static IOURingRecvmsgOut calloc() {
        return new IOURingRecvmsgOut(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static IOURingRecvmsgOut create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new IOURingRecvmsgOut(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static IOURingRecvmsgOut create(long j) {
        return new IOURingRecvmsgOut(j, null);
    }

    public static IOURingRecvmsgOut createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new IOURingRecvmsgOut(j, null);
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

    public static IOURingRecvmsgOut malloc(MemoryStack memoryStack) {
        return new IOURingRecvmsgOut(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static IOURingRecvmsgOut calloc(MemoryStack memoryStack) {
        return new IOURingRecvmsgOut(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nnamelen(long j) {
        return UNSAFE.getInt((Object) null, j + NAMELEN);
    }

    public static int ncontrollen(long j) {
        return UNSAFE.getInt((Object) null, j + CONTROLLEN);
    }

    public static int npayloadlen(long j) {
        return UNSAFE.getInt((Object) null, j + PAYLOADLEN);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static void nnamelen(long j, int i) {
        UNSAFE.putInt((Object) null, j + NAMELEN, i);
    }

    public static void ncontrollen(long j, int i) {
        UNSAFE.putInt((Object) null, j + CONTROLLEN, i);
    }

    public static void npayloadlen(long j, int i) {
        UNSAFE.putInt((Object) null, j + PAYLOADLEN, i);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/liburing/IOURingRecvmsgOut$Buffer.class */
    public static class Buffer extends StructBuffer<IOURingRecvmsgOut, Buffer> implements NativeResource {
        private static final IOURingRecvmsgOut ELEMENT_FACTORY = IOURingRecvmsgOut.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / IOURingRecvmsgOut.SIZEOF);
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
        public IOURingRecvmsgOut getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u32")
        public int namelen() {
            return IOURingRecvmsgOut.nnamelen(address());
        }

        @NativeType("__u32")
        public int controllen() {
            return IOURingRecvmsgOut.ncontrollen(address());
        }

        @NativeType("__u32")
        public int payloadlen() {
            return IOURingRecvmsgOut.npayloadlen(address());
        }

        @NativeType("__u32")
        public int flags() {
            return IOURingRecvmsgOut.nflags(address());
        }

        public Buffer namelen(@NativeType("__u32") int i) {
            IOURingRecvmsgOut.nnamelen(address(), i);
            return this;
        }

        public Buffer controllen(@NativeType("__u32") int i) {
            IOURingRecvmsgOut.ncontrollen(address(), i);
            return this;
        }

        public Buffer payloadlen(@NativeType("__u32") int i) {
            IOURingRecvmsgOut.npayloadlen(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("__u32") int i) {
            IOURingRecvmsgOut.nflags(address(), i);
            return this;
        }
    }
}
