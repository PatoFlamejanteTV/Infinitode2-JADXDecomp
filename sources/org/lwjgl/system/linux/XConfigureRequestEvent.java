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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XConfigureRequestEvent.class */
public class XConfigureRequestEvent extends Struct<XConfigureRequestEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int PARENT;
    public static final int WINDOW;
    public static final int X;
    public static final int Y;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int BORDER_WIDTH;
    public static final int ABOVE;
    public static final int DETAIL;
    public static final int VALUE_MASK;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(CLONG_SIZE), __member(4), __member(CLONG_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        PARENT = __struct.offsetof(4);
        WINDOW = __struct.offsetof(5);
        X = __struct.offsetof(6);
        Y = __struct.offsetof(7);
        WIDTH = __struct.offsetof(8);
        HEIGHT = __struct.offsetof(9);
        BORDER_WIDTH = __struct.offsetof(10);
        ABOVE = __struct.offsetof(11);
        DETAIL = __struct.offsetof(12);
        VALUE_MASK = __struct.offsetof(13);
    }

    protected XConfigureRequestEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XConfigureRequestEvent create(long j, ByteBuffer byteBuffer) {
        return new XConfigureRequestEvent(j, byteBuffer);
    }

    public XConfigureRequestEvent(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int type() {
        return ntype(address());
    }

    @NativeType("unsigned long")
    public long serial() {
        return nserial(address());
    }

    @NativeType("Bool")
    public boolean send_event() {
        return nsend_event(address()) != 0;
    }

    @NativeType("Display *")
    public long display() {
        return ndisplay(address());
    }

    @NativeType("Window")
    public long parent() {
        return nparent(address());
    }

    @NativeType("Window")
    public long window() {
        return nwindow(address());
    }

    public int x() {
        return nx(address());
    }

    public int y() {
        return ny(address());
    }

    public int width() {
        return nwidth(address());
    }

    public int height() {
        return nheight(address());
    }

    public int border_width() {
        return nborder_width(address());
    }

    @NativeType("Window")
    public long above() {
        return nabove(address());
    }

    public int detail() {
        return ndetail(address());
    }

    @NativeType("unsigned long")
    public long value_mask() {
        return nvalue_mask(address());
    }

    public XConfigureRequestEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XConfigureRequestEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XConfigureRequestEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XConfigureRequestEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XConfigureRequestEvent parent(@NativeType("Window") long j) {
        nparent(address(), j);
        return this;
    }

    public XConfigureRequestEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XConfigureRequestEvent x(int i) {
        nx(address(), i);
        return this;
    }

    public XConfigureRequestEvent y(int i) {
        ny(address(), i);
        return this;
    }

    public XConfigureRequestEvent width(int i) {
        nwidth(address(), i);
        return this;
    }

    public XConfigureRequestEvent height(int i) {
        nheight(address(), i);
        return this;
    }

    public XConfigureRequestEvent border_width(int i) {
        nborder_width(address(), i);
        return this;
    }

    public XConfigureRequestEvent above(@NativeType("Window") long j) {
        nabove(address(), j);
        return this;
    }

    public XConfigureRequestEvent detail(int i) {
        ndetail(address(), i);
        return this;
    }

    public XConfigureRequestEvent value_mask(@NativeType("unsigned long") long j) {
        nvalue_mask(address(), j);
        return this;
    }

    public XConfigureRequestEvent set(int i, long j, boolean z, long j2, long j3, long j4, int i2, int i3, int i4, int i5, int i6, long j5, int i7, long j6) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        parent(j3);
        window(j4);
        x(i2);
        y(i3);
        width(i4);
        height(i5);
        border_width(i6);
        above(j5);
        detail(i7);
        value_mask(j6);
        return this;
    }

    public XConfigureRequestEvent set(XConfigureRequestEvent xConfigureRequestEvent) {
        MemoryUtil.memCopy(xConfigureRequestEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XConfigureRequestEvent malloc() {
        return new XConfigureRequestEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XConfigureRequestEvent calloc() {
        return new XConfigureRequestEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XConfigureRequestEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XConfigureRequestEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XConfigureRequestEvent create(long j) {
        return new XConfigureRequestEvent(j, null);
    }

    public static XConfigureRequestEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XConfigureRequestEvent(j, null);
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
    public static XConfigureRequestEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XConfigureRequestEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XConfigureRequestEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XConfigureRequestEvent callocStack(MemoryStack memoryStack) {
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

    public static XConfigureRequestEvent malloc(MemoryStack memoryStack) {
        return new XConfigureRequestEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XConfigureRequestEvent calloc(MemoryStack memoryStack) {
        return new XConfigureRequestEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ntype(long j) {
        return UNSAFE.getInt((Object) null, j + TYPE);
    }

    public static long nserial(long j) {
        return MemoryUtil.memGetCLong(j + SERIAL);
    }

    public static int nsend_event(long j) {
        return UNSAFE.getInt((Object) null, j + SEND_EVENT);
    }

    public static long ndisplay(long j) {
        return MemoryUtil.memGetAddress(j + DISPLAY);
    }

    public static long nparent(long j) {
        return MemoryUtil.memGetCLong(j + PARENT);
    }

    public static long nwindow(long j) {
        return MemoryUtil.memGetCLong(j + WINDOW);
    }

    public static int nx(long j) {
        return UNSAFE.getInt((Object) null, j + X);
    }

    public static int ny(long j) {
        return UNSAFE.getInt((Object) null, j + Y);
    }

    public static int nwidth(long j) {
        return UNSAFE.getInt((Object) null, j + WIDTH);
    }

    public static int nheight(long j) {
        return UNSAFE.getInt((Object) null, j + HEIGHT);
    }

    public static int nborder_width(long j) {
        return UNSAFE.getInt((Object) null, j + BORDER_WIDTH);
    }

    public static long nabove(long j) {
        return MemoryUtil.memGetCLong(j + ABOVE);
    }

    public static int ndetail(long j) {
        return UNSAFE.getInt((Object) null, j + DETAIL);
    }

    public static long nvalue_mask(long j) {
        return MemoryUtil.memGetCLong(j + VALUE_MASK);
    }

    public static void ntype(long j, int i) {
        UNSAFE.putInt((Object) null, j + TYPE, i);
    }

    public static void nserial(long j, long j2) {
        MemoryUtil.memPutCLong(j + SERIAL, j2);
    }

    public static void nsend_event(long j, int i) {
        UNSAFE.putInt((Object) null, j + SEND_EVENT, i);
    }

    public static void ndisplay(long j, long j2) {
        MemoryUtil.memPutAddress(j + DISPLAY, Checks.check(j2));
    }

    public static void nparent(long j, long j2) {
        MemoryUtil.memPutCLong(j + PARENT, j2);
    }

    public static void nwindow(long j, long j2) {
        MemoryUtil.memPutCLong(j + WINDOW, j2);
    }

    public static void nx(long j, int i) {
        UNSAFE.putInt((Object) null, j + X, i);
    }

    public static void ny(long j, int i) {
        UNSAFE.putInt((Object) null, j + Y, i);
    }

    public static void nwidth(long j, int i) {
        UNSAFE.putInt((Object) null, j + WIDTH, i);
    }

    public static void nheight(long j, int i) {
        UNSAFE.putInt((Object) null, j + HEIGHT, i);
    }

    public static void nborder_width(long j, int i) {
        UNSAFE.putInt((Object) null, j + BORDER_WIDTH, i);
    }

    public static void nabove(long j, long j2) {
        MemoryUtil.memPutCLong(j + ABOVE, j2);
    }

    public static void ndetail(long j, int i) {
        UNSAFE.putInt((Object) null, j + DETAIL, i);
    }

    public static void nvalue_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + VALUE_MASK, j2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XConfigureRequestEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XConfigureRequestEvent, Buffer> implements NativeResource {
        private static final XConfigureRequestEvent ELEMENT_FACTORY = XConfigureRequestEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XConfigureRequestEvent.SIZEOF);
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
        public XConfigureRequestEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XConfigureRequestEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XConfigureRequestEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XConfigureRequestEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XConfigureRequestEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long parent() {
            return XConfigureRequestEvent.nparent(address());
        }

        @NativeType("Window")
        public long window() {
            return XConfigureRequestEvent.nwindow(address());
        }

        public int x() {
            return XConfigureRequestEvent.nx(address());
        }

        public int y() {
            return XConfigureRequestEvent.ny(address());
        }

        public int width() {
            return XConfigureRequestEvent.nwidth(address());
        }

        public int height() {
            return XConfigureRequestEvent.nheight(address());
        }

        public int border_width() {
            return XConfigureRequestEvent.nborder_width(address());
        }

        @NativeType("Window")
        public long above() {
            return XConfigureRequestEvent.nabove(address());
        }

        public int detail() {
            return XConfigureRequestEvent.ndetail(address());
        }

        @NativeType("unsigned long")
        public long value_mask() {
            return XConfigureRequestEvent.nvalue_mask(address());
        }

        public Buffer type(int i) {
            XConfigureRequestEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XConfigureRequestEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XConfigureRequestEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XConfigureRequestEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer parent(@NativeType("Window") long j) {
            XConfigureRequestEvent.nparent(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XConfigureRequestEvent.nwindow(address(), j);
            return this;
        }

        public Buffer x(int i) {
            XConfigureRequestEvent.nx(address(), i);
            return this;
        }

        public Buffer y(int i) {
            XConfigureRequestEvent.ny(address(), i);
            return this;
        }

        public Buffer width(int i) {
            XConfigureRequestEvent.nwidth(address(), i);
            return this;
        }

        public Buffer height(int i) {
            XConfigureRequestEvent.nheight(address(), i);
            return this;
        }

        public Buffer border_width(int i) {
            XConfigureRequestEvent.nborder_width(address(), i);
            return this;
        }

        public Buffer above(@NativeType("Window") long j) {
            XConfigureRequestEvent.nabove(address(), j);
            return this;
        }

        public Buffer detail(int i) {
            XConfigureRequestEvent.ndetail(address(), i);
            return this;
        }

        public Buffer value_mask(@NativeType("unsigned long") long j) {
            XConfigureRequestEvent.nvalue_mask(address(), j);
            return this;
        }
    }
}
