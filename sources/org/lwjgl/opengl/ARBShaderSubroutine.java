package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBShaderSubroutine.class */
public class ARBShaderSubroutine {
    public static final int GL_ACTIVE_SUBROUTINES = 36325;
    public static final int GL_ACTIVE_SUBROUTINE_UNIFORMS = 36326;
    public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_LOCATIONS = 36423;
    public static final int GL_ACTIVE_SUBROUTINE_MAX_LENGTH = 36424;
    public static final int GL_ACTIVE_SUBROUTINE_UNIFORM_MAX_LENGTH = 36425;
    public static final int GL_MAX_SUBROUTINES = 36327;
    public static final int GL_MAX_SUBROUTINE_UNIFORM_LOCATIONS = 36328;
    public static final int GL_NUM_COMPATIBLE_SUBROUTINES = 36426;
    public static final int GL_COMPATIBLE_SUBROUTINES = 36427;

    static {
        GL.initialize();
    }

    protected ARBShaderSubroutine() {
        throw new UnsupportedOperationException();
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
}
