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

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL42C.class */
public class GL42C extends GL41C {
    public static final int GL_COPY_READ_BUFFER_BINDING = 36662;
    public static final int GL_COPY_WRITE_BUFFER_BINDING = 36663;
    public static final int GL_TRANSFORM_FEEDBACK_ACTIVE = 36388;
    public static final int GL_TRANSFORM_FEEDBACK_PAUSED = 36387;
    public static final int GL_COMPRESSED_RGBA_BPTC_UNORM = 36492;
    public static final int GL_COMPRESSED_SRGB_ALPHA_BPTC_UNORM = 36493;
    public static final int GL_COMPRESSED_RGB_BPTC_SIGNED_FLOAT = 36494;
    public static final int GL_COMPRESSED_RGB_BPTC_UNSIGNED_FLOAT = 36495;
    public static final int GL_UNPACK_COMPRESSED_BLOCK_WIDTH = 37159;
    public static final int GL_UNPACK_COMPRESSED_BLOCK_HEIGHT = 37160;
    public static final int GL_UNPACK_COMPRESSED_BLOCK_DEPTH = 37161;
    public static final int GL_UNPACK_COMPRESSED_BLOCK_SIZE = 37162;
    public static final int GL_PACK_COMPRESSED_BLOCK_WIDTH = 37163;
    public static final int GL_PACK_COMPRESSED_BLOCK_HEIGHT = 37164;
    public static final int GL_PACK_COMPRESSED_BLOCK_DEPTH = 37165;
    public static final int GL_PACK_COMPRESSED_BLOCK_SIZE = 37166;
    public static final int GL_ATOMIC_COUNTER_BUFFER = 37568;
    public static final int GL_ATOMIC_COUNTER_BUFFER_BINDING = 37569;
    public static final int GL_ATOMIC_COUNTER_BUFFER_START = 37570;
    public static final int GL_ATOMIC_COUNTER_BUFFER_SIZE = 37571;
    public static final int GL_ATOMIC_COUNTER_BUFFER_DATA_SIZE = 37572;
    public static final int GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTERS = 37573;
    public static final int GL_ATOMIC_COUNTER_BUFFER_ACTIVE_ATOMIC_COUNTER_INDICES = 37574;
    public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_VERTEX_SHADER = 37575;
    public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_CONTROL_SHADER = 37576;
    public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_TESS_EVALUATION_SHADER = 37577;
    public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_GEOMETRY_SHADER = 37578;
    public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_FRAGMENT_SHADER = 37579;
    public static final int GL_MAX_VERTEX_ATOMIC_COUNTER_BUFFERS = 37580;
    public static final int GL_MAX_TESS_CONTROL_ATOMIC_COUNTER_BUFFERS = 37581;
    public static final int GL_MAX_TESS_EVALUATION_ATOMIC_COUNTER_BUFFERS = 37582;
    public static final int GL_MAX_GEOMETRY_ATOMIC_COUNTER_BUFFERS = 37583;
    public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTER_BUFFERS = 37584;
    public static final int GL_MAX_COMBINED_ATOMIC_COUNTER_BUFFERS = 37585;
    public static final int GL_MAX_VERTEX_ATOMIC_COUNTERS = 37586;
    public static final int GL_MAX_TESS_CONTROL_ATOMIC_COUNTERS = 37587;
    public static final int GL_MAX_TESS_EVALUATION_ATOMIC_COUNTERS = 37588;
    public static final int GL_MAX_GEOMETRY_ATOMIC_COUNTERS = 37589;
    public static final int GL_MAX_FRAGMENT_ATOMIC_COUNTERS = 37590;
    public static final int GL_MAX_COMBINED_ATOMIC_COUNTERS = 37591;
    public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_SIZE = 37592;
    public static final int GL_MAX_ATOMIC_COUNTER_BUFFER_BINDINGS = 37596;
    public static final int GL_ACTIVE_ATOMIC_COUNTER_BUFFERS = 37593;
    public static final int GL_UNIFORM_ATOMIC_COUNTER_BUFFER_INDEX = 37594;
    public static final int GL_UNSIGNED_INT_ATOMIC_COUNTER = 37595;
    public static final int GL_TEXTURE_IMMUTABLE_FORMAT = 37167;
    public static final int GL_MAX_IMAGE_UNITS = 36664;
    public static final int GL_MAX_COMBINED_IMAGE_UNITS_AND_FRAGMENT_OUTPUTS = 36665;
    public static final int GL_MAX_IMAGE_SAMPLES = 36973;
    public static final int GL_MAX_VERTEX_IMAGE_UNIFORMS = 37066;
    public static final int GL_MAX_TESS_CONTROL_IMAGE_UNIFORMS = 37067;
    public static final int GL_MAX_TESS_EVALUATION_IMAGE_UNIFORMS = 37068;
    public static final int GL_MAX_GEOMETRY_IMAGE_UNIFORMS = 37069;
    public static final int GL_MAX_FRAGMENT_IMAGE_UNIFORMS = 37070;
    public static final int GL_MAX_COMBINED_IMAGE_UNIFORMS = 37071;
    public static final int GL_IMAGE_BINDING_NAME = 36666;
    public static final int GL_IMAGE_BINDING_LEVEL = 36667;
    public static final int GL_IMAGE_BINDING_LAYERED = 36668;
    public static final int GL_IMAGE_BINDING_LAYER = 36669;
    public static final int GL_IMAGE_BINDING_ACCESS = 36670;
    public static final int GL_IMAGE_BINDING_FORMAT = 36974;
    public static final int GL_VERTEX_ATTRIB_ARRAY_BARRIER_BIT = 1;
    public static final int GL_ELEMENT_ARRAY_BARRIER_BIT = 2;
    public static final int GL_UNIFORM_BARRIER_BIT = 4;
    public static final int GL_TEXTURE_FETCH_BARRIER_BIT = 8;
    public static final int GL_SHADER_IMAGE_ACCESS_BARRIER_BIT = 32;
    public static final int GL_COMMAND_BARRIER_BIT = 64;
    public static final int GL_PIXEL_BUFFER_BARRIER_BIT = 128;
    public static final int GL_TEXTURE_UPDATE_BARRIER_BIT = 256;
    public static final int GL_BUFFER_UPDATE_BARRIER_BIT = 512;
    public static final int GL_FRAMEBUFFER_BARRIER_BIT = 1024;
    public static final int GL_TRANSFORM_FEEDBACK_BARRIER_BIT = 2048;
    public static final int GL_ATOMIC_COUNTER_BARRIER_BIT = 4096;
    public static final int GL_ALL_BARRIER_BITS = -1;
    public static final int GL_IMAGE_1D = 36940;
    public static final int GL_IMAGE_2D = 36941;
    public static final int GL_IMAGE_3D = 36942;
    public static final int GL_IMAGE_2D_RECT = 36943;
    public static final int GL_IMAGE_CUBE = 36944;
    public static final int GL_IMAGE_BUFFER = 36945;
    public static final int GL_IMAGE_1D_ARRAY = 36946;
    public static final int GL_IMAGE_2D_ARRAY = 36947;
    public static final int GL_IMAGE_CUBE_MAP_ARRAY = 36948;
    public static final int GL_IMAGE_2D_MULTISAMPLE = 36949;
    public static final int GL_IMAGE_2D_MULTISAMPLE_ARRAY = 36950;
    public static final int GL_INT_IMAGE_1D = 36951;
    public static final int GL_INT_IMAGE_2D = 36952;
    public static final int GL_INT_IMAGE_3D = 36953;
    public static final int GL_INT_IMAGE_2D_RECT = 36954;
    public static final int GL_INT_IMAGE_CUBE = 36955;
    public static final int GL_INT_IMAGE_BUFFER = 36956;
    public static final int GL_INT_IMAGE_1D_ARRAY = 36957;
    public static final int GL_INT_IMAGE_2D_ARRAY = 36958;
    public static final int GL_INT_IMAGE_CUBE_MAP_ARRAY = 36959;
    public static final int GL_INT_IMAGE_2D_MULTISAMPLE = 36960;
    public static final int GL_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 36961;
    public static final int GL_UNSIGNED_INT_IMAGE_1D = 36962;
    public static final int GL_UNSIGNED_INT_IMAGE_2D = 36963;
    public static final int GL_UNSIGNED_INT_IMAGE_3D = 36964;
    public static final int GL_UNSIGNED_INT_IMAGE_2D_RECT = 36965;
    public static final int GL_UNSIGNED_INT_IMAGE_CUBE = 36966;
    public static final int GL_UNSIGNED_INT_IMAGE_BUFFER = 36967;
    public static final int GL_UNSIGNED_INT_IMAGE_1D_ARRAY = 36968;
    public static final int GL_UNSIGNED_INT_IMAGE_2D_ARRAY = 36969;
    public static final int GL_UNSIGNED_INT_IMAGE_CUBE_MAP_ARRAY = 36970;
    public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE = 36971;
    public static final int GL_UNSIGNED_INT_IMAGE_2D_MULTISAMPLE_ARRAY = 36972;
    public static final int GL_IMAGE_FORMAT_COMPATIBILITY_TYPE = 37063;
    public static final int GL_IMAGE_FORMAT_COMPATIBILITY_BY_SIZE = 37064;
    public static final int GL_IMAGE_FORMAT_COMPATIBILITY_BY_CLASS = 37065;
    public static final int GL_NUM_SAMPLE_COUNTS = 37760;
    public static final int GL_MIN_MAP_BUFFER_ALIGNMENT = 37052;

    public static native void nglGetActiveAtomicCounterBufferiv(int i, int i2, int i3, long j);

    public static native void glTexStorage1D(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4);

    public static native void glTexStorage2D(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5);

    public static native void glTexStorage3D(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6);

    public static native void glDrawTransformFeedbackInstanced(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3);

    public static native void glDrawTransformFeedbackStreamInstanced(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4);

    public static native void glDrawArraysInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLuint") int i5);

    public static native void nglDrawElementsInstancedBaseInstance(int i, int i2, int i3, long j, int i4, int i5);

    public static native void nglDrawElementsInstancedBaseVertexBaseInstance(int i, int i2, int i3, long j, int i4, int i5, int i6);

    public static native void glBindImageTexture(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLint") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6);

    public static native void glMemoryBarrier(@NativeType("GLbitfield") int i);

    public static native void nglGetInternalformativ(int i, int i2, int i3, int i4, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL42C() {
        throw new UnsupportedOperationException();
    }

    public static void glGetActiveAtomicCounterBufferiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetActiveAtomicCounterBufferiv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetActiveAtomicCounterBufferi(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetActiveAtomicCounterBufferiv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4, @NativeType("GLuint") int i5) {
        nglDrawElementsInstancedBaseInstance(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLuint") int i4) {
        nglDrawElementsInstancedBaseInstance(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer), i3, i4);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLuint") int i3) {
        nglDrawElementsInstancedBaseInstance(i, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer), i2, i3);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2, @NativeType("GLuint") int i3) {
        nglDrawElementsInstancedBaseInstance(i, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer), i2, i3);
    }

    public static void glDrawElementsInstancedBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLuint") int i3) {
        nglDrawElementsInstancedBaseInstance(i, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer), i2, i3);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLuint") int i6) {
        nglDrawElementsInstancedBaseVertexBaseInstance(i, i2, i3, j, i4, i5, i6);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4, @NativeType("GLuint") int i5) {
        nglDrawElementsInstancedBaseVertexBaseInstance(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer), i3, i4, i5);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4) {
        nglDrawElementsInstancedBaseVertexBaseInstance(i, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer), i2, i3, i4);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4) {
        nglDrawElementsInstancedBaseVertexBaseInstance(i, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer), i2, i3, i4);
    }

    public static void glDrawElementsInstancedBaseVertexBaseInstance(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4) {
        nglDrawElementsInstancedBaseVertexBaseInstance(i, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer), i2, i3, i4);
    }

    public static void glGetInternalformativ(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        nglGetInternalformativ(i, i2, i3, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetInternalformati(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetInternalformativ(i, i2, i3, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetActiveAtomicCounterBufferiv(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetActiveAtomicCounterBufferiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetInternalformativ(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetInternalformativ;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr.length, iArr, j);
    }
}
