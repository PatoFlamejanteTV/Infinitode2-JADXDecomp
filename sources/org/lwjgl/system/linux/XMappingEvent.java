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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XMappingEvent.class */
public class XMappingEvent extends Struct<XMappingEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int REQUEST;
    public static final int FIRST_KEYCODE;
    public static final int COUNT;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        REQUEST = __struct.offsetof(5);
        FIRST_KEYCODE = __struct.offsetof(6);
        COUNT = __struct.offsetof(7);
    }

    protected XMappingEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XMappingEvent create(long j, ByteBuffer byteBuffer) {
        return new XMappingEvent(j, byteBuffer);
    }

    public XMappingEvent(ByteBuffer byteBuffer) {
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

    public int request() {
        return nrequest(address());
    }

    public int first_keycode() {
        return nfirst_keycode(address());
    }

    public int count() {
        return ncount(address());
    }

    public XMappingEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XMappingEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XMappingEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XMappingEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XMappingEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XMappingEvent request(int i) {
        nrequest(address(), i);
        return this;
    }

    public XMappingEvent first_keycode(int i) {
        nfirst_keycode(address(), i);
        return this;
    }

    public XMappingEvent count(int i) {
        ncount(address(), i);
        return this;
    }

    public XMappingEvent set(int i, long j, boolean z, long j2, long j3, int i2, int i3, int i4) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        request(i2);
        first_keycode(i3);
        count(i4);
        return this;
    }

    public XMappingEvent set(XMappingEvent xMappingEvent) {
        MemoryUtil.memCopy(xMappingEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XMappingEvent malloc() {
        return new XMappingEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XMappingEvent calloc() {
        return new XMappingEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XMappingEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XMappingEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XMappingEvent create(long j) {
        return new XMappingEvent(j, null);
    }

    public static XMappingEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XMappingEvent(j, null);
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
    public static XMappingEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XMappingEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XMappingEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XMappingEvent callocStack(MemoryStack memoryStack) {
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

    public static XMappingEvent malloc(MemoryStack memoryStack) {
        return new XMappingEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XMappingEvent calloc(MemoryStack memoryStack) {
        return new XMappingEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nrequest(long j) {
        return UNSAFE.getInt((Object) null, j + REQUEST);
    }

    public static int nfirst_keycode(long j) {
        return UNSAFE.getInt((Object) null, j + FIRST_KEYCODE);
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

    public static void nrequest(long j, int i) {
        UNSAFE.putInt((Object) null, j + REQUEST, i);
    }

    public static void nfirst_keycode(long j, int i) {
        UNSAFE.putInt((Object) null, j + FIRST_KEYCODE, i);
    }

    public static void ncount(long j, int i) {
        UNSAFE.putInt((Object) null, j + COUNT, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XMappingEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XMappingEvent, Buffer> implements NativeResource {
        private static final XMappingEvent ELEMENT_FACTORY = XMappingEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XMappingEvent.SIZEOF);
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
        public XMappingEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XMappingEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XMappingEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XMappingEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XMappingEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XMappingEvent.nwindow(address());
        }

        public int request() {
            return XMappingEvent.nrequest(address());
        }

        public int first_keycode() {
            return XMappingEvent.nfirst_keycode(address());
        }

        public int count() {
            return XMappingEvent.ncount(address());
        }

        public Buffer type(int i) {
            XMappingEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XMappingEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XMappingEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XMappingEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XMappingEvent.nwindow(address(), j);
            return this;
        }

        public Buffer request(int i) {
            XMappingEvent.nrequest(address(), i);
            return this;
        }

        public Buffer first_keycode(int i) {
            XMappingEvent.nfirst_keycode(address(), i);
            return this;
        }

        public Buffer count(int i) {
            XMappingEvent.ncount(address(), i);
            return this;
        }
    }
}
