package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import java.util.function.Consumer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/MSG.class */
public class MSG extends Struct<MSG> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int HWND;
    public static final int MESSAGE;
    public static final int WPARAM;
    public static final int LPARAM;
    public static final int TIME;
    public static final int PT;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(4), __member(POINT.SIZEOF, POINT.ALIGNOF));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        HWND = __struct.offsetof(0);
        MESSAGE = __struct.offsetof(1);
        WPARAM = __struct.offsetof(2);
        LPARAM = __struct.offsetof(3);
        TIME = __struct.offsetof(4);
        PT = __struct.offsetof(5);
    }

    protected MSG(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public MSG create(long j, ByteBuffer byteBuffer) {
        return new MSG(j, byteBuffer);
    }

    public MSG(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("HWND")
    public long hwnd() {
        return nhwnd(address());
    }

    @NativeType("UINT")
    public int message() {
        return nmessage(address());
    }

    @NativeType("WPARAM")
    public long wParam() {
        return nwParam(address());
    }

    @NativeType("LPARAM")
    public long lParam() {
        return nlParam(address());
    }

    @NativeType("DWORD")
    public int time() {
        return ntime(address());
    }

    public POINT pt() {
        return npt(address());
    }

    public MSG hwnd(@NativeType("HWND") long j) {
        nhwnd(address(), j);
        return this;
    }

    public MSG message(@NativeType("UINT") int i) {
        nmessage(address(), i);
        return this;
    }

    public MSG wParam(@NativeType("WPARAM") long j) {
        nwParam(address(), j);
        return this;
    }

    public MSG lParam(@NativeType("LPARAM") long j) {
        nlParam(address(), j);
        return this;
    }

    public MSG time(@NativeType("DWORD") int i) {
        ntime(address(), i);
        return this;
    }

    public MSG pt(POINT point) {
        npt(address(), point);
        return this;
    }

    public MSG pt(Consumer<POINT> consumer) {
        consumer.accept(pt());
        return this;
    }

    public MSG set(long j, int i, long j2, long j3, int i2, POINT point) {
        hwnd(j);
        message(i);
        wParam(j2);
        lParam(j3);
        time(i2);
        pt(point);
        return this;
    }

    public MSG set(MSG msg) {
        MemoryUtil.memCopy(msg.address(), address(), SIZEOF);
        return this;
    }

    public static MSG malloc() {
        return new MSG(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static MSG calloc() {
        return new MSG(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static MSG create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new MSG(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static MSG create(long j) {
        return new MSG(j, null);
    }

    public static MSG createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new MSG(j, null);
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
    public static MSG mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static MSG callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static MSG mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static MSG callocStack(MemoryStack memoryStack) {
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

    public static MSG malloc(MemoryStack memoryStack) {
        return new MSG(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static MSG calloc(MemoryStack memoryStack) {
        return new MSG(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nhwnd(long j) {
        return MemoryUtil.memGetAddress(j + HWND);
    }

    public static int nmessage(long j) {
        return UNSAFE.getInt((Object) null, j + MESSAGE);
    }

    public static long nwParam(long j) {
        return MemoryUtil.memGetAddress(j + WPARAM);
    }

    public static long nlParam(long j) {
        return MemoryUtil.memGetAddress(j + LPARAM);
    }

    public static int ntime(long j) {
        return UNSAFE.getInt((Object) null, j + TIME);
    }

    public static POINT npt(long j) {
        return POINT.create(j + PT);
    }

    public static void nhwnd(long j, long j2) {
        MemoryUtil.memPutAddress(j + HWND, j2);
    }

    public static void nmessage(long j, int i) {
        UNSAFE.putInt((Object) null, j + MESSAGE, i);
    }

    public static void nwParam(long j, long j2) {
        MemoryUtil.memPutAddress(j + WPARAM, j2);
    }

    public static void nlParam(long j, long j2) {
        MemoryUtil.memPutAddress(j + LPARAM, j2);
    }

    public static void ntime(long j, int i) {
        UNSAFE.putInt((Object) null, j + TIME, i);
    }

    public static void npt(long j, POINT point) {
        MemoryUtil.memCopy(point.address(), j + PT, POINT.SIZEOF);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/MSG$Buffer.class */
    public static class Buffer extends StructBuffer<MSG, Buffer> implements NativeResource {
        private static final MSG ELEMENT_FACTORY = MSG.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / MSG.SIZEOF);
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
        public MSG getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("HWND")
        public long hwnd() {
            return MSG.nhwnd(address());
        }

        @NativeType("UINT")
        public int message() {
            return MSG.nmessage(address());
        }

        @NativeType("WPARAM")
        public long wParam() {
            return MSG.nwParam(address());
        }

        @NativeType("LPARAM")
        public long lParam() {
            return MSG.nlParam(address());
        }

        @NativeType("DWORD")
        public int time() {
            return MSG.ntime(address());
        }

        public POINT pt() {
            return MSG.npt(address());
        }

        public Buffer hwnd(@NativeType("HWND") long j) {
            MSG.nhwnd(address(), j);
            return this;
        }

        public Buffer message(@NativeType("UINT") int i) {
            MSG.nmessage(address(), i);
            return this;
        }

        public Buffer wParam(@NativeType("WPARAM") long j) {
            MSG.nwParam(address(), j);
            return this;
        }

        public Buffer lParam(@NativeType("LPARAM") long j) {
            MSG.nlParam(address(), j);
            return this;
        }

        public Buffer time(@NativeType("DWORD") int i) {
            MSG.ntime(address(), i);
            return this;
        }

        public Buffer pt(POINT point) {
            MSG.npt(address(), point);
            return this;
        }

        public Buffer pt(Consumer<POINT> consumer) {
            consumer.accept(pt());
            return this;
        }
    }
}
