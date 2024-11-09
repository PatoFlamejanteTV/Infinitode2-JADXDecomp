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

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/INPUT.class */
public class INPUT extends Struct<INPUT> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int TYPE;
    public static final int DUMMYUNIONNAME;
    public static final int DUMMYUNIONNAME_MI;
    public static final int DUMMYUNIONNAME_KI;
    public static final int DUMMYUNIONNAME_HI;

    static {
        Struct.Layout __struct = __struct(__member(4), __union(__member(MOUSEINPUT.SIZEOF, MOUSEINPUT.ALIGNOF), __member(KEYBDINPUT.SIZEOF, KEYBDINPUT.ALIGNOF), __member(HARDWAREINPUT.SIZEOF, HARDWAREINPUT.ALIGNOF)));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        TYPE = __struct.offsetof(0);
        DUMMYUNIONNAME = __struct.offsetof(1);
        DUMMYUNIONNAME_MI = __struct.offsetof(2);
        DUMMYUNIONNAME_KI = __struct.offsetof(3);
        DUMMYUNIONNAME_HI = __struct.offsetof(4);
    }

    protected INPUT(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public INPUT create(long j, ByteBuffer byteBuffer) {
        return new INPUT(j, byteBuffer);
    }

    public INPUT(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int type() {
        return ntype(address());
    }

    public MOUSEINPUT DUMMYUNIONNAME_mi() {
        return nDUMMYUNIONNAME_mi(address());
    }

    public KEYBDINPUT DUMMYUNIONNAME_ki() {
        return nDUMMYUNIONNAME_ki(address());
    }

    public HARDWAREINPUT DUMMYUNIONNAME_hi() {
        return nDUMMYUNIONNAME_hi(address());
    }

    public INPUT type(@NativeType("DWORD") int i) {
        ntype(address(), i);
        return this;
    }

    public INPUT DUMMYUNIONNAME_mi(MOUSEINPUT mouseinput) {
        nDUMMYUNIONNAME_mi(address(), mouseinput);
        return this;
    }

    public INPUT DUMMYUNIONNAME_mi(Consumer<MOUSEINPUT> consumer) {
        consumer.accept(DUMMYUNIONNAME_mi());
        return this;
    }

    public INPUT DUMMYUNIONNAME_ki(KEYBDINPUT keybdinput) {
        nDUMMYUNIONNAME_ki(address(), keybdinput);
        return this;
    }

    public INPUT DUMMYUNIONNAME_ki(Consumer<KEYBDINPUT> consumer) {
        consumer.accept(DUMMYUNIONNAME_ki());
        return this;
    }

    public INPUT DUMMYUNIONNAME_hi(HARDWAREINPUT hardwareinput) {
        nDUMMYUNIONNAME_hi(address(), hardwareinput);
        return this;
    }

    public INPUT DUMMYUNIONNAME_hi(Consumer<HARDWAREINPUT> consumer) {
        consumer.accept(DUMMYUNIONNAME_hi());
        return this;
    }

    public INPUT set(INPUT input) {
        MemoryUtil.memCopy(input.address(), address(), SIZEOF);
        return this;
    }

    public static INPUT malloc() {
        return new INPUT(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static INPUT calloc() {
        return new INPUT(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static INPUT create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new INPUT(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static INPUT create(long j) {
        return new INPUT(j, null);
    }

    public static INPUT createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new INPUT(j, null);
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
    public static INPUT mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static INPUT callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static INPUT mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static INPUT callocStack(MemoryStack memoryStack) {
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

    public static INPUT malloc(MemoryStack memoryStack) {
        return new INPUT(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static INPUT calloc(MemoryStack memoryStack) {
        return new INPUT(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static MOUSEINPUT nDUMMYUNIONNAME_mi(long j) {
        return MOUSEINPUT.create(j + DUMMYUNIONNAME_MI);
    }

    public static KEYBDINPUT nDUMMYUNIONNAME_ki(long j) {
        return KEYBDINPUT.create(j + DUMMYUNIONNAME_KI);
    }

    public static HARDWAREINPUT nDUMMYUNIONNAME_hi(long j) {
        return HARDWAREINPUT.create(j + DUMMYUNIONNAME_HI);
    }

    public static void ntype(long j, int i) {
        UNSAFE.putInt((Object) null, j + TYPE, i);
    }

    public static void nDUMMYUNIONNAME_mi(long j, MOUSEINPUT mouseinput) {
        MemoryUtil.memCopy(mouseinput.address(), j + DUMMYUNIONNAME_MI, MOUSEINPUT.SIZEOF);
    }

    public static void nDUMMYUNIONNAME_ki(long j, KEYBDINPUT keybdinput) {
        MemoryUtil.memCopy(keybdinput.address(), j + DUMMYUNIONNAME_KI, KEYBDINPUT.SIZEOF);
    }

    public static void nDUMMYUNIONNAME_hi(long j, HARDWAREINPUT hardwareinput) {
        MemoryUtil.memCopy(hardwareinput.address(), j + DUMMYUNIONNAME_HI, HARDWAREINPUT.SIZEOF);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/INPUT$Buffer.class */
    public static class Buffer extends StructBuffer<INPUT, Buffer> implements NativeResource {
        private static final INPUT ELEMENT_FACTORY = INPUT.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / INPUT.SIZEOF);
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
        public INPUT getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int type() {
            return INPUT.ntype(address());
        }

        public MOUSEINPUT DUMMYUNIONNAME_mi() {
            return INPUT.nDUMMYUNIONNAME_mi(address());
        }

        public KEYBDINPUT DUMMYUNIONNAME_ki() {
            return INPUT.nDUMMYUNIONNAME_ki(address());
        }

        public HARDWAREINPUT DUMMYUNIONNAME_hi() {
            return INPUT.nDUMMYUNIONNAME_hi(address());
        }

        public Buffer type(@NativeType("DWORD") int i) {
            INPUT.ntype(address(), i);
            return this;
        }

        public Buffer DUMMYUNIONNAME_mi(MOUSEINPUT mouseinput) {
            INPUT.nDUMMYUNIONNAME_mi(address(), mouseinput);
            return this;
        }

        public Buffer DUMMYUNIONNAME_mi(Consumer<MOUSEINPUT> consumer) {
            consumer.accept(DUMMYUNIONNAME_mi());
            return this;
        }

        public Buffer DUMMYUNIONNAME_ki(KEYBDINPUT keybdinput) {
            INPUT.nDUMMYUNIONNAME_ki(address(), keybdinput);
            return this;
        }

        public Buffer DUMMYUNIONNAME_ki(Consumer<KEYBDINPUT> consumer) {
            consumer.accept(DUMMYUNIONNAME_ki());
            return this;
        }

        public Buffer DUMMYUNIONNAME_hi(HARDWAREINPUT hardwareinput) {
            INPUT.nDUMMYUNIONNAME_hi(address(), hardwareinput);
            return this;
        }

        public Buffer DUMMYUNIONNAME_hi(Consumer<HARDWAREINPUT> consumer) {
            consumer.accept(DUMMYUNIONNAME_hi());
            return this;
        }
    }
}
