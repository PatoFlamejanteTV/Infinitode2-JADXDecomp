package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL30C.class */
public class GL30C extends GL21C {
    public static final int GL_MAJOR_VERSION = 33307;
    public static final int GL_MINOR_VERSION = 33308;
    public static final int GL_NUM_EXTENSIONS = 33309;
    public static final int GL_CONTEXT_FLAGS = 33310;
    public static final int GL_CONTEXT_FLAG_FORWARD_COMPATIBLE_BIT = 1;
    public static final int GL_COMPARE_REF_TO_TEXTURE = 34894;
    public static final int GL_CLIP_DISTANCE0 = 12288;
    public static final int GL_CLIP_DISTANCE1 = 12289;
    public static final int GL_CLIP_DISTANCE2 = 12290;
    public static final int GL_CLIP_DISTANCE3 = 12291;
    public static final int GL_CLIP_DISTANCE4 = 12292;
    public static final int GL_CLIP_DISTANCE5 = 12293;
    public static final int GL_CLIP_DISTANCE6 = 12294;
    public static final int GL_CLIP_DISTANCE7 = 12295;
    public static final int GL_MAX_CLIP_DISTANCES = 3378;
    public static final int GL_MAX_VARYING_COMPONENTS = 35659;
    public static final int GL_VERTEX_ATTRIB_ARRAY_INTEGER = 35069;
    public static final int GL_SAMPLER_1D_ARRAY = 36288;
    public static final int GL_SAMPLER_2D_ARRAY = 36289;
    public static final int GL_SAMPLER_1D_ARRAY_SHADOW = 36291;
    public static final int GL_SAMPLER_2D_ARRAY_SHADOW = 36292;
    public static final int GL_SAMPLER_CUBE_SHADOW = 36293;
    public static final int GL_UNSIGNED_INT_VEC2 = 36294;
    public static final int GL_UNSIGNED_INT_VEC3 = 36295;
    public static final int GL_UNSIGNED_INT_VEC4 = 36296;
    public static final int GL_INT_SAMPLER_1D = 36297;
    public static final int GL_INT_SAMPLER_2D = 36298;
    public static final int GL_INT_SAMPLER_3D = 36299;
    public static final int GL_INT_SAMPLER_CUBE = 36300;
    public static final int GL_INT_SAMPLER_1D_ARRAY = 36302;
    public static final int GL_INT_SAMPLER_2D_ARRAY = 36303;
    public static final int GL_UNSIGNED_INT_SAMPLER_1D = 36305;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D = 36306;
    public static final int GL_UNSIGNED_INT_SAMPLER_3D = 36307;
    public static final int GL_UNSIGNED_INT_SAMPLER_CUBE = 36308;
    public static final int GL_UNSIGNED_INT_SAMPLER_1D_ARRAY = 36310;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_ARRAY = 36311;
    public static final int GL_MIN_PROGRAM_TEXEL_OFFSET = 35076;
    public static final int GL_MAX_PROGRAM_TEXEL_OFFSET = 35077;
    public static final int GL_QUERY_WAIT = 36371;
    public static final int GL_QUERY_NO_WAIT = 36372;
    public static final int GL_QUERY_BY_REGION_WAIT = 36373;
    public static final int GL_QUERY_BY_REGION_NO_WAIT = 36374;
    public static final int GL_MAP_READ_BIT = 1;
    public static final int GL_MAP_WRITE_BIT = 2;
    public static final int GL_MAP_INVALIDATE_RANGE_BIT = 4;
    public static final int GL_MAP_INVALIDATE_BUFFER_BIT = 8;
    public static final int GL_MAP_FLUSH_EXPLICIT_BIT = 16;
    public static final int GL_MAP_UNSYNCHRONIZED_BIT = 32;
    public static final int GL_BUFFER_ACCESS_FLAGS = 37151;
    public static final int GL_BUFFER_MAP_LENGTH = 37152;
    public static final int GL_BUFFER_MAP_OFFSET = 37153;
    public static final int GL_CLAMP_READ_COLOR = 35100;
    public static final int GL_FIXED_ONLY = 35101;
    public static final int GL_DEPTH_COMPONENT32F = 36012;
    public static final int GL_DEPTH32F_STENCIL8 = 36013;
    public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 36269;
    public static final int GL_TEXTURE_RED_TYPE = 35856;
    public static final int GL_TEXTURE_GREEN_TYPE = 35857;
    public static final int GL_TEXTURE_BLUE_TYPE = 35858;
    public static final int GL_TEXTURE_ALPHA_TYPE = 35859;
    public static final int GL_TEXTURE_DEPTH_TYPE = 35862;
    public static final int GL_UNSIGNED_NORMALIZED = 35863;
    public static final int GL_RGBA32F = 34836;
    public static final int GL_RGB32F = 34837;
    public static final int GL_RGBA16F = 34842;
    public static final int GL_RGB16F = 34843;
    public static final int GL_R11F_G11F_B10F = 35898;
    public static final int GL_UNSIGNED_INT_10F_11F_11F_REV = 35899;
    public static final int GL_RGB9_E5 = 35901;
    public static final int GL_UNSIGNED_INT_5_9_9_9_REV = 35902;
    public static final int GL_TEXTURE_SHARED_SIZE = 35903;
    public static final int GL_FRAMEBUFFER = 36160;
    public static final int GL_READ_FRAMEBUFFER = 36008;
    public static final int GL_DRAW_FRAMEBUFFER = 36009;
    public static final int GL_RENDERBUFFER = 36161;
    public static final int GL_STENCIL_INDEX1 = 36166;
    public static final int GL_STENCIL_INDEX4 = 36167;
    public static final int GL_STENCIL_INDEX8 = 36168;
    public static final int GL_STENCIL_INDEX16 = 36169;
    public static final int GL_RENDERBUFFER_WIDTH = 36162;
    public static final int GL_RENDERBUFFER_HEIGHT = 36163;
    public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
    public static final int GL_RENDERBUFFER_RED_SIZE = 36176;
    public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;
    public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;
    public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;
    public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
    public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;
    public static final int GL_RENDERBUFFER_SAMPLES = 36011;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 33296;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 33297;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 33298;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 33299;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 33300;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 33301;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 33302;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 33303;
    public static final int GL_FRAMEBUFFER_DEFAULT = 33304;
    public static final int GL_COLOR_ATTACHMENT0 = 36064;
    public static final int GL_COLOR_ATTACHMENT1 = 36065;
    public static final int GL_COLOR_ATTACHMENT2 = 36066;
    public static final int GL_COLOR_ATTACHMENT3 = 36067;
    public static final int GL_COLOR_ATTACHMENT4 = 36068;
    public static final int GL_COLOR_ATTACHMENT5 = 36069;
    public static final int GL_COLOR_ATTACHMENT6 = 36070;
    public static final int GL_COLOR_ATTACHMENT7 = 36071;
    public static final int GL_COLOR_ATTACHMENT8 = 36072;
    public static final int GL_COLOR_ATTACHMENT9 = 36073;
    public static final int GL_COLOR_ATTACHMENT10 = 36074;
    public static final int GL_COLOR_ATTACHMENT11 = 36075;
    public static final int GL_COLOR_ATTACHMENT12 = 36076;
    public static final int GL_COLOR_ATTACHMENT13 = 36077;
    public static final int GL_COLOR_ATTACHMENT14 = 36078;
    public static final int GL_COLOR_ATTACHMENT15 = 36079;
    public static final int GL_COLOR_ATTACHMENT16 = 36080;
    public static final int GL_COLOR_ATTACHMENT17 = 36081;
    public static final int GL_COLOR_ATTACHMENT18 = 36082;
    public static final int GL_COLOR_ATTACHMENT19 = 36083;
    public static final int GL_COLOR_ATTACHMENT20 = 36084;
    public static final int GL_COLOR_ATTACHMENT21 = 36085;
    public static final int GL_COLOR_ATTACHMENT22 = 36086;
    public static final int GL_COLOR_ATTACHMENT23 = 36087;
    public static final int GL_COLOR_ATTACHMENT24 = 36088;
    public static final int GL_COLOR_ATTACHMENT25 = 36089;
    public static final int GL_COLOR_ATTACHMENT26 = 36090;
    public static final int GL_COLOR_ATTACHMENT27 = 36091;
    public static final int GL_COLOR_ATTACHMENT28 = 36092;
    public static final int GL_COLOR_ATTACHMENT29 = 36093;
    public static final int GL_COLOR_ATTACHMENT30 = 36094;
    public static final int GL_COLOR_ATTACHMENT31 = 36095;
    public static final int GL_DEPTH_ATTACHMENT = 36096;
    public static final int GL_STENCIL_ATTACHMENT = 36128;
    public static final int GL_DEPTH_STENCIL_ATTACHMENT = 33306;
    public static final int GL_MAX_SAMPLES = 36183;
    public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 36059;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 36060;
    public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 36182;
    public static final int GL_FRAMEBUFFER_UNDEFINED = 33305;
    public static final int GL_FRAMEBUFFER_BINDING = 36006;
    public static final int GL_DRAW_FRAMEBUFFER_BINDING = 36006;
    public static final int GL_READ_FRAMEBUFFER_BINDING = 36010;
    public static final int GL_RENDERBUFFER_BINDING = 36007;
    public static final int GL_MAX_COLOR_ATTACHMENTS = 36063;
    public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
    public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;
    public static final int GL_DEPTH_STENCIL = 34041;
    public static final int GL_UNSIGNED_INT_24_8 = 34042;
    public static final int GL_DEPTH24_STENCIL8 = 35056;
    public static final int GL_TEXTURE_STENCIL_SIZE = 35057;
    public static final int GL_HALF_FLOAT = 5131;
    public static final int GL_RGBA32UI = 36208;
    public static final int GL_RGB32UI = 36209;
    public static final int GL_RGBA16UI = 36214;
    public static final int GL_RGB16UI = 36215;
    public static final int GL_RGBA8UI = 36220;
    public static final int GL_RGB8UI = 36221;
    public static final int GL_RGBA32I = 36226;
    public static final int GL_RGB32I = 36227;
    public static final int GL_RGBA16I = 36232;
    public static final int GL_RGB16I = 36233;
    public static final int GL_RGBA8I = 36238;
    public static final int GL_RGB8I = 36239;
    public static final int GL_RED_INTEGER = 36244;
    public static final int GL_GREEN_INTEGER = 36245;
    public static final int GL_BLUE_INTEGER = 36246;
    public static final int GL_RGB_INTEGER = 36248;
    public static final int GL_RGBA_INTEGER = 36249;
    public static final int GL_BGR_INTEGER = 36250;
    public static final int GL_BGRA_INTEGER = 36251;
    public static final int GL_TEXTURE_1D_ARRAY = 35864;
    public static final int GL_TEXTURE_2D_ARRAY = 35866;
    public static final int GL_PROXY_TEXTURE_2D_ARRAY = 35867;
    public static final int GL_PROXY_TEXTURE_1D_ARRAY = 35865;
    public static final int GL_TEXTURE_BINDING_1D_ARRAY = 35868;
    public static final int GL_TEXTURE_BINDING_2D_ARRAY = 35869;
    public static final int GL_MAX_ARRAY_TEXTURE_LAYERS = 35071;
    public static final int GL_COMPRESSED_RED_RGTC1 = 36283;
    public static final int GL_COMPRESSED_SIGNED_RED_RGTC1 = 36284;
    public static final int GL_COMPRESSED_RG_RGTC2 = 36285;
    public static final int GL_COMPRESSED_SIGNED_RG_RGTC2 = 36286;
    public static final int GL_R8 = 33321;
    public static final int GL_R16 = 33322;
    public static final int GL_RG8 = 33323;
    public static final int GL_RG16 = 33324;
    public static final int GL_R16F = 33325;
    public static final int GL_R32F = 33326;
    public static final int GL_RG16F = 33327;
    public static final int GL_RG32F = 33328;
    public static final int GL_R8I = 33329;
    public static final int GL_R8UI = 33330;
    public static final int GL_R16I = 33331;
    public static final int GL_R16UI = 33332;
    public static final int GL_R32I = 33333;
    public static final int GL_R32UI = 33334;
    public static final int GL_RG8I = 33335;
    public static final int GL_RG8UI = 33336;
    public static final int GL_RG16I = 33337;
    public static final int GL_RG16UI = 33338;
    public static final int GL_RG32I = 33339;
    public static final int GL_RG32UI = 33340;
    public static final int GL_RG = 33319;
    public static final int GL_COMPRESSED_RED = 33317;
    public static final int GL_COMPRESSED_RG = 33318;
    public static final int GL_RG_INTEGER = 33320;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER = 35982;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_START = 35972;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_SIZE = 35973;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_BINDING = 35983;
    public static final int GL_INTERLEAVED_ATTRIBS = 35980;
    public static final int GL_SEPARATE_ATTRIBS = 35981;
    public static final int GL_PRIMITIVES_GENERATED = 35975;
    public static final int GL_TRANSFORM_FEEDBACK_PRIMITIVES_WRITTEN = 35976;
    public static final int GL_RASTERIZER_DISCARD = 35977;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_INTERLEAVED_COMPONENTS = 35978;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_ATTRIBS = 35979;
    public static final int GL_MAX_TRANSFORM_FEEDBACK_SEPARATE_COMPONENTS = 35968;
    public static final int GL_TRANSFORM_FEEDBACK_VARYINGS = 35971;
    public static final int GL_TRANSFORM_FEEDBACK_BUFFER_MODE = 35967;
    public static final int GL_TRANSFORM_FEEDBACK_VARYING_MAX_LENGTH = 35958;
    public static final int GL_VERTEX_ARRAY_BINDING = 34229;
    public static final int GL_FRAMEBUFFER_SRGB = 36281;

    public static native long nglGetStringi(int i, int i2);

    public static native void nglClearBufferiv(int i, int i2, long j);

    public static native void nglClearBufferuiv(int i, int i2, long j);

    public static native void nglClearBufferfv(int i, int i2, long j);

    public static native void glClearBufferfi(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLint") int i3);

    public static native void glVertexAttribI1i(@NativeType("GLuint") int i, @NativeType("GLint") int i2);

    public static native void glVertexAttribI2i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glVertexAttribI3i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glVertexAttribI4i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glVertexAttribI1ui(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glVertexAttribI2ui(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glVertexAttribI3ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glVertexAttribI4ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void nglVertexAttribI1iv(int i, long j);

    public static native void nglVertexAttribI2iv(int i, long j);

    public static native void nglVertexAttribI3iv(int i, long j);

    public static native void nglVertexAttribI4iv(int i, long j);

    public static native void nglVertexAttribI1uiv(int i, long j);

    public static native void nglVertexAttribI2uiv(int i, long j);

    public static native void nglVertexAttribI3uiv(int i, long j);

    public static native void nglVertexAttribI4uiv(int i, long j);

    public static native void nglVertexAttribI4bv(int i, long j);

    public static native void nglVertexAttribI4sv(int i, long j);

    public static native void nglVertexAttribI4ubv(int i, long j);

    public static native void nglVertexAttribI4usv(int i, long j);

    public static native void nglVertexAttribIPointer(int i, int i2, int i3, int i4, long j);

    public static native void nglGetVertexAttribIiv(int i, int i2, long j);

    public static native void nglGetVertexAttribIuiv(int i, int i2, long j);

    public static native void glUniform1ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2);

    public static native void glUniform2ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glUniform3ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4);

    public static native void glUniform4ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5);

    public static native void nglUniform1uiv(int i, int i2, long j);

    public static native void nglUniform2uiv(int i, int i2, long j);

    public static native void nglUniform3uiv(int i, int i2, long j);

    public static native void nglUniform4uiv(int i, int i2, long j);

    public static native void nglGetUniformuiv(int i, int i2, long j);

    public static native void nglBindFragDataLocation(int i, int i2, long j);

    public static native int nglGetFragDataLocation(int i, long j);

    public static native void glBeginConditionalRender(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glEndConditionalRender();

    public static native long nglMapBufferRange(int i, long j, long j2, int i2);

    public static native void glFlushMappedBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glClampColor(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    @NativeType("GLboolean")
    public static native boolean glIsRenderbuffer(@NativeType("GLuint") int i);

    public static native void glBindRenderbuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglDeleteRenderbuffers(int i, long j);

    public static native void nglGenRenderbuffers(int i, long j);

    public static native void glRenderbufferStorage(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    public static native void glRenderbufferStorageMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5);

    public static native void nglGetRenderbufferParameteriv(int i, int i2, long j);

    @NativeType("GLboolean")
    public static native boolean glIsFramebuffer(@NativeType("GLuint") int i);

    public static native void glBindFramebuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void nglDeleteFramebuffers(int i, long j);

    public static native void nglGenFramebuffers(int i, long j);

    @NativeType("GLenum")
    public static native int glCheckFramebufferStatus(@NativeType("GLenum") int i);

    public static native void glFramebufferTexture1D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5);

    public static native void glFramebufferTexture2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5);

    public static native void glFramebufferTexture3D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6);

    public static native void glFramebufferTextureLayer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glFramebufferRenderbuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    public static native void nglGetFramebufferAttachmentParameteriv(int i, int i2, int i3, long j);

    public static native void glBlitFramebuffer(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLbitfield") int i9, @NativeType("GLenum") int i10);

    public static native void glGenerateMipmap(@NativeType("GLenum") int i);

    public static native void nglTexParameterIiv(int i, int i2, long j);

    public static native void nglTexParameterIuiv(int i, int i2, long j);

    public static native void nglGetTexParameterIiv(int i, int i2, long j);

    public static native void nglGetTexParameterIuiv(int i, int i2, long j);

    public static native void glColorMaski(@NativeType("GLuint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLboolean") boolean z2, @NativeType("GLboolean") boolean z3, @NativeType("GLboolean") boolean z4);

    public static native void nglGetBooleani_v(int i, int i2, long j);

    public static native void nglGetIntegeri_v(int i, int i2, long j);

    public static native void glEnablei(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glDisablei(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    @NativeType("GLboolean")
    public static native boolean glIsEnabledi(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glBindBufferRange(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glBindBufferBase(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glBeginTransformFeedback(@NativeType("GLenum") int i);

    public static native void glEndTransformFeedback();

    public static native void nglTransformFeedbackVaryings(int i, int i2, long j, int i3);

    public static native void nglGetTransformFeedbackVarying(int i, int i2, int i3, long j, long j2, long j3, long j4);

    public static native void glBindVertexArray(@NativeType("GLuint") int i);

    public static native void nglDeleteVertexArrays(int i, long j);

    public static native void nglGenVertexArrays(int i, long j);

    @NativeType("GLboolean")
    public static native boolean glIsVertexArray(@NativeType("GLuint") int i);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL30C() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLubyte const *")
    public static String glGetStringi(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return MemoryUtil.memUTF8Safe(nglGetStringi(i, i2));
    }

    public static void glClearBufferiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglClearBufferiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glClearBufferuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglClearBufferuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glClearBufferfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglClearBufferfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertexAttribI1iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribI1iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI2iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglVertexAttribI2iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI3iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglVertexAttribI3iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttribI4iv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI1uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglVertexAttribI1uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI2uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglVertexAttribI2uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI3uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglVertexAttribI3uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertexAttribI4uiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexAttribI4bv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttribI4bv(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribI4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttribI4sv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribI4ubv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglVertexAttribI4ubv(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribI4usv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertexAttribI4usv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglVertexAttribIPointer(i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        nglVertexAttribIPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglVertexAttribIPointer(i, i2, i3, i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglVertexAttribIPointer(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetVertexAttribIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetVertexAttribIiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexAttribIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexAttribIiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetVertexAttribIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetVertexAttribIuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetVertexAttribIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetVertexAttribIuiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glUniform1uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform1uiv(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform2uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform2uiv(i, intBuffer.remaining() >> 1, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform3uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform3uiv(i, intBuffer.remaining() / 3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glUniform4uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglUniform4uiv(i, intBuffer.remaining() >> 2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetUniformuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetUniformui(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetUniformuiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glBindFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglBindFragDataLocation(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glBindFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            nglBindFragDataLocation(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLint")
    public static int glGetFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetFragDataLocation(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetFragDataLocation(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2) {
        nglMapBufferRange(i, j, j2, i2);
        return MemoryUtil.memByteBufferSafe(j2, (int) j2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2, ByteBuffer byteBuffer) {
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapBufferRange(i, j, j2, i2), (int) j2);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteRenderbuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteRenderbuffers(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenRenderbuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenRenderbuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenRenderbuffers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenRenderbuffers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetRenderbufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetRenderbufferParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetRenderbufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetRenderbufferParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteFramebuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteFramebuffers(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenFramebuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenFramebuffers(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenFramebuffers() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenFramebuffers(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetFramebufferAttachmentParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetFramebufferAttachmentParameteriv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetFramebufferAttachmentParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetFramebufferAttachmentParameteriv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexParameterIiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexParameterIi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglTexParameterIiv(i, i2, MemoryUtil.memAddress(stackGet.ints(i3)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexParameterIuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexParameterIui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglTexParameterIuiv(i, i2, MemoryUtil.memAddress(stackGet.ints(i3)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexParameterIiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexParameterIi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexParameterIiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexParameterIuiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexParameterIui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexParameterIuiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetBooleani_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 1);
        }
        nglGetBooleani_v(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static boolean glGetBooleani(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            ByteBuffer calloc = stackGet.calloc(1);
            nglGetBooleani_v(i, i2, MemoryUtil.memAddress(calloc));
            return calloc.get(0) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetIntegeri_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetIntegeri_v(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetIntegeri(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetIntegeri_v(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTransformFeedbackVaryings(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLenum") int i2) {
        nglTransformFeedbackVaryings(i, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer), i2);
    }

    public static void glTransformFeedbackVaryings(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence[] charSequenceArr, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memASCII, charSequenceArr);
            nglTransformFeedbackVaryings(i, charSequenceArr.length, apiArray, i2);
            APIUtil.apiArrayFree(apiArray, charSequenceArr.length);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glTransformFeedbackVaryings(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence charSequence, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            long apiArray = APIUtil.apiArray(stackGet, MemoryUtil::memASCII, charSequence);
            nglTransformFeedbackVaryings(i, 1, apiArray, i2);
            APIUtil.apiArrayFree(apiArray, 1);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
            Checks.check((Buffer) intBuffer3, 1);
        }
        nglGetTransformFeedbackVarying(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(intBuffer3), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetTransformFeedbackVarying(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetTransformFeedbackVarying(i, i2, GL20.glGetProgrami(i, 35958), intBuffer, intBuffer2);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteVertexArrays(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteVertexArrays(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGenVertexArrays(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenVertexArrays(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenVertexArrays() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenVertexArrays(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glClearBufferiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glClearBufferiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glClearBufferuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glClearBufferuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glClearBufferfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glClearBufferfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glVertexAttribI1iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI1iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI2iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI3iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI1uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI1uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI2uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI2uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI3uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glVertexAttribI4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glVertexAttribI4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribI4sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glVertexAttribI4usv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertexAttribI4usv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glGetVertexAttribIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetVertexAttribIiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetVertexAttribIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetVertexAttribIuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glUniform1uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform1uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glUniform2uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform2uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 1, iArr, j);
    }

    public static void glUniform3uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length / 3, iArr, j);
    }

    public static void glUniform4uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glUniform4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length >> 2, iArr, j);
    }

    public static void glGetUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetUniformuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteRenderbuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenRenderbuffers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenRenderbuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetRenderbufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetRenderbufferParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteFramebuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenFramebuffers(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenFramebuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetFramebufferAttachmentParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetFramebufferAttachmentParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexParameterIiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glTexParameterIuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTexParameterIiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetTexParameterIuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetIntegeri_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetIntegeri_v;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetTransformFeedbackVarying;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.check(iArr2, 1);
            Checks.check(iArr3, 1);
        }
        JNI.callPPPPV(i, i2, byteBuffer.remaining(), iArr, iArr2, iArr3, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteVertexArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGenVertexArrays(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenVertexArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }
}
