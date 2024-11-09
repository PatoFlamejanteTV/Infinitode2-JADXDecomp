package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTTextureInteger.class */
public class EXTTextureInteger {
    public static final int GL_RGBA_INTEGER_MODE_EXT = 36254;
    public static final int GL_RGBA32UI_EXT = 36208;
    public static final int GL_RGB32UI_EXT = 36209;
    public static final int GL_ALPHA32UI_EXT = 36210;
    public static final int GL_INTENSITY32UI_EXT = 36211;
    public static final int GL_LUMINANCE32UI_EXT = 36212;
    public static final int GL_LUMINANCE_ALPHA32UI_EXT = 36213;
    public static final int GL_RGBA16UI_EXT = 36214;
    public static final int GL_RGB16UI_EXT = 36215;
    public static final int GL_ALPHA16UI_EXT = 36216;
    public static final int GL_INTENSITY16UI_EXT = 36217;
    public static final int GL_LUMINANCE16UI_EXT = 36218;
    public static final int GL_LUMINANCE_ALPHA16UI_EXT = 36219;
    public static final int GL_RGBA8UI_EXT = 36220;
    public static final int GL_RGB8UI_EXT = 36221;
    public static final int GL_ALPHA8UI_EXT = 36222;
    public static final int GL_INTENSITY8UI_EXT = 36223;
    public static final int GL_LUMINANCE8UI_EXT = 36224;
    public static final int GL_LUMINANCE_ALPHA8UI_EXT = 36225;
    public static final int GL_RGBA32I_EXT = 36226;
    public static final int GL_RGB32I_EXT = 36227;
    public static final int GL_ALPHA32I_EXT = 36228;
    public static final int GL_INTENSITY32I_EXT = 36229;
    public static final int GL_LUMINANCE32I_EXT = 36230;
    public static final int GL_LUMINANCE_ALPHA32I_EXT = 36231;
    public static final int GL_RGBA16I_EXT = 36232;
    public static final int GL_RGB16I_EXT = 36233;
    public static final int GL_ALPHA16I_EXT = 36234;
    public static final int GL_INTENSITY16I_EXT = 36235;
    public static final int GL_LUMINANCE16I_EXT = 36236;
    public static final int GL_LUMINANCE_ALPHA16I_EXT = 36237;
    public static final int GL_RGBA8I_EXT = 36238;
    public static final int GL_RGB8I_EXT = 36239;
    public static final int GL_ALPHA8I_EXT = 36240;
    public static final int GL_INTENSITY8I_EXT = 36241;
    public static final int GL_LUMINANCE8I_EXT = 36242;
    public static final int GL_LUMINANCE_ALPHA8I_EXT = 36243;
    public static final int GL_RED_INTEGER_EXT = 36244;
    public static final int GL_GREEN_INTEGER_EXT = 36245;
    public static final int GL_BLUE_INTEGER_EXT = 36246;
    public static final int GL_ALPHA_INTEGER_EXT = 36247;
    public static final int GL_RGB_INTEGER_EXT = 36248;
    public static final int GL_RGBA_INTEGER_EXT = 36249;
    public static final int GL_BGR_INTEGER_EXT = 36250;
    public static final int GL_BGRA_INTEGER_EXT = 36251;
    public static final int GL_LUMINANCE_INTEGER_EXT = 36252;
    public static final int GL_LUMINANCE_ALPHA_INTEGER_EXT = 36253;

    public static native void glClearColorIiEXT(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glClearColorIuiEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4);

    public static native void nglTexParameterIivEXT(int i, int i2, long j);

    public static native void nglTexParameterIuivEXT(int i, int i2, long j);

    public static native void nglGetTexParameterIivEXT(int i, int i2, long j);

    public static native void nglGetTexParameterIuivEXT(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected EXTTextureInteger() {
        throw new UnsupportedOperationException();
    }

    public static void glTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexParameterIivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexParameterIiEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglTexParameterIivEXT(i, i2, MemoryUtil.memAddress(stackGet.ints(i3)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexParameterIuivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexParameterIuiEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglTexParameterIuivEXT(i, i2, MemoryUtil.memAddress(stackGet.ints(i3)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexParameterIivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexParameterIiEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexParameterIivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexParameterIuivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexParameterIuiEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexParameterIuivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glTexParameterIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glTexParameterIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTexParameterIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetTexParameterIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }
}
