package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/KEYBDINPUT.class */
public class KEYBDINPUT extends Struct<KEYBDINPUT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int WVK;
    public static final int WSCAN;
    public static final int DWFLAGS;
    public static final int TIME;
    public static final int DWEXTRAINFO;

    static {
        Struct.Layout __struct = __struct(__member(2), __member(2), __member(4), __member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        WVK = __struct.offsetof(0);
        WSCAN = __struct.offsetof(1);
        DWFLAGS = __struct.offsetof(2);
        TIME = __struct.offsetof(3);
        DWEXTRAINFO = __struct.offsetof(4);
    }

    protected KEYBDINPUT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public KEYBDINPUT create(long j, ByteBuffer byteBuffer) {
        return new KEYBDINPUT(j, byteBuffer);
    }

    public KEYBDINPUT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("WORD")
    public short wVk() {
        return nwVk(address());
    }

    @NativeType("WORD")
    public short wScan() {
        return nwScan(address());
    }

    @NativeType("DWORD")
    public int dwFlags() {
        return ndwFlags(address());
    }

    @NativeType("DWORD")
    public int time() {
        return ntime(address());
    }

    @NativeType("ULONG_PTR")
    public long dwExtraInfo() {
        return ndwExtraInfo(address());
    }

    public KEYBDINPUT wVk(@NativeType("WORD") short s) {
        nwVk(address(), s);
        return this;
    }

    public KEYBDINPUT wScan(@NativeType("WORD") short s) {
        nwScan(address(), s);
        return this;
    }

    public KEYBDINPUT dwFlags(@NativeType("DWORD") int i) {
        ndwFlags(address(), i);
        return this;
    }

    public KEYBDINPUT time(@NativeType("DWORD") int i) {
        ntime(address(), i);
        return this;
    }

    public KEYBDINPUT dwExtraInfo(@NativeType("ULONG_PTR") long j) {
        ndwExtraInfo(address(), j);
        return this;
    }

    public KEYBDINPUT set(short s, short s2, int i, int i2, long j) {
        wVk(s);
        wScan(s2);
        dwFlags(i);
        time(i2);
        dwExtraInfo(j);
        return this;
    }

    public KEYBDINPUT set(KEYBDINPUT keybdinput) {
        MemoryUtil.memCopy(keybdinput.address(), address(), SIZEOF);
        return this;
    }

    public static KEYBDINPUT malloc() {
        return new KEYBDINPUT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static KEYBDINPUT calloc() {
        return new KEYBDINPUT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static KEYBDINPUT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new KEYBDINPUT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static KEYBDINPUT create(long j) {
        return new KEYBDINPUT(j, null);
    }

    public static KEYBDINPUT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new KEYBDINPUT(j, null);
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
    public static KEYBDINPUT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static KEYBDINPUT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static KEYBDINPUT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static KEYBDINPUT callocStack(MemoryStack memoryStack) {
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

    public static KEYBDINPUT malloc(MemoryStack memoryStack) {
        return new KEYBDINPUT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static KEYBDINPUT calloc(MemoryStack memoryStack) {
        return new KEYBDINPUT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static short nwVk(long j) {
        return UNSAFE.getShort((Object) null, j + WVK);
    }

    public static short nwScan(long j) {
        return UNSAFE.getShort((Object) null, j + WSCAN);
    }

    public static int ndwFlags(long j) {
        return UNSAFE.getInt((Object) null, j + DWFLAGS);
    }

    public static int ntime(long j) {
        return UNSAFE.getInt((Object) null, j + TIME);
    }

    public static long ndwExtraInfo(long j) {
        return MemoryUtil.memGetAddress(j + DWEXTRAINFO);
    }

    public static void nwVk(long j, short s) {
        UNSAFE.putShort((Object) null, j + WVK, s);
    }

    public static void nwScan(long j, short s) {
        UNSAFE.putShort((Object) null, j + WSCAN, s);
    }

    public static void ndwFlags(long j, int i) {
        UNSAFE.putInt((Object) null, j + DWFLAGS, i);
    }

    public static void ntime(long j, int i) {
        UNSAFE.putInt((Object) null, j + TIME, i);
    }

    public static void ndwExtraInfo(long j, long j2) {
        MemoryUtil.memPutAddress(j + DWEXTRAINFO, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/KEYBDINPUT$Buffer.class */
    public static class Buffer extends StructBuffer<KEYBDINPUT, Buffer> implements NativeResource {
        private static final KEYBDINPUT ELEMENT_FACTORY = KEYBDINPUT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / KEYBDINPUT.SIZEOF);
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
        public KEYBDINPUT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("WORD")
        public short wVk() {
            return KEYBDINPUT.nwVk(address());
        }

        @NativeType("WORD")
        public short wScan() {
            return KEYBDINPUT.nwScan(address());
        }

        @NativeType("DWORD")
        public int dwFlags() {
            return KEYBDINPUT.ndwFlags(address());
        }

        @NativeType("DWORD")
        public int time() {
            return KEYBDINPUT.ntime(address());
        }

        @NativeType("ULONG_PTR")
        public long dwExtraInfo() {
            return KEYBDINPUT.ndwExtraInfo(address());
        }

        public Buffer wVk(@NativeType("WORD") short s) {
            KEYBDINPUT.nwVk(address(), s);
            return this;
        }

        public Buffer wScan(@NativeType("WORD") short s) {
            KEYBDINPUT.nwScan(address(), s);
            return this;
        }

        public Buffer dwFlags(@NativeType("DWORD") int i) {
            KEYBDINPUT.ndwFlags(address(), i);
            return this;
        }

        public Buffer time(@NativeType("DWORD") int i) {
            KEYBDINPUT.ntime(address(), i);
            return this;
        }

        public Buffer dwExtraInfo(@NativeType("ULONG_PTR") long j) {
            KEYBDINPUT.ndwExtraInfo(address(), j);
            return this;
        }
    }
}
