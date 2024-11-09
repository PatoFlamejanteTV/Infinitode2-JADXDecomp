package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL31C.class */
public class GL31C extends GL30C {
    public static final int GL_R8_SNORM = 36756;
    public static final int GL_RG8_SNORM = 36757;
    public static final int GL_RGB8_SNORM = 36758;
    public static final int GL_RGBA8_SNORM = 36759;
    public static final int GL_R16_SNORM = 36760;
    public static final int GL_RG16_SNORM = 36761;
    public static final int GL_RGB16_SNORM = 36762;
    public static final int GL_RGBA16_SNORM = 36763;
    public static final int GL_SIGNED_NORMALIZED = 36764;
    public static final int GL_SAMPLER_BUFFER = 36290;
    public static final int GL_INT_SAMPLER_2D_RECT = 36301;
    public static final int GL_INT_SAMPLER_BUFFER = 36304;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_RECT = 36309;
    public static final int GL_UNSIGNED_INT_SAMPLER_BUFFER = 36312;
    public static final int GL_COPY_READ_BUFFER = 36662;
    public static final int GL_COPY_WRITE_BUFFER = 36663;
    public static final int GL_PRIMITIVE_RESTART = 36765;
    public static final int GL_PRIMITIVE_RESTART_INDEX = 36766;
    public static final int GL_TEXTURE_BUFFER = 35882;
    public static final int GL_MAX_TEXTURE_BUFFER_SIZE = 35883;
    public static final int GL_TEXTURE_BINDING_BUFFER = 35884;
    public static final int GL_TEXTURE_BUFFER_DATA_STORE_BINDING = 35885;
    public static final int GL_TEXTURE_RECTANGLE = 34037;
    public static final int GL_TEXTURE_BINDING_RECTANGLE = 34038;
    public static final int GL_PROXY_TEXTURE_RECTANGLE = 34039;
    public static final int GL_MAX_RECTANGLE_TEXTURE_SIZE = 34040;
    public static final int GL_SAMPLER_2D_RECT = 35683;
    public static final int GL_SAMPLER_2D_RECT_SHADOW = 35684;
    public static final int GL_UNIFORM_BUFFER = 35345;
    public static final int GL_UNIFORM_BUFFER_BINDING = 35368;
    public static final int GL_UNIFORM_BUFFER_START = 35369;
    public static final int GL_UNIFORM_BUFFER_SIZE = 35370;
    public static final int GL_MAX_VERTEX_UNIFORM_BLOCKS = 35371;
    public static final int GL_MAX_GEOMETRY_UNIFORM_BLOCKS = 35372;
    public static final int GL_MAX_FRAGMENT_UNIFORM_BLOCKS = 35373;
    public static final int GL_MAX_COMBINED_UNIFORM_BLOCKS = 35374;
    public static final int GL_MAX_UNIFORM_BUFFER_BINDINGS = 35375;
    public static final int GL_MAX_UNIFORM_BLOCK_SIZE = 35376;
    public static final int GL_MAX_COMBINED_VERTEX_UNIFORM_COMPONENTS = 35377;
    public static final int GL_MAX_COMBINED_GEOMETRY_UNIFORM_COMPONENTS = 35378;
    public static final int GL_MAX_COMBINED_FRAGMENT_UNIFORM_COMPONENTS = 35379;
    public static final int GL_UNIFORM_BUFFER_OFFSET_ALIGNMENT = 35380;
    public static final int GL_ACTIVE_UNIFORM_BLOCK_MAX_NAME_LENGTH = 35381;
    public static final int GL_ACTIVE_UNIFORM_BLOCKS = 35382;
    public static final int GL_UNIFORM_TYPE = 35383;
    public static final int GL_UNIFORM_SIZE = 35384;
    public static final int GL_UNIFORM_NAME_LENGTH = 35385;
    public static final int GL_UNIFORM_BLOCK_INDEX = 35386;
    public static final int GL_UNIFORM_OFFSET = 35387;
    public static final int GL_UNIFORM_ARRAY_STRIDE = 35388;
    public static final int GL_UNIFORM_MATRIX_STRIDE = 35389;
    public static final int GL_UNIFORM_IS_ROW_MAJOR = 35390;
    public static final int GL_UNIFORM_BLOCK_BINDING = 35391;
    public static final int GL_UNIFORM_BLOCK_DATA_SIZE = 35392;
    public static final int GL_UNIFORM_BLOCK_NAME_LENGTH = 35393;
    public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORMS = 35394;
    public static final int GL_UNIFORM_BLOCK_ACTIVE_UNIFORM_INDICES = 35395;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_VERTEX_SHADER = 35396;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_GEOMETRY_SHADER = 35397;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_FRAGMENT_SHADER = 35398;
    public static final int GL_INVALID_INDEX = -1;

    public static native void glDrawArraysInstanced(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    public static native void nglDrawElementsInstanced(int i, int i2, int i3, long j, int i4);

    public static native void glCopyBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLintptr") long j2, @NativeType("GLsizeiptr") long j3);

    public static native void glPrimitiveRestartIndex(@NativeType("GLuint") int i);

    public static native void glTexBuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void nglGetUniformIndices(int i, int i2, long j, long j2);

    public static native void nglGetActiveUniformsiv(int i, int i2, long j, int i3, long j2);

    public static native void nglGetActiveUniformName(int i, int i2, int i3, long j, long j2);

    public static native int nglGetUniformBlockIndex(int i, long j);

    public static native void nglGetActiveUniformBlockiv(int i, int i2, int i3, long j);

    public static native void nglGetActiveUniformBlockName(int i, int i2, int i3, long j, long j2);

    public static native void glUniformBlockBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL31C() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4) {
        nglDrawElementsInstanced(i, i2, i3, j, i4);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3) {
        nglDrawElementsInstanced(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer), i3);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2) {
        nglDrawElementsInstanced(i, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2) {
        nglDrawElementsInstanced(i, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glDrawElementsInstanced(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2) {
        nglDrawElementsInstanced(i, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, pointerBuffer.remaining());
        }
        nglGetUniformIndices(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence[] charSequenceArr, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, charSequenceArr.length);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memASCII, charSequenceArr);
            nglGetUniformIndices(i, charSequenceArr.length, apiArray, MemoryUtil.memAddress(intBuffer));
            APIUtil.apiArrayFree(apiArray, charSequenceArr.length);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static int glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memASCII, charSequence);
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetUniformIndices(i, 1, apiArray, MemoryUtil.memAddress(callocInt));
            APIUtil.apiArrayFree(apiArray, 1);
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveUniformsiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer2, intBuffer.remaining());
        }
        nglGetActiveUniformsiv(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), i2, MemoryUtil.memAddress(intBuffer2));
    }

    @NativeType("void")
    public static int glGetActiveUniformsi(@NativeType("GLuint") int i, @NativeType("GLuint const *") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetActiveUniformsiv(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)), i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetActiveUniformName(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetActiveUniformName(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        return glGetActiveUniformName(i, i2, glGetActiveUniformsi(i, i2, 35385));
    }

    @NativeType("GLuint")
    public static int glGetUniformBlockIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetUniformBlockIndex(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLuint")
    public static int glGetUniformBlockIndex(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetUniformBlockIndex(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveUniformBlockiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetActiveUniformBlockiv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetActiveUniformBlocki(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetActiveUniformBlockiv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetActiveUniformBlockName(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetActiveUniformBlockName(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        return glGetActiveUniformBlockName(i, i2, glGetActiveUniformBlocki(i, i2, 35393));
    }

    public static void glGetUniformIndices(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetUniformIndices;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, pointerBuffer.remaining());
        }
        JNI.callPPV(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), iArr, j);
    }

    public static void glGetActiveUniformsiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr2) {
        long j = GL.getICD().glGetActiveUniformsiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr2, iArr.length);
        }
        JNI.callPPV(i, iArr.length, iArr, i2, iArr2, j);
    }

    public static void glGetActiveUniformName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveUniformName;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, i2, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetActiveUniformBlockiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetActiveUniformBlockiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetActiveUniformBlockName(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetActiveUniformBlockName;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, i2, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }
}
