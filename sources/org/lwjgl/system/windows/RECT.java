package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/RECT.class */
public class RECT extends Struct<RECT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int LEFT;
    public static final int TOP;
    public static final int RIGHT;
    public static final int BOTTOM;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        LEFT = __struct.offsetof(0);
        TOP = __struct.offsetof(1);
        RIGHT = __struct.offsetof(2);
        BOTTOM = __struct.offsetof(3);
    }

    protected RECT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public RECT create(long j, ByteBuffer byteBuffer) {
        return new RECT(j, byteBuffer);
    }

    public RECT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("LONG")
    public int left() {
        return nleft(address());
    }

    @NativeType("LONG")
    public int top() {
        return ntop(address());
    }

    @NativeType("LONG")
    public int right() {
        return nright(address());
    }

    @NativeType("LONG")
    public int bottom() {
        return nbottom(address());
    }

    public RECT left(@NativeType("LONG") int i) {
        nleft(address(), i);
        return this;
    }

    public RECT top(@NativeType("LONG") int i) {
        ntop(address(), i);
        return this;
    }

    public RECT right(@NativeType("LONG") int i) {
        nright(address(), i);
        return this;
    }

    public RECT bottom(@NativeType("LONG") int i) {
        nbottom(address(), i);
        return this;
    }

    public RECT set(int i, int i2, int i3, int i4) {
        left(i);
        top(i2);
        right(i3);
        bottom(i4);
        return this;
    }

    public RECT set(RECT rect) {
        MemoryUtil.memCopy(rect.address(), address(), SIZEOF);
        return this;
    }

    public static RECT malloc() {
        return new RECT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static RECT calloc() {
        return new RECT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static RECT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new RECT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static RECT create(long j) {
        return new RECT(j, null);
    }

    public static RECT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new RECT(j, null);
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
    public static RECT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static RECT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static RECT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static RECT callocStack(MemoryStack memoryStack) {
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

    public static RECT malloc(MemoryStack memoryStack) {
        return new RECT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static RECT calloc(MemoryStack memoryStack) {
        return new RECT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nleft(long j) {
        return UNSAFE.getInt((Object) null, j + LEFT);
    }

    public static int ntop(long j) {
        return UNSAFE.getInt((Object) null, j + TOP);
    }

    public static int nright(long j) {
        return UNSAFE.getInt((Object) null, j + RIGHT);
    }

    public static int nbottom(long j) {
        return UNSAFE.getInt((Object) null, j + BOTTOM);
    }

    public static void nleft(long j, int i) {
        UNSAFE.putInt((Object) null, j + LEFT, i);
    }

    public static void ntop(long j, int i) {
        UNSAFE.putInt((Object) null, j + TOP, i);
    }

    public static void nright(long j, int i) {
        UNSAFE.putInt((Object) null, j + RIGHT, i);
    }

    public static void nbottom(long j, int i) {
        UNSAFE.putInt((Object) null, j + BOTTOM, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/RECT$Buffer.class */
    public static class Buffer extends StructBuffer<RECT, Buffer> implements NativeResource {
        private static final RECT ELEMENT_FACTORY = RECT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / RECT.SIZEOF);
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
        public RECT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("LONG")
        public int left() {
            return RECT.nleft(address());
        }

        @NativeType("LONG")
        public int top() {
            return RECT.ntop(address());
        }

        @NativeType("LONG")
        public int right() {
            return RECT.nright(address());
        }

        @NativeType("LONG")
        public int bottom() {
            return RECT.nbottom(address());
        }

        public Buffer left(@NativeType("LONG") int i) {
            RECT.nleft(address(), i);
            return this;
        }

        public Buffer top(@NativeType("LONG") int i) {
            RECT.ntop(address(), i);
            return this;
        }

        public Buffer right(@NativeType("LONG") int i) {
            RECT.nright(address(), i);
            return this;
        }

        public Buffer bottom(@NativeType("LONG") int i) {
            RECT.nbottom(address(), i);
            return this;
        }
    }
}
