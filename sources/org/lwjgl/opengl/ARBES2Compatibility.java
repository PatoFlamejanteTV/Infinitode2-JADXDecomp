package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBES2Compatibility.class */
public class ARBES2Compatibility {
    public static final int GL_SHADER_COMPILER = 36346;
    public static final int GL_SHADER_BINARY_FORMATS = 36344;
    public static final int GL_NUM_SHADER_BINARY_FORMATS = 36345;
    public static final int GL_MAX_VERTEX_UNIFORM_VECTORS = 36347;
    public static final int GL_MAX_VARYING_VECTORS = 36348;
    public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS = 36349;
    public static final int GL_IMPLEMENTATION_COLOR_READ_TYPE = 35738;
    public static final int GL_IMPLEMENTATION_COLOR_READ_FORMAT = 35739;
    public static final int GL_FIXED = 5132;
    public static final int GL_LOW_FLOAT = 36336;
    public static final int GL_MEDIUM_FLOAT = 36337;
    public static final int GL_HIGH_FLOAT = 36338;
    public static final int GL_LOW_INT = 36339;
    public static final int GL_MEDIUM_INT = 36340;
    public static final int GL_HIGH_INT = 36341;
    public static final int GL_RGB565 = 36194;

    static {
        GL.initialize();
    }

    protected ARBES2Compatibility() {
        throw new UnsupportedOperationException();
    }

    public static void glReleaseShaderCompiler() {
        GL41C.glReleaseShaderCompiler();
    }

    public static void nglShaderBinary(int i, long j, int i2, long j2, int i3) {
        GL41C.nglShaderBinary(i, j, i2, j2, i3);
    }

    public static void glShaderBinary(@NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL41C.glShaderBinary(intBuffer, i, byteBuffer);
    }

    public static void nglGetShaderPrecisionFormat(int i, int i2, long j, long j2) {
        GL41C.nglGetShaderPrecisionFormat(i, i2, j, j2);
    }

    public static void glGetShaderPrecisionFormat(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        GL41C.glGetShaderPrecisionFormat(i, i2, intBuffer, intBuffer2);
    }

    @NativeType("void")
    public static int glGetShaderPrecisionFormat(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        return GL41C.glGetShaderPrecisionFormat(i, i2, intBuffer);
    }

    public static void glDepthRangef(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2) {
        GL41C.glDepthRangef(f, f2);
    }

    public static void glClearDepthf(@NativeType("GLfloat") float f) {
        GL41C.glClearDepthf(f);
    }

    public static void glShaderBinary(@NativeType("GLuint const *") int[] iArr, @NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL41C.glShaderBinary(iArr, i, byteBuffer);
    }

    public static void glGetShaderPrecisionFormat(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        GL41C.glGetShaderPrecisionFormat(i, i2, iArr, iArr2);
    }
}
