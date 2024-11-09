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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XSelectionEvent.class */
public class XSelectionEvent extends Struct<XSelectionEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int REQUESTOR;
    public static final int SELECTION;
    public static final int TARGET;
    public static final int PROPERTY;
    public static final int TIME;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        REQUESTOR = __struct.offsetof(4);
        SELECTION = __struct.offsetof(5);
        TARGET = __struct.offsetof(6);
        PROPERTY = __struct.offsetof(7);
        TIME = __struct.offsetof(8);
    }

    protected XSelectionEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XSelectionEvent create(long j, ByteBuffer byteBuffer) {
        return new XSelectionEvent(j, byteBuffer);
    }

    public XSelectionEvent(ByteBuffer byteBuffer) {
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
    public long requestor() {
        return nrequestor(address());
    }

    @NativeType("Atom")
    public long selection() {
        return nselection(address());
    }

    @NativeType("Atom")
    public long target() {
        return ntarget(address());
    }

    @NativeType("Atom")
    public long property() {
        return nproperty(address());
    }

    @NativeType("Time")
    public long time() {
        return ntime(address());
    }

    public XSelectionEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XSelectionEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XSelectionEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XSelectionEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XSelectionEvent requestor(@NativeType("Window") long j) {
        nrequestor(address(), j);
        return this;
    }

    public XSelectionEvent selection(@NativeType("Atom") long j) {
        nselection(address(), j);
        return this;
    }

    public XSelectionEvent target(@NativeType("Atom") long j) {
        ntarget(address(), j);
        return this;
    }

    public XSelectionEvent property(@NativeType("Atom") long j) {
        nproperty(address(), j);
        return this;
    }

    public XSelectionEvent time(@NativeType("Time") long j) {
        ntime(address(), j);
        return this;
    }

    public XSelectionEvent set(int i, long j, boolean z, long j2, long j3, long j4, long j5, long j6, long j7) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        requestor(j3);
        selection(j4);
        target(j5);
        property(j6);
        time(j7);
        return this;
    }

    public XSelectionEvent set(XSelectionEvent xSelectionEvent) {
        MemoryUtil.memCopy(xSelectionEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XSelectionEvent malloc() {
        return new XSelectionEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XSelectionEvent calloc() {
        return new XSelectionEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XSelectionEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XSelectionEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XSelectionEvent create(long j) {
        return new XSelectionEvent(j, null);
    }

    public static XSelectionEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XSelectionEvent(j, null);
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
    public static XSelectionEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XSelectionEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XSelectionEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XSelectionEvent callocStack(MemoryStack memoryStack) {
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

    public static XSelectionEvent malloc(MemoryStack memoryStack) {
        return new XSelectionEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XSelectionEvent calloc(MemoryStack memoryStack) {
        return new XSelectionEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long nrequestor(long j) {
        return MemoryUtil.memGetCLong(j + REQUESTOR);
    }

    public static long nselection(long j) {
        return MemoryUtil.memGetCLong(j + SELECTION);
    }

    public static long ntarget(long j) {
        return MemoryUtil.memGetCLong(j + TARGET);
    }

    public static long nproperty(long j) {
        return MemoryUtil.memGetCLong(j + PROPERTY);
    }

    public static long ntime(long j) {
        return MemoryUtil.memGetCLong(j + TIME);
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

    public static void nrequestor(long j, long j2) {
        MemoryUtil.memPutCLong(j + REQUESTOR, j2);
    }

    public static void nselection(long j, long j2) {
        MemoryUtil.memPutCLong(j + SELECTION, j2);
    }

    public static void ntarget(long j, long j2) {
        MemoryUtil.memPutCLong(j + TARGET, j2);
    }

    public static void nproperty(long j, long j2) {
        MemoryUtil.memPutCLong(j + PROPERTY, j2);
    }

    public static void ntime(long j, long j2) {
        MemoryUtil.memPutCLong(j + TIME, j2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XSelectionEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XSelectionEvent, Buffer> implements NativeResource {
        private static final XSelectionEvent ELEMENT_FACTORY = XSelectionEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XSelectionEvent.SIZEOF);
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
        public XSelectionEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XSelectionEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XSelectionEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XSelectionEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XSelectionEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long requestor() {
            return XSelectionEvent.nrequestor(address());
        }

        @NativeType("Atom")
        public long selection() {
            return XSelectionEvent.nselection(address());
        }

        @NativeType("Atom")
        public long target() {
            return XSelectionEvent.ntarget(address());
        }

        @NativeType("Atom")
        public long property() {
            return XSelectionEvent.nproperty(address());
        }

        @NativeType("Time")
        public long time() {
            return XSelectionEvent.ntime(address());
        }

        public Buffer type(int i) {
            XSelectionEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XSelectionEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XSelectionEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XSelectionEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer requestor(@NativeType("Window") long j) {
            XSelectionEvent.nrequestor(address(), j);
            return this;
        }

        public Buffer selection(@NativeType("Atom") long j) {
            XSelectionEvent.nselection(address(), j);
            return this;
        }

        public Buffer target(@NativeType("Atom") long j) {
            XSelectionEvent.ntarget(address(), j);
            return this;
        }

        public Buffer property(@NativeType("Atom") long j) {
            XSelectionEvent.nproperty(address(), j);
            return this;
        }

        public Buffer time(@NativeType("Time") long j) {
            XSelectionEvent.ntime(address(), j);
            return this;
        }
    }
}
