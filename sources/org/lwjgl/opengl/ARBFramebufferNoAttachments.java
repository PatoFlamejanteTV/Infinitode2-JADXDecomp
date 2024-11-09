package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBFramebufferNoAttachments.class */
public class ARBFramebufferNoAttachments {
    public static final int GL_FRAMEBUFFER_DEFAULT_WIDTH = 37648;
    public static final int GL_FRAMEBUFFER_DEFAULT_HEIGHT = 37649;
    public static final int GL_FRAMEBUFFER_DEFAULT_LAYERS = 37650;
    public static final int GL_FRAMEBUFFER_DEFAULT_SAMPLES = 37651;
    public static final int GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 37652;
    public static final int GL_MAX_FRAMEBUFFER_WIDTH = 37653;
    public static final int GL_MAX_FRAMEBUFFER_HEIGHT = 37654;
    public static final int GL_MAX_FRAMEBUFFER_LAYERS = 37655;
    public static final int GL_MAX_FRAMEBUFFER_SAMPLES = 37656;

    public static native void glNamedFramebufferParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglGetNamedFramebufferParameterivEXT(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected ARBFramebufferNoAttachments() {
        throw new UnsupportedOperationException();
    }

    public static void glFramebufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL43C.glFramebufferParameteri(i, i2, i3);
    }

    public static void nglGetFramebufferParameteriv(int i, int i2, long j) {
        GL43C.nglGetFramebufferParameteriv(i, i2, j);
    }

    public static void glGetFramebufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL43C.glGetFramebufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetFramebufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL43C.glGetFramebufferParameteri(i, i2);
    }

    public static void glGetNamedFramebufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedFramebufferParameterivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedFramebufferParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedFramebufferParameterivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetFramebufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL43C.glGetFramebufferParameteriv(i, i2, iArr);
    }

    public static void glGetNamedFramebufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedFramebufferParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
