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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XCreateWindowEvent.class */
public class XCreateWindowEvent extends Struct<XCreateWindowEvent> implements NativeResource {
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
    public static final int OVERRIDE_REDIRECT;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4));
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
        OVERRIDE_REDIRECT = __struct.offsetof(11);
    }

    protected XCreateWindowEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XCreateWindowEvent create(long j, ByteBuffer byteBuffer) {
        return new XCreateWindowEvent(j, byteBuffer);
    }

    public XCreateWindowEvent(ByteBuffer byteBuffer) {
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

    public int override_redirect() {
        return noverride_redirect(address());
    }

    public XCreateWindowEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XCreateWindowEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XCreateWindowEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XCreateWindowEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XCreateWindowEvent parent(@NativeType("Window") long j) {
        nparent(address(), j);
        return this;
    }

    public XCreateWindowEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XCreateWindowEvent x(int i) {
        nx(address(), i);
        return this;
    }

    public XCreateWindowEvent y(int i) {
        ny(address(), i);
        return this;
    }

    public XCreateWindowEvent width(int i) {
        nwidth(address(), i);
        return this;
    }

    public XCreateWindowEvent height(int i) {
        nheight(address(), i);
        return this;
    }

    public XCreateWindowEvent border_width(int i) {
        nborder_width(address(), i);
        return this;
    }

    public XCreateWindowEvent override_redirect(int i) {
        noverride_redirect(address(), i);
        return this;
    }

    public XCreateWindowEvent set(int i, long j, boolean z, long j2, long j3, long j4, int i2, int i3, int i4, int i5, int i6, int i7) {
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
        override_redirect(i7);
        return this;
    }

    public XCreateWindowEvent set(XCreateWindowEvent xCreateWindowEvent) {
        MemoryUtil.memCopy(xCreateWindowEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XCreateWindowEvent malloc() {
        return new XCreateWindowEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XCreateWindowEvent calloc() {
        return new XCreateWindowEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XCreateWindowEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XCreateWindowEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XCreateWindowEvent create(long j) {
        return new XCreateWindowEvent(j, null);
    }

    public static XCreateWindowEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XCreateWindowEvent(j, null);
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
    public static XCreateWindowEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XCreateWindowEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XCreateWindowEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XCreateWindowEvent callocStack(MemoryStack memoryStack) {
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

    public static XCreateWindowEvent malloc(MemoryStack memoryStack) {
        return new XCreateWindowEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XCreateWindowEvent calloc(MemoryStack memoryStack) {
        return new XCreateWindowEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int noverride_redirect(long j) {
        return UNSAFE.getInt((Object) null, j + OVERRIDE_REDIRECT);
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

    public static void noverride_redirect(long j, int i) {
        UNSAFE.putInt((Object) null, j + OVERRIDE_REDIRECT, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XCreateWindowEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XCreateWindowEvent, Buffer> implements NativeResource {
        private static final XCreateWindowEvent ELEMENT_FACTORY = XCreateWindowEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XCreateWindowEvent.SIZEOF);
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
        public XCreateWindowEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XCreateWindowEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XCreateWindowEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XCreateWindowEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XCreateWindowEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long parent() {
            return XCreateWindowEvent.nparent(address());
        }

        @NativeType("Window")
        public long window() {
            return XCreateWindowEvent.nwindow(address());
        }

        public int x() {
            return XCreateWindowEvent.nx(address());
        }

        public int y() {
            return XCreateWindowEvent.ny(address());
        }

        public int width() {
            return XCreateWindowEvent.nwidth(address());
        }

        public int height() {
            return XCreateWindowEvent.nheight(address());
        }

        public int border_width() {
            return XCreateWindowEvent.nborder_width(address());
        }

        public int override_redirect() {
            return XCreateWindowEvent.noverride_redirect(address());
        }

        public Buffer type(int i) {
            XCreateWindowEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XCreateWindowEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XCreateWindowEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XCreateWindowEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer parent(@NativeType("Window") long j) {
            XCreateWindowEvent.nparent(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XCreateWindowEvent.nwindow(address(), j);
            return this;
        }

        public Buffer x(int i) {
            XCreateWindowEvent.nx(address(), i);
            return this;
        }

        public Buffer y(int i) {
            XCreateWindowEvent.ny(address(), i);
            return this;
        }

        public Buffer width(int i) {
            XCreateWindowEvent.nwidth(address(), i);
            return this;
        }

        public Buffer height(int i) {
            XCreateWindowEvent.nheight(address(), i);
            return this;
        }

        public Buffer border_width(int i) {
            XCreateWindowEvent.nborder_width(address(), i);
            return this;
        }

        public Buffer override_redirect(int i) {
            XCreateWindowEvent.noverride_redirect(address(), i);
            return this;
        }
    }
}
