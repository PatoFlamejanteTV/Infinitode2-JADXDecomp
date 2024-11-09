package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBSampleLocations.class */
public class ARBSampleLocations {
    public static final int GL_SAMPLE_LOCATION_SUBPIXEL_BITS_ARB = 37693;
    public static final int GL_SAMPLE_LOCATION_PIXEL_GRID_WIDTH_ARB = 37694;
    public static final int GL_SAMPLE_LOCATION_PIXEL_GRID_HEIGHT_ARB = 37695;
    public static final int GL_PROGRAMMABLE_SAMPLE_LOCATION_TABLE_SIZE_ARB = 37696;
    public static final int GL_FRAMEBUFFER_PROGRAMMABLE_SAMPLE_LOCATIONS_ARB = 37698;
    public static final int GL_FRAMEBUFFER_SAMPLE_LOCATION_PIXEL_GRID_ARB = 37699;

    public static native void nglFramebufferSampleLocationsfvARB(int i, int i2, int i3, long j);

    public static native void nglNamedFramebufferSampleLocationsfvARB(int i, int i2, int i3, long j);

    public static native void glEvaluateDepthValuesARB();

    static {
        GL.initialize();
    }

    protected ARBSampleLocations() {
        throw new UnsupportedOperationException();
    }

    public static void glFramebufferSampleLocationsfvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglFramebufferSampleLocationsfvARB(i, i2, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glNamedFramebufferSampleLocationsfvARB(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglNamedFramebufferSampleLocationsfvARB(i, i2, floatBuffer.remaining() >> 1, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glFramebufferSampleLocationsfvARB(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glFramebufferSampleLocationsfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 1, fArr, j);
    }

    public static void glNamedFramebufferSampleLocationsfvARB(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glNamedFramebufferSampleLocationsfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 1, fArr, j);
    }
}
