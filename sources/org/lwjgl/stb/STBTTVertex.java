package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_vertex")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTVertex.class */
public class STBTTVertex extends Struct<STBTTVertex> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X;
    public static final int Y;
    public static final int CX;
    public static final int CY;
    public static final int CX1;
    public static final int CY1;
    public static final int TYPE;

    static {
        Struct.Layout __struct = __struct(__member(2), __member(2), __member(2), __member(2), __member(2), __member(2), __member(1));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X = __struct.offsetof(0);
        Y = __struct.offsetof(1);
        CX = __struct.offsetof(2);
        CY = __struct.offsetof(3);
        CX1 = __struct.offsetof(4);
        CY1 = __struct.offsetof(5);
        TYPE = __struct.offsetof(6);
    }

    protected STBTTVertex(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTVertex create(long j, ByteBuffer byteBuffer) {
        return new STBTTVertex(j, byteBuffer);
    }

    public STBTTVertex(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("stbtt_vertex_type")
    public short x() {
        return nx(address());
    }

    @NativeType("stbtt_vertex_type")
    public short y() {
        return ny(address());
    }

    @NativeType("stbtt_vertex_type")
    public short cx() {
        return ncx(address());
    }

    @NativeType("stbtt_vertex_type")
    public short cy() {
        return ncy(address());
    }

    @NativeType("stbtt_vertex_type")
    public short cx1() {
        return ncx1(address());
    }

    @NativeType("stbtt_vertex_type")
    public short cy1() {
        return ncy1(address());
    }

    @NativeType("unsigned char")
    public byte type() {
        return ntype(address());
    }

    public static STBTTVertex malloc() {
        return new STBTTVertex(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTVertex calloc() {
        return new STBTTVertex(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTVertex create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTVertex(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTVertex create(long j) {
        return new STBTTVertex(j, null);
    }

    public static STBTTVertex createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTVertex(j, null);
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
    public static STBTTVertex mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTVertex callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTVertex mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTVertex callocStack(MemoryStack memoryStack) {
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

    public static STBTTVertex malloc(MemoryStack memoryStack) {
        return new STBTTVertex(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTVertex calloc(MemoryStack memoryStack) {
        return new STBTTVertex(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static short nx(long j) {
        return UNSAFE.getShort((Object) null, j + X);
    }

    public static short ny(long j) {
        return UNSAFE.getShort((Object) null, j + Y);
    }

    public static short ncx(long j) {
        return UNSAFE.getShort((Object) null, j + CX);
    }

    public static short ncy(long j) {
        return UNSAFE.getShort((Object) null, j + CY);
    }

    public static short ncx1(long j) {
        return UNSAFE.getShort((Object) null, j + CX1);
    }

    public static short ncy1(long j) {
        return UNSAFE.getShort((Object) null, j + CY1);
    }

    public static byte ntype(long j) {
        return UNSAFE.getByte((Object) null, j + TYPE);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTVertex$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTVertex, Buffer> implements NativeResource {
        private static final STBTTVertex ELEMENT_FACTORY = STBTTVertex.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTVertex.SIZEOF);
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
        public STBTTVertex getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("stbtt_vertex_type")
        public short x() {
            return STBTTVertex.nx(address());
        }

        @NativeType("stbtt_vertex_type")
        public short y() {
            return STBTTVertex.ny(address());
        }

        @NativeType("stbtt_vertex_type")
        public short cx() {
            return STBTTVertex.ncx(address());
        }

        @NativeType("stbtt_vertex_type")
        public short cy() {
            return STBTTVertex.ncy(address());
        }

        @NativeType("stbtt_vertex_type")
        public short cx1() {
            return STBTTVertex.ncx1(address());
        }

        @NativeType("stbtt_vertex_type")
        public short cy1() {
            return STBTTVertex.ncy1(address());
        }

        @NativeType("unsigned char")
        public byte type() {
            return STBTTVertex.ntype(address());
        }
    }
}
