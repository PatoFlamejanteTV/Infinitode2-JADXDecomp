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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XCirculateEvent.class */
public class XCirculateEvent extends Struct<XCirculateEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int EVENT;
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
        EVENT = __struct.offsetof(4);
        WINDOW = __struct.offsetof(5);
        PLACE = __struct.offsetof(6);
    }

    protected XCirculateEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XCirculateEvent create(long j, ByteBuffer byteBuffer) {
        return new XCirculateEvent(j, byteBuffer);
    }

    public XCirculateEvent(ByteBuffer byteBuffer) {
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
    public long event() {
        return nevent(address());
    }

    @NativeType("Window")
    public long window() {
        return nwindow(address());
    }

    public int place() {
        return nplace(address());
    }

    public XCirculateEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XCirculateEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XCirculateEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XCirculateEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XCirculateEvent event(@NativeType("Window") long j) {
        nevent(address(), j);
        return this;
    }

    public XCirculateEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XCirculateEvent place(int i) {
        nplace(address(), i);
        return this;
    }

    public XCirculateEvent set(int i, long j, boolean z, long j2, long j3, long j4, int i2) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        event(j3);
        window(j4);
        place(i2);
        return this;
    }

    public XCirculateEvent set(XCirculateEvent xCirculateEvent) {
        MemoryUtil.memCopy(xCirculateEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XCirculateEvent malloc() {
        return new XCirculateEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XCirculateEvent calloc() {
        return new XCirculateEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XCirculateEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XCirculateEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XCirculateEvent create(long j) {
        return new XCirculateEvent(j, null);
    }

    public static XCirculateEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XCirculateEvent(j, null);
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
    public static XCirculateEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XCirculateEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XCirculateEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XCirculateEvent callocStack(MemoryStack memoryStack) {
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

    public static XCirculateEvent malloc(MemoryStack memoryStack) {
        return new XCirculateEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XCirculateEvent calloc(MemoryStack memoryStack) {
        return new XCirculateEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long nevent(long j) {
        return MemoryUtil.memGetCLong(j + EVENT);
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

    public static void nevent(long j, long j2) {
        MemoryUtil.memPutCLong(j + EVENT, j2);
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

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XCirculateEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XCirculateEvent, Buffer> implements NativeResource {
        private static final XCirculateEvent ELEMENT_FACTORY = XCirculateEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XCirculateEvent.SIZEOF);
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
        public XCirculateEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XCirculateEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XCirculateEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XCirculateEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XCirculateEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long event() {
            return XCirculateEvent.nevent(address());
        }

        @NativeType("Window")
        public long window() {
            return XCirculateEvent.nwindow(address());
        }

        public int place() {
            return XCirculateEvent.nplace(address());
        }

        public Buffer type(int i) {
            XCirculateEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XCirculateEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XCirculateEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XCirculateEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer event(@NativeType("Window") long j) {
            XCirculateEvent.nevent(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XCirculateEvent.nwindow(address(), j);
            return this;
        }

        public Buffer place(int i) {
            XCirculateEvent.nplace(address(), i);
            return this;
        }
    }
}
