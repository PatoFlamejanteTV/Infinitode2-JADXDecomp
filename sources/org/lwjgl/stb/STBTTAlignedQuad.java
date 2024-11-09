package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_aligned_quad")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTAlignedQuad.class */
public class STBTTAlignedQuad extends Struct<STBTTAlignedQuad> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X0;
    public static final int Y0;
    public static final int S0;
    public static final int T0;
    public static final int X1;
    public static final int Y1;
    public static final int S1;
    public static final int T1;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X0 = __struct.offsetof(0);
        Y0 = __struct.offsetof(1);
        S0 = __struct.offsetof(2);
        T0 = __struct.offsetof(3);
        X1 = __struct.offsetof(4);
        Y1 = __struct.offsetof(5);
        S1 = __struct.offsetof(6);
        T1 = __struct.offsetof(7);
    }

    protected STBTTAlignedQuad(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTAlignedQuad create(long j, ByteBuffer byteBuffer) {
        return new STBTTAlignedQuad(j, byteBuffer);
    }

    public STBTTAlignedQuad(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public float x0() {
        return nx0(address());
    }

    public float y0() {
        return ny0(address());
    }

    public float s0() {
        return ns0(address());
    }

    public float t0() {
        return nt0(address());
    }

    public float x1() {
        return nx1(address());
    }

    public float y1() {
        return ny1(address());
    }

    public float s1() {
        return ns1(address());
    }

    public float t1() {
        return nt1(address());
    }

    public static STBTTAlignedQuad malloc() {
        return new STBTTAlignedQuad(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTAlignedQuad calloc() {
        return new STBTTAlignedQuad(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTAlignedQuad create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTAlignedQuad(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTAlignedQuad create(long j) {
        return new STBTTAlignedQuad(j, null);
    }

    public static STBTTAlignedQuad createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTAlignedQuad(j, null);
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
    public static STBTTAlignedQuad mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTAlignedQuad callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTAlignedQuad mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTAlignedQuad callocStack(MemoryStack memoryStack) {
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

    public static STBTTAlignedQuad malloc(MemoryStack memoryStack) {
        return new STBTTAlignedQuad(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTAlignedQuad calloc(MemoryStack memoryStack) {
        return new STBTTAlignedQuad(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static float nx0(long j) {
        return UNSAFE.getFloat((Object) null, j + X0);
    }

    public static float ny0(long j) {
        return UNSAFE.getFloat((Object) null, j + Y0);
    }

    public static float ns0(long j) {
        return UNSAFE.getFloat((Object) null, j + S0);
    }

    public static float nt0(long j) {
        return UNSAFE.getFloat((Object) null, j + T0);
    }

    public static float nx1(long j) {
        return UNSAFE.getFloat((Object) null, j + X1);
    }

    public static float ny1(long j) {
        return UNSAFE.getFloat((Object) null, j + Y1);
    }

    public static float ns1(long j) {
        return UNSAFE.getFloat((Object) null, j + S1);
    }

    public static float nt1(long j) {
        return UNSAFE.getFloat((Object) null, j + T1);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTAlignedQuad$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTAlignedQuad, Buffer> implements NativeResource {
        private static final STBTTAlignedQuad ELEMENT_FACTORY = STBTTAlignedQuad.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTAlignedQuad.SIZEOF);
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
        public STBTTAlignedQuad getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public float x0() {
            return STBTTAlignedQuad.nx0(address());
        }

        public float y0() {
            return STBTTAlignedQuad.ny0(address());
        }

        public float s0() {
            return STBTTAlignedQuad.ns0(address());
        }

        public float t0() {
            return STBTTAlignedQuad.nt0(address());
        }

        public float x1() {
            return STBTTAlignedQuad.nx1(address());
        }

        public float y1() {
            return STBTTAlignedQuad.ny1(address());
        }

        public float s1() {
            return STBTTAlignedQuad.ns1(address());
        }

        public float t1() {
            return STBTTAlignedQuad.nt1(address());
        }
    }
}
