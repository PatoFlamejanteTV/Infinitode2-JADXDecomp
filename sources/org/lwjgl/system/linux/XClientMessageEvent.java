package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.CLongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XClientMessageEvent.class */
public class XClientMessageEvent extends Struct<XClientMessageEvent> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int SERIAL;
    public static final int SEND_EVENT;
    public static final int DISPLAY;
    public static final int WINDOW;
    public static final int MESSAGE_TYPE;
    public static final int FORMAT;
    public static final int DATA;
    public static final int DATA_B;
    public static final int DATA_S;
    public static final int DATA_L;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(CLONG_SIZE), __member(4), __member(POINTER_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __struct(__array(1, 20), __array(2, 10), __array(CLONG_SIZE, 5)));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        SERIAL = __struct.offsetof(1);
        SEND_EVENT = __struct.offsetof(2);
        DISPLAY = __struct.offsetof(3);
        WINDOW = __struct.offsetof(4);
        MESSAGE_TYPE = __struct.offsetof(5);
        FORMAT = __struct.offsetof(6);
        DATA = __struct.offsetof(7);
        DATA_B = __struct.offsetof(8);
        DATA_S = __struct.offsetof(9);
        DATA_L = __struct.offsetof(10);
    }

    protected XClientMessageEvent(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public XClientMessageEvent create(long j, ByteBuffer byteBuffer) {
        return new XClientMessageEvent(j, byteBuffer);
    }

    public XClientMessageEvent(ByteBuffer byteBuffer) {
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

    @NativeType("Atom")
    public long message_type() {
        return nmessage_type(address());
    }

    public int format() {
        return nformat(address());
    }

    @NativeType("char[20]")
    public ByteBuffer data_b() {
        return ndata_b(address());
    }

    @NativeType("char")
    public byte data_b(int i) {
        return ndata_b(address(), i);
    }

    @NativeType("short[10]")
    public ShortBuffer data_s() {
        return ndata_s(address());
    }

    public short data_s(int i) {
        return ndata_s(address(), i);
    }

    @NativeType("long[5]")
    public CLongBuffer data_l() {
        return ndata_l(address());
    }

    public long data_l(int i) {
        return ndata_l(address(), i);
    }

    public XClientMessageEvent type(int i) {
        ntype(address(), i);
        return this;
    }

    public XClientMessageEvent serial(@NativeType("unsigned long") long j) {
        nserial(address(), j);
        return this;
    }

    public XClientMessageEvent send_event(@NativeType("Bool") boolean z) {
        nsend_event(address(), z ? 1 : 0);
        return this;
    }

    public XClientMessageEvent display(@NativeType("Display *") long j) {
        ndisplay(address(), j);
        return this;
    }

    public XClientMessageEvent window(@NativeType("Window") long j) {
        nwindow(address(), j);
        return this;
    }

    public XClientMessageEvent message_type(@NativeType("Atom") long j) {
        nmessage_type(address(), j);
        return this;
    }

    public XClientMessageEvent format(int i) {
        nformat(address(), i);
        return this;
    }

    public XClientMessageEvent data_b(@NativeType("char[20]") ByteBuffer byteBuffer) {
        ndata_b(address(), byteBuffer);
        return this;
    }

    public XClientMessageEvent data_b(int i, @NativeType("char") byte b2) {
        ndata_b(address(), i, b2);
        return this;
    }

    public XClientMessageEvent data_s(@NativeType("short[10]") ShortBuffer shortBuffer) {
        ndata_s(address(), shortBuffer);
        return this;
    }

    public XClientMessageEvent data_s(int i, short s) {
        ndata_s(address(), i, s);
        return this;
    }

    public XClientMessageEvent data_l(@NativeType("long[5]") CLongBuffer cLongBuffer) {
        ndata_l(address(), cLongBuffer);
        return this;
    }

    public XClientMessageEvent data_l(int i, long j) {
        ndata_l(address(), i, j);
        return this;
    }

    public XClientMessageEvent set(int i, long j, boolean z, long j2, long j3, long j4, int i2, ByteBuffer byteBuffer, ShortBuffer shortBuffer, CLongBuffer cLongBuffer) {
        type(i);
        serial(j);
        send_event(z);
        display(j2);
        window(j3);
        message_type(j4);
        format(i2);
        data_b(byteBuffer);
        data_s(shortBuffer);
        data_l(cLongBuffer);
        return this;
    }

    public XClientMessageEvent set(XClientMessageEvent xClientMessageEvent) {
        MemoryUtil.memCopy(xClientMessageEvent.address(), address(), SIZEOF);
        return this;
    }

    public static XClientMessageEvent malloc() {
        return new XClientMessageEvent(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static XClientMessageEvent calloc() {
        return new XClientMessageEvent(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static XClientMessageEvent create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new XClientMessageEvent(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static XClientMessageEvent create(long j) {
        return new XClientMessageEvent(j, null);
    }

    public static XClientMessageEvent createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new XClientMessageEvent(j, null);
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
    public static XClientMessageEvent mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XClientMessageEvent callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static XClientMessageEvent mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static XClientMessageEvent callocStack(MemoryStack memoryStack) {
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

    public static XClientMessageEvent malloc(MemoryStack memoryStack) {
        return new XClientMessageEvent(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static XClientMessageEvent calloc(MemoryStack memoryStack) {
        return new XClientMessageEvent(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static long nmessage_type(long j) {
        return MemoryUtil.memGetCLong(j + MESSAGE_TYPE);
    }

    public static int nformat(long j) {
        return UNSAFE.getInt((Object) null, j + FORMAT);
    }

    public static ByteBuffer ndata_b(long j) {
        return MemoryUtil.memByteBuffer(j + DATA_B, 20);
    }

    public static byte ndata_b(long j, int i) {
        return UNSAFE.getByte((Object) null, j + DATA_B + Checks.check(i, 20));
    }

    public static ShortBuffer ndata_s(long j) {
        return MemoryUtil.memShortBuffer(j + DATA_S, 10);
    }

    public static short ndata_s(long j, int i) {
        return UNSAFE.getShort((Object) null, j + DATA_S + (Checks.check(i, 10) << 1));
    }

    public static CLongBuffer ndata_l(long j) {
        return MemoryUtil.memCLongBuffer(j + DATA_L, 5);
    }

    public static long ndata_l(long j, int i) {
        return MemoryUtil.memGetCLong(j + DATA_L + (Checks.check(i, 5) * CLONG_SIZE));
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

    public static void nmessage_type(long j, long j2) {
        MemoryUtil.memPutCLong(j + MESSAGE_TYPE, j2);
    }

    public static void nformat(long j, int i) {
        UNSAFE.putInt((Object) null, j + FORMAT, i);
    }

    public static void ndata_b(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 20);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + DATA_B, byteBuffer.remaining());
    }

    public static void ndata_b(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + DATA_B + Checks.check(i, 20), b2);
    }

    public static void ndata_s(long j, ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(shortBuffer, 10);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(shortBuffer), j + DATA_S, shortBuffer.remaining() << 1);
    }

    public static void ndata_s(long j, int i, short s) {
        UNSAFE.putShort((Object) null, j + DATA_S + (Checks.check(i, 10) << 1), s);
    }

    public static void ndata_l(long j, CLongBuffer cLongBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(cLongBuffer, 5);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(cLongBuffer), j + DATA_L, cLongBuffer.remaining() * CLONG_SIZE);
    }

    public static void ndata_l(long j, int i, long j2) {
        MemoryUtil.memPutCLong(j + DATA_L + (Checks.check(i, 5) * CLONG_SIZE), j2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + DISPLAY));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/XClientMessageEvent$Buffer.class */
    public static class Buffer extends StructBuffer<XClientMessageEvent, Buffer> implements NativeResource {
        private static final XClientMessageEvent ELEMENT_FACTORY = XClientMessageEvent.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / XClientMessageEvent.SIZEOF);
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
        public XClientMessageEvent getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int type() {
            return XClientMessageEvent.ntype(address());
        }

        @NativeType("unsigned long")
        public long serial() {
            return XClientMessageEvent.nserial(address());
        }

        @NativeType("Bool")
        public boolean send_event() {
            return XClientMessageEvent.nsend_event(address()) != 0;
        }

        @NativeType("Display *")
        public long display() {
            return XClientMessageEvent.ndisplay(address());
        }

        @NativeType("Window")
        public long window() {
            return XClientMessageEvent.nwindow(address());
        }

        @NativeType("Atom")
        public long message_type() {
            return XClientMessageEvent.nmessage_type(address());
        }

        public int format() {
            return XClientMessageEvent.nformat(address());
        }

        @NativeType("char[20]")
        public ByteBuffer data_b() {
            return XClientMessageEvent.ndata_b(address());
        }

        @NativeType("char")
        public byte data_b(int i) {
            return XClientMessageEvent.ndata_b(address(), i);
        }

        @NativeType("short[10]")
        public ShortBuffer data_s() {
            return XClientMessageEvent.ndata_s(address());
        }

        public short data_s(int i) {
            return XClientMessageEvent.ndata_s(address(), i);
        }

        @NativeType("long[5]")
        public CLongBuffer data_l() {
            return XClientMessageEvent.ndata_l(address());
        }

        public long data_l(int i) {
            return XClientMessageEvent.ndata_l(address(), i);
        }

        public Buffer type(int i) {
            XClientMessageEvent.ntype(address(), i);
            return this;
        }

        public Buffer serial(@NativeType("unsigned long") long j) {
            XClientMessageEvent.nserial(address(), j);
            return this;
        }

        public Buffer send_event(@NativeType("Bool") boolean z) {
            XClientMessageEvent.nsend_event(address(), z ? 1 : 0);
            return this;
        }

        public Buffer display(@NativeType("Display *") long j) {
            XClientMessageEvent.ndisplay(address(), j);
            return this;
        }

        public Buffer window(@NativeType("Window") long j) {
            XClientMessageEvent.nwindow(address(), j);
            return this;
        }

        public Buffer message_type(@NativeType("Atom") long j) {
            XClientMessageEvent.nmessage_type(address(), j);
            return this;
        }

        public Buffer format(int i) {
            XClientMessageEvent.nformat(address(), i);
            return this;
        }

        public Buffer data_b(@NativeType("char[20]") ByteBuffer byteBuffer) {
            XClientMessageEvent.ndata_b(address(), byteBuffer);
            return this;
        }

        public Buffer data_b(int i, @NativeType("char") byte b2) {
            XClientMessageEvent.ndata_b(address(), i, b2);
            return this;
        }

        public Buffer data_s(@NativeType("short[10]") ShortBuffer shortBuffer) {
            XClientMessageEvent.ndata_s(address(), shortBuffer);
            return this;
        }

        public Buffer data_s(int i, short s) {
            XClientMessageEvent.ndata_s(address(), i, s);
            return this;
        }

        public Buffer data_l(@NativeType("long[5]") CLongBuffer cLongBuffer) {
            XClientMessageEvent.ndata_l(address(), cLongBuffer);
            return this;
        }

        public Buffer data_l(int i, long j) {
            XClientMessageEvent.ndata_l(address(), i, j);
            return this;
        }
    }
}
