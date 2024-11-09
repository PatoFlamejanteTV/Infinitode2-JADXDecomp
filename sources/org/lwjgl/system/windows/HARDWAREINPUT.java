package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/HARDWAREINPUT.class */
public class HARDWAREINPUT extends Struct<HARDWAREINPUT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int UMSG;
    public static final int WPARAML;
    public static final int WPARAMH;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(2), __member(2));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        UMSG = __struct.offsetof(0);
        WPARAML = __struct.offsetof(1);
        WPARAMH = __struct.offsetof(2);
    }

    protected HARDWAREINPUT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public HARDWAREINPUT create(long j, ByteBuffer byteBuffer) {
        return new HARDWAREINPUT(j, byteBuffer);
    }

    public HARDWAREINPUT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int uMsg() {
        return nuMsg(address());
    }

    @NativeType("WORD")
    public short wParamL() {
        return nwParamL(address());
    }

    @NativeType("WORD")
    public short wParamH() {
        return nwParamH(address());
    }

    public HARDWAREINPUT uMsg(@NativeType("DWORD") int i) {
        nuMsg(address(), i);
        return this;
    }

    public HARDWAREINPUT wParamL(@NativeType("WORD") short s) {
        nwParamL(address(), s);
        return this;
    }

    public HARDWAREINPUT wParamH(@NativeType("WORD") short s) {
        nwParamH(address(), s);
        return this;
    }

    public HARDWAREINPUT set(int i, short s, short s2) {
        uMsg(i);
        wParamL(s);
        wParamH(s2);
        return this;
    }

    public HARDWAREINPUT set(HARDWAREINPUT hardwareinput) {
        MemoryUtil.memCopy(hardwareinput.address(), address(), SIZEOF);
        return this;
    }

    public static HARDWAREINPUT malloc() {
        return new HARDWAREINPUT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static HARDWAREINPUT calloc() {
        return new HARDWAREINPUT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static HARDWAREINPUT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new HARDWAREINPUT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static HARDWAREINPUT create(long j) {
        return new HARDWAREINPUT(j, null);
    }

    public static HARDWAREINPUT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new HARDWAREINPUT(j, null);
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
    public static HARDWAREINPUT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static HARDWAREINPUT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static HARDWAREINPUT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static HARDWAREINPUT callocStack(MemoryStack memoryStack) {
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

    public static HARDWAREINPUT malloc(MemoryStack memoryStack) {
        return new HARDWAREINPUT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static HARDWAREINPUT calloc(MemoryStack memoryStack) {
        return new HARDWAREINPUT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nuMsg(long j) {
        return UNSAFE.getInt((Object) null, j + UMSG);
    }

    public static short nwParamL(long j) {
        return UNSAFE.getShort((Object) null, j + WPARAML);
    }

    public static short nwParamH(long j) {
        return UNSAFE.getShort((Object) null, j + WPARAMH);
    }

    public static void nuMsg(long j, int i) {
        UNSAFE.putInt((Object) null, j + UMSG, i);
    }

    public static void nwParamL(long j, short s) {
        UNSAFE.putShort((Object) null, j + WPARAML, s);
    }

    public static void nwParamH(long j, short s) {
        UNSAFE.putShort((Object) null, j + WPARAMH, s);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/HARDWAREINPUT$Buffer.class */
    public static class Buffer extends StructBuffer<HARDWAREINPUT, Buffer> implements NativeResource {
        private static final HARDWAREINPUT ELEMENT_FACTORY = HARDWAREINPUT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / HARDWAREINPUT.SIZEOF);
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
        public HARDWAREINPUT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int uMsg() {
            return HARDWAREINPUT.nuMsg(address());
        }

        @NativeType("WORD")
        public short wParamL() {
            return HARDWAREINPUT.nwParamL(address());
        }

        @NativeType("WORD")
        public short wParamH() {
            return HARDWAREINPUT.nwParamH(address());
        }

        public Buffer uMsg(@NativeType("DWORD") int i) {
            HARDWAREINPUT.nuMsg(address(), i);
            return this;
        }

        public Buffer wParamL(@NativeType("WORD") short s) {
            HARDWAREINPUT.nwParamL(address(), s);
            return this;
        }

        public Buffer wParamH(@NativeType("WORD") short s) {
            HARDWAREINPUT.nwParamH(address(), s);
            return this;
        }
    }
}
