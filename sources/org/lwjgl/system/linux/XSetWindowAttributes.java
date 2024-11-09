package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XSetWindowAttributes.class */
public class XSetWindowAttributes extends Struct<XSetWindowAttributes> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int BACKGROUND_PIXMAP;
    public static final int BACKGROUND_PIXEL;
    public static final int BORDER_PIXMAP;
    public static final int BORDER_PIXEL;
    public static final int BIT_GRAVITY;
    public static final int WIN_GRAVITY;
    public static final int BACKING_STORE;
    public static final int BACKING_PLANES;
    public static final int BACKING_PIXEL;
    public static final int SAVE_UNDER;
    public static final int EVENT_MASK;
    public static final int DO_NOT_PROPAGATE_MASK;
    public static final int OVERRIDE_REDIRECT;
    public static final int COLORMAP;
    public static final int CURSOR;

    static {
        Struct.Layout __struct = __struct(__member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(CLONG_SIZE), __member(CLONG_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        BACKGROUND_PIXMAP = __struct.offsetof(0);
        BACKGROUND_PIXEL = __struct.offsetof(1);
        BORDER_PIXMAP = __struct.offsetof(2);
        BORDER_PIXEL = __struct.offsetof(3);
        BIT_GRAVITY = __struct.offsetof(4);
        WIN_GRAVITY = __struct.offsetof(5);
        BACKING_STORE = __struct.offsetof(6);
        BACKING_PLANES = __struct.offsetof(7);
        BACKING_PIXEL = __struct.offsetof(8);
        SAVE_UNDER = __struct.offsetof(9);
        EVENT_MASK = __struct.offsetof(10);
        DO_NOT_PROPAGATE_MASK = __struct.offsetof(11);
        OVERRIDE_REDIRECT = __struct.offsetof(12);
        COLORMAP = __struct.offsetof(13);
        CURSOR = __struct.offsetof(14);
    }

    protected XSetWindowAttributes(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XSetWindowAttributes create(long j, ByteBuffer byteBuffer) {
        return new XSetWindowAttributes(j, byteBuffer);
    }

    public XSetWindowAttributes(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("Pixmap")
    public long background_pixmap() {
        return nbackground_pixmap(address());
    }

    @NativeType("unsigned long")
    public long background_pixel() {
        return nbackground_pixel(address());
    }

    @NativeType("Pixmap")
    public long border_pixmap() {
        return nborder_pixmap(address());
    }

    @NativeType("unsigned long")
    public long border_pixel() {
        return nborder_pixel(address());
    }

    public int bit_gravity() {
        return nbit_gravity(address());
    }

    public int win_gravity() {
        return nwin_gravity(address());
    }

    public int backing_store() {
        return nbacking_store(address());
    }

    @NativeType("unsigned long")
    public long backing_planes() {
        return nbacking_planes(address());
    }

    @NativeType("unsigned long")
    public long backing_pixel() {
        return nbacking_pixel(address());
    }

    @NativeType("Bool")
    public boolean save_under() {
        return nsave_under(address()) != 0;
    }

    public long event_mask() {
        return nevent_mask(address());
    }

    public long do_not_propagate_mask() {
        return ndo_not_propagate_mask(address());
    }

    @NativeType("Bool")
    public boolean override_redirect() {
        return noverride_redirect(address()) != 0;
    }

    @NativeType("Colormap")
    public long colormap() {
        return ncolormap(address());
    }

    @NativeType("Cursor")
    public long cursor() {
        return ncursor(address());
    }

    public XSetWindowAttributes background_pixmap(@NativeType("Pixmap") long j) {
        nbackground_pixmap(address(), j);
        return this;
    }

    public XSetWindowAttributes background_pixel(@NativeType("unsigned long") long j) {
        nbackground_pixel(address(), j);
        return this;
    }

    public XSetWindowAttributes border_pixmap(@NativeType("Pixmap") long j) {
        nborder_pixmap(address(), j);
        return this;
    }

    public XSetWindowAttributes border_pixel(@NativeType("unsigned long") long j) {
        nborder_pixel(address(), j);
        return this;
    }

    public XSetWindowAttributes bit_gravity(int i) {
        nbit_gravity(address(), i);
        return this;
    }

    public XSetWindowAttributes win_gravity(int i) {
        nwin_gravity(address(), i);
        return this;
    }

    public XSetWindowAttributes backing_store(int i) {
        nbacking_store(address(), i);
        return this;
    }

    public XSetWindowAttributes backing_planes(@NativeType("unsigned long") long j) {
        nbacking_planes(address(), j);
        return this;
    }

    public XSetWindowAttributes backing_pixel(@NativeType("unsigned long") long j) {
        nbacking_pixel(address(), j);
        return this;
    }

    public XSetWindowAttributes save_under(@NativeType("Bool") boolean z) {
        nsave_under(address(), z ? 1 : 0);
        return this;
    }

    public XSetWindowAttributes event_mask(long j) {
        nevent_mask(address(), j);
        return this;
    }

    public XSetWindowAttributes do_not_propagate_mask(long j) {
        ndo_not_propagate_mask(address(), j);
        return this;
    }

    public XSetWindowAttributes override_redirect(@NativeType("Bool") boolean z) {
        noverride_redirect(address(), z ? 1 : 0);
        return this;
    }

    public XSetWindowAttributes colormap(@NativeType("Colormap") long j) {
        ncolormap(address(), j);
        return this;
    }

    public XSetWindowAttributes cursor(@NativeType("Cursor") long j) {
        ncursor(address(), j);
        return this;
    }

    public XSetWindowAttributes set(long j, long j2, long j3, long j4, int i, int i2, int i3, long j5, long j6, boolean z, long j7, long j8, boolean z2, long j9, long j10) {
        background_pixmap(j);
        background_pixel(j2);
        border_pixmap(j3);
        border_pixel(j4);
        bit_gravity(i);
        win_gravity(i2);
        backing_store(i3);
        backing_planes(j5);
        backing_pixel(j6);
        save_under(z);
        event_mask(j7);
        do_not_propagate_mask(j8);
        override_redirect(z2);
        colormap(j9);
        cursor(j10);
        return this;
    }

    public XSetWindowAttributes set(XSetWindowAttributes xSetWindowAttributes) {
        MemoryUtil.memCopy(xSetWindowAttributes.address(), address(), SIZEOF);
        return this;
    }

    public static XSetWindowAttributes malloc() {
        return new XSetWindowAttributes(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XSetWindowAttributes calloc() {
        return new XSetWindowAttributes(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XSetWindowAttributes create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XSetWindowAttributes(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XSetWindowAttributes create(long j) {
        return new XSetWindowAttributes(j, null);
    }

    public static XSetWindowAttributes createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XSetWindowAttributes(j, null);
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
    public static XSetWindowAttributes mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XSetWindowAttributes callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XSetWindowAttributes mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XSetWindowAttributes callocStack(MemoryStack memoryStack) {
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

    public static XSetWindowAttributes malloc(MemoryStack memoryStack) {
        return new XSetWindowAttributes(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XSetWindowAttributes calloc(MemoryStack memoryStack) {
        return new XSetWindowAttributes(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nbackground_pixmap(long j) {
        return MemoryUtil.memGetCLong(j + BACKGROUND_PIXMAP);
    }

    public static long nbackground_pixel(long j) {
        return MemoryUtil.memGetCLong(j + BACKGROUND_PIXEL);
    }

    public static long nborder_pixmap(long j) {
        return MemoryUtil.memGetCLong(j + BORDER_PIXMAP);
    }

    public static long nborder_pixel(long j) {
        return MemoryUtil.memGetCLong(j + BORDER_PIXEL);
    }

    public static int nbit_gravity(long j) {
        return UNSAFE.getInt((Object) null, j + BIT_GRAVITY);
    }

    public static int nwin_gravity(long j) {
        return UNSAFE.getInt((Object) null, j + WIN_GRAVITY);
    }

    public static int nbacking_store(long j) {
        return UNSAFE.getInt((Object) null, j + BACKING_STORE);
    }

    public static long nbacking_planes(long j) {
        return MemoryUtil.memGetCLong(j + BACKING_PLANES);
    }

    public static long nbacking_pixel(long j) {
        return MemoryUtil.memGetCLong(j + BACKING_PIXEL);
    }

    public static int nsave_under(long j) {
        return UNSAFE.getInt((Object) null, j + SAVE_UNDER);
    }

    public static long nevent_mask(long j) {
        return MemoryUtil.memGetCLong(j + EVENT_MASK);
    }

    public static long ndo_not_propagate_mask(long j) {
        return MemoryUtil.memGetCLong(j + DO_NOT_PROPAGATE_MASK);
    }

    public static int noverride_redirect(long j) {
        return UNSAFE.getInt((Object) null, j + OVERRIDE_REDIRECT);
    }

    public static long ncolormap(long j) {
        return MemoryUtil.memGetCLong(j + COLORMAP);
    }

    public static long ncursor(long j) {
        return MemoryUtil.memGetCLong(j + CURSOR);
    }

    public static void nbackground_pixmap(long j, long j2) {
        MemoryUtil.memPutCLong(j + BACKGROUND_PIXMAP, j2);
    }

    public static void nbackground_pixel(long j, long j2) {
        MemoryUtil.memPutCLong(j + BACKGROUND_PIXEL, j2);
    }

    public static void nborder_pixmap(long j, long j2) {
        MemoryUtil.memPutCLong(j + BORDER_PIXMAP, j2);
    }

    public static void nborder_pixel(long j, long j2) {
        MemoryUtil.memPutCLong(j + BORDER_PIXEL, j2);
    }

    public static void nbit_gravity(long j, int i) {
        UNSAFE.putInt((Object) null, j + BIT_GRAVITY, i);
    }

    public static void nwin_gravity(long j, int i) {
        UNSAFE.putInt((Object) null, j + WIN_GRAVITY, i);
    }

    public static void nbacking_store(long j, int i) {
        UNSAFE.putInt((Object) null, j + BACKING_STORE, i);
    }

    public static void nbacking_planes(long j, long j2) {
        MemoryUtil.memPutCLong(j + BACKING_PLANES, j2);
    }

    public static void nbacking_pixel(long j, long j2) {
        MemoryUtil.memPutCLong(j + BACKING_PIXEL, j2);
    }

    public static void nsave_under(long j, int i) {
        UNSAFE.putInt((Object) null, j + SAVE_UNDER, i);
    }

    public static void nevent_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + EVENT_MASK, j2);
    }

    public static void ndo_not_propagate_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + DO_NOT_PROPAGATE_MASK, j2);
    }

    public static void noverride_redirect(long j, int i) {
        UNSAFE.putInt((Object) null, j + OVERRIDE_REDIRECT, i);
    }

    public static void ncolormap(long j, long j2) {
        MemoryUtil.memPutCLong(j + COLORMAP, j2);
    }

    public static void ncursor(long j, long j2) {
        MemoryUtil.memPutCLong(j + CURSOR, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XSetWindowAttributes$Buffer.class */
    public static class Buffer extends StructBuffer<XSetWindowAttributes, Buffer> implements NativeResource {
        private static final XSetWindowAttributes ELEMENT_FACTORY = XSetWindowAttributes.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XSetWindowAttributes.SIZEOF);
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
        public XSetWindowAttributes getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("Pixmap")
        public long background_pixmap() {
            return XSetWindowAttributes.nbackground_pixmap(address());
        }

        @NativeType("unsigned long")
        public long background_pixel() {
            return XSetWindowAttributes.nbackground_pixel(address());
        }

        @NativeType("Pixmap")
        public long border_pixmap() {
            return XSetWindowAttributes.nborder_pixmap(address());
        }

        @NativeType("unsigned long")
        public long border_pixel() {
            return XSetWindowAttributes.nborder_pixel(address());
        }

        public int bit_gravity() {
            return XSetWindowAttributes.nbit_gravity(address());
        }

        public int win_gravity() {
            return XSetWindowAttributes.nwin_gravity(address());
        }

        public int backing_store() {
            return XSetWindowAttributes.nbacking_store(address());
        }

        @NativeType("unsigned long")
        public long backing_planes() {
            return XSetWindowAttributes.nbacking_planes(address());
        }

        @NativeType("unsigned long")
        public long backing_pixel() {
            return XSetWindowAttributes.nbacking_pixel(address());
        }

        @NativeType("Bool")
        public boolean save_under() {
            return XSetWindowAttributes.nsave_under(address()) != 0;
        }

        public long event_mask() {
            return XSetWindowAttributes.nevent_mask(address());
        }

        public long do_not_propagate_mask() {
            return XSetWindowAttributes.ndo_not_propagate_mask(address());
        }

        @NativeType("Bool")
        public boolean override_redirect() {
            return XSetWindowAttributes.noverride_redirect(address()) != 0;
        }

        @NativeType("Colormap")
        public long colormap() {
            return XSetWindowAttributes.ncolormap(address());
        }

        @NativeType("Cursor")
        public long cursor() {
            return XSetWindowAttributes.ncursor(address());
        }

        public Buffer background_pixmap(@NativeType("Pixmap") long j) {
            XSetWindowAttributes.nbackground_pixmap(address(), j);
            return this;
        }

        public Buffer background_pixel(@NativeType("unsigned long") long j) {
            XSetWindowAttributes.nbackground_pixel(address(), j);
            return this;
        }

        public Buffer border_pixmap(@NativeType("Pixmap") long j) {
            XSetWindowAttributes.nborder_pixmap(address(), j);
            return this;
        }

        public Buffer border_pixel(@NativeType("unsigned long") long j) {
            XSetWindowAttributes.nborder_pixel(address(), j);
            return this;
        }

        public Buffer bit_gravity(int i) {
            XSetWindowAttributes.nbit_gravity(address(), i);
            return this;
        }

        public Buffer win_gravity(int i) {
            XSetWindowAttributes.nwin_gravity(address(), i);
            return this;
        }

        public Buffer backing_store(int i) {
            XSetWindowAttributes.nbacking_store(address(), i);
            return this;
        }

        public Buffer backing_planes(@NativeType("unsigned long") long j) {
            XSetWindowAttributes.nbacking_planes(address(), j);
            return this;
        }

        public Buffer backing_pixel(@NativeType("unsigned long") long j) {
            XSetWindowAttributes.nbacking_pixel(address(), j);
            return this;
        }

        public Buffer save_under(@NativeType("Bool") boolean z) {
            XSetWindowAttributes.nsave_under(address(), z ? 1 : 0);
            return this;
        }

        public Buffer event_mask(long j) {
            XSetWindowAttributes.nevent_mask(address(), j);
            return this;
        }

        public Buffer do_not_propagate_mask(long j) {
            XSetWindowAttributes.ndo_not_propagate_mask(address(), j);
            return this;
        }

        public Buffer override_redirect(@NativeType("Bool") boolean z) {
            XSetWindowAttributes.noverride_redirect(address(), z ? 1 : 0);
            return this;
        }

        public Buffer colormap(@NativeType("Colormap") long j) {
            XSetWindowAttributes.ncolormap(address(), j);
            return this;
        }

        public Buffer cursor(@NativeType("Cursor") long j) {
            XSetWindowAttributes.ncursor(address(), j);
            return this;
        }
    }
}
