package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CGPoint.class */
public class CGPoint extends Struct<CGPoint> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X;
    public static final int Y;

    static {
        Struct.Layout __struct = __struct(__member(8), __member(8));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X = __struct.offsetof(0);
        Y = __struct.offsetof(1);
    }

    protected CGPoint(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public CGPoint create(long j, ByteBuffer byteBuffer) {
        return new CGPoint(j, byteBuffer);
    }

    public CGPoint(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("CGFloat")
    public double x() {
        return nx(address());
    }

    @NativeType("CGFloat")
    public double y() {
        return ny(address());
    }

    public CGPoint x(@NativeType("CGFloat") double d) {
        nx(address(), d);
        return this;
    }

    public CGPoint y(@NativeType("CGFloat") double d) {
        ny(address(), d);
        return this;
    }

    public CGPoint set(double d, double d2) {
        x(d);
        y(d2);
        return this;
    }

    public CGPoint set(CGPoint cGPoint) {
        MemoryUtil.memCopy(cGPoint.address(), address(), SIZEOF);
        return this;
    }

    public static CGPoint malloc() {
        return new CGPoint(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static CGPoint calloc() {
        return new CGPoint(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static CGPoint create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new CGPoint(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static CGPoint create(long j) {
        return new CGPoint(j, null);
    }

    public static CGPoint createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new CGPoint(j, null);
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
    public static CGPoint mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static CGPoint callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static CGPoint mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static CGPoint callocStack(MemoryStack memoryStack) {
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

    public static CGPoint malloc(MemoryStack memoryStack) {
        return new CGPoint(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static CGPoint calloc(MemoryStack memoryStack) {
        return new CGPoint(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static double nx(long j) {
        return UNSAFE.getDouble((Object) null, j + X);
    }

    public static double ny(long j) {
        return UNSAFE.getDouble((Object) null, j + Y);
    }

    public static void nx(long j, double d) {
        UNSAFE.putDouble((Object) null, j + X, d);
    }

    public static void ny(long j, double d) {
        UNSAFE.putDouble((Object) null, j + Y, d);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/CGPoint$Buffer.class */
    public static class Buffer extends StructBuffer<CGPoint, Buffer> implements NativeResource {
        private static final CGPoint ELEMENT_FACTORY = CGPoint.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / CGPoint.SIZEOF);
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
        public CGPoint getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("CGFloat")
        public double x() {
            return CGPoint.nx(address());
        }

        @NativeType("CGFloat")
        public double y() {
            return CGPoint.ny(address());
        }

        public Buffer x(@NativeType("CGFloat") double d) {
            CGPoint.nx(address(), d);
            return this;
        }

        public Buffer y(@NativeType("CGFloat") double d) {
            CGPoint.ny(address(), d);
            return this;
        }
    }
}
