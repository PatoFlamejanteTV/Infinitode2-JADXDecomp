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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XExposeEvent.class */
public class XExposeEvent extends Struct<XExposeEvent> implements NativeResource {
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
    public static final int COUNT;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4));
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
        COUNT = __struct.offsetof(9);
    }

    protected XExposeEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XExposeEvent create(long j, ByteBuffer byteBuffer) {
        return new XExposeEvent(j, byteBuffer);
    }

    public XExposeEvent(ByteBuffer byteBuffer) {
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

    public int count() {
        return ncount(address());
    }

    public XExposeEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XExposeEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XExposeEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XExposeEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XExposeEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XExposeEvent x(int i) {
        nx(address(), i);
        return this;
    }

    public XExposeEvent y(int i) {
        ny(address(), i);
        return this;
    }

    public XExposeEvent width(int i) {
        nwidth(address(), i);
        return this;
    }

    public XExposeEvent height(int i) {
        nheight(address(), i);
        return this;
    }

    public XExposeEvent count(int i) {
        ncount(address(), i);
        return this;
    }

    public XExposeEvent set(int i, long j, boolean z, long j2, long j3, int i2, int i3, int i4, int i5, int i6) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        x(i2);
        y(i3);
        width(i4);
        height(i5);
        count(i6);
        return this;
    }

    public XExposeEvent set(XExposeEvent xExposeEvent) {
        MemoryUtil.memCopy(xExposeEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XExposeEvent malloc() {
        return new XExposeEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XExposeEvent calloc() {
        return new XExposeEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XExposeEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XExposeEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XExposeEvent create(long j) {
        return new XExposeEvent(j, null);
    }

    public static XExposeEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XExposeEvent(j, null);
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
    public static XExposeEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XExposeEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XExposeEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XExposeEvent callocStack(MemoryStack memoryStack) {
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

    public static XExposeEvent malloc(MemoryStack memoryStack) {
        return new XExposeEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XExposeEvent calloc(MemoryStack memoryStack) {
        return new XExposeEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int ncount(long j) {
        return UNSAFE.getInt((Object) null, j + COUNT);
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

    public static void ncount(long j, int i) {
        UNSAFE.putInt((Object) null, j + COUNT, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XExposeEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XExposeEvent, Buffer> implements NativeResource {
        private static final XExposeEvent ELEMENT_FACTORY = XExposeEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XExposeEvent.SIZEOF);
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
        public XExposeEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XExposeEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XExposeEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XExposeEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XExposeEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XExposeEvent.nwindow(address());
        }

        public int x() {
            return XExposeEvent.nx(address());
        }

        public int y() {
            return XExposeEvent.ny(address());
        }

        public int width() {
            return XExposeEvent.nwidth(address());
        }

        public int height() {
            return XExposeEvent.nheight(address());
        }

        public int count() {
            return XExposeEvent.ncount(address());
        }

        public Buffer type(int i) {
            XExposeEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XExposeEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XExposeEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XExposeEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XExposeEvent.nwindow(address(), j);
            return this;
        }

        public Buffer x(int i) {
            XExposeEvent.nx(address(), i);
            return this;
        }

        public Buffer y(int i) {
            XExposeEvent.ny(address(), i);
            return this;
        }

        public Buffer width(int i) {
            XExposeEvent.nwidth(address(), i);
            return this;
        }

        public Buffer height(int i) {
            XExposeEvent.nheight(address(), i);
            return this;
        }

        public Buffer count(int i) {
            XExposeEvent.ncount(address(), i);
            return this;
        }
    }
}
