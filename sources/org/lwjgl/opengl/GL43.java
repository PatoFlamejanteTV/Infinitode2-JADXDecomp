package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL43.class */
public class GL43 extends GL42 {
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
    public static final int GL_DISPLAY_LIST = 33511;
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

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL43() {
        throw new UnsupportedOperationException();
    }

    public static void nglClearBufferData(int i, int i2, int i3, int i4, long j) {
        GL43C.nglClearBufferData(i, i2, i3, i4, j);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, byteBuffer);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, shortBuffer);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, intBuffer);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL43C.glClearBufferData(i, i2, i3, i4, floatBuffer);
    }

    public static void nglClearBufferSubData(int i, int i2, long j, long j2, int i3, int i4, long j3) {
        GL43C.nglClearBufferSubData(i, i2, j, j2, i3, i4, j3);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, byteBuffer);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, shortBuffer);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, intBuffer);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, floatBuffer);
    }

    public static void glDispatchCompute(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL43C.glDispatchCompute(i, i2, i3);
    }

    public static void glDispatchComputeIndirect(@NativeType("GLintptr") long j) {
        GL43C.glDispatchComputeIndirect(j);
    }

    public static void glCopyImageSubData(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLuint") int i7, @NativeType("GLenum") int i8, @NativeType("GLint") int i9, @NativeType("GLint") int i10, @NativeType("GLint") int i11, @NativeType("GLint") int i12, @NativeType("GLsizei") int i13, @NativeType("GLsizei") int i14, @NativeType("GLsizei") int i15) {
        GL43C.glCopyImageSubData(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15);
    }

    public static void nglDebugMessageControl(int i, int i2, int i3, int i4, long j, boolean z) {
        GL43C.nglDebugMessageControl(i, i2, i3, i4, j, z);
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLboolean") boolean z) {
        GL43C.glDebugMessageControl(i, i2, i3, intBuffer, z);
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int i4, @NativeType("GLboolean") boolean z) {
        GL43C.glDebugMessageControl(i, i2, i3, i4, z);
    }

    public static void nglDebugMessageInsert(int i, int i2, int i3, int i4, int i5, long j) {
        GL43C.nglDebugMessageInsert(i, i2, i3, i4, i5, j);
    }

    public static void glDebugMessageInsert(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glDebugMessageInsert(i, i2, i3, i4, byteBuffer);
    }

    public static void glDebugMessageInsert(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glDebugMessageInsert(i, i2, i3, i4, charSequence);
    }

    public static void nglDebugMessageCallback(long j, long j2) {
        GL43C.nglDebugMessageCallback(j, j2);
    }

    public static void glDebugMessageCallback(@NativeType("GLDEBUGPROC") GLDebugMessageCallbackI gLDebugMessageCallbackI, @NativeType("void const *") long j) {
        GL43C.glDebugMessageCallback(gLDebugMessageCallbackI, j);
    }

    public static int nglGetDebugMessageLog(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6) {
        return GL43C.nglGetDebugMessageLog(i, i2, j, j2, j3, j4, j5, j6);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLog(@NativeType("GLuint") int i, @NativeType("GLenum *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3, @NativeType("GLenum *") IntBuffer intBuffer4, @NativeType("GLsizei *") IntBuffer intBuffer5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        return GL43C.glGetDebugMessageLog(i, intBuffer, intBuffer2, intBuffer3, intBuffer4, intBuffer5, byteBuffer);
    }

    public static void nglPushDebugGroup(int i, int i2, int i3, long j) {
        GL43C.nglPushDebugGroup(i, i2, i3, j);
    }

    public static void glPushDebugGroup(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glPushDebugGroup(i, i2, byteBuffer);
    }

    public static void glPushDebugGroup(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glPushDebugGroup(i, i2, charSequence);
    }

    public static void glPopDebugGroup() {
        GL43C.glPopDebugGroup();
    }

    public static void nglObjectLabel(int i, int i2, int i3, long j) {
        GL43C.nglObjectLabel(i, i2, i3, j);
    }

    public static void glObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glObjectLabel(i, i2, byteBuffer);
    }

    public static void glObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glObjectLabel(i, i2, charSequence);
    }

    public static void nglGetObjectLabel(int i, int i2, int i3, long j, long j2) {
        GL43C.nglGetObjectLabel(i, i2, i3, j, j2);
    }

    public static void glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectLabel(i, i2, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        return GL43C.glGetObjectLabel(i, i2, i3);
    }

    @NativeType("void")
    public static String glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return glGetObjectLabel(i, i2, GL11.glGetInteger(33512));
    }

    public static void nglObjectPtrLabel(long j, int i, long j2) {
        GL43C.nglObjectPtrLabel(j, i, j2);
    }

    public static void glObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glObjectPtrLabel(j, byteBuffer);
    }

    public static void glObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glObjectPtrLabel(j, charSequence);
    }

    public static void nglGetObjectPtrLabel(long j, int i, long j2, long j3) {
        GL43C.nglGetObjectPtrLabel(j, i, j2, j3);
    }

    public static void glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectPtrLabel(j, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei") int i) {
        return GL43C.glGetObjectPtrLabel(j, i);
    }

    @NativeType("void")
    public static String glGetObjectPtrLabel(@NativeType("void *") long j) {
        return glGetObjectPtrLabel(j, GL11.glGetInteger(33512));
    }

    public static void glFramebufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL43C.glFramebufferParameteri(i, i2, i3);
    }

    public static void nglGetFramebufferParameteriv(int i, int i2, long j) {
        GL43C.nglGetFramebufferParameteriv(i, i2, j);
    }

    public static void glGetFramebufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL43C.glGetFramebufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetFramebufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL43C.glGetFramebufferParameteri(i, i2);
    }

    public static void nglGetInternalformati64v(int i, int i2, int i3, int i4, long j) {
        GL43C.nglGetInternalformati64v(i, i2, i3, i4, j);
    }

    public static void glGetInternalformati64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") LongBuffer longBuffer) {
        GL43C.glGetInternalformati64v(i, i2, i3, longBuffer);
    }

    @NativeType("void")
    public static long glGetInternalformati64(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL43C.glGetInternalformati64(i, i2, i3);
    }

    public static void glInvalidateTexSubImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8) {
        GL43C.glInvalidateTexSubImage(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static void glInvalidateTexImage(@NativeType("GLuint") int i, @NativeType("GLint") int i2) {
        GL43C.glInvalidateTexImage(i, i2);
    }

    public static void glInvalidateBufferSubData(@NativeType("GLuint") int i, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL43C.glInvalidateBufferSubData(i, j, j2);
    }

    public static void glInvalidateBufferData(@NativeType("GLuint") int i) {
        GL43C.glInvalidateBufferData(i);
    }

    public static void nglInvalidateFramebuffer(int i, int i2, long j) {
        GL43C.nglInvalidateFramebuffer(i, i2, j);
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") IntBuffer intBuffer) {
        GL43C.glInvalidateFramebuffer(i, intBuffer);
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int i2) {
        GL43C.glInvalidateFramebuffer(i, i2);
    }

    public static void nglInvalidateSubFramebuffer(int i, int i2, long j, int i3, int i4, int i5, int i6) {
        GL43C.nglInvalidateSubFramebuffer(i, i2, j, i3, i4, i5, i6);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL43C.glInvalidateSubFramebuffer(i, intBuffer, i2, i3, i4, i5);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6) {
        GL43C.glInvalidateSubFramebuffer(i, i2, i3, i4, i5, i6);
    }

    public static void nglMultiDrawArraysIndirect(int i, long j, int i2, int i3) {
        GL43C.nglMultiDrawArraysIndirect(i, j, i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, byteBuffer, i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") long j, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, j, i2, i3);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, intBuffer, i2, i3);
    }

    public static void nglMultiDrawElementsIndirect(int i, int i2, long j, int i3, int i4) {
        GL43C.nglMultiDrawElementsIndirect(i, i2, j, i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, byteBuffer, i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, j, i3, i4);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") IntBuffer intBuffer, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, intBuffer, i3, i4);
    }

    public static void nglGetProgramInterfaceiv(int i, int i2, int i3, long j) {
        GL43C.nglGetProgramInterfaceiv(i, i2, i3, j);
    }

    public static void glGetProgramInterfaceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL43C.glGetProgramInterfaceiv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetProgramInterfacei(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL43C.glGetProgramInterfacei(i, i2, i3);
    }

    public static int nglGetProgramResourceIndex(int i, int i2, long j) {
        return GL43C.nglGetProgramResourceIndex(i, i2, j);
    }

    @NativeType("GLuint")
    public static int glGetProgramResourceIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL43C.glGetProgramResourceIndex(i, i2, byteBuffer);
    }

    @NativeType("GLuint")
    public static int glGetProgramResourceIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL43C.glGetProgramResourceIndex(i, i2, charSequence);
    }

    public static void nglGetProgramResourceName(int i, int i2, int i3, int i4, long j, long j2) {
        GL43C.nglGetProgramResourceName(i, i2, i3, i4, j, j2);
    }

    public static void glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetProgramResourceName(i, i2, i3, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4) {
        return GL43C.glGetProgramResourceName(i, i2, i3, i4);
    }

    @NativeType("void")
    public static String glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        return glGetProgramResourceName(i, i2, i3, glGetProgramInterfacei(i, i2, 37622));
    }

    public static void nglGetProgramResourceiv(int i, int i2, int i3, int i4, long j, int i5, long j2, long j3) {
        GL43C.nglGetProgramResourceiv(i, i2, i3, i4, j, i5, j2, j3);
    }

    public static void glGetProgramResourceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLint *") IntBuffer intBuffer3) {
        GL43C.glGetProgramResourceiv(i, i2, i3, intBuffer, intBuffer2, intBuffer3);
    }

    public static int nglGetProgramResourceLocation(int i, int i2, long j) {
        return GL43C.nglGetProgramResourceLocation(i, i2, j);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL43C.glGetProgramResourceLocation(i, i2, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocation(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL43C.glGetProgramResourceLocation(i, i2, charSequence);
    }

    public static int nglGetProgramResourceLocationIndex(int i, int i2, long j) {
        return GL43C.nglGetProgramResourceLocationIndex(i, i2, j);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocationIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        return GL43C.glGetProgramResourceLocationIndex(i, i2, byteBuffer);
    }

    @NativeType("GLint")
    public static int glGetProgramResourceLocationIndex(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        return GL43C.glGetProgramResourceLocationIndex(i, i2, charSequence);
    }

    public static void glShaderStorageBlockBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3) {
        GL43C.glShaderStorageBlockBinding(i, i2, i3);
    }

    public static void glTexBufferRange(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL43C.glTexBufferRange(i, i2, i3, j, j2);
    }

    public static void glTexStorage2DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z) {
        GL43C.glTexStorage2DMultisample(i, i2, i3, i4, i5, z);
    }

    public static void glTexStorage3DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z) {
        GL43C.glTexStorage3DMultisample(i, i2, i3, i4, i5, i6, z);
    }

    public static void glTextureView(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5, @NativeType("GLuint") int i6, @NativeType("GLuint") int i7, @NativeType("GLuint") int i8) {
        GL43C.glTextureView(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static void glBindVertexBuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3) {
        GL43C.glBindVertexBuffer(i, i2, j, i3);
    }

    public static void glVertexAttribFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i4) {
        GL43C.glVertexAttribFormat(i, i2, i3, z, i4);
    }

    public static void glVertexAttribIFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4) {
        GL43C.glVertexAttribIFormat(i, i2, i3, i4);
    }

    public static void glVertexAttribLFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4) {
        GL43C.glVertexAttribLFormat(i, i2, i3, i4);
    }

    public static void glVertexAttribBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL43C.glVertexAttribBinding(i, i2);
    }

    public static void glVertexBindingDivisor(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL43C.glVertexBindingDivisor(i, i2);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        GL43C.glClearBufferData(i, i2, i3, i4, sArr);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        GL43C.glClearBufferData(i, i2, i3, i4, iArr);
    }

    public static void glClearBufferData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        GL43C.glClearBufferData(i, i2, i3, i4, fArr);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, sArr);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, iArr);
    }

    public static void glClearBufferSubData(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        GL43C.glClearBufferSubData(i, i2, j, j2, i3, i4, fArr);
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int[] iArr, @NativeType("GLboolean") boolean z) {
        GL43C.glDebugMessageControl(i, i2, i3, iArr, z);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLog(@NativeType("GLuint") int i, @NativeType("GLenum *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("GLuint *") int[] iArr3, @NativeType("GLenum *") int[] iArr4, @NativeType("GLsizei *") int[] iArr5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        return GL43C.glGetDebugMessageLog(i, iArr, iArr2, iArr3, iArr4, iArr5, byteBuffer);
    }

    public static void glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectLabel(i, i2, iArr, byteBuffer);
    }

    public static void glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectPtrLabel(j, iArr, byteBuffer);
    }

    public static void glGetFramebufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL43C.glGetFramebufferParameteriv(i, i2, iArr);
    }

    public static void glGetInternalformati64v(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint64 *") long[] jArr) {
        GL43C.glGetInternalformati64v(i, i2, i3, jArr);
    }

    public static void glInvalidateFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int[] iArr) {
        GL43C.glInvalidateFramebuffer(i, iArr);
    }

    public static void glInvalidateSubFramebuffer(@NativeType("GLenum") int i, @NativeType("GLenum const *") int[] iArr, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL43C.glInvalidateSubFramebuffer(i, iArr, i2, i3, i4, i5);
    }

    public static void glMultiDrawArraysIndirect(@NativeType("GLenum") int i, @NativeType("void const *") int[] iArr, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3) {
        GL43C.glMultiDrawArraysIndirect(i, iArr, i2, i3);
    }

    public static void glMultiDrawElementsIndirect(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") int[] iArr, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL43C.glMultiDrawElementsIndirect(i, i2, iArr, i3, i4);
    }

    public static void glGetProgramInterfaceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL43C.glGetProgramInterfaceiv(i, i2, i3, iArr);
    }

    public static void glGetProgramResourceName(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetProgramResourceName(i, i2, i3, iArr, byteBuffer);
    }

    public static void glGetProgramResourceiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLint *") int[] iArr3) {
        GL43C.glGetProgramResourceiv(i, i2, i3, iArr, iArr2, iArr3);
    }
}
