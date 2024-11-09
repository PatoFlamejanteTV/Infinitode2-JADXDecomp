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

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XErrorEvent.class */
public class XErrorEvent extends Struct<XErrorEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int DISPLAY;
    public static final int RESOURCEID;
    public static final int SERIAL;
    public static final int ERROR_CODE;
    public static final int REQUEST_CODE;
    public static final int MINOR_CODE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(1), __member(1), __member(1));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        DISPLAY = __struct.offsetof(1);
        RESOURCEID = __struct.offsetof(2);
        SERIAL = __struct.offsetof(3);
        ERROR_CODE = __struct.offsetof(4);
        REQUEST_CODE = __struct.offsetof(5);
        MINOR_CODE = __struct.offsetof(6);
    }

    protected XErrorEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XErrorEvent create(long j, ByteBuffer byteBuffer) {
        return new XErrorEvent(j, byteBuffer);
    }

    public XErrorEvent(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int type() {
        return ntype(address());
    }

    @NativeType("Display *")
    public long display() {
        return ndisplay(address());
    }

    @NativeType("XID")
    public long resourceid() {
        return nresourceid(address());
    }

    @NativeType("unsigned long")
    public long serial() {
        return nserial(address());
    }

    @NativeType("unsigned char")
    public byte error_code() {
        return nerror_code(address());
    }

    @NativeType("unsigned char")
    public byte request_code() {
        return nrequest_code(address());
    }

    @NativeType("unsigned char")
    public byte minor_code() {
        return nminor_code(address());
    }

    public XErrorEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XErrorEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XErrorEvent resourceid(@NativeType("XID") long j) {
        nresourceid(address(), j);
        return this;
    }

    public XErrorEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XErrorEvent error_code(@NativeType("unsigned char") byte b2) {
        nerror_code(address(), b2);
        return this;
    }

    public XErrorEvent request_code(@NativeType("unsigned char") byte b2) {
        nrequest_code(address(), b2);
        return this;
    }

    public XErrorEvent minor_code(@NativeType("unsigned char") byte b2) {
        nminor_code(address(), b2);
        return this;
    }

    public XErrorEvent set(int i, long j, long j2, long j3, byte b2, byte b3, byte b4) {
        type(i);
        display(j);
        resourceid(j2);
        serial(j3);
        error_code(b2);
        request_code(b3);
        minor_code(b4);
        return this;
    }

    public XErrorEvent set(XErrorEvent xErrorEvent) {
        MemoryUtil.memCopy(xErrorEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XErrorEvent malloc() {
        return new XErrorEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XErrorEvent calloc() {
        return new XErrorEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XErrorEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XErrorEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XErrorEvent create(long j) {
        return new XErrorEvent(j, null);
    }

    public static XErrorEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XErrorEvent(j, null);
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
    public static XErrorEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XErrorEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XErrorEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XErrorEvent callocStack(MemoryStack memoryStack) {
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

    public static XErrorEvent malloc(MemoryStack memoryStack) {
        return new XErrorEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XErrorEvent calloc(MemoryStack memoryStack) {
        return new XErrorEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long ndisplay(long j) {
        return MemoryUtil.memGetAddress(j + DISPLAY);
    }

    public static long nresourceid(long j) {
        return MemoryUtil.memGetCLong(j + RESOURCEID);
    }

    public static long nserial(long j) {
        return MemoryUtil.memGetCLong(j + SERIAL);
    }

    public static byte nerror_code(long j) {
        return UNSAFE.getByte((Object) null, j + ERROR_CODE);
    }

    public static byte nrequest_code(long j) {
        return UNSAFE.getByte((Object) null, j + REQUEST_CODE);
    }

    public static byte nminor_code(long j) {
        return UNSAFE.getByte((Object) null, j + MINOR_CODE);
    }

    public static void ntype(long j, int i) {
        UNSAFE.putInt((Object) null, j + TYPE, i);
    }

    public static void ndisplay(long j, long j2) {
        MemoryUtil.memPutAddress(j + DISPLAY, Checks.check(j2));
    }

    public static void nresourceid(long j, long j2) {
        MemoryUtil.memPutCLong(j + RESOURCEID, j2);
    }

    public static void nserial(long j, long j2) {
        MemoryUtil.memPutCLong(j + SERIAL, j2);
    }

    public static void nerror_code(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + ERROR_CODE, b2);
    }

    public static void nrequest_code(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + REQUEST_CODE, b2);
    }

    public static void nminor_code(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + MINOR_CODE, b2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XErrorEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XErrorEvent, Buffer> implements NativeResource {
        private static final XErrorEvent ELEMENT_FACTORY = XErrorEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XErrorEvent.SIZEOF);
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
        public XErrorEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XErrorEvent.ntype(address());
        }

        @NativeType("Display *")
        public long display() {
            return XErrorEvent.ndisplay(address());
        }

        @NativeType("XID")
        public long resourceid() {
            return XErrorEvent.nresourceid(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XErrorEvent.nserial(address());
        }

        @NativeType("unsigned char")
        public byte error_code() {
            return XErrorEvent.nerror_code(address());
        }

        @NativeType("unsigned char")
        public byte request_code() {
            return XErrorEvent.nrequest_code(address());
        }

        @NativeType("unsigned char")
        public byte minor_code() {
            return XErrorEvent.nminor_code(address());
        }

        public Buffer type(int i) {
            XErrorEvent.ntype(address(), i);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XErrorEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer resourceid(@NativeType("XID") long j) {
            XErrorEvent.nresourceid(address(), j);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XErrorEvent.nserial(address(), j);
            return this;
        }

        public Buffer error_code(@NativeType("unsigned char") byte b2) {
            XErrorEvent.nerror_code(address(), b2);
            return this;
        }

        public Buffer request_code(@NativeType("unsigned char") byte b2) {
            XErrorEvent.nrequest_code(address(), b2);
            return this;
        }

        public Buffer minor_code(@NativeType("unsigned char") byte b2) {
            XErrorEvent.nminor_code(address(), b2);
            return this;
        }
    }
}
