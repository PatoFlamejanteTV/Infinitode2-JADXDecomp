package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt__bitmap")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTBitmap.class */
public class STBTTBitmap extends Struct<STBTTBitmap> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int W;
    public static final int H;
    public static final int STRIDE;
    public static final int PIXELS;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        W = __struct.offsetof(0);
        H = __struct.offsetof(1);
        STRIDE = __struct.offsetof(2);
        PIXELS = __struct.offsetof(3);
    }

    protected STBTTBitmap(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTBitmap create(long j, ByteBuffer byteBuffer) {
        return new STBTTBitmap(j, byteBuffer);
    }

    public STBTTBitmap(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int w() {
        return nw(address());
    }

    public int h() {
        return nh(address());
    }

    public int stride() {
        return nstride(address());
    }

    @NativeType("unsigned char *")
    public ByteBuffer pixels(int i) {
        return npixels(address(), i);
    }

    public STBTTBitmap w(int i) {
        nw(address(), i);
        return this;
    }

    public STBTTBitmap h(int i) {
        nh(address(), i);
        return this;
    }

    public STBTTBitmap stride(int i) {
        nstride(address(), i);
        return this;
    }

    public STBTTBitmap pixels(@NativeType("unsigned char *") ByteBuffer byteBuffer) {
        npixels(address(), byteBuffer);
        return this;
    }

    public STBTTBitmap set(int i, int i2, int i3, ByteBuffer byteBuffer) {
        w(i);
        h(i2);
        stride(i3);
        pixels(byteBuffer);
        return this;
    }

    public STBTTBitmap set(STBTTBitmap sTBTTBitmap) {
        MemoryUtil.memCopy(sTBTTBitmap.address(), address(), SIZEOF);
        return this;
    }

    public static STBTTBitmap malloc() {
        return new STBTTBitmap(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTBitmap calloc() {
        return new STBTTBitmap(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTBitmap create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTBitmap(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTBitmap create(long j) {
        return new STBTTBitmap(j, null);
    }

    public static STBTTBitmap createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTBitmap(j, null);
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
    public static STBTTBitmap mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTBitmap callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTBitmap mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTBitmap callocStack(MemoryStack memoryStack) {
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

    public static STBTTBitmap malloc(MemoryStack memoryStack) {
        return new STBTTBitmap(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTBitmap calloc(MemoryStack memoryStack) {
        return new STBTTBitmap(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nw(long j) {
        return UNSAFE.getInt((Object) null, j + W);
    }

    public static int nh(long j) {
        return UNSAFE.getInt((Object) null, j + H);
    }

    public static int nstride(long j) {
        return UNSAFE.getInt((Object) null, j + STRIDE);
    }

    public static ByteBuffer npixels(long j, int i) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + PIXELS), i);
    }

    public static void nw(long j, int i) {
        UNSAFE.putInt((Object) null, j + W, i);
    }

    public static void nh(long j, int i) {
        UNSAFE.putInt((Object) null, j + H, i);
    }

    public static void nstride(long j, int i) {
        UNSAFE.putInt((Object) null, j + STRIDE, i);
    }

    public static void npixels(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + PIXELS, MemoryUtil.memAddress(byteBuffer));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + PIXELS));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTBitmap$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTBitmap, Buffer> implements NativeResource {
        private static final STBTTBitmap ELEMENT_FACTORY = STBTTBitmap.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTBitmap.SIZEOF);
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
        public STBTTBitmap getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int w() {
            return STBTTBitmap.nw(address());
        }

        public int h() {
            return STBTTBitmap.nh(address());
        }

        public int stride() {
            return STBTTBitmap.nstride(address());
        }

        @NativeType("unsigned char *")
        public ByteBuffer pixels(int i) {
            return STBTTBitmap.npixels(address(), i);
        }

        public Buffer w(int i) {
            STBTTBitmap.nw(address(), i);
            return this;
        }

        public Buffer h(int i) {
            STBTTBitmap.nh(address(), i);
            return this;
        }

        public Buffer stride(int i) {
            STBTTBitmap.nstride(address(), i);
            return this;
        }

        public Buffer pixels(@NativeType("unsigned char *") ByteBuffer byteBuffer) {
            STBTTBitmap.npixels(address(), byteBuffer);
            return this;
        }
    }
}
