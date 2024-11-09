package org.lwjgl.opengl;

import java.nio.LongBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDGPUShaderInt64.class */
public class AMDGPUShaderInt64 {
    public static final int GL_INT64_NV = 5134;
    public static final int GL_UNSIGNED_INT64_NV = 5135;
    public static final int GL_INT8_NV = 36832;
    public static final int GL_INT8_VEC2_NV = 36833;
    public static final int GL_INT8_VEC3_NV = 36834;
    public static final int GL_INT8_VEC4_NV = 36835;
    public static final int GL_INT16_NV = 36836;
    public static final int GL_INT16_VEC2_NV = 36837;
    public static final int GL_INT16_VEC3_NV = 36838;
    public static final int GL_INT16_VEC4_NV = 36839;
    public static final int GL_INT64_VEC2_NV = 36841;
    public static final int GL_INT64_VEC3_NV = 36842;
    public static final int GL_INT64_VEC4_NV = 36843;
    public static final int GL_UNSIGNED_INT8_NV = 36844;
    public static final int GL_UNSIGNED_INT8_VEC2_NV = 36845;
    public static final int GL_UNSIGNED_INT8_VEC3_NV = 36846;
    public static final int GL_UNSIGNED_INT8_VEC4_NV = 36847;
    public static final int GL_UNSIGNED_INT16_NV = 36848;
    public static final int GL_UNSIGNED_INT16_VEC2_NV = 36849;
    public static final int GL_UNSIGNED_INT16_VEC3_NV = 36850;
    public static final int GL_UNSIGNED_INT16_VEC4_NV = 36851;
    public static final int GL_UNSIGNED_INT64_VEC2_NV = 36853;
    public static final int GL_UNSIGNED_INT64_VEC3_NV = 36854;
    public static final int GL_UNSIGNED_INT64_VEC4_NV = 36855;
    public static final int GL_FLOAT16_NV = 36856;
    public static final int GL_FLOAT16_VEC2_NV = 36857;
    public static final int GL_FLOAT16_VEC3_NV = 36858;
    public static final int GL_FLOAT16_VEC4_NV = 36859;

    static {
        GL.initialize();
    }

    protected AMDGPUShaderInt64() {
        throw new UnsupportedOperationException();
    }

    public static void glUniform1i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j) {
        NVGPUShader5.glUniform1i64NV(i, j);
    }

    public static void glUniform2i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2) {
        NVGPUShader5.glUniform2i64NV(i, j, j2);
    }

    public static void glUniform3i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3) {
        NVGPUShader5.glUniform3i64NV(i, j, j2, j3);
    }

    public static void glUniform4i64NV(@NativeType("GLint") int i, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3, @NativeType("GLint64EXT") long j4) {
        NVGPUShader5.glUniform4i64NV(i, j, j2, j3, j4);
    }

    public static void nglUniform1i64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform1i64vNV(i, i2, j);
    }

    public static void glUniform1i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform1i64vNV(i, longBuffer);
    }

    public static void nglUniform2i64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform2i64vNV(i, i2, j);
    }

    public static void glUniform2i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform2i64vNV(i, longBuffer);
    }

    public static void nglUniform3i64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform3i64vNV(i, i2, j);
    }

    public static void glUniform3i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform3i64vNV(i, longBuffer);
    }

    public static void nglUniform4i64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform4i64vNV(i, i2, j);
    }

    public static void glUniform4i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform4i64vNV(i, longBuffer);
    }

    public static void glUniform1ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j) {
        NVGPUShader5.glUniform1ui64NV(i, j);
    }

    public static void glUniform2ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2) {
        NVGPUShader5.glUniform2ui64NV(i, j, j2);
    }

    public static void glUniform3ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3) {
        NVGPUShader5.glUniform3ui64NV(i, j, j2, j3);
    }

    public static void glUniform4ui64NV(@NativeType("GLint") int i, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3, @NativeType("GLuint64EXT") long j4) {
        NVGPUShader5.glUniform4ui64NV(i, j, j2, j3, j4);
    }

    public static void nglUniform1ui64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform1ui64vNV(i, i2, j);
    }

    public static void glUniform1ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform1ui64vNV(i, longBuffer);
    }

    public static void nglUniform2ui64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform2ui64vNV(i, i2, j);
    }

    public static void glUniform2ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform2ui64vNV(i, longBuffer);
    }

    public static void nglUniform3ui64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform3ui64vNV(i, i2, j);
    }

    public static void glUniform3ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform3ui64vNV(i, longBuffer);
    }

    public static void nglUniform4ui64vNV(int i, int i2, long j) {
        NVGPUShader5.nglUniform4ui64vNV(i, i2, j);
    }

    public static void glUniform4ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glUniform4ui64vNV(i, longBuffer);
    }

    public static void nglGetUniformi64vNV(int i, int i2, long j) {
        NVGPUShader5.nglGetUniformi64vNV(i, i2, j);
    }

    public static void glGetUniformi64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT *") LongBuffer longBuffer) {
        NVGPUShader5.glGetUniformi64vNV(i, i2, longBuffer);
    }

    @NativeType("void")
    public static long glGetUniformi64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return NVGPUShader5.glGetUniformi64NV(i, i2);
    }

    public static void nglGetUniformui64vNV(int i, int i2, long j) {
        NVShaderBufferLoad.nglGetUniformui64vNV(i, i2, j);
    }

    public static void glGetUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT *") LongBuffer longBuffer) {
        NVShaderBufferLoad.glGetUniformui64vNV(i, i2, longBuffer);
    }

    @NativeType("void")
    public static long glGetUniformui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return NVShaderBufferLoad.glGetUniformui64NV(i, i2);
    }

    public static void glProgramUniform1i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j) {
        NVGPUShader5.glProgramUniform1i64NV(i, i2, j);
    }

    public static void glProgramUniform2i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2) {
        NVGPUShader5.glProgramUniform2i64NV(i, i2, j, j2);
    }

    public static void glProgramUniform3i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3) {
        NVGPUShader5.glProgramUniform3i64NV(i, i2, j, j2, j3);
    }

    public static void glProgramUniform4i64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT") long j, @NativeType("GLint64EXT") long j2, @NativeType("GLint64EXT") long j3, @NativeType("GLint64EXT") long j4) {
        NVGPUShader5.glProgramUniform4i64NV(i, i2, j, j2, j3, j4);
    }

    public static void nglProgramUniform1i64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform1i64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform1i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform1i64vNV(i, i2, longBuffer);
    }

    public static void nglProgramUniform2i64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform2i64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform2i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform2i64vNV(i, i2, longBuffer);
    }

    public static void nglProgramUniform3i64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform3i64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform3i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform3i64vNV(i, i2, longBuffer);
    }

    public static void nglProgramUniform4i64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform4i64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform4i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform4i64vNV(i, i2, longBuffer);
    }

    public static void glProgramUniform1ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j) {
        NVGPUShader5.glProgramUniform1ui64NV(i, i2, j);
    }

    public static void glProgramUniform2ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2) {
        NVGPUShader5.glProgramUniform2ui64NV(i, i2, j, j2);
    }

    public static void glProgramUniform3ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3) {
        NVGPUShader5.glProgramUniform3ui64NV(i, i2, j, j2, j3);
    }

    public static void glProgramUniform4ui64NV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT") long j, @NativeType("GLuint64EXT") long j2, @NativeType("GLuint64EXT") long j3, @NativeType("GLuint64EXT") long j4) {
        NVGPUShader5.glProgramUniform4ui64NV(i, i2, j, j2, j3, j4);
    }

    public static void nglProgramUniform1ui64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform1ui64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform1ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform1ui64vNV(i, i2, longBuffer);
    }

    public static void nglProgramUniform2ui64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform2ui64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform2ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform2ui64vNV(i, i2, longBuffer);
    }

    public static void nglProgramUniform3ui64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform3ui64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform3ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform3ui64vNV(i, i2, longBuffer);
    }

    public static void nglProgramUniform4ui64vNV(int i, int i2, int i3, long j) {
        NVGPUShader5.nglProgramUniform4ui64vNV(i, i2, i3, j);
    }

    public static void glProgramUniform4ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") LongBuffer longBuffer) {
        NVGPUShader5.glProgramUniform4ui64vNV(i, i2, longBuffer);
    }

    public static void glUniform1i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glUniform1i64vNV(i, jArr);
    }

    public static void glUniform2i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glUniform2i64vNV(i, jArr);
    }

    public static void glUniform3i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glUniform3i64vNV(i, jArr);
    }

    public static void glUniform4i64vNV(@NativeType("GLint") int i, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glUniform4i64vNV(i, jArr);
    }

    public static void glUniform1ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        NVGPUShader5.glUniform1ui64vNV(i, jArr);
    }

    public static void glUniform2ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT *") long[] jArr) {
        NVGPUShader5.glUniform2ui64vNV(i, jArr);
    }

    public static void glUniform3ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        NVGPUShader5.glUniform3ui64vNV(i, jArr);
    }

    public static void glUniform4ui64vNV(@NativeType("GLint") int i, @NativeType("GLuint64EXT const *") long[] jArr) {
        NVGPUShader5.glUniform4ui64vNV(i, jArr);
    }

    public static void glGetUniformi64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT *") long[] jArr) {
        NVGPUShader5.glGetUniformi64vNV(i, i2, jArr);
    }

    public static void glGetUniformui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT *") long[] jArr) {
        NVShaderBufferLoad.glGetUniformui64vNV(i, i2, jArr);
    }

    public static void glProgramUniform1i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform1i64vNV(i, i2, jArr);
    }

    public static void glProgramUniform2i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform2i64vNV(i, i2, jArr);
    }

    public static void glProgramUniform3i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform3i64vNV(i, i2, jArr);
    }

    public static void glProgramUniform4i64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform4i64vNV(i, i2, jArr);
    }

    public static void glProgramUniform1ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform1ui64vNV(i, i2, jArr);
    }

    public static void glProgramUniform2ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform2ui64vNV(i, i2, jArr);
    }

    public static void glProgramUniform3ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform3ui64vNV(i, i2, jArr);
    }

    public static void glProgramUniform4ui64vNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint64EXT const *") long[] jArr) {
        NVGPUShader5.glProgramUniform4ui64vNV(i, i2, jArr);
    }
}
