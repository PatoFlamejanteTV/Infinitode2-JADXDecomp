package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL41C.class */
public class GL41C extends GL40C {
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

    public static native void glReleaseShaderCompiler();

    public static native void nglShaderBinary(int i, long j, int i2, long j2, int i3);

    public static native void nglGetShaderPrecisionFormat(int i, int i2, long j, long j2);

    public static native void glDepthRangef(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glClearDepthf(@NativeType("GLfloat") float f);

    public static native void nglGetProgramBinary(int i, int i2, long j, long j2, long j3);

    public static native void nglProgramBinary(int i, int i2, long j, int i3);

    public static native void glProgramParameteri(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void glUseProgramStages(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3);

    public static native void glActiveShaderProgram(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native int nglCreateShaderProgramv(int i, int i2, long j);

    public static native void glBindProgramPipeline(@NativeType("GLuint") int i);

    public static native void nglDeleteProgramPipelines(int i, long j);

    public static native void nglGenProgramPipelines(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsProgramPipeline(@NativeType("GLuint") int i);

    public static native void nglGetProgramPipelineiv(int i, int i2, long j);

    public static native void glProgramUniform1i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glProgramUniform2i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glProgramUniform3i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glProgramUniform4i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6);

    public static native void glProgramUniform1ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3);

    public static native void glProgramUniform2ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4);

    public static native void glProgramUniform3ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5);

    public static native void glProgramUniform4ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6);

    public static native void glProgramUniform1f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f);

    public static native void glProgramUniform2f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glProgramUniform3f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glProgramUniform4f(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glProgramUniform1d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d);

    public static native void glProgramUniform2d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glProgramUniform3d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glProgramUniform4d(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglProgramUniform1iv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2iv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3iv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4iv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform1uiv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2uiv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3uiv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4uiv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform1fv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2fv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3fv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4fv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform1dv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2dv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3dv(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4dv(int i, int i2, int i3, long j);

    public static native void nglProgramUniformMatrix2fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x3fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x2fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x4fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x2fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x4fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x3fv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x3dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x2dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x4dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x2dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x4dv(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x3dv(int i, int i2, int i3, boolean z, long j);

    public static native void glValidateProgramPipeline(@NativeType("GLuint") int i);

    public static native void nglGetProgramPipelineInfoLog(int i, int i2, long j, long j2);

    public static native void glVertexAttribL1d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d);

    public static native void glVertexAttribL2d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glVertexAttribL3d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glVertexAttribL4d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglVertexAttribL1dv(int i, long j);

    public static native void nglVertexAttribL2dv(int i, long j);

    public static native void nglVertexAttribL3dv(int i, long j);

    public static native void nglVertexAttribL4dv(int i, long j);

    public static native void nglVertexAttribLPointer(int i, int i2, int i3, int i4, long j);

    public static native void nglGetVertexAttribLdv(int i, int i2, long j);

    public static native void nglViewportArrayv(int i, int i2, long j);

    public static native void glViewportIndexedf(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void nglViewportIndexedfv(int i, long j);

    public static native void nglScissorArrayv(int i, int i2, long j);

    public static native void glScissorIndexed(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5);

    public static native void nglScissorIndexedv(int i, long j);

    public static native void nglDepthRangeArrayv(int i, int i2, long j);

    public static native void glDepthRangeIndexed(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglGetFloati_v(int i, int i2, long j);

    public static native void nglGetDoublei_v(int i, int i2, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL41C() {
        throw new UnsupportedOperationException();
    }

    public static void glShaderBinary(@NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglShaderBinary(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static void glGetShaderPrecisionFormat(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
            Checks.check((Buffer) intBuffer2, 1);
        }
        nglGetShaderPrecisionFormat(i, i2, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    @NativeType("void")
    public static int glGetShaderPrecisionFormat(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetShaderPrecisionFormat(i, i2, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetProgramBinary(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        nglGetProgramBinary(i, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glProgramBinary(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglProgramBinary(i, i2, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramv(@NativeType("GLenum") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer) {
        return nglCreateShaderProgramv(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramv(@NativeType("GLenum") int i, @NativeType("GLchar const * const *") CharSequence... charSequenceArr) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memUTF8, charSequenceArr);
            int nglCreateShaderProgramv = nglCreateShaderProgramv(i, charSequenceArr.length, apiArray);
            APIUtil.apiArrayFree(apiArray, charSequenceArr.length);
            return nglCreateShaderProgramv;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramv(@NativeType("GLenum") int i, @NativeType("GLchar const * const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memUTF8, charSequence);
            int nglCreateShaderProgramv = nglCreateShaderProgramv(i, 1, apiArray);
            APIUtil.apiArrayFree(apiArray, 1);
            return nglCreateShaderProgramv;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteProgramPipelines(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteProgramPipelines(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteProgramPipelines(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteProgramPipelines(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenProgramPipelines(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenProgramPipelines(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenProgramPipelines() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenProgramPipelines(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetProgramPipelineiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetProgramPipelineiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetProgramPipelinei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetProgramPipelineiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glProgramUniform1iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform1iv(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform2iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform2iv(i, i2, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform3iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform3iv(i, i2, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform4iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform4iv(i, i2, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform1uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform1uiv(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform2uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform2uiv(i, i2, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform3uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform3uiv(i, i2, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform4uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform4uiv(i, i2, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform1fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform1fv(i, i2, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform2fv(i, i2, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform3fv(i, i2, floatBuffer.remaining() / 3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform4fv(i, i2, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform1dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform1dv(i, i2, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniform2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform2dv(i, i2, doubleBuffer.remaining() >> 1, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniform3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform3dv(i, i2, doubleBuffer.remaining() / 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniform4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniform4dv(i, i2, doubleBuffer.remaining() >> 2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix2fv(i, i2, floatBuffer.remaining() >> 2, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix3fv(i, i2, floatBuffer.remaining() / 9, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix4fv(i, i2, floatBuffer.remaining() >> 4, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix2dv(i, i2, doubleBuffer.remaining() >> 2, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix3dv(i, i2, doubleBuffer.remaining() / 9, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix4dv(i, i2, doubleBuffer.remaining() >> 4, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix2x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix2x3fv(i, i2, floatBuffer.remaining() / 6, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix3x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix3x2fv(i, i2, floatBuffer.remaining() / 6, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix2x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix2x4fv(i, i2, floatBuffer.remaining() >> 3, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix4x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix4x2fv(i, i2, floatBuffer.remaining() >> 3, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix3x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix3x4fv(i, i2, floatBuffer.remaining() / 12, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix4x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix4x3fv(i, i2, floatBuffer.remaining() / 12, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix2x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix2x3dv(i, i2, doubleBuffer.remaining() / 6, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix3x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix3x2dv(i, i2, doubleBuffer.remaining() / 6, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix2x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix2x4dv(i, i2, doubleBuffer.remaining() >> 3, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix4x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix4x2dv(i, i2, doubleBuffer.remaining() >> 3, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix3x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix3x4dv(i, i2, doubleBuffer.remaining() / 12, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniformMatrix4x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglProgramUniformMatrix4x3dv(i, i2, doubleBuffer.remaining() / 12, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetProgramPipelineInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetProgramPipelineInfoLog(i, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetProgramPipelineInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        ByteBuffer memAlloc = MemoryUtil.memAlloc(i2);
        try {
            IntBuffer ints = stackGet.ints(0);
            nglGetProgramPipelineInfoLog(i, i2, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(memAlloc));
            return MemoryUtil.memUTF8(memAlloc, ints.get(0));
        } finally {
            MemoryUtil.memFree(memAlloc);
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetProgramPipelineInfoLog(@NativeType("GLuint") int i) {
        return glGetProgramPipelineInfoLog(i, glGetProgramPipelinei(i, 35716));
    }

    public static void glVertexAttribL1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglVertexAttribL1dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribL2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglVertexAttribL2dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribL3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglVertexAttribL3dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribL4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglVertexAttribL4dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglVertexAttribLPointer(i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        nglVertexAttribLPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribLPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglVertexAttribLPointer(i, i2, 5130, i3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetVertexAttribLdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglGetVertexAttribLdv(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glViewportArrayv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglViewportArrayv(i, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glViewportIndexedfv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglViewportIndexedfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glScissorArrayv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglScissorArrayv(i, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glScissorIndexedv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglScissorIndexedv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glDepthRangeArrayv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglDepthRangeArrayv(i, doubleBuffer.remaining() >> 1, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetFloati_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetFloati_v(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetFloati(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetFloati_v(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetDoublei_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglGetDoublei_v(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetDoublei(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetDoublei_v(i, i2, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glShaderBinary(@NativeType("GLuint const *") int[] iArr, @NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glShaderBinary;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(iArr.length, iArr, i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), j);
    }

    public static void glGetShaderPrecisionFormat(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        long j = GL.getICD().glGetShaderPrecisionFormat;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
            Checks.check(iArr2, 1);
        }
        JNI.callPPV(i, i2, iArr, iArr2, j);
    }

    public static void glGetProgramBinary(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("void *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetProgramBinary;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
        }
        JNI.callPPPV(i, byteBuffer.remaining(), iArr, iArr2, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glDeleteProgramPipelines(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteProgramPipelines;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenProgramPipelines(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenProgramPipelines;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetProgramPipelineiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetProgramPipelineiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glProgramUniform1iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform1iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glProgramUniform2iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 1, iArr, j);
    }

    public static void glProgramUniform3iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length / 3, iArr, j);
    }

    public static void glProgramUniform4iv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 2, iArr, j);
    }

    public static void glProgramUniform1uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform1uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glProgramUniform2uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform2uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 1, iArr, j);
    }

    public static void glProgramUniform3uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length / 3, iArr, j);
    }

    public static void glProgramUniform4uiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 2, iArr, j);
    }

    public static void glProgramUniform1fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform1fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length, fArr, j);
    }

    public static void glProgramUniform2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 1, fArr, j);
    }

    public static void glProgramUniform3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 3, fArr, j);
    }

    public static void glProgramUniform4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 2, fArr, j);
    }

    public static void glProgramUniform1dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform1dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length, dArr, j);
    }

    public static void glProgramUniform2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 1, dArr, j);
    }

    public static void glProgramUniform3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 3, dArr, j);
    }

    public static void glProgramUniform4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniform4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 2, dArr, j);
    }

    public static void glProgramUniformMatrix2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 2, z, fArr, j);
    }

    public static void glProgramUniformMatrix3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 9, z, fArr, j);
    }

    public static void glProgramUniformMatrix4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 4, z, fArr, j);
    }

    public static void glProgramUniformMatrix2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 2, z, dArr, j);
    }

    public static void glProgramUniformMatrix3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 9, z, dArr, j);
    }

    public static void glProgramUniformMatrix4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 4, z, dArr, j);
    }

    public static void glProgramUniformMatrix2x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix2x3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 6, z, fArr, j);
    }

    public static void glProgramUniformMatrix3x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix3x2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 6, z, fArr, j);
    }

    public static void glProgramUniformMatrix2x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix2x4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 3, z, fArr, j);
    }

    public static void glProgramUniformMatrix4x2fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix4x2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 3, z, fArr, j);
    }

    public static void glProgramUniformMatrix3x4fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix3x4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 12, z, fArr, j);
    }

    public static void glProgramUniformMatrix4x3fv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix4x3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 12, z, fArr, j);
    }

    public static void glProgramUniformMatrix2x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix2x3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 6, z, dArr, j);
    }

    public static void glProgramUniformMatrix3x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix3x2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 6, z, dArr, j);
    }

    public static void glProgramUniformMatrix2x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix2x4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 3, z, dArr, j);
    }

    public static void glProgramUniformMatrix4x2dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix4x2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length >> 3, z, dArr, j);
    }

    public static void glProgramUniformMatrix3x4dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix3x4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 12, z, dArr, j);
    }

    public static void glProgramUniformMatrix4x3dv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramUniformMatrix4x3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr.length / 12, z, dArr, j);
    }

    public static void glGetProgramPipelineInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetProgramPipelineInfoLog;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glVertexAttribL1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL1dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttribL2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttribL3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttribL4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttribL4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glGetVertexAttribLdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetVertexAttribLdv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glViewportArrayv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glViewportArrayv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 2, fArr, j);
    }

    public static void glViewportIndexedfv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glViewportIndexedfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glScissorArrayv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glScissorArrayv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 2, iArr, j);
    }

    public static void glScissorIndexedv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glScissorIndexedv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glDepthRangeArrayv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glDepthRangeArrayv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length >> 1, dArr, j);
    }

    public static void glGetFloati_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetFloati_v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetDoublei_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetDoublei_v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, i2, dArr, j);
    }
}
