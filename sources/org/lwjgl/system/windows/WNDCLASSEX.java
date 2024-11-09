package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WNDCLASSEX.class */
public class WNDCLASSEX extends Struct<WNDCLASSEX> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CBSIZE;
    public static final int STYLE;
    public static final int LPFNWNDPROC;
    public static final int CBCLSEXTRA;
    public static final int CBWNDEXTRA;
    public static final int HINSTANCE;
    public static final int HICON;
    public static final int HCURSOR;
    public static final int HBRBACKGROUND;
    public static final int LPSZMENUNAME;
    public static final int LPSZCLASSNAME;
    public static final int HICONSM;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(POINTER_SIZE), __member(4), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        CBSIZE = __struct.offsetof(0);
        STYLE = __struct.offsetof(1);
        LPFNWNDPROC = __struct.offsetof(2);
        CBCLSEXTRA = __struct.offsetof(3);
        CBWNDEXTRA = __struct.offsetof(4);
        HINSTANCE = __struct.offsetof(5);
        HICON = __struct.offsetof(6);
        HCURSOR = __struct.offsetof(7);
        HBRBACKGROUND = __struct.offsetof(8);
        LPSZMENUNAME = __struct.offsetof(9);
        LPSZCLASSNAME = __struct.offsetof(10);
        HICONSM = __struct.offsetof(11);
    }

    protected WNDCLASSEX(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public WNDCLASSEX create(long j, ByteBuffer byteBuffer) {
        return new WNDCLASSEX(j, byteBuffer);
    }

    public WNDCLASSEX(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("UINT")
    public int cbSize() {
        return ncbSize(address());
    }

    @NativeType("UINT")
    public int style() {
        return nstyle(address());
    }

    @NativeType("WNDPROC")
    public WindowProc lpfnWndProc() {
        return nlpfnWndProc(address());
    }

    public int cbClsExtra() {
        return ncbClsExtra(address());
    }

    public int cbWndExtra() {
        return ncbWndExtra(address());
    }

    @NativeType("HINSTANCE")
    public long hInstance() {
        return nhInstance(address());
    }

    @NativeType("HICON")
    public long hIcon() {
        return nhIcon(address());
    }

    @NativeType("HCURSOR")
    public long hCursor() {
        return nhCursor(address());
    }

    @NativeType("HBRUSH")
    public long hbrBackground() {
        return nhbrBackground(address());
    }

    @NativeType("LPCTSTR")
    public ByteBuffer lpszMenuName() {
        return nlpszMenuName(address());
    }

    @NativeType("LPCTSTR")
    public String lpszMenuNameString() {
        return nlpszMenuNameString(address());
    }

    @NativeType("LPCTSTR")
    public ByteBuffer lpszClassName() {
        return nlpszClassName(address());
    }

    @NativeType("LPCTSTR")
    public String lpszClassNameString() {
        return nlpszClassNameString(address());
    }

    @NativeType("HICON")
    public long hIconSm() {
        return nhIconSm(address());
    }

    public WNDCLASSEX cbSize(@NativeType("UINT") int i) {
        ncbSize(address(), i);
        return this;
    }

    public WNDCLASSEX style(@NativeType("UINT") int i) {
        nstyle(address(), i);
        return this;
    }

    public WNDCLASSEX lpfnWndProc(@NativeType("WNDPROC") WindowProcI windowProcI) {
        nlpfnWndProc(address(), windowProcI);
        return this;
    }

    public WNDCLASSEX cbClsExtra(int i) {
        ncbClsExtra(address(), i);
        return this;
    }

    public WNDCLASSEX cbWndExtra(int i) {
        ncbWndExtra(address(), i);
        return this;
    }

    public WNDCLASSEX hInstance(@NativeType("HINSTANCE") long j) {
        nhInstance(address(), j);
        return this;
    }

    public WNDCLASSEX hIcon(@NativeType("HICON") long j) {
        nhIcon(address(), j);
        return this;
    }

    public WNDCLASSEX hCursor(@NativeType("HCURSOR") long j) {
        nhCursor(address(), j);
        return this;
    }

    public WNDCLASSEX hbrBackground(@NativeType("HBRUSH") long j) {
        nhbrBackground(address(), j);
        return this;
    }

    public WNDCLASSEX lpszMenuName(@NativeType("LPCTSTR") ByteBuffer byteBuffer) {
        nlpszMenuName(address(), byteBuffer);
        return this;
    }

    public WNDCLASSEX lpszClassName(@NativeType("LPCTSTR") ByteBuffer byteBuffer) {
        nlpszClassName(address(), byteBuffer);
        return this;
    }

    public WNDCLASSEX hIconSm(@NativeType("HICON") long j) {
        nhIconSm(address(), j);
        return this;
    }

    public WNDCLASSEX set(int i, int i2, WindowProcI windowProcI, int i3, int i4, long j, long j2, long j3, long j4, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, long j5) {
        cbSize(i);
        style(i2);
        lpfnWndProc(windowProcI);
        cbClsExtra(i3);
        cbWndExtra(i4);
        hInstance(j);
        hIcon(j2);
        hCursor(j3);
        hbrBackground(j4);
        lpszMenuName(byteBuffer);
        lpszClassName(byteBuffer2);
        hIconSm(j5);
        return this;
    }

    public WNDCLASSEX set(WNDCLASSEX wndclassex) {
        MemoryUtil.memCopy(wndclassex.address(), address(), SIZEOF);
        return this;
    }

    public static WNDCLASSEX malloc() {
        return new WNDCLASSEX(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static WNDCLASSEX calloc() {
        return new WNDCLASSEX(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static WNDCLASSEX create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new WNDCLASSEX(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static WNDCLASSEX create(long j) {
        return new WNDCLASSEX(j, null);
    }

    public static WNDCLASSEX createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new WNDCLASSEX(j, null);
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
    public static WNDCLASSEX mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static WNDCLASSEX callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static WNDCLASSEX mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static WNDCLASSEX callocStack(MemoryStack memoryStack) {
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

    public static WNDCLASSEX malloc(MemoryStack memoryStack) {
        return new WNDCLASSEX(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static WNDCLASSEX calloc(MemoryStack memoryStack) {
        return new WNDCLASSEX(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static int nstyle(long j) {
        return UNSAFE.getInt((Object) null, j + STYLE);
    }

    public static WindowProc nlpfnWndProc(long j) {
        return WindowProc.create(MemoryUtil.memGetAddress(j + LPFNWNDPROC));
    }

    public static int ncbClsExtra(long j) {
        return UNSAFE.getInt((Object) null, j + CBCLSEXTRA);
    }

    public static int ncbWndExtra(long j) {
        return UNSAFE.getInt((Object) null, j + CBWNDEXTRA);
    }

    public static long nhInstance(long j) {
        return MemoryUtil.memGetAddress(j + HINSTANCE);
    }

    public static long nhIcon(long j) {
        return MemoryUtil.memGetAddress(j + HICON);
    }

    public static long nhCursor(long j) {
        return MemoryUtil.memGetAddress(j + HCURSOR);
    }

    public static long nhbrBackground(long j) {
        return MemoryUtil.memGetAddress(j + HBRBACKGROUND);
    }

    public static ByteBuffer nlpszMenuName(long j) {
        return MemoryUtil.memByteBufferNT2Safe(MemoryUtil.memGetAddress(j + LPSZMENUNAME));
    }

    public static String nlpszMenuNameString(long j) {
        return MemoryUtil.memUTF16Safe(MemoryUtil.memGetAddress(j + LPSZMENUNAME));
    }

    public static ByteBuffer nlpszClassName(long j) {
        return MemoryUtil.memByteBufferNT2(MemoryUtil.memGetAddress(j + LPSZCLASSNAME));
    }

    public static String nlpszClassNameString(long j) {
        return MemoryUtil.memUTF16(MemoryUtil.memGetAddress(j + LPSZCLASSNAME));
    }

    public static long nhIconSm(long j) {
        return MemoryUtil.memGetAddress(j + HICONSM);
    }

    public static void ncbSize(long j, int i) {
        UNSAFE.putInt((Object) null, j + CBSIZE, i);
    }

    public static void nstyle(long j, int i) {
        UNSAFE.putInt((Object) null, j + STYLE, i);
    }

    public static void nlpfnWndProc(long j, WindowProcI windowProcI) {
        MemoryUtil.memPutAddress(j + LPFNWNDPROC, windowProcI.address());
    }

    public static void ncbClsExtra(long j, int i) {
        UNSAFE.putInt((Object) null, j + CBCLSEXTRA, i);
    }

    public static void ncbWndExtra(long j, int i) {
        UNSAFE.putInt((Object) null, j + CBWNDEXTRA, i);
    }

    public static void nhInstance(long j, long j2) {
        MemoryUtil.memPutAddress(j + HINSTANCE, j2);
    }

    public static void nhIcon(long j, long j2) {
        MemoryUtil.memPutAddress(j + HICON, j2);
    }

    public static void nhCursor(long j, long j2) {
        MemoryUtil.memPutAddress(j + HCURSOR, j2);
    }

    public static void nhbrBackground(long j, long j2) {
        MemoryUtil.memPutAddress(j + HBRBACKGROUND, j2);
    }

    public static void nlpszMenuName(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT2Safe(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + LPSZMENUNAME, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void nlpszClassName(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT2(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + LPSZCLASSNAME, MemoryUtil.memAddress(byteBuffer));
    }

    public static void nhIconSm(long j, long j2) {
        MemoryUtil.memPutAddress(j + HICONSM, j2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + LPFNWNDPROC));
        Checks.check(MemoryUtil.memGetAddress(j + LPSZCLASSNAME));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WNDCLASSEX$Buffer.class */
    public static class Buffer extends StructBuffer<WNDCLASSEX, Buffer> implements NativeResource {
        private static final WNDCLASSEX ELEMENT_FACTORY = WNDCLASSEX.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / WNDCLASSEX.SIZEOF);
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
        public WNDCLASSEX getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("UINT")
        public int cbSize() {
            return WNDCLASSEX.ncbSize(address());
        }

        @NativeType("UINT")
        public int style() {
            return WNDCLASSEX.nstyle(address());
        }

        @NativeType("WNDPROC")
        public WindowProc lpfnWndProc() {
            return WNDCLASSEX.nlpfnWndProc(address());
        }

        public int cbClsExtra() {
            return WNDCLASSEX.ncbClsExtra(address());
        }

        public int cbWndExtra() {
            return WNDCLASSEX.ncbWndExtra(address());
        }

        @NativeType("HINSTANCE")
        public long hInstance() {
            return WNDCLASSEX.nhInstance(address());
        }

        @NativeType("HICON")
        public long hIcon() {
            return WNDCLASSEX.nhIcon(address());
        }

        @NativeType("HCURSOR")
        public long hCursor() {
            return WNDCLASSEX.nhCursor(address());
        }

        @NativeType("HBRUSH")
        public long hbrBackground() {
            return WNDCLASSEX.nhbrBackground(address());
        }

        @NativeType("LPCTSTR")
        public ByteBuffer lpszMenuName() {
            return WNDCLASSEX.nlpszMenuName(address());
        }

        @NativeType("LPCTSTR")
        public String lpszMenuNameString() {
            return WNDCLASSEX.nlpszMenuNameString(address());
        }

        @NativeType("LPCTSTR")
        public ByteBuffer lpszClassName() {
            return WNDCLASSEX.nlpszClassName(address());
        }

        @NativeType("LPCTSTR")
        public String lpszClassNameString() {
            return WNDCLASSEX.nlpszClassNameString(address());
        }

        @NativeType("HICON")
        public long hIconSm() {
            return WNDCLASSEX.nhIconSm(address());
        }

        public Buffer cbSize(@NativeType("UINT") int i) {
            WNDCLASSEX.ncbSize(address(), i);
            return this;
        }

        public Buffer style(@NativeType("UINT") int i) {
            WNDCLASSEX.nstyle(address(), i);
            return this;
        }

        public Buffer lpfnWndProc(@NativeType("WNDPROC") WindowProcI windowProcI) {
            WNDCLASSEX.nlpfnWndProc(address(), windowProcI);
            return this;
        }

        public Buffer cbClsExtra(int i) {
            WNDCLASSEX.ncbClsExtra(address(), i);
            return this;
        }

        public Buffer cbWndExtra(int i) {
            WNDCLASSEX.ncbWndExtra(address(), i);
            return this;
        }

        public Buffer hInstance(@NativeType("HINSTANCE") long j) {
            WNDCLASSEX.nhInstance(address(), j);
            return this;
        }

        public Buffer hIcon(@NativeType("HICON") long j) {
            WNDCLASSEX.nhIcon(address(), j);
            return this;
        }

        public Buffer hCursor(@NativeType("HCURSOR") long j) {
            WNDCLASSEX.nhCursor(address(), j);
            return this;
        }

        public Buffer hbrBackground(@NativeType("HBRUSH") long j) {
            WNDCLASSEX.nhbrBackground(address(), j);
            return this;
        }

        public Buffer lpszMenuName(@NativeType("LPCTSTR") ByteBuffer byteBuffer) {
            WNDCLASSEX.nlpszMenuName(address(), byteBuffer);
            return this;
        }

        public Buffer lpszClassName(@NativeType("LPCTSTR") ByteBuffer byteBuffer) {
            WNDCLASSEX.nlpszClassName(address(), byteBuffer);
            return this;
        }

        public Buffer hIconSm(@NativeType("HICON") long j) {
            WNDCLASSEX.nhIconSm(address(), j);
            return this;
        }
    }
}
