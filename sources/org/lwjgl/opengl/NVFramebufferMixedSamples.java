package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVFramebufferMixedSamples.class */
public class NVFramebufferMixedSamples {
    public static final int GL_RASTER_MULTISAMPLE_EXT = 37671;
    public static final int GL_COVERAGE_MODULATION_TABLE_NV = 37681;
    public static final int GL_RASTER_SAMPLES_EXT = 37672;
    public static final int GL_MAX_RASTER_SAMPLES_EXT = 37673;
    public static final int GL_RASTER_FIXED_SAMPLE_LOCATIONS_EXT = 37674;
    public static final int GL_MULTISAMPLE_RASTERIZATION_ALLOWED_EXT = 37675;
    public static final int GL_EFFECTIVE_RASTER_SAMPLES_EXT = 37676;
    public static final int GL_COLOR_SAMPLES_NV = 36384;
    public static final int GL_DEPTH_SAMPLES_NV = 37677;
    public static final int GL_STENCIL_SAMPLES_NV = 37678;
    public static final int GL_MIXED_DEPTH_SAMPLES_SUPPORTED_NV = 37679;
    public static final int GL_MIXED_STENCIL_SAMPLES_SUPPORTED_NV = 37680;
    public static final int GL_COVERAGE_MODULATION_NV = 37682;
    public static final int GL_COVERAGE_MODULATION_TABLE_SIZE_NV = 37683;

    public static native void nglCoverageModulationTableNV(int i, long j);

    public static native void nglGetCoverageModulationTableNV(int i, long j);

    public static native void glCoverageModulationNV(@NativeType("GLenum") int i);

    static {
        GL.initialize();
    }

    protected NVFramebufferMixedSamples() {
        throw new UnsupportedOperationException();
    }

    public static void glRasterSamplesEXT(@NativeType("GLuint") int i, @NativeType("GLboolean") boolean z) {
        EXTRasterMultisample.glRasterSamplesEXT(i, z);
    }

    public static void glCoverageModulationTableNV(@NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglCoverageModulationTableNV(floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetCoverageModulationTableNV(@NativeType("GLfloat *") FloatBuffer floatBuffer) {
        nglGetCoverageModulationTableNV(floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void glCoverageModulationTableNV(@NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glCoverageModulationTableNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(fArr.length, fArr, j);
    }

    public static void glGetCoverageModulationTableNV(@NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetCoverageModulationTableNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(fArr.length, fArr, j);
    }
}
