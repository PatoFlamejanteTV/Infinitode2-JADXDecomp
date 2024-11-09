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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XColormapEvent.class */
public class XColormapEvent extends Struct<XColormapEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int COLORMAP;
    public static final int NEW;
    public static final int STATE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        COLORMAP = __struct.offsetof(5);
        NEW = __struct.offsetof(6);
        STATE = __struct.offsetof(7);
    }

    protected XColormapEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XColormapEvent create(long j, ByteBuffer byteBuffer) {
        return new XColormapEvent(j, byteBuffer);
    }

    public XColormapEvent(ByteBuffer byteBuffer) {
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

    @NativeType("Colormap")
    public long colormap() {
        return ncolormap(address());
    }

    public int new$() {
        return nnew$(address());
    }

    public int state() {
        return nstate(address());
    }

    public XColormapEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XColormapEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XColormapEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XColormapEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XColormapEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XColormapEvent colormap(@NativeType("Colormap") long j) {
        ncolormap(address(), j);
        return this;
    }

    public XColormapEvent new$(int i) {
        nnew$(address(), i);
        return this;
    }

    public XColormapEvent state(int i) {
        nstate(address(), i);
        return this;
    }

    public XColormapEvent set(int i, long j, boolean z, long j2, long j3, long j4, int i2, int i3) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        colormap(j4);
        new$(i2);
        state(i3);
        return this;
    }

    public XColormapEvent set(XColormapEvent xColormapEvent) {
        MemoryUtil.memCopy(xColormapEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XColormapEvent malloc() {
        return new XColormapEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XColormapEvent calloc() {
        return new XColormapEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XColormapEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XColormapEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XColormapEvent create(long j) {
        return new XColormapEvent(j, null);
    }

    public static XColormapEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XColormapEvent(j, null);
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
    public static XColormapEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XColormapEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XColormapEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XColormapEvent callocStack(MemoryStack memoryStack) {
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

    public static XColormapEvent malloc(MemoryStack memoryStack) {
        return new XColormapEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XColormapEvent calloc(MemoryStack memoryStack) {
        return new XColormapEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long ncolormap(long j) {
        return MemoryUtil.memGetCLong(j + COLORMAP);
    }

    public static int nnew$(long j) {
        return UNSAFE.getInt((Object) null, j + NEW);
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

    public static void ncolormap(long j, long j2) {
        MemoryUtil.memPutCLong(j + COLORMAP, j2);
    }

    public static void nnew$(long j, int i) {
        UNSAFE.putInt((Object) null, j + NEW, i);
    }

    public static void nstate(long j, int i) {
        UNSAFE.putInt((Object) null, j + STATE, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XColormapEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XColormapEvent, Buffer> implements NativeResource {
        private static final XColormapEvent ELEMENT_FACTORY = XColormapEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XColormapEvent.SIZEOF);
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
        public XColormapEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XColormapEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XColormapEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XColormapEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XColormapEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XColormapEvent.nwindow(address());
        }

        @NativeType("Colormap")
        public long colormap() {
            return XColormapEvent.ncolormap(address());
        }

        public int new$() {
            return XColormapEvent.nnew$(address());
        }

        public int state() {
            return XColormapEvent.nstate(address());
        }

        public Buffer type(int i) {
            XColormapEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XColormapEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XColormapEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XColormapEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XColormapEvent.nwindow(address(), j);
            return this;
        }

        public Buffer colormap(@NativeType("Colormap") long j) {
            XColormapEvent.ncolormap(address(), j);
            return this;
        }

        public Buffer new$(int i) {
            XColormapEvent.nnew$(address(), i);
            return this;
        }

        public Buffer state(int i) {
            XColormapEvent.nstate(address(), i);
            return this;
        }
    }
}
