package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL40C.class */
public class GL40C extends GL33C {
    public static final int GL_DRAW_INDIRECT_BUFFER = 36671;
    public static final int GL_DRAW_INDIRECT_BUFFER_BINDING = 36675;
    public static final int GL_GEOMETRY_SHADER_INVOCATIONS = 34943;
    public static final int GL_MAX_GEOMETRY_SHADER_INVOCATIONS = 36442;
    public static final int GL_MIN_FRAGMENT_INTERPOLATION_OFFSET = 36443;
    public static final int GL_MAX_FRAGMENT_INTERPOLATION_OFFSET = 36444;
    public static final int GL_FRAGMENT_INTERPOLATION_OFFSET_BITS = 36445;
    public static final int GL_DOUBLE_VEC2 = 36860;
    public static final int GL_DOUBLE_VEC3 = 36861;
    public static final int GL_DOUBLE_VEC4 = 36862;
    public static final int GL_DOUBLE_MAT2 = 36678;
    public static final int GL_DOUBLE_MAT3 = 36679;
    public static final int GL_DOUBLE_MAT4 = 36680;
    public static final int GL_DOUBLE_MAT2x3 = 36681;
    public static final int GL_DOUBLE_MAT2x4 = 36682;
    public static final int GL_DOUBLE_MAT3x2 = 36683;
    public static final int GL_DOUBLE_MAT3x4 = 36684;
    public static final int GL_DOUBLE_MAT4x2 = 36685;
    public static final int GL_DOUBLE_MAT4x3 = 36686;
    public static final int GL_SAMPLE_SHADING = 35894;
    public static final int GL_MIN_SAMPLE_SHADING_VALUE = 35895;
    public static final int GL_ACTIVE_SUBROUTINES = 36325;
    public static final int GL_ACTIVE_SUBROUTINE_UNIFORMS = 36326;
    public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 36423;
    public static final int GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 36424;
    public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 36425;
    public static final int GL_MAX_SUBROUTINES = 36327;
    public static final int GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 36328;
    public static final int GL_NUM_COMPATIBLE_SUBROUTINES = 36426;
    public static final int GL_COMPATIBLE_SUBROUTINES = 36427;
    public static final int GL_PATCHES = 14;
    public static final int GL_PATCH_VERTICES = 36466;
    public static final int GL_PATCH_DEFAULT_INNER_LEVEL = 36467;
    public static final int GL_PATCH_DEFAULT_OUTER_LEVEL = 36468;
    public static final int GL_TESS_CONTROL_OUTPUT_VERTICES = 36469;
    public static final int GL_TESS_GEN_MODE = 36470;
    public static final int GL_TESS_GEN_SPACING = 36471;
    public static final int GL_TESS_GEN_VERTEX_ORDER = 36472;
    public static final int GL_TESS_GEN_POINT_MODE = 36473;
    public static final int GL_ISOLINES = 36474;
    public static final int GL_FRACTIONAL_ODD = 36475;
    public static final int GL_FRACTIONAL_EVEN = 36476;
    public static final int GL_MAX_PATCH_VERTICES = 36477;
    public static final int GL_MAX_TESS_GEN_LEVEL = 36478;
    public static final int GL_MAX_TESS_CONTROL_UNIFORM_COMPONENTS = 36479;
    public static final int GL_MAX_TESS_EVALUATION_UNIFORM_COMPONENTS = 36480;
    public static final int GL_MAX_TESS_CONTROL_TEXTURE_IMAGE_UNITS = 36481;
    public static final int GL_MAX_TESS_EVALUATION_TEXTURE_IMAGE_UNITS = 36482;
    public static final int GL_MAX_TESS_CONTROL_OUTPUT_COMPONENTS = 36483;
    public static final int GL_MAX_TESS_PATCH_COMPONENTS = 36484;
    public static final int GL_MAX_TESS_CONTROL_TOTAL_OUTPUT_COMPONENTS = 36485;
    public static final int GL_MAX_TESS_EVALUATION_OUTPUT_COMPONENTS = 36486;
    public static final int GL_MAX_TESS_CONTROL_UNIFORM_BLOCKS = 36489;
    public static final int GL_MAX_TESS_EVALUATION_UNIFORM_BLOCKS = 36490;
    public static final int GL_MAX_TESS_CONTROL_INPUT_COMPONENTS = 34924;
    public static final int GL_MAX_TESS_EVALUATION_INPUT_COMPONENTS = 34925;
    public static final int GL_MAX_COMBINED_TESS_CONTROL_UNIFORM_COMPONENTS = 36382;
    public static final int GL_MAX_COMBINED_TESS_EVALUATION_UNIFORM_COMPONENTS = 36383;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_CONTROL_SHADER = 34032;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_TESS_EVALUATION_SHADER = 34033;
    public static final int GL_TESS_EVALUATION_SHADER = 36487;
    public static final int GL_TESS_CONTROL_SHADER = 36488;
    public static final int GL_TEXTURE_CUBE_MAP_ARRAY = 36873;
    public static final int GL_TEXTURE_BINDING_CUBE_MAP_ARRAY = 36874;
    public static final int GL_PROXY_TEXTURE_CUBE_MAP_ARRAY = 36875;
    public static final int GL_SAMPLER_CUBE_MAP_ARRAY = 36876;
    public static final int GL_SAMPLER_CUBE_MAP_ARRAY_SHADOW = 36877;
    public static final int GL_INT_SAMPLER_CUBE_MAP_ARRAY = 36878;
    public static final int GL_UNSIGNED_INT_SAMPLER_CUBE_MAP_ARRAY = 36879;
    public static final int GL_MIN_PROGRAM_TEXTURE_GATHER_OFFSET = 36446;
    public static final int GL_MAX_PROGRAM_TEXTURE_GATHER_OFFSET = 36447;
    public static final int GL_TRANSFORM_FEEDBACK = 36386;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_PAUSED = 36387;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_ACTIVE = 36388;
    public static final int GL_TRANSFORM_FEEDBACK_BINDING = 36389;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_BUFFERS = 36464;
    public static final int GL_MAX_VERTEX_STREAMS = 36465;

    public static native void glBlendEquationi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glBlendEquationSeparatei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3);

    public static native void glBlendFunci(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3);

    public static native void glBlendFuncSeparatei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5);

    public static native void nglDrawArraysIndirect(int i, long j);

    public static native void nglDrawElementsIndirect(int i, int i2, long j);

    public static native void glUniform1d(@NativeType("GLint") int i, @NativeType("GLdouble") double d);

    public static native void glUniform2d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glUniform3d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glUniform4d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglUniform1dv(int i, int i2, long j);

    public static native void nglUniform2dv(int i, int i2, long j);

    public static native void nglUniform3dv(int i, int i2, long j);

    public static native void nglUniform4dv(int i, int i2, long j);

    public static native void nglUniformMatrix2dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix3dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix4dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix2x3dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix2x4dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix3x2dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix3x4dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix4x2dv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix4x3dv(int i, int i2, boolean z, long j);

    public static native void nglGetUniformdv(int i, int i2, long j);

    public static native void glMinSampleShading(@NativeType("GLfloat") float f);

    public static native int nglGetSubroutineUniformLocation(int i, int i2, long j);

    public static native int nglGetSubroutineIndex(int i, int i2, long j);

    public static native void nglGetActiveSubroutineUniformiv(int i, int i2, int i3, int i4, long j);

    public static native void nglGetActiveSubroutineUniformName(int i, int i2, int i3, int i4, long j, long j2);

    public static native void nglGetActiveSubroutineName(int i, int i2, int i3, int i4, long j, long j2);

    public static native void nglUniformSubroutinesuiv(int i, int i2, long j);

    public static native void nglGetUniformSubroutineuiv(int i, int i2, long j);

    public static native void nglGetProgramStageiv(int i, int i2, int i3, long j);

    public static native void glPatchParameteri(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void nglPatchParameterfv(int i, long j);

    public static native void glBindTransformFeedback(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglDeleteTransformFeedbacks(int i, long j);

    public static native void nglGenTransformFeedbacks(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsTransformFeedback(@NativeType("GLuint") int i);

    public static native void glPauseTransformFeedback();

    public static native void glResumeTransformFeedback();

    public static native void glDrawTransformFeedback(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glDrawTransformFeedbackStream(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glBeginQueryIndexed(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glEndQueryIndexed(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglGetQueryIndexediv(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL40C() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 16);
        }
        nglDrawArraysIndirect(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") long j) {
        nglDrawArraysIndirect(i, j);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglDrawArraysIndirect(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 20);
        }
        nglDrawElementsIndirect(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j) {
        nglDrawElementsIndirect(i, i2, j);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 5);
        }
        nglDrawElementsIndirect(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform1dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniform1dv(i, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniform2dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniform2dv(i, doubleBuffer.remaining() >> 1, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniform3dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniform3dv(i, doubleBuffer.remaining() / 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniform4dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniform4dv(i, doubleBuffer.remaining() >> 2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix2dv(i, doubleBuffer.remaining() >> 2, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix3dv(i, doubleBuffer.remaining() / 9, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix4dv(i, doubleBuffer.remaining() >> 4, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix2x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix2x3dv(i, doubleBuffer.remaining() / 6, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix2x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix2x4dv(i, doubleBuffer.remaining() >> 3, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix3x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix3x2dv(i, doubleBuffer.remaining() / 6, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix3x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix3x4dv(i, doubleBuffer.remaining() / 12, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix4x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix4x2dv(i, doubleBuffer.remaining() >> 3, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glUniformMatrix4x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        nglUniformMatrix4x3dv(i, doubleBuffer.remaining() / 12, z, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglGetUniformdv(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetUniformd(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetUniformdv(i, i2, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLint")
    public static int glGetSubroutineUniformLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetSubroutineUniformLocation(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetSubroutineUniformLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetSubroutineUniformLocation(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLuint")
    public static int glGetSubroutineIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetSubroutineIndex(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLuint")
    public static int glGetSubroutineIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetSubroutineIndex(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveSubroutineUniformiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetActiveSubroutineUniformiv(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetActiveSubroutineUniformi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetActiveSubroutineUniformiv(i, i2, i3, i4, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetActiveSubroutineUniformName(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i4);
            nglGetActiveSubroutineUniformName(i, i2, i3, i4, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return glGetActiveSubroutineUniformName(i, i2, i3, glGetActiveSubroutineUniformi(i, i2, i3, 35385));
    }

    public static void glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetActiveSubroutineName(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i4);
            nglGetActiveSubroutineName(i, i2, i3, i4, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return glGetActiveSubroutineName(i, i2, i3, glGetProgramStagei(i, i2, 36424));
    }

    public static void glUniformSubroutinesuiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniformSubroutinesuiv(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniformSubroutinesui(@NativeType("GLenum") int i, @NativeType("GLuint const *") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglUniformSubroutinesuiv(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetUniformSubroutineuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetUniformSubroutineuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetUniformSubroutineui(@NativeType("GLenum") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetUniformSubroutineuiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetProgramStageiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetProgramStageiv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetProgramStagei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetProgramStageiv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glPatchParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) floatBuffer, GL11.glGetInteger(36466));
        }
        nglPatchParameterfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteTransformFeedbacks(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteTransformFeedbacks(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenTransformFeedbacks(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenTransformFeedbacks(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenTransformFeedbacks() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenTransformFeedbacks(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetQueryIndexediv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetQueryIndexediv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetQueryIndexedi(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetQueryIndexediv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glDrawArraysIndirect;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glDrawElementsIndirect;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 5);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glUniform1dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniform1dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length, dArr, j);
    }

    public static void glUniform2dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniform2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length >> 1, dArr, j);
    }

    public static void glUniform3dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniform3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length / 3, dArr, j);
    }

    public static void glUniform4dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniform4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length >> 2, dArr, j);
    }

    public static void glUniformMatrix2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length >> 2, z, dArr, j);
    }

    public static void glUniformMatrix3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length / 9, z, dArr, j);
    }

    public static void glUniformMatrix4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length >> 4, z, dArr, j);
    }

    public static void glUniformMatrix2x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix2x3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length / 6, z, dArr, j);
    }

    public static void glUniformMatrix2x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix2x4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length >> 3, z, dArr, j);
    }

    public static void glUniformMatrix3x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix3x2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length / 6, z, dArr, j);
    }

    public static void glUniformMatrix3x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix3x4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length / 12, z, dArr, j);
    }

    public static void glUniformMatrix4x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix4x2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length >> 3, z, dArr, j);
    }

    public static void glUniformMatrix4x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glUniformMatrix4x3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, dArr.length / 12, z, dArr, j);
    }

    public static void glGetUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetUniformdv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glGetActiveSubroutineUniformiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetActiveSubroutineUniformiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveSubroutineUniformName;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, i2, i3, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveSubroutineName;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, i2, i3, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glUniformSubroutinesuiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniformSubroutinesuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glGetUniformSubroutineuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetUniformSubroutineuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetProgramStageiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetProgramStageiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glPatchParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPatchParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            if (Checks.DEBUG) {
                Checks.check(fArr, GL11.glGetInteger(36466));
            }
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteTransformFeedbacks;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenTransformFeedbacks(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenTransformFeedbacks;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetQueryIndexediv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetQueryIndexediv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }
}
