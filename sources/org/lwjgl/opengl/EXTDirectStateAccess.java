package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTDirectStateAccess.class */
public class EXTDirectStateAccess {
    public static final int GL_PROGRAM_MATRIX_EXT = 36397;
    public static final int GL_TRANSPOSE_PROGRAM_MATRIX_EXT = 36398;
    public static final int GL_PROGRAM_MATRIX_STACK_DEPTH_EXT = 36399;

    public static native void glClientAttribDefaultEXT(@NativeType("GLbitfield") int i);

    public static native void glPushClientAttribDefaultEXT(@NativeType("GLbitfield") int i);

    public static native void nglMatrixLoadfEXT(int i, long j);

    public static native void nglMatrixLoaddEXT(int i, long j);

    public static native void nglMatrixMultfEXT(int i, long j);

    public static native void nglMatrixMultdEXT(int i, long j);

    public static native void glMatrixLoadIdentityEXT(@NativeType("GLenum") int i);

    public static native void glMatrixRotatefEXT(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glMatrixRotatedEXT(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void glMatrixScalefEXT(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glMatrixScaledEXT(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glMatrixTranslatefEXT(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glMatrixTranslatedEXT(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glMatrixOrthoEXT(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4, @NativeType("GLdouble") double d5, @NativeType("GLdouble") double d6);

    public static native void glMatrixFrustumEXT(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4, @NativeType("GLdouble") double d5, @NativeType("GLdouble") double d6);

    public static native void glMatrixPushEXT(@NativeType("GLenum") int i);

    public static native void glMatrixPopEXT(@NativeType("GLenum") int i);

    public static native void glTextureParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4);

    public static native void nglTextureParameterivEXT(int i, int i2, int i3, long j);

    public static native void glTextureParameterfEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat") float f);

    public static native void nglTextureParameterfvEXT(int i, int i2, int i3, long j);

    public static native void nglTextureImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglTextureImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void nglTextureSubImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglTextureSubImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void glCopyTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8);

    public static native void glCopyTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLint") int i9);

    public static native void glCopyTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7);

    public static native void glCopyTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9);

    public static native void nglGetTextureImageEXT(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglGetTextureParameterfvEXT(int i, int i2, int i3, long j);

    public static native void nglGetTextureParameterivEXT(int i, int i2, int i3, long j);

    public static native void nglGetTextureLevelParameterfvEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglGetTextureLevelParameterivEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglTextureImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void nglTextureSubImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j);

    public static native void glCopyTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLsizei") int i9, @NativeType("GLsizei") int i10);

    public static native void glBindMultiTextureEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void nglMultiTexCoordPointerEXT(int i, int i2, int i3, int i4, long j);

    public static native void glMultiTexEnvfEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat") float f);

    public static native void nglMultiTexEnvfvEXT(int i, int i2, int i3, long j);

    public static native void glMultiTexEnviEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4);

    public static native void nglMultiTexEnvivEXT(int i, int i2, int i3, long j);

    public static native void glMultiTexGendEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLdouble") double d);

    public static native void nglMultiTexGendvEXT(int i, int i2, int i3, long j);

    public static native void glMultiTexGenfEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat") float f);

    public static native void nglMultiTexGenfvEXT(int i, int i2, int i3, long j);

    public static native void glMultiTexGeniEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4);

    public static native void nglMultiTexGenivEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexEnvfvEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexEnvivEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexGendvEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexGenfvEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexGenivEXT(int i, int i2, int i3, long j);

    public static native void glMultiTexParameteriEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4);

    public static native void nglMultiTexParameterivEXT(int i, int i2, int i3, long j);

    public static native void glMultiTexParameterfEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat") float f);

    public static native void nglMultiTexParameterfvEXT(int i, int i2, int i3, long j);

    public static native void nglMultiTexImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglMultiTexImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void nglMultiTexSubImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglMultiTexSubImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void glCopyMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8);

    public static native void glCopyMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLint") int i9);

    public static native void glCopyMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7);

    public static native void glCopyMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9);

    public static native void nglGetMultiTexImageEXT(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglGetMultiTexParameterfvEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexParameterivEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexLevelParameterfvEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglGetMultiTexLevelParameterivEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglMultiTexImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void nglMultiTexSubImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j);

    public static native void glCopyMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLsizei") int i9, @NativeType("GLsizei") int i10);

    public static native void glEnableClientStateIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glDisableClientStateIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glEnableClientStateiEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glDisableClientStateiEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglGetFloatIndexedvEXT(int i, int i2, long j);

    public static native void nglGetDoubleIndexedvEXT(int i, int i2, long j);

    public static native void nglGetPointerIndexedvEXT(int i, int i2, long j);

    public static native void nglGetFloati_vEXT(int i, int i2, long j);

    public static native void nglGetDoublei_vEXT(int i, int i2, long j);

    public static native void nglGetPointeri_vEXT(int i, int i2, long j);

    public static native void nglNamedProgramStringEXT(int i, int i2, int i3, int i4, long j);

    public static native void glNamedProgramLocalParameter4dEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglNamedProgramLocalParameter4dvEXT(int i, int i2, int i3, long j);

    public static native void glNamedProgramLocalParameter4fEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void nglNamedProgramLocalParameter4fvEXT(int i, int i2, int i3, long j);

    public static native void nglGetNamedProgramLocalParameterdvEXT(int i, int i2, int i3, long j);

    public static native void nglGetNamedProgramLocalParameterfvEXT(int i, int i2, int i3, long j);

    public static native void nglGetNamedProgramivEXT(int i, int i2, int i3, long j);

    public static native void nglGetNamedProgramStringEXT(int i, int i2, int i3, long j);

    public static native void nglCompressedTextureImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void nglCompressedTextureImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglCompressedTextureImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglCompressedTextureSubImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j);

    public static native void nglCompressedTextureSubImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void nglCompressedTextureSubImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglGetCompressedTextureImageEXT(int i, int i2, int i3, long j);

    public static native void nglCompressedMultiTexImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void nglCompressedMultiTexImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglCompressedMultiTexImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglCompressedMultiTexSubImage3DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, long j);

    public static native void nglCompressedMultiTexSubImage2DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, long j);

    public static native void nglCompressedMultiTexSubImage1DEXT(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglGetCompressedMultiTexImageEXT(int i, int i2, int i3, long j);

    public static native void nglMatrixLoadTransposefEXT(int i, long j);

    public static native void nglMatrixLoadTransposedEXT(int i, long j);

    public static native void nglMatrixMultTransposefEXT(int i, long j);

    public static native void nglMatrixMultTransposedEXT(int i, long j);

    public static native void nglNamedBufferDataEXT(int i, long j, long j2, int i2);

    public static native void nglNamedBufferSubDataEXT(int i, long j, long j2, long j3);

    public static native long nglMapNamedBufferEXT(int i, int i2);

    @NativeType("GLboolean")
    public static native boolean glUnmapNamedBufferEXT(@NativeType("GLuint") int i);

    public static native void nglGetNamedBufferParameterivEXT(int i, int i2, long j);

    public static native void nglGetNamedBufferSubDataEXT(int i, long j, long j2, long j3);

    public static native void glProgramUniform1fEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f);

    public static native void glProgramUniform2fEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glProgramUniform3fEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glProgramUniform4fEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glProgramUniform1iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glProgramUniform2iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glProgramUniform3iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glProgramUniform4iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6);

    public static native void nglProgramUniform1fvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2fvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3fvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4fvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform1ivEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2ivEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3ivEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4ivEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniformMatrix2fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x3fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x2fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix2x4fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x2fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix3x4fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void nglProgramUniformMatrix4x3fvEXT(int i, int i2, int i3, boolean z, long j);

    public static native void glTextureBufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    public static native void glMultiTexBufferEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    public static native void nglTextureParameterIivEXT(int i, int i2, int i3, long j);

    public static native void nglTextureParameterIuivEXT(int i, int i2, int i3, long j);

    public static native void nglGetTextureParameterIivEXT(int i, int i2, int i3, long j);

    public static native void nglGetTextureParameterIuivEXT(int i, int i2, int i3, long j);

    public static native void nglMultiTexParameterIivEXT(int i, int i2, int i3, long j);

    public static native void nglMultiTexParameterIuivEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexParameterIivEXT(int i, int i2, int i3, long j);

    public static native void nglGetMultiTexParameterIuivEXT(int i, int i2, int i3, long j);

    public static native void glProgramUniform1uiEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3);

    public static native void glProgramUniform2uiEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4);

    public static native void glProgramUniform3uiEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5);

    public static native void glProgramUniform4uiEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6);

    public static native void nglProgramUniform1uivEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform2uivEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform3uivEXT(int i, int i2, int i3, long j);

    public static native void nglProgramUniform4uivEXT(int i, int i2, int i3, long j);

    public static native void nglNamedProgramLocalParameters4fvEXT(int i, int i2, int i3, int i4, long j);

    public static native void glNamedProgramLocalParameterI4iEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7);

    public static native void nglNamedProgramLocalParameterI4ivEXT(int i, int i2, int i3, long j);

    public static native void nglNamedProgramLocalParametersI4ivEXT(int i, int i2, int i3, int i4, long j);

    public static native void glNamedProgramLocalParameterI4uiEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6, @NativeType("GLuint") int i7);

    public static native void nglNamedProgramLocalParameterI4uivEXT(int i, int i2, int i3, long j);

    public static native void nglNamedProgramLocalParametersI4uivEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglGetNamedProgramLocalParameterIivEXT(int i, int i2, int i3, long j);

    public static native void nglGetNamedProgramLocalParameterIuivEXT(int i, int i2, int i3, long j);

    public static native void glNamedRenderbufferStorageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    public static native void nglGetNamedRenderbufferParameterivEXT(int i, int i2, long j);

    public static native void glNamedRenderbufferStorageMultisampleEXT(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5);

    public static native void glNamedRenderbufferStorageMultisampleCoverageEXT(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6);

    @NativeType("GLenum")
    public static native int glCheckNamedFramebufferStatusEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glNamedFramebufferTexture1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5);

    public static native void glNamedFramebufferTexture2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5);

    public static native void glNamedFramebufferTexture3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6);

    public static native void glNamedFramebufferRenderbufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    public static native void nglGetNamedFramebufferAttachmentParameterivEXT(int i, int i2, int i3, long j);

    public static native void glGenerateTextureMipmapEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glGenerateMultiTexMipmapEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    public static native void glFramebufferDrawBufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void nglFramebufferDrawBuffersEXT(int i, int i2, long j);

    public static native void glFramebufferReadBufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void nglGetFramebufferParameterivEXT(int i, int i2, long j);

    public static native void glNamedCopyBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3);

    public static native void glNamedFramebufferTextureEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4);

    public static native void glNamedFramebufferTextureLayerEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glNamedFramebufferTextureFaceEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5);

    public static native void glTextureRenderbufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void glMultiTexRenderbufferEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void glVertexArrayVertexOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLintptr") long j);

    public static native void glVertexArrayColorOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLintptr") long j);

    public static native void glVertexArrayEdgeFlagOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLintptr") long j);

    public static native void glVertexArrayIndexOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLintptr") long j);

    public static native void glVertexArrayNormalOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLintptr") long j);

    public static native void glVertexArrayTexCoordOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLintptr") long j);

    public static native void glVertexArrayMultiTexCoordOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("GLintptr") long j);

    public static native void glVertexArrayFogCoordOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLintptr") long j);

    public static native void glVertexArraySecondaryColorOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLintptr") long j);

    public static native void glVertexArrayVertexAttribOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i6, @NativeType("GLintptr") long j);

    public static native void glVertexArrayVertexAttribIOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("GLintptr") long j);

    public static native void glEnableVertexArrayEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glDisableVertexArrayEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glEnableVertexArrayAttribEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glDisableVertexArrayAttribEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void nglGetVertexArrayIntegervEXT(int i, int i2, long j);

    public static native void nglGetVertexArrayPointervEXT(int i, int i2, long j);

    public static native void nglGetVertexArrayIntegeri_vEXT(int i, int i2, int i3, long j);

    public static native void nglGetVertexArrayPointeri_vEXT(int i, int i2, int i3, long j);

    public static native long nglMapNamedBufferRangeEXT(int i, long j, long j2, int i2);

    public static native void glFlushMappedNamedBufferRangeEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    static {
        GL.initialize();
    }

    protected EXTDirectStateAccess() {
        throw new UnsupportedOperationException();
    }

    public static void glMatrixLoadfEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglMatrixLoadfEXT(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixLoaddEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglMatrixLoaddEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMatrixMultfEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglMatrixMultfEXT(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixMultdEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglMatrixMultdEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTextureParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTextureParameterivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglTextureParameterfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        nglTextureImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") long j) {
        nglTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") long j) {
        nglTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") long j) {
        nglTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetTextureImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") long j) {
        nglGetTextureImageEXT(i, i2, i3, i4, i5, j);
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetTextureImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") IntBuffer intBuffer) {
        nglGetTextureImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetTextureImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetTextureImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetTextureParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetTextureParameterfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTextureParameterfEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTextureParameterfvEXT(i, i2, i3, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureParameterivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureParameterivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureLevelParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetTextureLevelParameterfvEXT(i, i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTextureLevelParameterfEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTextureLevelParameterfvEXT(i, i2, i3, i4, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureLevelParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureLevelParameterivEXT(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureLevelParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureLevelParameterivEXT(i, i2, i3, i4, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") long j) {
        nglTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") long j) {
        nglTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j);
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") IntBuffer intBuffer) {
        nglTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMultiTexCoordPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        nglMultiTexCoordPointerEXT(i, i2, i3, i4, j);
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMultiTexCoordPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglMultiTexCoordPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMultiTexCoordPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexEnvfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglMultiTexEnvfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexEnvivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMultiTexEnvivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexGendvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglMultiTexGendvEXT(i, i2, i3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexGenfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglMultiTexGenfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexGenivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMultiTexGenivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetMultiTexEnvfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetMultiTexEnvfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetMultiTexEnvfEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetMultiTexEnvfvEXT(i, i2, i3, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexEnvivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMultiTexEnvivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMultiTexEnviEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMultiTexEnvivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexGendvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglGetMultiTexGendvEXT(i, i2, i3, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetMultiTexGendEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetMultiTexGendvEXT(i, i2, i3, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexGenfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetMultiTexGenfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetMultiTexGenfEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetMultiTexGenfvEXT(i, i2, i3, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexGenivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMultiTexGenivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMultiTexGeniEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMultiTexGenivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMultiTexParameterivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMultiTexParameterivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexParameterfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglMultiTexParameterfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        nglMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        nglMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") long j) {
        nglMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") IntBuffer intBuffer) {
        nglMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") long j) {
        nglMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") IntBuffer intBuffer) {
        nglMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") long j) {
        nglMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") IntBuffer intBuffer) {
        nglMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetMultiTexImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") long j) {
        nglGetMultiTexImageEXT(i, i2, i3, i4, i5, j);
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetMultiTexImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") IntBuffer intBuffer) {
        nglGetMultiTexImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetMultiTexImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetMultiTexImageEXT(i, i2, i3, i4, i5, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetMultiTexParameterfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetMultiTexParameterfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetMultiTexParameterfEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetMultiTexParameterfvEXT(i, i2, i3, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexParameterivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMultiTexParameterivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMultiTexParameteriEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMultiTexParameterivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexLevelParameterfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetMultiTexLevelParameterfvEXT(i, i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetMultiTexLevelParameterfEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetMultiTexLevelParameterfvEXT(i, i2, i3, i4, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexLevelParameterivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMultiTexLevelParameterivEXT(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMultiTexLevelParameteriEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMultiTexLevelParameterivEXT(i, i2, i3, i4, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") long j) {
        nglMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") IntBuffer intBuffer) {
        nglMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") long j) {
        nglMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j);
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") IntBuffer intBuffer) {
        nglMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetFloatIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetFloatIndexedvEXT(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetFloatIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetFloatIndexedvEXT(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetDoubleIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglGetDoubleIndexedvEXT(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetDoubleIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetDoubleIndexedvEXT(i, i2, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPointerIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetPointerIndexedvEXT(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetPointerIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetPointerIndexedvEXT(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetFloati_vEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetFloati_vEXT(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetFloatiEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetFloati_vEXT(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetDoublei_vEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglGetDoublei_vEXT(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetDoubleiEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetDoublei_vEXT(i, i2, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPointeri_vEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetPointeri_vEXT(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetPointeriEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetPointeri_vEXT(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glEnableIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        EXTDrawBuffers2.glEnableIndexedEXT(i, i2);
    }

    public static void glDisableIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        EXTDrawBuffers2.glDisableIndexedEXT(i, i2);
    }

    @NativeType("GLboolean")
    public static boolean glIsEnabledIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return EXTDrawBuffers2.glIsEnabledIndexedEXT(i, i2);
    }

    public static void nglGetIntegerIndexedvEXT(int i, int i2, long j) {
        EXTDrawBuffers2.nglGetIntegerIndexedvEXT(i, i2, j);
    }

    public static void glGetIntegerIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        EXTDrawBuffers2.glGetIntegerIndexedvEXT(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetIntegerIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return EXTDrawBuffers2.glGetIntegerIndexedEXT(i, i2);
    }

    public static void nglGetBooleanIndexedvEXT(int i, int i2, long j) {
        EXTDrawBuffers2.nglGetBooleanIndexedvEXT(i, i2, j);
    }

    public static void glGetBooleanIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        EXTDrawBuffers2.glGetBooleanIndexedvEXT(i, i2, byteBuffer);
    }

    @NativeType("void")
    public static boolean glGetBooleanIndexedEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return EXTDrawBuffers2.glGetBooleanIndexedEXT(i, i2);
    }

    public static void glNamedProgramStringEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglNamedProgramStringEXT(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glNamedProgramLocalParameter4dvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglNamedProgramLocalParameter4dvEXT(i, i2, i3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glNamedProgramLocalParameter4fvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglNamedProgramLocalParameter4fvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetNamedProgramLocalParameterdvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetNamedProgramLocalParameterdvEXT(i, i2, i3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetNamedProgramLocalParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetNamedProgramLocalParameterfvEXT(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetNamedProgramivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedProgramivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedProgramiEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedProgramivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedProgramStringEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, glGetNamedProgramiEXT(i, i2, ARBVertexProgram.GL_PROGRAM_LENGTH_ARB));
        }
        nglGetNamedProgramStringEXT(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLsizei") int i9, @NativeType("void const *") long j) {
        nglCompressedTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glCompressedTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        nglCompressedTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureImage2DEXT(i, i2, i3, i4, i5, i6, i7, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("void const *") long j) {
        nglCompressedTextureImage1DEXT(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureImage1DEXT(i, i2, i3, i4, i5, i6, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLsizei") int i11, @NativeType("void const *") long j) {
        nglCompressedTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j);
    }

    public static void glCompressedTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLsizei") int i9, @NativeType("void const *") long j) {
        nglCompressedTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glCompressedTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLsizei") int i7, @NativeType("void const *") long j) {
        nglCompressedTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTextureSubImage1DEXT(i, i2, i3, i4, i5, i6, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, glGetTextureLevelParameteriEXT(i, i2, i3, 34464));
        }
        nglGetCompressedTextureImageEXT(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("void *") long j) {
        nglGetCompressedTextureImageEXT(i, i2, i3, j);
    }

    public static void glCompressedMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLsizei") int i9, @NativeType("void const *") long j) {
        nglCompressedMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glCompressedMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedMultiTexImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        nglCompressedMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedMultiTexImage2DEXT(i, i2, i3, i4, i5, i6, i7, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("void const *") long j) {
        nglCompressedMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedMultiTexImage1DEXT(i, i2, i3, i4, i5, i6, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLsizei") int i11, @NativeType("void const *") long j) {
        nglCompressedMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, j);
    }

    public static void glCompressedMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedMultiTexSubImage3DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLsizei") int i9, @NativeType("void const *") long j) {
        nglCompressedMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, i9, j);
    }

    public static void glCompressedMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedMultiTexSubImage2DEXT(i, i2, i3, i4, i5, i6, i7, i8, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLsizei") int i7, @NativeType("void const *") long j) {
        nglCompressedMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedMultiTexSubImage1DEXT(i, i2, i3, i4, i5, i6, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, glGetMultiTexLevelParameteriEXT(i, i2, i3, 34464));
        }
        nglGetCompressedMultiTexImageEXT(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("void *") long j) {
        nglGetCompressedMultiTexImageEXT(i, i2, i3, j);
    }

    public static void glMatrixLoadTransposefEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglMatrixLoadTransposefEXT(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixLoadTransposedEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglMatrixLoadTransposedEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMatrixMultTransposefEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglMatrixMultTransposefEXT(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixMultTransposedEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglMatrixMultTransposedEXT(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("GLsizeiptr") long j, @NativeType("GLenum") int i2) {
        nglNamedBufferDataEXT(i, j, 0L, i2);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferDataEXT(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferDataEXT(i, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferDataEXT(i, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") FloatBuffer floatBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferDataEXT(i, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer), i2);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") DoubleBuffer doubleBuffer, @NativeType("GLenum") int i2) {
        nglNamedBufferDataEXT(i, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer), i2);
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglNamedBufferSubDataEXT(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") IntBuffer intBuffer) {
        nglNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return MemoryUtil.memByteBufferSafe(nglMapNamedBufferEXT(i, i2), glGetNamedBufferParameteriEXT(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapNamedBufferEXT(i, i2), glGetNamedBufferParameteriEXT(i, 34660));
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, long j, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapNamedBufferEXT(i, i2), (int) j);
    }

    public static void glGetNamedBufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedBufferParameterivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedBufferParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedBufferParameterivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetNamedBufferSubDataEXT(i, j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(shortBuffer.remaining()) << 1, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") IntBuffer intBuffer) {
        nglGetNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(intBuffer.remaining()) << 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(floatBuffer.remaining()) << 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetNamedBufferSubDataEXT(i, j, Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramUniform1fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform1fvEXT(i, i2, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform2fvEXT(i, i2, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform3fvEXT(i, i2, floatBuffer.remaining() / 3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniform4fvEXT(i, i2, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniform1ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform1ivEXT(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform2ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform2ivEXT(i, i2, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform3ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform3ivEXT(i, i2, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform4ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglProgramUniform4ivEXT(i, i2, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniformMatrix2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix2fvEXT(i, i2, floatBuffer.remaining() >> 2, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix3fvEXT(i, i2, floatBuffer.remaining() / 9, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix4fvEXT(i, i2, floatBuffer.remaining() >> 4, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix2x3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix2x3fvEXT(i, i2, floatBuffer.remaining() / 6, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix3x2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix3x2fvEXT(i, i2, floatBuffer.remaining() / 6, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix2x4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix2x4fvEXT(i, i2, floatBuffer.remaining() >> 3, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix4x2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix4x2fvEXT(i, i2, floatBuffer.remaining() >> 3, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix3x4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix3x4fvEXT(i, i2, floatBuffer.remaining() / 12, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramUniformMatrix4x3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramUniformMatrix4x3fvEXT(i, i2, floatBuffer.remaining() / 12, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTextureParameterIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTextureParameterIivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTextureParameterIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTextureParameterIuivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetTextureParameterIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureParameterIivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureParameterIiEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureParameterIivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTextureParameterIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTextureParameterIuivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTextureParameterIuiEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTextureParameterIuivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMultiTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMultiTexParameterIivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMultiTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMultiTexParameterIuivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetMultiTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMultiTexParameterIivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMultiTexParameterIiEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMultiTexParameterIivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMultiTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMultiTexParameterIuivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMultiTexParameterIuiEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMultiTexParameterIuivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glProgramUniform1uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform1uivEXT(i, i2, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform2uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform2uivEXT(i, i2, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform3uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform3uivEXT(i, i2, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramUniform4uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglProgramUniform4uivEXT(i, i2, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNamedProgramLocalParameters4fvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglNamedProgramLocalParameters4fvEXT(i, i2, i3, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glNamedProgramLocalParameterI4ivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglNamedProgramLocalParameterI4ivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNamedProgramLocalParametersI4ivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglNamedProgramLocalParametersI4ivEXT(i, i2, i3, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNamedProgramLocalParameterI4uivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglNamedProgramLocalParameterI4uivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNamedProgramLocalParametersI4uivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglNamedProgramLocalParametersI4uivEXT(i, i2, i3, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetNamedProgramLocalParameterIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetNamedProgramLocalParameterIivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetNamedProgramLocalParameterIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetNamedProgramLocalParameterIuivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetNamedRenderbufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedRenderbufferParameterivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedRenderbufferParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedRenderbufferParameterivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetNamedFramebufferAttachmentParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetNamedFramebufferAttachmentParameterivEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetNamedFramebufferAttachmentParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetNamedFramebufferAttachmentParameterivEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glFramebufferDrawBuffersEXT(@NativeType("GLuint") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        nglFramebufferDrawBuffersEXT(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetFramebufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetFramebufferParameterivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetFramebufferParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetFramebufferParameterivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexArrayIntegervEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetVertexArrayIntegervEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexArrayIntegerEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexArrayIntegervEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexArrayPointervEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetVertexArrayPointervEXT(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetVertexArrayPointerEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetVertexArrayPointervEXT(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexArrayIntegeri_vEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetVertexArrayIntegeri_vEXT(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexArrayIntegeriEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexArrayIntegeri_vEXT(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexArrayPointeri_vEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetVertexArrayPointeri_vEXT(i, i2, i3, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetVertexArrayPointeriEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetVertexArrayPointeri_vEXT(i, i2, i3, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferRangeEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2) {
        nglMapNamedBufferRangeEXT(i, j, j2, i2);
        return MemoryUtil.memByteBufferSafe(j2, (int) j2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapNamedBufferRangeEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapNamedBufferRangeEXT(i, j, j2, i2), (int) j2);
    }

    public static void glMatrixLoadfEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixLoadfEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixLoaddEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMatrixLoaddEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMatrixMultfEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixMultfEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixMultdEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMatrixMultdEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glTextureParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTextureParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glTextureParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTextureParameterfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, sArr, j);
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, iArr, j);
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, fArr, j);
    }

    public static void glTextureImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, dArr, j);
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, sArr, j);
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, iArr, j);
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, fArr, j);
    }

    public static void glTextureImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, dArr, j);
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, sArr, j);
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, iArr, j);
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, fArr, j);
    }

    public static void glTextureSubImage1DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, dArr, j);
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, sArr, j);
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, iArr, j);
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, fArr, j);
    }

    public static void glTextureSubImage2DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, dArr, j);
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetTextureImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, sArr, j);
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetTextureImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, iArr, j);
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetTextureImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, fArr, j);
    }

    public static void glGetTextureImageEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetTextureImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, dArr, j);
    }

    public static void glGetTextureParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTextureParameterfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetTextureParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTextureParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetTextureLevelParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTextureLevelParameterfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glGetTextureLevelParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTextureLevelParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr, j);
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr, j);
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr, j);
    }

    public static void glTextureImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr, j);
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTextureSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, sArr, j);
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTextureSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, iArr, j);
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTextureSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, fArr, j);
    }

    public static void glTextureSubImage3DEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTextureSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, dArr, j);
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glMultiTexCoordPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glMultiTexCoordPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glMultiTexCoordPointerEXT(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glMultiTexCoordPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glMultiTexEnvfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexEnvfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glMultiTexEnvivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexEnvivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMultiTexGendvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultiTexGendvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, i3, dArr, j);
    }

    public static void glMultiTexGenfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexGenfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glMultiTexGenivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexGenivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetMultiTexEnvfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMultiTexEnvfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetMultiTexEnvivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMultiTexEnvivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetMultiTexGendvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetMultiTexGendvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, i2, i3, dArr, j);
    }

    public static void glGetMultiTexGenfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMultiTexGenfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetMultiTexGenivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMultiTexGenivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMultiTexParameterivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMultiTexParameterfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultiTexParameterfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glMultiTexImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, sArr, j);
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glMultiTexImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, iArr, j);
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glMultiTexImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, fArr, j);
    }

    public static void glMultiTexImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glMultiTexImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, dArr, j);
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glMultiTexImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, sArr, j);
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glMultiTexImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, iArr, j);
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glMultiTexImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, fArr, j);
    }

    public static void glMultiTexImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glMultiTexImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, dArr, j);
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glMultiTexSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, sArr, j);
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glMultiTexSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, iArr, j);
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glMultiTexSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, fArr, j);
    }

    public static void glMultiTexSubImage1DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glMultiTexSubImage1DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, dArr, j);
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glMultiTexSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, sArr, j);
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glMultiTexSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, iArr, j);
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glMultiTexSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, fArr, j);
    }

    public static void glMultiTexSubImage2DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLenum") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glMultiTexSubImage2DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, dArr, j);
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetMultiTexImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, sArr, j);
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetMultiTexImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, iArr, j);
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetMultiTexImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, fArr, j);
    }

    public static void glGetMultiTexImageEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetMultiTexImageEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, dArr, j);
    }

    public static void glGetMultiTexParameterfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMultiTexParameterfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetMultiTexParameterivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMultiTexParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetMultiTexLevelParameterfvEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMultiTexLevelParameterfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glGetMultiTexLevelParameterivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMultiTexLevelParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glMultiTexImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, sArr, j);
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glMultiTexImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, iArr, j);
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glMultiTexImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, fArr, j);
    }

    public static void glMultiTexImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8, @NativeType("GLenum") int i9, @NativeType("GLenum") int i10, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glMultiTexImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, dArr, j);
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glMultiTexSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, sArr, j);
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glMultiTexSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, iArr, j);
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glMultiTexSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, fArr, j);
    }

    public static void glMultiTexSubImage3DEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLsizei") int i9, @NativeType("GLenum") int i10, @NativeType("GLenum") int i11, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glMultiTexSubImage3DEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, dArr, j);
    }

    public static void glGetFloatIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetFloatIndexedvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetDoubleIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetDoubleIndexedvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glGetFloati_vEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetFloati_vEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetDoublei_vEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetDoublei_vEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glGetIntegerIndexedvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") int[] iArr) {
        EXTDrawBuffers2.glGetIntegerIndexedvEXT(i, i2, iArr);
    }

    public static void glNamedProgramLocalParameter4dvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glNamedProgramLocalParameter4dvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, i3, dArr, j);
    }

    public static void glNamedProgramLocalParameter4fvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glNamedProgramLocalParameter4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetNamedProgramLocalParameterdvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetNamedProgramLocalParameterdvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, i3, dArr, j);
    }

    public static void glGetNamedProgramLocalParameterfvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetNamedProgramLocalParameterfvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetNamedProgramivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedProgramivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMatrixLoadTransposefEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixLoadTransposefEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixLoadTransposedEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMatrixLoadTransposedEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glMatrixMultTransposefEXT(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixMultTransposefEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixMultTransposedEXT(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMatrixMultTransposedEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") short[] sArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(sArr.length) << 1, sArr, i2, j);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") int[] iArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(iArr.length) << 2, iArr, i2, j);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") float[] fArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(fArr.length) << 2, fArr, i2, j);
    }

    public static void glNamedBufferDataEXT(@NativeType("GLuint") int i, @NativeType("void const *") double[] dArr, @NativeType("GLenum") int i2) {
        long j = GL.getICD().glNamedBufferDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, Integer.toUnsignedLong(dArr.length) << 3, dArr, i2, j);
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") short[] sArr) {
        long j2 = GL.getICD().glNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") int[] iArr) {
        long j2 = GL.getICD().glNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") float[] fArr) {
        long j2 = GL.getICD().glNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void const *") double[] dArr) {
        long j2 = GL.getICD().glNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glGetNamedBufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedBufferParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") short[] sArr) {
        long j2 = GL.getICD().glGetNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(sArr.length) << 1, sArr, j2);
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") int[] iArr) {
        long j2 = GL.getICD().glGetNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(iArr.length) << 2, iArr, j2);
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") float[] fArr) {
        long j2 = GL.getICD().glGetNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(fArr.length) << 2, fArr, j2);
    }

    public static void glGetNamedBufferSubDataEXT(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("void *") double[] dArr) {
        long j2 = GL.getICD().glGetNamedBufferSubDataEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.callPPPV(i, j, Integer.toUnsignedLong(dArr.length) << 3, dArr, j2);
    }

    public static void glProgramUniform1fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform1fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length, fArr, j);
    }

    public static void glProgramUniform2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform2fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 1, fArr, j);
    }

    public static void glProgramUniform3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform3fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 3, fArr, j);
    }

    public static void glProgramUniform4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniform4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 2, fArr, j);
    }

    public static void glProgramUniform1ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform1ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glProgramUniform2ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform2ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 1, iArr, j);
    }

    public static void glProgramUniform3ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform3ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length / 3, iArr, j);
    }

    public static void glProgramUniform4ivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform4ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 2, iArr, j);
    }

    public static void glProgramUniformMatrix2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix2fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 2, z, fArr, j);
    }

    public static void glProgramUniformMatrix3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix3fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 9, z, fArr, j);
    }

    public static void glProgramUniformMatrix4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 4, z, fArr, j);
    }

    public static void glProgramUniformMatrix2x3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix2x3fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 6, z, fArr, j);
    }

    public static void glProgramUniformMatrix3x2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix3x2fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 6, z, fArr, j);
    }

    public static void glProgramUniformMatrix2x4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix2x4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 3, z, fArr, j);
    }

    public static void glProgramUniformMatrix4x2fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix4x2fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 3, z, fArr, j);
    }

    public static void glProgramUniformMatrix3x4fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix3x4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 12, z, fArr, j);
    }

    public static void glProgramUniformMatrix4x3fvEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramUniformMatrix4x3fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length / 12, z, fArr, j);
    }

    public static void glTextureParameterIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTextureParameterIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glTextureParameterIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glTextureParameterIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetTextureParameterIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTextureParameterIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetTextureParameterIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetTextureParameterIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMultiTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexParameterIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glMultiTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glMultiTexParameterIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetMultiTexParameterIivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMultiTexParameterIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetMultiTexParameterIuivEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetMultiTexParameterIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glProgramUniform1uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform1uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length, iArr, j);
    }

    public static void glProgramUniform2uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform2uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 1, iArr, j);
    }

    public static void glProgramUniform3uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform3uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length / 3, iArr, j);
    }

    public static void glProgramUniform4uivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glProgramUniform4uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr.length >> 2, iArr, j);
    }

    public static void glNamedProgramLocalParameters4fvEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glNamedProgramLocalParameters4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, fArr.length >> 2, fArr, j);
    }

    public static void glNamedProgramLocalParameterI4ivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glNamedProgramLocalParameterI4ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glNamedProgramLocalParametersI4ivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glNamedProgramLocalParametersI4ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr.length >> 2, iArr, j);
    }

    public static void glNamedProgramLocalParameterI4uivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glNamedProgramLocalParameterI4uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glNamedProgramLocalParametersI4uivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glNamedProgramLocalParametersI4uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr.length >> 2, iArr, j);
    }

    public static void glGetNamedProgramLocalParameterIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedProgramLocalParameterIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetNamedProgramLocalParameterIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetNamedProgramLocalParameterIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetNamedRenderbufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedRenderbufferParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetNamedFramebufferAttachmentParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetNamedFramebufferAttachmentParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glFramebufferDrawBuffersEXT(@NativeType("GLuint") int i, @NativeType("GLenum const *") int[] iArr) {
        long j = GL.getICD().glFramebufferDrawBuffersEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glGetFramebufferParameterivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetFramebufferParameterivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexArrayIntegervEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexArrayIntegervEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexArrayIntegeri_vEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexArrayIntegeri_vEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }
}
