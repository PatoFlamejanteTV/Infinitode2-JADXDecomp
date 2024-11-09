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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XKeymapEvent.class */
public class XKeymapEvent extends Struct<XKeymapEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int KEY_VECTOR;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __array(1, 32));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        KEY_VECTOR = __struct.offsetof(5);
    }

    protected XKeymapEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XKeymapEvent create(long j, ByteBuffer byteBuffer) {
        return new XKeymapEvent(j, byteBuffer);
    }

    public XKeymapEvent(ByteBuffer byteBuffer) {
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

    @NativeType("char[32]")
    public ByteBuffer key_vector() {
        return nkey_vector(address());
    }

    @NativeType("char")
    public byte key_vector(int i) {
        return nkey_vector(address(), i);
    }

    public XKeymapEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XKeymapEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XKeymapEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XKeymapEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XKeymapEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XKeymapEvent key_vector(@NativeType("char[32]") ByteBuffer byteBuffer) {
        nkey_vector(address(), byteBuffer);
        return this;
    }

    public XKeymapEvent key_vector(int i, @NativeType("char") byte b2) {
        nkey_vector(address(), i, b2);
        return this;
    }

    public XKeymapEvent set(int i, long j, boolean z, long j2, long j3, ByteBuffer byteBuffer) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        key_vector(byteBuffer);
        return this;
    }

    public XKeymapEvent set(XKeymapEvent xKeymapEvent) {
        MemoryUtil.memCopy(xKeymapEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XKeymapEvent malloc() {
        return new XKeymapEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XKeymapEvent calloc() {
        return new XKeymapEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XKeymapEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XKeymapEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XKeymapEvent create(long j) {
        return new XKeymapEvent(j, null);
    }

    public static XKeymapEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XKeymapEvent(j, null);
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
    public static XKeymapEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XKeymapEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XKeymapEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XKeymapEvent callocStack(MemoryStack memoryStack) {
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

    public static XKeymapEvent malloc(MemoryStack memoryStack) {
        return new XKeymapEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XKeymapEvent calloc(MemoryStack memoryStack) {
        return new XKeymapEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static ByteBuffer nkey_vector(long j) {
        return MemoryUtil.memByteBuffer(j + KEY_VECTOR, 32);
    }

    public static byte nkey_vector(long j, int i) {
        return UNSAFE.getByte((Object) null, j + KEY_VECTOR + Checks.check(i, 32));
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

    public static void nkey_vector(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 32);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + KEY_VECTOR, byteBuffer.remaining());
    }

    public static void nkey_vector(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + KEY_VECTOR + Checks.check(i, 32), b2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XKeymapEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XKeymapEvent, Buffer> implements NativeResource {
        private static final XKeymapEvent ELEMENT_FACTORY = XKeymapEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XKeymapEvent.SIZEOF);
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
        public XKeymapEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XKeymapEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XKeymapEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XKeymapEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XKeymapEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XKeymapEvent.nwindow(address());
        }

        @NativeType("char[32]")
        public ByteBuffer key_vector() {
            return XKeymapEvent.nkey_vector(address());
        }

        @NativeType("char")
        public byte key_vector(int i) {
            return XKeymapEvent.nkey_vector(address(), i);
        }

        public Buffer type(int i) {
            XKeymapEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XKeymapEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XKeymapEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XKeymapEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XKeymapEvent.nwindow(address(), j);
            return this;
        }

        public Buffer key_vector(@NativeType("char[32]") ByteBuffer byteBuffer) {
            XKeymapEvent.nkey_vector(address(), byteBuffer);
            return this;
        }

        public Buffer key_vector(int i, @NativeType("char") byte b2) {
            XKeymapEvent.nkey_vector(address(), i, b2);
            return this;
        }
    }
}
