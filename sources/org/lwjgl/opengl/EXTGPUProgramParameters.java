package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTGPUProgramParameters.class */
public class EXTGPUProgramParameters {
    public static native void nglProgramEnvParameters4fvEXT(int i, int i2, int i3, long j);

    public static native void nglProgramLocalParameters4fvEXT(int i, int i2, int i3, long j);

    static {
        GL.initialize();
    }

    protected EXTGPUProgramParameters() {
        throw new UnsupportedOperationException();
    }

    public static void glProgramEnvParameters4fvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramEnvParameters4fvEXT(i, i2, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramLocalParameters4fvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") FloatBuffer floatBuffer) {
        nglProgramLocalParameters4fvEXT(i, i2, floatBuffer.remaining() >> 2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glProgramEnvParameters4fvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramEnvParameters4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 2, fArr, j);
    }

    public static void glProgramLocalParameters4fvEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat const *") float[] fArr) {
        long j = GL.getICD().glProgramLocalParameters4fvEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, fArr.length >> 2, fArr, j);
    }
}
