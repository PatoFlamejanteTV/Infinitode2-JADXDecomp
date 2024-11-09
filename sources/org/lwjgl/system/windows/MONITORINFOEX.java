package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/MONITORINFOEX.class */
public class MONITORINFOEX extends Struct<MONITORINFOEX> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CBSIZE;
    public static final int RCMONITOR;
    public static final int RCWORK;
    public static final int DWFLAGS;
    public static final int SZDEVICE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(RECT.SIZEOF, RECT.ALIGNOF), __member(RECT.SIZEOF, RECT.ALIGNOF), __member(4), __array(2, 32));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        CBSIZE = __struct.offsetof(0);
        RCMONITOR = __struct.offsetof(1);
        RCWORK = __struct.offsetof(2);
        DWFLAGS = __struct.offsetof(3);
        SZDEVICE = __struct.offsetof(4);
    }

    protected MONITORINFOEX(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public MONITORINFOEX create(long j, ByteBuffer byteBuffer) {
        return new MONITORINFOEX(j, byteBuffer);
    }

    public MONITORINFOEX(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int cbSize() {
        return ncbSize(address());
    }

    public RECT rcMonitor() {
        return nrcMonitor(address());
    }

    public RECT rcWork() {
        return nrcWork(address());
    }

    @NativeType("DWORD")
    public int dwFlags() {
        return ndwFlags(address());
    }

    @NativeType("TCHAR[32]")
    public ByteBuffer szDevice() {
        return nszDevice(address());
    }

    @NativeType("TCHAR[32]")
    public String szDeviceString() {
        return nszDeviceString(address());
    }

    public MONITORINFOEX cbSize(@NativeType("DWORD") int i) {
        ncbSize(address(), i);
        return this;
    }

    public MONITORINFOEX set(MONITORINFOEX monitorinfoex) {
        MemoryUtil.memCopy(monitorinfoex.address(), address(), SIZEOF);
        return this;
    }

    public static MONITORINFOEX malloc() {
        return new MONITORINFOEX(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static MONITORINFOEX calloc() {
        return new MONITORINFOEX(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static MONITORINFOEX create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new MONITORINFOEX(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static MONITORINFOEX create(long j) {
        return new MONITORINFOEX(j, null);
    }

    public static MONITORINFOEX createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new MONITORINFOEX(j, null);
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
    public static MONITORINFOEX mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static MONITORINFOEX callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static MONITORINFOEX mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static MONITORINFOEX callocStack(MemoryStack memoryStack) {
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

    public static MONITORINFOEX malloc(MemoryStack memoryStack) {
        return new MONITORINFOEX(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static MONITORINFOEX calloc(MemoryStack memoryStack) {
        return new MONITORINFOEX(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ncbSize(long j) {
        return UNSAFE.getInt((Object) null, j + CBSIZE);
    }

    public static RECT nrcMonitor(long j) {
        return RECT.create(j + RCMONITOR);
    }

    public static RECT nrcWork(long j) {
        return RECT.create(j + RCWORK);
    }

    public static int ndwFlags(long j) {
        return UNSAFE.getInt((Object) null, j + DWFLAGS);
    }

    public static ByteBuffer nszDevice(long j) {
        return MemoryUtil.memByteBuffer(j + SZDEVICE, 64);
    }

    public static String nszDeviceString(long j) {
        return MemoryUtil.memUTF16(j + SZDEVICE);
    }

    public static void ncbSize(long j, int i) {
        UNSAFE.putInt((Object) null, j + CBSIZE, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/MONITORINFOEX$Buffer.class */
    public static class Buffer extends StructBuffer<MONITORINFOEX, Buffer> implements NativeResource {
        private static final MONITORINFOEX ELEMENT_FACTORY = MONITORINFOEX.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / MONITORINFOEX.SIZEOF);
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
        public MONITORINFOEX getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int cbSize() {
            return MONITORINFOEX.ncbSize(address());
        }

        public RECT rcMonitor() {
            return MONITORINFOEX.nrcMonitor(address());
        }

        public RECT rcWork() {
            return MONITORINFOEX.nrcWork(address());
        }

        @NativeType("DWORD")
        public int dwFlags() {
            return MONITORINFOEX.ndwFlags(address());
        }

        @NativeType("TCHAR[32]")
        public ByteBuffer szDevice() {
            return MONITORINFOEX.nszDevice(address());
        }

        @NativeType("TCHAR[32]")
        public String szDeviceString() {
            return MONITORINFOEX.nszDeviceString(address());
        }

        public Buffer cbSize(@NativeType("DWORD") int i) {
            MONITORINFOEX.ncbSize(address(), i);
            return this;
        }
    }
}
