package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/MOUSEINPUT.class */
public class MOUSEINPUT extends Struct<MOUSEINPUT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int DX;
    public static final int DY;
    public static final int MOUSEDATA;
    public static final int DWFLAGS;
    public static final int TIME;
    public static final int DWEXTRAINFO;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        DX = __struct.offsetof(0);
        DY = __struct.offsetof(1);
        MOUSEDATA = __struct.offsetof(2);
        DWFLAGS = __struct.offsetof(3);
        TIME = __struct.offsetof(4);
        DWEXTRAINFO = __struct.offsetof(5);
    }

    protected MOUSEINPUT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public MOUSEINPUT create(long j, ByteBuffer byteBuffer) {
        return new MOUSEINPUT(j, byteBuffer);
    }

    public MOUSEINPUT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("LONG")
    public int dx() {
        return ndx(address());
    }

    @NativeType("LONG")
    public int dy() {
        return ndy(address());
    }

    @NativeType("DWORD")
    public int mouseData() {
        return nmouseData(address());
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

    public MOUSEINPUT dx(@NativeType("LONG") int i) {
        ndx(address(), i);
        return this;
    }

    public MOUSEINPUT dy(@NativeType("LONG") int i) {
        ndy(address(), i);
        return this;
    }

    public MOUSEINPUT mouseData(@NativeType("DWORD") int i) {
        nmouseData(address(), i);
        return this;
    }

    public MOUSEINPUT dwFlags(@NativeType("DWORD") int i) {
        ndwFlags(address(), i);
        return this;
    }

    public MOUSEINPUT time(@NativeType("DWORD") int i) {
        ntime(address(), i);
        return this;
    }

    public MOUSEINPUT dwExtraInfo(@NativeType("ULONG_PTR") long j) {
        ndwExtraInfo(address(), j);
        return this;
    }

    public MOUSEINPUT set(int i, int i2, int i3, int i4, int i5, long j) {
        dx(i);
        dy(i2);
        mouseData(i3);
        dwFlags(i4);
        time(i5);
        dwExtraInfo(j);
        return this;
    }

    public MOUSEINPUT set(MOUSEINPUT mouseinput) {
        MemoryUtil.memCopy(mouseinput.address(), address(), SIZEOF);
        return this;
    }

    public static MOUSEINPUT malloc() {
        return new MOUSEINPUT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static MOUSEINPUT calloc() {
        return new MOUSEINPUT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static MOUSEINPUT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new MOUSEINPUT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static MOUSEINPUT create(long j) {
        return new MOUSEINPUT(j, null);
    }

    public static MOUSEINPUT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new MOUSEINPUT(j, null);
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
    public static MOUSEINPUT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static MOUSEINPUT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static MOUSEINPUT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static MOUSEINPUT callocStack(MemoryStack memoryStack) {
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

    public static MOUSEINPUT malloc(MemoryStack memoryStack) {
        return new MOUSEINPUT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static MOUSEINPUT calloc(MemoryStack memoryStack) {
        return new MOUSEINPUT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ndx(long j) {
        return UNSAFE.getInt((Object) null, j + DX);
    }

    public static int ndy(long j) {
        return UNSAFE.getInt((Object) null, j + DY);
    }

    public static int nmouseData(long j) {
        return UNSAFE.getInt((Object) null, j + MOUSEDATA);
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

    public static void ndx(long j, int i) {
        UNSAFE.putInt((Object) null, j + DX, i);
    }

    public static void ndy(long j, int i) {
        UNSAFE.putInt((Object) null, j + DY, i);
    }

    public static void nmouseData(long j, int i) {
        UNSAFE.putInt((Object) null, j + MOUSEDATA, i);
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

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/MOUSEINPUT$Buffer.class */
    public static class Buffer extends StructBuffer<MOUSEINPUT, Buffer> implements NativeResource {
        private static final MOUSEINPUT ELEMENT_FACTORY = MOUSEINPUT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / MOUSEINPUT.SIZEOF);
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
        public MOUSEINPUT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("LONG")
        public int dx() {
            return MOUSEINPUT.ndx(address());
        }

        @NativeType("LONG")
        public int dy() {
            return MOUSEINPUT.ndy(address());
        }

        @NativeType("DWORD")
        public int mouseData() {
            return MOUSEINPUT.nmouseData(address());
        }

        @NativeType("DWORD")
        public int dwFlags() {
            return MOUSEINPUT.ndwFlags(address());
        }

        @NativeType("DWORD")
        public int time() {
            return MOUSEINPUT.ntime(address());
        }

        @NativeType("ULONG_PTR")
        public long dwExtraInfo() {
            return MOUSEINPUT.ndwExtraInfo(address());
        }

        public Buffer dx(@NativeType("LONG") int i) {
            MOUSEINPUT.ndx(address(), i);
            return this;
        }

        public Buffer dy(@NativeType("LONG") int i) {
            MOUSEINPUT.ndy(address(), i);
            return this;
        }

        public Buffer mouseData(@NativeType("DWORD") int i) {
            MOUSEINPUT.nmouseData(address(), i);
            return this;
        }

        public Buffer dwFlags(@NativeType("DWORD") int i) {
            MOUSEINPUT.ndwFlags(address(), i);
            return this;
        }

        public Buffer time(@NativeType("DWORD") int i) {
            MOUSEINPUT.ntime(address(), i);
            return this;
        }

        public Buffer dwExtraInfo(@NativeType("ULONG_PTR") long j) {
            MOUSEINPUT.ndwExtraInfo(address(), j);
            return this;
        }
    }
}
