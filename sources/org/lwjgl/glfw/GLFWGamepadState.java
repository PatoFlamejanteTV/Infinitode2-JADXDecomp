package org.lwjgl.glfw;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct GLFWgamepadstate")
/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWGamepadState.class */
public class GLFWGamepadState extends Struct<GLFWGamepadState> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int BUTTONS;
    public static final int AXES;

    static {
        Struct.Layout __struct = __struct(__array(1, 15), __array(4, 6));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        BUTTONS = __struct.offsetof(0);
        AXES = __struct.offsetof(1);
    }

    protected GLFWGamepadState(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public GLFWGamepadState create(long j, ByteBuffer byteBuffer) {
        return new GLFWGamepadState(j, byteBuffer);
    }

    public GLFWGamepadState(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("unsigned char[15]")
    public ByteBuffer buttons() {
        return nbuttons(address());
    }

    @NativeType("unsigned char")
    public byte buttons(int i) {
        return nbuttons(address(), i);
    }

    @NativeType("float[6]")
    public FloatBuffer axes() {
        return naxes(address());
    }

    public float axes(int i) {
        return naxes(address(), i);
    }

    public GLFWGamepadState buttons(@NativeType("unsigned char[15]") ByteBuffer byteBuffer) {
        nbuttons(address(), byteBuffer);
        return this;
    }

    public GLFWGamepadState buttons(int i, @NativeType("unsigned char") byte b2) {
        nbuttons(address(), i, b2);
        return this;
    }

    public GLFWGamepadState axes(@NativeType("float[6]") FloatBuffer floatBuffer) {
        naxes(address(), floatBuffer);
        return this;
    }

    public GLFWGamepadState axes(int i, float f) {
        naxes(address(), i, f);
        return this;
    }

    public GLFWGamepadState set(ByteBuffer byteBuffer, FloatBuffer floatBuffer) {
        buttons(byteBuffer);
        axes(floatBuffer);
        return this;
    }

    public GLFWGamepadState set(GLFWGamepadState gLFWGamepadState) {
        MemoryUtil.memCopy(gLFWGamepadState.address(), address(), SIZEOF);
        return this;
    }

    public static GLFWGamepadState malloc() {
        return new GLFWGamepadState(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static GLFWGamepadState calloc() {
        return new GLFWGamepadState(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static GLFWGamepadState create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new GLFWGamepadState(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static GLFWGamepadState create(long j) {
        return new GLFWGamepadState(j, null);
    }

    public static GLFWGamepadState createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new GLFWGamepadState(j, null);
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
    public static GLFWGamepadState mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GLFWGamepadState callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static GLFWGamepadState mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static GLFWGamepadState callocStack(MemoryStack memoryStack) {
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

    public static GLFWGamepadState malloc(MemoryStack memoryStack) {
        return new GLFWGamepadState(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static GLFWGamepadState calloc(MemoryStack memoryStack) {
        return new GLFWGamepadState(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ByteBuffer nbuttons(long j) {
        return MemoryUtil.memByteBuffer(j + BUTTONS, 15);
    }

    public static byte nbuttons(long j, int i) {
        return UNSAFE.getByte((Object) null, j + BUTTONS + Checks.check(i, 15));
    }

    public static FloatBuffer naxes(long j) {
        return MemoryUtil.memFloatBuffer(j + AXES, 6);
    }

    public static float naxes(long j, int i) {
        return UNSAFE.getFloat((Object) null, j + AXES + (Checks.check(i, 6) << 2));
    }

    public static void nbuttons(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(byteBuffer, 15);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(byteBuffer), j + BUTTONS, byteBuffer.remaining());
    }

    public static void nbuttons(long j, int i, byte b2) {
        UNSAFE.putByte((Object) null, j + BUTTONS + Checks.check(i, 15), b2);
    }

    public static void naxes(long j, FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.checkGT(floatBuffer, 6);
        }
        MemoryUtil.memCopy(MemoryUtil.memAddress(floatBuffer), j + AXES, floatBuffer.remaining() << 2);
    }

    public static void naxes(long j, int i, float f) {
        UNSAFE.putFloat((Object) null, j + AXES + (Checks.check(i, 6) << 2), f);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWGamepadState$Buffer.class */
    public static class Buffer extends StructBuffer<GLFWGamepadState, Buffer> implements NativeResource {
        private static final GLFWGamepadState ELEMENT_FACTORY = GLFWGamepadState.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / GLFWGamepadState.SIZEOF);
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
        public GLFWGamepadState getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("unsigned char[15]")
        public ByteBuffer buttons() {
            return GLFWGamepadState.nbuttons(address());
        }

        @NativeType("unsigned char")
        public byte buttons(int i) {
            return GLFWGamepadState.nbuttons(address(), i);
        }

        @NativeType("float[6]")
        public FloatBuffer axes() {
            return GLFWGamepadState.naxes(address());
        }

        public float axes(int i) {
            return GLFWGamepadState.naxes(address(), i);
        }

        public Buffer buttons(@NativeType("unsigned char[15]") ByteBuffer byteBuffer) {
            GLFWGamepadState.nbuttons(address(), byteBuffer);
            return this;
        }

        public Buffer buttons(int i, @NativeType("unsigned char") byte b2) {
            GLFWGamepadState.nbuttons(address(), i, b2);
            return this;
        }

        public Buffer axes(@NativeType("float[6]") FloatBuffer floatBuffer) {
            GLFWGamepadState.naxes(address(), floatBuffer);
            return this;
        }

        public Buffer axes(int i, float f) {
            GLFWGamepadState.naxes(address(), i, f);
            return this;
        }
    }
}
