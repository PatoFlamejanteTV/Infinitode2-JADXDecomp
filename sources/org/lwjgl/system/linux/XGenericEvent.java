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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGenericEvent.class */
public class XGenericEvent extends Struct<XGenericEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int EXTENSION;
    public static final int EVTYPE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        EXTENSION = __struct.offsetof(4);
        EVTYPE = __struct.offsetof(5);
    }

    protected XGenericEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XGenericEvent create(long j, ByteBuffer byteBuffer) {
        return new XGenericEvent(j, byteBuffer);
    }

    public XGenericEvent(ByteBuffer byteBuffer) {
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

    public XGenericEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XGenericEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XGenericEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XGenericEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XGenericEvent extension(int i) {
        nextension(address(), i);
        return this;
    }

    public XGenericEvent evtype(int i) {
        nevtype(address(), i);
        return this;
    }

    public XGenericEvent set(int i, long j, boolean z, long j2, int i2, int i3) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        extension(i2);
        evtype(i3);
        return this;
    }

    public XGenericEvent set(XGenericEvent xGenericEvent) {
        MemoryUtil.memCopy(xGenericEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XGenericEvent malloc() {
        return new XGenericEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XGenericEvent calloc() {
        return new XGenericEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XGenericEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XGenericEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XGenericEvent create(long j) {
        return new XGenericEvent(j, null);
    }

    public static XGenericEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XGenericEvent(j, null);
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
    public static XGenericEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGenericEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XGenericEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XGenericEvent callocStack(MemoryStack memoryStack) {
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

    public static XGenericEvent malloc(MemoryStack memoryStack) {
        return new XGenericEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XGenericEvent calloc(MemoryStack memoryStack) {
        return new XGenericEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XGenericEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XGenericEvent, Buffer> implements NativeResource {
        private static final XGenericEvent ELEMENT_FACTORY = XGenericEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XGenericEvent.SIZEOF);
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
        public XGenericEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XGenericEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XGenericEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XGenericEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XGenericEvent.ndisplay(address());
        }

        public int extension() {
            return XGenericEvent.nextension(address());
        }

        public int evtype() {
            return XGenericEvent.nevtype(address());
        }

        public Buffer type(int i) {
            XGenericEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XGenericEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XGenericEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XGenericEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer extension(int i) {
            XGenericEvent.nextension(address(), i);
            return this;
        }

        public Buffer evtype(int i) {
            XGenericEvent.nevtype(address(), i);
            return this;
        }
    }
}
