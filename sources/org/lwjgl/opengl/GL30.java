package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL30.class */
public class GL30 extends GL21 {
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
    public static final int GL_CLAMP_VERTEX_COLOR = 35098;
    public static final int GL_CLAMP_FRAGMENT_COLOR = 35099;
    public static final int GL_CLAMP_READ_COLOR = 35100;
    public static final int GL_FIXED_ONLY = 35101;
    public static final int GL_DEPTH_COMPONENT32F = 36012;
    public static final int GL_DEPTH32F_STENCIL8 = 36013;
    public static final int GL_FLOAT_32_UNSIGNED_INT_24_8_REV = 36269;
    public static final int GL_TEXTURE_RED_TYPE = 35856;
    public static final int GL_TEXTURE_GREEN_TYPE = 35857;
    public static final int GL_TEXTURE_BLUE_TYPE = 35858;
    public static final int GL_TEXTURE_ALPHA_TYPE = 35859;
    public static final int GL_TEXTURE_LUMINANCE_TYPE = 35860;
    public static final int GL_TEXTURE_INTENSITY_TYPE = 35861;
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
    public static final int GL_INDEX = 33314;
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
    public static final int GL_ALPHA_INTEGER = 36247;
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

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL30() {
        throw new UnsupportedOperationException();
    }

    public static long nglGetStringi(int i, int i2) {
        return GL30C.nglGetStringi(i, i2);
    }

    @NativeType("GLubyte const *")
    public static String glGetStringi(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL30C.glGetStringi(i, i2);
    }

    public static void nglClearBufferiv(int i, int i2, long j) {
        GL30C.nglClearBufferiv(i, i2, j);
    }

    public static void glClearBufferiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL30C.glClearBufferiv(i, i2, intBuffer);
    }

    public static void nglClearBufferuiv(int i, int i2, long j) {
        GL30C.nglClearBufferuiv(i, i2, j);
    }

    public static void glClearBufferuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL30C.glClearBufferuiv(i, i2, intBuffer);
    }

    public static void nglClearBufferfv(int i, int i2, long j) {
        GL30C.nglClearBufferfv(i, i2, j);
    }

    public static void glClearBufferfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL30C.glClearBufferfv(i, i2, floatBuffer);
    }

    public static void glClearBufferfi(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLfloat") float f, @NativeType("GLint") int i3) {
        GL30C.glClearBufferfi(i, i2, f, i3);
    }

    public static void glVertexAttribI1i(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        GL30C.glVertexAttribI1i(i, i2);
    }

    public static void glVertexAttribI2i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3) {
        GL30C.glVertexAttribI2i(i, i2, i3);
    }

    public static void glVertexAttribI3i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4) {
        GL30C.glVertexAttribI3i(i, i2, i3, i4);
    }

    public static void glVertexAttribI4i(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        GL30C.glVertexAttribI4i(i, i2, i3, i4, i5);
    }

    public static void glVertexAttribI1ui(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL30C.glVertexAttribI1ui(i, i2);
    }

    public static void glVertexAttribI2ui(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL30C.glVertexAttribI2ui(i, i2, i3);
    }

    public static void glVertexAttribI3ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4) {
        GL30C.glVertexAttribI3ui(i, i2, i3, i4);
    }

    public static void glVertexAttribI4ui(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        GL30C.glVertexAttribI4ui(i, i2, i3, i4, i5);
    }

    public static void nglVertexAttribI1iv(int i, long j) {
        GL30C.nglVertexAttribI1iv(i, j);
    }

    public static void glVertexAttribI1iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI1iv(i, intBuffer);
    }

    public static void nglVertexAttribI2iv(int i, long j) {
        GL30C.nglVertexAttribI2iv(i, j);
    }

    public static void glVertexAttribI2iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI2iv(i, intBuffer);
    }

    public static void nglVertexAttribI3iv(int i, long j) {
        GL30C.nglVertexAttribI3iv(i, j);
    }

    public static void glVertexAttribI3iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI3iv(i, intBuffer);
    }

    public static void nglVertexAttribI4iv(int i, long j) {
        GL30C.nglVertexAttribI4iv(i, j);
    }

    public static void glVertexAttribI4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI4iv(i, intBuffer);
    }

    public static void nglVertexAttribI1uiv(int i, long j) {
        GL30C.nglVertexAttribI1uiv(i, j);
    }

    public static void glVertexAttribI1uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI1uiv(i, intBuffer);
    }

    public static void nglVertexAttribI2uiv(int i, long j) {
        GL30C.nglVertexAttribI2uiv(i, j);
    }

    public static void glVertexAttribI2uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI2uiv(i, intBuffer);
    }

    public static void nglVertexAttribI3uiv(int i, long j) {
        GL30C.nglVertexAttribI3uiv(i, j);
    }

    public static void glVertexAttribI3uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI3uiv(i, intBuffer);
    }

    public static void nglVertexAttribI4uiv(int i, long j) {
        GL30C.nglVertexAttribI4uiv(i, j);
    }

    public static void glVertexAttribI4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribI4uiv(i, intBuffer);
    }

    public static void nglVertexAttribI4bv(int i, long j) {
        GL30C.nglVertexAttribI4bv(i, j);
    }

    public static void glVertexAttribI4bv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        GL30C.glVertexAttribI4bv(i, byteBuffer);
    }

    public static void nglVertexAttribI4sv(int i, long j) {
        GL30C.nglVertexAttribI4sv(i, j);
    }

    public static void glVertexAttribI4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        GL30C.glVertexAttribI4sv(i, shortBuffer);
    }

    public static void nglVertexAttribI4ubv(int i, long j) {
        GL30C.nglVertexAttribI4ubv(i, j);
    }

    public static void glVertexAttribI4ubv(@NativeType("GLuint") int i, @NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        GL30C.glVertexAttribI4ubv(i, byteBuffer);
    }

    public static void nglVertexAttribI4usv(int i, long j) {
        GL30C.nglVertexAttribI4usv(i, j);
    }

    public static void glVertexAttribI4usv(@NativeType("GLuint") int i, @NativeType("GLshort const *") ShortBuffer shortBuffer) {
        GL30C.glVertexAttribI4usv(i, shortBuffer);
    }

    public static void nglVertexAttribIPointer(int i, int i2, int i3, int i4, long j) {
        GL30C.nglVertexAttribIPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL30C.glVertexAttribIPointer(i, i2, i3, i4, byteBuffer);
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") long j) {
        GL30C.glVertexAttribIPointer(i, i2, i3, i4, j);
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL30C.glVertexAttribIPointer(i, i2, i3, i4, shortBuffer);
    }

    public static void glVertexAttribIPointer(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL30C.glVertexAttribIPointer(i, i2, i3, i4, intBuffer);
    }

    public static void nglGetVertexAttribIiv(int i, int i2, long j) {
        GL30C.nglGetVertexAttribIiv(i, i2, j);
    }

    public static void glGetVertexAttribIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetVertexAttribIiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetVertexAttribIi(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL30C.glGetVertexAttribIi(i, i2);
    }

    public static void nglGetVertexAttribIuiv(int i, int i2, long j) {
        GL30C.nglGetVertexAttribIuiv(i, i2, j);
    }

    public static void glGetVertexAttribIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGetVertexAttribIuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetVertexAttribIui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        return GL30C.glGetVertexAttribIui(i, i2);
    }

    public static void glUniform1ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2) {
        GL30C.glUniform1ui(i, i2);
    }

    public static void glUniform2ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL30C.glUniform2ui(i, i2, i3);
    }

    public static void glUniform3ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4) {
        GL30C.glUniform3ui(i, i2, i3, i4);
    }

    public static void glUniform4ui(@NativeType("GLint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLuint") int i4, @NativeType("GLuint") int i5) {
        GL30C.glUniform4ui(i, i2, i3, i4, i5);
    }

    public static void nglUniform1uiv(int i, int i2, long j) {
        GL30C.nglUniform1uiv(i, i2, j);
    }

    public static void glUniform1uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glUniform1uiv(i, intBuffer);
    }

    public static void nglUniform2uiv(int i, int i2, long j) {
        GL30C.nglUniform2uiv(i, i2, j);
    }

    public static void glUniform2uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glUniform2uiv(i, intBuffer);
    }

    public static void nglUniform3uiv(int i, int i2, long j) {
        GL30C.nglUniform3uiv(i, i2, j);
    }

    public static void glUniform3uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glUniform3uiv(i, intBuffer);
    }

    public static void nglUniform4uiv(int i, int i2, long j) {
        GL30C.nglUniform4uiv(i, i2, j);
    }

    public static void glUniform4uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glUniform4uiv(i, intBuffer);
    }

    public static void nglGetUniformuiv(int i, int i2, long j) {
        GL30C.nglGetUniformuiv(i, i2, j);
    }

    public static void glGetUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGetUniformuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetUniformui(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        return GL30C.glGetUniformui(i, i2);
    }

    public static void nglBindFragDataLocation(int i, int i2, long j) {
        GL30C.nglBindFragDataLocation(i, i2, j);
    }

    public static void glBindFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL30C.glBindFragDataLocation(i, i2, byteBuffer);
    }

    public static void glBindFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        GL30C.glBindFragDataLocation(i, i2, charSequence);
    }

    public static int nglGetFragDataLocation(int i, long j) {
        return GL30C.nglGetFragDataLocation(i, j);
    }

    @NativeType("GLint")
    public static int glGetFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL30C.glGetFragDataLocation(i, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetFragDataLocation(@NativeType("GLuint") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL30C.glGetFragDataLocation(i, charSequence);
    }

    public static void glBeginConditionalRender(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        GL30C.glBeginConditionalRender(i, i2);
    }

    public static void glEndConditionalRender() {
        GL30C.glEndConditionalRender();
    }

    public static long nglMapBufferRange(int i, long j, long j2, int i2) {
        return GL30C.nglMapBufferRange(i, j, j2, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2) {
        return GL30C.glMapBufferRange(i, j, j2, i2);
    }

    @NativeType("void *")
    public static ByteBuffer glMapBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLbitfield") int i2, ByteBuffer byteBuffer) {
        return GL30C.glMapBufferRange(i, j, j2, i2, byteBuffer);
    }

    public static void glFlushMappedBufferRange(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL30C.glFlushMappedBufferRange(i, j, j2);
    }

    public static void glClampColor(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        GL30C.glClampColor(i, i2);
    }

    @NativeType("GLboolean")
    public static boolean glIsRenderbuffer(@NativeType("GLuint") int i) {
        return GL30C.glIsRenderbuffer(i);
    }

    public static void glBindRenderbuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL30C.glBindRenderbuffer(i, i2);
    }

    public static void nglDeleteRenderbuffers(int i, long j) {
        GL30C.nglDeleteRenderbuffers(i, j);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glDeleteRenderbuffers(intBuffer);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") int i) {
        GL30C.glDeleteRenderbuffers(i);
    }

    public static void nglGenRenderbuffers(int i, long j) {
        GL30C.nglGenRenderbuffers(i, j);
    }

    public static void glGenRenderbuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGenRenderbuffers(intBuffer);
    }

    @NativeType("void")
    public static int glGenRenderbuffers() {
        return GL30C.glGenRenderbuffers();
    }

    public static void glRenderbufferStorage(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL30C.glRenderbufferStorage(i, i2, i3, i4);
    }

    public static void glRenderbufferStorageMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL30C.glRenderbufferStorageMultisample(i, i2, i3, i4, i5);
    }

    public static void nglGetRenderbufferParameteriv(int i, int i2, long j) {
        GL30C.nglGetRenderbufferParameteriv(i, i2, j);
    }

    public static void glGetRenderbufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetRenderbufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetRenderbufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL30C.glGetRenderbufferParameteri(i, i2);
    }

    @NativeType("GLboolean")
    public static boolean glIsFramebuffer(@NativeType("GLuint") int i) {
        return GL30C.glIsFramebuffer(i);
    }

    public static void glBindFramebuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL30C.glBindFramebuffer(i, i2);
    }

    public static void nglDeleteFramebuffers(int i, long j) {
        GL30C.nglDeleteFramebuffers(i, j);
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glDeleteFramebuffers(intBuffer);
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") int i) {
        GL30C.glDeleteFramebuffers(i);
    }

    public static void nglGenFramebuffers(int i, long j) {
        GL30C.nglGenFramebuffers(i, j);
    }

    public static void glGenFramebuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGenFramebuffers(intBuffer);
    }

    @NativeType("void")
    public static int glGenFramebuffers() {
        return GL30C.glGenFramebuffers();
    }

    @NativeType("GLenum")
    public static int glCheckFramebufferStatus(@NativeType("GLenum") int i) {
        return GL30C.glCheckFramebufferStatus(i);
    }

    public static void glFramebufferTexture1D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5) {
        GL30C.glFramebufferTexture1D(i, i2, i3, i4, i5);
    }

    public static void glFramebufferTexture2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5) {
        GL30C.glFramebufferTexture2D(i, i2, i3, i4, i5);
    }

    public static void glFramebufferTexture3D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6) {
        GL30C.glFramebufferTexture3D(i, i2, i3, i4, i5, i6);
    }

    public static void glFramebufferTextureLayer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        GL30C.glFramebufferTextureLayer(i, i2, i3, i4, i5);
    }

    public static void glFramebufferRenderbuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4) {
        GL30C.glFramebufferRenderbuffer(i, i2, i3, i4);
    }

    public static void nglGetFramebufferAttachmentParameteriv(int i, int i2, int i3, long j) {
        GL30C.nglGetFramebufferAttachmentParameteriv(i, i2, i3, j);
    }

    public static void glGetFramebufferAttachmentParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetFramebufferAttachmentParameteriv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetFramebufferAttachmentParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL30C.glGetFramebufferAttachmentParameteri(i, i2, i3);
    }

    public static void glBlitFramebuffer(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLbitfield") int i9, @NativeType("GLenum") int i10) {
        GL30C.glBlitFramebuffer(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    public static void glGenerateMipmap(@NativeType("GLenum") int i) {
        GL30C.glGenerateMipmap(i);
    }

    public static void nglTexParameterIiv(int i, int i2, long j) {
        GL30C.nglTexParameterIiv(i, i2, j);
    }

    public static void glTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL30C.glTexParameterIiv(i, i2, intBuffer);
    }

    public static void glTexParameterIi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int i3) {
        GL30C.glTexParameterIi(i, i2, i3);
    }

    public static void nglTexParameterIuiv(int i, int i2, long j) {
        GL30C.nglTexParameterIuiv(i, i2, j);
    }

    public static void glTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glTexParameterIuiv(i, i2, intBuffer);
    }

    public static void glTexParameterIui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int i3) {
        GL30C.glTexParameterIui(i, i2, i3);
    }

    public static void nglGetTexParameterIiv(int i, int i2, long j) {
        GL30C.nglGetTexParameterIiv(i, i2, j);
    }

    public static void glGetTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetTexParameterIiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetTexParameterIi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL30C.glGetTexParameterIi(i, i2);
    }

    public static void nglGetTexParameterIuiv(int i, int i2, long j) {
        GL30C.nglGetTexParameterIuiv(i, i2, j);
    }

    public static void glGetTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGetTexParameterIuiv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetTexParameterIui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL30C.glGetTexParameterIui(i, i2);
    }

    public static void glColorMaski(@NativeType("GLuint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLboolean") boolean z2, @NativeType("GLboolean") boolean z3, @NativeType("GLboolean") boolean z4) {
        GL30C.glColorMaski(i, z, z2, z3, z4);
    }

    public static void nglGetBooleani_v(int i, int i2, long j) {
        GL30C.nglGetBooleani_v(i, i2, j);
    }

    public static void glGetBooleani_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        GL30C.glGetBooleani_v(i, i2, byteBuffer);
    }

    @NativeType("void")
    public static boolean glGetBooleani(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL30C.glGetBooleani(i, i2);
    }

    public static void nglGetIntegeri_v(int i, int i2, long j) {
        GL30C.nglGetIntegeri_v(i, i2, j);
    }

    public static void glGetIntegeri_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetIntegeri_v(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetIntegeri(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL30C.glGetIntegeri(i, i2);
    }

    public static void glEnablei(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL30C.glEnablei(i, i2);
    }

    public static void glDisablei(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL30C.glDisablei(i, i2);
    }

    @NativeType("GLboolean")
    public static boolean glIsEnabledi(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL30C.glIsEnabledi(i, i2);
    }

    public static void glBindBufferRange(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL30C.glBindBufferRange(i, i2, i3, j, j2);
    }

    public static void glBindBufferBase(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL30C.glBindBufferBase(i, i2, i3);
    }

    public static void glBeginTransformFeedback(@NativeType("GLenum") int i) {
        GL30C.glBeginTransformFeedback(i);
    }

    public static void glEndTransformFeedback() {
        GL30C.glEndTransformFeedback();
    }

    public static void nglTransformFeedbackVaryings(int i, int i2, long j, int i3) {
        GL30C.nglTransformFeedbackVaryings(i, i2, j, i3);
    }

    public static void glTransformFeedbackVaryings(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") PointerBuffer pointerBuffer, @NativeType("GLenum") int i2) {
        GL30C.glTransformFeedbackVaryings(i, pointerBuffer, i2);
    }

    public static void glTransformFeedbackVaryings(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence[] charSequenceArr, @NativeType("GLenum") int i2) {
        GL30C.glTransformFeedbackVaryings(i, charSequenceArr, i2);
    }

    public static void glTransformFeedbackVaryings(@NativeType("GLuint") int i, @NativeType("GLchar const * const *") CharSequence charSequence, @NativeType("GLenum") int i2) {
        GL30C.glTransformFeedbackVaryings(i, charSequence, i2);
    }

    public static void nglGetTransformFeedbackVarying(int i, int i2, int i3, long j, long j2, long j3, long j4) {
        GL30C.nglGetTransformFeedbackVarying(i, i2, i3, j, j2, j3, j4);
    }

    public static void glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLenum *") IntBuffer intBuffer3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL30C.glGetTransformFeedbackVarying(i, i2, intBuffer, intBuffer2, intBuffer3, byteBuffer);
    }

    @NativeType("void")
    public static String glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return GL30C.glGetTransformFeedbackVarying(i, i2, i3, intBuffer, intBuffer2);
    }

    @NativeType("void")
    public static String glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        return glGetTransformFeedbackVarying(i, i2, GL20.glGetProgrami(i, 35958), intBuffer, intBuffer2);
    }

    public static void glBindVertexArray(@NativeType("GLuint") int i) {
        GL30C.glBindVertexArray(i);
    }

    public static void nglDeleteVertexArrays(int i, long j) {
        GL30C.nglDeleteVertexArrays(i, j);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glDeleteVertexArrays(intBuffer);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") int i) {
        GL30C.glDeleteVertexArrays(i);
    }

    public static void nglGenVertexArrays(int i, long j) {
        GL30C.nglGenVertexArrays(i, j);
    }

    public static void glGenVertexArrays(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGenVertexArrays(intBuffer);
    }

    @NativeType("void")
    public static int glGenVertexArrays() {
        return GL30C.glGenVertexArrays();
    }

    @NativeType("GLboolean")
    public static boolean glIsVertexArray(@NativeType("GLuint") int i) {
        return GL30C.glIsVertexArray(i);
    }

    public static void glClearBufferiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        GL30C.glClearBufferiv(i, i2, iArr);
    }

    public static void glClearBufferuiv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint const *") int[] iArr) {
        GL30C.glClearBufferuiv(i, i2, iArr);
    }

    public static void glClearBufferfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL30C.glClearBufferfv(i, i2, fArr);
    }

    public static void glVertexAttribI1iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL30C.glVertexAttribI1iv(i, iArr);
    }

    public static void glVertexAttribI2iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL30C.glVertexAttribI2iv(i, iArr);
    }

    public static void glVertexAttribI3iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL30C.glVertexAttribI3iv(i, iArr);
    }

    public static void glVertexAttribI4iv(@NativeType("GLuint") int i, @NativeType("GLint const *") int[] iArr) {
        GL30C.glVertexAttribI4iv(i, iArr);
    }

    public static void glVertexAttribI1uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glVertexAttribI1uiv(i, iArr);
    }

    public static void glVertexAttribI2uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glVertexAttribI2uiv(i, iArr);
    }

    public static void glVertexAttribI3uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glVertexAttribI3uiv(i, iArr);
    }

    public static void glVertexAttribI4uiv(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glVertexAttribI4uiv(i, iArr);
    }

    public static void glVertexAttribI4sv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        GL30C.glVertexAttribI4sv(i, sArr);
    }

    public static void glVertexAttribI4usv(@NativeType("GLuint") int i, @NativeType("GLshort const *") short[] sArr) {
        GL30C.glVertexAttribI4usv(i, sArr);
    }

    public static void glGetVertexAttribIiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetVertexAttribIiv(i, i2, iArr);
    }

    public static void glGetVertexAttribIuiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        GL30C.glGetVertexAttribIuiv(i, i2, iArr);
    }

    public static void glUniform1uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glUniform1uiv(i, iArr);
    }

    public static void glUniform2uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glUniform2uiv(i, iArr);
    }

    public static void glUniform3uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glUniform3uiv(i, iArr);
    }

    public static void glUniform4uiv(@NativeType("GLint") int i, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glUniform4uiv(i, iArr);
    }

    public static void glGetUniformuiv(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint *") int[] iArr) {
        GL30C.glGetUniformuiv(i, i2, iArr);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") int[] iArr) {
        GL30C.glDeleteRenderbuffers(iArr);
    }

    public static void glGenRenderbuffers(@NativeType("GLuint *") int[] iArr) {
        GL30C.glGenRenderbuffers(iArr);
    }

    public static void glGetRenderbufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetRenderbufferParameteriv(i, i2, iArr);
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") int[] iArr) {
        GL30C.glDeleteFramebuffers(iArr);
    }

    public static void glGenFramebuffers(@NativeType("GLuint *") int[] iArr) {
        GL30C.glGenFramebuffers(iArr);
    }

    public static void glGetFramebufferAttachmentParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetFramebufferAttachmentParameteriv(i, i2, i3, iArr);
    }

    public static void glTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL30C.glTexParameterIiv(i, i2, iArr);
    }

    public static void glTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL30C.glTexParameterIuiv(i, i2, iArr);
    }

    public static void glGetTexParameterIiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetTexParameterIiv(i, i2, iArr);
    }

    public static void glGetTexParameterIuiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint *") int[] iArr) {
        GL30C.glGetTexParameterIuiv(i, i2, iArr);
    }

    public static void glGetIntegeri_v(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetIntegeri_v(i, i2, iArr);
    }

    public static void glGetTransformFeedbackVarying(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLenum *") int[] iArr3, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL30C.glGetTransformFeedbackVarying(i, i2, iArr, iArr2, iArr3, byteBuffer);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") int[] iArr) {
        GL30C.glDeleteVertexArrays(iArr);
    }

    public static void glGenVertexArrays(@NativeType("GLuint *") int[] iArr) {
        GL30C.glGenVertexArrays(iArr);
    }
}
