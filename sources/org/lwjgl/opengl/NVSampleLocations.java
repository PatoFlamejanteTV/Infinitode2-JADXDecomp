package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVSampleLocations.class */
public class NVSampleLocations {
    public static final int GL_SAMPLE_LOCATION_SUBPIXEL_BITS_NV = 37693;
    public static final int GL_SAMPLE_LOCATION_PIXEL_GRID_WIDTH_NV = 37694;
    public static final int GL_SAMPLE_LOCATION_PIXEL_GRID_HEIGHT_NV = 37695;
    public static final int GL_PROGRAMMABLE_SAMPLE_LOCATION_TABLE_SIZE_NV = 37696;
    public static final int GL_SAMPLE_LOCATION_NV = 36432;
    public static final int GL_PROGRAMMABLE_SAMPLE_LOCATION_NV = 37697;
    public static final int GL_FRAMEBUFFER_PROGRAMMABLE_SAMPLE_LOCATIONS_NV = 37698;
    public static final int GL_FRAMEBUFFER_SAMPLE_LOCATION_PIXEL_GRID_NV = 37699;

    public static native void nglFramebufferSampleLocationsfvNV(int i, int i2, int i3, long j);

    public static native void nglNamedFramebufferSampleLocationsfvNV(int i, int i2, int i3, long j);

    public static native void glResolveDepthValuesNV();

    static {
        GL.initialize();
    }

    protected NVSampleLocations() {
        throw new UnsupportedOperationException();
    }

    public static void glFramebufferSampleLocationsfvNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglFramebufferSampleLocationsfvNV(i, i2, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glNamedFramebufferSampleLocationsfvNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglNamedFramebufferSampleLocationsfvNV(i, i2, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glFramebufferSampleLocationsfvNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glFramebufferSampleLocationsfvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 1, fArr, j);
    }

    public static void glNamedFramebufferSampleLocationsfvNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glNamedFramebufferSampleLocationsfvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 1, fArr, j);
    }
}
