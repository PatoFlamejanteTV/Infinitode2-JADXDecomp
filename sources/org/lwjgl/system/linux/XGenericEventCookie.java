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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGenericEventCookie.class */
public class XGenericEventCookie extends Struct<XGenericEventCookie> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int EXTENSION;
    public static final int EVTYPE;
    public static final int COOKIE;
    public static final int DATA;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(4), __member(4), __member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        EXTENSION = __struct.offsetof(4);
        EVTYPE = __struct.offsetof(5);
        COOKIE = __struct.offsetof(6);
        DATA = __struct.offsetof(7);
    }

    protected XGenericEventCookie(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XGenericEventCookie create(long j, ByteBuffer byteBuffer) {
        return new XGenericEventCookie(j, byteBuffer);
    }

    public XGenericEventCookie(ByteBuffer byteBuffer) {
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

    public int extension() {
        return nextension(address());
    }

    public int evtype() {
        return nevtype(address());
    }

    @NativeType("unsigned int")
    public int cookie() {
        return ncookie(address());
    }

    @NativeType("void *")
    public ByteBuffer data(int i) {
        return ndata(address(), i);
    }

    public XGenericEventCookie type(int i) {
        ntype(address(), i);
        return this;
    }

    public XGenericEventCookie serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XGenericEventCookie send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XGenericEventCookie display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XGenericEventCookie extension(int i) {
        nextension(address(), i);
        return this;
    }

    public XGenericEventCookie evtype(int i) {
        nevtype(address(), i);
        return this;
    }

    public XGenericEventCookie cookie(@NativeType("unsigned int") int i) {
        ncookie(address(), i);
        return this;
    }

    public XGenericEventCookie data(@NativeType("void *") ByteBuffer byteBuffer) {
        ndata(address(), byteBuffer);
        return this;
    }

    public XGenericEventCookie set(int i, long j, boolean z, long j2, int i2, int i3, int i4, ByteBuffer byteBuffer) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        extension(i2);
        evtype(i3);
        cookie(i4);
        data(byteBuffer);
        return this;
    }

    public XGenericEventCookie set(XGenericEventCookie xGenericEventCookie) {
        MemoryUtil.memCopy(xGenericEventCookie.address(), address(), SIZEOF);
        return this;
    }

    public static XGenericEventCookie malloc() {
        return new XGenericEventCookie(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XGenericEventCookie calloc() {
        return new XGenericEventCookie(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XGenericEventCookie create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XGenericEventCookie(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XGenericEventCookie create(long j) {
        return new XGenericEventCookie(j, null);
    }

    public static XGenericEventCookie createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XGenericEventCookie(j, null);
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
    public static XGenericEventCookie mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGenericEventCookie callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGenericEventCookie mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XGenericEventCookie callocStack(MemoryStack memoryStack) {
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

    public static XGenericEventCookie malloc(MemoryStack memoryStack) {
        return new XGenericEventCookie(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XGenericEventCookie calloc(MemoryStack memoryStack) {
        return new XGenericEventCookie(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nextension(long j) {
        return UNSAFE.getInt((Object) null, j + EXTENSION);
    }

    public static int nevtype(long j) {
        return UNSAFE.getInt((Object) null, j + EVTYPE);
    }

    public static int ncookie(long j) {
        return UNSAFE.getInt((Object) null, j + COOKIE);
    }

    public static ByteBuffer ndata(long j, int i) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + DATA), i);
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

    public static void nextension(long j, int i) {
        UNSAFE.putInt((Object) null, j + EXTENSION, i);
    }

    public static void nevtype(long j, int i) {
        UNSAFE.putInt((Object) null, j + EVTYPE, i);
    }

    public static void ncookie(long j, int i) {
        UNSAFE.putInt((Object) null, j + COOKIE, i);
    }

    public static void ndata(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + DATA, MemoryUtil.memAddress(byteBuffer));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
        Checks.check(MemoryUtil.memGetAddress(j + DATA));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGenericEventCookie$Buffer.class */
    public static class Buffer extends StructBuffer<XGenericEventCookie, Buffer> implements NativeResource {
        private static final XGenericEventCookie ELEMENT_FACTORY = XGenericEventCookie.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XGenericEventCookie.SIZEOF);
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
        public XGenericEventCookie getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XGenericEventCookie.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XGenericEventCookie.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XGenericEventCookie.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XGenericEventCookie.ndisplay(address());
        }

        public int extension() {
            return XGenericEventCookie.nextension(address());
        }

        public int evtype() {
            return XGenericEventCookie.nevtype(address());
        }

        @NativeType("unsigned int")
        public int cookie() {
            return XGenericEventCookie.ncookie(address());
        }

        @NativeType("void *")
        public ByteBuffer data(int i) {
            return XGenericEventCookie.ndata(address(), i);
        }

        public Buffer type(int i) {
            XGenericEventCookie.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XGenericEventCookie.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XGenericEventCookie.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XGenericEventCookie.ndisplay(address(), j);
            return this;
        }

        public Buffer extension(int i) {
            XGenericEventCookie.nextension(address(), i);
            return this;
        }

        public Buffer evtype(int i) {
            XGenericEventCookie.nevtype(address(), i);
            return this;
        }

        public Buffer cookie(@NativeType("unsigned int") int i) {
            XGenericEventCookie.ncookie(address(), i);
            return this;
        }

        public Buffer data(@NativeType("void *") ByteBuffer byteBuffer) {
            XGenericEventCookie.ndata(address(), byteBuffer);
            return this;
        }
    }
}
