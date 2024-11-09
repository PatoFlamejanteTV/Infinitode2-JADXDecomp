package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL21.class */
public class GL21 extends GL20 {
    public static final int GL_CURRENT_RASTER_SECONDARY_COLOR = 33887;
    public static final int GL_FLOAT_MAT2x3 = 35685;
    public static final int GL_FLOAT_MAT2x4 = 35686;
    public static final int GL_FLOAT_MAT3x2 = 35687;
    public static final int GL_FLOAT_MAT3x4 = 35688;
    public static final int GL_FLOAT_MAT4x2 = 35689;
    public static final int GL_FLOAT_MAT4x3 = 35690;
    public static final int GL_PIXEL_PACK_BUFFER = 35051;
    public static final int GL_PIXEL_UNPACK_BUFFER = 35052;
    public static final int GL_PIXEL_PACK_BUFFER_BINDING = 35053;
    public static final int GL_PIXEL_UNPACK_BUFFER_BINDING = 35055;
    public static final int GL_SRGB = 35904;
    public static final int GL_SRGB8 = 35905;
    public static final int GL_SRGB_ALPHA = 35906;
    public static final int GL_SRGB8_ALPHA8 = 35907;
    public static final int GL_SLUMINANCE_ALPHA = 35908;
    public static final int GL_SLUMINANCE8_ALPHA8 = 35909;
    public static final int GL_SLUMINANCE = 35910;
    public static final int GL_SLUMINANCE8 = 35911;
    public static final int GL_COMPRESSED_SRGB = 35912;
    public static final int GL_COMPRESSED_SRGB_ALPHA = 35913;
    public static final int GL_COMPRESSED_SLUMINANCE = 35914;
    public static final int GL_COMPRESSED_SLUMINANCE_ALPHA = 35915;

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL21() {
        throw new UnsupportedOperationException();
    }

    public static void nglUniformMatrix2x3fv(int i, int i2, boolean z, long j) {
        GL21C.nglUniformMatrix2x3fv(i, i2, z, j);
    }

    public static void glUniformMatrix2x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL21C.glUniformMatrix2x3fv(i, z, floatBuffer);
    }

    public static void nglUniformMatrix3x2fv(int i, int i2, boolean z, long j) {
        GL21C.nglUniformMatrix3x2fv(i, i2, z, j);
    }

    public static void glUniformMatrix3x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL21C.glUniformMatrix3x2fv(i, z, floatBuffer);
    }

    public static void nglUniformMatrix2x4fv(int i, int i2, boolean z, long j) {
        GL21C.nglUniformMatrix2x4fv(i, i2, z, j);
    }

    public static void glUniformMatrix2x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL21C.glUniformMatrix2x4fv(i, z, floatBuffer);
    }

    public static void nglUniformMatrix4x2fv(int i, int i2, boolean z, long j) {
        GL21C.nglUniformMatrix4x2fv(i, i2, z, j);
    }

    public static void glUniformMatrix4x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL21C.glUniformMatrix4x2fv(i, z, floatBuffer);
    }

    public static void nglUniformMatrix3x4fv(int i, int i2, boolean z, long j) {
        GL21C.nglUniformMatrix3x4fv(i, i2, z, j);
    }

    public static void glUniformMatrix3x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL21C.glUniformMatrix3x4fv(i, z, floatBuffer);
    }

    public static void nglUniformMatrix4x3fv(int i, int i2, boolean z, long j) {
        GL21C.nglUniformMatrix4x3fv(i, i2, z, j);
    }

    public static void glUniformMatrix4x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        GL21C.glUniformMatrix4x3fv(i, z, floatBuffer);
    }

    public static void glUniformMatrix2x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL21C.glUniformMatrix2x3fv(i, z, fArr);
    }

    public static void glUniformMatrix3x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL21C.glUniformMatrix3x2fv(i, z, fArr);
    }

    public static void glUniformMatrix2x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL21C.glUniformMatrix2x4fv(i, z, fArr);
    }

    public static void glUniformMatrix4x2fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL21C.glUniformMatrix4x2fv(i, z, fArr);
    }

    public static void glUniformMatrix3x4fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL21C.glUniformMatrix3x4fv(i, z, fArr);
    }

    public static void glUniformMatrix4x3fv(@NativeType("GLint") int i, @NativeType("GLboolean") boolean z, @NativeType("GLfloat const *") float[] fArr) {
        GL21C.glUniformMatrix4x3fv(i, z, fArr);
    }
}
