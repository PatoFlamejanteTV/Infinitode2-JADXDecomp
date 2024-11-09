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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XMotionEvent.class */
public class XMotionEvent extends Struct<XMotionEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int ROOT;
    public static final int SUBWINDOW;
    public static final int TIME;
    public static final int X;
    public static final int Y;
    public static final int X_ROOT;
    public static final int Y_ROOT;
    public static final int STATE;
    public static final int IS_HINT;
    public static final int SAME_SCREEN;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(1), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        ROOT = __struct.offsetof(5);
        SUBWINDOW = __struct.offsetof(6);
        TIME = __struct.offsetof(7);
        X = __struct.offsetof(8);
        Y = __struct.offsetof(9);
        X_ROOT = __struct.offsetof(10);
        Y_ROOT = __struct.offsetof(11);
        STATE = __struct.offsetof(12);
        IS_HINT = __struct.offsetof(13);
        SAME_SCREEN = __struct.offsetof(14);
    }

    protected XMotionEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XMotionEvent create(long j, ByteBuffer byteBuffer) {
        return new XMotionEvent(j, byteBuffer);
    }

    public XMotionEvent(ByteBuffer byteBuffer) {
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

    @NativeType("Window")
    public long root() {
        return nroot(address());
    }

    @NativeType("Window")
    public long subwindow() {
        return nsubwindow(address());
    }

    @NativeType("Time")
    public long time() {
        return ntime(address());
    }

    public int x() {
        return nx(address());
    }

    public int y() {
        return ny(address());
    }

    public int x_root() {
        return nx_root(address());
    }

    public int y_root() {
        return ny_root(address());
    }

    @NativeType("unsigned int")
    public int state() {
        return nstate(address());
    }

    @NativeType("char")
    public byte is_hint() {
        return nis_hint(address());
    }

    @NativeType("Bool")
    public boolean same_screen() {
        return nsame_screen(address()) != 0;
    }

    public XMotionEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XMotionEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XMotionEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XMotionEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XMotionEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XMotionEvent root(@NativeType("Window") long j) {
        nroot(address(), j);
        return this;
    }

    public XMotionEvent subwindow(@NativeType("Window") long j) {
        nsubwindow(address(), j);
        return this;
    }

    public XMotionEvent time(@NativeType("Time") long j) {
        ntime(address(), j);
        return this;
    }

    public XMotionEvent x(int i) {
        nx(address(), i);
        return this;
    }

    public XMotionEvent y(int i) {
        ny(address(), i);
        return this;
    }

    public XMotionEvent x_root(int i) {
        nx_root(address(), i);
        return this;
    }

    public XMotionEvent y_root(int i) {
        ny_root(address(), i);
        return this;
    }

    public XMotionEvent state(@NativeType("unsigned int") int i) {
        nstate(address(), i);
        return this;
    }

    public XMotionEvent is_hint(@NativeType("char") byte b2) {
        nis_hint(address(), b2);
        return this;
    }

    public XMotionEvent same_screen(@NativeType("Bool") boolean z) {
        nsame_screen(address(), z ? 1 : 0);
        return this;
    }

    public XMotionEvent set(int i, long j, boolean z, long j2, long j3, long j4, long j5, long j6, int i2, int i3, int i4, int i5, int i6, byte b2, boolean z2) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        root(j4);
        subwindow(j5);
        time(j6);
        x(i2);
        y(i3);
        x_root(i4);
        y_root(i5);
        state(i6);
        is_hint(b2);
        same_screen(z2);
        return this;
    }

    public XMotionEvent set(XMotionEvent xMotionEvent) {
        MemoryUtil.memCopy(xMotionEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XMotionEvent malloc() {
        return new XMotionEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XMotionEvent calloc() {
        return new XMotionEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XMotionEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XMotionEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XMotionEvent create(long j) {
        return new XMotionEvent(j, null);
    }

    public static XMotionEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XMotionEvent(j, null);
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
    public static XMotionEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XMotionEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XMotionEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XMotionEvent callocStack(MemoryStack memoryStack) {
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

    public static XMotionEvent malloc(MemoryStack memoryStack) {
        return new XMotionEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XMotionEvent calloc(MemoryStack memoryStack) {
        return new XMotionEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long nroot(long j) {
        return MemoryUtil.memGetCLong(j + ROOT);
    }

    public static long nsubwindow(long j) {
        return MemoryUtil.memGetCLong(j + SUBWINDOW);
    }

    public static long ntime(long j) {
        return MemoryUtil.memGetCLong(j + TIME);
    }

    public static int nx(long j) {
        return UNSAFE.getInt((Object) null, j + X);
    }

    public static int ny(long j) {
        return UNSAFE.getInt((Object) null, j + Y);
    }

    public static int nx_root(long j) {
        return UNSAFE.getInt((Object) null, j + X_ROOT);
    }

    public static int ny_root(long j) {
        return UNSAFE.getInt((Object) null, j + Y_ROOT);
    }

    public static int nstate(long j) {
        return UNSAFE.getInt((Object) null, j + STATE);
    }

    public static byte nis_hint(long j) {
        return UNSAFE.getByte((Object) null, j + IS_HINT);
    }

    public static int nsame_screen(long j) {
        return UNSAFE.getInt((Object) null, j + SAME_SCREEN);
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

    public static void nroot(long j, long j2) {
        MemoryUtil.memPutCLong(j + ROOT, j2);
    }

    public static void nsubwindow(long j, long j2) {
        MemoryUtil.memPutCLong(j + SUBWINDOW, j2);
    }

    public static void ntime(long j, long j2) {
        MemoryUtil.memPutCLong(j + TIME, j2);
    }

    public static void nx(long j, int i) {
        UNSAFE.putInt((Object) null, j + X, i);
    }

    public static void ny(long j, int i) {
        UNSAFE.putInt((Object) null, j + Y, i);
    }

    public static void nx_root(long j, int i) {
        UNSAFE.putInt((Object) null, j + X_ROOT, i);
    }

    public static void ny_root(long j, int i) {
        UNSAFE.putInt((Object) null, j + Y_ROOT, i);
    }

    public static void nstate(long j, int i) {
        UNSAFE.putInt((Object) null, j + STATE, i);
    }

    public static void nis_hint(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + IS_HINT, b2);
    }

    public static void nsame_screen(long j, int i) {
        UNSAFE.putInt((Object) null, j + SAME_SCREEN, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XMotionEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XMotionEvent, Buffer> implements NativeResource {
        private static final XMotionEvent ELEMENT_FACTORY = XMotionEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XMotionEvent.SIZEOF);
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
        public XMotionEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XMotionEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XMotionEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XMotionEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XMotionEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XMotionEvent.nwindow(address());
        }

        @NativeType("Window")
        public long root() {
            return XMotionEvent.nroot(address());
        }

        @NativeType("Window")
        public long subwindow() {
            return XMotionEvent.nsubwindow(address());
        }

        @NativeType("Time")
        public long time() {
            return XMotionEvent.ntime(address());
        }

        public int x() {
            return XMotionEvent.nx(address());
        }

        public int y() {
            return XMotionEvent.ny(address());
        }

        public int x_root() {
            return XMotionEvent.nx_root(address());
        }

        public int y_root() {
            return XMotionEvent.ny_root(address());
        }

        @NativeType("unsigned int")
        public int state() {
            return XMotionEvent.nstate(address());
        }

        @NativeType("char")
        public byte is_hint() {
            return XMotionEvent.nis_hint(address());
        }

        @NativeType("Bool")
        public boolean same_screen() {
            return XMotionEvent.nsame_screen(address()) != 0;
        }

        public Buffer type(int i) {
            XMotionEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XMotionEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XMotionEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XMotionEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XMotionEvent.nwindow(address(), j);
            return this;
        }

        public Buffer root(@NativeType("Window") long j) {
            XMotionEvent.nroot(address(), j);
            return this;
        }

        public Buffer subwindow(@NativeType("Window") long j) {
            XMotionEvent.nsubwindow(address(), j);
            return this;
        }

        public Buffer time(@NativeType("Time") long j) {
            XMotionEvent.ntime(address(), j);
            return this;
        }

        public Buffer x(int i) {
            XMotionEvent.nx(address(), i);
            return this;
        }

        public Buffer y(int i) {
            XMotionEvent.ny(address(), i);
            return this;
        }

        public Buffer x_root(int i) {
            XMotionEvent.nx_root(address(), i);
            return this;
        }

        public Buffer y_root(int i) {
            XMotionEvent.ny_root(address(), i);
            return this;
        }

        public Buffer state(@NativeType("unsigned int") int i) {
            XMotionEvent.nstate(address(), i);
            return this;
        }

        public Buffer is_hint(@NativeType("char") byte b2) {
            XMotionEvent.nis_hint(address(), b2);
            return this;
        }

        public Buffer same_screen(@NativeType("Bool") boolean z) {
            XMotionEvent.nsame_screen(address(), z ? 1 : 0);
            return this;
        }
    }
}
