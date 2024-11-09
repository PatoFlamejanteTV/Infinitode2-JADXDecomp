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

@NativeType("struct cmsghdr")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/CMsghdr.class */
public class CMsghdr extends Struct<CMsghdr> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CMSG_LEN;
    public static final int CMSG_LEVEL;
    public static final int CMSG_TYPE;
    public static final int CMSG_DATA;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __array(1, 0));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        CMSG_LEN = __struct.offsetof(0);
        CMSG_LEVEL = __struct.offsetof(1);
        CMSG_TYPE = __struct.offsetof(2);
        CMSG_DATA = __struct.offsetof(3);
    }

    protected CMsghdr(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public CMsghdr create(long j, ByteBuffer byteBuffer) {
        return new CMsghdr(j, byteBuffer);
    }

    public CMsghdr(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("socklen_t")
    public int cmsg_len() {
        return ncmsg_len(address());
    }

    public int cmsg_level() {
        return ncmsg_level(address());
    }

    public int cmsg_type() {
        return ncmsg_type(address());
    }

    @NativeType("char[0]")
    public ByteBuffer cmsg_data() {
        return ncmsg_data(address());
    }

    @NativeType("char")
    public byte cmsg_data(int i) {
        return ncmsg_data(address(), i);
    }

    public CMsghdr cmsg_len(@NativeType("socklen_t") int i) {
        ncmsg_len(address(), i);
        return this;
    }

    public CMsghdr cmsg_level(int i) {
        ncmsg_level(address(), i);
        return this;
    }

    public CMsghdr cmsg_type(int i) {
        ncmsg_type(address(), i);
        return this;
    }

    public CMsghdr cmsg_data(@NativeType("char[0]") ByteBuffer byteBuffer) {
        ncmsg_data(address(), byteBuffer);
        return this;
    }

    public CMsghdr cmsg_data(int i, @NativeType("char") byte b2) {
        ncmsg_data(address(), i, b2);
        return this;
    }

    public CMsghdr set(int i, int i2, int i3, ByteBuffer byteBuffer) {
        cmsg_len(i);
        cmsg_level(i2);
        cmsg_type(i3);
        cmsg_data(byteBuffer);
        return this;
    }

    public CMsghdr set(CMsghdr cMsghdr) {
        MemoryUtil.memCopy(cMsghdr.address(), address(), SIZEOF);
        return this;
    }

    public static CMsghdr malloc() {
        return new CMsghdr(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static CMsghdr calloc() {
        return new CMsghdr(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static CMsghdr create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new CMsghdr(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static CMsghdr create(long j) {
        return new CMsghdr(j, null);
    }

    public static CMsghdr createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new CMsghdr(j, null);
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

    public static CMsghdr malloc(MemoryStack memoryStack) {
        return new CMsghdr(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static CMsghdr calloc(MemoryStack memoryStack) {
        return new CMsghdr(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ncmsg_len(long j) {
        return UNSAFE.getInt((Object) null, j + CMSG_LEN);
    }

    public static int ncmsg_level(long j) {
        return UNSAFE.getInt((Object) null, j + CMSG_LEVEL);
    }

    public static int ncmsg_type(long j) {
        return UNSAFE.getInt((Object) null, j + CMSG_TYPE);
    }

    public static ByteBuffer ncmsg_data(long j) {
        return MemoryUtil.memByteBuffer(j + CMSG_DATA, 0);
    }

    public static byte ncmsg_data(long j, int i) {
        return UNSAFE.getByte((Object) null, j + CMSG_DATA + Checks.check(i, 0));
    }

    public static void ncmsg_len(long j, int i) {
        UNSAFE.putInt((Object) null, j + CMSG_LEN, i);
    }

    public static void ncmsg_level(long j, int i) {
        UNSAFE.putInt((Object) null, j + CMSG_LEVEL, i);
    }

    public static void ncmsg_type(long j, int i) {
        UNSAFE.putInt((Object) null, j + CMSG_TYPE, i);
    }

    public static void ncmsg_data(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 0);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + CMSG_DATA, byteBuffer.remaining());
    }

    public static void ncmsg_data(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + CMSG_DATA + Checks.check(i, 0), b2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/CMsghdr$Buffer.class */
    public static class Buffer extends StructBuffer<CMsghdr, Buffer> implements NativeResource {
        private static final CMsghdr ELEMENT_FACTORY = CMsghdr.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / CMsghdr.SIZEOF);
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
        public CMsghdr getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("socklen_t")
        public int cmsg_len() {
            return CMsghdr.ncmsg_len(address());
        }

        public int cmsg_level() {
            return CMsghdr.ncmsg_level(address());
        }

        public int cmsg_type() {
            return CMsghdr.ncmsg_type(address());
        }

        @NativeType("char[0]")
        public ByteBuffer cmsg_data() {
            return CMsghdr.ncmsg_data(address());
        }

        @NativeType("char")
        public byte cmsg_data(int i) {
            return CMsghdr.ncmsg_data(address(), i);
        }

        public Buffer cmsg_len(@NativeType("socklen_t") int i) {
            CMsghdr.ncmsg_len(address(), i);
            return this;
        }

        public Buffer cmsg_level(int i) {
            CMsghdr.ncmsg_level(address(), i);
            return this;
        }

        public Buffer cmsg_type(int i) {
            CMsghdr.ncmsg_type(address(), i);
            return this;
        }

        public Buffer cmsg_data(@NativeType("char[0]") ByteBuffer byteBuffer) {
            CMsghdr.ncmsg_data(address(), byteBuffer);
            return this;
        }

        public Buffer cmsg_data(int i, @NativeType("char") byte b2) {
            CMsghdr.ncmsg_data(address(), i, b2);
            return this;
        }
    }
}
