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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XSelectionRequestEvent.class */
public class XSelectionRequestEvent extends Struct<XSelectionRequestEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int OWNER;
    public static final int REQUESTOR;
    public static final int SELECTION;
    public static final int TARGET;
    public static final int PROPERTY;
    public static final int TIME;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        OWNER = __struct.offsetof(4);
        REQUESTOR = __struct.offsetof(5);
        SELECTION = __struct.offsetof(6);
        TARGET = __struct.offsetof(7);
        PROPERTY = __struct.offsetof(8);
        TIME = __struct.offsetof(9);
    }

    protected XSelectionRequestEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XSelectionRequestEvent create(long j, ByteBuffer byteBuffer) {
        return new XSelectionRequestEvent(j, byteBuffer);
    }

    public XSelectionRequestEvent(ByteBuffer byteBuffer) {
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
    public long owner() {
        return nowner(address());
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

    public XSelectionRequestEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XSelectionRequestEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XSelectionRequestEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XSelectionRequestEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XSelectionRequestEvent owner(@NativeType("Window") long j) {
        nowner(address(), j);
        return this;
    }

    public XSelectionRequestEvent requestor(@NativeType("Window") long j) {
        nrequestor(address(), j);
        return this;
    }

    public XSelectionRequestEvent selection(@NativeType("Atom") long j) {
        nselection(address(), j);
        return this;
    }

    public XSelectionRequestEvent target(@NativeType("Atom") long j) {
        ntarget(address(), j);
        return this;
    }

    public XSelectionRequestEvent property(@NativeType("Atom") long j) {
        nproperty(address(), j);
        return this;
    }

    public XSelectionRequestEvent time(@NativeType("Time") long j) {
        ntime(address(), j);
        return this;
    }

    public XSelectionRequestEvent set(int i, long j, boolean z, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        owner(j3);
        requestor(j4);
        selection(j5);
        target(j6);
        property(j7);
        time(j8);
        return this;
    }

    public XSelectionRequestEvent set(XSelectionRequestEvent xSelectionRequestEvent) {
        MemoryUtil.memCopy(xSelectionRequestEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XSelectionRequestEvent malloc() {
        return new XSelectionRequestEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XSelectionRequestEvent calloc() {
        return new XSelectionRequestEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XSelectionRequestEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XSelectionRequestEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XSelectionRequestEvent create(long j) {
        return new XSelectionRequestEvent(j, null);
    }

    public static XSelectionRequestEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XSelectionRequestEvent(j, null);
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
    public static XSelectionRequestEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XSelectionRequestEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XSelectionRequestEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XSelectionRequestEvent callocStack(MemoryStack memoryStack) {
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

    public static XSelectionRequestEvent malloc(MemoryStack memoryStack) {
        return new XSelectionRequestEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XSelectionRequestEvent calloc(MemoryStack memoryStack) {
        return new XSelectionRequestEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long nowner(long j) {
        return MemoryUtil.memGetCLong(j + OWNER);
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

    public static void nowner(long j, long j2) {
        MemoryUtil.memPutCLong(j + OWNER, j2);
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

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XSelectionRequestEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XSelectionRequestEvent, Buffer> implements NativeResource {
        private static final XSelectionRequestEvent ELEMENT_FACTORY = XSelectionRequestEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XSelectionRequestEvent.SIZEOF);
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
        public XSelectionRequestEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XSelectionRequestEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XSelectionRequestEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XSelectionRequestEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XSelectionRequestEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long owner() {
            return XSelectionRequestEvent.nowner(address());
        }

        @NativeType("Window")
        public long requestor() {
            return XSelectionRequestEvent.nrequestor(address());
        }

        @NativeType("Atom")
        public long selection() {
            return XSelectionRequestEvent.nselection(address());
        }

        @NativeType("Atom")
        public long target() {
            return XSelectionRequestEvent.ntarget(address());
        }

        @NativeType("Atom")
        public long property() {
            return XSelectionRequestEvent.nproperty(address());
        }

        @NativeType("Time")
        public long time() {
            return XSelectionRequestEvent.ntime(address());
        }

        public Buffer type(int i) {
            XSelectionRequestEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XSelectionRequestEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XSelectionRequestEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XSelectionRequestEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer owner(@NativeType("Window") long j) {
            XSelectionRequestEvent.nowner(address(), j);
            return this;
        }

        public Buffer requestor(@NativeType("Window") long j) {
            XSelectionRequestEvent.nrequestor(address(), j);
            return this;
        }

        public Buffer selection(@NativeType("Atom") long j) {
            XSelectionRequestEvent.nselection(address(), j);
            return this;
        }

        public Buffer target(@NativeType("Atom") long j) {
            XSelectionRequestEvent.ntarget(address(), j);
            return this;
        }

        public Buffer property(@NativeType("Atom") long j) {
            XSelectionRequestEvent.nproperty(address(), j);
            return this;
        }

        public Buffer time(@NativeType("Time") long j) {
            XSelectionRequestEvent.ntime(address(), j);
            return this;
        }
    }
}
