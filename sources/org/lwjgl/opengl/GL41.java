package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL41.class */
public class GL41 extends GL40 {
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
    public static final int GL_PROGRAM_BINARY_RETRIEVABLE_HINT = 33367;
    public static final int GL_PROGRAM_BINARY_LENGTH = 34625;
    public static final int GL_NUM_PROGRAM_BINARY_FORMATS = 34814;
    public static final int GL_PROGRAM_BINARY_FORMATS = 34815;
    public static final int GL_VERTEX_SHADER_BIT = 1;
    public static final int GL_FRAGMENT_SHADER_BIT = 2;
    public static final int GL_GEOMETRY_SHADER_BIT = 4;
    public static final int GL_TESS_CONTROL_SHADER_BIT = 8;
    public static final int GL_TESS_EVALUATION_SHADER_BIT = 16;
    public static final int GL_ALL_SHADER_BITS = -1;
    public static final int GL_PROGRAM_SEPARABLE = 33368;
    public static final int GL_ACTIVE_PROGRAM = 33369;
    public static final int GL_PROGRAM_PIPELINE_BINDING = 33370;
    public static final int GL_MAX_VIEWPORTS = 33371;
    public static final int GL_VIEWPORT_SUBPIXEL_BITS = 33372;
    public static final int GL_VIEWPORT_BOUNDS_RANGE = 33373;
    public static final int GL_LAYER_PROVOKING_VERTEX = 33374;
    public static final int GL_VIEWPORT_INDEX_PROVOKING_VERTEX = 33375;
    public static final int GL_UNDEFINED_VERTEX = 33376;

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL41() {
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

    public static void glUseProgramStages(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3) {
        GL41C.glUseProgramStages(i, i2, i3);
    }

    public static void glActiveShaderProgram(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL41C.glActiveShaderProgram(i, i2);
    }

    public static int nglCreateShaderProgramv(int i, int i2, long j) {
        return GL41C.nglCreateShaderProgramv(i, i2, j);
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramv(@NativeType("GLenum") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer) {
        return GL41C.glCreateShaderProgramv(i, pointerBuffer);
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramv(@NativeType("GLenum") int i, @NativeType("GLchar const * const *") CharSequence... charSequenceArr) {
        return GL41C.glCreateShaderProgramv(i, charSequenceArr);
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramv(@NativeType("GLenum") int i, @NativeType("GLchar const * const *") CharSequence charSequence) {
        return GL41C.glCreateShaderProgramv(i, charSequence);
    }

    public static void glBindProgramPipeline(@NativeType("GLuint") int i) {
        GL41C.glBindProgramPipeline(i);
    }

    public static void nglDeleteProgramPipelines(int i, long j) {
        GL41C.nglDeleteProgramPipelines(i, j);
    }

    public static void glDeleteProgramPipelines(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL41C.glDeleteProgramPipelines(intBuffer);
    }

    public static void glDeleteProgramPipelines(@NativeType("GLuint const *") int i) {
        GL41C.glDeleteProgramPipelines(i);
    }

    public static void nglGenProgramPipelines(int i, long j) {
        GL41C.nglGenProgramPipelines(i, j);
    }

    public static void glGenProgramPipelines(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL41C.glGenProgramPipelines(intBuffer);
    }

    @NativeType("void")
    public static int glGenProgramPipelines() {
        return GL41C.glGenProgramPipelines();
    }

    @NativeType("GLboolean")
    public static boolean glIsProgramPipeline(@NativeType("GLuint") int i) {
        return GL41C.glIsProgramPipeline(i);
    }

    public static void nglGetProgramPipelineiv(int i, int i2, long j) {
        GL41C.nglGetProgramPipelineiv(i, i2, j);
    }

    public static void glGetProgramPipelineiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL41C.glGetProgramPipelineiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetProgramPipelinei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL41C.glGetProgramPipelinei(i, i2);
    }

    public static void glProgramUniform1i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3) {
        GL41C.glProgramUniform1i(i, i2, i3);
    }

    public static void glProgramUniform2i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4) {
        GL41C.glProgramUniform2i(i, i2, i3, i4);
    }

    public static void glProgramUniform3i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        GL41C.glProgramUniform3i(i, i2, i3, i4, i5);
    }

    public static void glProgramUniform4i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6) {
        GL41C.glProgramUniform4i(i, i2, i3, i4, i5, i6);
    }

    public static void glProgramUniform1ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3) {
        GL41C.glProgramUniform1ui(i, i2, i3);
    }

    public static void glProgramUniform2ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4) {
        GL41C.glProgramUniform2ui(i, i2, i3, i4);
    }

    public static void glProgramUniform3ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5) {
        GL41C.glProgramUniform3ui(i, i2, i3, i4, i5);
    }

    public static void glProgramUniform4ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6) {
        GL41C.glProgramUniform4ui(i, i2, i3, i4, i5, i6);
    }

    public static void glProgramUniform1f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f) {
        GL41C.glProgramUniform1f(i, i2, f);
    }

    public static void glProgramUniform2f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2) {
        GL41C.glProgramUniform2f(i, i2, f, f2);
    }

    public static void glProgramUniform3f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3) {
        GL41C.glProgramUniform3f(i, i2, f, f2, f3);
    }

    public static void glProgramUniform4f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL41C.glProgramUniform4f(i, i2, f, f2, f3, f4);
    }

    public static void glProgramUniform1d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d) {
        GL41C.glProgramUniform1d(i, i2, d);
    }

    public static void glProgramUniform2d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL41C.glProgramUniform2d(i, i2, d, d2);
    }

    public static void glProgramUniform3d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3) {
        GL41C.glProgramUniform3d(i, i2, d, d2, d3);
    }

    public static void glProgramUniform4d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4) {
        GL41C.glProgramUniform4d(i, i2, d, d2, d3, d4);
    }

    public static void nglProgramUniform1iv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform1iv(i, i2, i3, j);
    }

    public static void glProgramUniform1iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform1iv(i, i2, intBuffer);
    }

    public static void nglProgramUniform2iv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform2iv(i, i2, i3, j);
    }

    public static void glProgramUniform2iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform2iv(i, i2, intBuffer);
    }

    public static void nglProgramUniform3iv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform3iv(i, i2, i3, j);
    }

    public static void glProgramUniform3iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform3iv(i, i2, intBuffer);
    }

    public static void nglProgramUniform4iv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform4iv(i, i2, i3, j);
    }

    public static void glProgramUniform4iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform4iv(i, i2, intBuffer);
    }

    public static void nglProgramUniform1uiv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform1uiv(i, i2, i3, j);
    }

    public static void glProgramUniform1uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform1uiv(i, i2, intBuffer);
    }

    public static void nglProgramUniform2uiv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform2uiv(i, i2, i3, j);
    }

    public static void glProgramUniform2uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform2uiv(i, i2, intBuffer);
    }

    public static void nglProgramUniform3uiv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform3uiv(i, i2, i3, j);
    }

    public static void glProgramUniform3uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform3uiv(i, i2, intBuffer);
    }

    public static void nglProgramUniform4uiv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform4uiv(i, i2, i3, j);
    }

    public static void glProgramUniform4uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL41C.glProgramUniform4uiv(i, i2, intBuffer);
    }

    public static void nglProgramUniform1fv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform1fv(i, i2, i3, j);
    }

    public static void glProgramUniform1fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniform1fv(i, i2, floatBuffer);
    }

    public static void nglProgramUniform2fv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform2fv(i, i2, i3, j);
    }

    public static void glProgramUniform2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniform2fv(i, i2, floatBuffer);
    }

    public static void nglProgramUniform3fv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform3fv(i, i2, i3, j);
    }

    public static void glProgramUniform3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniform3fv(i, i2, floatBuffer);
    }

    public static void nglProgramUniform4fv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform4fv(i, i2, i3, j);
    }

    public static void glProgramUniform4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniform4fv(i, i2, floatBuffer);
    }

    public static void nglProgramUniform1dv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform1dv(i, i2, i3, j);
    }

    public static void glProgramUniform1dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniform1dv(i, i2, doubleBuffer);
    }

    public static void nglProgramUniform2dv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform2dv(i, i2, i3, j);
    }

    public static void glProgramUniform2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniform2dv(i, i2, doubleBuffer);
    }

    public static void nglProgramUniform3dv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform3dv(i, i2, i3, j);
    }

    public static void glProgramUniform3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniform3dv(i, i2, doubleBuffer);
    }

    public static void nglProgramUniform4dv(int i, int i2, int i3, long j) {
        GL41C.nglProgramUniform4dv(i, i2, i3, j);
    }

    public static void glProgramUniform4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniform4dv(i, i2, doubleBuffer);
    }

    public static void nglProgramUniformMatrix2fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix2fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix2fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix3fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix3fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix3fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix4fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix4fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix4fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix2dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix2dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix2dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix3dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix3dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix3dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix4dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix4dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix4dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix2x3fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix2x3fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix2x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix2x3fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix3x2fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix3x2fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix3x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix3x2fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix2x4fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix2x4fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix2x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix2x4fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix4x2fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix4x2fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix4x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix4x2fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix3x4fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix3x4fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix3x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix3x4fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix4x3fv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix4x3fv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix4x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glProgramUniformMatrix4x3fv(i, i2, z, floatBuffer);
    }

    public static void nglProgramUniformMatrix2x3dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix2x3dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix2x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix2x3dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix3x2dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix3x2dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix3x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix3x2dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix2x4dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix2x4dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix2x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix2x4dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix4x2dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix4x2dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix4x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix4x2dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix3x4dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix3x4dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix3x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix3x4dv(i, i2, z, doubleBuffer);
    }

    public static void nglProgramUniformMatrix4x3dv(int i, int i2, int i3, boolean z, long j) {
        GL41C.nglProgramUniformMatrix4x3dv(i, i2, i3, z, j);
    }

    public static void glProgramUniformMatrix4x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glProgramUniformMatrix4x3dv(i, i2, z, doubleBuffer);
    }

    public static void glValidateProgramPipeline(@NativeType("GLuint") int i) {
        GL41C.glValidateProgramPipeline(i);
    }

    public static void nglGetProgramPipelineInfoLog(int i, int i2, long j, long j2) {
        GL41C.nglGetProgramPipelineInfoLog(i, i2, j, j2);
    }

    public static void glGetProgramPipelineInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL41C.glGetProgramPipelineInfoLog(i, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetProgramPipelineInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        return GL41C.glGetProgramPipelineInfoLog(i, i2);
    }

    @NativeType("void")
    public static String glGetProgramPipelineInfoLog(@NativeType("GLuint") int i) {
        return glGetProgramPipelineInfoLog(i, glGetProgramPipelinei(i, 35716));
    }

    public static void glVertexAttribL1d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d) {
        GL41C.glVertexAttribL1d(i, d);
    }

    public static void glVertexAttribL2d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL41C.glVertexAttribL2d(i, d, d2);
    }

    public static void glVertexAttribL3d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3) {
        GL41C.glVertexAttribL3d(i, d, d2, d3);
    }

    public static void glVertexAttribL4d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4) {
        GL41C.glVertexAttribL4d(i, d, d2, d3, d4);
    }

    public static void nglVertexAttribL1dv(int i, long j) {
        GL41C.nglVertexAttribL1dv(i, j);
    }

    public static void glVertexAttribL1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL1dv(i, doubleBuffer);
    }

    public static void nglVertexAttribL2dv(int i, long j) {
        GL41C.nglVertexAttribL2dv(i, j);
    }

    public static void glVertexAttribL2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL2dv(i, doubleBuffer);
    }

    public static void nglVertexAttribL3dv(int i, long j) {
        GL41C.nglVertexAttribL3dv(i, j);
    }

    public static void glVertexAttribL3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL3dv(i, doubleBuffer);
    }

    public static void nglVertexAttribL4dv(int i, long j) {
        GL41C.nglVertexAttribL4dv(i, j);
    }

    public static void glVertexAttribL4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribL4dv(i, doubleBuffer);
    }

    public static void nglVertexAttribLPointer(int i, int i2, int i3, int i4, long j) {
        GL41C.nglVertexAttribLPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL41C.glVertexAttribLPointer(i, i2, i3, i4, byteBuffer);
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        GL41C.glVertexAttribLPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL41C.glVertexAttribLPointer(i, i2, i3, doubleBuffer);
    }

    public static void nglGetVertexAttribLdv(int i, int i2, long j) {
        GL41C.nglGetVertexAttribLdv(i, i2, j);
    }

    public static void glGetVertexAttribLdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL41C.glGetVertexAttribLdv(i, i2, doubleBuffer);
    }

    public static void nglViewportArrayv(int i, int i2, long j) {
        GL41C.nglViewportArrayv(i, i2, j);
    }

    public static void glViewportArrayv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glViewportArrayv(i, floatBuffer);
    }

    public static void glViewportIndexedf(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL41C.glViewportIndexedf(i, f, f2, f3, f4);
    }

    public static void nglViewportIndexedfv(int i, long j) {
        GL41C.nglViewportIndexedfv(i, j);
    }

    public static void glViewportIndexedfv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL41C.glViewportIndexedfv(i, floatBuffer);
    }

    public static void nglScissorArrayv(int i, int i2, long j) {
        GL41C.nglScissorArrayv(i, i2, j);
    }

    public static void glScissorArrayv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glScissorArrayv(i, intBuffer);
    }

    public static void glScissorIndexed(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL41C.glScissorIndexed(i, i2, i3, i4, i5);
    }

    public static void nglScissorIndexedv(int i, long j) {
        GL41C.nglScissorIndexedv(i, j);
    }

    public static void glScissorIndexedv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL41C.glScissorIndexedv(i, intBuffer);
    }

    public static void nglDepthRangeArrayv(int i, int i2, long j) {
        GL41C.nglDepthRangeArrayv(i, i2, j);
    }

    public static void glDepthRangeArrayv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL41C.glDepthRangeArrayv(i, doubleBuffer);
    }

    public static void glDepthRangeIndexed(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL41C.glDepthRangeIndexed(i, d, d2);
    }

    public static void nglGetFloati_v(int i, int i2, long j) {
        GL41C.nglGetFloati_v(i, i2, j);
    }

    public static void glGetFloati_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL41C.glGetFloati_v(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetFloati(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL41C.glGetFloati(i, i2);
    }

    public static void nglGetDoublei_v(int i, int i2, long j) {
        GL41C.nglGetDoublei_v(i, i2, j);
    }

    public static void glGetDoublei_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL41C.glGetDoublei_v(i, i2, doubleBuffer);
    }

    @NativeType("void")
    public static double glGetDoublei(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL41C.glGetDoublei(i, i2);
    }

    public static void glShaderBinary(@NativeType("GLuint const *") int[] iArr, @NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL41C.glShaderBinary(iArr, i, byteBuffer);
    }

    public static void glGetShaderPrecisionFormat(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        GL41C.glGetShaderPrecisionFormat(i, i2, iArr, iArr2);
    }

    public static void glGetProgramBinary(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("void *") ByteBuffer byteBuffer) {
        GL41C.glGetProgramBinary(i, iArr, iArr2, byteBuffer);
    }

    public static void glDeleteProgramPipelines(@NativeType("GLuint const *") int[] iArr) {
        GL41C.glDeleteProgramPipelines(iArr);
    }

    public static void glGenProgramPipelines(@NativeType("GLuint *") int[] iArr) {
        GL41C.glGenProgramPipelines(iArr);
    }

    public static void glGetProgramPipelineiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL41C.glGetProgramPipelineiv(i, i2, iArr);
    }

    public static void glProgramUniform1iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        GL41C.glProgramUniform1iv(i, i2, iArr);
    }

    public static void glProgramUniform2iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        GL41C.glProgramUniform2iv(i, i2, iArr);
    }

    public static void glProgramUniform3iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        GL41C.glProgramUniform3iv(i, i2, iArr);
    }

    public static void glProgramUniform4iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        GL41C.glProgramUniform4iv(i, i2, iArr);
    }

    public static void glProgramUniform1uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL41C.glProgramUniform1uiv(i, i2, iArr);
    }

    public static void glProgramUniform2uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL41C.glProgramUniform2uiv(i, i2, iArr);
    }

    public static void glProgramUniform3uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL41C.glProgramUniform3uiv(i, i2, iArr);
    }

    public static void glProgramUniform4uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL41C.glProgramUniform4uiv(i, i2, iArr);
    }

    public static void glProgramUniform1fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniform1fv(i, i2, fArr);
    }

    public static void glProgramUniform2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniform2fv(i, i2, fArr);
    }

    public static void glProgramUniform3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniform3fv(i, i2, fArr);
    }

    public static void glProgramUniform4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniform4fv(i, i2, fArr);
    }

    public static void glProgramUniform1dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniform1dv(i, i2, dArr);
    }

    public static void glProgramUniform2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniform2dv(i, i2, dArr);
    }

    public static void glProgramUniform3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniform3dv(i, i2, dArr);
    }

    public static void glProgramUniform4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniform4dv(i, i2, dArr);
    }

    public static void glProgramUniformMatrix2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix2fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix3fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix4fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix2dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix3dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix4dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix2x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix2x3fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix3x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix3x2fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix2x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix2x4fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix4x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix4x2fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix3x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix3x4fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix4x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glProgramUniformMatrix4x3fv(i, i2, z, fArr);
    }

    public static void glProgramUniformMatrix2x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix2x3dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix3x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix3x2dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix2x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix2x4dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix4x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix4x2dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix3x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix3x4dv(i, i2, z, dArr);
    }

    public static void glProgramUniformMatrix4x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glProgramUniformMatrix4x3dv(i, i2, z, dArr);
    }

    public static void glGetProgramPipelineInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL41C.glGetProgramPipelineInfoLog(i, iArr, byteBuffer);
    }

    public static void glVertexAttribL1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL1dv(i, dArr);
    }

    public static void glVertexAttribL2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL2dv(i, dArr);
    }

    public static void glVertexAttribL3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL3dv(i, dArr);
    }

    public static void glVertexAttribL4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glVertexAttribL4dv(i, dArr);
    }

    public static void glGetVertexAttribLdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        GL41C.glGetVertexAttribLdv(i, i2, dArr);
    }

    public static void glViewportArrayv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glViewportArrayv(i, fArr);
    }

    public static void glViewportIndexedfv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL41C.glViewportIndexedfv(i, fArr);
    }

    public static void glScissorArrayv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL41C.glScissorArrayv(i, iArr);
    }

    public static void glScissorIndexedv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL41C.glScissorIndexedv(i, iArr);
    }

    public static void glDepthRangeArrayv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL41C.glDepthRangeArrayv(i, dArr);
    }

    public static void glGetFloati_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL41C.glGetFloati_v(i, i2, fArr);
    }

    public static void glGetDoublei_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") double[] dArr) {
        GL41C.glGetDoublei_v(i, i2, dArr);
    }
}
