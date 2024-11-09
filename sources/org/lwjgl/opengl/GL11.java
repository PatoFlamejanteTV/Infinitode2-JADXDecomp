package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL11.class */
public class GL11 {
    public static final int GL_ACCUM = 256;
    public static final int GL_LOAD = 257;
    public static final int GL_RETURN = 258;
    public static final int GL_MULT = 259;
    public static final int GL_ADD = 260;
    public static final int GL_NEVER = 512;
    public static final int GL_LESS = 513;
    public static final int GL_EQUAL = 514;
    public static final int GL_LEQUAL = 515;
    public static final int GL_GREATER = 516;
    public static final int GL_NOTEQUAL = 517;
    public static final int GL_GEQUAL = 518;
    public static final int GL_ALWAYS = 519;
    public static final int GL_CURRENT_BIT = 1;
    public static final int GL_POINT_BIT = 2;
    public static final int GL_LINE_BIT = 4;
    public static final int GL_POLYGON_BIT = 8;
    public static final int GL_POLYGON_STIPPLE_BIT = 16;
    public static final int GL_PIXEL_MODE_BIT = 32;
    public static final int GL_LIGHTING_BIT = 64;
    public static final int GL_FOG_BIT = 128;
    public static final int GL_DEPTH_BUFFER_BIT = 256;
    public static final int GL_ACCUM_BUFFER_BIT = 512;
    public static final int GL_STENCIL_BUFFER_BIT = 1024;
    public static final int GL_VIEWPORT_BIT = 2048;
    public static final int GL_TRANSFORM_BIT = 4096;
    public static final int GL_ENABLE_BIT = 8192;
    public static final int GL_COLOR_BUFFER_BIT = 16384;
    public static final int GL_HINT_BIT = 32768;
    public static final int GL_EVAL_BIT = 65536;
    public static final int GL_LIST_BIT = 131072;
    public static final int GL_TEXTURE_BIT = 262144;
    public static final int GL_SCISSOR_BIT = 524288;
    public static final int GL_ALL_ATTRIB_BITS = 1048575;
    public static final int GL_POINTS = 0;
    public static final int GL_LINES = 1;
    public static final int GL_LINE_LOOP = 2;
    public static final int GL_LINE_STRIP = 3;
    public static final int GL_TRIANGLES = 4;
    public static final int GL_TRIANGLE_STRIP = 5;
    public static final int GL_TRIANGLE_FAN = 6;
    public static final int GL_QUADS = 7;
    public static final int GL_QUAD_STRIP = 8;
    public static final int GL_POLYGON = 9;
    public static final int GL_ZERO = 0;
    public static final int GL_ONE = 1;
    public static final int GL_SRC_COLOR = 768;
    public static final int GL_ONE_MINUS_SRC_COLOR = 769;
    public static final int GL_SRC_ALPHA = 770;
    public static final int GL_ONE_MINUS_SRC_ALPHA = 771;
    public static final int GL_DST_ALPHA = 772;
    public static final int GL_ONE_MINUS_DST_ALPHA = 773;
    public static final int GL_DST_COLOR = 774;
    public static final int GL_ONE_MINUS_DST_COLOR = 775;
    public static final int GL_SRC_ALPHA_SATURATE = 776;
    public static final int GL_TRUE = 1;
    public static final int GL_FALSE = 0;
    public static final int GL_CLIP_PLANE0 = 12288;
    public static final int GL_CLIP_PLANE1 = 12289;
    public static final int GL_CLIP_PLANE2 = 12290;
    public static final int GL_CLIP_PLANE3 = 12291;
    public static final int GL_CLIP_PLANE4 = 12292;
    public static final int GL_CLIP_PLANE5 = 12293;
    public static final int GL_BYTE = 5120;
    public static final int GL_UNSIGNED_BYTE = 5121;
    public static final int GL_SHORT = 5122;
    public static final int GL_UNSIGNED_SHORT = 5123;
    public static final int GL_INT = 5124;
    public static final int GL_UNSIGNED_INT = 5125;
    public static final int GL_FLOAT = 5126;
    public static final int GL_2_BYTES = 5127;
    public static final int GL_3_BYTES = 5128;
    public static final int GL_4_BYTES = 5129;
    public static final int GL_DOUBLE = 5130;
    public static final int GL_NONE = 0;
    public static final int GL_FRONT_LEFT = 1024;
    public static final int GL_FRONT_RIGHT = 1025;
    public static final int GL_BACK_LEFT = 1026;
    public static final int GL_BACK_RIGHT = 1027;
    public static final int GL_FRONT = 1028;
    public static final int GL_BACK = 1029;
    public static final int GL_LEFT = 1030;
    public static final int GL_RIGHT = 1031;
    public static final int GL_FRONT_AND_BACK = 1032;
    public static final int GL_AUX0 = 1033;
    public static final int GL_AUX1 = 1034;
    public static final int GL_AUX2 = 1035;
    public static final int GL_AUX3 = 1036;
    public static final int GL_NO_ERROR = 0;
    public static final int GL_INVALID_ENUM = 1280;
    public static final int GL_INVALID_VALUE = 1281;
    public static final int GL_INVALID_OPERATION = 1282;
    public static final int GL_STACK_OVERFLOW = 1283;
    public static final int GL_STACK_UNDERFLOW = 1284;
    public static final int GL_OUT_OF_MEMORY = 1285;
    public static final int GL_2D = 1536;
    public static final int GL_3D = 1537;
    public static final int GL_3D_COLOR = 1538;
    public static final int GL_3D_COLOR_TEXTURE = 1539;
    public static final int GL_4D_COLOR_TEXTURE = 1540;
    public static final int GL_PASS_THROUGH_TOKEN = 1792;
    public static final int GL_POINT_TOKEN = 1793;
    public static final int GL_LINE_TOKEN = 1794;
    public static final int GL_POLYGON_TOKEN = 1795;
    public static final int GL_BITMAP_TOKEN = 1796;
    public static final int GL_DRAW_PIXEL_TOKEN = 1797;
    public static final int GL_COPY_PIXEL_TOKEN = 1798;
    public static final int GL_LINE_RESET_TOKEN = 1799;
    public static final int GL_EXP = 2048;
    public static final int GL_EXP2 = 2049;
    public static final int GL_CW = 2304;
    public static final int GL_CCW = 2305;
    public static final int GL_COEFF = 2560;
    public static final int GL_ORDER = 2561;
    public static final int GL_DOMAIN = 2562;
    public static final int GL_CURRENT_COLOR = 2816;
    public static final int GL_CURRENT_INDEX = 2817;
    public static final int GL_CURRENT_NORMAL = 2818;
    public static final int GL_CURRENT_TEXTURE_COORDS = 2819;
    public static final int GL_CURRENT_RASTER_COLOR = 2820;
    public static final int GL_CURRENT_RASTER_INDEX = 2821;
    public static final int GL_CURRENT_RASTER_TEXTURE_COORDS = 2822;
    public static final int GL_CURRENT_RASTER_POSITION = 2823;
    public static final int GL_CURRENT_RASTER_POSITION_VALID = 2824;
    public static final int GL_CURRENT_RASTER_DISTANCE = 2825;
    public static final int GL_POINT_SMOOTH = 2832;
    public static final int GL_POINT_SIZE = 2833;
    public static final int GL_POINT_SIZE_RANGE = 2834;
    public static final int GL_POINT_SIZE_GRANULARITY = 2835;
    public static final int GL_LINE_SMOOTH = 2848;
    public static final int GL_LINE_WIDTH = 2849;
    public static final int GL_LINE_WIDTH_RANGE = 2850;
    public static final int GL_LINE_WIDTH_GRANULARITY = 2851;
    public static final int GL_LINE_STIPPLE = 2852;
    public static final int GL_LINE_STIPPLE_PATTERN = 2853;
    public static final int GL_LINE_STIPPLE_REPEAT = 2854;
    public static final int GL_LIST_MODE = 2864;
    public static final int GL_MAX_LIST_NESTING = 2865;
    public static final int GL_LIST_BASE = 2866;
    public static final int GL_LIST_INDEX = 2867;
    public static final int GL_POLYGON_MODE = 2880;
    public static final int GL_POLYGON_SMOOTH = 2881;
    public static final int GL_POLYGON_STIPPLE = 2882;
    public static final int GL_EDGE_FLAG = 2883;
    public static final int GL_CULL_FACE = 2884;
    public static final int GL_CULL_FACE_MODE = 2885;
    public static final int GL_FRONT_FACE = 2886;
    public static final int GL_LIGHTING = 2896;
    public static final int GL_LIGHT_MODEL_LOCAL_VIEWER = 2897;
    public static final int GL_LIGHT_MODEL_TWO_SIDE = 2898;
    public static final int GL_LIGHT_MODEL_AMBIENT = 2899;
    public static final int GL_SHADE_MODEL = 2900;
    public static final int GL_COLOR_MATERIAL_FACE = 2901;
    public static final int GL_COLOR_MATERIAL_PARAMETER = 2902;
    public static final int GL_COLOR_MATERIAL = 2903;
    public static final int GL_FOG = 2912;
    public static final int GL_FOG_INDEX = 2913;
    public static final int GL_FOG_DENSITY = 2914;
    public static final int GL_FOG_START = 2915;
    public static final int GL_FOG_END = 2916;
    public static final int GL_FOG_MODE = 2917;
    public static final int GL_FOG_COLOR = 2918;
    public static final int GL_DEPTH_RANGE = 2928;
    public static final int GL_DEPTH_TEST = 2929;
    public static final int GL_DEPTH_WRITEMASK = 2930;
    public static final int GL_DEPTH_CLEAR_VALUE = 2931;
    public static final int GL_DEPTH_FUNC = 2932;
    public static final int GL_ACCUM_CLEAR_VALUE = 2944;
    public static final int GL_STENCIL_TEST = 2960;
    public static final int GL_STENCIL_CLEAR_VALUE = 2961;
    public static final int GL_STENCIL_FUNC = 2962;
    public static final int GL_STENCIL_VALUE_MASK = 2963;
    public static final int GL_STENCIL_FAIL = 2964;
    public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
    public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
    public static final int GL_STENCIL_REF = 2967;
    public static final int GL_STENCIL_WRITEMASK = 2968;
    public static final int GL_MATRIX_MODE = 2976;
    public static final int GL_NORMALIZE = 2977;
    public static final int GL_VIEWPORT = 2978;
    public static final int GL_MODELVIEW_STACK_DEPTH = 2979;
    public static final int GL_PROJECTION_STACK_DEPTH = 2980;
    public static final int GL_TEXTURE_STACK_DEPTH = 2981;
    public static final int GL_MODELVIEW_MATRIX = 2982;
    public static final int GL_PROJECTION_MATRIX = 2983;
    public static final int GL_TEXTURE_MATRIX = 2984;
    public static final int GL_ATTRIB_STACK_DEPTH = 2992;
    public static final int GL_CLIENT_ATTRIB_STACK_DEPTH = 2993;
    public static final int GL_ALPHA_TEST = 3008;
    public static final int GL_ALPHA_TEST_FUNC = 3009;
    public static final int GL_ALPHA_TEST_REF = 3010;
    public static final int GL_DITHER = 3024;
    public static final int GL_BLEND_DST = 3040;
    public static final int GL_BLEND_SRC = 3041;
    public static final int GL_BLEND = 3042;
    public static final int GL_LOGIC_OP_MODE = 3056;
    public static final int GL_INDEX_LOGIC_OP = 3057;
    public static final int GL_LOGIC_OP = 3057;
    public static final int GL_COLOR_LOGIC_OP = 3058;
    public static final int GL_AUX_BUFFERS = 3072;
    public static final int GL_DRAW_BUFFER = 3073;
    public static final int GL_READ_BUFFER = 3074;
    public static final int GL_SCISSOR_BOX = 3088;
    public static final int GL_SCISSOR_TEST = 3089;
    public static final int GL_INDEX_CLEAR_VALUE = 3104;
    public static final int GL_INDEX_WRITEMASK = 3105;
    public static final int GL_COLOR_CLEAR_VALUE = 3106;
    public static final int GL_COLOR_WRITEMASK = 3107;
    public static final int GL_INDEX_MODE = 3120;
    public static final int GL_RGBA_MODE = 3121;
    public static final int GL_DOUBLEBUFFER = 3122;
    public static final int GL_STEREO = 3123;
    public static final int GL_RENDER_MODE = 3136;
    public static final int GL_PERSPECTIVE_CORRECTION_HINT = 3152;
    public static final int GL_POINT_SMOOTH_HINT = 3153;
    public static final int GL_LINE_SMOOTH_HINT = 3154;
    public static final int GL_POLYGON_SMOOTH_HINT = 3155;
    public static final int GL_FOG_HINT = 3156;
    public static final int GL_TEXTURE_GEN_S = 3168;
    public static final int GL_TEXTURE_GEN_T = 3169;
    public static final int GL_TEXTURE_GEN_R = 3170;
    public static final int GL_TEXTURE_GEN_Q = 3171;
    public static final int GL_PIXEL_MAP_I_TO_I = 3184;
    public static final int GL_PIXEL_MAP_S_TO_S = 3185;
    public static final int GL_PIXEL_MAP_I_TO_R = 3186;
    public static final int GL_PIXEL_MAP_I_TO_G = 3187;
    public static final int GL_PIXEL_MAP_I_TO_B = 3188;
    public static final int GL_PIXEL_MAP_I_TO_A = 3189;
    public static final int GL_PIXEL_MAP_R_TO_R = 3190;
    public static final int GL_PIXEL_MAP_G_TO_G = 3191;
    public static final int GL_PIXEL_MAP_B_TO_B = 3192;
    public static final int GL_PIXEL_MAP_A_TO_A = 3193;
    public static final int GL_PIXEL_MAP_I_TO_I_SIZE = 3248;
    public static final int GL_PIXEL_MAP_S_TO_S_SIZE = 3249;
    public static final int GL_PIXEL_MAP_I_TO_R_SIZE = 3250;
    public static final int GL_PIXEL_MAP_I_TO_G_SIZE = 3251;
    public static final int GL_PIXEL_MAP_I_TO_B_SIZE = 3252;
    public static final int GL_PIXEL_MAP_I_TO_A_SIZE = 3253;
    public static final int GL_PIXEL_MAP_R_TO_R_SIZE = 3254;
    public static final int GL_PIXEL_MAP_G_TO_G_SIZE = 3255;
    public static final int GL_PIXEL_MAP_B_TO_B_SIZE = 3256;
    public static final int GL_PIXEL_MAP_A_TO_A_SIZE = 3257;
    public static final int GL_UNPACK_SWAP_BYTES = 3312;
    public static final int GL_UNPACK_LSB_FIRST = 3313;
    public static final int GL_UNPACK_ROW_LENGTH = 3314;
    public static final int GL_UNPACK_SKIP_ROWS = 3315;
    public static final int GL_UNPACK_SKIP_PIXELS = 3316;
    public static final int GL_UNPACK_ALIGNMENT = 3317;
    public static final int GL_PACK_SWAP_BYTES = 3328;
    public static final int GL_PACK_LSB_FIRST = 3329;
    public static final int GL_PACK_ROW_LENGTH = 3330;
    public static final int GL_PACK_SKIP_ROWS = 3331;
    public static final int GL_PACK_SKIP_PIXELS = 3332;
    public static final int GL_PACK_ALIGNMENT = 3333;
    public static final int GL_MAP_COLOR = 3344;
    public static final int GL_MAP_STENCIL = 3345;
    public static final int GL_INDEX_SHIFT = 3346;
    public static final int GL_INDEX_OFFSET = 3347;
    public static final int GL_RED_SCALE = 3348;
    public static final int GL_RED_BIAS = 3349;
    public static final int GL_ZOOM_X = 3350;
    public static final int GL_ZOOM_Y = 3351;
    public static final int GL_GREEN_SCALE = 3352;
    public static final int GL_GREEN_BIAS = 3353;
    public static final int GL_BLUE_SCALE = 3354;
    public static final int GL_BLUE_BIAS = 3355;
    public static final int GL_ALPHA_SCALE = 3356;
    public static final int GL_ALPHA_BIAS = 3357;
    public static final int GL_DEPTH_SCALE = 3358;
    public static final int GL_DEPTH_BIAS = 3359;
    public static final int GL_MAX_EVAL_ORDER = 3376;
    public static final int GL_MAX_LIGHTS = 3377;
    public static final int GL_MAX_CLIP_PLANES = 3378;
    public static final int GL_MAX_TEXTURE_SIZE = 3379;
    public static final int GL_MAX_PIXEL_MAP_TABLE = 3380;
    public static final int GL_MAX_ATTRIB_STACK_DEPTH = 3381;
    public static final int GL_MAX_MODELVIEW_STACK_DEPTH = 3382;
    public static final int GL_MAX_NAME_STACK_DEPTH = 3383;
    public static final int GL_MAX_PROJECTION_STACK_DEPTH = 3384;
    public static final int GL_MAX_TEXTURE_STACK_DEPTH = 3385;
    public static final int GL_MAX_VIEWPORT_DIMS = 3386;
    public static final int GL_MAX_CLIENT_ATTRIB_STACK_DEPTH = 3387;
    public static final int GL_SUBPIXEL_BITS = 3408;
    public static final int GL_INDEX_BITS = 3409;
    public static final int GL_RED_BITS = 3410;
    public static final int GL_GREEN_BITS = 3411;
    public static final int GL_BLUE_BITS = 3412;
    public static final int GL_ALPHA_BITS = 3413;
    public static final int GL_DEPTH_BITS = 3414;
    public static final int GL_STENCIL_BITS = 3415;
    public static final int GL_ACCUM_RED_BITS = 3416;
    public static final int GL_ACCUM_GREEN_BITS = 3417;
    public static final int GL_ACCUM_BLUE_BITS = 3418;
    public static final int GL_ACCUM_ALPHA_BITS = 3419;
    public static final int GL_NAME_STACK_DEPTH = 3440;
    public static final int GL_AUTO_NORMAL = 3456;
    public static final int GL_MAP1_COLOR_4 = 3472;
    public static final int GL_MAP1_INDEX = 3473;
    public static final int GL_MAP1_NORMAL = 3474;
    public static final int GL_MAP1_TEXTURE_COORD_1 = 3475;
    public static final int GL_MAP1_TEXTURE_COORD_2 = 3476;
    public static final int GL_MAP1_TEXTURE_COORD_3 = 3477;
    public static final int GL_MAP1_TEXTURE_COORD_4 = 3478;
    public static final int GL_MAP1_VERTEX_3 = 3479;
    public static final int GL_MAP1_VERTEX_4 = 3480;
    public static final int GL_MAP2_COLOR_4 = 3504;
    public static final int GL_MAP2_INDEX = 3505;
    public static final int GL_MAP2_NORMAL = 3506;
    public static final int GL_MAP2_TEXTURE_COORD_1 = 3507;
    public static final int GL_MAP2_TEXTURE_COORD_2 = 3508;
    public static final int GL_MAP2_TEXTURE_COORD_3 = 3509;
    public static final int GL_MAP2_TEXTURE_COORD_4 = 3510;
    public static final int GL_MAP2_VERTEX_3 = 3511;
    public static final int GL_MAP2_VERTEX_4 = 3512;
    public static final int GL_MAP1_GRID_DOMAIN = 3536;
    public static final int GL_MAP1_GRID_SEGMENTS = 3537;
    public static final int GL_MAP2_GRID_DOMAIN = 3538;
    public static final int GL_MAP2_GRID_SEGMENTS = 3539;
    public static final int GL_TEXTURE_1D = 3552;
    public static final int GL_TEXTURE_2D = 3553;
    public static final int GL_FEEDBACK_BUFFER_POINTER = 3568;
    public static final int GL_FEEDBACK_BUFFER_SIZE = 3569;
    public static final int GL_FEEDBACK_BUFFER_TYPE = 3570;
    public static final int GL_SELECTION_BUFFER_POINTER = 3571;
    public static final int GL_SELECTION_BUFFER_SIZE = 3572;
    public static final int GL_TEXTURE_WIDTH = 4096;
    public static final int GL_TEXTURE_HEIGHT = 4097;
    public static final int GL_TEXTURE_INTERNAL_FORMAT = 4099;
    public static final int GL_TEXTURE_COMPONENTS = 4099;
    public static final int GL_TEXTURE_BORDER_COLOR = 4100;
    public static final int GL_TEXTURE_BORDER = 4101;
    public static final int GL_DONT_CARE = 4352;
    public static final int GL_FASTEST = 4353;
    public static final int GL_NICEST = 4354;
    public static final int GL_LIGHT0 = 16384;
    public static final int GL_LIGHT1 = 16385;
    public static final int GL_LIGHT2 = 16386;
    public static final int GL_LIGHT3 = 16387;
    public static final int GL_LIGHT4 = 16388;
    public static final int GL_LIGHT5 = 16389;
    public static final int GL_LIGHT6 = 16390;
    public static final int GL_LIGHT7 = 16391;
    public static final int GL_AMBIENT = 4608;
    public static final int GL_DIFFUSE = 4609;
    public static final int GL_SPECULAR = 4610;
    public static final int GL_POSITION = 4611;
    public static final int GL_SPOT_DIRECTION = 4612;
    public static final int GL_SPOT_EXPONENT = 4613;
    public static final int GL_SPOT_CUTOFF = 4614;
    public static final int GL_CONSTANT_ATTENUATION = 4615;
    public static final int GL_LINEAR_ATTENUATION = 4616;
    public static final int GL_QUADRATIC_ATTENUATION = 4617;
    public static final int GL_COMPILE = 4864;
    public static final int GL_COMPILE_AND_EXECUTE = 4865;
    public static final int GL_CLEAR = 5376;
    public static final int GL_AND = 5377;
    public static final int GL_AND_REVERSE = 5378;
    public static final int GL_COPY = 5379;
    public static final int GL_AND_INVERTED = 5380;
    public static final int GL_NOOP = 5381;
    public static final int GL_XOR = 5382;
    public static final int GL_OR = 5383;
    public static final int GL_NOR = 5384;
    public static final int GL_EQUIV = 5385;
    public static final int GL_INVERT = 5386;
    public static final int GL_OR_REVERSE = 5387;
    public static final int GL_COPY_INVERTED = 5388;
    public static final int GL_OR_INVERTED = 5389;
    public static final int GL_NAND = 5390;
    public static final int GL_SET = 5391;
    public static final int GL_EMISSION = 5632;
    public static final int GL_SHININESS = 5633;
    public static final int GL_AMBIENT_AND_DIFFUSE = 5634;
    public static final int GL_COLOR_INDEXES = 5635;
    public static final int GL_MODELVIEW = 5888;
    public static final int GL_PROJECTION = 5889;
    public static final int GL_TEXTURE = 5890;
    public static final int GL_COLOR = 6144;
    public static final int GL_DEPTH = 6145;
    public static final int GL_STENCIL = 6146;
    public static final int GL_COLOR_INDEX = 6400;
    public static final int GL_STENCIL_INDEX = 6401;
    public static final int GL_DEPTH_COMPONENT = 6402;
    public static final int GL_RED = 6403;
    public static final int GL_GREEN = 6404;
    public static final int GL_BLUE = 6405;
    public static final int GL_ALPHA = 6406;
    public static final int GL_RGB = 6407;
    public static final int GL_RGBA = 6408;
    public static final int GL_LUMINANCE = 6409;
    public static final int GL_LUMINANCE_ALPHA = 6410;
    public static final int GL_BITMAP = 6656;
    public static final int GL_POINT = 6912;
    public static final int GL_LINE = 6913;
    public static final int GL_FILL = 6914;
    public static final int GL_RENDER = 7168;
    public static final int GL_FEEDBACK = 7169;
    public static final int GL_SELECT = 7170;
    public static final int GL_FLAT = 7424;
    public static final int GL_SMOOTH = 7425;
    public static final int GL_KEEP = 7680;
    public static final int GL_REPLACE = 7681;
    public static final int GL_INCR = 7682;
    public static final int GL_DECR = 7683;
    public static final int GL_VENDOR = 7936;
    public static final int GL_RENDERER = 7937;
    public static final int GL_VERSION = 7938;
    public static final int GL_EXTENSIONS = 7939;
    public static final int GL_S = 8192;
    public static final int GL_T = 8193;
    public static final int GL_R = 8194;
    public static final int GL_Q = 8195;
    public static final int GL_MODULATE = 8448;
    public static final int GL_DECAL = 8449;
    public static final int GL_TEXTURE_ENV_MODE = 8704;
    public static final int GL_TEXTURE_ENV_COLOR = 8705;
    public static final int GL_TEXTURE_ENV = 8960;
    public static final int GL_EYE_LINEAR = 9216;
    public static final int GL_OBJECT_LINEAR = 9217;
    public static final int GL_SPHERE_MAP = 9218;
    public static final int GL_TEXTURE_GEN_MODE = 9472;
    public static final int GL_OBJECT_PLANE = 9473;
    public static final int GL_EYE_PLANE = 9474;
    public static final int GL_NEAREST = 9728;
    public static final int GL_LINEAR = 9729;
    public static final int GL_NEAREST_MIPMAP_NEAREST = 9984;
    public static final int GL_LINEAR_MIPMAP_NEAREST = 9985;
    public static final int GL_NEAREST_MIPMAP_LINEAR = 9986;
    public static final int GL_LINEAR_MIPMAP_LINEAR = 9987;
    public static final int GL_TEXTURE_MAG_FILTER = 10240;
    public static final int GL_TEXTURE_MIN_FILTER = 10241;
    public static final int GL_TEXTURE_WRAP_S = 10242;
    public static final int GL_TEXTURE_WRAP_T = 10243;
    public static final int GL_CLAMP = 10496;
    public static final int GL_REPEAT = 10497;
    public static final int GL_CLIENT_PIXEL_STORE_BIT = 1;
    public static final int GL_CLIENT_VERTEX_ARRAY_BIT = 2;
    public static final int GL_CLIENT_ALL_ATTRIB_BITS = -1;
    public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
    public static final int GL_POLYGON_OFFSET_UNITS = 10752;
    public static final int GL_POLYGON_OFFSET_POINT = 10753;
    public static final int GL_POLYGON_OFFSET_LINE = 10754;
    public static final int GL_POLYGON_OFFSET_FILL = 32823;
    public static final int GL_ALPHA4 = 32827;
    public static final int GL_ALPHA8 = 32828;
    public static final int GL_ALPHA12 = 32829;
    public static final int GL_ALPHA16 = 32830;
    public static final int GL_LUMINANCE4 = 32831;
    public static final int GL_LUMINANCE8 = 32832;
    public static final int GL_LUMINANCE12 = 32833;
    public static final int GL_LUMINANCE16 = 32834;
    public static final int GL_LUMINANCE4_ALPHA4 = 32835;
    public static final int GL_LUMINANCE6_ALPHA2 = 32836;
    public static final int GL_LUMINANCE8_ALPHA8 = 32837;
    public static final int GL_LUMINANCE12_ALPHA4 = 32838;
    public static final int GL_LUMINANCE12_ALPHA12 = 32839;
    public static final int GL_LUMINANCE16_ALPHA16 = 32840;
    public static final int GL_INTENSITY = 32841;
    public static final int GL_INTENSITY4 = 32842;
    public static final int GL_INTENSITY8 = 32843;
    public static final int GL_INTENSITY12 = 32844;
    public static final int GL_INTENSITY16 = 32845;
    public static final int GL_R3_G3_B2 = 10768;
    public static final int GL_RGB4 = 32847;
    public static final int GL_RGB5 = 32848;
    public static final int GL_RGB8 = 32849;
    public static final int GL_RGB10 = 32850;
    public static final int GL_RGB12 = 32851;
    public static final int GL_RGB16 = 32852;
    public static final int GL_RGBA2 = 32853;
    public static final int GL_RGBA4 = 32854;
    public static final int GL_RGB5_A1 = 32855;
    public static final int GL_RGBA8 = 32856;
    public static final int GL_RGB10_A2 = 32857;
    public static final int GL_RGBA12 = 32858;
    public static final int GL_RGBA16 = 32859;
    public static final int GL_TEXTURE_RED_SIZE = 32860;
    public static final int GL_TEXTURE_GREEN_SIZE = 32861;
    public static final int GL_TEXTURE_BLUE_SIZE = 32862;
    public static final int GL_TEXTURE_ALPHA_SIZE = 32863;
    public static final int GL_TEXTURE_LUMINANCE_SIZE = 32864;
    public static final int GL_TEXTURE_INTENSITY_SIZE = 32865;
    public static final int GL_PROXY_TEXTURE_1D = 32867;
    public static final int GL_PROXY_TEXTURE_2D = 32868;
    public static final int GL_TEXTURE_PRIORITY = 32870;
    public static final int GL_TEXTURE_RESIDENT = 32871;
    public static final int GL_TEXTURE_BINDING_1D = 32872;
    public static final int GL_TEXTURE_BINDING_2D = 32873;
    public static final int GL_VERTEX_ARRAY = 32884;
    public static final int GL_NORMAL_ARRAY = 32885;
    public static final int GL_COLOR_ARRAY = 32886;
    public static final int GL_INDEX_ARRAY = 32887;
    public static final int GL_TEXTURE_COORD_ARRAY = 32888;
    public static final int GL_EDGE_FLAG_ARRAY = 32889;
    public static final int GL_VERTEX_ARRAY_SIZE = 32890;
    public static final int GL_VERTEX_ARRAY_TYPE = 32891;
    public static final int GL_VERTEX_ARRAY_STRIDE = 32892;
    public static final int GL_NORMAL_ARRAY_TYPE = 32894;
    public static final int GL_NORMAL_ARRAY_STRIDE = 32895;
    public static final int GL_COLOR_ARRAY_SIZE = 32897;
    public static final int GL_COLOR_ARRAY_TYPE = 32898;
    public static final int GL_COLOR_ARRAY_STRIDE = 32899;
    public static final int GL_INDEX_ARRAY_TYPE = 32901;
    public static final int GL_INDEX_ARRAY_STRIDE = 32902;
    public static final int GL_TEXTURE_COORD_ARRAY_SIZE = 32904;
    public static final int GL_TEXTURE_COORD_ARRAY_TYPE = 32905;
    public static final int GL_TEXTURE_COORD_ARRAY_STRIDE = 32906;
    public static final int GL_EDGE_FLAG_ARRAY_STRIDE = 32908;
    public static final int GL_VERTEX_ARRAY_POINTER = 32910;
    public static final int GL_NORMAL_ARRAY_POINTER = 32911;
    public static final int GL_COLOR_ARRAY_POINTER = 32912;
    public static final int GL_INDEX_ARRAY_POINTER = 32913;
    public static final int GL_TEXTURE_COORD_ARRAY_POINTER = 32914;
    public static final int GL_EDGE_FLAG_ARRAY_POINTER = 32915;
    public static final int GL_V2F = 10784;
    public static final int GL_V3F = 10785;
    public static final int GL_C4UB_V2F = 10786;
    public static final int GL_C4UB_V3F = 10787;
    public static final int GL_C3F_V3F = 10788;
    public static final int GL_N3F_V3F = 10789;
    public static final int GL_C4F_N3F_V3F = 10790;
    public static final int GL_T2F_V3F = 10791;
    public static final int GL_T4F_V4F = 10792;
    public static final int GL_T2F_C4UB_V3F = 10793;
    public static final int GL_T2F_C3F_V3F = 10794;
    public static final int GL_T2F_N3F_V3F = 10795;
    public static final int GL_T2F_C4F_N3F_V3F = 10796;
    public static final int GL_T4F_C4F_N3F_V4F = 10797;

    public static native void glAccum(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void glAlphaFunc(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native boolean nglAreTexturesResident(int i, long j, long j2);

    public static native void glArrayElement(@NativeType("GLint") int i);

    public static native void glBegin(@NativeType("GLenum") int i);

    public static native void nglBitmap(int i, int i2, float f, float f2, float f3, float f4, long j);

    public static native void glCallList(@NativeType("GLuint") int i);

    public static native void nglCallLists(int i, int i2, long j);

    public static native void glClearAccum(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glClearIndex(@NativeType("GLfloat") float f);

    public static native void nglClipPlane(int i, long j);

    public static native void glColor3b(@NativeType("GLbyte") byte b2, @NativeType("GLbyte") byte b3, @NativeType("GLbyte") byte b4);

    public static native void glColor3s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glColor3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glColor3f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glColor3d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glColor3ub(@NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4);

    public static native void glColor3us(@NativeType("GLushort") short s, @NativeType("GLushort") short s2, @NativeType("GLushort") short s3);

    public static native void glColor3ui(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void nglColor3bv(long j);

    public static native void nglColor3sv(long j);

    public static native void nglColor3iv(long j);

    public static native void nglColor3fv(long j);

    public static native void nglColor3dv(long j);

    public static native void nglColor3ubv(long j);

    public static native void nglColor3usv(long j);

    public static native void nglColor3uiv(long j);

    public static native void glColor4b(@NativeType("GLbyte") byte b2, @NativeType("GLbyte") byte b3, @NativeType("GLbyte") byte b4, @NativeType("GLbyte") byte b5);

    public static native void glColor4s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glColor4i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glColor4f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glColor4d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void glColor4ub(@NativeType("GLubyte") byte b2, @NativeType("GLubyte") byte b3, @NativeType("GLubyte") byte b4, @NativeType("GLubyte") byte b5);

    public static native void glColor4us(@NativeType("GLushort") short s, @NativeType("GLushort") short s2, @NativeType("GLushort") short s3, @NativeType("GLushort") short s4);

    public static native void glColor4ui(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void nglColor4bv(long j);

    public static native void nglColor4sv(long j);

    public static native void nglColor4iv(long j);

    public static native void nglColor4fv(long j);

    public static native void nglColor4dv(long j);

    public static native void nglColor4ubv(long j);

    public static native void nglColor4usv(long j);

    public static native void nglColor4uiv(long j);

    public static native void glColorMaterial(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    public static native void nglColorPointer(int i, int i2, int i3, long j);

    public static native void glCopyPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5);

    public static native void glDeleteLists(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2);

    public static native void glDisableClientState(@NativeType("GLenum") int i);

    public static native void nglDrawPixels(int i, int i2, int i3, int i4, long j);

    public static native void glEdgeFlag(@NativeType("GLboolean") boolean z);

    public static native void nglEdgeFlagv(long j);

    public static native void nglEdgeFlagPointer(int i, long j);

    public static native void glEnableClientState(@NativeType("GLenum") int i);

    public static native void glEnd();

    public static native void glEvalCoord1f(@NativeType("GLfloat") float f);

    public static native void nglEvalCoord1fv(long j);

    public static native void glEvalCoord1d(@NativeType("GLdouble") double d);

    public static native void nglEvalCoord1dv(long j);

    public static native void glEvalCoord2f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void nglEvalCoord2fv(long j);

    public static native void glEvalCoord2d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglEvalCoord2dv(long j);

    public static native void glEvalMesh1(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glEvalMesh2(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glEvalPoint1(@NativeType("GLint") int i);

    public static native void glEvalPoint2(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void nglFeedbackBuffer(int i, int i2, long j);

    public static native void glFogi(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void nglFogiv(int i, long j);

    public static native void glFogf(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void nglFogfv(int i, long j);

    @NativeType("GLuint")
    public static native int glGenLists(@NativeType("GLsizei") int i);

    public static native void nglGetClipPlane(int i, long j);

    public static native void nglGetLightiv(int i, int i2, long j);

    public static native void nglGetLightfv(int i, int i2, long j);

    public static native void nglGetMapiv(int i, int i2, long j);

    public static native void nglGetMapfv(int i, int i2, long j);

    public static native void nglGetMapdv(int i, int i2, long j);

    public static native void nglGetMaterialiv(int i, int i2, long j);

    public static native void nglGetMaterialfv(int i, int i2, long j);

    public static native void nglGetPixelMapfv(int i, long j);

    public static native void nglGetPixelMapusv(int i, long j);

    public static native void nglGetPixelMapuiv(int i, long j);

    public static native void nglGetPolygonStipple(long j);

    public static native void nglGetTexEnviv(int i, int i2, long j);

    public static native void nglGetTexEnvfv(int i, int i2, long j);

    public static native void nglGetTexGeniv(int i, int i2, long j);

    public static native void nglGetTexGenfv(int i, int i2, long j);

    public static native void nglGetTexGendv(int i, int i2, long j);

    public static native void glIndexi(@NativeType("GLint") int i);

    public static native void glIndexub(@NativeType("GLubyte") byte b2);

    public static native void glIndexs(@NativeType("GLshort") short s);

    public static native void glIndexf(@NativeType("GLfloat") float f);

    public static native void glIndexd(@NativeType("GLdouble") double d);

    public static native void nglIndexiv(long j);

    public static native void nglIndexubv(long j);

    public static native void nglIndexsv(long j);

    public static native void nglIndexfv(long j);

    public static native void nglIndexdv(long j);

    public static native void glIndexMask(@NativeType("GLuint") int i);

    public static native void nglIndexPointer(int i, int i2, long j);

    public static native void glInitNames();

    public static native void nglInterleavedArrays(int i, int i2, long j);

    @NativeType("GLboolean")
    public static native boolean glIsList(@NativeType("GLuint") int i);

    public static native void glLightModeli(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void glLightModelf(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void nglLightModeliv(int i, long j);

    public static native void nglLightModelfv(int i, long j);

    public static native void glLighti(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void glLightf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglLightiv(int i, int i2, long j);

    public static native void nglLightfv(int i, int i2, long j);

    public static native void glLineStipple(@NativeType("GLint") int i, @NativeType("GLushort") short s);

    public static native void glListBase(@NativeType("GLuint") int i);

    public static native void nglLoadMatrixf(long j);

    public static native void nglLoadMatrixd(long j);

    public static native void glLoadIdentity();

    public static native void glLoadName(@NativeType("GLuint") int i);

    public static native void nglMap1f(int i, float f, float f2, int i2, int i3, long j);

    public static native void nglMap1d(int i, double d, double d2, int i2, int i3, long j);

    public static native void nglMap2f(int i, float f, float f2, int i2, int i3, float f3, float f4, int i4, int i5, long j);

    public static native void nglMap2d(int i, double d, double d2, int i2, int i3, double d3, double d4, int i4, int i5, long j);

    public static native void glMapGrid1f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glMapGrid1d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glMapGrid2f(@NativeType("GLint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLint") int i2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glMapGrid2d(@NativeType("GLint") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLint") int i2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void glMateriali(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void glMaterialf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglMaterialiv(int i, int i2, long j);

    public static native void nglMaterialfv(int i, int i2, long j);

    public static native void glMatrixMode(@NativeType("GLenum") int i);

    public static native void nglMultMatrixf(long j);

    public static native void nglMultMatrixd(long j);

    public static native void glFrustum(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4, @NativeType("GLdouble") double d5, @NativeType("GLdouble") double d6);

    public static native void glNewList(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glEndList();

    public static native void glNormal3f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glNormal3b(@NativeType("GLbyte") byte b2, @NativeType("GLbyte") byte b3, @NativeType("GLbyte") byte b4);

    public static native void glNormal3s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glNormal3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glNormal3d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglNormal3fv(long j);

    public static native void nglNormal3bv(long j);

    public static native void nglNormal3sv(long j);

    public static native void nglNormal3iv(long j);

    public static native void nglNormal3dv(long j);

    public static native void nglNormalPointer(int i, int i2, long j);

    public static native void glOrtho(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4, @NativeType("GLdouble") double d5, @NativeType("GLdouble") double d6);

    public static native void glPassThrough(@NativeType("GLfloat") float f);

    public static native void nglPixelMapfv(int i, int i2, long j);

    public static native void nglPixelMapusv(int i, int i2, long j);

    public static native void nglPixelMapuiv(int i, int i2, long j);

    public static native void glPixelTransferi(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void glPixelTransferf(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void glPixelZoom(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void nglPolygonStipple(long j);

    public static native void glPushAttrib(@NativeType("GLbitfield") int i);

    public static native void glPushClientAttrib(@NativeType("GLbitfield") int i);

    public static native void glPopAttrib();

    public static native void glPopClientAttrib();

    public static native void glPopMatrix();

    public static native void glPopName();

    public static native void nglPrioritizeTextures(int i, long j, long j2);

    public static native void glPushMatrix();

    public static native void glPushName(@NativeType("GLuint") int i);

    public static native void glRasterPos2i(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void glRasterPos2s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glRasterPos2f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glRasterPos2d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglRasterPos2iv(long j);

    public static native void nglRasterPos2sv(long j);

    public static native void nglRasterPos2fv(long j);

    public static native void nglRasterPos2dv(long j);

    public static native void glRasterPos3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glRasterPos3s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glRasterPos3f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glRasterPos3d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglRasterPos3iv(long j);

    public static native void nglRasterPos3sv(long j);

    public static native void nglRasterPos3fv(long j);

    public static native void nglRasterPos3dv(long j);

    public static native void glRasterPos4i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glRasterPos4s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glRasterPos4f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glRasterPos4d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglRasterPos4iv(long j);

    public static native void nglRasterPos4sv(long j);

    public static native void nglRasterPos4fv(long j);

    public static native void nglRasterPos4dv(long j);

    public static native void glRecti(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glRects(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glRectf(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glRectd(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglRectiv(long j, long j2);

    public static native void nglRectsv(long j, long j2);

    public static native void nglRectfv(long j, long j2);

    public static native void nglRectdv(long j, long j2);

    @NativeType("GLint")
    public static native int glRenderMode(@NativeType("GLenum") int i);

    public static native void glRotatef(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glRotated(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void glScalef(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glScaled(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglSelectBuffer(int i, long j);

    public static native void glShadeModel(@NativeType("GLenum") int i);

    public static native void glTexCoord1f(@NativeType("GLfloat") float f);

    public static native void glTexCoord1s(@NativeType("GLshort") short s);

    public static native void glTexCoord1i(@NativeType("GLint") int i);

    public static native void glTexCoord1d(@NativeType("GLdouble") double d);

    public static native void nglTexCoord1fv(long j);

    public static native void nglTexCoord1sv(long j);

    public static native void nglTexCoord1iv(long j);

    public static native void nglTexCoord1dv(long j);

    public static native void glTexCoord2f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glTexCoord2s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glTexCoord2i(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void glTexCoord2d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglTexCoord2fv(long j);

    public static native void nglTexCoord2sv(long j);

    public static native void nglTexCoord2iv(long j);

    public static native void nglTexCoord2dv(long j);

    public static native void glTexCoord3f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glTexCoord3s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glTexCoord3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glTexCoord3d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglTexCoord3fv(long j);

    public static native void nglTexCoord3sv(long j);

    public static native void nglTexCoord3iv(long j);

    public static native void nglTexCoord3dv(long j);

    public static native void glTexCoord4f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glTexCoord4s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glTexCoord4i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glTexCoord4d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglTexCoord4fv(long j);

    public static native void nglTexCoord4sv(long j);

    public static native void nglTexCoord4iv(long j);

    public static native void nglTexCoord4dv(long j);

    public static native void nglTexCoordPointer(int i, int i2, int i3, long j);

    public static native void glTexEnvi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglTexEnviv(int i, int i2, long j);

    public static native void glTexEnvf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglTexEnvfv(int i, int i2, long j);

    public static native void glTexGeni(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglTexGeniv(int i, int i2, long j);

    public static native void glTexGenf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglTexGenfv(int i, int i2, long j);

    public static native void glTexGend(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble") double d);

    public static native void nglTexGendv(int i, int i2, long j);

    public static native void glTranslatef(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glTranslated(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void glVertex2f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glVertex2s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2);

    public static native void glVertex2i(@NativeType("GLint") int i, @NativeType("GLint") int i2);

    public static native void glVertex2d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void nglVertex2fv(long j);

    public static native void nglVertex2sv(long j);

    public static native void nglVertex2iv(long j);

    public static native void nglVertex2dv(long j);

    public static native void glVertex3f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3);

    public static native void glVertex3s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3);

    public static native void glVertex3i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3);

    public static native void glVertex3d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3);

    public static native void nglVertex3fv(long j);

    public static native void nglVertex3sv(long j);

    public static native void nglVertex3iv(long j);

    public static native void nglVertex3dv(long j);

    public static native void glVertex4f(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glVertex4s(@NativeType("GLshort") short s, @NativeType("GLshort") short s2, @NativeType("GLshort") short s3, @NativeType("GLshort") short s4);

    public static native void glVertex4i(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4);

    public static native void glVertex4d(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4);

    public static native void nglVertex4fv(long j);

    public static native void nglVertex4sv(long j);

    public static native void nglVertex4iv(long j);

    public static native void nglVertex4dv(long j);

    public static native void nglVertexPointer(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL11() {
        throw new UnsupportedOperationException();
    }

    public static void glEnable(@NativeType("GLenum") int i) {
        GL11C.glEnable(i);
    }

    public static void glDisable(@NativeType("GLenum") int i) {
        GL11C.glDisable(i);
    }

    @NativeType("GLboolean")
    public static boolean glAreTexturesResident(@NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, intBuffer.remaining());
        }
        return nglAreTexturesResident(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLboolean")
    public static boolean glAreTexturesResident(@NativeType("GLuint const *") int i, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            return nglAreTexturesResident(1, MemoryUtil.memAddress(stackGet.ints(i)), MemoryUtil.memAddress(byteBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glBindTexture(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL11C.glBindTexture(i, i2);
    }

    public static void glBitmap(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4, @NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, ((i + 7) >> 3) * i2);
        }
        nglBitmap(i, i2, f, f2, f3, f4, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glBitmap(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4, @NativeType("GLubyte const *") long j) {
        nglBitmap(i, i2, f, f2, f3, f4, j);
    }

    public static void glBlendFunc(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        GL11C.glBlendFunc(i, i2);
    }

    public static void glCallLists(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCallLists(byteBuffer.remaining() / GLChecks.typeToBytes(i), i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCallLists(@NativeType("void const *") ByteBuffer byteBuffer) {
        nglCallLists(byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCallLists(@NativeType("void const *") ShortBuffer shortBuffer) {
        nglCallLists(shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glCallLists(@NativeType("void const *") IntBuffer intBuffer) {
        nglCallLists(intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer));
    }

    public static void glClear(@NativeType("GLbitfield") int i) {
        GL11C.glClear(i);
    }

    public static void glClearColor(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4) {
        GL11C.glClearColor(f, f2, f3, f4);
    }

    public static void glClearDepth(@NativeType("GLdouble") double d) {
        GL11C.glClearDepth(d);
    }

    public static void glClearStencil(@NativeType("GLint") int i) {
        GL11C.glClearStencil(i);
    }

    public static void glClipPlane(@NativeType("GLenum") int i, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglClipPlane(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glColor3bv(@NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 3);
        }
        nglColor3bv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glColor3sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglColor3sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColor3iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglColor3iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glColor3fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglColor3fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glColor3dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglColor3dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glColor3ubv(@NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 3);
        }
        nglColor3ubv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glColor3usv(@NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglColor3usv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColor3uiv(@NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglColor3uiv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glColor4bv(@NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglColor4bv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glColor4sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglColor4sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColor4iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglColor4iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glColor4fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglColor4fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glColor4dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglColor4dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glColor4ubv(@NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 4);
        }
        nglColor4ubv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glColor4usv(@NativeType("GLushort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglColor4usv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColor4uiv(@NativeType("GLuint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglColor4uiv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glColorMask(@NativeType("GLboolean") boolean z, @NativeType("GLboolean") boolean z2, @NativeType("GLboolean") boolean z3, @NativeType("GLboolean") boolean z4) {
        GL11C.glColorMask(z, z2, z3, z4);
    }

    public static void glColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglColorPointer(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") long j) {
        nglColorPointer(i, i2, i3, j);
    }

    public static void glColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglColorPointer(i, i2, i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") IntBuffer intBuffer) {
        nglColorPointer(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glColorPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglColorPointer(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glCullFace(@NativeType("GLenum") int i) {
        GL11C.glCullFace(i);
    }

    public static void glDepthFunc(@NativeType("GLenum") int i) {
        GL11C.glDepthFunc(i);
    }

    public static void glDepthMask(@NativeType("GLboolean") boolean z) {
        GL11C.glDepthMask(z);
    }

    public static void glDepthRange(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2) {
        GL11C.glDepthRange(d, d2);
    }

    public static void glDrawArrays(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3) {
        GL11C.glDrawArrays(i, i2, i3);
    }

    public static void glDrawBuffer(@NativeType("GLenum") int i) {
        GL11C.glDrawBuffer(i);
    }

    public static void nglDrawElements(int i, int i2, int i3, long j) {
        GL11C.nglDrawElements(i, i2, i3, j);
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j) {
        GL11C.glDrawElements(i, i2, i3, j);
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL11C.glDrawElements(i, i2, byteBuffer);
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL11C.glDrawElements(i, byteBuffer);
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL11C.glDrawElements(i, shortBuffer);
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer) {
        GL11C.glDrawElements(i, intBuffer);
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglDrawPixels(i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") long j) {
        nglDrawPixels(i, i2, i3, i4, j);
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglDrawPixels(i, i2, i3, i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") IntBuffer intBuffer) {
        nglDrawPixels(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglDrawPixels(i, i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glEdgeFlagv(@NativeType("GLboolean const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 1);
        }
        nglEdgeFlagv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glEdgeFlagPointer(@NativeType("GLsizei") int i, @NativeType("GLboolean const *") ByteBuffer byteBuffer) {
        nglEdgeFlagPointer(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glEdgeFlagPointer(@NativeType("GLsizei") int i, @NativeType("GLboolean const *") long j) {
        nglEdgeFlagPointer(i, j);
    }

    public static void glEvalCoord1fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglEvalCoord1fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glEvalCoord1dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglEvalCoord1dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glEvalCoord2fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglEvalCoord2fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glEvalCoord2dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglEvalCoord2dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glFeedbackBuffer(@NativeType("GLenum") int i, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        nglFeedbackBuffer(floatBuffer.remaining(), i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glFinish() {
        GL11C.glFinish();
    }

    public static void glFlush() {
        GL11C.glFlush();
    }

    public static void glFogiv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglFogiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glFogfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglFogfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glFrontFace(@NativeType("GLenum") int i) {
        GL11C.glFrontFace(i);
    }

    public static void nglGenTextures(int i, long j) {
        GL11C.nglGenTextures(i, j);
    }

    public static void glGenTextures(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL11C.glGenTextures(intBuffer);
    }

    @NativeType("void")
    public static int glGenTextures() {
        return GL11C.glGenTextures();
    }

    public static void nglDeleteTextures(int i, long j) {
        GL11C.nglDeleteTextures(i, j);
    }

    public static void glDeleteTextures(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL11C.glDeleteTextures(intBuffer);
    }

    public static void glDeleteTextures(@NativeType("GLuint const *") int i) {
        GL11C.glDeleteTextures(i);
    }

    public static void glGetClipPlane(@NativeType("GLenum") int i, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetClipPlane(i, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void nglGetBooleanv(int i, long j) {
        GL11C.nglGetBooleanv(i, j);
    }

    public static void glGetBooleanv(@NativeType("GLenum") int i, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        GL11C.glGetBooleanv(i, byteBuffer);
    }

    @NativeType("void")
    public static boolean glGetBoolean(@NativeType("GLenum") int i) {
        return GL11C.glGetBoolean(i);
    }

    public static void nglGetFloatv(int i, long j) {
        GL11C.nglGetFloatv(i, j);
    }

    public static void glGetFloatv(@NativeType("GLenum") int i, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL11C.glGetFloatv(i, floatBuffer);
    }

    @NativeType("void")
    public static float glGetFloat(@NativeType("GLenum") int i) {
        return GL11C.glGetFloat(i);
    }

    public static void nglGetIntegerv(int i, long j) {
        GL11C.nglGetIntegerv(i, j);
    }

    public static void glGetIntegerv(@NativeType("GLenum") int i, @NativeType("GLint *") IntBuffer intBuffer) {
        GL11C.glGetIntegerv(i, intBuffer);
    }

    @NativeType("void")
    public static int glGetInteger(@NativeType("GLenum") int i) {
        return GL11C.glGetInteger(i);
    }

    public static void nglGetDoublev(int i, long j) {
        GL11C.nglGetDoublev(i, j);
    }

    public static void glGetDoublev(@NativeType("GLenum") int i, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        GL11C.glGetDoublev(i, doubleBuffer);
    }

    @NativeType("void")
    public static double glGetDouble(@NativeType("GLenum") int i) {
        return GL11C.glGetDouble(i);
    }

    @NativeType("GLenum")
    public static int glGetError() {
        return GL11C.glGetError();
    }

    public static void glGetLightiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetLightiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetLighti(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetLightiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetLightfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetLightfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetLightf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetLightfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMapiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglGetMapiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetMapi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetMapiv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMapfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetMapfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetMapf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetMapfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMapdv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetMapdv(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetMapd(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetMapdv(i, i2, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetMaterialiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetMaterialiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetMaterialfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetMaterialfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPixelMapfv(@NativeType("GLenum") int i, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 32);
        }
        nglGetPixelMapfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPixelMapfv(@NativeType("GLenum") int i, @NativeType("GLfloat *") long j) {
        nglGetPixelMapfv(i, j);
    }

    public static void glGetPixelMapusv(@NativeType("GLenum") int i, @NativeType("GLushort *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 32);
        }
        nglGetPixelMapusv(i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetPixelMapusv(@NativeType("GLenum") int i, @NativeType("GLushort *") long j) {
        nglGetPixelMapusv(i, j);
    }

    public static void glGetPixelMapuiv(@NativeType("GLenum") int i, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 32);
        }
        nglGetPixelMapuiv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetPixelMapuiv(@NativeType("GLenum") int i, @NativeType("GLuint *") long j) {
        nglGetPixelMapuiv(i, j);
    }

    public static void nglGetPointerv(int i, long j) {
        GL11C.nglGetPointerv(i, j);
    }

    public static void glGetPointerv(@NativeType("GLenum") int i, @NativeType("void **") PointerBuffer pointerBuffer) {
        GL11C.glGetPointerv(i, pointerBuffer);
    }

    @NativeType("void")
    public static long glGetPointer(@NativeType("GLenum") int i) {
        return GL11C.glGetPointer(i);
    }

    public static void glGetPolygonStipple(@NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 128);
        }
        nglGetPolygonStipple(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetPolygonStipple(@NativeType("void *") long j) {
        nglGetPolygonStipple(j);
    }

    public static long nglGetString(int i) {
        return GL11C.nglGetString(i);
    }

    @NativeType("GLubyte const *")
    public static String glGetString(@NativeType("GLenum") int i) {
        return GL11C.glGetString(i);
    }

    public static void glGetTexEnviv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexEnviv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexEnvi(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexEnviv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexEnvfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetTexEnvfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTexEnvf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTexEnvfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexGeniv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexGeniv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexGeni(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexGeniv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexGenfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglGetTexGenfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTexGenf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTexGenfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexGendv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglGetTexGendv(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetTexGend(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetTexGendv(i, i2, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nglGetTexImage(int i, int i2, int i3, int i4, long j) {
        GL11C.nglGetTexImage(i, i2, i3, i4, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ByteBuffer byteBuffer) {
        GL11C.glGetTexImage(i, i2, i3, i4, byteBuffer);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") long j) {
        GL11C.glGetTexImage(i, i2, i3, i4, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ShortBuffer shortBuffer) {
        GL11C.glGetTexImage(i, i2, i3, i4, shortBuffer);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") IntBuffer intBuffer) {
        GL11C.glGetTexImage(i, i2, i3, i4, intBuffer);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") FloatBuffer floatBuffer) {
        GL11C.glGetTexImage(i, i2, i3, i4, floatBuffer);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") DoubleBuffer doubleBuffer) {
        GL11C.glGetTexImage(i, i2, i3, i4, doubleBuffer);
    }

    public static void nglGetTexLevelParameteriv(int i, int i2, int i3, long j) {
        GL11C.nglGetTexLevelParameteriv(i, i2, i3, j);
    }

    public static void glGetTexLevelParameteriv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL11C.glGetTexLevelParameteriv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetTexLevelParameteri(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        return GL11C.glGetTexLevelParameteri(i, i2, i3);
    }

    public static void nglGetTexLevelParameterfv(int i, int i2, int i3, long j) {
        GL11C.nglGetTexLevelParameterfv(i, i2, i3, j);
    }

    public static void glGetTexLevelParameterfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL11C.glGetTexLevelParameterfv(i, i2, i3, floatBuffer);
    }

    @NativeType("void")
    public static float glGetTexLevelParameterf(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        return GL11C.glGetTexLevelParameterf(i, i2, i3);
    }

    public static void nglGetTexParameteriv(int i, int i2, long j) {
        GL11C.nglGetTexParameteriv(i, i2, j);
    }

    public static void glGetTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL11C.glGetTexParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetTexParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL11C.glGetTexParameteri(i, i2);
    }

    public static void nglGetTexParameterfv(int i, int i2, long j) {
        GL11C.nglGetTexParameterfv(i, i2, j);
    }

    public static void glGetTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL11C.glGetTexParameterfv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetTexParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL11C.glGetTexParameterf(i, i2);
    }

    public static void glHint(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        GL11C.glHint(i, i2);
    }

    public static void glIndexiv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglIndexiv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glIndexubv(@NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 1);
        }
        nglIndexubv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glIndexsv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglIndexsv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glIndexfv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglIndexfv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glIndexdv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglIndexdv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glIndexPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglIndexPointer(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glIndexPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") long j) {
        nglIndexPointer(i, i2, j);
    }

    public static void glIndexPointer(@NativeType("GLsizei") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglIndexPointer(5121, i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glIndexPointer(@NativeType("GLsizei") int i, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglIndexPointer(5122, i, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glIndexPointer(@NativeType("GLsizei") int i, @NativeType("void const *") IntBuffer intBuffer) {
        nglIndexPointer(5124, i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglInterleavedArrays(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") long j) {
        nglInterleavedArrays(i, i2, j);
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglInterleavedArrays(i, i2, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") IntBuffer intBuffer) {
        nglInterleavedArrays(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglInterleavedArrays(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglInterleavedArrays(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("GLboolean")
    public static boolean glIsEnabled(@NativeType("GLenum") int i) {
        return GL11C.glIsEnabled(i);
    }

    @NativeType("GLboolean")
    public static boolean glIsTexture(@NativeType("GLuint") int i) {
        return GL11C.glIsTexture(i);
    }

    public static void glLightModeliv(@NativeType("GLenum") int i, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglLightModeliv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glLightModelfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglLightModelfv(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glLightiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglLightiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glLightfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglLightfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glLineWidth(@NativeType("GLfloat") float f) {
        GL11C.glLineWidth(f);
    }

    public static void glLoadMatrixf(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglLoadMatrixf(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glLoadMatrixd(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglLoadMatrixd(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glLogicOp(@NativeType("GLenum") int i) {
        GL11C.glLogicOp(i);
    }

    public static void glMap1f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, i3 * i2);
        }
        nglMap1f(i, f, f2, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMap1d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, i2 * i3);
        }
        nglMap1d(i, d, d2, i2, i3, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMap2f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, i2 * i3 * i4 * i5);
        }
        nglMap2f(i, f, f2, i2, i3, f3, f4, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMap2d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, i2 * i3 * i4 * i5);
        }
        nglMap2d(i, d, d2, i2, i3, d3, d4, i4, i5, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glMaterialiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglMaterialiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glMaterialfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglMaterialfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultMatrixf(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 16);
        }
        nglMultMatrixf(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMultMatrixd(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 16);
        }
        nglMultMatrixd(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glNormal3fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglNormal3fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glNormal3bv(@NativeType("GLbyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 3);
        }
        nglNormal3bv(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glNormal3sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglNormal3sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glNormal3iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglNormal3iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glNormal3dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglNormal3dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glNormalPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglNormalPointer(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glNormalPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") long j) {
        nglNormalPointer(i, i2, j);
    }

    public static void glNormalPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglNormalPointer(i, i2, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glNormalPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") IntBuffer intBuffer) {
        nglNormalPointer(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glNormalPointer(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglNormalPointer(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPixelMapfv(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLfloat const *") long j) {
        nglPixelMapfv(i, i2, j);
    }

    public static void glPixelMapfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglPixelMapfv(i, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPixelMapusv(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLushort const *") long j) {
        nglPixelMapusv(i, i2, j);
    }

    public static void glPixelMapusv(@NativeType("GLenum") int i, @NativeType("GLushort const *") ShortBuffer shortBuffer) {
        nglPixelMapusv(i, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void glPixelMapuiv(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLuint const *") long j) {
        nglPixelMapuiv(i, i2, j);
    }

    public static void glPixelMapuiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        nglPixelMapuiv(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glPixelStorei(@NativeType("GLenum") int i, @NativeType("GLint") int i2) {
        GL11C.glPixelStorei(i, i2);
    }

    public static void glPixelStoref(@NativeType("GLenum") int i, @NativeType("GLfloat") float f) {
        GL11C.glPixelStoref(i, f);
    }

    public static void glPointSize(@NativeType("GLfloat") float f) {
        GL11C.glPointSize(f);
    }

    public static void glPolygonMode(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        GL11C.glPolygonMode(i, i2);
    }

    public static void glPolygonOffset(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2) {
        GL11C.glPolygonOffset(f, f2);
    }

    public static void glPolygonStipple(@NativeType("GLubyte const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 128);
        }
        nglPolygonStipple(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glPolygonStipple(@NativeType("GLubyte const *") long j) {
        nglPolygonStipple(j);
    }

    public static void glPrioritizeTextures(@NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, intBuffer.remaining());
        }
        nglPrioritizeTextures(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glRasterPos2iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglRasterPos2iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glRasterPos2sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglRasterPos2sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glRasterPos2fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglRasterPos2fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glRasterPos2dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglRasterPos2dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glRasterPos3iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglRasterPos3iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glRasterPos3sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglRasterPos3sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glRasterPos3fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglRasterPos3fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glRasterPos3dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglRasterPos3dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glRasterPos4iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglRasterPos4iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glRasterPos4sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglRasterPos4sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glRasterPos4fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglRasterPos4fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glRasterPos4dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglRasterPos4dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glReadBuffer(@NativeType("GLenum") int i) {
        GL11C.glReadBuffer(i);
    }

    public static void nglReadPixels(int i, int i2, int i3, int i4, int i5, int i6, long j) {
        GL11C.nglReadPixels(i, i2, i3, i4, i5, i6, j);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ByteBuffer byteBuffer) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, byteBuffer);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") long j) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, j);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ShortBuffer shortBuffer) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, shortBuffer);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") IntBuffer intBuffer) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, intBuffer);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") FloatBuffer floatBuffer) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, floatBuffer);
    }

    public static void glRectiv(@NativeType("GLint const *") IntBuffer intBuffer, @NativeType("GLint const *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
            Checks.check((Buffer) intBuffer2, 2);
        }
        nglRectiv(MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2));
    }

    public static void glRectsv(@NativeType("GLshort const *") ShortBuffer shortBuffer, @NativeType("GLshort const *") ShortBuffer shortBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
            Checks.check((Buffer) shortBuffer2, 2);
        }
        nglRectsv(MemoryUtil.memAddress(shortBuffer), MemoryUtil.memAddress(shortBuffer2));
    }

    public static void glRectfv(@NativeType("GLfloat const *") FloatBuffer floatBuffer, @NativeType("GLfloat const *") FloatBuffer floatBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
            Checks.check((Buffer) floatBuffer2, 2);
        }
        nglRectfv(MemoryUtil.memAddress(floatBuffer), MemoryUtil.memAddress(floatBuffer2));
    }

    public static void glRectdv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
            Checks.check((Buffer) doubleBuffer2, 2);
        }
        nglRectdv(MemoryUtil.memAddress(doubleBuffer), MemoryUtil.memAddress(doubleBuffer2));
    }

    public static void glScissor(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL11C.glScissor(i, i2, i3, i4);
    }

    public static void glSelectBuffer(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglSelectBuffer(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glStencilFunc(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3) {
        GL11C.glStencilFunc(i, i2, i3);
    }

    public static void glStencilMask(@NativeType("GLuint") int i) {
        GL11C.glStencilMask(i);
    }

    public static void glStencilOp(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        GL11C.glStencilOp(i, i2, i3);
    }

    public static void glTexCoord1fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglTexCoord1fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexCoord1sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 1);
        }
        nglTexCoord1sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord1iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglTexCoord1iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoord1dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglTexCoord1dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTexCoord2fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglTexCoord2fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexCoord2sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglTexCoord2sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord2iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglTexCoord2iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoord2dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglTexCoord2dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTexCoord3fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglTexCoord3fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexCoord3sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglTexCoord3sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord3iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglTexCoord3iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoord3dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglTexCoord3dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTexCoord4fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglTexCoord4fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexCoord4sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglTexCoord4sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoord4iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTexCoord4iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoord4dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglTexCoord4dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTexCoordPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTexCoordPointer(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTexCoordPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") long j) {
        nglTexCoordPointer(i, i2, i3, j);
    }

    public static void glTexCoordPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTexCoordPointer(i, i2, i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexCoordPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") IntBuffer intBuffer) {
        nglTexCoordPointer(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexCoordPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTexCoordPointer(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexEnviv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTexEnviv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexEnvfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglTexEnvfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexGeniv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTexGeniv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexGenfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglTexGenfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexGendv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglTexGendv(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void nglTexImage1D(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        GL11C.nglTexImage1D(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, byteBuffer);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") long j) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, shortBuffer);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") IntBuffer intBuffer) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, intBuffer);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, floatBuffer);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, doubleBuffer);
    }

    public static void nglTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        GL11C.nglTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, byteBuffer);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, shortBuffer);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, intBuffer);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, floatBuffer);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, doubleBuffer);
    }

    public static void glCopyTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7) {
        GL11C.glCopyTexImage1D(i, i2, i3, i4, i5, i6, i7);
    }

    public static void glCopyTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8) {
        GL11C.glCopyTexImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static void glCopyTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6) {
        GL11C.glCopyTexSubImage1D(i, i2, i3, i4, i5, i6);
    }

    public static void glCopyTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8) {
        GL11C.glCopyTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public static void glTexParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3) {
        GL11C.glTexParameteri(i, i2, i3);
    }

    public static void nglTexParameteriv(int i, int i2, long j) {
        GL11C.nglTexParameteriv(i, i2, j);
    }

    public static void glTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        GL11C.glTexParameteriv(i, i2, intBuffer);
    }

    public static void glTexParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f) {
        GL11C.glTexParameterf(i, i2, f);
    }

    public static void nglTexParameterfv(int i, int i2, long j) {
        GL11C.nglTexParameterfv(i, i2, j);
    }

    public static void glTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL11C.glTexParameterfv(i, i2, floatBuffer);
    }

    public static void nglTexSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j) {
        GL11C.nglTexSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, byteBuffer);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") long j) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, shortBuffer);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") IntBuffer intBuffer) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, intBuffer);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, floatBuffer);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, doubleBuffer);
    }

    public static void nglTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        GL11C.nglTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, byteBuffer);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, shortBuffer);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, intBuffer);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, floatBuffer);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, doubleBuffer);
    }

    public static void glVertex2fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglVertex2fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertex2sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 2);
        }
        nglVertex2sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertex2iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 2);
        }
        nglVertex2iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertex2dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 2);
        }
        nglVertex2dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertex3fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglVertex3fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertex3sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 3);
        }
        nglVertex3sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertex3iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 3);
        }
        nglVertex3iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertex3dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 3);
        }
        nglVertex3dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertex4fv(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglVertex4fv(MemoryUtil.memAddress(floatBuffer));
    }

    public static void glVertex4sv(@NativeType("GLshort const *") ShortBuffer shortBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer, 4);
        }
        nglVertex4sv(MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertex4iv(@NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglVertex4iv(MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertex4dv(@NativeType("GLdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 4);
        }
        nglVertex4dv(MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glVertexPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglVertexPointer(i, i2, i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glVertexPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") long j) {
        nglVertexPointer(i, i2, i3, j);
    }

    public static void glVertexPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglVertexPointer(i, i2, i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glVertexPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") IntBuffer intBuffer) {
        nglVertexPointer(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    public static void glVertexPointer(@NativeType("GLint") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglVertexPointer(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glViewport(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL11C.glViewport(i, i2, i3, i4);
    }

    @NativeType("GLboolean")
    public static boolean glAreTexturesResident(@NativeType("GLuint const *") int[] iArr, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glAreTexturesResident;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check((Buffer) byteBuffer, iArr.length);
        }
        return JNI.callPPZ(iArr.length, iArr, MemoryUtil.memAddress(byteBuffer), j);
    }

    public static void glClipPlane(@NativeType("GLenum") int i, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glClipPlane;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glColor3sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glColor3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glColor3iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glColor3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glColor3fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glColor3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glColor3dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glColor3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }

    public static void glColor3usv(@NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glColor3usv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glColor3uiv(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glColor3uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glColor4sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glColor4sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glColor4iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glColor4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(iArr, j);
    }

    public static void glColor4fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glColor4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(fArr, j);
    }

    public static void glColor4dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glColor4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(dArr, j);
    }

    public static void glColor4usv(@NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glColor4usv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glColor4uiv(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glColor4uiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(iArr, j);
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glDrawPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glDrawPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glDrawPixels(@NativeType("GLsizei") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glDrawPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glEvalCoord1fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glEvalCoord1fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(fArr, j);
    }

    public static void glEvalCoord1dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glEvalCoord1dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(dArr, j);
    }

    public static void glEvalCoord2fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glEvalCoord2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(fArr, j);
    }

    public static void glEvalCoord2dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glEvalCoord2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(dArr, j);
    }

    public static void glFeedbackBuffer(@NativeType("GLenum") int i, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glFeedbackBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(fArr.length, i, fArr, j);
    }

    public static void glFogiv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glFogiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glFogfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glFogfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glGenTextures(@NativeType("GLuint *") int[] iArr) {
        GL11C.glGenTextures(iArr);
    }

    public static void glDeleteTextures(@NativeType("GLuint const *") int[] iArr) {
        GL11C.glDeleteTextures(iArr);
    }

    public static void glGetClipPlane(@NativeType("GLenum") int i, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetClipPlane;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glGetFloatv(@NativeType("GLenum") int i, @NativeType("GLfloat *") float[] fArr) {
        GL11C.glGetFloatv(i, fArr);
    }

    public static void glGetIntegerv(@NativeType("GLenum") int i, @NativeType("GLint *") int[] iArr) {
        GL11C.glGetIntegerv(i, iArr);
    }

    public static void glGetDoublev(@NativeType("GLenum") int i, @NativeType("GLdouble *") double[] dArr) {
        GL11C.glGetDoublev(i, dArr);
    }

    public static void glGetLightiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetLightiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetLightfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetLightfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetMapiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMapiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetMapfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMapfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetMapdv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetMapdv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glGetMaterialiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetMaterialiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetMaterialfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMaterialfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetPixelMapfv(@NativeType("GLenum") int i, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPixelMapfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 32);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glGetPixelMapusv(@NativeType("GLenum") int i, @NativeType("GLushort *") short[] sArr) {
        long j = GL.getICD().glGetPixelMapusv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 32);
        }
        JNI.callPV(i, sArr, j);
    }

    public static void glGetPixelMapuiv(@NativeType("GLenum") int i, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetPixelMapuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 32);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glGetTexEnviv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTexEnviv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTexEnvfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTexEnvfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetTexGeniv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTexGeniv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTexGenfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTexGenfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetTexGendv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetTexGendv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") short[] sArr) {
        GL11C.glGetTexImage(i, i2, i3, i4, sArr);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") int[] iArr) {
        GL11C.glGetTexImage(i, i2, i3, i4, iArr);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") float[] fArr) {
        GL11C.glGetTexImage(i, i2, i3, i4, fArr);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") double[] dArr) {
        GL11C.glGetTexImage(i, i2, i3, i4, dArr);
    }

    public static void glGetTexLevelParameteriv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL11C.glGetTexLevelParameteriv(i, i2, i3, iArr);
    }

    public static void glGetTexLevelParameterfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        GL11C.glGetTexLevelParameterfv(i, i2, i3, fArr);
    }

    public static void glGetTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL11C.glGetTexParameteriv(i, i2, iArr);
    }

    public static void glGetTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL11C.glGetTexParameterfv(i, i2, fArr);
    }

    public static void glIndexiv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glIndexiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(iArr, j);
    }

    public static void glIndexsv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glIndexsv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(sArr, j);
    }

    public static void glIndexfv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glIndexfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(fArr, j);
    }

    public static void glIndexdv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glIndexdv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(dArr, j);
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glInterleavedArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, sArr, j);
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glInterleavedArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glInterleavedArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glInterleavedArrays(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glInterleavedArrays;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glLightModeliv(@NativeType("GLenum") int i, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glLightModeliv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glLightModelfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glLightModelfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glLightiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glLightiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glLightfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glLightfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glLoadMatrixf(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glLoadMatrixf;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(fArr, j);
    }

    public static void glLoadMatrixd(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glLoadMatrixd;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(dArr, j);
    }

    public static void glMap1f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMap1f;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, i3 * i2);
        }
        JNI.callPV(i, f, f2, i2, i3, fArr, j);
    }

    public static void glMap1d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMap1d;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, i2 * i3);
        }
        JNI.callPV(i, d, d2, i2, i3, dArr, j);
    }

    public static void glMap2f(@NativeType("GLenum") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMap2f;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, i2 * i3 * i4 * i5);
        }
        JNI.callPV(i, f, f2, i2, i3, f3, f4, i4, i5, fArr, j);
    }

    public static void glMap2d(@NativeType("GLenum") int i, @NativeType("GLdouble") double d, @NativeType("GLdouble") double d2, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLdouble") double d3, @NativeType("GLdouble") double d4, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMap2d;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, i2 * i3 * i4 * i5);
        }
        JNI.callPV(i, d, d2, i2, i3, d3, d4, i4, i5, dArr, j);
    }

    public static void glMaterialiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glMaterialiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glMaterialfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMaterialfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glMultMatrixf(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMultMatrixf;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 16);
        }
        JNI.callPV(fArr, j);
    }

    public static void glMultMatrixd(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glMultMatrixd;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 16);
        }
        JNI.callPV(dArr, j);
    }

    public static void glNormal3fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glNormal3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glNormal3sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glNormal3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glNormal3iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glNormal3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glNormal3dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glNormal3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }

    public static void glPixelMapfv(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPixelMapfv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length, fArr, j);
    }

    public static void glPixelMapusv(@NativeType("GLenum") int i, @NativeType("GLushort const *") short[] sArr) {
        long j = GL.getICD().glPixelMapusv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, sArr.length, sArr, j);
    }

    public static void glPixelMapuiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glPixelMapuiv;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, iArr.length, iArr, j);
    }

    public static void glPrioritizeTextures(@NativeType("GLuint const *") int[] iArr, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPrioritizeTextures;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, iArr.length);
        }
        JNI.callPPV(iArr.length, iArr, fArr, j);
    }

    public static void glRasterPos2iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glRasterPos2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(iArr, j);
    }

    public static void glRasterPos2sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glRasterPos2sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(sArr, j);
    }

    public static void glRasterPos2fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glRasterPos2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(fArr, j);
    }

    public static void glRasterPos2dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glRasterPos2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(dArr, j);
    }

    public static void glRasterPos3iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glRasterPos3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glRasterPos3sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glRasterPos3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glRasterPos3fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glRasterPos3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glRasterPos3dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glRasterPos3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }

    public static void glRasterPos4iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glRasterPos4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(iArr, j);
    }

    public static void glRasterPos4sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glRasterPos4sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glRasterPos4fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glRasterPos4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(fArr, j);
    }

    public static void glRasterPos4dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glRasterPos4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(dArr, j);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") short[] sArr) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, sArr);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") int[] iArr) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, iArr);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") float[] fArr) {
        GL11C.glReadPixels(i, i2, i3, i4, i5, i6, fArr);
    }

    public static void glRectiv(@NativeType("GLint const *") int[] iArr, @NativeType("GLint const *") int[] iArr2) {
        long j = GL.getICD().glRectiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
            Checks.check(iArr2, 2);
        }
        JNI.callPPV(iArr, iArr2, j);
    }

    public static void glRectsv(@NativeType("GLshort const *") short[] sArr, @NativeType("GLshort const *") short[] sArr2) {
        long j = GL.getICD().glRectsv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
            Checks.check(sArr2, 2);
        }
        JNI.callPPV(sArr, sArr2, j);
    }

    public static void glRectfv(@NativeType("GLfloat const *") float[] fArr, @NativeType("GLfloat const *") float[] fArr2) {
        long j = GL.getICD().glRectfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
            Checks.check(fArr2, 2);
        }
        JNI.callPPV(fArr, fArr2, j);
    }

    public static void glRectdv(@NativeType("GLdouble const *") double[] dArr, @NativeType("GLdouble const *") double[] dArr2) {
        long j = GL.getICD().glRectdv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
            Checks.check(dArr2, 2);
        }
        JNI.callPPV(dArr, dArr2, j);
    }

    public static void glSelectBuffer(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glSelectBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glTexCoord1fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTexCoord1fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(fArr, j);
    }

    public static void glTexCoord1sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glTexCoord1sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 1);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord1iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexCoord1iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(iArr, j);
    }

    public static void glTexCoord1dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glTexCoord1dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(dArr, j);
    }

    public static void glTexCoord2fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTexCoord2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(fArr, j);
    }

    public static void glTexCoord2sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glTexCoord2sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord2iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexCoord2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(iArr, j);
    }

    public static void glTexCoord2dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glTexCoord2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(dArr, j);
    }

    public static void glTexCoord3fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTexCoord3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glTexCoord3sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glTexCoord3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord3iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexCoord3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glTexCoord3dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glTexCoord3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }

    public static void glTexCoord4fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTexCoord4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(fArr, j);
    }

    public static void glTexCoord4sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glTexCoord4sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glTexCoord4iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexCoord4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(iArr, j);
    }

    public static void glTexCoord4dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glTexCoord4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(dArr, j);
    }

    public static void glTexEnviv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexEnviv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTexEnvfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTexEnvfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glTexGeniv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexGeniv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTexGenfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTexGenfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glTexGendv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glTexGendv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(i, i2, dArr, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") short[] sArr) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, sArr);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") int[] iArr) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, iArr);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") float[] fArr) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, fArr);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") double[] dArr) {
        GL11C.glTexImage1D(i, i2, i3, i4, i5, i6, i7, dArr);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, sArr);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, iArr);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, fArr);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        GL11C.glTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, dArr);
    }

    public static void glTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        GL11C.glTexParameteriv(i, i2, iArr);
    }

    public static void glTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        GL11C.glTexParameterfv(i, i2, fArr);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") short[] sArr) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, sArr);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") int[] iArr) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, iArr);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") float[] fArr) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, fArr);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") double[] dArr) {
        GL11C.glTexSubImage1D(i, i2, i3, i4, i5, i6, dArr);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, sArr);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, iArr);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, fArr);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        GL11C.glTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, dArr);
    }

    public static void glVertex2fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertex2fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(fArr, j);
    }

    public static void glVertex2sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertex2sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 2);
        }
        JNI.callPV(sArr, j);
    }

    public static void glVertex2iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertex2iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 2);
        }
        JNI.callPV(iArr, j);
    }

    public static void glVertex2dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertex2dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 2);
        }
        JNI.callPV(dArr, j);
    }

    public static void glVertex3fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertex3fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(fArr, j);
    }

    public static void glVertex3sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertex3sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 3);
        }
        JNI.callPV(sArr, j);
    }

    public static void glVertex3iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertex3iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 3);
        }
        JNI.callPV(iArr, j);
    }

    public static void glVertex3dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertex3dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 3);
        }
        JNI.callPV(dArr, j);
    }

    public static void glVertex4fv(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glVertex4fv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(fArr, j);
    }

    public static void glVertex4sv(@NativeType("GLshort const *") short[] sArr) {
        long j = GL.getICD().glVertex4sv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(sArr, 4);
        }
        JNI.callPV(sArr, j);
    }

    public static void glVertex4iv(@NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glVertex4iv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(iArr, j);
    }

    public static void glVertex4dv(@NativeType("GLdouble const *") double[] dArr) {
        long j = GL.getICD().glVertex4dv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 4);
        }
        JNI.callPV(dArr, j);
    }
}
