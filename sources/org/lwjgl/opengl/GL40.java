package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL40.class */
public class GL40 extends GL33 {
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

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL40() {
        throw new UnsupportedOperationException();
    }

    public static void glBlendEquationi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        GL40C.glBlendEquationi(i, i2);
    }

    public static void glBlendEquationSeparatei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        GL40C.glBlendEquationSeparatei(i, i2, i3);
    }

    public static void glBlendFunci(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        GL40C.glBlendFunci(i, i2, i3);
    }

    public static void glBlendFuncSeparatei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5) {
        GL40C.glBlendFuncSeparatei(i, i2, i3, i4, i5);
    }

    public static void nglDrawArraysIndirect(int i, long j) {
        GL40C.nglDrawArraysIndirect(i, j);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL40C.glDrawArraysIndirect(i, byteBuffer);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") long j) {
        GL40C.glDrawArraysIndirect(i, j);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer) {
        GL40C.glDrawArraysIndirect(i, intBuffer);
    }

    public static void nglDrawElementsIndirect(int i, int i2, long j) {
        GL40C.nglDrawElementsIndirect(i, i2, j);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL40C.glDrawElementsIndirect(i, i2, byteBuffer);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j) {
        GL40C.glDrawElementsIndirect(i, i2, j);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer) {
        GL40C.glDrawElementsIndirect(i, i2, intBuffer);
    }

    public static void glUniform1d(@NativeType("GLint") int i, @NativeType("GLdouble") double d) {
        GL40C.glUniform1d(i, d);
    }

    public static void glUniform2d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL40C.glUniform2d(i, d, d2);
    }

    public static void glUniform3d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3) {
        GL40C.glUniform3d(i, d, d2, d3);
    }

    public static void glUniform4d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4) {
        GL40C.glUniform4d(i, d, d2, d3, d4);
    }

    public static void nglUniform1dv(int i, int i2, long j) {
        GL40C.nglUniform1dv(i, i2, j);
    }

    public static void glUniform1dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform1dv(i, doubleBuffer);
    }

    public static void nglUniform2dv(int i, int i2, long j) {
        GL40C.nglUniform2dv(i, i2, j);
    }

    public static void glUniform2dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform2dv(i, doubleBuffer);
    }

    public static void nglUniform3dv(int i, int i2, long j) {
        GL40C.nglUniform3dv(i, i2, j);
    }

    public static void glUniform3dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform3dv(i, doubleBuffer);
    }

    public static void nglUniform4dv(int i, int i2, long j) {
        GL40C.nglUniform4dv(i, i2, j);
    }

    public static void glUniform4dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniform4dv(i, doubleBuffer);
    }

    public static void nglUniformMatrix2dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix2dv(i, i2, z, j);
    }

    public static void glUniformMatrix2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix2dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix3dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix3dv(i, i2, z, j);
    }

    public static void glUniformMatrix3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix3dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix4dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix4dv(i, i2, z, j);
    }

    public static void glUniformMatrix4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix4dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix2x3dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix2x3dv(i, i2, z, j);
    }

    public static void glUniformMatrix2x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix2x3dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix2x4dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix2x4dv(i, i2, z, j);
    }

    public static void glUniformMatrix2x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix2x4dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix3x2dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix3x2dv(i, i2, z, j);
    }

    public static void glUniformMatrix3x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix3x2dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix3x4dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix3x4dv(i, i2, z, j);
    }

    public static void glUniformMatrix3x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix3x4dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix4x2dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix4x2dv(i, i2, z, j);
    }

    public static void glUniformMatrix4x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix4x2dv(i, z, doubleBuffer);
    }

    public static void nglUniformMatrix4x3dv(int i, int i2, boolean z, long j) {
        GL40C.nglUniformMatrix4x3dv(i, i2, z, j);
    }

    public static void glUniformMatrix4x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL40C.glUniformMatrix4x3dv(i, z, doubleBuffer);
    }

    public static void nglGetUniformdv(int i, int i2, long j) {
        GL40C.nglGetUniformdv(i, i2, j);
    }

    public static void glGetUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL40C.glGetUniformdv(i, i2, doubleBuffer);
    }

    @NativeType("void")
    public static double glGetUniformd(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL40C.glGetUniformd(i, i2);
    }

    public static void glMinSampleShading(@NativeType("GLfloat") float f) {
        GL40C.glMinSampleShading(f);
    }

    public static int nglGetSubroutineUniformLocation(int i, int i2, long j) {
        return GL40C.nglGetSubroutineUniformLocation(i, i2, j);
    }

    @NativeType("GLint")
    public static int glGetSubroutineUniformLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL40C.glGetSubroutineUniformLocation(i, i2, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetSubroutineUniformLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL40C.glGetSubroutineUniformLocation(i, i2, charSequence);
    }

    public static int nglGetSubroutineIndex(int i, int i2, long j) {
        return GL40C.nglGetSubroutineIndex(i, i2, j);
    }

    @NativeType("GLuint")
    public static int glGetSubroutineIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL40C.glGetSubroutineIndex(i, i2, byteBuffer);
    }

    @NativeType("GLuint")
    public static int glGetSubroutineIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL40C.glGetSubroutineIndex(i, i2, charSequence);
    }

    public static void nglGetActiveSubroutineUniformiv(int i, int i2, int i3, int i4, long j) {
        GL40C.nglGetActiveSubroutineUniformiv(i, i2, i3, i4, j);
    }

    public static void glGetActiveSubroutineUniformiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") IntBuffer intBuffer) {
        GL40C.glGetActiveSubroutineUniformiv(i, i2, i3, i4, intBuffer);
    }

    @NativeType("void")
    public static int glGetActiveSubroutineUniformi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4) {
        return GL40C.glGetActiveSubroutineUniformi(i, i2, i3, i4);
    }

    public static void nglGetActiveSubroutineUniformName(int i, int i2, int i3, int i4, long j, long j2) {
        GL40C.nglGetActiveSubroutineUniformName(i, i2, i3, i4, j, j2);
    }

    public static void glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL40C.glGetActiveSubroutineUniformName(i, i2, i3, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        return GL40C.glGetActiveSubroutineUniformName(i, i2, i3, i4);
    }

    @NativeType("void")
    public static String glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return glGetActiveSubroutineUniformName(i, i2, i3, glGetActiveSubroutineUniformi(i, i2, i3, 35385));
    }

    public static void nglGetActiveSubroutineName(int i, int i2, int i3, int i4, long j, long j2) {
        GL40C.nglGetActiveSubroutineName(i, i2, i3, i4, j, j2);
    }

    public static void glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL40C.glGetActiveSubroutineName(i, i2, i3, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        return GL40C.glGetActiveSubroutineName(i, i2, i3, i4);
    }

    @NativeType("void")
    public static String glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return glGetActiveSubroutineName(i, i2, i3, glGetProgramStagei(i, i2, 36424));
    }

    public static void nglUniformSubroutinesuiv(int i, int i2, long j) {
        GL40C.nglUniformSubroutinesuiv(i, i2, j);
    }

    public static void glUniformSubroutinesuiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL40C.glUniformSubroutinesuiv(i, intBuffer);
    }

    public static void glUniformSubroutinesui(@NativeType("GLenum") int i, @NativeType("GLuint const *") int i2) {
        GL40C.glUniformSubroutinesui(i, i2);
    }

    public static void nglGetUniformSubroutineuiv(int i, int i2, long j) {
        GL40C.nglGetUniformSubroutineuiv(i, i2, j);
    }

    public static void glGetUniformSubroutineuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL40C.glGetUniformSubroutineuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetUniformSubroutineui(@NativeType("GLenum") int i, @NativeType("GLint") int i2) {
        return GL40C.glGetUniformSubroutineui(i, i2);
    }

    public static void nglGetProgramStageiv(int i, int i2, int i3, long j) {
        GL40C.nglGetProgramStageiv(i, i2, i3, j);
    }

    public static void glGetProgramStageiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL40C.glGetProgramStageiv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetProgramStagei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL40C.glGetProgramStagei(i, i2, i3);
    }

    public static void glPatchParameteri(@NativeType("GLenum") int i, @NativeType("GLint") int i2) {
        GL40C.glPatchParameteri(i, i2);
    }

    public static void nglPatchParameterfv(int i, long j) {
        GL40C.nglPatchParameterfv(i, j);
    }

    public static void glPatchParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL40C.glPatchParameterfv(i, floatBuffer);
    }

    public static void glBindTransformFeedback(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL40C.glBindTransformFeedback(i, i2);
    }

    public static void nglDeleteTransformFeedbacks(int i, long j) {
        GL40C.nglDeleteTransformFeedbacks(i, j);
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL40C.glDeleteTransformFeedbacks(intBuffer);
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") int i) {
        GL40C.glDeleteTransformFeedbacks(i);
    }

    public static void nglGenTransformFeedbacks(int i, long j) {
        GL40C.nglGenTransformFeedbacks(i, j);
    }

    public static void glGenTransformFeedbacks(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL40C.glGenTransformFeedbacks(intBuffer);
    }

    @NativeType("void")
    public static int glGenTransformFeedbacks() {
        return GL40C.glGenTransformFeedbacks();
    }

    @NativeType("GLboolean")
    public static boolean glIsTransformFeedback(@NativeType("GLuint") int i) {
        return GL40C.glIsTransformFeedback(i);
    }

    public static void glPauseTransformFeedback() {
        GL40C.glPauseTransformFeedback();
    }

    public static void glResumeTransformFeedback() {
        GL40C.glResumeTransformFeedback();
    }

    public static void glDrawTransformFeedback(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL40C.glDrawTransformFeedback(i, i2);
    }

    public static void glDrawTransformFeedbackStream(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL40C.glDrawTransformFeedbackStream(i, i2, i3);
    }

    public static void glBeginQueryIndexed(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL40C.glBeginQueryIndexed(i, i2, i3);
    }

    public static void glEndQueryIndexed(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL40C.glEndQueryIndexed(i, i2);
    }

    public static void nglGetQueryIndexediv(int i, int i2, int i3, long j) {
        GL40C.nglGetQueryIndexediv(i, i2, i3, j);
    }

    public static void glGetQueryIndexediv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL40C.glGetQueryIndexediv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetQueryIndexedi(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        return GL40C.glGetQueryIndexedi(i, i2, i3);
    }

    public static void glDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr) {
        GL40C.glDrawArraysIndirect(i, iArr);
    }

    public static void glDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr) {
        GL40C.glDrawElementsIndirect(i, i2, iArr);
    }

    public static void glUniform1dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform1dv(i, dArr);
    }

    public static void glUniform2dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform2dv(i, dArr);
    }

    public static void glUniform3dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform3dv(i, dArr);
    }

    public static void glUniform4dv(@NativeType("GLint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniform4dv(i, dArr);
    }

    public static void glUniformMatrix2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix2dv(i, z, dArr);
    }

    public static void glUniformMatrix3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix3dv(i, z, dArr);
    }

    public static void glUniformMatrix4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix4dv(i, z, dArr);
    }

    public static void glUniformMatrix2x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix2x3dv(i, z, dArr);
    }

    public static void glUniformMatrix2x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix2x4dv(i, z, dArr);
    }

    public static void glUniformMatrix3x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix3x2dv(i, z, dArr);
    }

    public static void glUniformMatrix3x4dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix3x4dv(i, z, dArr);
    }

    public static void glUniformMatrix4x2dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix4x2dv(i, z, dArr);
    }

    public static void glUniformMatrix4x3dv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLdouble const *") double[] dArr) {
        GL40C.glUniformMatrix4x3dv(i, z, dArr);
    }

    public static void glGetUniformdv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLdouble *") double[] dArr) {
        GL40C.glGetUniformdv(i, i2, dArr);
    }

    public static void glGetActiveSubroutineUniformiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") int[] iArr) {
        GL40C.glGetActiveSubroutineUniformiv(i, i2, i3, i4, iArr);
    }

    public static void glGetActiveSubroutineUniformName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL40C.glGetActiveSubroutineUniformName(i, i2, i3, iArr, byteBuffer);
    }

    public static void glGetActiveSubroutineName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL40C.glGetActiveSubroutineName(i, i2, i3, iArr, byteBuffer);
    }

    public static void glUniformSubroutinesuiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL40C.glUniformSubroutinesuiv(i, iArr);
    }

    public static void glGetUniformSubroutineuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        GL40C.glGetUniformSubroutineuiv(i, i2, iArr);
    }

    public static void glGetProgramStageiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL40C.glGetProgramStageiv(i, i2, i3, iArr);
    }

    public static void glPatchParameterfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL40C.glPatchParameterfv(i, fArr);
    }

    public static void glDeleteTransformFeedbacks(@NativeType("GLuint const *") int[] iArr) {
        GL40C.glDeleteTransformFeedbacks(iArr);
    }

    public static void glGenTransformFeedbacks(@NativeType("GLuint *") int[] iArr) {
        GL40C.glGenTransformFeedbacks(iArr);
    }

    public static void glGetQueryIndexediv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL40C.glGetQueryIndexediv(i, i2, i3, iArr);
    }
}
