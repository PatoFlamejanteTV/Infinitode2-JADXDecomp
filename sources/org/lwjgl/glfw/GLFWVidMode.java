package org.lwjgl.glfw;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct GLFWvidmode")
/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWVidMode.class */
public class GLFWVidMode extends Struct<GLFWVidMode> {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int REDBITS;
    public static final int GREENBITS;
    public static final int BLUEBITS;
    public static final int REFRESHRATE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        WIDTH = __struct.offsetof(0);
        HEIGHT = __struct.offsetof(1);
        REDBITS = __struct.offsetof(2);
        GREENBITS = __struct.offsetof(3);
        BLUEBITS = __struct.offsetof(4);
        REFRESHRATE = __struct.offsetof(5);
    }

    protected GLFWVidMode(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public GLFWVidMode create(long j, ByteBuffer byteBuffer) {
        return new GLFWVidMode(j, byteBuffer);
    }

    public GLFWVidMode(ByteBuffer byteBuffer) {
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

    public int redBits() {
        return nredBits(address());
    }

    public int greenBits() {
        return ngreenBits(address());
    }

    public int blueBits() {
        return nblueBits(address());
    }

    public int refreshRate() {
        return nrefreshRate(address());
    }

    public static GLFWVidMode create(long j) {
        return new GLFWVidMode(j, null);
    }

    public static GLFWVidMode createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new GLFWVidMode(j, null);
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

    public static int nwidth(long j) {
        return UNSAFE.getInt((Object) null, j + WIDTH);
    }

    public static int nheight(long j) {
        return UNSAFE.getInt((Object) null, j + HEIGHT);
    }

    public static int nredBits(long j) {
        return UNSAFE.getInt((Object) null, j + REDBITS);
    }

    public static int ngreenBits(long j) {
        return UNSAFE.getInt((Object) null, j + GREENBITS);
    }

    public static int nblueBits(long j) {
        return UNSAFE.getInt((Object) null, j + BLUEBITS);
    }

    public static int nrefreshRate(long j) {
        return UNSAFE.getInt((Object) null, j + REFRESHRATE);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWVidMode$Buffer.class */
    public static class Buffer extends StructBuffer<GLFWVidMode, Buffer> {
        private static final GLFWVidMode ELEMENT_FACTORY = GLFWVidMode.create(-1);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / GLFWVidMode.SIZEOF);
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
        public GLFWVidMode getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int width() {
            return GLFWVidMode.nwidth(address());
        }

        public int height() {
            return GLFWVidMode.nheight(address());
        }

        public int redBits() {
            return GLFWVidMode.nredBits(address());
        }

        public int greenBits() {
            return GLFWVidMode.ngreenBits(address());
        }

        public int blueBits() {
            return GLFWVidMode.nblueBits(address());
        }

        public int refreshRate() {
            return GLFWVidMode.nrefreshRate(address());
        }
    }
}
