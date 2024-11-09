package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBShaderObjects.class */
public class ARBShaderObjects {
    public static final int GL_PROGRAM_OBJECT_ARB = 35648;
    public static final int GL_OBJECT_TYPE_ARB = 35662;
    public static final int GL_OBJECT_SUBTYPE_ARB = 35663;
    public static final int GL_OBJECT_DELETE_STATUS_ARB = 35712;
    public static final int GL_OBJECT_COMPILE_STATUS_ARB = 35713;
    public static final int GL_OBJECT_LINK_STATUS_ARB = 35714;
    public static final int GL_OBJECT_VALIDATE_STATUS_ARB = 35715;
    public static final int GL_OBJECT_INFO_LOG_LENGTH_ARB = 35716;
    public static final int GL_OBJECT_ATTACHED_OBJECTS_ARB = 35717;
    public static final int GL_OBJECT_ACTIVE_UNIFORMS_ARB = 35718;
    public static final int GL_OBJECT_ACTIVE_UNIFORM_MAX_LENGTH_ARB = 35719;
    public static final int GL_OBJECT_SHADER_SOURCE_LENGTH_ARB = 35720;
    public static final int GL_SHADER_OBJECT_ARB = 35656;
    public static final int GL_FLOAT_VEC2_ARB = 35664;
    public static final int GL_FLOAT_VEC3_ARB = 35665;
    public static final int GL_FLOAT_VEC4_ARB = 35666;
    public static final int GL_INT_VEC2_ARB = 35667;
    public static final int GL_INT_VEC3_ARB = 35668;
    public static final int GL_INT_VEC4_ARB = 35669;
    public static final int GL_BOOL_ARB = 35670;
    public static final int GL_BOOL_VEC2_ARB = 35671;
    public static final int GL_BOOL_VEC3_ARB = 35672;
    public static final int GL_BOOL_VEC4_ARB = 35673;
    public static final int GL_FLOAT_MAT2_ARB = 35674;
    public static final int GL_FLOAT_MAT3_ARB = 35675;
    public static final int GL_FLOAT_MAT4_ARB = 35676;
    public static final int GL_SAMPLER_1D_ARB = 35677;
    public static final int GL_SAMPLER_2D_ARB = 35678;
    public static final int GL_SAMPLER_3D_ARB = 35679;
    public static final int GL_SAMPLER_CUBE_ARB = 35680;
    public static final int GL_SAMPLER_1D_SHADOW_ARB = 35681;
    public static final int GL_SAMPLER_2D_SHADOW_ARB = 35682;
    public static final int GL_SAMPLER_2D_RECT_ARB = 35683;
    public static final int GL_SAMPLER_2D_RECT_SHADOW_ARB = 35684;

    public static native void glDeleteObjectARB(@NativeType("GLhandleARB") int i);

    @NativeType("GLhandleARB")
    public static native int glGetHandleARB(@NativeType("GLenum") int i);

    public static native void glDetachObjectARB(@NativeType("GLhandleARB") int i, @NativeType("GLhandleARB") int i2);

    @NativeType("GLhandleARB")
    public static native int glCreateShaderObjectARB(@NativeType("GLenum") int i);

    public static native void nglShaderSourceARB(int i, int i2, long j, long j2);

    public static native void glCompileShaderARB(@NativeType("GLhandleARB") int i);

    @NativeType("GLhandleARB")
    public static native int glCreateProgramObjectARB();

    public static native void glAttachObjectARB(@NativeType("GLhandleARB") int i, @NativeType("GLhandleARB") int i2);

    public static native void glLinkProgramARB(@NativeType("GLhandleARB") int i);

    public static native void glUseProgramObjectARB(@NativeType("GLhandleARB") int i);

    public static native void glValidateProgramARB(@NativeType("GLhandleARB") int i);

    public static native void glUniform1fARB(@NativeType("GLint") int i, @NativeType("GLfloat") float f);

    public static native void glUniform2fARB(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glUniform3fARB(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glUniform4fARB(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glUniform1iARB(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void glUniform2iARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glUniform3iARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glUniform4iARB(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void nglUniform1fvARB(int i, int i2, long j);

    public static native void nglUniform2fvARB(int i, int i2, long j);

    public static native void nglUniform3fvARB(int i, int i2, long j);

    public static native void nglUniform4fvARB(int i, int i2, long j);

    public static native void nglUniform1ivARB(int i, int i2, long j);

    public static native void nglUniform2ivARB(int i, int i2, long j);

    public static native void nglUniform3ivARB(int i, int i2, long j);

    public static native void nglUniform4ivARB(int i, int i2, long j);

    public static native void nglUniformMatrix2fvARB(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix3fvARB(int i, int i2, boolean z, long j);

    public static native void nglUniformMatrix4fvARB(int i, int i2, boolean z, long j);

    public static native void nglGetObjectParameterfvARB(int i, int i2, long j);

    public static native void nglGetObjectParameterivARB(int i, int i2, long j);

    public static native void nglGetInfoLogARB(int i, int i2, long j, long j2);

    public static native void nglGetAttachedObjectsARB(int i, int i2, long j, long j2);

    public static native int nglGetUniformLocationARB(int i, long j);

    public static native void nglGetActiveUniformARB(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native void nglGetUniformfvARB(int i, int i2, long j);

    public static native void nglGetUniformivARB(int i, int i2, long j);

    public static native void nglGetShaderSourceARB(int i, int i2, long j, long j2);

    static {
        GL.initialize();
    }

    protected ARBShaderObjects() {
        throw new UnsupportedOperationException();
    }

    public static void glShaderSourceARB(@NativeType("GLhandleARB") int i, @NativeType("GLcharARB const **") PointerBuffer pointerBuffer, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, pointerBuffer.remaining());
        }
        nglShaderSourceARB(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glShaderSourceARB(@NativeType("GLhandleARB") int i, @NativeType("GLcharARB const **") CharSequence... charSequenceArr) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArrayi = APIUtil.apiArrayi(stackGet, MemoryUtil::memUTF8, charSequenceArr);
            nglShaderSourceARB(i, charSequenceArr.length, i, apiArrayi - (charSequenceArr.length << 2));
            APIUtil.apiArrayFree(apiArrayi, charSequenceArr.length);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glShaderSourceARB(@NativeType("GLhandleARB") int i, @NativeType("GLcharARB const **") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArrayi = APIUtil.apiArrayi(stackGet, MemoryUtil::memUTF8, charSequence);
            nglShaderSourceARB(i, 1, i, apiArrayi - 4);
            APIUtil.apiArrayFree(apiArrayi, 1);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glUniform1fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform1fvARB(i, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform2fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform2fvARB(i, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform3fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform3fvARB(i, floatBuffer.remaining() / 3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform4fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniform4fvARB(i, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniform1ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform1ivARB(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform2ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform2ivARB(i, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform3ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform3ivARB(i, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform4ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        nglUniform4ivARB(i, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniformMatrix2fvARB(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix2fvARB(i, floatBuffer.remaining() >> 2, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix3fvARB(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix3fvARB(i, floatBuffer.remaining() / 9, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glUniformMatrix4fvARB(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglUniformMatrix4fvARB(i, floatBuffer.remaining() >> 4, z, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetObjectParameterfvARB(@NativeType("GLhandleARB") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetObjectParameterfvARB(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetObjectParameterivARB(@NativeType("GLhandleARB") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetObjectParameterivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetObjectParameteriARB(@NativeType("GLhandleARB") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetObjectParameterivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetInfoLogARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLcharARB *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetInfoLogARB(i, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetInfoLogARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        ByteBuffer memAlloc = MemoryUtil.memAlloc(i2);
        try {
            IntBuffer ints = stackGet.ints(0);
            nglGetInfoLogARB(i, i2, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(memAlloc));
            return MemoryUtil.memUTF8(memAlloc, ints.get(0));
        } finally {
            MemoryUtil.memFree(memAlloc);
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetInfoLogARB(@NativeType("GLhandleARB") int i) {
        return glGetInfoLogARB(i, glGetObjectParameteriARB(i, 35716));
    }

    public static void glGetAttachedObjectsARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLhandleARB *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetAttachedObjectsARB(i, intBuffer2.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    @NativeType("GLint")
    public static int glGetUniformLocationARB(@NativeType("GLhandleARB") int i, @NativeType("GLcharARB const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetUniformLocationARB(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetUniformLocationARB(@NativeType("GLhandleARB") int i, @NativeType("GLcharARB const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nglGetUniformLocationARB(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveUniformARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLcharARB *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        nglGetActiveUniformARB(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveUniformARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetActiveUniformARB(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memUTF8(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveUniformARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetActiveUniformARB(i, i2, glGetObjectParameteriARB(i, 35719), intBuffer, intBuffer2);
    }

    public static void glGetUniformfvARB(@NativeType("GLhandleARB") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetUniformfvARB(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetUniformfARB(@NativeType("GLhandleARB") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetUniformfvARB(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetUniformivARB(@NativeType("GLhandleARB") int i, @NativeType("GLint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetUniformivARB(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetUniformiARB(@NativeType("GLhandleARB") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetUniformivARB(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetShaderSourceARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLcharARB *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetShaderSourceARB(i, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetShaderSourceARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        ByteBuffer memAlloc = MemoryUtil.memAlloc(i2);
        try {
            IntBuffer ints = stackGet.ints(0);
            nglGetShaderSourceARB(i, i2, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(memAlloc));
            return MemoryUtil.memUTF8(memAlloc, ints.get(0));
        } finally {
            MemoryUtil.memFree(memAlloc);
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetShaderSourceARB(@NativeType("GLhandleARB") int i) {
        return glGetShaderSourceARB(i, glGetObjectParameteriARB(i, 35720));
    }

    public static void glShaderSourceARB(@NativeType("GLhandleARB") int i, @NativeType("GLcharARB const **") PointerBuffer pointerBuffer, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glShaderSourceARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, pointerBuffer.remaining());
        }
        JNI.callPPV(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), iArr, j);
    }

    public static void glUniform1fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform1fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length, fArr, j);
    }

    public static void glUniform2fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform2fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 1, fArr, j);
    }

    public static void glUniform3fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform3fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 3, fArr, j);
    }

    public static void glUniform4fvARB(@NativeType("GLint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniform4fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 2, fArr, j);
    }

    public static void glUniform1ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform1ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glUniform2ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform2ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 1, iArr, j);
    }

    public static void glUniform3ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform3ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length / 3, iArr, j);
    }

    public static void glUniform4ivARB(@NativeType("GLint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glUniform4ivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 2, iArr, j);
    }

    public static void glUniformMatrix2fvARB(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix2fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 2, z, fArr, j);
    }

    public static void glUniformMatrix3fvARB(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix3fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length / 9, z, fArr, j);
    }

    public static void glUniformMatrix4fvARB(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glUniformMatrix4fvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length >> 4, z, fArr, j);
    }

    public static void glGetObjectParameterfvARB(@NativeType("GLhandleARB") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetObjectParameterfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetObjectParameterivARB(@NativeType("GLhandleARB") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetObjectParameterivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetInfoLogARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLcharARB *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetInfoLogARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetAttachedObjectsARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLhandleARB *") int[] iArr2) {
        long j = GL.getICD().glGetAttachedObjectsARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, iArr2.length, iArr, iArr2, j);
    }

    public static void glGetActiveUniformARB(@NativeType("GLhandleARB") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLcharARB *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveUniformARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        JNI.callPPPPV(i, i2, byteBuffer.remaining(), iArr, iArr2, iArr3, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetUniformfvARB(@NativeType("GLhandleARB") int i, @NativeType("GLint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetUniformfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetUniformivARB(@NativeType("GLhandleARB") int i, @NativeType("GLint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetUniformivARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetShaderSourceARB(@NativeType("GLhandleARB") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLcharARB *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetShaderSourceARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }
}
