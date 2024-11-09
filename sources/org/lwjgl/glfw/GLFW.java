package org.lwjgl.glfw;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Configuration;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.Struct;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFW.class */
public class GLFW {
    private static final SharedLibrary GLFW = Library.loadNative((Class<?>) GLFW.class, "org.lwjgl.glfw", Configuration.GLFW_LIBRARY_NAME.get(Platform.mapLibraryNameBundled("glfw")), true);
    public static final int GLFW_VERSION_MAJOR = 3;
    public static final int GLFW_VERSION_MINOR = 4;
    public static final int GLFW_VERSION_REVISION = 0;
    public static final int GLFW_TRUE = 1;
    public static final int GLFW_FALSE = 0;
    public static final int GLFW_RELEASE = 0;
    public static final int GLFW_PRESS = 1;
    public static final int GLFW_REPEAT = 2;
    public static final int GLFW_HAT_CENTERED = 0;
    public static final int GLFW_HAT_UP = 1;
    public static final int GLFW_HAT_RIGHT = 2;
    public static final int GLFW_HAT_DOWN = 4;
    public static final int GLFW_HAT_LEFT = 8;
    public static final int GLFW_HAT_RIGHT_UP = 3;
    public static final int GLFW_HAT_RIGHT_DOWN = 6;
    public static final int GLFW_HAT_LEFT_UP = 9;
    public static final int GLFW_HAT_LEFT_DOWN = 12;
    public static final int GLFW_KEY_UNKNOWN = -1;
    public static final int GLFW_KEY_SPACE = 32;
    public static final int GLFW_KEY_APOSTROPHE = 39;
    public static final int GLFW_KEY_COMMA = 44;
    public static final int GLFW_KEY_MINUS = 45;
    public static final int GLFW_KEY_PERIOD = 46;
    public static final int GLFW_KEY_SLASH = 47;
    public static final int GLFW_KEY_0 = 48;
    public static final int GLFW_KEY_1 = 49;
    public static final int GLFW_KEY_2 = 50;
    public static final int GLFW_KEY_3 = 51;
    public static final int GLFW_KEY_4 = 52;
    public static final int GLFW_KEY_5 = 53;
    public static final int GLFW_KEY_6 = 54;
    public static final int GLFW_KEY_7 = 55;
    public static final int GLFW_KEY_8 = 56;
    public static final int GLFW_KEY_9 = 57;
    public static final int GLFW_KEY_SEMICOLON = 59;
    public static final int GLFW_KEY_EQUAL = 61;
    public static final int GLFW_KEY_A = 65;
    public static final int GLFW_KEY_B = 66;
    public static final int GLFW_KEY_C = 67;
    public static final int GLFW_KEY_D = 68;
    public static final int GLFW_KEY_E = 69;
    public static final int GLFW_KEY_F = 70;
    public static final int GLFW_KEY_G = 71;
    public static final int GLFW_KEY_H = 72;
    public static final int GLFW_KEY_I = 73;
    public static final int GLFW_KEY_J = 74;
    public static final int GLFW_KEY_K = 75;
    public static final int GLFW_KEY_L = 76;
    public static final int GLFW_KEY_M = 77;
    public static final int GLFW_KEY_N = 78;
    public static final int GLFW_KEY_O = 79;
    public static final int GLFW_KEY_P = 80;
    public static final int GLFW_KEY_Q = 81;
    public static final int GLFW_KEY_R = 82;
    public static final int GLFW_KEY_S = 83;
    public static final int GLFW_KEY_T = 84;
    public static final int GLFW_KEY_U = 85;
    public static final int GLFW_KEY_V = 86;
    public static final int GLFW_KEY_W = 87;
    public static final int GLFW_KEY_X = 88;
    public static final int GLFW_KEY_Y = 89;
    public static final int GLFW_KEY_Z = 90;
    public static final int GLFW_KEY_LEFT_BRACKET = 91;
    public static final int GLFW_KEY_BACKSLASH = 92;
    public static final int GLFW_KEY_RIGHT_BRACKET = 93;
    public static final int GLFW_KEY_GRAVE_ACCENT = 96;
    public static final int GLFW_KEY_WORLD_1 = 161;
    public static final int GLFW_KEY_WORLD_2 = 162;
    public static final int GLFW_KEY_ESCAPE = 256;
    public static final int GLFW_KEY_ENTER = 257;
    public static final int GLFW_KEY_TAB = 258;
    public static final int GLFW_KEY_BACKSPACE = 259;
    public static final int GLFW_KEY_INSERT = 260;
    public static final int GLFW_KEY_DELETE = 261;
    public static final int GLFW_KEY_RIGHT = 262;
    public static final int GLFW_KEY_LEFT = 263;
    public static final int GLFW_KEY_DOWN = 264;
    public static final int GLFW_KEY_UP = 265;
    public static final int GLFW_KEY_PAGE_UP = 266;
    public static final int GLFW_KEY_PAGE_DOWN = 267;
    public static final int GLFW_KEY_HOME = 268;
    public static final int GLFW_KEY_END = 269;
    public static final int GLFW_KEY_CAPS_LOCK = 280;
    public static final int GLFW_KEY_SCROLL_LOCK = 281;
    public static final int GLFW_KEY_NUM_LOCK = 282;
    public static final int GLFW_KEY_PRINT_SCREEN = 283;
    public static final int GLFW_KEY_PAUSE = 284;
    public static final int GLFW_KEY_F1 = 290;
    public static final int GLFW_KEY_F2 = 291;
    public static final int GLFW_KEY_F3 = 292;
    public static final int GLFW_KEY_F4 = 293;
    public static final int GLFW_KEY_F5 = 294;
    public static final int GLFW_KEY_F6 = 295;
    public static final int GLFW_KEY_F7 = 296;
    public static final int GLFW_KEY_F8 = 297;
    public static final int GLFW_KEY_F9 = 298;
    public static final int GLFW_KEY_F10 = 299;
    public static final int GLFW_KEY_F11 = 300;
    public static final int GLFW_KEY_F12 = 301;
    public static final int GLFW_KEY_F13 = 302;
    public static final int GLFW_KEY_F14 = 303;
    public static final int GLFW_KEY_F15 = 304;
    public static final int GLFW_KEY_F16 = 305;
    public static final int GLFW_KEY_F17 = 306;
    public static final int GLFW_KEY_F18 = 307;
    public static final int GLFW_KEY_F19 = 308;
    public static final int GLFW_KEY_F20 = 309;
    public static final int GLFW_KEY_F21 = 310;
    public static final int GLFW_KEY_F22 = 311;
    public static final int GLFW_KEY_F23 = 312;
    public static final int GLFW_KEY_F24 = 313;
    public static final int GLFW_KEY_F25 = 314;
    public static final int GLFW_KEY_KP_0 = 320;
    public static final int GLFW_KEY_KP_1 = 321;
    public static final int GLFW_KEY_KP_2 = 322;
    public static final int GLFW_KEY_KP_3 = 323;
    public static final int GLFW_KEY_KP_4 = 324;
    public static final int GLFW_KEY_KP_5 = 325;
    public static final int GLFW_KEY_KP_6 = 326;
    public static final int GLFW_KEY_KP_7 = 327;
    public static final int GLFW_KEY_KP_8 = 328;
    public static final int GLFW_KEY_KP_9 = 329;
    public static final int GLFW_KEY_KP_DECIMAL = 330;
    public static final int GLFW_KEY_KP_DIVIDE = 331;
    public static final int GLFW_KEY_KP_MULTIPLY = 332;
    public static final int GLFW_KEY_KP_SUBTRACT = 333;
    public static final int GLFW_KEY_KP_ADD = 334;
    public static final int GLFW_KEY_KP_ENTER = 335;
    public static final int GLFW_KEY_KP_EQUAL = 336;
    public static final int GLFW_KEY_LEFT_SHIFT = 340;
    public static final int GLFW_KEY_LEFT_CONTROL = 341;
    public static final int GLFW_KEY_LEFT_ALT = 342;
    public static final int GLFW_KEY_LEFT_SUPER = 343;
    public static final int GLFW_KEY_RIGHT_SHIFT = 344;
    public static final int GLFW_KEY_RIGHT_CONTROL = 345;
    public static final int GLFW_KEY_RIGHT_ALT = 346;
    public static final int GLFW_KEY_RIGHT_SUPER = 347;
    public static final int GLFW_KEY_MENU = 348;
    public static final int GLFW_KEY_LAST = 348;
    public static final int GLFW_MOD_SHIFT = 1;
    public static final int GLFW_MOD_CONTROL = 2;
    public static final int GLFW_MOD_ALT = 4;
    public static final int GLFW_MOD_SUPER = 8;
    public static final int GLFW_MOD_CAPS_LOCK = 16;
    public static final int GLFW_MOD_NUM_LOCK = 32;
    public static final int GLFW_MOUSE_BUTTON_1 = 0;
    public static final int GLFW_MOUSE_BUTTON_2 = 1;
    public static final int GLFW_MOUSE_BUTTON_3 = 2;
    public static final int GLFW_MOUSE_BUTTON_4 = 3;
    public static final int GLFW_MOUSE_BUTTON_5 = 4;
    public static final int GLFW_MOUSE_BUTTON_6 = 5;
    public static final int GLFW_MOUSE_BUTTON_7 = 6;
    public static final int GLFW_MOUSE_BUTTON_8 = 7;
    public static final int GLFW_MOUSE_BUTTON_LAST = 7;
    public static final int GLFW_MOUSE_BUTTON_LEFT = 0;
    public static final int GLFW_MOUSE_BUTTON_RIGHT = 1;
    public static final int GLFW_MOUSE_BUTTON_MIDDLE = 2;
    public static final int GLFW_JOYSTICK_1 = 0;
    public static final int GLFW_JOYSTICK_2 = 1;
    public static final int GLFW_JOYSTICK_3 = 2;
    public static final int GLFW_JOYSTICK_4 = 3;
    public static final int GLFW_JOYSTICK_5 = 4;
    public static final int GLFW_JOYSTICK_6 = 5;
    public static final int GLFW_JOYSTICK_7 = 6;
    public static final int GLFW_JOYSTICK_8 = 7;
    public static final int GLFW_JOYSTICK_9 = 8;
    public static final int GLFW_JOYSTICK_10 = 9;
    public static final int GLFW_JOYSTICK_11 = 10;
    public static final int GLFW_JOYSTICK_12 = 11;
    public static final int GLFW_JOYSTICK_13 = 12;
    public static final int GLFW_JOYSTICK_14 = 13;
    public static final int GLFW_JOYSTICK_15 = 14;
    public static final int GLFW_JOYSTICK_16 = 15;
    public static final int GLFW_JOYSTICK_LAST = 15;
    public static final int GLFW_GAMEPAD_BUTTON_A = 0;
    public static final int GLFW_GAMEPAD_BUTTON_B = 1;
    public static final int GLFW_GAMEPAD_BUTTON_X = 2;
    public static final int GLFW_GAMEPAD_BUTTON_Y = 3;
    public static final int GLFW_GAMEPAD_BUTTON_LEFT_BUMPER = 4;
    public static final int GLFW_GAMEPAD_BUTTON_RIGHT_BUMPER = 5;
    public static final int GLFW_GAMEPAD_BUTTON_BACK = 6;
    public static final int GLFW_GAMEPAD_BUTTON_START = 7;
    public static final int GLFW_GAMEPAD_BUTTON_GUIDE = 8;
    public static final int GLFW_GAMEPAD_BUTTON_LEFT_THUMB = 9;
    public static final int GLFW_GAMEPAD_BUTTON_RIGHT_THUMB = 10;
    public static final int GLFW_GAMEPAD_BUTTON_DPAD_UP = 11;
    public static final int GLFW_GAMEPAD_BUTTON_DPAD_RIGHT = 12;
    public static final int GLFW_GAMEPAD_BUTTON_DPAD_DOWN = 13;
    public static final int GLFW_GAMEPAD_BUTTON_DPAD_LEFT = 14;
    public static final int GLFW_GAMEPAD_BUTTON_LAST = 14;
    public static final int GLFW_GAMEPAD_BUTTON_CROSS = 0;
    public static final int GLFW_GAMEPAD_BUTTON_CIRCLE = 1;
    public static final int GLFW_GAMEPAD_BUTTON_SQUARE = 2;
    public static final int GLFW_GAMEPAD_BUTTON_TRIANGLE = 3;
    public static final int GLFW_GAMEPAD_AXIS_LEFT_X = 0;
    public static final int GLFW_GAMEPAD_AXIS_LEFT_Y = 1;
    public static final int GLFW_GAMEPAD_AXIS_RIGHT_X = 2;
    public static final int GLFW_GAMEPAD_AXIS_RIGHT_Y = 3;
    public static final int GLFW_GAMEPAD_AXIS_LEFT_TRIGGER = 4;
    public static final int GLFW_GAMEPAD_AXIS_RIGHT_TRIGGER = 5;
    public static final int GLFW_GAMEPAD_AXIS_LAST = 5;
    public static final int GLFW_NO_ERROR = 0;
    public static final int GLFW_NOT_INITIALIZED = 65537;
    public static final int GLFW_NO_CURRENT_CONTEXT = 65538;
    public static final int GLFW_INVALID_ENUM = 65539;
    public static final int GLFW_INVALID_VALUE = 65540;
    public static final int GLFW_OUT_OF_MEMORY = 65541;
    public static final int GLFW_API_UNAVAILABLE = 65542;
    public static final int GLFW_VERSION_UNAVAILABLE = 65543;
    public static final int GLFW_PLATFORM_ERROR = 65544;
    public static final int GLFW_FORMAT_UNAVAILABLE = 65545;
    public static final int GLFW_NO_WINDOW_CONTEXT = 65546;
    public static final int GLFW_CURSOR_UNAVAILABLE = 65547;
    public static final int GLFW_FEATURE_UNAVAILABLE = 65548;
    public static final int GLFW_FEATURE_UNIMPLEMENTED = 65549;
    public static final int GLFW_PLATFORM_UNAVAILABLE = 65550;
    public static final int GLFW_FOCUSED = 131073;
    public static final int GLFW_ICONIFIED = 131074;
    public static final int GLFW_RESIZABLE = 131075;
    public static final int GLFW_VISIBLE = 131076;
    public static final int GLFW_DECORATED = 131077;
    public static final int GLFW_AUTO_ICONIFY = 131078;
    public static final int GLFW_FLOATING = 131079;
    public static final int GLFW_MAXIMIZED = 131080;
    public static final int GLFW_CENTER_CURSOR = 131081;
    public static final int GLFW_TRANSPARENT_FRAMEBUFFER = 131082;
    public static final int GLFW_HOVERED = 131083;
    public static final int GLFW_FOCUS_ON_SHOW = 131084;
    public static final int GLFW_MOUSE_PASSTHROUGH = 131085;
    public static final int GLFW_POSITION_X = 131086;
    public static final int GLFW_POSITION_Y = 131087;
    public static final int GLFW_SOFT_FULLSCREEN = 131088;
    public static final int GLFW_CURSOR = 208897;
    public static final int GLFW_STICKY_KEYS = 208898;
    public static final int GLFW_STICKY_MOUSE_BUTTONS = 208899;
    public static final int GLFW_LOCK_KEY_MODS = 208900;
    public static final int GLFW_RAW_MOUSE_MOTION = 208901;
    public static final int GLFW_UNLIMITED_MOUSE_BUTTONS = 208902;
    public static final int GLFW_IME = 208903;
    public static final int GLFW_CURSOR_NORMAL = 212993;
    public static final int GLFW_CURSOR_HIDDEN = 212994;
    public static final int GLFW_CURSOR_DISABLED = 212995;
    public static final int GLFW_CURSOR_CAPTURED = 212996;
    public static final int GLFW_ARROW_CURSOR = 221185;
    public static final int GLFW_IBEAM_CURSOR = 221186;
    public static final int GLFW_CROSSHAIR_CURSOR = 221187;
    public static final int GLFW_POINTING_HAND_CURSOR = 221188;
    public static final int GLFW_RESIZE_EW_CURSOR = 221189;
    public static final int GLFW_RESIZE_NS_CURSOR = 221190;
    public static final int GLFW_RESIZE_NWSE_CURSOR = 221191;
    public static final int GLFW_RESIZE_NESW_CURSOR = 221192;
    public static final int GLFW_RESIZE_ALL_CURSOR = 221193;
    public static final int GLFW_NOT_ALLOWED_CURSOR = 221194;
    public static final int GLFW_HRESIZE_CURSOR = 221189;
    public static final int GLFW_VRESIZE_CURSOR = 221190;
    public static final int GLFW_HAND_CURSOR = 221188;
    public static final int GLFW_CONNECTED = 262145;
    public static final int GLFW_DISCONNECTED = 262146;
    public static final int GLFW_JOYSTICK_HAT_BUTTONS = 327681;
    public static final int GLFW_ANGLE_PLATFORM_TYPE = 327682;
    public static final int GLFW_ANY_POSITION = Integer.MIN_VALUE;
    public static final int GLFW_PLATFORM = 327683;
    public static final int GLFW_MANAGE_PREEDIT_CANDIDATE = 327684;
    public static final int GLFW_COCOA_CHDIR_RESOURCES = 331777;
    public static final int GLFW_COCOA_MENUBAR = 331778;
    public static final int GLFW_X11_XCB_VULKAN_SURFACE = 335873;
    public static final int GLFW_X11_ONTHESPOT = 335874;
    public static final int GLFW_WAYLAND_LIBDECOR = 339969;
    public static final int GLFW_ANY_PLATFORM = 393216;
    public static final int GLFW_PLATFORM_WIN32 = 393217;
    public static final int GLFW_PLATFORM_COCOA = 393218;
    public static final int GLFW_PLATFORM_WAYLAND = 393219;
    public static final int GLFW_PLATFORM_X11 = 393220;
    public static final int GLFW_PLATFORM_NULL = 393221;
    public static final int GLFW_DONT_CARE = -1;
    public static final int GLFW_RED_BITS = 135169;
    public static final int GLFW_GREEN_BITS = 135170;
    public static final int GLFW_BLUE_BITS = 135171;
    public static final int GLFW_ALPHA_BITS = 135172;
    public static final int GLFW_DEPTH_BITS = 135173;
    public static final int GLFW_STENCIL_BITS = 135174;
    public static final int GLFW_ACCUM_RED_BITS = 135175;
    public static final int GLFW_ACCUM_GREEN_BITS = 135176;
    public static final int GLFW_ACCUM_BLUE_BITS = 135177;
    public static final int GLFW_ACCUM_ALPHA_BITS = 135178;
    public static final int GLFW_AUX_BUFFERS = 135179;
    public static final int GLFW_STEREO = 135180;
    public static final int GLFW_SAMPLES = 135181;
    public static final int GLFW_SRGB_CAPABLE = 135182;
    public static final int GLFW_REFRESH_RATE = 135183;
    public static final int GLFW_DOUBLEBUFFER = 135184;
    public static final int GLFW_CLIENT_API = 139265;
    public static final int GLFW_CONTEXT_VERSION_MAJOR = 139266;
    public static final int GLFW_CONTEXT_VERSION_MINOR = 139267;
    public static final int GLFW_CONTEXT_REVISION = 139268;
    public static final int GLFW_CONTEXT_ROBUSTNESS = 139269;
    public static final int GLFW_OPENGL_FORWARD_COMPAT = 139270;
    public static final int GLFW_CONTEXT_DEBUG = 139271;
    public static final int GLFW_OPENGL_DEBUG_CONTEXT = 139271;
    public static final int GLFW_OPENGL_PROFILE = 139272;
    public static final int GLFW_CONTEXT_RELEASE_BEHAVIOR = 139273;
    public static final int GLFW_CONTEXT_NO_ERROR = 139274;
    public static final int GLFW_CONTEXT_CREATION_API = 139275;
    public static final int GLFW_SCALE_TO_MONITOR = 139276;
    public static final int GLFW_SCALE_FRAMEBUFFER = 139277;
    public static final int GLFW_COCOA_RETINA_FRAMEBUFFER = 143361;
    public static final int GLFW_COCOA_FRAME_NAME = 143362;
    public static final int GLFW_COCOA_GRAPHICS_SWITCHING = 143363;
    public static final int GLFW_X11_CLASS_NAME = 147457;
    public static final int GLFW_X11_INSTANCE_NAME = 147458;
    public static final int GLFW_WIN32_KEYBOARD_MENU = 151553;
    public static final int GLFW_WIN32_SHOWDEFAULT = 151554;
    public static final int GLFW_WAYLAND_APP_ID = 155649;
    public static final int GLFW_NO_API = 0;
    public static final int GLFW_OPENGL_API = 196609;
    public static final int GLFW_OPENGL_ES_API = 196610;
    public static final int GLFW_NO_ROBUSTNESS = 0;
    public static final int GLFW_NO_RESET_NOTIFICATION = 200705;
    public static final int GLFW_LOSE_CONTEXT_ON_RESET = 200706;
    public static final int GLFW_OPENGL_ANY_PROFILE = 0;
    public static final int GLFW_OPENGL_CORE_PROFILE = 204801;
    public static final int GLFW_OPENGL_COMPAT_PROFILE = 204802;
    public static final int GLFW_ANY_RELEASE_BEHAVIOR = 0;
    public static final int GLFW_RELEASE_BEHAVIOR_FLUSH = 217089;
    public static final int GLFW_RELEASE_BEHAVIOR_NONE = 217090;
    public static final int GLFW_NATIVE_CONTEXT_API = 221185;
    public static final int GLFW_EGL_CONTEXT_API = 221186;
    public static final int GLFW_OSMESA_CONTEXT_API = 221187;
    public static final int GLFW_ANGLE_PLATFORM_TYPE_NONE = 225281;
    public static final int GLFW_ANGLE_PLATFORM_TYPE_OPENGL = 225282;
    public static final int GLFW_ANGLE_PLATFORM_TYPE_OPENGLES = 225283;
    public static final int GLFW_ANGLE_PLATFORM_TYPE_D3D9 = 225284;
    public static final int GLFW_ANGLE_PLATFORM_TYPE_D3D11 = 225285;
    public static final int GLFW_ANGLE_PLATFORM_TYPE_VULKAN = 225287;
    public static final int GLFW_ANGLE_PLATFORM_TYPE_METAL = 225288;
    public static final int GLFW_WAYLAND_PREFER_LIBDECOR = 229377;
    public static final int GLFW_WAYLAND_DISABLE_LIBDECOR = 229378;

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFW$Functions.class */
    public static final class Functions {
        public static final long Init = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwInit");
        public static final long Terminate = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwTerminate");
        public static final long InitHint = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwInitHint");
        public static final long InitAllocator = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwInitAllocator");
        public static final long GetVersion = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetVersion");
        public static final long GetVersionString = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetVersionString");
        public static final long GetError = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetError");
        public static final long SetErrorCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetErrorCallback");
        public static final long GetPlatform = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetPlatform");
        public static final long PlatformSupported = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwPlatformSupported");
        public static final long GetMonitors = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMonitors");
        public static final long GetPrimaryMonitor = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetPrimaryMonitor");
        public static final long GetMonitorPos = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMonitorPos");
        public static final long GetMonitorWorkarea = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMonitorWorkarea");
        public static final long GetMonitorPhysicalSize = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMonitorPhysicalSize");
        public static final long GetMonitorContentScale = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMonitorContentScale");
        public static final long GetMonitorName = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMonitorName");
        public static final long SetMonitorUserPointer = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetMonitorUserPointer");
        public static final long GetMonitorUserPointer = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMonitorUserPointer");
        public static final long SetMonitorCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetMonitorCallback");
        public static final long GetVideoModes = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetVideoModes");
        public static final long GetVideoMode = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetVideoMode");
        public static final long SetGamma = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetGamma");
        public static final long GetGammaRamp = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetGammaRamp");
        public static final long SetGammaRamp = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetGammaRamp");
        public static final long DefaultWindowHints = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwDefaultWindowHints");
        public static final long WindowHint = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwWindowHint");
        public static final long WindowHintString = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwWindowHintString");
        public static final long CreateWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwCreateWindow");
        public static final long DestroyWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwDestroyWindow");
        public static final long WindowShouldClose = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwWindowShouldClose");
        public static final long SetWindowShouldClose = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowShouldClose");
        public static final long GetWindowTitle = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowTitle");
        public static final long SetWindowTitle = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowTitle");
        public static final long SetWindowIcon = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowIcon");
        public static final long GetWindowPos = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowPos");
        public static final long SetWindowPos = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowPos");
        public static final long GetWindowSize = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowSize");
        public static final long SetWindowSizeLimits = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowSizeLimits");
        public static final long SetWindowAspectRatio = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowAspectRatio");
        public static final long SetWindowSize = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowSize");
        public static final long GetFramebufferSize = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetFramebufferSize");
        public static final long GetWindowFrameSize = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowFrameSize");
        public static final long GetWindowContentScale = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowContentScale");
        public static final long GetWindowOpacity = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowOpacity");
        public static final long SetWindowOpacity = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowOpacity");
        public static final long IconifyWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwIconifyWindow");
        public static final long RestoreWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwRestoreWindow");
        public static final long MaximizeWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwMaximizeWindow");
        public static final long ShowWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwShowWindow");
        public static final long HideWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwHideWindow");
        public static final long FocusWindow = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwFocusWindow");
        public static final long RequestWindowAttention = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwRequestWindowAttention");
        public static final long GetWindowMonitor = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowMonitor");
        public static final long SetWindowMonitor = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowMonitor");
        public static final long GetWindowAttrib = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowAttrib");
        public static final long SetWindowAttrib = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowAttrib");
        public static final long SetWindowUserPointer = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowUserPointer");
        public static final long GetWindowUserPointer = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetWindowUserPointer");
        public static final long SetWindowPosCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowPosCallback");
        public static final long SetWindowSizeCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowSizeCallback");
        public static final long SetWindowCloseCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowCloseCallback");
        public static final long SetWindowRefreshCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowRefreshCallback");
        public static final long SetWindowFocusCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowFocusCallback");
        public static final long SetWindowIconifyCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowIconifyCallback");
        public static final long SetWindowMaximizeCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowMaximizeCallback");
        public static final long SetFramebufferSizeCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetFramebufferSizeCallback");
        public static final long SetWindowContentScaleCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetWindowContentScaleCallback");
        public static final long PollEvents = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwPollEvents");
        public static final long WaitEvents = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwWaitEvents");
        public static final long WaitEventsTimeout = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwWaitEventsTimeout");
        public static final long PostEmptyEvent = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwPostEmptyEvent");
        public static final long GetInputMode = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetInputMode");
        public static final long SetInputMode = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetInputMode");
        public static final long RawMouseMotionSupported = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwRawMouseMotionSupported");
        public static final long GetKeyName = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetKeyName");
        public static final long GetKeyScancode = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetKeyScancode");
        public static final long GetKey = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetKey");
        public static final long GetMouseButton = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetMouseButton");
        public static final long GetCursorPos = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetCursorPos");
        public static final long SetCursorPos = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetCursorPos");
        public static final long CreateCursor = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwCreateCursor");
        public static final long CreateStandardCursor = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwCreateStandardCursor");
        public static final long DestroyCursor = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwDestroyCursor");
        public static final long SetCursor = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetCursor");
        public static final long GetPreeditCursorRectangle = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetPreeditCursorRectangle");
        public static final long SetPreeditCursorRectangle = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetPreeditCursorRectangle");
        public static final long ResetPreeditText = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwResetPreeditText");
        public static final long GetPreeditCandidate = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetPreeditCandidate");
        public static final long SetKeyCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetKeyCallback");
        public static final long SetCharCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetCharCallback");
        public static final long SetCharModsCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetCharModsCallback");
        public static final long SetPreeditCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetPreeditCallback");
        public static final long SetIMEStatusCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetIMEStatusCallback");
        public static final long SetPreeditCandidateCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetPreeditCandidateCallback");
        public static final long SetMouseButtonCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetMouseButtonCallback");
        public static final long SetCursorPosCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetCursorPosCallback");
        public static final long SetCursorEnterCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetCursorEnterCallback");
        public static final long SetScrollCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetScrollCallback");
        public static final long SetDropCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetDropCallback");
        public static final long JoystickPresent = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwJoystickPresent");
        public static final long GetJoystickAxes = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetJoystickAxes");
        public static final long GetJoystickButtons = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetJoystickButtons");
        public static final long GetJoystickHats = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetJoystickHats");
        public static final long GetJoystickName = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetJoystickName");
        public static final long GetJoystickGUID = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetJoystickGUID");
        public static final long SetJoystickUserPointer = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetJoystickUserPointer");
        public static final long GetJoystickUserPointer = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetJoystickUserPointer");
        public static final long JoystickIsGamepad = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwJoystickIsGamepad");
        public static final long SetJoystickCallback = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetJoystickCallback");
        public static final long UpdateGamepadMappings = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwUpdateGamepadMappings");
        public static final long GetGamepadName = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetGamepadName");
        public static final long GetGamepadState = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetGamepadState");
        public static final long SetClipboardString = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetClipboardString");
        public static final long GetClipboardString = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetClipboardString");
        public static final long GetTime = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetTime");
        public static final long SetTime = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSetTime");
        public static final long GetTimerValue = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetTimerValue");
        public static final long GetTimerFrequency = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetTimerFrequency");
        public static final long MakeContextCurrent = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwMakeContextCurrent");
        public static final long GetCurrentContext = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetCurrentContext");
        public static final long SwapBuffers = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSwapBuffers");
        public static final long SwapInterval = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwSwapInterval");
        public static final long ExtensionSupported = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwExtensionSupported");
        public static final long GetProcAddress = APIUtil.apiGetFunctionAddress(GLFW.GLFW, "glfwGetProcAddress");

        private Functions() {
        }
    }

    public static SharedLibrary getLibrary() {
        return GLFW;
    }

    protected GLFW() {
        throw new UnsupportedOperationException();
    }

    @NativeType("int")
    public static boolean glfwInit() {
        long j = Functions.Init;
        EventLoop.check();
        return JNI.invokeI(j) != 0;
    }

    public static void glfwTerminate() {
        JNI.invokeV(Functions.Terminate);
    }

    public static void glfwInitHint(int i, int i2) {
        JNI.invokeV(i, i2, Functions.InitHint);
    }

    public static void nglfwInitAllocator(long j) {
        long j2 = Functions.InitAllocator;
        if (Checks.CHECKS && j != 0) {
            GLFWAllocator.validate(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwInitAllocator(@NativeType("GLFWallocator const *") GLFWAllocator gLFWAllocator) {
        nglfwInitAllocator(MemoryUtil.memAddressSafe(gLFWAllocator));
    }

    public static void nglfwGetVersion(long j, long j2, long j3) {
        JNI.invokePPPV(j, j2, j3, Functions.GetVersion);
    }

    public static void glfwGetVersion(@NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
        }
        nglfwGetVersion(MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3));
    }

    public static long nglfwGetVersionString() {
        return JNI.invokeP(Functions.GetVersionString);
    }

    @NativeType("char const *")
    public static String glfwGetVersionString() {
        return MemoryUtil.memASCII(nglfwGetVersionString());
    }

    public static int nglfwGetError(long j) {
        return JNI.invokePI(j, Functions.GetError);
    }

    public static int glfwGetError(@NativeType("char const **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nglfwGetError(MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static long nglfwSetErrorCallback(long j) {
        return JNI.invokePP(j, Functions.SetErrorCallback);
    }

    @NativeType("GLFWerrorfun")
    public static GLFWErrorCallback glfwSetErrorCallback(@NativeType("GLFWerrorfun") GLFWErrorCallbackI gLFWErrorCallbackI) {
        return GLFWErrorCallback.createSafe(nglfwSetErrorCallback(MemoryUtil.memAddressSafe(gLFWErrorCallbackI)));
    }

    public static int glfwGetPlatform() {
        return JNI.invokeI(Functions.GetPlatform);
    }

    @NativeType("int")
    public static boolean glfwPlatformSupported(int i) {
        return JNI.invokeI(i, Functions.PlatformSupported) != 0;
    }

    public static long nglfwGetMonitors(long j) {
        return JNI.invokePP(j, Functions.GetMonitors);
    }

    @NativeType("GLFWmonitor **")
    public static PointerBuffer glfwGetMonitors() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nglfwGetMonitors(MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLFWmonitor *")
    public static long glfwGetPrimaryMonitor() {
        return JNI.invokeP(Functions.GetPrimaryMonitor);
    }

    public static void nglfwGetMonitorPos(long j, long j2, long j3) {
        long j4 = Functions.GetMonitorPos;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetMonitorPos(@NativeType("GLFWmonitor *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglfwGetMonitorPos(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void nglfwGetMonitorWorkarea(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.GetMonitorWorkarea;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPPPV(j, j2, j3, j4, j5, j6);
    }

    public static void glfwGetMonitorWorkarea(@NativeType("GLFWmonitor *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        nglfwGetMonitorWorkarea(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4));
    }

    public static void nglfwGetMonitorPhysicalSize(long j, long j2, long j3) {
        long j4 = Functions.GetMonitorPhysicalSize;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetMonitorPhysicalSize(@NativeType("GLFWmonitor *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglfwGetMonitorPhysicalSize(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void nglfwGetMonitorContentScale(long j, long j2, long j3) {
        long j4 = Functions.GetMonitorContentScale;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetMonitorContentScale(@NativeType("GLFWmonitor *") long j, @NativeType("float *") FloatBuffer floatBuffer, @NativeType("float *") FloatBuffer floatBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) floatBuffer, 1);
            Checks.checkSafe((Buffer) floatBuffer2, 1);
        }
        nglfwGetMonitorContentScale(j, MemoryUtil.memAddressSafe(floatBuffer), MemoryUtil.memAddressSafe(floatBuffer2));
    }

    public static long nglfwGetMonitorName(long j) {
        long j2 = Functions.GetMonitorName;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String glfwGetMonitorName(@NativeType("GLFWmonitor *") long j) {
        return MemoryUtil.memUTF8Safe(nglfwGetMonitorName(j));
    }

    public static void glfwSetMonitorUserPointer(@NativeType("GLFWmonitor *") long j, @NativeType("void *") long j2) {
        long j3 = Functions.SetMonitorUserPointer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.invokePPV(j, j2, j3);
    }

    @NativeType("void *")
    public static long glfwGetMonitorUserPointer(@NativeType("GLFWmonitor *") long j) {
        long j2 = Functions.GetMonitorUserPointer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static long nglfwSetMonitorCallback(long j) {
        return JNI.invokePP(j, Functions.SetMonitorCallback);
    }

    @NativeType("GLFWmonitorfun")
    public static GLFWMonitorCallback glfwSetMonitorCallback(@NativeType("GLFWmonitorfun") GLFWMonitorCallbackI gLFWMonitorCallbackI) {
        return GLFWMonitorCallback.createSafe(nglfwSetMonitorCallback(MemoryUtil.memAddressSafe(gLFWMonitorCallbackI)));
    }

    public static long nglfwGetVideoModes(long j, long j2) {
        long j3 = Functions.GetVideoModes;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWvidmode const *")
    public static GLFWVidMode.Buffer glfwGetVideoModes(@NativeType("GLFWmonitor *") long j) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return GLFWVidMode.createSafe(nglfwGetVideoModes(j, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetVideoMode(long j) {
        long j2 = Functions.GetVideoMode;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("GLFWvidmode const *")
    public static GLFWVidMode glfwGetVideoMode(@NativeType("GLFWmonitor *") long j) {
        return GLFWVidMode.createSafe(nglfwGetVideoMode(j));
    }

    public static void glfwSetGamma(@NativeType("GLFWmonitor *") long j, float f) {
        long j2 = Functions.SetGamma;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, f, j2);
    }

    public static long nglfwGetGammaRamp(long j) {
        long j2 = Functions.GetGammaRamp;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("GLFWgammaramp const *")
    public static GLFWGammaRamp glfwGetGammaRamp(@NativeType("GLFWmonitor *") long j) {
        return GLFWGammaRamp.createSafe(nglfwGetGammaRamp(j));
    }

    public static void nglfwSetGammaRamp(long j, long j2) {
        long j3 = Functions.SetGammaRamp;
        if (Checks.CHECKS) {
            Checks.check(j);
            GLFWGammaRamp.validate(j2);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static void glfwSetGammaRamp(@NativeType("GLFWmonitor *") long j, @NativeType("GLFWgammaramp const *") GLFWGammaRamp gLFWGammaRamp) {
        nglfwSetGammaRamp(j, gLFWGammaRamp.address());
    }

    public static void glfwDefaultWindowHints() {
        JNI.invokeV(Functions.DefaultWindowHints);
    }

    public static void glfwWindowHint(int i, int i2) {
        JNI.invokeV(i, i2, Functions.WindowHint);
    }

    public static void nglfwWindowHintString(int i, long j) {
        JNI.invokePV(i, j, Functions.WindowHintString);
    }

    public static void glfwWindowHintString(int i, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglfwWindowHintString(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glfwWindowHintString(int i, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nglfwWindowHintString(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwCreateWindow(int i, int i2, long j, long j2, long j3) {
        return JNI.invokePPPP(i, i2, j, j2, j3, Functions.CreateWindow);
    }

    @NativeType("GLFWwindow *")
    public static long glfwCreateWindow(int i, int i2, @NativeType("char const *") ByteBuffer byteBuffer, @NativeType("GLFWmonitor *") long j, @NativeType("GLFWwindow *") long j2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglfwCreateWindow(i, i2, MemoryUtil.memAddress(byteBuffer), j, j2);
    }

    @NativeType("GLFWwindow *")
    public static long glfwCreateWindow(int i, int i2, @NativeType("char const *") CharSequence charSequence, @NativeType("GLFWmonitor *") long j, @NativeType("GLFWwindow *") long j2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nglfwCreateWindow(i, i2, stackGet.getPointerAddress(), j, j2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glfwDestroyWindow(@NativeType("GLFWwindow *") long j) {
        JNI.invokePV(j, Functions.DestroyWindow);
    }

    @NativeType("int")
    public static boolean glfwWindowShouldClose(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.WindowShouldClose;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, j2) != 0;
    }

    public static void glfwSetWindowShouldClose(@NativeType("GLFWwindow *") long j, @NativeType("int") boolean z) {
        long j2 = Functions.SetWindowShouldClose;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, z ? 1 : 0, j2);
    }

    public static long nglfwGetWindowTitle(long j) {
        long j2 = Functions.GetWindowTitle;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String glfwGetWindowTitle(@NativeType("GLFWwindow *") long j) {
        return MemoryUtil.memUTF8Safe(nglfwGetWindowTitle(j));
    }

    public static void nglfwSetWindowTitle(long j, long j2) {
        long j3 = Functions.SetWindowTitle;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static void glfwSetWindowTitle(@NativeType("GLFWwindow *") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglfwSetWindowTitle(j, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glfwSetWindowTitle(@NativeType("GLFWwindow *") long j, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nglfwSetWindowTitle(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nglfwSetWindowIcon(long j, int i, long j2) {
        long j3 = Functions.SetWindowIcon;
        if (Checks.CHECKS) {
            Checks.check(j);
            if (j2 != 0) {
                Struct.validate(j2, i, GLFWImage.SIZEOF, GLFWImage::validate);
            }
        }
        JNI.invokePPV(j, i, j2, j3);
    }

    public static void glfwSetWindowIcon(@NativeType("GLFWwindow *") long j, @NativeType("GLFWimage const *") GLFWImage.Buffer buffer) {
        nglfwSetWindowIcon(j, Checks.remainingSafe(buffer), MemoryUtil.memAddressSafe(buffer));
    }

    public static void nglfwGetWindowPos(long j, long j2, long j3) {
        long j4 = Functions.GetWindowPos;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetWindowPos(@NativeType("GLFWwindow *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglfwGetWindowPos(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void glfwSetWindowPos(@NativeType("GLFWwindow *") long j, int i, int i2) {
        long j2 = Functions.SetWindowPos;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, i2, j2);
    }

    public static void nglfwGetWindowSize(long j, long j2, long j3) {
        long j4 = Functions.GetWindowSize;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetWindowSize(@NativeType("GLFWwindow *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglfwGetWindowSize(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void glfwSetWindowSizeLimits(@NativeType("GLFWwindow *") long j, int i, int i2, int i3, int i4) {
        long j2 = Functions.SetWindowSizeLimits;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, i2, i3, i4, j2);
    }

    public static void glfwSetWindowAspectRatio(@NativeType("GLFWwindow *") long j, int i, int i2) {
        long j2 = Functions.SetWindowAspectRatio;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, i2, j2);
    }

    public static void glfwSetWindowSize(@NativeType("GLFWwindow *") long j, int i, int i2) {
        long j2 = Functions.SetWindowSize;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, i2, j2);
    }

    public static void nglfwGetFramebufferSize(long j, long j2, long j3) {
        long j4 = Functions.GetFramebufferSize;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetFramebufferSize(@NativeType("GLFWwindow *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglfwGetFramebufferSize(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2));
    }

    public static void nglfwGetWindowFrameSize(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.GetWindowFrameSize;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPPPV(j, j2, j3, j4, j5, j6);
    }

    public static void glfwGetWindowFrameSize(@NativeType("GLFWwindow *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        nglfwGetWindowFrameSize(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4));
    }

    public static void nglfwGetWindowContentScale(long j, long j2, long j3) {
        long j4 = Functions.GetWindowContentScale;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetWindowContentScale(@NativeType("GLFWwindow *") long j, @NativeType("float *") FloatBuffer floatBuffer, @NativeType("float *") FloatBuffer floatBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) floatBuffer, 1);
            Checks.checkSafe((Buffer) floatBuffer2, 1);
        }
        nglfwGetWindowContentScale(j, MemoryUtil.memAddressSafe(floatBuffer), MemoryUtil.memAddressSafe(floatBuffer2));
    }

    public static float glfwGetWindowOpacity(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetWindowOpacity;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePF(j, j2);
    }

    public static void glfwSetWindowOpacity(@NativeType("GLFWwindow *") long j, float f) {
        long j2 = Functions.SetWindowOpacity;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, f, j2);
    }

    public static void glfwIconifyWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.IconifyWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwRestoreWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.RestoreWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwMaximizeWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.MaximizeWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwShowWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.ShowWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwHideWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.HideWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwFocusWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.FocusWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwRequestWindowAttention(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.RequestWindowAttention;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    @NativeType("GLFWmonitor *")
    public static long glfwGetWindowMonitor(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetWindowMonitor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static void glfwSetWindowMonitor(@NativeType("GLFWwindow *") long j, @NativeType("GLFWmonitor *") long j2, int i, int i2, int i3, int i4, int i5) {
        long j3 = Functions.SetWindowMonitor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, i, i2, i3, i4, i5, j3);
    }

    public static int glfwGetWindowAttrib(@NativeType("GLFWwindow *") long j, int i) {
        long j2 = Functions.GetWindowAttrib;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, i, j2);
    }

    public static void glfwSetWindowAttrib(@NativeType("GLFWwindow *") long j, int i, int i2) {
        long j2 = Functions.SetWindowAttrib;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, i2, j2);
    }

    public static void glfwSetWindowUserPointer(@NativeType("GLFWwindow *") long j, @NativeType("void *") long j2) {
        long j3 = Functions.SetWindowUserPointer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, j3);
    }

    @NativeType("void *")
    public static long glfwGetWindowUserPointer(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetWindowUserPointer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static long nglfwSetWindowPosCallback(long j, long j2) {
        long j3 = Functions.SetWindowPosCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowposfun")
    public static GLFWWindowPosCallback glfwSetWindowPosCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowposfun") GLFWWindowPosCallbackI gLFWWindowPosCallbackI) {
        return GLFWWindowPosCallback.createSafe(nglfwSetWindowPosCallback(j, MemoryUtil.memAddressSafe(gLFWWindowPosCallbackI)));
    }

    public static long nglfwSetWindowSizeCallback(long j, long j2) {
        long j3 = Functions.SetWindowSizeCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowsizefun")
    public static GLFWWindowSizeCallback glfwSetWindowSizeCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowsizefun") GLFWWindowSizeCallbackI gLFWWindowSizeCallbackI) {
        return GLFWWindowSizeCallback.createSafe(nglfwSetWindowSizeCallback(j, MemoryUtil.memAddressSafe(gLFWWindowSizeCallbackI)));
    }

    public static long nglfwSetWindowCloseCallback(long j, long j2) {
        long j3 = Functions.SetWindowCloseCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowclosefun")
    public static GLFWWindowCloseCallback glfwSetWindowCloseCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowclosefun") GLFWWindowCloseCallbackI gLFWWindowCloseCallbackI) {
        return GLFWWindowCloseCallback.createSafe(nglfwSetWindowCloseCallback(j, MemoryUtil.memAddressSafe(gLFWWindowCloseCallbackI)));
    }

    public static long nglfwSetWindowRefreshCallback(long j, long j2) {
        long j3 = Functions.SetWindowRefreshCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowrefreshfun")
    public static GLFWWindowRefreshCallback glfwSetWindowRefreshCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowrefreshfun") GLFWWindowRefreshCallbackI gLFWWindowRefreshCallbackI) {
        return GLFWWindowRefreshCallback.createSafe(nglfwSetWindowRefreshCallback(j, MemoryUtil.memAddressSafe(gLFWWindowRefreshCallbackI)));
    }

    public static long nglfwSetWindowFocusCallback(long j, long j2) {
        long j3 = Functions.SetWindowFocusCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowfocusfun")
    public static GLFWWindowFocusCallback glfwSetWindowFocusCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowfocusfun") GLFWWindowFocusCallbackI gLFWWindowFocusCallbackI) {
        return GLFWWindowFocusCallback.createSafe(nglfwSetWindowFocusCallback(j, MemoryUtil.memAddressSafe(gLFWWindowFocusCallbackI)));
    }

    public static long nglfwSetWindowIconifyCallback(long j, long j2) {
        long j3 = Functions.SetWindowIconifyCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowiconifyfun")
    public static GLFWWindowIconifyCallback glfwSetWindowIconifyCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowiconifyfun") GLFWWindowIconifyCallbackI gLFWWindowIconifyCallbackI) {
        return GLFWWindowIconifyCallback.createSafe(nglfwSetWindowIconifyCallback(j, MemoryUtil.memAddressSafe(gLFWWindowIconifyCallbackI)));
    }

    public static long nglfwSetWindowMaximizeCallback(long j, long j2) {
        long j3 = Functions.SetWindowMaximizeCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowmaximizefun")
    public static GLFWWindowMaximizeCallback glfwSetWindowMaximizeCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowmaximizefun") GLFWWindowMaximizeCallbackI gLFWWindowMaximizeCallbackI) {
        return GLFWWindowMaximizeCallback.createSafe(nglfwSetWindowMaximizeCallback(j, MemoryUtil.memAddressSafe(gLFWWindowMaximizeCallbackI)));
    }

    public static long nglfwSetFramebufferSizeCallback(long j, long j2) {
        long j3 = Functions.SetFramebufferSizeCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWframebuffersizefun")
    public static GLFWFramebufferSizeCallback glfwSetFramebufferSizeCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWframebuffersizefun") GLFWFramebufferSizeCallbackI gLFWFramebufferSizeCallbackI) {
        return GLFWFramebufferSizeCallback.createSafe(nglfwSetFramebufferSizeCallback(j, MemoryUtil.memAddressSafe(gLFWFramebufferSizeCallbackI)));
    }

    public static long nglfwSetWindowContentScaleCallback(long j, long j2) {
        long j3 = Functions.SetWindowContentScaleCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWwindowcontentscalefun")
    public static GLFWWindowContentScaleCallback glfwSetWindowContentScaleCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWwindowcontentscalefun") GLFWWindowContentScaleCallbackI gLFWWindowContentScaleCallbackI) {
        return GLFWWindowContentScaleCallback.createSafe(nglfwSetWindowContentScaleCallback(j, MemoryUtil.memAddressSafe(gLFWWindowContentScaleCallbackI)));
    }

    public static void glfwPollEvents() {
        JNI.invokeV(Functions.PollEvents);
    }

    public static void glfwWaitEvents() {
        JNI.invokeV(Functions.WaitEvents);
    }

    public static void glfwWaitEventsTimeout(double d) {
        JNI.invokeV(d, Functions.WaitEventsTimeout);
    }

    public static void glfwPostEmptyEvent() {
        JNI.invokeV(Functions.PostEmptyEvent);
    }

    public static int glfwGetInputMode(@NativeType("GLFWwindow *") long j, int i) {
        long j2 = Functions.GetInputMode;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, i, j2);
    }

    public static void glfwSetInputMode(@NativeType("GLFWwindow *") long j, int i, int i2) {
        long j2 = Functions.SetInputMode;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, i2, j2);
    }

    @NativeType("int")
    public static boolean glfwRawMouseMotionSupported() {
        return JNI.invokeI(Functions.RawMouseMotionSupported) != 0;
    }

    public static long nglfwGetKeyName(int i, int i2) {
        return JNI.invokeP(i, i2, Functions.GetKeyName);
    }

    @NativeType("char const *")
    public static String glfwGetKeyName(int i, int i2) {
        return MemoryUtil.memUTF8Safe(nglfwGetKeyName(i, i2));
    }

    public static int glfwGetKeyScancode(int i) {
        return JNI.invokeI(i, Functions.GetKeyScancode);
    }

    public static int glfwGetKey(@NativeType("GLFWwindow *") long j, int i) {
        long j2 = Functions.GetKey;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, i, j2);
    }

    public static int glfwGetMouseButton(@NativeType("GLFWwindow *") long j, int i) {
        long j2 = Functions.GetMouseButton;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, i, j2);
    }

    public static void nglfwGetCursorPos(long j, long j2, long j3) {
        long j4 = Functions.GetCursorPos;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPV(j, j2, j3, j4);
    }

    public static void glfwGetCursorPos(@NativeType("GLFWwindow *") long j, @NativeType("double *") DoubleBuffer doubleBuffer, @NativeType("double *") DoubleBuffer doubleBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) doubleBuffer, 1);
            Checks.checkSafe((Buffer) doubleBuffer2, 1);
        }
        nglfwGetCursorPos(j, MemoryUtil.memAddressSafe(doubleBuffer), MemoryUtil.memAddressSafe(doubleBuffer2));
    }

    public static void glfwSetCursorPos(@NativeType("GLFWwindow *") long j, double d, double d2) {
        long j2 = Functions.SetCursorPos;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, d, d2, j2);
    }

    public static long nglfwCreateCursor(long j, int i, int i2) {
        long j2 = Functions.CreateCursor;
        if (Checks.CHECKS) {
            GLFWImage.validate(j);
        }
        return JNI.invokePP(j, i, i2, j2);
    }

    @NativeType("GLFWcursor *")
    public static long glfwCreateCursor(@NativeType("GLFWimage const *") GLFWImage gLFWImage, int i, int i2) {
        return nglfwCreateCursor(gLFWImage.address(), i, i2);
    }

    @NativeType("GLFWcursor *")
    public static long glfwCreateStandardCursor(int i) {
        return JNI.invokeP(i, Functions.CreateStandardCursor);
    }

    public static void glfwDestroyCursor(@NativeType("GLFWcursor *") long j) {
        long j2 = Functions.DestroyCursor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwSetCursor(@NativeType("GLFWwindow *") long j, @NativeType("GLFWcursor *") long j2) {
        long j3 = Functions.SetCursor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static void nglfwGetPreeditCursorRectangle(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.GetPreeditCursorRectangle;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePPPPPV(j, j2, j3, j4, j5, j6);
    }

    public static void glfwGetPreeditCursorRectangle(@NativeType("GLFWwindow *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("int *") IntBuffer intBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((Buffer) intBuffer4, 1);
        }
        nglfwGetPreeditCursorRectangle(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(intBuffer4));
    }

    public static void glfwSetPreeditCursorRectangle(@NativeType("GLFWwindow *") long j, int i, int i2, int i3, int i4) {
        long j2 = Functions.SetPreeditCursorRectangle;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, i, i2, i3, i4, j2);
    }

    public static void glfwResetPreeditText(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.ResetPreeditText;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static long nglfwGetPreeditCandidate(long j, int i, long j2) {
        long j3 = Functions.GetPreeditCandidate;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, i, j2, j3);
    }

    @NativeType("unsigned int *")
    public static IntBuffer glfwGetPreeditCandidate(@NativeType("GLFWwindow *") long j, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memIntBufferSafe(nglfwGetPreeditCandidate(j, i, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwSetKeyCallback(long j, long j2) {
        long j3 = Functions.SetKeyCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWkeyfun")
    public static GLFWKeyCallback glfwSetKeyCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWkeyfun") GLFWKeyCallbackI gLFWKeyCallbackI) {
        return GLFWKeyCallback.createSafe(nglfwSetKeyCallback(j, MemoryUtil.memAddressSafe(gLFWKeyCallbackI)));
    }

    public static long nglfwSetCharCallback(long j, long j2) {
        long j3 = Functions.SetCharCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWcharfun")
    public static GLFWCharCallback glfwSetCharCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWcharfun") GLFWCharCallbackI gLFWCharCallbackI) {
        return GLFWCharCallback.createSafe(nglfwSetCharCallback(j, MemoryUtil.memAddressSafe(gLFWCharCallbackI)));
    }

    public static long nglfwSetCharModsCallback(long j, long j2) {
        long j3 = Functions.SetCharModsCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWcharmodsfun")
    public static GLFWCharModsCallback glfwSetCharModsCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWcharmodsfun") GLFWCharModsCallbackI gLFWCharModsCallbackI) {
        return GLFWCharModsCallback.createSafe(nglfwSetCharModsCallback(j, MemoryUtil.memAddressSafe(gLFWCharModsCallbackI)));
    }

    public static long nglfwSetPreeditCallback(long j, long j2) {
        long j3 = Functions.SetPreeditCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWpreeditfun")
    public static GLFWPreeditCallback glfwSetPreeditCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWpreeditfun") GLFWPreeditCallbackI gLFWPreeditCallbackI) {
        return GLFWPreeditCallback.createSafe(nglfwSetPreeditCallback(j, MemoryUtil.memAddressSafe(gLFWPreeditCallbackI)));
    }

    public static long nglfwSetIMEStatusCallback(long j, long j2) {
        long j3 = Functions.SetIMEStatusCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWimestatusfun")
    public static GLFWIMEStatusCallback glfwSetIMEStatusCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWimestatusfun") GLFWIMEStatusCallbackI gLFWIMEStatusCallbackI) {
        return GLFWIMEStatusCallback.createSafe(nglfwSetIMEStatusCallback(j, MemoryUtil.memAddressSafe(gLFWIMEStatusCallbackI)));
    }

    public static long nglfwSetPreeditCandidateCallback(long j, long j2) {
        long j3 = Functions.SetPreeditCandidateCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWpreeditcandidatefun")
    public static GLFWPreeditCandidateCallback glfwSetPreeditCandidateCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWpreeditcandidatefun") GLFWPreeditCandidateCallbackI gLFWPreeditCandidateCallbackI) {
        return GLFWPreeditCandidateCallback.createSafe(nglfwSetPreeditCandidateCallback(j, MemoryUtil.memAddressSafe(gLFWPreeditCandidateCallbackI)));
    }

    public static long nglfwSetMouseButtonCallback(long j, long j2) {
        long j3 = Functions.SetMouseButtonCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWmousebuttonfun")
    public static GLFWMouseButtonCallback glfwSetMouseButtonCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWmousebuttonfun") GLFWMouseButtonCallbackI gLFWMouseButtonCallbackI) {
        return GLFWMouseButtonCallback.createSafe(nglfwSetMouseButtonCallback(j, MemoryUtil.memAddressSafe(gLFWMouseButtonCallbackI)));
    }

    public static long nglfwSetCursorPosCallback(long j, long j2) {
        long j3 = Functions.SetCursorPosCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWcursorposfun")
    public static GLFWCursorPosCallback glfwSetCursorPosCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWcursorposfun") GLFWCursorPosCallbackI gLFWCursorPosCallbackI) {
        return GLFWCursorPosCallback.createSafe(nglfwSetCursorPosCallback(j, MemoryUtil.memAddressSafe(gLFWCursorPosCallbackI)));
    }

    public static long nglfwSetCursorEnterCallback(long j, long j2) {
        long j3 = Functions.SetCursorEnterCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWcursorenterfun")
    public static GLFWCursorEnterCallback glfwSetCursorEnterCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWcursorenterfun") GLFWCursorEnterCallbackI gLFWCursorEnterCallbackI) {
        return GLFWCursorEnterCallback.createSafe(nglfwSetCursorEnterCallback(j, MemoryUtil.memAddressSafe(gLFWCursorEnterCallbackI)));
    }

    public static long nglfwSetScrollCallback(long j, long j2) {
        long j3 = Functions.SetScrollCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWscrollfun")
    public static GLFWScrollCallback glfwSetScrollCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWscrollfun") GLFWScrollCallbackI gLFWScrollCallbackI) {
        return GLFWScrollCallback.createSafe(nglfwSetScrollCallback(j, MemoryUtil.memAddressSafe(gLFWScrollCallbackI)));
    }

    public static long nglfwSetDropCallback(long j, long j2) {
        long j3 = Functions.SetDropCallback;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("GLFWdropfun")
    public static GLFWDropCallback glfwSetDropCallback(@NativeType("GLFWwindow *") long j, @NativeType("GLFWdropfun") GLFWDropCallbackI gLFWDropCallbackI) {
        return GLFWDropCallback.createSafe(nglfwSetDropCallback(j, MemoryUtil.memAddressSafe(gLFWDropCallbackI)));
    }

    @NativeType("int")
    public static boolean glfwJoystickPresent(int i) {
        return JNI.invokeI(i, Functions.JoystickPresent) != 0;
    }

    public static long nglfwGetJoystickAxes(int i, long j) {
        return JNI.invokePP(i, j, Functions.GetJoystickAxes);
    }

    @NativeType("float const *")
    public static FloatBuffer glfwGetJoystickAxes(int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memFloatBufferSafe(nglfwGetJoystickAxes(i, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetJoystickButtons(int i, long j) {
        return JNI.invokePP(i, j, Functions.GetJoystickButtons);
    }

    @NativeType("unsigned char const *")
    public static ByteBuffer glfwGetJoystickButtons(int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memByteBufferSafe(nglfwGetJoystickButtons(i, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetJoystickHats(int i, long j) {
        return JNI.invokePP(i, j, Functions.GetJoystickHats);
    }

    @NativeType("unsigned char const *")
    public static ByteBuffer glfwGetJoystickHats(int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memByteBufferSafe(nglfwGetJoystickHats(i, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetJoystickName(int i) {
        return JNI.invokeP(i, Functions.GetJoystickName);
    }

    @NativeType("char const *")
    public static String glfwGetJoystickName(int i) {
        return MemoryUtil.memUTF8Safe(nglfwGetJoystickName(i));
    }

    public static long nglfwGetJoystickGUID(int i) {
        return JNI.invokeP(i, Functions.GetJoystickGUID);
    }

    @NativeType("char const *")
    public static String glfwGetJoystickGUID(int i) {
        return MemoryUtil.memUTF8Safe(nglfwGetJoystickGUID(i));
    }

    public static void glfwSetJoystickUserPointer(int i, @NativeType("void *") long j) {
        long j2 = Functions.SetJoystickUserPointer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, j, j2);
    }

    @NativeType("void *")
    public static long glfwGetJoystickUserPointer(int i) {
        return JNI.invokeP(i, Functions.GetJoystickUserPointer);
    }

    @NativeType("int")
    public static boolean glfwJoystickIsGamepad(int i) {
        return JNI.invokeI(i, Functions.JoystickIsGamepad) != 0;
    }

    public static long nglfwSetJoystickCallback(long j) {
        return JNI.invokePP(j, Functions.SetJoystickCallback);
    }

    @NativeType("GLFWjoystickfun")
    public static GLFWJoystickCallback glfwSetJoystickCallback(@NativeType("GLFWjoystickfun") GLFWJoystickCallbackI gLFWJoystickCallbackI) {
        return GLFWJoystickCallback.createSafe(nglfwSetJoystickCallback(MemoryUtil.memAddressSafe(gLFWJoystickCallbackI)));
    }

    public static int nglfwUpdateGamepadMappings(long j) {
        return JNI.invokePI(j, Functions.UpdateGamepadMappings);
    }

    @NativeType("int")
    public static boolean glfwUpdateGamepadMappings(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglfwUpdateGamepadMappings(MemoryUtil.memAddress(byteBuffer)) != 0;
    }

    public static long nglfwGetGamepadName(int i) {
        return JNI.invokeP(i, Functions.GetGamepadName);
    }

    @NativeType("char const *")
    public static String glfwGetGamepadName(int i) {
        return MemoryUtil.memUTF8Safe(nglfwGetGamepadName(i));
    }

    public static int nglfwGetGamepadState(int i, long j) {
        return JNI.invokePI(i, j, Functions.GetGamepadState);
    }

    @NativeType("int")
    public static boolean glfwGetGamepadState(int i, @NativeType("GLFWgamepadstate *") GLFWGamepadState gLFWGamepadState) {
        return nglfwGetGamepadState(i, gLFWGamepadState.address()) != 0;
    }

    public static void nglfwSetClipboardString(long j, long j2) {
        JNI.invokePPV(j, j2, Functions.SetClipboardString);
    }

    public static void glfwSetClipboardString(@NativeType("GLFWwindow *") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglfwSetClipboardString(j, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glfwSetClipboardString(@NativeType("GLFWwindow *") long j, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nglfwSetClipboardString(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetClipboardString(long j) {
        return JNI.invokePP(j, Functions.GetClipboardString);
    }

    @NativeType("char const *")
    public static String glfwGetClipboardString(@NativeType("GLFWwindow *") long j) {
        return MemoryUtil.memUTF8Safe(nglfwGetClipboardString(j));
    }

    public static double glfwGetTime() {
        return JNI.invokeD(Functions.GetTime);
    }

    public static void glfwSetTime(double d) {
        JNI.invokeV(d, Functions.SetTime);
    }

    @NativeType("uint64_t")
    public static long glfwGetTimerValue() {
        return JNI.invokeJ(Functions.GetTimerValue);
    }

    @NativeType("uint64_t")
    public static long glfwGetTimerFrequency() {
        return JNI.invokeJ(Functions.GetTimerFrequency);
    }

    public static void glfwMakeContextCurrent(@NativeType("GLFWwindow *") long j) {
        JNI.invokePV(j, Functions.MakeContextCurrent);
    }

    @NativeType("GLFWwindow *")
    public static long glfwGetCurrentContext() {
        return JNI.invokeP(Functions.GetCurrentContext);
    }

    public static void glfwSwapBuffers(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.SwapBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void glfwSwapInterval(int i) {
        JNI.invokeV(i, Functions.SwapInterval);
    }

    public static int nglfwExtensionSupported(long j) {
        return JNI.invokePI(j, Functions.ExtensionSupported);
    }

    @NativeType("int")
    public static boolean glfwExtensionSupported(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglfwExtensionSupported(MemoryUtil.memAddress(byteBuffer)) != 0;
    }

    @NativeType("int")
    public static boolean glfwExtensionSupported(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglfwExtensionSupported(stackGet.getPointerAddress()) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetProcAddress(long j) {
        return JNI.invokePP(j, Functions.GetProcAddress);
    }

    @NativeType("GLFWglproc")
    public static long glfwGetProcAddress(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglfwGetProcAddress(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLFWglproc")
    public static long glfwGetProcAddress(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglfwGetProcAddress(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glfwGetVersion(@NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3) {
        long j = Functions.GetVersion;
        if (Checks.CHECKS) {
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
        }
        JNI.invokePPPV(iArr, iArr2, iArr3, j);
    }

    public static void glfwGetMonitorPos(@NativeType("GLFWmonitor *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = Functions.GetMonitorPos;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.invokePPPV(j, iArr, iArr2, j2);
    }

    public static void glfwGetMonitorWorkarea(@NativeType("GLFWmonitor *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        long j2 = Functions.GetMonitorWorkarea;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        JNI.invokePPPPPV(j, iArr, iArr2, iArr3, iArr4, j2);
    }

    public static void glfwGetMonitorPhysicalSize(@NativeType("GLFWmonitor *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = Functions.GetMonitorPhysicalSize;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.invokePPPV(j, iArr, iArr2, j2);
    }

    public static void glfwGetMonitorContentScale(@NativeType("GLFWmonitor *") long j, @NativeType("float *") float[] fArr, @NativeType("float *") float[] fArr2) {
        long j2 = Functions.GetMonitorContentScale;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(fArr, 1);
            Checks.checkSafe(fArr2, 1);
        }
        JNI.invokePPPV(j, fArr, fArr2, j2);
    }

    public static void glfwGetWindowPos(@NativeType("GLFWwindow *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = Functions.GetWindowPos;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.invokePPPV(j, iArr, iArr2, j2);
    }

    public static void glfwGetWindowSize(@NativeType("GLFWwindow *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = Functions.GetWindowSize;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.invokePPPV(j, iArr, iArr2, j2);
    }

    public static void glfwGetFramebufferSize(@NativeType("GLFWwindow *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = Functions.GetFramebufferSize;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.invokePPPV(j, iArr, iArr2, j2);
    }

    public static void glfwGetWindowFrameSize(@NativeType("GLFWwindow *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        long j2 = Functions.GetWindowFrameSize;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        JNI.invokePPPPPV(j, iArr, iArr2, iArr3, iArr4, j2);
    }

    public static void glfwGetWindowContentScale(@NativeType("GLFWwindow *") long j, @NativeType("float *") float[] fArr, @NativeType("float *") float[] fArr2) {
        long j2 = Functions.GetWindowContentScale;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(fArr, 1);
            Checks.checkSafe(fArr2, 1);
        }
        JNI.invokePPPV(j, fArr, fArr2, j2);
    }

    public static void glfwGetCursorPos(@NativeType("GLFWwindow *") long j, @NativeType("double *") double[] dArr, @NativeType("double *") double[] dArr2) {
        long j2 = Functions.GetCursorPos;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(dArr, 1);
            Checks.checkSafe(dArr2, 1);
        }
        JNI.invokePPPV(j, dArr, dArr2, j2);
    }

    public static void glfwGetPreeditCursorRectangle(@NativeType("GLFWwindow *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("int *") int[] iArr4) {
        long j2 = Functions.GetPreeditCursorRectangle;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe(iArr4, 1);
        }
        JNI.invokePPPPPV(j, iArr, iArr2, iArr3, iArr4, j2);
    }
}
