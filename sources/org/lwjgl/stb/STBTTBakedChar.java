package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_bakedchar")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTBakedChar.class */
public class STBTTBakedChar extends Struct<STBTTBakedChar> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X0;
    public static final int Y0;
    public static final int X1;
    public static final int Y1;
    public static final int XOFF;
    public static final int YOFF;
    public static final int XADVANCE;

    static {
        Struct.Layout __struct = __struct(__member(2), __member(2), __member(2), __member(2), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X0 = __struct.offsetof(0);
        Y0 = __struct.offsetof(1);
        X1 = __struct.offsetof(2);
        Y1 = __struct.offsetof(3);
        XOFF = __struct.offsetof(4);
        YOFF = __struct.offsetof(5);
        XADVANCE = __struct.offsetof(6);
    }

    protected STBTTBakedChar(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTBakedChar create(long j, ByteBuffer byteBuffer) {
        return new STBTTBakedChar(j, byteBuffer);
    }

    public STBTTBakedChar(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("unsigned short")
    public short x0() {
        return nx0(address());
    }

    @NativeType("unsigned short")
    public short y0() {
        return ny0(address());
    }

    @NativeType("unsigned short")
    public short x1() {
        return nx1(address());
    }

    @NativeType("unsigned short")
    public short y1() {
        return ny1(address());
    }

    public float xoff() {
        return nxoff(address());
    }

    public float yoff() {
        return nyoff(address());
    }

    public float xadvance() {
        return nxadvance(address());
    }

    public static STBTTBakedChar malloc() {
        return new STBTTBakedChar(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTBakedChar calloc() {
        return new STBTTBakedChar(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTBakedChar create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTBakedChar(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTBakedChar create(long j) {
        return new STBTTBakedChar(j, null);
    }

    public static STBTTBakedChar createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTBakedChar(j, null);
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
    public static STBTTBakedChar mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTBakedChar callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTBakedChar mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTBakedChar callocStack(MemoryStack memoryStack) {
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

    public static STBTTBakedChar malloc(MemoryStack memoryStack) {
        return new STBTTBakedChar(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTBakedChar calloc(MemoryStack memoryStack) {
        return new STBTTBakedChar(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static short nx0(long j) {
        return UNSAFE.getShort((Object) null, j + X0);
    }

    public static short ny0(long j) {
        return UNSAFE.getShort((Object) null, j + Y0);
    }

    public static short nx1(long j) {
        return UNSAFE.getShort((Object) null, j + X1);
    }

    public static short ny1(long j) {
        return UNSAFE.getShort((Object) null, j + Y1);
    }

    public static float nxoff(long j) {
        return UNSAFE.getFloat((Object) null, j + XOFF);
    }

    public static float nyoff(long j) {
        return UNSAFE.getFloat((Object) null, j + YOFF);
    }

    public static float nxadvance(long j) {
        return UNSAFE.getFloat((Object) null, j + XADVANCE);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTBakedChar$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTBakedChar, Buffer> implements NativeResource {
        private static final STBTTBakedChar ELEMENT_FACTORY = STBTTBakedChar.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTBakedChar.SIZEOF);
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
        public STBTTBakedChar getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("unsigned short")
        public short x0() {
            return STBTTBakedChar.nx0(address());
        }

        @NativeType("unsigned short")
        public short y0() {
            return STBTTBakedChar.ny0(address());
        }

        @NativeType("unsigned short")
        public short x1() {
            return STBTTBakedChar.nx1(address());
        }

        @NativeType("unsigned short")
        public short y1() {
            return STBTTBakedChar.ny1(address());
        }

        public float xoff() {
            return STBTTBakedChar.nxoff(address());
        }

        public float yoff() {
            return STBTTBakedChar.nyoff(address());
        }

        public float xadvance() {
            return STBTTBakedChar.nxadvance(address());
        }
    }
}
