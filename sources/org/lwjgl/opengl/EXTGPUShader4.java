package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTGPUShader4.class */
public class EXTGPUShader4 {
    public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER_EXT = 35069;
    public static final int GL_SAMPLER_1D_ARRAY_EXT = 36288;
    public static final int GL_SAMPLER_2D_ARRAY_EXT = 36289;
    public static final int GL_SAMPLER_BUFFER_EXT = 36290;
    public static final int GL_SAMPLER_1D_ARRAY_SHADOW_EXT = 36291;
    public static final int GL_SAMPLER_2D_ARRAY_SHADOW_EXT = 36292;
    public static final int GL_SAMPLER_CUBE_SHADOW_EXT = 36293;
    public static final int GL_UNSIGNED_INT_VEC2_EXT = 36294;
    public static final int GL_UNSIGNED_INT_VEC3_EXT = 36295;
    public static final int GL_UNSIGNED_INT_VEC4_EXT = 36296;
    public static final int GL_INT_SAMPLER_1D_EXT = 36297;
    public static final int GL_INT_SAMPLER_2D_EXT = 36298;
    public static final int GL_INT_SAMPLER_3D_EXT = 36299;
    public static final int GL_INT_SAMPLER_CUBE_EXT = 36300;
    public static final int GL_INT_SAMPLER_2D_RECT_EXT = 36301;
    public static final int GL_INT_SAMPLER_1D_ARRAY_EXT = 36302;
    public static final int GL_INT_SAMPLER_2D_ARRAY_EXT = 36303;
    public static final int GL_INT_SAMPLER_BUFFER_EXT = 36304;
    public static final int GL_UNSIGNED_INT_SAMPLER_1D_EXT = 36305;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_EXT = 36306;
    public static final int GL_UNSIGNED_INT_SAMPLER_3D_EXT = 36307;
    public static final int GL_UNSIGNED_INT_SAMPLER_CUBE_EXT = 36308;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT_EXT = 36309;
    public static final int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY_EXT = 36310;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY_EXT = 36311;
    public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER_EXT = 36312;
    public static final int GL_MIN_PROGRAM_TEXEL_OFFSET_EXT = 35076;
    public static final int GL_MAX_PROGRAM_TEXEL_OFFSET_EXT = 35077;

    public static native void glVertexAttribI1iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2);

    public static native void glVertexAttribI2iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glVertexAttribI3iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glVertexAttribI4iEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glVertexAttribI1uiEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glVertexAttribI2uiEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glVertexAttribI3uiEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glVertexAttribI4uiEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void nglVertexAttribI1ivEXT(int i, long j);

    public static native void nglVertexAttribI2ivEXT(int i, long j);

    public static native void nglVertexAttribI3ivEXT(int i, long j);

    public static native void nglVertexAttribI4ivEXT(int i, long j);

    public static native void nglVertexAttribI1uivEXT(int i, long j);

    public static native void nglVertexAttribI2uivEXT(int i, long j);

    public static native void nglVertexAttribI3uivEXT(int i, long j);

    public static native void nglVertexAttribI4uivEXT(int i, long j);

    public static native void nglVertexAttribI4bvEXT(int i, long j);

    public static native void nglVertexAttribI4svEXT(int i, long j);

    public static native void nglVertexAttribI4ubvEXT(int i, long j);

    public static native void nglVertexAttribI4usvEXT(int i, long j);

    public static native void nglVertexAttribIPointerEXT(int i, int i2, int i3, int i4, long j);

    public static native void nglGetVertexAttribIivEXT(int i, int i2, long j);

    public static native void nglGetVertexAttribIuivEXT(int i, int i2, long j);

    public static native void nglGetUniformuivEXT(int i, int i2, long j);

    public static native void nglBindFragDataLocationEXT(int i, int i2, long j);

    public static native int nglGetFragDataLocationEXT(int i, long j);

    public static native void glUniform1uiEXT(@NativeType("GLint") int i, @NativeType("GLuint") int i2);

    public static native void glUniform2uiEXT(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glUniform3uiEXT(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4);

    public static native void glUniform4uiEXT(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5);

    public static native void nglUniform1uivEXT(int i, int i2, long j);

    public static native void nglUniform2uivEXT(int i, int i2, long j);

    public static native void nglUniform3uivEXT(int i, int i2, long j);

    public static native void nglUniform4uivEXT(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected EXTGPUShader4() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexAttribI1ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribI1ivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI2ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglVertexAttribI2ivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI3ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglVertexAttribI3ivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI4ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttribI4ivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI1uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribI1uivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI2uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglVertexAttribI2uivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI3uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglVertexAttribI3uivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI4uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttribI4uivEXT(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI4bvEXT(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttribI4bvEXT(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribI4svEXT(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttribI4svEXT(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribI4ubvEXT(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttribI4ubvEXT(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribI4usvEXT(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttribI4usvEXT(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribIPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglVertexAttribIPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribIPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        nglVertexAttribIPointerEXT(i, i2, i3, i4, j);
    }

    public static void glVertexAttribIPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglVertexAttribIPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribIPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglVertexAttribIPointerEXT(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetVertexAttribIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetVertexAttribIivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexAttribIiEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexAttribIivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexAttribIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetVertexAttribIuivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexAttribIuiEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexAttribIuivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetUniformuivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetUniformuivEXT(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetUniformuiEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetUniformuivEXT(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glBindFragDataLocationEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglBindFragDataLocationEXT(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glBindFragDataLocationEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglBindFragDataLocationEXT(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLint")
    public static int glGetFragDataLocationEXT(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetFragDataLocationEXT(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetFragDataLocationEXT(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetFragDataLocationEXT(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glUniform1uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform1uivEXT(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform2uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform2uivEXT(i, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform3uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform3uivEXT(i, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform4uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform4uivEXT(i, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI1ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI1ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI2ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI2ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI3ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI3ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI4ivEXT(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI4ivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI1uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI1uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI2uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI2uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI3uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI3uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI4uivEXT(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI4uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI4svEXT(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribI4svEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttribI4usvEXT(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribI4usvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttribIPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribIPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glVertexAttribIPointerEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribIPointerEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glGetVertexAttribIivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexAttribIivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexAttribIuivEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetVertexAttribIuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetUniformuivEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetUniformuivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glUniform1uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform1uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glUniform2uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform2uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 1, iArr, j);
    }

    public static void glUniform3uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform3uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length / 3, iArr, j);
    }

    public static void glUniform4uivEXT(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform4uivEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 2, iArr, j);
    }
}
