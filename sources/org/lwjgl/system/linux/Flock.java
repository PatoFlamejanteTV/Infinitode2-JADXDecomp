package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct flock64")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Flock.class */
public class Flock extends Struct<Flock> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int L_TYPE;
    public static final int L_WHENCE;
    public static final int L_START;
    public static final int L_LEN;
    public static final int L_PID;

    static {
        Struct.Layout __struct = __struct(__member(2), __member(2), __member(8), __member(8), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        L_TYPE = __struct.offsetof(0);
        L_WHENCE = __struct.offsetof(1);
        L_START = __struct.offsetof(2);
        L_LEN = __struct.offsetof(3);
        L_PID = __struct.offsetof(4);
    }

    protected Flock(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public Flock create(long j, ByteBuffer byteBuffer) {
        return new Flock(j, byteBuffer);
    }

    public Flock(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public short l_type() {
        return nl_type(address());
    }

    public short l_whence() {
        return nl_whence(address());
    }

    @NativeType("off_t")
    public long l_start() {
        return nl_start(address());
    }

    @NativeType("off_t")
    public long l_len() {
        return nl_len(address());
    }

    @NativeType("pid_t")
    public int l_pid() {
        return nl_pid(address());
    }

    public Flock l_type(short s) {
        nl_type(address(), s);
        return this;
    }

    public Flock l_whence(short s) {
        nl_whence(address(), s);
        return this;
    }

    public Flock l_start(@NativeType("off_t") long j) {
        nl_start(address(), j);
        return this;
    }

    public Flock l_len(@NativeType("off_t") long j) {
        nl_len(address(), j);
        return this;
    }

    public Flock l_pid(@NativeType("pid_t") int i) {
        nl_pid(address(), i);
        return this;
    }

    public Flock set(short s, short s2, long j, long j2, int i) {
        l_type(s);
        l_whence(s2);
        l_start(j);
        l_len(j2);
        l_pid(i);
        return this;
    }

    public Flock set(Flock flock) {
        MemoryUtil.memCopy(flock.address(), address(), SIZEOF);
        return this;
    }

    public static Flock malloc() {
        return new Flock(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static Flock calloc() {
        return new Flock(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static Flock create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new Flock(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static Flock create(long j) {
        return new Flock(j, null);
    }

    public static Flock createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new Flock(j, null);
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

    public static Flock malloc(MemoryStack memoryStack) {
        return new Flock(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static Flock calloc(MemoryStack memoryStack) {
        return new Flock(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static short nl_type(long j) {
        return UNSAFE.getShort((Object) null, j + L_TYPE);
    }

    public static short nl_whence(long j) {
        return UNSAFE.getShort((Object) null, j + L_WHENCE);
    }

    public static long nl_start(long j) {
        return UNSAFE.getLong((Object) null, j + L_START);
    }

    public static long nl_len(long j) {
        return UNSAFE.getLong((Object) null, j + L_LEN);
    }

    public static int nl_pid(long j) {
        return UNSAFE.getInt((Object) null, j + L_PID);
    }

    public static void nl_type(long j, short s) {
        UNSAFE.putShort((Object) null, j + L_TYPE, s);
    }

    public static void nl_whence(long j, short s) {
        UNSAFE.putShort((Object) null, j + L_WHENCE, s);
    }

    public static void nl_start(long j, long j2) {
        UNSAFE.putLong((Object) null, j + L_START, j2);
    }

    public static void nl_len(long j, long j2) {
        UNSAFE.putLong((Object) null, j + L_LEN, j2);
    }

    public static void nl_pid(long j, int i) {
        UNSAFE.putInt((Object) null, j + L_PID, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Flock$Buffer.class */
    public static class Buffer extends StructBuffer<Flock, Buffer> implements NativeResource {
        private static final Flock ELEMENT_FACTORY = Flock.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / Flock.SIZEOF);
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
        public Flock getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public short l_type() {
            return Flock.nl_type(address());
        }

        public short l_whence() {
            return Flock.nl_whence(address());
        }

        @NativeType("off_t")
        public long l_start() {
            return Flock.nl_start(address());
        }

        @NativeType("off_t")
        public long l_len() {
            return Flock.nl_len(address());
        }

        @NativeType("pid_t")
        public int l_pid() {
            return Flock.nl_pid(address());
        }

        public Buffer l_type(short s) {
            Flock.nl_type(address(), s);
            return this;
        }

        public Buffer l_whence(short s) {
            Flock.nl_whence(address(), s);
            return this;
        }

        public Buffer l_start(@NativeType("off_t") long j) {
            Flock.nl_start(address(), j);
            return this;
        }

        public Buffer l_len(@NativeType("off_t") long j) {
            Flock.nl_len(address(), j);
            return this;
        }

        public Buffer l_pid(@NativeType("pid_t") int i) {
            Flock.nl_pid(address(), i);
            return this;
        }
    }
}
