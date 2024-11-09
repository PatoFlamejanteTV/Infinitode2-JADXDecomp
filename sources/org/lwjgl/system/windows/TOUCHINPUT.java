package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/TOUCHINPUT.class */
public class TOUCHINPUT extends Struct<TOUCHINPUT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int X;
    public static final int Y;
    public static final int HSOURCE;
    public static final int DWID;
    public static final int DWFLAGS;
    public static final int DWMASK;
    public static final int DWTIME;
    public static final int DWEXTRAINFO;
    public static final int CXCONTACT;
    public static final int CYCONTACT;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(POINTER_SIZE), __member(4), __member(4), __member(4), __member(4), __member(POINTER_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        X = __struct.offsetof(0);
        Y = __struct.offsetof(1);
        HSOURCE = __struct.offsetof(2);
        DWID = __struct.offsetof(3);
        DWFLAGS = __struct.offsetof(4);
        DWMASK = __struct.offsetof(5);
        DWTIME = __struct.offsetof(6);
        DWEXTRAINFO = __struct.offsetof(7);
        CXCONTACT = __struct.offsetof(8);
        CYCONTACT = __struct.offsetof(9);
    }

    protected TOUCHINPUT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public TOUCHINPUT create(long j, ByteBuffer byteBuffer) {
        return new TOUCHINPUT(j, byteBuffer);
    }

    public TOUCHINPUT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("LONG")
    public int x() {
        return nx(address());
    }

    @NativeType("LONG")
    public int y() {
        return ny(address());
    }

    @NativeType("HANDLE")
    public long hSource() {
        return nhSource(address());
    }

    @NativeType("DWORD")
    public int dwID() {
        return ndwID(address());
    }

    @NativeType("DWORD")
    public int dwFlags() {
        return ndwFlags(address());
    }

    @NativeType("DWORD")
    public int dwMask() {
        return ndwMask(address());
    }

    @NativeType("DWORD")
    public int dwTime() {
        return ndwTime(address());
    }

    @NativeType("ULONG_PTR")
    public long dwExtraInfo() {
        return ndwExtraInfo(address());
    }

    @NativeType("DWORD")
    public int cxContact() {
        return ncxContact(address());
    }

    @NativeType("DWORD")
    public int cyContact() {
        return ncyContact(address());
    }

    public static TOUCHINPUT malloc() {
        return new TOUCHINPUT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static TOUCHINPUT calloc() {
        return new TOUCHINPUT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static TOUCHINPUT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new TOUCHINPUT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static TOUCHINPUT create(long j) {
        return new TOUCHINPUT(j, null);
    }

    public static TOUCHINPUT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new TOUCHINPUT(j, null);
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
    public static TOUCHINPUT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static TOUCHINPUT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static TOUCHINPUT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static TOUCHINPUT callocStack(MemoryStack memoryStack) {
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

    public static TOUCHINPUT malloc(MemoryStack memoryStack) {
        return new TOUCHINPUT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static TOUCHINPUT calloc(MemoryStack memoryStack) {
        return new TOUCHINPUT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nx(long j) {
        return UNSAFE.getInt((Object) null, j + X);
    }

    public static int ny(long j) {
        return UNSAFE.getInt((Object) null, j + Y);
    }

    public static long nhSource(long j) {
        return MemoryUtil.memGetAddress(j + HSOURCE);
    }

    public static int ndwID(long j) {
        return UNSAFE.getInt((Object) null, j + DWID);
    }

    public static int ndwFlags(long j) {
        return UNSAFE.getInt((Object) null, j + DWFLAGS);
    }

    public static int ndwMask(long j) {
        return UNSAFE.getInt((Object) null, j + DWMASK);
    }

    public static int ndwTime(long j) {
        return UNSAFE.getInt((Object) null, j + DWTIME);
    }

    public static long ndwExtraInfo(long j) {
        return MemoryUtil.memGetAddress(j + DWEXTRAINFO);
    }

    public static int ncxContact(long j) {
        return UNSAFE.getInt((Object) null, j + CXCONTACT);
    }

    public static int ncyContact(long j) {
        return UNSAFE.getInt((Object) null, j + CYCONTACT);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/TOUCHINPUT$Buffer.class */
    public static class Buffer extends StructBuffer<TOUCHINPUT, Buffer> implements NativeResource {
        private static final TOUCHINPUT ELEMENT_FACTORY = TOUCHINPUT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / TOUCHINPUT.SIZEOF);
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
        public TOUCHINPUT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("LONG")
        public int x() {
            return TOUCHINPUT.nx(address());
        }

        @NativeType("LONG")
        public int y() {
            return TOUCHINPUT.ny(address());
        }

        @NativeType("HANDLE")
        public long hSource() {
            return TOUCHINPUT.nhSource(address());
        }

        @NativeType("DWORD")
        public int dwID() {
            return TOUCHINPUT.ndwID(address());
        }

        @NativeType("DWORD")
        public int dwFlags() {
            return TOUCHINPUT.ndwFlags(address());
        }

        @NativeType("DWORD")
        public int dwMask() {
            return TOUCHINPUT.ndwMask(address());
        }

        @NativeType("DWORD")
        public int dwTime() {
            return TOUCHINPUT.ndwTime(address());
        }

        @NativeType("ULONG_PTR")
        public long dwExtraInfo() {
            return TOUCHINPUT.ndwExtraInfo(address());
        }

        @NativeType("DWORD")
        public int cxContact() {
            return TOUCHINPUT.ncxContact(address());
        }

        @NativeType("DWORD")
        public int cyContact() {
            return TOUCHINPUT.ncyContact(address());
        }
    }
}
