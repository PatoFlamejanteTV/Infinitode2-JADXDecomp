package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexShader.class */
public class ARBVertexShader {
    public static final int GL_VERTEX_SHADER_ARB = 35633;
    public static final int GL_MAX_VERTEX_UNIFORM_COMPONENTS_ARB = 35658;
    public static final int GL_MAX_VARYING_FLOATS_ARB = 35659;
    public static final int GL_MAX_VERTEX_ATTRIBS_ARB = 34921;
    public static final int GL_MAX_TEXTURE_IMAGE_UNITS_ARB = 34930;
    public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS_ARB = 35660;
    public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS_ARB = 35661;
    public static final int GL_MAX_TEXTURE_COORDS_ARB = 34929;
    public static final int GL_VERTEX_PROGRAM_POINT_SIZE_ARB = 34370;
    public static final int GL_VERTEX_PROGRAM_TWO_SIDE_ARB = 34371;
    public static final int GL_OBJECT_ACTIVE_ATTRIBUTES_ARB = 35721;
    public static final int GL_OBJECT_ACTIVE_ATTRIBUTE_MAX_LENGTH_ARB = 35722;
    public static final int GL_VERTEX_ATTRIB_ARRAY_ENABLED_ARB = 34338;
    public static final int GL_VERTEX_ATTRIB_ARRAY_SIZE_ARB = 34339;
    public static final int GL_VERTEX_ATTRIB_ARRAY_STRIDE_ARB = 34340;
    public static final int GL_VERTEX_ATTRIB_ARRAY_TYPE_ARB = 34341;
    public static final int GL_VERTEX_ATTRIB_ARRAY_NORMALIZED_ARB = 34922;
    public static final int GL_CURRENT_VERTEX_ATTRIB_ARB = 34342;
    public static final int GL_VERTEX_ATTRIB_ARRAY_POINTER_ARB = 34373;
    public static final int GL_FLOAT_VEC2_ARB = 35664;
    public static final int GL_FLOAT_VEC3_ARB = 35665;
    public static final int GL_FLOAT_VEC4_ARB = 35666;
    public static final int GL_FLOAT_MAT2_ARB = 35674;
    public static final int GL_FLOAT_MAT3_ARB = 35675;
    public static final int GL_FLOAT_MAT4_ARB = 35676;

    public static native void glVertexAttrib1fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f);

    public static native void glVertexAttrib1sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s);

    public static native void glVertexAttrib1dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d);

    public static native void glVertexAttrib2fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glVertexAttrib2sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glVertexAttrib2dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glVertexAttrib3fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glVertexAttrib3sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glVertexAttrib3dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glVertexAttrib4fARB(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glVertexAttrib4sARB(@NativeType("GLuint") int i, @NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glVertexAttrib4dARB(@NativeType("GLuint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void glVertexAttrib4NubARB(@NativeType("GLuint") int i, @NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4, @NativeType("GLubyte") byte b5);

    public static native void nglVertexAttrib1fvARB(int i, long j);

    public static native void nglVertexAttrib1svARB(int i, long j);

    public static native void nglVertexAttrib1dvARB(int i, long j);

    public static native void nglVertexAttrib2fvARB(int i, long j);

    public static native void nglVertexAttrib2svARB(int i, long j);

    public static native void nglVertexAttrib2dvARB(int i, long j);

    public static native void nglVertexAttrib3fvARB(int i, long j);

    public static native void nglVertexAttrib3svARB(int i, long j);

    public static native void nglVertexAttrib3dvARB(int i, long j);

    public static native void nglVertexAttrib4fvARB(int i, long j);

    public static native void nglVertexAttrib4svARB(int i, long j);

    public static native void nglVertexAttrib4dvARB(int i, long j);

    public static native void nglVertexAttrib4ivARB(int i, long j);

    public static native void nglVertexAttrib4bvARB(int i, long j);

    public static native void nglVertexAttrib4ubvARB(int i, long j);

    public static native void nglVertexAttrib4usvARB(int i, long j);

    public static native void nglVertexAttrib4uivARB(int i, long j);

    public static native void nglVertexAttrib4NbvARB(int i, long j);

    public static native void nglVertexAttrib4NsvARB(int i, long j);

    public static native void nglVertexAttrib4NivARB(int i, long j);

    public static native void nglVertexAttrib4NubvARB(int i, long j);

    public static native void nglVertexAttrib4NusvARB(int i, long j);

    public static native void nglVertexAttrib4NuivARB(int i, long j);

    public static native void nglVertexAttribPointerARB(int i, int i2, int i3, boolean z, int i4, long j);

    public static native void glEnableVertexAttribArrayARB(@NativeType("GLuint") int i);

    public static native void glDisableVertexAttribArrayARB(@NativeType("GLuint") int i);

    public static native void nglBindAttribLocationARB(int i, int i2, long j);

    public static native void nglGetActiveAttribARB(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native int nglGetAttribLocationARB(int i, long j);

    public static native void nglGetVertexAttribivARB(int i, int i2, long j);

    public static native void nglGetVertexAttribfvARB(int i, int i2, long j);

    public static native void nglGetVertexAttribdvARB(int i, int i2, long j);

    public static native void nglGetVertexAttribPointervARB(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected ARBVertexShader() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexAttrib1fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglVertexAttrib1fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib1svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglVertexAttrib1svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib1dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglVertexAttrib1dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib2fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglVertexAttrib2fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib2svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglVertexAttrib2svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib2dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglVertexAttrib2dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib3fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglVertexAttrib3fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib3svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglVertexAttrib3svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib3dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglVertexAttrib3dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib4fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglVertexAttrib4fvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttrib4svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4svARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglVertexAttrib4dvARB(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexAttrib4ivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4ivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttrib4bvARB(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4bvARB(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4ubvARB(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4ubvARB(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4usvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4usvARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4uivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4uivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttrib4NbvARB(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4NbvARB(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4NsvARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4NsvARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4NivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4NivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttrib4NubvARB(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttrib4NubvARB(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttrib4NusvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttrib4NusvARB(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttrib4NuivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttrib4NuivARB(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglVertexAttribPointerARB(i, i2, i3, z, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        nglVertexAttribPointerARB(i, i2, i3, z, i4, j);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglVertexAttribPointerARB(i, i2, i3, z, i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglVertexAttribPointerARB(i, i2, i3, z, i4, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglVertexAttribPointerARB(i, i2, i3, z, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glBindAttribLocationARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglBindAttribLocationARB(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glBindAttribLocationARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglBindAttribLocationARB(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveAttribARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        nglGetActiveAttribARB(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveAttribARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetActiveAttribARB(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveAttribARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetActiveAttribARB(i, i2, ARBShaderObjects.glGetObjectParameteriARB(i, 35722), intBuffer, intBuffer2);
    }

    @NativeType("GLint")
    public static int glGetAttribLocationARB(@NativeType("GLhandleARB") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetAttribLocationARB(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetAttribLocationARB(@NativeType("GLhandleARB") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetAttribLocationARB(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexAttribivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetVertexAttribivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexAttribiARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexAttribivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexAttribfvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetVertexAttribfvARB(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetVertexAttribdvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetVertexAttribdvARB(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetVertexAttribPointervARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetVertexAttribPointervARB(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetVertexAttribPointervARB(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glVertexAttrib1fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib1fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib1svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib1svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib1dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib1dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib2fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib2fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib2svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib2svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib2dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib2dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib3fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib3fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib3svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib3svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib3dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib3dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib4fvARB(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertexAttrib4fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glVertexAttrib4svARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4svARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4dvARB(@NativeType("GLuint") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertexAttrib4dvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glVertexAttrib4ivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttrib4usvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4usvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4uivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4uivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttrib4NsvARB(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4NsvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4NivARB(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4NivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttrib4NusvARB(@NativeType("GLuint") int i, @NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttrib4NusvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttrib4NuivARB(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttrib4NuivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribPointerARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, z, i4, sArr, j);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribPointerARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, z, i4, iArr, j);
    }

    public static void glVertexAttribPointerARB(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLsizei") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glVertexAttribPointerARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, z, i4, fArr, j);
    }

    public static void glGetActiveAttribARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveAttribARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        JNI.callPPPPV(i, i2, byteBuffer.remaining(), iArr, iArr2, iArr3, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetVertexAttribivARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexAttribivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexAttribfvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetVertexAttribfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetVertexAttribdvARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetVertexAttribdvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }
}
