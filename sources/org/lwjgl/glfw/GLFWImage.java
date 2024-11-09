package org.lwjgl.glfw;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct GLFWimage")
/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWImage.class */
public class GLFWImage extends Struct<GLFWImage> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int PIXELS;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        WIDTH = __struct.offsetof(0);
        HEIGHT = __struct.offsetof(1);
        PIXELS = __struct.offsetof(2);
    }

    protected GLFWImage(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public GLFWImage create(long j, ByteBuffer byteBuffer) {
        return new GLFWImage(j, byteBuffer);
    }

    public GLFWImage(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int width() {
        return nwidth(address());
    }

    public int height() {
        return nheight(address());
    }

    @NativeType("unsigned char *")
    public ByteBuffer pixels(int i) {
        return npixels(address(), i);
    }

    public GLFWImage width(int i) {
        nwidth(address(), i);
        return this;
    }

    public GLFWImage height(int i) {
        nheight(address(), i);
        return this;
    }

    public GLFWImage pixels(@NativeType("unsigned char *") ByteBuffer byteBuffer) {
        npixels(address(), byteBuffer);
        return this;
    }

    public GLFWImage set(int i, int i2, ByteBuffer byteBuffer) {
        width(i);
        height(i2);
        pixels(byteBuffer);
        return this;
    }

    public GLFWImage set(GLFWImage gLFWImage) {
        MemoryUtil.memCopy(gLFWImage.address(), address(), SIZEOF);
        return this;
    }

    public static GLFWImage malloc() {
        return new GLFWImage(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static GLFWImage calloc() {
        return new GLFWImage(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static GLFWImage create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new GLFWImage(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static GLFWImage create(long j) {
        return new GLFWImage(j, null);
    }

    public static GLFWImage createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new GLFWImage(j, null);
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
    public static GLFWImage mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GLFWImage callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GLFWImage mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static GLFWImage callocStack(MemoryStack memoryStack) {
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

    public static GLFWImage malloc(MemoryStack memoryStack) {
        return new GLFWImage(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static GLFWImage calloc(MemoryStack memoryStack) {
        return new GLFWImage(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nwidth(long j) {
        return UNSAFE.getInt((Object) null, j + WIDTH);
    }

    public static int nheight(long j) {
        return UNSAFE.getInt((Object) null, j + HEIGHT);
    }

    public static ByteBuffer npixels(long j, int i) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + PIXELS), i);
    }

    public static void nwidth(long j, int i) {
        UNSAFE.putInt((Object) null, j + WIDTH, i);
    }

    public static void nheight(long j, int i) {
        UNSAFE.putInt((Object) null, j + HEIGHT, i);
    }

    public static void npixels(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + PIXELS, MemoryUtil.memAddress(byteBuffer));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + PIXELS));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWImage$Buffer.class */
    public static class Buffer extends StructBuffer<GLFWImage, Buffer> implements NativeResource {
        private static final GLFWImage ELEMENT_FACTORY = GLFWImage.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / GLFWImage.SIZEOF);
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
        public GLFWImage getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int width() {
            return GLFWImage.nwidth(address());
        }

        public int height() {
            return GLFWImage.nheight(address());
        }

        @NativeType("unsigned char *")
        public ByteBuffer pixels(int i) {
            return GLFWImage.npixels(address(), i);
        }

        public Buffer width(int i) {
            GLFWImage.nwidth(address(), i);
            return this;
        }

        public Buffer height(int i) {
            GLFWImage.nheight(address(), i);
            return this;
        }

        public Buffer pixels(@NativeType("unsigned char *") ByteBuffer byteBuffer) {
            GLFWImage.npixels(address(), byteBuffer);
            return this;
        }
    }
}
