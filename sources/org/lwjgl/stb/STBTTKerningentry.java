package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_kerningentry")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTKerningentry.class */
public class STBTTKerningentry extends Struct<STBTTKerningentry> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int GLYPH1;
    public static final int GLYPH2;
    public static final int ADVANCE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        GLYPH1 = __struct.offsetof(0);
        GLYPH2 = __struct.offsetof(1);
        ADVANCE = __struct.offsetof(2);
    }

    protected STBTTKerningentry(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTKerningentry create(long j, ByteBuffer byteBuffer) {
        return new STBTTKerningentry(j, byteBuffer);
    }

    public STBTTKerningentry(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int glyph1() {
        return nglyph1(address());
    }

    public int glyph2() {
        return nglyph2(address());
    }

    public int advance() {
        return nadvance(address());
    }

    public static STBTTKerningentry malloc() {
        return new STBTTKerningentry(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTKerningentry calloc() {
        return new STBTTKerningentry(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTKerningentry create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTKerningentry(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTKerningentry create(long j) {
        return new STBTTKerningentry(j, null);
    }

    public static STBTTKerningentry createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTKerningentry(j, null);
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

    public static STBTTKerningentry malloc(MemoryStack memoryStack) {
        return new STBTTKerningentry(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTKerningentry calloc(MemoryStack memoryStack) {
        return new STBTTKerningentry(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nglyph1(long j) {
        return UNSAFE.getInt((Object) null, j + GLYPH1);
    }

    public static int nglyph2(long j) {
        return UNSAFE.getInt((Object) null, j + GLYPH2);
    }

    public static int nadvance(long j) {
        return UNSAFE.getInt((Object) null, j + ADVANCE);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTKerningentry$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTKerningentry, Buffer> implements NativeResource {
        private static final STBTTKerningentry ELEMENT_FACTORY = STBTTKerningentry.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTKerningentry.SIZEOF);
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
        public STBTTKerningentry getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int glyph1() {
            return STBTTKerningentry.nglyph1(address());
        }

        public int glyph2() {
            return STBTTKerningentry.nglyph2(address());
        }

        public int advance() {
            return STBTTKerningentry.nadvance(address());
        }
    }
}
