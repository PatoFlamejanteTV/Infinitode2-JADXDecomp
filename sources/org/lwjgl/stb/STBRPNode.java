package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbrp_node")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBRPNode.class */
public class STBRPNode extends Struct<STBRPNode> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X;
    public static final int Y;
    public static final int NEXT;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X = __struct.offsetof(0);
        Y = __struct.offsetof(1);
        NEXT = __struct.offsetof(2);
    }

    protected STBRPNode(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBRPNode create(long j, ByteBuffer byteBuffer) {
        return new STBRPNode(j, byteBuffer);
    }

    public STBRPNode(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("stbrp_coord")
    public int x() {
        return nx(address());
    }

    @NativeType("stbrp_coord")
    public int y() {
        return ny(address());
    }

    @NativeType("stbrp_node *")
    public STBRPNode next() {
        return nnext(address());
    }

    public static STBRPNode malloc() {
        return new STBRPNode(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBRPNode calloc() {
        return new STBRPNode(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBRPNode create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBRPNode(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBRPNode create(long j) {
        return new STBRPNode(j, null);
    }

    public static STBRPNode createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBRPNode(j, null);
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
    public static STBRPNode mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBRPNode callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBRPNode mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBRPNode callocStack(MemoryStack memoryStack) {
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

    public static STBRPNode malloc(MemoryStack memoryStack) {
        return new STBRPNode(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBRPNode calloc(MemoryStack memoryStack) {
        return new STBRPNode(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static STBRPNode nnext(long j) {
        return createSafe(MemoryUtil.memGetAddress(j + NEXT));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBRPNode$Buffer.class */
    public static class Buffer extends StructBuffer<STBRPNode, Buffer> implements NativeResource {
        private static final STBRPNode ELEMENT_FACTORY = STBRPNode.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBRPNode.SIZEOF);
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
        public STBRPNode getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("stbrp_coord")
        public int x() {
            return STBRPNode.nx(address());
        }

        @NativeType("stbrp_coord")
        public int y() {
            return STBRPNode.ny(address());
        }

        @NativeType("stbrp_node *")
        public STBRPNode next() {
            return STBRPNode.nnext(address());
        }
    }
}
