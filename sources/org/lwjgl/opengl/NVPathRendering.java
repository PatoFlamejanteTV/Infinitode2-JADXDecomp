package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVPathRendering.class */
public class NVPathRendering {
    public static final byte GL_CLOSE_PATH_NV = 0;
    public static final byte GL_MOVE_TO_NV = 2;
    public static final byte GL_RELATIVE_MOVE_TO_NV = 3;
    public static final byte GL_LINE_TO_NV = 4;
    public static final byte GL_RELATIVE_LINE_TO_NV = 5;
    public static final byte GL_HORIZONTAL_LINE_TO_NV = 6;
    public static final byte GL_RELATIVE_HORIZONTAL_LINE_TO_NV = 7;
    public static final byte GL_VERTICAL_LINE_TO_NV = 8;
    public static final byte GL_RELATIVE_VERTICAL_LINE_TO_NV = 9;
    public static final byte GL_QUADRATIC_CURVE_TO_NV = 10;
    public static final byte GL_RELATIVE_QUADRATIC_CURVE_TO_NV = 11;
    public static final byte GL_CUBIC_CURVE_TO_NV = 12;
    public static final byte GL_RELATIVE_CUBIC_CURVE_TO_NV = 13;
    public static final byte GL_SMOOTH_QUADRATIC_CURVE_TO_NV = 14;
    public static final byte GL_RELATIVE_SMOOTH_QUADRATIC_CURVE_TO_NV = 15;
    public static final byte GL_SMOOTH_CUBIC_CURVE_TO_NV = 16;
    public static final byte GL_RELATIVE_SMOOTH_CUBIC_CURVE_TO_NV = 17;
    public static final byte GL_SMALL_CCW_ARC_TO_NV = 18;
    public static final byte GL_RELATIVE_SMALL_CCW_ARC_TO_NV = 19;
    public static final byte GL_SMALL_CW_ARC_TO_NV = 20;
    public static final byte GL_RELATIVE_SMALL_CW_ARC_TO_NV = 21;
    public static final byte GL_LARGE_CCW_ARC_TO_NV = 22;
    public static final byte GL_RELATIVE_LARGE_CCW_ARC_TO_NV = 23;
    public static final byte GL_LARGE_CW_ARC_TO_NV = 24;
    public static final byte GL_RELATIVE_LARGE_CW_ARC_TO_NV = 25;
    public static final byte GL_CONIC_CURVE_TO_NV = 26;
    public static final byte GL_RELATIVE_CONIC_CURVE_TO_NV = 27;
    public static final byte GL_ROUNDED_RECT_NV = -24;
    public static final byte GL_RELATIVE_ROUNDED_RECT_NV = -23;
    public static final byte GL_ROUNDED_RECT2_NV = -22;
    public static final byte GL_RELATIVE_ROUNDED_RECT2_NV = -21;
    public static final byte GL_ROUNDED_RECT4_NV = -20;
    public static final byte GL_RELATIVE_ROUNDED_RECT4_NV = -19;
    public static final byte GL_ROUNDED_RECT8_NV = -18;
    public static final byte GL_RELATIVE_ROUNDED_RECT8_NV = -17;
    public static final byte GL_RESTART_PATH_NV = -16;
    public static final byte GL_DUP_FIRST_CUBIC_CURVE_TO_NV = -14;
    public static final byte GL_DUP_LAST_CUBIC_CURVE_TO_NV = -12;
    public static final byte GL_RECT_NV = -10;
    public static final byte GL_RELATIVE_RECT_NV = -9;
    public static final byte GL_CIRCULAR_CCW_ARC_TO_NV = -8;
    public static final byte GL_CIRCULAR_CW_ARC_TO_NV = -6;
    public static final byte GL_CIRCULAR_TANGENT_ARC_TO_NV = -4;
    public static final byte GL_ARC_TO_NV = -2;
    public static final byte GL_RELATIVE_ARC_TO_NV = -1;
    public static final int GL_PATH_FORMAT_SVG_NV = 36976;
    public static final int GL_PATH_FORMAT_PS_NV = 36977;
    public static final int GL_STANDARD_FONT_NAME_NV = 36978;
    public static final int GL_SYSTEM_FONT_NAME_NV = 36979;
    public static final int GL_FILE_NAME_NV = 36980;
    public static final int GL_STANDARD_FONT_FORMAT_NV = 37740;
    public static final int GL_SKIP_MISSING_GLYPH_NV = 37033;
    public static final int GL_USE_MISSING_GLYPH_NV = 37034;
    public static final int GL_FONT_GLYPHS_AVAILABLE_NV = 37736;
    public static final int GL_FONT_TARGET_UNAVAILABLE_NV = 37737;
    public static final int GL_FONT_UNAVAILABLE_NV = 37738;
    public static final int GL_FONT_UNINTELLIGIBLE_NV = 37739;
    public static final int GL_PATH_STROKE_WIDTH_NV = 36981;
    public static final int GL_PATH_INITIAL_END_CAP_NV = 36983;
    public static final int GL_PATH_TERMINAL_END_CAP_NV = 36984;
    public static final int GL_PATH_JOIN_STYLE_NV = 36985;
    public static final int GL_PATH_MITER_LIMIT_NV = 36986;
    public static final int GL_PATH_INITIAL_DASH_CAP_NV = 36988;
    public static final int GL_PATH_TERMINAL_DASH_CAP_NV = 36989;
    public static final int GL_PATH_DASH_OFFSET_NV = 36990;
    public static final int GL_PATH_CLIENT_LENGTH_NV = 36991;
    public static final int GL_PATH_DASH_OFFSET_RESET_NV = 37044;
    public static final int GL_PATH_FILL_MODE_NV = 36992;
    public static final int GL_PATH_FILL_MASK_NV = 36993;
    public static final int GL_PATH_FILL_COVER_MODE_NV = 36994;
    public static final int GL_PATH_STROKE_COVER_MODE_NV = 36995;
    public static final int GL_PATH_STROKE_MASK_NV = 36996;
    public static final int GL_PATH_STROKE_BOUND_NV = 36998;
    public static final int GL_PATH_END_CAPS_NV = 36982;
    public static final int GL_PATH_DASH_CAPS_NV = 36987;
    public static final int GL_COUNT_UP_NV = 37000;
    public static final int GL_COUNT_DOWN_NV = 37001;
    public static final int GL_PRIMARY_COLOR_NV = 34092;
    public static final int GL_SECONDARY_COLOR_NV = 34093;
    public static final int GL_PATH_OBJECT_BOUNDING_BOX_NV = 37002;
    public static final int GL_CONVEX_HULL_NV = 37003;
    public static final int GL_BOUNDING_BOX_NV = 37005;
    public static final int GL_TRANSLATE_X_NV = 37006;
    public static final int GL_TRANSLATE_Y_NV = 37007;
    public static final int GL_TRANSLATE_2D_NV = 37008;
    public static final int GL_TRANSLATE_3D_NV = 37009;
    public static final int GL_AFFINE_2D_NV = 37010;
    public static final int GL_AFFINE_3D_NV = 37012;
    public static final int GL_TRANSPOSE_AFFINE_2D_NV = 37014;
    public static final int GL_TRANSPOSE_AFFINE_3D_NV = 37016;
    public static final int GL_UTF8_NV = 37018;
    public static final int GL_UTF16_NV = 37019;
    public static final int GL_BOUNDING_BOX_OF_BOUNDING_BOXES_NV = 37020;
    public static final int GL_PATH_COMMAND_COUNT_NV = 37021;
    public static final int GL_PATH_COORD_COUNT_NV = 37022;
    public static final int GL_PATH_DASH_ARRAY_COUNT_NV = 37023;
    public static final int GL_PATH_COMPUTED_LENGTH_NV = 37024;
    public static final int GL_PATH_FILL_BOUNDING_BOX_NV = 37025;
    public static final int GL_PATH_STROKE_BOUNDING_BOX_NV = 37026;
    public static final int GL_SQUARE_NV = 37027;
    public static final int GL_ROUND_NV = 37028;
    public static final int GL_TRIANGULAR_NV = 37029;
    public static final int GL_BEVEL_NV = 37030;
    public static final int GL_MITER_REVERT_NV = 37031;
    public static final int GL_MITER_TRUNCATE_NV = 37032;
    public static final int GL_MOVE_TO_RESETS_NV = 37045;
    public static final int GL_MOVE_TO_CONTINUES_NV = 37046;
    public static final int GL_BOLD_BIT_NV = 1;
    public static final int GL_ITALIC_BIT_NV = 2;
    public static final int GL_PATH_ERROR_POSITION_NV = 37035;
    public static final int GL_PATH_FOG_GEN_MODE_NV = 37036;
    public static final int GL_PATH_STENCIL_FUNC_NV = 37047;
    public static final int GL_PATH_STENCIL_REF_NV = 37048;
    public static final int GL_PATH_STENCIL_VALUE_MASK_NV = 37049;
    public static final int GL_PATH_STENCIL_DEPTH_OFFSET_FACTOR_NV = 37053;
    public static final int GL_PATH_STENCIL_DEPTH_OFFSET_UNITS_NV = 37054;
    public static final int GL_PATH_COVER_DEPTH_FUNC_NV = 37055;
    public static final int GL_GLYPH_WIDTH_BIT_NV = 1;
    public static final int GL_GLYPH_HEIGHT_BIT_NV = 2;
    public static final int GL_GLYPH_HORIZONTAL_BEARING_X_BIT_NV = 4;
    public static final int GL_GLYPH_HORIZONTAL_BEARING_Y_BIT_NV = 8;
    public static final int GL_GLYPH_HORIZONTAL_BEARING_ADVANCE_BIT_NV = 16;
    public static final int GL_GLYPH_VERTICAL_BEARING_X_BIT_NV = 32;
    public static final int GL_GLYPH_VERTICAL_BEARING_Y_BIT_NV = 64;
    public static final int GL_GLYPH_VERTICAL_BEARING_ADVANCE_BIT_NV = 128;
    public static final int GL_GLYPH_HAS_KERNING_BIT_NV = 256;
    public static final int GL_FONT_X_MIN_BOUNDS_BIT_NV = 65536;
    public static final int GL_FONT_Y_MIN_BOUNDS_BIT_NV = 131072;
    public static final int GL_FONT_X_MAX_BOUNDS_BIT_NV = 262144;
    public static final int GL_FONT_Y_MAX_BOUNDS_BIT_NV = 524288;
    public static final int GL_FONT_UNITS_PER_EM_BIT_NV = 1048576;
    public static final int GL_FONT_ASCENDER_BIT_NV = 2097152;
    public static final int GL_FONT_DESCENDER_BIT_NV = 4194304;
    public static final int GL_FONT_HEIGHT_BIT_NV = 8388608;
    public static final int GL_FONT_MAX_ADVANCE_WIDTH_BIT_NV = 16777216;
    public static final int GL_FONT_MAX_ADVANCE_HEIGHT_BIT_NV = 33554432;
    public static final int GL_FONT_UNDERLINE_POSITION_BIT_NV = 67108864;
    public static final int GL_FONT_UNDERLINE_THICKNESS_BIT_NV = 134217728;
    public static final int GL_FONT_HAS_KERNING_BIT_NV = 268435456;
    public static final int GL_FONT_NUM_GLYPH_INDICES_BIT_NV = 536870912;
    public static final int GL_ACCUM_ADJACENT_PAIRS_NV = 37037;
    public static final int GL_ADJACENT_PAIRS_NV = 37038;
    public static final int GL_FIRST_TO_REST_NV = 37039;
    public static final int GL_PATH_GEN_MODE_NV = 37040;
    public static final int GL_PATH_GEN_COEFF_NV = 37041;
    public static final int GL_PATH_GEN_COLOR_FORMAT_NV = 37042;
    public static final int GL_PATH_GEN_COMPONENTS_NV = 37043;
    public static final int GL_FRAGMENT_INPUT_NV = 37741;
    public static final int GL_PATH_PROJECTION_NV = 5889;
    public static final int GL_PATH_MODELVIEW_NV = 5888;
    public static final int GL_PATH_MODELVIEW_STACK_DEPTH_NV = 2979;
    public static final int GL_PATH_MODELVIEW_MATRIX_NV = 2982;
    public static final int GL_PATH_MAX_MODELVIEW_STACK_DEPTH_NV = 3382;
    public static final int GL_PATH_TRANSPOSE_MODELVIEW_MATRIX_NV = 34019;
    public static final int GL_PATH_PROJECTION_STACK_DEPTH_NV = 2980;
    public static final int GL_PATH_PROJECTION_MATRIX_NV = 2983;
    public static final int GL_PATH_MAX_PROJECTION_STACK_DEPTH_NV = 3384;
    public static final int GL_PATH_TRANSPOSE_PROJECTION_MATRIX_NV = 34020;
    public static final int GL_2_BYTES_NV = 5127;
    public static final int GL_3_BYTES_NV = 5128;
    public static final int GL_4_BYTES_NV = 5129;
    public static final int GL_EYE_LINEAR_NV = 9216;
    public static final int GL_OBJECT_LINEAR_NV = 9217;
    public static final int GL_CONSTANT_NV = 34166;

    public static native void nglPathCommandsNV(int i, int i2, long j, int i3, int i4, long j2);

    public static native void nglPathCoordsNV(int i, int i2, int i3, long j);

    public static native void nglPathSubCommandsNV(int i, int i2, int i3, int i4, long j, int i5, int i6, long j2);

    public static native void nglPathSubCoordsNV(int i, int i2, int i3, int i4, long j);

    public static native void nglPathStringNV(int i, int i2, int i3, long j);

    public static native void nglPathGlyphsNV(int i, int i2, long j, int i3, int i4, int i5, long j2, int i6, int i7, float f);

    public static native void nglPathGlyphRangeNV(int i, int i2, long j, int i3, int i4, int i5, int i6, int i7, float f);

    public static native int nglPathGlyphIndexArrayNV(int i, int i2, long j, int i3, int i4, int i5, int i6, float f);

    public static native int nglPathMemoryGlyphIndexArrayNV(int i, int i2, long j, long j2, int i3, int i4, int i5, int i6, float f);

    public static native void glCopyPathNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void nglWeightPathsNV(int i, int i2, long j, long j2);

    public static native void glInterpolatePathsNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat") float f);

    public static native void nglTransformPathNV(int i, int i2, int i3, long j);

    public static native void nglPathParameterivNV(int i, int i2, long j);

    public static native void glPathParameteriNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void nglPathParameterfvNV(int i, int i2, long j);

    public static native void glPathParameterfNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat") float f);

    public static native void nglPathDashArrayNV(int i, int i2, long j);

    @NativeType("GLuint")
    public static native int glGenPathsNV(@NativeType("GLsizei") int i);

    public static native void glDeletePathsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2);

    @NativeType("GLboolean")
    public static native boolean glIsPathNV(@NativeType("GLuint") int i);

    public static native void glPathStencilFuncNV(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3);

    public static native void glPathStencilDepthOffsetNV(@NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    public static native void glStencilFillPathNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    public static native void glStencilStrokePathNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3);

    public static native void nglStencilFillPathInstancedNV(int i, int i2, long j, int i3, int i4, int i5, int i6, long j2);

    public static native void nglStencilStrokePathInstancedNV(int i, int i2, long j, int i3, int i4, int i5, int i6, long j2);

    public static native void glPathCoverDepthFuncNV(@NativeType("GLenum") int i);

    public static native void nglPathColorGenNV(int i, int i2, int i3, long j);

    public static native void nglPathTexGenNV(int i, int i2, int i3, long j);

    public static native void glPathFogGenNV(@NativeType("GLenum") int i);

    public static native void glCoverFillPathNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glCoverStrokePathNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void nglCoverFillPathInstancedNV(int i, int i2, long j, int i3, int i4, int i5, long j2);

    public static native void nglCoverStrokePathInstancedNV(int i, int i2, long j, int i3, int i4, int i5, long j2);

    public static native void glStencilThenCoverFillPathNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4);

    public static native void glStencilThenCoverStrokePathNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4);

    public static native void nglStencilThenCoverFillPathInstancedNV(int i, int i2, long j, int i3, int i4, int i5, int i6, int i7, long j2);

    public static native void nglStencilThenCoverStrokePathInstancedNV(int i, int i2, long j, int i3, int i4, int i5, int i6, int i7, long j2);

    public static native int nglPathGlyphIndexRangeNV(int i, long j, int i2, int i3, float f, long j2);

    public static native void nglProgramPathFragmentInputGenNV(int i, int i2, int i3, int i4, long j);

    public static native void nglGetPathParameterivNV(int i, int i2, long j);

    public static native void nglGetPathParameterfvNV(int i, int i2, long j);

    public static native void nglGetPathCommandsNV(int i, long j);

    public static native void nglGetPathCoordsNV(int i, long j);

    public static native void nglGetPathDashArrayNV(int i, long j);

    public static native void nglGetPathMetricsNV(int i, int i2, int i3, long j, int i4, int i5, long j2);

    public static native void nglGetPathMetricRangeNV(int i, int i2, int i3, int i4, long j);

    public static native void nglGetPathSpacingNV(int i, int i2, int i3, long j, int i4, float f, float f2, int i5, long j2);

    public static native void nglGetPathColorGenivNV(int i, int i2, long j);

    public static native void nglGetPathColorGenfvNV(int i, int i2, long j);

    public static native void nglGetPathTexGenivNV(int i, int i2, long j);

    public static native void nglGetPathTexGenfvNV(int i, int i2, long j);

    @NativeType("GLboolean")
    public static native boolean glIsPointInFillPathNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    @NativeType("GLboolean")
    public static native boolean glIsPointInStrokePathNV(@NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2);

    @NativeType("GLfloat")
    public static native float glGetPathLengthNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3);

    public static native boolean nglPointAlongPathNV(int i, int i2, int i3, float f, long j, long j2, long j3, long j4);

    public static native void nglMatrixLoad3x2fNV(int i, long j);

    public static native void nglMatrixLoad3x3fNV(int i, long j);

    public static native void nglMatrixLoadTranspose3x3fNV(int i, long j);

    public static native void nglMatrixMult3x2fNV(int i, long j);

    public static native void nglMatrixMult3x3fNV(int i, long j);

    public static native void nglMatrixMultTranspose3x3fNV(int i, long j);

    public static native void nglGetProgramResourcefvNV(int i, int i2, int i3, int i4, long j, int i5, long j2, long j3);

    static {
        GL.initialize();
    }

    protected NVPathRendering() {
        throw new UnsupportedOperationException();
    }

    public static void glPathCommandsNV(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer2) {
        nglPathCommandsNV(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer2));
    }

    public static void glPathCommandsNV(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglPathCommandsNV(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), (int) ((shortBuffer.remaining() << 1) >> GLChecks.typeToByteShift(i2)), i2, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glPathCommandsNV(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglPathCommandsNV(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), (int) ((floatBuffer.remaining() << 2) >> GLChecks.typeToByteShift(i2)), i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathCoordsNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglPathCoordsNV(i, byteBuffer.remaining() >> GLChecks.typeToByteShift(i2), i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glPathCoordsNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglPathCoordsNV(i, (int) ((shortBuffer.remaining() << 1) >> GLChecks.typeToByteShift(i2)), i2, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glPathCoordsNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglPathCoordsNV(i, (int) ((floatBuffer.remaining() << 2) >> GLChecks.typeToByteShift(i2)), i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathSubCommandsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer2) {
        nglPathSubCommandsNV(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), byteBuffer2.remaining() >> GLChecks.typeToByteShift(i4), i4, MemoryUtil.memAddress(byteBuffer2));
    }

    public static void glPathSubCommandsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i4, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglPathSubCommandsNV(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), (int) ((shortBuffer.remaining() << 1) >> GLChecks.typeToByteShift(i4)), i4, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glPathSubCommandsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i4, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglPathSubCommandsNV(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), (int) ((floatBuffer.remaining() << 2) >> GLChecks.typeToByteShift(i4)), i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathSubCoordsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglPathSubCoordsNV(i, i2, byteBuffer.remaining() >> GLChecks.typeToByteShift(i3), i3, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glPathSubCoordsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") ShortBuffer shortBuffer) {
        nglPathSubCoordsNV(i, i2, (int) ((shortBuffer.remaining() << 1) >> GLChecks.typeToByteShift(i3)), i3, MemoryUtil.memAddress(shortBuffer));
    }

    public static void glPathSubCoordsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") FloatBuffer floatBuffer) {
        nglPathSubCoordsNV(i, i2, (int) ((floatBuffer.remaining() << 2) >> GLChecks.typeToByteShift(i3)), i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathStringNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglPathStringNV(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glPathGlyphsNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i3, @NativeType("GLenum") int i4, @NativeType("void const *") ByteBuffer byteBuffer2, @NativeType("GLenum") int i5, @NativeType("GLuint") int i6, @NativeType("GLfloat") float f) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglPathGlyphsNV(i, i2, MemoryUtil.memAddress(byteBuffer), i3, byteBuffer2.remaining() / charcodeTypeToBytes(i4), i4, MemoryUtil.memAddress(byteBuffer2), i5, i6, f);
    }

    public static void glPathGlyphRangeNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i3, @NativeType("GLuint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLenum") int i6, @NativeType("GLuint") int i7, @NativeType("GLfloat") float f) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglPathGlyphRangeNV(i, i2, MemoryUtil.memAddress(byteBuffer), i3, i4, i5, i6, i7, f);
    }

    @NativeType("GLenum")
    public static int glPathGlyphIndexArrayNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i3, @NativeType("GLuint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLuint") int i6, @NativeType("GLfloat") float f) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglPathGlyphIndexArrayNV(i, i2, MemoryUtil.memAddress(byteBuffer), i3, i4, i5, i6, f);
    }

    @NativeType("GLenum")
    public static int glPathMemoryGlyphIndexArrayNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLsizei") int i3, @NativeType("GLuint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLuint") int i6, @NativeType("GLfloat") float f) {
        return nglPathMemoryGlyphIndexArrayNV(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), i3, i4, i5, i6, f);
    }

    public static void glWeightPathsNV(@NativeType("GLuint") int i, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, intBuffer.remaining());
        }
        nglWeightPathsNV(i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glTransformPathNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, transformTypeToElements(i3));
        }
        nglTransformPathNV(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglPathParameterivNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void glPathParameterfvNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglPathParameterfvNV(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathDashArrayNV(@NativeType("GLuint") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglPathDashArrayNV(i, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glStencilFillPathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, remaining * transformTypeToElements(i5));
        }
        nglStencilFillPathInstancedNV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glStencilStrokePathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, remaining * transformTypeToElements(i5));
        }
        nglStencilStrokePathInstancedNV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathColorGenNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, genModeToElements(i2) * colorFormatToComponents(i3));
        }
        nglPathColorGenNV(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathTexGenNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, genModeToElements(i2) * i3);
        }
        nglPathTexGenNV(i, i2, i3, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glCoverFillPathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, remaining * transformTypeToElements(i4));
        }
        nglCoverFillPathInstancedNV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glCoverStrokePathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, remaining * transformTypeToElements(i4));
        }
        nglCoverStrokePathInstancedNV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glStencilThenCoverFillPathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, remaining * transformTypeToElements(i6));
        }
        nglStencilThenCoverFillPathInstancedNV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, i6, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glStencilThenCoverStrokePathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, remaining * transformTypeToElements(i6));
        }
        nglStencilThenCoverStrokePathInstancedNV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, i6, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("GLenum")
    public static int glPathGlyphIndexRangeNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat") float f, @NativeType("GLuint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((Buffer) intBuffer, 2);
        }
        return nglPathGlyphIndexRangeNV(i, MemoryUtil.memAddress(byteBuffer), i2, i3, f, MemoryUtil.memAddress(intBuffer));
    }

    public static void glProgramPathFragmentInputGenNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, genModeToElements(i3) * i4);
        }
        nglProgramPathFragmentInputGenNV(i, i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPathParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetPathParameterivNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetPathParameteriNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetPathParameterivNV(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPathParameterfvNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetPathParameterfvNV(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetPathParameterfNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetPathParameterfvNV(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPathCommandsNV(@NativeType("GLuint") int i, @NativeType("GLubyte *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, glGetPathParameteriNV(i, GL_PATH_COMMAND_COUNT_NV));
        }
        nglGetPathCommandsNV(i, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetPathCoordsNV(@NativeType("GLuint") int i, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) floatBuffer, glGetPathParameteriNV(i, GL_PATH_COORD_COUNT_NV));
        }
        nglGetPathCoordsNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPathDashArrayNV(@NativeType("GLuint") int i, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) floatBuffer, glGetPathParameteriNV(i, GL_PATH_DASH_ARRAY_COUNT_NV));
        }
        nglGetPathDashArrayNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPathMetricsNV(@NativeType("GLbitfield") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i2);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, remaining * (i4 == 0 ? Integer.bitCount(i) : i4 >> 2));
        }
        nglGetPathMetricsNV(i, remaining, i2, MemoryUtil.memAddress(byteBuffer), i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPathMetricRangeNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, i3 * (i4 == 0 ? Integer.bitCount(i) : i4 >> 2));
        }
        nglGetPathMetricRangeNV(i, i2, i3, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPathSpacingNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i3, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLenum") int i4, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i2);
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, (remaining - 1) * (i4 == 37006 ? 1 : 2));
        }
        nglGetPathSpacingNV(i, remaining, i2, MemoryUtil.memAddress(byteBuffer), i3, f, f2, i4, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetPathColorGenivNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetPathColorGenivNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetPathColorGeniNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetPathColorGenivNV(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPathColorGenfvNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetPathColorGenfvNV(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetPathColorGenfNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetPathColorGenfvNV(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPathTexGenivNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetPathTexGenivNV(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glGetPathTexGeniNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglGetPathTexGenivNV(i, i2, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetPathTexGenfvNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nglGetPathTexGenfvNV(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("void")
    public static float glGetPathTexGenfNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            FloatBuffer callocFloat = stackGet.callocFloat(1);
            nglGetPathTexGenfvNV(i, i2, MemoryUtil.memAddress(callocFloat));
            return callocFloat.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLboolean")
    public static boolean glPointAlongPathNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLfloat") float f, @NativeType("GLfloat *") FloatBuffer floatBuffer, @NativeType("GLfloat *") FloatBuffer floatBuffer2, @NativeType("GLfloat *") FloatBuffer floatBuffer3, @NativeType("GLfloat *") FloatBuffer floatBuffer4) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) floatBuffer, 1);
            Checks.checkSafe((Buffer) floatBuffer2, 1);
            Checks.checkSafe((Buffer) floatBuffer3, 1);
            Checks.checkSafe((Buffer) floatBuffer4, 1);
        }
        return nglPointAlongPathNV(i, i2, i3, f, MemoryUtil.memAddressSafe(floatBuffer), MemoryUtil.memAddressSafe(floatBuffer2), MemoryUtil.memAddressSafe(floatBuffer3), MemoryUtil.memAddressSafe(floatBuffer4));
    }

    public static void glMatrixLoad3x2fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 6);
        }
        nglMatrixLoad3x2fNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixLoad3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 9);
        }
        nglMatrixLoad3x3fNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixLoadTranspose3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 9);
        }
        nglMatrixLoadTranspose3x3fNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixMult3x2fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 6);
        }
        nglMatrixMult3x2fNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixMult3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 9);
        }
        nglMatrixMult3x3fNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glMatrixMultTranspose3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 9);
        }
        nglMatrixMultTranspose3x3fNV(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetProgramResourcefvNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") IntBuffer intBuffer, @NativeType("GLsizei *") IntBuffer intBuffer2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer2, 1);
        }
        nglGetProgramResourcefvNV(i, i2, i3, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), floatBuffer.remaining(), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPathCommandsNV(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glPathCommandsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), sArr.length, i2, sArr, j);
    }

    public static void glPathCommandsNV(@NativeType("GLuint") int i, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i2, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glPathCommandsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), fArr.length, i2, fArr, j);
    }

    public static void glPathCoordsNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glPathCoordsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, sArr.length, i2, sArr, j);
    }

    public static void glPathCoordsNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glPathCoordsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length, i2, fArr, j);
    }

    public static void glPathSubCommandsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i4, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glPathSubCommandsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), sArr.length, i4, sArr, j);
    }

    public static void glPathSubCommandsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLubyte const *") ByteBuffer byteBuffer, @NativeType("GLenum") int i4, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glPathSubCommandsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPPV(i, i2, i3, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer), fArr.length, i4, fArr, j);
    }

    public static void glPathSubCoordsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") short[] sArr) {
        long j = GL.getICD().glPathSubCoordsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, sArr.length, i3, sArr, j);
    }

    public static void glPathSubCoordsNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("void const *") float[] fArr) {
        long j = GL.getICD().glPathSubCoordsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length, i3, fArr, j);
    }

    public static void glWeightPathsNV(@NativeType("GLuint") int i, @NativeType("GLuint const *") int[] iArr, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glWeightPathsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, iArr.length);
        }
        JNI.callPPV(i, iArr.length, iArr, fArr, j);
    }

    public static void glTransformPathNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glTransformPathNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, transformTypeToElements(i3));
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glPathParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint const *") int[] iArr) {
        long j = GL.getICD().glPathParameterivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glPathParameterfvNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPathParameterfvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glPathDashArrayNV(@NativeType("GLuint") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPathDashArrayNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, fArr.length, fArr, j);
    }

    public static void glStencilFillPathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glStencilFillPathInstancedNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, remaining * transformTypeToElements(i5));
        }
        JNI.callPPV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, fArr, j);
    }

    public static void glStencilStrokePathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glStencilStrokePathInstancedNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, remaining * transformTypeToElements(i5));
        }
        JNI.callPPV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, fArr, j);
    }

    public static void glPathColorGenNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPathColorGenNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, genModeToElements(i2) * colorFormatToComponents(i3));
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glPathTexGenNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPathTexGenNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, genModeToElements(i2) * i3);
        }
        JNI.callPV(i, i2, i3, fArr, j);
    }

    public static void glCoverFillPathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glCoverFillPathInstancedNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, remaining * transformTypeToElements(i4));
        }
        JNI.callPPV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, fArr, j);
    }

    public static void glCoverStrokePathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glCoverStrokePathInstancedNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, remaining * transformTypeToElements(i4));
        }
        JNI.callPPV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, fArr, j);
    }

    public static void glStencilThenCoverFillPathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glStencilThenCoverFillPathInstancedNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, remaining * transformTypeToElements(i6));
        }
        JNI.callPPV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, i6, fArr, j);
    }

    public static void glStencilThenCoverStrokePathInstancedNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLuint") int i4, @NativeType("GLenum") int i5, @NativeType("GLenum") int i6, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glStencilThenCoverStrokePathInstancedNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, remaining * transformTypeToElements(i6));
        }
        JNI.callPPV(remaining, i, MemoryUtil.memAddress(byteBuffer), i2, i3, i4, i5, i6, fArr, j);
    }

    @NativeType("GLenum")
    public static int glPathGlyphIndexRangeNV(@NativeType("GLenum") int i, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLbitfield") int i2, @NativeType("GLuint") int i3, @NativeType("GLfloat") float f, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glPathGlyphIndexRangeNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNT1(byteBuffer);
            Checks.check(iArr, 2);
        }
        return JNI.callPPI(i, MemoryUtil.memAddress(byteBuffer), i2, i3, f, iArr, j);
    }

    public static void glProgramPathFragmentInputGenNV(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLint") int i4, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramPathFragmentInputGenNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, genModeToElements(i3) * i4);
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glGetPathParameterivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetPathParameterivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetPathParameterfvNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathParameterfvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetPathCoordsNV(@NativeType("GLuint") int i, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathCoordsNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            if (Checks.DEBUG) {
                Checks.check(fArr, glGetPathParameteriNV(i, GL_PATH_COORD_COUNT_NV));
            }
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glGetPathDashArrayNV(@NativeType("GLuint") int i, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathDashArrayNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            if (Checks.DEBUG) {
                Checks.check(fArr, glGetPathParameteriNV(i, GL_PATH_DASH_ARRAY_COUNT_NV));
            }
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glGetPathMetricsNV(@NativeType("GLbitfield") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathMetricsNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i2);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, remaining * (i4 == 0 ? Integer.bitCount(i) : i4 >> 2));
        }
        JNI.callPPV(i, remaining, i2, MemoryUtil.memAddress(byteBuffer), i3, i4, fArr, j);
    }

    public static void glGetPathMetricRangeNV(@NativeType("GLbitfield") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathMetricRangeNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, i3 * (i4 == 0 ? Integer.bitCount(i) : i4 >> 2));
        }
        JNI.callPV(i, i2, i3, i4, fArr, j);
    }

    public static void glGetPathSpacingNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("void const *") ByteBuffer byteBuffer, @NativeType("GLuint") int i3, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLenum") int i4, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathSpacingNV;
        int remaining = byteBuffer.remaining() / pathNameTypeToBytes(i2);
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, (remaining - 1) * (i4 == 37006 ? 1 : 2));
        }
        JNI.callPPV(i, remaining, i2, MemoryUtil.memAddress(byteBuffer), i3, f, f2, i4, fArr, j);
    }

    public static void glGetPathColorGenivNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetPathColorGenivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetPathColorGenfvNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathColorGenfvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    public static void glGetPathTexGenivNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        long j = GL.getICD().glGetPathTexGenivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPV(i, i2, iArr, j);
    }

    public static void glGetPathTexGenfvNV(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetPathTexGenfvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.callPV(i, i2, fArr, j);
    }

    @NativeType("GLboolean")
    public static boolean glPointAlongPathNV(@NativeType("GLuint") int i, @NativeType("GLsizei") int i2, @NativeType("GLsizei") int i3, @NativeType("GLfloat") float f, @NativeType("GLfloat *") float[] fArr, @NativeType("GLfloat *") float[] fArr2, @NativeType("GLfloat *") float[] fArr3, @NativeType("GLfloat *") float[] fArr4) {
        long j = GL.getICD().glPointAlongPathNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(fArr, 1);
            Checks.checkSafe(fArr2, 1);
            Checks.checkSafe(fArr3, 1);
            Checks.checkSafe(fArr4, 1);
        }
        return JNI.callPPPPZ(i, i2, i3, f, fArr, fArr2, fArr3, fArr4, j);
    }

    public static void glMatrixLoad3x2fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixLoad3x2fNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 6);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixLoad3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixLoad3x3fNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 9);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixLoadTranspose3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixLoadTranspose3x3fNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 9);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixMult3x2fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixMult3x2fNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 6);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixMult3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixMult3x3fNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 9);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glMatrixMultTranspose3x3fNV(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glMatrixMultTranspose3x3fNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 9);
        }
        JNI.callPV(i, fArr, j);
    }

    public static void glGetProgramResourcefvNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum const *") int[] iArr, @NativeType("GLsizei *") int[] iArr2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetProgramResourcefvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr2, 1);
        }
        JNI.callPPPV(i, i2, i3, iArr.length, iArr, fArr.length, iArr2, fArr, j);
    }

    private static int charcodeTypeToBytes(int i) {
        switch (i) {
            case 5121:
            case GL_UTF8_NV /* 37018 */:
                return 1;
            case 5123:
            case 5127:
            case GL_UTF16_NV /* 37019 */:
                return 2;
            case 5125:
            case 5129:
                return 4;
            case 5128:
                return 3;
            default:
                throw new IllegalArgumentException(String.format("Unsupported charcode type: 0x%X", Integer.valueOf(i)));
        }
    }

    private static int pathNameTypeToBytes(int i) {
        switch (i) {
            case 5120:
            case 5121:
            case GL_UTF8_NV /* 37018 */:
                return 1;
            case 5122:
            case 5123:
            case 5127:
            case GL_UTF16_NV /* 37019 */:
                return 2;
            case 5124:
            case 5125:
            case 5129:
                return 4;
            case 5128:
                return 3;
            default:
                throw new IllegalArgumentException(String.format("Unsupported path name type: 0x%X", Integer.valueOf(i)));
        }
    }

    private static int transformTypeToElements(int i) {
        switch (i) {
            case 0:
                return 0;
            case GL_TRANSLATE_X_NV /* 37006 */:
            case GL_TRANSLATE_Y_NV /* 37007 */:
                return 1;
            case GL_TRANSLATE_2D_NV /* 37008 */:
                return 2;
            case GL_TRANSLATE_3D_NV /* 37009 */:
                return 3;
            case GL_AFFINE_2D_NV /* 37010 */:
            case GL_TRANSPOSE_AFFINE_2D_NV /* 37014 */:
                return 6;
            case GL_AFFINE_3D_NV /* 37012 */:
            case GL_TRANSPOSE_AFFINE_3D_NV /* 37016 */:
                return 12;
            default:
                throw new IllegalArgumentException(String.format("Unsupported transform type: 0x%X", Integer.valueOf(i)));
        }
    }

    private static int colorFormatToComponents(int i) {
        switch (i) {
            case 6406:
            case 6409:
            case GL11.GL_INTENSITY /* 32841 */:
                return 1;
            case 6407:
                return 3;
            case 6408:
                return 4;
            case 6410:
                return 2;
            default:
                throw new IllegalArgumentException(String.format("Unsupported colorFormat specified: 0x%X", Integer.valueOf(i)));
        }
    }

    private static int genModeToElements(int i) {
        switch (i) {
            case 0:
                return 0;
            case 9216:
                return 4;
            case 9217:
            case GL_PATH_OBJECT_BOUNDING_BOX_NV /* 37002 */:
                return 3;
            case 34166:
                return 1;
            default:
                throw new IllegalArgumentException(String.format("Unsupported genMode specified: 0x%X", Integer.valueOf(i)));
        }
    }
}
