package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct open_how")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/OpenHow.class */
public class OpenHow extends Struct<OpenHow> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int FLAGS;
    public static final int MODE;
    public static final int RESOLVE;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(8), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        FLAGS = __struct.offsetof(0);
        MODE = __struct.offsetof(1);
        RESOLVE = __struct.offsetof(2);
    }

    protected OpenHow(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public OpenHow create(long j, ByteBuffer byteBuffer) {
        return new OpenHow(j, byteBuffer);
    }

    public OpenHow(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("__u64")
    public long flags() {
        return nflags(address());
    }

    @NativeType("__u64")
    public long mode() {
        return nmode(address());
    }

    @NativeType("__u64")
    public long resolve() {
        return nresolve(address());
    }

    public OpenHow flags(@NativeType("__u64") long j) {
        nflags(address(), j);
        return this;
    }

    public OpenHow mode(@NativeType("__u64") long j) {
        nmode(address(), j);
        return this;
    }

    public OpenHow resolve(@NativeType("__u64") long j) {
        nresolve(address(), j);
        return this;
    }

    public OpenHow set(long j, long j2, long j3) {
        flags(j);
        mode(j2);
        resolve(j3);
        return this;
    }

    public OpenHow set(OpenHow openHow) {
        MemoryUtil.memCopy(openHow.address(), address(), SIZEOF);
        return this;
    }

    public static OpenHow malloc() {
        return new OpenHow(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static OpenHow calloc() {
        return new OpenHow(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static OpenHow create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new OpenHow(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static OpenHow create(long j) {
        return new OpenHow(j, null);
    }

    public static OpenHow createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new OpenHow(j, null);
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

    public static OpenHow malloc(MemoryStack memoryStack) {
        return new OpenHow(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static OpenHow calloc(MemoryStack memoryStack) {
        return new OpenHow(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nflags(long j) {
        return UNSAFE.getLong((Object) null, j + FLAGS);
    }

    public static long nmode(long j) {
        return UNSAFE.getLong((Object) null, j + MODE);
    }

    public static long nresolve(long j) {
        return UNSAFE.getLong((Object) null, j + RESOLVE);
    }

    public static void nflags(long j, long j2) {
        UNSAFE.putLong((Object) null, j + FLAGS, j2);
    }

    public static void nmode(long j, long j2) {
        UNSAFE.putLong((Object) null, j + MODE, j2);
    }

    public static void nresolve(long j, long j2) {
        UNSAFE.putLong((Object) null, j + RESOLVE, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/OpenHow$Buffer.class */
    public static class Buffer extends StructBuffer<OpenHow, Buffer> implements NativeResource {
        private static final OpenHow ELEMENT_FACTORY = OpenHow.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / OpenHow.SIZEOF);
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
        public OpenHow getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("__u64")
        public long flags() {
            return OpenHow.nflags(address());
        }

        @NativeType("__u64")
        public long mode() {
            return OpenHow.nmode(address());
        }

        @NativeType("__u64")
        public long resolve() {
            return OpenHow.nresolve(address());
        }

        public Buffer flags(@NativeType("__u64") long j) {
            OpenHow.nflags(address(), j);
            return this;
        }

        public Buffer mode(@NativeType("__u64") long j) {
            OpenHow.nmode(address(), j);
            return this;
        }

        public Buffer resolve(@NativeType("__u64") long j) {
            OpenHow.nresolve(address(), j);
            return this;
        }
    }
}
