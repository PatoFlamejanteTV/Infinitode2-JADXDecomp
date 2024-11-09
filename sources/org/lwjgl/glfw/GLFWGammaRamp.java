package org.lwjgl.glfw;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct GLFWgammaramp")
/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWGammaRamp.class */
public class GLFWGammaRamp extends Struct<GLFWGammaRamp> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int RED;
    public static final int GREEN;
    public static final int BLUE;
    public static final int SIZE;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        RED = __struct.offsetof(0);
        GREEN = __struct.offsetof(1);
        BLUE = __struct.offsetof(2);
        SIZE = __struct.offsetof(3);
    }

    protected GLFWGammaRamp(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public GLFWGammaRamp create(long j, ByteBuffer byteBuffer) {
        return new GLFWGammaRamp(j, byteBuffer);
    }

    public GLFWGammaRamp(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("unsigned short *")
    public ShortBuffer red() {
        return nred(address());
    }

    @NativeType("unsigned short *")
    public ShortBuffer green() {
        return ngreen(address());
    }

    @NativeType("unsigned short *")
    public ShortBuffer blue() {
        return nblue(address());
    }

    @NativeType("unsigned int")
    public int size() {
        return nsize(address());
    }

    public GLFWGammaRamp red(@NativeType("unsigned short *") ShortBuffer shortBuffer) {
        nred(address(), shortBuffer);
        return this;
    }

    public GLFWGammaRamp green(@NativeType("unsigned short *") ShortBuffer shortBuffer) {
        ngreen(address(), shortBuffer);
        return this;
    }

    public GLFWGammaRamp blue(@NativeType("unsigned short *") ShortBuffer shortBuffer) {
        nblue(address(), shortBuffer);
        return this;
    }

    public GLFWGammaRamp size(@NativeType("unsigned int") int i) {
        nsize(address(), i);
        return this;
    }

    public GLFWGammaRamp set(ShortBuffer shortBuffer, ShortBuffer shortBuffer2, ShortBuffer shortBuffer3, int i) {
        red(shortBuffer);
        green(shortBuffer2);
        blue(shortBuffer3);
        size(i);
        return this;
    }

    public GLFWGammaRamp set(GLFWGammaRamp gLFWGammaRamp) {
        MemoryUtil.memCopy(gLFWGammaRamp.address(), address(), SIZEOF);
        return this;
    }

    public static GLFWGammaRamp malloc() {
        return new GLFWGammaRamp(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static GLFWGammaRamp calloc() {
        return new GLFWGammaRamp(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static GLFWGammaRamp create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new GLFWGammaRamp(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static GLFWGammaRamp create(long j) {
        return new GLFWGammaRamp(j, null);
    }

    public static GLFWGammaRamp createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new GLFWGammaRamp(j, null);
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
    public static GLFWGammaRamp mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GLFWGammaRamp callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GLFWGammaRamp mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static GLFWGammaRamp callocStack(MemoryStack memoryStack) {
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

    public static GLFWGammaRamp malloc(MemoryStack memoryStack) {
        return new GLFWGammaRamp(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static GLFWGammaRamp calloc(MemoryStack memoryStack) {
        return new GLFWGammaRamp(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ShortBuffer nred(long j) {
        return MemoryUtil.memShortBuffer(MemoryUtil.memGetAddress(j + RED), nsize(j));
    }

    public static ShortBuffer ngreen(long j) {
        return MemoryUtil.memShortBuffer(MemoryUtil.memGetAddress(j + GREEN), nsize(j));
    }

    public static ShortBuffer nblue(long j) {
        return MemoryUtil.memShortBuffer(MemoryUtil.memGetAddress(j + BLUE), nsize(j));
    }

    public static int nsize(long j) {
        return UNSAFE.getInt((Object) null, j + SIZE);
    }

    public static void nred(long j, ShortBuffer shortBuffer) {
        MemoryUtil.memPutAddress(j + RED, MemoryUtil.memAddress(shortBuffer));
    }

    public static void ngreen(long j, ShortBuffer shortBuffer) {
        MemoryUtil.memPutAddress(j + GREEN, MemoryUtil.memAddress(shortBuffer));
    }

    public static void nblue(long j, ShortBuffer shortBuffer) {
        MemoryUtil.memPutAddress(j + BLUE, MemoryUtil.memAddress(shortBuffer));
    }

    public static void nsize(long j, int i) {
        UNSAFE.putInt((Object) null, j + SIZE, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + RED));
        Checks.check(MemoryUtil.memGetAddress(j + GREEN));
        Checks.check(MemoryUtil.memGetAddress(j + BLUE));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWGammaRamp$Buffer.class */
    public static class Buffer extends StructBuffer<GLFWGammaRamp, Buffer> implements NativeResource {
        private static final GLFWGammaRamp ELEMENT_FACTORY = GLFWGammaRamp.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / GLFWGammaRamp.SIZEOF);
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
        public GLFWGammaRamp getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("unsigned short *")
        public ShortBuffer red() {
            return GLFWGammaRamp.nred(address());
        }

        @NativeType("unsigned short *")
        public ShortBuffer green() {
            return GLFWGammaRamp.ngreen(address());
        }

        @NativeType("unsigned short *")
        public ShortBuffer blue() {
            return GLFWGammaRamp.nblue(address());
        }

        @NativeType("unsigned int")
        public int size() {
            return GLFWGammaRamp.nsize(address());
        }

        public Buffer red(@NativeType("unsigned short *") ShortBuffer shortBuffer) {
            GLFWGammaRamp.nred(address(), shortBuffer);
            return this;
        }

        public Buffer green(@NativeType("unsigned short *") ShortBuffer shortBuffer) {
            GLFWGammaRamp.ngreen(address(), shortBuffer);
            return this;
        }

        public Buffer blue(@NativeType("unsigned short *") ShortBuffer shortBuffer) {
            GLFWGammaRamp.nblue(address(), shortBuffer);
            return this;
        }

        public Buffer size(@NativeType("unsigned int") int i) {
            GLFWGammaRamp.nsize(address(), i);
            return this;
        }
    }
}
