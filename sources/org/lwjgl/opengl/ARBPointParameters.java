package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBPointParameters.class */
public class ARBPointParameters {
    public static final int GL_POINT_SIZE_MIN_ARB = 33062;
    public static final int GL_POINT_SIZE_MAX_ARB = 33063;
    public static final int GL_POINT_FADE_THRESHOLD_SIZE_ARB = 33064;
    public static final int GL_POINT_DISTANCE_ATTENUATION_ARB = 33065;

    public static native void glPointParameterfARB(@NativeType("GLenum") int i, @NativeType("GLfloat") float f);

    public static native void nglPointParameterfvARB(int i, long j);

    static {
        GL.initialize();
    }

    protected ARBPointParameters() {
        throw new UnsupportedOperationException();
    }

    public static void glPointParameterfvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 3);
        }
        nglPointParameterfvARB(i, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glPointParameterfvARB(@NativeType("GLenum") int i, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glPointParameterfvARB;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 3);
        }
        JNI.callPV(i, fArr, j);
    }
}
