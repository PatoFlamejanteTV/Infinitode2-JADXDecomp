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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XConfigureEvent.class */
public class XConfigureEvent extends Struct<XConfigureEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int X;
    public static final int Y;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int BORDER_WIDTH;
    public static final int ABOVE;
    public static final int OVERRIDE_REDIRECT;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(CLONG_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        X = __struct.offsetof(5);
        Y = __struct.offsetof(6);
        WIDTH = __struct.offsetof(7);
        HEIGHT = __struct.offsetof(8);
        BORDER_WIDTH = __struct.offsetof(9);
        ABOVE = __struct.offsetof(10);
        OVERRIDE_REDIRECT = __struct.offsetof(11);
    }

    protected XConfigureEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XConfigureEvent create(long j, ByteBuffer byteBuffer) {
        return new XConfigureEvent(j, byteBuffer);
    }

    public XConfigureEvent(ByteBuffer byteBuffer) {
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

    @NativeType("Bool")
    public boolean override_redirect() {
        return noverride_redirect(address()) != 0;
    }

    public XConfigureEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XConfigureEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XConfigureEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XConfigureEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XConfigureEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XConfigureEvent x(int i) {
        nx(address(), i);
        return this;
    }

    public XConfigureEvent y(int i) {
        ny(address(), i);
        return this;
    }

    public XConfigureEvent width(int i) {
        nwidth(address(), i);
        return this;
    }

    public XConfigureEvent height(int i) {
        nheight(address(), i);
        return this;
    }

    public XConfigureEvent border_width(int i) {
        nborder_width(address(), i);
        return this;
    }

    public XConfigureEvent above(@NativeType("Window") long j) {
        nabove(address(), j);
        return this;
    }

    public XConfigureEvent override_redirect(@NativeType("Bool") boolean z) {
        noverride_redirect(address(), z ? 1 : 0);
        return this;
    }

    public XConfigureEvent set(int i, long j, boolean z, long j2, long j3, int i2, int i3, int i4, int i5, int i6, long j4, boolean z2) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        x(i2);
        y(i3);
        width(i4);
        height(i5);
        border_width(i6);
        above(j4);
        override_redirect(z2);
        return this;
    }

    public XConfigureEvent set(XConfigureEvent xConfigureEvent) {
        MemoryUtil.memCopy(xConfigureEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XConfigureEvent malloc() {
        return new XConfigureEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XConfigureEvent calloc() {
        return new XConfigureEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XConfigureEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XConfigureEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XConfigureEvent create(long j) {
        return new XConfigureEvent(j, null);
    }

    public static XConfigureEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XConfigureEvent(j, null);
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
    public static XConfigureEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XConfigureEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XConfigureEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XConfigureEvent callocStack(MemoryStack memoryStack) {
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

    public static XConfigureEvent malloc(MemoryStack memoryStack) {
        return new XConfigureEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XConfigureEvent calloc(MemoryStack memoryStack) {
        return new XConfigureEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static void noverride_redirect(long j, int i) {
        UNSAFE.putInt((Object) null, j + OVERRIDE_REDIRECT, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XConfigureEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XConfigureEvent, Buffer> implements NativeResource {
        private static final XConfigureEvent ELEMENT_FACTORY = XConfigureEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XConfigureEvent.SIZEOF);
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
        public XConfigureEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XConfigureEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XConfigureEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XConfigureEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XConfigureEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XConfigureEvent.nwindow(address());
        }

        public int x() {
            return XConfigureEvent.nx(address());
        }

        public int y() {
            return XConfigureEvent.ny(address());
        }

        public int width() {
            return XConfigureEvent.nwidth(address());
        }

        public int height() {
            return XConfigureEvent.nheight(address());
        }

        public int border_width() {
            return XConfigureEvent.nborder_width(address());
        }

        @NativeType("Window")
        public long above() {
            return XConfigureEvent.nabove(address());
        }

        @NativeType("Bool")
        public boolean override_redirect() {
            return XConfigureEvent.noverride_redirect(address()) != 0;
        }

        public Buffer type(int i) {
            XConfigureEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XConfigureEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XConfigureEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XConfigureEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XConfigureEvent.nwindow(address(), j);
            return this;
        }

        public Buffer x(int i) {
            XConfigureEvent.nx(address(), i);
            return this;
        }

        public Buffer y(int i) {
            XConfigureEvent.ny(address(), i);
            return this;
        }

        public Buffer width(int i) {
            XConfigureEvent.nwidth(address(), i);
            return this;
        }

        public Buffer height(int i) {
            XConfigureEvent.nheight(address(), i);
            return this;
        }

        public Buffer border_width(int i) {
            XConfigureEvent.nborder_width(address(), i);
            return this;
        }

        public Buffer above(@NativeType("Window") long j) {
            XConfigureEvent.nabove(address(), j);
            return this;
        }

        public Buffer override_redirect(@NativeType("Bool") boolean z) {
            XConfigureEvent.noverride_redirect(address(), z ? 1 : 0);
            return this;
        }
    }
}
