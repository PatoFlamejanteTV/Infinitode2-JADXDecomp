package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL20.class */
public class GL20 extends GL15 {
    public static final int GL_SHADING_LANGUAGE_VERSION = 35724;
    public static final int GL_CURRENT_PROGRAM = 35725;
    public static final int GL_SHADER_TYPE = 35663;
    public static final int GL_DELETE_STATUS = 35712;
    public static final int GL_COMPILE_STATUS = 35713;
    public static final int GL_LINK_STATUS = 35714;
    public static final int GL_VALIDATE_STATUS = 35715;
    public static final int GL_INFO_LOG_LENGTH = 35716;
    public static final int GL_ATTACHED_SHADERS = 35717;
    public static final int GL_ACTIVE_UNIFORMS = 35718;
    public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH = 35719;
    public static final int GL_ACTIVE_ATTRIBUTES = 35721;
    public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH = 35722;
    public static final int GL_SHADER_SOURCE_LENGTH = 35720;
    public static final int GL_FLOAT_VEC2 = 35664;
    public static final int GL_FLOAT_VEC3 = 35665;
    public static final int GL_FLOAT_VEC4 = 35666;
    public static final int GL_INT_VEC2 = 35667;
    public static final int GL_INT_VEC3 = 35668;
    public static final int GL_INT_VEC4 = 35669;
    public static final int GL_BOOL = 35670;
    public static final int GL_BOOL_VEC2 = 35671;
    public static final int GL_BOOL_VEC3 = 35672;
    public static final int GL_BOOL_VEC4 = 35673;
    public static final int GL_FLOAT_MAT2 = 35674;
    public static final int GL_FLOAT_MAT3 = 35675;
    public static final int GL_FLOAT_MAT4 = 35676;
    public static final int GL_SAMPLER_1D = 35677;
    public static final int GL_SAMPLER_2D = 35678;
    public static final int GL_SAMPLER_3D = 35679;
    public static final int GL_SAMPLER_CUBE = 35680;
    public static final int GL_SAMPLER_1D_SHADOW = 35681;
    public static final int GL_SAMPLER_2D_SHADOW = 35682;
    public static final int GL_VERTEX_SHADER = 35633;
    public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS = 35658;
    public static final int GL_MAX_VARYING_FLOATS = 35659;
    public static final int GL_MAX_VERTEX_ATTRIBS = 34921;
    public static final int GL_MAX_TEXTURE_IMAGE_UNITS = 34930;
    public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS = 35660;
    public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS = 35661;
    public static final int GL_MAX_TEXTURE_COORDS = 34929;
    public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 34370;
    public static final int GL_VERTEX_PROGRAM_TWO_SIDE = 34371;
    public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED = 34338;
    public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE = 34339;
    public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE = 34340;
    public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE = 34341;
    public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED = 34922;
    public static final int GL_CURRENT_VERTEX_ATTRIB = 34342;
    public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER = 34373;
    public static final int GL_FRAGMENT_SHADER = 35632;
    public static final int GL_MAX_FRAGMENT_UNIFORM_COMPONENTS = 35657;
    public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 35723;
    public static final int GL_MAX_DRAW_BUFFERS = 34852;
    public static final int GL_DRAW_BUFFER0 = 34853;
    public static final int GL_DRAW_BUFFER1 = 34854;
    public static final int GL_DRAW_BUFFER2 = 34855;
    public static final int GL_DRAW_BUFFER3 = 34856;
    public static final int GL_DRAW_BUFFER4 = 34857;
    public static final int GL_DRAW_BUFFER5 = 34858;
    public static final int GL_DRAW_BUFFER6 = 34859;
    public static final int GL_DRAW_BUFFER7 = 34860;
    public static final int GL_DRAW_BUFFER8 = 34861;
    public static final int GL_DRAW_BUFFER9 = 34862;
    public static final int GL_DRAW_BUFFER10 = 34863;
    public static final int GL_DRAW_BUFFER11 = 34864;
    public static final int GL_DRAW_BUFFER12 = 34865;
    public static final int GL_DRAW_BUFFER13 = 34866;
    public static final int GL_DRAW_BUFFER14 = 34867;
    public static final int GL_DRAW_BUFFER15 = 34868;
    public static final int GL_POINT_SPRITE = 34913;
    public static final int GL_COORD_REPLACE = 34914;
    public static final int GL_POINT_SPRITE_COORD_ORIGIN = 36000;
    public static final int GL_LOWER_LEFT = 36001;
    public static final int GL_UPPER_LEFT = 36002;
    public static final int GL_BLEND_EQUATION_RGB = 32777;
    public static final int GL_BLEND_EQUATION_ALPHA = 34877;
    public static final int GL_STENCIL_BACK_FUNC = 34816;
    public static final int GL_STENCIL_BACK_FAIL = 34817;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL = 34818;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS = 34819;
    public static final int GL_STENCIL_BACK_REF = 36003;
    public static final int GL_STENCIL_BACK_VALUE_MASK = 36004;
    public static final int GL_STENCIL_BACK_WRITEMASK = 36005;

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL20() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLuint")
    public static int glCreateProgram() {
        return GL20C.glCreateProgram();
    }

    public static void glDeleteProgram(@NativeType("GLuint") int i) {
        GL20C.glDeleteProgram(i);
    }

    @NativeType("GLboolean")
    public static boolean glIsProgram(@NativeType("GLuint") int i) {
        return GL20C.glIsProgram(i);
    }

    @NativeType("GLuint")
    public static int glCreateShader(@NativeType("GLenum") int i) {
        return GL20C.glCreateShader(i);
    }

    public static void glDeleteShader(@NativeType("GLuint") int i) {
        GL20C.glDeleteShader(i);
    }

    @NativeType("GLboolean")
    public static boolean glIsShader(@NativeType("GLuint") int i) {
        return GL20C.glIsShader(i);
    }

    public static void glAttachShader(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL20C.glAttachShader(i, i2);
    }

    public static void glDetachShader(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL20C.glDetachShader(i, i2);
    }

    public static void nglShaderSource(int i, int i2, long j, long j2) {
        GL20C.nglShaderSource(i, i2, j, j2);
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL20C.glShaderSource(i, pointerBuffer, intBuffer);
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence... charSequenceArr) {
        GL20C.glShaderSource(i, charSequenceArr);
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence charSequence) {
        GL20C.glShaderSource(i, charSequence);
    }

    public static void glCompileShader(@NativeType("GLuint") int i) {
        GL20C.glCompileShader(i);
    }

    public static void glLinkProgram(@NativeType("GLuint") int i) {
        GL20C.glLinkProgram(i);
    }

    public static void glUseProgram(@NativeType("GLuint") int i) {
        GL20C.glUseProgram(i);
    }

    public static void glValidateProgram(@NativeType("GLuint") int i) {
        GL20C.glValidateProgram(i);
    }

    public static void glUniform1f(@NativeType("GLint") int i, @NativeType("GLfloat") float f) {
        GL20C.glUniform1f(i, f);
    }

    public static void glUniform2f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2) {
        GL20C.glUniform2f(i, f, f2);
    }

    public static void glUniform3f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3) {
        GL20C.glUniform3f(i, f, f2, f3);
    }

    public static void glUniform4f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL20C.glUniform4f(i, f, f2, f3, f4);
    }

    public static void glUniform1i(@NativeType("GLint") int i, @NativeType("GLint") int i2) {
        GL20C.glUniform1i(i, i2);
    }

    public static void glUniform2i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3) {
        GL20C.glUniform2i(i, i2, i3);
    }

    public static void glUniform3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4) {
        GL20C.glUniform3i(i, i2, i3, i4);
    }

    public static void glUniform4i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        GL20C.glUniform4i(i, i2, i3, i4, i5);
    }

    public static void nglUniform1fv(int i, int i2, long j) {
        GL20C.nglUniform1fv(i, i2, j);
    }

    public static void glUniform1fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glUniform1fv(i, floatBuffer);
    }

    public static void nglUniform2fv(int i, int i2, long j) {
        GL20C.nglUniform2fv(i, i2, j);
    }

    public static void glUniform2fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glUniform2fv(i, floatBuffer);
    }

    public static void nglUniform3fv(int i, int i2, long j) {
        GL20C.nglUniform3fv(i, i2, j);
    }

    public static void glUniform3fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glUniform3fv(i, floatBuffer);
    }

    public static void nglUniform4fv(int i, int i2, long j) {
        GL20C.nglUniform4fv(i, i2, j);
    }

    public static void glUniform4fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glUniform4fv(i, floatBuffer);
    }

    public static void nglUniform1iv(int i, int i2, long j) {
        GL20C.nglUniform1iv(i, i2, j);
    }

    public static void glUniform1iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL20C.glUniform1iv(i, intBuffer);
    }

    public static void nglUniform2iv(int i, int i2, long j) {
        GL20C.nglUniform2iv(i, i2, j);
    }

    public static void glUniform2iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL20C.glUniform2iv(i, intBuffer);
    }

    public static void nglUniform3iv(int i, int i2, long j) {
        GL20C.nglUniform3iv(i, i2, j);
    }

    public static void glUniform3iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL20C.glUniform3iv(i, intBuffer);
    }

    public static void nglUniform4iv(int i, int i2, long j) {
        GL20C.nglUniform4iv(i, i2, j);
    }

    public static void glUniform4iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL20C.glUniform4iv(i, intBuffer);
    }

    public static void nglUniformMatrix2fv(int i, int i2, boolean z, long j) {
        GL20C.nglUniformMatrix2fv(i, i2, z, j);
    }

    public static void glUniformMatrix2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glUniformMatrix2fv(i, z, floatBuffer);
    }

    public static void nglUniformMatrix3fv(int i, int i2, boolean z, long j) {
        GL20C.nglUniformMatrix3fv(i, i2, z, j);
    }

    public static void glUniformMatrix3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glUniformMatrix3fv(i, z, floatBuffer);
    }

    public static void nglUniformMatrix4fv(int i, int i2, boolean z, long j) {
        GL20C.nglUniformMatrix4fv(i, i2, z, j);
    }

    public static void glUniformMatrix4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glUniformMatrix4fv(i, z, floatBuffer);
    }

    public static void nglGetShaderiv(int i, int i2, long j) {
        GL20C.nglGetShaderiv(i, i2, j);
    }

    public static void glGetShaderiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL20C.glGetShaderiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetShaderi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL20C.glGetShaderi(i, i2);
    }

    public static void nglGetProgramiv(int i, int i2, long j) {
        GL20C.nglGetProgramiv(i, i2, j);
    }

    public static void glGetProgramiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL20C.glGetProgramiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetProgrami(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL20C.glGetProgrami(i, i2);
    }

    public static void nglGetShaderInfoLog(int i, int i2, long j, long j2) {
        GL20C.nglGetShaderInfoLog(i, i2, j, j2);
    }

    public static void glGetShaderInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetShaderInfoLog(i, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetShaderInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        return GL20C.glGetShaderInfoLog(i, i2);
    }

    @NativeType("void")
    public static String glGetShaderInfoLog(@NativeType("GLuint") int i) {
        return glGetShaderInfoLog(i, glGetShaderi(i, 35716));
    }

    public static void nglGetProgramInfoLog(int i, int i2, long j, long j2) {
        GL20C.nglGetProgramInfoLog(i, i2, j, j2);
    }

    public static void glGetProgramInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetProgramInfoLog(i, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetProgramInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        return GL20C.glGetProgramInfoLog(i, i2);
    }

    @NativeType("void")
    public static String glGetProgramInfoLog(@NativeType("GLuint") int i) {
        return glGetProgramInfoLog(i, glGetProgrami(i, 35716));
    }

    public static void nglGetAttachedShaders(int i, int i2, long j, long j2) {
        GL20C.nglGetAttachedShaders(i, i2, j, j2);
    }

    public static void glGetAttachedShaders(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2) {
        GL20C.glGetAttachedShaders(i, intBuffer, intBuffer2);
    }

    public static int nglGetUniformLocation(int i, long j) {
        return GL20C.nglGetUniformLocation(i, j);
    }

    @NativeType("GLint")
    public static int glGetUniformLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL20C.glGetUniformLocation(i, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetUniformLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL20C.glGetUniformLocation(i, charSequence);
    }

    public static void nglGetActiveUniform(int i, int i2, int i3, long j, long j2, long j3, long j4) {
        GL20C.nglGetActiveUniform(i, i2, i3, j, j2, j3, j4);
    }

    public static void glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetActiveUniform(i, i2, intBuffer, intBuffer2, intBuffer3, byteBuffer);
    }

    @NativeType("void")
    public static String glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return GL20C.glGetActiveUniform(i, i2, i3, intBuffer, intBuffer2);
    }

    @NativeType("void")
    public static String glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetActiveUniform(i, i2, glGetProgrami(i, 35719), intBuffer, intBuffer2);
    }

    public static void nglGetUniformfv(int i, int i2, long j) {
        GL20C.nglGetUniformfv(i, i2, j);
    }

    public static void glGetUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL20C.glGetUniformfv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetUniformf(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL20C.glGetUniformf(i, i2);
    }

    public static void nglGetUniformiv(int i, int i2, long j) {
        GL20C.nglGetUniformiv(i, i2, j);
    }

    public static void glGetUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL20C.glGetUniformiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetUniformi(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL20C.glGetUniformi(i, i2);
    }

    public static void nglGetShaderSource(int i, int i2, long j, long j2) {
        GL20C.nglGetShaderSource(i, i2, j, j2);
    }

    public static void glGetShaderSource(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetShaderSource(i, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetShaderSource(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        return GL20C.glGetShaderSource(i, i2);
    }

    @NativeType("void")
    public static String glGetShaderSource(@NativeType("GLuint") int i) {
        return glGetShaderSource(i, glGetShaderi(i, 35720));
    }

    public static void glVertexAttrib1f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f) {
        GL20C.glVertexAttrib1f(i, f);
    }

    public static void glVertexAttrib1s(@NativeType("GLuint") int i, @NativeType("GLshort") short s) {
        GL20C.glVertexAttrib1s(i, s);
    }

    public static void glVertexAttrib1d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d) {
        GL20C.glVertexAttrib1d(i, d);
    }

    public static void glVertexAttrib2f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2) {
        GL20C.glVertexAttrib2f(i, f, f2);
    }

    public static void glVertexAttrib2s(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2) {
        GL20C.glVertexAttrib2s(i, s, s2);
    }

    public static void glVertexAttrib2d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL20C.glVertexAttrib2d(i, d, d2);
    }

    public static void glVertexAttrib3f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3) {
        GL20C.glVertexAttrib3f(i, f, f2, f3);
    }

    public static void glVertexAttrib3s(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3) {
        GL20C.glVertexAttrib3s(i, s, s2, s3);
    }

    public static void glVertexAttrib3d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3) {
        GL20C.glVertexAttrib3d(i, d, d2, d3);
    }

    public static void glVertexAttrib4f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL20C.glVertexAttrib4f(i, f, f2, f3, f4);
    }

    public static void glVertexAttrib4s(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4) {
        GL20C.glVertexAttrib4s(i, s, s2, s3, s4);
    }

    public static void glVertexAttrib4d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4) {
        GL20C.glVertexAttrib4d(i, d, d2, d3, d4);
    }

    public static void glVertexAttrib4Nub(@NativeType("GLuint") int i, @NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4, @NativeType("GLubyte") byte b5) {
        GL20C.glVertexAttrib4Nub(i, b2, b3, b4, b5);
    }

    public static void nglVertexAttrib1fv(int i, long j) {
        GL20C.nglVertexAttrib1fv(i, j);
    }

    public static void glVertexAttrib1fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glVertexAttrib1fv(i, floatBuffer);
    }

    public static void nglVertexAttrib1sv(int i, long j) {
        GL20C.nglVertexAttrib1sv(i, j);
    }

    public static void glVertexAttrib1sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttrib1sv(i, shortBuffer);
    }

    public static void nglVertexAttrib1dv(int i, long j) {
        GL20C.nglVertexAttrib1dv(i, j);
    }

    public static void glVertexAttrib1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL20C.glVertexAttrib1dv(i, doubleBuffer);
    }

    public static void nglVertexAttrib2fv(int i, long j) {
        GL20C.nglVertexAttrib2fv(i, j);
    }

    public static void glVertexAttrib2fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glVertexAttrib2fv(i, floatBuffer);
    }

    public static void nglVertexAttrib2sv(int i, long j) {
        GL20C.nglVertexAttrib2sv(i, j);
    }

    public static void glVertexAttrib2sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttrib2sv(i, shortBuffer);
    }

    public static void nglVertexAttrib2dv(int i, long j) {
        GL20C.nglVertexAttrib2dv(i, j);
    }

    public static void glVertexAttrib2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL20C.glVertexAttrib2dv(i, doubleBuffer);
    }

    public static void nglVertexAttrib3fv(int i, long j) {
        GL20C.nglVertexAttrib3fv(i, j);
    }

    public static void glVertexAttrib3fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glVertexAttrib3fv(i, floatBuffer);
    }

    public static void nglVertexAttrib3sv(int i, long j) {
        GL20C.nglVertexAttrib3sv(i, j);
    }

    public static void glVertexAttrib3sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttrib3sv(i, shortBuffer);
    }

    public static void nglVertexAttrib3dv(int i, long j) {
        GL20C.nglVertexAttrib3dv(i, j);
    }

    public static void glVertexAttrib3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL20C.glVertexAttrib3dv(i, doubleBuffer);
    }

    public static void nglVertexAttrib4fv(int i, long j) {
        GL20C.nglVertexAttrib4fv(i, j);
    }

    public static void glVertexAttrib4fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL20C.glVertexAttrib4fv(i, floatBuffer);
    }

    public static void nglVertexAttrib4sv(int i, long j) {
        GL20C.nglVertexAttrib4sv(i, j);
    }

    public static void glVertexAttrib4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttrib4sv(i, shortBuffer);
    }

    public static void nglVertexAttrib4dv(int i, long j) {
        GL20C.nglVertexAttrib4dv(i, j);
    }

    public static void glVertexAttrib4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        GL20C.glVertexAttrib4dv(i, doubleBuffer);
    }

    public static void nglVertexAttrib4iv(int i, long j) {
        GL20C.nglVertexAttrib4iv(i, j);
    }

    public static void glVertexAttrib4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL20C.glVertexAttrib4iv(i, intBuffer);
    }

    public static void nglVertexAttrib4bv(int i, long j) {
        GL20C.nglVertexAttrib4bv(i, j);
    }

    public static void glVertexAttrib4bv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        GL20C.glVertexAttrib4bv(i, byteBuffer);
    }

    public static void nglVertexAttrib4ubv(int i, long j) {
        GL20C.nglVertexAttrib4ubv(i, j);
    }

    public static void glVertexAttrib4ubv(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        GL20C.glVertexAttrib4ubv(i, byteBuffer);
    }

    public static void nglVertexAttrib4usv(int i, long j) {
        GL20C.nglVertexAttrib4usv(i, j);
    }

    public static void glVertexAttrib4usv(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttrib4usv(i, shortBuffer);
    }

    public static void nglVertexAttrib4uiv(int i, long j) {
        GL20C.nglVertexAttrib4uiv(i, j);
    }

    public static void glVertexAttrib4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL20C.glVertexAttrib4uiv(i, intBuffer);
    }

    public static void nglVertexAttrib4Nbv(int i, long j) {
        GL20C.nglVertexAttrib4Nbv(i, j);
    }

    public static void glVertexAttrib4Nbv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        GL20C.glVertexAttrib4Nbv(i, byteBuffer);
    }

    public static void nglVertexAttrib4Nsv(int i, long j) {
        GL20C.nglVertexAttrib4Nsv(i, j);
    }

    public static void glVertexAttrib4Nsv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttrib4Nsv(i, shortBuffer);
    }

    public static void nglVertexAttrib4Niv(int i, long j) {
        GL20C.nglVertexAttrib4Niv(i, j);
    }

    public static void glVertexAttrib4Niv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL20C.glVertexAttrib4Niv(i, intBuffer);
    }

    public static void nglVertexAttrib4Nubv(int i, long j) {
        GL20C.nglVertexAttrib4Nubv(i, j);
    }

    public static void glVertexAttrib4Nubv(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        GL20C.glVertexAttrib4Nubv(i, byteBuffer);
    }

    public static void nglVertexAttrib4Nusv(int i, long j) {
        GL20C.nglVertexAttrib4Nusv(i, j);
    }

    public static void glVertexAttrib4Nusv(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttrib4Nusv(i, shortBuffer);
    }

    public static void nglVertexAttrib4Nuiv(int i, long j) {
        GL20C.nglVertexAttrib4Nuiv(i, j);
    }

    public static void glVertexAttrib4Nuiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL20C.glVertexAttrib4Nuiv(i, intBuffer);
    }

    public static void nglVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, long j) {
        GL20C.nglVertexAttribPointer(i, i2, i3, z, i4, j);
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL20C.glVertexAttribPointer(i, i2, i3, z, i4, byteBuffer);
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        GL20C.glVertexAttribPointer(i, i2, i3, z, i4, j);
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL20C.glVertexAttribPointer(i, i2, i3, z, i4, shortBuffer);
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL20C.glVertexAttribPointer(i, i2, i3, z, i4, intBuffer);
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL20C.glVertexAttribPointer(i, i2, i3, z, i4, floatBuffer);
    }

    public static void glEnableVertexAttribArray(@NativeType("GLuint") int i) {
        GL20C.glEnableVertexAttribArray(i);
    }

    public static void glDisableVertexAttribArray(@NativeType("GLuint") int i) {
        GL20C.glDisableVertexAttribArray(i);
    }

    public static void nglBindAttribLocation(int i, int i2, long j) {
        GL20C.nglBindAttribLocation(i, i2, j);
    }

    public static void glBindAttribLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL20C.glBindAttribLocation(i, i2, byteBuffer);
    }

    public static void glBindAttribLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        GL20C.glBindAttribLocation(i, i2, charSequence);
    }

    public static void nglGetActiveAttrib(int i, int i2, int i3, long j, long j2, long j3, long j4) {
        GL20C.nglGetActiveAttrib(i, i2, i3, j, j2, j3, j4);
    }

    public static void glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetActiveAttrib(i, i2, intBuffer, intBuffer2, intBuffer3, byteBuffer);
    }

    @NativeType("void")
    public static String glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return GL20C.glGetActiveAttrib(i, i2, i3, intBuffer, intBuffer2);
    }

    @NativeType("void")
    public static String glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetActiveAttrib(i, i2, glGetProgrami(i, 35722), intBuffer, intBuffer2);
    }

    public static int nglGetAttribLocation(int i, long j) {
        return GL20C.nglGetAttribLocation(i, j);
    }

    @NativeType("GLint")
    public static int glGetAttribLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL20C.glGetAttribLocation(i, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetAttribLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL20C.glGetAttribLocation(i, charSequence);
    }

    public static void nglGetVertexAttribiv(int i, int i2, long j) {
        GL20C.nglGetVertexAttribiv(i, i2, j);
    }

    public static void glGetVertexAttribiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL20C.glGetVertexAttribiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetVertexAttribi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL20C.glGetVertexAttribi(i, i2);
    }

    public static void nglGetVertexAttribfv(int i, int i2, long j) {
        GL20C.nglGetVertexAttribfv(i, i2, j);
    }

    public static void glGetVertexAttribfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL20C.glGetVertexAttribfv(i, i2, floatBuffer);
    }

    public static void nglGetVertexAttribdv(int i, int i2, long j) {
        GL20C.nglGetVertexAttribdv(i, i2, j);
    }

    public static void glGetVertexAttribdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL20C.glGetVertexAttribdv(i, i2, doubleBuffer);
    }

    public static void nglGetVertexAttribPointerv(int i, int i2, long j) {
        GL20C.nglGetVertexAttribPointerv(i, i2, j);
    }

    public static void glGetVertexAttribPointerv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        GL20C.glGetVertexAttribPointerv(i, i2, pointerBuffer);
    }

    @NativeType("void")
    public static long glGetVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL20C.glGetVertexAttribPointer(i, i2);
    }

    public static void nglDrawBuffers(int i, long j) {
        GL20C.nglDrawBuffers(i, j);
    }

    public static void glDrawBuffers(@NativeType("GLenum const *") IntBuffer intBuffer) {
        GL20C.glDrawBuffers(intBuffer);
    }

    public static void glDrawBuffers(@NativeType("GLenum const *") int i) {
        GL20C.glDrawBuffers(i);
    }

    public static void glBlendEquationSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        GL20C.glBlendEquationSeparate(i, i2);
    }

    public static void glStencilOpSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4) {
        GL20C.glStencilOpSeparate(i, i2, i3, i4);
    }

    public static void glStencilFuncSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4) {
        GL20C.glStencilFuncSeparate(i, i2, i3, i4);
    }

    public static void glStencilMaskSeparate(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL20C.glStencilMaskSeparate(i, i2);
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLint const *") int[] iArr) {
        GL20C.glShaderSource(i, pointerBuffer, iArr);
    }

    public static void glUniform1fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glUniform1fv(i, fArr);
    }

    public static void glUniform2fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glUniform2fv(i, fArr);
    }

    public static void glUniform3fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glUniform3fv(i, fArr);
    }

    public static void glUniform4fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glUniform4fv(i, fArr);
    }

    public static void glUniform1iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        GL20C.glUniform1iv(i, iArr);
    }

    public static void glUniform2iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        GL20C.glUniform2iv(i, iArr);
    }

    public static void glUniform3iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        GL20C.glUniform3iv(i, iArr);
    }

    public static void glUniform4iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        GL20C.glUniform4iv(i, iArr);
    }

    public static void glUniformMatrix2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glUniformMatrix2fv(i, z, fArr);
    }

    public static void glUniformMatrix3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glUniformMatrix3fv(i, z, fArr);
    }

    public static void glUniformMatrix4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glUniformMatrix4fv(i, z, fArr);
    }

    public static void glGetShaderiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL20C.glGetShaderiv(i, i2, iArr);
    }

    public static void glGetProgramiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL20C.glGetProgramiv(i, i2, iArr);
    }

    public static void glGetShaderInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetShaderInfoLog(i, iArr, byteBuffer);
    }

    public static void glGetProgramInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetProgramInfoLog(i, iArr, byteBuffer);
    }

    public static void glGetAttachedShaders(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLuint *") int[] iArr2) {
        GL20C.glGetAttachedShaders(i, iArr, iArr2);
    }

    public static void glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetActiveUniform(i, i2, iArr, iArr2, iArr3, byteBuffer);
    }

    public static void glGetUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL20C.glGetUniformfv(i, i2, fArr);
    }

    public static void glGetUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") int[] iArr) {
        GL20C.glGetUniformiv(i, i2, iArr);
    }

    public static void glGetShaderSource(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetShaderSource(i, iArr, byteBuffer);
    }

    public static void glVertexAttrib1fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glVertexAttrib1fv(i, fArr);
    }

    public static void glVertexAttrib1sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        GL20C.glVertexAttrib1sv(i, sArr);
    }

    public static void glVertexAttrib1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL20C.glVertexAttrib1dv(i, dArr);
    }

    public static void glVertexAttrib2fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glVertexAttrib2fv(i, fArr);
    }

    public static void glVertexAttrib2sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        GL20C.glVertexAttrib2sv(i, sArr);
    }

    public static void glVertexAttrib2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL20C.glVertexAttrib2dv(i, dArr);
    }

    public static void glVertexAttrib3fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glVertexAttrib3fv(i, fArr);
    }

    public static void glVertexAttrib3sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        GL20C.glVertexAttrib3sv(i, sArr);
    }

    public static void glVertexAttrib3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL20C.glVertexAttrib3dv(i, dArr);
    }

    public static void glVertexAttrib4fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        GL20C.glVertexAttrib4fv(i, fArr);
    }

    public static void glVertexAttrib4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        GL20C.glVertexAttrib4sv(i, sArr);
    }

    public static void glVertexAttrib4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        GL20C.glVertexAttrib4dv(i, dArr);
    }

    public static void glVertexAttrib4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL20C.glVertexAttrib4iv(i, iArr);
    }

    public static void glVertexAttrib4usv(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        GL20C.glVertexAttrib4usv(i, sArr);
    }

    public static void glVertexAttrib4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL20C.glVertexAttrib4uiv(i, iArr);
    }

    public static void glVertexAttrib4Nsv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        GL20C.glVertexAttrib4Nsv(i, sArr);
    }

    public static void glVertexAttrib4Niv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL20C.glVertexAttrib4Niv(i, iArr);
    }

    public static void glVertexAttrib4Nusv(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        GL20C.glVertexAttrib4Nusv(i, sArr);
    }

    public static void glVertexAttrib4Nuiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL20C.glVertexAttrib4Nuiv(i, iArr);
    }

    public static void glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL20C.glGetActiveAttrib(i, i2, iArr, iArr2, iArr3, byteBuffer);
    }

    public static void glGetVertexAttribiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL20C.glGetVertexAttribiv(i, i2, iArr);
    }

    public static void glGetVertexAttribfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL20C.glGetVertexAttribfv(i, i2, fArr);
    }

    public static void glGetVertexAttribdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        GL20C.glGetVertexAttribdv(i, i2, dArr);
    }

    public static void glDrawBuffers(@NativeType("GLenum const *") int[] iArr) {
        GL20C.glDrawBuffers(iArr);
    }
}
