package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDSamplePositions.class */
public class AMDSamplePositions {
    public static final int GL_SUBSAMPLE_DISTANCE_AMD = 34879;

    public static native void nglSetMultisamplefvAMD(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected AMDSamplePositions() {
        throw new UnsupportedOperationException();
    }

    public static void glSetMultisamplefvAMD(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglSetMultisamplefvAMD(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glSetMultisamplefvAMD(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glSetMultisamplefvAMD;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(i, i2, fArr, j);
    }
}
