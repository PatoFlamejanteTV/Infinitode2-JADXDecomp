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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGraphicsExposeEvent.class */
public class XGraphicsExposeEvent extends Struct<XGraphicsExposeEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int DRAWABLE;
    public static final int X;
    public static final int Y;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int COUNT;
    public static final int MAJOR_CODE;
    public static final int MINOR_CODE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        DRAWABLE = __struct.offsetof(4);
        X = __struct.offsetof(5);
        Y = __struct.offsetof(6);
        WIDTH = __struct.offsetof(7);
        HEIGHT = __struct.offsetof(8);
        COUNT = __struct.offsetof(9);
        MAJOR_CODE = __struct.offsetof(10);
        MINOR_CODE = __struct.offsetof(11);
    }

    protected XGraphicsExposeEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XGraphicsExposeEvent create(long j, ByteBuffer byteBuffer) {
        return new XGraphicsExposeEvent(j, byteBuffer);
    }

    public XGraphicsExposeEvent(ByteBuffer byteBuffer) {
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

    @NativeType("Drawable")
    public long drawable() {
        return ndrawable(address());
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

    public int major_code() {
        return nmajor_code(address());
    }

    public int minor_code() {
        return nminor_code(address());
    }

    public XGraphicsExposeEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XGraphicsExposeEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XGraphicsExposeEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XGraphicsExposeEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XGraphicsExposeEvent drawable(@NativeType("Drawable") long j) {
        ndrawable(address(), j);
        return this;
    }

    public XGraphicsExposeEvent x(int i) {
        nx(address(), i);
        return this;
    }

    public XGraphicsExposeEvent y(int i) {
        ny(address(), i);
        return this;
    }

    public XGraphicsExposeEvent width(int i) {
        nwidth(address(), i);
        return this;
    }

    public XGraphicsExposeEvent height(int i) {
        nheight(address(), i);
        return this;
    }

    public XGraphicsExposeEvent count(int i) {
        ncount(address(), i);
        return this;
    }

    public XGraphicsExposeEvent major_code(int i) {
        nmajor_code(address(), i);
        return this;
    }

    public XGraphicsExposeEvent minor_code(int i) {
        nminor_code(address(), i);
        return this;
    }

    public XGraphicsExposeEvent set(int i, long j, boolean z, long j2, long j3, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        drawable(j3);
        x(i2);
        y(i3);
        width(i4);
        height(i5);
        count(i6);
        major_code(i7);
        minor_code(i8);
        return this;
    }

    public XGraphicsExposeEvent set(XGraphicsExposeEvent xGraphicsExposeEvent) {
        MemoryUtil.memCopy(xGraphicsExposeEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XGraphicsExposeEvent malloc() {
        return new XGraphicsExposeEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XGraphicsExposeEvent calloc() {
        return new XGraphicsExposeEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XGraphicsExposeEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XGraphicsExposeEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XGraphicsExposeEvent create(long j) {
        return new XGraphicsExposeEvent(j, null);
    }

    public static XGraphicsExposeEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XGraphicsExposeEvent(j, null);
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
    public static XGraphicsExposeEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGraphicsExposeEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGraphicsExposeEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XGraphicsExposeEvent callocStack(MemoryStack memoryStack) {
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

    public static XGraphicsExposeEvent malloc(MemoryStack memoryStack) {
        return new XGraphicsExposeEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XGraphicsExposeEvent calloc(MemoryStack memoryStack) {
        return new XGraphicsExposeEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long ndrawable(long j) {
        return MemoryUtil.memGetCLong(j + DRAWABLE);
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

    public static int nmajor_code(long j) {
        return UNSAFE.getInt((Object) null, j + MAJOR_CODE);
    }

    public static int nminor_code(long j) {
        return UNSAFE.getInt((Object) null, j + MINOR_CODE);
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

    public static void ndrawable(long j, long j2) {
        MemoryUtil.memPutCLong(j + DRAWABLE, j2);
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

    public static void nmajor_code(long j, int i) {
        UNSAFE.putInt((Object) null, j + MAJOR_CODE, i);
    }

    public static void nminor_code(long j, int i) {
        UNSAFE.putInt((Object) null, j + MINOR_CODE, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGraphicsExposeEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XGraphicsExposeEvent, Buffer> implements NativeResource {
        private static final XGraphicsExposeEvent ELEMENT_FACTORY = XGraphicsExposeEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XGraphicsExposeEvent.SIZEOF);
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
        public XGraphicsExposeEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XGraphicsExposeEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XGraphicsExposeEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XGraphicsExposeEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XGraphicsExposeEvent.ndisplay(address());
        }

        @NativeType("Drawable")
        public long drawable() {
            return XGraphicsExposeEvent.ndrawable(address());
        }

        public int x() {
            return XGraphicsExposeEvent.nx(address());
        }

        public int y() {
            return XGraphicsExposeEvent.ny(address());
        }

        public int width() {
            return XGraphicsExposeEvent.nwidth(address());
        }

        public int height() {
            return XGraphicsExposeEvent.nheight(address());
        }

        public int count() {
            return XGraphicsExposeEvent.ncount(address());
        }

        public int major_code() {
            return XGraphicsExposeEvent.nmajor_code(address());
        }

        public int minor_code() {
            return XGraphicsExposeEvent.nminor_code(address());
        }

        public Buffer type(int i) {
            XGraphicsExposeEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XGraphicsExposeEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XGraphicsExposeEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XGraphicsExposeEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer drawable(@NativeType("Drawable") long j) {
            XGraphicsExposeEvent.ndrawable(address(), j);
            return this;
        }

        public Buffer x(int i) {
            XGraphicsExposeEvent.nx(address(), i);
            return this;
        }

        public Buffer y(int i) {
            XGraphicsExposeEvent.ny(address(), i);
            return this;
        }

        public Buffer width(int i) {
            XGraphicsExposeEvent.nwidth(address(), i);
            return this;
        }

        public Buffer height(int i) {
            XGraphicsExposeEvent.nheight(address(), i);
            return this;
        }

        public Buffer count(int i) {
            XGraphicsExposeEvent.ncount(address(), i);
            return this;
        }

        public Buffer major_code(int i) {
            XGraphicsExposeEvent.nmajor_code(address(), i);
            return this;
        }

        public Buffer minor_code(int i) {
            XGraphicsExposeEvent.nminor_code(address(), i);
            return this;
        }
    }
}
