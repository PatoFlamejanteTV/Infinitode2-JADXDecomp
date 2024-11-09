package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL11C.class */
public class GL11C {
    public static final int GL_NEVER = 512;
    public static final int GL_LESS = 513;
    public static final int GL_EQUAL = 514;
    public static final int GL_LEQUAL = 515;
    public static final int GL_GREATER = 516;
    public static final int GL_NOTEQUAL = 517;
    public static final int GL_GEQUAL = 518;
    public static final int GL_ALWAYS = 519;
    public static final int GL_DEPTH_BUFFER_BIT = 256;
    public static final int GL_STENCIL_BUFFER_BIT = 1024;
    public static final int GL_COLOR_BUFFER_BIT = 16384;
    public static final int GL_POINTS = 0;
    public static final int GL_LINES = 1;
    public static final int GL_LINE_LOOP = 2;
    public static final int GL_LINE_STRIP = 3;
    public static final int GL_TRIANGLES = 4;
    public static final int GL_TRIANGLE_STRIP = 5;
    public static final int GL_TRIANGLE_FAN = 6;
    public static final int GL_QUADS = 7;
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
    public static final int GL_BYTE = 5120;
    public static final int GL_UNSIGNED_BYTE = 5121;
    public static final int GL_SHORT = 5122;
    public static final int GL_UNSIGNED_SHORT = 5123;
    public static final int GL_INT = 5124;
    public static final int GL_UNSIGNED_INT = 5125;
    public static final int GL_FLOAT = 5126;
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
    public static final int GL_NO_ERROR = 0;
    public static final int GL_INVALID_ENUM = 1280;
    public static final int GL_INVALID_VALUE = 1281;
    public static final int GL_INVALID_OPERATION = 1282;
    public static final int GL_STACK_OVERFLOW = 1283;
    public static final int GL_STACK_UNDERFLOW = 1284;
    public static final int GL_OUT_OF_MEMORY = 1285;
    public static final int GL_CW = 2304;
    public static final int GL_CCW = 2305;
    public static final int GL_POINT_SIZE = 2833;
    public static final int GL_POINT_SIZE_RANGE = 2834;
    public static final int GL_POINT_SIZE_GRANULARITY = 2835;
    public static final int GL_LINE_SMOOTH = 2848;
    public static final int GL_LINE_WIDTH = 2849;
    public static final int GL_LINE_WIDTH_RANGE = 2850;
    public static final int GL_LINE_WIDTH_GRANULARITY = 2851;
    public static final int GL_POLYGON_MODE = 2880;
    public static final int GL_POLYGON_SMOOTH = 2881;
    public static final int GL_CULL_FACE = 2884;
    public static final int GL_CULL_FACE_MODE = 2885;
    public static final int GL_FRONT_FACE = 2886;
    public static final int GL_DEPTH_RANGE = 2928;
    public static final int GL_DEPTH_TEST = 2929;
    public static final int GL_DEPTH_WRITEMASK = 2930;
    public static final int GL_DEPTH_CLEAR_VALUE = 2931;
    public static final int GL_DEPTH_FUNC = 2932;
    public static final int GL_STENCIL_TEST = 2960;
    public static final int GL_STENCIL_CLEAR_VALUE = 2961;
    public static final int GL_STENCIL_FUNC = 2962;
    public static final int GL_STENCIL_VALUE_MASK = 2963;
    public static final int GL_STENCIL_FAIL = 2964;
    public static final int GL_STENCIL_PASS_DEPTH_FAIL = 2965;
    public static final int GL_STENCIL_PASS_DEPTH_PASS = 2966;
    public static final int GL_STENCIL_REF = 2967;
    public static final int GL_STENCIL_WRITEMASK = 2968;
    public static final int GL_VIEWPORT = 2978;
    public static final int GL_DITHER = 3024;
    public static final int GL_BLEND_DST = 3040;
    public static final int GL_BLEND_SRC = 3041;
    public static final int GL_BLEND = 3042;
    public static final int GL_LOGIC_OP_MODE = 3056;
    public static final int GL_COLOR_LOGIC_OP = 3058;
    public static final int GL_DRAW_BUFFER = 3073;
    public static final int GL_READ_BUFFER = 3074;
    public static final int GL_SCISSOR_BOX = 3088;
    public static final int GL_SCISSOR_TEST = 3089;
    public static final int GL_COLOR_CLEAR_VALUE = 3106;
    public static final int GL_COLOR_WRITEMASK = 3107;
    public static final int GL_DOUBLEBUFFER = 3122;
    public static final int GL_STEREO = 3123;
    public static final int GL_LINE_SMOOTH_HINT = 3154;
    public static final int GL_POLYGON_SMOOTH_HINT = 3155;
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
    public static final int GL_MAX_TEXTURE_SIZE = 3379;
    public static final int GL_MAX_VIEWPORT_DIMS = 3386;
    public static final int GL_SUBPIXEL_BITS = 3408;
    public static final int GL_TEXTURE_1D = 3552;
    public static final int GL_TEXTURE_2D = 3553;
    public static final int GL_TEXTURE_WIDTH = 4096;
    public static final int GL_TEXTURE_HEIGHT = 4097;
    public static final int GL_TEXTURE_INTERNAL_FORMAT = 4099;
    public static final int GL_TEXTURE_BORDER_COLOR = 4100;
    public static final int GL_DONT_CARE = 4352;
    public static final int GL_FASTEST = 4353;
    public static final int GL_NICEST = 4354;
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
    public static final int GL_TEXTURE = 5890;
    public static final int GL_COLOR = 6144;
    public static final int GL_DEPTH = 6145;
    public static final int GL_STENCIL = 6146;
    public static final int GL_STENCIL_INDEX = 6401;
    public static final int GL_DEPTH_COMPONENT = 6402;
    public static final int GL_RED = 6403;
    public static final int GL_GREEN = 6404;
    public static final int GL_BLUE = 6405;
    public static final int GL_ALPHA = 6406;
    public static final int GL_RGB = 6407;
    public static final int GL_RGBA = 6408;
    public static final int GL_POINT = 6912;
    public static final int GL_LINE = 6913;
    public static final int GL_FILL = 6914;
    public static final int GL_KEEP = 7680;
    public static final int GL_REPLACE = 7681;
    public static final int GL_INCR = 7682;
    public static final int GL_DECR = 7683;
    public static final int GL_VENDOR = 7936;
    public static final int GL_RENDERER = 7937;
    public static final int GL_VERSION = 7938;
    public static final int GL_EXTENSIONS = 7939;
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
    public static final int GL_REPEAT = 10497;
    public static final int GL_POLYGON_OFFSET_FACTOR = 32824;
    public static final int GL_POLYGON_OFFSET_UNITS = 10752;
    public static final int GL_POLYGON_OFFSET_POINT = 10753;
    public static final int GL_POLYGON_OFFSET_LINE = 10754;
    public static final int GL_POLYGON_OFFSET_FILL = 32823;
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
    public static final int GL_PROXY_TEXTURE_1D = 32867;
    public static final int GL_PROXY_TEXTURE_2D = 32868;
    public static final int GL_TEXTURE_BINDING_1D = 32872;
    public static final int GL_TEXTURE_BINDING_2D = 32873;
    public static final int GL_VERTEX_ARRAY = 32884;

    public static native void glEnable(@NativeType("GLenum") int i);

    public static native void glDisable(@NativeType("GLenum") int i);

    public static native void glBindTexture(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glBlendFunc(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    public static native void glClear(@NativeType("GLbitfield") int i);

    public static native void glClearColor(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4);

    public static native void glClearDepth(@NativeType("GLdouble") double d);

    public static native void glClearStencil(@NativeType("GLint") int i);

    public static native void glColorMask(@NativeType("GLboolean") boolean z, @NativeType("GLboolean") boolean z2, @NativeType("GLboolean") boolean z3, @NativeType("GLboolean") boolean z4);

    public static native void glCullFace(@NativeType("GLenum") int i);

    public static native void glDepthFunc(@NativeType("GLenum") int i);

    public static native void glDepthMask(@NativeType("GLboolean") boolean z);

    public static native void glDepthRange(@NativeType("GLdouble") double d, @NativeType("GLdouble") double d2);

    public static native void glDrawArrays(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3);

    public static native void glDrawBuffer(@NativeType("GLenum") int i);

    public static native void nglDrawElements(int i, int i2, int i3, long j);

    public static native void glFinish();

    public static native void glFlush();

    public static native void glFrontFace(@NativeType("GLenum") int i);

    public static native void nglGenTextures(int i, long j);

    public static native void nglDeleteTextures(int i, long j);

    public static native void nglGetBooleanv(int i, long j);

    public static native void nglGetFloatv(int i, long j);

    public static native void nglGetIntegerv(int i, long j);

    public static native void nglGetDoublev(int i, long j);

    @NativeType("GLenum")
    public static native int glGetError();

    public static native void nglGetPointerv(int i, long j);

    public static native long nglGetString(int i);

    public static native void nglGetTexImage(int i, int i2, int i3, int i4, long j);

    public static native void nglGetTexLevelParameteriv(int i, int i2, int i3, long j);

    public static native void nglGetTexLevelParameterfv(int i, int i2, int i3, long j);

    public static native void nglGetTexParameteriv(int i, int i2, long j);

    public static native void nglGetTexParameterfv(int i, int i2, long j);

    public static native void glHint(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    @NativeType("GLboolean")
    public static native boolean glIsEnabled(@NativeType("GLenum") int i);

    @NativeType("GLboolean")
    public static native boolean glIsTexture(@NativeType("GLuint") int i);

    public static native void glLineWidth(@NativeType("GLfloat") float f);

    public static native void glLogicOp(@NativeType("GLenum") int i);

    public static native void glPixelStorei(@NativeType("GLenum") int i, @NativeType("GLint") int i2);

    public static native void glPixelStoref(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void glPointSize(@NativeType("GLfloat") float f);

    public static native void glPolygonMode(@NativeType("GLenum") int i, @NativeType("GLenum") int i2);

    public static native void glPolygonOffset(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glReadBuffer(@NativeType("GLenum") int i);

    public static native void nglReadPixels(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void glScissor(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    public static native void glStencilFunc(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3);

    public static native void glStencilMask(@NativeType("GLuint") int i);

    public static native void glStencilOp(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3);

    public static native void nglTexImage1D(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void glCopyTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7);

    public static native void glCopyTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLint") int i8);

    public static native void glCopyTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6);

    public static native void glCopyTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8);

    public static native void glTexParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglTexParameteriv(int i, int i2, long j);

    public static native void glTexParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglTexParameterfv(int i, int i2, long j);

    public static native void nglTexSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void nglTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void glViewport(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL11C() {
        throw new UnsupportedOperationException();
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") long j) {
        nglDrawElements(i, i2, i3, j);
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglDrawElements(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglDrawElements(i, byteBuffer.remaining(), 5121, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglDrawElements(i, shortBuffer.remaining(), 5123, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glDrawElements(@NativeType("GLenum") int i, @NativeType("void const *") IntBuffer intBuffer) {
        nglDrawElements(i, intBuffer.remaining(), 5125, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGenTextures(@NativeType("GLuint *") IntBuffer intBuffer) {
        nglGenTextures(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGenTextures() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGenTextures(1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glDeleteTextures(@NativeType("GLuint const *") IntBuffer intBuffer) {
        nglDeleteTextures(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glDeleteTextures(@NativeType("GLuint const *") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglDeleteTextures(1, MemoryUtil.memAddress(stackGet.ints(i)));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetBooleanv(@NativeType("GLenum") int i, @NativeType("GLboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, 1);
        }
        nglGetBooleanv(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static boolean glGetBoolean(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            ByteBuffer calloc = stackGet.calloc(1);
            nglGetBooleanv(i, MemoryUtil.memAddress(calloc));
            return calloc.get(0) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetFloatv(@NativeType("GLenum") int i, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetFloatv(i, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetFloat(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetFloatv(i, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetIntegerv(@NativeType("GLenum") int i, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetIntegerv(i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetInteger(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetIntegerv(i, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetDoublev(@NativeType("GLenum") int i, @NativeType("GLdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nglGetDoublev(i, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("void")
    public static double glGetDouble(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nglGetDoublev(i, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPointerv(@NativeType("GLenum") int i, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nglGetPointerv(i, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("void")
    public static long glGetPointer(@NativeType("GLenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nglGetPointerv(i, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLubyte const *")
    public static String glGetString(@NativeType("GLenum") int i) {
        return MemoryUtil.memUTF8Safe(nglGetString(i));
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ByteBuffer byteBuffer) {
        nglGetTexImage(i, i2, i3, i4, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") long j) {
        nglGetTexImage(i, i2, i3, i4, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") ShortBuffer shortBuffer) {
        nglGetTexImage(i, i2, i3, i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") IntBuffer intBuffer) {
        nglGetTexImage(i, i2, i3, i4, MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") FloatBuffer floatBuffer) {
        nglGetTexImage(i, i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") DoubleBuffer doubleBuffer) {
        nglGetTexImage(i, i2, i3, i4, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGetTexLevelParameteriv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexLevelParameteriv(i, i2, i3, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexLevelParameteri(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexLevelParameteriv(i, i2, i3, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexLevelParameterfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetTexLevelParameterfv(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTexLevelParameterf(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTexLevelParameterfv(i, i2, i3, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetTexParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetTexParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetTexParameteriv(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetTexParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetTexParameterf(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetTexParameterfv(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ByteBuffer byteBuffer) {
        nglReadPixels(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") long j) {
        nglReadPixels(i, i2, i3, i4, i5, i6, j);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") ShortBuffer shortBuffer) {
        nglReadPixels(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") IntBuffer intBuffer) {
        nglReadPixels(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(intBuffer));
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") FloatBuffer floatBuffer) {
        nglReadPixels(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTexImage1D(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") long j) {
        nglTexImage1D(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTexImage1D(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") IntBuffer intBuffer) {
        nglTexImage1D(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTexImage1D(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTexImage1D(i, i2, i3, i4, i5, i6, i7, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        nglTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        nglTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTexImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void glTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 4);
        }
        nglTexParameteriv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 4);
        }
        nglTexParameterfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTexSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") long j) {
        nglTexSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTexSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") IntBuffer intBuffer) {
        nglTexSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTexSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTexSubImage1D(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") long j) {
        nglTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") IntBuffer intBuffer) {
        nglTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(intBuffer));
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") DoubleBuffer doubleBuffer) {
        nglTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void glGenTextures(@NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGenTextures;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glDeleteTextures(@NativeType("GLuint const *") int[] iArr) {
        long j = GL.getICD().glDeleteTextures;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(iArr.length, iArr, j);
    }

    public static void glGetFloatv(@NativeType("GLenum") int i, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetFloatv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glGetIntegerv(@NativeType("GLenum") int i, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetIntegerv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, iArr, j);
    }

    public static void glGetDoublev(@NativeType("GLenum") int i, @NativeType("GLdouble *") double[] dArr) {
        long j = GL.getICD().glGetDoublev;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.callPV(i, dArr, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glGetTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, sArr, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glGetTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, iArr, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glGetTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glGetTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("void *") double[] dArr) {
        long j = GL.getICD().glGetTexImage;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, dArr, j);
    }

    public static void glGetTexLevelParameteriv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTexLevelParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, i3, iArr, j);
    }

    public static void glGetTexLevelParameterfv(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTexLevelParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glGetTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetTexParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetTexParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") short[] sArr) {
        long j = GL.getICD().glReadPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, sArr, j);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") int[] iArr) {
        long j = GL.getICD().glReadPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, iArr, j);
    }

    public static void glReadPixels(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void *") float[] fArr) {
        long j = GL.getICD().glReadPixels;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, fArr, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTexImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, sArr, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTexImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, iArr, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTexImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, fArr, j);
    }

    public static void glTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLenum") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTexImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, dArr, j);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTexImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, sArr, j);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTexImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, iArr, j);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTexImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, fArr, j);
    }

    public static void glTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTexImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, dArr, j);
    }

    public static void glTexParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glTexParameteriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 4);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glTexParameterfv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTexParameterfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 4);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTexSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, sArr, j);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTexSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, iArr, j);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTexSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, fArr, j);
    }

    public static void glTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTexSubImage1D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, dArr, j);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glTexSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, sArr, j);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") int[] iArr) {
        long j = GL.getICD().glTexSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, iArr, j);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glTexSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, fArr, j);
    }

    public static void glTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLenum") int i8, @NativeType("void const *") double[] dArr) {
        long j = GL.getICD().glTexSubImage2D;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, i4, i5, i6, i7, i8, dArr, j);
    }
}
