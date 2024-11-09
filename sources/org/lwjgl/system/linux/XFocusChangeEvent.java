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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XFocusChangeEvent.class */
public class XFocusChangeEvent extends Struct<XFocusChangeEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int MODE;
    public static final int DETAIL;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        MODE = __struct.offsetof(5);
        DETAIL = __struct.offsetof(6);
    }

    protected XFocusChangeEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XFocusChangeEvent create(long j, ByteBuffer byteBuffer) {
        return new XFocusChangeEvent(j, byteBuffer);
    }

    public XFocusChangeEvent(ByteBuffer byteBuffer) {
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

    public int mode() {
        return nmode(address());
    }

    public int detail() {
        return ndetail(address());
    }

    public XFocusChangeEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XFocusChangeEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XFocusChangeEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XFocusChangeEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XFocusChangeEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XFocusChangeEvent mode(int i) {
        nmode(address(), i);
        return this;
    }

    public XFocusChangeEvent detail(int i) {
        ndetail(address(), i);
        return this;
    }

    public XFocusChangeEvent set(int i, long j, boolean z, long j2, long j3, int i2, int i3) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        mode(i2);
        detail(i3);
        return this;
    }

    public XFocusChangeEvent set(XFocusChangeEvent xFocusChangeEvent) {
        MemoryUtil.memCopy(xFocusChangeEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XFocusChangeEvent malloc() {
        return new XFocusChangeEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XFocusChangeEvent calloc() {
        return new XFocusChangeEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XFocusChangeEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XFocusChangeEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XFocusChangeEvent create(long j) {
        return new XFocusChangeEvent(j, null);
    }

    public static XFocusChangeEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XFocusChangeEvent(j, null);
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
    public static XFocusChangeEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XFocusChangeEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XFocusChangeEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XFocusChangeEvent callocStack(MemoryStack memoryStack) {
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

    public static XFocusChangeEvent malloc(MemoryStack memoryStack) {
        return new XFocusChangeEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XFocusChangeEvent calloc(MemoryStack memoryStack) {
        return new XFocusChangeEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nmode(long j) {
        return UNSAFE.getInt((Object) null, j + MODE);
    }

    public static int ndetail(long j) {
        return UNSAFE.getInt((Object) null, j + DETAIL);
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

    public static void nmode(long j, int i) {
        UNSAFE.putInt((Object) null, j + MODE, i);
    }

    public static void ndetail(long j, int i) {
        UNSAFE.putInt((Object) null, j + DETAIL, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XFocusChangeEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XFocusChangeEvent, Buffer> implements NativeResource {
        private static final XFocusChangeEvent ELEMENT_FACTORY = XFocusChangeEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XFocusChangeEvent.SIZEOF);
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
        public XFocusChangeEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XFocusChangeEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XFocusChangeEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XFocusChangeEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XFocusChangeEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XFocusChangeEvent.nwindow(address());
        }

        public int mode() {
            return XFocusChangeEvent.nmode(address());
        }

        public int detail() {
            return XFocusChangeEvent.ndetail(address());
        }

        public Buffer type(int i) {
            XFocusChangeEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XFocusChangeEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XFocusChangeEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XFocusChangeEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XFocusChangeEvent.nwindow(address(), j);
            return this;
        }

        public Buffer mode(int i) {
            XFocusChangeEvent.nmode(address(), i);
            return this;
        }

        public Buffer detail(int i) {
            XFocusChangeEvent.ndetail(address(), i);
            return this;
        }
    }
}
