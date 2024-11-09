package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL21C.class */
public class GL21C extends GL20C {
    public static final int GL_FLOAT_MAT2x3 = 35685;
    public static final int GL_FLOAT_MAT2x4 = 35686;
    public static final int GL_FLOAT_MAT3x2 = 35687;
    public static final int GL_FLOAT_MAT3x4 = 35688;
    public static final int GL_FLOAT_MAT4x2 = 35689;
    public static final int GL_FLOAT_MAT4x3 = 35690;
    public static final int GL_PIXEL_PACK_BUFFER = 35051;
    public static final int GL_PIXEL_UNPACK_BUFFER = 35052;
    public static final int GL_PIXEL_PACK_BUFFER_BINDING = 35053;
    public static final int GL_PIXEL_UNPACK_BUFFER_BINDING = 35055;
    public static final int GL_SRGB = 35904;
    public static final int GL_SRGB8 = 35905;
    public static final int GL_SRGB_ALPHA = 35906;
    public static final int GL_SRGB8_ALPHA8 = 35907;
    public static final int GL_COMPRESSED_SRGB = 35912;
    public static final int GL_COMPRESSED_SRGB_ALPHA = 35913;

    public static native void nglUniformMatrix2x3fv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix3x2fv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix2x4fv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix4x2fv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix3x4fv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix4x3fv(int i, int i2, boolean z, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL21C() {
        throw new UnsupportedOperationException();
    }

    public static void glUniformMatrix2x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix2x3fv(i, floatBuffer.remaining() / 6, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix3x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix3x2fv(i, floatBuffer.remaining() / 6, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix2x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix2x4fv(i, floatBuffer.remaining() >> 3, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix4x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix4x2fv(i, floatBuffer.remaining() >> 3, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix3x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix3x4fv(i, floatBuffer.remaining() / 12, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix4x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix4x3fv(i, floatBuffer.remaining() / 12, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix2x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix2x3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 6, z, fArr, j);
    }

    public static void glUniformMatrix3x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix3x2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 6, z, fArr, j);
    }

    public static void glUniformMatrix2x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix2x4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 3, z, fArr, j);
    }

    public static void glUniformMatrix4x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix4x2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 3, z, fArr, j);
    }

    public static void glUniformMatrix3x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix3x4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 12, z, fArr, j);
    }

    public static void glUniformMatrix4x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix4x3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 12, z, fArr, j);
    }
}
