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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XNoExposeEvent.class */
public class XNoExposeEvent extends Struct<XNoExposeEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int DRAWABLE;
    public static final int MAJOR_CODE;
    public static final int MINOR_CODE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        DRAWABLE = __struct.offsetof(4);
        MAJOR_CODE = __struct.offsetof(5);
        MINOR_CODE = __struct.offsetof(6);
    }

    protected XNoExposeEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XNoExposeEvent create(long j, ByteBuffer byteBuffer) {
        return new XNoExposeEvent(j, byteBuffer);
    }

    public XNoExposeEvent(ByteBuffer byteBuffer) {
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

    @NativeType("Drawable")
    public long drawable() {
        return ndrawable(address());
    }

    public int major_code() {
        return nmajor_code(address());
    }

    public int minor_code() {
        return nminor_code(address());
    }

    public XNoExposeEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XNoExposeEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XNoExposeEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XNoExposeEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XNoExposeEvent drawable(@NativeType("Drawable") long j) {
        ndrawable(address(), j);
        return this;
    }

    public XNoExposeEvent major_code(int i) {
        nmajor_code(address(), i);
        return this;
    }

    public XNoExposeEvent minor_code(int i) {
        nminor_code(address(), i);
        return this;
    }

    public XNoExposeEvent set(int i, long j, boolean z, long j2, long j3, int i2, int i3) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        drawable(j3);
        major_code(i2);
        minor_code(i3);
        return this;
    }

    public XNoExposeEvent set(XNoExposeEvent xNoExposeEvent) {
        MemoryUtil.memCopy(xNoExposeEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XNoExposeEvent malloc() {
        return new XNoExposeEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XNoExposeEvent calloc() {
        return new XNoExposeEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XNoExposeEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XNoExposeEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XNoExposeEvent create(long j) {
        return new XNoExposeEvent(j, null);
    }

    public static XNoExposeEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XNoExposeEvent(j, null);
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
    public static XNoExposeEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XNoExposeEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XNoExposeEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XNoExposeEvent callocStack(MemoryStack memoryStack) {
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

    public static XNoExposeEvent malloc(MemoryStack memoryStack) {
        return new XNoExposeEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XNoExposeEvent calloc(MemoryStack memoryStack) {
        return new XNoExposeEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long ndrawable(long j) {
        return MemoryUtil.memGetCLong(j + DRAWABLE);
    }

    public static int nmajor_code(long j) {
        return UNSAFE.getInt((Object) null, j + MAJOR_CODE);
    }

    public static int nminor_code(long j) {
        return UNSAFE.getInt((Object) null, j + MINOR_CODE);
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

    public static void ndrawable(long j, long j2) {
        MemoryUtil.memPutCLong(j + DRAWABLE, j2);
    }

    public static void nmajor_code(long j, int i) {
        UNSAFE.putInt((Object) null, j + MAJOR_CODE, i);
    }

    public static void nminor_code(long j, int i) {
        UNSAFE.putInt((Object) null, j + MINOR_CODE, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XNoExposeEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XNoExposeEvent, Buffer> implements NativeResource {
        private static final XNoExposeEvent ELEMENT_FACTORY = XNoExposeEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XNoExposeEvent.SIZEOF);
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
        public XNoExposeEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XNoExposeEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XNoExposeEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XNoExposeEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XNoExposeEvent.ndisplay(address());
        }

        @NativeType("Drawable")
        public long drawable() {
            return XNoExposeEvent.ndrawable(address());
        }

        public int major_code() {
            return XNoExposeEvent.nmajor_code(address());
        }

        public int minor_code() {
            return XNoExposeEvent.nminor_code(address());
        }

        public Buffer type(int i) {
            XNoExposeEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XNoExposeEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XNoExposeEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XNoExposeEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer drawable(@NativeType("Drawable") long j) {
            XNoExposeEvent.ndrawable(address(), j);
            return this;
        }

        public Buffer major_code(int i) {
            XNoExposeEvent.nmajor_code(address(), i);
            return this;
        }

        public Buffer minor_code(int i) {
            XNoExposeEvent.nminor_code(address(), i);
            return this;
        }
    }
}
