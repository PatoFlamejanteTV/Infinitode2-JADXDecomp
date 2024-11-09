package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL43C.class */
public class GL43C extends GL42C {
    public static final int GL_NUM_SHADING_LANGUAGE_VERSIONS = 33513;
    public static final int GL_VERTEX_ATTRIB_ARRAY_LONG = 34638;
    public static final int GL_COMPRESSED_RGB8_ETC2 = 37492;
    public static final int GL_COMPRESSED_SRGB8_ETC2 = 37493;
    public static final int GL_COMPRESSED_RGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 37494;
    public static final int GL_COMPRESSED_SRGB8_PUNCHTHROUGH_ALPHA1_ETC2 = 37495;
    public static final int GL_COMPRESSED_RGBA8_ETC2_EAC = 37496;
    public static final int GL_COMPRESSED_SRGB8_ALPHA8_ETC2_EAC = 37497;
    public static final int GL_COMPRESSED_R11_EAC = 37488;
    public static final int GL_COMPRESSED_SIGNED_R11_EAC = 37489;
    public static final int GL_COMPRESSED_RG11_EAC = 37490;
    public static final int GL_COMPRESSED_SIGNED_RG11_EAC = 37491;
    public static final int GL_PRIMITIVE_RESTART_FIXED_INDEX = 36201;
    public static final int GL_ANY_SAMPLES_PASSED_CONSERVATIVE = 36202;
    public static final int GL_MAX_ELEMENT_INDEX = 36203;
    public static final int GL_TEXTURE_IMMUTABLE_LEVELS = 33503;
    public static final int GL_COMPUTE_SHADER = 37305;
    public static final int GL_MAX_COMPUTE_UNIFORM_BLOCKS = 37307;
    public static final int GL_MAX_COMPUTE_TEXTURE_IMAGE_UNITS = 37308;
    public static final int GL_MAX_COMPUTE_IMAGE_UNIFORMS = 37309;
    public static final int GL_MAX_COMPUTE_SHARED_MEMORY_SIZE = 33378;
    public static final int GL_MAX_COMPUTE_UNIFORM_COMPONENTS = 33379;
    public static final int GL_MAX_COMPUTE_ATOMIC_COUNTER_BUFFERS = 33380;
    public static final int GL_MAX_COMPUTE_ATOMIC_COUNTERS = 33381;
    public static final int GL_MAX_COMBINED_COMPUTE_UNIFORM_COMPONENTS = 33382;
    public static final int GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS = 37099;
    public static final int GL_MAX_COMPUTE_WORK_GROUP_COUNT = 37310;
    public static final int GL_MAX_COMPUTE_WORK_GROUP_SIZE = 37311;
    public static final int GL_COMPUTE_WORK_GROUP_SIZE = 33383;
    public static final int GL_UNIFORM_BLOCK_REFERENCED_BY_COMPUTE_SHADER = 37100;
    public static final int GL_ATOMIC_COUNTER_BUFFER_REFERENCED_BY_COMPUTE_SHADER = 37101;
    public static final int GL_DISPATCH_INDIRECT_BUFFER = 37102;
    public static final int GL_DISPATCH_INDIRECT_BUFFER_BINDING = 37103;
    public static final int GL_COMPUTE_SHADER_BIT = 32;
    public static final int GL_DEBUG_OUTPUT = 37600;
    public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS = 33346;
    public static final int GL_CONTEXT_FLAG_DEBUG_BIT = 2;
    public static final int GL_MAX_DEBUG_MESSAGE_LENGTH = 37187;
    public static final int GL_MAX_DEBUG_LOGGED_MESSAGES = 37188;
    public static final int GL_DEBUG_LOGGED_MESSAGES = 37189;
    public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 33347;
    public static final int GL_MAX_DEBUG_GROUP_STACK_DEPTH = 33388;
    public static final int GL_DEBUG_GROUP_STACK_DEPTH = 33389;
    public static final int GL_MAX_LABEL_LENGTH = 33512;
    public static final int GL_DEBUG_CALLBACK_FUNCTION = 33348;
    public static final int GL_DEBUG_CALLBACK_USER_PARAM = 33349;
    public static final int GL_DEBUG_SOURCE_API = 33350;
    public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM = 33351;
    public static final int GL_DEBUG_SOURCE_SHADER_COMPILER = 33352;
    public static final int GL_DEBUG_SOURCE_THIRD_PARTY = 33353;
    public static final int GL_DEBUG_SOURCE_APPLICATION = 33354;
    public static final int GL_DEBUG_SOURCE_OTHER = 33355;
    public static final int GL_DEBUG_TYPE_ERROR = 33356;
    public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357;
    public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 33358;
    public static final int GL_DEBUG_TYPE_PORTABILITY = 33359;
    public static final int GL_DEBUG_TYPE_PERFORMANCE = 33360;
    public static final int GL_DEBUG_TYPE_OTHER = 33361;
    public static final int GL_DEBUG_TYPE_MARKER = 33384;
    public static final int GL_DEBUG_TYPE_PUSH_GROUP = 33385;
    public static final int GL_DEBUG_TYPE_POP_GROUP = 33386;
    public static final int GL_DEBUG_SEVERITY_HIGH = 37190;
    public static final int GL_DEBUG_SEVERITY_MEDIUM = 37191;
    public static final int GL_DEBUG_SEVERITY_LOW = 37192;
    public static final int GL_DEBUG_SEVERITY_NOTIFICATION = 33387;
    public static final int GL_BUFFER = 33504;
    public static final int GL_SHADER = 33505;
    public static final int GL_PROGRAM = 33506;
    public static final int GL_QUERY = 33507;
    public static final int GL_PROGRAM_PIPELINE = 33508;
    public static final int GL_SAMPLER = 33510;
    public static final int GL_MAX_UNIFORM_LOCATIONS = 33390;
    public static final int GL_FRAMEBUFFER_DEFAULT_WIDTH = 37648;
    public static final int GL_FRAMEBUFFER_DEFAULT_HEIGHT = 37649;
    public static final int GL_FRAMEBUFFER_DEFAULT_LAYERS = 37650;
    public static final int GL_FRAMEBUFFER_DEFAULT_SAMPLES = 37651;
    public static final int GL_FRAMEBUFFER_DEFAULT_FIXED_SAMPLE_LOCATIONS = 37652;
    public static final int GL_MAX_FRAMEBUFFER_WIDTH = 37653;
    public static final int GL_MAX_FRAMEBUFFER_HEIGHT = 37654;
    public static final int GL_MAX_FRAMEBUFFER_LAYERS = 37655;
    public static final int GL_MAX_FRAMEBUFFER_SAMPLES = 37656;
    public static final int GL_INTERNALFORMAT_SUPPORTED = 33391;
    public static final int GL_INTERNALFORMAT_PREFERRED = 33392;
    public static final int GL_INTERNALFORMAT_RED_SIZE = 33393;
    public static final int GL_INTERNALFORMAT_GREEN_SIZE = 33394;
    public static final int GL_INTERNALFORMAT_BLUE_SIZE = 33395;
    public static final int GL_INTERNALFORMAT_ALPHA_SIZE = 33396;
    public static final int GL_INTERNALFORMAT_DEPTH_SIZE = 33397;
    public static final int GL_INTERNALFORMAT_STENCIL_SIZE = 33398;
    public static final int GL_INTERNALFORMAT_SHARED_SIZE = 33399;
    public static final int GL_INTERNALFORMAT_RED_TYPE = 33400;
    public static final int GL_INTERNALFORMAT_GREEN_TYPE = 33401;
    public static final int GL_INTERNALFORMAT_BLUE_TYPE = 33402;
    public static final int GL_INTERNALFORMAT_ALPHA_TYPE = 33403;
    public static final int GL_INTERNALFORMAT_DEPTH_TYPE = 33404;
    public static final int GL_INTERNALFORMAT_STENCIL_TYPE = 33405;
    public static final int GL_MAX_WIDTH = 33406;
    public static final int GL_MAX_HEIGHT = 33407;
    public static final int GL_MAX_DEPTH = 33408;
    public static final int GL_MAX_LAYERS = 33409;
    public static final int GL_MAX_COMBINED_DIMENSIONS = 33410;
    public static final int GL_COLOR_COMPONENTS = 33411;
    public static final int GL_DEPTH_COMPONENTS = 33412;
    public static final int GL_STENCIL_COMPONENTS = 33413;
    public static final int GL_COLOR_RENDERABLE = 33414;
    public static final int GL_DEPTH_RENDERABLE = 33415;
    public static final int GL_STENCIL_RENDERABLE = 33416;
    public static final int GL_FRAMEBUFFER_RENDERABLE = 33417;
    public static final int GL_FRAMEBUFFER_RENDERABLE_LAYERED = 33418;
    public static final int GL_FRAMEBUFFER_BLEND = 33419;
    public static final int GL_READ_PIXELS = 33420;
    public static final int GL_READ_PIXELS_FORMAT = 33421;
    public static final int GL_READ_PIXELS_TYPE = 33422;
    public static final int GL_TEXTURE_IMAGE_FORMAT = 33423;
    public static final int GL_TEXTURE_IMAGE_TYPE = 33424;
    public static final int GL_GET_TEXTURE_IMAGE_FORMAT = 33425;
    public static final int GL_GET_TEXTURE_IMAGE_TYPE = 33426;
    public static final int GL_MIPMAP = 33427;
    public static final int GL_MANUAL_GENERATE_MIPMAP = 33428;
    public static final int GL_AUTO_GENERATE_MIPMAP = 33429;
    public static final int GL_COLOR_ENCODING = 33430;
    public static final int GL_SRGB_READ = 33431;
    public static final int GL_SRGB_WRITE = 33432;
    public static final int GL_FILTER = 33434;
    public static final int GL_VERTEX_TEXTURE = 33435;
    public static final int GL_TESS_CONTROL_TEXTURE = 33436;
    public static final int GL_TESS_EVALUATION_TEXTURE = 33437;
    public static final int GL_GEOMETRY_TEXTURE = 33438;
    public static final int GL_FRAGMENT_TEXTURE = 33439;
    public static final int GL_COMPUTE_TEXTURE = 33440;
    public static final int GL_TEXTURE_SHADOW = 33441;
    public static final int GL_TEXTURE_GATHER = 33442;
    public static final int GL_TEXTURE_GATHER_SHADOW = 33443;
    public static final int GL_SHADER_IMAGE_LOAD = 33444;
    public static final int GL_SHADER_IMAGE_STORE = 33445;
    public static final int GL_SHADER_IMAGE_ATOMIC = 33446;
    public static final int GL_IMAGE_TEXEL_SIZE = 33447;
    public static final int GL_IMAGE_COMPATIBILITY_CLASS = 33448;
    public static final int GL_IMAGE_PIXEL_FORMAT = 33449;
    public static final int GL_IMAGE_PIXEL_TYPE = 33450;
    public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_TEST = 33452;
    public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_TEST = 33453;
    public static final int GL_SIMULTANEOUS_TEXTURE_AND_DEPTH_WRITE = 33454;
    public static final int GL_SIMULTANEOUS_TEXTURE_AND_STENCIL_WRITE = 33455;
    public static final int GL_TEXTURE_COMPRESSED_BLOCK_WIDTH = 33457;
    public static final int GL_TEXTURE_COMPRESSED_BLOCK_HEIGHT = 33458;
    public static final int GL_TEXTURE_COMPRESSED_BLOCK_SIZE = 33459;
    public static final int GL_CLEAR_BUFFER = 33460;
    public static final int GL_TEXTURE_VIEW = 33461;
    public static final int GL_VIEW_COMPATIBILITY_CLASS = 33462;
    public static final int GL_FULL_SUPPORT = 33463;
    public static final int GL_CAVEAT_SUPPORT = 33464;
    public static final int GL_IMAGE_CLASS_4_X_32 = 33465;
    public static final int GL_IMAGE_CLASS_2_X_32 = 33466;
    public static final int GL_IMAGE_CLASS_1_X_32 = 33467;
    public static final int GL_IMAGE_CLASS_4_X_16 = 33468;
    public static final int GL_IMAGE_CLASS_2_X_16 = 33469;
    public static final int GL_IMAGE_CLASS_1_X_16 = 33470;
    public static final int GL_IMAGE_CLASS_4_X_8 = 33471;
    public static final int GL_IMAGE_CLASS_2_X_8 = 33472;
    public static final int GL_IMAGE_CLASS_1_X_8 = 33473;
    public static final int GL_IMAGE_CLASS_11_11_10 = 33474;
    public static final int GL_IMAGE_CLASS_10_10_10_2 = 33475;
    public static final int GL_VIEW_CLASS_128_BITS = 33476;
    public static final int GL_VIEW_CLASS_96_BITS = 33477;
    public static final int GL_VIEW_CLASS_64_BITS = 33478;
    public static final int GL_VIEW_CLASS_48_BITS = 33479;
    public static final int GL_VIEW_CLASS_32_BITS = 33480;
    public static final int GL_VIEW_CLASS_24_BITS = 33481;
    public static final int GL_VIEW_CLASS_16_BITS = 33482;
    public static final int GL_VIEW_CLASS_8_BITS = 33483;
    public static final int GL_VIEW_CLASS_S3TC_DXT1_RGB = 33484;
    public static final int GL_VIEW_CLASS_S3TC_DXT1_RGBA = 33485;
    public static final int GL_VIEW_CLASS_S3TC_DXT3_RGBA = 33486;
    public static final int GL_VIEW_CLASS_S3TC_DXT5_RGBA = 33487;
    public static final int GL_VIEW_CLASS_RGTC1_RED = 33488;
    public static final int GL_VIEW_CLASS_RGTC2_RG = 33489;
    public static final int GL_VIEW_CLASS_BPTC_UNORM = 33490;
    public static final int GL_VIEW_CLASS_BPTC_FLOAT = 33491;
    public static final int GL_UNIFORM = 37601;
    public static final int GL_UNIFORM_BLOCK = 37602;
    public static final int GL_PROGRAM_INPUT = 37603;
    public static final int GL_PROGRAM_OUTPUT = 37604;
    public static final int GL_BUFFER_VARIABLE = 37605;
    public static final int GL_SHADER_STORAGE_BLOCK = 37606;
    public static final int GL_VERTEX_SUBROUTINE = 37608;
    public static final int GL_TESS_CONTROL_SUBROUTINE = 37609;
    public static final int GL_TESS_EVALUATION_SUBROUTINE = 37610;
    public static final int GL_GEOMETRY_SUBROUTINE = 37611;
    public static final int GL_FRAGMENT_SUBROUTINE = 37612;
    public static final int GL_COMPUTE_SUBROUTINE = 37613;
    public static final int GL_VERTEX_SUBROUTINE_UNIFORM = 37614;
    public static final int GL_TESS_CONTROL_SUBROUTINE_UNIFORM = 37615;
    public static final int GL_TESS_EVALUATION_SUBROUTINE_UNIFORM = 37616;
    public static final int GL_GEOMETRY_SUBROUTINE_UNIFORM = 37617;
    public static final int GL_FRAGMENT_SUBROUTINE_UNIFORM = 37618;
    public static final int GL_COMPUTE_SUBROUTINE_UNIFORM = 37619;
    public static final int GL_TRANSFORM_FEEDBACK_VARYING = 37620;
    public static final int GL_ACTIVE_RESOURCES = 37621;
    public static final int GL_MAX_NAME_LENGTH = 37622;
    public static final int GL_MAX_NUM_ACTIVE_VARIABLES = 37623;
    public static final int GL_MAX_NUM_COMPATIBLE_SUBROUTINES = 37624;
    public static final int GL_NAME_LENGTH = 37625;
    public static final int GL_TYPE = 37626;
    public static final int GL_ARRAY_SIZE = 37627;
    public static final int GL_OFFSET = 37628;
    public static final int GL_BLOCK_INDEX = 37629;
    public static final int GL_ARRAY_STRIDE = 37630;
    public static final int GL_MATRIX_STRIDE = 37631;
    public static final int GL_IS_ROW_MAJOR = 37632;
    public static final int GL_ATOMIC_COUNTER_BUFFER_INDEX = 37633;
    public static final int GL_BUFFER_BINDING = 37634;
    public static final int GL_BUFFER_DATA_SIZE = 37635;
    public static final int GL_NUM_ACTIVE_VARIABLES = 37636;
    public static final int GL_ACTIVE_VARIABLES = 37637;
    public static final int GL_REFERENCED_BY_VERTEX_SHADER = 37638;
    public static final int GL_REFERENCED_BY_TESS_CONTROL_SHADER = 37639;
    public static final int GL_REFERENCED_BY_TESS_EVALUATION_SHADER = 37640;
    public static final int GL_REFERENCED_BY_GEOMETRY_SHADER = 37641;
    public static final int GL_REFERENCED_BY_FRAGMENT_SHADER = 37642;
    public static final int GL_REFERENCED_BY_COMPUTE_SHADER = 37643;
    public static final int GL_TOP_LEVEL_ARRAY_SIZE = 37644;
    public static final int GL_TOP_LEVEL_ARRAY_STRIDE = 37645;
    public static final int GL_LOCATION = 37646;
    public static final int GL_LOCATION_INDEX = 37647;
    public static final int GL_IS_PER_PATCH = 37607;
    public static final int GL_SHADER_STORAGE_BUFFER = 37074;
    public static final int GL_SHADER_STORAGE_BUFFER_BINDING = 37075;
    public static final int GL_SHADER_STORAGE_BUFFER_START = 37076;
    public static final int GL_SHADER_STORAGE_BUFFER_SIZE = 37077;
    public static final int GL_MAX_VERTEX_SHADER_STORAGE_BLOCKS = 37078;
    public static final int GL_MAX_GEOMETRY_SHADER_STORAGE_BLOCKS = 37079;
    public static final int GL_MAX_TESS_CONTROL_SHADER_STORAGE_BLOCKS = 37080;
    public static final int GL_MAX_TESS_EVALUATION_SHADER_STORAGE_BLOCKS = 37081;
    public static final int GL_MAX_FRAGMENT_SHADER_STORAGE_BLOCKS = 37082;
    public static final int GL_MAX_COMPUTE_SHADER_STORAGE_BLOCKS = 37083;
    public static final int GL_MAX_COMBINED_SHADER_STORAGE_BLOCKS = 37084;
    public static final int GL_MAX_SHADER_STORAGE_BUFFER_BINDINGS = 37085;
    public static final int GL_MAX_SHADER_STORAGE_BLOCK_SIZE = 37086;
    public static final int GL_SHADER_STORAGE_BUFFER_OFFSET_ALIGNMENT = 37087;
    public static final int GL_SHADER_STORAGE_BARRIER_BIT = 8192;
    public static final int GL_MAX_COMBINED_SHADER_OUTPUT_RESOURCES = 36665;
    public static final int GL_DEPTH_STENCIL_TEXTURE_MODE = 37098;
    public static final int GL_TEXTURE_BUFFER_OFFSET = 37277;
    public static final int GL_TEXTURE_BUFFER_SIZE = 37278;
    public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;
    public static final int GL_TEXTURE_VIEW_MIN_LEVEL = 33499;
    public static final int GL_TEXTURE_VIEW_NUM_LEVELS = 33500;
    public static final int GL_TEXTURE_VIEW_MIN_LAYER = 33501;
    public static final int GL_TEXTURE_VIEW_NUM_LAYERS = 33502;
    public static final int GL_VERTEX_ATTRIB_BINDING = 33492;
    public static final int GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 33493;
    public static final int GL_VERTEX_BINDING_DIVISOR = 33494;
    public static final int GL_VERTEX_BINDING_OFFSET = 33495;
    public static final int GL_VERTEX_BINDING_STRIDE = 33496;
    public static final int GL_VERTEX_BINDING_BUFFER = 36687;
    public static final int GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 33497;
    public static final int GL_MAX_VERTEX_ATTRIB_BINDINGS = 33498;

    public static native void nglClearBufferData(int i, int i2, int i3, int i4, long j);

    public static native void nglClearBufferSubData(int i, int i2, long j, long j2, int i3, int i4, long j3);

    public static native void glDispatchCompute(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glDispatchComputeIndirect(@NativeType("GLintptr") long j);

    public static native void glCopyImageSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLuint") int i7, @NativeType("GLenum") int i8, @NativeType("GLint") int i9, @NativeType("GLint") int i10, @NativeType("GLint") int i11, @NativeType("GLint") int i12, @NativeType("GLsizei") int i13, @NativeType("GLsizei") int i14, @NativeType("GLsizei") int i15);

    public static native void nglDebugMessageControl(int i, int i2, int i3, int i4, long j, boolean z);

    public static native void nglDebugMessageInsert(int i, int i2, int i3, int i4, int i5, long j);

    public static native void nglDebugMessageCallback(long j, long j2);

    public static native int nglGetDebugMessageLog(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6);

    public static native void nglPushDebugGroup(int i, int i2, int i3, long j);

    public static native void glPopDebugGroup();

    public static native void nglObjectLabel(int i, int i2, int i3, long j);

    public static native void nglGetObjectLabel(int i, int i2, int i3, long j, long j2);

    public static native void nglObjectPtrLabel(long j, int i, long j2);

    public static native void nglGetObjectPtrLabel(long j, int i, long j2, long j3);

    public static native void glFramebufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglGetFramebufferParameteriv(int i, int i2, long j);

    public static native void nglGetInternalformati64v(int i, int i2, int i3, int i4, long j);

    public static native void glInvalidateTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8);

    public static native void glInvalidateTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2);

    public static native void glInvalidateBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glInvalidateBufferData(@NativeType("GLuint") int i);

    public static native void nglInvalidateFramebuffer(int i, int i2, long j);

    public static native void nglInvalidateSubFramebuffer(int i, int i2, long j, int i3, int i4, int i5, int i6);

    public static native void nglMultiDrawArraysIndirect(int i, long j, int i2, int i3);

    public static native void nglMultiDrawElementsIndirect(int i, int i2, long j, int i3, int i4);

    public static native void nglGetProgramInterfaceiv(int i, int i2, int i3, long j);

    public static native int nglGetProgramResourceIndex(int i, int i2, long j);

    public static native void nglGetProgramResourceName(int i, int i2, int i3, int i4, long j, long j2);

    public static native void nglGetProgramResourceiv(int i, int i2, int i3, int i4, long j, int i5, long j2, long j3);

    public static native int nglGetProgramResourceLocation(int i, int i2, long j);

    public static native int nglGetProgramResourceLocationIndex(int i, int i2, long j);

    public static native void glShaderStorageBlockBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glTexBufferRange(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    public static native void glTexStorage2DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z);

    public static native void glTexStorage3DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z);

    public static native void glTextureView(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6, @NativeType("GLuint") int i7, @NativeType("GLuint") int i8);

    public static native void glBindVertexBuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3);

    public static native void glVertexAttribFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i4);

    public static native void glVertexAttribIFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    public static native void glVertexAttribLFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4);

    public static native void glVertexAttribBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glVertexBindingDivisor(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL43C() {
        throw new UnsupportedOperationException();
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearBufferData(i, i2, i3, i4, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglClearBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglClearBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglClearBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglClearBufferSubData(i, i2, j, j2, i3, i4, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLboolean") boolean z) {
        nglDebugMessageControl(i, i2, i3, Checks.remainingSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer), z);
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int i4, @NativeType("GLboolean") boolean z) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDebugMessageControl(i, i2, i3, 1, MemoryUtil.memAddress(stackGet.ints(i4)), z);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDebugMessageInsert(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglDebugMessageInsert(i, i2, i3, i4, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDebugMessageInsert(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDebugMessageInsert(i, i2, i3, i4, stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDebugMessageCallback(@NativeType("GLDEBUGPROC") GLDebugMessageCallbackI gLDebugMessageCallbackI, @NativeType("void const *") long j) {
        nglDebugMessageCallback(MemoryUtil.memAddressSafe(gLDebugMessageCallbackI), j);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLog(@NativeType("GLuint") int i, @NativeType("GLenum *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3, @NativeType("GLenum *") IntBuffer intBuffer4, @NativeType("GLsizei *") IntBuffer intBuffer5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, i);
            Checks.checkSafe((Buffer) intBuffer2, i);
            Checks.checkSafe((Buffer) intBuffer3, i);
            Checks.checkSafe((Buffer) intBuffer4, i);
            Checks.checkSafe((Buffer) intBuffer5, i);
        }
        return nglGetDebugMessageLog(i, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4), MemoryUtil.memAddressSafe(intBuffer5), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glPushDebugGroup(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglPushDebugGroup(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glPushDebugGroup(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglPushDebugGroup(i, i2, stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglObjectLabel(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglObjectLabel(i, i2, stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetObjectLabel(i, i2, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetObjectLabel(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memUTF8(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return glGetObjectLabel(i, i2, GL11.glGetInteger(33512));
    }

    public static void glObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nglObjectPtrLabel(j, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLchar const *") CharSequence charSequence) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglObjectPtrLabel(j, stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetObjectPtrLabel(j, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei") int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i);
            nglGetObjectPtrLabel(j, i, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memUTF8(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetObjectPtrLabel(@NativeType("void *") long j) {
        return glGetObjectPtrLabel(j, GL11.glGetInteger(33512));
    }

    public static void glGetFramebufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetFramebufferParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetFramebufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetFramebufferParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetInternalformati64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") LongBuffer longBuffer) {
        nglGetInternalformati64v(i, i2, i3, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("void")
    public static long glGetInternalformati64(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nglGetInternalformati64v(i, i2, i3, 1, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        nglInvalidateFramebuffer(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglInvalidateFramebuffer(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        nglInvalidateSubFramebuffer(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), i2, i3, i4, i5);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglInvalidateSubFramebuffer(i, 1, MemoryUtil.memAddress(stackGet.ints(i2)), i3, i4, i5, i6);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i2 * (i3 == 0 ? 16 : i3));
        }
        nglMultiDrawArraysIndirect(i, MemoryUtil.memAddress(byteBuffer), i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        nglMultiDrawArraysIndirect(i, j, i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, (i2 * (i3 == 0 ? 16 : i3)) >> 2);
        }
        nglMultiDrawArraysIndirect(i, MemoryUtil.memAddress(intBuffer), i2, i3);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i3 * (i4 == 0 ? 20 : i4));
        }
        nglMultiDrawElementsIndirect(i, i2, MemoryUtil.memAddress(byteBuffer), i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        nglMultiDrawElementsIndirect(i, i2, j, i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, (i3 * (i4 == 0 ? 20 : i4)) >> 2);
        }
        nglMultiDrawElementsIndirect(i, i2, MemoryUtil.memAddress(intBuffer), i3, i4);
    }

    public static void glGetProgramInterfaceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetProgramInterfaceiv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetProgramInterfacei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetProgramInterfaceiv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLuint")
    public static int glGetProgramResourceIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetProgramResourceIndex(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLuint")
    public static int glGetProgramResourceIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nglGetProgramResourceIndex(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
        }
        nglGetProgramResourceName(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i4);
            nglGetProgramResourceName(i, i2, i3, i4, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memASCII(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("void")
    public static String glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return glGetProgramResourceName(i, i2, i3, glGetProgramInterfacei(i, i2, 37622));
    }

    public static void glGetProgramResourceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLint *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglGetProgramResourceiv(i, i2, i3, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), intBuffer3.remaining(), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddress(intBuffer3));
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetProgramResourceLocation(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetProgramResourceLocation(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocationIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetProgramResourceLocationIndex(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocationIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetProgramResourceLocationIndex(i, i2, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glClearBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glClearBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glClearBufferData;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j3 = GL.getICD().glClearBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, sArr, j3);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j3 = GL.getICD().glClearBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, iArr, j3);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j3 = GL.getICD().glClearBufferSubData;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.callPPPV(i, i2, j, j2, i3, i4, fArr, j3);
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int[] iArr, @NativeType("GLboolean") boolean z) {
        long j = GL.getICD().glDebugMessageControl;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, Checks.lengthSafe(iArr), iArr, z, j);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLog(@NativeType("GLuint") int i, @NativeType("GLenum *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("GLuint *") int[] iArr3, @NativeType("GLenum *") int[] iArr4, @NativeType("GLsizei *") int[] iArr5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetDebugMessageLog;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, i);
            Checks.checkSafe(iArr2, i);
            Checks.checkSafe(iArr3, i);
            Checks.checkSafe(iArr4, i);
            Checks.checkSafe(iArr5, i);
        }
        return JNI.callPPPPPPI(i, Checks.remainingSafe(byteBuffer), iArr, iArr2, iArr3, iArr4, iArr5, MemoryUtil.memAddressSafe(byteBuffer), j);
    }

    public static void glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetObjectLabel;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, i2, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j2 = GL.getICD().glGetObjectPtrLabel;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPPV(j, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j2);
    }

    public static void glGetFramebufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetFramebufferParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetInternalformati64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") long[] jArr) {
        long j = GL.getICD().glGetInternalformati64v;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, jArr.length, jArr, j);
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int[] iArr) {
        long j = GL.getICD().glInvalidateFramebuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int[] iArr, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        long j = GL.getICD().glInvalidateSubFramebuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, i2, i3, i4, i5, j);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        long j = GL.getICD().glMultiDrawArraysIndirect;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, (i2 * (i3 == 0 ? 16 : i3)) >> 2);
        }
        JNI.callPV(i, iArr, i2, i3, j);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        long j = GL.getICD().glMultiDrawElementsIndirect;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, (i3 * (i4 == 0 ? 20 : i4)) >> 2);
        }
        JNI.callPV(i, i2, iArr, i3, i4, j);
    }

    public static void glGetProgramInterfaceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetProgramInterfaceiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetProgramResourceName;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
        }
        JNI.callPPV(i, i2, i3, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glGetProgramResourceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLint *") int[] iArr3) {
        long j = GL.getICD().glGetProgramResourceiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.callPPPV(i, i2, i3, iArr.length, iArr, iArr3.length, iArr2, iArr3, j);
    }
}
