package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBGetProgramBinary.class */
public class ARBGetProgramBinary {
    public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 33367;
    public static final int GL_PROGRAM_BINARY_LENGTH = 34625;
    public static final int GL_NUM_PROGRAM_BINARY_FORMATS = 34814;
    public static final int GL_PROGRAM_BINARY_FORMATS = 34815;

    static {
        GL.initialize();
    }

    protected ARBGetProgramBinary() {
        throw new UnsupportedOperationException();
    }

    public static void nglGetProgramBinary(int i, int i2, long j, long j2, long j3) {
        GL41C.nglGetProgramBinary(i, i2, j, j2, j3);
    }

    public static void glGetProgramBinary(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, @NativeType("void *") ByteBuffer byteBuffer) {
        GL41C.glGetProgramBinary(i, intBuffer, intBuffer2, byteBuffer);
    }

    public static void nglProgramBinary(int i, int i2, long j, int i3) {
        GL41C.nglProgramBinary(i, i2, j, i3);
    }

    public static void glProgramBinary(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL41C.glProgramBinary(i, i2, byteBuffer);
    }

    public static void glProgramParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL41C.glProgramParameteri(i, i2, i3);
    }

    public static void glGetProgramBinary(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("void *") ByteBuffer byteBuffer) {
        GL41C.glGetProgramBinary(i, iArr, iArr2, byteBuffer);
    }
}
