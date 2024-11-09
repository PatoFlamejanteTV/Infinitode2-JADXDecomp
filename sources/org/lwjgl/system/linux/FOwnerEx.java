package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct f_owner_ex")
/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/FOwnerEx.class */
public class FOwnerEx extends Struct<FOwnerEx> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int PID;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        PID = __struct.offsetof(1);
    }

    protected FOwnerEx(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public FOwnerEx create(long j, ByteBuffer byteBuffer) {
        return new FOwnerEx(j, byteBuffer);
    }

    public FOwnerEx(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int type() {
        return ntype(address());
    }

    @NativeType("pid_t")
    public int pid() {
        return npid(address());
    }

    public FOwnerEx type(int i) {
        ntype(address(), i);
        return this;
    }

    public FOwnerEx pid(@NativeType("pid_t") int i) {
        npid(address(), i);
        return this;
    }

    public FOwnerEx set(int i, int i2) {
        type(i);
        pid(i2);
        return this;
    }

    public FOwnerEx set(FOwnerEx fOwnerEx) {
        MemoryUtil.memCopy(fOwnerEx.address(), address(), SIZEOF);
        return this;
    }

    public static FOwnerEx malloc() {
        return new FOwnerEx(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static FOwnerEx calloc() {
        return new FOwnerEx(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static FOwnerEx create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new FOwnerEx(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static FOwnerEx create(long j) {
        return new FOwnerEx(j, null);
    }

    public static FOwnerEx createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new FOwnerEx(j, null);
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

    public static FOwnerEx malloc(MemoryStack memoryStack) {
        return new FOwnerEx(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static FOwnerEx calloc(MemoryStack memoryStack) {
        return new FOwnerEx(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ntype(long j) {
        return UNSAFE.getInt((Object) null, j + TYPE);
    }

    public static int npid(long j) {
        return UNSAFE.getInt((Object) null, j + PID);
    }

    public static void ntype(long j, int i) {
        UNSAFE.putInt((Object) null, j + TYPE, i);
    }

    public static void npid(long j, int i) {
        UNSAFE.putInt((Object) null, j + PID, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/FOwnerEx$Buffer.class */
    public static class Buffer extends StructBuffer<FOwnerEx, Buffer> implements NativeResource {
        private static final FOwnerEx ELEMENT_FACTORY = FOwnerEx.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / FOwnerEx.SIZEOF);
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
        public FOwnerEx getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return FOwnerEx.ntype(address());
        }

        @NativeType("pid_t")
        public int pid() {
            return FOwnerEx.npid(address());
        }

        public Buffer type(int i) {
            FOwnerEx.ntype(address(), i);
            return this;
        }

        public Buffer pid(@NativeType("pid_t") int i) {
            FOwnerEx.npid(address(), i);
            return this;
        }
    }
}
