package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL32.class */
public class GL32 extends GL31 {
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

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL32() {
        throw new UnsupportedOperationException();
    }

    public static void nglGetBufferParameteri64v(int i, int i2, long j) {
        GL32C.nglGetBufferParameteri64v(i, i2, j);
    }

    public static void glGetBufferParameteri64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL32C.glGetBufferParameteri64v(i, i2, longBuffer);
    }

    @NativeType("void")
    public static long glGetBufferParameteri64(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL32C.glGetBufferParameteri64(i, i2);
    }

    public static void nglDrawElementsBaseVertex(int i, int i2, int i3, long j, int i4) {
        GL32C.nglDrawElementsBaseVertex(i, i2, i3, j, i4);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLint") int i4) {
        GL32C.glDrawElementsBaseVertex(i, i2, i3, j, i4);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsBaseVertex(i, i2, byteBuffer, i3);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i2) {
        GL32C.glDrawElementsBaseVertex(i, byteBuffer, i2);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLint") int i2) {
        GL32C.glDrawElementsBaseVertex(i, shortBuffer, i2);
    }

    public static void glDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLint") int i2) {
        GL32C.glDrawElementsBaseVertex(i, intBuffer, i2);
    }

    public static void nglDrawRangeElementsBaseVertex(int i, int i2, int i3, int i4, int i5, long j, int i6) {
        GL32C.nglDrawRangeElementsBaseVertex(i, i2, i3, i4, i5, j, i6);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") long j, @NativeType("GLint") int i6) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, i4, i5, j, i6);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i5) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, i4, byteBuffer, i5);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLint") int i4) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, byteBuffer, i4);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLint") int i4) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, shortBuffer, i4);
    }

    public static void glDrawRangeElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLint") int i4) {
        GL32C.glDrawRangeElementsBaseVertex(i, i2, i3, intBuffer, i4);
    }

    public static void nglDrawElementsInstancedBaseVertex(int i, int i2, int i3, long j, int i4, int i5) {
        GL32C.nglDrawElementsInstancedBaseVertex(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5) {
        GL32C.glDrawElementsInstancedBaseVertex(i, i2, i3, j, i4, i5);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLint") int i4) {
        GL32C.glDrawElementsInstancedBaseVertex(i, i2, byteBuffer, i3, i4);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsInstancedBaseVertex(i, byteBuffer, i2, i3);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsInstancedBaseVertex(i, shortBuffer, i2, i3);
    }

    public static void glDrawElementsInstancedBaseVertex(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3) {
        GL32C.glDrawElementsInstancedBaseVertex(i, intBuffer, i2, i3);
    }

    public static void nglMultiDrawElementsBaseVertex(int i, long j, int i2, long j2, int i3, long j3) {
        GL32C.nglMultiDrawElementsBaseVertex(i, j, i2, j2, i3, j3);
    }

    public static void glMultiDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei const *") IntBuffer intBuffer, @NativeType("GLenum") int i2, @NativeType("void const * const *") PointerBuffer pointerBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        GL32C.glMultiDrawElementsBaseVertex(i, intBuffer, i2, pointerBuffer, intBuffer2);
    }

    public static void glProvokingVertex(@NativeType("GLenum") int i) {
        GL32C.glProvokingVertex(i);
    }

    public static void glTexImage2DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z) {
        GL32C.glTexImage2DMultisample(i, i2, i3, i4, i5, z);
    }

    public static void glTexImage3DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z) {
        GL32C.glTexImage3DMultisample(i, i2, i3, i4, i5, i6, z);
    }

    public static void nglGetMultisamplefv(int i, int i2, long j) {
        GL32C.nglGetMultisamplefv(i, i2, j);
    }

    public static void glGetMultisamplefv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL32C.glGetMultisamplefv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetMultisamplef(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL32C.glGetMultisamplef(i, i2);
    }

    public static void glSampleMaski(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2) {
        GL32C.glSampleMaski(i, i2);
    }

    public static void glFramebufferTexture(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4) {
        GL32C.glFramebufferTexture(i, i2, i3, i4);
    }

    @NativeType("GLsync")
    public static long glFenceSync(@NativeType("GLenum") int i, @NativeType("GLbitfield") int i2) {
        return GL32C.glFenceSync(i, i2);
    }

    public static boolean nglIsSync(long j) {
        return GL32C.nglIsSync(j);
    }

    @NativeType("GLboolean")
    public static boolean glIsSync(@NativeType("GLsync") long j) {
        return GL32C.glIsSync(j);
    }

    public static void nglDeleteSync(long j) {
        GL32C.nglDeleteSync(j);
    }

    public static void glDeleteSync(@NativeType("GLsync") long j) {
        GL32C.glDeleteSync(j);
    }

    public static int nglClientWaitSync(long j, int i, long j2) {
        return GL32C.nglClientWaitSync(j, i, j2);
    }

    @NativeType("GLenum")
    public static int glClientWaitSync(@NativeType("GLsync") long j, @NativeType("GLbitfield") int i, @NativeType("GLuint64") long j2) {
        return GL32C.glClientWaitSync(j, i, j2);
    }

    public static void nglWaitSync(long j, int i, long j2) {
        GL32C.nglWaitSync(j, i, j2);
    }

    public static void glWaitSync(@NativeType("GLsync") long j, @NativeType("GLbitfield") int i, @NativeType("GLuint64") long j2) {
        GL32C.glWaitSync(j, i, j2);
    }

    public static void nglGetInteger64v(int i, long j) {
        GL32C.nglGetInteger64v(i, j);
    }

    public static void glGetInteger64v(@NativeType("GLenum") int i, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL32C.glGetInteger64v(i, longBuffer);
    }

    @NativeType("void")
    public static long glGetInteger64(@NativeType("GLenum") int i) {
        return GL32C.glGetInteger64(i);
    }

    public static void nglGetInteger64i_v(int i, int i2, long j) {
        GL32C.nglGetInteger64i_v(i, i2, j);
    }

    public static void glGetInteger64i_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL32C.glGetInteger64i_v(i, i2, longBuffer);
    }

    @NativeType("void")
    public static long glGetInteger64i(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL32C.glGetInteger64i(i, i2);
    }

    public static void nglGetSynciv(long j, int i, int i2, long j2, long j3) {
        GL32C.nglGetSynciv(j, i, i2, j2, j3);
    }

    public static void glGetSynciv(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLint *") IntBuffer intBuffer2) {
        GL32C.glGetSynciv(j, i, intBuffer, intBuffer2);
    }

    @NativeType("void")
    public static int glGetSynci(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") IntBuffer intBuffer) {
        return GL32C.glGetSynci(j, i, intBuffer);
    }

    public static void glGetBufferParameteri64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint64 *") long[] jArr) {
        GL32C.glGetBufferParameteri64v(i, i2, jArr);
    }

    public static void glMultiDrawElementsBaseVertex(@NativeType("GLenum") int i, @NativeType("GLsizei const *") int[] iArr, @NativeType("GLenum") int i2, @NativeType("void const * const *") PointerBuffer pointerBuffer, @NativeType("GLint *") int[] iArr2) {
        GL32C.glMultiDrawElementsBaseVertex(i, iArr, i2, pointerBuffer, iArr2);
    }

    public static void glGetMultisamplefv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL32C.glGetMultisamplefv(i, i2, fArr);
    }

    public static void glGetInteger64v(@NativeType("GLenum") int i, @NativeType("GLint64 *") long[] jArr) {
        GL32C.glGetInteger64v(i, jArr);
    }

    public static void glGetInteger64i_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint64 *") long[] jArr) {
        GL32C.glGetInteger64i_v(i, i2, jArr);
    }

    public static void glGetSynciv(@NativeType("GLsync") long j, @NativeType("GLenum") int i, @NativeType("GLsizei *") int[] iArr, @NativeType("GLint *") int[] iArr2) {
        GL32C.glGetSynciv(j, i, iArr, iArr2);
    }
}
