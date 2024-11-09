package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexProgram.class */
public class ARBVertexProgram {
    public static final int GL_VERTEX_PROGRAM_ARB = 34336;
    public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 34370;
    public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 34371;
    public static final int GL_COLOR_SUM_ARB = 33880;
    public static final int GL_PROGRAM_FORMAT_ASCII_ARB = 34933;
    public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 34338;
    public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 34339;
    public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 34340;
    public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 34341;
    public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 34922;
    public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 34342;
    public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 34373;
    public static final int GL_PROGRAM_LENGTH_ARB = 34343;
    public static final int GL_PROGRAM_FORMAT_ARB = 34934;
    public static final int GL_PROGRAM_BINDING_ARB = 34423;
    public static final int GL_PROGRAM_INSTRUCTIONS_ARB = 34976;
    public static final int GL_MAX_PROGRAM_INSTRUCTIONS_ARB = 34977;
    public static final int GL_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 34978;
    public static final int GL_MAX_PROGRAM_NATIVE_INSTRUCTIONS_ARB = 34979;
    public static final int GL_PROGRAM_TEMPORARIES_ARB = 34980;
    public static final int GL_MAX_PROGRAM_TEMPORARIES_ARB = 34981;
    public static final int GL_PROGRAM_NATIVE_TEMPORARIES_ARB = 34982;
    public static final int GL_MAX_PROGRAM_NATIVE_TEMPORARIES_ARB = 34983;
    public static final int GL_PROGRAM_PARAMETERS_ARB = 34984;
    public static final int GL_MAX_PROGRAM_PARAMETERS_ARB = 34985;
    public static final int GL_PROGRAM_NATIVE_PARAMETERS_ARB = 34986;
    public static final int GL_MAX_PROGRAM_NATIVE_PARAMETERS_ARB = 34987;
    public static final int GL_PROGRAM_ATTRIBS_ARB = 34988;
    public static final int GL_MAX_PROGRAM_ATTRIBS_ARB = 34989;
    public static final int GL_PROGRAM_NATIVE_ATTRIBS_ARB = 34990;
    public static final int GL_MAX_PROGRAM_NATIVE_ATTRIBS_ARB = 34991;
    public static final int GL_PROGRAM_ADDRESS_REGISTERS_ARB = 34992;
    public static final int GL_MAX_PROGRAM_ADDRESS_REGISTERS_ARB = 34993;
    public static final int GL_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34994;
    public static final int GL_MAX_PROGRAM_NATIVE_ADDRESS_REGISTERS_ARB = 34995;
    public static final int GL_MAX_PROGRAM_LOCAL_PARAMETERS_ARB = 34996;
    public static final int GL_MAX_PROGRAM_ENV_PARAMETERS_ARB = 34997;
    public static final int GL_PROGRAM_UNDER_NATIVE_LIMITS_ARB = 34998;
    public static final int GL_PROGRAM_STRING_ARB = 34344;
    public static final int GL_PROGRAM_ERROR_POSITION_ARB = 34379;
    public static final int GL_CURRENT_MATRIX_ARB = 34369;
    public static final int GL_TRANSPOSE_CURRENT_MATRIX_ARB = 34999;
    public static final int GL_CURRENT_MATRIX_STACK_DEPTH_ARB = 34368;
    public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 34921;
    public static final int GL_MAX_PROGRAM_MATRICES_ARB = 34351;
    public static final int GL_MAX_PROGRAM_MATRIX_STACK_DEPTH_ARB = 34350;
    public static final int GL_PROGRAM_ERROR_STRING_ARB = 34932;
    public static final int GL_MATRIX0_ARB = 35008;
    public static final int GL_MATRIX1_ARB = 35009;
    public static final int GL_MATRIX2_ARB = 35010;
    public static final int GL_MATRIX3_ARB = 35011;
    public static final int GL_MATRIX4_ARB = 35012;
    public static final int GL_MATRIX5_ARB = 35013;
    public static final int GL_MATRIX6_ARB = 35014;
    public static final int GL_MATRIX7_ARB = 35015;
    public static final int GL_MATRIX8_ARB = 35016;
    public static final int GL_MATRIX9_ARB = 35017;
    public static final int GL_MATRIX10_ARB = 35018;
    public static final int GL_MATRIX11_ARB = 35019;
    public static final int GL_MATRIX12_ARB = 35020;
    public static final int GL_MATRIX13_ARB = 35021;
    public static final int GL_MATRIX14_ARB = 35022;
    public static final int GL_MATRIX15_ARB = 35023;
    public static final int GL_MATRIX16_ARB = 35024;
    public static final int GL_MATRIX17_ARB = 35025;
    public static final int GL_MATRIX18_ARB = 35026;
    public static final int GL_MATRIX19_ARB = 35027;
    public static final int GL_MATRIX20_ARB = 35028;
    public static final int GL_MATRIX21_ARB = 35029;
    public static final int GL_MATRIX22_ARB = 35030;
    public static final int GL_MATRIX23_ARB = 35031;
    public static final int GL_MATRIX24_ARB = 35032;
    public static final int GL_MATRIX25_ARB = 35033;
    public static final int GL_MATRIX26_ARB = 35034;
    public static final int GL_MATRIX27_ARB = 35035;
    public static final int GL_MATRIX28_ARB = 35036;
    public static final int GL_MATRIX29_ARB = 35037;
    public static final int GL_MATRIX30_ARB = 35038;
    public static final int GL_MATRIX31_ARB = 35039;

    public static native void nglProgramStringARB(int i, int i2, int i3, long j);

    public static native void glBindProgramARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglDeleteProgramsARB(int i, long j);

    public static native void nglGenProgramsARB(int i, long j);

    public static native void glProgramEnvParameter4dARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglProgramEnvParameter4dvARB(int i, int i2, long j);

    public static native void glProgramEnvParameter4fARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void nglProgramEnvParameter4fvARB(int i, int i2, long j);

    public static native void glProgramLocalParameter4dARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglProgramLocalParameter4dvARB(int i, int i2, long j);

    public static native void glProgramLocalParameter4fARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void nglProgramLocalParameter4fvARB(int i, int i2, long j);

    public static native void nglGetProgramEnvParameterfvARB(int i, int i2, long j);

    public static native void nglGetProgramEnvParameterdvARB(int i, int i2, long j);

    public static native void nglGetProgramLocalParameterfvARB(int i, int i2, long j);

    public static native void nglGetProgramLocalParameterdvARB(int i, int i2, long j);

    public static native void nglGetProgramivARB(int i, int i2, long j);

    public static native void nglGetProgramStringARB(int i, int i2, long j);

    @NativeType("GLboolean")
    public static native boolean glIsProgramARB(@NativeType("GLuint") int i);

    static {
        GL.initialize();
    }

    protected ARBVertexProgram() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexAttrib1sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s) {
        ARBVertexShader.glVertexAttrib1sARB(i, s);
    }

    public static void glVertexAttrib1fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f) {
        ARBVertexShader.glVertexAttrib1fARB(i, f);
    }

    public static void glVertexAttrib1dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d) {
        ARBVertexShader.glVertexAttrib1dARB(i, d);
    }

    public static void glVertexAttrib2sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2) {
        ARBVertexShader.glVertexAttrib2sARB(i, s, s2);
    }

    public static void glVertexAttrib2fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2) {
        ARBVertexShader.glVertexAttrib2fARB(i, f, f2);
    }

    public static void glVertexAttrib2dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        ARBVertexShader.glVertexAttrib2dARB(i, d, d2);
    }

    public static void glVertexAttrib3sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3) {
        ARBVertexShader.glVertexAttrib3sARB(i, s, s2, s3);
    }

    public static void glVertexAttrib3fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3) {
        ARBVertexShader.glVertexAttrib3fARB(i, f, f2, f3);
    }

    public static void glVertexAttrib3dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3) {
        ARBVertexShader.glVertexAttrib3dARB(i, d, d2, d3);
    }

    public static void glVertexAttrib4sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4) {
        ARBVertexShader.glVertexAttrib4sARB(i, s, s2, s3, s4);
    }

    public static void glVertexAttrib4fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        ARBVertexShader.glVertexAttrib4fARB(i, f, f2, f3, f4);
    }

    public static void glVertexAttrib4dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4) {
        ARBVertexShader.glVertexAttrib4dARB(i, d, d2, d3, d4);
    }

    public static void glVertexAttrib4NubARB(@NativeType("GLuint") int i, @NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4, @NativeType("GLubyte") byte b5) {
        ARBVertexShader.glVertexAttrib4NubARB(i, b2, b3, b4, b5);
    }

    public static void nglVertexAttrib1svARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib1svARB(i, j);
    }

    public static void glVertexAttrib1svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttrib1svARB(i, shortBuffer);
    }

    public static void nglVertexAttrib1fvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib1fvARB(i, j);
    }

    public static void glVertexAttrib1fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        ARBVertexShader.glVertexAttrib1fvARB(i, floatBuffer);
    }

    public static void nglVertexAttrib1dvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib1dvARB(i, j);
    }

    public static void glVertexAttrib1dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        ARBVertexShader.glVertexAttrib1dvARB(i, doubleBuffer);
    }

    public static void nglVertexAttrib2svARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib2svARB(i, j);
    }

    public static void glVertexAttrib2svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttrib2svARB(i, shortBuffer);
    }

    public static void nglVertexAttrib2fvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib2fvARB(i, j);
    }

    public static void glVertexAttrib2fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        ARBVertexShader.glVertexAttrib2fvARB(i, floatBuffer);
    }

    public static void nglVertexAttrib2dvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib2dvARB(i, j);
    }

    public static void glVertexAttrib2dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        ARBVertexShader.glVertexAttrib2dvARB(i, doubleBuffer);
    }

    public static void nglVertexAttrib3svARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib3svARB(i, j);
    }

    public static void glVertexAttrib3svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttrib3svARB(i, shortBuffer);
    }

    public static void nglVertexAttrib3fvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib3fvARB(i, j);
    }

    public static void glVertexAttrib3fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        ARBVertexShader.glVertexAttrib3fvARB(i, floatBuffer);
    }

    public static void nglVertexAttrib3dvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib3dvARB(i, j);
    }

    public static void glVertexAttrib3dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        ARBVertexShader.glVertexAttrib3dvARB(i, doubleBuffer);
    }

    public static void nglVertexAttrib4fvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4fvARB(i, j);
    }

    public static void glVertexAttrib4fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        ARBVertexShader.glVertexAttrib4fvARB(i, floatBuffer);
    }

    public static void nglVertexAttrib4bvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4bvARB(i, j);
    }

    public static void glVertexAttrib4bvARB(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        ARBVertexShader.glVertexAttrib4bvARB(i, byteBuffer);
    }

    public static void nglVertexAttrib4svARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4svARB(i, j);
    }

    public static void glVertexAttrib4svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttrib4svARB(i, shortBuffer);
    }

    public static void nglVertexAttrib4ivARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4ivARB(i, j);
    }

    public static void glVertexAttrib4ivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        ARBVertexShader.glVertexAttrib4ivARB(i, intBuffer);
    }

    public static void nglVertexAttrib4ubvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4ubvARB(i, j);
    }

    public static void glVertexAttrib4ubvARB(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        ARBVertexShader.glVertexAttrib4ubvARB(i, byteBuffer);
    }

    public static void nglVertexAttrib4usvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4usvARB(i, j);
    }

    public static void glVertexAttrib4usvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttrib4usvARB(i, shortBuffer);
    }

    public static void nglVertexAttrib4uivARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4uivARB(i, j);
    }

    public static void glVertexAttrib4uivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        ARBVertexShader.glVertexAttrib4uivARB(i, intBuffer);
    }

    public static void nglVertexAttrib4dvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4dvARB(i, j);
    }

    public static void glVertexAttrib4dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        ARBVertexShader.glVertexAttrib4dvARB(i, doubleBuffer);
    }

    public static void nglVertexAttrib4NbvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4NbvARB(i, j);
    }

    public static void glVertexAttrib4NbvARB(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        ARBVertexShader.glVertexAttrib4NbvARB(i, byteBuffer);
    }

    public static void nglVertexAttrib4NsvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4NsvARB(i, j);
    }

    public static void glVertexAttrib4NsvARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttrib4NsvARB(i, shortBuffer);
    }

    public static void nglVertexAttrib4NivARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4NivARB(i, j);
    }

    public static void glVertexAttrib4NivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        ARBVertexShader.glVertexAttrib4NivARB(i, intBuffer);
    }

    public static void nglVertexAttrib4NubvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4NubvARB(i, j);
    }

    public static void glVertexAttrib4NubvARB(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        ARBVertexShader.glVertexAttrib4NubvARB(i, byteBuffer);
    }

    public static void nglVertexAttrib4NusvARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4NusvARB(i, j);
    }

    public static void glVertexAttrib4NusvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttrib4NusvARB(i, shortBuffer);
    }

    public static void nglVertexAttrib4NuivARB(int i, long j) {
        ARBVertexShader.nglVertexAttrib4NuivARB(i, j);
    }

    public static void glVertexAttrib4NuivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        ARBVertexShader.glVertexAttrib4NuivARB(i, intBuffer);
    }

    public static void nglVertexAttribPointerARB(int i, int i2, int i3, boolean z, int i4, long j) {
        ARBVertexShader.nglVertexAttribPointerARB(i, i2, i3, z, i4, j);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, byteBuffer);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, j);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, shortBuffer);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, intBuffer);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, floatBuffer);
    }

    public static void glEnableVertexAttribArrayARB(@NativeType("GLuint") int i) {
        ARBVertexShader.glEnableVertexAttribArrayARB(i);
    }

    public static void glDisableVertexAttribArrayARB(@NativeType("GLuint") int i) {
        ARBVertexShader.glDisableVertexAttribArrayARB(i);
    }

    public static void glProgramStringARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglProgramStringARB(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDeleteProgramsARB(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteProgramsARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGenProgramsARB(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenProgramsARB(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenProgramsARB() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenProgramsARB(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glProgramEnvParameter4dvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglProgramEnvParameter4dvARB(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramEnvParameter4fvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglProgramEnvParameter4fvARB(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramLocalParameter4dvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglProgramLocalParameter4dvARB(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glProgramLocalParameter4fvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglProgramLocalParameter4fvARB(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetProgramEnvParameterfvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetProgramEnvParameterfvARB(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetProgramEnvParameterdvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetProgramEnvParameterdvARB(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetProgramLocalParameterfvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetProgramLocalParameterfvARB(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetProgramLocalParameterdvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetProgramLocalParameterdvARB(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetProgramivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetProgramivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetProgramiARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetProgramivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetProgramStringARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, glGetProgramiARB(i, GL_PROGRAM_LENGTH_ARB));
        }
        nglGetProgramStringARB(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void nglGetVertexAttribfvARB(int i, int i2, long j) {
        ARBVertexShader.nglGetVertexAttribfvARB(i, i2, j);
    }

    public static void glGetVertexAttribfvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        ARBVertexShader.glGetVertexAttribfvARB(i, i2, floatBuffer);
    }

    public static void nglGetVertexAttribdvARB(int i, int i2, long j) {
        ARBVertexShader.nglGetVertexAttribdvARB(i, i2, j);
    }

    public static void glGetVertexAttribdvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        ARBVertexShader.glGetVertexAttribdvARB(i, i2, doubleBuffer);
    }

    public static void nglGetVertexAttribivARB(int i, int i2, long j) {
        ARBVertexShader.nglGetVertexAttribivARB(i, i2, j);
    }

    public static void glGetVertexAttribivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        ARBVertexShader.glGetVertexAttribivARB(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetVertexAttribiARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return ARBVertexShader.glGetVertexAttribiARB(i, i2);
    }

    public static void nglGetVertexAttribPointervARB(int i, int i2, long j) {
        ARBVertexShader.nglGetVertexAttribPointervARB(i, i2, j);
    }

    public static void glGetVertexAttribPointervARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        ARBVertexShader.glGetVertexAttribPointervARB(i, i2, pointerBuffer);
    }

    @NativeType("void")
    public static long glGetVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return ARBVertexShader.glGetVertexAttribPointerARB(i, i2);
    }

    public static void glVertexAttrib1svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        ARBVertexShader.glVertexAttrib1svARB(i, sArr);
    }

    public static void glVertexAttrib1fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        ARBVertexShader.glVertexAttrib1fvARB(i, fArr);
    }

    public static void glVertexAttrib1dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        ARBVertexShader.glVertexAttrib1dvARB(i, dArr);
    }

    public static void glVertexAttrib2svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        ARBVertexShader.glVertexAttrib2svARB(i, sArr);
    }

    public static void glVertexAttrib2fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        ARBVertexShader.glVertexAttrib2fvARB(i, fArr);
    }

    public static void glVertexAttrib2dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        ARBVertexShader.glVertexAttrib2dvARB(i, dArr);
    }

    public static void glVertexAttrib3svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        ARBVertexShader.glVertexAttrib3svARB(i, sArr);
    }

    public static void glVertexAttrib3fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        ARBVertexShader.glVertexAttrib3fvARB(i, fArr);
    }

    public static void glVertexAttrib3dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        ARBVertexShader.glVertexAttrib3dvARB(i, dArr);
    }

    public static void glVertexAttrib4fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        ARBVertexShader.glVertexAttrib4fvARB(i, fArr);
    }

    public static void glVertexAttrib4svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        ARBVertexShader.glVertexAttrib4svARB(i, sArr);
    }

    public static void glVertexAttrib4ivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        ARBVertexShader.glVertexAttrib4ivARB(i, iArr);
    }

    public static void glVertexAttrib4usvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        ARBVertexShader.glVertexAttrib4usvARB(i, sArr);
    }

    public static void glVertexAttrib4uivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        ARBVertexShader.glVertexAttrib4uivARB(i, iArr);
    }

    public static void glVertexAttrib4dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        ARBVertexShader.glVertexAttrib4dvARB(i, dArr);
    }

    public static void glVertexAttrib4NsvARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        ARBVertexShader.glVertexAttrib4NsvARB(i, sArr);
    }

    public static void glVertexAttrib4NivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        ARBVertexShader.glVertexAttrib4NivARB(i, iArr);
    }

    public static void glVertexAttrib4NusvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        ARBVertexShader.glVertexAttrib4NusvARB(i, sArr);
    }

    public static void glVertexAttrib4NuivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        ARBVertexShader.glVertexAttrib4NuivARB(i, iArr);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") short[] sArr) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, sArr);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") int[] iArr) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, iArr);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") float[] fArr) {
        ARBVertexShader.glVertexAttribPointerARB(i, i2, i3, z, i4, fArr);
    }

    public static void glDeleteProgramsARB(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteProgramsARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenProgramsARB(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenProgramsARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glProgramEnvParameter4dvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramEnvParameter4dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glProgramEnvParameter4fvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramEnvParameter4fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glProgramLocalParameter4dvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glProgramLocalParameter4dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glProgramLocalParameter4fvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramLocalParameter4fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetProgramEnvParameterfvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetProgramEnvParameterfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetProgramEnvParameterdvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetProgramEnvParameterdvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glGetProgramLocalParameterfvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetProgramLocalParameterfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetProgramLocalParameterdvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetProgramLocalParameterdvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glGetProgramivARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetProgramivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexAttribfvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        ARBVertexShader.glGetVertexAttribfvARB(i, i2, fArr);
    }

    public static void glGetVertexAttribdvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        ARBVertexShader.glGetVertexAttribdvARB(i, i2, dArr);
    }

    public static void glGetVertexAttribivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        ARBVertexShader.glGetVertexAttribivARB(i, i2, iArr);
    }
}
