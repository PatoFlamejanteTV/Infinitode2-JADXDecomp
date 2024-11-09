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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XPropertyEvent.class */
public class XPropertyEvent extends Struct<XPropertyEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int ATOM;
    public static final int TIME;
    public static final int STATE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        ATOM = __struct.offsetof(5);
        TIME = __struct.offsetof(6);
        STATE = __struct.offsetof(7);
    }

    protected XPropertyEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XPropertyEvent create(long j, ByteBuffer byteBuffer) {
        return new XPropertyEvent(j, byteBuffer);
    }

    public XPropertyEvent(ByteBuffer byteBuffer) {
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

    @NativeType("Atom")
    public long atom() {
        return natom(address());
    }

    @NativeType("Time")
    public long time() {
        return ntime(address());
    }

    public int state() {
        return nstate(address());
    }

    public XPropertyEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XPropertyEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XPropertyEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XPropertyEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XPropertyEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XPropertyEvent atom(@NativeType("Atom") long j) {
        natom(address(), j);
        return this;
    }

    public XPropertyEvent time(@NativeType("Time") long j) {
        ntime(address(), j);
        return this;
    }

    public XPropertyEvent state(int i) {
        nstate(address(), i);
        return this;
    }

    public XPropertyEvent set(int i, long j, boolean z, long j2, long j3, long j4, long j5, int i2) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        atom(j4);
        time(j5);
        state(i2);
        return this;
    }

    public XPropertyEvent set(XPropertyEvent xPropertyEvent) {
        MemoryUtil.memCopy(xPropertyEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XPropertyEvent malloc() {
        return new XPropertyEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XPropertyEvent calloc() {
        return new XPropertyEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XPropertyEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XPropertyEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XPropertyEvent create(long j) {
        return new XPropertyEvent(j, null);
    }

    public static XPropertyEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XPropertyEvent(j, null);
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
    public static XPropertyEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XPropertyEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XPropertyEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XPropertyEvent callocStack(MemoryStack memoryStack) {
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

    public static XPropertyEvent malloc(MemoryStack memoryStack) {
        return new XPropertyEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XPropertyEvent calloc(MemoryStack memoryStack) {
        return new XPropertyEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long natom(long j) {
        return MemoryUtil.memGetCLong(j + ATOM);
    }

    public static long ntime(long j) {
        return MemoryUtil.memGetCLong(j + TIME);
    }

    public static int nstate(long j) {
        return UNSAFE.getInt((Object) null, j + STATE);
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

    public static void natom(long j, long j2) {
        MemoryUtil.memPutCLong(j + ATOM, j2);
    }

    public static void ntime(long j, long j2) {
        MemoryUtil.memPutCLong(j + TIME, j2);
    }

    public static void nstate(long j, int i) {
        UNSAFE.putInt((Object) null, j + STATE, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XPropertyEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XPropertyEvent, Buffer> implements NativeResource {
        private static final XPropertyEvent ELEMENT_FACTORY = XPropertyEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XPropertyEvent.SIZEOF);
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
        public XPropertyEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XPropertyEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XPropertyEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XPropertyEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XPropertyEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XPropertyEvent.nwindow(address());
        }

        @NativeType("Atom")
        public long atom() {
            return XPropertyEvent.natom(address());
        }

        @NativeType("Time")
        public long time() {
            return XPropertyEvent.ntime(address());
        }

        public int state() {
            return XPropertyEvent.nstate(address());
        }

        public Buffer type(int i) {
            XPropertyEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XPropertyEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XPropertyEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XPropertyEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XPropertyEvent.nwindow(address(), j);
            return this;
        }

        public Buffer atom(@NativeType("Atom") long j) {
            XPropertyEvent.natom(address(), j);
            return this;
        }

        public Buffer time(@NativeType("Time") long j) {
            XPropertyEvent.ntime(address(), j);
            return this;
        }

        public Buffer state(int i) {
            XPropertyEvent.nstate(address(), i);
            return this;
        }
    }
}
