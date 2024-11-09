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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGravityEvent.class */
public class XGravityEvent extends Struct<XGravityEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int EVENT;
    public static final int WINDOW;
    public static final int X;
    public static final int Y;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        EVENT = __struct.offsetof(4);
        WINDOW = __struct.offsetof(5);
        X = __struct.offsetof(6);
        Y = __struct.offsetof(7);
    }

    protected XGravityEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XGravityEvent create(long j, ByteBuffer byteBuffer) {
        return new XGravityEvent(j, byteBuffer);
    }

    public XGravityEvent(ByteBuffer byteBuffer) {
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

    public int x() {
        return nx(address());
    }

    public int y() {
        return ny(address());
    }

    public XGravityEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XGravityEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XGravityEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XGravityEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XGravityEvent event(@NativeType("Window") long j) {
        nevent(address(), j);
        return this;
    }

    public XGravityEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XGravityEvent x(int i) {
        nx(address(), i);
        return this;
    }

    public XGravityEvent y(int i) {
        ny(address(), i);
        return this;
    }

    public XGravityEvent set(int i, long j, boolean z, long j2, long j3, long j4, int i2, int i3) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        event(j3);
        window(j4);
        x(i2);
        y(i3);
        return this;
    }

    public XGravityEvent set(XGravityEvent xGravityEvent) {
        MemoryUtil.memCopy(xGravityEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XGravityEvent malloc() {
        return new XGravityEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XGravityEvent calloc() {
        return new XGravityEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XGravityEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XGravityEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XGravityEvent create(long j) {
        return new XGravityEvent(j, null);
    }

    public static XGravityEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XGravityEvent(j, null);
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
    public static XGravityEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGravityEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGravityEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XGravityEvent callocStack(MemoryStack memoryStack) {
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

    public static XGravityEvent malloc(MemoryStack memoryStack) {
        return new XGravityEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XGravityEvent calloc(MemoryStack memoryStack) {
        return new XGravityEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nx(long j) {
        return UNSAFE.getInt((Object) null, j + X);
    }

    public static int ny(long j) {
        return UNSAFE.getInt((Object) null, j + Y);
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

    public static void nx(long j, int i) {
        UNSAFE.putInt((Object) null, j + X, i);
    }

    public static void ny(long j, int i) {
        UNSAFE.putInt((Object) null, j + Y, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGravityEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XGravityEvent, Buffer> implements NativeResource {
        private static final XGravityEvent ELEMENT_FACTORY = XGravityEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XGravityEvent.SIZEOF);
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
        public XGravityEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XGravityEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XGravityEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XGravityEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XGravityEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long event() {
            return XGravityEvent.nevent(address());
        }

        @NativeType("Window")
        public long window() {
            return XGravityEvent.nwindow(address());
        }

        public int x() {
            return XGravityEvent.nx(address());
        }

        public int y() {
            return XGravityEvent.ny(address());
        }

        public Buffer type(int i) {
            XGravityEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XGravityEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XGravityEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XGravityEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer event(@NativeType("Window") long j) {
            XGravityEvent.nevent(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XGravityEvent.nwindow(address(), j);
            return this;
        }

        public Buffer x(int i) {
            XGravityEvent.nx(address(), i);
            return this;
        }

        public Buffer y(int i) {
            XGravityEvent.ny(address(), i);
            return this;
        }
    }
}
