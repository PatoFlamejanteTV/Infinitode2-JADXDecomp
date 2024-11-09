package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL32C.class */
public class GL32C extends GL31C {
    public static final int GL_CONTEXT_PROFILE_MASK = 37158;
    public static final int GL_CONTEXT_CORE_PROFILE_BIT = 1;
    public static final int GL_CONTEXT_COMPATIBILITY_PROFILE_BIT = 2;
    public static final int GL_MAX_VERTEX_OUTPUT_COMPONENTS = 37154;
    public static final int GL_MAX_GEOMETRY_INPUT_COMPONENTS = 37155;
    public static final int GL_MAX_GEOMETRY_OUTPUT_COMPONENTS = 37156;
    public static final int GL_MAX_FRAGMENT_INPUT_COMPONENTS = 37157;
    public static final int GL_FIRST_VERTEX_CONVENTION = 36429;
    public static final int GL_LAST_VERTEX_CONVENTION = 36430;
    public static final int GL_PROVOKING_VERTEX = 36431;
    public static final int GL_QUADS_FOLLOW_PROVOKING_VERTEX_CONVENTION = 36428;
    public static final int GL_TEXTURE_CUBE_MAP_SEAMLESS = 34895;
    public static final int GL_SAMPLE_POSITION = 36432;
    public static final int GL_SAMPLE_MASK = 36433;
    public static final int GL_SAMPLE_MASK_VALUE = 36434;
    public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
    public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE = 37121;
    public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
    public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 37123;
    public static final int GL_MAX_SAMPLE_MASK_WORDS = 36441;
    public static final int GL_MAX_COLOR_TEXTURE_SAMPLES = 37134;
    public static final int GL_MAX_DEPTH_TEXTURE_SAMPLES = 37135;
    public static final int GL_MAX_INTEGER_SAMPLES = 37136;
    public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE = 37124;
    public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 37125;
    public static final int GL_TEXTURE_SAMPLES = 37126;
    public static final int GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 37127;
    public static final int GL_SAMPLER_2D_MULTISAMPLE = 37128;
    public static final int GL_INT_SAMPLER_2D_MULTISAMPLE = 37129;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 37130;
    public static final int GL_SAMPLER_2D_MULTISAMPLE_ARRAY = 37131;
    public static final int GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37132;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37133;
    public static final int GL_DEPTH_CLAMP = 34383;
    public static final int GL_GEOMETRY_SHADER = 36313;
    public static final int GL_GEOMETRY_VERTICES_OUT = 36314;
    public static final int GL_GEOMETRY_INPUT_TYPE = 36315;
    public static final int GL_GEOMETRY_OUTPUT_TYPE = 36316;
    public static final int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS = 35881;
    public static final int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS = 36319;
    public static final int GL_MAX_GEOMETRY_OUTPUT_VERTICES = 36320;
    public static final int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS = 36321;
    public static final int GL_LINES_ADJACENCY = 10;
    public static final int GL_LINE_STRIP_ADJACENCY = 11;
    public static final int GL_TRIANGLES_ADJACENCY = 12;
    public static final int GL_TRIANGLE_STRIP_ADJACENCY = 13;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS = 36264;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED = 36263;
    public static final int GL_PROGRAM_POINT_SIZE = 34370;
    public static final int GL_MAX_SERVER_WAIT_TIMEOUT = 37137;
    public static final int GL_OBJECT_TYPE = 37138;
    public static final int GL_SYNC_CONDITION = 37139;
    public static final int GL_SYNC_STATUS = 37140;
    public static final int GL_SYNC_FLAGS = 37141;
    public static final int GL_SYNC_FENCE = 37142;
    public static final int GL_SYNC_GPU_COMMANDS_COMPLETE = 37143;
    public static final int GL_UNSIGNALED = 37144;
    public static final int GL_SIGNALED = 37145;
    public static final int GL_SYNC_FLUSH_COMMANDS_BIT = 1;
    public static final long GL_TIMEOUT_IGNORED = -1;
    public static final int GL_ALREADY_SIGNALED = 37146;
    public static final int GL_TIMEOUT_EXPIRED = 37147;
    public static final int GL_CONDITION_SATISFIED = 37148;
    public static final int GL_WAIT_FAILED = 37149;

    public static native void nglGetBufferParameteri64v(int i, int i2, long j);

    public static native void nglDrawElementsBaseVertex(int i, int i2, int i3, long j, int i4);

    public static native void nglDrawRangeElementsBaseVertex(int i, int i2, int i3, int i4, int i5, long j, int i6);

    public static native void nglDrawElementsInstancedBaseVertex(int i, int i2, int i3, long j, int i4, int i5);

    public static native void nglMultiDrawElementsBaseVertex(int i, long j, int i2, long j2, int i3, long j3);

    public static native void glProvokingVertex(@NativeType("GLenum") int i);

    public static native void glTexImage2DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z);

    public static native void glTexImage3DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z);

    public static native void nglGetMultisamplefv(int i, int i2, long j);

    public static native void glSampleMaski(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2);

    public static native void glFramebufferTexture(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4);

    @NativeType("GLsync")
    public static native long glFenceSync(@NativeType("GLenum") int i, @NativeType("GLbitfield") int i2);

    public static native boolean nglIsSync(long j);

    public static native void nglDeleteSync(long j);

    public static native int nglClientWaitSync(long j, int i, long j2);

    public static native void nglWaitSync(long j, int i, long j2);

    public static native void nglGetInteger64v(int i, long j);

    public static native void nglGetInteger64i_v(int i, int i2, long j);

    public static native void nglGetSynciv(long j, int i, int i2, long j2, long j3);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL32C() {
        throw new UnsupportedOperationException();
    }

    public static void glGetBufferParameteri64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetBufferParameteri64v(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetBufferParameteri64(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetBufferParameteri64v(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLint") int i4) {
        nglDrawElementsBaseVertex(i, i2, i3, j, i4);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i3) {
        nglDrawElementsBaseVertex(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer), i3);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i2) {
        nglDrawElementsBaseVertex(i, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer), i2);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLint") int i2) {
        nglDrawElementsBaseVertex(i, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer), i2);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLint") int i2) {
        nglDrawElementsBaseVertex(i, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer), i2);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") long j, @NativeType("GLint") int i6) {
        nglDrawRangeElementsBaseVertex(i, i2, i3, i4, i5, j, i6);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i5) {
        nglDrawRangeElementsBaseVertex(i, i2, i3, byteBuffer.remaining() >> GLChecks.typeToByteShift(i4), i4, MemoryUtil.memAddress(byteBuffer), i5);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i4) {
        nglDrawRangeElementsBaseVertex(i, i2, i3, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer), i4);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLint") int i4) {
        nglDrawRangeElementsBaseVertex(i, i2, i3, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer), i4);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLint") int i4) {
        nglDrawRangeElementsBaseVertex(i, i2, i3, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer), i4);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5) {
        nglDrawElementsInstancedBaseVertex(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4) {
        nglDrawElementsInstancedBaseVertex(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer), i3, i4);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        nglDrawElementsInstancedBaseVertex(i, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer), i2, i3);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        nglDrawElementsInstancedBaseVertex(i, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer), i2, i3);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        nglDrawElementsInstancedBaseVertex(i, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer), i2, i3);
    }

    public static void glMultiDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei const *") IntBuffer intBuffer, @NativeType("GLenum") int i2, @NativeType("void const * const *") PointerBuffer pointerBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, intBuffer.remaining());
            Checks.check((Buffer) intBuffer2, intBuffer.remaining());
        }
        nglMultiDrawElementsBaseVertex(i, MemoryUtil.memAddress(intBuffer), i2, MemoryUtil.memAddress(pointerBuffer), intBuffer.remaining(), MemoryUtil.memAddress(intBuffer2));
    }

    public static void glGetMultisamplefv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetMultisamplefv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetMultisamplef(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetMultisamplefv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLboolean")
    public static boolean glIsSync(@NativeType("GLsync") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nglIsSync(j);
    }

    public static void glDeleteSync(@NativeType("GLsync") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nglDeleteSync(j);
    }

    @NativeType("GLenum")
    public static int glClientWaitSync(@NativeType("GLsync") long j, @NativeType("GLbitfield") int i, @NativeType("GLuint64") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nglClientWaitSync(j, i, j2);
    }

    public static void glWaitSync(@NativeType("GLsync") long j, @NativeType("GLbitfield") int i, @NativeType("GLuint64") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nglWaitSync(j, i, j2);
    }

    public static void glGetInteger64v(@NativeType("GLenum") int i, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetInteger64v(i, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetInteger64(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetInteger64v(i, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetInteger64i_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nglGetInteger64i_v(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetInteger64i(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetInteger64i_v(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetSynciv(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetSynciv(j, i, intBuffer2.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    @NativeType("void")
    public static int glGetSynci(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetSynciv(j, i, 1, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetBufferParameteri64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetBufferParameteri64v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glMultiDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei const *") int[] iArr, @NativeType("GLenum") int i2, @NativeType("void const * const *") PointerBuffer pointerBuffer, @NativeType("GLint *") int[] iArr2) {
        long j = GL.getICD().glMultiDrawElementsBaseVertex;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check((CustomBuffer<?>) pointerBuffer, iArr.length);
            Checks.check(iArr2, iArr.length);
        }
        JNI.callPPPV(i, iArr, i2, MemoryUtil.memAddress(pointerBuffer), iArr.length, iArr2, j);
    }

    public static void glGetMultisamplefv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMultisamplefv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetInteger64v(@NativeType("GLenum") int i, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetInteger64v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, jArr, j);
    }

    public static void glGetInteger64i_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetInteger64i_v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.callPV(i, i2, jArr, j);
    }

    public static void glGetSynciv(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        long j2 = GL.getICD().glGetSynciv;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPPV(j, i, iArr2.length, iArr, iArr2, j2);
    }
}
