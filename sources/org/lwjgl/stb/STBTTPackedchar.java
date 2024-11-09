package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_packedchar")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTPackedchar.class */
public class STBTTPackedchar extends Struct<STBTTPackedchar> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X0;
    public static final int Y0;
    public static final int X1;
    public static final int Y1;
    public static final int XOFF;
    public static final int YOFF;
    public static final int XADVANCE;
    public static final int XOFF2;
    public static final int YOFF2;

    static {
        Struct.Layout __struct = __struct(__member(2), __member(2), __member(2), __member(2), __member(4), __member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X0 = __struct.offsetof(0);
        Y0 = __struct.offsetof(1);
        X1 = __struct.offsetof(2);
        Y1 = __struct.offsetof(3);
        XOFF = __struct.offsetof(4);
        YOFF = __struct.offsetof(5);
        XADVANCE = __struct.offsetof(6);
        XOFF2 = __struct.offsetof(7);
        YOFF2 = __struct.offsetof(8);
    }

    protected STBTTPackedchar(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTPackedchar create(long j, ByteBuffer byteBuffer) {
        return new STBTTPackedchar(j, byteBuffer);
    }

    public STBTTPackedchar(ByteBuffer byteBuffer) {
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

    public float xoff2() {
        return nxoff2(address());
    }

    public float yoff2() {
        return nyoff2(address());
    }

    public STBTTPackedchar x0(@NativeType("unsigned short") short s) {
        nx0(address(), s);
        return this;
    }

    public STBTTPackedchar y0(@NativeType("unsigned short") short s) {
        ny0(address(), s);
        return this;
    }

    public STBTTPackedchar x1(@NativeType("unsigned short") short s) {
        nx1(address(), s);
        return this;
    }

    public STBTTPackedchar y1(@NativeType("unsigned short") short s) {
        ny1(address(), s);
        return this;
    }

    public STBTTPackedchar xoff(float f) {
        nxoff(address(), f);
        return this;
    }

    public STBTTPackedchar yoff(float f) {
        nyoff(address(), f);
        return this;
    }

    public STBTTPackedchar xadvance(float f) {
        nxadvance(address(), f);
        return this;
    }

    public STBTTPackedchar xoff2(float f) {
        nxoff2(address(), f);
        return this;
    }

    public STBTTPackedchar yoff2(float f) {
        nyoff2(address(), f);
        return this;
    }

    public STBTTPackedchar set(short s, short s2, short s3, short s4, float f, float f2, float f3, float f4, float f5) {
        x0(s);
        y0(s2);
        x1(s3);
        y1(s4);
        xoff(f);
        yoff(f2);
        xadvance(f3);
        xoff2(f4);
        yoff2(f5);
        return this;
    }

    public STBTTPackedchar set(STBTTPackedchar sTBTTPackedchar) {
        MemoryUtil.memCopy(sTBTTPackedchar.address(), address(), SIZEOF);
        return this;
    }

    public static STBTTPackedchar malloc() {
        return new STBTTPackedchar(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTPackedchar calloc() {
        return new STBTTPackedchar(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTPackedchar create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTPackedchar(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTPackedchar create(long j) {
        return new STBTTPackedchar(j, null);
    }

    public static STBTTPackedchar createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTPackedchar(j, null);
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
    public static STBTTPackedchar mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTPackedchar callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTPackedchar mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTPackedchar callocStack(MemoryStack memoryStack) {
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

    public static STBTTPackedchar malloc(MemoryStack memoryStack) {
        return new STBTTPackedchar(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTPackedchar calloc(MemoryStack memoryStack) {
        return new STBTTPackedchar(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static float nxoff2(long j) {
        return UNSAFE.getFloat((Object) null, j + XOFF2);
    }

    public static float nyoff2(long j) {
        return UNSAFE.getFloat((Object) null, j + YOFF2);
    }

    public static void nx0(long j, short s) {
        UNSAFE.putShort((Object) null, j + X0, s);
    }

    public static void ny0(long j, short s) {
        UNSAFE.putShort((Object) null, j + Y0, s);
    }

    public static void nx1(long j, short s) {
        UNSAFE.putShort((Object) null, j + X1, s);
    }

    public static void ny1(long j, short s) {
        UNSAFE.putShort((Object) null, j + Y1, s);
    }

    public static void nxoff(long j, float f) {
        UNSAFE.putFloat((Object) null, j + XOFF, f);
    }

    public static void nyoff(long j, float f) {
        UNSAFE.putFloat((Object) null, j + YOFF, f);
    }

    public static void nxadvance(long j, float f) {
        UNSAFE.putFloat((Object) null, j + XADVANCE, f);
    }

    public static void nxoff2(long j, float f) {
        UNSAFE.putFloat((Object) null, j + XOFF2, f);
    }

    public static void nyoff2(long j, float f) {
        UNSAFE.putFloat((Object) null, j + YOFF2, f);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTPackedchar$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTPackedchar, Buffer> implements NativeResource {
        private static final STBTTPackedchar ELEMENT_FACTORY = STBTTPackedchar.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTPackedchar.SIZEOF);
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
        public STBTTPackedchar getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("unsigned short")
        public short x0() {
            return STBTTPackedchar.nx0(address());
        }

        @NativeType("unsigned short")
        public short y0() {
            return STBTTPackedchar.ny0(address());
        }

        @NativeType("unsigned short")
        public short x1() {
            return STBTTPackedchar.nx1(address());
        }

        @NativeType("unsigned short")
        public short y1() {
            return STBTTPackedchar.ny1(address());
        }

        public float xoff() {
            return STBTTPackedchar.nxoff(address());
        }

        public float yoff() {
            return STBTTPackedchar.nyoff(address());
        }

        public float xadvance() {
            return STBTTPackedchar.nxadvance(address());
        }

        public float xoff2() {
            return STBTTPackedchar.nxoff2(address());
        }

        public float yoff2() {
            return STBTTPackedchar.nyoff2(address());
        }

        public Buffer x0(@NativeType("unsigned short") short s) {
            STBTTPackedchar.nx0(address(), s);
            return this;
        }

        public Buffer y0(@NativeType("unsigned short") short s) {
            STBTTPackedchar.ny0(address(), s);
            return this;
        }

        public Buffer x1(@NativeType("unsigned short") short s) {
            STBTTPackedchar.nx1(address(), s);
            return this;
        }

        public Buffer y1(@NativeType("unsigned short") short s) {
            STBTTPackedchar.ny1(address(), s);
            return this;
        }

        public Buffer xoff(float f) {
            STBTTPackedchar.nxoff(address(), f);
            return this;
        }

        public Buffer yoff(float f) {
            STBTTPackedchar.nyoff(address(), f);
            return this;
        }

        public Buffer xadvance(float f) {
            STBTTPackedchar.nxadvance(address(), f);
            return this;
        }

        public Buffer xoff2(float f) {
            STBTTPackedchar.nxoff2(address(), f);
            return this;
        }

        public Buffer yoff2(float f) {
            STBTTPackedchar.nyoff2(address(), f);
            return this;
        }
    }
}
