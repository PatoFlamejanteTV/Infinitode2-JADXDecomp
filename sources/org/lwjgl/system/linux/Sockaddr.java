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

@NativeType("struct sockaddr")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Sockaddr.class */
public class Sockaddr extends Struct<Sockaddr> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int SA_FAMILY;
    public static final int SA_DATA;

    static {
        Struct.Layout __struct = __struct(__member(2), __array(1, 14));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        SA_FAMILY = __struct.offsetof(0);
        SA_DATA = __struct.offsetof(1);
    }

    protected Sockaddr(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public Sockaddr create(long j, ByteBuffer byteBuffer) {
        return new Sockaddr(j, byteBuffer);
    }

    public Sockaddr(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("sa_family_t")
    public short sa_family() {
        return nsa_family(address());
    }

    @NativeType("char[14]")
    public ByteBuffer sa_data() {
        return nsa_data(address());
    }

    @NativeType("char")
    public byte sa_data(int i) {
        return nsa_data(address(), i);
    }

    public Sockaddr sa_family(@NativeType("sa_family_t") short s) {
        nsa_family(address(), s);
        return this;
    }

    public Sockaddr sa_data(@NativeType("char[14]") ByteBuffer byteBuffer) {
        nsa_data(address(), byteBuffer);
        return this;
    }

    public Sockaddr sa_data(int i, @NativeType("char") byte b2) {
        nsa_data(address(), i, b2);
        return this;
    }

    public Sockaddr set(short s, ByteBuffer byteBuffer) {
        sa_family(s);
        sa_data(byteBuffer);
        return this;
    }

    public Sockaddr set(Sockaddr sockaddr) {
        MemoryUtil.memCopy(sockaddr.address(), address(), SIZEOF);
        return this;
    }

    public static Sockaddr malloc() {
        return new Sockaddr(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static Sockaddr calloc() {
        return new Sockaddr(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static Sockaddr create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new Sockaddr(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static Sockaddr create(long j) {
        return new Sockaddr(j, null);
    }

    public static Sockaddr createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new Sockaddr(j, null);
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

    public static Sockaddr malloc(MemoryStack memoryStack) {
        return new Sockaddr(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static Sockaddr calloc(MemoryStack memoryStack) {
        return new Sockaddr(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static short nsa_family(long j) {
        return UNSAFE.getShort((Object) null, j + SA_FAMILY);
    }

    public static ByteBuffer nsa_data(long j) {
        return MemoryUtil.memByteBuffer(j + SA_DATA, 14);
    }

    public static byte nsa_data(long j, int i) {
        return UNSAFE.getByte((Object) null, j + SA_DATA + Checks.check(i, 14));
    }

    public static void nsa_family(long j, short s) {
        UNSAFE.putShort((Object) null, j + SA_FAMILY, s);
    }

    public static void nsa_data(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 14);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + SA_DATA, byteBuffer.remaining());
    }

    public static void nsa_data(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + SA_DATA + Checks.check(i, 14), b2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Sockaddr$Buffer.class */
    public static class Buffer extends StructBuffer<Sockaddr, Buffer> implements NativeResource {
        private static final Sockaddr ELEMENT_FACTORY = Sockaddr.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / Sockaddr.SIZEOF);
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
        public Sockaddr getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("sa_family_t")
        public short sa_family() {
            return Sockaddr.nsa_family(address());
        }

        @NativeType("char[14]")
        public ByteBuffer sa_data() {
            return Sockaddr.nsa_data(address());
        }

        @NativeType("char")
        public byte sa_data(int i) {
            return Sockaddr.nsa_data(address(), i);
        }

        public Buffer sa_family(@NativeType("sa_family_t") short s) {
            Sockaddr.nsa_family(address(), s);
            return this;
        }

        public Buffer sa_data(@NativeType("char[14]") ByteBuffer byteBuffer) {
            Sockaddr.nsa_data(address(), byteBuffer);
            return this;
        }

        public Buffer sa_data(int i, @NativeType("char") byte b2) {
            Sockaddr.nsa_data(address(), i, b2);
            return this;
        }
    }
}
