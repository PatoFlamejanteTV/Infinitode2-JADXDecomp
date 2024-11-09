package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL14C.class */
public class GL14C extends GL13C {
    public static final int GL_CONSTANT_COLOR = 32769;
    public static final int GL_ONE_MINUS_CONSTANT_COLOR = 32770;
    public static final int GL_CONSTANT_ALPHA = 32771;
    public static final int GL_ONE_MINUS_CONSTANT_ALPHA = 32772;
    public static final int GL_BLEND_COLOR = 32773;
    public static final int GL_FUNC_ADD = 32774;
    public static final int GL_MIN = 32775;
    public static final int GL_MAX = 32776;
    public static final int GL_BLEND_EQUATION = 32777;
    public static final int GL_FUNC_SUBTRACT = 32778;
    public static final int GL_FUNC_REVERSE_SUBTRACT = 32779;
    public static final int GL_DEPTH_COMPONENT16 = 33189;
    public static final int GL_DEPTH_COMPONENT24 = 33190;
    public static final int GL_DEPTH_COMPONENT32 = 33191;
    public static final int GL_TEXTURE_DEPTH_SIZE = 34890;
    public static final int GL_TEXTURE_COMPARE_MODE = 34892;
    public static final int GL_TEXTURE_COMPARE_FUNC = 34893;
    public static final int GL_POINT_FADE_THRESHOLD_SIZE = 33064;
    public static final int GL_BLEND_DST_RGB = 32968;
    public static final int GL_BLEND_SRC_RGB = 32969;
    public static final int GL_BLEND_DST_ALPHA = 32970;
    public static final int GL_BLEND_SRC_ALPHA = 32971;
    public static final int GL_INCR_WRAP = 34055;
    public static final int GL_DECR_WRAP = 34056;
    public static final int GL_TEXTURE_LOD_BIAS = 34049;
    public static final int GL_MAX_TEXTURE_LOD_BIAS = 34045;
    public static final int GL_MIRRORED_REPEAT = 33648;

    public static native void glBlendColor(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glBlendEquation(@NativeType("GLenum") int i);

    public static native void nglMultiDrawArrays(int i, long j, long j2, int i2);

    public static native void nglMultiDrawElements(int i, long j, int i2, long j2, int i3);

    public static native void glPointParameterf(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void glPointParameteri(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void nglPointParameterfv(int i, long j);

    public static native void nglPointParameteriv(int i, long j);

    public static native void glBlendFuncSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL14C() {
        throw new UnsupportedOperationException();
    }

    public static void glMultiDrawArrays(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer, @NativeType("GLsizei const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer2, intBuffer.remaining());
        }
        nglMultiDrawArrays(i, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), intBuffer.remaining());
    }

    public static void glMultiDrawElements(@NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum") int i2, @NativeType("void const **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, intBuffer.remaining());
        }
        nglMultiDrawElements(i, MemoryUtil.memAddress(intBuffer), i2, MemoryUtil.memAddress(pointerBuffer), intBuffer.remaining());
    }

    public static void glPointParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglPointParameterfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPointParameteriv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglPointParameteriv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiDrawArrays(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr, @NativeType("GLsizei const *") int[] iArr2) {
        long j = GL.getICD().glMultiDrawArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr2, iArr.length);
        }
        JNI.callPPV(i, iArr, iArr2, iArr.length, j);
    }

    public static void glMultiDrawElements(@NativeType("GLenum") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLenum") int i2, @NativeType("void const **") PointerBuffer pointerBuffer) {
        long j = GL.getICD().glMultiDrawElements;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check((CustomBuffer<?>) pointerBuffer, iArr.length);
        }
        JNI.callPPV(i, iArr, i2, MemoryUtil.memAddress(pointerBuffer), iArr.length, j);
    }

    public static void glPointParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPointParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glPointParameteriv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glPointParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, iArr, j);
    }
}
