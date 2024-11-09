package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XVisualInfo.class */
public class XVisualInfo extends Struct<XVisualInfo> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int VISUAL;
    public static final int VISUALID;
    public static final int SCREEN;
    public static final int DEPTH;
    public static final int CLASS;
    public static final int RED_MASK;
    public static final int GREEN_MASK;
    public static final int BLUE_MASK;
    public static final int COLORMAP_SIZE;
    public static final int BITS_PER_RGB;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        VISUAL = __struct.offsetof(0);
        VISUALID = __struct.offsetof(1);
        SCREEN = __struct.offsetof(2);
        DEPTH = __struct.offsetof(3);
        CLASS = __struct.offsetof(4);
        RED_MASK = __struct.offsetof(5);
        GREEN_MASK = __struct.offsetof(6);
        BLUE_MASK = __struct.offsetof(7);
        COLORMAP_SIZE = __struct.offsetof(8);
        BITS_PER_RGB = __struct.offsetof(9);
    }

    protected XVisualInfo(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XVisualInfo create(long j, ByteBuffer byteBuffer) {
        return new XVisualInfo(j, byteBuffer);
    }

    public XVisualInfo(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("Visual *")
    public Visual visual() {
        return nvisual(address());
    }

    @NativeType("VisualID")
    public long visualid() {
        return nvisualid(address());
    }

    public int screen() {
        return nscreen(address());
    }

    public int depth() {
        return ndepth(address());
    }

    public int class$() {
        return nclass$(address());
    }

    @NativeType("unsigned long")
    public long red_mask() {
        return nred_mask(address());
    }

    @NativeType("unsigned long")
    public long green_mask() {
        return ngreen_mask(address());
    }

    @NativeType("unsigned long")
    public long blue_mask() {
        return nblue_mask(address());
    }

    public int colormap_size() {
        return ncolormap_size(address());
    }

    public int bits_per_rgb() {
        return nbits_per_rgb(address());
    }

    public XVisualInfo visual(@NativeType("Visual *") Visual visual) {
        nvisual(address(), visual);
        return this;
    }

    public XVisualInfo visualid(@NativeType("VisualID") long j) {
        nvisualid(address(), j);
        return this;
    }

    public XVisualInfo screen(int i) {
        nscreen(address(), i);
        return this;
    }

    public XVisualInfo depth(int i) {
        ndepth(address(), i);
        return this;
    }

    public XVisualInfo class$(int i) {
        nclass$(address(), i);
        return this;
    }

    public XVisualInfo red_mask(@NativeType("unsigned long") long j) {
        nred_mask(address(), j);
        return this;
    }

    public XVisualInfo green_mask(@NativeType("unsigned long") long j) {
        ngreen_mask(address(), j);
        return this;
    }

    public XVisualInfo blue_mask(@NativeType("unsigned long") long j) {
        nblue_mask(address(), j);
        return this;
    }

    public XVisualInfo colormap_size(int i) {
        ncolormap_size(address(), i);
        return this;
    }

    public XVisualInfo bits_per_rgb(int i) {
        nbits_per_rgb(address(), i);
        return this;
    }

    public XVisualInfo set(Visual visual, long j, int i, int i2, int i3, long j2, long j3, long j4, int i4, int i5) {
        visual(visual);
        visualid(j);
        screen(i);
        depth(i2);
        class$(i3);
        red_mask(j2);
        green_mask(j3);
        blue_mask(j4);
        colormap_size(i4);
        bits_per_rgb(i5);
        return this;
    }

    public XVisualInfo set(XVisualInfo xVisualInfo) {
        MemoryUtil.memCopy(xVisualInfo.address(), address(), SIZEOF);
        return this;
    }

    public static XVisualInfo malloc() {
        return new XVisualInfo(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XVisualInfo calloc() {
        return new XVisualInfo(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XVisualInfo create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XVisualInfo(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XVisualInfo create(long j) {
        return new XVisualInfo(j, null);
    }

    public static XVisualInfo createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XVisualInfo(j, null);
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
    public static XVisualInfo mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XVisualInfo callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XVisualInfo mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XVisualInfo callocStack(MemoryStack memoryStack) {
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

    public static XVisualInfo malloc(MemoryStack memoryStack) {
        return new XVisualInfo(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XVisualInfo calloc(MemoryStack memoryStack) {
        return new XVisualInfo(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static Visual nvisual(long j) {
        return Visual.create(MemoryUtil.memGetAddress(j + VISUAL));
    }

    public static long nvisualid(long j) {
        return MemoryUtil.memGetCLong(j + VISUALID);
    }

    public static int nscreen(long j) {
        return UNSAFE.getInt((Object) null, j + SCREEN);
    }

    public static int ndepth(long j) {
        return UNSAFE.getInt((Object) null, j + DEPTH);
    }

    public static int nclass$(long j) {
        return UNSAFE.getInt((Object) null, j + CLASS);
    }

    public static long nred_mask(long j) {
        return MemoryUtil.memGetCLong(j + RED_MASK);
    }

    public static long ngreen_mask(long j) {
        return MemoryUtil.memGetCLong(j + GREEN_MASK);
    }

    public static long nblue_mask(long j) {
        return MemoryUtil.memGetCLong(j + BLUE_MASK);
    }

    public static int ncolormap_size(long j) {
        return UNSAFE.getInt((Object) null, j + COLORMAP_SIZE);
    }

    public static int nbits_per_rgb(long j) {
        return UNSAFE.getInt((Object) null, j + BITS_PER_RGB);
    }

    public static void nvisual(long j, Visual visual) {
        MemoryUtil.memPutAddress(j + VISUAL, visual.address());
    }

    public static void nvisualid(long j, long j2) {
        MemoryUtil.memPutCLong(j + VISUALID, j2);
    }

    public static void nscreen(long j, int i) {
        UNSAFE.putInt((Object) null, j + SCREEN, i);
    }

    public static void ndepth(long j, int i) {
        UNSAFE.putInt((Object) null, j + DEPTH, i);
    }

    public static void nclass$(long j, int i) {
        UNSAFE.putInt((Object) null, j + CLASS, i);
    }

    public static void nred_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + RED_MASK, j2);
    }

    public static void ngreen_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + GREEN_MASK, j2);
    }

    public static void nblue_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + BLUE_MASK, j2);
    }

    public static void ncolormap_size(long j, int i) {
        UNSAFE.putInt((Object) null, j + COLORMAP_SIZE, i);
    }

    public static void nbits_per_rgb(long j, int i) {
        UNSAFE.putInt((Object) null, j + BITS_PER_RGB, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + VISUAL));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XVisualInfo$Buffer.class */
    public static class Buffer extends StructBuffer<XVisualInfo, Buffer> implements NativeResource {
        private static final XVisualInfo ELEMENT_FACTORY = XVisualInfo.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XVisualInfo.SIZEOF);
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
        public XVisualInfo getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("Visual *")
        public Visual visual() {
            return XVisualInfo.nvisual(address());
        }

        @NativeType("VisualID")
        public long visualid() {
            return XVisualInfo.nvisualid(address());
        }

        public int screen() {
            return XVisualInfo.nscreen(address());
        }

        public int depth() {
            return XVisualInfo.ndepth(address());
        }

        public int class$() {
            return XVisualInfo.nclass$(address());
        }

        @NativeType("unsigned long")
        public long red_mask() {
            return XVisualInfo.nred_mask(address());
        }

        @NativeType("unsigned long")
        public long green_mask() {
            return XVisualInfo.ngreen_mask(address());
        }

        @NativeType("unsigned long")
        public long blue_mask() {
            return XVisualInfo.nblue_mask(address());
        }

        public int colormap_size() {
            return XVisualInfo.ncolormap_size(address());
        }

        public int bits_per_rgb() {
            return XVisualInfo.nbits_per_rgb(address());
        }

        public Buffer visual(@NativeType("Visual *") Visual visual) {
            XVisualInfo.nvisual(address(), visual);
            return this;
        }

        public Buffer visualid(@NativeType("VisualID") long j) {
            XVisualInfo.nvisualid(address(), j);
            return this;
        }

        public Buffer screen(int i) {
            XVisualInfo.nscreen(address(), i);
            return this;
        }

        public Buffer depth(int i) {
            XVisualInfo.ndepth(address(), i);
            return this;
        }

        public Buffer class$(int i) {
            XVisualInfo.nclass$(address(), i);
            return this;
        }

        public Buffer red_mask(@NativeType("unsigned long") long j) {
            XVisualInfo.nred_mask(address(), j);
            return this;
        }

        public Buffer green_mask(@NativeType("unsigned long") long j) {
            XVisualInfo.ngreen_mask(address(), j);
            return this;
        }

        public Buffer blue_mask(@NativeType("unsigned long") long j) {
            XVisualInfo.nblue_mask(address(), j);
            return this;
        }

        public Buffer colormap_size(int i) {
            XVisualInfo.ncolormap_size(address(), i);
            return this;
        }

        public Buffer bits_per_rgb(int i) {
            XVisualInfo.nbits_per_rgb(address(), i);
            return this;
        }
    }
}
