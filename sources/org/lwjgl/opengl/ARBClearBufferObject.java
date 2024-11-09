package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBClearBufferObject.class */
public class ARBClearBufferObject {
    public static native void nglClearNamedBufferDataEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglClearNamedBufferSubDataEXT(int i, int i2, long j, long j2, int i3, int i4, long j3);

    static {
        GL.initialize();
    }

    protected ARBClearBufferObject() {
        throw new UnsupportedOperationException();
    }

    public static void nglClearBufferData(int i, int i2, int i3, int i4, long j) {
        GL43C.nglClearBufferData(i, i2, i3, i4, j);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, byteBuffer);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, shortBuffer);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, intBuffer);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, floatBuffer);
    }

    public static void nglClearBufferSubData(int i, int i2, long j, long j2, int i3, int i4, long j3) {
        GL43C.nglClearBufferSubData(i, i2, j, j2, i3, i4, j3);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, byteBuffer);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, shortBuffer);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, intBuffer);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, floatBuffer);
    }

    public static void glClearNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearNamedBufferDataEXT(i, i2, i3, i4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearNamedBufferDataEXT(i, i2, i3, i4, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearNamedBufferDataEXT(i, i2, i3, i4, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearNamedBufferDataEXT(i, i2, i3, i4, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glClearNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearNamedBufferSubDataEXT(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearNamedBufferSubDataEXT(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearNamedBufferSubDataEXT(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearNamedBufferSubDataEXT(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        GL43C.glClearBufferData(i, i2, i3, i4, sArr);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        GL43C.glClearBufferData(i, i2, i3, i4, iArr);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        GL43C.glClearBufferData(i, i2, i3, i4, fArr);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, sArr);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, iArr);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, fArr);
    }

    public static void glClearNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glClearNamedBufferDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glClearNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glClearNamedBufferDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glClearNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glClearNamedBufferDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glClearNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j3 = GL.getICD().glClearNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, sArr, j3);
    }

    public static void glClearNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j3 = GL.getICD().glClearNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, iArr, j3);
    }

    public static void glClearNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j3 = GL.getICD().glClearNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, fArr, j3);
    }
}
