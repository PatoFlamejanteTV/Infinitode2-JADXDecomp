package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/POINT.class */
public class POINT extends Struct<POINT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X;
    public static final int Y;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X = __struct.offsetof(0);
        Y = __struct.offsetof(1);
    }

    protected POINT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public POINT create(long j, ByteBuffer byteBuffer) {
        return new POINT(j, byteBuffer);
    }

    public POINT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("LONG")
    public int x() {
        return nx(address());
    }

    @NativeType("LONG")
    public int y() {
        return ny(address());
    }

    public POINT x(@NativeType("LONG") int i) {
        nx(address(), i);
        return this;
    }

    public POINT y(@NativeType("LONG") int i) {
        ny(address(), i);
        return this;
    }

    public POINT set(int i, int i2) {
        x(i);
        y(i2);
        return this;
    }

    public POINT set(POINT point) {
        MemoryUtil.memCopy(point.address(), address(), SIZEOF);
        return this;
    }

    public static POINT malloc() {
        return new POINT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static POINT calloc() {
        return new POINT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static POINT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new POINT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static POINT create(long j) {
        return new POINT(j, null);
    }

    public static POINT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new POINT(j, null);
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

    @Deprecated
    public static POINT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static POINT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static POINT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static POINT callocStack(MemoryStack memoryStack) {
        return calloc(memoryStack);
    }

    @Deprecated
    public static Buffer mallocStack(int i) {
        return malloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer callocStack(int i) {
        return calloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer mallocStack(int i, MemoryStack memoryStack) {
        return malloc(i, memoryStack);
    }

    @Deprecated
    public static Buffer callocStack(int i, MemoryStack memoryStack) {
        return calloc(i, memoryStack);
    }

    public static POINT malloc(MemoryStack memoryStack) {
        return new POINT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static POINT calloc(MemoryStack memoryStack) {
        return new POINT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nx(long j) {
        return UNSAFE.getInt((Object) null, j + X);
    }

    public static int ny(long j) {
        return UNSAFE.getInt((Object) null, j + Y);
    }

    public static void nx(long j, int i) {
        UNSAFE.putInt((Object) null, j + X, i);
    }

    public static void ny(long j, int i) {
        UNSAFE.putInt((Object) null, j + Y, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/POINT$Buffer.class */
    public static class Buffer extends StructBuffer<POINT, Buffer> implements NativeResource {
        private static final POINT ELEMENT_FACTORY = POINT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / POINT.SIZEOF);
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
        public POINT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("LONG")
        public int x() {
            return POINT.nx(address());
        }

        @NativeType("LONG")
        public int y() {
            return POINT.ny(address());
        }

        public Buffer x(@NativeType("LONG") int i) {
            POINT.nx(address(), i);
            return this;
        }

        public Buffer y(@NativeType("LONG") int i) {
            POINT.ny(address(), i);
            return this;
        }
    }
}
