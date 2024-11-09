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
import org.lwjgl.system.linux.IOVec;

@NativeType("struct msghdr")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Msghdr.class */
public class Msghdr extends Struct<Msghdr> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int MSG_NAME;
    public static final int MSG_NAMELEN;
    public static final int MSG_IOV;
    public static final int MSG_IOVLEN;
    public static final int MSG_CONTROL;
    public static final int MSG_CONTROLLEN;
    public static final int MSG_FLAGS;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        MSG_NAME = __struct.offsetof(0);
        MSG_NAMELEN = __struct.offsetof(1);
        MSG_IOV = __struct.offsetof(2);
        MSG_IOVLEN = __struct.offsetof(3);
        MSG_CONTROL = __struct.offsetof(4);
        MSG_CONTROLLEN = __struct.offsetof(5);
        MSG_FLAGS = __struct.offsetof(6);
    }

    protected Msghdr(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public Msghdr create(long j, ByteBuffer byteBuffer) {
        return new Msghdr(j, byteBuffer);
    }

    public Msghdr(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("void *")
    public ByteBuffer msg_name() {
        return nmsg_name(address());
    }

    @NativeType("socklen_t")
    public int msg_namelen() {
        return nmsg_namelen(address());
    }

    @NativeType("struct iovec *")
    public IOVec.Buffer msg_iov() {
        return nmsg_iov(address());
    }

    @NativeType("size_t")
    public long msg_iovlen() {
        return nmsg_iovlen(address());
    }

    @NativeType("void *")
    public ByteBuffer msg_control() {
        return nmsg_control(address());
    }

    @NativeType("size_t")
    public long msg_controllen() {
        return nmsg_controllen(address());
    }

    public int msg_flags() {
        return nmsg_flags(address());
    }

    public Msghdr msg_name(@NativeType("void *") ByteBuffer byteBuffer) {
        nmsg_name(address(), byteBuffer);
        return this;
    }

    public Msghdr msg_iov(@NativeType("struct iovec *") IOVec.Buffer buffer) {
        nmsg_iov(address(), buffer);
        return this;
    }

    public Msghdr msg_control(@NativeType("void *") ByteBuffer byteBuffer) {
        nmsg_control(address(), byteBuffer);
        return this;
    }

    public Msghdr msg_flags(int i) {
        nmsg_flags(address(), i);
        return this;
    }

    public Msghdr set(ByteBuffer byteBuffer, IOVec.Buffer buffer, ByteBuffer byteBuffer2, int i) {
        msg_name(byteBuffer);
        msg_iov(buffer);
        msg_control(byteBuffer2);
        msg_flags(i);
        return this;
    }

    public Msghdr set(Msghdr msghdr) {
        MemoryUtil.memCopy(msghdr.address(), address(), SIZEOF);
        return this;
    }

    public static Msghdr malloc() {
        return new Msghdr(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static Msghdr calloc() {
        return new Msghdr(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static Msghdr create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new Msghdr(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static Msghdr create(long j) {
        return new Msghdr(j, null);
    }

    public static Msghdr createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new Msghdr(j, null);
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

    public static Msghdr malloc(MemoryStack memoryStack) {
        return new Msghdr(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static Msghdr calloc(MemoryStack memoryStack) {
        return new Msghdr(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ByteBuffer nmsg_name(long j) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + MSG_NAME), nmsg_namelen(j));
    }

    public static int nmsg_namelen(long j) {
        return UNSAFE.getInt((Object) null, j + MSG_NAMELEN);
    }

    public static IOVec.Buffer nmsg_iov(long j) {
        return IOVec.create(MemoryUtil.memGetAddress(j + MSG_IOV), (int) nmsg_iovlen(j));
    }

    public static long nmsg_iovlen(long j) {
        return MemoryUtil.memGetAddress(j + MSG_IOVLEN);
    }

    public static ByteBuffer nmsg_control(long j) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + MSG_CONTROL), (int) nmsg_controllen(j));
    }

    public static long nmsg_controllen(long j) {
        return MemoryUtil.memGetAddress(j + MSG_CONTROLLEN);
    }

    public static int nmsg_flags(long j) {
        return UNSAFE.getInt((Object) null, j + MSG_FLAGS);
    }

    public static void nmsg_name(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + MSG_NAME, MemoryUtil.memAddress(byteBuffer));
        nmsg_namelen(j, byteBuffer.remaining());
    }

    public static void nmsg_namelen(long j, int i) {
        UNSAFE.putInt((Object) null, j + MSG_NAMELEN, i);
    }

    public static void nmsg_iov(long j, IOVec.Buffer buffer) {
        MemoryUtil.memPutAddress(j + MSG_IOV, buffer.address());
        nmsg_iovlen(j, buffer.remaining());
    }

    public static void nmsg_iovlen(long j, long j2) {
        MemoryUtil.memPutAddress(j + MSG_IOVLEN, j2);
    }

    public static void nmsg_control(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + MSG_CONTROL, MemoryUtil.memAddress(byteBuffer));
        nmsg_controllen(j, byteBuffer.remaining());
    }

    public static void nmsg_controllen(long j, long j2) {
        MemoryUtil.memPutAddress(j + MSG_CONTROLLEN, j2);
    }

    public static void nmsg_flags(long j, int i) {
        UNSAFE.putInt((Object) null, j + MSG_FLAGS, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + MSG_NAME));
        Checks.check(MemoryUtil.memGetAddress(j + MSG_IOV));
        Checks.check(MemoryUtil.memGetAddress(j + MSG_CONTROL));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Msghdr$Buffer.class */
    public static class Buffer extends StructBuffer<Msghdr, Buffer> implements NativeResource {
        private static final Msghdr ELEMENT_FACTORY = Msghdr.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / Msghdr.SIZEOF);
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
        public Msghdr getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("void *")
        public ByteBuffer msg_name() {
            return Msghdr.nmsg_name(address());
        }

        @NativeType("socklen_t")
        public int msg_namelen() {
            return Msghdr.nmsg_namelen(address());
        }

        @NativeType("struct iovec *")
        public IOVec.Buffer msg_iov() {
            return Msghdr.nmsg_iov(address());
        }

        @NativeType("size_t")
        public long msg_iovlen() {
            return Msghdr.nmsg_iovlen(address());
        }

        @NativeType("void *")
        public ByteBuffer msg_control() {
            return Msghdr.nmsg_control(address());
        }

        @NativeType("size_t")
        public long msg_controllen() {
            return Msghdr.nmsg_controllen(address());
        }

        public int msg_flags() {
            return Msghdr.nmsg_flags(address());
        }

        public Buffer msg_name(@NativeType("void *") ByteBuffer byteBuffer) {
            Msghdr.nmsg_name(address(), byteBuffer);
            return this;
        }

        public Buffer msg_iov(@NativeType("struct iovec *") IOVec.Buffer buffer) {
            Msghdr.nmsg_iov(address(), buffer);
            return this;
        }

        public Buffer msg_control(@NativeType("void *") ByteBuffer byteBuffer) {
            Msghdr.nmsg_control(address(), byteBuffer);
            return this;
        }

        public Buffer msg_flags(int i) {
            Msghdr.nmsg_flags(address(), i);
            return this;
        }
    }
}
