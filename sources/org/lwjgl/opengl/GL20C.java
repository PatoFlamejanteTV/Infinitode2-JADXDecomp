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

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL20C.class */
public class GL20C extends GL15C {
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
    public static final int GL_VERTEX_PROGRAM_POINT_SIZE = 34370;
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

    @NativeType("GLuint")
    public static native int glCreateProgram();

    public static native void glDeleteProgram(@NativeType("GLuint") int i);

    @NativeType("GLboolean")
    public static native boolean glIsProgram(@NativeType("GLuint") int i);

    @NativeType("GLuint")
    public static native int glCreateShader(@NativeType("GLenum") int i);

    public static native void glDeleteShader(@NativeType("GLuint") int i);

    @NativeType("GLboolean")
    public static native boolean glIsShader(@NativeType("GLuint") int i);

    public static native void glAttachShader(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glDetachShader(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void nglShaderSource(int i, int i2, long j, long j2);

    public static native void glCompileShader(@NativeType("GLuint") int i);

    public static native void glLinkProgram(@NativeType("GLuint") int i);

    public static native void glUseProgram(@NativeType("GLuint") int i);

    public static native void glValidateProgram(@NativeType("GLuint") int i);

    public static native void glUniform1f(@NativeType("GLint") int i, @NativeType("GLfloat") float f);

    public static native void glUniform2f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glUniform3f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glUniform4f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glUniform1i(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void glUniform2i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glUniform3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glUniform4i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void nglUniform1fv(int i, int i2, long j);

    public static native void nglUniform2fv(int i, int i2, long j);

    public static native void nglUniform3fv(int i, int i2, long j);

    public static native void nglUniform4fv(int i, int i2, long j);

    public static native void nglUniform1iv(int i, int i2, long j);

    public static native void nglUniform2iv(int i, int i2, long j);

    public static native void nglUniform3iv(int i, int i2, long j);

    public static native void nglUniform4iv(int i, int i2, long j);

    public static native void nglUniformMatrix2fv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix3fv(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix4fv(int i, int i2, boolean z, long j);

    public static native void nglGetShaderiv(int i, int i2, long j);

    public static native void nglGetProgramiv(int i, int i2, long j);

    public static native void nglGetShaderInfoLog(int i, int i2, long j, long j2);

    public static native void nglGetProgramInfoLog(int i, int i2, long j, long j2);

    public static native void nglGetAttachedShaders(int i, int i2, long j, long j2);

    public static native int nglGetUniformLocation(int i, long j);

    public static native void nglGetActiveUniform(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native void nglGetUniformfv(int i, int i2, long j);

    public static native void nglGetUniformiv(int i, int i2, long j);

    public static native void nglGetShaderSource(int i, int i2, long j, long j2);

    public static native void glVertexAttrib1f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f);

    public static native void glVertexAttrib1s(@NativeType("GLuint") int i, @NativeType("GLshort") short s);

    public static native void glVertexAttrib1d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d);

    public static native void glVertexAttrib2f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glVertexAttrib2s(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glVertexAttrib2d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glVertexAttrib3f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glVertexAttrib3s(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glVertexAttrib3d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glVertexAttrib4f(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glVertexAttrib4s(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glVertexAttrib4d(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void glVertexAttrib4Nub(@NativeType("GLuint") int i, @NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4, @NativeType("GLubyte") byte b5);

    public static native void nglVertexAttrib1fv(int i, long j);

    public static native void nglVertexAttrib1sv(int i, long j);

    public static native void nglVertexAttrib1dv(int i, long j);

    public static native void nglVertexAttrib2fv(int i, long j);

    public static native void nglVertexAttrib2sv(int i, long j);

    public static native void nglVertexAttrib2dv(int i, long j);

    public static native void nglVertexAttrib3fv(int i, long j);

    public static native void nglVertexAttrib3sv(int i, long j);

    public static native void nglVertexAttrib3dv(int i, long j);

    public static native void nglVertexAttrib4fv(int i, long j);

    public static native void nglVertexAttrib4sv(int i, long j);

    public static native void nglVertexAttrib4dv(int i, long j);

    public static native void nglVertexAttrib4iv(int i, long j);

    public static native void nglVertexAttrib4bv(int i, long j);

    public static native void nglVertexAttrib4ubv(int i, long j);

    public static native void nglVertexAttrib4usv(int i, long j);

    public static native void nglVertexAttrib4uiv(int i, long j);

    public static native void nglVertexAttrib4Nbv(int i, long j);

    public static native void nglVertexAttrib4Nsv(int i, long j);

    public static native void nglVertexAttrib4Niv(int i, long j);

    public static native void nglVertexAttrib4Nubv(int i, long j);

    public static native void nglVertexAttrib4Nusv(int i, long j);

    public static native void nglVertexAttrib4Nuiv(int i, long j);

    public static native void nglVertexAttribPointer(int i, int i2, int i3, boolean z, int i4, long j);

    public static native void glEnableVertexAttribArray(@NativeType("GLuint") int i);

    public static native void glDisableVertexAttribArray(@NativeType("GLuint") int i);

    public static native void nglBindAttribLocation(int i, int i2, long j);

    public static native void nglGetActiveAttrib(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native int nglGetAttribLocation(int i, long j);

    public static native void nglGetVertexAttribiv(int i, int i2, long j);

    public static native void nglGetVertexAttribfv(int i, int i2, long j);

    public static native void nglGetVertexAttribdv(int i, int i2, long j);

    public static native void nglGetVertexAttribPointerv(int i, int i2, long j);

    public static native void nglDrawBuffers(int i, long j);

    public static native void glBlendEquationSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    public static native void glStencilOpSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4);

    public static native void glStencilFuncSeparate(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4);

    public static native void glStencilMaskSeparate(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL20C() {
        throw new UnsupportedOperationException();
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, pointerBuffer.remaining());
        }
        nglShaderSource(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence... charSequenceArr) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArrayi = APIUtil.apiArrayi(stackGet, MemoryUtil::memUTF8, charSequenceArr);
            nglShaderSource(i, charSequenceArr.length, i, apiArrayi - (charSequenceArr.length << 2));
            APIUtil.apiArrayFree(apiArrayi, charSequenceArr.length);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArrayi = APIUtil.apiArrayi(stackGet, MemoryUtil::memUTF8, charSequence);
            nglShaderSource(i, 1, i, apiArrayi - 4);
            APIUtil.apiArrayFree(apiArrayi, 1);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glUniform1fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform1fv(i, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform2fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform2fv(i, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform3fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform3fv(i, floatBuffer.remaining() / 3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform4fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform4fv(i, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform1iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform1iv(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform2iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform2iv(i, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform3iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform3iv(i, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform4iv(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform4iv(i, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniformMatrix2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix2fv(i, floatBuffer.remaining() >> 2, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix3fv(i, floatBuffer.remaining() / 9, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix4fv(i, floatBuffer.remaining() >> 4, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetShaderiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetShaderiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetShaderi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetShaderiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetProgramiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetProgramiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetProgrami(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetProgramiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetShaderInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetShaderInfoLog(i, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetShaderInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        ByteBuffer memAlloc = MemoryUtil.memAlloc(i2);
        try {
            IntBuffer ints = stackGet.ints(0);
            nglGetShaderInfoLog(i, i2, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(memAlloc));
            return MemoryUtil.memUTF8(memAlloc, ints.get(0));
        } finally {
            MemoryUtil.memFree(memAlloc);
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetShaderInfoLog(@NativeType("GLuint") int i) {
        return glGetShaderInfoLog(i, glGetShaderi(i, 35716));
    }

    public static void glGetProgramInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetProgramInfoLog(i, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetProgramInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        ByteBuffer memAlloc = MemoryUtil.memAlloc(i2);
        try {
            IntBuffer ints = stackGet.ints(0);
            nglGetProgramInfoLog(i, i2, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(memAlloc));
            return MemoryUtil.memUTF8(memAlloc, ints.get(0));
        } finally {
            MemoryUtil.memFree(memAlloc);
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetProgramInfoLog(@NativeType("GLuint") int i) {
        return glGetProgramInfoLog(i, glGetProgrami(i, 35716));
    }

    public static void glGetAttachedShaders(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLuint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetAttachedShaders(i, intBuffer2.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    @NativeType("GLint")
    public static int glGetUniformLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetUniformLocation(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetUniformLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetUniformLocation(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        nglGetActiveUniform(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetActiveUniform(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetActiveUniform(i, i2, glGetProgrami(i, 35719), intBuffer, intBuffer2);
    }

    public static void glGetUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetUniformfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetUniformf(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetUniformfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetUniformiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetUniformi(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetUniformiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetShaderSource(@NativeType("GLuint") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetShaderSource(i, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetShaderSource(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        ByteBuffer memAlloc = MemoryUtil.memAlloc(i2);
        try {
            IntBuffer ints = stackGet.ints(0);
            nglGetShaderSource(i, i2, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(memAlloc));
            return MemoryUtil.memUTF8(memAlloc, ints.get(0));
        } finally {
            MemoryUtil.memFree(memAlloc);
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetShaderSource(@NativeType("GLuint") int i) {
        return glGetShaderSource(i, glGetShaderi(i, 35720));
    }

    public static void glVertexAttrib1fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglVertexAttrib1fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib1sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglVertexAttrib1sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglVertexAttrib1dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib2fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglVertexAttrib2fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib2sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglVertexAttrib2sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglVertexAttrib2dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib3fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglVertexAttrib3fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib3sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglVertexAttrib3sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglVertexAttrib3dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib4fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglVertexAttrib4fv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglVertexAttrib4dv(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttrib4bv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4bv(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4ubv(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4ubv(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4usv(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4usv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttrib4Nbv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4Nbv(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4Nsv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4Nsv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4Niv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4Niv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttrib4Nubv(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4Nubv(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4Nusv(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4Nusv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4Nuiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4Nuiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglVertexAttribPointer(i, i2, i3, z, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        nglVertexAttribPointer(i, i2, i3, z, i4, j);
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglVertexAttribPointer(i, i2, i3, z, i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglVertexAttribPointer(i, i2, i3, z, i4, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglVertexAttribPointer(i, i2, i3, z, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glBindAttribLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglBindAttribLocation(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glBindAttribLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglBindAttribLocation(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        nglGetActiveAttrib(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetActiveAttrib(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetActiveAttrib(i, i2, glGetProgrami(i, 35722), intBuffer, intBuffer2);
    }

    @NativeType("GLint")
    public static int glGetAttribLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetAttribLocation(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetAttribLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetAttribLocation(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexAttribiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetVertexAttribiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexAttribi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexAttribiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexAttribfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetVertexAttribfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetVertexAttribdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetVertexAttribdv(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetVertexAttribPointerv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetVertexAttribPointerv(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetVertexAttribPointer(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetVertexAttribPointerv(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDrawBuffers(@NativeType("GLenum const *") IntBuffer intBuffer) {
        nglDrawBuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDrawBuffers(@NativeType("GLenum const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDrawBuffers(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glShaderSource(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glShaderSource;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, pointerBuffer.remaining());
        }
        JNI.callPPV(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), iArr, j);
    }

    public static void glUniform1fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform1fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length, fArr, j);
    }

    public static void glUniform2fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 1, fArr, j);
    }

    public static void glUniform3fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 3, fArr, j);
    }

    public static void glUniform4fv(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 2, fArr, j);
    }

    public static void glUniform1iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform1iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glUniform2iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 1, iArr, j);
    }

    public static void glUniform3iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length / 3, iArr, j);
    }

    public static void glUniform4iv(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 2, iArr, j);
    }

    public static void glUniformMatrix2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 2, z, fArr, j);
    }

    public static void glUniformMatrix3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 9, z, fArr, j);
    }

    public static void glUniformMatrix4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 4, z, fArr, j);
    }

    public static void glGetShaderiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetShaderiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetProgramiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetProgramiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetShaderInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetShaderInfoLog;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetProgramInfoLog(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetProgramInfoLog;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetAttachedShaders(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLuint *") int[] iArr2) {
        long j = GL.getICD().glGetAttachedShaders;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, iArr2.length, iArr, iArr2, j);
    }

    public static void glGetActiveUniform(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveUniform;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        JNI.callPPPPV(i, i2, byteBuffer.remaining(), iArr, iArr2, iArr3, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetUniformfv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetUniformfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetUniformiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetUniformiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetShaderSource(@NativeType("GLuint") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetShaderSource;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glVertexAttrib1fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib1fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib1sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib1sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib1dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib1dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib2fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib2sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib2sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib2dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib3fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib3sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib3dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib4fv(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4dv(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttrib4usv(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4usv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttrib4Nsv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4Nsv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4Niv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4Niv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttrib4Nusv(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4Nusv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4Nuiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4Nuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glGetActiveAttrib(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveAttrib;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        JNI.callPPPPV(i, i2, byteBuffer.remaining(), iArr, iArr2, iArr3, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetVertexAttribiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexAttribiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexAttribfv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetVertexAttribfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetVertexAttribdv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetVertexAttribdv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glDrawBuffers(@NativeType("GLenum const *") int[] iArr) {
        long j = GL.getICD().glDrawBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }
}
