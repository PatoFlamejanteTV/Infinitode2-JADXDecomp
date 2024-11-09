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

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WINDOWPLACEMENT.class */
public class WINDOWPLACEMENT extends Struct<WINDOWPLACEMENT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int LENGTH;
    public static final int FLAGS;
    public static final int SHOWCMD;
    public static final int PTMINPOSITION;
    public static final int PTMAXPOSITION;
    public static final int RCNORMALPOSITION;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(POINT.SIZEOF, POINT.ALIGNOF), __member(POINT.SIZEOF, POINT.ALIGNOF), __member(RECT.SIZEOF, RECT.ALIGNOF));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        LENGTH = __struct.offsetof(0);
        FLAGS = __struct.offsetof(1);
        SHOWCMD = __struct.offsetof(2);
        PTMINPOSITION = __struct.offsetof(3);
        PTMAXPOSITION = __struct.offsetof(4);
        RCNORMALPOSITION = __struct.offsetof(5);
    }

    protected WINDOWPLACEMENT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public WINDOWPLACEMENT create(long j, ByteBuffer byteBuffer) {
        return new WINDOWPLACEMENT(j, byteBuffer);
    }

    public WINDOWPLACEMENT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("UINT")
    public int length() {
        return nlength(address());
    }

    @NativeType("UINT")
    public int flags() {
        return nflags(address());
    }

    @NativeType("UINT")
    public int showCmd() {
        return nshowCmd(address());
    }

    public POINT ptMinPosition() {
        return nptMinPosition(address());
    }

    public POINT ptMaxPosition() {
        return nptMaxPosition(address());
    }

    public RECT rcNormalPosition() {
        return nrcNormalPosition(address());
    }

    public WINDOWPLACEMENT length(@NativeType("UINT") int i) {
        nlength(address(), i);
        return this;
    }

    public WINDOWPLACEMENT flags(@NativeType("UINT") int i) {
        nflags(address(), i);
        return this;
    }

    public WINDOWPLACEMENT showCmd(@NativeType("UINT") int i) {
        nshowCmd(address(), i);
        return this;
    }

    public WINDOWPLACEMENT ptMinPosition(POINT point) {
        nptMinPosition(address(), point);
        return this;
    }

    public WINDOWPLACEMENT ptMinPosition(Consumer<POINT> consumer) {
        consumer.accept(ptMinPosition());
        return this;
    }

    public WINDOWPLACEMENT ptMaxPosition(POINT point) {
        nptMaxPosition(address(), point);
        return this;
    }

    public WINDOWPLACEMENT ptMaxPosition(Consumer<POINT> consumer) {
        consumer.accept(ptMaxPosition());
        return this;
    }

    public WINDOWPLACEMENT rcNormalPosition(RECT rect) {
        nrcNormalPosition(address(), rect);
        return this;
    }

    public WINDOWPLACEMENT rcNormalPosition(Consumer<RECT> consumer) {
        consumer.accept(rcNormalPosition());
        return this;
    }

    public WINDOWPLACEMENT set(int i, int i2, int i3, POINT point, POINT point2, RECT rect) {
        length(i);
        flags(i2);
        showCmd(i3);
        ptMinPosition(point);
        ptMaxPosition(point2);
        rcNormalPosition(rect);
        return this;
    }

    public WINDOWPLACEMENT set(WINDOWPLACEMENT windowplacement) {
        MemoryUtil.memCopy(windowplacement.address(), address(), SIZEOF);
        return this;
    }

    public static WINDOWPLACEMENT malloc() {
        return new WINDOWPLACEMENT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static WINDOWPLACEMENT calloc() {
        return new WINDOWPLACEMENT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static WINDOWPLACEMENT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new WINDOWPLACEMENT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static WINDOWPLACEMENT create(long j) {
        return new WINDOWPLACEMENT(j, null);
    }

    public static WINDOWPLACEMENT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new WINDOWPLACEMENT(j, null);
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
    public static WINDOWPLACEMENT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static WINDOWPLACEMENT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static WINDOWPLACEMENT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static WINDOWPLACEMENT callocStack(MemoryStack memoryStack) {
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

    public static WINDOWPLACEMENT malloc(MemoryStack memoryStack) {
        return new WINDOWPLACEMENT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static WINDOWPLACEMENT calloc(MemoryStack memoryStack) {
        return new WINDOWPLACEMENT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nlength(long j) {
        return UNSAFE.getInt((Object) null, j + LENGTH);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    public static int nshowCmd(long j) {
        return UNSAFE.getInt((Object) null, j + SHOWCMD);
    }

    public static POINT nptMinPosition(long j) {
        return POINT.create(j + PTMINPOSITION);
    }

    public static POINT nptMaxPosition(long j) {
        return POINT.create(j + PTMAXPOSITION);
    }

    public static RECT nrcNormalPosition(long j) {
        return RECT.create(j + RCNORMALPOSITION);
    }

    public static void nlength(long j, int i) {
        UNSAFE.putInt((Object) null, j + LENGTH, i);
    }

    public static void nflags(long j, int i) {
        UNSAFE.putInt((Object) null, j + FLAGS, i);
    }

    public static void nshowCmd(long j, int i) {
        UNSAFE.putInt((Object) null, j + SHOWCMD, i);
    }

    public static void nptMinPosition(long j, POINT point) {
        MemoryUtil.memCopy(point.address(), j + PTMINPOSITION, POINT.SIZEOF);
    }

    public static void nptMaxPosition(long j, POINT point) {
        MemoryUtil.memCopy(point.address(), j + PTMAXPOSITION, POINT.SIZEOF);
    }

    public static void nrcNormalPosition(long j, RECT rect) {
        MemoryUtil.memCopy(rect.address(), j + RCNORMALPOSITION, RECT.SIZEOF);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WINDOWPLACEMENT$Buffer.class */
    public static class Buffer extends StructBuffer<WINDOWPLACEMENT, Buffer> implements NativeResource {
        private static final WINDOWPLACEMENT ELEMENT_FACTORY = WINDOWPLACEMENT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / WINDOWPLACEMENT.SIZEOF);
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
        public WINDOWPLACEMENT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("UINT")
        public int length() {
            return WINDOWPLACEMENT.nlength(address());
        }

        @NativeType("UINT")
        public int flags() {
            return WINDOWPLACEMENT.nflags(address());
        }

        @NativeType("UINT")
        public int showCmd() {
            return WINDOWPLACEMENT.nshowCmd(address());
        }

        public POINT ptMinPosition() {
            return WINDOWPLACEMENT.nptMinPosition(address());
        }

        public POINT ptMaxPosition() {
            return WINDOWPLACEMENT.nptMaxPosition(address());
        }

        public RECT rcNormalPosition() {
            return WINDOWPLACEMENT.nrcNormalPosition(address());
        }

        public Buffer length(@NativeType("UINT") int i) {
            WINDOWPLACEMENT.nlength(address(), i);
            return this;
        }

        public Buffer flags(@NativeType("UINT") int i) {
            WINDOWPLACEMENT.nflags(address(), i);
            return this;
        }

        public Buffer showCmd(@NativeType("UINT") int i) {
            WINDOWPLACEMENT.nshowCmd(address(), i);
            return this;
        }

        public Buffer ptMinPosition(POINT point) {
            WINDOWPLACEMENT.nptMinPosition(address(), point);
            return this;
        }

        public Buffer ptMinPosition(Consumer<POINT> consumer) {
            consumer.accept(ptMinPosition());
            return this;
        }

        public Buffer ptMaxPosition(POINT point) {
            WINDOWPLACEMENT.nptMaxPosition(address(), point);
            return this;
        }

        public Buffer ptMaxPosition(Consumer<POINT> consumer) {
            consumer.accept(ptMaxPosition());
            return this;
        }

        public Buffer rcNormalPosition(RECT rect) {
            WINDOWPLACEMENT.nrcNormalPosition(address(), rect);
            return this;
        }

        public Buffer rcNormalPosition(Consumer<RECT> consumer) {
            consumer.accept(rcNormalPosition());
            return this;
        }
    }
}
