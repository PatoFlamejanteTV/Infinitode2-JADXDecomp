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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XCirculateRequestEvent.class */
public class XCirculateRequestEvent extends Struct<XCirculateRequestEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int PARENT;
    public static final int WINDOW;
    public static final int PLACE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        PARENT = __struct.offsetof(4);
        WINDOW = __struct.offsetof(5);
        PLACE = __struct.offsetof(6);
    }

    protected XCirculateRequestEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XCirculateRequestEvent create(long j, ByteBuffer byteBuffer) {
        return new XCirculateRequestEvent(j, byteBuffer);
    }

    public XCirculateRequestEvent(ByteBuffer byteBuffer) {
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

    public int place() {
        return nplace(address());
    }

    public XCirculateRequestEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XCirculateRequestEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XCirculateRequestEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XCirculateRequestEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XCirculateRequestEvent parent(@NativeType("Window") long j) {
        nparent(address(), j);
        return this;
    }

    public XCirculateRequestEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XCirculateRequestEvent place(int i) {
        nplace(address(), i);
        return this;
    }

    public XCirculateRequestEvent set(int i, long j, boolean z, long j2, long j3, long j4, int i2) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        parent(j3);
        window(j4);
        place(i2);
        return this;
    }

    public XCirculateRequestEvent set(XCirculateRequestEvent xCirculateRequestEvent) {
        MemoryUtil.memCopy(xCirculateRequestEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XCirculateRequestEvent malloc() {
        return new XCirculateRequestEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XCirculateRequestEvent calloc() {
        return new XCirculateRequestEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XCirculateRequestEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XCirculateRequestEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XCirculateRequestEvent create(long j) {
        return new XCirculateRequestEvent(j, null);
    }

    public static XCirculateRequestEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XCirculateRequestEvent(j, null);
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
    public static XCirculateRequestEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XCirculateRequestEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XCirculateRequestEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XCirculateRequestEvent callocStack(MemoryStack memoryStack) {
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

    public static XCirculateRequestEvent malloc(MemoryStack memoryStack) {
        return new XCirculateRequestEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XCirculateRequestEvent calloc(MemoryStack memoryStack) {
        return new XCirculateRequestEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nplace(long j) {
        return UNSAFE.getInt((Object) null, j + PLACE);
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

    public static void nplace(long j, int i) {
        UNSAFE.putInt((Object) null, j + PLACE, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XCirculateRequestEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XCirculateRequestEvent, Buffer> implements NativeResource {
        private static final XCirculateRequestEvent ELEMENT_FACTORY = XCirculateRequestEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XCirculateRequestEvent.SIZEOF);
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
        public XCirculateRequestEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XCirculateRequestEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XCirculateRequestEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XCirculateRequestEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XCirculateRequestEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long parent() {
            return XCirculateRequestEvent.nparent(address());
        }

        @NativeType("Window")
        public long window() {
            return XCirculateRequestEvent.nwindow(address());
        }

        public int place() {
            return XCirculateRequestEvent.nplace(address());
        }

        public Buffer type(int i) {
            XCirculateRequestEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XCirculateRequestEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XCirculateRequestEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XCirculateRequestEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer parent(@NativeType("Window") long j) {
            XCirculateRequestEvent.nparent(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XCirculateRequestEvent.nwindow(address(), j);
            return this;
        }

        public Buffer place(int i) {
            XCirculateRequestEvent.nplace(address(), i);
            return this;
        }
    }
}
